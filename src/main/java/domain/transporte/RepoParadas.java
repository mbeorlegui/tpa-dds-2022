package domain.transporte;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoParadas {
  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoParadas INSTANCE = new RepoParadas();

  public static RepoParadas getInstance() {
    return INSTANCE;
  }

  public Parada getParada(Long id) {
    return em.find(Parada.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Parada> getParadas() {
    return em
        .createQuery("from Parada")
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Parada> getParadasDeTransporte(Transporte transporte) {
    return em
        .createQuery("from Parada where transporte_publico_id = :id")
        .setParameter("id", transporte.getId())
        .getResultList();
  }
}