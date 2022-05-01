package domain.transporte;

import domain.organizacion.Tipo;
import domain.ubicacion.Ubicacion;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TransportePublico extends Transporte {
  private TipoDeTransportePublico tipoDeTransportePublico;
  private List<Ubicacion> paradas;
  private String linea;
  private Ubicacion paradaInicio;
  private Ubicacion paradaFin;

  public TransportePublico(TipoDeTransportePublico tipoDeTransportePublico, String linea) {
    this.tipoDeTransportePublico = tipoDeTransportePublico;
    this.linea = linea;
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

  public void addParadas(List<Ubicacion> paradas) {paradas.stream().map(parada -> this.paradas.add(parada));}
}
