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
    //TODO: dependiendo de la unidad pasar a kilometros
    return valor;
  }
}
