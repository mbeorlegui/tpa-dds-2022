package db;

import domain.InicializacionTests;
import domain.administrador.UnidadEquivalenteCarbono;
import domain.inicializacion.InstanciasOrganizacion;
import domain.medicion.*;
import domain.organizacion.*;
import domain.reports.ReportGenerator;
import domain.reports.ReporteDeComposicion;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DbTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityManager em = PerThreadEntityManagers.getEntityManager();
  EntityTransaction et = em.getTransaction();
  private Organizacion org;
  private Organizacion utn;
  private Organizacion orgFalsa;
  private Medicion medicion1;
  private Medicion medicion2;
  private SectorTerritorial sectorTerritorial1;

  @BeforeEach
  public void begin() {
    InicializacionTests inicializador = new InicializacionTests();
    et.begin();
    Ubicacion ubicacion = new Ubicacion(1, "Calle Falsa", "123");
    org = new Organizacion(
        "Prueba Empresa",
        TipoOrganizacion.EMPRESA,
        ubicacion,
        Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
    utn = inicializador.getOrganizaciones().getUtn();
    orgFalsa = inicializador.getOrganizaciones().getOrgFalsa();
    RepoTiposConsumos.getInstance().actualizarTiposDeConsumoDB();
    MedicionRead medicionRead1 = new MedicionRead(
        "ELECTRICIDAD", "6000", "MENSUAL", "04/2021");
    medicion1 = new MedicionAdapter().adaptarMedicion(medicionRead1);
    MedicionRead medicionRead2 = new MedicionRead("GAS_NATURAL", "5000", "MENSUAL", "03/2022");
    medicion2 = new MedicionAdapter().adaptarMedicion(medicionRead2);
    sectorTerritorial1 = new SectorTerritorial();
    sectorTerritorial1.agregarOrganizacion(org);
    sectorTerritorial1.agregarOrganizacion(utn);
  }

  @AfterEach
  public void tearDown() {
    et.rollback();
  }

  @Test
  @DisplayName("Cuando guardo una organizacion en la base, la saco correctamente de la misma")
  public void guardarOrganizacion() {
    em.persist(org);
    Organizacion orgDeBase = RepoOrganizaciones.getInstance().getOrganizacion(org.getId());
    assertEquals(org, orgDeBase);
  }

  @Test
  @DisplayName("Cuando guardo una organizacion en la base, saco correctamente sus mediciones")
  public void guardarMedicionesDeOrg() {
    org.agregarMedicion(medicion1);
    em.persist(org);
    Medicion medicion = RepoOrganizaciones.getInstance().getOrganizacion(org.getId()).getMediciones().get(0);
    assertEquals(medicion1, medicion);
  }

  @Test
  @DisplayName("Calculo hc del sector territorial con dos organizaciones")
  public void hcTotalPorSectorTerritorial() {
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    em.persist(org);
    em.persist(utn);
    em.persist(sectorTerritorial1);
    double hcOrg = org.huellaDeCarbonoEnPeriodo(Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);
    double hcUtn = utn.huellaDeCarbonoEnPeriodo(Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);

    double hcSectorTerritorial = ReportGenerator.hcTotalDeSectorTerritorial(
        sectorTerritorial1.getId(), Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);
    assertEquals(hcSectorTerritorial, hcOrg + hcUtn);
  }

  @Test
  @DisplayName("Calculo hc por tipo de organizacion (empresa)")
  public void hcTotalPorTipoDeOrganizacion() {
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    em.persist(org);
    em.persist(orgFalsa);
    double hcOrg = org.huellaDeCarbonoEnPeriodo(Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);
    double hcOrgFalsa = orgFalsa.huellaDeCarbonoEnPeriodo(Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);

    double hcSectorTerritorial = ReportGenerator.hcTotalDeOrganizacionesDeTipo(
        TipoOrganizacion.EMPRESA, Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);
    assertEquals(hcSectorTerritorial, hcOrg + hcOrgFalsa);
  }

  @Test
  @DisplayName("Composición de HC total de un sector territorial con dos organizaciones")
  public void composicionHcSectorTerritorial() {
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    em.persist(org);
    em.persist(utn);
    em.persist(sectorTerritorial1);

    double hcMedicionesOrg = org.hcMedicionesEnPeriodo(Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);
    double hcMedicionesUtn = utn.hcMedicionesEnPeriodo(Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);
    double hcTrayectosOrg = org.hcTrayectosMiembros(Periodicidad.MENSUAL,
        UnidadEquivalenteCarbono.GRAMO);
    double hcTrayectosUtn = utn.hcTrayectosMiembros(Periodicidad.MENSUAL,
        UnidadEquivalenteCarbono.GRAMO);

    ReporteDeComposicion ejemploReporte = new ReporteDeComposicion(
        hcMedicionesOrg+hcMedicionesUtn,hcTrayectosOrg+hcTrayectosUtn);

    ReporteDeComposicion reporteDeComposicionCalculado = ReportGenerator.composicionHcDeSectorTerritorial(
        sectorTerritorial1.getId(), Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);

    assertTrue(reporteDeComposicionCalculado.tieneMismoHC(ejemploReporte));
  }

  @Test
  @DisplayName("Composición de HC total de una organizacion")
  public void composicionHcOrganizacion() {
    em.persist(utn);

    double hcMedicionesUtn = utn.hcMedicionesEnPeriodo(Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);
    double hcTrayectosUtn = utn.hcTrayectosMiembros(Periodicidad.MENSUAL,
        UnidadEquivalenteCarbono.GRAMO);

    ReporteDeComposicion ejemploReporte = new ReporteDeComposicion(hcMedicionesUtn, hcTrayectosUtn);

    ReporteDeComposicion reporteDeComposicionCalculado = ReportGenerator.composicionHcDeOrganizacion(
        utn.getId(), Periodicidad.MENSUAL,
        "04/2021", UnidadEquivalenteCarbono.GRAMO);

    assertTrue(reporteDeComposicionCalculado.tieneMismoHC(ejemploReporte));
  }

  @Test
  @DisplayName("Evolucion de hc de un sector territorial con dos organizaciones")
  public void evolucionDeHcDeUnSectorTerritorial() {
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    em.persist(org);
    em.persist(utn);
    em.persist(sectorTerritorial1);
    List<Double> evolucionCalculada = ReportGenerator.getEvolucionHcDeSector(
        sectorTerritorial1.getId(), Periodicidad.MENSUAL, "04/2021",
        "03/2022", UnidadEquivalenteCarbono.GRAMO);

    List<Double> miLista = Arrays.asList(3559.2, 1159.2, 1159.2, 1159.2, 1159.2,
        1159.2, 1159.2, 1159.2, 1159.2, 1159.2, 1159.2, 2159.2);
    assertEquals(miLista, evolucionCalculada);
  }
  @Test
  @DisplayName("Cuando guardo mediciones, saco correctamente la evolucion de HC")
  public void evaluacionDeHcEnPeriodo() {
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    em.persist(org);
    List<Double> evolucionCalculada = ReportGenerator.getEvolucionHcDeOrganizacion(
        org.getId(), Periodicidad.MENSUAL, "04/2021", "03/2022", UnidadEquivalenteCarbono.KILOGRAMO);

    List<Double> miLista = Arrays.asList(2.4, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);
    System.out.println(org);
    assertEquals(miLista, evolucionCalculada);
  }
}
