package com.company.utils.parsers;

import com.company.models.Glyph;
import com.company.utils.fileUtils.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by isaac on 27/03/17.
 */
public class XMLParser {

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

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("glyph");

            //System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String name = eElement.getAttribute("glyph-name");
                    String unicode = eElement.getAttribute("unicode");
                    String path = eElement.getAttribute("d");

                    if(path.length() == 0) {
                        continue;
                    }

                    glyphs.add(new Glyph(name, unicode, path));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return glyphs;
    }
}
