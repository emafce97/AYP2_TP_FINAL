package excepciones;

public class CuitIncorrectoEx extends Exception {

	public CuitIncorrectoEx() {
		super("[ATENCION] El CUIT ingresado es incorrecto...");
	}

}
