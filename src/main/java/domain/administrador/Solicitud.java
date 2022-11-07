package domain.administrador;

import domain.miembro.Miembro;
import domain.organizacion.Sector;
import lombok.Getter;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "solicitud")
public class Solicitud {
  @Getter
  @Id
  @GeneratedValue
  @Column(name = "solicitud_id")
  private long id;
  @Getter
  @ManyToOne
  @JoinColumn(name = "sector_id")
  private Sector sector;
  @Getter
  @ManyToOne
  @JoinColumn(name = "miembro_id")
  private Miembro miembro;
  @Getter
  private String motivo;

  public Solicitud() {
  }

  public Solicitud(Sector sector, Miembro miembro, String motivo) {
    this.sector = sector;
    this.miembro = miembro;
    this.motivo = motivo;
  }
}