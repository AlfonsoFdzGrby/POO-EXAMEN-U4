package src.Usuarios;

import java.time.LocalDate;
import java.util.Scanner;

import src.Carrera.Carrera;
import src.Materia.util.NombreDeMateria;
import src.Semestre.Semestre;
import src.Sistema.Sistema;
import src.Sistema.util.Tools;
import src.Usuarios.util.Rol;

public class Coordinador extends Usuario {
    private Scanner sc = new Scanner(System.in);
    private String rfc;
    private double sueldo;
    private Carrera carrera;
    //private ArrayList<Materia> materiasImpartidas = new ArrayList<>();
    public Coordinador(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Rol rol, double sueldo) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, rol);
        this.sueldo = sueldo;
        this.rfc = Tools.GenerateRFC(nombre, apellidos, fechaNacimiento);
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
}
