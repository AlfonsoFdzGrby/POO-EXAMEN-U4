package src.Sistema.util;

import java.io.File;
import java.time.*;
import java.time.format.*;
import java.util.*;

import src.Carrera.util.NombreDeCarrera;
import src.Sistema.Sistema;
import src.Sistema.util.JSON.UsuariosSerializer;
import src.Usuarios.Usuario;
import src.Usuarios.util.Rol;

public class Tools {
    private static Scanner sc = new Scanner(System.in);
    private static Random ran = new Random();

    // Secuencia para mover el cursor al inicio de la línea
    private static final String ANSI_CURSOR_START = "\u001b[0G";
    // Secuencia para borrar la línea actual
    private static final String ANSI_CLEAR_LINE = "\u001b[2K";

    public static void clearLine() {
        System.out.print(ANSI_CURSOR_START);  // Mueve el cursor al inicio de la línea
        System.out.print(ANSI_CLEAR_LINE);    // Borra la línea actual
    }

    public static void printMindboxLogo() throws Exception {
        //imprimir separación
        
        String header = "";
        for (int i = 0; i < 64; i++) {
            header+="=";
            System.out.print("\r" + header);
            Thread.sleep(5);
        }
        System.out.println();
        System.out.println();
        header = "";

        //imprimir "MINDBOX"
        String parte = "";

        for (int i = 0; i < 5; i++) {
            String textoanimado = "";

            switch (i) {
                case 0 -> parte = "    ██      ██  ██              ██  ██";
                case 1 -> parte = "    ████  ████                  ██  ██";
                case 2 -> parte = "    ██  ██  ██  ██  █████    █████  █████    █████   ██ ██";
                case 3 -> parte = "    ██      ██  ██  ██  ██  ██  ██  ██  ██  ██   ██   ███";
                case 4 -> parte = "    ██      ██  ██  ██  ██   █████  █████    █████   ██ ██";
            }

            for (int j = 0; j < parte.length(); j++) {
                char caracter = parte.charAt(j);
                textoanimado += caracter;
                System.out.print("\r" + textoanimado);
                Thread.sleep(3);
            }

            System.out.println();
        }
        System.out.println();

        //imprimir separación

        for (int i = 0; i < 64; i++) {
            header+="=";
            System.out.print("\r" + header);
            Thread.sleep(3);
        }
        System.out.println();
        System.out.println();

        
    }

    public static void loadBar() throws Exception {
        File file = new File("usuarios.json");
        printHeader("CARGAR ARCHIVO JSON");
        
        if(!(file.exists())){
            System.out.println("ERROR: No existe ningún archivo JSON.");
            System.out.println();
            System.out.println(" * Asegúrese de crear un archivo con el nombre 'usuarios.json'");
            System.out.println("   en el directorio raíz del programa antes de continuar");
            System.out.println();
            next();
        }
        
        if(file.length()==0){
            System.out.println("INFO: El archivo JSON está vacío");
            next();
        }else{
            System.out.println("¿Desea cargar los datos almacenados en el archivo JSON? (s/n)");
            System.out.print(">> ");
            char opc = sc.nextLine().charAt(0);
            
            if(AskForYesOrNo(opc)){
                UsuariosSerializer.readFromJSON();
            }
        }

        clear();
        Thread.sleep(500);
        printHeader("CARGANDO SISTEMA...");
        String loadbar = "";
        for (int i = 0; i <= 100; i+=2) {
            System.out.print("\r" + i + "%  - | " + loadbar + " |");
            loadbar += "█";
            Thread.sleep(25);
        }
        System.out.println();
        System.out.println("===============================================================");
        System.out.println("¡Sistema cargado exitosamente!");
        next();
        clear();
    }

    public static void clear() throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void printHeader(String header) throws Exception {
        clear();

        /*String splitter = "";
        for (int i = 0; i < 64; i++) {
            splitter+="=";
            System.out.print("\r" + splitter);
            Thread.sleep(2);
        }
        System.out.println();
        splitter = "";*/

        System.out.println("===============================================================");
        System.out.println(header);
        System.out.println("===============================================================");

        /*for (int i = 0; i < 64; i++) {
            splitter+="=";
            System.out.print("\r" + splitter);
            Thread.sleep(1);
        }
        System.out.println();
        splitter = "";*/
    }

    public static void next(){
        System.out.print("Presione enter para continuar...");
        sc.nextLine();
    }

    public static int nextInt(){
        String num;
        int res;
        while(true){
            num = sc.nextLine();
            try {
                res = Integer.parseInt(num);
                break;
            } catch (Exception e) {
                System.out.println("Por favor ingrese un número");
            }
        }
        return res;
    }

    public static double nextDouble(){
        String num;
        double res;
        while(true){
            num = sc.nextLine();
            try {
                res = Double.parseDouble(num);
                break;
            } catch (Exception e) {
                System.out.println("Por favor ingrese un número");
            }
        }
        return res;
    }

    public static boolean validarRetiro(double fondos, double cantidad){
        boolean exito = false;
        if(!(cantidad<0 || cantidad>fondos)){
            exito = true;
        }
        return exito;
    }

    public static boolean AskForYesOrNo(char opc){
        boolean answer = false;
        switch (Character.toLowerCase(opc)) {
            case 'y' -> answer = true;
            case 's' -> answer = true;
            default -> answer = false;
        }
        return answer;
    }

