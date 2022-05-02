package domain.ubicacion;

public class Ubicacion {
  private double latitud;
  private double longitud;

  public Ubicacion(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Ubicacion) {
      Ubicacion ubicacion = (Ubicacion) obj;
      return (this.latitud == ubicacion.latitud && this.longitud == ubicacion.longitud);
    } else {
      return false;
    }
  }
  
  public double getLatitud() {
    return latitud;
  }

  public double getLongitud() {
    return longitud;
  }
}
