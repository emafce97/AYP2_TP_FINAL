package excepciones;

public class NoHayClientesRegistradosEx extends Exception {

    public NoHayClientesRegistradosEx() {
        super("[ERROR] No hay clientes cargador por el momento...");
    }

}
