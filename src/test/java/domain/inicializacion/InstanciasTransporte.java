package domain.inicializacion;

import domain.medicion.TiposConsumos;
import domain.transporte.*;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasTransporte {
  private TransportePublico colectivoLinea7;
  private TransportePublico colectivoLinea157;
  private TransportePublico subteX;
  private Bicicleta bicicleta;
  private ServicioContratado taxi;
  private VehiculoParticular motoNafta;
  private Pie pie;

  public InstanciasTransporte() {
    InstanciasParada paradas = new InstanciasParada();
    this.colectivoLinea7 = colectivoLinea7(paradas.getParada3(),paradas.getParada1(),
        paradas.getParada6(),paradas.getParada5());
    this.colectivoLinea157 = colectivoLinea157(paradas.getParada3(), paradas.getParada4());
    this.subteX = subteX(paradas.getParada1(), paradas.getParada3(),
        paradas.getParada6(), paradas.getParada2());
    this.bicicleta = bicicleta();
    this.taxi = taxi();
    this.motoNafta = motoNafta();
    this.pie = pie();
  }

  @DisplayName("Instanciar: Colectivo Linea 7")
  private TransportePublico colectivoLinea7(Parada... paradas) {
    TransportePublico colectivo7 = new TransportePublico(TiposConsumos.getInstance().hayarTipo(
        "GAS_NATURAL"), 100.0, TipoDeTransportePublico.COLECTIVO, "7");
    colectivo7.addParadas(paradas);
    return colectivo7;
  }

  @DisplayName("Instanciar: Subte X")
  private TransportePublico subteX(Parada... paradas) {
    TransportePublico subte = new TransportePublico(TiposConsumos.getInstance().hayarTipo(
        "GAS_NATURAL"), 100.0, TipoDeTransportePublico.SUBTE, "X");
    subte.addParadas(paradas);
    return subte;
  }

  @DisplayName("Instanciar: Colectivo linea 157")
  private TransportePublico colectivoLinea157(Parada... paradas) {
    TransportePublico bondi = new TransportePublico(TiposConsumos.getInstance().hayarTipo(
        "GAS_NATURAL"), 100.0, TipoDeTransportePublico.COLECTIVO, "157");
    bondi.addParadas(paradas);
    return bondi;
  }

  @DisplayName("Instanciar: Taxi")
  private ServicioContratado taxi() {
    return new ServicioContratado(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"),
        100.0, TipoDeServicioContratado.TAXI);
  }
  @DisplayName("Instanciar: Moto que usa nafta")
  private VehiculoParticular motoNafta() {
    return new VehiculoParticular(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"),
        100.0, TipoDeVehiculo.MOTO);
  }

  @DisplayName("Instanciar: Bicicleta")
  private Bicicleta bicicleta() {
    return new Bicicleta();
  }

  @DisplayName("Instanciar: Pie")
  private Pie pie() {
    return new Pie();
  }
}
