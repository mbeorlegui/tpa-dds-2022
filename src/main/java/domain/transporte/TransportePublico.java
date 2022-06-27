package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.services.apidistancias.entities.ResultadoDistancia;
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

  public void agregarParadaLuegoDe(Parada nuevaParada, Ubicacion ubicacion) {
    if(this.tieneUnaParadaEn(ubicacion)&&this.distanciaValida(nuevaParada, ubicacion)){
      Parada parada = this.obtenerParada(ubicacion);
      int indiceParada = paradas.indexOf(parada);
      paradas.add(indiceParada+1,nuevaParada);
      ResultadoDistancia nuevaDistacia =new ResultadoDistancia(
          parada.getDistanciaSiguienteParada()-nuevaParada.getDistanciaSiguienteParada(),
          parada.getUnidadDistancia());
      parada.setDistanciaSiguienteParada(nuevaDistacia);
    } else {
      throw new IllegalArgumentException(); // la ubicacion no es valida o la distancia es mayor a la permitida
    }
  }

  public void agregarParadaFinal(Parada nuevaParada, Ubicacion ubicacion, ResultadoDistancia nuevaDistancia) {
    this.agregarParadaLuegoDe(nuevaParada, ubicacion);
    obtenerParada(ubicacion).setDistanciaSiguienteParada(nuevaDistancia);
  }

  public void agregarParadaInicial(Parada nuevaParada) {
    paradas.add(0,nuevaParada);
  }

  private boolean distanciaValida(Parada nuevaParada, Ubicacion ubicacion) {
    Parada parada = obtenerParada(ubicacion);
    if(paradas.indexOf(parada)==paradas.size()-1){ //es la ultima parada permite distanciaSiguiente igual 0
      return nuevaParada.getDistanciaSiguienteParada()==0;
    } else if (paradas.indexOf(parada)==0) { //es la primer parada distancia debe ser mayor a 0
      return nuevaParada.getDistanciaSiguienteParada()>0;
    }else {
      return nuevaParada.getDistanciaSiguienteParada() < parada.getDistanciaSiguienteParada();
    }
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
    this.verificarParadas(origenDeTramo,destinoDeTramo);
    List<Parada> paradasTramo = this.obtenerParadasTramo(origenDeTramo, destinoDeTramo);
    return paradasTramo.stream().mapToDouble(p->p.getDistanciaSiguienteParada()).sum();
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

  public Parada obtenerParada(Ubicacion ubicacion){
    for (int i=0;i<paradas.size();i++){
      if(this.paradas.get(i).getUbicacion().esMismaUbicacionQue(ubicacion)){
        return paradas.get(i);
      }
    }
    return null;
  }
}
