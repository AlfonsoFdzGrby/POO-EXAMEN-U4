package src.Sistema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import src.Carrera.Carrera;
import src.Carrera.util.NombreDeCarrera;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Materia.util.NombreDeMateria;
import src.Semestre.Semestre;
import src.Sistema.util.Tools;
import src.Sistema.util.JSON.UsuariosSerializer;
import src.Usuarios.Alumno;
import src.Usuarios.Coordinador;
import src.Usuarios.Profesor;
import src.Usuarios.Usuario;
import src.Usuarios.util.Rol;

public class Sistema {
    private static Scanner sc = new Scanner(System.in);
    public static HashMap<NombreDeCarrera, ArrayList<Semestre>> semestres = new HashMap<NombreDeCarrera, ArrayList<Semestre>>();
    public static ArrayList<Carrera> carreras = new ArrayList<>();
    public static HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<Rol, ArrayList<Usuario>>();

    public Sistema(){
        //CREACIÓN DE USUARIOS
        usuarios.put(Rol.ALUMNO, new ArrayList<>());
        usuarios.put(Rol.PROFESOR, new ArrayList<>());
        usuarios.put(Rol.COORDINADOR, new ArrayList<>());
        
        //CREACIÓN DE COORDINADORES POR DEFAULT
        Coordinador coordISC = new Coordinador("Laura Nelly", "Alvarado Zamora", LocalDate.of(1973, 12, 15), "Morelia", "Michoacán", "Av. Acueducto #253", false, Rol.COORDINADOR, 30000);
        Coordinador coordIMT = new Coordinador("Francisco", "Reyes Calderón", LocalDate.of(1963, 2, 5), "Morelia", "Michoacán", "Av. Madero #538", true, Rol.COORDINADOR, 30000);
        Coordinador coordELC = new Coordinador("Javier", "López Mateos", LocalDate.of(1983, 5, 12), "Morelia", "Michoacán", "Perif. Paseo de la República #253", true, Rol.COORDINADOR, 30000);

        usuarios.get(Rol.COORDINADOR).add(coordISC);
        usuarios.get(Rol.COORDINADOR).add(coordIMT);
        usuarios.get(Rol.COORDINADOR).add(coordELC);
        //CREACION DE PROFESORES (Minimo 9 profesores de inicio a menos que un profe pueda impartir varias materias entonces no se xd)

        //Profesor profe = new ...
        //Profesor profe = new ...
        //Profesor profe = new ...

        //CREACIÓN DE MATERIAS - ISC

        //Semestre 1
        Materia programacion1 = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Programacion1, new ArrayList<>());
        Materia probabilidad1 = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Probabilidad1, new ArrayList<>());
        Materia calculo1_ISC = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Calculo1, new ArrayList<>());
        
        //Semestre 2
        Materia programacion2 = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Programacion2, new ArrayList<>());
        Materia probabilidad2 = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Probabilidad2, new ArrayList<>());
        Materia calculo2_ISC = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Calculo2, new ArrayList<>());

        //Semestre 3
        Materia programacion3 = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Programacion3, new ArrayList<>());
        Materia probabilidad3 = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Probabilidad3, new ArrayList<>());
        Materia calculo3_ISC = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Calculo3, new ArrayList<>());

        //CREACIÓN DE MATERIAS - IMT

        //Semestre 1
        Materia estadistica1 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Estadistica1, new ArrayList<>());
        Materia contabilidad1 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Contabilidad1, new ArrayList<>());
        Materia calculo1_IMT = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Calculo1, new ArrayList<>());

        //Semestre 2
        Materia estadistica2 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Estadistica2, new ArrayList<>());
        Materia contabilidad2 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Contabilidad2, new ArrayList<>());
        Materia calculo2_IMT = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Calculo2, new ArrayList<>());

        //Semestre 3
        Materia estadistica3 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Estadistica3, new ArrayList<>());
        Materia contabilidad3 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Contabilidad3, new ArrayList<>());
        Materia calculo3_IMT = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Calculo3, new ArrayList<>());

        //CREACIÓN DE MATERIAS - ELC

        //Semestre 1
        Materia redes1 = new Materia(NombreDeCarrera.IMT, coordELC, NombreDeMateria.Redes1, new ArrayList<>());
        Materia circuitos1 = new Materia(NombreDeCarrera.IMT, coordELC, NombreDeMateria.Circuitos1, new ArrayList<>());
        Materia calculo1_ELC = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Calculo1, new ArrayList<>());

        //Semestre 2
        Materia redes2 = new Materia(NombreDeCarrera.IMT, coordELC, NombreDeMateria.Redes2, new ArrayList<>());
        Materia circuitos2 = new Materia(NombreDeCarrera.IMT, coordELC, NombreDeMateria.Circuitos2, new ArrayList<>());
        Materia calculo2_ELC = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Calculo2, new ArrayList<>());

        //Semestre 3
        Materia redes3 = new Materia(NombreDeCarrera.IMT, coordELC, NombreDeMateria.Redes3, new ArrayList<>());
        Materia circuitos3 = new Materia(NombreDeCarrera.IMT, coordELC, NombreDeMateria.Circuitos3, new ArrayList<>());
        Materia calculo3_ELC = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Calculo3, new ArrayList<>());

        //Los arraylists se quedan en blanco porque dependiendo del número de alumnos en el grupo A, se crea o no el B
        
        //Semestres ISC
        Semestre semestre1ISC = new Semestre(NombreDeCarrera.ISC, 1 , new ArrayList<>(Arrays.asList(probabilidad1, programacion1, calculo1_ISC)));
        Semestre semestre2ISC = new Semestre(NombreDeCarrera.ISC, 2 , new ArrayList<>(Arrays.asList(probabilidad2, programacion2, calculo2_ISC)));
        Semestre semestre3ISC = new Semestre(NombreDeCarrera.ISC, 3 , new ArrayList<>(Arrays.asList(probabilidad3, programacion3, calculo3_ISC)));

        //Semestres IMT
        Semestre semestre1IMT = new Semestre(NombreDeCarrera.ISC, 1 , new ArrayList<>(Arrays.asList(estadistica1, contabilidad1, calculo1_IMT)));
        Semestre semestre2IMT = new Semestre(NombreDeCarrera.ISC, 2 , new ArrayList<>(Arrays.asList(estadistica2, contabilidad2, calculo2_IMT)));
        Semestre semestre3IMT = new Semestre(NombreDeCarrera.ISC, 3 , new ArrayList<>(Arrays.asList(estadistica3, contabilidad3, calculo3_IMT)));

        //Semestres ELC
        Semestre semestre1ELC = new Semestre(NombreDeCarrera.ISC, 1 , new ArrayList<>(Arrays.asList(redes1, circuitos1, calculo1_ELC)));
        Semestre semestre2ELC = new Semestre(NombreDeCarrera.ISC, 2 , new ArrayList<>(Arrays.asList(redes2, circuitos2, calculo2_ELC)));
        Semestre semestre3ELC = new Semestre(NombreDeCarrera.ISC, 3 , new ArrayList<>(Arrays.asList(redes3, circuitos3, calculo3_ELC)));

        //Semestre "Graduados"
        Semestre semestreGraduados = new Semestre(NombreDeCarrera.GRADUADOS, 4 , new ArrayList<>());

        //Agregar al HashMap
        semestres.put(NombreDeCarrera.ISC, new ArrayList<>(Arrays.asList(semestre1ISC, semestre2ISC, semestre3ISC)));
        semestres.put(NombreDeCarrera.IMT, new ArrayList<>(Arrays.asList(semestre1IMT, semestre2IMT, semestre3IMT)));
        semestres.put(NombreDeCarrera.ELC, new ArrayList<>(Arrays.asList(semestre1ELC, semestre2ELC, semestre3ELC)));
        semestres.put(NombreDeCarrera.GRADUADOS, new ArrayList<>(Arrays.asList(semestreGraduados)));

    }

    public static Usuario iniciarSesion(){
        Usuario usuario = null;
        if(usuarios.get(Rol.ALUMNO).isEmpty() && usuarios.get(Rol.COORDINADOR).isEmpty() && usuarios.get(Rol.PROFESOR).isEmpty()){
            System.out.println("No hay usuarios registrados en el sistema");
            usuario = null;
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
