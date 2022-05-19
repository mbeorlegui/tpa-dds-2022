package domain.administrador;

public class PasswordNullValidator extends Validator {
  static final String ERROR_MESSAGE = "no puede ser vacia!";


  public PasswordNullValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (password == null) {
      throw new IllegalArgumentException(PASSWORD + ERROR_MESSAGE);
    }
  }

}
