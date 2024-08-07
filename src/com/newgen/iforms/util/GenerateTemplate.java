/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.util;

import com.newgen.aproj2.template.BusinessDataVO;
import com.newgen.aproj2.template.BusinessValidation;
import com.newgen.aproj2.template.ReadXmlData;
//import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.*;

public class GenerateTemplate implements Serializable {

    public static final String USER_DIR = "user.dir";
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String PRE_GENERATED_TEMPLATES = "preGeneratedtemplates";
    public static final String TEMPLATE_GENERATION = "TemplateGeneration";
    public static final String GENERATED_TEMPLATES = "generatedTemplates";
    public static final String TEMPLATE = "Template";
    private Map<String, String> currentServiceProperty = new HashMap<>();
    private Map<String, String> ht = new HashMap<>();

    public void readCabProperty(String sectionName) {
        currentServiceProperty.clear();
        String ini = System.getProperty(USER_DIR) + System.getProperty(FILE_SEPARATOR) + TEMPLATE_GENERATION
                + System.getProperty(FILE_SEPARATOR) + PRE_GENERATED_TEMPLATES + System.getProperty(FILE_SEPARATOR)
                + TEMPLATE + ".ini";
        LogMessages.templateLog.info("ini---- " + ini);
        LogMessages.templateLog.info("sectionName---- " + sectionName);

        try (RandomAccessFile raf = new RandomAccessFile(ini, "rw");) {
            String line;
            boolean sectionFound = false;

            while ((line = raf.readLine()) != null) {
                line = line.trim();

                if (line.charAt(0) == '[' || sectionFound) {
                    break; // Exit the loop if we've already found the section
                }

                if (!line.equalsIgnoreCase("") || (line.charAt(line.length() - 1) == ']')) {
                    int i = line.indexOf('=');
                    String temp = line.substring(0, i);

                    if (line.substring(1, line.length() - 1).equalsIgnoreCase(sectionName)) {
                        currentServiceProperty.put(temp.trim(), line.substring(i + 1));
                        sectionFound = true; // Mark the section as found
                    }
                }
            }
        } catch (Exception e) {
            LogMessages.templateLog.info("ReadCabProperty Exception in GenerateTemplate::", e);
        }
    }


    public String generateTemplate(IFormReference ifr, String templateName, String pid) {

        LogMessages.templateLog.info("Inside generateTemplate function...");
        String templatePath = "";
        String destinationPath = "";
        String extTblName = "";
        boolean readStatus = false;
        try {
            LogMessages.templateLog.info("Process -->" + ifr.getProcessName());
            readCabProperty(templateName);
            String documentName = currentServiceProperty.get("DOCUMENTNAME");
            templateName = currentServiceProperty.get("XMLFILENAME");
            extTblName = currentServiceProperty.get("EXTTABLENAME");
            LogMessages.templateLog.info("documentname from INI -->" + documentName + ":template name from INI -->"
                    + templateName + ":extTblName from INI -->" + extTblName);
            templatePath = System.getProperty(USER_DIR) + System.getProperty(FILE_SEPARATOR)
                    + TEMPLATE_GENERATION + System.getProperty(FILE_SEPARATOR) + PRE_GENERATED_TEMPLATES
                    + System.getProperty(FILE_SEPARATOR) + TEMPLATE + System.getProperty(FILE_SEPARATOR)
                    + documentName + "." + "docx";
            LogMessages.templateLog.info("template path ----- -->" + templatePath);
            destinationPath = System.getProperty(USER_DIR) + System.getProperty(FILE_SEPARATOR)
                    + TEMPLATE_GENERATION + System.getProperty(FILE_SEPARATOR) + GENERATED_TEMPLATES
                    + System.getProperty(FILE_SEPARATOR) + pid;
            LogMessages.templateLog.info("destination generated path ----- -->" + destinationPath);
            File dirpath = new File(destinationPath);
            if (!dirpath.isDirectory()) {
                dirpath.mkdir();
            }
            destinationPath = System.getProperty(USER_DIR) + System.getProperty(FILE_SEPARATOR)
                    + TEMPLATE_GENERATION + System.getProperty(FILE_SEPARATOR) + GENERATED_TEMPLATES
                    + System.getProperty(FILE_SEPARATOR) + pid + System.getProperty(FILE_SEPARATOR)
                    + documentName + "." + "docx";
            LogMessages.templateLog.info("destination  path ----- -->" + destinationPath);
            String finalPDFPath = System.getProperty(USER_DIR) + System.getProperty(FILE_SEPARATOR)
                    + TEMPLATE_GENERATION + System.getProperty(FILE_SEPARATOR) + GENERATED_TEMPLATES
                    + System.getProperty(FILE_SEPARATOR) + pid + System.getProperty(FILE_SEPARATOR)
                    + documentName + "." + "pdf";
            LogMessages.templateLog.info("pdf destination  path ----- -->" + finalPDFPath);
            readStatus = true;
        } catch (Exception exp) {
            LogMessages.templateLog.info("Error in reading the Property File of GenerateTemplate :=", exp);
        }

        if (readStatus) {
            ReadXmlData readxmldata = new ReadXmlData();
            String xmlFile = System.getProperty(USER_DIR) + System.getProperty(FILE_SEPARATOR)
                    + TEMPLATE_GENERATION + System.getProperty(FILE_SEPARATOR) + PRE_GENERATED_TEMPLATES
                    + System.getProperty(FILE_SEPARATOR) + TEMPLATE + System.getProperty(FILE_SEPARATOR)
                    + TEMPLATE + "_" + templateName + ".xml";
            LogMessages.templateLog.info(xmlFile);
            if (readxmldata.readData(xmlFile)) {
                LogMessages.templateLog.info("Read External table Xml contents into ArrayList");
                ArrayList<BusinessDataVO> arrExtlist = readxmldata.getXmlDataRecords();
                validateData(ifr, arrExtlist, pid, extTblName);
                boolean bReturn = generateDoc(ht, templatePath, destinationPath, ifr, pid);
                LogMessages.templateLog.info("bReturn = " + bReturn);
                if (bReturn) {
                    LogMessages.templateLog.info("Template Generated Successfully = " + bReturn);
                    LogMessages.templateLog.info("Return path:: " + destinationPath);
                } else {
                    LogMessages.templateLog.info("Template generation failed");
                    destinationPath = "";
                }
            } else {
                LogMessages.templateLog.info("Error in reading Xml file");
                destinationPath = "";
            }

        }
        return destinationPath;
    }

