package domain;

import domain.transporte.*;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransporteTests {

  @DisplayName("El tipo de Bicicleta es BICICLETA")
  @Test
  public void elTipoDeBicicletaEsBicicleta() throws FileNotFoundException {
    assertEquals(bicicleta().getTipoTransporte(), TipoTransporte.BICICLETA);
  }

  @DisplayName("Un colectivo 156 es un transporte publico de tipo Colectivo y su linea es 157")
  @Test
  public void colectivo157EsTransportePublicoDeTipoColectivoYLinea157() throws FileNotFoundException {
    assertEquals(colectivoLinea157().getTipoTransporte(), TipoTransporte.PUBLICO);
    assertEquals(colectivoLinea157().getTipoDeTransportePublico(), TipoDeTransportePublico.COLECTIVO);
    assertEquals(colectivoLinea157().getLinea(), "157");
  }

  @DisplayName("Un taxi es un servicio contratado de tipo TAXI")
  @Test
  public void unTaxiEsServicioContratadoDeTipoTaxi() throws FileNotFoundException {
    assertEquals(taxi().getTipoDeServicioContratado(), TipoDeServicioContratado.TAXI);
    assertEquals(taxi().getTipoTransporte(), TipoTransporte.CONTRATADO);
  }

  @DisplayName("Una moto que usa nafta es un transporte particular de tipo moto y su combustible es nafta")
  @Test
  public void unaMotoQueUsaNaftaEsParticularDeTipoMotoYSuCombustibleEsNafta() throws FileNotFoundException {
    assertEquals(motoNafta().getTipoTransporte(), TipoTransporte.PARTICULAR);
    assertEquals(motoNafta().getTipoDeVehiculo(), TipoDeVehiculo.MOTO);
    assertEquals(motoNafta().getCombustible(), Combustible.NAFTA);
  }

  @DisplayName("puedoAgregarParadasAUnColectivo")
  @Test
  public void puedoAgregarParadasAUnColectivo() throws FileNotFoundException {
    List<Ubicacion> paradas = new ArrayList<>();
    paradas.add(parada3());
    paradas.add(parada4());
    colectivoLinea157().addParadas(paradas);
    assertEquals(colectivoLinea157().getParadas(), paradas);
  }

  @DisplayName("puedoAgregarUnaParadaAUnColectivo")
  @Test
  public void puedoAgregarUnaParadaAUnColectivo() throws FileNotFoundException {
    colectivoLinea157().addParada(parada3());
    assertEquals(colectivoLinea157().getParadas(), parada3());
  }

  @DisplayName("Instanciar: Parada1")
  private Ubicacion parada1() throws FileNotFoundException {
    return new Ubicacion(15, 30);
  }

  @DisplayName("Instanciar: Parada2")
  private Ubicacion parada2() throws FileNotFoundException {
    return new Ubicacion(20, 40);
  }

  @DisplayName("Instanciar: Parada3")
  private Ubicacion parada3() throws FileNotFoundException {
    return new Ubicacion(30, 40);
  }

  @DisplayName("Instanciar: Parada4")
  private Ubicacion parada4() throws FileNotFoundException {
    return new Ubicacion(10, 10);
  }

  @DisplayName("Instanciar: Bicicleta")
  private Bicicleta bicicleta() throws FileNotFoundException {
    return new Bicicleta();
  }

  @DisplayName("Instanciar: Colectivo linea 157")
  private TransportePublico colectivoLinea157() throws FileNotFoundException {
    return new TransportePublico(TipoDeTransportePublico.COLECTIVO, "157", parada1(), parada2());
  }

  @DisplayName("Instanciar: Taxi")
  private ServicioContratado taxi() throws FileNotFoundException {
    return new ServicioContratado(TipoDeServicioContratado.TAXI);
  }
  @DisplayName("Instanciar: Moto que usa nafta")
  private VehiculoParticular motoNafta() throws FileNotFoundException {
    return new VehiculoParticular(TipoDeVehiculo.MOTO, Combustible.NAFTA);
  }

}