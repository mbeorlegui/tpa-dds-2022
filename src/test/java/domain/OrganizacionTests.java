package domain;

import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.Periodicidad;
import domain.organizacion.*;
import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.trayecto.Tramo;
import org.junit.jupiter.api.BeforeEach;
import domain.exceptions.NoPuedeSerTrayectoCompartidoException;
import domain.exceptions.NonMemberException;
import domain.miembro.Miembro;
import domain.organizacion.Clasificacion;
import domain.organizacion.Sector;
import domain.organizacion.TipoOrganizacion;
import domain.trayecto.Trayecto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrganizacionTests {
  private Organizacion utn;
  private Organizacion orgFalsa;
  private Sector sectorRRHH;
  private Miembro miembro1;
  private Miembro miembro2;
  private Miembro miembro3;
  private Miembro miembro6;
  private Trayecto trayectoConServicioContratadoYVehiculoParticular;
  private Trayecto casaHastaUTN;
  private MedicionAdapter medicionAdapter;
  private Medicion unaMedicion;

  private CalculadoraDeDistancia calculadoraMock;
  private ResultadoDistancia resultadoDistancia1;
  private ResultadoDistancia resultadoDistancia2;
  private Tramo casa2HastaLinea7;
  private Tramo casaHastaLinea7;
  private Trayecto casa2HastaUTN;
  private Medicion unaMedicionAdaptada;

  @BeforeEach
  void init() {
    InicializacionTests inicializador = new InicializacionTests();
    utn = inicializador.getOrganizaciones().getUtn();
    sectorRRHH = inicializador.getOrganizaciones().getSectorDeRRHH();
    orgFalsa = inicializador.getOrganizaciones().getOrgFalsa();
    miembro1 = inicializador.getMiembros().getMiembro1();
    miembro2 = inicializador.getMiembros().getMiembro2();
    miembro3 = inicializador.getMiembros().getMiembro3();
    miembro6 = inicializador.getMiembros().getMiembro6();
    trayectoConServicioContratadoYVehiculoParticular = inicializador.getTrayectos()
        .getServicioContratadoYVehiculoParticular();
    casaHastaUTN = inicializador.getTrayectos().getCasaHastaUTN();
    medicionAdapter = inicializador.getMediciones().getUnAdapterDeMedicion();
    unaMedicion = inicializador.getMediciones().getMedicionEstandar();
    resultadoDistancia1 = new ResultadoDistancia(3000, "m");
    //-- Para test de impacto HC --
    resultadoDistancia2 = new ResultadoDistancia(8, "KM");
    casaHastaLinea7 = inicializador.getTramos().getCasaHastaParadaLinea7();
    casa2HastaLinea7 = inicializador.getTramos().getCasa2HastaParadaLinea7();
    casa2HastaUTN = inicializador.getTrayectos().getCasa2HastaUTN();
    unaMedicionAdaptada = medicionAdapter.adaptarMedicion(
        inicializador.getMediciones().getMedicionDeLectura1());
    utn.agregarMedicion(unaMedicionAdaptada);
  }

  @DisplayName("La Universidad es de Tipo Gubernamental")
  @Test
  public void unaUniversidadGubernamentalEsDeTipoGubernamental() {
    assertEquals(utn.getTipoOrganizacion(), TipoOrganizacion.GUBERNAMENTAL);
  }

  @DisplayName("La Universidad es una Universidad")
  @Test
  public void unaUniversidadGubernamentalEsUnaUniversidad() {
    assertEquals(utn.getClasificacion(), Clasificacion.UNIVERSIDAD);
  }

  @DisplayName("El sector de RRHH forma parte de la Universidad")
  @Test
  public void elSectorDeRRHHFormaParteDeLaUniversidad() {
    assertTrue(utn.tieneSectorDe(sectorRRHH));
  }

  @DisplayName("La organizacion del sector RRHH es la Universidad")
  @Test
  public void laUtnTieneClasificacionUniversidadTipoGubernamental() {
    assertEquals(utn.getClasificacion(), Clasificacion.UNIVERSIDAD);
    assertEquals(utn.getTipoOrganizacion(), TipoOrganizacion.GUBERNAMENTAL);
    assertEquals(utn.getRazonSocial(), "UTN");
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

  @DisplayName("La Universidad tiene 1 contacto")
  @Test
  public void laUniversidadTieneUnContacto() {
    assertEquals(utn.getContactos().size(), 1);
  }

  @DisplayName("La utn tiene 1 trayecto")
  @Test
  public void laUtnTieneUnTrayecto() {
    assertEquals(utn.getTrayectos().size(), 1);
  }


  @DisplayName("miembro2 es un miembro de la UTN")
  @Test
  public void utnTieneMiembro2() {
    assertTrue(utn.esMiembro(miembro2));
  }

  @DisplayName("la medicion que agrego es del periodo correcto ")
  @Test
  public void utnTieneMedicionDePeriodoCorrecto() {
    utn.agregarMedicion(unaMedicion);
    assertTrue(utn.getMediciones().get(0).esDePeriodo(Periodicidad.MENSUAL, "03/2022"));
  }

  @DisplayName("Puedo agregar una medicion a la UTN")
  @Test
  public void utnTieneUnaMedicion() {
    utn.agregarMedicion(unaMedicion);
    assertEquals(utn.getMediciones().size(), 2);
    assertNotNull(utn.getMediciones().get(0));
    assertEquals(utn.getMediciones().get(1), unaMedicion);
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
    assertThrows(NonMemberException.class, () -> orgFalsa.asignarTrayectoA(
        trayectoConServicioContratadoYVehiculoParticular, miembro1, miembro3));
  }

  @DisplayName("A miembros de la misma organizacion NO se les puede asignar un trayecto en colectivo")
  @Test
  public void aMiembrosDeLaMismaOrganizacionNoSeLesPuedeAsignarUnTrayectoEnColectivo() {
    assertThrows(NoPuedeSerTrayectoCompartidoException.class, () -> orgFalsa.asignarTrayectoA(
        casaHastaUTN, miembro1, miembro2));
  }

  @DisplayName("la medicion que agrego es del periodo correcto ")
  @Test
  public void impactoMiembro6SobreHuellaCarbonoDeUTN() {
    Sector nuevoSector = new Sector();
    nuevoSector.addMiembro(miembro6);
    utn.addSector(nuevoSector);
    utn.getMiembros().get(1).setTrayecto(casa2HastaUTN);
    calculadoraMock = mock(CalculadoraDeDistancia.class);
    casa2HastaLinea7.getTransporteUtilizado().setCalculadoraDeDistancia(calculadoraMock);
    casaHastaLinea7.getTransporteUtilizado().setCalculadoraDeDistancia(calculadoraMock);
    when(calculadoraMock.distancia(casa2HastaLinea7.getOrigenDeTramo(), casa2HastaLinea7.getDestinoDeTramo()))
        .thenReturn(resultadoDistancia2.getValor());
    when(calculadoraMock.distancia(casaHastaLinea7.getOrigenDeTramo(), casaHastaLinea7.getDestinoDeTramo()))
        .thenReturn(0.0);
    //hc del miembro6 = 652000 * 20 (dias trabajados mensual)= 13040000
    //hc del miembro1 = 252000 * 20 = 5040000
    //la suma de ambos es 18080000
    double impactoMiembro6 = 13040000.0 / 18130000.0; // es igual a 0.71924986210
    assertEquals(13040000, miembro6.calcularHuellaDeCarbono(Periodicidad.MENSUAL));
    //assertEquals(18080000, utn.hcTrayectosMiembros(Periodicidad.MENSUAL));
    assertEquals(18130000, utn.huellaDeCarbonoEnPeriodo(Periodicidad.MENSUAL, "03/2022"));
    assertEquals(impactoMiembro6, utn.impactoMiembroSobreHC(miembro6,Periodicidad.MENSUAL,"03/2022"));
  }
}
