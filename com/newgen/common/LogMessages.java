package com.newgen.common;

/**
 *
 * @author bibek.shah
 *
 **/

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogMessages
{
  public static Logger errorLog = null;
  public static Logger statusLog = null;
  public static Logger xmlLog = null;
  public static Logger errorLogLoans = null;
  public static Logger statusLogLoans = null;
  public static Logger xmlLogLoans = null;
  public static Logger templateLog = null;

  private static long lastModifiedTimeStamp = -1L;
  private static final String action = "==============================================================";
  private static String dirPath = System.getProperty("user.dir");
  public static final String integrationFilePath = dirPath + "/NABILConfig/Integration.properties";

  public static long getLastModifiedTimeStamp()
  {
    return lastModifiedTimeStamp;
  }

  static
  {
    String userDir = "";
    String filePath = "";
    try
    {
      Properties pLog4j = new Properties();
      userDir = System.getProperty("user.dir");

      filePath = userDir + "/NABILConfig/Log4j.properties";

      File log4jPropFile = new File(filePath);

      FileInputStream fis = new FileInputStream(log4jPropFile);
      pLog4j.load(fis);

      PropertyConfigurator.configure(pLog4j);

      errorLog = Logger.getLogger("ErrorLog");
      statusLog = Logger.getLogger("StatusLog");
      xmlLog = Logger.getLogger("XMLLog");
      errorLogLoans = Logger.getLogger("ErrorLogLoans");
      statusLogLoans = Logger.getLogger("StatusLogLoans");
      xmlLogLoans = Logger.getLogger("XMLLogLoans");
      templateLog = Logger.getLogger("TemplateLog");
      lastModifiedTimeStamp = new File(filePath).lastModified();

      dumpinitialLog();
    }
    catch (Exception e) {
      errorLog.info("Exception in creating dynamic log :" + e);
    }
  }

private static void dumpinitialLog()
  {
    errorLog.info("==============================================================");
    errorLog.info("Error Log Initialized for Customization Module");
    errorLog.info("==============================================================");

    statusLog.info("==============================================================");
    statusLog.info("Status Log Initialized for Customization Module");
    statusLog.info("==============================================================");
												
							   
  

    xmlLog.info("==============================================================");
    xmlLog.info("XML Log Initialized for Customization Module");
    xmlLog.info("==============================================================");
  }
}
