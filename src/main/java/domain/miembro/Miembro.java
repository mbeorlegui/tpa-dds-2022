package domain.miembro;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;

import domain.trayecto.Trayecto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;


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

  public double calcularHuellaDeCarbono(Periodicidad periodicidad,
                                        UnidadEquivalenteCarbono unidadDeseada) {
    return trayecto.huellaDeCarbonoEnPeriodo(periodicidad, unidadDeseada);
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
