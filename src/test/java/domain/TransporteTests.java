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


  @DisplayName("Puedo agregar 2 paradas a un colectivo")
  @Test
  public void puedoAgregarDosParadasAUnColectivo() throws FileNotFoundException {
    assertEquals(colectivoLinea157().getParadas().get(0).getLatitud(), parada3().getLatitud());
    assertEquals(colectivoLinea157().getParadas().get(0).getLongitud(), parada3().getLongitud());
    assertEquals(colectivoLinea157().getParadas().get(1).getLatitud(), parada4().getLatitud());
    assertEquals(colectivoLinea157().getParadas().get(1).getLongitud(), parada4().getLongitud());
  }

  @DisplayName("Instancias: Paradas")
  private List<Ubicacion> paradas() throws FileNotFoundException{
    List<Ubicacion> paradas = new ArrayList<>();
    paradas.add(parada3());
    paradas.add(parada4());
    return paradas;
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
    TransportePublico bondi = new TransportePublico(TipoDeTransportePublico.COLECTIVO, "157", parada1(), parada2());
    bondi.addParadas(paradas());
    return bondi;
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
