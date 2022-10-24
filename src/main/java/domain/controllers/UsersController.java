package domain.controllers;

import domain.administrador.RepoUsuarios;
import domain.administrador.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsersController {
  public ModelAndView index(Request request, Response response) {
    return new ModelAndView(null, "login.hbs");
  }

  public ModelAndView post(Request request, Response response) {
    String usuario = request.queryParams("nombre");
    String password = request.queryParams("password");

    System.out.println(usuario);
    // System.out.println("Un Usuario: " + RepoUsuarios.getInstance().findByUsername("matias").getUser() + RepoUsuarios.getInstance().findByUsername("matias").getPassword() );

    Usuario usuarioEncontrado = RepoUsuarios.getInstance().findByUsername(usuario);

    if (usuarioEncontrado == null ||
        !usuarioEncontrado.getPassword().equals(password)) {
      return new ModelAndView(null, "login.hbs");
    }

    request.session().attribute("usuario_logueado", usuario);
    request.session().attribute("tipo_usuario", usuarioEncontrado.getTipoUsuario());
    response.redirect("/home");
    return null;
  }
}
