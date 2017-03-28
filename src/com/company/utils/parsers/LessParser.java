package com.company.utils.parsers;

import com.company.utils.fileUtils.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by isaac on 27/03/17.
 */
public class LessParser {

    //@fa-var-([\w-]+):\s*"\\([0-9a-f]+)"
    private static final String ANY_CHAR = "\\w";
    private static final String SPACE_CHAR = "\\s";
    private static final String SLASH_CHAR = "\\\\";

    private static final String LESS_REG_EX = "@fa-var-(["+ANY_CHAR+"-]+):"+SPACE_CHAR+"*\""+SLASH_CHAR+"([0-9a-f]+)\";";

    private static String fontPath = FileUtils.getFontAwesomePath() + "/less" + "/";

    public static HashMap<String, String> parse(String fileName) {

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

    private static void checkMatch(HashMap<String, String> nameMap, String line) {
        Pattern pattern = Pattern.compile(LESS_REG_EX);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String key = createCharFromKey(matcher.group(2));
            String name = matcher.group(1);

            //System.out.println("key: " + key);
            //System.out.println("name: " + name);

            nameMap.put(key, name);
        }
    }

    private static String createCharFromKey(String key) {
        return String.valueOf((char) Integer.parseInt(key, 16));
    }
}
