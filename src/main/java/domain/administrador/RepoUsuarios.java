package domain.administrador;

import domain.organizacion.Organizacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoUsuarios {

  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  private static final RepoUsuarios INSTANCE = new RepoUsuarios();

  public static RepoUsuarios getInstance() {
    return INSTANCE;
  }

  public RepoUsuarios() {
  }

  public List<Usuario> getUsuarios() {
    return em
        .createQuery("from Usuario")
        .getResultList();
  }

  public Usuario getUsuario(Long id) {
    return em.find(Usuario.class, id);
  }

  public Usuario getUsuarioByUsername(String user) {
    return (Usuario) em
        .createQuery("from Usuario where user = :user")
        .setParameter("user",user)
        .getSingleResult();
  }

  public Usuario findByUsername(String username) {
    System.out.println(username);
    System.out.println(this.getUsuarios());
    return this.getUsuarios().stream().filter(usuario -> usuario.getUser().equals(username)).findAny().get();
  }

  public void update(Usuario user) {
    EntityTransaction et = em.getTransaction();
    et.begin();
    em.merge(user);
    et.commit();
  }
}
