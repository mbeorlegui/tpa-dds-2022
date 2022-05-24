package domain.administrador;

import domain.exceptions.InvalidPasswordException;

public class PasswordHasOnlyNumbersAndLettersValidator extends Validator {
  static final String ERROR_MESSAGE =
      "debe tener caracteres especiales (_%^&*()!@/#=+¡,;)!";
  static final String ONLY_NUMBERS_AND_LETTERS_REGEX = "[a-zA-Z0-9]*";

  public PasswordHasOnlyNumbersAndLettersValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (password.matches(ONLY_NUMBERS_AND_LETTERS_REGEX)) {
      throw new InvalidPasswordException(PASSWORD + ERROR_MESSAGE);
    }
  }
}

