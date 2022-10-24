package domain.administrador;

import domain.medicion.Actividad;
import domain.medicion.Alcance;
import domain.medicion.Unidad;
import domain.medicion.TipoConsumo;
import domain.medicion.RepoTiposConsumos;

import lombok.Getter;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "usuario")
public class Usuario {
  @Id
  @GeneratedValue
  @Column(name = "administrador_id")
  private long id;
  @Getter
  String user;
  @Getter
  String password;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_usuario")
  @Getter
  private TipoUsuario tipoUsuario;
  @Transient
  PasswordValidator validador = new PasswordValidator();

  public Usuario(String user, String password, TipoUsuario tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    validador.validarPassword(password, user);
    this.password = password;
  }

  public void generarNuevoFactorDeEmision(Actividad actividad,
                                          Alcance alcance,
                                          Unidad unidad,
                                          FactorDeEmision factorDeEmision,
                                          String nombreTipoDeConsumo) {
    RepoTiposConsumos.getInstance().agregarTiposDeConsumo(
        new TipoConsumo(actividad, alcance, unidad, factorDeEmision, nombreTipoDeConsumo)
    );
  }

}