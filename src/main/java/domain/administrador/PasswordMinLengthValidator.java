package domain.administrador;

public class PasswordMinLengthValidator extends Validator{
  static int MIN_LENGTH;
  public PasswordMinLengthValidator(String password, String user, String errorMessage, Integer MIN_LENGTH) {
    super(password, user, errorMessage);
    this.MIN_LENGTH = MIN_LENGTH;
  }

  @Override
  public void validate() {
    if (password.length() < MIN_LENGTH){
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
