package domain.administrador;

import lombok.Getter;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminador", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
  @Id
  @GeneratedValue
  @Column(name = "usuario_id")
  private long id;
  @Getter
  String user;
  @Getter
  String password;
  /*
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_usuario")
  @Getter
  private TipoUsuario tipoUsuario;
   */
  @Transient
  PasswordValidator validador = new PasswordValidator();

  public Usuario() {
  }

  /*
  public Usuario(String user, String password, TipoUsuario tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    validador.validarPassword(password, user);
    this.password = password;
  }
   */

  public Usuario(String user, String password) {
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    validador.validarPassword(password, user);
    this.password = password;
  }

  /* Esto es una accion de un actor, no un metodo de una clase
  public void generarNuevoFactorDeEmision(Actividad actividad,
                                          Alcance alcance,
                                          Unidad unidad,
                                          FactorDeEmision factorDeEmision,
                                          String nombreTipoDeConsumo) {
    RepoTiposConsumos.getInstance().agregarTiposDeConsumo(
        new TipoConsumo(actividad, alcance, unidad, factorDeEmision, nombreTipoDeConsumo)
    );
  }

   */

}