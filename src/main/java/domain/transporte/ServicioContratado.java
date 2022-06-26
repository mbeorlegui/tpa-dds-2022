package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;

public class ServicioContratado extends Transporte {
  private TipoDeServicioContratado tipoDeServicioContratado;

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

  public ServicioContratado(TipoConsumo combustible, Double combustiblePorKm,
                            TipoDeServicioContratado tipoDeServicioContratado) {
    super(combustible, combustiblePorKm);
    this.tipoDeServicioContratado = tipoDeServicioContratado;
    setTipoTransporte(TipoTransporte.CONTRATADO);
  }

  public TipoDeServicioContratado getTipoDeServicioContratado() {
    return tipoDeServicioContratado;
  }

}
