package menus;

import dominio.Banco;
import dominio.Cliente;
import dominio.Transferencia;
import excepciones.ClienteNoExisteEx;
import excepciones.FondosInsuficientesEx;
import excepciones.MontoIncorrectoEx;
import java.util.Scanner;

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
                """.strip();
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
        try {
            String tipoCuenta = this.elegirCuenta(true);
            double monto = this.pedirMonto(tipoCuenta);
            this.cliente.depositar(monto, tipoCuenta);
            System.out.println("[INFO] El dinero ha sido depositado.");
        } catch (MontoIncorrectoEx ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void transferir() throws ClienteNoExisteEx, MontoIncorrectoEx {
        System.out.print("Ingrese el alias del cliente a quien va transferir: ");
        String alias = this.scn.nextLine();
        if (banco.buscarClientePorAlias(alias) == null) {
            throw new ClienteNoExisteEx();
        }
        try {
            System.out.print("Ingrese el monto: $");
            double monto = Double.parseDouble(this.scn.nextLine());
            if (monto <= 0) {
                throw new MontoIncorrectoEx();
            }
            System.out.print("Ingrese el motivo o concepto de la transferencia: ");
            String concepto = this.scn.nextLine();
            System.out.print("hola");
            Transferencia transferencia = new Transferencia(this.cliente.getAlias(), alias, concepto, monto);
            // this.cliente.hacerTransferencia(alias, cliente, monto, concepto);
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void retirarEfectivo() {
        try {
            String tipoCuenta = this.elegirCuenta(false);
            double monto = this.pedirMonto(tipoCuenta);
            this.cliente.retirarEfectivo(monto, tipoCuenta);
            System.out.println("[INFO] El dinero ha sido retirado con exito.");
        } catch (FondosInsuficientesEx | MontoIncorrectoEx ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void comprarDolares() {
        try {
            double monto = this.pedirMonto("03");
            this.cliente.retirarEfectivo(monto, "03");
            System.out.println("[INFO] El dinero ha sido retirado con exito.");
        } catch (FondosInsuficientesEx | MontoIncorrectoEx ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void verSaldos() {
        this.cliente.mostrarDatos();
    }

    private String elegirCuenta(boolean conDolares) {
        String menu = "Seleccion el tipo de cuenta:\n01-Caja de ahorro en pesos\n02-Cuenta corriente";
        if (conDolares) {
            menu = "Seleccione el tipo de cuenta:\n01-Caja de ahorro en pesos\n02-Cuenta corriente\n03-Caja de ahorro en dolares";
        }
        System.out.println(menu);
        System.out.print("Ingrese su opcion: ");
        return scn.nextLine();
    }

    private String elegirDestinoTransferencia() {
        String menu = "Seleccione a quien se hara la transferencia:\n1-Cuentas propias\n2-Otro cliente";
        System.out.println(menu);
        System.out.print("Ingrese su opcion: ");
        return this.scn.nextLine();
    }

    private double pedirMonto(String tipoCuenta) throws MontoIncorrectoEx {
        String msj = "Ahora, ingrese el monto a retirar: ";
        if (tipoCuenta.equals("01") || tipoCuenta.equals("02"))
            msj += "$";
        else {
            msj += "USD$";
        }
        System.out.print(msj);
        double monto;
        try {
            monto = Double.parseDouble(this.scn.nextLine());
            if (monto <= 0) {
                throw new MontoIncorrectoEx();
            }
        } catch (NumberFormatException ex) {
            System.out.println("[ATENCION] El valor ingresado no es un numero.");
            return -1;
        }
        return monto;
    }

}
