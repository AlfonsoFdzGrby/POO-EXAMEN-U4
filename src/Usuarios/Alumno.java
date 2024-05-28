package src.Usuarios;

import java.time.LocalDate;
import java.util.HashMap;

import src.Carrera.Carrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Semestre.Semestre;
import src.Sistema.util.Tools;
import src.Usuarios.util.Calificaciones;
import src.Usuarios.util.Rol;

public class Alumno extends Usuario {
    private Carrera carrera;
    private Semestre semestre;
    private LetraGrupo grupo;
    private double promedio;
    private boolean graduado = false;
    private boolean acredito = false;
    private HashMap<Materia, Calificaciones> calificaciones = new HashMap<>();
    
    public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Carrera carrera, Semestre semestre, LetraGrupo grupo, double promedio, String contraseña) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, Rol.ALUMNO);
        this.carrera = carrera;
        this.semestre = semestre;
        this.grupo = grupo;
        this.promedio = promedio;
        this.numControl = Tools.GenerateCtrlNum(nombre, LocalDate.now(), carrera.getCarrera(), rol);
        this.contraseña = contraseña;
        calificaciones.put(semestre.getMaterias().get(0), new Calificaciones(semestre.getMaterias().get(0).getNombre()));
        calificaciones.put(semestre.getMaterias().get(1), new Calificaciones(semestre.getMaterias().get(1).getNombre()));
        calificaciones.put(semestre.getMaterias().get(2), new Calificaciones(semestre.getMaterias().get(2).getNombre()));
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void graduar(){
        this.graduado = true;
    }

    public boolean isAcredito() {
        return acredito;
    }

    public void acreditarMaterias(){
        if (calificaciones.get(semestre.getMaterias().get(0)).isAcreditado() && calificaciones.get(semestre.getMaterias().get(1)).isAcreditado() &&
        calificaciones.get(semestre.getMaterias().get(2)).isAcreditado()) {
            this.acredito = true;
            System.out.println("Se han acreditado todas las materias, solicite su avance de semestre");
            Tools.next();
        } else {
            System.out.println("No ha aprobado todas las materias! Pregunte a sus maestros");
            Tools.next();
        }
    }
    
}
