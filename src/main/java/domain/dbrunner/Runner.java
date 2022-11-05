package domain.dbrunner;

import domain.administrador.*;
import domain.medicion.*;
import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.SectorTerritorial;
import domain.organizacion.TipoOrganizacion;
import domain.reports.ReportGenerator;
import domain.transporte.TipoDeTransportePublico;
import domain.transporte.TransportePublico;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {
  //  Probar con: mvn compile exec:java
  public static void main(String[] args) {
    System.out.println("Ejecutando queries!");
    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction et = em.getTransaction();
    et.begin();
    RepoTiposConsumos.getInstance().actualizarTiposDeConsumoDB();
    et.commit();
    Usuario admin = new UsuarioGeneral("matias", "AltaContrRaseNia_*3154", null);
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
    Sector unSector = new Sector("Un Sector");
    SectorTerritorial sectorTerritorial = new SectorTerritorial("Sector Prueba");
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
    TransportePublico subte = new TransportePublico(RepoTiposConsumos.getInstance().getTiposConsumos().get(0),
        0.5, TipoDeTransportePublico.SUBTE, "X");
    List<Tramo> tramos = new ArrayList<>();
    Tramo tramo = new Tramo(ubicacion, ubicacion, subte);
    tramos.add(tramo);
    Trayecto unTrayecto = new Trayecto(tramos);
    Miembro miembro = new Miembro("Matias", "Beorlegui", 41567890, Documento.DNI, unTrayecto);
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    org2.agregarMedicion(medicion3);
    org2.agregarMedicion(medicion4);
    org2.addSector(unSector);
    sectorTerritorial.agregarOrganizacion(org2);
    sectorTerritorial.agregarOrganizacion(org);
    Solicitud solicitud = new Solicitud(
        unSector,
        miembro,
        "El motivo de la solicitud es porque quiero trabajar allí por el gran clima laboral"
    );
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
    em.persist(admin);
    em.persist(subte);
    em.persist(tramo);
    em.persist(unTrayecto);
    em.persist(miembro);
    em.persist(solicitud);
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
    System.out.println("Un Usuario: " + RepoUsuarios.getInstance().findByUsername("matias").getUser() + RepoUsuarios.getInstance().findByUsername("matias").getPassword());
    System.out.println("Solicitudes: " + RepoSolicitudes.getInstance().getSolicitudes());
    System.out.println("Cerrando conexion");
    em.close();
  }
}