package domain.administrador;

import domain.miembro.Miembro;
import domain.organizacion.Sector;
import lombok.Getter;

import javax.persistence.*;

import java.time.LocalDateTime;

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
  @OneToOne
  @JoinColumn(name = "sector_id")
  private Sector sector;
  @Getter
  @OneToOne
  @JoinColumn(name = "miembro_id")
  private Miembro miembro;
  @Getter
  private String motivo;
  @Getter
  @Column(name = "fecha_generacion")
  private LocalDateTime fechaGeneracion;
  @Getter
  @Enumerated(EnumType.STRING)
  @Column(name = "estado_solicitud")
  private EstadoSolicitud estadoSolicitud;

  public Solicitud() {
  }

  public Solicitud(Sector sector, Miembro miembro, String motivo, LocalDateTime fechaGeneracion) {
    this.sector = sector;
    this.miembro = miembro;
    this.motivo = motivo;
    this.fechaGeneracion = fechaGeneracion;
    this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
  }

  public boolean estaPendiente() {
    return this.getEstadoSolicitud().equals(EstadoSolicitud.PENDIENTE);
  }

  public void aceptar() {
    this.sector.addMiembro(this.miembro);
    this.estadoSolicitud = EstadoSolicitud.COMPLETADA;
  }

  public void rechazar() {
    this.estadoSolicitud = EstadoSolicitud.COMPLETADA;
  }
}