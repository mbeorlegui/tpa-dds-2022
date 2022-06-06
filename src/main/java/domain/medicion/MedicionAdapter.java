package domain.medicion;

public class MedicionAdapter {
  /**
   * @param unaMedicion Recibe una MedicionRead, con todos los parametros tipo String
   * @return Retorna una Medicion, con el objeto convertido correctamente
   */
  public Medicion adaptarMedicion(MedicionRead unaMedicion) {
    return
        new Medicion(adaptarTipoConsumo(unaMedicion),
            adaptarValor(unaMedicion),
            adaptarPeriodicidad(unaMedicion),
            adaptarPeriodo(unaMedicion));
  }

  public Integer adaptarValor(MedicionRead unaMedicion) {
    return Integer.parseInt(unaMedicion.getValor());
  }

  public Periodicidad adaptarPeriodicidad(MedicionRead unaMedicion) {
    return Periodicidad.valueOf(unaMedicion.getPeriodicidad());
  }

  public String adaptarPeriodo(MedicionRead unaMedicion) {
    /*
      TODO: Analizar si conviene modelarlo como String y en caso de que no, adaptarlo aca
    */
    return unaMedicion.getPeriodoDeImputacion();
  }

  public TipoConsumo adaptarTipoConsumo(MedicionRead unaMedicion) {
    return TipoConsumo.valueOf(unaMedicion.getTipoConsumo());
    // TODO: en caso de hacer el refactor en TipoConsumo habria que cambiar este adapter
  }

}
