package domain.controllers;

import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import domain.organizacion.*;
import domain.reports.ReportGenerator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportController {
  public ModelAndView calculadoraOrganizacion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
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
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
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
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    model.put("tipo_reporte","Huella de Carbono");
    return new ModelAndView(model, "reporteHcTotal.hbs");
  }

  public ModelAndView reporteEvolucion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    model.put("tipo_reporte","Evolución");
    List<Organizacion> organizaciones = RepoOrganizaciones.getInstance().getOrganizaciones();
    model.put("organizaciones", organizaciones);
    if (request.queryParams("entidad") != null) {
      Long organizacionId = Long.parseLong(request.queryParams("entidad"));
      Periodicidad periodicidad = request.queryParams("periodicidad") == "anual" ? Periodicidad.ANUAL : Periodicidad.MENSUAL;
      System.out.println(request.queryParams("periodicidad"));
      periodicidad =  Periodicidad.ANUAL;
      String periodoDeImputacionInicio;
      String periodoDeImputacionFin;
      UnidadEquivalenteCarbono unidadEquivalenteCarbono;
      if(periodicidad == Periodicidad.ANUAL) {
        periodoDeImputacionInicio = request.queryParams("anio1");
        periodoDeImputacionFin = request.queryParams("anio2");
      } else {
        if (Integer.parseInt(request.queryParams("mes")) < 10) {
          periodoDeImputacionInicio = "0" + request.queryParams("mes") + "/" + request.queryParams("anio");
          periodoDeImputacionFin = "0" + request.queryParams("mes") + "/" + request.queryParams("anio");
        } else {
          periodoDeImputacionInicio = request.queryParams("mes") + "/" + request.queryParams("anio");
          periodoDeImputacionFin = "0" + request.queryParams("mes") + "/" + request.queryParams("anio");
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
      List<Double> resultado = ReportGenerator.getEvolucionHcDeOrganizacion(organizacionId, periodicidad,
      periodoDeImputacionInicio, periodoDeImputacionFin, unidadEquivalenteCarbono);
      System.out.println(resultado.get(0));
      model.put("resultado", resultado);
    }
    return new ModelAndView(model, "reporteEvolucion.hbs");
  }

  public ModelAndView reporteComposicion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    model.put("tipo_reporte","Composición");
    return new ModelAndView(model, "reporteComposicion.hbs");
  }

  public ModelAndView reportes(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    model.put("tipo_reporte","");
    return new ModelAndView(model, "layoutReporte.hbs");
  }
}
