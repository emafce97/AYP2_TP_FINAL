package dominio;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {

	private String tipo, alias;
	private double saldo;
	private List<Transferencia> transEnviadas, transRecibidas;

	public Cuenta() {
		this.transEnviadas = new ArrayList<>();
		this.transRecibidas = new ArrayList<>();
	}

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

	public List<Transferencia> getTransEnviadas() {
		return transEnviadas;
	}

	public void setTransEnviadas(List<Transferencia> transEnviadas) {
		this.transEnviadas = transEnviadas;
	}

	public List<Transferencia> getTransRecibidas() {
		return transRecibidas;
	}

	public void setTransRecibidas(List<Transferencia> transRecibidas) {
		this.transRecibidas = transRecibidas;
	}

	public String toString() {
		return String.format("%s,%d", this.tipo, this.alias);
	}

}
