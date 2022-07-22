package domain.services.apidistancias.entities;

import lombok.Getter;
import lombok.Setter;

public class ResultadoDistancia {
  @Getter
  private double valor;
  @Getter @Setter
  private String unidad;
  //TODO: cambiar a tipo UnidadDistancia

  public ResultadoDistancia(double valor, String unidad) {
    this.valor = valor;
    this.unidad = unidad;
  }

  public double obtenerKilometros() {
    //TODO: dependiendo de la unidad pasar a kilometros, rehacer y ver hacer enum de Unidad
    double valorEnKm = valor;
    if (esUnidadEnMetros()) {
      valorEnKm = valorEnKm / 1000.0;
    }
    return valorEnKm;
  }

  public boolean esUnidadEnMetros() {
    return this.unidad.equals("M") || this.unidad.equals("m");
  }
}
