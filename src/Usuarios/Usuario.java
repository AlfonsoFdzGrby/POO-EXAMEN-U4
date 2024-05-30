package src.Usuarios;

import java.time.LocalDate;

import src.Sistema.util.Tools;
import src.Usuarios.util.Rol;

public class Usuario {
    // Nombre y apellidos
    protected String nombre;
    protected String apellidos;
    protected String fechaNacimiento;
    protected String ciudad;
    protected String estado;
    protected String curp;
    protected String dirección;
    protected String fechaRegistro;
    protected boolean esHombre; //true = hombre , false = mujer
    protected Rol rol;
    protected String numControl;
    protected String contraseña;

    public Usuario(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Rol rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento.toString();
        this.fechaRegistro = LocalDate.now().toString();
        this.esHombre = esHombre;
        this.curp = Tools.GenerateCURP(nombre, apellidos, fechaNacimiento, esHombre, estado);
        this.ciudad = ciudad;
        this.estado = estado;
        this.dirección = dirección;
        this.rol = rol;
    }

    public String getNumControl() {
        return numControl;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }
    
    public String toString(){
        return "";
    }
    
}
