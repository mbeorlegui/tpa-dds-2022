package domain.trayecto;

import lombok.Getter;

import java.io.IOException;
import java.util.List;

public class Trayecto {
  @Getter
  private List<Tramo> tramos;

  public Trayecto(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public Double distanciaTotal() {
    return this.tramos.stream().mapToDouble( tramo -> tramo.distanciaIntermedia()).sum();
  }
}
