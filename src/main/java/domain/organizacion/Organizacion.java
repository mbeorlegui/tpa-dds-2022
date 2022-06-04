package domain.organizacion;

import domain.miembro.Miembro;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class Organizacion {
  @Getter
  private String razonSocial;
  @Getter
  private Tipo tipo;
  @Getter
  private Ubicacion ubicacion;
  private List<Sector> sectores = new ArrayList<>();
  @Getter
  private Clasificacion clasificacion;
  private CsvHandler csvHandler;


  public Organizacion(String razonSocial, Tipo tipo, Ubicacion ubicacion,
                      Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipo = tipo;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
  }


  public void addSector(Sector sector) {
    if (!tieneSectorDe(sector)) {
      sectores.add(sector);
    }
  }

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
