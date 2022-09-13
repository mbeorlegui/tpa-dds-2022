package domain.trayecto;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.exceptions.NoPuedeSerTrayectoCompartidoException;
import domain.medicion.Periodicidad;
import lombok.Getter;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

@Entity
@Table(name = "trayecto")
public class Trayecto {
  @Id
  @GeneratedValue
  @Column(name = "trayecto_id")
  private long id;
  @Getter
  @Transient
  private List<Tramo> tramos;

  public Trayecto(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public Double distanciaTotal() {
    return this.tramos.stream().mapToDouble(tramo -> tramo.distanciaIntermedia()).sum();
  }

  public void verificarQuePuedaSerAsignadoAMiembros() {
    if (!this.tramosPuedenSerCompartidos()) {
      throw new NoPuedeSerTrayectoCompartidoException("No puede ser trayecto compartido");
    }
  }

  private boolean tramosPuedenSerCompartidos() {
    return this.getTramos().stream().allMatch(tramo -> {
      return tramo.puedeSerCompartido();
    });
  }

  public double huellaDeCarbonoEnPeriodo(Periodicidad periodicidad,
                                         UnidadEquivalenteCarbono unidadDeseada) {
    return periodicidad.diasTrabajados() * this.hcTramos(unidadDeseada);
  }

  private double hcTramos(UnidadEquivalenteCarbono unidadDeseada) {
    return this.tramos
        .stream()
        .mapToDouble(tramo -> tramo.huellaDeCarbono(unidadDeseada))
        .sum();
  }
}
