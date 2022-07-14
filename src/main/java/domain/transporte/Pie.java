package domain.transporte;

import domain.medicion.Actividad;
import domain.medicion.Alcance;
import domain.medicion.TipoConsumo;
import domain.medicion.Unidad;
import domain.ubicacion.Ubicacion;

public class Pie extends Transporte {
  public Pie() {
    super(new TipoConsumo(Actividad.LOGISTICA, Alcance.OTRAS_EMISIONES, Unidad.SIN_UNIDAD,0,""),
        0.0);
    setTipoTransporte(TipoTransporte.PIE);
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

}
