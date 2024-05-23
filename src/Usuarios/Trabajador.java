package src.Usuarios;

import java.time.LocalDate;
import java.util.*;
import src.Sistema.util.Tools;

public class Trabajador extends Usuario {
    private String rfc;
    private double sueldo;

    public Trabajador(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, double sueldo) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre);
        this.sueldo = sueldo;
        this.rfc = Tools.GenerateRFC(nombre, apellidos, fechaNacimiento);
    }
    
    //private ArrayList<Materia> materiasImpartidas = new ArrayList<>();
}
