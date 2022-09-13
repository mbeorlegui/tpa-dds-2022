package domain.administrador;

import domain.medicion.Actividad;
import domain.medicion.Alcance;
import domain.medicion.TiposConsumos;
import domain.medicion.TipoConsumo;
import domain.medicion.Unidad;

import lombok.Getter;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "administrador")
public class Administrador {
  @Id
  @GeneratedValue
  @Column(name = "administrador_id")
  private long id;
  @Getter
  String user;
  @Getter
  String password;
  @Transient
  PasswordValidator validador = new PasswordValidator();

  public Administrador(String user, String password) {
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    validador.validarPassword(password, user);
    this.password = password;
  }

  public void generarNuevoFactorDeEmision(Actividad actividad,
                                          Alcance alcance,
                                          Unidad unidad,
                                          FactorDeEmision factorDeEmision,
                                          String nombreTipoDeConsumo) {
    TiposConsumos.getInstance().agregarTiposDeConsumo(
        new TipoConsumo(actividad, alcance, unidad, factorDeEmision, nombreTipoDeConsumo)
    );
  }

}