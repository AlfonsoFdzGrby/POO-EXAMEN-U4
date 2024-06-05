package src.Carrera;

import java.time.LocalDate;
import java.util.ArrayList;

import src.Carrera.util.NombreDeCarrera;
import src.Semestre.Semestre;

public class Carrera {
    private static int nextID = 1;
    private int id;
    private NombreDeCarrera carrera;
    private String nombre;
    private int cantidadGrupos;
    private int cantidadAlumnos; 
    // solo el coordinador puede avanzar de semestre
    // después del tercer semestre es graduación
    // si el alumno reprueba una materia, se queda en el mismo semestre y grupo y sólo se quitan las calificaciones anteriores
    // (se queda en el mismo semestre, así haya sido una sola materia la que haya reprobado)
    // no puede avanzar de semestre si falta un alumno de calificación
    // GRADUADOS (x alumno): Fecha graduación (fecha con formato), carrera, alumnos graduados, generación de graduación
    // GRUPOS: máximo 2 grupos (A y B) si un grupo tiene más de 3 alumnos, se puede crear un siguiente grupo
    private int cantidadMaterias;
    private String fechaDeFundacion;
    private String coordinador;
    private ArrayList<Semestre> semestres;
    
    public Carrera(NombreDeCarrera carrera, int cantidadGrupos, int cantidadAlumnos, int cantidadMaterias,
            LocalDate fechaDeFundacion, String coordinador, ArrayList<Semestre> semestres) {
        this.carrera = carrera;

        switch (carrera) {
            case ISC -> nombre = "Ingeniería en Sistemas Computacionales";
            case IMT -> nombre = "Ingeniería en Materiales";
            case ELC -> nombre = "Ingeniería Eléctrónica";
            case GRADUADOS -> nombre = "Graduados";
        }

        this.cantidadGrupos = cantidadGrupos;
        this.cantidadAlumnos = cantidadAlumnos;
        this.cantidadMaterias = cantidadMaterias;
        this.fechaDeFundacion = fechaDeFundacion.toString();
        this.coordinador = coordinador;
        this.id = nextID;
        this.semestres = semestres;
        nextID++;
    }

    public ArrayList<Semestre> getSemestres() {
        return semestres;
    }

    public NombreDeCarrera getCarrera() {
        return carrera;
    }

}