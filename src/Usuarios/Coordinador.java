package src.Usuarios;

import java.time.LocalDate;

import src.Sistema.util.Tools;
import src.Usuarios.util.Rol;

public class Coordinador extends Usuario {
    private String rfc;
    private double sueldo;
    //private ArrayList<Materia> materiasImpartidas = new ArrayList<>();
    public Coordinador(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Rol rol, double sueldo) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, rol);
        this.sueldo = sueldo;
        this.rfc = Tools.GenerateRFC(nombre, apellidos, fechaNacimiento);
    }
    
}
