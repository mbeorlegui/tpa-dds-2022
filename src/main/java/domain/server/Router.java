package domain.server;

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
        response.redirect("/home");
      }
    }));

    Spark.before("/user/general/*", ((request, response) -> {
      if (request.session().attribute("tipo_usuario").toString() != "UsuarioGeneral") {
        response.redirect("/home");
      }
    }));

    Spark.before("/user/admin/*", ((request, response) -> {
      if (request.session().attribute("tipo_usuario").toString() != "Administrador") {
        response.redirect("/home");
      }
    }));

    Spark.get("/user/request", requestController::request, engineTemplate);

    Spark.get("/user/registrarMedicionCsv", registrarMedicionController::registrarMedicionCsv, engineTemplate);

    Spark.get("/user/registrarMedicionParticular", registrarMedicionController::registrarMedicionParticular, engineTemplate);

    Spark.get("/user/registrarTrayecto", registrarTrayectosController::registrarTrayecto, engineTemplate);

    Spark.get("/user/calculadoraOrganizacion", reportController::calculadoraOrganizacion, engineTemplate);

    Spark.get("/user/calculadoraSectorTerritorial", reportController::calculadoraSectorTerritorial, engineTemplate);

    Spark.get("/user/vinculaciones", requestController::aceptarVinculacion, engineTemplate);

    Spark.get("/user/reportes", reportController::reportes, engineTemplate);

    Spark.get("/user/reportes/hcTotal", reportController::reporteHcTotal, engineTemplate);

    Spark.get("/user/reportes/evolucion", reportController::reporteEvolucion, engineTemplate);

    Spark.get("/user/reportes/composicion", reportController::reporteComposicion, engineTemplate);
  }
}
