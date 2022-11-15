package domain.controllers;

import domain.administrador.Administrador;
import domain.administrador.RepoUsuarios;
import domain.administrador.UnidadEquivalenteCarbono;
import domain.medicion.Periodicidad;
import domain.medicion.Periodo;
import domain.organizacion.*;
import domain.reports.ReportGenerator;
import domain.reports.ReporteDeComposicion;
import javassist.NotFoundException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class ReportController {
  public ModelAndView calculadoraOrganizacion(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<Organizacion> organizaciones = RepoOrganizaciones.getInstance().getOrganizaciones();
    model.put("organizaciones", organizaciones);

    model.put("periodicidad", request.queryParams("periodicidad"));
    model.put("anio", request.queryParams("anio"));
    model.put("mes", request.queryParams("mes"));
    model.put("unidad", request.queryParams("unidad"));
    model.put("resultado","");

    if (request.queryParams("organizacion") != null) {
      Long organizacionId = Long.parseLong(request.queryParams("organizacion"));
      Periodicidad periodicidad = request.queryParams("periodicidad").equals("anual") ? Periodicidad.ANUAL : Periodicidad.MENSUAL;
      String periodoDeImputacion;
      UnidadEquivalenteCarbono unidadEquivalenteCarbono;
      String unidadResultado = "";
      Integer anio = Integer.parseInt(request.queryParams("anio"));
      Integer mes = Integer.parseInt(request.queryParams("mes"));
      if(periodicidad == Periodicidad.ANUAL) {
        periodoDeImputacion = anio.toString();
      } else {
        if (mes < 10) {
          periodoDeImputacion = "0" + mes.toString() + "/" + anio.toString();
        } else {
          periodoDeImputacion = mes.toString() + "/" + anio.toString();
        }
      }
      switch (Integer.parseInt(request.queryParams("unidad"))) {
        case 0:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.GRAMO;
          unidadResultado = "g";
          break;
        case 1:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          unidadResultado = "kg";
          break;
        case 2:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.TONELADA;
          unidadResultado = "t";
          break;
        default:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          unidadResultado = "kg";
          break;
      }
      Organizacion organizacion = RepoOrganizaciones.getInstance().getOrganizacion(organizacionId);
      model.put("resultado", organizacion.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadEquivalenteCarbono));
      model.put("unidad", unidadResultado);
    }
    return new ModelAndView(model, "calculadoraOrganizacion.hbs");
  }

  public ModelAndView calculadoraSectorTerritorial(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<SectorTerritorial> sectoresTerritoriales = RepoSectoresTerritoriales.getInstance().getSectoresTerritoriales();
    model.put("sectores_territoriales", sectoresTerritoriales);

    model.put("periodicidad", request.queryParams("periodicidad"));
    model.put("anio", request.queryParams("anio"));
    model.put("mes", request.queryParams("mes"));
    model.put("unidad", request.queryParams("unidad"));

    if (request.queryParams("sector_territorial") != null) {
      Long organizacionId = Long.parseLong(request.queryParams("sector_territorial"));
      Periodicidad periodicidad = request.queryParams("periodicidad").equals("anual") ? Periodicidad.ANUAL : Periodicidad.MENSUAL;
      String periodoDeImputacion;
      UnidadEquivalenteCarbono unidadEquivalenteCarbono;
      String unidadResultado = "";
      Integer anio = Integer.parseInt(request.queryParams("anio"));
      Integer mes = Integer.parseInt(request.queryParams("mes"));
      if(periodicidad == Periodicidad.ANUAL) {
        periodoDeImputacion = anio.toString();
      } else {
        if (mes < 10) {
          periodoDeImputacion = "0" + mes.toString() + "/" + anio.toString();
        } else {
          periodoDeImputacion = mes.toString() + "/" + anio.toString();
        }
      }
      switch (Integer.parseInt(request.queryParams("unidad"))) {
        case 0:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.GRAMO;
          unidadResultado = "g";
          break;
        case 1:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          unidadResultado = "kg";
          break;
        case 2:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.TONELADA;
          unidadResultado = "t";
          break;
        default:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          unidadResultado = "kg";
          break;
      }
      SectorTerritorial sectorTerritorial = RepoSectoresTerritoriales.getInstance().getSectorTerritorial(organizacionId);
      model.put("resultado", sectorTerritorial.huellaDeCarbonoEnPeriodo(periodicidad, periodoDeImputacion, unidadEquivalenteCarbono));
      model.put("unidad", unidadResultado);
    }

    return new ModelAndView(model, "calculadoraSectorTerritorial.hbs");
  }

  public ModelAndView reporteHcTotal(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("tipo_reporte","Huella de Carbono");
    List<Organizacion> organizaciones = RepoOrganizaciones.getInstance().getOrganizaciones();
    model.put("organizaciones", organizaciones);
    
    if (request.queryParams("tipo-entidad") == null){
      return new ModelAndView(model, "reporteHcTotal.hbs");
    }else{
      Periodicidad periodicidad = request.queryParams("periodicidad").equals("anual")  ? Periodicidad.ANUAL : Periodicidad.MENSUAL;
      String periodoDeImputacion;
      UnidadEquivalenteCarbono unidadEquivalenteCarbono;
      Double resultado = 0.0;
      String nombreEntidad = "";
      Long idEntidad = 0L;
      if(periodicidad == Periodicidad.ANUAL) {
        periodoDeImputacion = request.queryParams("anio");
      } else {
        if (Integer.parseInt(request.queryParams("mes")) < 10) {
          periodoDeImputacion = "0" + request.queryParams("mes") + "/" + request.queryParams("anio");
        } else {
          periodoDeImputacion = request.queryParams("mes") + "/" + request.queryParams("anio");
        }
      }
      switch (Integer.parseInt(request.queryParams("unidad"))) {
        case 0:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.GRAMO;
          break;
        case 1:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
        case 2:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.TONELADA;
          break;
        default:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
      }
      if (request.queryParams("tipo-entidad").equals("organizacion")) {
        String entidad = request.queryParams("entidad");
        System.out.println("Entidad: " + entidad);
        Long organizacionId = Long.parseLong(entidad);
        Organizacion organizacion = RepoOrganizaciones.getInstance().getOrganizacion(organizacionId);
        resultado = ReportGenerator.getHuellaDeCarbonoEnPeriodo(organizacionId,
         periodicidad, periodoDeImputacion, unidadEquivalenteCarbono);
        nombreEntidad =  organizacion.getRazonSocial();
        idEntidad = organizacionId;
        System.out.println("entro org: " + nombreEntidad);
      }else if(request.queryParams("tipo-entidad").equals("sector")){
        Administrador user = (Administrador) RepoUsuarios.getInstance().getUsuarioByUsername(request.session().attribute("usuario_logueado"));
        List<SectorTerritorial> sectoresTerritoriales = RepoSectoresTerritoriales.getInstance().getSectoresTerritoriales();
        try{
          SectorTerritorial sectorTerritorial = sectoresTerritoriales.stream()
          .filter(st -> st.contieneOrganizacion(user.getOrganizacionAsociada().getId()))
          .findFirst()
          .orElseThrow(() -> new NotFoundException("La organizacion no tiene un sector asociado"));
          resultado = sectorTerritorial.huellaDeCarbonoEnPeriodo(
            periodicidad, periodoDeImputacion, unidadEquivalenteCarbono);
          nombreEntidad = sectorTerritorial.getNombre();
          idEntidad = sectorTerritorial.getId();
          System.out.println("entro sector: "+nombreEntidad);
        }catch(NotFoundException e){
          System.out.println("La organizacion no tiene un sector asociado");
        }
      }
      System.out.println(resultado);
      model.put("resultado", resultado);
      model.put("nombreEntidad", nombreEntidad);
      model.put("id", idEntidad);
      model.put("unidad", unidadEquivalenteCarbono);
      return new ModelAndView(model, "reporteHcTotal.hbs");
    }
  }

  public ModelAndView reporteEvolucion(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("tipo_reporte","Evolución");
    List<Organizacion> organizaciones = RepoOrganizaciones.getInstance().getOrganizaciones();
    model.put("organizaciones", organizaciones);

    if (request.queryParams("tipo-entidad") == null){
      return new ModelAndView(model, "reporteEvolucion.hbs");
    }else{
      Periodicidad periodicidad = request.queryParams("periodicidad").equals("anual")  ? Periodicidad.ANUAL : Periodicidad.MENSUAL;
      String periodoDeImputacionInicio;
      String periodoDeImputacionFin;
      UnidadEquivalenteCarbono unidadEquivalenteCarbono;
      List<Double> resultado = new ArrayList<>();
      List<Periodo> periodicidadList = new ArrayList<>();
      if(periodicidad == Periodicidad.ANUAL) {
        periodoDeImputacionInicio = request.queryParams("anio1");
        periodoDeImputacionFin = request.queryParams("anio2");
      } else {
        if (Integer.parseInt(request.queryParams("mes1")) < 10) {
          periodoDeImputacionInicio = "0" + request.queryParams("mes1") + "/" + request.queryParams("anio1");
        } else {
          periodoDeImputacionInicio = request.queryParams("mes1") + "/" + request.queryParams("anio1");
        }
        if (Integer.parseInt(request.queryParams("mes2")) < 10) {
          periodoDeImputacionFin = "0" + request.queryParams("mes2") + "/" + request.queryParams("anio2");
        } else {
          periodoDeImputacionFin = request.queryParams("mes2") + "/" + request.queryParams("anio2");
        }
      }
      switch (Integer.parseInt(request.queryParams("unidad"))) {
        case 0:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.GRAMO;
          break;
        case 1:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
        case 2:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.TONELADA;
          break;
        default:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
      }
      if (request.queryParams("tipo-entidad").equals("organizacion")) {
        String entidad = request.queryParams("entidad");
        System.out.println("Entidad: " + entidad);
        Long organizacionId = Long.parseLong(entidad);
        resultado = ReportGenerator.getEvolucionHcDeOrganizacion(organizacionId, periodicidad,
        periodoDeImputacionInicio, periodoDeImputacionFin, unidadEquivalenteCarbono);
      }else if(request.queryParams("tipo-entidad").equals("sector")){
        Administrador user = (Administrador) RepoUsuarios.getInstance().getUsuarioByUsername(request.session().attribute("usuario_logueado"));
        List<SectorTerritorial> sectoresTerritoriales = RepoSectoresTerritoriales.getInstance().getSectoresTerritoriales();
        try{
          SectorTerritorial sectorTerritorial = sectoresTerritoriales.stream()
          .filter(st -> st.contieneOrganizacion(user.getOrganizacionAsociada().getId()))
          .findFirst()
          .orElseThrow(() -> new NotFoundException("La organizacion no tiene un sector asociado"));
          resultado = ReportGenerator.getEvolucionHcDeSector(sectorTerritorial.getId(),
            periodicidad, periodoDeImputacionInicio, periodoDeImputacionFin, unidadEquivalenteCarbono);
        }catch(NotFoundException e){
          System.out.println("La organizacion no tiene un sector asociado");
        }
      }
      periodicidadList = periodicidad.getPeriodos(periodoDeImputacionInicio, periodoDeImputacionFin);
      model.put("resultado", resultado);
      model.put("periodicidad", new Gson().toJson(periodicidadList));
      return new ModelAndView(model, "reporteEvolucion.hbs");
    }
    
  }

  public ModelAndView reporteComposicion(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("tipo_reporte","Composición");
    List<Organizacion> organizaciones = RepoOrganizaciones.getInstance().getOrganizaciones();
    model.put("organizaciones", organizaciones);

    if (request.queryParams("tipo-entidad") == null){
      return new ModelAndView(model, "reporteComposicion.hbs");
    }else{
      Periodicidad periodicidad = request.queryParams("periodicidad").equals("anual")  ? Periodicidad.ANUAL : Periodicidad.MENSUAL;
      String periodoDeImputacion;
      UnidadEquivalenteCarbono unidadEquivalenteCarbono;
      ReporteDeComposicion resultado = new ReporteDeComposicion(0, 0);
      String nombreEntidad = "";
      Long idEntidad = 0L;
      if(periodicidad == Periodicidad.ANUAL) {
        periodoDeImputacion = request.queryParams("anio");
      } else {
        if (Integer.parseInt(request.queryParams("mes")) < 10) {
          periodoDeImputacion = "0" + request.queryParams("mes") + "/" + request.queryParams("anio");
        } else {
          periodoDeImputacion = request.queryParams("mes") + "/" + request.queryParams("anio");
        }
      }
      switch (Integer.parseInt(request.queryParams("unidad"))) {
        case 0:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.GRAMO;
          break;
        case 1:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
        case 2:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.TONELADA;
          break;
        default:
          unidadEquivalenteCarbono = UnidadEquivalenteCarbono.KILOGRAMO;
          break;
      }
      if (request.queryParams("tipo-entidad").equals("organizacion")) {
        Long organizacionId = Long.parseLong(request.queryParams("entidad"));
        Organizacion organizacion = RepoOrganizaciones.getInstance().getOrganizacion(organizacionId);
        resultado = ReportGenerator.composicionHcDeOrganizacion(organizacionId,
         periodicidad, periodoDeImputacion, unidadEquivalenteCarbono);
        nombreEntidad =  organizacion.getRazonSocial();
        idEntidad = organizacionId;
        System.out.println("entro org: "+nombreEntidad);
      }else if(request.queryParams("tipo-entidad").equals("sector")){
        Administrador user = (Administrador) RepoUsuarios.getInstance().getUsuarioByUsername(request.session().attribute("usuario_logueado"));
        List<SectorTerritorial> sectoresTerritoriales = RepoSectoresTerritoriales.getInstance().getSectoresTerritoriales();
        try{
          SectorTerritorial sectorTerritorial = sectoresTerritoriales.stream()
          .filter(st -> st.contieneOrganizacion(user.getOrganizacionAsociada().getId()))
          .findFirst()
          .orElseThrow(() -> new NotFoundException("La organizacion no tiene un sector asociado"));
          resultado = ReportGenerator.composicionHcDeSectorTerritorial(sectorTerritorial.getId(),
           periodicidad, periodoDeImputacion, unidadEquivalenteCarbono);
          nombreEntidad = sectorTerritorial.getNombre();
          idEntidad = sectorTerritorial.getId();
          System.out.println("entro sector: "+nombreEntidad);
        }catch(NotFoundException e){
          System.out.println("La organizacion no tiene un sector asociado");
        }
      }
      System.out.println(new Gson().toJson(resultado));
      model.put("hcTrayectos", resultado.getHcTrayectos());
      model.put("hcMediciones", resultado.getHcMediciones());
      model.put("nombreEntidad", nombreEntidad);
      model.put("id", idEntidad);
      model.put("unidad", unidadEquivalenteCarbono);
      return new ModelAndView(model, "reporteComposicion.hbs");
    }
  }

  public ModelAndView reportes(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    model.put("tipo_reporte","");
    return new ModelAndView(model, "layoutReporte.hbs");
  }
}
