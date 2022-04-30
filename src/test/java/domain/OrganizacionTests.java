package domain;

import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.Tipo;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganizacionTests {

  @DisplayName("Instanciar: Univerdidad Gubernamental")
  private Organizacion unaUniversidadGubernamental() {
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL, ubicacionUtn, Clasificacion.UNIVERSIDAD);
    organizacion.crearNuevoSector();
    organizacion.crearNuevoSector();
    organizacion.crearNuevoSector();

    return organizacion;
  }

  @DisplayName("Instanciar: Sector de RRHH")
  private Sector sectorDeRRHH(Organizacion organizacion) {
    return new Sector(organizacion);
  }

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
/*
  @DisplayName("El sector de RRHH forma parte de la Universidad")
  @Test
  public void elSectorDeRRHHFormaParteDeLaUniversidad() {
    assertTrue(unaUniversidadGubernamental().esSectorDeLaOrganizacion(sectorDeRRHH(unaUniversidadGubernamental())));
    // Como se llama a la universidad dos veces, se hace new dos veces => cada objeto de universidad es distinto
  }
*/
  @DisplayName("La organizacion del sector RRHH es la Universidad")
  @Test
  public void laOrganizacionDelSectorRRHHEsLaUniversidad() {
    assertEquals(sectorDeRRHH(unaUniversidadGubernamental()).getOrganizacion().getClasificacion(), Clasificacion.UNIVERSIDAD);
    assertEquals(sectorDeRRHH(unaUniversidadGubernamental()).getOrganizacion().getTipo(), Tipo.GUBERNAMENTAL);
    assertEquals(sectorDeRRHH(unaUniversidadGubernamental()).getOrganizacion().getRazonSocial(), "UTN");
  }

  @DisplayName("La Universidad tiene 3 sectores")
  @Test
  public void laUniversidadTieneTresSectores() {

  }

}
