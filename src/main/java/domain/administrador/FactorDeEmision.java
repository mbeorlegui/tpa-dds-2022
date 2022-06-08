package domain.administrador;

import domain.medicion.TipoConsumo;
import lombok.Getter;

@Getter
public class FactorDeEmision {
  Integer factor;
  TipoConsumo tipoConsumo;

  public FactorDeEmision(Integer factor, TipoConsumo tipoConsumo) {
    this.factor = factor;
    this.tipoConsumo = tipoConsumo;
  }
}
