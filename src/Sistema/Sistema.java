package src.Sistema;

import java.time.LocalDate;
import java.util.*;

import src.Carrera.Carrera;
import src.Carrera.util.NombreDeCarrera;
import src.Grupos.Grupo;
import src.Grupos.util.LetraGrupo;
import src.Materia.Materia;
import src.Materia.util.NombreDeMateria;
import src.Semestre.Semestre;
import src.Sistema.util.Tools;
import src.Sistema.util.JSON.UsuariosSerializer;
import src.Usuarios.*;
import src.Usuarios.util.Rol;
import src.Usuarios.util.UsuarioEnSesion;

public class Sistema {
    private static Scanner sc = new Scanner(System.in);
    public static HashMap<NombreDeCarrera, ArrayList<Semestre>> semestres = new HashMap<NombreDeCarrera, ArrayList<Semestre>>();
    public static ArrayList<Carrera> carreras = new ArrayList<>();
    public static HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<Rol, ArrayList<Usuario>>();
    
    public static ArrayList<Alumno> alumnos = new ArrayList<>();
    public static ArrayList<Profesor> profesores = new ArrayList<>();
    public static ArrayList<Coordinador> coordinadores = new ArrayList<>();

    public Sistema(boolean inicializar){

        //CREACIÓN KEYS EN USUARIOS
        usuarios.put(Rol.ALUMNO, new ArrayList<>());
        usuarios.put(Rol.PROFESOR, new ArrayList<>());
        usuarios.put(Rol.COORDINADOR, new ArrayList<>());
        usuarios.put(null, null);

        //CREACIÓN KEYS EN SEMESTRES
        semestres.put(NombreDeCarrera.ISC, new ArrayList<>());
        semestres.put(NombreDeCarrera.IMT, new ArrayList<>());
        semestres.put(NombreDeCarrera.ELC, new ArrayList<>());
        semestres.put(NombreDeCarrera.GRADUADOS, new ArrayList<>());

        if(inicializar){
            //CREACIÓN DE COORDINADORES POR DEFAULT
            Coordinador coordISC = new Coordinador("Laura Nelly", "Alvarado Zamora", LocalDate.of(1973, 12, 15), "Morelia", "Michoacán", "Av. Acueducto #253", false, Rol.COORDINADOR, 30000, NombreDeCarrera.ISC, "1234");
            Coordinador coordIMT = new Coordinador("Francisco", "Reyes Calderón", LocalDate.of(1963, 2, 5), "Morelia", "Michoacán", "Av. Madero #538", true, Rol.COORDINADOR, 30000, NombreDeCarrera.IMT, "1234");
            Coordinador coordELC = new Coordinador("Javier", "López Mateos", LocalDate.of(1983, 5, 12), "Morelia", "Michoacán", "Perif. Paseo de la República #253", true, Rol.COORDINADOR, 30000, NombreDeCarrera.ELC, "1234");

            coordinadores.addAll(Arrays.asList(coordISC, coordIMT, coordELC));
            usuarios.get(Rol.COORDINADOR).addAll(coordinadores);
       
            //CREACION DE PROFESORES (Minimo 9 profesores de inicio a menos que un profe pueda impartir varias materias entonces no se xd)
            
            Profesor profesor1_ISC = new Profesor("Rubén", "Vega Cano", LocalDate.of(1963, 2, 15), "Morelia", "Michoacán", "Av. Acueducto #354", NombreDeCarrera.ISC, true, 15000, NombreDeCarrera.ISC, new ArrayList<>(), "1234");
            Profesor profesor2_ISC = new Profesor("Eder", "Rivera Cisneros", LocalDate.of(2000, 4, 9), "Morelia", "Michoacán", "Av. Acueducto #383", NombreDeCarrera.ISC, true, 15000, NombreDeCarrera.ISC, new ArrayList<>(), "1234");
            Profesor profesor3_ISC = new Profesor("José Antonio", "Mejía Villaseñor", LocalDate.of(1952, 2, 15), "Morelia", "Michoacán", "Av. Acueducto #354", NombreDeCarrera.ISC, true, 15000, NombreDeCarrera.ISC, new ArrayList<>(), "1234");
            
            Profesor profesor1_IMT = new Profesor("Alfredo", "Morales Hernández", LocalDate.of(1963, 2, 15), "Morelia", "Michoacán", "Av. Acueducto #354", NombreDeCarrera.IMT, true, 15000, NombreDeCarrera.IMT, new ArrayList<>(), "1234");
            Profesor profesor2_IMT = new Profesor("Pedro", "Garnica González", LocalDate.of(1963, 2, 15), "Morelia", "Michoacán", "Av. Acueducto #354", NombreDeCarrera.IMT, true, 15000, NombreDeCarrera.IMT, new ArrayList<>(), "1234");
            Profesor profesor3_IMT = new Profesor("Fernando Manuel", "Torres Ruiz", LocalDate.of(1963, 2, 15), "Morelia", "Michoacán", "Av. Acueducto #354", NombreDeCarrera.IMT, true, 15000, NombreDeCarrera.IMT, new ArrayList<>(), "1234");
            
            Profesor profesor1_ELC = new Profesor("Salvador", "Aburto Bedolla", LocalDate.of(1963, 2, 15), "Morelia", "Michoacán", "Av. Acueducto #354", NombreDeCarrera.IMT, true, 15000, NombreDeCarrera.ELC, new ArrayList<>(), "1234");
            Profesor profesor2_ELC = new Profesor("Martín", "Zaragoza Aguirre", LocalDate.of(1963, 2, 15), "Morelia", "Michoacán", "Av. Acueducto #354", NombreDeCarrera.IMT, true, 15000, NombreDeCarrera.ELC, new ArrayList<>(), "1234");
            Profesor profesor3_ELC = new Profesor("José Alfredo", "Jiménez Murillo", LocalDate.of(1963, 2, 15), "Morelia", "Michoacán", "Av. Acueducto #354", NombreDeCarrera.IMT, true, 15000, NombreDeCarrera.ELC, new ArrayList<>(), "1234");

            profesor1_ISC.agregarMateria(NombreDeMateria.Programacion1);
            profesor1_ISC.agregarMateria(NombreDeMateria.Programacion2);
            profesor2_ISC.agregarMateria(NombreDeMateria.Probabilidad1);
            profesor2_ISC.agregarMateria(NombreDeMateria.Probabilidad2);
            profesor3_ISC.agregarMateria(NombreDeMateria.Calculo1);
            profesor3_ISC.agregarMateria(NombreDeMateria.Calculo2);
            profesor3_ISC.agregarMateria(NombreDeMateria.Calculo3);

            coordISC.agregarMateria(NombreDeMateria.Programacion3);
            coordISC.agregarMateria(NombreDeMateria.Probabilidad3);

            coordIMT.agregarMateria(NombreDeMateria.Estadistica1);
            coordIMT.agregarMateria(NombreDeMateria.Estadistica2);
            coordIMT.agregarMateria(NombreDeMateria.Estadistica3);
            coordIMT.agregarMateria(NombreDeMateria.Contabilidad1);
            coordIMT.agregarMateria(NombreDeMateria.Contabilidad2);
            coordIMT.agregarMateria(NombreDeMateria.Contabilidad3);
            coordIMT.agregarMateria(NombreDeMateria.Calculo1);
            coordIMT.agregarMateria(NombreDeMateria.Calculo2);
            coordIMT.agregarMateria(NombreDeMateria.Calculo3);

            coordELC.agregarMateria(NombreDeMateria.Redes1);
            coordELC.agregarMateria(NombreDeMateria.Redes2);
            coordELC.agregarMateria(NombreDeMateria.Redes3);
            coordELC.agregarMateria(NombreDeMateria.Circuitos1);
            coordELC.agregarMateria(NombreDeMateria.Circuitos2);
            coordELC.agregarMateria(NombreDeMateria.Circuitos3);
            coordELC.agregarMateria(NombreDeMateria.Calculo1);
            coordELC.agregarMateria(NombreDeMateria.Calculo2);
            coordELC.agregarMateria(NombreDeMateria.Calculo3);

            profesores.addAll(Arrays.asList(profesor1_ELC, profesor1_IMT, profesor1_ISC, profesor2_ELC, profesor2_IMT, profesor2_ISC, profesor3_ELC, profesor3_IMT, profesor3_ISC));
            usuarios.get(Rol.PROFESOR).addAll(profesores);

            Materia graduados = new Materia(NombreDeCarrera.GRADUADOS, null, null, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.GRADUADOS, alumnos, LetraGrupo.A))));

            //Semestre 1
            Materia programacion1 = new Materia(NombreDeCarrera.ISC, profesor1_ISC, NombreDeMateria.Programacion1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));
            Materia probabilidad1 = new Materia(NombreDeCarrera.ISC, profesor2_ISC, NombreDeMateria.Probabilidad1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo1_ISC = new Materia(NombreDeCarrera.ISC, profesor3_ISC, NombreDeMateria.Calculo1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));
            
            //Semestre 2
            Materia programacion2 = new Materia(NombreDeCarrera.ISC, profesor1_ISC, NombreDeMateria.Programacion2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));
            Materia probabilidad2 = new Materia(NombreDeCarrera.ISC, profesor2_ISC, NombreDeMateria.Probabilidad2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo2_ISC = new Materia(NombreDeCarrera.ISC, profesor3_ISC, NombreDeMateria.Calculo2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));

            //Semestre 3
            Materia programacion3 = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Programacion3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));
            Materia probabilidad3 = new Materia(NombreDeCarrera.ISC, coordISC, NombreDeMateria.Probabilidad3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo3_ISC = new Materia(NombreDeCarrera.ISC, profesor3_ISC, NombreDeMateria.Calculo3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ISC, new ArrayList<>(), LetraGrupo.B))));

            //CREACIÓN DE MATERIAS - IMT

            //Semestre 1
            Materia estadistica1 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Estadistica1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));
            Materia contabilidad1 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Contabilidad1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo1_IMT = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Calculo1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));

            //Semestre 2
            Materia estadistica2 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Estadistica2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));
            Materia contabilidad2 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Contabilidad2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo2_IMT = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Calculo2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));

            //Semestre 3
            Materia estadistica3 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Estadistica3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));
            Materia contabilidad3 = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Contabilidad3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo3_IMT = new Materia(NombreDeCarrera.IMT, coordIMT, NombreDeMateria.Calculo3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.IMT, new ArrayList<>(), LetraGrupo.B))));

            //CREACIÓN DE MATERIAS - ELC

            //Semestre 1
            Materia redes1 = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Redes1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));
            Materia circuitos1 = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Circuitos1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo1_ELC = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Calculo1, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));

            //Semestre 2
            Materia redes2 = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Redes2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));
            Materia circuitos2 = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Circuitos2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo2_ELC = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Calculo2, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));

            //Semestre 3
            Materia redes3 = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Redes3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));
            Materia circuitos3 = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Circuitos3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));
            Materia calculo3_ELC = new Materia(NombreDeCarrera.ELC, coordELC, NombreDeMateria.Calculo3, new ArrayList<>(Arrays.asList(new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.A), new Grupo(NombreDeCarrera.ELC, new ArrayList<>(), LetraGrupo.B))));

            //Los arraylists se quedan en blanco porque dependiendo del número de alumnos en el grupo A, se crea o no el B

            //Semestres ISC
            Semestre semestre1ISC = new Semestre(NombreDeCarrera.ISC, 1 , new ArrayList<>(Arrays.asList(probabilidad1, programacion1, calculo1_ISC)));
            Semestre semestre2ISC = new Semestre(NombreDeCarrera.ISC, 2 , new ArrayList<>(Arrays.asList(probabilidad2, programacion2, calculo2_ISC)));
            Semestre semestre3ISC = new Semestre(NombreDeCarrera.ISC, 3 , new ArrayList<>(Arrays.asList(probabilidad3, programacion3, calculo3_ISC)));

            //Semestres IMT
            Semestre semestre1IMT = new Semestre(NombreDeCarrera.IMT, 1 , new ArrayList<>(Arrays.asList(estadistica1, contabilidad1, calculo1_IMT)));
            Semestre semestre2IMT = new Semestre(NombreDeCarrera.IMT, 2 , new ArrayList<>(Arrays.asList(estadistica2, contabilidad2, calculo2_IMT)));
            Semestre semestre3IMT = new Semestre(NombreDeCarrera.IMT, 3 , new ArrayList<>(Arrays.asList(estadistica3, contabilidad3, calculo3_IMT)));

            //Semestres ELC
            Semestre semestre1ELC = new Semestre(NombreDeCarrera.ELC, 1 , new ArrayList<>(Arrays.asList(redes1, circuitos1, calculo1_ELC)));
            Semestre semestre2ELC = new Semestre(NombreDeCarrera.ELC, 2 , new ArrayList<>(Arrays.asList(redes2, circuitos2, calculo2_ELC)));
            Semestre semestre3ELC = new Semestre(NombreDeCarrera.ELC, 3 , new ArrayList<>(Arrays.asList(redes3, circuitos3, calculo3_ELC)));

            //Semestre "Graduados"
            Semestre semestreGraduados = new Semestre(NombreDeCarrera.GRADUADOS, 4 , new ArrayList<>(Arrays.asList(graduados)));

            //Agregar al HashMap
            semestres.put(NombreDeCarrera.ISC, new ArrayList<>(Arrays.asList(semestre1ISC, semestre2ISC, semestre3ISC)));
            semestres.put(NombreDeCarrera.IMT, new ArrayList<>(Arrays.asList(semestre1IMT, semestre2IMT, semestre3IMT)));
            semestres.put(NombreDeCarrera.ELC, new ArrayList<>(Arrays.asList(semestre1ELC, semestre2ELC, semestre3ELC)));
            semestres.put(NombreDeCarrera.GRADUADOS, new ArrayList<>(Arrays.asList(semestreGraduados)));

            

        }

    }

    public static Usuario iniciarSesion(int id /* 1 - Alumno / 2 - Profesor / 3 - Coordinador */) throws Exception {
        Usuario usuario = null;
        if(usuarios.get(Rol.ALUMNO).isEmpty() && usuarios.get(Rol.COORDINADOR).isEmpty() && usuarios.get(Rol.PROFESOR).isEmpty()){
            System.out.println("No hay usuarios registrados en el sistema");
            usuario = null;
        }else if(id==1 && usuarios.get(Rol.ALUMNO).isEmpty()){
            System.out.println("No existen alumnos registrados en el sistema");
            usuario = null;
        }else if(id==2 && usuarios.get(Rol.PROFESOR).isEmpty()){
            System.out.println("No existen profesores registrados en el sistema");
            usuario = null;
        }else if(id==3 && usuarios.get(Rol.COORDINADOR).isEmpty()){
            System.out.println("No existen coordinadores registrados en el sistema");
            usuario = null;
        }else{
            while(true){
                System.out.println("Ingrese su número de control:");
                System.out.print(">> ");
                String numControl = sc.nextLine();

                if(numControl.equalsIgnoreCase("exit")){
                    System.out.println();
                    String salir = "Cancelando inicio de sesión";
                    for (int i = 0; i <= 4; i++) {
                        if(i==3){
                            Tools.clearLine();
                            System.out.print("\rInicio de sesión cancelado");
                            break;
                        }
                        System.out.print("\r" + salir);
                        salir += ".";
                        Thread.sleep(500);
                    }
                    System.out.println();
                    usuario = null;
                    break;
                }

                for (ArrayList<Usuario> usuariosFor : usuarios.values()) {
                    for (Usuario usuarioABuscar : usuariosFor) {
                        if(usuarioABuscar.getNumControl().equals(numControl)){
                            usuario = usuarioABuscar;
                            break;
                        }
                    }  
                }
                

                if(usuario==null){
                    System.out.println("El usuario no fue encontrado");
                }else{
                    break;
                }
            }

            if(usuario!=null){
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

            
        }
        Tools.next();
        return usuario;
    }

    public static void agregarUsuario(Usuario usuario, Rol rol){
        usuarios.get(rol).add(usuario);
        UsuariosSerializer.writeToJSON();
    }

    public static void printGraduados(){
        Sistema.semestres.get(NombreDeCarrera.GRADUADOS).get(0).getMaterias().get(0).getGrupos().get(0).getAlumnos()
        .stream().filter(alumno -> alumno.getCarrera().equals(UsuarioEnSesion.getInstancia().getUsuarioActual().getNombreCarrera())).forEach(alumno -> System.out.println(alumno.toString()));
    }
}
