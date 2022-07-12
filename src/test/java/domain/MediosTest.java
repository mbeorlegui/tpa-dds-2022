package domain;

import static org.mockito.Mockito.*;
import domain.medios.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediosTest {

  private Contacto contacto1;

  @BeforeEach
  void init() {
    InicializacionTests inicializador = new InicializacionTests();
    contacto1 = inicializador.getContacto1();
  }

  @Test
  public void contactoTieneNombreCorrecto() {
    assertEquals(contacto1.getNombre(), "unNombre");
  }

  @Test
  public void contactoTieneNumeroCorrecto() {
    assertEquals(contacto1.getNumeroDeWhatsapp(), 123456789);
  }

  @Test
  public void contactoTieneMailCorrecto() {
    assertEquals(contacto1.getEmail(), "unEmail@falso.com");
  }
}
