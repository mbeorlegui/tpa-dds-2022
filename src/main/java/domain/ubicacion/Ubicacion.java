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

  public boolean esMismaUbicacionQue(Ubicacion inicioDeTramo) {
    return (this.localidadID == inicioDeTramo.localidadID & this.getCalle().equals(inicioDeTramo.getCalle()) &
        this.getAltura().equals(inicioDeTramo.getAltura()));
  }

}
