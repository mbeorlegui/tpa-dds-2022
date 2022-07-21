package domain;

import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.services.apidistancias.ServicioDistancia;
import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.trayecto.Tramo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TramoTests {
  private Tramo casaHastaLinea7;
  private Tramo casa2HastaLinea7;
  private Tramo linea7HastaUTN;
  private ResultadoDistancia resultadoDistancia1;
  private ResultadoDistancia resultadoDistancia2;
  private CalculadoraDeDistancia calculadoraMock;
  private ServicioDistancia servicioDistancia;

  @BeforeEach
  void init() {
    InicializacionTests inicializador = new InicializacionTests();
    casaHastaLinea7 = inicializador.getTramos().getCasaHastaParadaLinea7();
    casa2HastaLinea7 = inicializador.getTramos().getCasa2HastaParadaLinea7();
    linea7HastaUTN = inicializador.getTramos().getParadaLinea7HastaUTN();
    resultadoDistancia1 = new ResultadoDistancia(50, "m");
    resultadoDistancia2 = new ResultadoDistancia(10, "km");
    calculadoraMock = mock(CalculadoraDeDistancia.class);
    casa2HastaLinea7.getTransporteUtilizado().setCalculadoraDeDistancia(calculadoraMock);
    servicioDistancia = ServicioDistancia.getInstancia();
  }

  @DisplayName("Calculo de distancia intermedia de un tramo")
  @Test
  public void seCalculaLaDistanciaIntermediaColectivoLinea7() {
    assertEquals(4200, linea7HastaUTN.distanciaIntermedia());
  }

  @DisplayName("Calculo de distancia intermedia de un tramo")
  @Test
  public void seCalculaCorrectamenteLaDistanciaIntermediaServicioContratado() {
    when(calculadoraMock.distancia(casa2HastaLinea7.getOrigenDeTramo(), casa2HastaLinea7.getDestinoDeTramo()))
        .thenReturn(resultadoDistancia1.getValor());
    assertEquals(50, resultadoDistancia1.getValor());
    assertEquals(resultadoDistancia1.getValor(), casa2HastaLinea7.distanciaIntermedia());
  }

  @DisplayName("Se crea correctamente la instancia de SerivicioDistancia")
  @Test
  public void seCreaInstanciaDeSerivicioDistancia(){
    assertEquals(ServicioDistancia.class,servicioDistancia.getClass());
    assertNotNull(servicioDistancia);
  }
}