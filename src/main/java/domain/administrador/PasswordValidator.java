package domain.administrador;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

  private List<Validator> validadores = new ArrayList<Validator>();

  public void validarPassword(String password, String user) {
    this.crearValidadores(password, user);
    this.ejecutarValidaciones();
  }

  public void crearValidadores(String password, String user) {
    validadores.add(new PasswordNullValidator(password, user));
    validadores.add(new PasswordMinLengthValidator(password, user));
    validadores.add(new PasswordMaxLengthValidator(password, user));
    validadores.add(new PasswordContainsUserValidator(password, user));
    validadores.add(new PasswordMatchesInvalidCharactersValidator(password, user));
    validadores.add(new PasswordHasOnlyLettersValidator(password, user));
    validadores.add(new PasswordHasOnlyNumbersValidator(password, user));
    validadores.add(new PasswordHasOnlyNumbersAndLettersValidator(password, user));
    validadores.add(new CommonPasswordValidator(password, user));
  }

  public void ejecutarValidaciones() {
    for (Validator v : this.validadores) {
      v.ejecutarValidacion();
    }
  }

}