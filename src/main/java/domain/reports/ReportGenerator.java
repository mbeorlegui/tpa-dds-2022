package domain.reports;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import domain.organizacion.RepoSectoresTerritoriales;
import domain.organizacion.RepoOrganizaciones;
import domain.organizacion.TipoOrganizacion;
import domain.organizacion.Organizacion;
import domain.organizacion.SectorTerritorial;

import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator implements WithGlobalEntityManager {

  // Evaluar si ponerlo en RepoSectoresTerritoriales
  public static double hcTotalDeSectorTerritorial(Long sectorTerritorialId,
                                           Periodicidad periodicidad,
                                           String periodoDeImputacion,
                                           UnidadEquivalenteCarbono unidadDeseada) {
    return RepoSectoresTerritoriales
        .getInstance()
        .getSectorTerritorial(sectorTerritorialId)
        .huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada);
  }

  // Evaluar si vale la pena esta abstraccion (es basicamente un pasamanos)
  public static double hcTotalDeOrganizacionesDeTipo(TipoOrganizacion tipoOrganizacion,
                                              Periodicidad periodicidad,
                                              String periodoDeImputacion,
                                              UnidadEquivalenteCarbono unidadDeseada) {
    return RepoOrganizaciones
        .getInstance()
        .hcTotalDeOrganizacionesDeTipo(
            tipoOrganizacion, periodicidad, periodoDeImputacion, unidadDeseada
        );
  }

  @SuppressWarnings("unchecked")
  public static double getHuellaDeCarbonoEnPeriodo(Long organizacionId,
                                                   Periodicidad periodicidad,
                                                   String periodo,
                                                   UnidadEquivalenteCarbono unidad) {
    return RepoOrganizaciones
        .getInstance()
        .getOrganizacion(organizacionId)
        .huellaDeCarbonoEnPeriodo(periodicidad, periodo, unidad);
  }

  public static List<Double> getEvolucionHcDeOrganizacion(Long organizacionId,
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

  public static List<Double> getEvolucionHcDeSector(Long sectorTerritorialId,
                                             Periodicidad periodicidad,
                                             String periodoInicio,
                                             String periodoFin,
                                             UnidadEquivalenteCarbono unidad) {
    List<String> periodosIntermedios =
        periodicidad.getPeriodosIntermedios(periodoInicio, periodoFin);
    SectorTerritorial sector = RepoSectoresTerritoriales.getInstance()
        .getSectorTerritorial(sectorTerritorialId);
    return periodosIntermedios.stream().mapToDouble(
        p -> sector.huellaDeCarbonoEnPeriodo(periodicidad, p, unidad)
    ).boxed().collect(Collectors.toList());
  }

  public static ReporteDeComposicion composicionHcDeSectorTerritorial(Long sectorTerritorialId,
                                                               Periodicidad periodicidad,
                                                               String periodoDeImputacion,
                                                               UnidadEquivalenteCarbono
                                                                   unidadDeseada) {
    SectorTerritorial sectorTerritorial = RepoSectoresTerritoriales.getInstance()
        .getSectorTerritorial(sectorTerritorialId);
    return sectorTerritorial
        .composicionHuellaDeCarbono(
            periodicidad, periodoDeImputacion, unidadDeseada
        );
  }

  public static ReporteDeComposicion composicionHcDeOrganizacion(Long organizacionId,
                                                          Periodicidad periodicidad,
                                                          String periodoDeImputacion,
                                                          UnidadEquivalenteCarbono unidadDeseada) {
    Organizacion organizacion = RepoOrganizaciones.getInstance().getOrganizacion(organizacionId);
    return organizacion
        .composicionHuellaDeCarbono(
            periodicidad, periodoDeImputacion, unidadDeseada
        );
  }
}