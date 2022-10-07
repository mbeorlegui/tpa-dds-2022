package domain.inicializacion;

import domain.administrador.FactorDeEmision;
import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Actividad;
import domain.medicion.Alcance;
import domain.medicion.TipoConsumo;
import domain.medicion.Unidad;
import lombok.Getter;

@Getter
public class InstanciasTipoDeConsumo {
    private TipoConsumo gasNatural;
    private TipoConsumo dieselGasoil;
    private TipoConsumo nafta;
    private TipoConsumo carbon;
    private TipoConsumo ccGasoil;
    private TipoConsumo ccNafta;
    private TipoConsumo electricidad;
    private TipoConsumo medioDeTransporte;
    private TipoConsumo distanciaMedia;

    public InstanciasTipoDeConsumo() {
        this.gasNatural = new TipoConsumo(Actividad.COMBUSTION_FIJA,
                Alcance.EMISION_DIRECTA, Unidad.M3, new FactorDeEmision(0.2, UnidadEquivalenteCarbono.GRAMO),
                "GAS_NATURAL");
        this.dieselGasoil = new TipoConsumo(Actividad.COMBUSTION_FIJA,
                Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.5, UnidadEquivalenteCarbono.GRAMO),
                "DIESEL_GASOIL");
        this.nafta = new TipoConsumo(Actividad.COMBUSTION_FIJA,
                Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.3, UnidadEquivalenteCarbono.GRAMO),
                "NAFTA");
        this.carbon = new TipoConsumo(Actividad.COMBUSTION_FIJA,
                Alcance.EMISION_DIRECTA, Unidad.KG, new FactorDeEmision(1.6, UnidadEquivalenteCarbono.GRAMO),
                "CARBON");
        this.ccGasoil = new TipoConsumo(Actividad.COMBUSTION_MOVIL,
                Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.7, UnidadEquivalenteCarbono.GRAMO),
                "CC_GASOIL");
        this.ccNafta = new TipoConsumo(Actividad.COMBUSTION_MOVIL,
                Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.4, UnidadEquivalenteCarbono.GRAMO),
                "CC_NAFTA");
        this.electricidad = new TipoConsumo(Actividad.ELECTRICIDAD,
                Alcance.EMISION_INDIRECTA,Unidad.KWH,new FactorDeEmision(0.4, UnidadEquivalenteCarbono.GRAMO),
                "ELECTRICIDAD");
        this.medioDeTransporte = new TipoConsumo(Actividad.LOGISTICA,
                Alcance.OTRAS_EMISIONES, Unidad.SIN_UNIDAD,
                new FactorDeEmision(0.1, UnidadEquivalenteCarbono.GRAMO),
                "MEDIO_DE_TRANSPORTE");
        this.distanciaMedia = new TipoConsumo(Actividad.LOGISTICA,
                Alcance.OTRAS_EMISIONES, Unidad.KM, new FactorDeEmision(1.0, UnidadEquivalenteCarbono.GRAMO),
                "DISTANCIA_MEDIA_RECORRIDA");
    }
}
