package db;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.TipoOrganizacion;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  @Test
  public void contextUp() {
    assertNotNull(entityManager());
  }

  @Test
  public void contextUpWithTransaction() throws Exception {
    withTransaction(() -> {
    });
  }

}
