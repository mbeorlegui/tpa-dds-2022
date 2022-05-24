package domain.administrador;

import domain.exceptions.InvalidPasswordException;

public class PasswordMinLengthValidator extends Validator {
  static final int MIN_LENGTH = 8;
  static final String ERROR_MESSAGE = "debe tener más de 8 caracteres!";

  public PasswordMinLengthValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (password.length() < MIN_LENGTH) {
      throw new InvalidPasswordException(PASSWORD + ERROR_MESSAGE);
    }
  }
}
