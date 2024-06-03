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
        Tools.loadBar();
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
                        
                        if(usuario == null){
                            break;
                        }

                        if(usuario.getNumControl().charAt(0)=='I'){
                            usuarioEnSesion.setUsuario(usuario);
                            System.out.println("Se ha iniciado sesión correctamente con el");
                            System.out.println("alumno " + usuarioEnSesion.getUsuarioActual().getNombreCompleto() + ".");
                            Tools.next();
                            menuAlumno();
                            break;
                        }else{
                            System.out.println("El usuario encontrado no es un alumno");
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
        //Tools.clear();
        Alumno alumno = (Alumno)usuarioEnSesion.getUsuarioActual();
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("MINDBOX - ALUMNO");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Consultar calificaciones");
            System.out.println("2. Cerrar sesión");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                case 1 -> {
                    Tools.printHeader("CALIFICACIONES");
                    alumno.imprimirCalificaciones();
                    if(alumno.isAcredito()){
                        System.out.println("El alumno ha acreditado el semestre");
                    }else{
                        System.out.println("El alumno no ha acreditado el semestre");
                    }
                    Tools.next();
                }
                case 2 -> usuarioEnSesion.setUsuario(null);
            }
        }
    }

    private static void menuProfesor(){
        //Tools.clear();
        Profesor profesor = (Profesor)usuarioEnSesion.getUsuarioActual();
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("MINDBOX - PROFESOR");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Calificar grupo");
            System.out.println("2. Mostrar grupos");
            System.out.println("3. Cerrar sesión");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                //case 1 -> profesor.calificarGrupo();
                //case 2 -> profesor.mostrarGrupos();
                case 3 -> usuarioEnSesion.setUsuario(null);
            }
        }
        
        
    }

    private static void menuCoordinador(){
        //Tools.clear();
        Coordinador coordinador = (Coordinador)usuarioEnSesion.getUsuarioActual();
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("MINDBOX - COORDINADOR");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Calificar grupo");
            System.out.println("2. Mostrar grupos");
            System.out.println("3. Avanzar de semestre");
            System.out.println("4. Cerrar sesión");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                //case 1 -> coordinador.calificarGrupo();
                //case 2 -> coordinador.mostrarGrupos();
                case 3 -> coordinador.avanzarSemestre();
                case 4 -> usuarioEnSesion.setUsuario(null);
            }
        }
    }
}
