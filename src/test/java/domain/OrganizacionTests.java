package domain;

import domain.organizacion.Clasificacion;
import domain.organizacion.Sector;
import domain.organizacion.Tipo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static domain.Utils.unaUniversidadGubernamental;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrganizacionTests {

  @DisplayName("La Universidad es de Tipo Gubernamental")
  @Test
  public void unaUniversidadGubernamentalEsDeTipoGubernamental() {
    assertEquals(unaUniversidadGubernamental().getTipo(), Tipo.GUBERNAMENTAL);
  }

  @DisplayName("La Universidad es una Universidad")
  @Test
  public void unaUniversidadGubernamentalEsUnaUniversidad() {
    assertEquals(unaUniversidadGubernamental().getClasificacion(), Clasificacion.UNIVERSIDAD);
  }

  @DisplayName("La organizacion del sector RRHH es la Universidad")
  @Test
  public void laUtnTieneClasificacionUniversidadTipoGubernamental() {
    assertEquals(unaUniversidadGubernamental().getClasificacion(), Clasificacion.UNIVERSIDAD);
    assertEquals(unaUniversidadGubernamental().getTipo(), Tipo.GUBERNAMENTAL);
    assertEquals(unaUniversidadGubernamental().getRazonSocial(), "UTN");
  }

  @DisplayName("La Universidad tiene 3 sectores")
  @Test
  public void laUniversidadTieneTresSectores() {
    assertEquals(unaUniversidadGubernamental().cantidadDeSectores(), 3);
  }

  @DisplayName("La Universidad tiene 1 miembro")
  @Test
  public void laUniversidadTieneUnEmpleado() {
    assertEquals(unaUniversidadGubernamental().cantidadDeMiembros(), 1);
  }

}
