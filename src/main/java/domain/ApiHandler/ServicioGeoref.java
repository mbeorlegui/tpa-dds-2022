package domain.ApiHandler;

import retrofit2.Retrofit;
import retrofit2.*;

public class ServicioGeoref {
  private static ServicioGeoref instancia = null;
  private static final String urlAPI = "https://ddstpa.com.ar/api/";
  private Retrofit retrofit;

  private ServicioGeoref(){
    this.retrofit = new Retrofit.Builder().baseUrl(urlAPI).addConverterFactory(GsonConverterFactory.create()).build();

  }
}
