package domain.transporte;

import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransportePublico extends Transporte {
  private TipoDeTransportePublico tipoDeTransportePublico;
  private List<Ubicacion> paradas = new ArrayList<>();
  private String linea;
  //private Ubicacion paradaInicio; // Agrego paradaInicio y paradaFin como atributos
  //private Ubicacion paradaFin;

  //public TransportePublico(TipoDeTransportePublico tipoDeTransportePublico, String linea,
  // Ubicacion paradaInicio, Ubicacion paradaFin) {
  public TransportePublico(TipoDeTransportePublico tipoDeTransportePublico, String linea) {
    this.tipoDeTransportePublico = tipoDeTransportePublico;
    this.linea = linea;
    //this.paradaInicio = paradaInicio;//Agrego paradaInicio y paradaFin como parametros
    // necesarios para el constructor.
    //this.paradaFin = paradaFin;No creo que sea necesario teniendo una lista de paradas,suponiendo
    //que la primera sera el elemento 0 (se puede conseguir con .get(i)) y lo mismo para la ultima
    setTipoTransporte(TipoTransporte.PUBLICO);
  }

  public TipoDeTransportePublico getTipoDeTransportePublico() {
    return tipoDeTransportePublico;
  }

  public List<Ubicacion> getParadas() {
    return paradas;
  }

  public String getLinea() {
    return linea;
  }

  /*public Ubicacion getParadaInicio() {
    return paradaInicio;
  }

  public Ubicacion getParadaFin() {
    return paradaFin;
  }
*/
  public void addParadas(Ubicacion... ubicaciones) {
    Collections.addAll(this.paradas, ubicaciones);
  }

  public boolean tieneUnaParadaEn(Ubicacion unaUbicacion) {
    return paradas.stream().anyMatch(ubicacion -> ubicacion.esMismaUbicacionQue(unaUbicacion));
  }

}
