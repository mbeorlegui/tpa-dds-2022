package domain;

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
    contacto1 = inicializador.getContactos().getContacto1();
  }

  @DisplayName("Contacto tiene nombre correcto")
  @Test
  public void contactoTieneNombreCorrecto() {
    assertEquals(contacto1.getNombre(), "unNombre");
  }

  @DisplayName("Contacto tiene numero correcto")
  @Test
  public void contactoTieneNumeroCorrecto() {
    assertEquals(contacto1.getNumeroDeWhatsapp(), 123456789);
  }

  @DisplayName("Contacto tiene mail correcto")
  @Test
  public void contactoTieneMailCorrecto() {
    assertEquals(contacto1.getEmail(), "unEmail@falso.com");
  }


  @DisplayName("Al enviar notificacion de Whatsapp el programa rompe porque no se implementó")
  @Test
  public void enviarNotificacionPorWhatsappRompe() {
    String link = "google.com.ar";
    assertThrows(UnsupportedOperationException.class, () -> {
      Whatsapp unWpp = new Whatsapp();
      unWpp.enviarNotificacion(link, contacto1);
    });
  }


  @DisplayName("Al enviar notificacion de Email el programa rompe porque no se implementó")
  @Test
  public void enviarNotificacionPorEmailRompe() {
    String link = "google.com.ar";
    assertThrows(UnsupportedOperationException.class, () -> {
      Email unMail = new Email();
      unMail.enviarNotificacion(link, contacto1);
    });
  }


}
