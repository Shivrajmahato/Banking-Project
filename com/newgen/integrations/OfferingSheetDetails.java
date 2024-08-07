/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.ConfProperty;
import com.newgen.common.LogMessages;
import com.newgen.iforms.util.CommonMethods;
import com.newgen.iforms.custom.IFormReference;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.util.TextUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
*
* @author bibek.shah
*
**/
public class OfferingSheetDetails {

    public String offeringSheetRequest(String input, IFormReference iFormObj) {

        String request = "";
        String[] param = input.split("#");
        try {
            LogMessages.statusLog.info("Inside create Request of LimitTreeDetails." + param[0]);
            //request = "{\"CustId\":\""+param[1]+"\"}";
            JSONObject finalRequest = new JSONObject();            
            JSONArray output=iFormObj.getDataFromGrid ("tblLimitTree");
            LogMessages.statusLog.info("############DATA FROM GRID ::::" + output.toString());
            String additionalRequestChild = "";
            StringBuffer sb;
            for (int i=0; i<output.size();i++){
                 JSONObject arrayJsonObject = (JSONObject) output.get(i);
                 
                 String additionalRequestData = String.valueOf(arrayJsonObject.get("AdditionalRequest"));
                  LogMessages.statusLog.info("############additionalRequestData ::::" + additionalRequestData);
                 
                if(!TextUtils.isEmpty(additionalRequestData)){
                    String limitType = String.valueOf(arrayJsonObject.get("LimitSuffix"));
                    additionalRequestChild = limitType+":"+additionalRequestData+"," + additionalRequestChild;                    
                }
            }
            sb= new StringBuffer(additionalRequestChild); 
            sb.deleteCharAt(sb.length()-1);  
            finalRequest.put("CustId",param[1]);
            finalRequest.put("SolId","ALL");
            finalRequest.put("UserId","KISHORG");
            finalRequest.put("AdditionalRequest", sb.toString());
            request = finalRequest.toJSONString();
            //request = "{\"CustId\":\"" + param[1] + "\",\"SolId\":\"ALL\",\"UserId\":\"KISHORG\",\"AdditionalRequest\":\"\"}";
            LogMessages.statusLog.info("############OfferingSheet REQUEST::::" + request);

        } catch (Exception e) {
            LogMessages.statusLog.info("Exception OfferingSheet Request: " + e);
        }
        return request;
    }

