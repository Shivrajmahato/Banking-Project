/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import com.newgen.iforms.*;
import com.newgen.common.LogMessages;
import com.newgen.omni.wf.util.app.NGEjbClient;
import com.newgen.wfdesktop.xmlapi.WFCallBroker;
import com.newgen.wfdesktop.xmlapi.WFInputXml;

public class XMLGeneration {

	XMLParser xmlParser = null;
	static int sessionCheckInt = 0;
	static boolean mblnIsUserLoggedIn = false;
	static int loopCount = 50;
	public static NGEjbClient ngEjbClient;
	static String ProcessDefID = "";
	// static Logging_LOS logObj=new Logging_LOS();

	public static String ExecuteQuery_APSelect(String sQuery,String sEngineName,String sSessionId)
	{
		WFInputXml wfInputXml=null;
		try{
			wfInputXml = new WFInputXml();

			wfInputXml.appendStartCallName("APSelectWithColumnNames", "Input");
			wfInputXml.appendTagAndValue("Query",sQuery);
			wfInputXml.appendTagAndValue("EngineName",sEngineName);
			wfInputXml.appendTagAndValue("SessionId",sSessionId);
			wfInputXml.appendEndCallName("APSelectWithColumnNames","Input");
		}catch(Exception e){

		}
		return wfInputXml.toString();
	}
	
	public String getSessionID(String cabName, String JTSIP, String JTSPORT, String userName, String password) {
		String lExceptionId = "com.newgen.srvr.FunctionWI.connectToWorkFlow";
		String sessionID = "";
		int i = -9;
		String desc = null;
		String connectInputXML = null;
		String connectOutputXML = null;
		try {
			connectInputXML = getConnectInputXML(cabName, userName, password);
			// logObj.servicelog(("connectInputXML : " + connectInputXML));
			// logObj.servicelog(("connectInputXML 2 : " + "JTSIP="+JTSIP+"
			// JTSPORT="+Integer.parseInt(JTSPORT)));

			LogMessages.statusLog.info(("connectInputXML : " + connectInputXML));
			LogMessages.statusLog
					.info(("connectInputXML 2 : " + "JTSIP=" + JTSIP + " JTSPORT=" + Integer.parseInt(JTSPORT)));
			LogMessages.statusLog.info("*** connectInputXML call from OtherUtil ends *** ");
			// System.out.println(("connectInputXML : " + connectInputXML));
			// System.out.println(("connectInputXML 2 : " + "JTSIP=" + JTSIP + " JTSPORT=" +
			// Integer.parseInt(JTSPORT)));
			// System.out.println("*** connectInputXML call from OtherUtil ends *** ");
			sessionCheckInt = 0;
			while (sessionCheckInt < loopCount) {
				try {
					//
					// logObj.servicelog("*** connectOutputXML ***");
					LogMessages.statusLog.info("*** connectOutputXML    ***");
					connectOutputXML = WFNGExecute(connectInputXML, JTSIP, Integer.parseInt(JTSPORT), 1);
					// logObj.servicelog("connectOutputXML : ");
					// logObj.servicelog("connectOutputXML : " + connectOutputXML);
					LogMessages.statusLog.info("connectOutputXML    : ");
					LogMessages.statusLog.info("connectOutputXML    : " + connectOutputXML);
					// System.out.println(" --- connectOutputXML : " + connectOutputXML + "---");
					break;
				} catch (Exception e) {
					// logObj.servicelog("Exception in Execute of getsession ID: " + e + "\nNumber "
					// + sessionCheckInt);
					LogMessages.statusLog
							.info("Exception in Execute of getsession ID: " + e + "\nNumber " + sessionCheckInt);
					// System.out.println("Exception in Execute of getsession ID: " + e + "\nNumber
					// " + sessionCheckInt);
					// addToTextArea("Exception in Execute of getsession ID: " + e+"\nNumber
					// "+sessionCheckInt);
					sessionCheckInt++;
					continue;
				}
			}

			XMLParser lobjXMLParser = new XMLParser();
			lobjXMLParser.setInputXML(connectOutputXML);
			String s9 = lobjXMLParser.getValueOf("Option");
			/*
			 * if (!s9.equalsIgnoreCase("WMConnect")) {
			 * //addToTextArea("Error Invalid Workflow Server IP and Port are registered.");
			 * return "Error Invalid Workflow Server IP and Port are registered."; }
			 */
			// String s6 = lobjXMLParser.getValueOf("MainCode");
			String s6 = lobjXMLParser.getValueOf("MainCode");
			// System.out.println("Value of s6 ========== " + s6);
			i = Integer.parseInt(s6);
			if (i == 0) {
				// sessionID = lobjXMLParser.getValueOf("SessionID");
				sessionID = lobjXMLParser.getValueOf("SessionId");
				// addToTextArea("session id "+sessionID);
				mblnIsUserLoggedIn = true;
				return sessionID;
			} else {
				String s7 = lobjXMLParser.getValueOf("SubErrorCode");
				desc = lobjXMLParser.getValueOf("Description");
				// addToTextArea("SubErrorCode Description "+desc);
				i = Integer.parseInt(s7);
			}
		} catch (Exception lExcp) {
			// logObj.servicelog(lExceptionId + ": " + lExcp.toString());
			LogMessages.statusLog.info(lExceptionId + ": ", lExcp);
		}
		return "Error " + desc;
	}

