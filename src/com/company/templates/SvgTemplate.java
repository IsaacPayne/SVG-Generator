package com.company.templates;

import com.company.utils.fileUtils.FileUtils;

/**
 * Created by isaac on 28/03/17.
 */
public class SvgTemplate implements Template {

    private String fileName;
    private String path;
    private int viewportSize;
    private String colour;

    public SvgTemplate(String fileName, String path, int viewportSize, String colour) {
        this.fileName = fileName;
        this.path = path;
        this.viewportSize = viewportSize;
        this.colour = colour;
    }

    @Override
    public String getOutputPath() {

        return FileUtils.getOutputPath() + colour.substring(1) + "/" + "svg/";
    }

    @Override
    public String getOutputString() {
        return String.format("<svg " +
                        "width=\"%d\" " +
                        "height=\"%d\" " +
                        "viewBox=\"0 0 %d %d\" " +
                        "xmlns=\"http://www.w3.org/2000/svg\">" +
                        "<path d=\"%s\" fill=\"%s\"/>" +
                        "</svg>",
                viewportSize, viewportSize,
                viewportSize, viewportSize,
                path, colour);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
