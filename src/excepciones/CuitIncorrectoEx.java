package excepciones;

public class CuitIncorrectoEx extends Exception {
	
	public CuitIncorrectoEx(){
		super("-EL CUIT INGRESADO ES INCORRECTO-");
	}

}
