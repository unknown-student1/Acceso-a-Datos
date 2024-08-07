
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio4 {
    public static void main(String[] args) {
        try {
            // Lee el contenido del archivo XML
            String xmlContent = new String(Files.readAllBytes(Paths.get("peliculas.xml")));

            // Convierte XML a JSONObject
            JSONObject jsonObject = XML.toJSONObject(xmlContent);

            // Convierte el JSONObject a una representación JSON formateada
            String jsonFormatted = jsonObject.toString(4);

            // Imprime el resultado
            System.out.println(jsonFormatted);

            // Opcional: Guarda el JSON en un archivo
            Path outputPath = Paths.get("peliculas.json");
            Files.write(outputPath, jsonFormatted.getBytes());

            System.out.println("El archivo JSON ha sido creado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
