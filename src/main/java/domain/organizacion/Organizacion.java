package domain.organizacion;

import domain.ubicacion.Ubicacion;

import java.util.List;

public class Organizacion {
  String razonSocial;
  Tipo tipo;
  Ubicacion ubicacion;
  List<Sector> sectores;
  Clasificacion clasificacion;
}