	public static String WFNGExecute(String ipXML, String serverIP, int serverPort, int flag)
			throws IOException, Exception {
		// System.out.println("SERVER IP: " + serverIP + "SERVER PORT; " + serverPort);
		// logObj.servicelog("SERVER IP: " + serverIP + "SERVER PORT; " + serverPort);
		LogMessages.statusLog.info("SERVER IP: " + serverIP + "SERVER PORT; " + serverPort);
		try {
			// System.out.println("***** Calling NGEJBCLIENT *****");
			LogMessages.statusLog.info("***** Calling NGEJBCLIENT *****");
			ngEjbClient = NGEjbClient.getSharedInstance();
		} catch (Exception e) {
			// System.out.println("Error in WFNGExecute");
			LogMessages.statusLog.info("Error in WFNGExecute", e);
			// e.printStackTrace();
		}

		String portNo = Integer.toString(serverPort);
		// logObj.servicelog("portNo.startsWith('33')=" + portNo.startsWith("33"));
		LogMessages.statusLog.info("portNo.startsWith('33')=" + portNo.startsWith("33"));
		try {
			/*
			 * if(portNo.startsWith("33")){
			 * System.out.println(" *** portNo.startsWith('33')="+portNo.startsWith("33") +
			 * "***" );
			 * logObj.servicelog("portNo.startsWith('33')="+portNo.startsWith("33"));
			 * 
			 * logObj.servicelog("Before return statements...!!"); String opt =
			 * WFCallBroker.execute(ipXML,serverIP,serverPort,1);
			 * logObj.servicelog("output:: " + opt); return opt ; } else
			 */
			return ngEjbClient.makeCall(serverIP, String.valueOf(serverPort), "JBOSSEAP", ipXML);
		} catch (Exception e) {
			// logObj.servicelog("ERRORRRRR........!!");
			LogMessages.statusLog.info("Exception found in WFNGExecute of XMLGeneration", e);
		}
		return "";
	}

	public static String getConnectInputXML(String cabinetName, String username, String password) {
		StringBuffer ipXMLBuffer = new StringBuffer();

		ipXMLBuffer.append("<?xml version=\"1.0\"?>");
		ipXMLBuffer.append("<WMConnect_Input>");
		ipXMLBuffer.append("<Option>WMConnect</Option>");
		ipXMLBuffer.append("<EngineName>");
		ipXMLBuffer.append(cabinetName);
		ipXMLBuffer.append("</EngineName>\n");
		ipXMLBuffer.append("<ApplicationInfo></ApplicationInfo>\n");
		ipXMLBuffer.append("<Participant>\n");
		ipXMLBuffer.append("<Name>");
		ipXMLBuffer.append(username);
		ipXMLBuffer.append("</Name>\n");
		ipXMLBuffer.append("<Password>");
		ipXMLBuffer.append(password);
		ipXMLBuffer.append("</Password>\n");
		ipXMLBuffer.append("<Scope></Scope>\n");
		ipXMLBuffer.append("<UserExist>N</UserExist>\n");
		ipXMLBuffer.append("<Locale>en-us</Locale>\n");
		ipXMLBuffer.append("<ParticipantType>U</ParticipantType>\n");
		ipXMLBuffer.append("</Participant>");
		ipXMLBuffer.append("</WMConnect_Input>");
		return ipXMLBuffer.toString();
	}