    public JSONArray offeringSheetResponse(String input, IFormReference iFormObj) {
        String[] param = input.split("##");
        LogMessages.statusLog.info("OfferingSheetRes BODY:" + param[1]);
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        if (param[0].equalsIgnoreCase("SUCCESS")) {
            JSONParser parser = new JSONParser();

            JSONObject jsonObj;
            try {
                jsonObj = (JSONObject) parser.parse(param[1]);
                String code = String.valueOf(jsonObj.get("Code"));
                if (code.equalsIgnoreCase("0")) {
                    String limitdetails = String.valueOf(((JSONObject) jsonObj.get("Data")).get("limitDetails"));
                    String CustomerName = String.valueOf(((JSONObject) jsonObj.get("Data")).get("CustomerName"));
                    String GroupName = String.valueOf(((JSONObject) jsonObj.get("Data")).get("GroupName"));
                    iFormObj.setValue("OS_customerName", CustomerName);
                    iFormObj.setValue("OS_groupName", GroupName);
                    LogMessages.statusLog.info("CustomerName: " + CustomerName);
                    LogMessages.statusLog.info("GroupName: " + GroupName);
                    JSONObject obj = (JSONObject) parser.parse(limitdetails);
                    JSONArray jsonArrayLimit = (JSONArray) obj.get("limitDetailsLsts");
                    JSONArray table1arr = new JSONArray();
                    if (jsonArrayLimit.size() > 0) {
                        for (int i = 0; i < jsonArrayLimit.size(); i++) {
                            JSONObject arrayJsonObject = (JSONObject) jsonArrayLimit.get(i);
                            JSONObject table1 = (JSONObject) new JSONObject();
                            table1.put("Limit Node", String.valueOf(arrayJsonObject.get("LimitSuffix")));
                            table1.put("Description", String.valueOf(arrayJsonObject.get("LimitDescription")));
                            table1.put("Approved Limits", String.valueOf(arrayJsonObject.get("ApprovedLines")));
                            table1.put("Present Outstanding", String.valueOf(arrayJsonObject.get("PresentOutstanding")));
                            table1.put("Additional Request", String.valueOf(arrayJsonObject.get("AdditionalRequest")));
                            table1.put("Total Incl Additonal request", String.valueOf(arrayJsonObject.get("TotalAdditionalRequest")));
                            table1.put("Excess/Actual OS", String.valueOf(arrayJsonObject.get("ExcessOver")));
                            table1arr.add(table1);
                            iFormObj.addDataToGrid("tblLimitsDetails", table1arr);
                            table1arr.clear();
                        }
                    }
                    
                    String limitDetailsLsts = obj.get("limitDetailsLsts").toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes LIMITDETAILSLIST: " + limitDetailsLsts);

                    
                    String unitPastDues = String.valueOf(((JSONObject) jsonObj.get("Data")).get("unitPastDues"));
                    JSONObject obj3 = (JSONObject) parser.parse(unitPastDues);
                    JSONArray jsonArrayUnitPastDues = (JSONArray) obj3.get("unitPastDuesLsts");
                    JSONArray table2arr = new JSONArray();
                    if (jsonArrayUnitPastDues.size() > 0) {
                        for (int i = 0; i < jsonArrayUnitPastDues.size(); i++) {
                            JSONObject arrayJsonObject = (JSONObject) jsonArrayUnitPastDues.get(i);
                            JSONObject table2 = (JSONObject) new JSONObject();
                            table2.put("A/c No", String.valueOf(arrayJsonObject.get("AccountNumber")));
                            table2.put("Disb Id/Bill No", String.valueOf(arrayJsonObject.get("DisbIdBillNo")));
                            table2.put("O/S LCY", String.valueOf(arrayJsonObject.get("OSLcy")));
                            table2.put("Overdue Since", CommonMethods.DateConverter(String.valueOf(arrayJsonObject.get("OverdueSince"))));
                            table2.put("Reason For Overdue", String.valueOf(arrayJsonObject.get("ReasonForOverdue")));
                            table2arr.add(table2);
                            iFormObj.addDataToGrid("tblUnitPastDues", table2arr);
                            table2arr.clear();
                        }
                    }
                    String unitPastDuesLsts = obj3.get("unitPastDuesLsts").toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes UNIT PAST DUES: " + unitPastDuesLsts);

                    
                    String funded = String.valueOf(((JSONObject) jsonObj.get("Data")).get("funded"));
                    LogMessages.statusLog.info("Exception funded: " + funded);
                    String fundedLsts;

                    if (funded.equalsIgnoreCase("null") || funded==null) {
                        fundedLsts = "";
                    } else {
                        JSONObject obj4 = (JSONObject) parser.parse(funded);
                        fundedLsts = obj4.get("fundedLsts").toString();
                        JSONArray jsonArrayNonFunded = (JSONArray) obj4.get("fundedLsts");
                        JSONArray table3arr = new JSONArray();
                        if (jsonArrayNonFunded.size() > 0) {
                            for (int i = 0; i < jsonArrayNonFunded.size(); i++) {
                                JSONObject arrayJsonObject = (JSONObject) jsonArrayNonFunded.get(i);
                                JSONObject table3 = (JSONObject) new JSONObject();
                                table3.put("Customer ID", String.valueOf(arrayJsonObject.get("CustId")));
                                table3.put("ID Type", String.valueOf(arrayJsonObject.get("IdType")));
                                table3.put("Funding Outstanding", String.valueOf(arrayJsonObject.get("FundedOutstanding")));
                                table3arr.add(table3);
                                iFormObj.addDataToGrid("tblFunded", table3arr);
                                table3arr.clear();
                            }
                        }
                    }

                    LogMessages.statusLog.info("Exception OfferingSheetRes FUNDED LIST: " + fundedLsts);

                    String nonFunded = String.valueOf(((JSONObject) jsonObj.get("Data")).get("nonFunded"));
                    JSONObject obj5 = (JSONObject) parser.parse(nonFunded);
                    JSONArray jsonArrayNonFunded = (JSONArray) obj5.get("nonFundedLsts");
                    JSONArray table4arr = new JSONArray();
                    if (jsonArrayNonFunded.size() > 0) {
                        for (int i = 0; i < jsonArrayNonFunded.size(); i++) {
                            JSONObject arrayJsonObject = (JSONObject) jsonArrayNonFunded.get(i);
                            JSONObject table4 = (JSONObject) new JSONObject();
                            table4.put("Customer ID", String.valueOf(arrayJsonObject.get("CustId")));
                            table4.put("ID Type", String.valueOf(arrayJsonObject.get("IdType")));
                            table4.put("Non Funding Outstanding", String.valueOf(arrayJsonObject.get("NonFundedOutstanding")));
                            table4arr.add(table4);
                            iFormObj.addDataToGrid("tblNonFunded", table4arr);
                            table4arr.clear();
                        }
                    }
                    String nonFundedLsts = obj5.get("nonFundedLsts").toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes: NON FUNDED LIST " + nonFundedLsts);

                    
                    
                    String totalExposure = String.valueOf(((JSONObject) jsonObj.get("Data")).get("totalExposure"));
                    JSONObject obj6 = (JSONObject) parser.parse(totalExposure);
                    JSONArray jsonArrayTotalExposure = (JSONArray) obj6.get("totalExposureLsts");
                    JSONArray table5arr = new JSONArray();
                    if (jsonArrayTotalExposure.size() > 0) {
                        for (int i = 0; i < jsonArrayTotalExposure.size(); i++) {
                            JSONObject arrayJsonObject = (JSONObject) jsonArrayTotalExposure.get(i);
                            JSONObject table5 = (JSONObject) new JSONObject();
                            table5.put("Total Group Limit", String.valueOf(arrayJsonObject.get("Limit")));
                            table5.put("Funded", String.valueOf(arrayJsonObject.get("Funded")));
                            table5.put("Non Funded", String.valueOf(arrayJsonObject.get("NonFunded")));
                            table5.put("Total", String.valueOf(arrayJsonObject.get("Total")));
                            table5.put("Excess", String.valueOf(arrayJsonObject.get("Excess")));
                            table5.put("Actual OD", String.valueOf(arrayJsonObject.get("ActualOd")));
                            table5.put("Lien Amount", String.valueOf(arrayJsonObject.get("LienAmount")));
                            table5.put("Actual Limit", String.valueOf(arrayJsonObject.get("ActualLimit")));
                            table5arr.add(table5);
                            iFormObj.addDataToGrid("tblTotalExposure", table5arr);
                            table5arr.clear();
                        }
                    }
                    
                    String totalExposureLsts = obj6.get("totalExposureLsts").toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes: TOTAL EXPOSURE " + totalExposureLsts);

                    String groupPastDues = String.valueOf(((JSONObject) jsonObj.get("Data")).get("groupPastDues"));
                    JSONObject obj7 = (JSONObject) parser.parse(groupPastDues);
                    JSONArray jsonArrayGroupPastDues = (JSONArray) obj7.get("groupPastDuesLsts");
                    JSONArray table6arr = new JSONArray();
                    if (jsonArrayGroupPastDues.size() > 0) {
                        for (int i = 0; i < jsonArrayGroupPastDues.size(); i++) {
                            JSONObject arrayJsonObject = (JSONObject) jsonArrayGroupPastDues.get(i);
                            JSONObject table6 = (JSONObject) new JSONObject();
                            table6.put("Customer ID", String.valueOf(arrayJsonObject.get("CustId")));
                            table6.put("A/c No", String.valueOf(arrayJsonObject.get("AccountNumber")));
                            table6.put("Disb Id/Bill No", String.valueOf(arrayJsonObject.get("DisbIdBillNo")));
                            table6.put("O/S LCY", String.valueOf(arrayJsonObject.get("OSLcy")));
                            table6.put("Overdue Since", CommonMethods.DateConverter(String.valueOf(arrayJsonObject.get("OverdueSince"))));
                            table6.put("AccountSbu", String.valueOf(arrayJsonObject.get("AccountSbu")));
                            table6.put("Reason for Overdue", String.valueOf(arrayJsonObject.get("ReasonForOverdue")));
                            table6arr.add(table6);
                            iFormObj.addDataToGrid("tblGroupPastDues", table6arr);
                            table6arr.clear();
                        }
                    }
                    
                    String groupPastDuesLsts = obj7.get("groupPastDuesLsts").toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes: GROUP PAST DUES " + groupPastDuesLsts);

                    
                    String groupUnits = String.valueOf(((JSONObject) jsonObj.get("Data")).get("groupUnits"));
                    JSONObject obj8 = (JSONObject) parser.parse(groupUnits);
                    JSONArray jsonArrayGroupUnitList = (JSONArray) obj8.get("groupUnitsLsts");
                    JSONArray table7arr = new JSONArray();
                    if (jsonArrayGroupUnitList.size() > 0) {
                        for (int i = 0; i < jsonArrayGroupUnitList.size(); i++) {
                            JSONObject arrayJsonObject = (JSONObject) jsonArrayGroupUnitList.get(i);
                            JSONObject table7 = (JSONObject) new JSONObject();
                            table7.put("Cust ID", String.valueOf(arrayJsonObject.get("CustId")));
                            table7.put("Unit Name", String.valueOf(arrayJsonObject.get("UnitName")));
                            table7.put("CustSbu", String.valueOf(arrayJsonObject.get("CustSbu")));
                            table7.put("CustRo", String.valueOf(arrayJsonObject.get("CustRo")));
                            table7arr.add(table7);
                            iFormObj.addDataToGrid("tblGroupUnits", table7arr);
                            table7arr.clear();
                        }
                    }
                    
                    String groupUnitsLsts = obj8.get("groupUnitsLsts").toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes: GROUP UNIT LIST " + groupUnitsLsts);

                    res.put("limitDetailsLsts", limitDetailsLsts);
                    res.put("unitPastDuesLsts", unitPastDuesLsts);
                    res.put("FundedLsts", fundedLsts);
                    res.put("nonFundedLsts", nonFundedLsts);
                    res.put("totalExposureLsts", totalExposureLsts);
                    res.put("groupPastDuesLsts", groupPastDuesLsts);
                    res.put("groupUnitsLsts", groupUnitsLsts);
                    res.put("status", "SUCCESS");
                    response.add(res);
                } else {
                    res.put("status", "FAILURE");
                    String message = String.valueOf(jsonObj.get("Message"));
                    res.put("message", message);
                    response.add(res);
                }
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception TRY CATCH retLimitTreeDetailsRes: " + e);
            }

        } else {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[1]);
                String error = String.valueOf(obj);
                res.put("status", error);
                LogMessages.statusLog.info("else retLimitTreeDetailsRes");
                response.add(res);
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception retLimitTreeDetailsRes: " + e);
            }

        }
        return response;
    }
}
