/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
*
* @author bibek.shah
*
**/
public class LoanOverdue {

    public String loanOverdueAccountInquiryReq(String input, IFormReference iFormObj) {
        String request = "";
        String[] param = input.split("#", -1);
        try {
            LogMessages.statusLog.info("Inside create Request of loanOverdueAccountInquiryReq." + param[0]);
            //request ="{\"RetCustInqRequest\":{\"RetCustInqRq\":{\"CustId\":\""+param[1]+"\"}}}";
            request = "{\"AccountNumber\":\"" + param[1] + "\"}";

            LogMessages.statusLog.info("Request loanOverdueAccountInquiryReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanOverdueAccountInquiryReq:  " + e);
        }
        return request;
    }

    public String loanOverdueAccountInquiryRes(String input) {
        String[] param = input.split("##");
        LogMessages.statusLog.info("loanOverdueAccountInquiryRes BODY:" + param[1]);
        String res = "";
        if (param[0].equalsIgnoreCase("SUCCESS")) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[1]);
                String code = String.valueOf(obj.get("Code"));
                String message = String.valueOf(obj.get("Message"));
                if (code.equalsIgnoreCase("0")) {
                    String freezestat = String.valueOf(((JSONObject) obj.get("Data")).get("NomineeFreezeStatus"));
                    res = "SUCCESS" + "#" + param[1]+"#"+freezestat;
                } else {
                    res = "FAILURE" + "#" + message;
                }
                LogMessages.statusLog.info("loanOverdueAccountInquiryRes Success" + message);
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception loanOverdueAccountInquiryRes:  " + e);
            }

        } else {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[1]);
                String error = String.valueOf(obj);;
                LogMessages.statusLog.info("else loanOverdueAccountInquiryRes");
                res = "FAILURE" + "#" + error;
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception loanOverdueAccountInquiryRes:  " + e);
            }

        }
        return res;
    }
}
