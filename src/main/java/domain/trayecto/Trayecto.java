package domain.trayecto;

import domain.exceptions.NoPuedeSerTrayectoCompartidoException;
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
    return this.tramos.stream().mapToDouble(tramo -> tramo.distanciaIntermedia()).sum();
  }

  public void verificarQuePuedaSerAsignadoAMiembros() {
    if (!this.tramosPuedenSerCompartidos()) {
      throw new NoPuedeSerTrayectoCompartidoException("No puede ser trayecto compartido");
    }
  }

  private boolean tramosPuedenSerCompartidos() {
    return this.getTramos().stream().allMatch(tramo -> {
      return tramo.puedeSerCompartido();
    });
  }
}
