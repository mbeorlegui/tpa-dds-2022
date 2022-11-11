package domain.trayecto;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoTrayectos {
  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoTrayectos INSTANCE = new RepoTrayectos();

  public static RepoTrayectos getInstance() {
    return INSTANCE;
  }

  public Trayecto getTrayecto(Long id) {
    return em.find(Trayecto.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Trayecto> getTrayectos() {
    return em
        .createQuery("from Trayecto")
        .getResultList();
  }

  public void persistTrayecto(Trayecto trayecto) {
    em.persist(trayecto);
  }
}
