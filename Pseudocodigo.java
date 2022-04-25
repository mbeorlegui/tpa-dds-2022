
class Administrador {
  String user;
  String password;
  
  constructor(String user, String password){
    this.user = requireNonNull(user, "El usuario no debe ser vacio!");
    this.password = requireNonNull(user, "La contrasenia no debe ser vacia!");
    validador.chequearValidez(password, user);
    this.password = password;
  }
}


// Utilizamos un singleton, ya que solamente queremos una instancia de validador
// que funciona auxiliarmente como chequeador de contraseñas y nada mas
class PasswordValidator {
  // Este objeto deberia almacenar todas las contraseñas comunes,
  // o abrir el archivo y contrastar con todas esas palabras cada 
  // vez que se requiera chequear la validez de las contraseñas
	private static final PasswordValidator INSTANCE = new PasswordValidator();
  String REGEX = "[a-zA-Z0-9_]*";
  public static instance() {
    return INSTANCE;
  }

  public Boolean chequearValidez(String password, String user) {
    if (password.length() < 8)
      throw new IllegalArgumentException("La contraseña debe tener más de 8 caracteres!");
    if (password.length() > 64)
      throw new IllegalArgumentException("La contraseña debe tener 64 caracteres o menos!");
    if (commonList.contains(password))
      throw new IllegalArgumentException("La contraseña se encuentra dentro de las mas comunes!");
    if (password.contains(user))
      throw new IllegalArgumentException("La contraseña no debe contener el nombre de usuario!");
    if (!password.matches(REGEX))
      throw new IllegalArgumentException("La contraseña contiene caracteres invalidos!");
    // Para la comprobacion de caracteres invalidos creo que deberiamos hacer una REGEX
    // que admita letras y catacteres medianamente convencionales, 
    // de ultima si nos dicen que faltan mas se pueden agregar tranquilamente a esa REGEX
    // Doc: https://www.geeksforgeeks.org/regular-expressions-in-java/#:~:text=Regular%20Expressions%20or%20Regex%20(in,Expressions%20are%20provided%20under%20java.
    return true;
  }
}
// Se instancia con:
//    validador = PasswordValidator.instance();