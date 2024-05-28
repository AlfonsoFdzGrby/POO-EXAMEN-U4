package src.Usuarios.util;

import src.Sistema.Sistema;
import src.Usuarios.Usuario;

public class FindID {
    
    public static Usuario findControlNumber(String numControl, Rol rol){
        for (Usuario usuario : Sistema.usuarios.get(rol)) {
            if (usuario.getNumControl() == numControl) {
                return usuario;
            }
        }
        System.out.println("No se ha encontrado el usuario!");
        return null;
    }

}
