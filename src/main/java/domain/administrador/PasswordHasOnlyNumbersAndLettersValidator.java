package domain.administrador;

public class PasswordHasOnlyNumbersAndLettersValidator extends Validator{
  static String ONLY_NUMBERS_AND_LETTERS_REGEX;
  public PasswordHasOnlyNumbersAndLettersValidator(String password, String user, String errorMessage, String ONLY_NUMBERS_AND_LETTERS_REGEX) {
    super(password, user, errorMessage);
    this.ONLY_NUMBERS_AND_LETTERS_REGEX = ONLY_NUMBERS_AND_LETTERS_REGEX;
  }

  @Override
  public void validate() {
    if (password.matches(ONLY_NUMBERS_AND_LETTERS_REGEX)) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}

