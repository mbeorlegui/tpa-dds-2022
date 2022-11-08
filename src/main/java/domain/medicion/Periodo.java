package domain.medicion;

import lombok.Getter;

@Getter
public class Periodo {
    private int mes;
    private int anio;

    public Periodo(int mes, int anio){
        this.mes = mes;
        this.anio = anio;
    }
    
}
