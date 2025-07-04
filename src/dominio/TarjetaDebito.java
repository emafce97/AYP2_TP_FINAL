package dominio;

public class TarjetaDebito {
	
	private short pin;
	private int numTarjeta, cuit;
	
	public TarjetaDebito(int numTarjeta, short pin, int cuit) {
		this.numTarjeta = numTarjeta;
		this.pin = pin;
		this.cuit = cuit;
	}
	
	public String toString() {
		return String.format("%d.%d.%d", this.numTarjeta, this.pin, this.cuit);
	}
	

}
