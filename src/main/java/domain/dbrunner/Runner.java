package domain.dbrunner;

import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
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
  //  Probar con: mvn compile exec:java
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
    SectorTerritorial sectorTerritorial = new SectorTerritorial();
    // ------------------------
    // pruebas de mediciones
    MedicionRead medicionRead1 = new MedicionRead(
        "ELECTRICIDAD", "6000", "MENSUAL", "04/2021");
    Medicion medicion1 = new MedicionAdapter().adaptarMedicion(medicionRead1);
    MedicionRead medicionRead2 = new MedicionRead("GAS_NATURAL", "100", "MENSUAL", "03/2022");
    Medicion medicion2 = new MedicionAdapter().adaptarMedicion(medicionRead2);
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    org2.addSector(unSector);
//    sectorTerritorial.agregarOrganizacion(org2);
    sectorTerritorial.agregarOrganizacion(org);
    et.begin();
    em.persist(ubicacion);
    em.persist(org2);
    em.persist(unSector);
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
    em.persist(sectorTerritorial);
    em.persist(medicion1);
    em.persist(medicion2);
    em.persist(org);
    et.commit();
    System.out.println(
        "Mediciones en periodo1: "
            + ReportGenerator.getMedicionEnPeriodo(org, "04/2021")
    );
    System.out.println(
        "Mediciones en periodo2: "
            + ReportGenerator.getMedicionEnPeriodo(org, "03/2022")
    );
    System.out.println(
        "Variacion entre periodos: "
            + ReportGenerator.getVariacionEntrePeriodos(org, "04/2021", "03/2022")
            + "%"
    );
    System.out.println("Cerrando conexion");
    em.close();
  }
}