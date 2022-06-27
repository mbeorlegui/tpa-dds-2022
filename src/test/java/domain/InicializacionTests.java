package domain;

import domain.medicion.MedicionAdapter;
import domain.medicion.MedicionRead;
import domain.medicion.TiposConsumos;
import domain.miembro.Documento;
import domain.miembro.Miembro;
import domain.organizacion.*;
import domain.services.apidistancias.entities.ResultadoDistancia;
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
  private Organizacion orgFalsa;
  private Trayecto casaHastaUTN;
  private TransportePublico colectivoLinea7;
  private TransportePublico colectivoLinea157;
  private TransportePublico subteX;
  private Bicicleta bicicleta;
  private ServicioContratado taxi;
  private VehiculoParticular motoNafta;
  private Miembro unMiembro;
  private Miembro otroMiembro;
  private Trayecto servicioContratadoYVehiculoParticular;
  private Sector sectorDeRRHH;
  private MedicionRead medicionDeLectura1;
  private MedicionRead medicionDeLectura2;
  private MedicionRead medicionDeLectura3;
  private MedicionAdapter unAdapterDeMedicion;
  private Ubicacion casa;
  private Ubicacion linea7;
  private Ubicacion ubicacionUtn;
  private Tramo casaHastaLinea7;
  private Tramo linea7HastaUTN;
  private CsvHandler csvHandler;

  public InicializacionTests() {
    this.casa = casa();
    this.linea7 = linea7();
    this.ubicacionUtn = ubicacionUtn();
    this.unMiembro = unMiembro();
    this.otroMiembro = otroMiembro();
    this.sectorDeRRHH = sectorDeRRHH();
    this.utn = unaUniversidadGubernamental();
    this.orgFalsa = orgFalsa();
    this.colectivoLinea7 = colectivoLinea7();
    this.colectivoLinea157 = colectivoLinea157();
    this.subteX = subteX();
    this.bicicleta = bicicleta();
    this.taxi = taxi();
    this.motoNafta = motoNafta();
    this.casaHastaUTN = casaHastaUTN();
    this.servicioContratadoYVehiculoParticular = trayectoConServicioContratadoYVehiculoParticular();
    this.medicionDeLectura1 = medicionDeLectura1();
    this.medicionDeLectura2 = medicionDeLectura2();
    this.medicionDeLectura3 = medicionDeLectura3();
    this.unAdapterDeMedicion = unAdapterDeMedicion();
    this.casaHastaLinea7 = casaHastaLinea7();
    this.linea7HastaUTN = linea7HastaUTN();
    this.csvHandler = csvHandler();
  }

  /*
    Instancia de Organizacion UTN
   */
  @DisplayName("Instanciar: Univerdidad Gubernamental")
  private Organizacion unaUniversidadGubernamental() {
    Organizacion organizacion = new Organizacion("UTN", Tipo.GUBERNAMENTAL,
        ubicacionUtn, Clasificacion.UNIVERSIDAD);
    organizacion.addSector(sectorDeRRHH);
    organizacion.addSector(new Sector());
    organizacion.addSector(new Sector());
    sectorDeRRHH.addMiembro(otroMiembro);
    return organizacion;
  }

  @DisplayName("Instanciar: Un sector")
  public Sector unSector() {
    return new Sector();
  }

  @DisplayName("Instanciar: Sector de RRHH")
  private Sector sectorDeRRHH() {
    return new Sector();
  }

  @DisplayName("Instanciar: Miembro")
  public Miembro unMiembro() {
    return new Miembro(
        "Matias", "Beorlegui", 47813065, Documento.DNI, casaHastaUTN);
  }

  @DisplayName("Instanciar: Otro miembro")
  public Miembro otroMiembro() {
    return new Miembro(
        "Alejo", "Goltzman", 43978123, Documento.DNI, casaHastaUTN);
  }

  @DisplayName("Instanciar: Otra organizacion")
  public Organizacion orgFalsa() {
    Sector unSector = unSector();
    unSector.addMiembro(unMiembro);
    Sector otroSector = unSector();
    otroSector.addMiembro(otroMiembro);

    Organizacion organizacion = new Organizacion(
        "orgFalsa SRL", Tipo.EMPRESA, new Ubicacion(10, "medrano", "720"),
        Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
    organizacion.addSector(unSector);
    organizacion.addSector(otroSector);
    return organizacion;
  }

  @DisplayName("Instanciar: Trayecto con servicio contratado y vehiculo particular")
  private Trayecto trayectoConServicioContratadoYVehiculoParticular() {
    Ubicacion casa = new Ubicacion(1, "maipu", "100");
    Ubicacion estacionamiento = new Ubicacion(1, "maipu", "2250");

    Tramo primerTramo = new Tramo(casa, estacionamiento, new ServicioContratado(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"), 100.0, TipoDeServicioContratado.TAXI));
    Tramo segundoTramo = new Tramo(estacionamiento, orgFalsa.getUbicacion(), new VehiculoParticular(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"), 100.0, TipoDeVehiculo.AUTO));

    List<Tramo> tramos = new ArrayList<>();
    tramos.add(primerTramo);
    tramos.add(segundoTramo);

    return new Trayecto(tramos);
  }

  /*
    Instancia de Trayecto de casa hasta UTN
   */
  @DisplayName("Instanciar: Trayecto")
  public Trayecto casaHastaUTN() {
    Ubicacion casa = new Ubicacion(1, "maipu", "100");
    Ubicacion paradaCasaLinea7 = new Ubicacion(1, "maipu", "500");

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
    TransportePublico colectivo7 = new TransportePublico(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"), 100.0, TipoDeTransportePublico.COLECTIVO, "7");
    colectivo7.addParadas(parada5(),parada6());
    return colectivo7;
  }

  @DisplayName("Instanciar: Parada5")
  private Parada parada5() {
    Ubicacion ubicacion = new Ubicacion(457, "O'Higgins", "200");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(4200,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }

  @DisplayName("Instanciar: Parada6")
  private Parada parada6() {
    Ubicacion ubicacion = new Ubicacion(1, "maipu", "500");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(0,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }

  /*
    Instancia de TransportePublico subte linea X
   */
  @DisplayName("Instanciar: Subte X")
  private TransportePublico subteX() {
    TransportePublico subte = new TransportePublico(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"), 100.0, TipoDeTransportePublico.SUBTE, "X");
    subte.addParadas(parada1(), parada2(),parada6());
    return subte;
  }

  @DisplayName("Instanciar: Parada1")
  private Parada parada1() {
    Ubicacion ubicacion = new Ubicacion(15, "mozart", "1400");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(3000,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }

  @DisplayName("Instanciar: Parada2")
  private Parada parada2() {
    Ubicacion ubicacion = new Ubicacion(20, "rivadavia", "4000");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(0,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }
  /*
    Instancia de TransportePublico colectivo linea 157
   */
  @DisplayName("Instanciar: Colectivo linea 157")
  private TransportePublico colectivoLinea157() {
    TransportePublico bondi = new TransportePublico(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"), 100.0, TipoDeTransportePublico.COLECTIVO, "157");
    bondi.addParadas(parada3(), parada4());
    return bondi;
  }

  @DisplayName("Instanciar: Parada3")
  private Parada parada3() {
    Ubicacion ubicacion = new Ubicacion(10, "medrano", "500");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(300,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
  }
  @DisplayName("Instanciar: Parada4")
  private Parada parada4() {
    Ubicacion ubicacion = new Ubicacion(10, "medrano", "800");
    ResultadoDistancia distanciaSiguienteParada = new ResultadoDistancia(0,"M");
    return new Parada(ubicacion,distanciaSiguienteParada);
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
    return new ServicioContratado(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"), 100.0, TipoDeServicioContratado.TAXI);
  }

  /*
    Instancia de Transporte VehiculoParticular moto nafta
   */
  @DisplayName("Instanciar: Moto que usa nafta")
  private VehiculoParticular motoNafta() {
    return new VehiculoParticular(TiposConsumos.getInstance().hayarTipo("GAS_NATURAL"), 100.0, TipoDeVehiculo.MOTO);
  }

  /*
    Instancias de Mediciones
   */
  @DisplayName("Instanciar: Medicion de lectura 1")
  static MedicionRead medicionDeLectura1() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("MENSUAL");
    unaMedicion.setTipoConsumo("GAS_NATURAL");
    unaMedicion.setValor("100");
    unaMedicion.setPeriodoDeImputacion("03/2022");
    return unaMedicion;
  }

  @DisplayName("Instanciar: Medicion de lectura 2")
  static MedicionRead medicionDeLectura2() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("MENSUAL");
    unaMedicion.setTipoConsumo("ELECTRICIDAD");
    unaMedicion.setValor("6000");
    unaMedicion.setPeriodoDeImputacion("04/2021");
    return unaMedicion;
  }

  @DisplayName("Instanciar: Medicion de lectura 3")
  static MedicionRead medicionDeLectura3() {
    MedicionRead unaMedicion = new MedicionRead();
    unaMedicion.setPeriodicidad("ANUAL");
    unaMedicion.setTipoConsumo("DIESEL_GASOIL");
    unaMedicion.setValor("50");
    unaMedicion.setPeriodoDeImputacion("2021");
    return unaMedicion;
  }

  @DisplayName("Instanciar: Un adapter de Medicion")
  static MedicionAdapter unAdapterDeMedicion() {
    return new MedicionAdapter();
  }


  @DisplayName("Instanciar: Ubicacion")
  public Ubicacion casa() {
    return new Ubicacion(1, "maipu", "100");
  }

  @DisplayName("Instanciar: Ubicacion")
  public Ubicacion linea7() {
    return new Ubicacion(457, "O'Higgins", "200");
  }

  @DisplayName("Instanciar: Ubicacion")
  public Ubicacion ubicacionUtn() {
    return new Ubicacion(457, "O'Higgins", "200");
  }

  @DisplayName("Instanciar: Tramo")
  public Tramo casaHastaLinea7() {
    return new Tramo(casa(), linea7, new Pie());
  }

  @DisplayName("Instanciar: Tramo")
  public Tramo linea7HastaUTN() {
    return new Tramo(linea7, ubicacionUtn, new Pie());
  }

  @DisplayName("Instanciar: Csv Handler")
  public CsvHandler csvHandler() {
    return new CsvHandler();
  }
}
