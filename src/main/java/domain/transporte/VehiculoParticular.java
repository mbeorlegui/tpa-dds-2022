package domain.transporte;

import lombok.Getter;
import lombok.Setter;

public class VehiculoParticular implements Transporte {
  @Getter
  private TipoDeVehiculo tipoDeVehiculo;
  @Getter
  private Combustible combustible;
  @Setter
  @Getter
  private TipoTransporte tipoTransporte;

  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte) {
    return (this.tipoTransporte.equals(unTransporte.getTipoTransporte()));
  }

  public VehiculoParticular(TipoDeVehiculo tipoDeVehiculo, Combustible combustible) {
    this.tipoDeVehiculo = tipoDeVehiculo;
    this.combustible = combustible;
    setTipoTransporte(TipoTransporte.PARTICULAR);
  }

}
