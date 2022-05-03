package domain.transporte;

public abstract class Transporte {
  protected TipoTransporte tipoTransporte;

  public TipoTransporte getTipoTransporte() {
    return tipoTransporte;
  }

  public void setTipoTransporte(TipoTransporte tipoTransporte) {
    this.tipoTransporte = tipoTransporte;
  }

  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte) {
    return (this.tipoTransporte.equals(unTransporte.tipoTransporte));
  }

  public boolean esTranspportePublico() {
    return (this.tipoTransporte.equals(TipoTransporte.PUBLICO));
  }

}
