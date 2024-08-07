package com.newgen.iforms.util;

/**
 * @author bibek.shah
 * @author jenish.tamrakar
 **/

import ISPack.CPISDocumentTxn;
import ISPack.ISUtil.JPDBRecoverDocData;
import ISPack.ISUtil.JPISException;
import ISPack.ISUtil.JPISIsIndex;
import com.newgen.common.ConfProperty;
import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class CommonMethods implements Serializable {

    public static final String WHERE_TRANSACTION_ID = "' WHERE TransactionID = '";
    String isIndex = "";

    public static String dateConverter(String inputDate) {
        String outputDate = "";
        if (inputDate != null && !inputDate.equalsIgnoreCase("")) {
            try {
                DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
                DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");
                Date date = inputFormat.parse(inputDate);
                outputDate = outputFormat.format(date);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CommonMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
            ConfProperty.writeToLog("Output Date::::: " + outputDate);
        }
        return outputDate;
    }

    public String generateDocument(IFormReference ifr, String docType, String pid, String serverIp, String serverPort, String cabinetName) {
        boolean gtSuccess = false;
        String addDocOutXML = "";
        LogMessages.templateLog.info("Inside generateDocument function...");

        String destinationPath = "";
        try {
            LogMessages.templateLog.info("Inside try catch...");
            LogMessages.templateLog.info("after new function...");
            GenerateTemplate gt = new GenerateTemplate();
            LogMessages.templateLog.info("Document type is ----" + docType);
            destinationPath = gt.generateTemplate(ifr, docType, pid);
            LogMessages.templateLog.info(destinationPath);
            LogMessages.templateLog.info("gtSuccess -->" + gtSuccess);
        } catch (Exception e) {
            LogMessages.templateLog.info("Error while generating document...", e);
        }

        String docIn = "";
        String docIndex = "";
        String sessionId = "";
        try {
            if ((destinationPath != null) && (!destinationPath.equals(""))) {
                LogMessages.templateLog.info("idIndex: " + this.isIndex);

                List<String> folderIndex = ifr.getDataFromDB("select folderindex from pdbfolder WITH(NOLOCK) where name = '" + pid + "'");
                XMLGeneration xmlGen = new XMLGeneration();
                sessionId = xmlGen.getSessionID(cabinetName, serverIp, serverPort, "padmin", "system123#");
                LogMessages.templateLog.info("session ID: " + sessionId);
                LogMessages.templateLog.info("FOLDER ID: " + folderIndex);
                LogMessages.templateLog.info("folder index: " + folderIndex.get(0));

                String qry = getDocIndexQuery(docType, pid);

                List<String> docIndexList = ifr.getDataFromDB(qry);
                LogMessages.templateLog.info("QRY::: " + qry);
                docIndex = docIndexList.get(0);
                LogMessages.templateLog.info("docIndex_list::: " + docIndexList);
                LogMessages.templateLog.info("documentIndex: " + docIndex);

                if (!docIndex.equalsIgnoreCase("")) {
                    String delDocumentInputXML = XMLGeneration.getNGODeleteDocumentInput(cabinetName, sessionId, docIndex, folderIndex.get(0));
                    LogMessages.templateLog.info("delDocumentInputXML:: " + delDocumentInputXML);
                    String delDocOutXML = XMLGeneration.wfngExecute(delDocumentInputXML, serverIp, Integer.parseInt(serverPort));
                    LogMessages.templateLog.info("DELETE DOC output XML:: " + delDocOutXML);
                }

                this.isIndex = addDocToSMS(destinationPath, serverIp, serverPort, cabinetName);
                if ((sessionId.equalsIgnoreCase("")) || (sessionId.equalsIgnoreCase("null")) || (sessionId.toLowerCase().contains("Error".toLowerCase()))) {
                    LogMessages.templateLog.info("Unable to connect to server, some problem occurred.");
                } else {
                    TemplateUtil templateUtil = new TemplateUtil();
                    String iNoOfPages = templateUtil.getPDBDocTypeAndNoOfPages(destinationPath);
                    LogMessages.templateLog.info("No of pages:: " + iNoOfPages);

                    Path path = Paths.get(destinationPath, "");

                    NGOAddDocumentInputData ngoAddDocumentInputData = buildNgoAddDocumentInputData(docType, cabinetName, destinationPath, sessionId, folderIndex, iNoOfPages, path);
                    String addDocumentInputXML =  XMLGeneration.getNGOAddDocumentInput(ngoAddDocumentInputData);

                    LogMessages.templateLog.info("addDOC input XML:: " + addDocumentInputXML);
                    addDocOutXML = XMLGeneration.wfngExecute(addDocumentInputXML, serverIp, Integer.parseInt(serverPort));
                    LogMessages.templateLog.info("ADD DOC output XML:: " + addDocOutXML);
                    XMLParser xmlParserData = new XMLParser();
                    xmlParserData.setInputXML(addDocOutXML);
                    docIn = xmlParserData.getValueOf("DocumentIndex");
                    int status = getStatusFromUpdateQuery(ifr, docType, pid, docIn);
                    LogMessages.templateLog.info("STATUS OF UPDATE QRY" + status);
                }
            } else {
                LogMessages.templateLog.info("Path of destination not received!!");
            }
        } catch (Exception e) {
            LogMessages.templateLog.info("Error in uploading of the document!!!:: ", e);
        }

        return addDocOutXML + "@" + docIn + "@" + docIndex;
    }

    private NGOAddDocumentInputData buildNgoAddDocumentInputData(String docType, String cabinetName, String destinationPath, String sessionId, List<String> folderIndex, String iNoOfPages, Path path) throws IOException {
        NGOAddDocumentInputData ngoAddDocumentInputData = new NGOAddDocumentInputData();
        ngoAddDocumentInputData.setCabinetName(cabinetName);
        ngoAddDocumentInputData.setSessionID(sessionId);
        ngoAddDocumentInputData.setParentFolderIndex(folderIndex.get(0));
        ngoAddDocumentInputData.setNoOfPages(Integer.parseInt(iNoOfPages));
        ngoAddDocumentInputData.setsDocumentFullName("N");
        ngoAddDocumentInputData.setDocType(docType);
        ngoAddDocumentInputData.setDocSize(Files.size(path) + "");
        ngoAddDocumentInputData.setCreatedByAppName(destinationPath.substring(destinationPath.lastIndexOf(".") + 1));
        ngoAddDocumentInputData.setIsIndex(this.isIndex);
        return ngoAddDocumentInputData;
    }

    private int getStatusFromUpdateQuery(IFormReference ifr, String docType, String pid, String docIn) {
        int status = 0;
        if (docType.equalsIgnoreCase("CATemplate")) {
            LogMessages.templateLog.info("Inside CAtemplatestatius");
            status = ifr.saveDataInDB("UPDATE CA_TEMPLATE_DATA SET docIndex_CATemp = '" + docIn + WHERE_TRANSACTION_ID + pid + "'");
        } else if (docType.equalsIgnoreCase("RetailApprovalSummary_Individual_template")) {
            LogMessages.templateLog.info("Inside RetailApprovalSummary_Individual_template");
            status = ifr.saveDataInDB("UPDATE RetailAS_INDIVIDUAL_TEMPLATE SET docIndex_RetailAS_INDIVIDUALTemp = '" + docIn + WHERE_TRANSACTION_ID + pid + "'");
        } else if (docType.equalsIgnoreCase("RetailApprovalSummary_Institution_template")) {
            LogMessages.templateLog.info("Inside RetailApprovalSummary_Institution_template");
            status = ifr.saveDataInDB("UPDATE RetailAS_INSTITUTION_TEMPLATE SET docIndex_RetailAS_INSTITUTIONTemp = '" + docIn + WHERE_TRANSACTION_ID + pid + "'");
        }
        return status;
    }

    private String getDocIndexQuery(String docType, String pid) {
        String qry = "";
        if (docType.equalsIgnoreCase("CATemplate")) {
            qry = "Select docIndex_CATemp from CA_TEMPLATE_DATA WITH(NOLOCK) where TransactionID = '" + pid + "'";
            LogMessages.templateLog.info("Inside CATemplate");
        } else if (docType.equalsIgnoreCase("RetailApprovalSummary_Individual_template")) {
            qry = "Select docIndex_RetailAS_INDIVIDUALTemp from RetailAS_INDIVIDUAL_TEMPLATE WITH(NOLOCK) where TransactionID = '" + pid + "'";
            LogMessages.templateLog.info("Inside RetailApprovalSummary_Individual_template");
        } else if (docType.equalsIgnoreCase("RetailApprovalSummary_Institution_template")) {
            qry = "Select docIndex_RetailAS_INSTITUTIONTemp from RetailAS_INSTITUTION_TEMPLATE WITH(NOLOCK) where TransactionID = '" + pid + "'";
            LogMessages.templateLog.info("Inside RetailApprovalSummary_Institution_template");
        }
        return qry;
    }

    public String addDocToSMS(String filePath, String sIPAddress, String sJTSPort, String sCabinetName) {
        LogMessages.templateLog.info("*******************************Inside addDocToSMS()*******************************");
        try {
            LogMessages.templateLog.info("sIPAddress = " + sIPAddress);
            LogMessages.templateLog.info("sJTSPort = " + sJTSPort);
            LogMessages.templateLog.info("sCabinetName = " + sCabinetName);
            LogMessages.templateLog.info("Successfully Read Server Properties File");
        } catch (Exception ex) {
            LogMessages.templateLog.info("Error in Reading Server Properties File");
            return null;
        }

        String fullPath = filePath;
        String simgVolId1 = "";
        String simgDocId = "";
        JPISIsIndex jpisIsIndex = new JPISIsIndex();
        LogMessages.templateLog.info("isIndex = " + isIndex);
        try {
            JPDBRecoverDocData docDBData = new JPDBRecoverDocData();

            LogMessages.templateLog.info("sIPAddress = " + sIPAddress);
            LogMessages.templateLog.info("sJTSPort = " + sJTSPort);
            LogMessages.templateLog.info("sCabinetName = " + sCabinetName);
            LogMessages.templateLog.info("Short.parseShort(\"1\") = " + Short.parseShort("1"));
            LogMessages.templateLog.info("fullPath = " + fullPath);
            LogMessages.templateLog.info("docDBData = " + docDBData);
            CPISDocumentTxn.AddDocument_MT(null, sIPAddress, Short.parseShort(sJTSPort), sCabinetName, Short.parseShort("1"), fullPath, docDBData, null, jpisIsIndex);
        } catch (Exception ex) {
            LogMessages.templateLog.info(ex);
        } catch (JPISException ex) {
            LogMessages.templateLog.info(ex);
        }

        simgVolId1 = simgVolId1 + jpisIsIndex.m_sVolumeId;
        simgDocId = simgDocId + jpisIsIndex.m_nDocIndex;
        String sISIndex = simgDocId + "#" + simgVolId1;
        LogMessages.templateLog.info("Printing values of objects");
        LogMessages.templateLog.info("simgVolId1 " + simgVolId1);
        LogMessages.templateLog.info("simgDocId " + simgDocId);

        LogMessages.templateLog.info("ISIndex of Document : " + sISIndex);
        LogMessages.templateLog.info("Document is successfully added in SMS server from location" + filePath);
        return sISIndex;
    }
}