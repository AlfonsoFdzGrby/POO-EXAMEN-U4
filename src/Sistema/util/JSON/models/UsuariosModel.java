package src.Sistema.util.JSON.models;

import java.util.ArrayList;
import java.util.HashMap;

import src.Carrera.util.NombreDeCarrera;
import src.Materia.util.NombreDeMateria;
import src.Semestre.Semestre;
import src.Usuarios.*;
import src.Usuarios.util.Calificaciones;
import src.Usuarios.util.Rol;

public class UsuariosModel {
    private HashMap<Rol, ArrayList<Usuario>> usuarios;
    private HashMap<NombreDeCarrera, ArrayList<Semestre>> semestres;

    public HashMap<Rol, ArrayList<Usuario>> getUsuarios() {
        return usuarios;
    }

    public HashMap<NombreDeCarrera, ArrayList<Semestre>> getSemestres() {
        return semestres;
    }
}
