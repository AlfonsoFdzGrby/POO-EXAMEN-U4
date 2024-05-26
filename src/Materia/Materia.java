package src.Materia;

import src.Carrera.util.NombreDeCarrera;
import src.Usuarios.Usuario;

public class Materia {
    private static int nextID;
    private int id;
    private NombreDeCarrera carrera;
    //se especifica como usuario ya que tanto un coordinador
    //como un profesor pueden impartir la materia
    private Usuario profesor;

    public Materia(NombreDeCarrera carrera, Usuario profesor) {
        this.carrera = carrera;
        this.profesor = profesor;
        this.id = nextID;
        nextID++;
    }
    
}
