/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.util;

import com.newgen.common.LogMessages;
import com.newgen.niplj.NIPLJ;
import com.newgen.niplj.codec.pdf.PDF;
import com.newgen.niplj.fileformat.Tif6;
import com.newgen.niplj.generic.NGIMException;
import com.newgen.niplj.io.RandomInputStreamSource;
import com.newgen.opall.Viewer;

import java.io.*;

import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;

public class TemplateUtil {

	public String getPDBDocTypeAndNoOfPages(String filePath) {
		LogMessages.templateLog.info("File Path in get Dcument Type  " + filePath);
		int noOfPages = 1;
		try {
			if (filePath.endsWith(".docx")) {
				LogMessages.templateLog.info("inside iffffffff");
				return ZERO;
			}
			RandomInputStreamSource riss = new RandomInputStreamSource(filePath);
			int iFileFormat = NIPLJ.getFileFormat(riss);
			LogMessages.templateLog.info("iFileFormat = " + iFileFormat);
			if (iFileFormat == NIPLJ.TIFF_FORMAT) {
				noOfPages = Tif6.getPageCount(filePath);
			} else if (iFileFormat == NIPLJ.PDF_FORMAT) {
				InputStream in = new FileInputStream(new File(filePath));
				int pdfSupport = Viewer.checkPDFSuport(in);
				LogMessages.templateLog.info("pdfSupport = " + pdfSupport);
				noOfPages = PDF.getPDFInReadMode(filePath, null).getNumberOfPages();
				in.close();
			}
			riss.getStream().close();
		} catch (FileNotFoundException e) {
			LogMessages.templateLog.info("Exception of filenotfound in getPDBDocTypeAndNoOfPages of TemplateUtil.", e);
		} catch (IOException e) {
			LogMessages.templateLog.info("Exception of ioexception in getPDBDocTypeAndNoOfPages of TemplateUtil.", e);
		} catch (NGIMException e) {
			LogMessages.templateLog.info("Exception of ngimexception in getPDBDocTypeAndNoOfPages of TemplateUtil.", e);
		}
		return noOfPages + "";
	}

}
