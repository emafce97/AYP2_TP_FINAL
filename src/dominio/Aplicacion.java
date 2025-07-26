package dominio;

import menus.MenuBanco;
import menus.MenuCajero;
import java.util.Scanner;

public class Aplicacion {

    private Banco banco;
    private Scanner scn;
    private MenuBanco mb;
    private MenuCajero mc;

    public Aplicacion() {
        this.banco = new Banco();
        this.scn = new Scanner(System.in);
        this.mb = new MenuBanco(scn, this.banco);
        this.mc = new MenuCajero(scn, this.banco);
    }

    public void ejecutar() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("--MENU PRINCIPAL--\n1-Menu del banco\n2-Menu del cajero\n3-Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = Integer.parseInt(this.scn.nextLine());
            switch (opcion) {
                case 1:
                    this.mb.ejecutar();
                    break;
                case 2:
                    this.mc.ejecutar();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion incorrecta...");
                    break;
            }
        }
    }

}
