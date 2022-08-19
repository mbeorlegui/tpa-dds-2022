package domain.administrador;

import domain.medicion.TipoConsumo;
import lombok.Getter;


public class FactorDeEmision {
  Double factor;
  @Getter
  UnidadEquivalenteCarbono unidadEqivalenteCarbono;

  public FactorDeEmision(Double factor, UnidadEquivalenteCarbono unidadEqivalenteCarbono) {
    this.factor = factor;
    this.unidadEqivalenteCarbono = unidadEqivalenteCarbono;
  }

  public Double getFactor(UnidadEquivalenteCarbono unidadDestino) {
    return this.unidadEqivalenteCarbono.equivalenciaA(this.factor, unidadDestino);
  }

  public void setUnidadEqivalenteCarbono(UnidadEquivalenteCarbono unidadDestino) {
    this.factor = this.unidadEqivalenteCarbono.equivalenciaA(this.factor, unidadDestino);
    this.unidadEqivalenteCarbono = unidadDestino;
  }

}
