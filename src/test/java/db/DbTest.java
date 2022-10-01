package db;

import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.TipoOrganizacion;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class DbTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityManager em = PerThreadEntityManagers.getEntityManager();
  EntityTransaction et = em.getTransaction();

  @BeforeEach
  public void begin() throws Exception {
    et.begin();
  }

  @AfterEach
  public void tearDown() {
    et.rollback();
  }

  @Test
  public void guardarOrganizacion() {
    Ubicacion ubicacion = new Ubicacion(1, "Calle Falsa", "123");
    Organizacion org = new Organizacion(
        "Prueba Empresa",
        TipoOrganizacion.EMPRESA,
        ubicacion,
        Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
    Sector unSector = new Sector();
    org.addSector(unSector);
    em.persist(org);
  }
}
