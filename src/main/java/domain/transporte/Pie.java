package domain.transporte;

import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

public class Pie implements Transporte {
  @Getter
  @Setter
  private TipoTransporte tipoTransporte;

  public Pie() {
    setTipoTransporte(TipoTransporte.PIE);
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

  public boolean esDeTipo(TipoTransporte tipoTransporte) {
    return this.tipoTransporte.equals(tipoTransporte);
  }
}
