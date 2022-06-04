package domain.transporte;

import lombok.Getter;
import lombok.Setter;

public class Pie implements Transporte {
  @Getter
  @Setter
  private TipoTransporte tipoTransporte;

  public Pie() {
    setTipoTransporte(TipoTransporte.PIE);
  }

  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte) {
    return (this.tipoTransporte.equals(unTransporte.getTipoTransporte()));
  }
}
