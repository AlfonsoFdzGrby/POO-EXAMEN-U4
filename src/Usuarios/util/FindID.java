package src.Usuarios.util;

import src.Materia.Materia;
import src.Materia.util.NombreDeMateria;
import src.Sistema.Sistema;
import src.Usuarios.*;

public class FindID {
    
    public static Usuario findControlNumber(String numControl, Rol rol){
        for (Usuario usuario : Sistema.usuarios.get(rol)) {
            if (usuario.getNumControl().equals(numControl)) {
                return usuario;
            }
        }
        System.out.println("No se ha encontrado el usuario!");
        return null;
    }

    public static Materia findMateria(NombreDeMateria nombreDeMateria){
        for (int j = 0; j < 3; j++) {
            for (Materia materia : Sistema.semestres.get(UsuarioEnSesion.getInstancia().getUsuarioActual().getNombreCarrera()).get(j).getMaterias()) {
                if (materia.getNombre().equals(nombreDeMateria)) {
                    return materia;
                }
            }   
        }
        return null;
    }

}
