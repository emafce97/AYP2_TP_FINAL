package dominio;

import java.util.*;
import excepciones.*;

public class Banco {

	// Colecciones
	private Map<String, Cliente> clientesCuit;
	private Map<String, Cliente> clientesAlias;
	private Set<String> aliasUsados;
	private List<String> palabrasParaAlias;

	// Constantes
	public static final double CAMBIO_DOLAR_PESOS = 1200;

	// Objectos
	private Random r;

	public Banco() {
		this.clientesCuit = new HashMap<>();
		this.clientesAlias = new HashMap<>();
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
		try {
			if (this.existeClienteCuit(cuit)) {
				throw new ClienteRegistradoEx();
			}
			Cliente cliente = this.crearClienteConAlias(cuit);
			this.clientesCuit.put(cuit, cliente);
			this.clientesAlias.put(cliente.getAlias(), cliente);
		} catch (CuitIncorrectoEx ex) {
			System.out.println(ex.getMessage());
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
				this.clientesCuit.put(cuit, cliente);
				this.clientesAlias.put(cliente.getAlias(), cliente);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
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
	 * Busca un cliente por su CUIT en la base de datos del Banco.
	 * 
	 * @param cuit
	 * @return
	 */
	public Cliente buscarClientePorCuit(String cuit) throws CuitIncorrectoEx {
		if (!this.cuitCorrecto(cuit)) {
			throw new CuitIncorrectoEx();
		}
		return this.clientesCuit.get(cuit);
	}

	/**
	 * Busca un cliente por su alias en la base de datos del Banco.
	 * 
	 * @param alias
	 * @return
	 */
	public Cliente buscarClientePorAlias(String alias) throws AliasIncorrectoEx {
		if (!this.aliasCorrecto(alias)) {
			throw new AliasIncorrectoEx();
		}
		return this.clientesAlias.get(alias);
	}

	/**
	 * Elimina un cliente, segun el CUIT, de la base de datos del Banco.
	 * 
	 * @param cuit
	 * @throws ClienteNoExisteEx
	 */
	public void eliminarCliente(String cuit) throws CuitIncorrectoEx, NoHayClientesRegistradosEx, ClienteNoExisteEx {
		if (!this.cuitCorrecto(cuit)) {
			throw new CuitIncorrectoEx();
		}
		if (!this.hayClientesRegistrados()) {
			throw new NoHayClientesRegistradosEx();
		}
		if (!this.existeClienteCuit(cuit)) {
			throw new ClienteNoExisteEx();
		}
		Cliente cliente = this.buscarClientePorCuit(cuit);
		this.aliasUsados.remove(cliente.getAlias());
		this.clientesAlias.remove(cliente.getAlias());
		this.clientesCuit.remove(cuit);
	}

	/**
	 * Muestra todos los clientes cargados hasta el momento.
	 */
	public void listarClientes() throws NoHayClientesRegistradosEx {
		if (!this.hayClientesRegistrados()) {
			throw new NoHayClientesRegistradosEx();
		}
		System.out.println("Clientes registrados:");
		this.clientesCuit.forEach((cuit, cliente) -> System.out.println("\t - " + cliente));
	}

	/**
	 * Verifica si existe un cliente segun el CUIT.
	 * 
	 * @param cuit
	 */
	public boolean existeClienteCuit(String cuit) throws CuitIncorrectoEx {
		if (!this.cuitCorrecto(cuit)) {
			throw new CuitIncorrectoEx();
		}
		return this.clientesCuit.containsKey(cuit);
	}

	/**
	 * Verifica si existe un cliente segun el alias.
	 * 
	 * @param alias
	 * @return
	 */
	public boolean existeClienteAlias(String alias) throws AliasIncorrectoEx {
		if (!this.aliasCorrecto(alias)) {
			throw new AliasIncorrectoEx();
		}
		return this.clientesAlias.containsKey(alias);
	}

	/**
	 * Verifica si hay clientes registrados
	 * 
	 */
	public boolean hayClientesRegistrados() {
		return !this.clientesCuit.isEmpty();
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
	 * Genera un alias aleatorio.
	 * 
	 */
	private String generarAlias() {
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
	 * Genera un alias correcto. Evita que se generen alias duplicados.
	 * 
	 * @param cuit
	 * @return
	 */
	private String darAliasCorrecto() {
		String alias = this.generarAlias();
		while (!this.aliasCorrecto(alias)) {
			alias = this.generarAlias();
		}
		this.aliasUsados.add(alias);
		return alias;
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
	 * Crea un cliente con su CUIT, y a partir de este se le asigna un alias.
	 * 
	 * @param cuit
	 * @return
	 */
	private Cliente crearClienteConAlias(String cuit) throws CuitIncorrectoEx {
		if (!this.cuitCorrecto(cuit)) {
			throw new CuitIncorrectoEx();
		}
		return new Cliente(cuit, this.darAliasCorrecto());
	}

}
