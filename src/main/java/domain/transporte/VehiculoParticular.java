package domain.transporte;

public class VehiculoParticular implements Transporte {
  private TipoDeVehiculo tipoDeVehiculo;
  private Combustible combustible;
  private TipoTransporte tipoTransporte;

  public TipoTransporte getTipoTransporte() {
    return tipoTransporte;
  }

  public void setTipoTransporte(TipoTransporte tipoTransporte) {
    this.tipoTransporte = tipoTransporte;
  }

  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte) {
    return (this.tipoTransporte.equals(unTransporte.getTipoTransporte()));
  }

  public VehiculoParticular(TipoDeVehiculo tipoDeVehiculo, Combustible combustible) {
    this.tipoDeVehiculo = tipoDeVehiculo;
    this.combustible = combustible;
    setTipoTransporte(TipoTransporte.PARTICULAR);
  }

  public TipoDeVehiculo getTipoDeVehiculo() {
    return tipoDeVehiculo;
  }

  public Combustible getCombustible() {
    return combustible;
  }

}
