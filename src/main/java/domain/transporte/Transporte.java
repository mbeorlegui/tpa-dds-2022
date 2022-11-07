package domain.transporte;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.TipoConsumo;
import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Transient;
import javax.persistence.Enumerated;
import javax.persistence.DiscriminatorType;
import javax.persistence.InheritanceType;

@Entity
@Table(name = "transporte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminador", discriminatorType = DiscriminatorType.STRING)
public abstract class Transporte {
  @Id
  @GeneratedValue
  @Getter
  @Column(name = "transporte_id")
  private long id;
  @Getter
  @ManyToOne
  @JoinColumn(name = "combustible_tipo_consumo_id")
  public TipoConsumo combustible;
  @Getter
  @Column(name = "combustible_por_km")
  public Double combustiblePorKm;
  @Getter
  @Setter
  @Enumerated
  @Column(name = "tipo_transporte")
  public TipoTransporte tipoTransporte;
  @Getter
  @Setter
  @Transient
  private CalculadoraDeDistancia calculadoraDeDistancia;

  public Transporte(){
  }

  public Transporte(TipoConsumo combustible, Double combustiblePorKm) {
    this.combustible = combustible;
    this.combustiblePorKm = combustiblePorKm;
  }

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