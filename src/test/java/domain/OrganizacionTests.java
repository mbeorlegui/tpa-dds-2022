package domain;

import domain.organizacion.*;
import domain.transporte.TransportePublico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrganizacionTests {

  public TransportePublico colectivoLinea7;
  public Organizacion utn;

  @BeforeEach
  private void init(){
    InicializacionTests inicializador = new InicializacionTests();
    colectivoLinea7 = inicializador.getColectivoLinea7();
    utn = inicializador.getUtn();
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

}
