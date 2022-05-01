package domain.transporte;

public abstract class Transporte {
  private TipoTransporte tipoTransporte;

  public TipoTransporte getTipoTransporte() {
    return tipoTransporte;
  }

  public void setTipoTransporte(TipoTransporte tipoTransporte) {
    this.tipoTransporte = tipoTransporte;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Transporte) {
      Transporte transporte = (Transporte) obj;
      return (this.tipoTransporte.equals(transporte.tipoTransporte));
    } else {
      return false;
    }
  }
}