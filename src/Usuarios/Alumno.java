package src.Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.google.gson.annotations.Expose;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
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
    private String fechaGraduacion = null;
    
    @Expose
    private Map<NombreDeMateria, Calificaciones> calificaciones = new HashMap<>();
    
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

    public String toString(){
        return String.format("%s Semestre: %s\n Grupo: %s\n Promedio: %f\n ",super.toString(),this.numSemestre,this.grupo.toString(),this.promedio);
    }

    public void setFechaGraduacion(LocalDate fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion.toString();
    }

    public String getFechaGraduacion() {
        return fechaGraduacion;
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

    public boolean isGraduado() {
        return graduado;
    }

    public Map<NombreDeMateria, Calificaciones> getCalificaciones() {
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
    
    public double getPromedio() {
        return promedio;
    }

    //Método para imprimir calificaciones
    public void imprimirCalificaciones(){
        for (int i = 1; i <= numSemestre; i++) {
            System.out.println("SEMESTRE "+i);
            System.out.println("Materias:\tCalificación:");
            for (Calificaciones calificacion : calificaciones.values()) {
                for (int j = 0; j < 3; j++) {
                    if (Sistema.semestres.get(nombreCarrera).get(numSemestre).getMaterias().get(j).getNombre().equals(calificacion.getMateria())) {
                        System.out.println("   * " + calificacion.getMateria().toString() + ": \t" + calificacion.getCalificacion());
                    }    
                }
            }
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
            alumno.toString();
        }
        Tools.next();
    }

    public static void printAlumnos(boolean aprobado) throws Exception {
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
            }
            Tools.next();
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
            }
            Tools.next();
        }
    }

    public static void printAlumnos(int numSemestre) throws Exception {
        if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
        .filter(alumno -> numSemestre+1 == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
            System.out.println("No hay alumnos con este filtro!");
            Tools.next();
            return;
        } else {
            Tools.printHeader("LISTA DE ALUMNOS");
            Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
            filter(alumno -> numSemestre+1 == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
        }
        Tools.next();
    }

    public static void printAlumnos(int numSemestre, boolean aprobado) throws Exception {
        if (aprobado) {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> alumno.isAcredito() && numSemestre+1 == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
                filter(alumno -> alumno.isAcredito() && numSemestre+1 == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
            }
            Tools.next();
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && numSemestre+1 == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Tools.printHeader("LISTA DE ALUMNOS");
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario).
                filter(alumno -> !alumno.isAcredito() && numSemestre+1 == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
            }
            Tools.next();
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
            .filter(alumno -> alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
            }
        } else {
            if (Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().isEmpty()) {
                System.out.println("No hay alumnos con este filtro!");
                Tools.next();
                return;
            } else {
                Sistema.usuarios.get(Rol.ALUMNO).stream().map(usuario -> (Alumno) usuario)
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
            }
        }
        Tools.next();
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
        .filter(alumno -> alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
        }
        Tools.next();
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
            .filter(alumno -> alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
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
            .filter(alumno -> !alumno.isAcredito() && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
            }
        }
        Tools.next();    
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
        .filter(alumno -> alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
        }
        Tools.next();
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
            .filter(alumno -> alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
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
            .filter(alumno -> !alumno.isAcredito() && alumno.grupo.equals(lGrupo) && alumno.calificaciones.containsKey(nombreDeMateria) && numSemestre == alumno.getNumSemestre() && UsuarioEnSesion.getInstancia().getUsuarioActual().nombreCarrera.equals(alumno.getNombreCarrera())).toList().forEach(alumno -> System.out.println(alumno.toString()));
            }
        }
        Tools.next(); 
    }

    public static void registrarAlumno() throws Exception {
        Tools.printHeader("REGISTRAR ALUMNO");
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
        alumno.calificaciones.put(Sistema.semestres.get(nombreDeCarrera).get(0).getMaterias().get(0).getNombre(), new Calificaciones(Sistema.semestres.get(nombreDeCarrera).get(0).getMaterias().get(0).getNombre()));
        alumno.calificaciones.put(Sistema.semestres.get(nombreDeCarrera).get(0).getMaterias().get(1).getNombre(), new Calificaciones(Sistema.semestres.get(nombreDeCarrera).get(0).getMaterias().get(1).getNombre()));
        alumno.calificaciones.put(Sistema.semestres.get(nombreDeCarrera).get(0).getMaterias().get(2).getNombre(), new Calificaciones(Sistema.semestres.get(nombreDeCarrera).get(0).getMaterias().get(2).getNombre()));
        
        Sistema.usuarios.get(Rol.ALUMNO).add(alumno);
        System.out.println("Alumno registrado correctamente!!");
        Tools.next();
    }

    public void calcularPromedio(){
        double calculo = 0;
        for (Calificaciones calificacion : this.calificaciones.values()) {
            for (int j = 0; j < 3; j++) {
                if (calificacion.getMateria().equals(Sistema.semestres.get(this.nombreCarrera).get(this.numSemestre-1).getMaterias().get(j).getNombre())) {
                    calculo =+ calificacion.getCalificacion();
                }    
            }
        }
        this.promedio = calculo/3 ;
        this.acredito = promedio >= 70 ;
    }

    public void consultarMaterias() throws Exception {
        Tools.printHeader("MATERIAS EN CURSO");
        
        Alumno alumno = (Alumno)UsuarioEnSesion.getInstancia().getUsuarioActual();
        int i = 0;
        for (Materia materia : Sistema.semestres.get(nombreCarrera).get(alumno.getNumSemestre()-1).getMaterias()) {
            System.out.println(materia.toString() + " * Grupo : " + grupo.toString());
        }

        Tools.next();
    }

    public void informacionGraduacion() throws Exception {
        double calculo = 0;
        int i = 0;
        for (Calificaciones calificacion : this.calificaciones.values()) {
            for (int j = 0; j < 3; j++) {
                calculo =+ calificacion.getCalificacion();
                i++;
            }
        }
        promedio = calculo / i ;

        Tools.printHeader("INFORMACION GRADUACION");
        System.out.println("Usted se Graduó el "+this.fechaGraduacion+" con un promedio final de: "+promedio);
        Tools.next();
    }
}
