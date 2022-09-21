package domain.transporte;

import domain.services.apidistancias.entities.ResultadoDistancia;
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
import javax.persistence.Transient;

@Entity
@Table(name = "parada")
public class Parada {
  @Id
  @GeneratedValue
  @Column(name = "parada_id")
  private long id;
  @Getter
  @ManyToOne
  // Ya que varias paradas comparten ubicacion pero no necesariamente distancia a la siguiente
  @JoinColumn(name = "ubicacion_id")
  Ubicacion ubicacion;
  @Setter
  @Transient
  ResultadoDistancia distanciaSiguienteParada;

  public Parada(Ubicacion ubicacion, ResultadoDistancia distanciaSiguienteParada) {
    this.ubicacion = ubicacion;
    this.distanciaSiguienteParada = distanciaSiguienteParada;
  }

  public double getDistanciaSiguienteParada() {
    return distanciaSiguienteParada.obtenerKilometros();
  }

  public String getUnidadDistancia() {
    return distanciaSiguienteParada.getUnidad();
  }

  public void modificarDistanciaSiguienteParada(Parada nuevaParada) {
    ResultadoDistancia nuevaDistacia = new ResultadoDistancia(
        this.getDistanciaSiguienteParada() - nuevaParada.getDistanciaSiguienteParada(),
        "KM");
    this.setDistanciaSiguienteParada(nuevaDistacia);
  }
}
