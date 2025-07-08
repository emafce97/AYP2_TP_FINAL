package dominio;

public class CuentaAhorroDolares extends Cuenta{

    public CuentaAhorroDolares(){
        this.setTipo("03");
    }

    @Override
    public String toString(){
        return super.toString() + "$USD " + this.getSaldo();
    }
    
}
