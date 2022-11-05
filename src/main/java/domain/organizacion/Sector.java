package domain.organizacion;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import domain.miembro.Miembro;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "sector")
public class Sector {
  //private Organizacion organizacion;
  /*    Hago que la organizacion conozca los sectores y
      que los sectores conozcan a los miembros
  */
  @Getter
  @Id
  @GeneratedValue
  @Column(name = "sector_id")
  private long id;
  @Getter
  private String nombre;
  @Getter
  @ManyToMany
  private List<Miembro> miembros = new ArrayList<>();


  public Sector() {
  }

  public Sector(String nombre) {
    this.nombre = nombre;
  }

  public void addMiembro(Miembro miembro) {
    if (!miembros.contains(miembro)) {
      miembros.add(miembro);
    }
  }

  public void addMiembros(Miembro... miembros) {
    Arrays.stream(miembros).forEach(this::addMiembro);
  }

  public double calcularHuellaDeCarbono(Periodicidad periodicidad,
                                        UnidadEquivalenteCarbono unidadDeseada) {
    return miembros
        .stream()
        .mapToDouble(miembro -> miembro.calcularHuellaDeCarbono(periodicidad, unidadDeseada))
        .sum();
  }

}
