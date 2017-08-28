package com.company;

import com.company.models.Glyph;
import com.company.utils.parsers.LessParser;
import com.company.utils.parsers.XMLParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //Check that the input is in the format of #000 or #000000
    private static final String REG_EX_HEX = "^#([0-9A-Fa-f]{3}){1,2}$";
    private static final String DEFAULT_TINT = "#000000"; //black

    public static void main(String[] args) {
        String colour = DEFAULT_TINT;

        if (args.length == 1) {
            colour = getHEXFromInput(args[0]);
        }

        XMLParser xmlParser = new XMLParser("fontawesome-webfont.svg");
        ArrayList<Glyph> glyphs = xmlParser.parse();

        LessParser lessParser = new LessParser("variables.less");
        HashMap<String, String> nameMap = lessParser.parse();

        for (Glyph glyph : glyphs) {
            String glyphName = nameMap.get(glyph.getUnicode());

            //Ignore SVGs beginning with a full-stop e.g .notdef
            if(glyph.getName().startsWith(".")) {
                continue;
            }

            if(glyphName != null) {
                glyph.setName(glyphName);
            }

            glyph.createFile(Glyph.OutputType.ANDROID, colour);
            glyph.createFile(Glyph.OutputType.SVG, colour);
        }
    }

    private static String getHEXFromInput(String input) {
        Pattern pattern = Pattern.compile(REG_EX_HEX);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            System.out.println(String.format("Invalid colour entered: %s expecting format #000000 or #000", input));
            return DEFAULT_TINT;
        }

        return matcher.group(0);
    }
}

