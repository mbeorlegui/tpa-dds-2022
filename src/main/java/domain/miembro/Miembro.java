package domain.miembro;

import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.trayecto.Trayecto;
import java.util.HashSet;

public class Miembro {
  private String nombre;
  private String apellido;
  private Integer numeroDeDocumento;
  private Documento tipoDeDocumento;
  private Trayecto trayecto;
  private HashSet<Organizacion> organizaciones = new HashSet<>();
  //Uso hashSet para que no se repitan las organizaciones

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public Integer getNumeroDeDocumento() {
    return numeroDeDocumento;
  }

  public Documento getTipoDeDocumento() {
    return tipoDeDocumento;
  }

  public HashSet<Organizacion> getOrganizaciones() {
    return organizaciones;
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

  public void addSector(Sector sector, Organizacion organizacion) {
    if (organizacion.tieneSectorDe(sector)) {
      sector.addMiembro(this);
      organizaciones.add(organizacion);
    }
  }

  public void setTrayecto(Trayecto trayecto) {
    this.trayecto = trayecto;
  }

  public Trayecto getTrayecto() {
    return trayecto;
  }
}
