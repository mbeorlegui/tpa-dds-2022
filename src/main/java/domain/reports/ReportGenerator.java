package domain.reports;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import domain.organizacion.*;
import domain.ubicacion.Ubicacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator implements WithGlobalEntityManager {

  public double hcTotalDeSectorTerritorial(Long sectorTerritorialId,
                                           Periodicidad periodicidad,
                                           String periodoDeImputacion,
                                           UnidadEquivalenteCarbono unidadDeseada) {
    return RepoSectoresTerritoriales
        .getInstance()
        .getSectorTerritorial(sectorTerritorialId)
        .huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada);
  }

  public double hcTotalDeOrganizacionesDeTipo(TipoOrganizacion tipoOrganizacion,
                                              Periodicidad periodicidad,
                                              String periodoDeImputacion,
                                              UnidadEquivalenteCarbono unidadDeseada) {
    return RepoOrganizaciones
        .getInstance()
        .getOrganizacionesPorTipo(tipoOrganizacion)
        .stream()
        .mapToDouble(org ->
            org.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada))
        .sum();
  }

  @SuppressWarnings("unchecked")
  public static double getHuellaDeCarbonoEnPeriodo(
      Long organizacionId,
      Periodicidad periodicidad,
      String periodo,
      UnidadEquivalenteCarbono unidad) {
    return RepoOrganizaciones.getInstance().getOrganizacion(organizacionId).huellaDeCarbonoEnPeriodo(periodicidad, periodo, unidad);
  }

  public static List<Double> getEvolucionHcDeOrganizacion(
      Long organizacionId,
      Periodicidad periodicidad,
      String periodoInicio,
      String periodoFin,
      UnidadEquivalenteCarbono unidad) {
    List<String> periodosIntermedios =
        periodicidad.getPeriodosIntermedios(periodoInicio, periodoFin);
    Organizacion org = RepoOrganizaciones.getInstance().getOrganizacion(organizacionId);
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
    SectorTerritorial sector = RepoSectoresTerritoriales.getInstance().getSectorTerritorial(sectorTerritorialId);
    return periodosIntermedios.stream().mapToDouble(
        p -> sector.huellaDeCarbonoEnPeriodo(periodicidad, p, unidad)
    ).boxed().collect(Collectors.toList());
  }

  public ReporteDeComposicion composicionHcDeSectorTerritorial(
      Long sectorTerritorialId,
      Periodicidad periodicidad,
      String periodoDeImputacion,
      UnidadEquivalenteCarbono unidadDeseada) {
    List<Organizacion> organizaciones = RepoSectoresTerritoriales.getInstance().getSectorTerritorial(sectorTerritorialId).getOrganizaciones();
    return this.composicionHcDeOrganizaciones(
        organizaciones, periodicidad, periodoDeImputacion, unidadDeseada);
  }

  public ReporteDeComposicion composicionHcDeOrganizacion(
      Long organizacionId,
      Periodicidad periodicidad,
      String periodoDeImputacion,
      UnidadEquivalenteCarbono unidadDeseada) {
    Organizacion organizacion = RepoOrganizaciones.getInstance().getOrganizacion(organizacionId);
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