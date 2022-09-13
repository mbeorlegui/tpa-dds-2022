package domain.ubicacion;

import lombok.Getter;

import javax.persistence.*;


@Entity
@Table(name = "ubicacion")
public class Ubicacion {
  @Id
  @GeneratedValue
  @Column(name = "ubicacion_id")
  private long id;
  @Getter
  @Column(name = "localidad_id")
  private Integer localidadID;
  @Getter
  private String calle;
  @Getter
  private String altura;

  //private Integer altura; En la API la altura aparece como string
  public Ubicacion() {
  }

  public Ubicacion(Integer localidadID, String calle, String altura) {
    this.localidadID = localidadID;
    this.calle = calle;
    this.altura = altura;
  }

  public boolean esMismaUbicacionQue(Ubicacion unaUbicacion) {
    return (this.getLocalidadID().equals(unaUbicacion.getLocalidadID())
        && this.getCalle().equals(unaUbicacion.getCalle())
        && this.getAltura().equals(unaUbicacion.getAltura()));
  }

}
