package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.transporte.Parada;
import domain.transporte.RepoParadas;
import domain.transporte.RepoTransportes;
import domain.transporte.Transporte;

public class RegistrarTrayectosController {
  public ModelAndView registrarTrayecto(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    List<Parada> paradas = RepoParadas.getInstance().getParadas();
    System.out.println(paradas.size());
    model.put("paradas", paradas);
    List<Transporte> transportes = RepoTransportes.getInstance().getTransportes();
    model.put("transportes", transportes);
    return new ModelAndView(model, "registrarTrayecto.hbs");
  }
}
