/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.util;

import com.newgen.common.LogMessages;
import com.newgen.omni.wf.util.app.NGEjbClient;

public class XMLGeneration {

    public String getSessionID(String cabName, String jtsip, String jtsPort, String userName, String password) {
        String connectOutputXML = null;
        String connectInputXML = getConnectInputXML(cabName, userName, password);
        LogMessages.statusLog.info(("connectInputXML : " + connectInputXML));
        LogMessages.statusLog
                .info(("connectInputXML 2 : " + "jtsip=" + jtsip + " jtsPort=" + Integer.parseInt(jtsPort)));
        LogMessages.statusLog.info("*** connectInputXML call from OtherUtil ends *** ");

        int loopCount = 50;
        int sessionCheckInt = 0;
        while (sessionCheckInt < loopCount) {
            try {
                LogMessages.statusLog.info("*** connectOutputXML    ***");
                connectOutputXML = wfngExecute(connectInputXML, jtsip, Integer.parseInt(jtsPort));
                LogMessages.statusLog.info("connectOutputXML    : ");
                LogMessages.statusLog.info("connectOutputXML    : " + connectOutputXML);
                break;
            } catch (Exception e) {
                LogMessages.statusLog
                        .info("Exception in Execute of getsession ID: " + e + "\nNumber " + sessionCheckInt);
                sessionCheckInt++;
            }
        }

        XMLParser lobjXMLParser = new XMLParser();
        lobjXMLParser.setInputXML(connectOutputXML);
        String s6 = lobjXMLParser.getValueOf("MainCode");
        int i = Integer.parseInt(s6);
        String desc = null;
        if (i == 0) {
            return lobjXMLParser.getValueOf("SessionId");
        } else {
            desc = lobjXMLParser.getValueOf("Description");
        }

        return "Error " + desc;
    }

    public static String wfngExecute(String ipXML, String serverIP, int serverPort) {
        LogMessages.statusLog.info("SERVER IP: " + serverIP + "SERVER PORT; " + serverPort);
        String portNo = Integer.toString(serverPort);
        LogMessages.statusLog.info("portNo.startsWith('33')=" + portNo.startsWith("33"));
        try {
            LogMessages.statusLog.info("***** Calling NGEJBCLIENT *****");
            NGEjbClient ngEjbClient = NGEjbClient.getSharedInstance();
            return ngEjbClient.makeCall(serverIP, String.valueOf(serverPort), "JBOSSEAP", ipXML);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception found in WFNGExecute of XMLGeneration", e);
        }
        return "";
    }

    public static String getConnectInputXML(String cabinetName, String username, String password) {
        StringBuilder ipXMLBuffer = new StringBuilder();
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

    public static String getNGOAddDocumentInput(NGOAddDocumentInputData data) {
        StringBuilder ipXMLBuffer = new StringBuilder();
        ipXMLBuffer.append("?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        ipXMLBuffer.append("<NGOAddDocument_Input>\n");
        ipXMLBuffer.append("<Option>NGOAddDocument</Option>\n");
        ipXMLBuffer.append("<CabinetName>");
        ipXMLBuffer.append(data.getCabinetName());
        ipXMLBuffer.append("</CabinetName>\n");
        ipXMLBuffer.append("<UserDBId>");
        ipXMLBuffer.append(data.getSessionID());
        ipXMLBuffer.append("</UserDBId>\n");
        ipXMLBuffer.append("<GroupIndex>0</GroupIndex>\n");
        ipXMLBuffer.append("<Document>\n");
        ipXMLBuffer.append("<parentFolderIndex>");
        ipXMLBuffer.append(data.getParentFolderIndex());
        ipXMLBuffer.append("</parentFolderIndex>\n");
        ipXMLBuffer.append("<NoOfPages>" + data.getNoOfPages() + "</NoOfPages>\n");
        ipXMLBuffer.append("<AccessType>I</AccessType>\n");
        ipXMLBuffer.append("<DocumentName>");
        ipXMLBuffer.append(data.getsDocumentFullName());
        ipXMLBuffer.append("</DocumentName>\n");
        ipXMLBuffer.append("<CreatedByAppName>" + data.getCreatedByAppName() + "</CreatedByAppName>\n");
        ipXMLBuffer.append("<ISIndex>");
        ipXMLBuffer.append(data.getIsIndex());
        ipXMLBuffer.append("</ISIndex>\n");
        ipXMLBuffer.append("<NoOfPages>" + data.getNoOfPages() + "</NoOfPages>");
        ipXMLBuffer.append("<DocumentType>" + data.getDocType() + "</DocumentType>\n");
        ipXMLBuffer.append("<DocumentSize>");
        ipXMLBuffer.append(data.getDocSize());
        ipXMLBuffer.append("</DocumentSize>\n");
        ipXMLBuffer.append("<ODMADocumentIndex></ODMADocumentIndex><Comment></Comment><EnableLog>Y</EnableLog>\n");
        ipXMLBuffer.append("<FTSFlag>PP</FTSFlag>");
        ipXMLBuffer.append("</Document>\n");
        ipXMLBuffer.append("</NGOAddDocument_Input>");
        LogMessages.statusLogLoans.info("get_NGOAddDocument_Input:: " + ipXMLBuffer.toString());

        return ipXMLBuffer.toString();
    }

    public static String getNGODeleteDocumentInput(String cabinetName, String sessionID, String documentIndex, String parentFolderIndex) {
        StringBuilder ipXMLBuffer = new StringBuilder();
        ipXMLBuffer.append("?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        ipXMLBuffer.append("<NGODeleteDocument_Input>\n");
        ipXMLBuffer.append("<Option>NGODeleteDocument</Option>\n");
        ipXMLBuffer.append("<CabinetName>");
        ipXMLBuffer.append(cabinetName);
        ipXMLBuffer.append("</CabinetName>\n");
        ipXMLBuffer.append("<UserDBId>");
        ipXMLBuffer.append(sessionID);
        ipXMLBuffer.append("</UserDBId>\n");
        ipXMLBuffer.append("<documentIndex>");
        ipXMLBuffer.append(documentIndex);
        ipXMLBuffer.append("</documentIndex>\n");
        ipXMLBuffer.append("<parentFolderIndex>");
        ipXMLBuffer.append(parentFolderIndex);
        ipXMLBuffer.append("</parentFolderIndex>\n");
        ipXMLBuffer.append("<ReferenceFlag>");
        ipXMLBuffer.append("Y");
        ipXMLBuffer.append("</ReferenceFlag>\n");
        ipXMLBuffer.append("</NGODeleteDocument_Input>");
        LogMessages.statusLogLoans.info("get_NGOAddDocument_Input:: " + ipXMLBuffer.toString());

        return ipXMLBuffer.toString();
    }

}

