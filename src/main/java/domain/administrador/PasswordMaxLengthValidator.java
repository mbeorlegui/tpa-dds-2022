package domain.administrador;

public class PasswordMaxLengthValidator extends Validator{
  static int MAX_LENGTH;
  public PasswordMaxLengthValidator(String password, String user, String errorMessage, Integer MAX_LENGTH) {
    super(password, user, errorMessage);
    this.MAX_LENGTH = MAX_LENGTH;
  }

  @Override
  public void validate() {
    if (password.length() > MAX_LENGTH){
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
