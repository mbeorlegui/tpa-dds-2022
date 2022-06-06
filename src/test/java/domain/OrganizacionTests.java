package domain;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrganizacionTests {

  @DisplayName("Instanciar: Univerdidad Gubernamental")
  private Organizacion unaUniversidadGubernamental() {
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);
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

  @DisplayName("A miembros de la misma organizacion se les puede asignar el mismo trayecto")
  @Test
  public void aMiembrosDeLaMismaOrganizacionSeLesPuedeAsignarElMismoTrayecto() {
    Miembro miembro1 = unMiembro();
    Miembro miembro2 = otroMiembro();
    Sector unSector = unSector();
    unSector.addMiembro(miembro1);
    Sector otroSector = unSector();
    otroSector.addMiembro(miembro2);

    Organizacion unaOrg = orgFalsa();
    unaOrg.addSector(unSector);
    unaOrg.addSector(otroSector);

    Trayecto trayectoCompartido = trajectoConServicioContratadoYVehiculoParticular(unaOrg);

    unaOrg.asignarTrayectoA(trayectoCompartido, miembro1, miembro2);
    assertEquals(miembro1.getTrayecto(), trayectoCompartido);
    assertEquals(miembro2.getTrayecto(), trayectoCompartido);
  }

  @DisplayName("A miembros que no son de la misma organizacion NO se les puede asignar el mismo trayecto")
  @Test
  public void aMiembrosQueNoSonDeLaMismaOrganizacionNoSeLesPuedeAsignarElMismoTrayecto() {
    Miembro miembro1 = unMiembro();
    Miembro miembro2 = otroMiembro();
    Sector unSector = unSector();
    unSector.addMiembro(miembro1);
    Sector otroSector = unSector();

    Organizacion unaOrg = orgFalsa();
    unaOrg.addSector(unSector);
    unaOrg.addSector(otroSector);

    Trayecto trayectoCompartido = trajectoConServicioContratadoYVehiculoParticular(unaOrg);

    assertThrows(NonMemberException.class, () -> {
      unaOrg.asignarTrayectoA(trayectoCompartido, miembro1, miembro2);
    });
  }

  @DisplayName("A miembros de la misma organizacion NO se les puede asignar un trayecto en colectivo")
  @Test
  public void aMiembrosDeLaMismaOrganizacionNoSeLesPuedeAsignarUnTrayectoEnColectivo() {
    Miembro miembro1 = unMiembro();
    Miembro miembro2 = otroMiembro();
    Sector unSector = unSector();
    unSector.addMiembro(miembro1);
    Sector otroSector = unSector();
    otroSector.addMiembro(miembro2);

    Organizacion unaOrg = orgFalsa();
    unaOrg.addSector(unSector);
    unaOrg.addSector(otroSector);

    Trayecto trayectoCompartido = casaHastaUTN();

    assertThrows(NoPuedeSerTrayectoCompartidoException.class, () -> {
      unaOrg.asignarTrayectoA(trayectoCompartido, miembro1, miembro2);
    });
  }

  @DisplayName("Instanciar: Miembro")
  public Miembro unMiembro() {
    return new Miembro(
        "Matias", "Beorlegui", 47813065, Documento.DNI, casaHastaUTN());
  }

  @DisplayName("Instanciar: Otro miembro")
  public Miembro otroMiembro() {
    return new Miembro(
        "Alejo", "Goltzman", 43978123, Documento.DNI, casaHastaUTN());
  }

  @DisplayName("Instanciar: Un sector")
  public Sector unSector() {
    return new Sector();
  }

  @DisplayName("Instanciar: Otra organizacion")
  public Organizacion orgFalsa() {
    return new Organizacion(
        "orgFalsa SRL", Tipo.EMPRESA, new Ubicacion(-36.659488779869484, -58.4671460833512), Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
  }

  @DisplayName("Instanciar: Trayecto con servicio contratado y vehiculo particular")
  private Trayecto trajectoConServicioContratadoYVehiculoParticular(Organizacion organizacion) {
    Ubicacion casa = new Ubicacion(-34.615995882339334, -58.41700275360413);
    Ubicacion estacionamiento = new Ubicacion(-34.61908707635995, -58.41677917831219);

    Tramo primerTramo = new Tramo(casa, estacionamiento, new ServicioContratado(TipoDeServicioContratado.TAXI));
    Tramo segundoTramo = new Tramo(estacionamiento, organizacion.getUbicacion(), new VehiculoParticular(TipoDeVehiculo.AUTO, Combustible.NAFTA));

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(primerTramo);
    tramos.add(segundoTramo);

    return new Trayecto(tramos);
  }
}