	public String WFAddToMailQueue(String cabinetName, String sessionID, String pID, String fromEmail, String toMail,
			String activityID, String subject, String msg, String workitemID) {
		String inputXML = "<?xml version=\"1.0\"?><WFAddToMailQueue_Input><Option>WFAddToMailQueue</Option><EngineName>"
				+ cabinetName + "</EngineName>" + "<SessionId>" + sessionID
				+ "</SessionId><ProcessDefId>2</ProcessDefId><ActivityId>" + activityID
				+ "</ActivityId><ProcessInstanceId>" + pID + "</ProcessInstanceId>" + "<WorkItemId>" + workitemID
				+ "</WorkItemId><MailFrom>" + fromEmail + "</MailFrom><MailTo>" + toMail
				+ "</MailTo><MailCC></MailCC><MailSubject>" + subject + "</MailSubject>" + "<MailMessage>" + msg
				+ "</MailMessage></WFAddToMailQueue_Input>";

		return inputXML;
	}

	public static String get_NGOAddDocument_Input(String cabinetName, String sessionID, String ParentFolderIndex,
			int noOfPages, String sDocumentFullName, String docType, String docSize, String createdByAppName,
			String isIndex) {
		StringBuffer ipXMLBuffer = new StringBuffer();

		ipXMLBuffer.append("?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		ipXMLBuffer.append("<NGOAddDocument_Input>\n");
		ipXMLBuffer.append("<Option>NGOAddDocument</Option>\n");
		ipXMLBuffer.append("<CabinetName>");
		ipXMLBuffer.append(cabinetName);
		ipXMLBuffer.append("</CabinetName>\n");
		ipXMLBuffer.append("<UserDBId>");
		ipXMLBuffer.append(sessionID);
		ipXMLBuffer.append("</UserDBId>\n");
		ipXMLBuffer.append("<GroupIndex>0</GroupIndex>\n");
		ipXMLBuffer.append("<Document>\n");
		ipXMLBuffer.append("<ParentFolderIndex>");
		ipXMLBuffer.append(ParentFolderIndex);
		ipXMLBuffer.append("</ParentFolderIndex>\n");
		ipXMLBuffer.append("<NoOfPages>" + noOfPages + "</NoOfPages>\n");
		ipXMLBuffer.append("<AccessType>I</AccessType>\n");
		ipXMLBuffer.append("<DocumentName>");
		ipXMLBuffer.append(sDocumentFullName);
		ipXMLBuffer.append("</DocumentName>\n");
		ipXMLBuffer.append("<CreatedByAppName>" + createdByAppName + "</CreatedByAppName>\n");
		ipXMLBuffer.append("<ISIndex>");
		ipXMLBuffer.append(isIndex);
		ipXMLBuffer.append("</ISIndex>\n");
		ipXMLBuffer.append("<NoOfPages>" + noOfPages + "</NoOfPages>");
		ipXMLBuffer.append("<DocumentType>" + docType + "</DocumentType>\n");
		ipXMLBuffer.append("<DocumentSize>");
		ipXMLBuffer.append(docSize);
		ipXMLBuffer.append("</DocumentSize>\n");
		ipXMLBuffer.append("<ODMADocumentIndex></ODMADocumentIndex><Comment></Comment><EnableLog>Y</EnableLog>\n");
		ipXMLBuffer.append("<FTSFlag>PP</FTSFlag>");
		ipXMLBuffer.append("</Document>\n");
		ipXMLBuffer.append("</NGOAddDocument_Input>");
		LogMessages.statusLogLoans.info("get_NGOAddDocument_Input:: " + ipXMLBuffer.toString());
		return ipXMLBuffer.toString();
		
	}

	public static String get_NGODeleteDocument_Input(String cabinetName, String sessionID, String DocumentIndex,
			String ParentFolderIndex){
		StringBuffer ipXMLBuffer=new StringBuffer();
		
		ipXMLBuffer.append("?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		ipXMLBuffer.append("<NGODeleteDocument_Input>\n");
		ipXMLBuffer.append("<Option>NGODeleteDocument</Option>\n");
		ipXMLBuffer.append("<CabinetName>");
		ipXMLBuffer.append(cabinetName);
		ipXMLBuffer.append("</CabinetName>\n");
		ipXMLBuffer.append("<UserDBId>");
		ipXMLBuffer.append(sessionID);
		ipXMLBuffer.append("</UserDBId>\n");
		ipXMLBuffer.append("<DocumentIndex>");
		ipXMLBuffer.append(DocumentIndex);
		ipXMLBuffer.append("</DocumentIndex>\n");
		ipXMLBuffer.append("<ParentFolderIndex>");
		ipXMLBuffer.append(ParentFolderIndex);
		ipXMLBuffer.append("</ParentFolderIndex>\n");
		ipXMLBuffer.append("<ReferenceFlag>");
		ipXMLBuffer.append("Y");
		ipXMLBuffer.append("</ReferenceFlag>\n");
		ipXMLBuffer.append("</NGODeleteDocument_Input>");
		LogMessages.statusLogLoans.info("get_NGOAddDocument_Input:: " + ipXMLBuffer.toString());
		return ipXMLBuffer.toString();
	}
	
	public static String get_NGOAddDocument_Input(String cabinetName, String sessionID, String ParentFolderIndex,
			int noOfPages, String sDocumentFullName, String docType, String docSize, String createdByAppName,
			String isIndex, String sdataClassIndex) {

		String Tags = "";

		if (sdataClassIndex.equals("")) {
			// Nothing
		} else {
			// Tags = makeFieldTag(IndexID, IndexType, IndexValue);
		}

		String DataDefinition = "<DataDefIndex>" + sdataClassIndex + "</DataDefIndex><Fields>" + Tags + "</Fields>";

		String inputXml = "<?xml version=\"1.0\"?>" + "\n" + "<NGOAddDocument_Input>" + "\n"
				+ "<Option>NGOAddDocument</Option>" + "\n" + "<CabinetName>" + cabinetName + "</CabinetName>" + "\n"
				+ "<UserDBId>" + sessionID + "</UserDBId>" + "\n" + "<GroupIndex>0</GroupIndex>" + "\n" + "<Document>"
				+ "\n" + "<ParentFolderIndex>" + ParentFolderIndex + "</ParentFolderIndex>" + "\n" + "<NoOfPages>"
				+ noOfPages + "</NoOfPages>" + "\n" + "<AccessType>I</AccessType>" + "\n" + "<DocumentName>"
				+ sDocumentFullName + "</DocumentName>" + "\n" + "<CreationDateTime></CreationDateTime>" + "\n"
				+ "<DocumentType>" + docType + "</DocumentType>" + "\n" + "<DocumentSize>" + docSize + "</DocumentSize>"
				+ "\n" + "<CreatedByAppName>" + createdByAppName + "</CreatedByAppName>" + "\n" + "<ISIndex>" + isIndex
				+ "</ISIndex>" + "\n" + "<ODMADocumentIndex></ODMADocumentIndex>" + "\n" + "<Comment></Comment>" + "\n"
				+ "<EnableLog>Y</EnableLog>" + "\n" + "<FTSFlag>PP</FTSFlag>" + "\n" + "<DataDefinition>"
				+ DataDefinition + "</DataDefinition>" + "\n" + "<Keywords></Keywords>" + "\n" + "</Document>" + "\n"
				+ "</NGOAddDocument_Input>";
		LogMessages.statusLogLoans.info("get_NGOAddDocument_Input:: " + inputXml.toString());
		return inputXml;
	}

	public static String makeFieldTag(ArrayList<String> sIndexID, ArrayList<String> sIndexType,
			ArrayList<String> sIndexValue) {
		String TagXML = "";

		// System.out.println("sIndexID = " + sIndexID);
		// System.out.println("sIndexValue = " + sIndexValue);
		LogMessages.statusLog.info("sIndexID = " + sIndexID);
		LogMessages.statusLog.info("sIndexValue = " + sIndexValue);

		try {
			for (int i = 0; i < sIndexID.size(); i++) {
				TagXML = TagXML.concat("\n<Field>\n");
				TagXML = TagXML.concat("<IndexId>");
				TagXML = TagXML.concat((String) sIndexID.get(i));
				TagXML = TagXML.concat("</IndexId>\n");
				TagXML = TagXML.concat("<IndexType>");
				TagXML = TagXML.concat((String) sIndexType.get(i));
				TagXML = TagXML.concat("</IndexType>\n");
				TagXML = TagXML.concat("<IndexValue>");
				TagXML = TagXML.concat((String) sIndexValue.get(i));
				TagXML = TagXML.concat("</IndexValue>\n");
				TagXML = TagXML.concat("</Field>");
			}
		} catch (Exception e) {
			// System.out.println("Error in adding xml tag" + e.toString());
			LogMessages.statusLog.info("Error in adding xml tag", e);
		}

		return TagXML;
	}

}

