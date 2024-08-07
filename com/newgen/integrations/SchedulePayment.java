/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import java.text.SimpleDateFormat;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;

/**
*
* @author bibek.shah
*
**/
public class SchedulePayment {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");

    public String schedulePaymentReq(String input, IFormReference iFormObj) {
        String request = "";
        String[] param = input.split("#", -1);
        String str = sdf.format(new Date());

        try {
            LogMessages.statusLog.info("Inside create Request of schedulePaymentReq." + param[0]);
            //request ="{\"RetCustInqRequest\":{\"RetCustInqRq\":{\"CustId\":\""+param[1]+"\"}}}";
            request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"ScheduleLaPmtCustom\"},\"executeFinacleScript_CustomData\":{\"enteredCrAmt\":\"" + param[1] + "\",\"principal\":\"" + param[2] + "\",\"interest\":\"" + param[3] + "\",\"laAcct\":\"" + param[4] + "\",\"srcAcct\":\"" + param[5] + "\",\"drFromOperAcctFlg\":\"" + param[6] + "\",\"glDate\":\"" + str + "\"}}}";

            LogMessages.statusLog.info("Request schedulePaymentReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception schedulePaymentReq:  " + e);
        }
        return request;
    }

    public JSONArray schedulePaymentRes(String input) {
        String[] param = input.split("##");
        LogMessages.statusLog.info("schedulePaymentRes BODY:" + param[1]);
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        if (param[0].equalsIgnoreCase("SUCCESS")) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[1]);
                String code = String.valueOf(obj.get("Code"));
                String message = String.valueOf(obj.get("Message"));
                if (code.equalsIgnoreCase("0")) {
                    String schPayres = String.valueOf(((JSONObject) obj.get("Data")).get("executeFinacleScriptResponse"));
                    JSONObject obj1 = (JSONObject) parser.parse(schPayres);
                    String finalres = String.valueOf((JSONObject) obj1.get("executeFinacleScript_CustomData"));
                    res.put("status", "SUCCESS");
                    res.put("response", finalres);
                    response.add(res);
                } else {
                    res.put("status", "SUCCESS");
                    res.put("response", message);
                    response.add(res);
                }
                LogMessages.statusLog.info("schedulePaymentRes Success" + message);
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception schedulePaymentRes:  " + e);
            }

        } else {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[1]);
                String error = String.valueOf(obj);;
                LogMessages.statusLog.info("else schedulePaymentRes");
                res.put("status", "SUCCESS");
                res.put("response", error);
                response.add(res);
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception schedulePaymentRes:  " + e);
            }

        }
        return response;
    }
}
