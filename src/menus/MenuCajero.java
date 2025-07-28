package menus;

import java.util.Scanner;
import dominio.Banco;
import dominio.Cliente;
import excepciones.ClienteNoExisteEx;

public class MenuCajero {

    private Scanner scn;
    private Banco banco;

    public MenuCajero(Scanner scn, Banco banco) {
        this.scn = scn;
        this.banco = banco;
    }

    public void ejecutar() {
        String menu = """
                --MENU DEL CAJERO--
                1-Ingresar
                2-Salir
                """.strip();
        int opcion = 0;
        while (opcion != 2) {
            System.out.println(menu);
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scn.nextLine());
            try {
                switch (opcion) {
                    case 1:
                        this.simularIngreso();
                        break;
                    case 2:
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

    private void simularIngreso() throws ClienteNoExisteEx {
        System.out.print("Ingrese su CUIT: ");
        String cuit = this.scn.nextLine();
        Cliente cliente = this.banco.buscarClientePorCuit(cuit);
        System.out.print(cliente);
        if (cliente == null) {
            throw new ClienteNoExisteEx();
        }
        new MenuCliente(this.scn, banco, cliente).ejecutar();
    }

}
