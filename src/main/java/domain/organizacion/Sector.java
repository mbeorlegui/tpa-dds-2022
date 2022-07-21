package domain.organizacion;

import domain.medicion.Periodicidad;
import domain.miembro.Miembro;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
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

  public void addMiembros(Miembro... miembros) {
    Arrays.stream(miembros).forEach(this::addMiembro);
  }

  public double calcularHuellaDeCarbono(Periodicidad periodicidad) {
    return miembros
        .stream()
        .mapToDouble(miembro -> miembro.calcularHuellaDeCarbono(periodicidad))
        .sum();
  }

}
