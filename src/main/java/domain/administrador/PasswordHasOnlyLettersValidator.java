package domain.administrador;

import domain.exceptions.InvalidPasswordException;

public class PasswordHasOnlyLettersValidator extends Validator {
  static final String ONLY_LETTERS_REGEX = "[a-zA-Z]*";
  static final String ERROR_MESSAGE =
      "solo contiene letras, debe agregar numeros y caracteres especiales (_%^&*()!@/#=+¡,;)!";

  public PasswordHasOnlyLettersValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (password.matches(ONLY_LETTERS_REGEX)) {
      throw new InvalidPasswordException(PASSWORD + ERROR_MESSAGE);
    }
  }
}
