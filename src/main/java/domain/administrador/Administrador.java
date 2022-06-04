package domain.administrador;

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

}