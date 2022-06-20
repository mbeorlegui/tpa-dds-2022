package domain.organizacion;

import domain.exceptions.NonMemberException;
import domain.medicion.Medicion;
import domain.miembro.Miembro;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
  // CsvHandler csvHandler = new CsvHandler();

  @Getter
  private List<Medicion> mediciones = new ArrayList<Medicion>();


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

  public void asignarTrayectoA(Trayecto trayecto, Miembro... miembros) {
    this.verificarQueSeanMiembros(miembros);
    trayecto.verificarQuePuedaSerAsignadoAMiembros();
    Arrays.stream(miembros).forEach(miembro -> {
      miembro.setTrayecto(trayecto);
    });
  }

  private void verificarQueSeanMiembros(Miembro[] miembros) {
    if (!this.todosSonMiembros(miembros)) {
      throw new NonMemberException("Una de las personas no es miembro");
    }
  }

  private boolean todosSonMiembros(Miembro[] miembros) {
    return Arrays.stream(miembros).allMatch(this::esMiembro);
  }

  public void agregarMedicion(Medicion unaMedicion) {
    mediciones.add(unaMedicion);
  }

  public void agregarMediciones(List<Medicion> mediciones) {
    // Se implementa:
    // unaOrganizacion.agregarMediciones(csvHandler.getMediciones());
    this.mediciones.addAll(mediciones);
  }

  public boolean existeMedicion(Medicion unaMedicion) {
    return mediciones.contains(unaMedicion);
  }

  public boolean contieneMedicionIdentica(Medicion unaMedicion) {
    return mediciones.stream().anyMatch(m -> m.esMedicionIdentica(unaMedicion));
  }

}
