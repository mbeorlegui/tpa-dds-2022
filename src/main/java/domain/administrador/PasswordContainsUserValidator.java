package domain.administrador;

public class PasswordContainsUserValidator extends Validator {
  static final String ERROR_MESSAGE = "no debe contener el nombre de usuario!";

  public PasswordContainsUserValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (password.contains(user)) {
      throw new IllegalArgumentException(PASSWORD + ERROR_MESSAGE);
    }
  }
}
