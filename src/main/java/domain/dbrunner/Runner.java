package domain.dbrunner;

import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.TipoOrganizacion;
import domain.reports.ReportGenerator;
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
    Ubicacion ubicacion = new Ubicacion(1, "Calle Falsa", "123");
    Organizacion org = new Organizacion("Prueba", TipoOrganizacion.EMPRESA, ubicacion, Clasificacion.UNIVERSIDAD);
    et.begin();
    em.persist(ubicacion);
    em.persist(org);
    et.commit();
    System.out.println("Ubicacion 0: " + ReportGenerator.getUbicaciones().get(0).getCalle());
    System.out.println("Organizacion 0: " + ReportGenerator.getOrganizaciones().get(0).getRazonSocial());
    System.out.println("Cerrando conexion");
  }
}
