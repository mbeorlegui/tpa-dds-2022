package domain.administrador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Utilizamos un singleton, ya que solamente queremos una instancia de validador
// que funciona auxiliarmente como chequeador de contraseñas y nada mas
public class PasswordValidator {
  // Este objeto abre el archivo y contrasta con todas esas palabras cada
  // vez que se requiera chequear la validez de las contraseñas
  private static final PasswordValidator INSTANCE = new PasswordValidator();
  static final int MAX_LENGTH = 64;
  static final int MIN_LENGTH = 8;
  static final String VALID_CHARACTERS_REGEX = "[a-zA-Z0-9_%^&*()!@/#=+¡,;]*";
  static final String ONLY_LETTERS_REGEX = "[a-zA-Z]*";
  static final String ONLY_NUMBERS_REGEX = "[0-9]*";
  static final String ONLY_NUMBERS_AND_LETTERS_REGEX = "[a-zA-Z0-9]*";
  static final String PASSWORD = "La contraseña ";
  static final String MIN_LENGTH_ERROR = "debe tener más de 8 caracteres!";
  static final String MAX_LENGTH_ERROR = "debe tener 64 caracteres o menos!";
  static final String COMMON_PASSWORD_ERROR = "se encuentra dentro de las mas comunes!";
  static final String USER_PASSWORD_ERROR = "no debe contener el nombre de usuario!";
  static final String INVALID_CHARACTERS_ERROR = "contiene caracteres invalidos!";
  static final String EMPTY_PASSWORD_ERROR = "no puede ser vacia!";
  static final String ONLY_LETTERS_ERROR =
      "solo contiene letras, debe agregar numeros y caracteres especiales (_%^&*()!@/#=+¡,;)!";
  static final String ONLY_NUMBERS_ERROR =
      "solo contiene numeros, debe agregar letras y caracteres especiales (_%^&*()!@/#=+¡,;)!";
  static final String ONLY_NUMBERS_AND_LETTERS_ERROR =
      "debe tener caracteres especiales (_%^&*()!@/#=+¡,;)!";
  static final String COMMON_PASSWORDS_LOCATION = "data/commonly_used_passwords.txt";

  public static PasswordValidator getInstance() {
    return INSTANCE;
  }

  private boolean passwordEsComun(String unPassword) {
    try {
      Scanner unArchivo = new Scanner(new File(COMMON_PASSWORDS_LOCATION), "UTF-8");
      return palabraEstaEnArchivo(unPassword, unArchivo);

    } catch (FileNotFoundException e) {
      System.out.println(
          "Error al abrir archivo, verifique que el mismo se encuentre en "
              + COMMON_PASSWORDS_LOCATION);
      e.printStackTrace();
      return false;
    }
  }

  private boolean palabraEstaEnArchivo(String unPassword, Scanner unArchivo) {
    while (unArchivo.hasNextLine()) {
      String line = unArchivo.nextLine();
      if (line.equals(unPassword)) {
        return true;
      }
    }
    return false;
  }

  public Boolean passwordEsValido(String password, String user) {
    if (password == null) {
      throw new IllegalArgumentException(PASSWORD + EMPTY_PASSWORD_ERROR);
    }
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
    if (password.matches(ONLY_LETTERS_REGEX)) {
      throw new IllegalArgumentException(PASSWORD + ONLY_LETTERS_ERROR);
    }
    if (password.matches(ONLY_NUMBERS_REGEX)) {
      throw new IllegalArgumentException(PASSWORD + ONLY_NUMBERS_ERROR);
    }
    if (password.matches(ONLY_NUMBERS_AND_LETTERS_REGEX)) {
      throw new IllegalArgumentException(PASSWORD + ONLY_NUMBERS_AND_LETTERS_ERROR);
    }
    if (passwordEsComun(password)) {
      throw new IllegalArgumentException(PASSWORD + COMMON_PASSWORD_ERROR);
    }
    return true;
  }
}