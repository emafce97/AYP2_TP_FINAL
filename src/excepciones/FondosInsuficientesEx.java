package excepciones;

public class FondosInsuficientesEx extends Exception {

    public FondosInsuficientesEx() {
        super("[ATENCION] El monto solicitado excede lo disponible...");
    }

}
