package domain.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import com.github.jknack.handlebars.Handlebars;
import domain.administrador.Administrador;
import domain.administrador.UsuarioGeneral;
import domain.controllers.*;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public static void configure() {
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
    HomeController homeController = new HomeController();
    UsersController usersController = new UsersController();
    RequestController requestController = new RequestController();
    RegistrarMedicionController registrarMedicionController = new RegistrarMedicionController();
    RegistrarTrayectosController registrarTrayectosController = new RegistrarTrayectosController();
    ReportController reportController = new ReportController();

    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction et = em.getTransaction();

    DebugScreen.enableDebugScreen();

    Spark.staticFiles.location("public"); // --> carpeta en la que se encuentran los .css dentro de la carpeta resouces

    Spark.get("/home", homeController::home, engineTemplate);
    Spark.redirect.get("/", "/home");

    Spark.get("/login", usersController::login, engineTemplate);
    Spark.get("/signin", usersController::signin, engineTemplate);
    Spark.get("/loginError", usersController::loginError, engineTemplate);
    Spark.post("/session", usersController::post, engineTemplate);
    Spark.post("/session/remove", usersController::delete, engineTemplate);

    Spark.before("/user/*", ((request, response) -> {
      if (request.session().attribute("usuario_logueado") == null) {
        request.session().attribute("mensaje", "Debes iniciar sesion para acceder");
        response.redirect("/home");
      }
    }));

    Spark.before("/user/general/*", ((request, response) -> {
      if (!request.session().attribute("tipo_usuario").toString().equals("UsuarioGeneral")) {
        request.session().attribute("mensaje", "Debes ser un usuario general para acceder");
        response.redirect("/home");
      }
    }));

    Spark.before("/user/admin/*", ((request, response) -> {
      if (!request.session().attribute("tipo_usuario").toString().equals("Administrador")) {
        request.session().attribute("mensaje", "Debes ser un administrador para acceder");
        response.redirect("/home");
      }
    }));
    // Este after elimina la cache del em despues de terminar la request
    /*Spark.after(((request, response) -> {
      PerThreadEntityManagers.getEntityManager().clear();
      //PerThreadEntityManagers.closeEntityManager();
    }));*/

    Spark.get("/user/general/request", requestController::request, engineTemplate);

    Spark.get("/user/admin/registrarMedicionCsv", registrarMedicionController::registrarMedicionCsv, engineTemplate);

    Spark.get("/user/admin/registrarMedicionParticular", registrarMedicionController::registrarMedicionParticular, engineTemplate);

    Spark.get("/user/general/registrarTrayecto", registrarTrayectosController::registrarTrayecto, engineTemplate);

    Spark.get("/user/calculadoraOrganizacion", reportController::calculadoraOrganizacion, engineTemplate);

    Spark.get("/user/calculadoraSectorTerritorial", reportController::calculadoraSectorTerritorial, engineTemplate);

    Spark.get("/user/admin/vinculaciones", requestController::aceptarVinculacion, engineTemplate);

    Spark.get("/user/admin/reportes", reportController::reportes, engineTemplate);

    Spark.get("/user/admin/reportes/hcTotal", reportController::reporteHcTotal, engineTemplate);

    Spark.get("/user/admin/reportes/evolucion", reportController::reporteEvolucion, engineTemplate);

    Spark.get("/user/admin/reportes/composicion", reportController::reporteComposicion, engineTemplate);

    Spark.post("/user/general/registrarTrayecto/persist", registrarTrayectosController::generarTrayecto, engineTemplate);
  }
}
