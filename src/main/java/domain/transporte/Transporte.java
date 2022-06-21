package domain.transporte;

import domain.ubicacion.Ubicacion;

public interface Transporte {

  public TipoTransporte getTipoTransporte();

  public void setTipoTransporte(TipoTransporte tipoTransporte);

  public void verificarParadas(Ubicacion origen, Ubicacion destino);

  public boolean esDeTipo(TipoTransporte tipoTransporte);
}
