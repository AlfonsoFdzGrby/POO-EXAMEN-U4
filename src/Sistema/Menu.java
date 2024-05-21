package src.Sistema;

import java.util.*;

import src.Sistema.util.Tools;

public class Menu {
    private static Scanner sc = new Scanner(System.in);

    public static void ejecutarMenu(){
        Tools.printHeader("MINDBOX");
        System.out.println("Ingrese su número de control:");
        System.out.print(">> ");
        String numControl = sc.nextLine();
        System.out.println("Ingrese su contraseña");
        System.out.print(">> ");
        String contraseña = sc.nextLine();
    }
}
