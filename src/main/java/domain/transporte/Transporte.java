package domain.transporte;

public abstract class Transporte {
  private TipoTransporte tipoTransporte;

  public TipoTransporte getTipoTransporte() {
    return tipoTransporte;
  }

  public void setTipoTransporte(TipoTransporte tipoTransporte) {
    this.tipoTransporte = tipoTransporte;
  }
}
