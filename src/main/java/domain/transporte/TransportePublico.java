package domain.transporte;

import domain.ubicacion.Ubicacion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransportePublico implements Transporte {
  private TipoDeTransportePublico tipoDeTransportePublico;
  private List<Ubicacion> paradas = new ArrayList<>();
  private String linea;
  private TipoTransporte tipoTransporte;

  public TipoTransporte getTipoTransporte() {
    return tipoTransporte;
  }

  public void setTipoTransporte(TipoTransporte tipoTransporte) {
    this.tipoTransporte = tipoTransporte;
  }

  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte) {
    return (this.tipoTransporte.equals(unTransporte.getTipoTransporte()));
  }

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

  public void addParadas(Ubicacion... ubicaciones) {
    Collections.addAll(this.paradas, ubicaciones);
  }

  public boolean tieneUnaParadaEn(Ubicacion unaUbicacion) {
    return paradas.stream().anyMatch(ubicacion -> ubicacion.esMismaUbicacionQue(unaUbicacion));
  }

  public boolean esTranspportePublico() {
    return (this.tipoTransporte.equals(TipoTransporte.PUBLICO));
  }

}
