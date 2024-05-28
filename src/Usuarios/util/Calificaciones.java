package src.Usuarios.util;

import src.Materia.Materia;
import src.Materia.util.NombreDeMateria;

public class Calificaciones {
    private double promedio;
    private boolean acreditado = false;
    private NombreDeMateria materia;

    public Calificaciones(NombreDeMateria materia){
        this.materia = materia;
    }

    public boolean isAcreditado() {
        return acreditado;
    }
}
