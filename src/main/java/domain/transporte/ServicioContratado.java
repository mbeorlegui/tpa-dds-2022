package domain.transporte;

import domain.medicion.TipoConsumo;
import domain.ubicacion.Ubicacion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "servicio_contratado")
public class ServicioContratado extends Transporte {
  @Enumerated
  @Column(name = "tipo_de_servicio_contratado")
  private TipoDeServicioContratado tipoDeServicioContratado;

  @Override
  public void verificarParadas(Ubicacion origen, Ubicacion destino) {

  }

  public ServicioContratado(TipoConsumo combustible, Double combustiblePorKm,
                            TipoDeServicioContratado tipoDeServicioContratado) {
    super(combustible, combustiblePorKm);
    this.tipoDeServicioContratado = tipoDeServicioContratado;
    setTipoTransporte(TipoTransporte.CONTRATADO);
  }

  public TipoDeServicioContratado getTipoDeServicioContratado() {
    return tipoDeServicioContratado;
  }

}
