package domain;

import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
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
  private Trayecto casaHastaUTN(){
    Ubicacion casa = new Ubicacion(-34.615995882339334, -58.41700275360413);
    Ubicacion paradaCasaLinea7 = new Ubicacion(-34.61908707635995, -58.41677917831219);
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);

    Tramo casaHastaParadaLinea7 = new Tramo(casa,paradaCasaLinea7,new Pie());
    Tramo paradaLinea7HastaUTN = new Tramo(paradaCasaLinea7,ubicacionUtn,new TransportePublico(TipoDeTransportePublico.COLECTIVO,"7"));

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(casaHastaParadaLinea7);
    tramos.add(paradaLinea7HastaUTN);
    return new Trayecto(tramos);
  }

  @DisplayName("Instanciar: Univerdidad Tecnologica Nacional FRBA")
  private Organizacion universidadTecnologicaNacionalFRBA() {
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL, ubicacionUtn, Clasificacion.UNIVERSIDAD);
    organizacion.crearNuevoSector();
    organizacion.crearNuevoSector();
    organizacion.crearNuevoSector();

    return organizacion;
  }

  @Test
  public void tramoCasaHastaUtnTienePrimerTramoHastaParadaLinea7(){
    Ubicacion casa = new Ubicacion(-34.615995882339334, -58.41700275360413);
    Ubicacion paradaCasaLinea7 = new Ubicacion(-34.61908707635995, -58.41677917831219);
    Tramo casaHastaParadaLinea7 = new Tramo(casa,paradaCasaLinea7,new Pie());

    assertEquals(casaHastaUTN().getTramos().get(0),(casaHastaParadaLinea7));
  }

  @Test
  public void inicioDelTrayectoEsCasa(){
    Ubicacion inicioTrayecto = casaHastaUTN().getTramos().get(0).getInicioDeTramo();
    Ubicacion casa = new Ubicacion(-34.615995882339334, -58.41700275360413);
    assertEquals(inicioTrayecto, casa);
  }

  @Test
  public void finalDelTrayectoEsUniversidadTecnologicaNacionalFRBA(){
    Ubicacion finalTrayecto = casaHastaUTN().getTramos().get(1).getFinDeTramo();
    assertEquals(finalTrayecto, universidadTecnologicaNacionalFRBA().getUbicacion());
  }

}
