package domain.administrador;

public class PasswordNullValidator extends Validator{


  public PasswordNullValidator(String password, String user, String errorMessage) {
    super(password, user, errorMessage);
  }

  @Override
  public void validate() {
    if (this.password == null){
      throw new NullPointerException(errorMessage);
    }
  }

}
