package com.company;

import com.company.models.Glyph;
import com.company.utils.fileUtils.FileWriter;
import com.company.utils.parsers.LessParser;
import com.company.utils.parsers.XMLParser;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    //TODO: Accept color as arg
    public static void main(String[] args) {
        //if (args.length == 0) {
        //    System.out.print("Please provide an arg");
        //}

        String fontPath = "fontawesome-webfont.svg";
        ArrayList<Glyph> glyphs = XMLParser.parse(fontPath);

        String lessPath = "variables.less";
        HashMap<String, String> nameMap = LessParser.parse(lessPath);

        for (Glyph glyph : glyphs) {
            String glyphName = nameMap.get(glyph.getUnicode());

            if(glyphName != null) {
                glyph.setName(glyphName);
            }

            //System.out.println("glyphName for " + glyph.getUnicode() + ": " + glyphName);
            glyph.createFile(FileWriter.OutputType.ANDROID);
            glyph.createFile(FileWriter.OutputType.SVG);
        }
    }
}
