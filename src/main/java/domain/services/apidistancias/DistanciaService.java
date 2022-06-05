package domain.services.apidistancias;

import domain.services.apidistancias.entities.ResultadoDistancia;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface DistanciaService {
  @GET("distancia")
  Call<ResultadoDistancia> obtenerDistancia(@Query("localidadOrigenId") int localidadOrigenId,
                                            @Query("calleOrigen") String calleOrigen,
                                            @Query("alturaOrigen") int alturaOrigen,
                                            @Query("localidadDestinoId") int localidadDestinoId,
                                            @Query("calleDestino") String calleDestino,
                                            @Query("alturaDestino") int alturaDestino,
                                            @Header("Authorization") String bearerAuth);
}
