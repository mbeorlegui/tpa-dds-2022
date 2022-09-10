package domain.dbrunner;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;

public class Runner {
  public static void main(String[] args) {
    System.out.println("Hola!");
    EntityManager em = PerThreadEntityManagers.getEntityManager();
    em.getProperties();
  }
}
