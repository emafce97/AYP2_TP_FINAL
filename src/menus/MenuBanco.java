package menus;

import java.util.Scanner;
import excepciones.*;
import dominio.*;

public class MenuBanco {

	private Scanner scn;
	private Banco banco;
	private String cuit_scn;

	public MenuBanco(Scanner scn, Banco banco) {
		this.scn = scn;
		this.banco = banco;
	}

	/**
	 * Se ejecuta el menu del Banco, para el alta y baja de clientes
	 */
	public void ejecutar() {
		int opcion = 0;
		while (opcion != 5) {
			System.out.println("--MENU DEL BANCO--\n1-Agregar cliente\n2-Buscar cliente\n3-Eliminar cliente\n4-Listar clientes\n5-Salir");
			System.out.print("Ingrese su opcion: ");
			opcion = Integer.parseInt(scn.nextLine());
			try {
				switch (opcion) {
					case 1:
						this.agregarCliente(scn);
						break;
					case 2:
						this.buscarCliente(scn);
						break;
					case 3:
						this.eliminarCliente(scn);
						break;
					case 4:
						this.banco.listarClientes();
						break;
					case 5:
						System.out.println("Saliendo...");
						break;
					default:
						System.out.println(">> Opcion incorrecta...");
						break;
				}
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
	}

	/**
	 * Agrega un cliente al banco desde un menu exterior
	 * 
	 * @param scn
	 * @throws CuitIncorrectoEx
	 */
	private void agregarCliente(Scanner scn) throws CuitIncorrectoEx {
		System.out.print("Ingrese el cuil: ");
		String cuit = scn.nextLine();
		if (this.cuitCorrecto(cuit)) {
			try {
				this.banco.agregarCliente(cuit);
				System.out.println(">> El cliente ha sido agregado...");
			} catch (ClienteRegistradoEx ex) {
				System.out.println(ex);
			}
		} else {
			throw new CuitIncorrectoEx();
		}
	}

	/**
	 * Busca en la base de datos del Banco si el cliente existe.
	 * 
	 * @param scn
	 */
	private void buscarCliente(Scanner scn) {
		System.out.print("Ingrese el CUIT del cliente: ");
		this.cuit_scn = scn.nextLine();
		Cliente c = this.banco.buscarCliente(cuit_scn);
		if (c == null) {
			System.out.println(">> El cliente no existe...");
		} else {
			System.out.println(c);
		}
	}

	/**
	 * Elimina un cliente de la base de datos del Banco
	 * 
	 * @param scn
	 * @throws ClienteNoExisteEx
	 */
	private void eliminarCliente(Scanner scn) throws ClienteNoExisteEx {
		System.out.print("Ingrese el CUIT del cliente: ");
		this.cuit_scn = scn.nextLine();
		this.banco.eliminarCliente(cuit_scn);
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
