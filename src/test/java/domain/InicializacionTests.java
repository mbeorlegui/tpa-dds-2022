package domain;

import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.Tipo;
import domain.transporte.*;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;


@Getter
public class InicializacionTests {
  private Organizacion utn;
  private Trayecto casaHastaUTN;
  private TransportePublico colectivoLinea7;
  private TransportePublico colectivoLinea157;
  private TransportePublico subteX;
  private Bicicleta bicicleta;
  private ServicioContratado taxi;
  private VehiculoParticular motoNafta;

  public InicializacionTests() {
    this.utn = unaUniversidadGubernamental();
    this.casaHastaUTN = casaHastaUTN();
    this.colectivoLinea7 = colectivoLinea7();
    this.colectivoLinea157 = colectivoLinea157();
    this.subteX = subteX();
    this.bicicleta = bicicleta();
    this.taxi = taxi();
    this.motoNafta = motoNafta();
  }

  /*
    Instancia de Organizacion UTN
   */
  @DisplayName("Instanciar: Univerdidad Gubernamental")
  private Organizacion unaUniversidadGubernamental() {
    Ubicacion ubicacionUtn = new Ubicacion(457,"O'Higgins", "200");
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL, ubicacionUtn, Clasificacion.UNIVERSIDAD);
    // organizacion.crearNuevoSector();
    Sector unSectorDeRRHH = sectorDeRRHH();
    organizacion.addSector(unSectorDeRRHH);
    organizacion.addSector(new Sector());
    organizacion.addSector(new Sector());
    Miembro miembro = new Miembro("Alejo", "Goltzman", 43994311, Documento.DNI, casaHastaUTN());
    //miembro.addSector(unSectorDeRRHH, organizacion);
    unSectorDeRRHH.addMiembro(miembro);
    return organizacion;
  }

  @DisplayName("Instanciar: Sector de RRHH")
  private Sector sectorDeRRHH() {
    return new Sector();
  }

  /*
    Instancia de Trayecto de casa hasta UTN
   */
  @DisplayName("Instanciar: Trayecto")
  public Trayecto casaHastaUTN() {
    Ubicacion casa = new Ubicacion(1,"maipu","100");
    Ubicacion paradaCasaLinea7 = new Ubicacion(1,"maipu", "500");
    Ubicacion ubicacionUtn = new Ubicacion(457,"O'Higgins", "200");

    Tramo casaHastaParadaLinea7 = new Tramo(casa, paradaCasaLinea7, new Pie());
    Tramo paradaLinea7HastaUTN = new Tramo(paradaCasaLinea7, ubicacionUtn, colectivoLinea7);

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(casaHastaParadaLinea7);
    tramos.add(paradaLinea7HastaUTN);
    return new Trayecto(tramos);
  }

  /*
    Instancia de TransportePublico colectivo linea 7
   */
  @DisplayName("Instanciar: Colectivo Linea 7")
  private TransportePublico colectivoLinea7() {
    TransportePublico colectivo7 = new TransportePublico(TipoDeTransportePublico.COLECTIVO,"7");
    colectivo7.addParadas(new Ubicacion(457,"O'Higgins", "200"),
                          new Ubicacion(1,"maipu", "500"));
    return colectivo7;
  }

  /*
    Instancia de TransportePublico subte linea X
   */
  @DisplayName("Instanciar: Subte X")
  private TransportePublico subteX() {
    TransportePublico subte = new TransportePublico(TipoDeTransportePublico.SUBTE, "X");
    subte.addParadas(parada1(),parada2());
    return subte;
  }

  @DisplayName("Instanciar: Parada1")
  private Ubicacion parada1() {
    return new Ubicacion(15, "mozart", "1400");
  }

  @DisplayName("Instanciar: Parada2")
  private Ubicacion parada2() {
    return new Ubicacion(20, "rivadavia", "4000");
  }

  /*
    Instancia de TransportePublico colectivo linea 157
   */
  @DisplayName("Instanciar: Colectivo linea 157")
  private TransportePublico colectivoLinea157() {
    TransportePublico bondi = new TransportePublico(TipoDeTransportePublico.COLECTIVO, "157");
    bondi.addParadas(parada3(),parada4());
    return bondi;
  }

  @DisplayName("Instanciar: Parada3")
  private Ubicacion parada3() {
    return new Ubicacion(30, "medrano", "500");
  }

  @DisplayName("Instanciar: Parada4")
  private Ubicacion parada4() {
    return new Ubicacion(10, "medrano", "800");
  }

  /*
    Instancia de Transporte bicicleta
   */
  @DisplayName("Instanciar: Bicicleta")
  private Bicicleta bicicleta() {
    return new Bicicleta();
  }

  /*
    Instancia de Transporte ServicioContratado taxi
   */
  @DisplayName("Instanciar: Taxi")
  private ServicioContratado taxi() {
    return new ServicioContratado(TipoDeServicioContratado.TAXI);
  }

  /*
    Instancia de Transporte VehiculoParticular moto nafta
   */
  @DisplayName("Instanciar: Moto que usa nafta")
  private VehiculoParticular motoNafta() {
    return new VehiculoParticular(TipoDeVehiculo.MOTO, Combustible.NAFTA);
  }
}
