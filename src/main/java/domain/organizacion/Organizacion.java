package domain.organizacion;

import domain.exceptions.NonMemberException;
import domain.medicion.Medicion;
import domain.medicion.Periodicidad;
import domain.medios.Contacto;
import domain.medios.MediosDeComunicacion;
import domain.miembro.Miembro;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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

  @Getter
  private List<Medicion> mediciones = new ArrayList<Medicion>();

  private List<Contacto> contactos;
  private List<MediosDeComunicacion> mediosDeComunicacion;

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

  public void agregarContacto(Contacto contacto) {
    contactos.add(contacto);
  }


  // Cambiado a double
  // TODO: Cambiar en diagrama
  public double huellaDeCarbonoEnPeriodo(Periodicidad periodicidad, String periodoDeImputacion) {
    return hcMedicionesEnPeriodo(periodicidad, periodoDeImputacion) + hcTrayectosMiembros(periodicidad);
  }

  private double hcTrayectosMiembros(Periodicidad periodicidad) {
    return this.getTrayectos()
        .stream()
        .mapToDouble(trayecto -> trayecto.huellaDeCarbonoEnPeriodo(periodicidad))
        .sum();
  }

  private double hcMedicionesEnPeriodo(Periodicidad periodicidad, String periodoDeImputacion) {
    return this.medicionesEnPeriodo(periodicidad, periodoDeImputacion)
        .stream()
        .mapToDouble(medicion -> medicion.huellaDeCarbono())
        .sum();
  }

  private List<Medicion> medicionesEnPeriodo(Periodicidad periodicidad, String periodoDeImputacion) {
    return mediciones
        .stream()
        .filter(medicion -> medicion.esDePeriodo(periodicidad, periodoDeImputacion))
        .collect(Collectors.toList());
  }

  private Set<Trayecto> getTrayectos() {
    return this.getMiembros()
        .stream()
        .map(miembro -> miembro.getTrayecto())
        .collect(Collectors.toSet());
  }

  // Cambiado a double
  // TODO: Cambiar en diagrama
  public double impactoMiembroSobreHC(Miembro miembro,
                                   Periodicidad periodicidad,
                                   String periodoDeImputacion) {
    // TODO: revisar que este bien.
    //  En este caso lo mostramos como porcentaje
    return huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion) /
        miembro.calcularHuellaDeCarbono(periodicidad);
  }

  // Cambiado a double
  // TODO: Cambiar en diagrama
  public double indiceSectorSobreHC(Sector sector,
                                 Periodicidad periodicidad,
                                 String periodoDeImputacion) {
    // TODO: revisar
    return huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion) /
        sector.calcularHuellaDeCarbono(periodicidad);
  }

  public void enviarGuiaDeRecomendaciones(String link) {
    // TODO
  }
}
