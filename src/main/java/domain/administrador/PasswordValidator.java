package domain.administrador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

// Utilizamos un singleton, ya que solamente queremos una instancia de validador
// que funciona auxiliarmente como chequeador de contraseñas y nada mas
public class PasswordValidator {
  // Este objeto deberia almacenar todas las contraseñas comunes,
  // o abrir el archivo y contrastar con todas esas palabras cada
  // vez que se requiera chequear la validez de las contraseñas
  private static final PasswordValidator INSTANCE = new PasswordValidator();
  static final String VALID_CHARACTERS_REGEX = "[a-zA-Z0-9_%^&*()!@/#=+¡,;]*";
  // final String TOO_SIMPLE_REGEX = "[a-zA-Z0-9]*";
  static final int MAX_LENGTH = 64;
  static final int MIN_LENGTH = 8;
  static final String PASSWORD = "La contraseña ";
  static final String MIN_LENGTH_ERROR = "debe tener más de 8 caracteres!";
  static final String MAX_LENGTH_ERROR = "debe tener 64 caracteres o menos!";
  static final String COMMON_PASSWORD_ERROR = "se encuentra dentro de las mas comunes!";
  static final String USER_PASSWORD_ERROR = "no debe contener el nombre de usuario!";
  static final String INVALID_CHARACTERS_ERROR = "contiene caracteres invalidos!";
  static final String COMMON_PASSWORDS_LOCATION = "../../../data/commonly_used_passwords.txt";

  public static PasswordValidator getInstance() {
    return INSTANCE;
  }

  public PasswordValidator() {
  }

  public boolean palabraEsComun(String unaPalabra) throws FileNotFoundException {
    Scanner txtscan = new Scanner(new File(COMMON_PASSWORDS_LOCATION));

    while (txtscan.hasNextLine()) {
      String str = txtscan.nextLine();
      if (str.contains(unaPalabra)) {
        System.out.println("EXISTE");
        return true;
      }
    }
    return false;
  }

  public Boolean chequearValidez(String password, String user) throws FileNotFoundException {
    if (password.length() < MIN_LENGTH) {
      throw new IllegalArgumentException(PASSWORD + MIN_LENGTH_ERROR);
    }
    if (password.length() > MAX_LENGTH) {
      throw new IllegalArgumentException(PASSWORD + MAX_LENGTH_ERROR);
    }
    if (password.contains(user)) {
      throw new IllegalArgumentException(PASSWORD + USER_PASSWORD_ERROR);
    }
    if (!password.matches(VALID_CHARACTERS_REGEX)) {
      throw new IllegalArgumentException(PASSWORD + INVALID_CHARACTERS_ERROR);
    }
//    if (this.palabraEsComun(password)) {
//      throw new IllegalArgumentException(PASSWORD + COMMON_PASSWORD_ERROR);  // TODO: Needs refactor
//    }
    return true;
  }
}
// Se instancia con:
//    validador = PasswordValidator.instance();