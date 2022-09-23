package domain.organizacion;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import domain.reports.ReporteDeComposicion;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sector_territorial")
public class SectorTerritorial {
  @Id
  @GeneratedValue
  @Column(name = "sector_territorial_id")
  @Getter
  private long id;
  @OneToMany
  @JoinColumn(name = "sector_territorial_id")
  private List<Organizacion> organizaciones = new ArrayList<>();

  public double huellaDeCarbonoEnPeriodo(Periodicidad periodicidad,
                                         String periodoDeImputacion,
                                         UnidadEquivalenteCarbono unidadDeseada) {
    return RepoOrganizaciones
        .getInstance()
        .huellaDeCarbonoEnPeriodoDeOrganizaciones(organizaciones, periodicidad, periodoDeImputacion, unidadDeseada);
  }

  public ReporteDeComposicion composicionHuellaDeCarbono(Periodicidad periodicidad,
                                                         String periodoDeImputacion,
                                                         UnidadEquivalenteCarbono unidadDeseada) {
    return new ReporteDeComposicion(
        this.hcMediciones(periodicidad, periodoDeImputacion, unidadDeseada),
        this.hcTrayectos(periodicidad, unidadDeseada));
  }

  private double hcMediciones(Periodicidad periodicidad,
                              String periodoDeImputacion,
                              UnidadEquivalenteCarbono unidadDeseada) {
    return organizaciones
        .stream()
        .mapToDouble(
            org -> org.hcMedicionesEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada))
        .sum();
  }

  private double hcTrayectos(Periodicidad periodicidad,
                             UnidadEquivalenteCarbono unidadDeseada) {
    return organizaciones
        .stream()
        .mapToDouble(org -> org.hcTrayectosMiembros(periodicidad, unidadDeseada))
        .sum();
  }

  public void agregarOrganizacion(Organizacion organizacion) {
    organizaciones.add(organizacion);
  }

  public Long getId() {
    return id;
  }

  public List<Organizacion> getOrganizaciones() {
    return organizaciones;
  }
}
