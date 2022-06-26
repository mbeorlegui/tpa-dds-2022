package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransportePublico extends Transporte {
  @Getter
  private TipoDeTransportePublico tipoDeTransportePublico;
  @Getter
  private List<Ubicacion> paradas = new ArrayList<>();
  @Getter
  private String linea;

  public TransportePublico(TipoConsumo combustible, Double combustiblePorKm,
                           TipoDeTransportePublico tipoDeTransportePublico, String linea) {
    super(combustible, combustiblePorKm);
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

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {
    if (!this.tieneUnaParadaEn(origen) || !this.tieneUnaParadaEn(destino)) {
      throw new IllegalArgumentException(); // los tramos no coinciden con las paradas
    }
  }

}
