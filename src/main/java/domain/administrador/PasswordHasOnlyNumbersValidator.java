package domain.administrador;

import domain.exceptions.InvalidPasswordException;

public class PasswordHasOnlyNumbersValidator extends Validator {
  static final String ONLY_NUMBERS_REGEX = "[0-9]*";
  static final String ERROR_MESSAGE =
      "solo contiene numeros, debe agregar letras y caracteres especiales (_%^&*()!@/#=+¡,;)!";

  public PasswordHasOnlyNumbersValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (password.matches(ONLY_NUMBERS_REGEX)) {
      throw new InvalidPasswordException(PASSWORD + ERROR_MESSAGE);
    }
  }
}
