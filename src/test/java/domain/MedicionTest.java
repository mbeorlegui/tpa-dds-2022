package domain;

import domain.medicion.MedicionAdapter;
import domain.organizacion.Organizacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.Utils.*;
import static domain.Utils.unaMedicionDeLectura;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicionTest {


  @BeforeEach
  void initOrganizacion() {
  }

  @DisplayName("Creo una medicion, la transformo y la agrego a la organizacion")
  @Test
  public void agregarMedicionReadAOrganziacion() {
    Organizacion organizacion = unaUniversidadGubernamental();
    MedicionAdapter medicionAdapter = unAdapterDeMedicion();
    organizacion.agregarMedicion(medicionAdapter.adaptarMedicion(unaMedicionDeLectura()));
    System.out.println(unaUniversidadGubernamental().getMediciones());
    assertEquals(unaUniversidadGubernamental().getMediciones().size(), 1);
  }

}
