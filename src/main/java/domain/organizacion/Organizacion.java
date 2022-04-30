package domain.organizacion;

import domain.miembro.Miembro;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Organizacion {
  private String razonSocial;
  private Tipo tipo;
  private Ubicacion ubicacion;
  private List<Sector> sectores = new ArrayList<Sector>();
  private Clasificacion clasificacion;

  public Organizacion(String razonSocial, Tipo tipo, Ubicacion ubicacion, Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipo = tipo;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
  }

  public void addSector(Sector sector) {
    if (!sectores.contains(sector)) {
      sectores.add(sector);
      sector.setOrganizacion(this);
    }
  }

  public List<Miembro> getMiembros() {
    List<Miembro> miembros = sectores.stream().flatMap(s -> s.getMiembros().stream()).collect(Collectors.toList());
    return miembros;
  }
}
