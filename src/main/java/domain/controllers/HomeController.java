package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController {
  public ModelAndView home(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    model.put("mensaje", request.session().attribute("mensaje"));
    request.session().removeAttribute("mensaje");
    return new ModelAndView(model, "home.hbs");
  }

  public ModelAndView guiaRecomendaciones(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    return new ModelAndView(model, "guiaRecomendaciones.hbs");
  }
}
