package domain.medicion;

import java.util.List;

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

  /**
   * @param unaMedicion Recibe una MedicionRead, con todos los parametros tipo String
   *                    y el tipo de consumo en el caso de los tests
   * @return Retorna una Medicion, con el objeto convertido correctamente para el uso en los tests
   */
  public Medicion adaptarMedicion(List<TipoConsumo> tipoConsumos, MedicionRead unaMedicion) {
    return
            new Medicion(adaptarTipoConsumo(unaMedicion, tipoConsumos),
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

  public TipoConsumo adaptarTipoConsumo(MedicionRead unaMedicion, List<TipoConsumo> tipoConsumos) {
    return tipoConsumos.stream().filter(unConsumo -> unConsumo.toString().equals(
            unaMedicion.getTipoConsumo())).findFirst().get();
  }
}
