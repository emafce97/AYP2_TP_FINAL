package dominio;

import excepciones.ClienteNoExisteEx;
import excepciones.FondosInsuficientesEx;
import excepciones.MontoIncorrectoEx;
import dominio.Banco;

public class Cliente {

	private String cuit, alias;
	private Cuenta cc, ca, cad, aux;

	public Cliente(String cuit, String alias) {
		this.cuit = cuit;
		this.alias = alias;
		this.cc = new Cuenta();
		this.ca = new Cuenta();
		this.cad = new Cuenta();
	}

	public Cliente(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Muestra los datos y el estado de la cuenta del cliente
	 */
	public void mostrarDatos() {
		String datos = String.format(
				"** DATOS DE LA CUENTA **\n-CUIT: %s\n-Alias: %s\n-Saldo CC: $ %.2f\n-Saldo CAP: $ %.2f\n-Saldo CAD: USD$ %.2f",
				this.cuit, this.alias, this.cc.getSaldo(), this.ca.getSaldo(), this.cad.getSaldo());
		System.out.println(datos);
	}

	/**
	 * Se retira dinero
	 * 
	 * @param monto
	 */
	public void retirarEfectivo(double monto, String tipoCuenta) throws FondosInsuficientesEx, MontoIncorrectoEx {
		if (this.montoIncorrecto(monto)) {
			throw new MontoIncorrectoEx();
		}

		this.aux = this.getCuentaSegunTipo(tipoCuenta);
		double saldo = aux.getSaldo();

		if (this.fondosInsuficientes(monto, saldo)) {
			throw new FondosInsuficientesEx();
		}

		aux.setSaldo(saldo - monto);
	}

	/**
	 * Se compra un monto especificado de dolares, unicamente con las dos cuentas
	 * que manejan pesos
	 * 
	 * @param monto
	 */
	public void comprarDolares(String tipoCuenta, double monto) throws FondosInsuficientesEx, MontoIncorrectoEx {
		if (this.montoIncorrecto(monto)) {
			throw new MontoIncorrectoEx();
		}

		this.aux = this.getCuentaSegunTipo(tipoCuenta);
		double saldo = this.aux.getSaldo();

		if (this.fondosInsuficientes(monto, this.simularCambioPesosDolar(saldo))) {
			throw new FondosInsuficientesEx();
		}

		this.cad.setSaldo(this.cad.getSaldo() + monto);
		this.aux.setSaldo(saldo - (monto * Banco.CAMBIO_DOLAR_PESOS));
	}

	/**
	 * Se depositan pesos o dolares
	 * 
	 * @param monto
	 * @param tipoCuenta
	 * @throws FondosInsuficientesEx
	 */
	public void depositar(double monto, String tipoCuenta) throws MontoIncorrectoEx {
		if (this.montoIncorrecto(monto)) {
			throw new MontoIncorrectoEx();
		}
		this.aux = this.getCuentaSegunTipo(tipoCuenta);
		this.aux.setSaldo(this.aux.getSaldo() + monto);
	}

	/**
	 * Hace una transferencia a otra cuenta
	 * 
	 * @param tipoCuenta
	 * @param otroCliente
	 * @param monto
	 * @param concepto
	 * @throws MontoIncorrectoEx
	 * @throws FondosInsuficientesEx
	 * @throws ClienteNoExisteEx
	 */
	public void hacerTransferencia(String tipoCuenta, Cliente otroCliente, double monto, String concepto)
			throws MontoIncorrectoEx, FondosInsuficientesEx, ClienteNoExisteEx {
		if (this.montoIncorrecto(monto)) {
			throw new MontoIncorrectoEx();
		}

		this.aux = this.getCuentaSegunTipo(tipoCuenta);
		double saldo = this.aux.getSaldo();

		if (this.fondosInsuficientes(monto, saldo)) {
			throw new FondosInsuficientesEx();
		}

		if (otroCliente == null) {
			throw new ClienteNoExisteEx();
		}

		Transferencia transferencia = new Transferencia(this.getAlias(), otroCliente.getAlias(), concepto, monto);
		this.aux.setSaldo(saldo - monto);
		this.aux.agregarTransferenciaEnviada(transferencia);
		otroCliente.recibirTransferencia(tipoCuenta, transferencia);
	}

	/**
	 * Se recibe una transferencia hacia un tipo de cuenta especifica
	 * 
	 * @param tipoCuenta
	 * @param transferencia
	 */
	public void recibirTransferencia(String tipoCuenta, Transferencia transferencia) {
		this.aux = this.getCuentaSegunTipo(tipoCuenta);
		this.aux.setSaldo(this.aux.getSaldo() + transferencia.getMonto());
		this.aux.agregarTransferenciaRecibida(transferencia);
	}

	/**
	 * Verifica si un monto es menor a cero
	 * 
	 * @param monto
	 * @return
	 */
	private boolean montoIncorrecto(double monto) {
		return monto < 0;
	}

	/**
	 * Verifica si los fondos disponibles en la cuenta son suficientes para cubrir
	 * lo que se quiere gastar
	 * 
	 * @param monto
	 * @param saldo
	 * @return
	 */
	private boolean fondosInsuficientes(double monto, double saldo) {
		return monto > saldo;
	}

	/**
	 * Se obtiene un tipo de cuenta segun su codigo
	 * 
	 * @param codigo
	 * @return
	 */
	public Cuenta getCuentaSegunTipo(String tipoCuenta) {
		switch (tipoCuenta) {
			case "01":
				return this.ca;
			case "02":
				return this.cc;
			case "03":
				return this.cad;
			default:
				return null;
		}
	}

	/**
	 * Da un monto aproximado de cuantos dolares se pueden comprar segun el saldo de
	 * una cuenta
	 * 
	 * @param cuenta
	 * @return
	 */
	private double simularCambioPesosDolar(double saldo) {
		return saldo / Banco.CAMBIO_DOLAR_PESOS;
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
