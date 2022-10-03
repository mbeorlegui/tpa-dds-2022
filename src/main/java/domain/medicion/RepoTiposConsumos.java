package domain.medicion;

import domain.administrador.FactorDeEmision;
import domain.administrador.UnidadEquivalenteCarbono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepoTiposConsumos {
  public List<TipoConsumo> consumos = new ArrayList<>();
  private TipoConsumo gasNatural = new TipoConsumo(Actividad.COMBUSTION_FIJA,
      Alcance.EMISION_DIRECTA, Unidad.M3, new FactorDeEmision(0.2, UnidadEquivalenteCarbono.GRAMO),
      "GAS_NATURAL");
  private TipoConsumo dieselGasoil = new TipoConsumo(Actividad.COMBUSTION_FIJA,
      Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.5, UnidadEquivalenteCarbono.GRAMO),
      "DIESEL_GASOIL");
  private TipoConsumo nafta = new TipoConsumo(Actividad.COMBUSTION_FIJA,
      Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.3, UnidadEquivalenteCarbono.GRAMO),
      "NAFTA");
  private TipoConsumo carbon = new TipoConsumo(Actividad.COMBUSTION_FIJA,
      Alcance.EMISION_DIRECTA, Unidad.KG, new FactorDeEmision(1.6, UnidadEquivalenteCarbono.GRAMO),
      "CARBON");
  private TipoConsumo ccGasoil = new TipoConsumo(Actividad.COMBUSTION_MOVIL,
      Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.7, UnidadEquivalenteCarbono.GRAMO),
      "CC_GASOIL");
  private TipoConsumo ccNafta = new TipoConsumo(Actividad.COMBUSTION_MOVIL,
      Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.4, UnidadEquivalenteCarbono.GRAMO),
      "CC_NAFTA");
  private TipoConsumo electricidad = new TipoConsumo(Actividad.ELECTRICIDAD,
      Alcance.EMISION_INDIRECTA, Unidad.KWH, new FactorDeEmision(0.4, UnidadEquivalenteCarbono.GRAMO),
      "ELECTRICIDAD");
  private TipoConsumo medioDeTransporte = new TipoConsumo(Actividad.LOGISTICA,
      Alcance.OTRAS_EMISIONES, Unidad.SIN_UNIDAD,
      new FactorDeEmision(0.1, UnidadEquivalenteCarbono.GRAMO),
      "MEDIO_DE_TRANSPORTE");
  private TipoConsumo distanciaMedia = new TipoConsumo(Actividad.LOGISTICA,
      Alcance.OTRAS_EMISIONES, Unidad.KM, new FactorDeEmision(1.0, UnidadEquivalenteCarbono.GRAMO),
      "DISTANCIA_MEDIA_RECORRIDA");

  private static final RepoTiposConsumos INSTANCE =
      new RepoTiposConsumos();

  // TODO: chequear factor de emision, por ahora definido en 500

  private RepoTiposConsumos() {
    agregarTiposDeConsumo(gasNatural, dieselGasoil, nafta,
        carbon, ccGasoil, ccNafta, electricidad,
        medioDeTransporte, distanciaMedia);
  }
  // TODO: Mejorar el init que está feo

  public void agregarTiposDeConsumo(TipoConsumo... tiposConsumo) {
    this.consumos.addAll(Arrays.asList(tiposConsumo));
  }

  public static RepoTiposConsumos getInstance() {
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
