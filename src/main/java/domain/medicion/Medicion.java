package domain.medicion;

import lombok.Getter;
public class Medicion {
  @Getter
  private TipoConsumo tipoConsumo;
  @Getter
  private Integer valor;
  @Getter
  private Periodicidad periodicidad;
  @Getter
  private String periodoDeImputacion;
}
