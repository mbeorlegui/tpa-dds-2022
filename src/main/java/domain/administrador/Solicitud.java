package domain.administrador;

import domain.organizacion.Sector;
import lombok.Getter;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "solicitud")
public class Solicitud {
  @Id
  @GeneratedValue
  @Column(name = "solicitud_id")
  private long id;
  @Transient
  private Sector sector;
  @Getter
  private String motivo;

  public Solicitud() {
  }

  public Solicitud(Sector sector, String motivo) {
    this.sector = sector;
    this.motivo = motivo;
  }
}