/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONObject;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.niplj.NIPLJ;
import com.newgen.niplj.codec.pdf.PDF;
import com.newgen.niplj.fileformat.Tif6;
import com.newgen.niplj.generic.NGIMException;
import com.newgen.niplj.io.RandomInputStreamSource;
import com.newgen.opall.Viewer;

public class TemplateUtil {

	// Logging_LOS logObj=new Logging_LOS();
	static String PID = null;
	static String sessionId = null;
	static JSONObject jsonobj = null;
	static String addDocumentOutputXml = new String();

	public String AddTemplate(IFormReference ifr, String templateName, String destinationPath, String sessionId,
			String volumeId, String folderIndex) {
		try {

			// Config.readPropertiesFile();
			String socketType = "templateSocket";
			PID = (String) ifr.getValue("PID");
			String inputMsg = "<Input_Message><OPTION>CREATEDOC</OPTION>" + "<pid>" + PID + "</pid>" + "<templatename>"
					+ templateName + "</templatename>" + "<destinationpath>" + destinationPath + "</destinationpath>"
					+ "<volumeid>" + volumeId + "</volumeid>" + "<folderindex>" + folderIndex + "</folderindex>"
					+ "<processName>Collection</processName>" + "<sessionid>" + sessionId
					+ "</sessionid></Input_Message>";
			// add Process identifier : RLOS
			CommonMethods cmObj = new CommonMethods();
			String addDocOutXML = cmObj.connectToSocket(inputMsg, socketType);
			// logObj.templateLog("addDocOutXML....^^ @ Calling Client" + addDocOutXML);
			LogMessages.templateLog.info("addDocOutXML....^^ @ Calling Client" + addDocOutXML);
			addDocumentOutputXml = addDocOutXML;

		} catch (Exception e) {
			// e.printStackTrace();
			// logObj.templateLog("Inside generateTemplate of TemplateUtil CATCH...");
			LogMessages.templateLog.info("Inside generateTemplate of TemplateUtil CATCH...",e);
		}
		return addDocumentOutputXml;
	}

	public String getPDBDocTypeAndNoOfPages(String filePath) {

		// logObj.templateLog("File Path in get Dcument Type " + filePath);
		LogMessages.templateLog.info("File Path in get Dcument Type  " + filePath);
		String documentType = "N";
		String docExtension = "NOCHANGE";
		int noOfPages = 1;

		RandomInputStreamSource riss;
		int iFileFormat = 0;

		try {

			if (filePath.endsWith(".docx")) {
				// logObj.templateLog("inside iffffffff");
				LogMessages.templateLog.info("inside iffffffff");
				return "0";
			}

			riss = new RandomInputStreamSource(filePath);
			iFileFormat = NIPLJ.getFileFormat(riss);

			// logObj.templateLog("iFileFormat = " + iFileFormat);
			LogMessages.templateLog.info("iFileFormat = " + iFileFormat);
			if (iFileFormat == NIPLJ.JPEG_FORMAT) {
				documentType = "I";
				docExtension = "jpg";
			} else if (iFileFormat == NIPLJ.TIFF_FORMAT) {
				documentType = "I";
				docExtension = "tiff";

				try {
					noOfPages = Tif6.getPageCount(filePath);
				} catch (Exception noOfPage) {
				}
			} else if (iFileFormat == NIPLJ.PDF_FORMAT) {
				docExtension = "pdf";
				InputStream in = new FileInputStream(new File(filePath));
				int pdfSupport = Viewer.checkPDFSuport(in);
				// logObj.templateLog("pdfSupport = " + pdfSupport);
				LogMessages.templateLog.info("pdfSupport = " + pdfSupport);
				pdfSupport = Math.abs(pdfSupport);

				if (pdfSupport == 1 || pdfSupport == 5002 || pdfSupport == 7100) {
					documentType = "I";
				} else {
					documentType = "N";
				}

				in.close();

				try {
					noOfPages = PDF.getPDFInReadMode(filePath, null).getNumberOfPages();
				} catch (Exception noOfPage) {
				}
			} else {
				documentType = "N";
			}

			riss.getStream().close();
		} catch (FileNotFoundException e) {
			LogMessages.templateLog.info("Exception of filenotfound in getPDBDocTypeAndNoOfPages of TemplateUtil.", e);
			// e.printStackTrace();
		} catch (IOException e) {
			LogMessages.templateLog.info("Exception of ioexception in getPDBDocTypeAndNoOfPages of TemplateUtil.", e);
			// e.printStackTrace();
		} catch (NGIMException e) {
			LogMessages.templateLog.info("Exception of ngimexception in getPDBDocTypeAndNoOfPages of TemplateUtil.", e);
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return noOfPages + "";
	}

}
