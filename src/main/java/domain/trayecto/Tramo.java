package domain.trayecto;

import domain.exceptions.ErrorEnLaApiException;
import domain.services.apidistancias.AdapterCalculadoraDeDistancia;
import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.services.apidistancias.ServicioDistancia;
import domain.transporte.Transporte;
import domain.transporte.TransportePublico;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class Tramo {
  @Getter
  private Ubicacion inicioDeTramo;
  @Getter
  private Ubicacion finDeTramo;
  @Getter
  private Transporte transporteUtilizado;
  @Setter
  private AdapterCalculadoraDeDistancia calculadoraDeDistancia;

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

  public boolean esMismoTramo(Tramo unTramo) {
    return (this.inicioDeTramo.esMismaUbicacionQue(unTramo.getInicioDeTramo())
        && this.finDeTramo.esMismaUbicacionQue(unTramo.getFinDeTramo())
        && this.transporteUtilizado.esMismoTipoDeTransporteQue(unTramo.getTransporteUtilizado()));
  }

  void validarTramoCompartidoMiembrosOrg(){

  }

  public double distanciaIntermedia() {
    return this.calculadoraDeDistancia.distancia(inicioDeTramo, finDeTramo);
  }
}