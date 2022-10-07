package domain.transporte;

import domain.administrador.UnidadEquivalenteCarbono;
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

  @Override
  public double huellaDeCarbonoDeDistancia(double distanciaRecorrida,
                                           UnidadEquivalenteCarbono unidadDeseada) {
    return 0.0;
  }
}
