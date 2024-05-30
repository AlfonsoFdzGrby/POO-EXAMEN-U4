package src.Usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.Carrera.Carrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Materia.util.NombreDeMateria;
import src.Semestre.Semestre;
import src.Sistema.Sistema;
import src.Sistema.util.Tools;
import src.Usuarios.util.Calificaciones;
import src.Usuarios.util.Rol;

public class Alumno extends Usuario {
    private Carrera carrera;
    private int numSemestre;
    private LetraGrupo grupo;
    private double promedio;
    private boolean graduado = false;
    private boolean acredito = false;
    private HashMap<NombreDeMateria, Calificaciones> calificaciones = new HashMap<>();
    
    public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Carrera carrera, int semestre, LetraGrupo grupo, double promedio, String contraseña) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, Rol.ALUMNO);
        this.carrera = carrera;
        this.numSemestre = semestre;
        this.grupo = grupo;
        this.promedio = promedio;
        this.numControl = Tools.GenerateCtrlNum(nombre, LocalDate.now(), carrera.getCarrera(), rol);
        this.contraseña = contraseña;
        calificaciones.put(Sistema.semestres.get(carrera.getCarrera()).get(numSemestre-1).getMaterias().get(0).getNombre(), new Calificaciones(Sistema.semestres.get(carrera.getCarrera()).get(numSemestre-1).getMaterias().get(0).getNombre()));
        calificaciones.put(Sistema.semestres.get(carrera.getCarrera()).get(numSemestre-1).getMaterias().get(1).getNombre(), new Calificaciones(Sistema.semestres.get(carrera.getCarrera()).get(numSemestre-1).getMaterias().get(1).getNombre()));
        calificaciones.put(Sistema.semestres.get(carrera.getCarrera()).get(numSemestre-1).getMaterias().get(2).getNombre(), new Calificaciones(Sistema.semestres.get(carrera.getCarrera()).get(numSemestre-1).getMaterias().get(1).getNombre()));
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    public int getNumSemestre() {
        return numSemestre;
    }

    public HashMap<NombreDeMateria, Calificaciones> getCalificaciones() {
        return calificaciones;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public void setNumSemestre(int semestre) {
        this.numSemestre = semestre;
    }

    public void setGrupo(LetraGrupo grupo) {
        this.grupo = grupo;
    }

    public void setCalificaciones(HashMap<NombreDeMateria, Calificaciones> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public void graduar(){
        this.graduado = true;
    }

    public boolean isAcredito() {
        return acredito;
    }

    public void acreditarMaterias(){
        for (Calificaciones calificacion : calificaciones.values()) {
            calificacion.acreditar();
        }
    }

    public static void printAlumnos(){
        Tools.printHeader("LISTA DE ALUMNOS");
        for (Usuario usuario : Sistema.usuarios.get(Rol.ALUMNO)) {
            Alumno alumno = (Alumno)usuario;
            alumno.toString();
        }
    }

    public static void printAlumnos(boolean aprobado){
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(Alumno::isAcredito).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            for (Usuario usuario : Sistema.usuarios.get(Rol.ALUMNO)) {
                Alumno alumno = (Alumno)usuario;
                if (aprobado) {
                    if (alumno.acredito) {
                        alumno.toString();
                    }
                } else {
                    if (!alumno.acredito) {
                        alumno.toString();
                    }
                }
            }
        }
    }

    public static void printAlumnos(int numSemestre){
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
            filter(alumno -> numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
        }
    }

    public static void printAlumnos(int numSemestre, boolean aprobado){
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
                filter(alumno -> alumno.isAcredito() && numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
            }
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
                filter(alumno -> !alumno.isAcredito() && numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
            }
        }
        
    }

    public static void printAlumnos(int numSemestre, NombreDeMateria nombreDeMateria){
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
        }
    }

    public static void printAlumnos(int numSemestre, NombreDeMateria nombreDeMateria, boolean aprobado){
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
            }
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
            }
        }
            
    }

    public static void printAlumnos(int numSemestre, NombreDeMateria nombreDeMateria, int grupo){
        LetraGrupo lGrupo = grupo == 0 ? LetraGrupo.A : LetraGrupo.B ; 
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
        }
    }

    public static void printAlumnos(int numSemestre, NombreDeMateria nombreDeMateria, int grupo, boolean aprobado){
        LetraGrupo lGrupo = grupo == 0 ? LetraGrupo.A : LetraGrupo.B ;
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
            }
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre()).toList().forEach(alumno -> alumno.toString());
            }
        } 
    }
}
