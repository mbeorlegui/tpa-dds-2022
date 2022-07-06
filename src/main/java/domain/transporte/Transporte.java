package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;


public abstract class Transporte {
  @Getter
  public TipoConsumo combustible;
  @Getter
  public Double combustiblePorKm;
  @Getter
  @Setter
  public TipoTransporte tipoTransporte;
  @Getter @Setter
  private CalculadoraDeDistancia calculadoraDeDistancia;

  public Transporte(TipoConsumo combustible, Double combustiblePorKm) {
    this.combustible = combustible;
    this.combustiblePorKm = combustiblePorKm;
  }

  public abstract void verificarParadas(Ubicacion origen, Ubicacion destino);


  public double calcularDistancia(Ubicacion origenDeTramo, Ubicacion destinoDeTramo) {
    return this.calculadoraDeDistancia.distancia(origenDeTramo, destinoDeTramo);
  }

  public boolean esDeTipo(TipoTransporte tipoTransporte) {
    return this.tipoTransporte.equals(tipoTransporte);
  }

  public double huellaDeCarbonoDeDistancia(double distanciaRecorrida) {
    return combustible.calcularHuellaDeCarbono(distanciaRecorrida * combustiblePorKm);
  }
}

// TODO: verificar