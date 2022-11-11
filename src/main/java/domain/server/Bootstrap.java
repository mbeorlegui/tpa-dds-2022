package domain.server;

import domain.administrador.*;
import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
import domain.medicion.RepoTiposConsumos;
import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.*;
import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.transporte.Bicicleta;
import domain.transporte.Parada;
import domain.transporte.Pie;
import domain.transporte.ServicioContratado;
import domain.transporte.TipoDeServicioContratado;
import domain.transporte.TipoDeTransportePublico;
import domain.transporte.TipoDeVehiculo;
import domain.transporte.TransportePublico;
import domain.transporte.VehiculoParticular;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
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

    Ubicacion ubicacion = new Ubicacion(1, "Calle Falsa", "123");
    Ubicacion ubicacion2 = new Ubicacion(2, "Rivadavia", "4000");
    Ubicacion ubicacion3 = new Ubicacion(2, "Rivadavia", "4300");
    Ubicacion ubicacion4 = new Ubicacion(3, "Medrano", "500");
    Ubicacion ubicacion5 = new Ubicacion(5, "Mozart", "2300");
    Parada parada1 = new Parada(ubicacion, new ResultadoDistancia(1200,"M"));
    Parada parada2 = new Parada(ubicacion2, new ResultadoDistancia(2200,"M"));
    Parada parada3 = new Parada(ubicacion3, new ResultadoDistancia(800,"M"));
    Parada parada4 = new Parada(ubicacion4, new ResultadoDistancia(3200,"M"));
    Parada parada5 = new Parada(ubicacion5, new ResultadoDistancia(3800,"M"));
    Parada parada6 = new Parada(ubicacion4, new ResultadoDistancia(7800,"M"));
    Parada parada7 = new Parada(ubicacion2, new ResultadoDistancia(900,"M"));
    TransportePublico colectivo8 = new TransportePublico(RepoTiposConsumos.getInstance().getTiposConsumos().get(0),
        0.5, TipoDeTransportePublico.COLECTIVO, "8");
    colectivo8.addParadas(parada2, parada3, parada4);
    TransportePublico colectivo7 = new TransportePublico(RepoTiposConsumos.getInstance().getTiposConsumos().get(0),
        0.5, TipoDeTransportePublico.COLECTIVO, "7");
    colectivo7.addParadas(parada6, parada5, parada1, parada7);
    ServicioContratado taxi =  new ServicioContratado(RepoTiposConsumos.getInstance().getTiposConsumos().get(0), 2.0, TipoDeServicioContratado.TAXI);
    VehiculoParticular motoNafta = new VehiculoParticular(RepoTiposConsumos.getInstance().getTiposConsumos().get(2), 1.2, TipoDeVehiculo.MOTO);
    VehiculoParticular auto = new VehiculoParticular(RepoTiposConsumos.getInstance().getTiposConsumos().get(1), 2.5, TipoDeVehiculo.AUTO);
    Bicicleta bicicleta = new Bicicleta();
    Pie pie = new Pie();
    
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
    Sector otroSector = new Sector("Otro Sector");
    Sector otroSectorMas = new Sector("Otro Sector Mas");
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
    List<Tramo> tramos2 = new ArrayList<>();
    Tramo tramo2 = new Tramo(ubicacion3, ubicacion4, colectivo8);
    Tramo tramo3 = new Tramo(ubicacion4, ubicacion, colectivo7);
    tramos2.add(tramo2);
    tramos2.add(tramo3);
    Trayecto unTrayecto = new Trayecto(tramos);
    Trayecto trayecto2 = new Trayecto(tramos2);
    Miembro miembro1 = new Miembro("Matias", "Beorlegui", 41567890, Documento.DNI, unTrayecto);
    Solicitud solicitud1 = new Solicitud(
        unSector,
        miembro1,
        "El motivo de la solicitud es porque quiero trabajar allí por el gran clima laboral",
        LocalDateTime.now()
    );
    Miembro miembro2 = new Miembro("Ignacio", "Ardanaz", 41567890, Documento.DNI, unTrayecto);
    Solicitud solicitud2 = new Solicitud(
        otroSector,
        miembro2,
        "Necesito el trabajo, no llego a fin de mes",
        LocalDateTime.now().minusDays(2)
    );
    Miembro miembro3 = new Miembro("Alejo", "Goltzman", 41756189, Documento.DNI, unTrayecto);
    Solicitud solicitud3 = new Solicitud(
        otroSectorMas,
        miembro3,
        "Formar parte de la organización significaría un gran paso para mi carrera profesional",
        LocalDateTime.now().minusDays(1)
    );
    Miembro miembro4 = new Miembro("Alejo", "Sandrini", 41091789, Documento.DNI, trayecto2);
    Solicitud solicitud4 = new Solicitud(
        unSector,
        miembro4,
        "Tengo ganas de empezar a trabajar",
        LocalDateTime.now().minusHours(3)
    );
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    org2.agregarMedicion(medicion3);
    org2.agregarMedicion(medicion4);
    unSector.addMiembro(miembro4);
    org2.addSector(unSector);
    org2.addSector(otroSector);
    org.addSector(otroSectorMas);
    sectorTerritorial.agregarOrganizacion(org2);
    sectorTerritorial.agregarOrganizacion(org);
    Usuario usuario = new UsuarioGeneral("matias", "AltaContrRaseNia_*3154", miembro1);
    Usuario admin = new Administrador("alejo", "AltaContrRaseNia_*3154", org2);

    et.begin();
    // em.persist(ubicacion);
    em.persist(parada1);
    em.persist(parada2);
    em.persist(parada3);
    em.persist(parada4);
    em.persist(parada5);
    em.persist(parada6);
    em.persist(parada7);
    em.persist(colectivo7);
    em.persist(colectivo8);
    em.persist(taxi);
    em.persist(motoNafta);
    em.persist(auto);
    em.persist(bicicleta);
    em.persist(pie);
    em.persist(unSector);
    em.persist(otroSector);
    em.persist(otroSectorMas);
    em.persist(org);
    em.persist(org2);
    em.persist(sectorTerritorial);
    em.persist(tramo2);
    em.persist(tramo3);
    em.persist(trayecto2);
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

    em.persist(medicion1);
    em.persist(medicion2);

    em.persist(medicion3);
    em.persist(medicion4);
    // em.persist(org2);
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
    em.persist(usuario);
    em.persist(admin);
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
