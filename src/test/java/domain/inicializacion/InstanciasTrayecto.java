package domain.inicializacion;

import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import java.util.Arrays;

@Getter
public class InstanciasTrayecto {
  private Trayecto casaHastaUTN;
  private Trayecto casa2HastaUTN;
  private Trayecto servicioContratadoYVehiculoParticular;

  public InstanciasTrayecto() {
    InstanciasTramo tramos = new InstanciasTramo();
    this.casaHastaUTN = casaHastaUTN(tramos.getCasaHastaParadaLinea7(),
        tramos.getParadaLinea7HastaUTN());
    this.casa2HastaUTN = casa2HastaUTN(tramos.getCasa2HastaParadaLinea7(),
        tramos.getParadaLinea7HastaUTN());
    this.servicioContratadoYVehiculoParticular = servicioContratadoYVehiculoParticular(
        tramos.getCasaHastaEstacionamiento(), tramos.getEstacionamientoHastaOrgFalsa());
  }

  @DisplayName("Instanciar: Trayecto")
  private Trayecto casaHastaUTN(Tramo... tramos) {
    return new Trayecto(Arrays.asList(tramos));
  }

  @DisplayName("Instanciar: Trayecto")
  private Trayecto casa2HastaUTN(Tramo... tramos) {
    return new Trayecto(Arrays.asList(tramos));
  }

  @DisplayName("Instanciar: Trayecto con servicio contratado y vehiculo particular")
  private Trayecto servicioContratadoYVehiculoParticular(Tramo... tramos) {
    return new Trayecto(Arrays.asList(tramos));
  }
}
