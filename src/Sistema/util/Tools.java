package src.Sistema.util;

import java.time.*;
import java.time.format.*;
import java.util.*;

import src.Carrera.util.NombreDeCarrera;

public class Tools {
    private static Scanner sc = new Scanner(System.in);
    private static Random ran = new Random();
    private static int indiceNControl = 0;

    public static void printHeader(String header){
        System.out.println("===============================================================");
        System.out.println(header);
        System.out.println("===============================================================");
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

    public static String GenerateCtrlNum(String nombre, LocalDate fechaRegistro, NombreDeCarrera nombreDeCarrera){
        // Primer letra (por default)
        String numCtrl = "I";

        // Año (ultimos dos dígitos del año)
        int año = fechaRegistro.getYear();
        numCtrl += año%100;

        // Abreviación carrera
        numCtrl += nombreDeCarrera.toString();

        // Indice (REQUIERE DE UNA VALIDACIÓN)
        numCtrl += indiceNControl;

        return numCtrl;
    }
}
