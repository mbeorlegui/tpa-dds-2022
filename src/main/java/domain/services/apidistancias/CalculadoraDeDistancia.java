package domain.services.apidistancias;

import domain.ubicacion.Ubicacion;

public interface CalculadoraDeDistancia {

  public double distancia(Ubicacion origen, Ubicacion destino);

}
