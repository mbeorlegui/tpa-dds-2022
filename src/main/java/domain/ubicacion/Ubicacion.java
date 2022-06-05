package domain.ubicacion;

import lombok.Getter;

public class Ubicacion {
  @Getter
  private Integer localidadID;
  @Getter
  private String calle;
  @Getter
  private String altura;

  public Ubicacion(Integer localidadID, String calle, String altura) {
    this.localidadID = localidadID;
    this.calle = calle;
    this.altura = altura;
  }

  public boolean esMismaUbicacionQue(Ubicacion unaUbicacion) {
    return (this.getLocalidadID().equals(unaUbicacion.getLocalidadID()) &&
        this.getCalle().equals(unaUbicacion.getCalle()) &&
        this.getAltura().equals(unaUbicacion.getAltura()));
  }

}
