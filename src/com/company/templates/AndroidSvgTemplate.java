package com.company.templates;

import com.company.utils.fileUtils.FileUtils;

/**
 * Created by isaac on 28/03/17.
 *
 * A template which will output the glyphs as Android Vector Drawables
 */
public class AndroidSvgTemplate implements Template {

    private final int DEFAULT_SIZE_IN_DP = 256;

    private String name;
    private String path;
    private int viewportSize;
    private String colour;
    private int hozAdvX;

    public AndroidSvgTemplate(String name, String path, int viewportSize, String colour, int hozAdvX) {
        this.name = name;
        this.path = path;
        this.viewportSize = viewportSize;
        this.colour = colour;
        this.hozAdvX = hozAdvX;
    }

    @Override
    public String getOutputPath() {
        return FileUtils.getOutputPath() + colour.substring(1) + "/" + "android-svg/";
    }

    @Override
    public String getOutputString() {
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
                viewportSize, viewportSize,
                colour, path);
    }

    @Override
    public String getFileName() {
        return String.format("%s.xml", name);
    }
}
