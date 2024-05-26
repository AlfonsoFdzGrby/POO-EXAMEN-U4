package src.Sistema;

import java.time.*;
import java.util.*;

import src.Carrera.Carrera;
import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Sistema.util.Tools;
import src.Usuarios.*;
import src.Usuarios.util.Rol;
import src.Usuarios.util.UsuarioEnSesion;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static UsuarioEnSesion usuarioEnSesion = UsuarioEnSesion.getInstancia();
    private static Sistema sistema = new Sistema();

    public static void ejecutarMenu() throws Exception {
        //Tools.loadBar();
        while(usuarioEnSesion.getUsuarioActual()==null){
            Tools.clear();
            Tools.printMindboxLogo(); 
            System.out.println("Seleccione una opción para ingresar como:");
            System.out.println("1. Alumno");
            System.out.println("2. Profesor");
            System.out.println("3. Coordinador");
            System.out.println("4. Salir del programa");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                case 1 -> {
                    Tools.printHeader("INGRESAR - ALUMNO");
                    while(true){
                        Usuario usuario = Sistema.iniciarSesion();
                        if(usuario instanceof Alumno){
                            usuarioEnSesion.setUsuario(usuario);
                            System.out.println("Se ha iniciado sesión correctamente con el");
                            System.out.println("alumno " + usuarioEnSesion.getUsuarioActual().getNombreCompleto() + ".");
                            Tools.next();
                            menuAlumno();
                            break;
                        }else if(usuario == null){
                            break;
                        }else{
                            System.out.println("No se ha encontrado al alumno ingresado");
                        }
                    }
                }

                case 2 -> {
                    Tools.printHeader("INGRESAR - PROFESOR");
                    while(true){
                        Usuario usuario = Sistema.iniciarSesion();
                        if(usuario instanceof Profesor){
                            usuarioEnSesion.setUsuario(usuario);
                            System.out.println("Se ha iniciado sesión correctamente con el");
                            System.out.println("profesor " + usuarioEnSesion.getUsuarioActual().getNombreCompleto() + ".");
                            Tools.next();
                            menuProfesor();
                            break;
                        }else if(usuario == null){
                            break;
                        }else{
                            System.out.println("No se ha encontrado al alumno ingresado");
                        }
                    }
                }

                case 3 -> {
                    Tools.printHeader("INGRESAR - COORDINADOR");
                    while(true){
                        Usuario usuario = Sistema.iniciarSesion();
                        if(usuario instanceof Coordinador){
                            usuarioEnSesion.setUsuario(usuario);
                            System.out.println("Se ha iniciado sesión correctamente con el");
                            System.out.println("coordinador " + usuarioEnSesion.getUsuarioActual().getNombreCompleto() + ".");
                            Tools.next();
                            menuCoordinador();
                            break;
                        }else if(usuario == null){
                            break;
                        }else{
                            System.out.println("No se ha encontrado al alumno ingresado");
                        }
                    }
                }

                case 4 -> System.exit(0);
            }
        }
    }

    private static void menuAlumno(){
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("MINDBOX - ALUMNO");
            System.out.println("Seleccione una opción:");

        }
    }

    private static void menuProfesor(){

    }

    private static void menuCoordinador(){

    }
}
