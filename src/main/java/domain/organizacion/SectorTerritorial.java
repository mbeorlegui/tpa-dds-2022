package domain.organizacion;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;

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
  private long id;
  @OneToMany
  @JoinColumn(name = "sector_territorial_id")
  private List<Organizacion> organizaciones = new ArrayList<>();

  public double huellaDeCarbonoEnPeriodo(Periodicidad periodicidad,
                                         String periodoDeImputacion,
                                         UnidadEquivalenteCarbono unidadDeseada) {
    return organizaciones
        .stream()
        .mapToDouble(org ->
            org.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadDeseada))
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
