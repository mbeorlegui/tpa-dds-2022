package domain;

import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.Tipo;
import domain.transporte.Pie;
import domain.transporte.TipoDeTransportePublico;
import domain.transporte.TransportePublico;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MiembroTests {
  private Organizacion utn;
  private Organizacion orgFalsa;
  private Miembro miembro1;
  private Miembro miembro2;
  private Miembro miembro3;


  @BeforeEach
  private void init(){
    InicializacionTests inicializador = new InicializacionTests();
    utn = inicializador.getUtn();
    orgFalsa = inicializador.getOrgFalsa();
    miembro1 = inicializador.getUnMiembro();
    miembro2 = inicializador.getOtroMiembro();
  }
  
  @DisplayName("Al agregar varios sectores a Miembro sin repetir")
  @Test
  public void correctoAgregadoDeMiembrosASectoresDeDosOrganizaciones() {
    assertTrue(utn.esMiembro(miembro2));
    assertTrue(orgFalsa.esMiembro(miembro1));
    assertTrue(orgFalsa.esMiembro(miembro2));
  }

  @Test
  public void unMiembroSeCreaCorrectamente() {
    assertEquals(miembro1.getNombre(), "Matias");
    assertEquals(miembro1.getApellido(), "Beorlegui");
    assertEquals(miembro1.getNumeroDeDocumento(), 47813065);
    assertEquals(miembro1.getTipoDeDocumento(), Documento.DNI);
  }
}
