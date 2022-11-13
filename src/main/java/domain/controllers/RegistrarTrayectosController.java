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

import domain.administrador.RepoUsuarios;
import domain.administrador.UsuarioGeneral;
import domain.miembro.Miembro;
import domain.miembro.RepoMiembros;
import domain.transporte.Parada;
import domain.transporte.RepoParadas;
import domain.transporte.RepoTransportes;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;
import domain.trayecto.RepoTramos;
import domain.trayecto.RepoTrayectos;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;

public class RegistrarTrayectosController  implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView registrarTrayecto(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    List<Parada> paradas = RepoParadas.getInstance().getParadas();
    model.put("paradas", new Gson().toJson(paradas));
    List<Transporte> transportes = RepoTransportes.getInstance().getTransportes();
    model.put("transportes", new Gson().toJson(transportes));
    Map<Long, List<Parada>> paradasPorTransporte = new HashMap<Long, List<Parada>>();
    transportes.stream().filter(t -> t.tipoTransporte == TipoTransporte.PUBLICO)
      .forEach(t -> paradasPorTransporte.put(t.getId(), RepoParadas.getInstance().getParadasDeTransporte(t)));
    model.put("paradasPorTransporte", new Gson().toJson(paradasPorTransporte));
    return new ModelAndView(model, "registrarTrayecto.hbs");
  }

  public ModelAndView generarTrayecto(Request request, Response response) {
    int cantidadTramos = Integer.parseInt(request.queryParams("cantidadTramos"));
    List<Tramo> tramos = new ArrayList<>();
    int numeroTramo = 1;
    while(numeroTramo<=cantidadTramos){
      String inicio = request.queryParams("ubicacion"+(numeroTramo-1));
      String fin = request.queryParams("ubicacion"+numeroTramo);
      Long idTransporte = Long.parseLong(request.queryParams("transporte"+numeroTramo));
      int separacionInicio = inicio.trim().lastIndexOf(" ");
      int separacionFin = fin.trim().lastIndexOf(" ");
      
      Transporte transporte = RepoTransportes.getInstance().getTransporte(idTransporte);
      Parada paradaInicio = RepoParadas.getInstance().getParada(
        inicio.substring(0, separacionInicio),inicio.substring(separacionInicio+1));
      Parada paradaFin = RepoParadas.getInstance().getParada(
        fin.substring(0, separacionFin),fin.substring(separacionFin+1));
        
      Tramo tramo = new Tramo(paradaInicio.getUbicacion(), paradaFin.getUbicacion(), transporte);
      tramos.add(tramo);
      numeroTramo++;
    }
    
    UsuarioGeneral usuario = (UsuarioGeneral) RepoUsuarios.getInstance()
      .findByUsername(request.session().attribute("usuario_logueado"));
    Miembro miembro = usuario.getMiembroAsociado();
    Trayecto trayecto = new Trayecto(tramos);
    miembro.setTrayecto(trayecto);
    withTransaction(() -> {
      tramos.forEach(t -> RepoTramos.getInstance().persistTramo(t));
      RepoTrayectos.getInstance().persistTrayecto(trayecto);
      RepoMiembros.getInstance().updateMiembro(miembro);
    });
    request.session().attribute("mensaje", "Se registro correctamente su trayecto");
    response.redirect("/home");

    return null;
  }
}
