package domain.transporte;

import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransportePublico implements Transporte {
  @Getter
  private TipoDeTransportePublico tipoDeTransportePublico;
  @Getter
  private List<Ubicacion> paradas = new ArrayList<>();
  @Getter
  private String linea;
  @Getter
  @Setter
  private TipoTransporte tipoTransporte;

  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte) {
    return (this.tipoTransporte.equals(unTransporte.getTipoTransporte()));
  }

  public TransportePublico(TipoDeTransportePublico tipoDeTransportePublico, String linea) {
    this.tipoDeTransportePublico = tipoDeTransportePublico;
    this.linea = linea;
    setTipoTransporte(TipoTransporte.PUBLICO);
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
