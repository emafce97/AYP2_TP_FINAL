package excepciones;

public class ClienteNoExisteEx extends Exception {
	
	public ClienteNoExisteEx() {
		super("-NO EXISTE EL CLIENTE-");
	}

}
