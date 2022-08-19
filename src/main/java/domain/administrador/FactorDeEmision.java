package domain.administrador;

import domain.medicion.TipoConsumo;
import lombok.Getter;

@Getter
public class FactorDeEmision {
  Double factor;
  UnidadEquivalenteCarbono unidadEqivalenteCarbono;

  public FactorDeEmision(Double factor, UnidadEquivalenteCarbono unidadEqivalenteCarbono) {
    this.factor = factor;
    this.unidadEqivalenteCarbono = unidadEqivalenteCarbono;
  }

  public void pasajeA(UnidadEquivalenteCarbono unidadDestino) {
    this.factor = this.unidadEqivalenteCarbono.equivalenciaA(this.factor, unidadDestino);
    this.unidadEqivalenteCarbono = unidadDestino;
  }

}
