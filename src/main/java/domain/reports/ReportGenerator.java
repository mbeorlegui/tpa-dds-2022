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

  // Evaluar si ponerlo en RepoSectoresTerritoriales
  public double hcTotalDeSectorTerritorial(Long sectorTerritorialId,
                                           Periodicidad periodicidad,
                                           String periodoDeImputacion,
                                           UnidadEquivalenteCarbono unidadDeseada) {
    return RepoSectoresTerritoriales
        .getInstance()
        .getSectorTerritorial(sectorTerritorialId)
        .huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada);
  }

  // Evaluar si vale la pena esta abstraccion (es basicamente un pasamanos)
  public double hcTotalDeOrganizacionesDeTipo(TipoOrganizacion tipoOrganizacion,
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

  public List<Double> getEvolucionHcDeSector(Long sectorTerritorialId,
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

  public ReporteDeComposicion composicionHcDeSectorTerritorial(Long sectorTerritorialId,
                                                               Periodicidad periodicidad,
                                                               String periodoDeImputacion,
                                                               UnidadEquivalenteCarbono unidadDeseada) {
    SectorTerritorial sectorTerritorial = RepoSectoresTerritoriales.getInstance().getSectorTerritorial(sectorTerritorialId);
    return sectorTerritorial
        .composicionHuellaDeCarbono(
            periodicidad, periodoDeImputacion, unidadDeseada
        );
  }

  public ReporteDeComposicion composicionHcDeOrganizacion(Long organizacionId,
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