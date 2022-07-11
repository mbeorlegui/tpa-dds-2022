package domain.medios;

import lombok.Getter;

public class Contacto {
  @Getter
  String nombre;
  @Getter
  String email;
  @Getter
  Integer numeroDeWhatsapp;

  public Contacto(String nombre, String email, Integer numeroDeWhatsapp) {
    this.nombre = nombre;
    this.email = email;
    this.numeroDeWhatsapp = numeroDeWhatsapp;
  }
}