    public static LocalDate askForDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = null;
        while (true) {
            System.out.println("Ingrese la fecha en el formato dd-MM-yyyy");
            System.out.print(">> ");
            try{
                fecha = LocalDate.parse(sc.nextLine(), format);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("La fecha ingresada no es válida");
            }
        }
        return fecha;
    }

    public static String GenerateCURP(String nombre, String apellidos, LocalDate fechaNac, boolean esHombre, String estado){
        String[] arrApellidos = apellidos.split(" ");
        String CURP="";
        char letra;

        // Nombres y apellidos

        for (int i = 0; i < 2; i++) {
            letra = apellidos.charAt(i);
            CURP += Character.toUpperCase(letra);
        }

        letra = arrApellidos[1].charAt(0);
        CURP += Character.toUpperCase(letra);
        letra = nombre.charAt(0);
        CURP += Character.toUpperCase(letra);
        
        // Fecha de nacimiento

        CURP += String.format("%02d",fechaNac.getYear() % 100);
        CURP += String.format("%02d",fechaNac.getMonthValue() % 100);
        CURP += String.format("%02d",fechaNac.getDayOfMonth() % 100);

        // Género

        if(esHombre){
            CURP += "H";
        }else{
            CURP += "M";
        }

        // Entidad federativa

        for (int i = 0; i < 2; i++) {
            letra = estado.charAt(i);
            CURP += Character.toUpperCase(letra);
        }

        // Primeras consonantes internas de apellidos

        String apTemp;
        
        for (int j = 0; j < arrApellidos.length; j++) {
            apTemp = arrApellidos[j];
            for (int j2 = 1; j2 < apTemp.length(); j2++) {
                letra = Character.toUpperCase(apTemp.charAt(j2));
                if(letra!='A' && letra!='E' && letra!='I' && letra!='O' && letra!='U'){
                    CURP += letra;
                    break;
                }
            }
        }

        // Primera consonante interna del nombre

        for (int i = 1; i < nombre.length(); i++) {
            letra = Character.toUpperCase(nombre.charAt(i));
            if(letra!='A' && letra!='E' && letra!='I' && letra!='O' && letra!='U'){
                CURP += letra;
                break;
            }
        }

        // Ultimos dos dígitos que genera el algoritmo

        for (int i = 0; i < 2; i++) {
            int num = ran.nextInt(10);
            CURP += num;
        }

        return CURP;
    }

    public static String GenerateRFC(String nombre, String apellidos, LocalDate fechaNac){
        String[] arrApellidos = apellidos.split(" ");
        String RFC="";
        char letra;

        // Nombres y apellidos

        for (int i = 0; i < 2; i++) {
            letra = apellidos.charAt(i);
            RFC += Character.toUpperCase(letra);
        }

        letra = arrApellidos[1].charAt(0);
        RFC += Character.toUpperCase(letra);
        letra = nombre.charAt(0);
        RFC += Character.toUpperCase(letra);
        
        // Fecha de nacimiento

        RFC += String.format("%02d",fechaNac.getYear() % 100);
        RFC += String.format("%02d",fechaNac.getMonthValue() % 100);
        RFC += String.format("%02d",fechaNac.getDayOfMonth() % 100);

        // Homoclave generada por el algoritmo

        for (int i = 0; i < 3; i++) {
            int num = ran.nextInt(10);
            RFC += num;
        }

        return RFC;
    }

    public static String GenerateCtrlNum(String nombre, LocalDate fechaRegistro, NombreDeCarrera nombreDeCarrera, Rol rol) {
        // Primer letra (por default)
        String numCtrl = "";
    
        switch (rol) {
            case ALUMNO -> numCtrl += "I";
            case PROFESOR -> numCtrl += "M";
            case COORDINADOR -> numCtrl += "C";
        }
    
        // Primera letra de su nombre
        numCtrl += Character.toUpperCase(nombre.charAt(0));
    
        // Año (últimos dos dígitos del año)
        int año = fechaRegistro.getYear();
        numCtrl += String.format("%02d", año % 100);
    
        // Abreviación carrera
        numCtrl += nombreDeCarrera.toString();
    
        // Comienza con el índice 0
        int indice = 0;
    
        // Crea un StringBuilder con el valor actual de numCtrl
        StringBuilder numeroNuevo = new StringBuilder(numCtrl);
    
        // Buscar el mayor índice existente
        for (ArrayList<Usuario> listaDeUsuarios : Sistema.usuarios.values()) {
            if (listaDeUsuarios != null) { // Verifica si listaDeUsuarios no es null
                for (Usuario usuario : listaDeUsuarios) {
                    String numeroExistente = usuario.getNumControl();
                    // Comprobar si el número de control existente no es nulo y tiene el mismo prefijo
                    if (numeroExistente != null && numeroExistente.startsWith(numCtrl)) {
                        // Obtener el índice del número de control existente
                        int indiceExistente = Character.getNumericValue(numeroExistente.charAt(7));
                        // Actualizar el índice si el índice existente es mayor o igual
                        if (indice <= indiceExistente) {
                            indice = indiceExistente + 1;
                        }
                    }
                }
            }
        }
    
        // Añadir el índice final al número de control
        numCtrl += indice;
    
        return numCtrl;
    }
    
}
