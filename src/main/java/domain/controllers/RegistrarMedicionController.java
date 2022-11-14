package domain.controllers;

import com.google.gson.Gson;
import domain.administrador.Administrador;
import domain.administrador.RepoUsuarios;
import domain.administrador.Usuario;
import domain.medicion.*;
import domain.organizacion.CsvHandler;
import domain.organizacion.Organizacion;
import domain.organizacion.RepoOrganizaciones;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

public class RegistrarMedicionController {
  private CsvHandler csvHandler;
  public ModelAndView registrarMedicionCsv(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("mensaje", request.session().attribute("mensaje"));
    request.session().removeAttribute("mensaje");
    return new ModelAndView(model, "registrarMedicionCsv.hbs");
  }

  @SneakyThrows
  public ModelAndView procesarArchivo(Request request, Response response) {
    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp")); // Sin esto rompe, no me pregunten por que

    // Se obtienen mediciones del csv y se persisten
    InputStream input = request.raw().getPart("uploaded_file").getInputStream(); // getPart needs to use same "name" as input field in for
    csvHandler = new CsvHandler();
    List<Medicion> mediciones = csvHandler.getMediciones(input);
    mediciones.forEach(RepoMediciones.getInstance()::save);

    // Se obtiene la organizacion asociada al usuario de la sesion y se le agregan las mediciones
    Administrador user = (Administrador) RepoUsuarios.getInstance().getUsuarioByUsername(request.session().attribute("usuario_logueado"));
    input.close();
    Organizacion org = RepoOrganizaciones.getInstance().findByRazonZocial(user.getOrganizacionAsociada().getRazonSocial());
    org.agregarMediciones(mediciones);
    RepoOrganizaciones.getInstance().update(org);

    request.session().attribute("mensaje", "Mediciones registradas");
    response.redirect("/user/admin/registrarMedicionCsv");
    return null;
  }

  @SneakyThrows
  public ModelAndView procesarMedicionParticular(Request request, Response response) {
    List<TipoConsumo> tiposConsumos = RepoTiposConsumos.getInstance().getTiposConsumos();
    String valor = request.queryParams("valor");
    String periodicidad = request.queryParams("periodicidad");
    String periodoImputacion = request.queryParams("periodoImputacion");
    int tipoDeConsumo = Integer.parseInt(request.queryParams("tipoDeConsumo")) - 1;
    MedicionRead medicionRead = new MedicionRead(tiposConsumos.get(tipoDeConsumo).getNombre(), valor, periodicidad.toUpperCase(), periodoImputacion);
    Medicion medicion = new MedicionAdapter().adaptarMedicion(medicionRead);
    RepoMediciones.getInstance().save(medicion);

    // Se obtiene la organizacion asociada al usuario de la sesion y se le agregan las mediciones
    Administrador user = (Administrador) RepoUsuarios.getInstance().getUsuarioByUsername(request.session().attribute("usuario_logueado"));
    Organizacion org = RepoOrganizaciones.getInstance().findByRazonZocial(user.getOrganizacionAsociada().getRazonSocial());
    org.agregarMedicion(medicion);
    RepoOrganizaciones.getInstance().update(org);
    System.out.println(medicion);
    request.session().attribute("mensaje", "Mediciones registradas");
    response.redirect("/user/admin/registrarMedicionParticular");
    return null;
  }

  public ModelAndView registrarMedicionParticular(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<TipoConsumo> tipos_consumo = RepoTiposConsumos.getInstance().getTiposConsumos();
    model.put("tipos_consumo", tipos_consumo);
    return new ModelAndView(model, "registrarMedicionParticular.hbs");
  }
}
