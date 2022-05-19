package domain.administrador;

public class PasswordHasOnlyLettersValidator extends Validator{
  static String ONLY_LETTERS_REGEX;
  public PasswordHasOnlyLettersValidator(String password, String user, String errorMessage, String ONLY_LETTERS_REGEX) {
    super(password, user, errorMessage);
    this.ONLY_LETTERS_REGEX = ONLY_LETTERS_REGEX;
  }

  @Override
  public void validate() {
    if (password.matches(ONLY_LETTERS_REGEX)) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
