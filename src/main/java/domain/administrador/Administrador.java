package domain.administrador;

import static java.util.Objects.requireNonNull;


public class Administrador {
  String user;
  String password;
  PasswordValidator validador = PasswordValidator.getInstance();

  public Administrador(String user, String password) {
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    if (validador.passwordEsValido(password, user)) {
      this.password = password;
    }
  }
}