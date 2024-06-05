package src.Usuarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.annotations.Expose;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Materia.util.NombreDeMateria;
import src.Semestre.Semestre;
import src.Sistema.Sistema;
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

    public String toString(){
        return String.format("%s RFC: %s\n Sueldo: $%.2f\n",super.toString(),this.rfc,this.sueldo);
    }

    public static void printProfesores() throws Exception {
        Tools.printHeader("LISTA DE PROFESORES");
        Sistema.usuarios.get(Rol.PROFESOR).stream().filter(profesor -> profesor.getNombreCarrera().equals(UsuarioEnSesion.getInstancia()
        .getUsuarioActual().getNombreCarrera())).forEach(profesor -> System.out.println(profesor.toString()));
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
                this.printMateriasImpartidas();
    
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

    public void printMateriasImpartidas(){
        if (materiasImpartidas.isEmpty()) {
            System.out.println("No imparte ninguna materia!!");
            Tools.next();
            return;
        }

        int i = 1;
        for (NombreDeMateria materia : materiasImpartidas) {
            System.out.println(i + ". " + materia.toString());
            i++;
        }
        Tools.next();
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
        System.out.println("Profesor registrado correctamente!!");
        Sistema.usuarios.get(Rol.PROFESOR).add(profesor);
        Tools.next();
    }

    public void calificarGrupo() throws Exception {
        NombreDeMateria nombreMateria = null ;

        if (materiasImpartidas.isEmpty()) {
            System.out.println("No imparte ninguna materia!!");
            Tools.next();
            return;
        }

        while (nombreMateria == null) {
            System.out.println("Que materia desea calificar: ");
            this.printMateriasImpartidas();

            System.out.print(">> ");

            int opt = Tools.nextInt();
            try {
                nombreMateria = materiasImpartidas.get(opt-1); 
            } catch (Exception e) {
                System.out.println("No se encontro la opcion que solicito, ingrese otra");
            }   
        }

        for (ArrayList<Semestre> semestres : Sistema.semestres.values()) {
            for (Semestre semestre : semestres) {
                for (Materia materia : semestre.getMaterias()) {
                    if (materia.getNombre().equals(nombreMateria)) {
                        materia.calificarGrupo();
                    }
                }
            }  
        }
    }

    public void mostrarGrupos() throws Exception{
        if (materiasImpartidas.isEmpty()) {
            System.out.println("No imparte ninguna materia!!");
            Tools.next();
            return;
        }

        Tools.printHeader("GRUPOS A LOS CUALES IMPARTE CLASE");

        for (ArrayList<Semestre> semestres : Sistema.semestres.values()) {
            for (Semestre semestre : semestres) {
                for (Materia materia : semestre.getMaterias()) {
                    if (materiasImpartidas.contains(materia.getNombre())) {
                        System.out.println("MATERIA : "+materia.getNombre().toString().toUpperCase());
                        System.out.println(" GRUPO A : ");
                        for (Alumno alumno : materia.getGrupos().get(0).getAlumnos()) {
                            System.out.println("  "+alumno.toString());    
                        }
                        if (!materia.getGrupos().get(1).getAlumnos().isEmpty()) {
                            System.out.println("GRUPO B : ");
                            for (Alumno alumno : materia.getGrupos().get(1).getAlumnos()) {
                                System.out.println("  "+alumno.toString());    
                            }
                        }
                    }
                }
            }  
        }

        Tools.next();
    }
}
