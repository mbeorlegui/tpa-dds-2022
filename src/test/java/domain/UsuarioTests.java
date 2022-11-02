package domain;

import domain.administrador.Administrador;
import domain.administrador.TipoUsuario;
import domain.administrador.Usuario;
import domain.exceptions.InvalidPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTests {
  static final String LARGE_TEXT =
      "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
  static final String SHORT_TEXT = "1Ab_401";

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
    assertThrows(InvalidPasswordException.class, () -> {
      adminConPasswordVacio();
    });
  }

  @DisplayName("Admin no puede tener misma contraseña que usuario")
  @Test
  public void adminNoPuedeTenerIgualPasswordYUser() {
    assertThrows(InvalidPasswordException.class, () -> {
      adminConIgualPasswordYUsuario();
    });
  }

  @DisplayName("Admin no puede tener contraseña que contenga a usuario")
  @Test
  public void adminNoPuedeTenerUserQueContienePassword() {
    assertThrows(InvalidPasswordException.class, () -> {
      adminConUserQueContienePassword();
    });
  }

  @DisplayName("Admin no puede tener password menor a 8 caracteres")
  @Test
  public void adminNoPuedeTenerPasswordCorto() {
    assertThrows(InvalidPasswordException.class, () -> {
      adminConPasswordCorto();
    });
  }

  @DisplayName("Admin no puede tener password mayor a 64 caracteres")
  @Test
  public void adminNoPuedeTenerPasswordLargo() {
    assertThrows(InvalidPasswordException.class, () -> {
      adminConPasswordLargo();
    });
  }

  @DisplayName("Admin no puede tener password con caracteres invalidos")
  @Test
  public void adminNoPuedeTenerPasswordConCaracteresInvalidos() {
    assertThrows(InvalidPasswordException.class, () -> {
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
    assertThrows(InvalidPasswordException.class, () -> {
      adminConPasswordComun();
    });
  }

//  @Test
//  public void adminConPasswordCompletoGeneraNuevoFactorDeEmision() {
//    adminConPasswordCompleto().generarNuevoFactorDeEmision(
//        Actividad.ELECTRICIDAD, Alcance.OTRAS_EMISIONES, Unidad.SIN_UNIDAD, new FactorDeEmision(120.0, UnidadEquivalenteCarbono.GRAMO),
//        "factorDeEmisionDePrueba");
//    assertTrue(RepoTiposConsumos.getInstance().existeTipo("factorDeEmisionDePrueba"));
//  }
//  TODO: se deberia pasar a los tests de db, ya que generarNuevoFactorDeEmision deberia usar persist (eso supongo)


  @DisplayName("Instanciar: Admin con igual user y password")
  private Usuario adminConUserQueContienePassword() {
    return new Administrador("matias", "matias", null);
  }

  @DisplayName("Instanciar: Admin con user que contiene a password")
  private Usuario adminConIgualPasswordYUsuario() {
    return new Administrador("matias", "matias", null);
  }

  @DisplayName("Instanciar: Admin con password corto")
  private Usuario adminConPasswordFacil() {
    return new Administrador("matias", "asd", null);
  }

  @DisplayName("Instanciar: Admin con password comun")
  private Usuario adminConPasswordComun() {
    return new Administrador("matias", "1111111111", null);
  }

  @DisplayName("Instanciar: Admin con password sin definir")
  private Usuario adminConPasswordVacio() {
    return new Administrador("matias", null, null);
  }

  @DisplayName("Instanciar: Admin con user sin definir")
  private Usuario adminConUserVacio() {
    return new Administrador(null, "AltaContrRaseNia_*3154", null);
  }

  @DisplayName("Instanciar: Admin con password largo")
  private Usuario adminConPasswordLargo() {
    return new Administrador("matias", LARGE_TEXT, null);
  }

  @DisplayName("Instanciar: Admin con password corto")
  private Usuario adminConPasswordCorto() {
    return new Administrador("matias", SHORT_TEXT, null);
  }

  @DisplayName("Instanciar: Admin con password completo")
  private Usuario adminConPasswordCompleto() {
    return new Administrador("matias", "AltaContrRaseNia_*3154", null);
  }

  @DisplayName("Instanciar: Admin con password con caracteres invalidos")
  private Usuario adminConPasswordConCaracteresRarosPeroValidos() {
    return new Administrador("matias", "AltaContrRaseNia_*3154", null);
  }

  @DisplayName("Instanciar: Admin con password con caracteres raros pero invalidos")
  private Usuario adminConPasswordConCaracteresInvalidos() {
    return new Administrador("matias", "AltaContrRaseNia_*3154¥", null);
  }

}
