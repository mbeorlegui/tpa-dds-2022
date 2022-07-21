package domain.inicializacion;

import domain.miembro.Miembro;
import domain.organizacion.Clasificacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.organizacion.TipoOrganizacion;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;

@Getter
public class InstanciasOrganizacion {
  private Sector sectorDeRRHH;
  private Sector sectorDesarrollo;
  private Organizacion utn;
  private Organizacion orgFalsa;

  public InstanciasOrganizacion(InstanciasUbicacion ubicaciones, InstanciasMiembro miembros, InstanciasContacto contactos) {
    this.sectorDeRRHH = unSector(miembros.getMiembro2());
    this.sectorDesarrollo = unSector(miembros.getMiembro1());
    this.utn = unaUniversidadGubernamental(ubicaciones.getUbicacionUtn());
    this.utn.addSectores(sectorDeRRHH, new Sector(), new Sector());
    this.utn.agregarContactos(contactos.getContacto1());
    this.orgFalsa = unaEmpresaSectorPrimario(ubicaciones.getUbicacionOrgFalsa());
    this.orgFalsa.addSectores(sectorDesarrollo, sectorDeRRHH);
  }

  @DisplayName("Instanciar: Un sector")
  public Sector unSector(Miembro... miembros) {
    Sector sector = new Sector();
    sector.addMiembros(miembros);
    return sector;
  }

  @DisplayName("Instanciar: Univerdidad Gubernamental")
  private Organizacion unaUniversidadGubernamental(Ubicacion ubicacion) {
    return new Organizacion("UTN", TipoOrganizacion.GUBERNAMENTAL, ubicacion,
        Clasificacion.UNIVERSIDAD);
  }

  @DisplayName("Instanciar: Otra organizacion")
  public Organizacion unaEmpresaSectorPrimario(Ubicacion ubicacion) {
    return new Organizacion("orgFalsa SRL", TipoOrganizacion.EMPRESA, ubicacion,
        Clasificacion.EMPRESA_DEL_SECTOR_PRIMARIO);
  }
}
