package src.Usuarios.util;

import java.text.Format;

import src.Materia.Materia;
import src.Materia.util.NombreDeMateria;

public class Calificaciones {
    private double calificacion;
    private boolean acreditado = false;
    private NombreDeMateria materia;

    public Calificaciones(NombreDeMateria materia){
        this.materia = materia;
        this.calificacion = 0;
    }

    public boolean isAcreditado() {
        return acreditado;
    }

    public String toString(){
        return String.format(" Materia: %s Calificacion: %f\n", this.materia, this.calificacion);
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void acreditar(){
        if (this.calificacion >= 70) {
            this.acreditado = true;
        }
    }

    public NombreDeMateria getMateria() {
        return materia;
    }
}
