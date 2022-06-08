package domain.services.apidistancias.entities;

import lombok.Getter;
import lombok.Setter;

public class ResultadoDistancia {
  @Getter
  private double valor;
  @Getter @Setter
  private String unidad;
}
