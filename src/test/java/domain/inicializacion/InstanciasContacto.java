package domain.inicializacion;

import domain.medios.Contacto;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasContacto {
  private Contacto contacto1;

  public InstanciasContacto() {
    this.contacto1 = contacto1();
  }

  @DisplayName("Instanciar: Contacto1")
  private Contacto contacto1() {
    return new Contacto("unNombre", "unEmail@falso.com", 123456789);
  }
}
