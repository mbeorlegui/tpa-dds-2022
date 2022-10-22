package domain.server;

import domain.controllers.HomeController;
import domain.controllers.SigninController;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
  public static void configure() {
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
    HomeController homeController = new HomeController();
    SigninController signinController = new SigninController();

    DebugScreen.enableDebugScreen();

    // Spark.staticFiles.location("public"); --> carpeta en la que se encuentran los .css dentro de la carpeta resouces

    Spark.get("/home", homeController::index, engineTemplate);
    Spark.redirect.get("/", "/home");

    Spark.get("/signin", signinController::index, engineTemplate);
    Spark.post("/session", signinController::post, engineTemplate);

  }
}
