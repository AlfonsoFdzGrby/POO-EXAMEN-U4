package src.Carrera;

import java.time.LocalDate;

import src.Carrera.util.NombreDeCarrera;

public class Carrera {
    private static int nextID = 1;
    private int id;
    private NombreDeCarrera carrera;
    private String nombre;
    private int cantidadGrupos;
    private int cantidadAlumnos; //solo el coordinador puede avanzar de semestre
    // después del tercer semestre es graduación
    // si el alumno reprueba una materia, se queda en el mismo semestre y grupo y sólo se quitan las calificaciones anteriores
    // (se queda en el mismo semestre, así haya sido una sola materia la que haya reprobado)
    // no puede avanzar de semestre si falta un alumno de calificación
    // GRADUADOS (x alumno): Fecha graduación (fecha con formato), carrera, alumnos graduados, generación de graduación
    // GRUPOS: máximo 2 grupos (A y B) si un grupo tiene más de 3 alumnos, se puede crear un siguiente grupo
    private int cantidadMaterias;
    private LocalDate fechaDeFundacion;
    private String coordinador;
    
    public Carrera(NombreDeCarrera carrera, int cantidadGrupos, int cantidadAlumnos, int cantidadMaterias,
            LocalDate fechaDeFundacion, String coordinador) {
        this.carrera = carrera;

        switch (carrera) {
            case ISC -> nombre = "Ingeniería en Sistemas Computacionales";
            case IMAT -> nombre = "Ingeniería en Materiales";
            case ELC -> nombre = "Ingeniería Eléctrónica";
        }

        this.cantidadGrupos = cantidadGrupos;
        this.cantidadAlumnos = cantidadAlumnos;
        this.cantidadMaterias = cantidadMaterias;
        this.fechaDeFundacion = fechaDeFundacion;
        this.coordinador = coordinador;
        this.id = nextID;
        nextID++;
    }

    public NombreDeCarrera getCarrera() {
        return carrera;
    }

}