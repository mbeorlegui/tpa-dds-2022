package domain.dbrunner;

import domain.administrador.FactorDeEmision;
import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.*;
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
import java.util.stream.Collectors;

public class Runner {
  //  Probar con: mvn compile exec:java
  public static void main(String[] args) {
    RepoTiposConsumos.getInstance().actualizarTiposDeConsumoDB();
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
    MedicionRead medicionRead2 = new MedicionRead("GAS_NATURAL", "5000", "MENSUAL", "03/2022");
    Medicion medicion2 = new MedicionAdapter().adaptarMedicion(medicionRead2);
    MedicionRead medicionRead3 = new MedicionRead(
        "ELECTRICIDAD", "7000", "MENSUAL", "04/2021");
    Medicion medicion3 = new MedicionAdapter().adaptarMedicion(medicionRead3);
    MedicionRead medicionRead4 = new MedicionRead("GAS_NATURAL", "8000", "MENSUAL", "03/2022");
    Medicion medicion4 = new MedicionAdapter().adaptarMedicion(medicionRead4);
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    org2.agregarMedicion(medicion3);
    org2.agregarMedicion(medicion4);
    org2.addSector(unSector);
    sectorTerritorial.agregarOrganizacion(org2);
    sectorTerritorial.agregarOrganizacion(org);
    et.begin();
    // em.persist(ubicacion);
    em.persist(org2);
    em.persist(unSector);
    // Para que los metodos anden en el runner deben ser static
    // System.out.println("Ubicacion 0: " + ReportGenerator.getUbicaciones().get(0).getCalle());
//    System.out.println(
//        "Organizacion 0: " + ReportGenerator.getOrganizaciones().get(0).getRazonSocial());
//    System.out.println(
//        "Ubicacion de Organizacion 0: "
//            + ReportGenerator.getOrganizaciones().get(0).getUbicacion().getCalle());
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
    em.persist(medicion3);
    em.persist(medicion4);
    em.persist(org2);
    et.commit();
//    System.out.println(
//        "Mediciones en periodo1: "
//            + ReportGenerator.getHuellaDeCarbonoEnPeriodo(org.getId(), Periodicidad.MENSUAL, "04/2021", UnidadEquivalenteCarbono.KILOGRAMO)
//    );
//    System.out.println(
//        "Mediciones en periodo2: "
//            + ReportGenerator.getHuellaDeCarbonoEnPeriodo(org.getId(), Periodicidad.MENSUAL, "03/2022", UnidadEquivalenteCarbono.KILOGRAMO)
//    );
//    System.out.println(
//        "Periodos intermedios entre 06/2021 - 02/2022: "
//            + Periodicidad.MENSUAL.getPeriodosIntermedios("06/2021", "02/2022")
//    );
//    System.out.println(
//        "Mediciones entre periodo1 y periodo2: "
//            + ReportGenerator.getEvolucionHcDeOrganizacion(org.getId(), Periodicidad.MENSUAL, "04/2021", "03/2022", UnidadEquivalenteCarbono.KILOGRAMO)
//    );
//    System.out.println(
//        "Variacion entre periodos: "
//            + ReportGenerator.getVariacionEntrePeriodos(org, "04/2021", "03/2022")
//            + "%"
//    );
//    System.out.println(
//        "Variacion entre periodos: "
//            + ReportGenerator.getVariacionEntrePeriodosDeSector(sectorTerritorial, "04/2021", "03/2022")
//            + "%"
//    );
    System.out.println("Tipos de consumo: " + RepoTiposConsumos.getInstance().getTiposConsumos());
    System.out.println("Factores de emision de tipos de consumo: " + RepoTiposConsumos.getInstance().getTiposConsumos().stream().map(tc -> tc.factorDeEmision.getFactor(UnidadEquivalenteCarbono.GRAMO)).collect(Collectors.toList()));
    System.out.println("Cerrando conexion");
    em.close();
  }
}