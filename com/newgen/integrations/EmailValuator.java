package com.newgen.integrations;

import com.newgen.common.Logging_LOS;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.util.XMLGeneration;
import com.newgen.iforms.util.XMLParser;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EmailValuator
{
  Logging_LOS logObj = new Logging_LOS();

  public String sendEmail(String stringData, IFormReference iFormObj)
  {
    this.logObj.servicelog("Inside Email trigger");
    String ab ="";
    String maincode = "";
    XMLGeneration generation = new XMLGeneration();
    String sessionid = "";
    String subject = ""; String msg = "";
    String cabinetName = iFormObj.getCabinetName();
    this.logObj.servicelog(cabinetName);

    String serverIP = iFormObj.getServerIp();
    this.logObj.servicelog(serverIP);

    String serverPort = iFormObj.getServerPort();
    this.logObj.servicelog(serverPort);

    String username = "padmin";
    String password = "system123#";
    String activityID = iFormObj.getActivityId();
    this.logObj.servicelog(activityID);

    JSONArray output = iFormObj.getDataFromGrid("table159");

    this.logObj.servicelog("table159:: " + output.toString());

    for (int i = 0; i < output.size(); i++) {
      JSONObject jsonObject = new JSONObject();
      jsonObject = (JSONObject)output.get(i);

      String nameO = jsonObject.get("Name of Owner").toString();
      this.logObj.servicelog("Name of Owner:: " + nameO);
      String property = jsonObject.get("Property").toString();
      this.logObj.servicelog("Property:: " + property);
      String wardNo = jsonObject.get("Ward No.").toString();
      this.logObj.servicelog("Ward No.:: " + wardNo);
      String muniVDC = jsonObject.get("Municipality VDC").toString();
      this.logObj.servicelog("Municipality VDC:: " + muniVDC);
      String district = jsonObject.get("District").toString();
      this.logObj.servicelog("District:: " + district);
      String plotNo = jsonObject.get("Plot No.").toString();
      this.logObj.servicelog("Plot No.:: " + plotNo);
      String area = jsonObject.get("Area").toString();
      this.logObj.servicelog("Area:: " + area);
      String vName = jsonObject.get("Name").toString();
      this.logObj.servicelog("Name:: " + vName);
      String address = jsonObject.get("Address").toString();
      this.logObj.servicelog("Address:: " + address);
      String cPerson = iFormObj.getValue("Contact Person").toString();
      this.logObj.servicelog("Contact Person:: " + cPerson);
      String cNo = iFormObj.getValue("Contact Number").toString();
      this.logObj.servicelog("Contact no:: " + cNo);
      String vID = iFormObj.getValue("Valuation Id").toString();
      this.logObj.servicelog("Valuation Id:: " + vID);
      String vlId = iFormObj.getValue("Valuer Id").toString();
      this.logObj.servicelog("Valuer Id:: " + vlId);
      String iRRD = iFormObj.getValue("Initial Report Request Date").toString();
      this.logObj.servicelog("Initial Report Request Date:: " + iRRD);
      String fRRD = iFormObj.getValue("Final Report Request Date").toString();
      this.logObj.servicelog("Final Report Request Date:: " + fRRD);
      String fRSD = iFormObj.getValue("Final Report Submission Date").toString();
      this.logObj.servicelog("Final Report Submission Date:: " + fRSD);
      String rRD = jsonObject.get("Initial Report Submission Date").toString();
      this.logObj.servicelog("Initial Report Submission Date:: " + rRD);
      String vType = jsonObject.get("Valutation Type").toString();
      this.logObj.servicelog("Valutation Type:: " + vType);
      String vemail = jsonObject.get("Valuator Email").toString();
      this.logObj.servicelog("Valuator Email:: " + vemail);

      String name = (String)iFormObj.getValue("Company");
      String rmName = (String)iFormObj.getValue("RMName");
      String bname = (String)iFormObj.getValue("HBranchName");
      String rmemail = (String)iFormObj.getValue("RMEmail");

      String bmname = (String)iFormObj.getValue("RMName");
      try
      {
        sessionid = generation.getSessionID(cabinetName, serverIP, serverPort, username, password);
        this.logObj.servicelog("session ID: " + sessionid);

        String workitemID = getWorkitemID(iFormObj, stringData);

        subject = "Valuation Job Assignment ";
        msg = "Date: 05.02.2015\r\n\r\n" + 
          vName + "\r\n" + 
          address + ",\r\n" + 
          "Nepal.\r\n" + 
          "Attention: " + cPerson + "," + cNo + "\r\n" + 
          "\r\n" + 
          "\r\n" + 
          "Confidential\r\n" + 
          "Re.: Valuation of " + property + " \r\n" + 
          "\r\n" + 
          "Dear Sir/Madam, \r\n" + 
          "\r\n" + 
          "Mr/Miss/Mrs " + name + " has submitted a proposal for availing Credit Facilities at our Bank and proposed the following Assets as collateral security:\r\n" + 
          "\r\n" + 
          "\r\n Type of Assets:" + property + 
          "\r\n Location:" + muniVDC + "-" + wardNo + "," + district + 
          "\r\n Plot No. :" + plotNo + 
          "\r\n Area:" + area + 
          "\r\n Owned by:" + nameO + 
          "\r\n" + 
          "We therefore request you to carry out the valuation of the above asset(s). \r\n" + 
          "\r\n" + 
          "Thank you.\r\n" + 
          "\r\n" + 
          "Yours Sincerely,\r\n" + 
          "\r\n" + 
          "Name:" + rmName + "\t\t\t\t\t\tName: " + bmname + "\r\n" + 
          "Relationship Officer\t\t\t\t\t\tBusiness Unit Head / Branch Manager\r\n" + 
          bname + "\r\n" + 
          "NIC Asia Bank Ltd.\t\t\t\t\t\tNIC Asia Bank Ltd.\r\n" + 
          "Tel/Mobile No : ���\r\n" + 
          "Email:" + rmemail + "\r\n";

        if ((sessionid.equalsIgnoreCase("")) || (sessionid.equalsIgnoreCase("null")) || 
          (sessionid.toLowerCase().contains("Error".toLowerCase()))) {
          this.logObj.servicelog("Unable to connect to server, some problem occurred.");
        }
        else {
          String inputXML = generation.WFAddToMailQueue(cabinetName, sessionid, stringData, 
            "noreply@nicasiabank.com", vemail, activityID, subject, msg, workitemID);
          this.logObj.servicelog("inputXML:: " + inputXML);

          String output_str = XMLGeneration.WFNGExecute(inputXML, serverIP, 
            Integer.parseInt(serverPort), 1);
          this.logObj.servicelog("Output::" + output_str);

          XMLParser lobjXMLParser = new XMLParser();
          lobjXMLParser.setInputXML(output_str);
          maincode = lobjXMLParser.getValueOf("MainCode");
          this.logObj.servicelog("MAINCODE:: " + maincode);
        }
      }
      catch (Exception e)
      {
        this.logObj.servicelog(e.getMessage());
      }
    }

    return maincode;
  }

  private String getWorkitemID(IFormReference iFormObj, String stringData)
  {
    String qryWID = "Select WorkItemId from WFINSTRUMENTTABLE where processInstanceId = '" + stringData + "'";
    this.logObj.servicelog("DedupeQuery is: " + qryWID);

    List list_wid = iFormObj.getDataFromDB(qryWID);

    String res = "";
    if (!list_wid.isEmpty()) {
      res = (String)((List)list_wid.get(0)).get(0);
    }

    return res;
  }

  private String getClientName(IFormReference iFormObj, String stringData)
  {
    String qry = "select ClientNamePD from Ext_Sme where flag2 = '" + stringData + "'";
    this.logObj.servicelog("ClientName query is: " + qry);

    List list_name = iFormObj.getDataFromDB(qry);
    //this.logObj.servicelog(list_name.size());

    this.logObj.servicelog(list_name.get(0).toString());

    if (list_name.size() == 0) {
      return "";
    }
    return list_name.get(0).toString();
  }
}