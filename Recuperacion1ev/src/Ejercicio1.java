import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ejercicio1 {

    public static void main(String[] args) {
        // Conexión a la base de datos
        String url = "jdbc:mysql://localhost/motos";
        String usuario = "root";
        String contraseña = "root";

        ArrayList<Moto> motos = new ArrayList<>();

        try {
            File xmlFile = new File("motos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("vehiculo");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int numero = Integer.parseInt(element.getAttribute("numero"));
                    String marca = element.getElementsByTagName("marca").item(0).getTextContent();
                    String modelo = element.getElementsByTagName("modelo").item(0).getTextContent();

                    Moto moto = new Moto(numero,marca,modelo);
                    motos.add(moto);
                }
            }

            for(int i = 0; i < motos.size(); i++){
                int numero = motos.get(i).getNumero();
                String marca = motos.get(i).getMarca();
                String modelo = motos.get(i).getModelo();

                try {
                    Connection connection = DriverManager.getConnection(url,usuario,contraseña);

                    String insert = "INSERT INTO MOTOS VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insert);
                    preparedStatement.setInt(1, numero);
                    preparedStatement.setString(2, marca);
                    preparedStatement.setString(3, modelo);

                    preparedStatement.executeUpdate();

                    connection.close();

                } catch (SQLException e) {
                    System.out.println("\u001B[31mError: No ha sido posible contactar con la base de datos\u001B[0m");
                    e.printStackTrace();
                }

            }

            System.out.println("Los datos se han insertado correctamente");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
