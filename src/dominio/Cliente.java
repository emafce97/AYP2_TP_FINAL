package dominio;

public class Cliente {
	
	private long cuit;
	private TarjetaDebito tarjeta;
	private String alias;
	
	public Cliente(String cuit, String alias, TarjetaDebito tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	public void retirarEfectivo(double monto) {
		
	}
	
	public void comprarDolares(double monto) {
		
	}
	
	public void depositar(int opcion, double monto) {
		
	}
	
	public void transferir(Cuenta otraCUenta) {
		
	}

	public long getCuit() {
		return cuit;
	}

	public String getAlias() {
		return alias;
	}
	
	public String toString() {
		return String.format("%s,%s", this.cuit, this.alias);
	}
	
	

}
