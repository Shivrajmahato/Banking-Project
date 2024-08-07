package com.newgen.integrations;

/**
 * @author bibek.shah
 **/


import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;

public class AccountEnquiry {

    public String accountEnquiryReq(String[] param) {
        String request = "";
        try {
            request = "{\"TransactionId\":\"" + param[0] + "\",\"AccountNumber\":\"" + param[1] + "\"}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception accountEnquiryReq:  " + e);
        }
        LogMessages.statusLog.info("Request accountEnquiryReq:  " + request);
        return request;
    }

    public String accountEnquiryRes(String[] param) {
        String response = "";
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    response = String.valueOf(obj.get("AccountName"));
                } else {
                    response = "-1";
                }
            } else {
                response = String.valueOf(obj);
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception accountEnquiryRes:  " + e);
        }
        LogMessages.statusLog.info("Response accountEnquiryRes:  " + response);
        return response;
    }

}