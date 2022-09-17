package domain.organizacion;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;

import javax.persistence.*;
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
}
