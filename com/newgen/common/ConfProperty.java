/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 *
 * @author Lalit Regmi
 */
public class ConfProperty {

    public static Properties prop;
    public static String userQuery;

    //************Read Property file**************************//
    static {
        ConfProperty.prop = new Properties();
        ConfProperty.userQuery = "";
        String fileLocation = System.getProperty("user.dir") + File.separator + "TF_Config" + File.separator + "Config.properties";

        try {
            prop.load(new FileInputStream(fileLocation));
            Enumeration em = ConfProperty.prop.propertyNames();
            while (em.hasMoreElements()) {
                String key = (String) em.nextElement();
                String val = (ConfProperty.prop.get(key) != null) ? ConfProperty.prop.get(key).toString() : null;
                if (val != null || !val.trim().equals("")) {
                    ConfProperty.prop.setProperty(key.trim().toLowerCase(), val.trim());
                }
            }
        } catch (Exception ex) {
            System.out.println("ConfProperty property file read exception" + ex);
        }
    }

    //***********fetch value from Property file*************//
    public static String getProperty(String key) {
        String val = "";
        val = ConfProperty.prop.getProperty(key.toLowerCase().trim());
        if (val == null) {
            System.out.println("No value found for key :" + key.toLowerCase().trim());
        }
        return val;
    }

    public static void WriteToLog(String strOutput) {
        DateFormat logDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        StringBuffer str = new StringBuffer();
        str.append(DateFormat.getDateTimeInstance(0, 2).format(new Date()));

        str.append("\t\t\t");
        str.append(strOutput);
        str.append("\n");
        StringBuffer strFilePath = null;
        String tmpFilePath = "";
        Calendar calendar = new GregorianCalendar();
        String DtString = String.valueOf(logDateFormat.format(date));
        try {
            strFilePath = new StringBuffer(50);
            strFilePath.append(System.getProperty("user.dir"));
            strFilePath.append(File.separatorChar);
            strFilePath.append("TF_Logs");
            strFilePath.append(File.separatorChar);
            strFilePath.append(DtString);
            strFilePath.append(File.separatorChar);
            File test1 = new File(strFilePath.toString());
            if (!(test1.exists())) {
                test1.mkdirs();
            }
            strFilePath.append("TF.log");
            tmpFilePath = strFilePath.toString();
            BufferedWriter out = new BufferedWriter(new FileWriter(tmpFilePath, true));
            out.write(str.toString());
            out.close();
        } catch (Exception e) {
            System.out.println("Exception in creating log file" + e.getMessage());
        } finally {
            strFilePath = null;
        }
    }
}
