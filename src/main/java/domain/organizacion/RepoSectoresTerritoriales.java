package domain.organizacion;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoSectoresTerritoriales {
  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoSectoresTerritoriales INSTANCE = new RepoSectoresTerritoriales();

  public static RepoSectoresTerritoriales getInstance() {
    return INSTANCE;
  }

  public SectorTerritorial getSectorTerritorial(Long id) {
    return em.find(SectorTerritorial.class, id);
  }

  public List<SectorTerritorial> getSectoresTerritoriales() {
    return em
        .createQuery("from SectorTerritorial")
        .getResultList();
  }
}
