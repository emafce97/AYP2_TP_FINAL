package dominio;
import java.util.*;

import excepciones.*;

public class Banco {
	
	private List<Cliente> clientesL;
	private Map<Integer, Cliente> clientesM;
	
	public Banco() {
		this.clientesL = new ArrayList<>();
		this.clientesM = new HashMap<>();
	}
	
	public void agregarCliente(Cliente cliente) throws ClienteRegistradoEx {
		if(this.existeCliente(cliente.getCuit())) {
			throw new ClienteRegistradoEx();
		}
		this.clientesL.add(cliente);
		this.clientesM.put(cliente.getCuit(), cliente);
	}
	
	public Cliente buscarCliente(int cuit) {
		return this.clientesM.get(cuit);
	}
	
	public void eliminarCliente(int cuit) throws ClienteNoExisteEx{
		if(this.existeCliente(cuit)) {
			Cliente cliente = this.buscarCliente(cuit);
			this.clientesL.remove(cliente);
			this.clientesM.remove(cuit);
		}else {
			throw new ClienteNoExisteEx();
		}
	}
	
	public void listarClientes() {
		if(!this.hayClientesRegistrados()) {
			System.out.println("No hay clientes registrados por el momento...");
		}else {
			System.out.println("Clientes registrados:");
			for(Cliente cliente : this.clientesL) {
				System.out.println(cliente);
			}
		}
	}
	
	private boolean existeCliente(int cuit) {
		return this.buscarCliente(cuit) != null;
	}
	
	private boolean hayClientesRegistrados() {
		return this.clientesL.isEmpty();
	}

}
