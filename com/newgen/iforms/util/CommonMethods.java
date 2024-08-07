package com.newgen.iforms.util;

/**
 *
 * @author bibek.shah
 * @author jenish.tamrakar
 *
 **/

import ISPack.CPISDocumentTxn;
import ISPack.ISUtil.JPDBRecoverDocData;
import ISPack.ISUtil.JPISException;
import ISPack.ISUtil.JPISIsIndex;
import com.newgen.common.ConfProperty;
import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.niplj.generic.NGIMException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class CommonMethods extends UtilConstants
  implements Serializable
{
  TemplateUtil templateUtil = new TemplateUtil();
  String iNoOfPages = ""; String addDocumentInputXML = ""; String sessionid = ""; String isIndex = "";
  XMLGeneration xmlgen = new XMLGeneration();

  public static String DateConverter(String inputDate) {
        String outputDate = "";
       
        if (inputDate == null || inputDate.equalsIgnoreCase("")) {
            return outputDate = "";
        } else {
            DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
            DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                Date date = inputFormat.parse(inputDate);
                outputDate = outputFormat.format(date);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CommonMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            ConfProperty.WriteToLog("Output Date::::: " + outputDate);
            return outputDate;
        }
    }
  public String generateDocument(IFormReference ifr, String docType, String pid, String serverIp, String serverPort, String cabinetName)
    throws NGIMException, Exception
  {
    boolean gtSuccess = false;
    String addDocOutXML = "";
    LogMessages.templateLog.info("Inside generateDocument function...");

    String destinationPath = "";
    try {
      LogMessages.templateLog.info("Inside try catch...");
      com.newgen.omni.wf.util.xml.XMLParser generalDataParser = null;
      LogMessages.templateLog.info("after new function...");
      GenerateTemplate gt = new GenerateTemplate();
      LogMessages.templateLog.info("Document type is ----" + docType);
      destinationPath = gt.generateTemplate(ifr, generalDataParser, docType, pid);
      LogMessages.templateLog.info(destinationPath);
      LogMessages.templateLog.info("gtSuccess -->" + gtSuccess);
    }
    catch (Exception e) {
      LogMessages.templateLog.info("Error while generating document...", e);
    }

    String docIn = "";
    String docIndex = "";
    try {
        if ((destinationPath != null) && (destinationPath != ""))
        {
            LogMessages.templateLog.info("idIndex: " + this.isIndex);

            List folderIndex = ifr.getDataFromDB("select folderindex from pdbfolder WITH(NOLOCK) where name = '" + pid + "'");

            LogMessages.templateLog.info("idIndex: " + this.isIndex);
            this.sessionid = this.xmlgen.getSessionID(cabinetName, serverIp, serverPort, "padmin", "system123#");
            LogMessages.templateLog.info("session ID: " + this.sessionid);
            LogMessages.templateLog.info("idIndex: " + this.isIndex);
            LogMessages.templateLog.info("FOLDER ID: " + folderIndex);
            LogMessages.templateLog.info("folder index: " + (String)((List)folderIndex.get(0)).get(0));

            String qry = "";
            if (docType.equalsIgnoreCase("CATemplate")) {
                qry = "Select docIndex_CATemp from CA_TEMPLATE_DATA WITH(NOLOCK) where TransactionID = '" + pid + "'";
                LogMessages.templateLog.info("Inside CATemplate");
            }
            else if(docType.equalsIgnoreCase("RetailApprovalSummary_Individual_template"))
            {
                qry = "Select docIndex_RetailAS_INDIVIDUALTemp from RetailAS_INDIVIDUAL_TEMPLATE WITH(NOLOCK) where TransactionID = '"+ pid +"'";
                LogMessages.templateLog.info("Inside RetailApprovalSummary_Individual_template");
            }
            else if(docType.equalsIgnoreCase("RetailApprovalSummary_Institution_template"))
            {
                qry = "Select docIndex_RetailAS_INSTITUTIONTemp from RetailAS_INSTITUTION_TEMPLATE WITH(NOLOCK) where TransactionID = '"+ pid +"'";
                LogMessages.templateLog.info("Inside RetailApprovalSummary_Institution_template");
            }

            List docIndex_list = ifr.getDataFromDB(qry);
            LogMessages.templateLog.info("QRY::: " + qry);
            docIndex = ((String)((List)docIndex_list.get(0)).get(0)).toString();
            LogMessages.templateLog.info("docIndex_list::: " + docIndex_list);
            LogMessages.templateLog.info("documentIndex: " + docIndex);

            if (!docIndex.equalsIgnoreCase("")) {
                String delDocumentInputXML = XMLGeneration.get_NGODeleteDocument_Input(cabinetName, this.sessionid, docIndex, (String)((List)folderIndex.get(0)).get(0));
                LogMessages.templateLog.info("delDocumentInputXML:: " + delDocumentInputXML);
                String delDocOutXML = XMLGeneration.WFNGExecute(delDocumentInputXML, serverIp, Integer.parseInt(serverPort), 1);
                LogMessages.templateLog.info("DELETE DOC output XML:: " + delDocOutXML);
            }

            this.isIndex = addDocToSMS(destinationPath, serverIp, serverPort, cabinetName);
            if ((this.sessionid.equalsIgnoreCase("")) || (this.sessionid.equalsIgnoreCase("null")) || (this.sessionid.toLowerCase().contains("Error".toLowerCase()))) {
                LogMessages.templateLog.info("Unable to connect to server, some problem occurred.");
            }
            else {
                Path path = Paths.get(destinationPath, new String[0]);
                try {
                    this.iNoOfPages = this.templateUtil.getPDBDocTypeAndNoOfPages(destinationPath);
                }
                catch (Exception ex) {
                 LogMessages.templateLog.info("No of pages::  iNoOfPages" + ex);
                }LogMessages.templateLog.info("No of pages:: " + this.iNoOfPages);

                this.addDocumentInputXML = XMLGeneration.get_NGOAddDocument_Input(cabinetName, this.sessionid, (String)((List)folderIndex.get(0)).get(0), Integer.parseInt(this.iNoOfPages), docType, "N", Files.size(path) + "", destinationPath.substring(destinationPath.lastIndexOf(".") + 1), this.isIndex);

                LogMessages.templateLog.info("addDOC input XML:: " + this.addDocumentInputXML);
                addDocOutXML = XMLGeneration.WFNGExecute(this.addDocumentInputXML, serverIp, Integer.parseInt(serverPort), 1);
                LogMessages.templateLog.info("ADD DOC output XML:: " + addDocOutXML);
                XMLParser xmlParserData = new XMLParser();
                xmlParserData.setInputXML(addDocOutXML);
                docIn = xmlParserData.getValueOf("DocumentIndex").toString();
                int status = 0;
                if (docType.equalsIgnoreCase("CATemplate")) {
                    LogMessages.templateLog.info("Inside CAtemplatestatius");
                    status = ifr.saveDataInDB("UPDATE CA_TEMPLATE_DATA SET docIndex_CATemp = '" + docIn + "' WHERE TransactionID = '" + pid + "'");
                }
                else if(docType.equalsIgnoreCase("RetailApprovalSummary_Individual_template"))
                {
                    LogMessages.templateLog.info("Inside RetailApprovalSummary_Individual_template");
                    status = ifr.saveDataInDB("UPDATE RetailAS_INDIVIDUAL_TEMPLATE SET docIndex_RetailAS_INDIVIDUALTemp = '"+docIn+"' WHERE TransactionID = '"+ pid +"'");
                }
                else if(docType.equalsIgnoreCase("RetailApprovalSummary_Institution_template"))
                {
                    LogMessages.templateLog.info("Inside RetailApprovalSummary_Institution_template");
                    status = ifr.saveDataInDB("UPDATE RetailAS_INSTITUTION_TEMPLATE SET docIndex_RetailAS_INSTITUTIONTemp = '"+docIn+"' WHERE TransactionID = '"+ pid +"'");
                LogMessages.templateLog.info("STATUS OF UPDATE QRY" + status);
                }
            }
        } else {
            LogMessages.templateLog.info("Path of destination not received!!");
        }
    }
    catch (Exception e) {
      LogMessages.templateLog.info("Error in uploading of the document!!!:: ", e);
    }

    return addDocOutXML + "@" + docIn + "@" + docIndex;
  }

  public String connectToSocket(String inputMessage, String socketType)
  {
    try
    {
      Socket s;
      if ("webserviceSocket".equalsIgnoreCase(socketType))
        s = new Socket("127.0.0.1", 4446);
      else
        s = new Socket("127.0.0.1", 4444);
      LogMessages.templateLog.info("Socket Conected.......^^^" + s);
      DataInputStream din = new DataInputStream(s.getInputStream());
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      LogMessages.templateLog.info("DATA to writeDataToSocket....^^" + dout);
      writeDataToSocket(dout, inputMessage);
      s.setSoTimeout(10000);
      return readDataFromSocket(din);
    }
    catch (Exception e) {
      LogMessages.templateLog.info("Error in connectToSocket!!:: ", e);
    }return "Socket time out exception";
  }

  public boolean writeDataToSocket(DataOutputStream dOut, String sData)
  {
    boolean bFlag = false;
    LogMessages.templateLog.info("Data to Write from client to server: " + sData);
    try {
      LogMessages.templateLog.info("Inside writeDataToSocket...TRY");
      if ((sData != null) && (sData.length() > 0)) {
        String len = String.format("%09d", new Object[] { Integer.valueOf(sData.length()) });

        dOut.write(len.getBytes("UTF-8"));
        dOut.write(sData.getBytes("UTF-8"));
        bFlag = true;
      } else {
        LogMessages.templateLog.info("sRequest is Blank.");
      }
    } catch (Exception e) {
      LogMessages.templateLog.info("Exception while writing data to socket server:", e);
      bFlag = false;
    }
    return bFlag;
  }

  public String readDataFromSocket(DataInputStream dIn) {
    LogMessages.templateLog.info("readDataFromSocket readDataFromSocket :: " + new Date().getTime());
    String sData = "";
    try {
      byte[] buffer = new byte[9];
      dIn.read(buffer, 0, 9);
      int iFile = Integer.parseInt(new String(buffer, "UTF-8"));
      buffer = new byte[iFile];
      int len = 0;
      int reclen = 0;
      while ((len = dIn.read(buffer)) > 0) {
        LogMessages.templateLog.info("Inside while loop ::");
        byte[] arrayBytes = new byte[len];
        System.arraycopy(buffer, 0, arrayBytes, 0, len);
        sData = sData + new String(arrayBytes, "UTF-8");
        reclen += len;
        if (reclen >= iFile)
          break;
      }
      LogMessages.templateLog.info("sData.....^^^... " + sData);
    } catch (Exception e) {
      e.printStackTrace();
      LogMessages.templateLog.info("Error:" + e);
    }
    LogMessages.templateLog.info("Data Received from Socket Server: " + sData);
    return sData;
  }

  public String addDocToSMS(String filePath, String sIPAddress, String sJTSPort, String sCabinetName) {
    LogMessages.templateLog.info("*******************************Inside addDocToSMS()*******************************");
    try
    {
      LogMessages.templateLog.info("sIPAddress = " + sIPAddress);
      LogMessages.templateLog.info("sJTSPort = " + sJTSPort);
      LogMessages.templateLog.info("sCabinetName = " + sCabinetName);
      LogMessages.templateLog.info("Successfully Read Server Properties File");
    }
    catch (Exception ex) {
      LogMessages.templateLog.info("Error in Reading Server Properties File");
      return null;
    }

    String FullPath = filePath;
    String simgVolId1 = "";
    String simgDocId = "";
    JPISIsIndex isIndex = new JPISIsIndex();
    LogMessages.templateLog.info("isIndex = " + isIndex);
    try {
      JPDBRecoverDocData docDBData = new JPDBRecoverDocData();

      LogMessages.templateLog.info("sIPAddress = " + sIPAddress);
      LogMessages.templateLog.info("sJTSPort = " + sJTSPort);
      LogMessages.templateLog.info("sCabinetName = " + sCabinetName);
      LogMessages.templateLog.info("Short.parseShort(\"1\") = " + Short.parseShort("1"));
      LogMessages.templateLog.info("FullPath = " + FullPath);
      LogMessages.templateLog.info("docDBData = " + docDBData);
      CPISDocumentTxn.AddDocument_MT(null, sIPAddress, Short.parseShort(sJTSPort), sCabinetName, Short.parseShort("1"), FullPath, docDBData, null, isIndex);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      LogMessages.templateLog.info(ex);
    } catch (JPISException ex) {
      LogMessages.templateLog.info(ex);
    }

    simgVolId1 = simgVolId1 + isIndex.m_sVolumeId;
    simgDocId = simgDocId + isIndex.m_nDocIndex;
    String sISIndex = simgDocId + "#" + simgVolId1;
    LogMessages.templateLog.info("Printing values of objects");
    LogMessages.templateLog.info("simgVolId1 " + simgVolId1);
    LogMessages.templateLog.info("simgDocId " + simgDocId);

    LogMessages.templateLog.info("ISIndex of Document : " + sISIndex);
    LogMessages.templateLog.info("Document is successfully added in SMS server from location" + filePath);
    return sISIndex;
  }
}