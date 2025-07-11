package excepciones;

public class ClienteRegistradoEx extends Exception {

	public ClienteRegistradoEx() {
		super("[ATENCION] El cliente ya esta registrado...");
	}

}
