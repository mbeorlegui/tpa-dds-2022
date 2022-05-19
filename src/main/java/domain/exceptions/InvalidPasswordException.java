package domain.exceptions;

public class InvalidPasswordException extends IllegalArgumentException {

  public InvalidPasswordException(String message) {
    super(message);
  }
  
}
