package domain.transporte;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.TipoConsumo;
import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.services.apidistancias.CalculadoraDeDistanciaRetrofit;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transporte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminador",discriminatorType = DiscriminatorType.STRING)
public abstract class Transporte {
  @Id
  @GeneratedValue
  @Column(name = "transporte_id")
  private long id;
  @Getter
  @Transient
  public TipoConsumo combustible;
  @Getter
  @Column(name = "combustible_por_km")
  public Double combustiblePorKm;
  @Getter
  @Setter
  @Enumerated
  @Column(name = "tipo_transporte")
  public TipoTransporte tipoTransporte;
  @Getter @Setter
  @Transient
  private CalculadoraDeDistancia calculadoraDeDistancia;

  @Getter @Setter
  @Transient
  private CalculadoraDeDistanciaRetrofit calculadoraDeDistanciaRetrofit;

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

  public double huellaDeCarbonoDeDistancia(double distanciaRecorrida,
                                           UnidadEquivalenteCarbono unidadDeseada) {
    return combustible.calcularHuellaDeCarbono(
        distanciaRecorrida * combustiblePorKm, unidadDeseada);
  }
}