    private boolean validateData(IFormReference ifr, ArrayList<BusinessDataVO> alRecordsValidation, String swiName, String mstTblName) {
        LogMessages.templateLog.info("1-->");
        boolean bReturn = false;

        try {
            String scolnames = "";
            String sorgcolnames = "";
            String stempcolnames = "";
            String orgFinalcolnames = "";
            String tempFinalcolnames = "";

            LogMessages.templateLog.info("2-->" + alRecordsValidation);
            LogMessages.templateLog.info("3-->");
            for (int i = 0; i < alRecordsValidation.size(); i++) {
                BusinessDataVO dc = alRecordsValidation.get(i);
                String extName = dc.getExtName();
                String tempName = dc.getTempName();

                sorgcolnames = sorgcolnames.concat(extName).concat(",");
                orgFinalcolnames = sorgcolnames.substring(0, sorgcolnames.lastIndexOf(","));

                stempcolnames = stempcolnames.concat(tempName).concat(",");
                tempFinalcolnames = stempcolnames.substring(0, stempcolnames.lastIndexOf(","));

                if (extName.equalsIgnoreCase("GETDATE()")) {
                    extName = "convert(varchar, " + extName + ", 103) AS SYS_DATE";
                }
                scolnames = scolnames.concat(extName).concat(",");
            }
            String finalColnames = scolnames.substring(0, scolnames.lastIndexOf(","));
            finalColnames = "DISTINCT  " + finalColnames;
            BusinessValidation bv = new BusinessValidation();
            String queryResult = bv.DynamicQuery(finalColnames, swiName, "test", mstTblName);

            queryResult = queryResult.replace("wi_name", "transactionid");

            LogMessages.templateLog.info("getNGDataFromDataCache skoutputXml queryResult := " + queryResult);

            List<List<String>> outputXml = ifr.getDataFromDB(queryResult);
            LogMessages.templateLog.info("getNGDataFromDataCache skoutputXml := " + outputXml);

            if (!outputXml.isEmpty()) {
                String[] arrcol = orgFinalcolnames.split(",");
                String[] arrtempcol = tempFinalcolnames.split(",");
                LogMessages.templateLog.info("getNGDataFromDataCache arrcol := " + arrcol);
                LogMessages.templateLog.info("getNGDataFromDataCache arrtempcol := " + arrtempcol);
                for (List<String> viewList : outputXml) {
                    for (int i = 0; i < arrcol.length; i++) {
                        parseViewList(arrtempcol, viewList, i);
                    }
                    bReturn = true;
                }
            } else {
                LogMessages.templateLog.info("getNGDataFromDataCache skoutputXml is emty := " + outputXml);
            }

        } catch (Exception ex) {
            bReturn = false;
            LogMessages.templateLog.info("Error during External table field Validations.", ex);
        }
        return bReturn;
    }

