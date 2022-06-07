package domain.transporte;

import lombok.Getter;
import lombok.Setter;

public class ServicioContratado implements Transporte {
  private TipoDeServicioContratado tipoDeServicioContratado;
  @Setter
  @Getter
  private TipoTransporte tipoTransporte;

  public TipoTransporte getTipoTransporte() {
    return tipoTransporte;
  }

  public void setTipoTransporte(TipoTransporte tipoTransporte) {
    this.tipoTransporte = tipoTransporte;
  }

  /*
  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte) {
    return (this.tipoTransporte.equals(unTransporte.getTipoTransporte()));
  }
  */
  public ServicioContratado(TipoDeServicioContratado tipoDeServicioContratado) {
    this.tipoDeServicioContratado = tipoDeServicioContratado;
    setTipoTransporte(TipoTransporte.CONTRATADO);
  }

  public TipoDeServicioContratado getTipoDeServicioContratado() {
    return tipoDeServicioContratado;
  }

  public boolean esDeTipo(TipoTransporte tipoTransporte) {
    return this.tipoTransporte.equals(tipoTransporte);
  }
}
