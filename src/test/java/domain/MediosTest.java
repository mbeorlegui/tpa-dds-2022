package domain;

import domain.medios.*;
import domain.organizacion.Organizacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MediosTest {

  private Contacto contacto1;
  private Contacto contacto2;
  private Contacto contacto3;
  private Organizacion utn;
  private MedioDeComunicacion medioDeComunicacion;
  private String guiaDeRecomendaciones = "http:\\linkEjemplo.com";

  @BeforeEach
  void init() {
    InicializacionTests inicializador = new InicializacionTests();
    contacto1 = inicializador.getContactos().getContacto1();
    contacto2 = inicializador.getContactos().getContacto2();
    contacto3 = inicializador.getContactos().getContacto3();
    utn = inicializador.getOrganizaciones().getUtn();
    medioDeComunicacion = mock(MedioDeComunicacion.class);
    utn.agregarMedioDeComunicacion(medioDeComunicacion);
    utn.agregarContactos(contacto2, contacto3);
  }

  @DisplayName("Contacto tiene nombre correcto")
  @Test
  public void contactoTieneNombreCorrecto() {
    assertEquals(contacto1.getNombre(), "Sofia");
  }

  @DisplayName("Contacto tiene numero correcto")
  @Test
  public void contactoTieneNumeroCorrecto() {
    assertEquals(contacto1.getNumeroDeWhatsapp(), 123456789);
  }

  @DisplayName("Contacto tiene mail correcto")
  @Test
  public void contactoTieneMailCorrecto() {
    assertEquals(contacto1.getEmail(), "sofia@mail.com");
  }

  @Test
  public void enviarNotificacionContactosUTN(){
    doNothing().when(medioDeComunicacion).enviarNotificacion(isA(String.class), isA(Contacto.class));
    utn.enviarGuiaDeRecomendaciones(guiaDeRecomendaciones);
    for (Contacto contacto: utn.getContactos()){
      verify(medioDeComunicacion, times(1)).enviarNotificacion(guiaDeRecomendaciones, contacto);
    }
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
