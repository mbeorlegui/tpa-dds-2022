package domain.ApiHandler;

import domain.ubicacion.Ubicacion;

public interface CalculadoraDeDistancia {

  default double distancia(Ubicacion origen, Ubicacion destino){

    return 0;
  }
}
