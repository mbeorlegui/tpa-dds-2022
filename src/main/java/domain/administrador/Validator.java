package domain.administrador;

public interface Validator {
  static final String PASSWORD = "La contrase�a ";

  public boolean validar(String password, String user);
}
// TODO: Revisar validacion