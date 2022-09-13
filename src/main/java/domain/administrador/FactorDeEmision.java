package domain.administrador;

import domain.medicion.TipoConsumo;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class FactorDeEmision {
  Double factor;
  @Getter
  @Enumerated
  @Column(name = "unidad_eqivalente_carbono")
  UnidadEquivalenteCarbono unidadEqivalenteCarbono;

  public FactorDeEmision(Double factor, UnidadEquivalenteCarbono unidadEqivalenteCarbono) {
    this.factor = factor;
    this.unidadEqivalenteCarbono = unidadEqivalenteCarbono;
  }

  public Double getFactor(UnidadEquivalenteCarbono unidadDestino) {
    return this.unidadEqivalenteCarbono.equivalenciaA(this.factor, unidadDestino);
  }
}
