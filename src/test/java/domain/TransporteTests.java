package domain;

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
    assertTrue(colectivoLinea157.getParadas().get(0).esMismaUbicacionQue(parada3));
    assertTrue(colectivoLinea157.getParadas().get(1).esMismaUbicacionQue(parada4));
  }

  @Test
  public void subteXTieneParadaEnParada1() {
    assertTrue(subteX.tieneUnaParadaEn(parada1));
  }


}
