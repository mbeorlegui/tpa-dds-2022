package domain.medios;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
@Table(name = "medio_de_comunicacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminador", discriminatorType = DiscriminatorType.STRING)
public abstract class MedioDeComunicacion {
  @Id
  @GeneratedValue
  @Column(name = "medio_de_comunicacion_id")
  private long id;

  public abstract void enviarNotificacion(String link, Contacto contacto);
}
