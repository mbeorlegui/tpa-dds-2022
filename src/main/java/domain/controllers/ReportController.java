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
    if (request.queryParams("organizacion") != null) {
      Integer organizacionId = Integer.parseInt(request.queryParams("organizacion"));

      model.put("resultado", 10000);
    }
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
    model.put("tipo_reporte","Huella de Carbono");
    return new ModelAndView(model, "reporteHcTotal.hbs");
  }

  public ModelAndView reporteEvolucion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    model.put("tipo_reporte","Evolución");
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
