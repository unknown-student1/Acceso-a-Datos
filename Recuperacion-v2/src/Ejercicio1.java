import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.sql.*;
import java.util.ArrayList;


public class Ejercicio1 {
    /**
     * 1. Realiza un programa que lea el contenido de la base de datos, lo guarde en un ArrayList y
     * genere un archivo.xml.*/

    public static void main(String[] args) {
        // Conexión a la base de datos
        String url = "jdbc:mysql://localhost/animales";
        String usuario = "root";
        String contraseña = "root";

        try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
            // Consulta SQL
            String consulta = "SELECT * FROM mascotas";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(consulta)) {

                // Crear ArrayList para almacenar las mascotas
                ArrayList<Mascota> mascotas = new ArrayList<>();

                // Leer resultados y almacenar en ArrayList
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String especie = resultSet.getString("especie");
                    String raza = resultSet.getString("raza");
                    int edad = resultSet.getInt("edad");

                    Mascota mascota = new Mascota(id, nombre, especie, raza, edad);
                    mascotas.add(mascota);
                }

                // Crear documento XML
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();

                // Crear elemento raíz
                Element raiz = document.createElement("mascotas");
                document.appendChild(raiz);

                // Crear nodos para cada mascota
                for (Mascota mascota : mascotas) {
                    Element mascotaElemento = document.createElement("mascota");

                    Element idElemento = document.createElement("id");
                    idElemento.appendChild(document.createTextNode(String.valueOf(mascota.getId())));
                    mascotaElemento.appendChild(idElemento);

                    Element nombreElemento = document.createElement("nombre");
                    nombreElemento.appendChild(document.createTextNode(mascota.getNombre()));
                    mascotaElemento.appendChild(nombreElemento);

                    Element especieElemento = document.createElement("especie");
                    especieElemento.appendChild(document.createTextNode(mascota.getEspecie()));
                    mascotaElemento.appendChild(especieElemento);

                    Element razaElemento = document.createElement("raza");
                    razaElemento.appendChild(document.createTextNode(mascota.getRaza()));
                    mascotaElemento.appendChild(razaElemento);

                    Element edadElemento = document.createElement("edad");
                    edadElemento.appendChild(document.createTextNode(String.valueOf(mascota.getEdad())));
                    mascotaElemento.appendChild(edadElemento);

                    raiz.appendChild(mascotaElemento);
                }

                // Guardar el documento en un archivo XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new java.io.File("mascotas.xml"));
                transformer.transform(source, result);

                System.out.println("Archivo XML generado correctamente.");

            } catch (SQLException e) {
                System.out.println("\u001B[31mError: No ha sido posible establecer la conexión con la base de datos\u001B[0m");
                e.printStackTrace();
            }
        } catch (SQLException | ParserConfigurationException | TransformerException e) {
            if (e instanceof SQLException){
                System.out.println("\u001B[31mError: No ha sido posible contactar con la base de datos\u001B[0m");
            }
            if (e instanceof ParserConfigurationException || e instanceof TransformerException){
                System.out.println("\u001B[31mError: Error al generar el archivo XML\u001B[0m");
            }
        }
    }
}
