package dominio;

import excepciones.FondosInsuficientesEx;

public class Cliente {

	private String cuit, alias;
	private Cuenta cc, cap, cad;

	public Cliente(String cuit, String alias) {
		this.cuit = cuit;
		this.alias = alias;
		this.cc = new Cuenta();
		this.cap = new Cuenta();
		this.cad = new Cuenta();
	}

	/**
	 * Muestra los datos y el estado de la cuenta del cliente
	 */
	public void mostrarDatos() {
		String datos = String.format(
				"** DATOS DE LA CUENTA **\n-CUIT: %s\n-Alias: %s\n-Saldo CC: $ %.2f\n-Saldo CAP: $ %.2f\n-Saldo CAD: USD$ %.2f",
				this.cuit, this.alias, this.cc.getSaldo(), this.cap.getSaldo(), this.cad.getSaldo());
		System.out.println(datos);
	}

	/**
	 * Se retira dinero
	 * @param monto
	 */
	public void retirarEfectivo(double monto) throws FondosInsuficientesEx {

	}

	public void comprarDolares(double monto) {

	}

	public void depositar(int opcion, double monto) {

	}

	public void transferir(Cuenta otraCUenta) {

	}

	public String getCuit() {
		return this.cuit;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String toString() {
		return String.format("%s,%s", this.cuit, this.alias);
	}

}
