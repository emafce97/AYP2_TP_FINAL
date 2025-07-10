package menus;

import java.util.Scanner;
import dominio.Banco;
import dominio.Cliente;
import excepciones.ClienteNoExisteEx;

public class MenuCajero{

    private Scanner scn;
    private Banco banco;


    public MenuCajero(Scanner scn, Banco banco) {
        this.scn = scn;
        this.banco = banco;
    }

    public void ejecutar() {
        int opcion = 0;
        while (opcion != 2) {
            System.out.println("--MENU DEL CAJERO AUTOMATICO--\n1-Ingresar\n2-Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scn.nextLine());
            try {
                switch (opcion) {
                    case 1:
                        this.simularIngreso(scn);
                        break;
                    case 2:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta...");
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    private void simularIngreso(Scanner scn) throws ClienteNoExisteEx{
        System.out.print("Ingrese su CUIT: ");
        String cuit = scn.nextLine();
        Cliente c = this.banco.buscarCliente(cuit);
        if(c == null){
            throw new ClienteNoExisteEx();
        }else{
            c.mostrarDatos();
            new MenuCliente(scn, banco, c);
        }
    }

}
