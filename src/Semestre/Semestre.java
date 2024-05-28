package src.Semestre;

import java.util.ArrayList;
import java.util.Scanner;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.Grupo;
import src.Materia.Materia;
import src.Sistema.Sistema;
import src.Sistema.util.Tools;
import src.Usuarios.Alumno;
import src.Usuarios.Usuario;

public class Semestre {
    private static Scanner sc = new Scanner(System.in);
    private static int nextID = 1;
    private int id;
    private int numSemestre;
    private NombreDeCarrera nombreDeCarrera;
    private ArrayList<Grupo> grupos;
    private ArrayList<Materia> materias;
    
    public Semestre(NombreDeCarrera nombreDeCarrera, ArrayList<Grupo> grupos) {
        this.nombreDeCarrera = nombreDeCarrera;
        this.grupos = grupos;
        this.id = nextID;
        this.numSemestre = this.id;
        nextID++;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void avanzarSemestre(Semestre semestre){
        System.out.println("Se avanzará a todos los alumnos del semestre con calificacion mayor a 70");
        System.out.println("Esta seguro: (S/N)");
        char opt = sc.next().charAt(0);
        boolean YoN = Tools.AskForYesOrNo(opt);

        if (YoN) {
            Semestre semestreCopy = Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre-1);
            for (int i = 0; i < 2; i++) {
                for (Alumno alumno : semestreCopy.grupos.get(i).getAlumnos()) {
                    if (alumno.isAcredito()) {
                        Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre-1).grupos.get(i).getAlumnos().remove(alumno);
                        if (Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre).grupos.get(0).getAlumnos().size() == 10) {
                            Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre).grupos.get(1).getAlumnos().add(alumno);
                        } else {
                            Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre).grupos.get(0).getAlumnos().add(alumno);
                        }    
                    }
                }    
            }
        } else {
            Tools.next();
            return;
        }

        
    }


    
}
