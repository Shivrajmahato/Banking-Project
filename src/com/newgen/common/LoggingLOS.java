package com.newgen.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class LoggingLOS implements Serializable {

    public void serviceLog(String strLog) {

        String sLogFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "SME_Custom_Logs";

        File objDirs = new File(sLogFilePath);
        if (!objDirs.exists()) {
            objDirs.mkdirs();
        }

        GregorianCalendar cal = new GregorianCalendar();
        int logCount = 0;
        sLogFilePath = sLogFilePath + System.getProperty("file.separator") + "CustomPurgeUtil_" + cal.get(5) + "-" + (cal.get(2) + 1) + "-" + cal.get(1) + "_" + logCount + ".log";

        File logfile = new File(sLogFilePath);
        if (logfile.length() > 5242880L) {
            logCount++;
        }
        try (FileOutputStream fos = new FileOutputStream(sLogFilePath, true);
             PrintWriter pw = new PrintWriter(fos);) {
            pw.println("................................................................");
            pw.print(new Date() + "  ");
            pw.println(strLog);

        } catch (Exception ex) {
            LogMessages.statusLog.info("Error in creating log " + ex.getMessage());
        }
    }

}