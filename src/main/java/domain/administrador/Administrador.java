package domain.administrador;

import domain.medicion.TipoConsumo;
import lombok.Getter;

import static java.util.Objects.requireNonNull;

public class Administrador {
  @Getter
  String user;
  @Getter
  String password;
  PasswordValidator validador = new PasswordValidator();

  public Administrador(String user, String password) {
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    validador.validarPassword(password, user);
    this.password = password;
  }

  public FactorDeEmision generarNuevoFactorDeEmision(Integer factor, TipoConsumo tipoConsumo) {
    return new FactorDeEmision(factor, tipoConsumo);
  }

}