package domain.administrador;

import domain.exceptions.InvalidPasswordException;

public class PasswordMaxLengthValidator extends Validator {
  static final int MAX_LENGTH = 64;
  static final String ERROR_MESSAGE = "debe tener 64 caracteres o menos!";

  public PasswordMaxLengthValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (password.length() > MAX_LENGTH) {
      throw new InvalidPasswordException(PASSWORD + ERROR_MESSAGE);
    }
  }
}
