package domain.medios;

public interface MedioDeComunicacion {
  void enviarNotificacion(String link, Contacto contacto);
  // TODO: testear mockeando la interfaz
}
