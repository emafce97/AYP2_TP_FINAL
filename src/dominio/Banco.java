package dominio;

import java.util.*;
import excepciones.*;

public class Banco {

	// Colecciones
	private Map<String, Cliente> clientes;
	private Set<String> aliasUsados;
	private List<String> palabrasParaAlias;

	// Constantes
	public static final double CAMBIO_DOLAR_PESOS = 1200;

	// Objectos
	private Random r;

	public Banco() {
		this.clientes = new HashMap<>();
		this.aliasUsados = new HashSet<>();
		this.palabrasParaAlias = new ArrayList<>();
		this.r = new Random();
		this.cargarClientes();
	}

	/**
	 * Agrega un cliente a la base de datos del Banco.
	 * 
	 * @param cuit
	 * @throws ClienteRegistradoEx
	 */
	public void agregarCliente(String cuit) throws ClienteRegistradoEx {
		if (this.existeCliente(cuit)) {
			throw new ClienteRegistradoEx();
		}
		try {
			this.clientes.put(cuit, this.crearClienteConAlias(cuit));
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	/**
	 * Agrega clientes ya creados para no crearlos por consola.
	 */
	private void cargarClientes() {
		this.cargarPalabras();
		String[] cuits = { "20404881079", "20424250589", "23127700780", "21304881078", "13214181076", "20133086207" };
		Cliente cliente;
		for (String cuit : cuits) {
			try {
				cliente = this.crearClienteConAlias(cuit);
				this.clientes.put(cuit, cliente);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	/**
	 * Se cargan todas las palabras que se usaran para generar los alias.
	 */
	private void cargarPalabras() {
		String[] palabras = { "casa", "auto", "nieve", "sol", "mar", "rio", "neuquen", "formosa", "cordoba", "osito",
				"ruso", "yankee",
				"moto", "rojo", "azul", "verde", "alto", "bajo", "gordo", "flaco", "tarjeta", "directo", "chat",
				"canal",
				"datos", "cole", "hora", "telefono", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado",
				"domingo",
				"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre",
				"noviembre",
				"diciembre", "youtube", "infobae", "facebook" };
		this.palabrasParaAlias.addAll(Arrays.asList(palabras));
	}

	/**
	 * Busca un cliente en la base de datos del Banco.
	 * 
	 * @param cuit
	 * @return
	 */
	public Cliente buscarCliente(String cuit) {
		return this.clientes.get(cuit);
	}

	/**
	 * Elimina un cliente de la base de datos del Banco.
	 * 
	 * @param cuit
	 * @throws ClienteNoExisteEx
	 */
	public void eliminarCliente(String cuit) throws ClienteNoExisteEx {
		if (!this.existeCliente(cuit)) {
			throw new ClienteNoExisteEx();
		}
		this.clientes.remove(cuit);
		System.out.println("El cliente ha sido eliminado...");
	}

	/**
	 * Muestra todos los clientes cargados hasta el momento.
	 */
	public void listarClientes() throws NoHayClientesRegistradosEx {
		if (!this.hayClientesRegistrados()) {
			throw new NoHayClientesRegistradosEx();
		}
		System.out.println("Clientes registrados:");
		this.clientes.forEach((cuit, cliente) -> System.out.println("\t - " + cliente));
	}

	/**
	 * Verifica si existe un cliente.
	 * 
	 * @param cuit
	 */
	private boolean existeCliente(String cuit) {
		return this.clientes.containsKey(cuit);
	}

	/**
	 * Verifica si hay clientes registrados
	 * 
	 */
	public boolean hayClientesRegistrados() {
		return !this.clientes.isEmpty();
	}

	/**
	 * Genera un alias a partir del CUIT.
	 * 
	 * @param cuit
	 */
	private String generarAlias(String cuit) {
		int[] numeros = { this.r.nextInt(50), this.r.nextInt(50), this.r.nextInt(50) };
		StringBuilder alias = new StringBuilder();
		String palabra;
		for (int i = 0; i < 3; i++) {
			palabra = this.palabrasParaAlias.get(numeros[i]);
			alias.append(palabra);
			if (i != 2) {
				alias.append(".");
			}
		}
		return alias.toString();
	}

	/**
	 * Se verifica si un alias generado no se encuentra en uso por otro cliente
	 * 
	 * @param alias
	 */
	private boolean aliasRegistrado(String alias) {
		return this.aliasUsados.contains(alias);
	}

	/**
	 * Verifica si el alias generado es correcto: es unico y tiene un maximo de 20
	 * caracteres.
	 * 
	 * @param alias
	 */
	private boolean aliasCorrecto(String alias) {
		return alias.length() <= 20 && !this.aliasRegistrado(alias);
	}

	/**
	 * Genera un alias correcto. Evita que se generen alias duplicados.
	 * 
	 * @param cuit
	 * @return
	 */
	private String darAlias(String cuit) {
		String alias = this.generarAlias(cuit);
		while (!this.aliasCorrecto(alias)) {
			alias = this.generarAlias(cuit);
		}
		this.aliasUsados.add(alias);
		return alias;
	}

	/**
	 * Crea un cliente con su CUIT, y a partir de este se le asigna un alias.
	 * 
	 * @param cuit
	 * @return
	 */
	private Cliente crearClienteConAlias(String cuit) throws CuitIncorrectoEx {
		if (!this.cuitCorrecto(cuit)) {
			throw new CuitIncorrectoEx();
		}
		return new Cliente(cuit, this.darAlias(cuit));
	}

	/**
	 * Verifica si el formato del CUIT ingresado es correcto
	 * 
	 * @param cuit
	 * @return
	 */
	private boolean cuitCorrecto(String cuit) {
		return cuit.matches("^\\d{11}$");
	}

}
