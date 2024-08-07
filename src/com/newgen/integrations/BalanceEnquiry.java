package com.newgen.integrations;

/**
 * @author bibek.shah
 **/


import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtilConstants.*;

public class BalanceEnquiry {

    public String balanceEnquiryReq(String[] param) {
        String request = "";
        try {
            request = "{\"AcctInqRequest\":{\"AcctInqRq\":{\"AcctId\":{\"AcctId\":\"" + param[1] + "\"}}}}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception balanceEnquiryReq:  " + e);
        }
        LogMessages.statusLog.info("Request balanceEnquiryReq:  " + request);
        return request;
    }

    public String balanceEnquiryRes(String[] param) {
        String response = "";
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String acctInqResponse = String.valueOf(((JSONObject) obj.get("Data")).get("AcctInqResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(acctInqResponse);
                    String acctBal = String.valueOf(((JSONObject) obj2.get("AcctInqRs")).get("AcctBal"));
                    JSONArray arr = NewGenUtil.getParsedJsonArray(acctBal);
                    for (int i = 0; i < arr.size(); i++) {
                        JSONObject obj1 = NewGenUtil.getParsedJsonObj(String.valueOf(arr.get(i)));
                        if (String.valueOf(obj1.get("BalType")).equalsIgnoreCase("EFFAVL")) {
                            response = String.valueOf(((JSONObject) obj1.get("BalAmt")).get("amountValue"));
                        }
                    }
                } else {
                    response = "0";
                }
            } else {
                response = String.valueOf(obj);
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception balanceEnquiryRes:  " + e);
        }
        LogMessages.statusLog.info("Response balanceEnquiryRes: " + response);
        return response;
    }

}