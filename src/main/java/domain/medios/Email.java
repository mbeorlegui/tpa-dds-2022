package domain.medios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "email")
public class Email extends MedioDeComunicacion {
  @Override
  public void enviarNotificacion(String link, Contacto contacto) {
    throw new UnsupportedOperationException("No se ha implementado esta funcionalidad");
    // TODO: Implementar
  }
}
