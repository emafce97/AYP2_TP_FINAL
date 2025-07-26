package dominio;

public class Transferencia {

    public static int ID;

    private String origen, destino, concepto;
    private double monto;
    private int id;

    public Transferencia(String origen, String destino, String concepto, double monto) {
        this.origen = origen;
        this.destino = destino;
        this.concepto = concepto;
        this.monto = monto;
        this.id = Transferencia.ID++;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getId() {
        return this.id;
    }

}
