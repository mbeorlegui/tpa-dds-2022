package domain.services.apidistancias;

import domain.ubicacion.Ubicacion;

public interface AdapterCalculadoraDeDistancia {

  public double distancia(Ubicacion origen, Ubicacion destino);

}
