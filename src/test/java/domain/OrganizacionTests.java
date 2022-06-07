package domain;

import domain.organizacion.*;
import org.junit.jupiter.api.BeforeEach;
import domain.exceptions.NoPuedeSerTrayectoCompartidoException;
import domain.exceptions.NonMemberException;
import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.Tipo;
import domain.transporte.*;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrganizacionTests {
  private Organizacion utn;
  private Organizacion orgFalsa;
  private Miembro miembro1;
  private Miembro miembro2;
  private Miembro miembro3;
  private Trayecto trayectoConServicioContratadoYVehiculoParticular;
  private Trayecto casaHastaUTN;


  @BeforeEach
  private void init(){
    InicializacionTests inicializador = new InicializacionTests();
    utn = inicializador.getUtn();
    orgFalsa = inicializador.getOrgFalsa();
    miembro1 = inicializador.getUnMiembro();
    miembro2 = inicializador.getOtroMiembro();
    miembro3 = new Miembro("Alejo","Sandrini",43987654, Documento.DNI, casaHastaUTN);
    trayectoConServicioContratadoYVehiculoParticular = inicializador.getServicioContratadoYVehiculoParticular();
    casaHastaUTN = inicializador.getCasaHastaUTN();
  }

  @DisplayName("La Universidad es de Tipo Gubernamental")
  @Test
  public void unaUniversidadGubernamentalEsDeTipoGubernamental() {
    assertEquals(utn.getTipo(), Tipo.GUBERNAMENTAL);
  }

  @DisplayName("La Universidad es una Universidad")
  @Test
  public void unaUniversidadGubernamentalEsUnaUniversidad() {
    assertEquals(utn.getClasificacion(), Clasificacion.UNIVERSIDAD);
  }

  @DisplayName("La organizacion del sector RRHH es la Universidad")
  @Test
  public void laUtnTieneClasificacionUniversidadTipoGubernamental() {
    assertEquals(utn.getClasificacion(), Clasificacion.UNIVERSIDAD);
    assertEquals(utn.getTipo(), Tipo.GUBERNAMENTAL);
    assertEquals(utn.getRazonSocial(),"UTN");
  }

  @DisplayName("La Universidad tiene 3 sectores")
  @Test
  public void laUniversidadTieneTresSectores() {
    assertEquals(utn.cantidadDeSectores(), 3);
  }

  @DisplayName("La Universidad tiene 1 miembro")
  @Test
  public void laUniversidadTieneUnEmpleado() {
    assertEquals(utn.cantidadDeMiembros(), 1);
  }

  @DisplayName("A miembros de la misma organizacion se les puede asignar el mismo trayecto")
  @Test
  public void aMiembrosDeLaMismaOrganizacionSeLesPuedeAsignarElMismoTrayecto() {
    orgFalsa.asignarTrayectoA(trayectoConServicioContratadoYVehiculoParticular, miembro1, miembro2);
    assertEquals(miembro1.getTrayecto(), trayectoConServicioContratadoYVehiculoParticular);
    assertEquals(miembro2.getTrayecto(), trayectoConServicioContratadoYVehiculoParticular);
  }

  @DisplayName("A miembros que no son de la misma organizacion NO se les puede asignar el mismo trayecto")
  @Test
  public void aMiembrosQueNoSonDeLaMismaOrganizacionNoSeLesPuedeAsignarElMismoTrayecto() {
    assertThrows(NonMemberException.class, () -> {
      orgFalsa.asignarTrayectoA(trayectoConServicioContratadoYVehiculoParticular, miembro1, miembro3);
    });
  }

  @DisplayName("A miembros de la misma organizacion NO se les puede asignar un trayecto en colectivo")
  @Test
  public void aMiembrosDeLaMismaOrganizacionNoSeLesPuedeAsignarUnTrayectoEnColectivo() {
    assertThrows(NoPuedeSerTrayectoCompartidoException.class, () -> {
      orgFalsa.asignarTrayectoA(casaHastaUTN, miembro1, miembro2);
    });
  }
}
