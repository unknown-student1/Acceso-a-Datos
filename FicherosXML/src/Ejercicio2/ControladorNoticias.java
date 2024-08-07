package Ejercicio2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;


public class ControladorNoticias extends DefaultHandler{
    private boolean inItem = false;
    private boolean inTitle = false;
    private boolean inDate = false;

    private StringBuilder titleBuffer;
    private StringBuilder dateBuffer;

    public void parse(String url) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(new URL(url).openStream(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equalsIgnoreCase(qName)) {
            inItem = true;
        } else if (inItem && "title".equalsIgnoreCase(qName)) {
            inTitle = true;
            titleBuffer = new StringBuilder();
        } else if (inItem && "pubDate".equalsIgnoreCase(qName)) {
            inDate = true;
            dateBuffer = new StringBuilder();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (inTitle) {
            titleBuffer.append(ch, start, length);
        } else if (inDate) {
            dateBuffer.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equalsIgnoreCase(qName)) {
            inItem = false;
            System.out.println("Título: " + titleBuffer.toString().trim());
            System.out.println("Fecha: " + dateBuffer.toString().trim());
            System.out.println("------------------------------------------");
        } else if (inItem && "title".equalsIgnoreCase(qName)) {
            inTitle = false;
        } else if (inItem && "pubDate".equalsIgnoreCase(qName)) {
            inDate = false;
        }
    }
}
