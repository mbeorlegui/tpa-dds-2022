package db;

import domain.InicializacionTests;
import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.*;
import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.RepoOrganizaciones;
import domain.organizacion.TipoOrganizacion;
import domain.reports.ReportGenerator;
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


public class DbTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityManager em = PerThreadEntityManagers.getEntityManager();
  EntityTransaction et = em.getTransaction();
  private Organizacion org;
  private Medicion medicion1;
  private Medicion medicion2;

  @BeforeEach
  public void begin() {
    et.begin();
    Ubicacion ubicacion = new Ubicacion(1, "Calle Falsa", "123");
    org = new Organizacion(
        "Prueba Empresa",
        TipoOrganizacion.EMPRESA,
        ubicacion,
        Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
    RepoTiposConsumos.getInstance().actualizarTiposDeConsumoDB();
    MedicionRead medicionRead1 = new MedicionRead(
        "ELECTRICIDAD", "6000", "MENSUAL", "04/2021");
    medicion1 = new MedicionAdapter().adaptarMedicion(medicionRead1);
    MedicionRead medicionRead2 = new MedicionRead("GAS_NATURAL", "5000", "MENSUAL", "03/2022");
    medicion2 = new MedicionAdapter().adaptarMedicion(medicionRead2);
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
