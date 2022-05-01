package domain.transporte;

public class ServicioContratado extends Transporte {
  private TipoDeServicioContratado tipoDeServicioContratado;

  public ServicioContratado(TipoDeServicioContratado tipoDeServicioContratado) {
    this.tipoDeServicioContratado = tipoDeServicioContratado;
    setTipoTransporte(TipoTransporte.CONTRATADO);
  }

  public TipoDeServicioContratado getTipoDeServicioContratado() {
    return tipoDeServicioContratado;
  }
}
