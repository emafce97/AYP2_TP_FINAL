package dominio;

public class CuentaCorriente extends Cuenta {

    private double saldoDescubierto;

    public CuentaCorriente(){
        this.setTipo("02");
    }

    public double getSaldoDescubierto(){
        return this.saldoDescubierto;
    }

    public void setSaldoDescubierto(double saldoDescubierto){
        this.saldoDescubierto = saldoDescubierto;
    }

    @Override
    public String toString(){
        return super.toString() + "$ " + this.getSaldo() + "$ " + this.saldoDescubierto;
    }

}
