package TreekingApp;

import org.json.JSONArray;
import org.json.JSONObject;
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Main {
    public static void main(String[] args) {
        // Ruta del archivo JSON
        String filePath = "trail.json";
        String jsonString = readJsonFile(filePath);

        // Creamos un Objeto de tipo JSON
        JSONObject jsonObject = new JSONObject(jsonString);

        // Obtenemos el valor $name del objeto JSON
        String name = jsonObject.getString("name");

        // Obtenemos la fecha del objeto JSON
        String dateString = jsonObject.getString("date");
        Date date = parseDateString(dateString);

        // Creamos un array con todos $trackpoints con el método JsonArray
        JSONArray trackPointsArray = jsonObject.getJSONArray("trackPoints");

        // Creamos una instancia de Track
        Track track = new Track(name, date);

        // Iteramos sobre el array para obtener los valores de los $trackpoints
        for (int i = 0; i < trackPointsArray.length(); i++) {
            JSONObject trackPoint = trackPointsArray.getJSONObject(i);
            double lng = trackPoint.getDouble("lng");
            double lat = trackPoint.getDouble("lat");
            double ele = trackPoint.getDouble("ele");
            String timeString = trackPoint.getString("time");
            Date time = parseDateString(timeString);

            // Creamos una instancia de TrackPoint y la agregamos al Track
            TrackPoint trackPointInstance = new TrackPoint(lng, lat, ele, time);
            track.addTrackPoint(trackPointInstance);
        }

        // Exportamos a XML
        try {
            exportToXML(track, "trail.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mostrar elevación máxima y mínima
        System.out.println("Elevación Máxima: " + track.getMaxElevation() + " metros");
        System.out.println("Elevación Mínima: " + track.getMinElevation() + " metros");
    }

    /**
     * Método para leer la información de un fichero JSON
     * @param filePath
     * @return String con el contenido del fichero
     */
    private static String readJsonFile(String filePath) {
        StringBuilder jsonString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }

    /** Método para transforma una cadena en un Objeto de tipo Date
     * @param dateString
     * @return Date
     */
    private static Date parseDateString(String dateString) {
        // Creamos un formato de fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método para exportar los puntos al documento XML
     * @param track
     * @param fileName
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws IOException
     */
    private static void exportToXML(Track track, String fileName) throws ParserConfigurationException, TransformerException, IOException {
        try {

            if (track.getDate() == null) {
                throw new IllegalArgumentException("La fecha de la ruta no puede ser nula");
            }

            // Crear un nuevo documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Elemento raíz <trk>
            Element rootElement = doc.createElement("trk");
            doc.appendChild(rootElement);

            // Elemento <name>
            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(track.getName()));
            rootElement.appendChild(nameElement);

            // Elemento <date>
            Element dateElement = doc.createElement("date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            dateElement.appendChild(doc.createTextNode(dateFormat.format(track.getDate())));
            rootElement.appendChild(dateElement);

            // Elemento <trkPoints>
            Element trkPointsElement = doc.createElement("trkPoints");
            rootElement.appendChild(trkPointsElement);

            // Iterar sobre los TrackPoints y agregar elementos <trkpt> al elemento <trkPoints>
            for (TrackPoint trackPoint : track.getTrackPoints()) {
                Element trkptElement = doc.createElement("trkpt");
                trkptElement.setAttribute("lat", Double.toString(trackPoint.getLat()));
                trkptElement.setAttribute("lon", Double.toString(trackPoint.getLng()));

                // Elemento <ele>
                Element eleElement = doc.createElement("ele");
                eleElement.appendChild(doc.createTextNode(Double.toString(trackPoint.getEle())));
                trkptElement.appendChild(eleElement);

                // Elemento <time>
                Element timeElement = doc.createElement("time");
                timeElement.appendChild(doc.createTextNode(dateFormat.format(trackPoint.getTime())));
                trkptElement.appendChild(timeElement);

                trkPointsElement.appendChild(trkptElement);
            }

            // Crear el objeto Transformer para escribir el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(fileName));

            // Escribir el documento XML en el archivo
            transformer.transform(source, result);

            System.out.println("XML exportado correctamente a " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
