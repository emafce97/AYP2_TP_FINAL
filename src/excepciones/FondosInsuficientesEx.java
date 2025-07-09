package excepciones;

public class FondosInsuficientesEx extends Exception {

    public FondosInsuficientesEx() {
        super("El monto solicitado no puede ser retirado");
    }

}
