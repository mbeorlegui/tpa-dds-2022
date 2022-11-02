package domain.controllers;

import domain.administrador.RepoSolicitudes;
import domain.administrador.Solicitud;
import domain.organizacion.Organizacion;
import domain.organizacion.RepoOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestController {
  public ModelAndView request(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<Organizacion> organizaciones = RepoOrganizaciones.getInstance().getOrganizaciones();
    model.put("organizaciones", organizaciones);
    return new ModelAndView(model, "request.hbs");
  }

  public ModelAndView aceptarVinculacion(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<Solicitud> solicitudes = RepoSolicitudes.getInstance().getSolicitudes();
    model.put("solicitudes", solicitudes);
    return new ModelAndView(model, "aceptacionVinculacion.hbs");
  }
}
