/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shivaraj
 */
public class ConfProperty {

    private ConfProperty() {

    }

    static Properties prop;
    static String userQuery;

    //************Read Property file**************************//
    static {
        ConfProperty.prop = new Properties();
        ConfProperty.userQuery = "";
        String fileLocation = System.getProperty("user.dir") + File.separator + "TF_Config" + File.separator + "Config.properties";

        try {
            prop.load(new FileInputStream(fileLocation));
            Enumeration<?> em = ConfProperty.prop.propertyNames();
            while (em.hasMoreElements()) {
                String key = (String) em.nextElement();
                String val = (ConfProperty.prop.get(key) != null) ? ConfProperty.prop.get(key).toString() : null;
                if (val != null || !val.trim().equals("")) {
                    ConfProperty.prop.setProperty(key.trim().toLowerCase(), val.trim());
                }
            }
        } catch (Exception ex) {
            LogMessages.statusLog.info("ConfProperty property file read exception" + ex);
        }
    }

    public static void writeToLog(String strOutput) {
        DateFormat logDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        StringBuilder str = new StringBuilder();
        str.append(DateFormat.getDateTimeInstance(0, 2).format(new Date()));
        str.append("\t\t\t");
        str.append(strOutput);
        str.append("\n");

        String dateString = logDateFormat.format(date);
        StringBuilder strFilePath = new StringBuilder(50);
        strFilePath.append(System.getProperty("user.dir"));
        strFilePath.append(File.separatorChar);
        strFilePath.append("TF_Logs");
        strFilePath.append(File.separatorChar);
        strFilePath.append(dateString);
        strFilePath.append(File.separatorChar);
        File test1 = new File(strFilePath.toString());
        if (!(test1.exists())) {
            test1.mkdirs();
        }
        strFilePath.append("TF.log");

        try (BufferedWriter out = new BufferedWriter(new FileWriter(strFilePath.toString(), true))) {
            out.write(str.toString());
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception in creating log file" + e.getMessage());
        }
    }
}
