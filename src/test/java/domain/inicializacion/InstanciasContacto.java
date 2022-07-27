package domain.inicializacion;

import domain.medios.Contacto;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasContacto {
  private Contacto contacto1;
  private Contacto contacto2;
  private Contacto contacto3;


  public InstanciasContacto() {
    this.contacto1 = contacto1();
    this.contacto2 = contacto2();
    this.contacto3 = contacto3();
  }

  @DisplayName("Instanciar: Contacto1")
  private Contacto contacto1() {
    return new Contacto("Sofia", "sofia@mail.com", 123456789);
  }

  @DisplayName("Instanciar: Contacto1")
  private Contacto contacto2() {
    return new Contacto("Juan", "juan@mail.com", 123789465);
  }

  @DisplayName("Instanciar: Contacto1")
  private Contacto contacto3() {
    return new Contacto("Rene", "rene@mail.com", 465132798);
  }
}
