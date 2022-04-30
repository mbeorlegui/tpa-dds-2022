package domain.administrador;

import java.io.FileNotFoundException;

import static java.util.Objects.requireNonNull;

public class Administrador {
  String user;
  String password;
  PasswordValidator validador = PasswordValidator.getInstance();

  public Administrador(String user, String password) throws FileNotFoundException {
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    if (validador.chequearValidez(password, user)) {
      this.password = password;
    }
  }
}