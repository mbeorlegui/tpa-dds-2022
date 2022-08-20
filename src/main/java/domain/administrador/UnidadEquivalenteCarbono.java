package domain.administrador;

import lombok.Getter;

@Getter
public enum UnidadEquivalenteCarbono {
  GRAMO(0),
  KILOGRAMO(3),
  TONELADA(6);

  private final Integer potencia10;

  UnidadEquivalenteCarbono(Integer potencia10) {
    this.potencia10 = potencia10;
  }

  public Double equivalenciaA(Double factor, UnidadEquivalenteCarbono unidadDestino) {
    int potenciaFinal = this.potencia10 - unidadDestino.getPotencia10();
    return factor * Math.pow(10, potenciaFinal);
  }
}
