package src.Semestre;

import java.util.ArrayList;
import java.util.Scanner;

import src.Carrera.util.NombreDeCarrera;
import src.Grupos.Grupo;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Sistema.Sistema;
import src.Sistema.util.Tools;
import src.Usuarios.Alumno;
import src.Usuarios.Usuario;
import src.Usuarios.util.Calificaciones;

public class Semestre {
    private static Scanner sc = new Scanner(System.in);
    private static int nextID = 1;
    private int id;
    private int numSemestre;
    private NombreDeCarrera nombreDeCarrera;
    private ArrayList<Materia> materias;
    
    public Semestre(NombreDeCarrera nombreDeCarrera, int numSemestre, ArrayList<Materia> materias) {
        this.nombreDeCarrera = nombreDeCarrera;
        this.id = nextID;
        this.numSemestre = numSemestre;
        this.materias = materias;
        nextID++;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public int getNumSemestre() {
        return numSemestre;
    }
    
    public static void avanzarSemestre(Semestre semestre){
        System.out.println("Se avanzará a todos los alumnos del semestre con calificacion mayor a 70");
        System.out.println("Esta seguro: (S/N)");
        char opt = sc.next().charAt(0);
        boolean YoN = Tools.AskForYesOrNo(opt);

        if (YoN) {
            Semestre semestreCopy = Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre-1);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    for (Alumno alumno : semestreCopy.materias.get(i).getGrupos().get(j).getAlumnos()) {
                        alumno.acreditarMaterias();
                        if (alumno.isAcredito()) {
                            Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre-1).materias.get(i).getGrupos().get(j).getAlumnos().remove(alumno);
                            if (alumno.getNumSemestre() == 3) {
                                Semestre nuevoSemestre = Sistema.semestres.get(NombreDeCarrera.GRADUADOS).get(0);
                                alumno.setPromedio(0);
                                alumno.setGrupo(null);
                                alumno.setNumSemestre(nuevoSemestre.getNumSemestre());
                                alumno.setGraduado(true); 
                            } else if (Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre).materias.get(i).getGrupos().get(j).getAlumnos().size() == 10) {
                                Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre).materias.get(i).getGrupos().get(j).getAlumnos().add(alumno);
                                alumno.setGrupo(LetraGrupo.A);
                                alumno.setPromedio(0);
                                Semestre nuevoSemestre = Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre);
                                alumno.setNumSemestre(nuevoSemestre.getNumSemestre());
                                alumno.getCalificaciones().put(nuevoSemestre.materias.get(0).getNombre(), new Calificaciones(nuevoSemestre.materias.get(0).getNombre()));
                                alumno.getCalificaciones().put(nuevoSemestre.materias.get(1).getNombre(), new Calificaciones(nuevoSemestre.materias.get(1).getNombre()));
                                alumno.getCalificaciones().put(nuevoSemestre.materias.get(2).getNombre(), new Calificaciones(nuevoSemestre.materias.get(2).getNombre()));
                            } else {
                                Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre).materias.get(i).getGrupos().get(j).getAlumnos().add(alumno);
                                alumno.setGrupo(LetraGrupo.B);
                                alumno.setPromedio(0);
                                Semestre nuevoSemestre = Sistema.semestres.get(semestre.nombreDeCarrera).get(semestre.numSemestre);
                                alumno.setNumSemestre(nuevoSemestre.getNumSemestre());
                                alumno.getCalificaciones().put(nuevoSemestre.materias.get(0).getNombre(), new Calificaciones(nuevoSemestre.materias.get(0).getNombre()));
                                alumno.getCalificaciones().put(nuevoSemestre.materias.get(1).getNombre(), new Calificaciones(nuevoSemestre.materias.get(1).getNombre()));
                                alumno.getCalificaciones().put(nuevoSemestre.materias.get(2).getNombre(), new Calificaciones(nuevoSemestre.materias.get(2).getNombre()));
                            }
                        }
                    }    
                }    
            }
            System.out.println("\nSE HAN AVANZADO A LOS ALUMNOS AL SIGUIENTE SEMESTRE CORRECTAMENTE!!\n");
            Tools.next();
        } else {
            System.out.println("Asegurese de haber calificado a todos los alumnos antes de avanzarlos de semestre!");
            Tools.next();
            return;
        }

        
    }
}
