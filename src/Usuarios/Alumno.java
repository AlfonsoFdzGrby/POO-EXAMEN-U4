package src.Usuarios;

import java.time.LocalDate;

import src.Carrera.Carrera;
import src.Grupos.util.LetraGrupo;
import src.Sistema.util.Tools;

public class Alumno extends Usuario {
    private Carrera carrera;
    private int semestre;
    private LetraGrupo grupo;
    private double promedio;
    private String numControl; //REQUIERE DE UN GENERADOR
    
    public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Carrera carrera, int semestre, LetraGrupo grupo, double promedio) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre);
        this.carrera = carrera;
        this.semestre = semestre;
        this.grupo = grupo;
        this.promedio = promedio;
        this.numControl = Tools.GenerateCtrlNum(nombre, fechaNacimiento, carrera.getCarrera());
    }
    
}
