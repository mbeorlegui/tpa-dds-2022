package domain.services.apidistancias;

import domain.exceptions.ErrorEnLaApiException;
import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.ubicacion.Ubicacion;

import java.io.IOException;

public class AdapterCalculadoraDeDistanciaRetrofit implements AdapterCalculadoraDeDistancia {

  @Override
  public double distancia(Ubicacion origen, Ubicacion destino) {
    ServicioDistancia servicioDistancia = ServicioDistancia.getInstancia();
    int localidadOrigenId = origen.getLocalidadID();
    String calleOrigen = origen.getCalle();
    int alturaOrigen = Integer.parseInt(origen.getAltura());
    int localidadDestinoId = destino.getLocalidadID();
    String calleDestino = destino.getCalle();
    int alturaDestino = Integer.parseInt(destino.getAltura());

    try {
      ResultadoDistancia resultadoDistancia = servicioDistancia.distancia(localidadOrigenId,
          calleOrigen,
          alturaOrigen,
          localidadDestinoId,
          calleDestino,
          alturaDestino);
      return resultadoDistancia.getValor();
    } catch (IOException e) {
      throw new ErrorEnLaApiException(e);
    }
  }
}
