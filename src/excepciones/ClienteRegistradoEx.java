package excepciones;

public class ClienteRegistradoEx extends Exception {
	
	public ClienteRegistradoEx() {
		super("-EL CLIENTE YA ESTA REGISTRADO-");
	}

}
