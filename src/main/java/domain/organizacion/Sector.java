package domain.organizacion;

import domain.medicion.Periodicidad;
import domain.miembro.Miembro;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Sector {
  //private Organizacion organizacion;
  /*    Hago que la organizacion conozca los sectores y
      que los sectores conozcan a los miembros
  */
  @Getter
  private List<Miembro> miembros;

  public Sector() {
    miembros = new ArrayList<>();
  }

  public void addMiembro(Miembro miembro) {
    if (!miembros.contains(miembro)) {
      miembros.add(miembro);
    }
  }

  public int calcularHuellaDeCarbono(Periodicidad periodicidad) {
    // TODO: La huella de carbono del sector deberia ser la sumatoria
    //  de la huella de carbono de cada miembro
    return 0;
  }

}
