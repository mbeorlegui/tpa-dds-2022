package domain.miembro;

import domain.organizacion.Sector;

import java.util.ArrayList;
import java.util.List;

public class Miembro {
  private String nombre;
  private String apellido;
  private Integer numeroDeDocumento;
  private Documento tipoDeDocumento;
  private List<Sector> sectores = new ArrayList<>();
  // private Trayecto trayecto;


  public Miembro(String nombre, String apellido, Integer numeroDeDocumento, Documento tipoDeDocumento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.numeroDeDocumento = numeroDeDocumento;
    this.tipoDeDocumento = tipoDeDocumento;
  }

  public void addSector(Sector sector) {
    if(!sectores.contains(sector)) {
      sectores.add(sector);
      sector.addMiembro(this);
    }
  }
}
