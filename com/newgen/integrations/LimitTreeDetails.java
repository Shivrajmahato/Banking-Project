/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.ConfProperty;
import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.hornetq.utils.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
*
* @author bibek.shah
*
**/
public class LimitTreeDetails {

    public String limitTreeRequest(String input, IFormReference iFormObj) {

        String request = "";
        String[] param = input.split("#");
        JSONObject finalRequest = new JSONObject();  
        try {
            
            finalRequest.put("CustId",param[1]);
//            finalRequest.put("SolId","ALL");
//            finalRequest.put("UserId","KISHORG");
            LogMessages.statusLog.info("Inside create Request of LimitTreeDetails." + param[0]);
            request = finalRequest.toJSONString();
            //request = "{\"CustId\":\"" + param[1] + "\"}";
            LogMessages.statusLog.info("############LimitTreeDetails REQUEST::::" + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LimitTreeDetailsReq: " + e);
        }
        return request;
    }
    
     public JSONArray limitTreeResponse(String input, IFormReference iFormObj)  throws JSONException{
        String[] param = input.split("##");
        LogMessages.statusLog.info("OfferingSheetRes BODY:" + param[1]);
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        if (param[0].equalsIgnoreCase("SUCCESS")) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObj ;
            try {
                jsonObj = (JSONObject) parser.parse(param[1]);
                String code = String.valueOf(jsonObj.get("Code"));
                if (code.equalsIgnoreCase("0")) {
                    JSONArray jsonArray =  (JSONArray)jsonObj.get("Data");
                    JSONArray tableArray = new JSONArray();
                    if (jsonArray.size() > 0) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject arrayJsonObject = (JSONObject) jsonArray.get(i);
                            JSONObject tableJsonObject = (JSONObject) new JSONObject();
                            tableJsonObject.put("LimitSuffix", String.valueOf(arrayJsonObject.get("LimitSuffix")));
                            tableJsonObject.put("LimitB2kId", String.valueOf(arrayJsonObject.get("LimitB2kId")));
                            tableJsonObject.put("ParentLimitB2kId", String.valueOf(arrayJsonObject.get("ParentLimitB2kId")));
                            tableJsonObject.put("LimitDescription", String.valueOf(arrayJsonObject.get("LimitDescription")));
                            tableJsonObject.put("SanctionLimit", String.valueOf(arrayJsonObject.get("SanctionLimit")));
                            tableJsonObject.put("DrawingPower", String.valueOf(arrayJsonObject.get("DrawingPower")));
                            tableJsonObject.put("Liability", String.valueOf(arrayJsonObject.get("Liability")));
                            tableJsonObject.put("ContingentLiability", String.valueOf(arrayJsonObject.get("ContingentLiability")));
                            tableJsonObject.put("NumberOfAccounts", String.valueOf(arrayJsonObject.get("NumberOfAccounts")));
                            tableJsonObject.put("CurrencyCode", String.valueOf(arrayJsonObject.get("CurrencyCode")));
                            tableJsonObject.put("LimitSanctionedDate", String.valueOf(arrayJsonObject.get("LimitSanctionedDate")));
                            tableJsonObject.put("LimitExpiryDate", String.valueOf(arrayJsonObject.get("LimitExpiryDate")));
                            tableJsonObject.put("Remarks", String.valueOf(arrayJsonObject.get("Remarks")));
                            tableArray.add(tableJsonObject);
                            iFormObj.addDataToGrid("tblLimitTree", tableArray);
                            tableArray.clear();
                        }
                    }
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
