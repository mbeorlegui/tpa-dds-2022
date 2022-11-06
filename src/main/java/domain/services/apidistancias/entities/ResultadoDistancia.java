package domain.services.apidistancias.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
public class ResultadoDistancia {
  @Getter
  @Column(name = "valor_distancia")
  private double valor;
  @Getter @Setter
  @Column(name = "unidad_distancia")
  private String unidad;
  //TODO: cambiar a tipo UnidadDistancia

  public ResultadoDistancia(double valor, String unidad) {
    this.valor = valor;
    this.unidad = unidad;
  }

  public ResultadoDistancia(){
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
