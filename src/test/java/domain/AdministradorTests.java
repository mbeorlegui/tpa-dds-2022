package domain;

import domain.administrador.Administrador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdministradorTests {
  static final String LARGE_TEXT =
      "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
  static final String SHORT_TEXT = "1728401";

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
    assertThrows(IllegalArgumentException.class, () -> {
      adminConPasswordVacio();
    });
  }

  @DisplayName("Admin no puede tener misma contraseña que usuario")
  @Test
  public void adminNoPuedeTenerIgualPasswordYUser() {
    assertThrows(IllegalArgumentException.class, () -> {
      adminConIgualPasswordYUsuario();
    });
  }

  @DisplayName("Admin no puede tener contraseña que contenga a usuario")
  @Test
  public void adminNoPuedeTeneUserQueContienePassword() {
    assertThrows(IllegalArgumentException.class, () -> {
      adminConUserQueContienePassword();
    });
  }

  @DisplayName("Admin no puede tener password menor a 8 caracteres")
  @Test
  public void adminNoPuedeTenerPasswordCorto() {
    assertThrows(IllegalArgumentException.class, () -> {
      adminConPasswordCorto();
    });
  }

  @DisplayName("Admin no puede tener password menor a 8 caracteres")
  @Test
  public void adminNoPuedeTenerPasswordLargo() {
    assertThrows(IllegalArgumentException.class, () -> {
      adminConPasswordLargo();
    });
  }

  @DisplayName("Admin no puede tener password con caracteres invalidos")
  @Test
  public void adminNoPuedeTenerPasswordConCaracteresInvalidos() {
    assertThrows(IllegalArgumentException.class, () -> {
      adminConPasswordConCaracteresInvalidos();
    });
  }

  @DisplayName("Admin puede tener password con caracteres 'raros' pero validos")
  @Test
  public void adminPuedeTenerPasswordConCaracteresRarosPeroValidos() {
    assertDoesNotThrow(() -> {
      adminConPasswordConCaracteresRarosPeroValidos();
    });
  }

  @DisplayName("Admin con password completo es valido")
  @Test
  public void adminConPasswordCompletoNoRompe() {
    assertDoesNotThrow(() -> {
      adminConPasswordCompleto();
    });
  }

  @DisplayName("Admin no puede tener contraseña comun")
  @Test
  public void adminNoPuedeTenerPasswordComun() {
    assertThrows(IllegalArgumentException.class, () -> {
      adminConPasswordComun();
    });
  }
  // 1111111111


  @DisplayName("Instanciar: Admin con igual user y password")
  private Administrador adminConUserQueContienePassword() throws FileNotFoundException {
    return new Administrador("matias", "matias");
  }

  @DisplayName("Instanciar: Admin con user que contiene a password")
  private Administrador adminConIgualPasswordYUsuario() throws FileNotFoundException {
    return new Administrador("matias", "matias");
  }

  @DisplayName("Instanciar: Admin con password corto")
  private Administrador adminConPasswordFacil() throws FileNotFoundException {
    return new Administrador("matias", "asd");
  }

  @DisplayName("Instanciar: Admin con password comun")
  private Administrador adminConPasswordComun() throws FileNotFoundException {
    return new Administrador("matias", "1111111111");
  }

  @DisplayName("Instanciar: Admin con password sin definir")
  private Administrador adminConPasswordVacio() throws FileNotFoundException {
    return new Administrador("matias", null);
  }

  @DisplayName("Instanciar: Admin con user sin definir")
  private Administrador adminConUserVacio() throws FileNotFoundException {
    return new Administrador(null, "AltaContrRaseNia_*3154");
  }

  @DisplayName("Instanciar: Admin con password largo")
  private Administrador adminConPasswordLargo() throws FileNotFoundException {
    return new Administrador("matias", LARGE_TEXT);
  }

  @DisplayName("Instanciar: Admin con password corto")
  private Administrador adminConPasswordCorto() throws FileNotFoundException {
    return new Administrador("matias", SHORT_TEXT);
  }

  @DisplayName("Instanciar: Admin con password completo")
  private Administrador adminConPasswordCompleto() throws FileNotFoundException {
    return new Administrador("matias", "AltaContrRaseNia_*3154");
  }

  @DisplayName("Instanciar: Admin con password con caracteres invalidos")
  private Administrador adminConPasswordConCaracteresRarosPeroValidos() throws FileNotFoundException {
    return new Administrador("matias", "AltaContrRaseNia_*3154");
  }

  @DisplayName("Instanciar: Admin con password con caracteres raros pero invalidos")
  private Administrador adminConPasswordConCaracteresInvalidos() throws FileNotFoundException {
    return new Administrador("matias", "AltaContrRaseNia_*3154¥");
  }

}
