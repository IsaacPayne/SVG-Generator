package com.company.templates;

import com.company.utils.fileUtils.FileUtils;

/**
 * Created by isaac on 28/03/17.
 */
public class SvgTemplate implements Template {

    private String name;
    private String path;
    private int viewportSize;
    private String colour;
    private int hozAdvX;

    public SvgTemplate(String name, String path, int viewportSize, String colour, int hozAdvX) {
        this.name = name;
        this.path = path;
        this.viewportSize = viewportSize;
        this.colour = colour;
        this.hozAdvX = hozAdvX;

        createSVG();
    }

    @Override
    public String getOutputPath() {
        return FileUtils.getOutputPath() + colour.substring(1) + "/" + "svg/";
    }

    @Override
    public String getOutputString() {
        return String.format("<svg " +
                        "xmlns=\"http://www.w3.org/2000/svg\" " +
                        "width=\"%d\" " +
                        "height=\"%d\" " +
                        "viewBox=\"%d %d %d %d\" >" +
                        "<path fill=\"%s\" transform=\"scale(1 -1)\" d=\"%s\"/>" +
                        "</svg>",
                viewportSize, viewportSize,
                -(viewportSize - hozAdvX) / 2, -1536,
                viewportSize, viewportSize,
                colour, path);
    }
    }

    @Override
    public String getFileName() {
        return String.format("%s.svg", name);
    }
}
