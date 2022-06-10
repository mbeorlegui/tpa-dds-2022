package domain.trayecto;


import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;
import domain.transporte.TransportePublico;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

public class Tramo {
  @Getter
  private Ubicacion origenDeTramo;
  @Getter
  private Ubicacion destinoDeTramo;
  @Getter
  private Transporte transporteUtilizado;
  @Getter @Setter
  private CalculadoraDeDistancia calculadoraDeDistancia;

  public Tramo(Ubicacion origenDeTramo, Ubicacion destinoDeTramo, Transporte transporteUtilizado) {
    if ((transporteUtilizado instanceof TransportePublico)
        && (!((TransportePublico) transporteUtilizado).tieneUnaParadaEn(origenDeTramo)
        || !((TransportePublico) transporteUtilizado).tieneUnaParadaEn(destinoDeTramo))) {
      throw new IllegalArgumentException(); // los tramos no coinciden con las paradas
    }
    this.origenDeTramo = origenDeTramo;
    this.destinoDeTramo = destinoDeTramo;
    this.transporteUtilizado = transporteUtilizado;
  }

  public boolean esMismoTramo(Tramo unTramo) {
    return (this.origenDeTramo.esMismaUbicacionQue(unTramo.getOrigenDeTramo())
        && this.destinoDeTramo.esMismaUbicacionQue(unTramo.getDestinoDeTramo())
        && this.transporteUtilizado.esDeTipo(unTramo.getTransporteUtilizado().getTipoTransporte()));
  }

  public boolean puedeSerCompartido() {
    return this.transporteUtilizado.esDeTipo(TipoTransporte.PARTICULAR)
        || this.transporteUtilizado.esDeTipo(TipoTransporte.CONTRATADO);
  }

  public Double distanciaIntermedia() {
    return this.calculadoraDeDistancia.distancia(origenDeTramo, destinoDeTramo);
  }
}