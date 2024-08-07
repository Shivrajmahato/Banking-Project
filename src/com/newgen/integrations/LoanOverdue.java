/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;

/**
 * @author bibek.shah
 **/
public class LoanOverdue {

    public String loanOverdueAccountInquiryReq(String input) {
        String request = "";
        String[] param = input.split("#", -1);
        try {
            LogMessages.statusLog.info("Inside create Request of loanOverdueAccountInquiryReq." + param[0]);
            request = "{\"AccountNumber\":\"" + param[1] + "\"}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanOverdueAccountInquiryReq:  " + e);
        }
        LogMessages.statusLog.info("Request loanOverdueAccountInquiryReq:  " + request);
        return request;
    }

    public String loanOverdueAccountInquiryRes(String[] param) {
        String res = "";
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String freezeStat = String.valueOf(((JSONObject) obj.get("Data")).get("NomineeFreezeStatus"));
                    res = NewGenUtilConstants.SUCCESS + "#" + param[1] + "#" + freezeStat;
                } else {
                    res = NewGenUtilConstants.FAILURE + "#" + obj.get("Message");
                }
            } else {
                res = NewGenUtilConstants.FAILURE + "#" + obj;
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanOverdueAccountInquiryRes:  " + e);
        }
        LogMessages.statusLog.info("loanOverdueAccountInquiryRes response is:" + res);
        return res;
    }
}
