import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ejercicio5 {
    public static void main(String[] args) {
        try {
            // URL de la página web con las cotizaciones
            String url = "http://www.expansion.com/mercados/cotizaciones/indices/ibex35_I.IB.html?ci d=SEM23201";

            // Conecta y obtiene el contenido HTML de la página
            Document document = Jsoup.connect(url).get();

            // Crear el documento XML
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            org.w3c.dom.Document xmlDocument = documentBuilder.newDocument();

            // Crear el elemento principal <Empresas>
            org.w3c.dom.Element empresasElement = xmlDocument.createElement("Empresas");
            xmlDocument.appendChild(empresasElement);

            // Seleccionar las filas de la tabla con las cotizaciones
            Elements rows = document.select("table#listado_valores tbody tr");

            // Iterar sobre las filas y extraer la información
            for (Element row : rows) {
                // Extraer la información de cada columna
                String nombre = row.select("td.primera a").text();
                String cotizacion = row.select("td:nth-child(4)").text();
                String hora = row.select("td:nth-child(10)").text();

                // Obtener la fecha del dia
                LocalDate fechaHoy = LocalDate.now();

                // Formatear la fecha con el formato "dd-MM-yyyy"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fechaFormateada = fechaHoy.format(formatter);

                // Crear el elemento <empresa> y sus elementos hijos
                org.w3c.dom.Element empresaElement = xmlDocument.createElement("empresa");
                org.w3c.dom.Element nombreElement = xmlDocument.createElement("nombre");
                org.w3c.dom.Element fechaElement = xmlDocument.createElement("fecha");
                org.w3c.dom.Element horaElement = xmlDocument.createElement("hora");
                org.w3c.dom.Element cotizacionElement = xmlDocument.createElement("cotizacion");

                nombreElement.appendChild(xmlDocument.createTextNode(nombre));
                fechaElement.appendChild(xmlDocument.createTextNode(fechaFormateada));
                horaElement.appendChild(xmlDocument.createTextNode(hora));
                cotizacionElement.appendChild(xmlDocument.createTextNode(cotizacion));

                empresaElement.appendChild(nombreElement);
                empresaElement.appendChild(fechaElement);
                empresaElement.appendChild(horaElement);
                empresaElement.appendChild(cotizacionElement);

                // Agregar el elemento <empresa> al elemento principal <Empresas>
                empresasElement.appendChild(empresaElement);
            }

            // Guardar el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xmlDocument);

            StreamResult result = new StreamResult(new FileWriter("cotizaciones.xml"));
            transformer.transform(source, result);

            System.out.println("Cotizaciones guardadas en cotizaciones.xml con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
