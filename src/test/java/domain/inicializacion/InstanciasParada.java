package domain.inicializacion;

import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.transporte.Parada;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasParada {
  private Parada parada1;
  private Parada parada2;
  private Parada parada3;
  private Parada parada4;
  private Parada parada5;
  private Parada parada6;

  public InstanciasParada() {
    this.parada1 = parada1();
    this.parada2 = parada2();
    this.parada3 = parada3();
    this.parada4 = parada4();
    this.parada5 = parada5();
    this.parada6 = parada6();
  }

  @DisplayName("Instanciar: Parada1")
  private Parada parada1() {
    Ubicacion ubicacion = new Ubicacion(15, "mozart", "1400");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(3000,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }

  @DisplayName("Instanciar: Parada2")
  private Parada parada2() {
    Ubicacion ubicacion = new Ubicacion(1, "rivadavia", "2000");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(1000,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }

  @DisplayName("Instanciar: Parada3")
  private Parada parada3() {
    Ubicacion ubicacion = new Ubicacion(10, "medrano", "500");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(300,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }
  @DisplayName("Instanciar: Parada4")
  private Parada parada4() {
    Ubicacion ubicacion = new Ubicacion(10, "medrano", "800");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(0,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }

  @DisplayName("Instanciar: Parada5")
  private Parada parada5() {
    Ubicacion ubicacion = new Ubicacion(457, "O'Higgins", "200");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(0,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }

  @DisplayName("Instanciar: Parada6")
  private Parada parada6() {
    Ubicacion ubicacion = new Ubicacion(1, "maipu", "500");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(4200,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }
}
