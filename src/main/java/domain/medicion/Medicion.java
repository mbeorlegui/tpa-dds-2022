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

  public Medicion(TipoConsumo tipoConsumo, Integer valor, Periodicidad periodicidad, String periodoDeImputacion) {
    this.tipoConsumo = tipoConsumo;
    this.valor = valor;
    this.periodicidad = periodicidad;
    this.periodoDeImputacion = periodoDeImputacion;
  }

  public String toString() {
    return "Medicion [Tipo de consumo: " + tipoConsumo
        + ", Valor: " + valor + ", Periodicidad: " + periodicidad
        + ", Periodo de imputacion: " + periodoDeImputacion + "]";
  }
}
