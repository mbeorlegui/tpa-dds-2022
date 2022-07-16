package domain.organizacion;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class OrganizacionesRepositorio {
  @Getter
  private static List<Organizacion> organizaciones = new ArrayList<>();

  private static final OrganizacionesRepositorio INSTANCE = new OrganizacionesRepositorio();

  private OrganizacionesRepositorio() {
    // Inicializar
  }

  // Se ejecuta con tarea programada
  public static void enviarGuiaDeRecomendaciones(String link) {
    organizaciones.forEach(
        org -> org.enviarGuiaDeRecomendaciones(link)
    );
  }

  public static void main(String[] args) {
    System.out.println("Guia de recomendaciones enviada a contactos - Link: " + args[0]);
    enviarGuiaDeRecomendaciones(args[0]);
  }
  // Correr con:
  // java -cp $PWD domain.organizacion.OrganizacionesRepositorio "google.com"
  // $PWD -> Variable de entorno que almacena el directorio actual

  public static OrganizacionesRepositorio getInstance() {
    return INSTANCE;
  }
  // Lo llamamos con OrganizacionesRepositorio.getInstance()
}
