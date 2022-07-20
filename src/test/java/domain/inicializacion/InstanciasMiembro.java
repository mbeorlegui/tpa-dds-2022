package domain.inicializacion;

import domain.miembro.Documento;
import domain.miembro.Miembro;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasMiembro {
  private Miembro miembro1;
  private Miembro miembro2;
  private Miembro miembro3;

  public InstanciasMiembro() {
    this.miembro1 = miembro1();
    this.miembro2 = miembro2();
    this.miembro3 = miembro3();
  }

  @DisplayName("Instanciar: Miembro")
  private Miembro miembro1() {
    return new Miembro("Matias", "Beorlegui", 47813065, Documento.DNI, null);
  } //agregar trayecto casa hasta utn

  @DisplayName("Instanciar: Otro miembro")
  private Miembro miembro2() {
    return new Miembro("Alejo", "Goltzman", 43978123, Documento.DNI, null);
  } //agregar trayecto casa hasta utn

  @DisplayName("Instanciar: Otro miembro")
  private Miembro miembro3() {
    return new Miembro("Alejo", "Sandrini", 43987654, Documento.DNI, null);
  } //agregar trayecto casa hasta utn
}
