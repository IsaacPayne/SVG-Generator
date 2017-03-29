package com.company.utils.fileUtils;

import com.company.templates.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * Created by isaac on 27/03/17.
 */
public class FileWriter {

    public static void writeToFile(Template template) {
        String path = template.getOutputPath();
        String pathName = path + template.getFileName();

        FileUtils.createPath(path);

        try {
            File file = new File(pathName);

            if(!file.exists() && file.createNewFile()) {
                System.out.println("Created " + template.getFileName() + "!");
            }

            BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(pathName));

            bw.write(template.getOutputString());
            bw.close();

            System.out.println("Done writing " + template.getFileName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
