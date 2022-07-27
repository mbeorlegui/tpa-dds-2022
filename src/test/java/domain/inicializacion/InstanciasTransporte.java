package domain.inicializacion;

import domain.medicion.TiposConsumos;
import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.transporte.*;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

import static org.mockito.Mockito.mock;

@Getter
public class InstanciasTransporte {
  private TransportePublico colectivoLinea7;
  private TransportePublico colectivoLinea157;
  private TransportePublico subteX;
  private Bicicleta bicicleta;
  private ServicioContratado taxi;
  private VehiculoParticular motoNafta;
  private VehiculoParticular auto;
  private Pie pie;
  private CalculadoraDeDistancia calculadoraMock;

  public InstanciasTransporte(InstanciasParada paradas) {
    this.colectivoLinea7 = colectivoLinea7(paradas.getParada3(),paradas.getParada1(),
        paradas.getParada6(),paradas.getParada5());
    this.colectivoLinea157 = colectivoLinea157(paradas.getParada3(), paradas.getParada4());
    this.subteX = subteX(paradas.getParada1(), paradas.getParada3(),
        paradas.getParada6(), paradas.getParada2(), paradas.getParada5());
    calculadoraMock = mock(CalculadoraDeDistancia.class);
    this.bicicleta = bicicleta(calculadoraMock);
    this.taxi = taxi(calculadoraMock);
    this.motoNafta = motoNafta(calculadoraMock);
    this.auto = auto(calculadoraMock);
    this.pie = pie(calculadoraMock);
  }

  @DisplayName("Instanciar: Colectivo Linea 7")
  private TransportePublico colectivoLinea7(Parada... paradas) {
    TransportePublico colectivo7 = new TransportePublico(TiposConsumos.getInstance().hayarTipo(
        "NAFTA"), 3.0, TipoDeTransportePublico.COLECTIVO, "7");
    colectivo7.addParadas(paradas);
    return colectivo7;
  }

  @DisplayName("Instanciar: Subte X")
  private TransportePublico subteX(Parada... paradas) {
    TransportePublico subte = new TransportePublico(TiposConsumos.getInstance().hayarTipo(
        "DIESEL_GASOIL"), 0.5, TipoDeTransportePublico.SUBTE, "X");
    subte.addParadas(paradas);
    return subte;
  }

  @DisplayName("Instanciar: Colectivo linea 157")
  private TransportePublico colectivoLinea157(Parada... paradas) {
    TransportePublico bondi = new TransportePublico(TiposConsumos.getInstance().hayarTipo(
        "NAFTA"), 3.0, TipoDeTransportePublico.COLECTIVO, "157");
    bondi.addParadas(paradas);
    return bondi;
  }

  @DisplayName("Instanciar: Taxi")
  private ServicioContratado taxi(CalculadoraDeDistancia calculadoraMock) {
    ServicioContratado taxi =  new ServicioContratado(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"),
        2.0, TipoDeServicioContratado.TAXI);
    taxi.setCalculadoraDeDistancia(calculadoraMock);
    return taxi;
  }
  @DisplayName("Instanciar: Moto que usa nafta")
  private VehiculoParticular motoNafta(CalculadoraDeDistancia calculadoraMock) {
    VehiculoParticular motoNafta = new VehiculoParticular(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"),
        1.2, TipoDeVehiculo.MOTO);
    motoNafta.setCalculadoraDeDistancia(calculadoraMock);
    return motoNafta;
  }

  @DisplayName("Instanciar: Auto")
  private VehiculoParticular auto(CalculadoraDeDistancia calculadoraMock) {
    VehiculoParticular auto = new VehiculoParticular(TiposConsumos.getInstance().hayarTipo("DIESEL_GASOIL"),
        2.5, TipoDeVehiculo.AUTO);
    auto.setCalculadoraDeDistancia(calculadoraMock);
    return auto;
  }

  @DisplayName("Instanciar: Bicicleta")
  private Bicicleta bicicleta(CalculadoraDeDistancia calculadoraMock) {
    Bicicleta bicicleta = new Bicicleta();
    bicicleta.setCalculadoraDeDistancia(calculadoraMock);
    return bicicleta;
  }

  @DisplayName("Instanciar: Pie")
  private Pie pie(CalculadoraDeDistancia calculadoraMock) {
    Pie pie = new Pie();
    pie.setCalculadoraDeDistancia(calculadoraMock);
    return pie;
  }
}
