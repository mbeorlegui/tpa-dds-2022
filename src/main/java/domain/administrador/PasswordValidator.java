package domain.administrador;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
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

  FileHandler fileReader = new FileHandler();
  private List<Validator> validadores = new ArrayList<Validator>(); // TODO: Revisar validacion

  private boolean passwordEsComun(String unPassword) {
    return fileReader.palabraEstaEnArchivo(unPassword);
  }

  // TODO: Revisar validacion
  public void validarPassword(String password, String user) {
    if (password == null) {
      throw new NullPointerException(PASSWORD + EMPTY_PASSWORD_ERROR);
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
  }

  public void crearValidadores(String password, String user){
    validadores.add(new PasswordNullValidator(password, user, PASSWORD + EMPTY_PASSWORD_ERROR));
    validadores.add(new PasswordMinLengthValidator(password, user, PASSWORD + MIN_LENGTH_ERROR, MIN_LENGTH));
    validadores.add(new PasswordMaxLengthValidator(password, user, PASSWORD + MAX_LENGTH_ERROR, MAX_LENGTH));
    validadores.add(new PasswordContainsUserValidator(password, user, PASSWORD + USER_PASSWORD_ERROR));
    validadores.add(new PasswordMatchesInvalidCharactersValidator(password, user, PASSWORD + INVALID_CHARACTERS_ERROR, VALID_CHARACTERS_REGEX));
    validadores.add(new PasswordHasOnlyLettersValidator(password, user, PASSWORD + ONLY_LETTERS_ERROR, ONLY_LETTERS_REGEX));
    validadores.add(new PasswordHasOnlyNumbersValidator(password, user, PASSWORD + ONLY_NUMBERS_ERROR, ONLY_NUMBERS_REGEX));
    validadores.add(new PasswordHasOnlyNumbersAndLettersValidator(password, user, PASSWORD + ONLY_NUMBERS_AND_LETTERS_ERROR, ONLY_NUMBERS_AND_LETTERS_REGEX));
    validadores.add(new CommonPasswordValidator(password, user, PASSWORD + COMMON_PASSWORD_ERROR, fileReader));
  }

  // TODO: Revisar validacion
  public void validarPassword_2(String password, String user) {

    for (Validator v : this.validadores) {
      v.validate();
    }
  }

}