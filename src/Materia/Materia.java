package src.Materia;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.annotations.Expose;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.Grupo;
import src.Materia.util.NombreDeMateria;
import src.Sistema.util.Tools;
import src.Usuarios.Alumno;
import src.Usuarios.Coordinador;
import src.Usuarios.Profesor;
import src.Usuarios.Usuario;
import src.Usuarios.util.FindID;
import src.Usuarios.util.Rol;

public class Materia {
    @Expose(serialize = false, deserialize = false)
    private static Scanner sc = new Scanner(System.in);

    @Expose
    private static int nextID;
    
    @Expose
    private NombreDeMateria nombre;
    
    @Expose
    private int id;
    
    @Expose
    private NombreDeCarrera carrera;
    
    @Expose
    private ArrayList<Grupo> grupos = new ArrayList<>();
    
    @Expose
    private Usuario profesor;

    public Materia(NombreDeCarrera carrera, Usuario profesor, NombreDeMateria nombreDeMateria, ArrayList<Grupo> grupos) {
        this.carrera = carrera;
        this.profesor = profesor;
        this.id = nextID;
        this.grupos = grupos;
        this.nombre = nombreDeMateria;
        nextID++;
    }
    public int getId() {
        return id;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public NombreDeCarrera getCarrera() {
        return carrera;
    }

    public NombreDeMateria getNombre() {
        return nombre;
    }

    public String toString(){
        return String.format(" * Materia: %s\n Profesor: %s\n", nombre, profesor.getNombreCompleto());
    }

    public void setProfesor(Profesor profesor, Materia materia) throws Exception {
        Profesor profesor1 = null;
        System.out.println("LA MATERIA IMPARTIDA POR "+profesor.getNombreCompleto().toUpperCase()+" SERA ASIGNADA A OTRO PROFESOR\n");
        Profesor.printProfesores();
        while (true) {
            System.out.println("Ingrese el numero de control del nuevo profesor que se le sera asignada la materia");
            System.out.print(">> ");
            String numControl = sc.nextLine();
            profesor1 = (Profesor) FindID.findControlNumber(numControl, Rol.PROFESOR);
            
            if (profesor1 == null) {
                System.out.println("Numero de control incorrecto ingrese otro");
            } else {
                break;
            }
        }
        materia.profesor = profesor1;
        profesor1.agregarMateria(materia.getNombre());
        profesor.quitarMateria(materia.getNombre());
        System.out.println("Se ha cambiado al profesor encargado!!");
        Tools.next();
    }

    public void setProfesor(Coordinador coordinador, Materia materia) throws Exception {
        Profesor profesor1 = null;
        System.out.println("LA MATERIA IMPARTIDA POR "+profesor.getNombreCompleto().toUpperCase()+" SERA ASIGNADA A OTRO PROFESOR\n");
        Profesor.printProfesores();
        while (true) {
            System.out.println("Ingrese el numero de control del nuevo profesor que se le sera asignada la materia");
            System.out.print(">> ");
            String numControl = sc.nextLine();
            profesor1 = (Profesor) FindID.findControlNumber(numControl, Rol.PROFESOR);
            
            if (profesor1 == null) {
                System.out.println("Numero de control incorrecto ingrese otro");
            } else {
                break;
            }
        }
        materia.profesor = profesor1;
        profesor1.agregarMateria(materia.getNombre());
        coordinador.quitarMateria(materia.getNombre());
        System.out.println("Se ha cambiado al profesor encargado!!");
        Tools.next();
    }

    public void calificarGrupo() throws Exception {
        Tools.printHeader("CALIFICAR GRUPO");
        double calificacion = -1;

        for (Grupo grupo : grupos) {
            for (Alumno alumno : grupo.getAlumnos()) {
                System.out.println(alumno.getNombreCompleto() + " Promedio: " + alumno.getPromedio());
                alumno.getCalificaciones().get(this.getNombre()).toString();

                do {
                    System.out.println("\nQue calificacion desea otorgarle: ");
                    System.out.print(">> ");

                    calificacion = Tools.nextDouble();

                } while (calificacion < 0);

                alumno.getCalificaciones().get(this.getNombre()).setCalificacion(calificacion);
                alumno.calcularPromedio();
            }
        }

        System.out.println("Ha calificado a todo el grupo");
        Tools.next();
    }
    
}
