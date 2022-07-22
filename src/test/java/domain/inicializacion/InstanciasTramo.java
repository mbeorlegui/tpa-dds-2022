package domain.inicializacion;

import domain.transporte.*;
import domain.trayecto.Tramo;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasTramo {
  private Tramo casaHastaParadaLinea7;
  private Tramo casa2HastaParadaLinea7;
  private Tramo paradaLinea7HastaUTN;
  private Tramo casaHastaEstacionamiento;
  private Tramo estacionamientoHastaOrgFalsa;
  private Tramo ubaHastaCasa2;
  public Tramo orgFalsaHastaUba;

  public InstanciasTramo(InstanciasUbicacion ubicaciones, InstanciasTransporte transportes) {
    this.casaHastaParadaLinea7 = casaHastaParadaLinea7(ubicaciones.getCasa(),
        ubicaciones.getUbicacionParadaLinea7(), transportes.getPie());
    this.casa2HastaParadaLinea7 = casa2HastaParadaLinea7(ubicaciones.getCasa2(),
        ubicaciones.getUbicacionParadaLinea7(), transportes.getTaxi());
    this.paradaLinea7HastaUTN = paradaLinea7HastaUTN(ubicaciones.getUbicacionParadaLinea7(),
        ubicaciones.getUbicacionUtn(), transportes.getColectivoLinea7());
    this.casaHastaEstacionamiento = casaHastaEstacionamiento(ubicaciones.getCasa(),
        ubicaciones.getEstacionamiento(), transportes.getTaxi());
    this.estacionamientoHastaOrgFalsa = estacionamientoHastaOrgFalsa(
        ubicaciones.getEstacionamiento(),
        ubicaciones.getUbicacionOrgFalsa(), transportes.getAuto());
    this.ubaHastaCasa2 = ubaHastaCasa2(ubicaciones.getUbicacionUba(), ubicaciones.getCasa2(),
        transportes.getSubteX());
    this.orgFalsaHastaUba = orgFalsaHastaUba(ubicaciones.getUbicacionOrgFalsa(),
        ubicaciones.getUbicacionUba(), transportes.getColectivoLinea7());
  }

  @DisplayName("Instanciar: Tramo")
  private Tramo casaHastaParadaLinea7(Ubicacion casa, Ubicacion linea7, Transporte transporte) {
    return new Tramo(casa, linea7, transporte);
  }

  @DisplayName("Instanciar: Tramo")
  private Tramo casaHastaEstacionamiento(Ubicacion casa, Ubicacion estacionamiento,
                                         Transporte transporte) {
    return new Tramo(casa, estacionamiento, transporte);
  }

  @DisplayName("Instanciar: Tramo")
  private Tramo estacionamientoHastaOrgFalsa(Ubicacion estacionamiento, Ubicacion orgFalsa,
                                             Transporte transporte) {
    return new Tramo(estacionamiento, orgFalsa, transporte);
  }

  @DisplayName("Instanciar: Tramo")
  private Tramo casa2HastaParadaLinea7(Ubicacion casa2, Ubicacion linea7, Transporte transporte) {
    return new Tramo(casa2, linea7, transporte);
  }

  @DisplayName("Instanciar: Tramo")
  private Tramo paradaLinea7HastaUTN(Ubicacion linea7, Ubicacion ubicacionUtn,
                                     Transporte transporte) {
    return new Tramo(linea7, ubicacionUtn, transporte);
  }

  @DisplayName("Instanciar: Tramo")
  private Tramo ubaHastaCasa2(Ubicacion uba, Ubicacion casa2, Transporte subteX) {
    return new Tramo(uba, casa2, subteX);
  }

  @DisplayName("Instanciar: Tramo")
  private Tramo orgFalsaHastaUba(Ubicacion ubicacionOrgFalsa, Ubicacion uba,
                                Transporte colectivoLinea7) {
    return new Tramo(ubicacionOrgFalsa, uba, colectivoLinea7);
  }

}
