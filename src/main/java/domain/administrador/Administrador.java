package domain.administrador;

import domain.organizacion.Organizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "administrador")
public class Administrador extends Usuario {

  @Getter
  @Setter
  @OneToOne
  // @Column(name = "organizacion_asociada")
  @JoinColumn(name = "organizacion_asociada_id")
  private Organizacion organizacionAsociada;

  public Administrador(String user, String password, Organizacion organizacionAsociada) {
    super(user, password);
    this.organizacionAsociada = organizacionAsociada;
  }

  public Administrador() {
  }
}
