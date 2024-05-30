package src.Materia;

import java.util.ArrayList;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.Grupo;
import src.Materia.util.NombreDeMateria;
import src.Usuarios.Usuario;

public class Materia {
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
    
}
