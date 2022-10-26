package domain.server;

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
    Spark.get("/loginError", usersController::loginError, engineTemplate);
    Spark.post("/session", usersController::post, engineTemplate);
    Spark.post("/session/remove", usersController::delete, engineTemplate);

    Spark.before("/user/me/*", ((request, response) -> {
      if (request.session().attribute("usuario_logueado") == null) {
        response.redirect("/home");
      }
    }));

    Spark.get("/user/me/request", requestController::request, engineTemplate);

    Spark.get("/user/me/registrarMedicionCsv", registrarMedicionController::registrarMedicionCsv, engineTemplate);

    Spark.get("/user/me/registrarMedicionParticular", registrarMedicionController::registrarMedicionParticular, engineTemplate);

    Spark.get("/user/me/registrarTrayecto", registrarTrayectosController::registrarTrayecto, engineTemplate);

    Spark.get("/user/me/calculadora", reportController::calculadora, engineTemplate);

    Spark.get("/user/me/aceptacionVinculacion", requestController::aceptarVinculacion, engineTemplate);

    Spark.get("/user/me/reporteHcTotal", reportController::reporteHcTotal, engineTemplate);
  }
}
