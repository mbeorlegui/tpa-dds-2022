package domain.administrador;

import domain.organizacion.Organizacion;
import domain.organizacion.RepoOrganizaciones;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.List;

public class RepoUsuarios {

  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoUsuarios INSTANCE = new RepoUsuarios();

  public static RepoUsuarios getInstance() {
    return INSTANCE;
  }

  public List<Usuario> getUsuarios() {
    return em
        .createQuery("from Usuario")
        .getResultList();
  }

  public Usuario getUsuario(Long id) {
    return em.find(Usuario.class, id);
  }

}
