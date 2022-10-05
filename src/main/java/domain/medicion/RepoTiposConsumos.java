package domain.medicion;

import domain.administrador.FactorDeEmision;
import domain.administrador.UnidadEquivalenteCarbono;
import domain.organizacion.Organizacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepoTiposConsumos {

  private EntityManager em = PerThreadEntityManagers.getEntityManager();

  public List<TipoConsumo> consumos = new ArrayList<>();

  private static final RepoTiposConsumos INSTANCE =
      new RepoTiposConsumos();

  private RepoTiposConsumos() {}

  public List<TipoConsumo> getTiposConsumos() {
    return em
        .createQuery("from TipoConsumo")
        .getResultList();
  }

  public void agregarTiposDeConsumo(TipoConsumo... tiposConsumo) {
    this.consumos.addAll(Arrays.asList(tiposConsumo));
  }
  //TODO: modificar para que agregue a la db y hacer un test

  public static RepoTiposConsumos getInstance() {
    return INSTANCE;
  }

  public TipoConsumo hayarTipo(String nombre) {
    return this.getTiposConsumos().stream().filter(unConsumo -> unConsumo.toString().equals(nombre))
        .findFirst().get();
  }

  public boolean existeTipo(String nombre) {
    return this.getTiposConsumos().stream().anyMatch(unConsumo -> unConsumo.toString().equals(nombre));
  }

  public void actualizarTiposDeConsumoDB(){
    TipoConsumo gasNatural = new TipoConsumo(Actividad.COMBUSTION_FIJA,
            Alcance.EMISION_DIRECTA, Unidad.M3, new FactorDeEmision(0.2, UnidadEquivalenteCarbono.GRAMO),
            "GAS_NATURAL");
    TipoConsumo dieselGasoil = new TipoConsumo(Actividad.COMBUSTION_FIJA,
            Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.5, UnidadEquivalenteCarbono.GRAMO),
            "DIESEL_GASOIL");
    TipoConsumo nafta = new TipoConsumo(Actividad.COMBUSTION_FIJA,
            Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.3, UnidadEquivalenteCarbono.GRAMO),
            "NAFTA");
    TipoConsumo carbon = new TipoConsumo(Actividad.COMBUSTION_FIJA,
            Alcance.EMISION_DIRECTA, Unidad.KG, new FactorDeEmision(1.6, UnidadEquivalenteCarbono.GRAMO),
            "CARBON");
    TipoConsumo ccGasoil = new TipoConsumo(Actividad.COMBUSTION_MOVIL,
            Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.7, UnidadEquivalenteCarbono.GRAMO),
            "CC_GASOIL");
    TipoConsumo ccNafta = new TipoConsumo(Actividad.COMBUSTION_MOVIL,
            Alcance.EMISION_DIRECTA, Unidad.LT, new FactorDeEmision(2.4, UnidadEquivalenteCarbono.GRAMO),
            "CC_NAFTA");
    TipoConsumo electricidad = new TipoConsumo(Actividad.ELECTRICIDAD,
            Alcance.EMISION_INDIRECTA,Unidad.KWH,new FactorDeEmision(0.4, UnidadEquivalenteCarbono.GRAMO),
            "ELECTRICIDAD");
    TipoConsumo medioDeTransporte = new TipoConsumo(Actividad.LOGISTICA,
            Alcance.OTRAS_EMISIONES, Unidad.SIN_UNIDAD,
            new FactorDeEmision(0.1, UnidadEquivalenteCarbono.GRAMO),
            "MEDIO_DE_TRANSPORTE");
    TipoConsumo distanciaMedia = new TipoConsumo(Actividad.LOGISTICA,
            Alcance.OTRAS_EMISIONES, Unidad.KM, new FactorDeEmision(1.0, UnidadEquivalenteCarbono.GRAMO),
            "DISTANCIA_MEDIA_RECORRIDA");

    // EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction et = em.getTransaction();
    et.begin();
    em.persist(gasNatural);
    em.persist(dieselGasoil);
    em.persist(nafta);
    em.persist(carbon);
    em.persist(ccGasoil);
    em.persist(ccNafta);
    em.persist(electricidad);
    em.persist(medioDeTransporte);
    em.persist(distanciaMedia);
    et.commit();
    System.out.println("Tipos de consumo actualizados");
    //em.close();
  }
}
