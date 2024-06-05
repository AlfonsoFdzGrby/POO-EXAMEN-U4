package src.Sistema.util.JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.*;

import src.Carrera.util.NombreDeCarrera;
import src.Semestre.Semestre;
import src.Sistema.Sistema;
import src.Usuarios.*;
import src.Usuarios.util.Rol;

public class UsuariosSerializer {
    public static void writeToJSON(){
        for (Usuario usuario : Sistema.usuarios.get(Rol.ALUMNO)) {
            Alumno alumno = (Alumno)usuario;
            Sistema.alumnos.add(alumno);
        }

        for (Usuario usuario : Sistema.usuarios.get(Rol.PROFESOR)) {
            Profesor profesor = (Profesor)usuario;
            Sistema.profesores.add(profesor);
        }

        for (Usuario usuario : Sistema.usuarios.get(Rol.COORDINADOR)) {
            Coordinador coordinador = (Coordinador)usuario;
            Sistema.coordinadores.add(coordinador);
        }

        Lister lister = new Lister(Sistema.alumnos, Sistema.profesores, Sistema.coordinadores, Sistema.semestres);
        
        Gson json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.json"));
            json.toJson(lister, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromJSON(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.json"));
            Gson json = new Gson();

            //UsuariosModel model = json.fromJson(reader, UsuariosModel.class);

            Lister model = json.fromJson(reader, Lister.class);

            Sistema.usuarios = model.getAsHashMap();

            Sistema.semestres = model.getSemestres();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Lister {
    @Expose
    private ArrayList<Alumno> alumnos;
    
    @Expose
    private ArrayList<Profesor> profesores;
    
    @Expose
    private ArrayList<Coordinador> coordinadores;

    @Expose
    private HashMap<NombreDeCarrera, ArrayList<Semestre>> semestres;

    public Lister(ArrayList<Alumno> alumnos, ArrayList<Profesor> profesores, ArrayList<Coordinador> coordinadores, HashMap<NombreDeCarrera, ArrayList<Semestre>> semestres) {
        this.alumnos = alumnos;
        this.profesores = profesores;
        this.coordinadores = coordinadores;
        this.semestres = semestres;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public ArrayList<Coordinador> getCoordinadores() {
        return coordinadores;
    }

    public HashMap<NombreDeCarrera, ArrayList<Semestre>> getSemestres() {
        return semestres;
    }

    public HashMap<Rol, ArrayList<Usuario>> getAsHashMap(){
        HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<>();
        
        usuarios.put(Rol.ALUMNO, new ArrayList<>());
        usuarios.put(Rol.PROFESOR, new ArrayList<>());
        usuarios.put(Rol.COORDINADOR, new ArrayList<>());

        usuarios.get(Rol.ALUMNO).addAll(alumnos);
        usuarios.get(Rol.PROFESOR).addAll(profesores);
        usuarios.get(Rol.COORDINADOR).addAll(coordinadores);

        return usuarios;
    }
    
}