package Ejercicio3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CatalogoPeliculas extends JFrame{
    private DefaultTableModel tableModel;
    private JTable table;

    public CatalogoPeliculas() {
        setTitle("Catálogo de Películas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 400));
        setLayout(new BorderLayout());

        // Crear la tabla
        tableModel = new DefaultTableModel(new Object[]{"Título", "Duración", "Género", "Fecha", "Director", "Sinopsis", "Actores"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear el botón de importar
        JButton importButton = new JButton("Importar Catálogo");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarCatalogo();
            }
        });

        // Crear el panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.add(importButton);

        // Agregar componentes al marco
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void importarCatalogo() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos XML", "xml");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            procesarArchivoXML(selectedFile);
        }
    }

    private void procesarArchivoXML(File file) {
        try {
            // Limpiar la tabla antes de agregar nuevas películas
            tableModel.setRowCount(0);

            // Crear un objeto DocumentBuilder para procesar el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            // Obtener la lista de nodos de películas
            NodeList peliculas = document.getElementsByTagName("Pelicula");

            // Recorrer cada película y agregarla a la tabla
            for (int i = 0; i < peliculas.getLength(); i++) {
                Element pelicula = (Element) peliculas.item(i);

                String titulo = pelicula.getElementsByTagName("Titulo").item(0).getTextContent();
                String duracion = pelicula.getElementsByTagName("Duracion").item(0).getTextContent();
                String genero = pelicula.getElementsByTagName("Genero").item(0).getTextContent();
                String fecha = pelicula.getElementsByTagName("Fecha").item(0).getTextContent();
                String director = pelicula.getElementsByTagName("Director").item(0).getTextContent();
                String sinopsis = pelicula.getElementsByTagName("sinopsis").item(0).getTextContent();

                NodeList actoresNodeList = pelicula.getElementsByTagName("Actor");
                StringBuilder actores = new StringBuilder();
                for (int j = 0; j < actoresNodeList.getLength(); j++) {
                    actores.append(actoresNodeList.item(j).getTextContent());
                    if (j < actoresNodeList.getLength() - 1) {
                        actores.append(", ");
                    }
                }

                // Agregar la película a la tabla
                tableModel.addRow(new Object[]{titulo, duracion, genero, fecha, director, sinopsis, actores.toString()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al procesar el archivo XML", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
