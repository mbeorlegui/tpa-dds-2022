package domain;

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

import static org.junit.jupiter.api.Assertions.*;

public class TrayectoTests {

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

  @DisplayName("Instanciar: Univerdidad Tecnologica Nacional FRBA")
  private Organizacion universidadTecnologicaNacionalFRBA() {
    Ubicacion ubicacionUtn = new Ubicacion(457,"O'Higgins", "200");
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL, ubicacionUtn, Clasificacion.UNIVERSIDAD);
    // organizacion.crearNuevoSector();
    // organizacion.crearNuevoSector();
    // organizacion.crearNuevoSector();
    organizacion.addSector(new Sector());
    organizacion.addSector(new Sector());
    organizacion.addSector(new Sector());

    return organizacion;
  }

  @Test
  public void tramoCasaHastaUtnTienePrimerTramoHastaParadaLinea7() {
    Ubicacion casa = new Ubicacion(1,"maipu","100");
    Ubicacion paradaCasaLinea7 = new Ubicacion(1,"maipu", "500");
    Tramo casaHastaParadaLinea7 = new Tramo(casa, paradaCasaLinea7, new Pie());

    assertTrue(casaHastaUTN().getTramos().get(0).esMismoTramo(casaHastaParadaLinea7));
  }

  @Test
  public void inicioDelTrayectoEsCasa() {
    Ubicacion inicioTrayecto = casaHastaUTN().getTramos().get(0).getInicioDeTramo();
    Ubicacion casa = new Ubicacion(1,"maipu","100");
    assertTrue(inicioTrayecto.esMismaUbicacionQue(casa));
  }

  @Test
  public void finalDelTrayectoEsUniversidadTecnologicaNacionalFRBA() {
    Ubicacion finalTrayecto = casaHastaUTN().getTramos().get(1).getFinDeTramo();
    assertTrue(finalTrayecto.esMismaUbicacionQue(universidadTecnologicaNacionalFRBA().getUbicacion()));
  }

}
