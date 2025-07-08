package dominio;

public class CuentaAhorroPesos extends Cuenta{

    public CuentaAhorroPesos(){
        this.setTipo("01");
    }

    @Override
    public String toString(){
        return super.toString() + "$ " + this.getSaldo();
    }
    
}
