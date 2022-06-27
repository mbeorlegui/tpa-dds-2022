package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TransportePublico extends Transporte {
  @Getter
  private TipoDeTransportePublico tipoDeTransportePublico;
  @Getter
  private List<Parada> paradas = new ArrayList<>();
  @Getter
  private String linea;

  public TransportePublico(TipoConsumo combustible, Double combustiblePorKm,
                           TipoDeTransportePublico tipoDeTransportePublico, String linea) {
    super(combustible, combustiblePorKm);
    this.tipoDeTransportePublico = tipoDeTransportePublico;
    this.linea = linea;
    setTipoTransporte(TipoTransporte.PUBLICO);
  }

  public void addParadas(Parada... paradas) {
    Collections.addAll(this.paradas, paradas);
  }

  public boolean tieneUnaParadaEn(Ubicacion unaUbicacion) {
    return paradas.stream().anyMatch(p -> p.getUbicacion().esMismaUbicacionQue(unaUbicacion));
  }

//  public Parada paradaEn(Ubicacion unaUbicacion) {
//    return paradas.stream().findFirst(p -> p.getUbicacion().esMismaUbicacionQue(unaUbicacion));
//  }

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {
    if (!this.tieneUnaParadaEn(origen) || !this.tieneUnaParadaEn(destino)) {
      throw new IllegalArgumentException(); // los tramos no coinciden con las paradas
    }
  }

  public Double calcularDistancia(Ubicacion origenDeTramo, Ubicacion destinoDeTramo) {
    List<Parada> paradasTramo = obtenerParadasTramo(origenDeTramo, destinoDeTramo);
    return paradasTramo.stream().mapToDouble(p->p.getDistanciaSiguienteParada().getValor()).sum();
  }

  public List<Parada> obtenerParadasTramo(Ubicacion origenDeTramo, Ubicacion destinoDeTramo){
    List<Parada> paradasTramo = new ArrayList<>();
    boolean recorriendoTramo=false;

    for (int i=0;i<paradas.size();i++){
      if(this.paradas.get(i).getUbicacion().esMismaUbicacionQue(origenDeTramo)){
        recorriendoTramo = true;
      } else if (this.paradas.get(i).getUbicacion().esMismaUbicacionQue(destinoDeTramo)) {
        recorriendoTramo = false;
      }
      if (recorriendoTramo==true) {
        paradasTramo.add(this.paradas.get(i)); //la ultima parada no se agrega
      }
    }
    return paradasTramo;
  }
}
