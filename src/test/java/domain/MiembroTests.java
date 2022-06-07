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
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MiembroTests {
  
  @DisplayName("Al agregar varios sectores a Miembro sin repetir")
  @Test
  public void correctoAgregadoDeMiembrosASectoresDeDosOrganizaciones() {
    Miembro miembro1 = unMiembro();
    Miembro miembro2 = otroMiembro();
    Organizacion utn = utn();
    Organizacion org = orgFalsa();
    Sector unSector = unSector();
    Sector otroSector = unSector();
    Sector unSectorOrgFalsa = unSector();
    utn.addSector(unSector);
    utn.addSector(otroSector);
    org.addSector(unSectorOrgFalsa);
    // miembro1.addSector(unSector, utn);
    unSector.addMiembro(miembro1);
    utn.addSector(unSector);
    // miembro2.addSector(otroSector, utn);
    otroSector.addMiembro(miembro2);
    utn.addSector(otroSector);
    // miembro2.addSector(unSectorOrgFalsa,org);
    unSectorOrgFalsa.addMiembro(miembro2);
    org.addSector(unSectorOrgFalsa);
    /*
    HashSet<Organizacion> organizaciones1 = new HashSet<>();
    organizaciones1.add(utn);
    HashSet<Organizacion> organizaciones2 = new HashSet<>();
    organizaciones2.add(utn);
    organizaciones2.add(org);
    assertEquals(miembro1.getOrganizaciones(), organizaciones1);
    assertEquals(miembro2.getOrganizaciones(), organizaciones2);
    */
    assertTrue(utn.esMiembro(miembro1));
    assertTrue(utn.esMiembro(miembro2));
    assertTrue(org.esMiembro(miembro2));
  }

  @Test
  public void unMiembroSeCreaCorrectamente() {
    Miembro miembro = unMiembro();
    assertEquals(miembro.getNombre(), "Matias");
    assertEquals(miembro.getApellido(), "Beorlegui");
    assertEquals(miembro.getNumeroDeDocumento(), 47813065);
    assertEquals(miembro.getTipoDeDocumento(), Documento.DNI);
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
    return new Sector();
  }

  @DisplayName("Instanciar: Una organizacion")
  public Organizacion utn() {
    return new Organizacion(
        "UTN FRBA", Tipo.EMPRESA, new Ubicacion(457,"O'Higgins", "200"), Clasificacion.UNIVERSIDAD);
   }

  @DisplayName("Instanciar: Otra organizacion")
  public Organizacion orgFalsa() {
    return new Organizacion(
        "orgFalsa SRL", Tipo.EMPRESA, new Ubicacion(457,"O'Higgins", "500"), Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
  }

  @DisplayName("Instanciar: Trayecto")
  public Trayecto casaHastaUTN() {
    Ubicacion casa = new Ubicacion(1,"maipu","100");
    Ubicacion paradaCasaLinea7 = new Ubicacion(1,"maipu", "500");
    Ubicacion ubicacionUtn = new Ubicacion(457,"O'Higgins", "200");

    Tramo casaHastaParadaLinea7 = new Tramo(casa, paradaCasaLinea7, new Pie());
    Tramo paradaLinea7HastaUTN = new Tramo(paradaCasaLinea7, ubicacionUtn, colectivoLinea7());

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(casaHastaParadaLinea7);
    tramos.add(paradaLinea7HastaUTN);
    return new Trayecto(tramos);
  }

  @DisplayName("Instanciar: Colectivo Linea 7")
  private TransportePublico colectivoLinea7() {
    TransportePublico colectivo7 = new TransportePublico(TipoDeTransportePublico.COLECTIVO,"7");
    colectivo7.addParadas(new Ubicacion(457,"O'Higgins", "200"),
      new Ubicacion(457,"O'Higgins", "500"));
    return colectivo7;
  }
}
