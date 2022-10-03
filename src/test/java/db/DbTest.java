package db;

import domain.InicializacionTests;
import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
import domain.medicion.Periodicidad;
import domain.organizacion.Organizacion;
import domain.organizacion.RepoOrganizaciones;
import domain.reports.ReportGenerator;
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


public class DbTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityManager em = PerThreadEntityManagers.getEntityManager();
  EntityTransaction et = em.getTransaction();
  private Organizacion org;
  private MedicionAdapter medicionAdapter;
  private Medicion medicionAdaptada;
  private Medicion medicion1;
  private Medicion medicion2;
  private Medicion medicion3;
  private Medicion medicion4;

  @BeforeEach
  public void begin() {
    et.begin();
    InicializacionTests inicializador = new InicializacionTests();
    org = inicializador.getOrganizaciones().getOrgFalsa();
    medicionAdapter = inicializador.getMediciones().getUnAdapterDeMedicion();
    medicionAdaptada = medicionAdapter.adaptarMedicion(
        inicializador.getMediciones().getMedicionDeLectura2());
    MedicionRead medicionRead1 = new MedicionRead(
        "ELECTRICIDAD", "6000", "MENSUAL", "04/2021");
    medicion1 = new MedicionAdapter().adaptarMedicion(medicionRead1);
    MedicionRead medicionRead2 = new MedicionRead("GAS_NATURAL", "5000", "MENSUAL", "03/2022");
    medicion2 = new MedicionAdapter().adaptarMedicion(medicionRead2);
    MedicionRead medicionRead3 = new MedicionRead(
        "ELECTRICIDAD", "7000", "MENSUAL", "04/2021");
    medicion3 = new MedicionAdapter().adaptarMedicion(medicionRead3);
    MedicionRead medicionRead4 = new MedicionRead("GAS_NATURAL", "8000", "MENSUAL", "03/2022");
    medicion4 = new MedicionAdapter().adaptarMedicion(medicionRead4);
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
    org.agregarMedicion(medicionAdaptada);
    em.persist(org);
    Medicion medicion = RepoOrganizaciones.getInstance().getOrganizacion(org.getId()).getMediciones().get(0);
    assertEquals(medicionAdaptada, medicion);
  }

  @Test
  @DisplayName("Cuando guardo mediciones, saco correctamente la evolucion de HC")
  public void evaluacionDeHcEnPeriodo() {
    org.agregarMedicion(medicion1);
    org.agregarMedicion(medicion2);
    org.agregarMedicion(medicion3);
    org.agregarMedicion(medicion4);
    em.persist(medicion1);
    em.persist(medicion2);
    em.persist(medicion3);
    em.persist(medicion4);
    em.persist(org);
    List<Double> evolucion = ReportGenerator.getEvolucionHcDeOrganizacion(org.getId(), Periodicidad.MENSUAL, "04/2021", "03/2022", UnidadEquivalenteCarbono.KILOGRAMO);
    
    List<Double> miLista = Arrays.asList(6000.0, 5000.0, 7000.0, 8000.0);
    assertEquals(evolucion, miLista);
  }
}
