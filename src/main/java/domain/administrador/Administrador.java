package domain.administrador;

import static java.util.Objects.requireNonNull;

public class Administrador {
  String user;
  String password;
  PasswordValidator validador = PasswordValidator.getInstance();

  public Administrador(String user, String password) {
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    this.password = requireNonNull(password, "La contraseña no debe ser vacia!");
    if (validador.chequearValidez(password, user)) {
      this.password = password;
    }
  }
}