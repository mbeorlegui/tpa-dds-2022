package domain.controllers;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import domain.organizacion.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportController {
  public ModelAndView calculadoraOrganizacion(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<Organizacion> organizaciones = RepoOrganizaciones.getInstance().getOrganizaciones();
    model.put("organizaciones", organizaciones);
    if (request.queryParams("organizacion") != null) {
      Long organizacionId = Long.parseLong(request.queryParams("organizacion"));
      Periodicidad periodicidad = request.queryParams("periodicidad") == "anual" ? Periodicidad.ANUAL : Periodicidad.MENSUAL;
      String periodoDeImputacion;
      UnidadEquivalenteCarbono unidadEquivalenteCarbono;
      if(periodicidad == Periodicidad.ANUAL) {
        periodoDeImputacion = request.queryParams("anio");
      } else {
        if (Integer.parseInt(request.queryParams("mes")) < 10) {
          periodoDeImputacion = "0" + request.queryParams("mes") + "/" + request.queryParams("anio");
        } else {
          periodoDeImputacion = request.queryParams("mes") + "/" + request.queryParams("anio");
        }
      }
      switch (Integer.parseInt(request.queryParams("unidad"))) {
        case 0:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.GRAMO;
          break;
        case 1:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
        case 2:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.TONELADA;
          break;
        default:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
      }
      Organizacion organizacion = RepoOrganizaciones.getInstance().getOrganizacion(organizacionId);
      model.put("resultado", organizacion.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadEquivalenteCarbono));
    }
    return new ModelAndView(model, "calculadoraOrganizacion.hbs");
  }

  public ModelAndView calculadoraSectorTerritorial(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<SectorTerritorial> sectoresTerritoriales = RepoSectoresTerritoriales.getInstance().getSectoresTerritoriales();
    model.put("sectores_territoriales", sectoresTerritoriales);

    if (request.queryParams("sector_territorial") != null) {
      Long organizacionId = Long.parseLong(request.queryParams("sector_territorial"));
      Periodicidad periodicidad = request.queryParams("periodicidad") == "anual" ? Periodicidad.ANUAL : Periodicidad.MENSUAL;
      String periodoDeImputacion;
      UnidadEquivalenteCarbono unidadEquivalenteCarbono;
      if(periodicidad == Periodicidad.ANUAL) {
        periodoDeImputacion = request.queryParams("anio");
      } else {
        if (Integer.parseInt(request.queryParams("mes")) < 10) {
          periodoDeImputacion = "0" + request.queryParams("mes") + "/" + request.queryParams("anio");
        } else {
          periodoDeImputacion = request.queryParams("mes") + "/" + request.queryParams("anio");
        }
      }
      switch (Integer.parseInt(request.queryParams("unidad"))) {
        case 0:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.GRAMO;
          break;
        case 1:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
        case 2:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.TONELADA;
          break;
        default:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
      }
      SectorTerritorial sectorTerritorial = RepoSectoresTerritoriales.getInstance().getSectorTerritorial(organizacionId);
      model.put("resultado", sectorTerritorial.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadEquivalenteCarbono));
    }

    return new ModelAndView(model, "calculadoraSectorTerritorial.hbs");
  }

  public ModelAndView reporteHcTotal(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("tipo_reporte","Huella de Carbono");
    return new ModelAndView(model, "reporteHcTotal.hbs");
  }

  public ModelAndView reporteEvolucion(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("tipo_reporte","Evolución");
    return new ModelAndView(model, "reporteEvolucion.hbs");
  }

  public ModelAndView reporteComposicion(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("tipo_reporte","Composición");
    return new ModelAndView(model, "reporteComposicion.hbs");
  }

  public ModelAndView reportes(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("tipo_reporte","");
    return new ModelAndView(model, "layoutReporte.hbs");
  }
}
