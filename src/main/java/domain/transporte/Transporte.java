package domain.transporte;

import domain.medicion.TipoConsumo;
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

  public Transporte(TipoConsumo combustible, Double combustiblePorKm) {
    this.combustible = combustible;
    this.combustiblePorKm = combustiblePorKm;
  }

  public abstract void verificarParadas(Ubicacion origen, Ubicacion destino);


  public boolean esDeTipo(TipoTransporte tipoTransporte) {
    return this.tipoTransporte.equals(tipoTransporte);
  }
}

// TODO: verificar