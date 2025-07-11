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
        while (opcion != 6) {
            System.out.println(
                    "--MENU DEL CLIENTE--\n1-Depositar\n2-Transferir\n3-Retirar efectivo\n4-Comprar dolares\n5-Ver saldos\n6-Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scn.nextLine());
            try {
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
                        this.verSaldos();
                        break;
                    case 6:
                        System.out.println("Saliendo del menu...");
                        break;
                    default:
                        System.out.println("[ERROR] La opcion ingresada es incorrecta...");
                        break;
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    private void depositar() throws MontoIncorrectoEx {
        System.out.print("Ingrese el monto a depositar: $");
        double monto = Double.parseDouble(this.scn.nextLine());
        System.out.println(
                "Seleccione a que cuenta:\n01-Caja de ahorro en pesos\n02-Cuenta corriente\n03-Caja de ahorro en dolares");
        System.out.print("Ingrese su opcion: ");
        String tipoCuenta = scn.nextLine();
        this.cliente.getCuentaSegunTipo(tipoCuenta);
        this.cliente.depositar(monto, tipoCuenta);
        System.out.println(">> El dineroo ha sido depositado...");
    }

    private void transferir() {

    }

    private void retirarEfectivo() {

    }

    private void comprarDolares() {

    }

    private void verSaldos() {

    }

}
