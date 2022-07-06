package domain.medicion;

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
  public double factorDeEmision;
  @Getter
  public String nombre;

  public TipoConsumo(Actividad actividad, Alcance alcance, Unidad unidad,
                     double factorDeEmision, String nombre) {
    this.actividad = actividad;
    this.alcance = alcance;
    this.unidad = unidad;
    this.factorDeEmision = factorDeEmision;
    this.nombre = nombre;
  }

  // TODO: Analizar otros refactors

  @Override
  public String toString() {
    return nombre;
  }

  public double calcularHuellaDeCarbono(double medicion) {
    return medicion * this.factorDeEmision;
  }
}
