package domain.exceptions;

public class NonMemberException extends RuntimeException {
  public NonMemberException(String message) {
    super(message);
  }
}
