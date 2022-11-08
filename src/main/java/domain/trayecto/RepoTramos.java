package domain.trayecto;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoTramos {
  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoTramos INSTANCE = new RepoTramos();

  public static RepoTramos getInstance() {
    return INSTANCE;
  }

  public Tramo getTramo(Long id) {
    return em.find(Tramo.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Tramo> getTramos() {
    return em
        .createQuery("from Tramo")
        .getResultList();
  }

  public void persistTramo(Tramo tramo) {
    em.persist(tramo);
  }
}
