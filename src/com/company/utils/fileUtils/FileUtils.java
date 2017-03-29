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
        return getRootPath() + "output/";
    }

    public static String getFontAwesomePath() {
        return getRootPath() + "src/" + "com/" + "company/" + "font_awesome/";
    }

    /**
     * Recursively creates any directories need to create the full path
     * @param path The path we want to create
     */
    static void createPath(String path) {
        File file = new File(path);

        File parent = file.getParentFile();
        if(!parent.exists()) {
            createPath(parent.getAbsolutePath());
        }

        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory (" + path + ") is created!");
            } else {
                System.out.println("Failed to create directory! (" + path + ")");
            }
        }
    }
}
