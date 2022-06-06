package domain.transporte;

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
  /*
  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte) {
    return (this.tipoTransporte.equals(unTransporte.getTipoTransporte()));
  }
  */

  public boolean esDeTipo(TipoTransporte tipoTransporte) {
    return this.tipoTransporte.equals(tipoTransporte);
  }
}
