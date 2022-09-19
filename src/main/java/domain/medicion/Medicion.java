package domain.medicion;

import domain.administrador.UnidadEquivalenteCarbono;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Enumerated;

@Entity
@Table(name = "medicion")
public class Medicion {
  @Id
  @GeneratedValue
  @Column(name = "medicion_id")
  private long id;
  @Getter
  @ManyToOne
  @JoinColumn(name = "tipo_consumo_id")
  private TipoConsumo tipoConsumo;
  @Getter
  private Integer valor;
  @Getter
  @Enumerated
  private Periodicidad periodicidad;
  @Getter
  @Column(name = "periodo_de_imputacion")
  private String periodoDeImputacion;

  public Medicion(TipoConsumo tipoConsumo,
                  Integer valor,
                  Periodicidad periodicidad,
                  String periodoDeImputacion) {
    this.tipoConsumo = tipoConsumo;
    this.valor = valor;
    this.periodicidad = periodicidad;
    this.periodoDeImputacion = periodoDeImputacion;
  }

  public String toString() {
    return "Medicion [Tipo de consumo: " + tipoConsumo
        + ", Valor: " + valor + ", Periodicidad: " + periodicidad
        + ", Periodo de imputacion: " + periodoDeImputacion + "]";
  }

  public boolean esMedicionIdentica(Medicion unaMedicion) {
    return (this.tipoConsumo == unaMedicion.getTipoConsumo()
        && this.valor.equals(unaMedicion.getValor())
        && this.periodicidad == unaMedicion.getPeriodicidad()
        && this.periodoDeImputacion.equals(unaMedicion.getPeriodoDeImputacion()));
  }

  public boolean esDePeriodo(Periodicidad periodicidad, String periodoDeImputacion) {
    return this.periodicidad.equals(periodicidad)
        && this.periodoDeImputacion.equals(periodoDeImputacion);
  }

  public double huellaDeCarbono(UnidadEquivalenteCarbono unidadDeseada) {
    return this.tipoConsumo.calcularHuellaDeCarbono(this.valor, unidadDeseada);
  }
}
