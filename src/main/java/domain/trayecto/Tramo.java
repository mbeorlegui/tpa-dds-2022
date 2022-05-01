package domain.trayecto;

import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;

public class Tramo {
  private Ubicacion inicioDeTramo;
  private Ubicacion finDeTramo;
  private Transporte transporteUtilizado;

  public Tramo(Ubicacion inicioDeTramo, Ubicacion finDeTramo, Transporte transporteUtilizado) {
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

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Tramo) {
      Tramo tramo = (Tramo) obj;
      return (this.inicioDeTramo.equals(tramo.inicioDeTramo)
          && this.finDeTramo.equals(tramo.finDeTramo)
          && this.transporteUtilizado.equals(tramo.transporteUtilizado)
        );
    } else {
      return false;
    }
  }
}
