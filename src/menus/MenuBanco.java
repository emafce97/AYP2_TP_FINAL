package menus;

import java.util.Scanner;
import excepciones.*;
import dominio.*;

public class MenuBanco {

	private Scanner scn;
	private Banco banco;

	public MenuBanco(Scanner scn, Banco banco) {
		this.scn = scn;
		this.banco = banco;
	}

	/**
	 * Se ejecuta el menu del Banco, para el alta y baja de clientes
	 */
	public void ejecutar() {
		String menu = """
				--MENU DEL BANCO--
				1-Agregar cliente
				2-Buscar cliente
				3-Eliminar cliente
				4-Listar cliente
				5-Salir
				""".strip();
		int opcion = 0;
		while (opcion != 5) {
			System.out.println(menu);
			System.out.print("Ingrese su opcion: ");
			opcion = Integer.parseInt(this.scn.nextLine());
			try {
				switch (opcion) {
					case 1:
						this.agregarCliente();
						break;
					case 2:
						this.buscarCliente();
						break;
					case 3:
						this.eliminarCliente();
						break;
					case 4:
						this.listarClientes();
						break;
					case 5:
						System.out.println("Saliendo...");
						break;
					default:
						System.out.println("[ATENCION] La opcion ingresada es incorrecta.");
						break;
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	/**
	 * Agrega un cliente al banco desde un menu exterior
	 * 
	 * @throws CuitIncorrectoEx
	 */
	private void agregarCliente() throws CuitIncorrectoEx {
		String cuit = this.pedirCUIT();
		try {
			this.banco.agregarCliente(cuit);
			System.out.println("[INFO] El cliente ha asido agregado.");
		} catch (ClienteRegistradoEx ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Busca en la base de datos del Banco si el cliente existe.
	 * 
	 */
	private void buscarCliente() throws CuitIncorrectoEx, ClienteNoExisteEx {
		String cuit = this.pedirCUIT();
		if (!this.cuitCorrecto(cuit)) {
			throw new CuitIncorrectoEx();
		}
		Cliente cliente = this.banco.buscarClientePorCuit(cuit);
		if (cliente == null) {
			throw new ClienteNoExisteEx();
		}
		System.out.println(cliente);
	}

	/**
	 * Elimina un cliente de la base de datos del Banco
	 * 
	 * @throws ClienteNoExisteEx
	 */
	private void eliminarCliente() throws ClienteNoExisteEx, CuitIncorrectoEx {
		try {
			String cuit = this.pedirCUIT();
			this.banco.eliminarCliente(cuit);
			System.out.println("[INFO] El cliente ha sido eliminado.");
		} catch (NoHayClientesRegistradosEx | ClienteNoExisteEx ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Lista todos los clientes registrados
	 * 
	 */
	private void listarClientes() {
		try {
			this.banco.listarClientes();
		} catch (NoHayClientesRegistradosEx ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Verifica si el formato del CUIT ingresado es correcto
	 * 
	 * @return
	 */
	private boolean cuitCorrecto(String cuit) {
		return cuit.matches("^\\d{11}$");
	}

	/**
	 * Solicita el CUIT por consola
	 * 
	 * @return
	 */
	private String pedirCUIT() {
		System.out.print("Ingrese el CUIT del cliente: ");
		return this.scn.nextLine();
	}

}
