package domain.organizacion;

import lombok.Getter;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RepoSectores {
  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoSectores INSTANCE = new RepoSectores();

  private RepoSectores() {
    // Inicializar
  }

  public static RepoSectores getInstance() {
    return INSTANCE;
  }

  public Sector getSector(Long id) {
    return em.find(Sector.class, id);
  }
}
