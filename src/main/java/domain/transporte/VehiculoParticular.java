package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "vehiculo_particular")
public class VehiculoParticular extends Transporte {
  @Getter
  @Enumerated
  @Column(name = "tipo_de_vehiculo")
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
