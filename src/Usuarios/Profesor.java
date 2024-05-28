package src.Usuarios;

import java.time.LocalDate;

import src.Carrera.util.NombreDeCarrera;
import src.Sistema.util.Tools;
import src.Usuarios.util.Rol;

public class Profesor extends Usuario {
    private String rfc;
    private double sueldo;

    public Profesor(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, double sueldo, NombreDeCarrera carrera) {
        super(nombre, apellidos, fechaNacimiento, ciudad, estado, dirección, esHombre, Rol.PROFESOR);
        this.sueldo = sueldo;
        this.rfc = Tools.GenerateRFC(nombre, apellidos, fechaNacimiento);
        this.numControl = Tools.GenerateCtrlNum(nombre, fechaNacimiento, carrera, rol);
    }
    
    //private ArrayList<Materia> materiasImpartidas = new ArrayList<>();
}
