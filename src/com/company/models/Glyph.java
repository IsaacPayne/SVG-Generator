package com.company.models;


import com.company.utils.fileUtils.FileWriter;

/**
 * Created by isaac on 27/03/17.
 */
public class Glyph {

    private final int DEFAULT_SIZE_IN_DP = 256;
    private final int DEFAULT_VIEWPORT_SIZE = 1792;
    private final String DEFAULT_TINT = "#000"; //black

    private String name;
    private String unicode;
    private String path;

    public Glyph(String name, String unicode, String path) {
        this.name = name;
        this.unicode = unicode;
        this.path = path;
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

    public String getFileName() {
        return String.format("%s.xml", name);
    }

    public void createFile(FileWriter.OutputType outputType) {
        FileWriter.writeToFile(outputType, getFileName(), toString(outputType));
    }

    public String toString(FileWriter.OutputType outputType) {
        switch (outputType) {
            case ANDROID:
                return String.format("<!-- %s -->\n" +
                                "<vector xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                                "    android:height=\"%ddp\"\n" +
                                "    android:width=\"%ddp\"\n" +
                                "    android:viewportWidth=\"%d\"\n" +
                                "    android:viewportHeight=\"%d\">\n" +
                                "\n" +
                                "    <path android:fillColor=\"%s\"\n" +
                                "        android:pathData=\"%s\" />\n" +
                                "</vector>\n",
                        getFileName(),
                        DEFAULT_SIZE_IN_DP, DEFAULT_SIZE_IN_DP,
                        DEFAULT_VIEWPORT_SIZE, DEFAULT_VIEWPORT_SIZE,
                        DEFAULT_TINT, path);
            case SVG:
                return String.format("<svg " +
                                "width=\"%d\" " +
                                "height=\"%d\" " +
                                "viewBox=\"0 0 %d %d\" " +
                                "xmlns=\"http://www.w3.org/2000/svg\">" +
                                "<path d=\"%s\"/>" +
                                "</svg>",
                        DEFAULT_VIEWPORT_SIZE, DEFAULT_VIEWPORT_SIZE,
                        DEFAULT_VIEWPORT_SIZE, DEFAULT_VIEWPORT_SIZE,
                        path);
            default:
                return toString();
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
