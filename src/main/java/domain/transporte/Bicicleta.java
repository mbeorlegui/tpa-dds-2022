package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;

public class Bicicleta extends Transporte {

  public Bicicleta() {
    super(null, null);
    setTipoTransporte(TipoTransporte.BICICLETA);
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

}
