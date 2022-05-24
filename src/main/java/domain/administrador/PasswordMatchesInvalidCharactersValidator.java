package domain.administrador;

import domain.exceptions.InvalidPasswordException;

public class PasswordMatchesInvalidCharactersValidator extends Validator {
  static final String VALID_CHARACTERS_REGEX = "[a-zA-Z0-9_%^&*()!@/#=+¡,;]*";
  static final String ERROR_MESSAGE = "contiene caracteres invalidos!";

  public PasswordMatchesInvalidCharactersValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (!password.matches(VALID_CHARACTERS_REGEX)) {
      throw new InvalidPasswordException(PASSWORD + ERROR_MESSAGE);
    }
  }
}
