package domain;

import static org.mockito.Mockito.*;
import domain.medios.*;
import domain.organizacion.Organizacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediosTest {

  private Contacto contacto1;
  private Organizacion utn;
  private MedioDeComunicacion medioDeComunicacion;
  private String guiaDeRecomendaciones = "http:\\linkEjemplo.com";

  @BeforeEach
  void init() {
    InicializacionTests inicializador = new InicializacionTests();
    contacto1 = inicializador.getContacto1();
    utn = inicializador.getUtn();
    medioDeComunicacion = mock(MedioDeComunicacion.class);
    utn.agregarMedioDeComunicacion(medioDeComunicacion);
    while(utn.getContactos().size()<5){
      utn.agregarContacto(contacto1);
    }
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

  @Test
  public void enviarNotificacionContactosUTN(){
    doNothing().when(medioDeComunicacion).enviarNotificacion(isA(String.class), isA(Contacto.class));
    utn.enviarGuiaDeRecomendaciones(guiaDeRecomendaciones);
    verify(medioDeComunicacion, times(5)).enviarNotificacion(guiaDeRecomendaciones, utn.getContactos().get(0));
  }
}
