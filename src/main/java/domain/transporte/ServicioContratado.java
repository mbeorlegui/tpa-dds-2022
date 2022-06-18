package domain.transporte;

import domain.ubicacion.Ubicacion;
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

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

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
