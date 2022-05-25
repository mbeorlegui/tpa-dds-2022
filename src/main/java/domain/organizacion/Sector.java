package domain.organizacion;

import domain.miembro.Miembro;
import java.util.ArrayList;
import java.util.List;

public class Sector {
  //private Organizacion organizacion;
  /*    Hago que la organizacion conozca los sectores,
      que los sectores conozcan a los miembros
      y que los miembros conozcan las organizaciones donde trabaja
  */
  private List<Miembro> miembros;

  public Sector() {
    miembros = new ArrayList<>();
  }

  public void addMiembro(Miembro miembro) {
    if (!miembros.contains(miembro)) {
      miembros.add(miembro);
    }
  }

  public List<Miembro> getMiembros() {
    return miembros;
  }
}
