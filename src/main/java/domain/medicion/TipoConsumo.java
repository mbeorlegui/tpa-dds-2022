package domain.medicion;

import domain.administrador.FactorDeEmision;
import domain.administrador.UnidadEquivalenteCarbono;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.Embedded;

@Getter
@Entity(name = "TipoConsumo")
@Table(name = "tipo_consumo")
public class TipoConsumo {
  @Id
  @GeneratedValue
  @Column(name = "tipo_consumo_id")
  private long id;
  @Enumerated
  public Actividad actividad;
  @Enumerated
  public Alcance alcance;
  @Enumerated
  public Unidad unidad;
  @Setter
  @Embedded
  public FactorDeEmision factorDeEmision;
  public String nombre;

  public TipoConsumo(Actividad actividad, Alcance alcance, Unidad unidad,
                     FactorDeEmision factorDeEmision, String nombre) {
    this.actividad = actividad;
    this.alcance = alcance;
    this.unidad = unidad;
    this.factorDeEmision = factorDeEmision;
    this.nombre = nombre;
  }

  public TipoConsumo() {}

  @Override
  public String toString() {
    return nombre;
  }

  public double calcularHuellaDeCarbono(double medicion, UnidadEquivalenteCarbono unidadDeseada) {
    return medicion * this.factorDeEmision.getFactor(unidadDeseada);
  }
}
