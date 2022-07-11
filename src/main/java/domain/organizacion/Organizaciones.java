package domain.organizacion;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Organizaciones {
  @Getter
  private List<Organizacion> organizaciones = new ArrayList<>();

  private static final Organizaciones INSTANCE = new Organizaciones();

  private Organizaciones() {
    // Inicializar
  }

  // Se ejecuta con tarea programada
  public void enviarGuiaDeRecomendaciones(String link) {
    organizaciones.forEach(
        org -> org.enviarGuiaDeRecomendaciones(link)
    );
  }

  public static Organizaciones getInstance() {
    return INSTANCE;
  }
  // Lo llamamos con Organizaciones.getInstance()
}
