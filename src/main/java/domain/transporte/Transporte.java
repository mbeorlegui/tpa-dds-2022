package domain.transporte;

public interface Transporte {

  public TipoTransporte getTipoTransporte();

  public void setTipoTransporte(TipoTransporte tipoTransporte);

  public boolean esMismoTipoDeTransporteQue(Transporte unTransporte);
}
