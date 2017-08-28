package com.company.utils.parsers;

import com.company.models.Glyph;
import com.company.utils.fileUtils.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by isaac on 27/03/17.
 *
 * Parses the FontAwesome XML file to get the each of the glyphs
 */
public class XMLParser {

    //Taken from FontAwesome
    private final int DEFAULT_HOZ_ADV_X = 1536;

    private String fontPath = FileUtils.getFontAwesomePath() + "fonts/";
    private String fileName;

    public XMLParser(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Glyph> parse() {
        ArrayList<Glyph> glyphs = new ArrayList<>();

        try {
            File fXmlFile = new File(fontPath + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList glyphList = doc.getElementsByTagName("glyph");

            for (int i = 0; i < glyphList.getLength(); i++) {

                Node glyphNode = glyphList.item(i);

                if (glyphNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element glyphElement = (Element) glyphNode;

                    String name = glyphElement.getAttribute("glyph-name");
                    String unicode = glyphElement.getAttribute("unicode");
                    int hozAdvX = parseAsInt(glyphElement.getAttribute("horiz-adv-x"), DEFAULT_HOZ_ADV_X);

                    String path = glyphElement.getAttribute("d");

                    if(path.length() == 0) {
                        continue;
                    }

                    glyphs.add(new Glyph(name, unicode, path, hozAdvX));
                }
            }
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return glyphs;
    }

    private int parseAsInt(String n, int defaultValue) {
        if(n.length() == 0) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(n);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
