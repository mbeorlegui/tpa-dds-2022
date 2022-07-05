package domain.organizacion;

import java.util.ArrayList;
import java.util.List;

public class Organizaciones {
  private List<Organizacion> organizaciones = new ArrayList<>();

  private static final Organizaciones INSTANCE = new Organizaciones();

  private Organizaciones() {
    // Inicializar
  }

  // Lo llamamos con Organizaciones.getInstance()
}
