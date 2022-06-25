package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;

public class Pie extends Transporte {
  public Pie() {
    super(null, null);
    setTipoTransporte(TipoTransporte.PIE);
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

}
