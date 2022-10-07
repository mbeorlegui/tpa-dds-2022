package domain;

import domain.inicializacion.*;
import lombok.Getter;

@Getter
public class InicializacionTests {
  InstanciasContacto contactos;
  InstanciasMedicion mediciones;
  InstanciasMiembro miembros;
  InstanciasOrganizacion organizaciones;
  InstanciasParada paradas;
  InstanciasTramo tramos;
  InstanciasTransporte transportes;
  InstanciasTrayecto trayectos;
  InstanciasUbicacion ubicaciones;
  InstanciasTipoDeConsumo tiposDeConsumo;
  public InicializacionTests() {
    this.contactos = new InstanciasContacto();
    this.paradas = new InstanciasParada();
    this.ubicaciones = new InstanciasUbicacion();
    this.tiposDeConsumo = new InstanciasTipoDeConsumo();
    this.mediciones = new InstanciasMedicion(this.getTiposDeConsumo());
    this.transportes = new InstanciasTransporte(this.getParadas(), this.getTiposDeConsumo());
    this.tramos = new InstanciasTramo(this.getUbicaciones(), this.getTransportes());
    this.trayectos = new InstanciasTrayecto(this.getTramos());
    this.miembros = new InstanciasMiembro(this.getTrayectos());
    this.organizaciones = new InstanciasOrganizacion(this.getUbicaciones(),
        this.getMiembros(), this.getContactos());
  }
}

