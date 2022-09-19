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

public class ReportGenerator implements WithGlobalEntityManager {
  private static EntityManager em = PerThreadEntityManagers.getEntityManager();

  @SuppressWarnings("unchecked")
  public static List<Organizacion> getOrganizaciones() {
    return em
        .createQuery("from Organizacion")
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public static List<Organizacion> getOrganizacionesPorSector(SectorTerritorial sector) {
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
}