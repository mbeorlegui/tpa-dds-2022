package domain.reports;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import domain.organizacion.Organizacion;
import domain.organizacion.SectorTerritorial;
import domain.organizacion.TipoOrganizacion;
import domain.ubicacion.Ubicacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator implements WithGlobalEntityManager {
  private static EntityManager em = PerThreadEntityManagers.getEntityManager();

  @SuppressWarnings("unchecked")
  public static List<Organizacion> getOrganizaciones() {
    return em
        .createQuery("from Organizacion")
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Organizacion> getOrganizacionesPorSector(SectorTerritorial sector) {
    return em
        .createQuery("from Organizacion where sector_territorial_id = :sector")
        .setParameter("sector", sector)
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public static List<Ubicacion> getUbicaciones() {
    return em
        .createQuery("from Ubicacion")
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Organizacion> getOrganizacionesPorTipo(TipoOrganizacion tipoOrganizacion) {
    return em
        .createQuery("from Organizacion where tipo_organizacion = :tipoOrg")
        .setParameter("tipoOrg", tipoOrganizacion.name())
        .getResultList();
  }

  public SectorTerritorial getSectorTerritorial(Long id) {
    return em.find(SectorTerritorial.class, id);
  }

  public static Organizacion getOrganizacion(Long id) {
    return em.find(Organizacion.class, id);
  }

  public double hcTotalDeSectorTerritorial(Long sectorTerritorialId,
                                           Periodicidad periodicidad,
                                           String periodoDeImputacion,
                                           UnidadEquivalenteCarbono unidadDeseada) {
    return this
        .getSectorTerritorial(sectorTerritorialId)
        .huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada);
  }

  public double hcTotalDeOrganizacionesDeTipo(TipoOrganizacion tipoOrganizacion,
                                              Periodicidad periodicidad,
                                              String periodoDeImputacion,
                                              UnidadEquivalenteCarbono unidadDeseada) {
    return this
        .getOrganizacionesPorTipo(tipoOrganizacion)
        .stream()
        .mapToDouble(org ->
            org.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada))
        .sum();
  }

  public double evolucionDeHcEnSector(
      SectorTerritorial sector, String periodoDeInicio, String periodoDeFin) {
    return 0;
  }

  public double evolucionDeHcEnOrganizacion(
      Organizacion organizacion, String periodoDeInicio, String periodoDeFin) {
    return 0;
  }

  @SuppressWarnings("unchecked")
  public static double getHuellaDeCarbonoEnPeriodo(
      Long organizacionId,
      Periodicidad periodicidad,
      String periodo,
      UnidadEquivalenteCarbono unidad) {
    return getOrganizacion(organizacionId).huellaDeCarbonoEnPeriodo(periodicidad, periodo, unidad);
  }

  public static List<Double> getEvolucionHcDeOrganizacion(
      Long organizacionId,
      Periodicidad periodicidad,
      String periodoInicio,
      String periodoFin,
      UnidadEquivalenteCarbono unidad) {
    List<String> periodosIntermedios =
        periodicidad.getPeriodosIntermedios(periodoInicio, periodoFin);
    Organizacion org = getOrganizacion(organizacionId);
    return periodosIntermedios.stream().mapToDouble(
        p -> org.huellaDeCarbonoEnPeriodo(periodicidad, p, unidad)
    ).boxed().collect(Collectors.toList());
  }

  public List<Double> getEvolucionHcDeSector(
      Long sectorTerritorialId,
      Periodicidad periodicidad,
      String periodoInicio,
      String periodoFin,
      UnidadEquivalenteCarbono unidad) {
    List<String> periodosIntermedios =
        periodicidad.getPeriodosIntermedios(periodoInicio, periodoFin);
    SectorTerritorial sector = this.getSectorTerritorial(sectorTerritorialId);
    return periodosIntermedios.stream().mapToDouble(
        p -> sector.huellaDeCarbonoEnPeriodo(periodicidad, p, unidad)
    ).boxed().collect(Collectors.toList());
  }

  public ReporteDeComposicion composicionHcDeSectorTerritorial(
      Long sectorTerritorialId,
      Periodicidad periodicidad,
      String periodoDeImputacion,
      UnidadEquivalenteCarbono unidadDeseada) {
    List<Organizacion> organizaciones = this.getSectorTerritorial(
        sectorTerritorialId).getOrganizaciones();
    return this.composicionHcDeOrganizaciones(
        organizaciones, periodicidad, periodoDeImputacion, unidadDeseada);
  }

  public ReporteDeComposicion composicionHcDeOrganizacion(
      Long organizacionId,
      Periodicidad periodicidad,
      String periodoDeImputacion,
      UnidadEquivalenteCarbono unidadDeseada) {
    Organizacion organizacion = getOrganizacion(organizacionId);
    return new ReporteDeComposicion(
        organizacion.hcMedicionesEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada),
        organizacion.hcTrayectosMiembros(periodicidad, unidadDeseada));
  }

  private ReporteDeComposicion composicionHcDeOrganizaciones(
      List<Organizacion> organizaciones,
      Periodicidad periodicidad,
      String periodoDeImputacion,
      UnidadEquivalenteCarbono unidadDeseada) {
    return new ReporteDeComposicion(
        this.hcMediciones(organizaciones, periodicidad, periodoDeImputacion, unidadDeseada),
        this.hcTrayectos(organizaciones, periodicidad, unidadDeseada));
  }

  private double hcMediciones(List<Organizacion> organizaciones,
                              Periodicidad periodicidad,
                              String periodoDeImputacion,
                              UnidadEquivalenteCarbono unidadDeseada) {
    return organizaciones
        .stream()
        .mapToDouble(
            org -> org.hcMedicionesEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada))
        .sum();
  }

  private double hcTrayectos(List<Organizacion> organizaciones,
                             Periodicidad periodicidad,
                             UnidadEquivalenteCarbono unidadDeseada) {
    return organizaciones
        .stream()
        .mapToDouble(org -> org.hcTrayectosMiembros(periodicidad, unidadDeseada))
        .sum();
  }
}