package src.Usuarios;

import java.time.LocalDate;

import src.Carrera.Carrera;
import src.Grupos.util.LetraGrupo;
import src.Sistema.util.Tools;
import src.Usuarios.util.Rol;

public class Alumno extends Usuario {
    private Carrera carrera;
    private int semestre;
    private LetraGrupo grupo;
    private double promedio;
    
    public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Carrera carrera, int semestre, LetraGrupo grupo, double promedio, String contraseña) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, Rol.ALUMNO);
        this.carrera = carrera;
        this.semestre = semestre;
        this.grupo = grupo;
        this.promedio = promedio;
        this.numControl = Tools.GenerateCtrlNum(nombre, fechaNacimiento, carrera.getCarrera(), rol);
        this.contraseña = contraseña;
    }

    public Carrera getCarrera() {
        return carrera;
    }
    
}
