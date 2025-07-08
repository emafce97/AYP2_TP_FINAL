package dominio;

public class Cuenta {

	private String tipo, alias;;
	private double saldo;

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String toString() {
		return String.format("%s,%d", this.tipo, this.alias);
	}

}
