package Ejercicio2;

import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        ControladorNoticias analizarnoticias = new ControladorNoticias();
        analizarnoticias.parse("https://feeds.elpais.com/mrss-s/pages/ep/site/elpais.com/portada");
    }
}
