package domain.trayecto;


import domain.administrador.UnidadEquivalenteCarbono;
import domain.services.apidistancias.CalculadoraDeDistancia;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tramo")
public class Tramo {
  @Id
  @GeneratedValue
  @Column(name = "tramo_id")
  private long id;
  @Getter
  @ManyToOne
  @JoinColumn(name = "ubicacion_origen_id")
  private Ubicacion origenDeTramo;
  @Getter
  @ManyToOne
  @JoinColumn(name = "ubicacion_destino_id")
  private Ubicacion destinoDeTramo;
  @Getter
  @ManyToOne
  @JoinColumn(name = "transporte_id")
  private Transporte transporteUtilizado;

  public Tramo(Ubicacion origenDeTramo, Ubicacion destinoDeTramo, Transporte transporteUtilizado) {
    transporteUtilizado.verificarParadas(origenDeTramo, destinoDeTramo);
    this.origenDeTramo = origenDeTramo;
    this.destinoDeTramo = destinoDeTramo;
    this.transporteUtilizado = transporteUtilizado;
  }

  public boolean esMismoTramo(Tramo unTramo) {
    return (this.origenDeTramo.esMismaUbicacionQue(unTramo.getOrigenDeTramo())
        && this.destinoDeTramo.esMismaUbicacionQue(unTramo.getDestinoDeTramo())
        && this.transporteUtilizado.esDeTipo(unTramo.getTransporteUtilizado().getTipoTransporte()));
  }

  public boolean puedeSerCompartido() {
    return this.transporteUtilizado.esDeTipo(TipoTransporte.PARTICULAR)
        || this.transporteUtilizado.esDeTipo(TipoTransporte.CONTRATADO);
  }

  public double distanciaIntermedia() {
    return this.getTransporteUtilizado().calcularDistancia(origenDeTramo, destinoDeTramo);
  }

  public double huellaDeCarbono(UnidadEquivalenteCarbono unidadDeseada) {
    return this.transporteUtilizado
        .huellaDeCarbonoDeDistancia(this.distanciaIntermedia(), unidadDeseada);
  }
}