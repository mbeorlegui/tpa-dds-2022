package domain.reports;

public class ReporteDeComposicion {
  @SuppressWarnings({"FieldCanBeLocal", "unused"})
  private final double hcMediciones;
  @SuppressWarnings({"FieldCanBeLocal", "unused"})
  private final double hcTrayectos;

  public ReporteDeComposicion(double hcMediciones, double hcTrayectos) {
    this.hcMediciones = hcMediciones;
    this.hcTrayectos = hcTrayectos;
  }

  public boolean tieneMismoHC(ReporteDeComposicion otroReporte) {
    return otroReporte.hcMediciones == this.hcMediciones
        && otroReporte.hcTrayectos == this.hcTrayectos;
  }
}
