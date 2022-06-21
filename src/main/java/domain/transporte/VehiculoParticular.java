package domain.transporte;

import domain.ubicacion.Ubicacion;
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

  public VehiculoParticular(TipoDeVehiculo tipoDeVehiculo, Combustible combustible) {
    this.tipoDeVehiculo = tipoDeVehiculo;
    this.combustible = combustible;
    setTipoTransporte(TipoTransporte.PARTICULAR);
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

  public boolean esDeTipo(TipoTransporte tipoTransporte) {
    return this.tipoTransporte.equals(tipoTransporte);
  }
}
