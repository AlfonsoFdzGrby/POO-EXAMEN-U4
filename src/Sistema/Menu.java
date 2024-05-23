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
        Sistema.carreras.add(new Carrera(NombreDeCarrera.ISC, 3, 0, 0, LocalDate.of(1963, 10, 23), "Laura Nelly Alvarado Zamora"));
        Sistema.agregarUsuario(new Alumno("Javier", "Martínez López", LocalDate.of(2000, 4, 9), "Morelia", "Michoacán", "Calle Matamoros #323", true, Sistema.carreras.get(0), 1, LetraGrupo.A, 0, "1234"), Rol.ALUMNO);
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
                    Usuario usuario = Sistema.iniciarSesion();
                    if(usuario instanceof Alumno){

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
        System.out.println("Se ha iniciado sesión correctamente con");

    }
}
