package domain.organizacion;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
  // java -cp $PWD domain.organizacion.OrganizacionesRepositorio "google.com"
  // $PWD -> Variable de entorno que almacena el directorio actual

  public static OrganizacionesRepositorio getInstance() {
    return INSTANCE;
  }
  // Lo llamamos con OrganizacionesRepositorio.getInstance()
}
