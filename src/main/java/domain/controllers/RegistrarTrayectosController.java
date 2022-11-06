package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.google.gson.Gson;

import domain.transporte.Parada;
import domain.transporte.RepoParadas;
import domain.transporte.RepoTransportes;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;

public class RegistrarTrayectosController  implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView registrarTrayecto(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    List<Parada> paradas = RepoParadas.getInstance().getParadas();
    model.put("paradas", new Gson().toJson(paradas));
    List<Transporte> transportes = RepoTransportes.getInstance().getTransportes();
    model.put("transportes", new Gson().toJson(transportes));
    Map<Long, List<Parada>> paradasPorTransporte = new HashMap<Long, List<Parada>>();
    transportes.forEach(t -> paradasPorTransporte.put(t.getId(),RepoParadas.getInstance().getParadasDeTransporte(t)));
    model.put("paradasPorTransporte", new Gson().toJson(paradasPorTransporte));
    return new ModelAndView(model, "registrarTrayecto.hbs");
  }

  public ModelAndView generarTrayecto(Request request, Response response) {
    String inicio = request.queryParams("ubicacionInicio");
    String fin = request.queryParams("ubicacionFin");
    Long idTransporte1 = Long.parseLong(request.queryParams("transporte1"));

    Transporte transporte1 = RepoTransportes.getInstance().getTransporte(idTransporte1);

    System.out.println(inicio);
    System.out.println(fin);
    System.out.println(new Gson().toJson(transporte1));
    // agregar a la clase para usarlo: implements WithGlobalEntityManager, TransactionalOps
    /*withTransaction(() -> {
      RepoSolicitudes.getInstance().persistSolicitud(nuevaSolicitud);
    });

    */
    request.session().attribute("mensaje", "Se genero la nueva solicitud");
    response.redirect("/home");

    return null;
  }
}
