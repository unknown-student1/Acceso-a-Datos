import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio3 {
    /**
     * 3. Realiza la lectura del fichero xml (creado en el ejercicio 1) y guárdalo en un fichero de objetos.
     * (Si el programa se ejecuta nuevamente, los datos seguirán añadiéndose al fichero).
     * 4. Comprueba que se han almacenado bien, mostrando el contenido de todo el fichero de de objetos.
     */

    private static final String RUTA_XML = "mascotas.xml";
    private static final String RUTA_FICHERO_OBJETOS = "fichero_mascotas.obj";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer el fichero XML y guardar en un ArrayList de Mascotas
        ArrayList<Mascota> mascotas = leerXML(RUTA_XML);

        while (true) {
            System.out.println("1. Guardar XML en fichero de objetos");
            System.out.println("2. Mostrar fichero de objetos");
            System.out.println("3. Recuperar Objetos del fichero");
            System.out.println("4. Salir");

            System.out.print("Seleccione una operación: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Guardar el ArrayList de Mascotas en un fichero de objetos
                    guardarEnFicheroObjetos(mascotas, RUTA_FICHERO_OBJETOS);
                    break;
                case 2:
                    // Mostrar el contenido del fichero de objetos
                    mostrarContenidoFicheroObjetos(RUTA_FICHERO_OBJETOS);
                    break;
                case 3:
                    // Recuperar el ArrayList de Mascotas desde el fichero de objetos
                    ArrayList<Mascota> mascotasRecuperadas = recuperarDeFicheroObjetos(RUTA_FICHERO_OBJETOS);

                    // Mostrar el contenido de los objetos recuperados
                    System.out.println("Contenido del fichero de objetos recuperado:");
                    for (Mascota mascota : mascotasRecuperadas) {
                        System.out.println(mascota.toString());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    // Método para leer el fichero XML y retornar un ArrayList de Mascotas
    private static ArrayList<Mascota> leerXML(String rutaXML) {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(rutaXML));

            NodeList mascotaNodes = document.getElementsByTagName("mascota");

            for (int i = 0; i < mascotaNodes.getLength(); i++) {
                Element mascotaElement = (Element) mascotaNodes.item(i);

                int id = Integer.parseInt(mascotaElement.getElementsByTagName("id").item(0).getTextContent());
                String nombre = mascotaElement.getElementsByTagName("nombre").item(0).getTextContent();
                String especie = mascotaElement.getElementsByTagName("especie").item(0).getTextContent();
                String raza = mascotaElement.getElementsByTagName("raza").item(0).getTextContent();
                int edad = Integer.parseInt(mascotaElement.getElementsByTagName("edad").item(0).getTextContent());

                Mascota mascota = new Mascota(id, nombre, especie, raza, edad);
                mascotas.add(mascota);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mascotas;
    }

    // Método para guardar un ArrayList de Mascotas en un fichero de objetos
    private static void guardarEnFicheroObjetos(ArrayList<Mascota> mascotas, String rutaFicheroObjetos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFicheroObjetos, true))) {
            oos.writeObject(mascotas);
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero de objetos.");
            e.printStackTrace();
        }
    }

    // Método para mostrar el contenido del fichero de objetos
    private static void mostrarContenidoFicheroObjetos(String rutaFicheroObjetos) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaFicheroObjetos))) {
            while (true) {
                try {
                    ArrayList<Mascota> mascotas = (ArrayList<Mascota>) ois.readObject();
                    System.out.println("Contenido del fichero de objetos:");
                    for (Mascota mascota : mascotas) {
                        System.out.println(mascota.toString());
                    }
                } catch (EOFException e) {
                    // Fin del archivo
                    break;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer el fichero de objetos.");
            e.printStackTrace();
        }
    }

    // Método para recuperar un ArrayList de Mascotas desde un fichero de objetos
    private static ArrayList<Mascota> recuperarDeFicheroObjetos(String rutaFicheroObjetos) {
        ArrayList<Mascota> mascotasRecuperadas = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaFicheroObjetos))) {
            while (true) {
                try {
                    ArrayList<Mascota> mascotas = (ArrayList<Mascota>) ois.readObject();
                    mascotasRecuperadas.addAll(mascotas);
                } catch (EOFException e) {
                    // Fin del archivo
                    break;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer el fichero de objetos.");
            e.printStackTrace();
        }

        return mascotasRecuperadas;
    }
}

