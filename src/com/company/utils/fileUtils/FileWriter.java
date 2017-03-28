package com.company.utils.fileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * Created by isaac on 27/03/17.
 */
public class FileWriter {

    public enum OutputType {
        ANDROID,
        SVG
    }

    private final static String DEFAULT_ANDROID_OUTPUT_DIR = "android-svg" + "/";
    private final static String DEFAULT_SVG_OUTPUT_DIR = "svg" + "/";

    public static void writeToFile(OutputType outputType, String fileName, String data) {
        String path = getDefaultDir(outputType);
        String pathName = path + fileName;

        createDir(path);

        try {
            File file = new File(pathName);

            if(!file.exists() && file.createNewFile()) {
                System.out.println("Created " + fileName + "!");
            }

            BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(pathName));

            bw.write(data);
            bw.close();

            System.out.println("Done writing " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDir(String pathName) {
        File file = new File(pathName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory (" + pathName + ") is created!");
            } else {
                System.out.println("Failed to create directory! (" + pathName + ")");
            }
        }
    }

    private static String getDefaultDir(OutputType outputType){
        String path = FileUtils.getOutputPath();

        switch (outputType){
            case ANDROID:
                return path + DEFAULT_ANDROID_OUTPUT_DIR;
            case SVG:
                return path + DEFAULT_SVG_OUTPUT_DIR;
            default:
                return path + DEFAULT_SVG_OUTPUT_DIR;
        }
    }
}
