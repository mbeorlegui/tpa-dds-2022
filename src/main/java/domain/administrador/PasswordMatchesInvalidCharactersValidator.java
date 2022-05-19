package domain.administrador;

public class PasswordMatchesInvalidCharactersValidator extends Validator{
  static String VALID_CHARACTERS_REGEX;
  public PasswordMatchesInvalidCharactersValidator(String password, String user, String errorMessage, String VALID_CHARACTERS_REGEX) {
    super(password, user, errorMessage);
    this.VALID_CHARACTERS_REGEX = VALID_CHARACTERS_REGEX;
  }

  @Override
  public void validate() {
    if (!password.matches(VALID_CHARACTERS_REGEX)) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
