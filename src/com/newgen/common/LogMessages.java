package com.newgen.common;

/**
 * @author shivaraj
 **/

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogMessages {
    public static final Logger errorLog = Logger.getLogger("ErrorLog");
    public static final Logger statusLog = Logger.getLogger("StatusLog");
    public static final Logger xmlLog = Logger.getLogger("ErrorLogLoans");
    public static final Logger errorLogLoans = Logger.getLogger("StatusLogLoans");
    public static final Logger statusLogLoans = Logger.getLogger("StatusLogLoans");
    public static final Logger templateLog = Logger.getLogger("TemplateLog");

    private static final String LINE_SEPARATOR = "==============================================================";

    private LogMessages() {

    }

    static {
        try {
            Properties pLog4j = new Properties();

            String filePath = System.getProperty("user.dir") + "/Config/Log4j.properties";
            File log4jPropFile = new File(filePath);

            FileInputStream fis = new FileInputStream(log4jPropFile);
            pLog4j.load(fis);

            PropertyConfigurator.configure(pLog4j);
            dumpInitialLog();
        } catch (Exception e) {
            errorLog.info("Exception in creating dynamic log :" + e);
        }
    }

    private static void dumpInitialLog() {
        errorLog.info(LINE_SEPARATOR);
        errorLog.info("Error Log Initialized for Customization Module");
        errorLog.info(LINE_SEPARATOR);

        statusLog.info(LINE_SEPARATOR);
        statusLog.info("Status Log Initialized for Customization Module");
        statusLog.info(LINE_SEPARATOR);

        xmlLog.info(LINE_SEPARATOR);
        xmlLog.info("XML Log Initialized for Customization Module");
        xmlLog.info(LINE_SEPARATOR);
    }
}
