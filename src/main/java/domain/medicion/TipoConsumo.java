package domain.medicion;

public enum TipoConsumo {
  GAS_NATURAL,
  DIESEL_GASOIL,
  NAFTA,
  CARBON,
  CC_GASOIL,
  CC_NAFTA,
  ELECTRICIDAD,
  MEDIO_DE_TRANSPORTE,
  DISTANCIA_MEDIA_RECORRIDA,
}

// TODO: Analizar refactor futuro para realizar el calculo de huella de carbono
//  utilizando varias unidades. Quizas convenga utilizar un strategy y que cada
//  medicion sepa su consumo en una unidad determinada

