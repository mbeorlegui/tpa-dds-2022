package domain;

import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
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

import java.util.ArrayList;
import java.util.List;
public class Utils {

  @DisplayName("Instanciar: Universidad Gubernamental")
  public static Organizacion unaUniversidadGubernamental() {
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL, ubicacionUtn, Clasificacion.UNIVERSIDAD);
    Sector unSectorDeRRHH = sectorDeRRHH();
    organizacion.addSector(unSectorDeRRHH);
    organizacion.addSector(new Sector());
    organizacion.addSector(new Sector());
    Miembro miembro = new Miembro("Alejo", "Goltzman", 43994311, Documento.DNI, casaHastaUTN());
    unSectorDeRRHH.addMiembro(miembro);
    return organizacion;
  }

  @DisplayName("Instanciar: Sector de RRHH")
  private static Sector sectorDeRRHH() {
    return new Sector();
  }

  @DisplayName("Instanciar: Trayecto")
  public static Trayecto casaHastaUTN() {
    Ubicacion casa = new Ubicacion(-34.615995882339334, -58.41700275360413);
    Ubicacion paradaCasaLinea7 = new Ubicacion(-34.61908707635995, -58.41677917831219);
    Ubicacion ubicacionUtn = new Ubicacion(-34.659488779869484, -58.4671460833512);

    Tramo casaHastaParadaLinea7 = new Tramo(casa, paradaCasaLinea7, new Pie());
    Tramo paradaLinea7HastaUTN = new Tramo(paradaCasaLinea7, ubicacionUtn, colectivoLinea7());

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(casaHastaParadaLinea7);
    tramos.add(paradaLinea7HastaUTN);
    return new Trayecto(tramos);
  }

  @DisplayName("Instanciar: Colectivo Linea 7")
  private static TransportePublico colectivoLinea7() {
    TransportePublico colectivo7 = new TransportePublico(TipoDeTransportePublico.COLECTIVO, "7");
    colectivo7.addParadas(new Ubicacion(-34.61908707635995, -58.41677917831219),
        new Ubicacion(-34.659488779869484, -58.4671460833512));
    return colectivo7;
  }

  @DisplayName("Instanciar: Medicion de lectura 1")
  static MedicionRead unaMedicionDeLectura() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("MENSUAL");
    unaMedicion.setTipoConsumo("GAS_NATURAL");
    unaMedicion.setValor("100");
    unaMedicion.setPeriodoDeImputacion("03/2022");
    System.out.println(unaMedicion);
    return unaMedicion;
  }

  @DisplayName("Instanciar: Medicion de lectura 2")
  static MedicionRead otraMedicionDeLectura() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("ANUAL");
    unaMedicion.setTipoConsumo("ELECTRICIDAD");
    unaMedicion.setValor("6000");
    unaMedicion.setPeriodoDeImputacion("2020");
    return unaMedicion;
  }

  @DisplayName("Instanciar: Medicion de lectura 3")
  static MedicionRead otraMedicionDeLectura2() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("ANUAL");
    unaMedicion.setTipoConsumo("DIESEL_GASOIL");
    unaMedicion.setValor("1");
    unaMedicion.setPeriodoDeImputacion("1999");
    return unaMedicion;
  }

  @DisplayName("Instanciar: Un adapter de Medicion")
  static MedicionAdapter unAdapterDeMedicion() {
    return new MedicionAdapter();
  }


}
