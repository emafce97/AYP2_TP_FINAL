package excepciones;

public class ClienteNoExisteEx extends Exception {

	public ClienteNoExisteEx() {
		super("[ATENCION] El cliente solicitado no existe...");
	}

}
