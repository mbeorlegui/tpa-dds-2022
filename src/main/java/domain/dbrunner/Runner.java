package domain.dbrunner;

import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.SectorTerritorial;
import domain.organizacion.TipoOrganizacion;
import domain.reports.ReportGenerator;
import domain.ubicacion.Ubicacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Runner {
  Probar con: mvn compile exec:java
  public static void main(String[] args) {
    System.out.println("Ejecutando queries!");
    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction et = em.getTransaction();
    Ubicacion ubicacion = new Ubicacion(1, "Calle Falsa", "123");
    Organizacion org = new Organizacion(
        "Prueba Empresa",
        TipoOrganizacion.EMPRESA,
        ubicacion,
        Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
    Organizacion org2 = new Organizacion(
        "Prueba Universidad",
        TipoOrganizacion.GUBERNAMENTAL,
        ubicacion,
        Clasificacion.UNIVERSIDAD);
    Sector unSector = new Sector();
    org2.addSector(unSector);
    SectorTerritorial sectorTerritorial = new SectorTerritorial();
//    sectorTerritorial.agregarOrganizacion(org2);
    sectorTerritorial.agregarOrganizacion(org);
    et.begin();
    em.persist(ubicacion);
    em.persist(org2);
    em.persist(org);
    em.persist(unSector);
    em.persist(sectorTerritorial);
    et.commit();
    // Para que los metodos anden en el runner deben ser static
    // System.out.println("Ubicacion 0: " + ReportGenerator.getUbicaciones().get(0).getCalle());
    System.out.println(
        "Organizacion 0: " + ReportGenerator.getOrganizaciones().get(0).getRazonSocial());
    System.out.println(
        "Ubicacion de Organizacion 0: "
            + ReportGenerator.getOrganizaciones().get(0).getUbicacion().getCalle());
//    System.out.println(
//        "Organizacion 0 en sector territorial: "
//            + ReportGenerator.getSectorTerritorial(
//                sectorTerritorial.getId()).getOrganizaciones().get(0).getRazonSocial());
//    System.out.println(
//        "Organizacion 0 de tipo gubernamental: " +
//            ReportGenerator.getOrganizacionesPorTipo(
//                TipoOrganizacion.GUBERNAMENTAL).get(0).getRazonSocial());
    System.out.println("Cerrando conexion");
  }
}
