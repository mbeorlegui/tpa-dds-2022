package domain.medicion;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RepoMediciones {
  private final EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoMediciones INSTANCE = new RepoMediciones();

  public static RepoMediciones getInstance() {
    return INSTANCE;
  }

  public RepoMediciones() {
  }

  public void save(Medicion medicion) {
    EntityTransaction et = em.getTransaction();
    et.begin();
    em.persist(medicion);
    et.commit();
  }

  public void update(Medicion medicion) {
    EntityTransaction et = em.getTransaction();
    et.begin();
    em.merge(medicion);
    et.commit();
  }
}
