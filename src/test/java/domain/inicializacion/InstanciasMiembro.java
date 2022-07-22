package domain.inicializacion;

import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.trayecto.Trayecto;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasMiembro {
  private Miembro miembro1;
  private Miembro miembro2;
  private Miembro miembro3;
  private Miembro miembro6;

  public InstanciasMiembro(InstanciasTrayecto trayectos) {
    this.miembro1 = miembro1(trayectos.getCasaHastaUTN());
    this.miembro2 = miembro2(trayectos.getCasaHastaUTN());
    this.miembro3 = miembro3(trayectos.getCasaHastaUTN());
    this.miembro6 = miembro6(trayectos.getCasa2HastaUTN());
  }

  @DisplayName("Instanciar: Miembro")
  private Miembro miembro1(Trayecto trayecto) {
    return new Miembro("Matias", "Beorlegui", 47813065, Documento.DNI, trayecto);
  }

  @DisplayName("Instanciar: Otro miembro")
  private Miembro miembro2(Trayecto trayecto) {
    return new Miembro("Alejo", "Goltzman", 43978123, Documento.DNI, trayecto);
  }

  @DisplayName("Instanciar: Otro miembro")
  private Miembro miembro3(Trayecto trayecto) {
    return new Miembro("Alejo", "Sandrini", 43987654, Documento.DNI, trayecto);
  }

  @DisplayName("Instanciar: Otro miembro")
  public Miembro miembro6(Trayecto trayecto) {
    return new Miembro("Pepe", "Argento", 18978321, Documento.DNI, trayecto);
  }
}
