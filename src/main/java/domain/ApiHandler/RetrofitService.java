package domain.ApiHandler;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
  @GET("distancia")
  Call<ResultadoDistancia> obtenerDistancia(@Query("localidadOrigenId") int localidadOrigenId,
                                            @Query("calleOrigen") String calleOrigen,
                                            @Query("alturaOrigen") int alturaOrigen,
                                            @Query("localidadDestinoId") int localidadDestinoId,
                                            @Query("calleDestino") int calleDestino,
                                            @Query("alturaDestino") int alturaDestino);
}
