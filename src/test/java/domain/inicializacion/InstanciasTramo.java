package domain.inicializacion;

import domain.medicion.TiposConsumos;
import domain.transporte.*;
import domain.trayecto.Tramo;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;

public class InstanciasTramo {
  private Tramo casaHastaParadaLinea7;
  private Tramo casa2HastaParadaLinea7;
  private Tramo paradaLinea7HastaUTN;
  private Tramo casaHastaEstacionamiento;
  private Tramo estacionamientoHastaOrgFalsa;

  public InstanciasTramo() {
    InstanciasUbicacion ubicaciones = new InstanciasUbicacion();
    this.casaHastaParadaLinea7 = casaHastaParadaLinea7(ubicaciones.getCasa(),
        ubicaciones.getUbicacionParadaLinea7());
    this.casa2HastaParadaLinea7 = casa2HastaParadaLinea7(ubicaciones.getCasa2(),
        ubicaciones.getUbicacionParadaLinea7());
    this.paradaLinea7HastaUTN = paradaLinea7HastaUTN(ubicaciones.getUbicacionParadaLinea7(),
        ubicaciones.getUbicacionUtn());
    this.casaHastaEstacionamiento = casaHastaEstacionamiento(ubicaciones.getCasa(),
        ubicaciones.getEstacionamiento());
    this.estacionamientoHastaOrgFalsa = estacionamientoHastaOrgFalsa(
        ubicaciones.getEstacionamiento(),
        ubicaciones.getUbicacionOrgFalsa());
  }

  @DisplayName("Instanciar: Tramo")
  public Tramo casaHastaParadaLinea7(Ubicacion casa, Ubicacion linea7) {
    return new Tramo(casa, linea7, new Pie());
  }

  @DisplayName("Instanciar: Tramo")
  public Tramo casaHastaEstacionamiento(Ubicacion casa, Ubicacion estacionamiento) {
    return new Tramo(casa, estacionamiento, new Pie());
  } //TODO: cambiar transporte a taxi

  @DisplayName("Instanciar: Tramo")
  public Tramo estacionamientoHastaOrgFalsa(Ubicacion estacionamiento, Ubicacion orgFalsa) {
    return new Tramo(estacionamiento, orgFalsa, new Pie());
  } //TODO: cambiar transporte a auto

  @DisplayName("Instanciar: Tramo")
  public Tramo casa2HastaParadaLinea7(Ubicacion casa2, Ubicacion linea7) {
    return new Tramo(casa2, linea7, new Pie());
  } //TODO: cambiar transporte a taxi

  @DisplayName("Instanciar: Tramo")
  public Tramo paradaLinea7HastaUTN(Ubicacion linea7, Ubicacion ubicacionUtn) {
    return new Tramo(linea7, ubicacionUtn, new Pie());
  } //TODO: cambiar transporte a colectivo 7
}
