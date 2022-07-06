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

  // Lo llamamos con Organizaciones.getInstance()
}
