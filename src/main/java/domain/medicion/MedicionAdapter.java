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
    return unaMedicion.getPeriodoDeImputacion();
  }

  public TipoConsumo adaptarTipoConsumo(MedicionRead unaMedicion) {
    return RepoTiposConsumos.getInstance().hayarTipo(unaMedicion.getTipoConsumo());
  }
//TODO: ver como cambiar esto, ya que TiposConsumos ahora interactua con db
}
