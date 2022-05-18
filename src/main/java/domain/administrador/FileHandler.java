package domain.administrador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FileHandler {
  // Las instancias de esta clase abren el archivo y contrasta con todas esas palabras
  // cada vez que se requiera chequear la validez de las contraseñas
  static final String COMMON_PASSWORDS_LOCATION = "data/commonly_used_passwords.txt";

  public boolean palabraEstaEnArchivo(String unPassword) {
    Scanner unArchivo = openFile();
    while (unArchivo.hasNextLine()) {
      String line = unArchivo.nextLine();
      if (line.equals(unPassword)) {
        return true;
      }
    }
    return false;
  }

  private Scanner openFile() {
    try {
      return new Scanner(new File(COMMON_PASSWORDS_LOCATION), "UTF-8");
    } catch (FileNotFoundException e) {
      System.out.println(
          "Error al abrir archivo, verifique que el mismo se encuentre en "
              + COMMON_PASSWORDS_LOCATION);
      e.printStackTrace();
      return null;
    }
  }
}
