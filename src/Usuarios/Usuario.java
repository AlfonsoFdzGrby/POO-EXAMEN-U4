package src.Usuarios;

import java.time.LocalDate;

import src.Sistema.util.Tools;

public abstract class Usuario {
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

    public Usuario(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento.toString();
        this.fechaRegistro = LocalDate.now().toString();
        this.esHombre = esHombre;
        this.curp = Tools.GenerateCURP(nombre, apellidos, fechaNacimiento, esHombre, estado);
        this.ciudad = ciudad;
        this.estado = estado;
        this.dirección = dirección;
    }
    
}
