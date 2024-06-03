package src.Materia;

import java.util.ArrayList;
import java.util.Scanner;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.Grupo;
import src.Materia.util.NombreDeMateria;
import src.Sistema.util.Tools;
import src.Usuarios.Coordinador;
import src.Usuarios.Profesor;
import src.Usuarios.Usuario;
import src.Usuarios.util.FindID;
import src.Usuarios.util.Rol;

public class Materia {
    private static Scanner sc = new Scanner(System.in);
    private static int nextID;
    private NombreDeMateria nombre;
    private int id;
    private NombreDeCarrera carrera;
    private ArrayList<Grupo> grupos = new ArrayList<>();
    //se especifica como usuario ya que tanto un coordinador
    //como un profesor pueden impartir la materia
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

    public NombreDeMateria getNombre() {
        return nombre;
    }

    public void setProfesor(Profesor profesor, Materia materia){
        Profesor profesor1 = null;
        System.out.println("LA MATERIA IMPARTIDA POR "+profesor.getNombreCompleto().toUpperCase()+" SERA ASIGNADA A OTRO PROFESOR\n");
        //Profesor.printProfesores();
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
    }

    public void setProfesor(Coordinador coordinador, Materia materia){
        Profesor profesor1 = null;
        System.out.println("LA MATERIA IMPARTIDA POR "+profesor.getNombreCompleto().toUpperCase()+" SERA ASIGNADA A OTRO PROFESOR\n");
        //Profesor.printProfesores();
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
    }
    
}
