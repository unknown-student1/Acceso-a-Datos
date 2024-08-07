import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("peliculas.xml");

            // Obtener la lista de nodos de películas
            NodeList peliculas = document.getElementsByTagName("Pelicula");

            // Recorrer cada película
            for (int i = 0; i < peliculas.getLength(); i++) {
                Element pelicula = (Element) peliculas.item(i);

                // Obtener la información de la película
                String titulo = pelicula.getElementsByTagName("Titulo").item(0).getTextContent();
                String director = pelicula.getElementsByTagName("Director").item(0).getTextContent();
                String year = pelicula.getElementsByTagName("Fecha").item(0).getTextContent();

                // Obtener la lista de actores
                NodeList actoresNodeList = pelicula.getElementsByTagName("Actor");
                StringBuilder actores = new StringBuilder();

                for (int j = 0; j < actoresNodeList.getLength(); j++) {
                    actores.append(actoresNodeList.item(j).getTextContent());
                    if (j < actoresNodeList.getLength() - 1) {
                        actores.append(", ");
                    }
                }

                // Mostrar la información
                System.out.println("Título: " + titulo);
                System.out.println("Director: " + director);
                System.out.println("Año: " + year);
                System.out.println("Actores: " + actores);
                System.out.println("---------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
