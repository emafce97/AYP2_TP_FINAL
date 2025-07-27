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
        String menu = """
                --MENU DEL CLIENTE--
                1- Depositar
                2- Transferir
                3- Retirar efectivo
                4- Comprar dólares
                5- Ver saldos
                6- Salir
                """;
        int opcion = 0;
        while (opcion != 6) {
            System.out.println(menu);
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
        double monto = 0;
        try {
            monto = Double.parseDouble(this.scn.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        if (monto <= 0) {
            throw new MontoIncorrectoEx();
        }
        String tipoCuenta = this.elegirCuenta(scn);
        this.cliente.depositar(monto, tipoCuenta);
    }

    private void transferir() {

    }

    private void retirarEfectivo() {

    }

    private void comprarDolares() {

    }

    private void verSaldos() {

    }

    private String elegirCuenta(Scanner scn) {
        String menu = """
                Seleccione el tipo de cuenta:
                01-Caja de ahorro en pesos
                02-Cuenta corriente
                03-Caja de ahorro en dolares
                """;
        System.out.println(menu);
        System.out.print("Ingrese su opcion: ");
        return scn.nextLine();
    }

}
