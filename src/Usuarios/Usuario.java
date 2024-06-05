package src.Usuarios;

import java.time.LocalDate;

import com.google.gson.annotations.Expose;

import src.Carrera.util.NombreDeCarrera;
import src.Sistema.util.Tools;
import src.Usuarios.util.Rol;

public class Usuario {
    // Nombre y apellidos
    @Expose
    protected NombreDeCarrera nombreCarrera;
    
    @Expose
    protected String nombre;
    
    @Expose
    protected String apellidos;
    
    @Expose
    protected String fechaNacimiento;
    
    @Expose
    protected String ciudad;
    
    @Expose
    protected String estado;
    
    @Expose
    protected String curp;
    
    @Expose
    protected String dirección;
    
    @Expose
    protected String fechaRegistro;
    
    @Expose
    protected boolean esHombre; //true = hombre , false = mujer
    
    @Expose
    protected Rol rol;
    
    @Expose
    protected String numControl;
    
    @Expose
    protected String contraseña;

    public Usuario(String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado,
            String dirección, boolean esHombre, Rol rol, NombreDeCarrera nombreDeCarrera, String contraseña) {
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
        this.nombreCarrera = nombreDeCarrera;
        this.numControl = Tools.GenerateCtrlNum(nombre, fechaNacimiento, nombreDeCarrera, rol);
        this.contraseña = contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public NombreDeCarrera getNombreCarrera() {
        return nombreCarrera;
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
        return String.format(" Nombre: %s\n Apellidos: %s\n Fecha de nacimiento: %s\n Fecha de registro: %s\n Sexo: %s\n Curp: %s\n Ciudad: %s\n Estado: %s\n Direccion: %s\n Rol: %s \n Carrera: %s \n Numero de Control: %s\n ",this.nombre,this.apellidos,this.fechaNacimiento,this.fechaRegistro,this.esHombre ? "Hombre" : "Mujer",this.curp,this.ciudad,this.estado,this.dirección,this.rol,this.nombreCarrera,this.numControl );
    }

    public Rol getRol() {
        return rol;
    }
    
}
