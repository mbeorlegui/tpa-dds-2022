package domain.controllers;

import domain.administrador.*;
import domain.organizacion.Organizacion;
import domain.organizacion.RepoOrganizaciones;
import domain.organizacion.RepoSectores;
import domain.organizacion.Sector;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class RequestController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView request(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<Organizacion> organizaciones = RepoOrganizaciones.getInstance().getOrganizaciones();
    model.put("organizaciones", organizaciones);
    return new ModelAndView(model, "request.hbs");
  }

  public ModelAndView generarRequest(Request request, Response response) {
    String motivo = request.queryParams("motivo");
    Long idSector = Long.parseLong(request.queryParams("sector"));

    Sector sector = RepoSectores.getInstance().getSector(idSector);

    UsuarioGeneral usuario = (UsuarioGeneral) RepoUsuarios.getInstance().findByUsername(request.session().attribute("usuario_logueado"));

    Solicitud nuevaSolicitud = new Solicitud(sector, usuario.getMiembroAsociado(), motivo, LocalDateTime.now());

    System.out.println(nuevaSolicitud);

    // agregar a la clase para usarlo: implements WithGlobalEntityManager, TransactionalOps
    withTransaction(() -> {
      RepoSolicitudes.getInstance().persistSolicitud(nuevaSolicitud);
    });


    request.session().attribute("mensaje", "Se genero la nueva solicitud");
    response.redirect("/home");

    return null;
  }

  public ModelAndView vinculacionesOrganizacion(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    Administrador usuario = (Administrador) RepoUsuarios.getInstance().findByUsername(request.session().attribute("usuario_logueado"));

    List<Solicitud> solicitudes = RepoSolicitudes.getInstance().getSolicitudesPendientesDeOrganizacion(usuario.getOrganizacionAsociada());
    System.out.println("Organizacion: " + usuario.getOrganizacionAsociada().getRazonSocial());
    System.out.println("Solicitudes de organizacion: " + solicitudes);
    model.put("solicitudes", solicitudes);

    model.put("mensaje", request.session().attribute("mensaje"));
    request.session().removeAttribute("mensaje");
    return new ModelAndView(model, "aceptacionVinculacion.hbs");
  }


  public ModelAndView aceptarVinculacion(Request request, Response response) {
    Long idSolicitud = Long.parseLong(request.queryParams("solicitud"));
    Solicitud solicitud = RepoSolicitudes.getInstance().getSolicitud(idSolicitud);
    solicitud.aceptar();

    withTransaction(() -> {
      RepoSolicitudes.getInstance().updateSolicitud(solicitud);
      RepoSectores.getInstance().updateSector(solicitud.getSector());
    });

    request.session().attribute("mensaje", "Se acepto la solicitud");
    response.redirect("/user/admin/vinculaciones");

    return null;
  }

  public ModelAndView rechazarVinculacion(Request request, Response response) {
    Long idSolicitud = Long.parseLong(request.queryParams("solicitud"));
    Solicitud solicitud = RepoSolicitudes.getInstance().getSolicitud(idSolicitud);
    solicitud.rechazar();

    withTransaction(() -> {
      RepoSolicitudes.getInstance().updateSolicitud(solicitud);
    });

    request.session().attribute("mensaje", "Se rechazo la solicitud");
    response.redirect("/user/admin/vinculaciones");
    return null;
  }
}
