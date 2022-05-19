package domain.administrador;

public class PasswordContainsUserValidator extends Validator{

  public PasswordContainsUserValidator(String password, String user, String errorMessage) {
    super(password, user, errorMessage);
  }

  @Override
  public void validate() {
    if (password.contains(user)){
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
