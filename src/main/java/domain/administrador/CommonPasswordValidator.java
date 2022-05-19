package domain.administrador;

public class CommonPasswordValidator extends Validator{
  FileHandler fileReader;
  public CommonPasswordValidator(String password, String user, String errorMessage, FileHandler fileReader) {
    super(password, user, errorMessage);
    this.fileReader = fileReader;
  }

  @Override
  public void validate() {
    if (fileReader.palabraEstaEnArchivo(password)) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}
