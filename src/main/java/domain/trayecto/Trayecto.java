package domain.trayecto;

import java.util.List;

public class Trayecto {
  private List<Tramo> tramos;

  public Trayecto(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public List<Tramo> getTramos() {
    return tramos;
  }
}
