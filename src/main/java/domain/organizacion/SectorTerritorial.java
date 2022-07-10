package domain.organizacion;

import domain.medicion.Periodicidad;

import java.util.ArrayList;
import java.util.List;

public class SectorTerritorial {
  private List<Organizacion> organizaciones = new ArrayList<>();

  public double huellaDeCarbonoEnPeriodo(Periodicidad periodicidad, String periodoDeImputacion) {
    return organizaciones
        .stream()
        .mapToDouble(org -> org.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion))
        .sum();
  }
}
