package domain.ubicacion;

import lombok.Getter;

public class Ubicacion {
  @Getter
  private double latitud;
  @Getter
  private double longitud;

  public Ubicacion(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }


  public boolean esMismaUbicacionQue(Ubicacion unaUbicacion) {
    return (this.latitud == unaUbicacion.latitud && this.longitud == unaUbicacion.longitud);
  }
}
