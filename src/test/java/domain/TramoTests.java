package domain;

import domain.services.apidistancias.AdapterCalculadoraDeDistancia;
import domain.services.apidistancias.AdapterCalculadoraDeDistanciaRetrofit;
import domain.transporte.Pie;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TramoTests {

  @DisplayName("Calculo de distancia intermedia de un tramo")
  @Test
  public void seCalculaCorrectamenteLaDistanciaIntermedia() {
    Tramo casaHastaLinea7 = casaHastaLinea7();
    AdapterCalculadoraDeDistancia adapterMock = mock(AdapterCalculadoraDeDistanciaRetrofit.class);
    casaHastaLinea7.setCalculadoraDeDistancia(adapterMock);
    when(casaHastaLinea7.distanciaIntermedia()).thenReturn(50.0);

    assertEquals(50, casaHastaLinea7.distanciaIntermedia());
  }

  @DisplayName("Instanciar: Ubicacion")
  public Ubicacion casa() {
    return new Ubicacion(1, "maipu", "100");
  }

  @DisplayName("Instanciar: Ubicacion")
  public Ubicacion linea7() {
    return new Ubicacion(457, "O'Higgins","200");
  }

  @DisplayName("Instanciar: Ubicacion")
  public Ubicacion ubicacionUtn() {
    return new Ubicacion(168, "Florida", "150");
  }

  @DisplayName("Instanciar: Tramo")
  public Tramo casaHastaLinea7() {
    return new Tramo(casa(), linea7(), new Pie());
  }

  @DisplayName("Instanciar: Tramo")
  public Tramo linea7HastaUTN() {
    return new Tramo(linea7(), ubicacionUtn(), new Pie());
  }
}
