package dominio;

import excepciones.ClienteNoExisteEx;
import excepciones.FondosInsuficientesEx;
import excepciones.MontoIncorrectoEx;
import dominio.Banco;

public class Cliente {

	private String cuit, alias;
	private Cuenta cc, ca, cau;

	public Cliente(String cuit, String alias) {
		this.cuit = cuit;
		this.alias = alias;
		this.cc = new Cuenta();
		this.ca = new Cuenta();
		this.cau = new Cuenta();
	}

	/**
	 * Muestra los datos y el estado de la cuenta del cliente
	 */
	public void mostrarDatos() {
		String datos = String.format(
				"** DATOS DE LA CUENTA **\n-CUIT: %s\n-Alias: %s\n-Saldo CC: $ %.2f\n-Saldo CAP: $ %.2f\n-Saldo CAD: USD$ %.2f",
				this.cuit, this.alias, this.cc.getSaldo(), this.ca.getSaldo(), this.cau.getSaldo());
		System.out.println(datos);
	}

	/**
	 * Se obtiene una cuenta segun su codigo
	 * 
	 * @param codigo
	 * @return
	 */
	private Cuenta getCuentaSegunTipo(String tipoCuenta) {
		switch (tipoCuenta) {
			case "01":
				return this.ca;
			case "02":
				return this.cc;
			case "03":
				return this.cau;
			default:
				return null;
		}
	}

	/**
	 * Se retira dinero
	 * 
	 * @param monto
	 */
	public void retirarEfectivo(double monto, String tipoCuenta) throws FondosInsuficientesEx, MontoIncorrectoEx {
		if (monto < 0) {
			throw new MontoIncorrectoEx();
		}
		Cuenta cuenta = this.getCuentaSegunTipo(tipoCuenta);
		if (monto > cuenta.getSaldo()) {
			throw new FondosInsuficientesEx();
		}
		double nuevoSaldo = cuenta.getSaldo() - monto;
		cuenta.setSaldo(nuevoSaldo);
	}

	/**
	 * Se compra un monto especificado de dolares
	 * 
	 * @param monto
	 */
	public void comprarDolares(String tipoCuenta, double monto) throws FondosInsuficientesEx, MontoIncorrectoEx {
		if (monto < 0) {
			throw new MontoIncorrectoEx();
		}

		Cuenta cuenta = this.getCuentaSegunTipo(tipoCuenta);
		double cantDolaresQueSePuedeComprar = this.simularCambioPesosDolar(cuenta);

		if (monto > cantDolaresQueSePuedeComprar) {
			throw new FondosInsuficientesEx();
		}

		double nuevoSaldo = this.cau.getSaldo() + monto;
		this.cau.setSaldo(nuevoSaldo);
		nuevoSaldo = cuenta.getSaldo() - (monto * Banco.CAMBIO_DOLAR_PESOS);
		cuenta.setSaldo(nuevoSaldo);
	}

	/**
	 * Da un monto aproximado de cuantos dolares se pueden comprar segun el saldo de
	 * una cuenta
	 * 
	 * @param cuenta
	 * @return
	 */
	private double simularCambioPesosDolar(Cuenta cuenta) {
		return cuenta.getSaldo() / Banco.CAMBIO_DOLAR_PESOS;
	}

	/**
	 * Se depositan pesos o dolares
	 * 
	 * @param monto
	 * @param tipoCuenta
	 * @throws FondosInsuficientesEx
	 */
	public void depositar(double monto, String tipoCuenta) throws FondosInsuficientesEx, MontoIncorrectoEx {
		if (monto < 0) {
			throw new MontoIncorrectoEx();
		}
		Cuenta cuenta = this.getCuentaSegunTipo(tipoCuenta);
		if (monto > cuenta.getSaldo()) {
			throw new FondosInsuficientesEx();
		}
		double nuevoSaldo = cuenta.getSaldo() + monto;
		cuenta.setSaldo(nuevoSaldo);
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
		if (monto < 0) {
			throw new MontoIncorrectoEx();
		}

		Cuenta cuentaOrigen = this.getCuentaSegunTipo(tipoCuenta);

		if (monto > cuentaOrigen.getSaldo()) {
			throw new FondosInsuficientesEx();
		}

		if (otroCliente == null) {
			throw new ClienteNoExisteEx();
		}

		Transferencia transferencia = new Transferencia(this.getAlias(), otroCliente.getAlias(), concepto, monto);

		double nuevoSaldo = cuentaOrigen.getSaldo() - monto;
		cuentaOrigen.setSaldo(nuevoSaldo);
		cuentaOrigen.getTransEnviadas().add(transferencia);
		otroCliente.recibirTransferencia(tipoCuenta, transferencia);
	}

	/**
	 * Se recibe una transferencia hacia un tipo de cuenta especifica
	 * 
	 * @param tipoCuenta
	 * @param transferencia
	 */
	public void recibirTransferencia(String tipoCuenta, Transferencia transferencia) {
		Cuenta cuenta = this.getCuentaSegunTipo(tipoCuenta);
		double nuevoSaldo = cuenta.getSaldo() + transferencia.getMonto();
		cuenta.setSaldo(nuevoSaldo);
		cuenta.getTransRecibidas().add(transferencia);
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
