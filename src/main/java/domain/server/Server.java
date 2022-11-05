package domain.server;

import domain.dbrunner.Runner;
import spark.Spark;

public class Server {
  public static void main(String[] args) {
    Bootstrap.init();
    if(System.getenv("PORT") != null) {
      Spark.port(Integer.parseInt(System.getenv("PORT")));
    } else {
      Spark.port(9000);
    }
    Router.configure();
  }
}
