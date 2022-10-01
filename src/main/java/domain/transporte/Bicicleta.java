package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "bicicleta")
public class Bicicleta extends Transporte {

  public Bicicleta() {
    super(null, null);
    setTipoTransporte(TipoTransporte.BICICLETA);
  }
}
