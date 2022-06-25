package domain;

import domain.medicion.Medicion;
import domain.medicion.MedicionAdapter;
import domain.organizacion.CsvHandler;
import domain.organizacion.Organizacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicionTest {
  private Organizacion organizacion;
  private Organizacion otraOrganizacion;
  private Medicion unaMedicionAdaptada;
  private Medicion otraMedicionAdaptada;
  private Medicion otraMedicionAdaptadaMas;
  private CsvHandler csvHandler;

  @BeforeEach
  void initOrganizacion() {
    InicializacionTests inicializador = new InicializacionTests();
    organizacion = inicializador.getUtn();
    otraOrganizacion = inicializador.getOrgFalsa();
    MedicionAdapter medicionAdapter = inicializador.getUnAdapterDeMedicion();
    unaMedicionAdaptada = medicionAdapter.adaptarMedicion(inicializador.getMedicionDeLectura1());
    otraMedicionAdaptada = medicionAdapter.adaptarMedicion(inicializador.getMedicionDeLectura2());
    otraMedicionAdaptadaMas = medicionAdapter.adaptarMedicion(inicializador.getMedicionDeLectura3());
    csvHandler = inicializador.csvHandler();
  }

  @DisplayName("Creo una medicion, la transformo y la agrego a la organizacion")
  @Test
  public void agregarMedicionReadAOrganziacion() {
    organizacion.agregarMedicion(unaMedicionAdaptada);
    assertEquals(organizacion.getMediciones().size(), 1);
    assertTrue(organizacion.existeMedicion(unaMedicionAdaptada));
  }

  @DisplayName("Creo 3 mediciones, las transformo y las agrego a la organizacion")
  @Test
  public void agregarMedicionesReadAOrganziacion() {
    organizacion.agregarMedicion(unaMedicionAdaptada);
    organizacion.agregarMedicion(otraMedicionAdaptada);
    organizacion.agregarMedicion(otraMedicionAdaptadaMas);
    assertTrue(organizacion.existeMedicion(unaMedicionAdaptada));
    assertTrue(organizacion.existeMedicion(otraMedicionAdaptada));
    assertTrue(organizacion.existeMedicion(otraMedicionAdaptadaMas));
    assertEquals(organizacion.getMediciones().size(), 3);
  }

  @DisplayName("Las mediciones obtenidas del CSV son las mismas que las agregadas a Organizacion")
  @Test
  public void medicionesDelCsvSonCorrectamenteLeidas() throws IOException {
    // Si cambiamos el CSV este test va a romper
    organizacion.agregarMedicion(unaMedicionAdaptada);
    organizacion.agregarMedicion(otraMedicionAdaptada);
    organizacion.agregarMedicion(otraMedicionAdaptadaMas);
    otraOrganizacion.agregarMediciones(csvHandler.getMediciones());
    assertTrue(organizacion.contieneMedicionIdentica(unaMedicionAdaptada));
    assertTrue(organizacion.contieneMedicionIdentica(otraMedicionAdaptada));
    assertTrue(organizacion.contieneMedicionIdentica(otraMedicionAdaptadaMas));
    assertEquals(organizacion.getMediciones().size(), 3);
    assertTrue(otraOrganizacion.contieneMedicionIdentica(unaMedicionAdaptada));
    assertTrue(otraOrganizacion.contieneMedicionIdentica(otraMedicionAdaptada));
    assertTrue(otraOrganizacion.contieneMedicionIdentica(otraMedicionAdaptadaMas));
    assertEquals(otraOrganizacion.getMediciones().size(), 4);
  }


}
