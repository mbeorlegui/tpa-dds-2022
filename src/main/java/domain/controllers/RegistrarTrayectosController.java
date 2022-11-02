package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class RegistrarTrayectosController {
  public ModelAndView registrarTrayecto(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    return new ModelAndView(model, "registrarTrayecto.hbs");
  }
}
