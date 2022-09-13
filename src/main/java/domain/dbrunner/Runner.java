package domain.dbrunner;

import domain.ubicacion.Ubicacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Runner {
  // Probar con: mvn compile exec:java
  public static void main(String[] args) {
    System.out.println("Ejecutando queries!");
    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction et = em.getTransaction();
    et.begin();
    Ubicacion ubicacion = new Ubicacion(1, "Calle Falsa", "123");
    em.persist(ubicacion);
    et.commit();
    System.out.println("Cerrando conexion");
  }
}
