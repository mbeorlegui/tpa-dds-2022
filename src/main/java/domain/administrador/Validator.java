package domain.administrador;

public abstract class Validator {
  String password;
  String user;
  static final String PASSWORD = "La contraseña ";

  public Validator(String password, String user) {
    this.password = password;
    this.user = user;
  }

  public abstract void ejecutarValidacion();
}