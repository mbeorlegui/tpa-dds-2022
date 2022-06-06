package domain.trayecto;

import domain.exceptions.NoPuedeSerTrayectoCompartidoException;

import java.util.List;

public class Trayecto {
  private List<Tramo> tramos;

  public Trayecto(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public List<Tramo> getTramos() {
    return tramos;
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
