package domain.ubicacion;

public class Ubicacion {
  private double latitud;
  private double longitud;

  public Ubicacion(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }


  public double getLatitud() {
    return latitud;
  }

  public double getLongitud() {
    return longitud;
  }

  public boolean esMismaUbicacionQue(Ubicacion unaUbicacion) {
    return (this.latitud == unaUbicacion.latitud && this.longitud == unaUbicacion.longitud);
  }
}
