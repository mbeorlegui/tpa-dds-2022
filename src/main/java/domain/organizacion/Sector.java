package domain.organizacion;

import domain.miembro.Miembro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sector {
  private Organizacion organizacion;
  private List<Miembro> miembros = new ArrayList<>();

  public Sector(Organizacion organizacion) {
    this.organizacion = organizacion;
    //organizacion.addSector(this);
  }

  public Organizacion getOrganizacion() {
    return organizacion;
  }

  public void setOrganizacion(Organizacion organizacion) {
    this.organizacion = organizacion;
  }

  public void addMiembro(Miembro miembro) {
    if(!miembros.contains(miembro)) {
      miembros.add(miembro);
      miembro.addSector(this);
    }
  }

  public List<Miembro> getMiembros() {
    return miembros;
  }
}
