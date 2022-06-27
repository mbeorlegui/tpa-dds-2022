package domain.transporte;

import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;


public class Parada {
  @Getter
  Ubicacion ubicacion;
  @Setter
  ResultadoDistancia distanciaSiguienteParada;

  public Parada(Ubicacion ubicacion, ResultadoDistancia distanciaSiguienteParada) {
    this.ubicacion = ubicacion;
    this.distanciaSiguienteParada = distanciaSiguienteParada;
  }

  public double getDistanciaSiguienteParada() {
    return distanciaSiguienteParada.obtenerMetros();
  }

  public String getUnidadDistancia() {
    return distanciaSiguienteParada.getUnidad();
  }

  public void modificarDistanciaSiguienteParada(Parada nuevaParada) {
    ResultadoDistancia nuevaDistacia = new ResultadoDistancia(
        this.getDistanciaSiguienteParada() - nuevaParada.getDistanciaSiguienteParada(),
        this.getUnidadDistancia());
    this.setDistanciaSiguienteParada(nuevaDistacia);
  }
}
