package domain.medicion;

import lombok.Getter;
import lombok.Setter;

public class MedicionRead {
  @Getter
  @Setter
  private String tipoConsumo;
  @Getter
  @Setter
  private String valor;
  @Getter
  @Setter
  private String periodicidad;
  @Getter
  @Setter
  private String periodoDeImputacion;

  public MedicionRead() {
  }

  public MedicionRead(String tipoConsumo, String valor, String periodicidad, String periodoDeImputacion) {
    this.tipoConsumo = tipoConsumo;
    this.valor = valor;
    this.periodicidad = periodicidad;
    this.periodoDeImputacion = periodoDeImputacion;
  }

  @Override
  public String toString() {
    return "Medicion Read [Tipo de consumo: " + tipoConsumo
        + ", Valor: " + valor + ", Periodicidad: " + periodicidad
        + ", Periodo de imputacion: " + periodoDeImputacion + "]";
  }
}
