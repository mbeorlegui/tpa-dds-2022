package domain.transporte;

import domain.medicion.TiposConsumos;
import domain.ubicacion.Ubicacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "pie")
public class Pie extends Transporte {
  public Pie() {
    super(TiposConsumos.getInstance().hayarTipo("MEDIO_DE_TRANSPORTE"), 0.0);
    setTipoTransporte(TipoTransporte.PIE);
  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

}
