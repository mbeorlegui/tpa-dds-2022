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
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL, ubicacionUtn, Clasificacion.UNIVERSIDAD);
    // organizacion.crearNuevoSector();
    Sector unSectorDeRRHH = sectorDeRRHH(organizacion);
    organizacion.crearNuevoSector();
    organizacion.crearNuevoSector();
    Miembro miembro = new Miembro("Alejo", "Goltzman", 43994311, Documento.DNI, casaHastaUTN());
    miembro.addSector(unSectorDeRRHH);

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
    assertEquals(sectorDeRRHH(unaUniversidadGubernamental()).getOrganizacion().getClasificacion(),
        Clasificacion.UNIVERSIDAD);
    assertEquals(sectorDeRRHH(unaUniversidadGubernamental()).getOrganizacion().getTipo(), Tipo.
        GUBERNAMENTAL);
    assertEquals(sectorDeRRHH(unaUniversidadGubernamental()).getOrganizacion().getRazonSocial(),
        "UTN");
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
    Ubicacion casa = new Ubicacion(-34.615995882339334, -58.41700275360413);
    Ubicacion paradaCasaLinea7 = new Ubicacion(-34.61908707635995, -58.41677917831219);
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);

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
    colectivo7.addParadas(new Ubicacion(-34.61908707635995, -58.41677917831219),
        new Ubicacion(-34.659488779869484, -58.4671460833512));
    return colectivo7;
  }
}
