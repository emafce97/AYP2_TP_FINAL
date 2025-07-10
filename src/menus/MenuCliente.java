package menus;

import java.util.Scanner;
import dominio.Banco;
import dominio.Cliente;
import excepciones.MontoIncorrectoEx;

public class MenuCliente {

    private Scanner scn;
    private Banco banco;
    private Cliente cliente;

    public MenuCliente(Scanner scn, Banco banco, Cliente cliente) {
        this.scn = scn;
        this.banco = banco;
        this.cliente = cliente;
    }

    public void ejecutar() {
        int opcion = 0;
        while (opcion != 5) {
            System.out.println(
                    "--MENU DEL CLIENTE--\n1-Depositar\n2-Transferir\n3-Retirar efectivo\n4-Comprar dolares\n5-Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scn.nextLine());
            switch (opcion) {
                case 1:
                    this.depositar();
                    break;
                case 2:
                    this.transferir();
                    break;
                case 3:
                    this.retirarEfectivo();
                    break;
                case 4:
                    this.comprarDolares();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta...");
                    break;
            }

        }
    }

    private void depositar() throws MontoIncorrectoEx{
        System.out.print("Ingrese el monto a depositar: $");
        double monto = Double.parseDouble(this.scn.nextLine());
        if(monto < 0){
            throw new MontoIncorrectoEx();
        }else{
            this.cliente.
        }
    }

}
