package src.Usuarios.util;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.Usuarios.Coordinador;
import src.Usuarios.util.UsuarioEnSesion;

import src.Sistema.util.Tools;

public class DatosComun{
    private static Scanner sc = new Scanner(System.in);

    public static ArrayList<String> datosComun(){
        System.out.println("Ingrese el nombre de la persona: ");
        System.out.print(">> ");
        String name = sc.nextLine();

        String apellidos = "";
        while(true){
            System.out.println("Ingrese los apellidos de la persona (paterno materno): ");
            System.out.print(">> ");
            apellidos = sc.nextLine();
            String[] apellidos2 = apellidos.split(" ");
            if(apellidos2.length<2){
                System.out.println("Por favor ingrese ambos de sus apellidos");
            }else{
                break;
            }
        }
        

        System.out.println("La persona es hombre? (S/N): ");
        System.out.print(">> ");
        String YoN = sc.nextLine();

        System.out.println("Ingrese la fecha de nacimiento:");
        LocalDate fecha = Tools.askForDate();

        System.out.println("Ingrese la ciudad de residencia de la persona: ");
        System.out.print(">> ");
        String ciudad = sc.nextLine();

        System.out.println("Ingrese el estado de residencia de la persona: ");
        System.out.print(">> ");
        String estado = sc.nextLine();

        System.out.println("Ingrese la direccion de la persona: ");
        System.out.print(">> ");
        String direccion = sc.nextLine();

        System.out.println("Ingrese la contraseña con la que contará el usuario: ");
        System.out.print(">> ");
        String contrasena = sc.nextLine();

        ArrayList<String> datosComun = new ArrayList<>();
        datosComun.addAll(Arrays.asList(name, apellidos, YoN, fecha.toString(), ciudad, estado, direccion, contrasena));

        return datosComun;
    }
}