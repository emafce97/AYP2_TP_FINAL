package dominio;

import java.util.*;

import excepciones.*;

public class Banco {

	private List<Cliente> clientesL;
	private Map<String, Cliente> clientesM;
	private Set<String> aliasUsados;
	private List<String> palabras;
	private Random r;

	public Banco() {
		this.clientesL = new ArrayList<>();
		this.clientesM = new HashMap<>();
		this.aliasUsados = new HashSet<>();
		this.palabras = new ArrayList<>();
		this.r = new Random();
		this.cagarPalabras();
	}

	/**
	 * Agrega un cliente a la base de datos del Banco
	 * @param cuit
	 * @throws ClienteRegistradoEx
	 */
	public void agregarCliente(String cuit) throws ClienteRegistradoEx {
		Cliente cliente = this.crearCliente(cuit);
		if (this.existeCliente(cliente.getCuit())) {
			throw new ClienteRegistradoEx();
		} else {
			this.clientesL.add(cliente);
			this.clientesM.put(cliente.getCuit(), cliente);
		}
	}

	/**
	 * Busca un cliente en la base de datos del Banco
	 * @param cuit
	 * @return
	 */
	public Cliente buscarCliente(String cuit) {
		return this.clientesM.get(cuit);
	}

	/**
	 * Elimina un cliente de la base de datos del Banco
	 * @param cuit
	 * @throws ClienteNoExisteEx
	 */
	public void eliminarCliente(String cuit) throws ClienteNoExisteEx {
		if (this.existeCliente(cuit)) {
			Cliente cliente = this.buscarCliente(cuit);
			this.clientesL.remove(cliente);
			this.clientesM.remove(cuit);
			System.out.println("El cliente ha sido eliminado...");
		} else {
			throw new ClienteNoExisteEx();
		}
	}

	/**
	 * Muestra todos los clientes cargados
	 */
	public void listarClientes() {
		if (!this.hayClientesRegistrados()) {
			System.out.println("No hay clientes registrados por el momento...");
		} else {
			System.out.println(">> Clientes registrados:");
			for (Cliente cliente : this.clientesL) {
				System.out.println(cliente);
			}
		}
	}

	/**
	 * Verifica si existe un cliente
	 * 
	 * @param cuit
	 * @return True si existe, sino False
	 */
	private boolean existeCliente(String cuit) {
		return this.buscarCliente(cuit) != null;
	}

	/**
	 * Verifica si hay clientes registrados
	 * 
	 * @return
	 */
	private boolean hayClientesRegistrados() {
		return !this.clientesL.isEmpty();
	}

	/**
	 * Genera un alias a partir del CUIT
	 * 
	 * @param cuit
	 * @return
	 */
	private String generarAlias(String cuit) {
		int[] numeros = { this.r.nextInt(50), this.r.nextInt(50), this.r.nextInt(50) };
		String alias = "", palabra;
		for (int i = 0; i < 3; i++) {
			palabra = this.palabras.get(numeros[i]);
			alias += palabra;
			if (i != 2) {
				alias += ".";
			}
		}
		return alias;
	}

	/**
	 * Verifica si el alias generado es correcto: es unico y tiene un maximo de 20
	 * caracteres
	 * 
	 * @param alias
	 * @return
	 */
	private boolean aliasCorrecto(String alias) {
		return alias.length() <= 20 && !this.aliasRegistrado(alias);
	}

	/**
	 * Se verifica si un alias generado no se encuentra en uso por otro cliente
	 * 
	 * @param alias
	 * @return
	 */
	private boolean aliasRegistrado(String alias) {
		return this.aliasUsados.contains(alias);
	}

	/**
	 * Genera un alias correcto
	 * 
	 * @param cuit
	 * @return
	 */
	private String darAlias(String cuit) {
		String alias = this.generarAlias(cuit);
		while (!this.aliasCorrecto(alias)) {
			alias = this.generarAlias(cuit);
		}
		return alias;
	}

	/**
	 * Genera una clave entre 0000 y 9999
	 * 
	 * @return clave
	 */
	private short generarClave() {
		return (short) new Random().nextInt(10000);
	}

	/**
	 * Se cargan todas las palabras que se usaran para generar los alias
	 */
	private void cagarPalabras() {
		String[] palabras = { "casa", "auto", "nieve", "sol", "mar", "rio", "neuquen", "formosa", "cordoba", "osito",
				"ruso", "yankee",
				"moto", "rojo", "azul", "verde", "alto", "bajo", "gordo", "flaco", "tarjeta", "directo", "chat",
				"canal",
				"datos", "cole", "hora", "telefono", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado",
				"domingo",
				"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre",
				"noviembre",
				"diciembre", "youtube", "infobae", "facebook" };
		this.palabras.addAll(Arrays.asList(palabras));
	}

	/**
	 * Se listan todas las palabras cargadas para poder generar los alias
	 */
	public void listarPalabras() {
		for (String p : this.palabras) {
			System.out.println(p);
		}
	}

	/**
	 * Crea un cliente con su CUIT, y a partir de este se le asigna un alias.
	 * 
	 * @param cuit
	 * @return
	 */
	private Cliente crearCliente(String cuit) {
		Cliente cliente = new Cliente();
		String alias = this.generarAlias(cuit);
		cliente.setCuit(cuit);
		cliente.setAlias(alias);
		return cliente;
	}

}
