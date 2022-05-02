package domain.transporte;

import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class TransportePublico extends Transporte {
  private TipoDeTransportePublico tipoDeTransportePublico;
  private List<Ubicacion> paradas = new ArrayList<>();
  private String linea;

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

  public void addParadas(List<Ubicacion> paradas) {
    paradas.stream().map(parada -> this.paradas.add(parada));
  }
}
