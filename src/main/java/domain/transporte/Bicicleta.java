package domain.transporte;

import domain.ubicacion.Ubicacion;
import lombok.Setter;

public class Bicicleta implements Transporte {
  @Setter
  private TipoTransporte tipoTransporte;

  public Bicicleta() {
    setTipoTransporte(TipoTransporte.BICICLETA);
  }

  public TipoTransporte getTipoTransporte() {
    return tipoTransporte;
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

  public boolean esDeTipo(TipoTransporte tipoTransporte) {
    return this.tipoTransporte.equals(tipoTransporte);
  }
}
