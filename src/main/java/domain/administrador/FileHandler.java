package domain.administrador;

import java.io.InputStream;
import java.util.Scanner;

public class FileHandler {
  // Las instancias de esta clase abren el archivo y contrasta con todas esas palabras
  // cada vez que se requiera chequear la validez de las contraseñas

  static final String COMMON_PASSWORDS_LOCATION = "public/data/commonly_used_passwords.txt";

  public boolean palabraEstaEnArchivo(String unPassword) {
    Scanner unArchivo = new Scanner(getFileFromResourceAsStream(), "UTF-8");
    while (unArchivo.hasNextLine()) {
      String line = unArchivo.nextLine();
      if (line.equals(unPassword)) {
        unArchivo.close();
        return true;
      }
    }
    return false;
  }

  private InputStream getFileFromResourceAsStream() {
    // The class loader that loaded the class
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(COMMON_PASSWORDS_LOCATION);

    // the stream holding the file content
    if (inputStream == null) {
      throw new IllegalArgumentException("Archivo no encontrado en la ruta: " + COMMON_PASSWORDS_LOCATION);
    } else {
      return inputStream;
    }
  }
}
