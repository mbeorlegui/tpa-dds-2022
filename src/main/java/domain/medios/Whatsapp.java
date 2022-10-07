package domain.medios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "whatsapp")
public class Whatsapp extends MedioDeComunicacion {
  @Override
  public void enviarNotificacion(String link, Contacto contacto) {
    throw new UnsupportedOperationException("No se ha implementado esta funcionalidad");
    // TODO: Implementar
  }
}
