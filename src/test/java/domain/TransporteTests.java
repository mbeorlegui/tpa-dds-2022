package domain;

import domain.transporte.*;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import java.io.FileNotFoundException; para que era FileNotFoundException? estaba en todos los metodos

import static org.junit.jupiter.api.Assertions.*;

public class TransporteTests {

  @DisplayName("El tipo de Bicicleta es BICICLETA")
  @Test
  public void elTipoDeBicicletaEsBicicleta() {
    assertEquals(bicicleta().getTipoTransporte(), TipoTransporte.BICICLETA);
  }

  @DisplayName("Un colectivo 157 es un transporte publico de tipo Colectivo y su linea es 157")
  @Test
  public void colectivo157EsTransportePublicoDeTipoColectivoYLinea157() {
    assertEquals(colectivoLinea157().getTipoTransporte(), TipoTransporte.PUBLICO);
    assertEquals(colectivoLinea157().getTipoDeTransportePublico(), TipoDeTransportePublico.COLECTIVO);
    assertEquals(colectivoLinea157().getLinea(), "157");
  }

  @DisplayName("Un taxi es un servicio contratado de tipo TAXI")
  @Test
  public void unTaxiEsServicioContratadoDeTipoTaxi() {
    assertEquals(taxi().getTipoDeServicioContratado(), TipoDeServicioContratado.TAXI);
    assertEquals(taxi().getTipoTransporte(), TipoTransporte.CONTRATADO);
  }

  @DisplayName("Una moto que usa nafta es un transporte particular de tipo moto y su combustible es nafta")
  @Test
  public void unaMotoQueUsaNaftaEsParticularDeTipoMotoYSuCombustibleEsNafta()  {
    assertEquals(motoNafta().getTipoTransporte(), TipoTransporte.PARTICULAR);
    assertEquals(motoNafta().getTipoDeVehiculo(), TipoDeVehiculo.MOTO);
    assertEquals(motoNafta().getCombustible(), Combustible.NAFTA);
  }

  @DisplayName("Puedo agregar 2 paradas a un colectivo")
  @Test
  public void puedoAgregarDosParadasAUnColectivo() {
    assertTrue(colectivoLinea157().getParadas().get(0).esMismaUbicacionQue(parada3()));
    assertTrue(colectivoLinea157().getParadas().get(1).esMismaUbicacionQue(parada4()));
  }

  @Test
  public void subteXTieneParadaEnParada1() {
    assertTrue(subteX().tieneUnaParadaEn(parada1()));
  }

  @DisplayName("Instanciar: Colectivo linea 157")
  private TransportePublico subteX() {
    TransportePublico subte = new TransportePublico(TipoDeTransportePublico.SUBTE, "X");
    subte.addParadas(parada1(),parada2());
    return subte;
  }

  @DisplayName("Instanciar: Parada1")
  private Ubicacion parada1() {
    return new Ubicacion(15, 30);
  }

  @DisplayName("Instanciar: Parada2")
  private Ubicacion parada2() {
    return new Ubicacion(20, 40);
  }

  @DisplayName("Instanciar: Parada3")
  private Ubicacion parada3() {
    return new Ubicacion(30, 40);
  }

  @DisplayName("Instanciar: Parada4")
  private Ubicacion parada4() {
    return new Ubicacion(10, 10);
  }

  @DisplayName("Instanciar: Bicicleta")
  private Bicicleta bicicleta() {
    return new Bicicleta();
  }

  @DisplayName("Instanciar: Colectivo linea 157")
  private TransportePublico colectivoLinea157() {
    //TransportePublico bondi = new TransportePublico(TipoDeTransportePublico.COLECTIVO, "157", parada1(), parada2());
    TransportePublico bondi = new TransportePublico(TipoDeTransportePublico.COLECTIVO, "157");
    bondi.addParadas(parada3(),parada4());
    return bondi;
  }

  @DisplayName("Instanciar: Taxi")
  private ServicioContratado taxi() {
    return new ServicioContratado(TipoDeServicioContratado.TAXI);
  }
  @DisplayName("Instanciar: Moto que usa nafta")
  private VehiculoParticular motoNafta() {
    return new VehiculoParticular(TipoDeVehiculo.MOTO, Combustible.NAFTA);
  }
}
