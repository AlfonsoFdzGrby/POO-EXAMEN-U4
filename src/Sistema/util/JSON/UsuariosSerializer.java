package src.Sistema.util.JSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import src.Sistema.Sistema;
import src.Sistema.util.JSON.models.UsuariosModel;
import src.Usuarios.util.Rol;

public class UsuariosSerializer {
    public static void writeToJSON(){
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.json"));
            json.toJson(Sistema.usuarios, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromJSON(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.json"));
            Gson json = new Gson();
            UsuariosModel model = json.fromJson(reader, UsuariosModel.class);
            Sistema.usuarios.get(Rol.ALUMNO).addAll(model.getALUMNO());
            Sistema.usuarios.get(Rol.PROFESOR).addAll(model.getPROFESOR());
            Sistema.usuarios.get(Rol.COORDINADOR).addAll(model.getCOORDINADOR());
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}
