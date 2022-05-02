package domain.transporte;

import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class TransportePublico extends Transporte {
  private TipoDeTransportePublico tipoDeTransportePublico;
  private List<Ubicacion> paradas = new ArrayList<>();
  private String linea;
  private Ubicacion paradaInicio; // Agrego paradaInicio y paradaFin como atributos
  private Ubicacion paradaFin;

  public TransportePublico(TipoDeTransportePublico tipoDeTransportePublico, String linea, Ubicacion paradaInicio, Ubicacion paradaFin) {
    this.tipoDeTransportePublico = tipoDeTransportePublico;
    this.linea = linea;
    this.paradaInicio = paradaInicio; // Agrego paradaInicio y paradaFin como parametros necesarios para el constructor.
    this.paradaFin = paradaFin;
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
  
  public Ubicacion getParadaInicio() {
    return paradaInicio;
  }

  public Ubicacion getParadaFin() {
    return paradaFin;
  }

  public void addParadas(List<Ubicacion> paradas) {
    paradas.stream().map(parada -> this.paradas.add(parada));
  }
  
  public void addParada(Ubicacion parada){
    this.paradas.add(parada); 
  }
}
