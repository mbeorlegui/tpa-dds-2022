package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.services.apidistancias.entities.ResultadoDistancia;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OrderColumn;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "transporte_publico")
public class TransportePublico extends Transporte {
  @Getter
  @Enumerated
  @Column(name = "tipo_de_transporte_publico")
  private TipoDeTransportePublico tipoDeTransportePublico;
  @Getter
  //@ManyToMany
  @OneToMany
  @JoinColumn(name = "transporte_publico_id")
  @OrderColumn(name = "posicion")
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
    if (this.tieneUnaParadaEn(ubicacion)) {
      this.validarDistancia(nuevaParada, ubicacion);
      Parada parada = this.obtenerParada(ubicacion);
      int indiceParada = paradas.indexOf(parada);
      paradas.add(indiceParada + 1, nuevaParada);
      parada.modificarDistanciaSiguienteParada(nuevaParada);
    } else {
      throw new IllegalArgumentException(
          "La ubicacion no es valida o la distancia es mayor a la permitida");
    }
  }

  public void agregarParadaFinal(
      Parada nuevaParada, Ubicacion ubicacion, ResultadoDistancia nuevaDistancia) {
    this.agregarParadaLuegoDe(nuevaParada, ubicacion);
    obtenerParada(ubicacion).setDistanciaSiguienteParada(nuevaDistancia);
  }

  public void agregarParadaInicial(Parada nuevaParada) {
    paradas.add(0, nuevaParada);
  }

  private void validarDistancia(Parada nuevaParada, Ubicacion ubicacion) {
    Parada parada = obtenerParada(ubicacion);

    if (esUltimaParada(parada) && !(nuevaParada.getDistanciaSiguienteParada() == 0)) {
      throw new IllegalArgumentException(
          "La distancia a la ultima parada debe ser 0");
    } else if (esPrimerParada(parada) && !(nuevaParada.getDistanciaSiguienteParada() > 0)) {
      throw new IllegalArgumentException(
          "La distancia de la primer parada debe ser mayor a 0");
    } else if (!(esUltimaParada(parada) || esPrimerParada(parada))
        && !(nuevaParada.getDistanciaSiguienteParada() < parada.getDistanciaSiguienteParada())) {
      throw new IllegalArgumentException(
          "La distancia de la nueva parada no puede ser mayor que la anterior");
    }
  }

  private boolean esPrimerParada(Parada parada) {
    return paradas.indexOf(parada) == 0;
  }

  private boolean esUltimaParada(Parada parada) {
    return paradas.indexOf(parada) == (paradas.size() - 1);
  }


  public boolean tieneUnaParadaEn(Ubicacion unaUbicacion) {
    return paradas.stream().anyMatch(p -> p.getUbicacion().esMismaUbicacionQue(unaUbicacion));
  }

  public void verificarParadas(Ubicacion origen, Ubicacion destino) {
    if (!this.tieneUnaParadaEn(origen) || !this.tieneUnaParadaEn(destino)) {
      throw new IllegalArgumentException("Los tramos no coinciden con las paradas");
    }
  }

  @Override
  public double calcularDistancia(Ubicacion origenDeTramo, Ubicacion destinoDeTramo) {
    this.verificarParadas(origenDeTramo, destinoDeTramo);
    List<Parada> paradasTramo = this.obtenerParadasTramo(origenDeTramo, destinoDeTramo);
    return paradasTramo.stream().mapToDouble(Parada::getDistanciaSiguienteParada).sum();
  }

  public List<Parada> obtenerParadasTramo(Ubicacion origenDeTramo, Ubicacion destinoDeTramo) {
    int indiceParadaOrigen = paradas.indexOf(this.obtenerParada(origenDeTramo));
    int indiceParadaDestino = paradas.indexOf(this.obtenerParada(destinoDeTramo));
    return paradas.subList(indiceParadaOrigen, indiceParadaDestino);
  }

  public Parada obtenerParada(Ubicacion ubicacion) {
    return paradas.stream()
        .filter(p -> p.getUbicacion().esMismaUbicacionQue(ubicacion))
        .findFirst().orElse(null);
  }
}
