package domain.transporte;

import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.ubicacion.Ubicacion;
import lombok.Getter;


public class Parada {
  @Getter
  Ubicacion ubicacion;
  ResultadoDistancia distanciaSiguienteParada;

  public Parada(Ubicacion ubicacion, ResultadoDistancia distanciaSiguienteParada) {
    this.ubicacion = ubicacion;
    this.distanciaSiguienteParada = distanciaSiguienteParada;
  }

  public double getDistanciaSiguienteParada() {
    return distanciaSiguienteParada.obtenerMetros();
  }
}
