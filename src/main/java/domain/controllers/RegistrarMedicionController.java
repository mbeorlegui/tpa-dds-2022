package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.medicion.RepoTiposConsumos;
import domain.medicion.TipoConsumo;

public class RegistrarMedicionController {
  public ModelAndView registrarMedicionCsv(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    return new ModelAndView(model, "registrarMedicionCsv.hbs");
  }

  public ModelAndView registrarMedicionParticular(Request request, Response response) {
    Map<String, Object> model = new IndexController().llenarIndex(request);
    List<TipoConsumo> tipos_consumo = RepoTiposConsumos.getInstance().getTiposConsumos();
    model.put("tipos_consumo", tipos_consumo);
    return new ModelAndView(model, "registrarMedicionParticular.hbs");
  }
}
