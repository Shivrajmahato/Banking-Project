/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtilConstants.*;

/**
 * @author KRISHNA NAND
 */
public class LoanServicingDetails {

    public static final String MESSAGE = "Message";

    public String loanServicingReq(String[] param) {
        String request = "";
        try {
            request = "{\"CustomerId\":\"" + param[1] + "\"}";

        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanServicingReq:  " + e);
        }
        LogMessages.statusLog.info("Request loanServicingReq:  " + request);
        return request;
    }

    public JSONArray loanDetails(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String message = String.valueOf(obj.get(MESSAGE));
                    if (message.equalsIgnoreCase(SUCCESS)) {
                        String accounts = String.valueOf(((JSONObject) obj.get("Data")).get("Accounts"));
                        if (!accounts.isEmpty()) {
                            res.put("TblLoanDetails", accounts);
                            res.put(MESSAGE, obj.get(MESSAGE));
                        }
                    } else {
                        res.put(MESSAGE, NewGenUtilConstants.FAILURE);
                        res.put(MESSAGE, String.valueOf(obj.get(MESSAGE)));
                    }
                } else {
                    res.put(MESSAGE, NewGenUtilConstants.FAILURE);
                    res.put(MESSAGE, String.valueOf(obj.get(CODE)));
                }
            } else {
                res.put(MESSAGE, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanDetails:  " + e);
        }
        LogMessages.statusLog.info("Response loanDetails:  " + response);
        return response;
    }

}
