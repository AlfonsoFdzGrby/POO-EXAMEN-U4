package src.Sistema;

import java.time.LocalDate;
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

    public static void ejecutarMenu(){
        while(usuarioEnSesion.getUsuarioActual()==null){
            Tools.printHeader("MINDBOX - INICIAR SESIÓN");
            System.out.println("Seleccione una opción para ingresar como:");
            System.out.println("1. Alumno");
            System.out.println("2. Profesor");
            System.out.println("3. Coordinador");
            System.out.println("4. Salir del programa");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                case 1 -> {
                    while(true){
                        Usuario usuario = Sistema.iniciarSesion();
                        if(usuario instanceof Alumno){
                            usuarioEnSesion.setUsuario(usuario);
                            System.out.println("Se ha iniciado sesión correctamente con el");
                            System.out.println("alumno " + usuarioEnSesion.getUsuarioActual().getNombreCompleto() + ".");
                            Tools.next();
                            //menuAlumno();
                            break;
                        }else if(!(usuario instanceof Alumno)){
                            System.out.println("No se ha encontrado al alumno ingresado");
                        }else{
                            break;
                        }
                    }
                }

                case 2 -> {
                    /*Profesor profesor = Sistema.iniciarSesionProfesor();
                    *usuarioEnSesion.setUsuario(profesor); 
                    */
                }

                case 3 -> {
                    /*Coordinador coordinador = Sistema.iniciarSesionCoordinador();
                    *usuarioEnSesion.setUsuario(coordinador); 
                    */
                }

                case 4 -> System.exit(0);

                default -> System.out.println("== OPCIÓN INVÁLIDA ==");
            }
        }
        //esto va después de asignarlo al singleton
        

    }
}
