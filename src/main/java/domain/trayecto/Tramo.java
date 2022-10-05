package domain.trayecto;


import domain.administrador.UnidadEquivalenteCarbono;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Table(name = "tramo")
public class Tramo {
  @Id
  @GeneratedValue
  @Column(name = "tramo_id")
  private long id;
  @Getter
  @AttributeOverrides({
      @AttributeOverride(name = "localidadID", column = @Column(name = "origen_localidad_id")),
      @AttributeOverride(name = "altura", column = @Column(name = "origen_altura")),
      @AttributeOverride(name = "calle", column = @Column(name = "origen_calle"))
  })
  @Embedded private Ubicacion origenDeTramo;
  @Getter
  @AttributeOverrides({
      @AttributeOverride(name = "localidadID", column = @Column(name = "destino_localidad_id")),
      @AttributeOverride(name = "altura", column = @Column(name = "destino_altura")),
      @AttributeOverride(name = "calle", column = @Column(name = "destino_calle"))
  })
  @Embedded private Ubicacion destinoDeTramo;

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