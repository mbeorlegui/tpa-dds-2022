package domain.medicion;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Periodicidad {
  MENSUAL {
    @Override
    public int diasTrabajados() {
      return 20;
    }

    @Override
    public List<String> getPeriodosIntermedios(String periodoInicio, String periodoFin) {
      // 06/2021 - 02/2022
      int mesInicio = Integer.parseInt(StringUtils.left(periodoInicio, 2));
      int mesFin = Integer.parseInt(StringUtils.left(periodoFin, 2));
      int anioInicio = Integer.parseInt(
          periodoInicio.substring(periodoInicio.length() - 4));
      int anioFin = Integer.parseInt(periodoFin.substring(periodoFin.length() - 4));
      List<String> periodos = new ArrayList<>();

      int anioAGuardar = anioInicio;
      int mesAGuardar = mesInicio;
      while ((anioAGuardar * 100 + mesAGuardar) <= (anioFin * 100 + mesFin)) {
        if (mesAGuardar >= 10) {
          periodos.add(mesAGuardar + "/" + anioAGuardar);
        } else {
          periodos.add("0" + mesAGuardar + "/" + anioAGuardar);
        }
        if (mesAGuardar == 12) {
          mesAGuardar = 1;
          anioAGuardar++;
        } else {
          mesAGuardar++;
        }
      }
      return periodos;
    }

    @Override
    public List<Periodo> getPeriodos(String periodoInicio, String periodoFin) {
      // 06/2021 - 02/2022
      int mesInicio = Integer.parseInt(StringUtils.left(periodoInicio, 2));
      int mesFin = Integer.parseInt(StringUtils.left(periodoFin, 2));
      int anioInicio = Integer.parseInt(
          periodoInicio.substring(periodoInicio.length() - 4));
      int anioFin = Integer.parseInt(periodoFin.substring(periodoFin.length() - 4));
      List<Periodo> periodos = new ArrayList<>();

      int anioAGuardar = anioInicio;
      int mesAGuardar = mesInicio;
      while ((anioAGuardar * 100 + mesAGuardar) <= (anioFin * 100 + mesFin)) {
        periodos.add(new Periodo(mesAGuardar, anioAGuardar));
        if (mesAGuardar == 12) {
          mesAGuardar = 1;
          anioAGuardar++;
        } else {
          mesAGuardar++;
        }
      }
      return periodos;
    }
  },
  ANUAL {
    @Override
    public int diasTrabajados() {
      return 20 * 12;
    }

    @Override
    public List<String> getPeriodosIntermedios(String periodoInicio, String periodoFin) {
      int anioInicio = Integer.parseInt(periodoInicio);
      int anioFin = Integer.parseInt(periodoFin);
      List<String> anios = new ArrayList<>();
      for (int i = anioInicio; i <= anioFin; i++) {
        anios.add(String.valueOf(i));
      }
      return anios;
    }

    @Override
    public List<Periodo> getPeriodos(String periodoInicio, String periodoFin) {
      int anioInicio = Integer.parseInt(periodoInicio);
      int anioFin = Integer.parseInt(periodoFin);
      List<Periodo> anios = new ArrayList<>();
      for (int i = anioInicio; i <= anioFin; i++) {
        anios.add(new Periodo(0,i));
      }
      return anios;
    }
  };

  public int diasTrabajados() {
    return 0;
  }

  public List<String> getPeriodosIntermedios(String periodoInicio, String periodoFin) {
    return Collections.emptyList();
  }

  public List<Periodo> getPeriodos(String periodoInicio, String periodoFin) {
    return Collections.emptyList();
  }
}