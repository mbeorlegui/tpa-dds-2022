package domain.administrador;

import domain.organizacion.Organizacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

  public List<Solicitud> getSolicitudesDeOrganizacion(Organizacion organizacion) {
    return this.getSolicitudesOrdenadasPorFecha()
        .stream()
        .filter(solicitud -> organizacion.tieneSectorConId(solicitud.getSector().getId()))
        .collect(Collectors.toList());
  }

  public List<Solicitud> getSolicitudesOrdenadasPorFecha() {
    List<Solicitud> solicitudesOrdenadas = this.getSolicitudes();
    Collections.sort(solicitudesOrdenadas, new Comparator<Solicitud>() {
      @Override
      public int compare(Solicitud s1, Solicitud s2) {
        return s1.getFechaGeneracion().compareTo(s2.getFechaGeneracion());
      }
    });
    return solicitudesOrdenadas;
  }

  public List<Solicitud> getSolicitudesPendientesDeOrganizacion(Organizacion organizacion) {
    return this.getSolicitudesDeOrganizacion(organizacion)
        .stream()
        .filter(solicitud -> solicitud.estaPendiente())
        .collect(Collectors.toList());
  }
}
