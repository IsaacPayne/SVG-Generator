package com.company.templates;

/**
 * Created by isaac on 28/03/17.
 *
 * The template interface which an output template must implement
 */
public interface Template {

    /*
     * The directory to output the file into
     */
    String getOutputPath();

    /*
     * The output string e.g the SVG/XML string
     */
    String getOutputString();

    /*
     * What to call the file
     */
    String getFileName();
}
