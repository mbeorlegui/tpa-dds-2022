package domain.administrador;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoSolicitudes {

  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoSolicitudes INSTANCE = new RepoSolicitudes();

  public static RepoSolicitudes getInstance() {
    return INSTANCE;
  }

  public RepoSolicitudes() {
  }

  public List<Solicitud> getSolicitudes() {
    return em
        .createQuery("from Solicitud")
        .getResultList();
  }

  public Solicitud getSolicitud(Long id) {
    return em.find(Solicitud.class, id);
  }

  public void persistSolicitud(Solicitud solicitud) {
    em.persist(solicitud);
  }


}
