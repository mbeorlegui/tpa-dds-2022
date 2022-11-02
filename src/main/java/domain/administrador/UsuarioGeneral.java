package domain.administrador;

import domain.miembro.Miembro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "usuario_general")
public class UsuarioGeneral extends Usuario {

  @Getter
  @Setter
  @OneToOne
  // @Column(name = "miembro_asociado")
  @JoinColumn(name = "miembro_asociado_id")
  private Miembro miembroAsociado;

  public UsuarioGeneral(String user, String password, Miembro miembroAsociado) {
    super(user, password);
    this.miembroAsociado = miembroAsociado;
  }

  public UsuarioGeneral() {
  }
}
