package domain.server;

import domain.controllers.HomeController;
import domain.controllers.RequestController;
import domain.controllers.UsersController;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public static void configure() {
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
    HomeController homeController = new HomeController();
    UsersController usersController = new UsersController();
    RequestController requestController = new RequestController();

    DebugScreen.enableDebugScreen();

    Spark.staticFiles.location("public"); // --> carpeta en la que se encuentran los .css dentro de la carpeta resouces

    Spark.get("/home", homeController::index, engineTemplate);
    Spark.redirect.get("/", "/home");

    Spark.get("/login", usersController::index, engineTemplate);
    Spark.post("/session", usersController::post, engineTemplate);
    Spark.post("/session/remove", usersController::delete, engineTemplate);

    Spark.before("/user/me/*", ((request, response) -> {
      if(request.session().attribute("usuario_logueado") == null) {
        response.redirect("/home");
      }
    }));

    Spark.get("/user/me/request", requestController::request, engineTemplate);

  }
}
