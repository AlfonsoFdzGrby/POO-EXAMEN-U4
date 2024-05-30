package src.Usuarios;

import java.time.LocalDate;
import java.util.Scanner;

import src.Carrera.util.NombreDeCarrera;
import src.Materia.Materia;
import src.Sistema.util.Tools;
import src.Usuarios.util.FindID;
import src.Usuarios.util.Rol;

public class Profesor extends Usuario {
    private Scanner sc = new Scanner(System.in);
    private String rfc;
    private double sueldo;

    public Profesor(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, double sueldo, NombreDeCarrera carrera) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, Rol.PROFESOR);
        this.sueldo = sueldo;
        this.rfc = Tools.GenerateRFC(nombre, apellidos, fechaNacimiento);
        this.numControl = Tools.GenerateCtrlNum(nombre, fechaNacimiento, carrera, rol);
    }
    
    //private ArrayList<Materia> materiasImpartidas = new ArrayList<>();

    public void calificarAlumno(Materia materia){
        Alumno alumno;
        Tools.printHeader("CALIFICAR ALUMNOS");
            while (true) {
                //printAlumnos(materia);
                System.out.println("Ingrese el Numero de Control del alumno que quiera calificar");
                System.out.print(">> ");
                String numControl = sc.nextLine();
                if (numControl.equals("-2")) {
                    return;
                }
                alumno = (Alumno)FindID.findControlNumber(numControl, Rol.ALUMNO);
                
                if (alumno != null) {
                    break;
                } else {
                    System.out.println("Ha ingresado un Numero de control inexistente");
                }
            }
            alumno.getCalificaciones().get(materia.getNombre()).toString();
            System.out.println("Que calificaciones desea darle a el alumno: ");
            System.out.print(">> ");
            double calificacion = Tools.nextDouble();
            alumno.getCalificaciones().get(materia.getNombre()).setCalificacion(calificacion);
            alumno.acreditarMaterias();
            if (alumno.isAcredito()) {
                System.out.println("El Alumno a acreditado todas sus materias!!");
            } else {
                System.out.println("El alumno aun no acredita todas sus materias");
            }
            System.out.println("Calificacion Modificada Correctamente!");
            Tools.next();
    }
}
