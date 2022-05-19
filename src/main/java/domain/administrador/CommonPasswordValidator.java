package domain.administrador;

public class CommonPasswordValidator extends Validator {
  static final String ERROR_MESSAGE = "se encuentra dentro de las mas comunes!";
  FileHandler fileReader = new FileHandler();

  public CommonPasswordValidator(String password, String user) {
    super(password, user);
  }

  @Override
  public void ejecutarValidacion() {
    if (fileReader.palabraEstaEnArchivo(password)) {
      throw new IllegalArgumentException(PASSWORD + ERROR_MESSAGE);
    }
  }
}
