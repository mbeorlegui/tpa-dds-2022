package domain.inicializacion;

import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
import domain.organizacion.CsvHandler;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasMedicion {
  private MedicionRead medicionDeLectura1;
  private MedicionRead medicionDeLectura2;
  private MedicionRead medicionDeLectura3;
  private MedicionAdapter unAdapterDeMedicion;
  private Medicion medicionEstandar;
  private CsvHandler csvHandler;

  public InstanciasMedicion(InstanciasTipoDeConsumo tipoDeConsumo) {
    this.medicionDeLectura1 = medicionDeLectura1();
    this.medicionDeLectura2 = medicionDeLectura2();
    this.medicionDeLectura3 = medicionDeLectura3();
    this.unAdapterDeMedicion = unAdapterDeMedicion();
    this.csvHandler = csvHandler();
    this.medicionEstandar = unAdapterDeMedicion.adaptarMedicion(
            tipoDeConsumo.getGasNatural(), medicionDeLectura1);
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
