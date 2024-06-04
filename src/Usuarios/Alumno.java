package src.Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.util.NombreDeMateria;
import src.Sistema.Sistema;
import src.Sistema.util.Tools;
import src.Usuarios.util.Calificaciones;
import src.Usuarios.util.DatosComun;
import src.Usuarios.util.Rol;
import src.Usuarios.util.UsuarioEnSesion;

public class Alumno extends Usuario {

    @Expose
    private int numSemestre;
    
    @Expose
    private LetraGrupo grupo;
    
    @Expose
    private double promedio;
    
    @Expose
    private boolean graduado = false;
    
    @Expose
    private boolean acredito = false;
    
    @Expose
    private HashMap<NombreDeMateria, Calificaciones> calificaciones = new HashMap<>();
    
    public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, NombreDeCarrera carrera, int semestre, LetraGrupo grupo, double promedio, String contraseña) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, Rol.ALUMNO, carrera, contraseña);
        this.numSemestre = semestre;
        this.grupo = grupo;
        this.promedio = promedio;
        this.numControl = Tools.GenerateCtrlNum(nombre, LocalDate.now(), nombreCarrera, rol);
        this.contraseña = contraseña;
        calificaciones.put(Sistema.semestres.get(nombreCarrera).get(numSemestre-1).getMaterias().get(0).getNombre(), new Calificaciones(Sistema.semestres.get(nombreCarrera).get(numSemestre-1).getMaterias().get(0).getNombre()));
        calificaciones.put(Sistema.semestres.get(nombreCarrera).get(numSemestre-1).getMaterias().get(1).getNombre(), new Calificaciones(Sistema.semestres.get(nombreCarrera).get(numSemestre-1).getMaterias().get(1).getNombre()));
        calificaciones.put(Sistema.semestres.get(nombreCarrera).get(numSemestre-1).getMaterias().get(2).getNombre(), new Calificaciones(Sistema.semestres.get(nombreCarrera).get(numSemestre-1).getMaterias().get(1).getNombre()));
    }

    public NombreDeCarrera getCarrera() {
        return nombreCarrera;
    }

    public LetraGrupo getGrupo() {
        return grupo;
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

    //Método para imprimir calificaciones
    public void imprimirCalificaciones(){
        System.out.println("Materias:\tCalificación:");
        for (Calificaciones calificaciones : calificaciones.values()) {
            System.out.println("   * " + calificaciones.getMateria().toString() + ": \t" + calificaciones.getCalificacion());
        }
        Tools.next();
    }

    public void acreditarMaterias(){
        for (Calificaciones calificacion : calificaciones.values()) {
            calificacion.acreditar();
        }
    }

    public static void printAlumnos() throws Exception {
        Tools.printHeader("LISTA DE ALUMNOS");
        for (Usuario usuario : Sistema.usuarios.get(Rol.ALUMNO)) {
            Alumno alumno = (Alumno)usuario;
            if (UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())) {
                alumno.toString();    
            }
        }
    }

    public static void printAlumnos(boolean aprobado) throws Exception {
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.isAcredito() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.isAcredito() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
        }
    }

    public static void printAlumnos(int numSemestre) throws Exception {
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
            filter(alumno -> numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
        }
    }

    public static void printAlumnos(int numSemestre, boolean aprobado) throws Exception {
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
                filter(alumno -> alumno.isAcredito() && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
            }
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
                filter(alumno -> !alumno.isAcredito() && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
            }
        }
        
    }

    public static void printAlumnos(NombreDeMateria nombreDeMateria, boolean aprobado){
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
            }
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
            }
        }
    }

    public static void printAlumnos(int numSemestre, NombreDeMateria nombreDeMateria) throws Exception {
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
        }
    }

    public static void printAlumnos(int numSemestre, NombreDeMateria nombreDeMateria, boolean aprobado) throws Exception {
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
            }
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
            }
        }
            
    }

    public static void printAlumnos(int numSemestre, NombreDeMateria nombreDeMateria, int grupo) throws Exception {
        LetraGrupo lGrupo = grupo == 0 ? LetraGrupo.A : LetraGrupo.B ; 
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
        }
    }

    public static void printAlumnos(int numSemestre, NombreDeMateria nombreDeMateria, int grupo, boolean aprobado) throws Exception {
        LetraGrupo lGrupo = grupo == 0 ? LetraGrupo.A : LetraGrupo.B ;
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
            }
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> alumno.toString());
            }
        } 
    }

    public static void registrarAlumno(){
        LetraGrupo grupo = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<String> datosComun = DatosComun.datosComun();
        String nombre = datosComun.get(0);
        String apellidos = datosComun.get(1);
        char YoN = datosComun.get(2).charAt(0);
        boolean esHombre = Tools.AskForYesOrNo(YoN);
        String fecha = datosComun.get(3);
        String ciudad = datosComun.get(4);
        String estado = datosComun.get(5);
        String direccion = datosComun.get(6);
        String contrasena = datosComun.get(7);

        Rol rol = Rol.ALUMNO;
        LocalDate fechaNacimiento = LocalDate.parse(fecha, format);
        Coordinador coordinador = (Coordinador)UsuarioEnSesion.getInstancia().getUsuarioActual();
        NombreDeCarrera nombreDeCarrera = coordinador.getNombreCarrera();
        int numeroSemestre = 1;

        if (Sistema.semestres.get(nombreDeCarrera).get(0).getMaterias().get(0).getGrupos().get(0).getAlumnos().size() >= 10) {
            grupo = LetraGrupo.B;
        } else {
            grupo = LetraGrupo.A;
        }
        double promedio = 0;

        Alumno alumno = new Alumno(nombre, apellidos, fechaNacimiento, ciudad, estado, direccion, esHombre, nombreDeCarrera, numeroSemestre, grupo, promedio, contrasena);
    }
}
