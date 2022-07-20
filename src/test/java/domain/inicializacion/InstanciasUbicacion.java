package domain.inicializacion;

import domain.ubicacion.Ubicacion;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasUbicacion {
  private Ubicacion casa;
  private Ubicacion casa2;
  private Ubicacion ubicacionUba;
  private Ubicacion ubicacionParadaLinea7;
  private Ubicacion ubicacionUtn;
  private Ubicacion ubicacionOrgFalsa;

  public InstanciasUbicacion() {
    this.casa = casa();
    this.casa2 = casa2();
    this.ubicacionUba = ubicacionUba();
    this.ubicacionParadaLinea7 = ubicacionParadaLinea7();
    this.ubicacionUtn = ubicacionUtn();
    this.ubicacionOrgFalsa = ubicacionOrgFalsa();
  }

  @DisplayName("Instanciar: Ubicacion")
  private Ubicacion casa() {
    return new Ubicacion(1, "maipu", "100");
  }

  @DisplayName("Instanciar: Ubicacion")
  private Ubicacion casa2() {
    return new Ubicacion(1, "rivadavia", "2000");
  }

  @DisplayName("Instanciar: Ubicacion")
  private Ubicacion ubicacionUba() {
    return new Ubicacion(1, "maipu", "500");
  }

  @DisplayName("Instanciar: Ubicacion OrgFalsa")
  private Ubicacion ubicacionOrgFalsa(){
    return new Ubicacion(10, "medrano", "500");
  }

  @DisplayName("Instanciar: Ubicacion")
  private Ubicacion ubicacionParadaLinea7() {
    return new Ubicacion(1, "maipu", "500");
  }

  @DisplayName("Instanciar: Ubicacion")
  private Ubicacion ubicacionUtn() {
    return new Ubicacion(457, "O'Higgins", "200");
  }
}
