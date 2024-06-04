package src.Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.annotations.Expose;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.util.NombreDeMateria;
import src.Sistema.util.Tools;
import src.Usuarios.util.DatosComun;
import src.Usuarios.util.FindID;
import src.Usuarios.util.Rol;
import src.Usuarios.util.UsuarioEnSesion;

public class Profesor extends Usuario {
    @Expose(serialize = false, deserialize = false)
    private static Scanner sc = new Scanner(System.in);

    @Expose
    private String rfc;

    @Expose
    private double sueldo;
    
    @Expose
    private ArrayList<NombreDeMateria> materiasImpartidas = new ArrayList<>();
    
    @Expose
    private int numeroMaterias = 0;

    public Profesor(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, NombreDeCarrera nombreCarrera, boolean esHombre, double sueldo, NombreDeCarrera carrera, ArrayList<NombreDeMateria> materiasImpartidas, String contraseña) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, Rol.PROFESOR, nombreCarrera, contraseña);
        this.sueldo = sueldo;
        this.materiasImpartidas = materiasImpartidas;
        this.rfc = Tools.GenerateRFC(nombre, apellidos, fechaNacimiento);
        this.numControl = Tools.GenerateCtrlNum(nombre, fechaNacimiento, carrera, rol);
    }

    public void calificarAlumno(NombreDeMateria nombreMateria) throws Exception {
        Alumno alumno;
        Tools.printHeader("CALIFICAR ALUMNOS");
            while (true) {
                Alumno.printAlumnos(nombreMateria, false);
                System.out.println("Ingrese el Numero de Control del alumno que quiera calificar");
                System.out.print(">> ");
                String numControl = sc.nextLine();
                if (numControl.equals("-2")) {
                    return;
                }
                alumno = (Alumno)FindID.findControlNumber(numControl, Rol.ALUMNO);
                
                if (alumno != null) {
                    break;
                } else {
                    System.out.println("Ha ingresado un Numero de control inexistente");
                }
            }
            alumno.getCalificaciones().get(nombreMateria).toString();
            System.out.println("Que calificaciones desea darle a el alumno: ");
            System.out.print(">> ");
            double calificacion = Tools.nextDouble();
            alumno.getCalificaciones().get(nombreMateria).setCalificacion(calificacion);
            alumno.acreditarMaterias();
            if (alumno.isAcredito()) {
                System.out.println("El Alumno a acreditado todas sus materias!!");
            } else {
                System.out.println("El alumno aun no acredita todas sus materias");
            }
            System.out.println("Calificacion Modificada Correctamente!");
            Tools.next();
    }

    public void agregarMateria(NombreDeMateria nombreDeMateria){
        this.materiasImpartidas.add(nombreDeMateria);
        this.numeroMaterias++;
    }

    public void quitarMateria(NombreDeMateria nombreDeMateria){
        this.materiasImpartidas.remove(nombreDeMateria);
        this.numeroMaterias--;
    }

    public ArrayList<NombreDeMateria> getMateriasImpartidas() {
        return materiasImpartidas;
    }

    public void verAlumnos() throws Exception {
        boolean aprobado = true;
        boolean flag = true;
        Tools.printHeader("VER ALUMNOS");
        while (flag) {
            System.out.println("Desea ver los alumnos: ");
            System.out.println("1. Reprobados");
            System.out.println("2. Aprobados");
            System.out.print(">> ");

            int opt = Tools.nextInt();

            switch (opt) {
                case 1 -> {
                    aprobado = false;
                    flag = false; }
                case 2 -> {
                    aprobado = true;
                    flag = false; }
                default -> System.out.println("Ha ingresado una opcion incorrecta!");    
            }    
        }
        System.out.println("Desea filtrar por alguna materia? (S/N)");
        System.out.print(">> ");
        char YoN = sc.next().charAt(0);
        boolean filtro = Tools.AskForYesOrNo(YoN);
        NombreDeMateria nombreMateria = null ;

        if (filtro) {
            while (nombreMateria == null) {
                System.out.println("Que materia desea filtrar: ");
                this.printMaterias();
    
                System.out.print(">> ");
    
                int opt = Tools.nextInt();
                try {
                    nombreMateria = materiasImpartidas.get(opt-1); 
                } catch (Exception e) {
                    System.out.println("No se encontro la opcion que solicito, ingrese otra");
                }   
            }
            Alumno.printAlumnos(nombreMateria, aprobado);
        } else {
            Alumno.printAlumnos(aprobado);
        }
    }

    public NombreDeCarrera getCarrera() {
        return nombreCarrera;
    }

    public static void printMaterias(){
        int i = 1;
        /*foreach(materias){
            sout("i. "+nombreMateria.toString())
            }
         * 
         * 
        */
    }

    public void registerProfesor(){
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

        Rol rol = Rol.PROFESOR;
        LocalDate fechaNacimiento = LocalDate.parse(fecha, format);
        Coordinador coordinador = (Coordinador)UsuarioEnSesion.getInstancia().getUsuarioActual();
        NombreDeCarrera nombreDeCarrera = coordinador.getNombreCarrera();
        double sueldo = 0;

        Profesor profesor = new Profesor(nombre, apellidos, fechaNacimiento, ciudad, estado, direccion, nombreDeCarrera, esHombre, sueldo, nombreDeCarrera, materiasImpartidas, contrasena);
    }
}
