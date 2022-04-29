package domain;

import domain.administrador.Administrador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordValidatorTests {
  @DisplayName("Admin no puede tener usuario vacio")
  @Test
  public void adminNoPuedeTenerUserVacio() {
    assertThrows(NullPointerException.class, () -> {
      adminConUserVacio();
    });
  }

  @DisplayName("Admin no puede tener contraseña vacia")
  @Test
  public void adminNoPuedeTenerPasswordVacio() {
    assertThrows(NullPointerException.class, () -> {
      adminConPasswordVacio();
    });
  }


  @DisplayName("Instanciar: Admin con igual user y password")
  private Administrador adminConIgualPasswordYUsuario() {
    return new Administrador("matias", "matias");
  }

  @DisplayName("Instanciar: Admin con password corto")
  private Administrador adminConPasswordFacil() {
    return new Administrador("matias", "asd");
  }

  @DisplayName("Instanciar: Admin con password completo")
  private Administrador adminConPasswordCompleto() {
    return new Administrador("matias", "AltaContrRaseNia_*3154");
  }

  @DisplayName("Instanciar: Admin con password comun")
  private Administrador adminConPasswordComun() {
    return new Administrador("matias", "11111111114");
  }

  @DisplayName("Instanciar: Admin con password sin definir")
  private Administrador adminConPasswordVacio() {
    return new Administrador("matias", null);
  }

  @DisplayName("Instanciar: Admin con user sin definir")
  private Administrador adminConUserVacio() {
    return new Administrador(null, "AltaContrRaseNia_*3154");
  }


}
