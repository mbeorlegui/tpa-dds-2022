package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
  public ModelAndView index(Request request, Response response) {
    return new ModelAndView(null, "home.hbs");
  }

  public ModelAndView guiaRecomendaciones(Request request, Response response) {
    return new ModelAndView(null, "guiaRecomendaciones.hbs");
  }
}
