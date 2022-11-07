package domain.controllers;

import domain.administrador.RepoUsuarios;
import domain.administrador.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class UsersController {
  public ModelAndView login(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    return new ModelAndView(model, "login.hbs");
  }

  public ModelAndView signin(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    return new ModelAndView(model, "signin.hbs");
  }

  public ModelAndView loginError(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("usuario_logueado", request.session().attribute("usuario_logueado"));
    return new ModelAndView(model, "loginError.hbs");
  }

  public ModelAndView post(Request request, Response response) {
    String usuario = request.queryParams("nombre");
    String password = request.queryParams("password");

    System.out.println(usuario);
    // System.out.println("Un Usuario: " + RepoUsuarios.getInstance().findByUsername("matias").getUser() + RepoUsuarios.getInstance().findByUsername("matias").getPassword() );
    Usuario usuarioEncontrado;

    try {
      usuarioEncontrado = RepoUsuarios.getInstance().findByUsername(usuario);
    } catch (Exception e) {
      // Si no encuentra el usuario, redirige a pagina de error de login
      response.redirect("/loginError");
      return null;
    }

    if (usuarioEncontrado == null ||
        !usuarioEncontrado.getPassword().equals(password)) {
      // Si el usuario y la contraseña no coinciden, redirige a pagina de error de login
      // TODO: Analizar crear una ventana a parte con el error
      response.redirect("/loginError");
      return null;
    }
    
    request.session().attribute("usuario_logueado", usuario);
    // request.session().attribute("tipo_usuario", usuarioEncontrado.getTipoUsuario());
    System.out.println(usuarioEncontrado.tipoUsuario());
    request.session().attribute("tipo_usuario", usuarioEncontrado.tipoUsuario());
    request.session().attribute("mensaje", "Bienvenido "+usuario+"!");
    response.redirect("/home");
    return null;
  }

  public ModelAndView delete(Request req, Response res) {
    req.session().removeAttribute("usuario_logueado");
    req.session().removeAttribute("tipo_usuario");
    req.session().attribute("mensaje", "Cerraste sesión correctamente.");
    res.redirect("/home");
    return null;
  }
}
