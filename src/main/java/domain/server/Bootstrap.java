package domain.server;

import domain.administrador.*;
import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
import domain.medicion.RepoTiposConsumos;
import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.*;
import domain.transporte.Parada;
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

public class Bootstrap {
  public static void init() {
    System.out.println("Ejecutando queries!");
    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction et = em.getTransaction();
    et.begin();
    RepoTiposConsumos.getInstance().actualizarTiposDeConsumoDB();
    et.commit();
    Usuario admin = new Usuario("matias", "AltaContrRaseNia_*3154", TipoUsuario.ADMINISTRADOR);
    Ubicacion ubicacion = new Ubicacion(1, "Calle Falsa", "123");
    Ubicacion ubicacion2 = new Ubicacion(2, "Rivadavia", "4000");
    Ubicacion ubicacion3 = new Ubicacion(2, "Rivadavia", "4300");
    Ubicacion ubicacion4 = new Ubicacion(3, "Medrano", "500");
    Ubicacion ubicacion5 = new Ubicacion(5, "Mozart", "2300");
    Parada parada1 = new Parada(ubicacion, null);
    Parada parada2 = new Parada(ubicacion2, null);
    Parada parada3 = new Parada(ubicacion3, null);
    Parada parada4 = new Parada(ubicacion4, null);
    Parada parada5 = new Parada(ubicacion5, null);
    TransportePublico colectivo8 = new TransportePublico(RepoTiposConsumos.getInstance().getTiposConsumos().get(0),
        0.5, TipoDeTransportePublico.COLECTIVO, "8");
    colectivo8.addParadas(parada2, parada3);
    TransportePublico colectivo7 = new TransportePublico(RepoTiposConsumos.getInstance().getTiposConsumos().get(0),
        0.5, TipoDeTransportePublico.COLECTIVO, "7");
    colectivo7.addParadas(parada4, parada5);
    
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
    Miembro miembro1 = new Miembro("Matias", "Beorlegui", 41567890, Documento.DNI, unTrayecto);
    Solicitud solicitud1 = new Solicitud(
        unSector,
        miembro1,
        "El motivo de la solicitud es porque quiero trabajar allí por el gran clima laboral"
    );
    Miembro miembro2 = new Miembro("Ignacio", "Ardanaz", 41567890, Documento.DNI, unTrayecto);
    Solicitud solicitud2 = new Solicitud(
        unSector,
        miembro2,
        "Necesito el trabajo, no llego a fin de mes"
    );
    Miembro miembro3 = new Miembro("Alejo", "Goltzman", 41756189, Documento.DNI, unTrayecto);
    Solicitud solicitud3 = new Solicitud(
        unSector,
        miembro3,
        "Formar parte de la organización significaría un gran paso para mi carrera profesional"
    );
    Miembro miembro4 = new Miembro("Alejo", "Sandrini", 41091789, Documento.DNI, unTrayecto);
    Solicitud solicitud4 = new Solicitud(
        unSector,
        miembro4,
        "Tengo ganas de empezar a trabajar"
    );
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    org2.agregarMedicion(medicion3);
    org2.agregarMedicion(medicion4);
    org2.addSector(unSector);
    sectorTerritorial.agregarOrganizacion(org2);
    sectorTerritorial.agregarOrganizacion(org);

    et.begin();
    // em.persist(ubicacion);
    em.persist(parada1);
    em.persist(parada2);
    em.persist(parada3);
    em.persist(parada4);
    em.persist(parada5);
    em.persist(colectivo7);
    em.persist(colectivo8);
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
    em.persist(miembro1);
    em.persist(solicitud1);
    em.persist(miembro2);
    em.persist(solicitud2);
    em.persist(miembro3);
    em.persist(solicitud3);
    em.persist(miembro4);
    em.persist(solicitud4);
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
    /*
    System.out.println("Tipos de consumo: " + RepoTiposConsumos.getInstance().getTiposConsumos());
    System.out.println("Factores de emision de tipos de consumo: " + RepoTiposConsumos.getInstance().getTiposConsumos().stream().map(tc -> tc.factorDeEmision.getFactor(UnidadEquivalenteCarbono.GRAMO)).collect(Collectors.toList()));
    System.out.println("Un Usuario: " + RepoUsuarios.getInstance().findByUsername("matias").getUser() + RepoUsuarios.getInstance().findByUsername("matias").getPassword() );
    System.out.println("Cerrando conexion");
     */
    // em.close();
  }
}
