/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import com.newgen.common.LogMessages;
import com.newgen.aproj2.template.BusinessDataVO;
import com.newgen.aproj2.template.BusinessValidation;
import com.newgen.aproj2.template.ReadXmlData;
import com.newgen.omni.wf.util.xml.XMLParser;

import com.newgen.iforms.custom.IFormReference;

public class GenerateTemplate implements Serializable {

	// Logging_LOS logObj=new Logging_LOS();

	public Map<String, String> currentServiceProperty = new HashMap<String, String>();
	Hashtable<String, String> ht = new Hashtable<String, String>();
	public String finalPDFPath = "";
	String gtablesIdBodyCardApp[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
			"33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
			"51", "52" };

	public void readCabProperty(String sectionName, String processName) {
		currentServiceProperty.clear();
		// logObj.templateLog(System.getProperty("user.dir") +
		// System.getProperty("file.separator") + "TemplateGeneration" +
		// System.getProperty("file.separator") + "preGeneratedtemplates" +
		// System.getProperty("file.separator") +
		// "Template" + ".ini");
		LogMessages.templateLog.info(System.getProperty("user.dir") + System.getProperty("file.separator")
				+ "TemplateGeneration" + System.getProperty("file.separator") + "preGeneratedtemplates"
				+ System.getProperty("file.separator") + "Template" + ".ini");
		String INI = System.getProperty("user.dir") + System.getProperty("file.separator") + "TemplateGeneration"
				+ System.getProperty("file.separator") + "preGeneratedtemplates" + System.getProperty("file.separator")
				+ "Template" + ".ini";
		//
		// logObj.templateLog("INI---- " + INI);
		// logObj.templateLog("sectionName---- " + sectionName);

		LogMessages.templateLog.info("INI---- " + INI);
		LogMessages.templateLog.info("sectionName---- " + sectionName);

		String line = "";
		String sSection = "";
		String temp = "";
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(INI, "rw");
			for (line = raf.readLine(); line != null; line = raf.readLine()) {
				if (line.equalsIgnoreCase("")) {
					continue;
				}
				line = line.trim();
				if ((line.charAt(0) != '[') || (line.charAt(line.length() - 1) != ']')
						|| (!line.substring(1, line.length() - 1).equalsIgnoreCase(sectionName))) {
					continue;
				}
				sSection = line.substring(1, line.length() - 1);

				if (!sSection.equalsIgnoreCase(sectionName)) {
					continue;
				}
				sSection = "";
				for (line = raf.readLine(); line != null; line = raf.readLine()) {
					if (line.equalsIgnoreCase("")) {
						continue;
					}
					line = line.trim();
					if (line.charAt(0) == '[') {
						break;
					}
					int i = line.indexOf('=');

					temp = line.substring(0, i);
					currentServiceProperty.put(temp.trim(), line.substring(i + 1));
					temp = "";
				}
				break;
			}
		} catch (Exception e) {
			// logObj.templateLog("ReadPRoperty Exceptioon " + e);
			LogMessages.templateLog.info("ReadPRoperty Exception in GenerateTemplate::", e);
			// e.printStackTrace();
			raf = null;
			line = "";
			sSection = "";
			temp = "";
		} finally {

			try {
				raf = null;
				line = "";
				sSection = "";
				temp = "";
			} catch (Exception e) {
				// e.printStackTrace();
			}

		}
	}

	public String generateTemplate(IFormReference ifr, XMLParser generalDParser, String templateName, String pid) {

		// logObj.templateLog("Inside GenerateTemplate function...");
		LogMessages.templateLog.info("Inside GenerateTemplate function...");

		// logObj.templateLog("Inside generateTemplate()...");
		LogMessages.templateLog.info("Inside generateTemplate()...");
		String processName = "";
		String templatePath = "";
		String destinationPath = "";
		String documentName = "";
		String viewer = "";
		ReadXmlData readxmldata = null;
		String extTblName = "";
		ArrayList arrExtlist = null;
		boolean readstatus = false;
		boolean bReturn = false;
		try {
			processName = ifr.getProcessName();
			// logObj.templateLog("Process -->" + processName);
			LogMessages.templateLog.info("Process -->" + processName);
			try {
				readCabProperty(templateName, processName);
				documentName = (String) currentServiceProperty.get("DOCUMENTNAME");
				templateName = (String) currentServiceProperty.get("XMLFILENAME");
				viewer = (String) currentServiceProperty.get("VIEWER");
				extTblName = (String) currentServiceProperty.get("EXTTABLENAME");
				// logObj.templateLog("documentname from INI -->" + documentName + ":template
				// name from INI -->"
				// + templateName + ":extTblName from INI -->" + extTblName);
				LogMessages.templateLog.info("documentname from INI -->" + documentName + ":template name from INI -->"
						+ templateName + ":extTblName from INI -->" + extTblName);
				templatePath = System.getProperty("user.dir") + System.getProperty("file.separator")
						+ "TemplateGeneration" + System.getProperty("file.separator") + "preGeneratedtemplates"
						+ System.getProperty("file.separator") + "Template" + System.getProperty("file.separator")
						+ documentName + "." + "docx";
				// logObj.templateLog("template path ----- -->" + templatePath);
				LogMessages.templateLog.info("template path ----- -->" + templatePath);
				destinationPath = System.getProperty("user.dir") + System.getProperty("file.separator")
						+ "TemplateGeneration" + System.getProperty("file.separator") + "generatedTemplates"
						+ System.getProperty("file.separator") + pid;
				// logObj.templateLog("destination generated path ----- -->" + destinationPath);
				LogMessages.templateLog.info("destination generated path ----- -->" + destinationPath);
				File dirpath = new File(destinationPath);
				if (!dirpath.isDirectory()) {
					dirpath.mkdir();
				}
				dirpath = null;
				destinationPath = System.getProperty("user.dir") + System.getProperty("file.separator")
						+ "TemplateGeneration" + System.getProperty("file.separator") + "generatedTemplates"
						+ System.getProperty("file.separator") + pid + System.getProperty("file.separator")
						+ documentName + "." + "docx";
				// logObj.templateLog("destination path ----- -->" + destinationPath);
				LogMessages.templateLog.info("destination  path ----- -->" + destinationPath);
				finalPDFPath = System.getProperty("user.dir") + System.getProperty("file.separator")
						+ "TemplateGeneration" + System.getProperty("file.separator") + "generatedTemplates"
						+ System.getProperty("file.separator") + pid + System.getProperty("file.separator")
						+ documentName + "." + "pdf";
				// logObj.templateLog("pdf destination path ----- -->" + finalPDFPath);
				LogMessages.templateLog.info("pdf destination  path ----- -->" + finalPDFPath);
				readstatus = true;
			} catch (Exception exp) {
				readstatus = false;
				// logObj.templateLog("Error in reading the Property File :=" + exp);
				LogMessages.templateLog.info("Error in reading the Property File of GenerateTemplate :=", exp);
			}

			if (readstatus) {
				try {

					LogMessages.templateLog.info("Inside if:::::");
					readxmldata = new ReadXmlData();
					arrExtlist = new ArrayList();
					// logObj.templateLog("Readed Xml contents into ArrayList filepath-->"
					// + (System.getProperty("user.dir") + System.getProperty("file.separator")
					// + "TemplateGeneration" + System.getProperty("file.separator")
					// + "preGeneratedtemplates" + System.getProperty("file.separator") + "Template"
					// + System.getProperty("file.separator") + "Template" + "_" + templateName +
					// ".xml"));

					LogMessages.templateLog.info("Readed Xml contents into ArrayList filepath-->"
							+ (System.getProperty("user.dir") + System.getProperty("file.separator")
									+ "TemplateGeneration" + System.getProperty("file.separator")
									+ "preGeneratedtemplates" + System.getProperty("file.separator") + "Template"
									+ System.getProperty("file.separator") + "Template" + "_" + templateName + ".xml"));
					if (readxmldata.readData(System.getProperty("user.dir") + System.getProperty("file.separator")
							+ "TemplateGeneration" + System.getProperty("file.separator") + "preGeneratedtemplates"
							+ System.getProperty("file.separator") + "Template" + System.getProperty("file.separator")
							+ "Template" + "_" + templateName + ".xml")) {

						// logObj.templateLog("Readed External table Xml contents into ArrayList");
						LogMessages.templateLog.info("Readed External table Xml contents into ArrayList");
					}

					arrExtlist = readxmldata.getXmlDataRecords();

					boolean extFlag = validateData(ifr, generalDParser, arrExtlist, pid, extTblName);

					ClassLoader classloader = org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
					URL res = classloader.getResource("org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
					String path = res.getPath();
					// System.out.println("POI Core came from " + path);

					classloader = org.apache.poi.util.POILogger.class.getClassLoader();
					LogMessages.statusLog.info("ERROR1::"+classloader);
					LogMessages.statusLog.info("ERROR2::"+res);
					res = classloader.getResource("org/apache/poi/POIXMLDocument.class");
					LogMessages.statusLog.info("ERROR3::"+res);
					try {
					path = res.getPath();
					}
					catch(Exception ex){
						LogMessages.templateLog.info("Error 229", ex);
					}
					// System.out.println("POI OOXML came from " + path);

					bReturn = generateDoc(ht, templatePath, destinationPath, ifr, pid);
					// logObj.templateLog("bReturn = " + bReturn);
					LogMessages.templateLog.info("bReturn = " + bReturn);
					if (bReturn) {
						// createPDF(destinationPath, finalPDFPath);
						// convertWordToPdf(destinationPath,pdfdestinationPath);
						// logObj.templateLog("Template Generated Successfully = " + bReturn);
						LogMessages.templateLog.info("Template Generated Successfully = " + bReturn);
						// destinationPath = finalPDFPath;
						// logObj.templateLog("Return path:: " + destinationPath);
						LogMessages.templateLog.info("Return path:: " + destinationPath);
						return destinationPath;
					} else {
						// logObj.templateLog("Template generation failed");
						LogMessages.templateLog.info("Template generation failed");
						destinationPath = "";
					}

				} catch (Exception ex) {
					// logObj.templateLog(ex + "Error in reading Xml file");
					LogMessages.templateLog.info("Error in reading Xml file", ex);
					destinationPath = "";
					// ex.printStackTrace();
				}
			}

		} catch (Exception exp) {
			// logObj.templateLog("exception in catch -->" + exp.toString());
			LogMessages.templateLog.info("exception in catch -->" + exp.toString());
			destinationPath = "";
			// exp.printStackTrace();
		} finally {
			try {
				processName = null;
				processName = "";
				templatePath = "";
				destinationPath = "";
				documentName = "";
				readxmldata = null;
				extTblName = "";
				readstatus = false;
				arrExtlist = null;
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return destinationPath;
	}

	private boolean validateData(IFormReference ifr, XMLParser generalDParser, ArrayList alRecordsValidation,
			String swi_name, String mstTblName) {

		// logObj.templateLog("1-->");
		LogMessages.templateLog.info("1-->");
		String extName = "";
		String tempName = "";
		String isFormat = "";
		String format = "";
		String sReturnValue = "";
		String sErrorCode = "";
		String QueryResult = "";
		String scolnames = "";
		String Finalcolnames = "";
		String sorgcolnames = "";
		String stempcolnames = "";
		String orgFinalcolnames = "";
		String tempFinalcolnames = "";
		String swiname = "";
		String whereval = "";
		boolean bReturn = false;
		XMLParser xmlParserobject = null;
		BusinessDataVO dc = null;
		BusinessDataVO df = null;
		BusinessValidation bv = null;
		String strEngineName = "";
		String jtsIP = "";
		String port = "";
		int jtsPort;

		strEngineName = ifr.getCabinetName();

		String cabinetName = strEngineName;

		try {

			String process = ifr.getProcessName();

			// logObj.templateLog("2-->");
			LogMessages.templateLog.info("2-->");
			bv = new BusinessValidation();
			// logObj.templateLog("3-->" + alRecordsValidation);
			LogMessages.templateLog.info("3-->" + alRecordsValidation);
			df = (BusinessDataVO) alRecordsValidation.get(0);
			swiname = df.getExtName();
			// logObj.templateLog("4-->");
			LogMessages.templateLog.info("4-->");
			for (int i = 0; i < alRecordsValidation.size(); i++) {
				dc = (BusinessDataVO) alRecordsValidation.get(i);
				extName = dc.getExtName();
				tempName = dc.getTempName();
				// isFormat = dc.getIsFormat();
				// format = dc.getFormat();

				sorgcolnames = sorgcolnames.concat(extName).concat(",");
				orgFinalcolnames = sorgcolnames.substring(0, sorgcolnames.lastIndexOf(","));

				stempcolnames = stempcolnames.concat(tempName).concat(",");
				tempFinalcolnames = stempcolnames.substring(0, stempcolnames.lastIndexOf(","));

				sErrorCode = extName;
				if (extName.equalsIgnoreCase("GETDATE()")) {
					extName = "convert(varchar, " + extName + ", 103) AS SYS_DATE";
				} else {
					// Noting
				}
				scolnames = scolnames.concat(extName).concat(",");
			}
			Finalcolnames = scolnames.substring(0, scolnames.lastIndexOf(","));
			whereval = swi_name;

			/*
			 * Added By Toseef---Starts
			 */
			Finalcolnames = "DISTINCT	" + Finalcolnames;
			/*
			 * Ends
			 */

			QueryResult = bv.DynamicQuery(Finalcolnames, whereval, "test", mstTblName);

			QueryResult = QueryResult.replaceAll("wi_name", "transactionid");

			// logObj.templateLog("getNGDataFromDataCache skoutputXml QueryResult := " +
			// QueryResult);
			LogMessages.templateLog.info("getNGDataFromDataCache skoutputXml QueryResult := " + QueryResult);

			/*
			 * Code added by Toseef To get result on the basis of Product and for Applicant
			 * Only -----------------------STARTS--------------------------------
			 */

			List<List<String>> output = null;

			/*
			 * ----------------------ENDS----------------------------------
			 */

			List<List<String>> outputXml = null;
			outputXml = ifr.getDataFromDB(QueryResult);
			// logObj.templateLog("getNGDataFromDataCache skoutputXml := " + outputXml);
			LogMessages.templateLog.info("getNGDataFromDataCache skoutputXml := " + outputXml);

			xmlParserobject = new XMLParser();
			// xmlParserobject.setInputXML(outputXml.toString());
			if (!outputXml.isEmpty()) {
				String arrcol[] = orgFinalcolnames.split(",");
				String arrtempcol[] = tempFinalcolnames.split(",");
				// logObj.templateLog("getNGDataFromDataCache arrcol := " + arrcol);
				LogMessages.templateLog.info("getNGDataFromDataCache arrcol := " + arrcol);
				// System.out.println(arrcol.toString());
				// logObj.templateLog("getNGDataFromDataCache arrtempcol := " + arrtempcol);
				LogMessages.templateLog.info("getNGDataFromDataCache arrtempcol := " + arrtempcol);
				// LogMessages.templateLog.info("getNGDataFromDataCache arrtempcol := " +
				// arrtempcol);
				// System.out.println(arrtempcol.toString());
				for (List<String> viewList : outputXml) {
					for (int i = 0; i < arrcol.length; i++) {

						try {
							// logObj.templateLog("Got value : " + viewList.get(i));
							//LogMessages.templateLog.info("Got value	:	" + viewList.get(i));
							if (!viewList.get(i).equalsIgnoreCase("--Select--")) {
								ht.put("" + arrtempcol[i] + "", viewList.get(i));
								// logObj.templateLog("put ColumnName" + arrtempcol[i] + "#put value#" +
								// viewList.get(i));
								//LogMessages.templateLog
									//	.info("put ColumnName" + arrtempcol[i] + "#put value#" + viewList.get(i));
							} else {
								ht.put("" + arrtempcol[i] + "", "");
								// logObj.templateLog(
								// "Got --Select-- , put ColumnName" + arrtempcol[i] + "#put value#" + "");
								//LogMessages.templateLog
									//	.info("Got --Select-- , put ColumnName" + arrtempcol[i] + "#put value#" + "");
							}
						} catch (NullPointerException e) {
							ht.put("" + arrtempcol[i] + "", "");
							// logObj.templateLog("Got Null value for Column : " + arrtempcol[i]
							// + " .Replacing it with Blank String");
							//LogMessages.templateLog.info("Got Null value for Column : " + arrtempcol[i]
								//	+ " .Replacing it with Blank String");
						} catch (Exception e2) {
							// logObj.templateLog("Some Exception Occurred While parsing retrieved DB data
							// from List....."
							// + e2.toString());
							LogMessages.templateLog
									.info("Some Exception Occurred While parsing retrieved DB data from List.....", e2);
						}
					}
					bReturn = true;
				}
			} else {

				// logObj.templateLog("getNGDataFromDataCache skoutputXml is emty := " +
				// outputXml);
				LogMessages.templateLog.info("getNGDataFromDataCache skoutputXml is emty := " + outputXml);
			}

		} catch (Exception ex) {
			bReturn = false;
			// ex.printStackTrace();
			// logObj.templateLog("Error during External table field Validations." + ex);
			LogMessages.templateLog.info("Error during External table field Validations.", ex);
		} finally {
			extName = null;
			tempName = null;
			isFormat = null;
			format = null;
			sReturnValue = null;
			sErrorCode = null;
			xmlParserobject = null;
		}
		return bReturn;
	}

	private boolean generateDoc(Map<String, String> ht, String templatePathFilename, String outputPathFilename,
			IFormReference ifr, String pid) {
		boolean bReturn = false;
		XWPFDocument xwpfDoc = null;
		try {

			// logObj.templateLog("template file path file name in generate doc" +
			// templatePathFilename);
			LogMessages.templateLog.info("template file path file name in generate doc" + templatePathFilename);
			try {
				xwpfDoc = new XWPFDocument(OPCPackage.open(templatePathFilename));
			} catch (Exception e) {
				// logObj.templateLog("error...:: " + e.getMessage());
				// logObj.templateLog(e.toString());

				LogMessages.templateLog.info("error...:: " + e.getMessage());
				LogMessages.templateLog.info(e.toString());
			}
			// logObj.templateLog("after open" + xwpfDoc);
			LogMessages.templateLog.info("after open" + xwpfDoc);
			if (ht.isEmpty()) {
				// logObj.templateLog("inside if" + ht.size());
				LogMessages.templateLog.info("inside if" + ht.size());
				// System.out.println("Body text not being changed");
			} else {
				// logObj.templateLog("inside else");
				LogMessages.templateLog.info("inside else");
				xwpfDoc = ReplaceBodyContent(xwpfDoc, outputPathFilename);

				xwpfDoc = replaceComplexDataContent(xwpfDoc, outputPathFilename, ifr, pid);
				// System.out.println("After replaceComplexDataContent call");
			}

			try {
				xwpfDoc.write(new FileOutputStream(outputPathFilename));
			} catch (FileNotFoundException e) {
				// logObj.templateLog(
				// "--------------------->>>>>>>>>.catch exception in generate doc file not
				// founf" + e.toString());
				LogMessages.templateLog
						.info("--------------------->>>>>>>>>.catch exception in generate doc file not founf", e);
				// e.printStackTrace();
			} catch (IOException e) {
				// logObj.templateLog("--------------------->>>>>>>>>.catch exception in
				// generate doc io" + e.toString());
				LogMessages.templateLog.info("--------------------->>>>>>>>>.catch exception in generate doc  io", e);
				// e.printStackTrace();
			}
			// logObj.templateLog("output path file name in generate doc" +
			// outputPathFilename);
			LogMessages.templateLog.info("output path file name in generate doc" + outputPathFilename);
			bReturn = true;
		} catch (Exception ex) {
			// logObj.templateLog("--------------------->>>>>>>>>.catch exception in
			// generate doc" + ex.toString());
			LogMessages.templateLog.info("--------------------->>>>>>>>>.catch exception in generate doc", ex);
			// ex.printStackTrace();
		}
		return bReturn;
	}

	private XWPFDocument replaceComplexDataContent(XWPFDocument doc, String outputPathFilename, IFormReference ifr,
			String pid) {

		try {
			String TABLEIDVIEW = (String) currentServiceProperty.get("TABLEIDVIEW");
			String tableViewColumns = (String) currentServiceProperty.get("TABLEVIEWCOLUMNS");
			// logObj.templateLog("TABLEIDVIEW...:..: " + TABLEIDVIEW);
			LogMessages.templateLog.info("TABLEIDVIEW...:..: " + TABLEIDVIEW);
			if (TABLEIDVIEW != null) {
				String[] tableIdViewPair = TABLEIDVIEW.split(",");

				for (int i = 0; i < tableIdViewPair.length; i++) {
					// logObj.templateLog("tableIdViewPair...: " + tableIdViewPair[i]);
					LogMessages.templateLog.info("tableIdViewPair...: " + tableIdViewPair[i]);
					// System.out.println("tableIdViewPair...: " + tableIdViewPair[i]);
					String tableId = tableIdViewPair[i].split("-")[0];
					String viewName = tableIdViewPair[i].split("-")[1];
					String viewColumns = tableViewColumns.split("#")[i];
					// logObj.templateLog("tableId...:..: " + tableId);
					// logObj.templateLog("viewName...:..: " + viewName);

					LogMessages.templateLog.info("tableId...:..: " + tableId);
					LogMessages.templateLog.info("viewName...:..: " + viewName);

					List<List<String>> viewDataList = getViewData(viewName, ifr, viewColumns, pid);
					addTableEntryCard(doc, tableId, viewDataList, viewColumns);

				}
			}
		} catch (Exception e) {
			// System.out.println("Inside catch replaceComplexDataContent...");
			LogMessages.templateLog.info("Inside catch replaceComplexDataContent...", e);
			// e.printStackTrace();
		}
		return doc;

	}

	private List<List<String>> getViewData(String viewName, IFormReference ifr, String viewColumns, String pid) {

		List<List<String>> result = null;
		try {
			String viewNameOnly = viewName.split("#")[0];
			String isSerialNo = viewName.split("#")[1];
			// String tableViewColumns = (String)
			// currentServiceProperty.get("TABLEVIEWCOLUMNS");
			// logObj.templateLog("viewColumns...: " + viewColumns);
			LogMessages.templateLog.info("viewColumns...: " + viewColumns);
			String query = "select " + viewColumns + " from " + viewNameOnly + " where TransactionID = '" + pid + "'";
			// logObj.templateLog("Query getViewData...: " + query);
			LogMessages.templateLog.info("Query getViewData...: " + query);
			result = ifr.getDataFromDB(query);
			// logObj.templateLog("Result getViewData...: " + result);
			LogMessages.templateLog.info("Result getViewData...: " + result);
		} catch (Exception e) {
			// logObj.templateLog("Inside catch getViewData...");
			LogMessages.templateLog.info("Inside catch getViewData....", e);
			// e.printStackTrace();
		}

		return result;

	}

	private void addTableEntryCard(XWPFDocument doc, String tableId, List<List<String>> viewDataList,
			String viewColumns) {

		try {
			int iFontsize = 12;
			String fontFamily = "Arial";
			List<XWPFTable> tables = doc.getTables();
			XWPFTable tableOne = tables.get(Integer.parseInt(tableId));
			List<XWPFTableRow> tableRows = tableOne.getRows();
			XWPFTableRow tableRow = tableRows.get(0);
			// XWPFTableCell tableRowNew = tableRows.get(0);
			int NoOfColumn = tableRow.getTableCells().size();
			// System.out.println("table11...." + tableRow.getCell(0).getText());
			List<XWPFTableCell> tableCells = tableRow.getTableCells();
			// logObj.templateLog("List of tableCells...: " + tableCells);
			LogMessages.templateLog.info("List of tableCells...: " + tableCells);
			// System.out.println("Number of columns...: " + NoOfColumn);
			// logObj.templateLog("Number of columns...: " + NoOfColumn);
			LogMessages.templateLog.info("Number of columns...: " + NoOfColumn);
			XWPFParagraph p1 = tableCells.get(0).getParagraphs().get(0);
			for (XWPFRun r : p1.getRuns()) {
				// logObj.templateLog("r.getText...." + r.getText(0));
				//LogMessages.templateLog.info("r.getText...." + r.getText(0));
				r.setBold(false);
				iFontsize = r.getFontSize();
				fontFamily = r.getFontFamily();
				// logObj.templateLog("Font SIze...: " + iFontsize);
				// logObj.templateLog("Font family...: " + fontFamily);
				LogMessages.templateLog.info("Font SIze...: " + iFontsize);
				LogMessages.templateLog.info("Font family...: " + fontFamily);

			}

			for (int i = 0; i < viewDataList.size(); i++) {
				XWPFTableRow tableOneRowTwo = tableOne.createRow();

				List<String> tableValueArray = viewDataList.get(i);
				// System.out.println("tableValueArray... " + tableValueArray);
				// logObj.templateLog("tableValueArray... " + tableValueArray);
				LogMessages.templateLog.info("tableValueArray... " + tableValueArray);
				for (int k = 0; k < tableValueArray.size(); k++) {
					// logObj.templateLog("inside for loopS tableValueArray....");
					LogMessages.templateLog.info("inside for loopS tableValueArray....");
					XWPFTableCell cell = tableOneRowTwo.getCell(k);
					LogMessages.templateLog.info(k+":::::ERRROR 2:"+cell);
					XWPFParagraph p = cell.getParagraphs().get(0);
					try {
						LogMessages.templateLog.info("ERRROR 2:"+p);
					}
					catch(Exception ex){
						LogMessages.templateLog.info("ERRROR 3:"+ex);
					}
					// logObj.templateLog("Fist paragraph.... " + p);
					//LogMessages.templateLog.info("Fist paragraph.... " + p);
					// System.out.println("Fist paragraph.... " + p);
					XWPFRun r = p.createRun();

					r.setFontSize(12);
					r.setFontFamily(fontFamily);
					r.setText(tableValueArray.get(k));
					// logObj.templateLog("Run after setText.... " + r);
					//LogMessages.templateLog.info("Run after setText.... " + r);
					p.setSpacingAfter(0);
				}
			}

		} catch (Exception e) {
			// logObj.templateLog("Exception... " + e.getMessage());
			LogMessages.templateLog.info("Exception in addTableEntryCard of GenerateTemplate ", e);
			// System.out.println("There is no table # in the document.");
			// e.printStackTrace();
		}

	}

	public XWPFDocument ReplaceBodyContent(XWPFDocument doc, String outputPathFilename) {
		// Replace free texts
		List<XWPFRun> runs = null;
		String text = "";
		try {
			// doc.getHeaderArray(0).getParagraphs().get(0);
			// logObj.templateLog("inside replase body");
			LogMessages.templateLog.info("inside replase body");

			for (XWPFParagraph p : doc.getParagraphs()) {
				// logObj.templateLog("inside for loopS");
				LogMessages.templateLog.info("inside for loopS");
				p.getParagraphText();
				p.getText();
				// logObj.templateLog("p.getParagraphText().... " + p.getParagraphText());
				// logObj.templateLog("p.getText().... " + p.getText());
				LogMessages.templateLog.info("p.getParagraphText().... " + p.getParagraphText());
				LogMessages.templateLog.info("p.getText().... " + p.getText());
				runs = p.getRuns();

				if (runs != null) {
					for (XWPFRun r : runs) {
						// logObj.templateLog("runs " + runs);
						LogMessages.templateLog.info("runs  " + runs);
						text = r.getText(0);
						if (text == null || text.trim() == "")
							continue;
						// logObj.templateLog("text... " + text);
						//LogMessages.templateLog.info("text...  " + text);
						for (String key : ht.keySet()) {
							// logObj.templateLog("key " + key);
							//LogMessages.templateLog.info("key  " + key);

							if (text != null && (text.contains(key))) {

								// logObj.templateLog("text " + text);
								//LogMessages.templateLog.info("text  " + text);
								text = text.replace(key, ht.get(key));
								r.setText(text, 0);
								continue;
							}

						}
					}
				}
			}
		} catch (Exception e) {
			// logObj.templateLog("paragraph error" + e.toString());
			LogMessages.templateLog.info("Exception in ReplaceBodyContent of GenerateTemplate", e);
			// e.printStackTrace();
		}
		runs = null;
		text = "";

		// Replace text formated with some tables
		try {
			for (XWPFTable tbl : doc.getTables()) {
				// logObj.templateLog("Replacing text in table format");
				LogMessages.templateLog.info("Replacing text in table format");
				// System.out.println("Replacing text in table format");
				textReplaceintable(ht, tbl);
			}
		} catch (Exception e) {
			// logObj.templateLog("table error temp" + e.toString());
			LogMessages.templateLog.info("table error temp ", e);
			// e.printStackTrace();
		}

		// Replace text in Header table
		/*
		 * try { //XWPFHeader header = doc.getHeaderArray(1);
		 * 
		 * 
		 * XWPFHeaderFooterPolicy policy= doc.getHeaderFooterPolicy(); XWPFHeader header
		 * = policy.getFirstPageHeader(); XWPFHeader header2 = policy.getHeader(1);
		 * logObj.templateLog("header...!!!! " + header);
		 * logObj.templateLog("header2...!!!! " + header2);
		 * logObj.templateLog("header.getText()...!!!! " + header.getText());
		 * 
		 * for (XWPFTable tbl : header.getTables()) {
		 * logObj.templateLog("header.getTable()..tbl.!!!! " + tbl);
		 * logObj.templateLog("Replacing text in header table format"); //
		 * System.out.println("Replacing text in table format"); textReplaceintable(ht,
		 * tbl); } } catch (Exception e) { logObj.templateLog("table error temp" +
		 * e.toString()); e.printStackTrace(); }
		 */
		return doc;

	}

	public void textReplaceintable(Map<String, String> ht, XWPFTable tbl) {
		int table_no = 0;
		String text = "";
		List<XWPFTable> tables1 = null;
		try {
			for (XWPFTableRow row : tbl.getRows()) {
				// logObj.templateLog("no of rows" + row.toString());
				LogMessages.templateLog.info("no of rows" + row.toString());
				for (XWPFTableCell cell : row.getTableCells()) {
					tables1 = cell.getTables();
					table_no = tables1.size();
					for (XWPFParagraph p : cell.getParagraphs()) {
						for (XWPFRun r : p.getRuns()) {
							// logObj.templateLog("no of runs" + r);
							LogMessages.templateLog.info("no of runs" + r);
							text = r.getText(0);
							if (text == null || text.trim() == "")
								continue;
							// System.out.println(text);
							for (String key : ht.keySet()) {
								// logObj.templateLog("no of keys table" + key);
								//LogMessages.templateLog.info("no of keys table" + key);
								if (text != null && text.contains(key)) {
									// logObj.templateLog("\n" + key + " replaced with " + ht.get(key));
									//LogMessages.templateLog.info("\n" + key + " replaced with  " + ht.get(key));
									text = text.replace(key, ht.get(key));
									r.setText(text, 0);
									continue;
								}

							}
						}
					}

					if (table_no > 0) {
						for (XWPFTable table_2 : tables1) {
							// logObj.templateLog("insde multiple table");
							LogMessages.templateLog.info("insde multiple table");
							textReplaceintable(ht, table_2);
						}
					}
				}

			}
		} catch (Exception e) {
			// logObj.templateLog("Exception in textReplaceintable()" + e);
			LogMessages.templateLog.info("Exception in textReplaceintable() of GenerateTemplate", e);
		} finally {
			try {
				table_no = 0;
				text = "";
				tables1 = null;
			} catch (Exception e) {
			}
		}
	}

	private void createPDF(String generatedDocPath, String outputPdfFilePath) {

		XWPFDocument document = null;
		// PdfOptions options = null;
		OutputStream fileForPdf = null;
		try {

			// logObj.templateLog("path::::" + generatedDocPath + "---" +
			// outputPdfFilePath);
			LogMessages.templateLog.info("path::::" + generatedDocPath + "---" + outputPdfFilePath);
			/*
			 * document = new XWPFDocument(new FileInputStream(generatedDocPath));
			 * logObj.templateLog("document Info ::: " + document);
			 * 
			 * PdfConverter.getInstance().convert(document, new FileOutputStream(new
			 * File(outputPdfFilePath)), PdfOptions.create());
			 * logObj.templateLog("control after pdf generation ::: " + document);
			 */

			/*
			 * XWPFDocument docx = new XWPFDocument(new FileInputStream( generatedDocPath));
			 * // using XWPFWordExtractor Class XWPFWordExtractor we = new
			 * XWPFWordExtractor(docx); String k = we.getText();
			 * 
			 * fileForPdf = new FileOutputStream(new File( outputPdfFilePath)); we.close();
			 */

		} catch (Exception e) {
			// logObj.templateLog("exception while generating pdf" + e.getMessage());
			LogMessages.templateLog.info("exception while generating pdf of GenerateTemplate", e);

		} finally {
			try {
				document = null;
			} catch (Exception e) {
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
