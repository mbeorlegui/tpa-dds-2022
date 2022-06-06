package domain.medicion;

import lombok.Getter;
public class MedicionRead {
  @Getter
  private String tipoConsumo;
  @Getter
  private String valor;
  @Getter
  private String periodicidad;
  @Getter
  private String periodoDeImputacion;

  @Override
  public String toString() {
    return "Medicion [Tipo de consumo: " + tipoConsumo
        + ", Valor: " + valor + ", Periodicidad: " + periodicidad
        + ", Periodo de imputacion: " + periodoDeImputacion + "]";
  }
  // TODO: Agregar al diagramna de clases
}
