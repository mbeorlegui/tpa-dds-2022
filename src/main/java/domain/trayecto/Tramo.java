package domain.trayecto;

import domain.transporte.Transporte;
import domain.transporte.TransportePublico;
import domain.ubicacion.Ubicacion;

public class Tramo {
  private Ubicacion inicioDeTramo;
  private Ubicacion finDeTramo;
  private Transporte transporteUtilizado;

  public Tramo(Ubicacion inicioDeTramo, Ubicacion finDeTramo, Transporte transporteUtilizado) {
    if ((transporteUtilizado instanceof TransportePublico)
        && (!((TransportePublico) transporteUtilizado).tieneUnaParadaEn(inicioDeTramo)
            || !((TransportePublico) transporteUtilizado).tieneUnaParadaEn(finDeTramo))) {
      throw new IllegalArgumentException(); // los tramos no coinciden con las paradas
    }
    this.inicioDeTramo = inicioDeTramo;
    this.finDeTramo = finDeTramo;
    this.transporteUtilizado = transporteUtilizado;
  }

  public Ubicacion getInicioDeTramo() {
    return inicioDeTramo;
  }

  public Ubicacion getFinDeTramo() {
    return finDeTramo;
  }

  public Transporte getTransporteUtilizado() {
    return transporteUtilizado;
  }

  public boolean esMismoTramo(Tramo unTramo) {
    return (this.inicioDeTramo.esMismaUbicacionQue(unTramo.getInicioDeTramo())
        && this.finDeTramo.esMismaUbicacionQue(unTramo.getFinDeTramo())
        && this.transporteUtilizado.esMismoTipoDeTransporteQue(unTramo.getTransporteUtilizado()));
  }
}