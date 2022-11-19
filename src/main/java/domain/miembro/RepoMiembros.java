package domain.miembro;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoMiembros implements WithGlobalEntityManager {
  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoMiembros INSTANCE = new RepoMiembros();

  public static RepoMiembros getInstance() {
    return INSTANCE;
  }

  public Miembro getMiembro(Long id) {
    return em.find(Miembro.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Miembro> getMiembros() {
    return em
        .createQuery("from Miembro")
        .getResultList();
  }

  public void updateMiembro(Miembro miembro) {
    entityManager().merge(miembro);
  }
    
}
