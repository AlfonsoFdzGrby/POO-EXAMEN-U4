package src.Grupos;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Semestre.Semestre;
import src.Sistema.util.Tools;
import src.Usuarios.Alumno;

public class Grupo {
    // un maestro puede impartir multiples materias
    // mínimo 70 para pasar
    @Expose
    private static int nextID;

    @Expose
    private NombreDeCarrera nombreDeCarrera;
    
    @Expose
    private ArrayList<Alumno> alumnos;
    
    @Expose
    private LetraGrupo grupo;
    
    @Expose
    private int id;

    public Grupo(NombreDeCarrera nombreDeCarrera, ArrayList<Alumno> alumnos, LetraGrupo grupo) {
        this.nombreDeCarrera = nombreDeCarrera;
        this.alumnos = alumnos;
        this.grupo = grupo;
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
