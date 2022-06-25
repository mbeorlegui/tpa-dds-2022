package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

public class VehiculoParticular extends Transporte {
  @Getter
  private TipoDeVehiculo tipoDeVehiculo;


  public VehiculoParticular(TipoConsumo combustible, Double combustiblePorKm,
                            TipoDeVehiculo tipoDeVehiculo) {
    super(combustible, combustiblePorKm);
    this.tipoDeVehiculo = tipoDeVehiculo;
    setTipoTransporte(TipoTransporte.PARTICULAR);
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

}
