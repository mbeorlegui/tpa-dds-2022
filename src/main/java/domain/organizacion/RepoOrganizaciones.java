package domain.organizacion;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import lombok.Getter;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RepoOrganizaciones {
  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  @Getter
  private static List<Organizacion> organizaciones = new ArrayList<>();

  private static final RepoOrganizaciones INSTANCE = new RepoOrganizaciones();

  private RepoOrganizaciones() {
    // Inicializar
  }

  @SuppressWarnings("unchecked")
  public List<Organizacion> getOrganizaciones() {
    return em
        .createQuery("from Organizacion")
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Organizacion> getOrganizacionesPorTipo(TipoOrganizacion tipoOrganizacion) {
    return em
        .createQuery("from Organizacion where tipo_organizacion = :tipoOrg")
        .setParameter("tipoOrg", tipoOrganizacion.name())
        .getResultList();
  }

  public Organizacion getOrganizacion(Long id) {
    return em.find(Organizacion.class, id);
  }

  public double huellaDeCarbonoEnPeriodoDeOrganizaciones(List<Organizacion> organizaciones,
                                                         Periodicidad periodicidad,
                                                         String periodoDeImputacion,
                                                         UnidadEquivalenteCarbono unidadDeseada) {
    return organizaciones
        .stream()
        .mapToDouble(org ->
            org.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada))
        .sum();
  }

  public double hcTotalDeOrganizacionesDeTipo(TipoOrganizacion tipoOrganizacion,
                                              Periodicidad periodicidad,
                                              String periodoDeImputacion,
                                              UnidadEquivalenteCarbono unidadDeseada) {
    return this
        .huellaDeCarbonoEnPeriodoDeOrganizaciones(
            this.getOrganizacionesPorTipo(tipoOrganizacion),
            periodicidad,
            periodoDeImputacion,
            unidadDeseada
        );
  }

  // Se ejecuta con tarea programada
  public static void enviarGuiaDeRecomendaciones(String link) {
    organizaciones.forEach(
        org -> org.enviarGuiaDeRecomendaciones(link)
    );
  }

  public static void main(String[] args) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    String currentDate = formatter.format(date);
    System.out.println(
        "[" + currentDate + "] - INFO - Guia de recomendaciones enviada a contactos - Link: "
            + args[0]
    );
    enviarGuiaDeRecomendaciones(args[0]);
  }
  // Correr con:
  // java -cp $PWD domain.organizacion.RepoOrganizaciones "google.com"
  // $PWD -> Variable de entorno que almacena el directorio actual

  public static RepoOrganizaciones getInstance() {
    return INSTANCE;
  }
  // Lo llamamos con RepoOrganizaciones.getInstance()
}
