package domain.organizacion;

import domain.miembro.Miembro;
import domain.ubicacion.Ubicacion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Organizacion {
  private String razonSocial;
  private Tipo tipo;
  private Ubicacion ubicacion;
  private List<Sector> sectores = new ArrayList<>();
  private Clasificacion clasificacion;


  public Organizacion(String razonSocial, Tipo tipo, Ubicacion ubicacion,
                      Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipo = tipo;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public Clasificacion getClasificacion() {
    return clasificacion;
  }

  public void addSector(Sector sector) {
    if (!tieneSectorDe(sector)) {
      sectores.add(sector);
    }
  }
  /*
  public void crearNuevoSector() {
    this.addSector(new Sector());
  }
  */

  public List<Miembro> getMiembros() {
    return sectores
        .stream()
        .flatMap(s -> s.getMiembros().stream())
        .collect(Collectors.toList());
  }

  public Boolean tieneSectorDe(Sector sector) {
    return sectores.contains(sector);
  }

  public Integer cantidadDeSectores() {
    return sectores.size();
  }

  public Integer cantidadDeMiembros() {
    return this.getMiembros().size();
  }

  public Boolean esMiembro(Miembro miembro) {
    return this.getMiembros().contains(miembro);
  }
}
