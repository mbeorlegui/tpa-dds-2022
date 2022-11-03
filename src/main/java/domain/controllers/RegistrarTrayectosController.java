package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import domain.transporte.Parada;
import domain.transporte.RepoParadas;
import domain.transporte.RepoTransportes;
import domain.transporte.Transporte;

public class RegistrarTrayectosController {
  public ModelAndView registrarTrayecto(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    List<Parada> paradas = RepoParadas.getInstance().getParadas();
    System.out.println(new Gson().toJson(paradas.get(0)) );
    model.put("paradas", paradas);
    List<Transporte> transportes = RepoTransportes.getInstance().getTransportes();
    model.put("transportes", transportes);
    System.out.println("transportes: "+new Gson().toJson(transportes));
    Map<Long, List<Parada>> paradasPorTransporte = new HashMap<Long, List<Parada>>();
    transportes.forEach(t -> paradasPorTransporte.put(t.getId(),RepoParadas.getInstance().getParadasDeTransporte(t)));
    model.put("paradasPorTransporte", new Gson().toJson(paradasPorTransporte));
    System.out.println(new Gson().toJson(paradasPorTransporte) );
    return new ModelAndView(model, "registrarTrayecto.hbs");
  }
}
