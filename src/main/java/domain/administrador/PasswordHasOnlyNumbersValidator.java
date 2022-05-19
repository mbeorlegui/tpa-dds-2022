package domain.administrador;

public class PasswordHasOnlyNumbersValidator extends Validator{
  static String ONLY_NUMBERS_REGEX;
  public PasswordHasOnlyNumbersValidator(String password, String user, String errorMessage, String ONLY_NUMBERS_REGEX) {
    super(password, user, errorMessage);
    this.ONLY_NUMBERS_REGEX = ONLY_NUMBERS_REGEX;
  }

  @Override
  public void validate() {
    if (password.matches(ONLY_NUMBERS_REGEX)) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
