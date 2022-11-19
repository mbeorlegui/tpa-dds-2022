package domain.trayecto;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoTramos implements WithGlobalEntityManager {
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
    entityManager().persist(tramo);
  }
}
