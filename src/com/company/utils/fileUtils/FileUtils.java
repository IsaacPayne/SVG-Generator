package com.company.utils.fileUtils;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by isaac on 28/03/17.
 */
public class FileUtils {

    public static String getRootPath() {
        return Paths.get(".").toAbsolutePath().normalize().toString() + "/";
    }

    public static String getOutputPath() {
        String pathName = getRootPath() + "output" + "/";
        createDir(pathName);
        return pathName;
    }

    public static String getFontAwesomePath() {
        return getRootPath() + "src" + "/com" + "/company" + "/font_awesome";
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
}
