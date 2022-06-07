package domain.exceptions;

public class NoPuedeSerTrayectoCompartidoException extends RuntimeException {
  public NoPuedeSerTrayectoCompartidoException(String message) {
    super(message);
  }
}
