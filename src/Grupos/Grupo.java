package src.Grupos;

import java.util.ArrayList;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Semestre.Semestre;
import src.Sistema.util.Tools;
import src.Usuarios.Alumno;

public class Grupo {
    // un maestro puede impartir multiples materias
    // mínimo 70 para pasar
    private static int nextID;
    private NombreDeCarrera nombreDeCarrera;
    private ArrayList<Alumno> alumnos;
    private LetraGrupo grupo;
    private int id;
    //private ArrayList<Materia> materias;
    private Semestre semestre;

    public Grupo(NombreDeCarrera nombreDeCarrera, ArrayList<Alumno> alumnos, LetraGrupo grupo,
            ArrayList<Materia> materias, Semestre semestre) {
        this.nombreDeCarrera = nombreDeCarrera;
        this.alumnos = alumnos;
        this.grupo = grupo;
        //this.materias = materias;
        this.semestre = semestre;
        this.id = nextID;
        nextID++;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void agregarAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }
}
