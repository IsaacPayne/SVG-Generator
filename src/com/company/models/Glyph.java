package com.company.models;


import com.company.templates.AndroidSvgTemplate;
import com.company.templates.SvgTemplate;
import com.company.utils.fileUtils.FileWriter;

/**
 * Created by isaac on 27/03/17.
 */
public class Glyph {

    //Taken from FontAwesome
    private final int DEFAULT_VIEWPORT_SIZE = 1792;

    public enum OutputType {
        ANDROID,
        SVG
    }

    private String name;
    private String unicode;
    private String path;
    private int hozAdvX; //TODO: use to position Android icon

    public Glyph(String name, String unicode, String path, int hozAdvX) {
        this.name = name;
        this.unicode = unicode;
        this.path = path;
        this.hozAdvX = hozAdvX;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnicode() {
        return unicode;
    }

    public String getPath() {
        return path;
    }

    public void createFile(OutputType outputType, String colour) {
        switch (outputType) {
            case ANDROID:
                FileWriter.writeToFile(new AndroidSvgTemplate(name, path, DEFAULT_VIEWPORT_SIZE, colour, hozAdvX));
                break;
            case SVG:
                FileWriter.writeToFile(new SvgTemplate(name, path, DEFAULT_VIEWPORT_SIZE, colour, hozAdvX));
                break;
        }
    }

    @Override
    public String toString() {
        return
                "{" +
                        "Name: " + name + " " +
                        "Unicode: " + unicode + " " +
                        "Path: " + path +
                "}";
    }
}
