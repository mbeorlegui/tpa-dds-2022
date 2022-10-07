package domain.inicializacion;

import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
import domain.medicion.TipoConsumo;
import domain.organizacion.CsvHandler;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InstanciasMedicion {
  private MedicionRead medicionDeLectura1;
  private MedicionRead medicionDeLectura2;
  private MedicionRead medicionDeLectura3;
  private MedicionAdapter unAdapterDeMedicion;
  private Medicion medicionEstandar;
  private CsvHandler csvHandler;
  private List<TipoConsumo> tipoConsumos = new ArrayList<>();

  public InstanciasMedicion(InstanciasTipoDeConsumo tipoDeConsumo) {
    this.medicionDeLectura1 = medicionDeLectura1();
    this.medicionDeLectura2 = medicionDeLectura2();
    this.medicionDeLectura3 = medicionDeLectura3();
    this.unAdapterDeMedicion = unAdapterDeMedicion();
    this.csvHandler = csvHandler();
    tipoConsumos.add(tipoDeConsumo.getGasNatural());
    tipoConsumos.add(tipoDeConsumo.getNafta());
    tipoConsumos.add(tipoDeConsumo.getCarbon());
    tipoConsumos.add(tipoDeConsumo.getElectricidad());
    tipoConsumos.add(tipoDeConsumo.getDieselGasoil());
    tipoConsumos.add(tipoDeConsumo.getCcGasoil());
    tipoConsumos.add(tipoDeConsumo.getCcNafta());
    tipoConsumos.add(tipoDeConsumo.getMedioDeTransporte());
    tipoConsumos.add(tipoDeConsumo.getDistanciaMedia());
    this.medicionEstandar = unAdapterDeMedicion.adaptarMedicion(
            tipoConsumos, medicionDeLectura1);
  }

  @DisplayName("Instanciar: Medicion de lectura 1")
  static MedicionRead medicionDeLectura1() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("MENSUAL");
    unaMedicion.setTipoConsumo("GAS_NATURAL");
    unaMedicion.setValor("100");
    unaMedicion.setPeriodoDeImputacion("03/2022");
    return unaMedicion;
  }

  @DisplayName("Instanciar: Medicion de lectura 2")
  static MedicionRead medicionDeLectura2() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("MENSUAL");
    unaMedicion.setTipoConsumo("ELECTRICIDAD");
    unaMedicion.setValor("6000");
    unaMedicion.setPeriodoDeImputacion("03/2022");
    return unaMedicion;
  }

  @DisplayName("Instanciar: Medicion de lectura 3")
  static MedicionRead medicionDeLectura3() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("ANUAL");
    unaMedicion.setTipoConsumo("DIESEL_GASOIL");
    unaMedicion.setValor("50");
    unaMedicion.setPeriodoDeImputacion("2021");
    return unaMedicion;
  }

  @DisplayName("Instanciar: Un adapter de Medicion")
  static MedicionAdapter unAdapterDeMedicion() {
    return new MedicionAdapter();
  }

  @DisplayName("Instanciar: Csv Handler")
  public CsvHandler csvHandler() {
    return new CsvHandler();
  }
}
