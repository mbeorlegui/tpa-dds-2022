package domain.transporte;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoTransportes {
  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoTransportes INSTANCE = new RepoTransportes();

  public static RepoTransportes getInstance() {
    return INSTANCE;
  }

  public Transporte getTransporte(Long id) {
    return em.find(Transporte.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Transporte> getTransportes() {
    return em
        .createQuery("from Transporte")
        .getResultList();
  }
}