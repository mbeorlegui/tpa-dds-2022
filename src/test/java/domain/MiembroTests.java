package domain;

import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.Tipo;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiembroTests {
  @DisplayName("Al agregar varios sectores a Miembro sin repetir")
  @Test
  public void correctoAgregadoDeSectoresAMiembro() {
    unMiembro().addSector(unSector());
    unMiembro().addSector(otroSector());
    unMiembro().addSector(otroSector());
    otroMiembro().addSector(unSector());
    otroMiembro().addSector(otroSector());
    assertEquals(unMiembro().getSectores(), otroMiembro().getSectores());
  }


  @DisplayName("Instanciar: Miembro")
  public Miembro unMiembro() {
    return new Miembro(
        "Matias", "Beorlegui", 47813065, Documento.DNI);
  }

  @DisplayName("Instanciar: Otro miembro")
  public Miembro otroMiembro() {
    return new Miembro(
        "Alejo", "Goltzman", 43978123, Documento.DNI);
  }

  @DisplayName("Instanciar: Un sector")
  public Sector unSector() {
    return new Sector(unaOrganizacion());
  }

  @DisplayName("Instanciar: Otro sector")
  public Sector otroSector() {
    return new Sector(otraOrganizacion());
  }

  @DisplayName("Instanciar: Una organizacion")
  public Organizacion unaOrganizacion() {
    return new Organizacion(
        "OrgFalsa SRL", Tipo.EMPRESA, new Ubicacion(-34.659488779869484, -58.4671460833512), Clasificacion.EMPRESA_DEL_SECTOR_SECUNDARIO);
  }

  @DisplayName("Instanciar: Otra organizacion")
  public Organizacion otraOrganizacion() {
    return new Organizacion(
        "OtraOrgFalsa SRL", Tipo.EMPRESA, new Ubicacion(-34.659488779869484, -58.4671460833512), Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
  }
}
