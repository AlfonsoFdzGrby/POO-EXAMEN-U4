package src.Semestre;

import java.util.ArrayList;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.Grupo;

public class Semestre {
    private static int nextID = 1;
    private int id;
    private int numSemestre;
    private NombreDeCarrera nombreDeCarrera;
    private ArrayList<Grupo> grupos;
    
    public Semestre(NombreDeCarrera nombreDeCarrera, ArrayList<Grupo> grupos) {
        this.nombreDeCarrera = nombreDeCarrera;
        this.grupos = grupos;
        this.id = nextID;
        this.numSemestre = this.id;
        nextID++;
    }
    
}