    private void parseViewList(String[] arrtempcol, List<String> viewList, int i) {
        try {
            if (!viewList.get(i).equalsIgnoreCase("--Select--")) {
                ht.put("" + arrtempcol[i] + "", viewList.get(i));
            } else {
                ht.put("" + arrtempcol[i] + "", "");
            }
        } catch (NullPointerException e) {
            ht.put("" + arrtempcol[i] + "", "");
        } catch (Exception e2) {
            LogMessages.templateLog
                    .info("Some Exception Occurred While parsing retrieved DB data from List.....", e2);
        }
    }

    private boolean generateDoc(Map<String, String> ht, String templatePathFilename, String outputPathFilename,
                                IFormReference ifr, String pid) {
        boolean bReturn = false;

        try {
            LogMessages.templateLog.info("template file path file name in generate doc" + templatePathFilename);
            XWPFDocument xwpfDoc = new XWPFDocument(OPCPackage.open(templatePathFilename));
            LogMessages.templateLog.info("after open" + xwpfDoc);
            if (!ht.isEmpty()) {
                xwpfDoc = replaceBodyContent(xwpfDoc);
                xwpfDoc = replaceComplexDataContent(xwpfDoc, ifr, pid);
                xwpfDoc.write(new FileOutputStream(outputPathFilename));
                bReturn = true;
            }
        } catch (IOException | InvalidFormatException e) {
            LogMessages.templateLog.info("--------------------->>>>>>>>>.catch exception in generate doc  io", e);
        }
        return bReturn;
    }

    private XWPFDocument replaceComplexDataContent(XWPFDocument doc, IFormReference ifr, String pid) {

        try {
            String tableIdView = currentServiceProperty.get("tableIdView");
            String tableViewColumns = currentServiceProperty.get("TABLEVIEWCOLUMNS");
            LogMessages.templateLog.info("tableIdView...:..: " + tableIdView);
            if (tableIdView != null) {
                String[] tableIdViewPair = tableIdView.split(",");

                for (int i = 0; i < tableIdViewPair.length; i++) {
                    LogMessages.templateLog.info("tableIdViewPair...: " + tableIdViewPair[i]);
                    String tableId = tableIdViewPair[i].split("-")[0];
                    String viewName = tableIdViewPair[i].split("-")[1];
                    String viewColumns = tableViewColumns.split("#")[i];

                    LogMessages.templateLog.info("tableId...:..: " + tableId);
                    LogMessages.templateLog.info("viewName...:..: " + viewName);

                    List<List<String>> viewDataList = getViewData(viewName, ifr, viewColumns, pid);
                    addTableEntryCard(doc, tableId, viewDataList);

                }
            }
        } catch (Exception e) {
            LogMessages.templateLog.info("Inside catch replaceComplexDataContent...", e);
        }
        return doc;

    }

    private List<List<String>> getViewData(String viewName, IFormReference ifr, String viewColumns, String pid) {

        List<List<String>> result = null;
        try {
            String viewNameOnly = viewName.split("#")[0];
            LogMessages.templateLog.info("viewColumns...: " + viewColumns);
            String query = "select " + viewColumns + " from " + viewNameOnly + " where TransactionID = '" + pid + "'";
            LogMessages.templateLog.info("Query getViewData...: " + query);
            result = ifr.getDataFromDB(query);
            LogMessages.templateLog.info("Result getViewData...: " + result);
        } catch (Exception e) {
            LogMessages.templateLog.info("Inside catch getViewData....", e);
        }
        return result;
    }

