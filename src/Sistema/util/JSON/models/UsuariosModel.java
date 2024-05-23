package src.Sistema.util.JSON.models;

import java.util.ArrayList;

import src.Usuarios.Usuario;

public class UsuariosModel {
    private ArrayList<Usuario> ALUMNO;
    private ArrayList<Usuario> PROFESOR;
    private ArrayList<Usuario> COORDINADOR;

    public ArrayList<Usuario> getALUMNO() {
        return ALUMNO;
    }
    public ArrayList<Usuario> getPROFESOR() {
        return PROFESOR;
    }
    public ArrayList<Usuario> getCOORDINADOR() {
        return COORDINADOR;
    }

    
}
