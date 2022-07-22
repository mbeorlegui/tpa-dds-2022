package domain.medicion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TiposConsumos {
  public List<TipoConsumo> consumos = new ArrayList<>();
  private TipoConsumo gasNatural = new TipoConsumo(Actividad.COMBUSTION_FIJA,
      Alcance.EMISION_DIRECTA, Unidad.M3, 0.2, "GAS_NATURAL");
  private TipoConsumo dieselGasoil = new TipoConsumo(Actividad.COMBUSTION_FIJA,
      Alcance.EMISION_DIRECTA, Unidad.LT, 2.6, "DIESEL_GASOIL");
  private TipoConsumo nafta = new TipoConsumo(Actividad.COMBUSTION_FIJA,
      Alcance.EMISION_DIRECTA, Unidad.LT, 2.3, "NAFTA");
  private TipoConsumo carbon = new TipoConsumo(Actividad.COMBUSTION_FIJA,
      Alcance.EMISION_DIRECTA, Unidad.KG, 1.6, "CARBON");
  private TipoConsumo ccGasoil = new TipoConsumo(Actividad.COMBUSTION_MOVIL,
      Alcance.EMISION_DIRECTA, Unidad.LT, 2.7, "CC_GASOIL");
  private TipoConsumo ccNafta = new TipoConsumo(Actividad.COMBUSTION_MOVIL,
      Alcance.EMISION_DIRECTA, Unidad.LT, 2.4, "CC_NAFTA");
  private TipoConsumo electricidad = new TipoConsumo(Actividad.ELECTRICIDAD,
      Alcance.EMISION_INDIRECTA, Unidad.KWH, 0.4, "ELECTRICIDAD");
  private TipoConsumo medioDeTransporte = new TipoConsumo(Actividad.LOGISTICA,
      Alcance.OTRAS_EMISIONES, Unidad.SIN_UNIDAD, 0.1, "MEDIO_DE_TRANSPORTE");
  private TipoConsumo distanciaMedia = new TipoConsumo(Actividad.LOGISTICA,
      Alcance.OTRAS_EMISIONES, Unidad.KM, 1, "DISTANCIA_MEDIA_RECORRIDA");

  private static final TiposConsumos INSTANCE =
      new TiposConsumos();

  // TODO: chequear factor de emision, por ahora definido en 500

  private TiposConsumos() {
    agregarTiposDeConsumo(gasNatural, dieselGasoil, nafta,
                          carbon, ccGasoil, ccNafta, electricidad, 
                          medioDeTransporte, distanciaMedia);
  }
  // TODO: Mejorar el init que está feo

  public void agregarTiposDeConsumo(TipoConsumo... tiposConsumo) {
    this.consumos.addAll(Arrays.asList(tiposConsumo));
  }

  public static TiposConsumos getInstance() {
    return INSTANCE;
  }

  public TipoConsumo hayarTipo(String nombre) {
    return this.consumos.stream().filter(unConsumo -> unConsumo.toString().equals(nombre))
        .findFirst().get();
  }

  public boolean existeTipo(String nombre) {
    return this.consumos.stream().anyMatch(unConsumo -> unConsumo.toString().equals(nombre));
  }
}
