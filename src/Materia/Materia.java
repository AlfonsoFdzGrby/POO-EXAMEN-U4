package src.Materia;

import src.Carrera.util.NombreDeCarrera;
import src.Materia.util.NombreDeMateria;
import src.Usuarios.Usuario;

public class Materia {
    private static int nextID;
    private NombreDeMateria nombre;
    private int id;
    private NombreDeCarrera carrera;
    //se especifica como usuario ya que tanto un coordinador
    //como un profesor pueden impartir la materia
    private Usuario profesor;

    public Materia(NombreDeCarrera carrera, Usuario profesor, NombreDeMateria nombreDeMateria) {
        this.carrera = carrera;
        this.profesor = profesor;
        this.id = nextID;
        this.nombre = nombreDeMateria;
        nextID++;
    }
    public int getId() {
        return id;
    }

    public NombreDeMateria getNombre() {
        return nombre;
    }
    
}
