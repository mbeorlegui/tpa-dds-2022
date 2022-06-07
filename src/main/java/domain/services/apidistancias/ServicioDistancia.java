package domain.services.apidistancias;

import domain.services.apidistancias.entities.ResultadoDistancia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioDistancia {
  private static ServicioDistancia instancia = null;
  private static final String urlAPI = "https://ddstpa.com.ar/api/";
  private Retrofit retrofit;

  private ServicioDistancia(){
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static ServicioDistancia getInstancia() {
    if(instancia == null) {
      instancia = new ServicioDistancia();
    }
    return instancia;
  }

  public ResultadoDistancia distancia(int localidadOrigenId,
                                      String calleOrigen,
                                      int alturaOrigen,
                                      int localidadDestinoId,
                                      String calleDestino,
                                      int alturaDestino) throws IOException {
    DistanciaService distanciaService = this.retrofit.create(DistanciaService.class);
    Call<ResultadoDistancia> requestDistancia = distanciaService.obtenerDistancia(localidadOrigenId,
        calleOrigen,
        alturaOrigen,
        localidadDestinoId,
        calleDestino,
        alturaDestino,
        "Bearer AKTjssyxUH0yI/Hf6UfllCUKt5sZ2RrNN3QvaRsLvZ8="); // Hardcodeado pero bueno, es lo que hay jeje
    Response<ResultadoDistancia> responseDistancia =  requestDistancia.execute();
    return responseDistancia.body();
  }
}
