package domain.transporte;

public class VehiculoParticular extends Transporte {
  private TipoDeVehiculo tipoDeVehiculo;
  private  Combustible combustible;

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