    private void addTableEntryCard(XWPFDocument doc, String tableId, List<List<String>> viewDataList) {

        try {
            String fontFamily = "Arial";
            List<XWPFTable> tables = doc.getTables();
            XWPFTable tableOne = tables.get(Integer.parseInt(tableId));
            List<XWPFTableRow> tableRows = tableOne.getRows();
            XWPFTableRow tableRow = tableRows.get(0);
            int noOfColumn = tableRow.getTableCells().size();
            List<XWPFTableCell> tableCells = tableRow.getTableCells();
            LogMessages.templateLog.info("List of tableCells...: " + tableCells);
            LogMessages.templateLog.info("Number of columns...: " + noOfColumn);
            XWPFParagraph p1 = tableCells.get(0).getParagraphs().get(0);
            for (XWPFRun r : p1.getRuns()) {
                r.setBold(false);
                int iFontsize = r.getFontSize();
                fontFamily = r.getFontFamily();
                LogMessages.templateLog.info("Font SIze...: " + iFontsize);
                LogMessages.templateLog.info("Font family...: " + fontFamily);

            }

            for (int i = 0; i < viewDataList.size(); i++) {
                XWPFTableRow tableOneRowTwo = tableOne.createRow();
                List<String> tableValueArray = viewDataList.get(i);
                LogMessages.templateLog.info("tableValueArray... " + tableValueArray);
                for (int k = 0; k < tableValueArray.size(); k++) {
                    LogMessages.templateLog.info("inside for loopS tableValueArray....");
                    XWPFTableCell cell = tableOneRowTwo.getCell(k);
                    LogMessages.templateLog.info(k + ":::::ERROR cell:" + cell);
                    XWPFParagraph p = cell.getParagraphs().get(0);
                    LogMessages.templateLog.info("ERROR p:" + p);
                    XWPFRun r = p.createRun();

                    r.setFontSize(12);
                    r.setFontFamily(fontFamily);
                    r.setText(tableValueArray.get(k));
                    p.setSpacingAfter(0);
                }
            }

        } catch (Exception e) {
            LogMessages.templateLog.info("Exception in addTableEntryCard of GenerateTemplate ", e);
        }

    }

    public XWPFDocument replaceBodyContent(XWPFDocument doc) {
        try {
            LogMessages.templateLog.info("inside replaceBodyContent");

            for (XWPFParagraph p : doc.getParagraphs()) {
                LogMessages.templateLog.info("p.getParagraphText().... " + p.getParagraphText());
                LogMessages.templateLog.info("p.getText().... " + p.getText());
                extractTextFromParagraph(ht, p);
            }
            // Replace text formatted with some tables
            for (XWPFTable tbl : doc.getTables()) {
                LogMessages.templateLog.info("Replacing text in table format");
                textReplaceInTable(ht, tbl);
            }
        } catch (Exception e) {
            LogMessages.templateLog.info("Exception in ReplaceBodyContent of GenerateTemplate", e);
        }
        return doc;
    }

    public void textReplaceInTable(Map<String, String> ht, XWPFTable tbl) {
        List<XWPFTable> tables1 = null;
        try {
            for (XWPFTableRow row : tbl.getRows()) {
                LogMessages.templateLog.info("no of rows" + row.toString());
                for (XWPFTableCell cell : row.getTableCells()) {
                    tables1 = cell.getTables();
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        extractTextFromParagraph(ht, p);
                    }
                    if (!tables1.isEmpty()) {
                        for (XWPFTable table_2 : tables1) {
                            LogMessages.templateLog.info("inside multiple table");
                            textReplaceInTable(ht, table_2);
                        }
                    }
                }

            }
        } catch (Exception e) {
            LogMessages.templateLog.info("Exception in textReplaceInTable() of GenerateTemplate", e);
        }
    }

    private void extractTextFromParagraph(Map<String, String> ht, XWPFParagraph p) {
        for (XWPFRun r : p.getRuns()) {
            LogMessages.templateLog.info("no of runs" + r);
            String text = r.getText(0);
            if (text == null || "".equals(text.trim()))
                continue;
            for (Map.Entry<String, String> h : ht.entrySet()) {
                if (text != null && text.contains(h.getKey())) {
                    text = text.replace(h.getKey(), h.getValue());
                    r.setText(text, 0);
                }

            }
        }
    }

    /**
     * ****************************************************************** * Function
     * Name : writeToDocumentLog * Date Written : 05/24/2016 * Author : Vipul
     * Aggarwal * Input Parameters : int iLogType, String strText, String
     * nameOfWebservice Return Values : void * Description : This function used to
     * write logs of Webservices I/O xml
     * **********************************************************************
     */

}
