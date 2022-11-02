package domain.controllers;

import domain.administrador.UsuarioGeneral;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

public class IndexController {
  public Map<String, Object> llenarIndex(Request request) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    if(request.session().attribute("tipo_usuario") != null) {
      if (request.session().attribute("tipo_usuario").toString().equals("UsuarioGeneral")) {
        model.put("usuario_general", "UsuarioGeneral");
      } else {
        System.out.println("Session tipo usuario: " + request.session().attribute("tipo_usuario").toString());
        model.put("usuario_administrador", "Administrador");
      }
    }
    return model;
  }
}
