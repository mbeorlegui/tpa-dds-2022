package domain;

import domain.organizacion.Organizacion;
import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.trayecto.*;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrayectoTests {
  private Trayecto casaHastaUTN;
  private Organizacion utn;
  private Ubicacion casa;
  private Tramo casaHastaParadaLinea7;
  private ResultadoDistancia resultadoDistancia1;
  private CalculadoraDeDistancia calculadoraMock;
  private Tramo casa2HastaLinea7;
  private Trayecto casa2HastaUTN;
  private Trayecto orgFalsaHastaCasa2;

  @BeforeEach
  void init(){
    InicializacionTests inicializador = new InicializacionTests();
    utn = inicializador.getOrganizaciones().getUtn();
    casaHastaUTN = inicializador.getTrayectos().getCasaHastaUTN();
    casa = inicializador.getUbicaciones().getCasa();
    casaHastaParadaLinea7 = inicializador.getTramos().getCasaHastaParadaLinea7();
    resultadoDistancia1 = new ResultadoDistancia(8000, "m");
    calculadoraMock = mock(CalculadoraDeDistancia.class);
    casa2HastaLinea7 = inicializador.getTramos().getCasa2HastaParadaLinea7();
    casa2HastaLinea7.getTransporteUtilizado().setCalculadoraDeDistancia(calculadoraMock);
    casa2HastaUTN = inicializador.getTrayectos().getCasa2HastaUTN();
    orgFalsaHastaCasa2 = inicializador.getTrayectos().getOrgFalsaHastaCasa2();
  }

  @Test
  public void tramoCasaHastaUtnTienePrimerTramoHastaParadaLinea7() {
    assertTrue(casaHastaUTN.getTramos().get(0).esMismoTramo(casaHastaParadaLinea7));
  }

  @Test
  public void inicioDelTrayectoEsCasa() {
    assertTrue(casaHastaUTN.getTramos().get(0).getOrigenDeTramo().esMismaUbicacionQue(casa));
  }

  @Test
  public void finalDelTrayectoEsUniversidadTecnologicaNacionalFRBA() {
    assertTrue(casaHastaUTN.getTramos().get(1).getDestinoDeTramo().esMismaUbicacionQue(utn.getUbicacion()));
  }

  @Test
  public void calculoDeDistanciaDelTrayectoConDosTransportePublico(){
    assertEquals(7.5,orgFalsaHastaCasa2.distanciaTotal());
  }

  @Test
  public void calculoDeDistanciaDelTrayectoConDosTransportes(){
    when(calculadoraMock.distancia(casa2HastaLinea7.getOrigenDeTramo(), casa2HastaLinea7.getDestinoDeTramo()))
        .thenReturn(resultadoDistancia1.getValor());
    assertEquals(8004.2,casa2HastaUTN.distanciaTotal());
  }

}
