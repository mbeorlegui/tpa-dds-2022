package domain.medicion;

import domain.administrador.FactorDeEmision;
import domain.administrador.UnidadEquivalenteCarbono;
import lombok.Getter;
import lombok.Setter;

public class TipoConsumo {
  @Getter
  public Actividad actividad;
  @Getter
  public Alcance alcance;
  @Getter
  public Unidad unidad;
  @Getter
  @Setter
  public FactorDeEmision factorDeEmision;
  @Getter
  public String nombre;

  public TipoConsumo(Actividad actividad, Alcance alcance, Unidad unidad,
                     FactorDeEmision factorDeEmision, String nombre) {
    this.actividad = actividad;
    this.alcance = alcance;
    this.unidad = unidad;
    this.factorDeEmision = factorDeEmision;
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return nombre;
  }

  public double calcularHuellaDeCarbono(double medicion, UnidadEquivalenteCarbono unidadDeseada) {
    this.factorDeEmision.pasajeA(unidadDeseada);
    return medicion * this.factorDeEmision.getFactor();
  }
}
