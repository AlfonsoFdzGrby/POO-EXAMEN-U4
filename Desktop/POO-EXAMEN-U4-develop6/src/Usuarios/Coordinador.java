package src.Usuarios;

import java.time.LocalDate;
import java.util.Scanner;

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

public class Coordinador extends Usuario {
    private Scanner sc = new Scanner(System.in);
    private String rfc;
    private double sueldo;
    //private ArrayList<Materia> materiasImpartidas = new ArrayList<>();
    public Coordinador(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Rol rol, double sueldo, NombreDeCarrera nombreDeCarrera) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, rol, nombreDeCarrera);
        this.sueldo = sueldo;
        this.rfc = Tools.GenerateRFC(nombre, apellidos, fechaNacimiento);
    }

    public NombreDeCarrera getNombreCarrera() {
        return nombreCarrera;
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
        Carrera carrera = null;
        for (Carrera carrera1 : Sistema.carreras) {
            if (carrera1.getCarrera().equals(this.nombreCarrera)) {
                carrera = carrera1;
            }
        }
        Semestre semestre = carrera.getSemestres().get(numSemestre);
        Semestre.avanzarSemestre(semestre);
    }

    public void verAlumnos(){
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
            for (Carrera carrera1 : Sistema.carreras) {
                if (carrera1.getCarrera().equals(this.nombreCarrera)) {
                    carrera = carrera1;
                }
            }
            while (flag) {
                System.out.println("Que materia desea ver: ");
                System.out.println("1. "+carrera.getSemestres().get(numSemestre).getMaterias().get(0).getNombre().toString());
                System.out.println("2. "+carrera.getSemestres().get(numSemestre).getMaterias().get(1).getNombre().toString());
                System.out.println("3. "+carrera.getSemestres().get(numSemestre).getMaterias().get(2).getNombre().toString());
                System.out.println("4. Todas");
                System.out.print(">> ");

                int opt = Tools.nextInt();

                switch (opt) {
                    case 1 -> {
                        nombreMateria = carrera.getSemestres().get(numSemestre-1).getMaterias().get(0).getNombre();
                        flag = false;
                    }
                    case 2 -> {
                        nombreMateria = carrera.getSemestres().get(numSemestre-1).getMaterias().get(1).getNombre();
                        flag = false;
                    }
                    case 3 -> {
                        nombreMateria = carrera.getSemestres().get(numSemestre-1).getMaterias().get(2).getNombre();
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

    public void modificarProfesMaterias(){
        Profesor profesor = null;
        Tools.printHeader("MOVER PROFESOR DE MATERIAS");
        //Profesor.printProfesores();
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
            profesor.printMaterias();

            System.out.print(">> ");
            int opt = Tools.nextInt();

            try {
                Materia materia = FindID.findMateria(profesor.getMateriasImpartidas().get(opt));
                materia.setProfesor(profesor, materia);
                flag = false;    
            } catch (Exception e) {
                System.out.println("OPCION INVALIDA INGRESE OTRA");
            }
        }
    }

    public void modificarProfesor() {
        Tools.printHeader("MODIFICAR PROFESOR");
        //Profesor.printProfesores();
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

    public void modificarAlumno(){
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

    public void eliminarAlumno(){
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

    public void eliminarProfesor(){
        Tools.printHeader("DESPEDIR A UN PROFESOR");
        //Profesor.printProfesores();
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
}
