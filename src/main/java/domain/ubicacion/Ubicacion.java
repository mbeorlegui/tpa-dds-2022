package domain.ubicacion;

import lombok.Getter;

public class Ubicacion {
  @Getter
  private Integer localidadId;
  @Getter
  private String calle;
  @Getter
  private String altura;

  public Ubicacion(Integer localidadId, String calle, String altura) {
    this.localidadId = localidadId;
    this.calle = calle;
    this.altura = altura;
  }


  public boolean esMismaUbicacionQue(Ubicacion unaUbicacion) {
    return (this.localidadId==unaUbicacion.localidadId && this.calle == unaUbicacion.calle && this.altura == unaUbicacion.altura);
  }
}
