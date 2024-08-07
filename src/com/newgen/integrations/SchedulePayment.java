/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.DateUtil;
import com.newgen.iforms.util.NewGenUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

import static com.newgen.iforms.util.NewGenUtilConstants.*;

/**
 * @author bibek.shah
 **/
public class SchedulePayment {

    public String schedulePaymentReq(String input) {
        String request = "";
        String[] param = input.split("#", -1);
        try {
            LogMessages.statusLog.info("Inside create Request of schedulePaymentReq." + param[0]);
            String str = DateUtil.parseDateToString(new Date(), DD_MM_YYYY_HH_MM_SS);
            request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"ScheduleLaPmtCustom\"},\"executeFinacleScript_CustomData\":{\"enteredCrAmt\":\"" + param[1] + "\",\"principal\":\"" + param[2] + "\",\"interest\":\"" + param[3] + "\",\"laAcct\":\"" + param[4] + "\",\"srcAcct\":\"" + param[5] + "\",\"drFromOperAcctFlg\":\"" + param[6] + "\",\"glDate\":\"" + str + "\"}}}";
            LogMessages.statusLog.info("Request schedulePaymentReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception schedulePaymentReq:  " + e);
        }
        return request;
    }

    public JSONArray schedulePaymentRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String schPayres = String.valueOf(((JSONObject) obj.get("Data")).get("executeFinacleScriptResponse"));
                    JSONObject obj1 = NewGenUtil.getParsedJsonObj(schPayres);
                    String finalres = String.valueOf(obj1.get("executeFinacleScript_CustomData"));
                    res.put(STATUS, SUCCESS);
                    res.put(RESPONSE, finalres);
                } else {
                    res.put(STATUS, SUCCESS);
                    res.put(RESPONSE, String.valueOf(obj.get("Message")));
                }
            } else {
                res.put(STATUS, SUCCESS);
                res.put(RESPONSE, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception schedulePaymentRes:  " + e);
        }
        LogMessages.statusLog.info("schedulePaymentRes response" + response);
        return response;
    }
}
