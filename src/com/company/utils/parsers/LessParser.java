package com.company.utils.parsers;

import com.company.utils.fileUtils.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by isaac on 27/03/17.
 *
 * Parses the less file to get the names of each of the glyphs
 */
public class LessParser {

    //@fa-var-([\w-]+):\s*"\\([0-9a-f]+)"
    private final String ANY_CHAR = "\\w";
    private final String SPACE_CHAR = "\\s";
    private final String SLASH_CHAR = "\\\\";

    private final String LESS_REG_EX = "@fa-var-(["+ANY_CHAR+"-]+):"+SPACE_CHAR+"*\""+SLASH_CHAR+"([0-9a-f]+)\";";

    private String fontPath = FileUtils.getFontAwesomePath() + "less/";

    private String fileName;

    public LessParser(String fileName) {
        this.fileName = fileName;
    }

    public HashMap<String, String> parse() {

        HashMap<String, String> nameMap = new HashMap<>();

        try {
            File file = new File(fontPath + fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                checkMatch(nameMap, line);
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nameMap;
    }

    private void checkMatch(HashMap<String, String> nameMap, String line) {
        Pattern pattern = Pattern.compile(LESS_REG_EX);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String key = createCharFromKey(matcher.group(2));
            String name = matcher.group(1);

            nameMap.put(key, name);
        }
    }

    private String createCharFromKey(String key) {
        return String.valueOf((char) Integer.parseInt(key, 16));
    }
}
