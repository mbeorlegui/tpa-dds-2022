package domain;

import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.Tipo;
import domain.transporte.Pie;
import domain.transporte.TipoDeTransportePublico;
import domain.transporte.TransportePublico;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        "Matias", "Beorlegui", 47813065, Documento.DNI, casaHastaUTN());
  }

  @DisplayName("Instanciar: Otro miembro")
  public Miembro otroMiembro() {
    return new Miembro(
        "Alejo", "Goltzman", 43978123, Documento.DNI, casaHastaUTN());
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

  @DisplayName("Instanciar: Trayecto")
  public Trayecto casaHastaUTN() {
    Ubicacion casa = new Ubicacion(-34.615995882339334, -58.41700275360413);
    Ubicacion paradaCasaLinea7 = new Ubicacion(-34.61908707635995, -58.41677917831219);
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);

    Tramo casaHastaParadaLinea7 = new Tramo(casa, paradaCasaLinea7, new Pie());
    Tramo paradaLinea7HastaUTN = new Tramo(paradaCasaLinea7, ubicacionUtn, new TransportePublico(TipoDeTransportePublico.COLECTIVO, "7"));

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(casaHastaParadaLinea7);
    tramos.add(paradaLinea7HastaUTN);
    return new Trayecto(tramos);
  }

}
