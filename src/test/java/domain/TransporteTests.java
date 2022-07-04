package domain;

import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.transporte.*;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import java.io.FileNotFoundException; para que era FileNotFoundException? estaba en todos los metodos

import static org.junit.jupiter.api.Assertions.*;

public class TransporteTests {
  public Bicicleta bicicleta;
  public TransportePublico colectivoLinea157;
  public ServicioContratado taxi;
  public VehiculoParticular motoNafta;
  public TransportePublico subteX;
  public Ubicacion parada1;
  public Ubicacion parada2;
  public Ubicacion parada3;
  public Ubicacion parada4;

  @BeforeEach
  void init() {
    InicializacionTests inicializador = new InicializacionTests();
    parada1 = new Ubicacion(15, "mozart", "1400");
    parada2 = new Ubicacion(20, "rivadavia", "4000");
    parada3 = new Ubicacion(10, "medrano", "500");
    parada4 = new Ubicacion(10, "medrano", "800");
    bicicleta = inicializador.getBicicleta();
    colectivoLinea157 = inicializador.getColectivoLinea157();
    taxi = inicializador.getTaxi();
    motoNafta = inicializador.getMotoNafta();
    subteX = inicializador.getSubteX();

  }

  @DisplayName("El tipo de Bicicleta es BICICLETA")
  @Test
  public void elTipoDeBicicletaEsBicicleta() {
    assertEquals(bicicleta.getTipoTransporte(), TipoTransporte.BICICLETA);
  }

  @DisplayName("Un colectivo 157 es un transporte publico de tipo Colectivo y su linea es 157")
  @Test
  public void colectivo157EsTransportePublicoDeTipoColectivoYLinea157() {
    assertEquals(colectivoLinea157.getTipoTransporte(), TipoTransporte.PUBLICO);
    assertEquals(colectivoLinea157.getTipoDeTransportePublico(), TipoDeTransportePublico.COLECTIVO);
    assertEquals(colectivoLinea157.getLinea(), "157");
  }

  @DisplayName("Un taxi es un servicio contratado de tipo TAXI")
  @Test
  public void unTaxiEsServicioContratadoDeTipoTaxi() {
    assertEquals(taxi.getTipoDeServicioContratado(), TipoDeServicioContratado.TAXI);
    assertEquals(taxi.getTipoTransporte(), TipoTransporte.CONTRATADO);
  }

  @DisplayName("Una moto que usa nafta es un transporte particular de tipo moto y su combustible es nafta")
  @Test
  public void unaMotoQueUsaNaftaEsParticularDeTipoMotoYSuCombustibleEsNafta() {
    assertEquals(motoNafta.getTipoTransporte(), TipoTransporte.PARTICULAR);
    assertEquals(motoNafta.getTipoDeVehiculo(), TipoDeVehiculo.MOTO);
  }

  @DisplayName("Puedo agregar 2 paradas a un colectivo")
  @Test
  public void puedoAgregarDosParadasAUnColectivo() {
    assertTrue(colectivoLinea157.getParadas().get(0).getUbicacion().esMismaUbicacionQue(parada3));
    assertTrue(colectivoLinea157.getParadas().get(1).getUbicacion().esMismaUbicacionQue(parada4));
  }

  @Test
  public void subteXTieneParadaEnParada1() {
    assertTrue(subteX.tieneUnaParadaEn(parada1));
  }

  @DisplayName("Calculo de distancia intermedia de un tramo")
  @Test
  public void seCalculaCorrectamenteLaDistanciaIntermedia() {
    assertEquals(300, colectivoLinea157.calcularDistancia(parada3,parada4));
  }

  @DisplayName("Calculo de distancia intermedia de un tramo")
  @Test
  public void seAgregaUnaParadaIntermedia() {
    colectivoLinea157.agregarParadaLuegoDe(new Parada(parada2,new ResultadoDistancia(120,"M")),parada3);
    assertEquals(180, colectivoLinea157.calcularDistancia(parada3,parada2));
  }

  @DisplayName("Verifico que se agregue al final nueva parada")
  @Test
  public void seAgregaUnaParadaFinal() {
    colectivoLinea157.agregarParadaFinal(new Parada(parada1, new ResultadoDistancia(0,"M")),
        parada4,
        new ResultadoDistancia(450,"M"));
    assertTrue(colectivoLinea157.tieneUnaParadaEn(parada1));
  }

  @DisplayName("Verifico que se agregue al final nueva parada")
  @Test
  public void seAgregaUnaParadaInicial() {
    colectivoLinea157.agregarParadaInicial(new Parada(parada2, new ResultadoDistancia(450,"M")));
    assertEquals(parada2, colectivoLinea157.getParadas().get(0).getUbicacion());
  }
}
