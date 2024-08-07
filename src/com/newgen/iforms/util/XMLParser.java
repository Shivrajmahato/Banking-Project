/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.util;

public class XMLParser {

    private String parseString;
    private String copyString;

    public void setInputXML(String parseThisString) {
        if (parseThisString != null) {
            copyString = parseThisString;
            parseString = toUpperCase(copyString);
        } else {
            parseString = null;
            copyString = null;
        }
    }

    public String getValueOf(String valueOf) {
        try {
            return copyString.substring(parseString.indexOf("<" +
                            toUpperCase(valueOf) + ">") + valueOf.length() + 2,
                    parseString.
                            indexOf("</" +
                                    toUpperCase(valueOf) + ">"));
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    public String toUpperCase(String valueOf) throws StringIndexOutOfBoundsException {
        String returnStr = "";
        try {
            int count = valueOf.length();
            char [] strChar = new char[count];
            valueOf.getChars(0, count, strChar, 0);
            while (count-- > 0) {
                strChar[count] = Character.toUpperCase(strChar[count]);
            }
            return new String(strChar);
        } catch (ArrayIndexOutOfBoundsException e) {
            return returnStr;
        }
    }
}
