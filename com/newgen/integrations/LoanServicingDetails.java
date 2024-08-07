/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author KRISHNA NAND
 */
public class LoanServicingDetails {
    
     public String loanServicingReq(String input, IFormReference iFormObj) {
        String request = "";
        String[] param = input.split("#");
        try {
            LogMessages.statusLog.info("Inside create Request of Loan Servicing." + param[0]);
            //request = "{\"RetCustInqRequest\":{\"RetCustInqRq\":{\"CustId\":\"" + param[0] + "\"}}}";
            request = "{\"CustomerId\":\"" + param[1] + "\"}";
            LogMessages.statusLog.info("Request Loan Servicing:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception Loan Servicing:  " + e);
        }
        return request;
    }

       public JSONArray loanDetails(String input) {
        LogMessages.statusLog.info("Loan Servicing BODY:" + input);
        String[] param = input.split("##");
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        if (param[0].equalsIgnoreCase("SUCCESS")) {
            JSONParser parser = new JSONParser();
            JSONObject obj;
            try {
                obj = (JSONObject) parser.parse(param[1]);
                String code = String.valueOf(obj.get("Code"));
                if (code.equalsIgnoreCase("0")) {
                    String message = String.valueOf(obj.get("Message"));
                    if (message.equalsIgnoreCase("SUCCESS")) {
                        String Accounts = String.valueOf(((JSONObject) obj.get("Data")).get("Accounts"));
                        if (!Accounts.isEmpty()) {
                            res.put("TblLoanDetails", Accounts);
                            res.put("Message", obj.get("Message"));
                            response.add(res);
                        }
                    } else {
                        res.put("Message", "FAILURE");
                        String messageA = String.valueOf(obj.get("Message"));
                        res.put("Message", messageA);
                        response.add(res);
                    }
                    //LogMessages.statusLog.info("Exception OfferingSheetRes: GROUP UNIT LIST " + groupUnitsLsts);
                } else {
                    res.put("Message", "FAILURE");
                    String CodeA = String.valueOf(obj.get("Code"));
                    res.put("Message", CodeA);
                    response.add(res);
                }

            } catch (Exception e) {
                System.out.println("Error Occcure in Loan Servicing : " + e);
            }

        } else {

            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[0]);
                String error = String.valueOf(obj);
                res.put("Message", error);
                LogMessages.statusLog.info("else Loan Servicing Response");
                response.add(res);
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception Loan Servicing Response:  " + e);
            }

        }
        return response;
    }
    
}
