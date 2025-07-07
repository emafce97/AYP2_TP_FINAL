package dominio;

public class Cliente {
	
	private String cuit, alias;
	private TarjetaDebito tarjeta;
	
	public Cliente(String cuit, String alias, TarjetaDebito tarjeta) {
		this.cuit = cuit;
		this.alias = alias;
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

	public String getCuit() {
		return this.cuit;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setCuit(String cuit){
		this.cuit = cuit;
	}

	public void setAlias(String alias){
		this.alias = alias;
	}

	public TarjetaDebito getTarjetaDebito(){
		return this.tarjeta;
	}

	public void setTarjetaDebito(TarjetaDebito tb){
		this.tarjeta = tb;
	}
	
	public String toString() {
		return String.format("%s,%s", this.cuit, this.alias);
	}
	
	

}
