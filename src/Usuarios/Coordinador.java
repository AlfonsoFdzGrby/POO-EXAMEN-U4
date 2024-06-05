package src.Usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.annotations.Expose;

import src.Carrera.Carrera;
import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Materia.util.NombreDeMateria;
import src.Semestre.Semestre;
import src.Sistema.Sistema;
import src.Sistema.util.Tools;
import src.Usuarios.util.FindID;
import src.Usuarios.util.Rol;
import src.Usuarios.util.UsuarioEnSesion;

public class Coordinador extends Usuario {
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

    public Coordinador(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Rol rol, double sueldo, NombreDeCarrera nombreDeCarrera, String contraseña) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, rol, nombreDeCarrera, contraseña);
        this.sueldo = sueldo;
        this.materiasImpartidas = new ArrayList<>();
        this.rfc = Tools.GenerateRFC(nombre, apellidos, fechaNacimiento);
        this.numControl = Tools.GenerateCtrlNum(nombre, fechaNacimiento, nombreDeCarrera, rol);
    }

    public String toString(){
        return String.format("%s RFC: %s\n Sueldo: $%.2f\n ",super.toString(),this.rfc,this.sueldo);
    }

    public NombreDeCarrera getNombreCarrera() {
        return nombreCarrera;
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
    }

    public void printMateriasGeneral(){
        int i = 1;
        for (ArrayList<Semestre> semestres : Sistema.semestres.values()) {
            for (Semestre semestre : semestres) {
                for (Materia materia : semestre.getMaterias()) {
                    if (materia.getCarrera().equals(UsuarioEnSesion.getInstancia().getUsuarioActual().getNombreCarrera())) {
                        System.out.println(i + ". " + materia.getNombre().toString());
                        i++;    
                    }
                }
            }  
        }
    }

    public void verProfesores() throws Exception {
        Profesor.printProfesores();
        Tools.next();
    }
    
    public void avanzarSemestre(){
        int numSemestre = -1;
        while (numSemestre < 1 && numSemestre > 3) {
            System.out.println("Ingrese el numero de Semestre que desea avanzar: ");
            numSemestre = Tools.nextInt();
            if (numSemestre < 1 && numSemestre > 3) {
                System.out.println("Ha ingresado un semestre no existente, intente de nuevo...");
            }            
        }

        Semestre semestre = Sistema.semestres.get(nombreCarrera).get(numSemestre-1);
        Semestre.avanzarSemestre(semestre);
    }

    public void verAlumnos() throws Exception {
        int numSemestre = 0;
        NombreDeMateria nombreMateria = null;
        int grupo = -1;
        boolean aprobado = true;
        boolean all = false;
        boolean flag = true;
        Tools.printHeader("VER ALUMNOS");
        while (flag) {
            System.out.println("Desea ver los alumnos: ");
            System.out.println("1. Reprobados");
            System.out.println("2. Aprobados");
            System.out.println("3. Todos");
            System.out.print(">> ");

            int opt = Tools.nextInt();

            switch (opt) {
                case 1 -> {
                    aprobado = false;
                    flag = false; }
                case 2 -> {
                    aprobado = true;
                    flag = false; }
                case 3 -> {
                    all = true;
                    flag = false; }
                default -> System.out.println("Ha ingresado una opcion incorrecta!");    
            }    
        }
        flag = true;
        boolean filtro;
        System.out.println("Desea filtrar por Semestre / Materia / Grupo ? (S/N)");
        System.out.print(">> ");
        char YoN = sc.next().charAt(0);
        filtro = Tools.AskForYesOrNo(YoN);

        if (filtro) {
            while (flag) {
                System.out.println("Que semestre desea ver: ");
                System.out.println("Semestre 1");
                System.out.println("Semestre 2");
                System.out.println("Semestre 3");
                System.out.print(">> ");

                int opt = Tools.nextInt();

                switch (opt) {
                    case 1 -> {
                        numSemestre = 0;
                        flag = false;}
                    case 2 -> {
                        numSemestre = 1;
                        flag = false;}
                    case 3 -> {
                        numSemestre = 2;
                        flag = false;}    
                    default -> System.out.println("Semestre no existente");
                }
            }
            flag = true;
            Carrera carrera = null;
            while (flag) {
                System.out.println("Que materia desea ver: ");
                System.out.println("1. "+Sistema.semestres.get(this.nombreCarrera).get(numSemestre).getMaterias().get(0));
                System.out.println("2. "+Sistema.semestres.get(this.nombreCarrera).get(numSemestre).getMaterias().get(0));
                System.out.println("3. "+Sistema.semestres.get(this.nombreCarrera).get(numSemestre).getMaterias().get(0));
                System.out.println("4. Todas");
                System.out.print(">> ");

                int opt = Tools.nextInt();

                switch (opt) {
                    case 1 -> {
                        nombreMateria = Sistema.semestres.get(this.nombreCarrera).get(numSemestre).getMaterias().get(0).getNombre();
                        flag = false;
                    }
                    case 2 -> {
                        nombreMateria = Sistema.semestres.get(this.nombreCarrera).get(numSemestre).getMaterias().get(1).getNombre();
                        flag = false;
                    }
                    case 3 -> {
                        nombreMateria = Sistema.semestres.get(this.nombreCarrera).get(numSemestre).getMaterias().get(2).getNombre();
                        flag = false;
                    }
                    case 4 -> {
                        nombreMateria = null;
                        flag = false;
                    }
                    default -> {
                        System.out.println("Ingreso una opcion incorrecta intente de nuevo!!");
                    }
                }
            } if (nombreMateria == null) {
                if (all) {
                    Alumno.printAlumnos(numSemestre);
                } else {
                    Alumno.printAlumnos(numSemestre, aprobado);
                }
            } else {
                flag = true;
                while (flag) {
                    System.out.println("Que grupo desea ver: ");
                    System.out.println("1. A");
                    System.out.println("2. B");
                    System.out.println("3. Ambos");
                    System.out.print(">> ");

                    int opt = Tools.nextInt();

                    switch (opt) {
                        case 1 -> {
                            grupo = 0;
                            flag = false;
                        }
                        case 2 -> {
                            grupo = 1;
                            flag = false;
                        }
                        case 3 -> {
                            grupo = -1;
                            flag = false;
                        }
                        default -> {
                            System.out.println("Ingreso una opcion incorrecta intente de nuevo!!");
                        }
                    }
                }
                flag = true;
                if (grupo == -1) {
                    if (all) {
                        Alumno.printAlumnos(numSemestre, nombreMateria);
                    } else {
                        Alumno.printAlumnos(numSemestre, nombreMateria, aprobado);
                    }
                } else {
                    if (all) {
                        Alumno.printAlumnos(numSemestre, nombreMateria, grupo);
                    } else {
                        Alumno.printAlumnos(numSemestre, nombreMateria, grupo, aprobado);
                    }
                }
            }
        } else {
            if (all) {
                Alumno.printAlumnos();
            } else {
                Alumno.printAlumnos(aprobado);
            }
        }
    }

    public void modificarProfesMaterias() throws Exception {
        Profesor profesor = null;
        Tools.printHeader("MOVER PROFESOR DE MATERIAS");
        Profesor.printProfesores();
        while (true) {
            System.out.println("De que profesor desea cambiar la materia que imparte: (Ingrese su numero de control)");
            System.out.print(">> ");
            String numControl = sc.nextLine();

            profesor = (Profesor) FindID.findControlNumber(numControl, Rol.PROFESOR);
            
            if (profesor != null) {
                break;
            } else {
                System.out.println("Numero de control no encontrado, ingrese otro!!");
            }
        }
        
        boolean flag = true;

        if (profesor.getMateriasImpartidas().isEmpty()) {
            System.out.println("El profesor seleccionado no tiene materias para cambiar!!");
            return;
        }

        while (flag) {
            System.out.println("Que materia del profesor desea cambiar: ");
            profesor.printMateriasImpartidas();

            System.out.print(">> ");
            int opt = Tools.nextInt();

            try {
                Materia materia = FindID.findMateria(profesor.getMateriasImpartidas().get(opt-1));
                materia.setProfesor(profesor, materia);
                flag = false;    
            } catch (Exception e) {
                System.out.println("OPCION INVALIDA INGRESE OTRA");
            }
        }
    }

    public void modificarMateriaCoordinador() throws Exception {
        NombreDeMateria nombreMateria;
        if (materiasImpartidas.isEmpty()) {
            System.out.println("No imparte ninguna materia!!");
            Tools.next();
            return;
        }
        
        boolean flag = true;

        while (flag) {
            Tools.printHeader("HA SELECCIONADO CAMBIAR UNA MATERIA QUE USTED IMPARTE A OTRO PROFESOR");
            System.out.println("Que materia desea cambiar: ");
            this.printMateriasImpartidas();
            System.out.print(">> ");

            int opt = Tools.nextInt();
            try {
                Materia materia = FindID.findMateria(this.getMateriasImpartidas().get(opt-1));
                materia.setProfesor(this, materia);
                flag = false;    
            } catch (Exception e) {
                System.out.println("OPCION INVALIDA INGRESE OTRA");
            }     
        }
    }

    public void modificarProfesor() throws Exception {
        Tools.printHeader("MODIFICAR PROFESOR");
        Profesor.printProfesores();
        Profesor profesor = null;
        while (true) {
            System.out.println("Ingrese el Numero de Control del profesor que desea modificar");
            System.out.print(">> ");
            String numControl = sc.nextLine();

            profesor = (Profesor) FindID.findControlNumber(numControl, Rol.PROFESOR);

            if (profesor != null) {
                System.out.println("Profesor encontrado");
                break;
            } else {
                System.out.println("Numero de control no encontrado, ingrese uno correcto!!");
            }    
        }

        System.out.println("Ingrese la nueva contraseña: ");
        System.out.print(">> ");
        String nuevaContrasena = sc.nextLine();

        profesor.setContraseña(nuevaContrasena);

        System.out.println("Datos del profesor actualizados correctamente.");
        Tools.next();
    }

    public void modificarAlumno() throws Exception {
        Tools.printHeader("MODIFICAR ALUMNO");
        Alumno.printAlumnos();
        Alumno usuario = null;
        while (true) {
            System.out.println("Ingrese el Numero de Control del alumno que desea modificar");
            System.out.print(">> ");
            String numControl = sc.nextLine();

            usuario = (Alumno) FindID.findControlNumber(numControl, Rol.ALUMNO);

            if (usuario != null) {
                System.out.println("Alumno encontrado");
                break;
            } else {
                System.out.println("Numero de control no encontrado, ingrese uno correcto!!");
            }    
        }

        System.out.println("Ingrese la nueva contraseña: ");
        System.out.print(">> ");
        String nuevaContrasena = sc.nextLine();

        usuario.setContraseña(nuevaContrasena);

        System.out.println("Datos del profesor actualizados correctamente.");
        Tools.next();
    }

    public void eliminarAlumno() throws Exception {
        Tools.printHeader("DAR DE BAJA A UN ALUMNO");
        Alumno.printAlumnos();
        System.out.println("A que alumno desea dar de baja (ingrese el numero de control del alumno): ");
        String numControl= sc.nextLine();
        Alumno usuario = (Alumno)FindID.findControlNumber(numControl,Rol.ALUMNO);
        System.out.println("Esta seguro que desea dar de baja al alumno (S/N)");
        System.out.println(">>");
        char opt = sc.nextLine().charAt(0);
        if(Tools.AskForYesOrNo(opt)){
            Sistema.usuarios.get(Rol.ALUMNO).remove(usuario);
            for (int i = 0; i<2;i++){
                int indexGrupo =  usuario.getGrupo().equals(LetraGrupo.A) ? 0 : 1 ;
                Sistema.semestres.get(usuario.getCarrera()).get(usuario.getNumSemestre()-1).getMaterias().get(i).getGrupos().get(indexGrupo).getAlumnos().remove(usuario);
            }
            System.out.println("SE HA DADO DE BAJA AL ALUMNO\n");
            Tools.next();
        }
        else{
            System.out.println("\n NO SE HA DADO DE BAJA A NINGUN ALUMNO\n");
            Tools.next();
        }

    }

    public void eliminarProfesor() throws Exception {
        Tools.printHeader("DESPEDIR A UN PROFESOR");
        Profesor.printProfesores();
        System.out.println("A que profesor desea despedir (ingrese el numero de control del profesor): ");
        String numControl= sc.nextLine();
        Profesor usuario = (Profesor)FindID.findControlNumber(numControl,Rol.PROFESOR);
        System.out.println("Esta seguro que desea despedir al profesor? (S/N)");
        System.out.println(">>");
        char opt = sc.nextLine().charAt(0);
        if(Tools.AskForYesOrNo(opt)){
            Sistema.usuarios.get(Rol.PROFESOR).remove(usuario);
            System.out.println("Las materias que impartia el profesor deben de ser asignadas a un nuevo profesor");
            for (int j = 0; j < 3; j++) {
                for (NombreDeMateria nombreDeMateria : usuario.getMateriasImpartidas()) {
                    for (Materia materia : Sistema.semestres.get(usuario.nombreCarrera).get(j).getMaterias()) {
                        if (materia.getNombre().equals(nombreDeMateria)) {
                            materia.setProfesor(usuario, materia);
                        }
                    }
                }    
            }
            System.out.println("\nSE HA DESPEDIDO CORRECTAMENTE AL PROFESOR\n");
            Tools.next();
        }
        else{
            System.out.println("\n NO SE HA DESPEDIDO NINGUN PROFESOR\n");
            Tools.next();
        }
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

    public void mostrarGrupos() throws Exception {
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
