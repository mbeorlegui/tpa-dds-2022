package domain;

import domain.services.apidistancias.AdapterCalculadoraDeDistancia;
import domain.services.apidistancias.AdapterCalculadoraDeDistanciaRetrofit;
import domain.trayecto.Tramo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TramoTests {
  private Tramo casaHastaLinea7;

  @BeforeEach
  void init() {
    InicializacionTests inicializador = new InicializacionTests();
    casaHastaLinea7 = inicializador.casaHastaLinea7();
  }

  @DisplayName("Calculo de distancia intermedia de un tramo")
  @Test
  public void seCalculaCorrectamenteLaDistanciaIntermedia() {
    AdapterCalculadoraDeDistancia adapterMock = mock(AdapterCalculadoraDeDistanciaRetrofit.class);
    casaHastaLinea7.setCalculadoraDeDistancia(adapterMock);
    when(casaHastaLinea7.distanciaIntermedia()).thenReturn(50.0);

    assertEquals(50, casaHastaLinea7.distanciaIntermedia());
  }

}
