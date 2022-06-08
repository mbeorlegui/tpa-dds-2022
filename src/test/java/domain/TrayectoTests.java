package domain;

import domain.organizacion.Organizacion;
import domain.transporte.Pie;
import domain.trayecto.*;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrayectoTests {
  private Trayecto casaHastaUTN;
  private Organizacion utn;
  private Ubicacion casa;
  private Ubicacion paradaCasaLinea7;
  private Tramo casaHastaParadaLinea7;

  @BeforeEach
  void init(){
    InicializacionTests inicializador = new InicializacionTests();
    utn = inicializador.getUtn();
    casaHastaUTN = inicializador.casaHastaUTN();
    casa = new Ubicacion(1,"maipu","100");
    paradaCasaLinea7 = new Ubicacion(1,"maipu", "500");
    casaHastaParadaLinea7 = new Tramo(casa, paradaCasaLinea7, new Pie());
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

}
