package domain.organizacion;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.exceptions.NonMemberException;
import domain.medicion.Medicion;
import domain.medicion.Periodicidad;
import domain.medios.Contacto;
import domain.medios.MedioDeComunicacion;
import domain.miembro.Miembro;
import domain.reports.ReporteDeComposicion;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Embedded;
import javax.persistence.AttributeOverride;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Getter
@Entity
@Table(name = "organizacion")
public class Organizacion {
  @Id
  @GeneratedValue
  @Column(name = "organizacion_id")
  private long id;
  @Column(name = "razon_social")
  private String razonSocial;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_organizacion")
  private TipoOrganizacion tipoOrganizacion;
  @Embedded
  @AttributeOverride(name = "localidadID", column = @Column(name = "localidad_id"))
  private Ubicacion ubicacion;
  @OneToMany
  @JoinColumn(name = "organizacion_id")
  private List<Sector> sectores = new ArrayList<>();
  @Enumerated
  private Clasificacion clasificacion;
  @OneToMany
  @JoinColumn(name = "organizacion_id")
  private List<Medicion> mediciones = new ArrayList<Medicion>();
  @ManyToMany
  private List<Contacto> contactos = new ArrayList<>();
  @ManyToMany
  private List<MedioDeComunicacion> mediosDeComunicacion = new ArrayList<>();

  public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Ubicacion ubicacion,
                      Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoOrganizacion = tipoOrganizacion;
    this.ubicacion = ubicacion;
    this.clasificacion = clasificacion;
  }

  public void addSector(Sector sector) {
    if (!tieneSectorDe(sector)) {
      sectores.add(sector);
    }
  }

  public void addSectores(Sector... sectores) {
    Arrays.stream(sectores).forEach(sector -> addSector(sector));
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

  public Boolean esSector(Sector sector) {
    return this.sectores.contains(sector);
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

  private void verificarQueSeaMiembro(Miembro miembro) {
    if (!this.esMiembro(miembro)) {
      throw new NonMemberException("Esta persona no es un miembro");
    }
  }

  private void verificarQueSeaSector(Sector sector) {
    if (!this.esSector(sector)) {
      throw new NonMemberException("Este sector no forma parte de la organizacion");
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
    //TODO: verificar que tenga contacto antes de agregar
  }

  public void agregarContactos(Contacto... contactos) {
    Arrays.stream(contactos).forEach(contacto -> agregarContacto(contacto));
  }

  public void agregarMedioDeComunicacion(MedioDeComunicacion medioDeComunicacion) {
    mediosDeComunicacion.add(medioDeComunicacion);
  }

  public double huellaDeCarbonoEnPeriodo(Periodicidad periodicidad,
                                         String periodoDeImputacion,
                                         UnidadEquivalenteCarbono unidadDeseada) {
    return hcMedicionesEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada)
        + hcTrayectosMiembros(periodicidad, unidadDeseada);
  }

  public ReporteDeComposicion composicionHuellaDeCarbono(Periodicidad periodicidad,
                                                         String periodoDeImputacion,
                                                         UnidadEquivalenteCarbono unidadDeseada) {
    return new ReporteDeComposicion(
        this.hcMedicionesEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada),
        this.hcTrayectosMiembros(periodicidad, unidadDeseada));
  }

  public double hcTrayectosMiembros(Periodicidad periodicidad,
                                    UnidadEquivalenteCarbono unidadDeseada) {
    return this.getTrayectos()
        .stream()
        .mapToDouble(trayecto -> trayecto.huellaDeCarbonoEnPeriodo(periodicidad, unidadDeseada))
        .sum();
  }

  public double hcMedicionesEnPeriodo(Periodicidad periodicidad,
                                      String periodoDeImputacion,
                                      UnidadEquivalenteCarbono unidadDeseada) {
    return this.medicionesEnPeriodo(periodicidad, periodoDeImputacion)
        .stream()
        .mapToDouble(medicion -> medicion.huellaDeCarbono(unidadDeseada))
        .sum();
  }

  private List<Medicion> medicionesEnPeriodo(Periodicidad periodicidad,
                                             String periodoDeImputacion) {
    return mediciones
        .stream()
        .filter(medicion -> medicion.esDePeriodo(periodicidad, periodoDeImputacion))
        .collect(Collectors.toList());
  }

  public Set<Trayecto> getTrayectos() {
    return this.getMiembros()
        .stream()
        .map(miembro -> miembro.getTrayecto())
        .collect(Collectors.toSet());
  }

  public double impactoMiembroSobreHC(Miembro miembro,
                                      Periodicidad periodicidad,
                                      String periodoDeImputacion,
                                      UnidadEquivalenteCarbono unidadDeseada) {
    this.verificarQueSeaMiembro(miembro);
    return miembro.calcularHuellaDeCarbono(periodicidad, unidadDeseada)
        / huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada);
  }

  public double indiceSectorSobreHC(Sector sector,
                                    Periodicidad periodicidad,
                                    String periodoDeImputacion,
                                    UnidadEquivalenteCarbono unidadDeseada) {
    this.verificarQueSeaSector(sector);
    return sector.calcularHuellaDeCarbono(periodicidad, unidadDeseada)
        / huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada);
  }


  public void enviarGuiaDeRecomendaciones(String link) {
    contactos.forEach(
        contacto -> mediosDeComunicacion.forEach(
            medio -> medio.enviarNotificacion(link, contacto)
        )
    );
  }

  public boolean tieneMenorHcQue(Organizacion otraOrganizacion,
                                 Periodicidad periodicidad,
                                 String periodo,
                                 UnidadEquivalenteCarbono unidadDeseada) {
    return this.huellaDeCarbonoEnPeriodo(periodicidad, periodo, unidadDeseada)
        < otraOrganizacion.huellaDeCarbonoEnPeriodo(periodicidad, periodo, unidadDeseada);
  }
}
