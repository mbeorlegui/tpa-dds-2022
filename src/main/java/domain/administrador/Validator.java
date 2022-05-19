package domain.administrador;

public abstract class Validator {
  static String password;
  static String user;
  static String errorMessage;

  public Validator(String password, String user, String errorMessage){
    this.password = password;
    this.user = user;
    this.errorMessage = errorMessage;

  }
  public abstract void validate();
}
// TODO: Revisar validacion