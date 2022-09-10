package domain.miembro;

import domain.medicion.Periodicidad;

import javax.persistence.*;

import domain.trayecto.Trayecto;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Entity
public class Miembro {
  @Id
  @GeneratedValue
  private long id;
  @Getter
  private String nombre;
  @Getter
  private String apellido;
  @Getter
  private Integer numeroDeDocumento;
  @Getter
  @Transient
  private Documento tipoDeDocumento;
  @Setter
  @Getter
  @Transient
  private Trayecto trayecto;

  /*
  public HashSet<Organizacion> getOrganizaciones() {
    return organizaciones;
  }
  */
  public Miembro() {
  }

  public Miembro(
      String nombre,
      String apellido,
      Integer numeroDeDocumento,
      Documento tipoDeDocumento,
      Trayecto trayecto) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.numeroDeDocumento = numeroDeDocumento;
    this.tipoDeDocumento = tipoDeDocumento;
    this.trayecto = trayecto;
  }

  public double calcularHuellaDeCarbono(Periodicidad periodicidad) {
    return trayecto.huellaDeCarbonoEnPeriodo(periodicidad);
  }
  /*
  public void addSector(Sector sector, Organizacion organizacion) {
    if (organizacion.tieneSectorDe(sector)) {
      sector.addMiembro(this);
      organizaciones.add(organizacion);
    }
  }
  */

}
