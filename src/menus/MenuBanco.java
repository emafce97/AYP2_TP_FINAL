package menus;

import java.util.Scanner;
import excepciones.*;
import dominio.Banco;

public class MenuBanco {
	
	private Scanner scn;
	private Banco banco;
	
	public MenuBanco(Scanner scn, Banco banco) {
		this.scn = scn;
		this.banco = banco;
	}
	
	public void ejecutar() {
		int opcion = 0;
		while(opcion != 5) {
			System.out.println("--MENU DEL BANCO--\n1-Agregar cliente\n2-Buscar cliente\n3-Eliminar cliente\n4-Salir");
			System.out.print("Ingrese su opcion: ");
			opcion = Integer.parseInt(scn.nextLine());
			try{
				switch(opcion) {
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
						System.out.println("Saliendo...");
						break;
					default:
						System.out.println("Opcion incorrecta...");
						break;
					}
			}
			catch(Exception ex){
					System.err.println(ex);
			}
		}
	}

	private void eliminarCliente(Scanner scn) {
		// TODO Auto-generated method stub
		
	}

	private void buscarCliente(Scanner scn) {
		// TODO Auto-generated method stub
		
	}

	private void agregarCliente(Scanner scn) throws CuitIncorrectoEx {
		System.out.print("Ingrese el cuil: ");
		String cuit = scn.nextLine();
		if(this.cuitCorrecto(cuit)) {
			try {
				this.banco.agregarCliente(null);
				System.out.println("El cliente ha sido agregado...");
			}catch(ClienteRegistradoEx ex) {
				System.out.println(ex);
			}
		}else {
			throw new CuitIncorrectoEx();
		}
	}
	
	private boolean cuitCorrecto(String cuit) {
		return cuit.matches("^\\d{11}$");
	}

}
