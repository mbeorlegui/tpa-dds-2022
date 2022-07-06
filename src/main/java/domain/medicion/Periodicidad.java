package domain.medicion;

public enum Periodicidad {
  MENSUAL {
    @Override
    public int diasTrabajados() {return 20;}
  },
  ANUAL{
    @Override
    public int diasTrabajados() {return 20 * 12;}
  };

  public int diasTrabajados() {return 0;}
}
// TODO: Analizar refactor para el calculo de huella de carbono -> Strategy