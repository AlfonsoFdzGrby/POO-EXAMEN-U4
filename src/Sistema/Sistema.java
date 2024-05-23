package src.Sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import src.Carrera.Carrera;
import src.Sistema.util.Tools;
import src.Sistema.util.JSON.UsuariosSerializer;
import src.Usuarios.Usuario;
import src.Usuarios.util.Rol;

public class Sistema {
    private static Scanner sc = new Scanner(System.in);
    public static ArrayList<Carrera> carreras = new ArrayList<>();
    public static HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<Rol, ArrayList<Usuario>>();

    public Sistema(){
        usuarios.put(Rol.ALUMNO, new ArrayList<>());
        usuarios.put(Rol.PROFESOR, new ArrayList<>());
        usuarios.put(Rol.COORDINADOR, new ArrayList<>());
    }

    public static Usuario iniciarSesion(){
        Usuario usuario = null;
        if(usuarios.get(Rol.ALUMNO).isEmpty()){
            System.out.println("No hay usuarios registrados en el sistema");
        }else{
            while(true){
                System.out.println("Ingrese su número de control:");
                System.out.print(">> ");
                String numControl = sc.nextLine();

                for (Usuario usuarioABuscar : usuarios.get(Rol.ALUMNO)) {
                    if(usuarioABuscar.getNumControl().equals(numControl)){
                        usuario = usuarioABuscar;
                        break;
                    }
                }

                if(usuario==null){
                    System.out.println("El usuario no fue encontrado");
                    System.out.println("Ingrese un número de control válido");
                }else{
                    break;
                }
            }

            int intentos = 3;
            System.out.println("Ingrese su contraseña");
            
            while (true) {
                if(intentos<=0){
                    System.out.println("Sus intentos se han agotado");
                    usuario = null;
                    Tools.next();
                    break;
                }
                System.out.println("Tiene " + intentos + " intentos");
                System.out.print(">> ");
                String contraseña = sc.nextLine();
                if(contraseña.equals(usuario.getContraseña())){
                    System.out.println("La contraseña es correcta");
                    break;
                }else{
                    System.out.println("La contraseña es incorrecta");
                    intentos--;
                }
            }
        }
        Tools.next();
        return usuario;
    }

    public static void agregarUsuario(Usuario usuario, Rol rol){
        usuarios.get(rol).add(usuario);
        UsuariosSerializer.writeToJSON();
    }
}
