package com.newgen.integrations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;

/**
*
* @author KRISHNA NAND
*
**/

public class SanctionLimit {
	
	public String sanctionLimitDetailsReq(String input, IFormReference iFormObj) {
        String request = "";
        String[] param = input.split("#");
        try {
            LogMessages.statusLog.info("Inside create Request of sanctionLimitDetailsReq." + param[0]);
            request = "{\"CustId\":\"" + param[1] + "\"}";
            LogMessages.statusLog.info("Request sanctionLimitDetailsReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception sanctionLimitDetailsReq:  " + e);
        }
        return request;
    }

        public JSONArray sanctionLimitDetailsRes(String input) {
        String[] param = input.split("##");
         LogMessages.statusLog.info("sanctionLimitDetailsRes BODY:" + param[1]);
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        if(param[0].equalsIgnoreCase("SUCCESS")){
            JSONParser parser = new JSONParser();
            JSONObject obj;
            try {
                obj = (JSONObject) parser.parse(param[1]);
                String code = String.valueOf(obj.get("Code"));
                if (code.equalsIgnoreCase("0")) {
                    String message = String.valueOf(obj.get("Message"));
                    if (message.equalsIgnoreCase("SUCCESS")) {
                        String AccountList = String.valueOf(((JSONObject) obj.get("Data")).get("AccountList"));
                        JSONArray arr = (JSONArray) parser.parse(AccountList);
                        if (!arr.isEmpty()) {
                            res.put("tblFacilitiesRetail", AccountList);
                            res.put("Message", obj.get("Message"));
                            response.add(res);
                        } else {
                            res.put("Message", "FAILURE");
                            String messageA = String.valueOf(obj.get("Message"));
                            res.put("Message", messageA);
                            response.add(res);
                        }
                    } else {
                        res.put("Message", "FAILURE");
                        String messageA = String.valueOf(obj.get("Message"));
                        res.put("Message", messageA);
                        response.add(res);
                    }
                } else {
                    res.put("Message", "FAILURE");
                    String messageA = String.valueOf(obj.get("Message"));
                    res.put("Message", messageA);
                    response.add(res);
                }

            } catch (Exception e) {
                LogMessages.statusLog.info("Exception occure during Conversion Response:  " + e);
            }
        } else {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[0]);
                String error = String.valueOf(obj);
                res.put("Message", error);
                LogMessages.statusLog.info("else Sanction Limit Details Response");
                response.add(res);
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception Sanction Limit Details Response:  " + e);
            }
        }
        return response;
    }


}
