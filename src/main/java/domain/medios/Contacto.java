package domain.medios;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
@Table(name = "contacto")
public class Contacto {
  @Id
  @GeneratedValue
  @Column(name = "ubicacion_id")
  private long id;
  @Getter
  String nombre;
  @Getter
  String email;
  @Getter
  @Column(name = "numero_de_whatsapp")
  Integer numeroDeWhatsapp;

  public Contacto(String nombre, String email, Integer numeroDeWhatsapp) {
    this.nombre = nombre;
    this.email = email;
    this.numeroDeWhatsapp = numeroDeWhatsapp;
  }
}