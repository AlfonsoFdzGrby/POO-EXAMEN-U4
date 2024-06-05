package src.Sistema;

import java.io.File;
import java.util.*;

import src.Sistema.util.Tools;
import src.Sistema.util.JSON.UsuariosSerializer;
import src.Usuarios.*;
import src.Usuarios.util.UsuarioEnSesion;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static UsuarioEnSesion usuarioEnSesion = UsuarioEnSesion.getInstancia();
    private static Sistema sistema = new Sistema(false);

    public static void ejecutarMenu() throws Exception {
        Tools.loadBar();
        while(usuarioEnSesion.getUsuarioActual()==null){
            Tools.clear();
            Tools.printMindboxLogo(); 
            System.out.println("Seleccione una opción para ingresar como:");
            System.out.println("1. Alumno");
            System.out.println("2. Profesor");
            System.out.println("3. Coordinador");
            System.out.println("4. Guardar cambios en archivo JSON");
            System.out.println("5. Salir del programa");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                case 1 -> {
                    Tools.printHeader("INGRESAR - ALUMNO");
                    while(true){
                        Usuario usuario = Sistema.iniciarSesion(1);
                        
                        if(usuario == null){
                            break;
                        }

                        if(usuario.getNumControl().charAt(0)=='I'){
                            usuarioEnSesion.setUsuario(usuario);
                            Tools.printHeader("INGRESAR - PROFESOR");
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
                        Usuario usuario = Sistema.iniciarSesion(2);
                        
                        if(usuario == null){
                            break;
                        }
                        
                        if(usuario.getNumControl().charAt(0)=='M'){
                            usuarioEnSesion.setUsuario(usuario);
                            Tools.printHeader("INGRESAR - PROFESOR");
                            System.out.println("Se ha iniciado sesión correctamente con el");
                            System.out.println("profesor " + usuarioEnSesion.getUsuarioActual().getNombreCompleto() + ".");
                            Tools.next();
                            menuProfesor();
                            break;
                        }else{
                            System.out.println("El usuario encontrado no es un profesor");
                        }
                    }
                }

                case 3 -> {
                    Tools.printHeader("INGRESAR - COORDINADOR");
                    while(true){
                        Usuario usuario = Sistema.iniciarSesion(3);
                        
                        if(usuario == null){
                            break;
                        }

                        if(usuario.getNumControl().charAt(0)=='C'){
                            usuarioEnSesion.setUsuario(usuario);
                            Tools.printHeader("INGRESAR - PROFESOR");
                            System.out.println("Se ha iniciado sesión correctamente con el");
                            System.out.println("coordinador " + usuarioEnSesion.getUsuarioActual().getNombreCompleto() + ".");
                            Tools.next();
                            menuCoordinador();
                            break;
                        }else{
                            System.out.println("El usuario encontrado no es un coordinador");
                        }
                    }
                }

                case 4 -> {
                    File file = new File("usuarios.json");
                    UsuariosSerializer.writeToJSON();
                }

                case 5 -> {
                    File file = new File("usuarios.json");
                    if(file.length()==0){
                        UsuariosSerializer.writeToJSON(); 
                    }
                    System.exit(0);
                }
            }
        }
    }

    private static void menuAlumno() throws Exception {
        Tools.clear();
        Alumno alumno = (Alumno)usuarioEnSesion.getUsuarioActual();
        if (alumno.isGraduado()) {
            menuGraduados();
        } else {
            while(usuarioEnSesion.getUsuarioActual()!=null){
                Tools.printHeader("MINDBOX - ALUMNO");
                System.out.println("Seleccione una opción:");
                System.out.println("1. Consultar calificaciones");
                System.out.println("2. Ver Informacion");
                System.out.println("3. Consultar Materias");
                System.out.println("4. Cerrar sesión");
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
                    }
                    case 2 -> mostrarInformacion(alumno);
                    case 3 -> alumno.consultarMaterias();
                    case 4 -> usuarioEnSesion.setUsuario(null);
                    default -> System.out.println("Opcion ingresada incorrecta!!");
                }
            }
        }
    }

    private static void menuProfesor() throws Exception {
        Tools.clear();
        Profesor profesor = (Profesor)usuarioEnSesion.getUsuarioActual();
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("MINDBOX - PROFESOR");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Calificar grupo");
            System.out.println("2. Mostrar grupos");
            System.out.println("3. Mostrar Informacion");
            System.out.println("4. Cerrar sesión");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                case 1 -> profesor.calificarGrupo();
                case 2 -> profesor.mostrarGrupos();
                case 3 -> mostrarInformacion(profesor);
                case 4 -> usuarioEnSesion.setUsuario(null);
                default -> System.out.println("Opcion ingresada incorrecta!!");
            }
        }
        
        
    }

    private static void menuCoordinador() throws Exception {
        Tools.clear();
        Coordinador coordinador = (Coordinador)usuarioEnSesion.getUsuarioActual();
        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("MINDBOX - COORDINADOR");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Calificar grupo");
            System.out.println("2. Mostrar grupos");
            System.out.println("3. Avanzar de semestre");
            System.out.println("4. Mostrar Informacion");
            System.out.println("5. Cambiar Profesor de Materia");
            System.out.println("6. Delegar Materia a Otro Profesor");
            System.out.println("7. Ver Informacion Sistema");
            System.out.println("8. Crear Usuario");
            System.out.println("9. Eliminar Usuarios");
            System.out.println("10. Modificar Usuarios");
            System.out.println("11. Cerrar sesión");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                case 1 -> coordinador.calificarGrupo();
                case 2 -> coordinador.mostrarGrupos();
                case 3 -> coordinador.avanzarSemestre();
                case 4 -> mostrarInformacion(coordinador);
                case 5 -> coordinador.modificarProfesMaterias();
                case 6 -> coordinador.modificarMateriaCoordinador();
                case 7 -> menuPrints();
                case 8 -> Alumno.registrarAlumno();
                case 9 -> menuEliminaciones();
                case 10 -> menuModificaciones();
                case 11 -> usuarioEnSesion.setUsuario(null);
                default -> System.out.println("Opcion ingresada incorrecta!!");
            }
        }
    }

    private static void mostrarInformacion(Usuario usuario) throws Exception{
        Tools.printHeader("INFORMACION DEL USUARIO");
        System.out.println(usuario.toString());

        Tools.next();
    }

    private static void menuPrints() throws Exception{
        Coordinador coordinador = (Coordinador)usuarioEnSesion.getUsuarioActual();
        boolean flag = true;

        while (flag) {
            Tools.printHeader("INFORMACION DE LA CARRERA");
            System.out.println("Que desea imprimir: ");
            System.out.println("1. Alumnos");
            System.out.println("2. Profesores");
            System.out.println("3. Graduados");
            System.out.println("4. Materias");
            System.out.println("5. Volver");
            System.out.print(">> ");

            int opt = Tools.nextInt();

            switch (opt) {
                case 1 -> coordinador.verAlumnos();
                case 2 -> Profesor.printProfesores();
                case 3 -> Sistema.printGraduados();
                case 4 -> coordinador.printMateriasGeneral();
                case 5 -> flag = false;
                default -> System.out.println("Opcion ingresada incorrecta!!");
            }
        }
    }
    
    private static void menuEliminaciones() throws Exception{
        Coordinador coordinador = (Coordinador)usuarioEnSesion.getUsuarioActual();
        boolean flag = true;

        while (flag) {
            Tools.printHeader("ELIMINAR USUARIO");
            System.out.println("Que desea eliminar: ");
            System.out.println("1. Alumno");
            System.out.println("2. Profesore");
            System.out.println("3. Volver");
            System.out.print(">> ");

            int opt = Tools.nextInt();

            switch (opt) {
                case 1 -> coordinador.eliminarAlumno();
                case 2 -> coordinador.eliminarProfesor();
                case 3 -> flag = false;
                default -> System.out.println("Opcion ingresada incorrecta!!");
            }
        }
    }

    private static void menuModificaciones() throws Exception{
        Coordinador coordinador = (Coordinador)usuarioEnSesion.getUsuarioActual();
        boolean flag = true;

        while (flag) {
            Tools.printHeader("MODIFICAR USUARIO");
            System.out.println("Que desea modificar: ");
            System.out.println("1. Alumno");
            System.out.println("2. Profesore");
            System.out.println("3. Volver");
            System.out.print(">> ");

            int opt = Tools.nextInt();

            switch (opt) {
                case 1 -> coordinador.modificarAlumno();
                case 2 -> coordinador.modificarProfesor();
                case 3 -> flag = false;
                default -> System.out.println("Opcion ingresada incorrecta!!");
            }
        }
    }

    private static void menuGraduados() throws Exception{
        Alumno alumno = (Alumno)usuarioEnSesion.getUsuarioActual();

        while(usuarioEnSesion.getUsuarioActual()!=null){
            Tools.printHeader("MINDBOX - GRADUADO");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Historial Calificaciones");
            System.out.println("2. Ver Informacion");
            System.out.println("3. Informacion Graduacion");
            System.out.println("4. Cerrar sesión");
            System.out.print(">> ");
            int opc = Tools.nextInt();
            switch (opc) {
                case 1 -> {
                    Tools.printHeader("CALIFICACIONES");
                    alumno.imprimirCalificaciones();
                }
                case 2 -> mostrarInformacion(alumno);
                case 3 -> alumno.informacionGraduacion();
                case 4 -> usuarioEnSesion.setUsuario(null);
                default -> System.out.println("Opcion ingresada incorrecta!!");
            }
        }
    }
}
