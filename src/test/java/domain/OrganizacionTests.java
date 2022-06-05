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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrganizacionTests {

  @DisplayName("Instanciar: Univerdidad Gubernamental")
  private Organizacion unaUniversidadGubernamental() {
    Ubicacion ubicacionUtn = new Ubicacion(457,"O'Higgins", "200");
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL, ubicacionUtn, Clasificacion.UNIVERSIDAD);
    // organizacion.crearNuevoSector();
    Sector unSectorDeRRHH = sectorDeRRHH();
    organizacion.addSector(unSectorDeRRHH);
    organizacion.addSector(new Sector());
    organizacion.addSector(new Sector());
    Miembro miembro = new Miembro("Alejo", "Goltzman", 43994311, Documento.DNI, casaHastaUTN());
    //miembro.addSector(unSectorDeRRHH, organizacion);
    unSectorDeRRHH.addMiembro(miembro);
    return organizacion;
  }

  @DisplayName("Instanciar: Sector de RRHH")
  private Sector sectorDeRRHH() {
    return new Sector();
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
  public void laUtnTieneClasificacionUniversidadTipoGubernamental() {
    assertEquals(unaUniversidadGubernamental().getClasificacion(), Clasificacion.UNIVERSIDAD);
    assertEquals(unaUniversidadGubernamental().getTipo(), Tipo.GUBERNAMENTAL);
    assertEquals(unaUniversidadGubernamental().getRazonSocial(),"UTN");
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

  @DisplayName("Instanciar: Trayecto")
  public Trayecto casaHastaUTN() {
    Ubicacion casa = new Ubicacion(1,"maipu","100");
    Ubicacion paradaCasaLinea7 = new Ubicacion(1,"maipu", "500");
    Ubicacion ubicacionUtn = new Ubicacion(457,"O'Higgins", "200");

    Tramo casaHastaParadaLinea7 = new Tramo(casa, paradaCasaLinea7, new Pie());
    Tramo paradaLinea7HastaUTN = new Tramo(paradaCasaLinea7, ubicacionUtn, colectivoLinea7());

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(casaHastaParadaLinea7);
    tramos.add(paradaLinea7HastaUTN);
    return new Trayecto(tramos);
  }

  @DisplayName("Instanciar: Colectivo Linea 7")
  private TransportePublico colectivoLinea7() {
    TransportePublico colectivo7 = new TransportePublico(TipoDeTransportePublico.COLECTIVO,"7");
    colectivo7.addParadas(new Ubicacion(457,"O'Higgins", "200"),
                          new Ubicacion(457,"O'Higgins", "500"));
    return colectivo7;
  }
}
