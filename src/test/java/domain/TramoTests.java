package domain;

import domain.medicion.Unidad;
import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.trayecto.Tramo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TramoTests {
  private Tramo casaHastaLinea7;
  private ResultadoDistancia resultadoDistancia;

  @BeforeEach
  void init() {
    InicializacionTests inicializador = new InicializacionTests();
    casaHastaLinea7 = inicializador.casaHastaLinea7();
    resultadoDistancia = new ResultadoDistancia(50, "km");
  }

  @DisplayName("Calculo de distancia intermedia de un tramo")
  @Test
  public void seCalculaCorrectamenteLaDistanciaIntermedia() {
    CalculadoraDeDistancia calculadoraMock = mock(CalculadoraDeDistancia.class);
    casaHastaLinea7.setCalculadoraDeDistancia(calculadoraMock);
    when(calculadoraMock.distancia(casaHastaLinea7.getOrigenDeTramo(), casaHastaLinea7.getDestinoDeTramo()))
        .thenReturn(resultadoDistancia.getValor());
    assertEquals(50,resultadoDistancia.getValor());
    assertEquals(resultadoDistancia.getValor(), casaHastaLinea7.distanciaIntermedia());
  }
}
