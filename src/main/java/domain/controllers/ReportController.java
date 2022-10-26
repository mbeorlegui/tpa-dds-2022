package domain.controllers;

import domain.organizacion.*;
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
    return new ModelAndView(model, "calculadoraOrganizacion.hbs");
  }

  public ModelAndView calculadoraSectorTerritorial(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    List<SectorTerritorial> sectoresTerritoriales = RepoSectoresTerritoriales.getInstance().getSectoresTerritoriales();
    model.put("sectores_territoriales", sectoresTerritoriales);
    return new ModelAndView(model, "calculadoraSectorTerritorial.hbs");
  }

  public ModelAndView reporteHcTotal(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    return new ModelAndView(model, "reporteHcTotal.hbs");
  }

  public ModelAndView reporteEvolucion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    return new ModelAndView(model, "reporteEvolucion.hbs");
  }

  public ModelAndView reporteComposicion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    return new ModelAndView(model, "reporteComposicion.hbs");
  }
}
