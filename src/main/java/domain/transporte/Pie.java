package domain.transporte;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.ubicacion.Ubicacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "pie")
public class Pie extends Transporte {
  public Pie() {
    super(null, 0.0);
    setTipoTransporte(TipoTransporte.PIE);
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }
  @Override
  public double huellaDeCarbonoDeDistancia(double distanciaRecorrida,
                                           UnidadEquivalenteCarbono unidadDeseada) {
    return 0.0;
  }

}
