/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import static com.newgen.iforms.util.NewGenUtil.getFailureResponse;
import com.newgen.iforms.util.NewGenUtilConstants;
import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shivaraj Mahato
 */
public class HPayOffLAA {
     public String HPAYOFFReq(String[] param) {
        String request = "";
        //String jsonRequest = "{\"PayOffProcessRequest\":{\"PayOffProcessRq\":{\"PayOffCrit\":{\"TrnType\":{\"TrnType\":\"T\",\"TrnSubtype\":\"BI\"},\"AcctId\":\"00166017503772\",\"PayOffValueDt\":\"2023-07-25T00:00:00.000\",\"GLDt\":\"2023-07-25T00:00:00.000\",\"CollAcctId\":\"00101017501258\",\"CalcDsaPenality\":\"N\",\"ReverseSubvention\":\"N\"},\"AssmntLAAcctId\":\"00166017503772\"}}}";
        //String initSolId = iformObj.getValue("table104_Solid").toString();
        //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        Date date = new Date();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject TrnType = new JSONObject();
        JSONObject PayOffCrit = new JSONObject();
        JSONObject PayOffProcessRq = new JSONObject();
        JSONObject PayOffProcessRequest = new JSONObject();
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T':mm:ss.mmm");
        

        TrnType.put("TrnType", param[1]);
        TrnType.put("TrnSubtype", param[2]);
        

        PayOffCrit.put("AcctId", param[3]);//need to put
        PayOffCrit.put("PayOffValueDt", f.format(date));
        PayOffCrit.put("GLDt", f.format(date));
        PayOffCrit.put("CollAcctId", param[6]);//need to put
        PayOffCrit.put("CalcDsaPenality", param[7]);
        PayOffCrit.put("ReverseSubvention", param[8]);
        PayOffProcessRq.put("AssmntLAAcctId", param[9]);

        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                PayOffCrit.put("TrnType", TrnType);
                PayOffProcessRq.put("PayOffCrit",PayOffCrit);
                PayOffProcessRequest.put("PayOffProcessRq",PayOffProcessRq);
                finalJson.put("PayOffProcessRequest",PayOffProcessRequest);
                LogMessages.statusLog.info("Request HPAYOFFReq:  " + finalJson);
                request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception HPAYOFFReq:  " + e);
        }
        LogMessages.statusLog.info("Request HPAYOFFReq:  " + request);
        return request;
    }
     public JSONArray HPAYOFFRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String PayOffProcessResponse = String.valueOf(((JSONObject) obj.get("Data")).get("PayOffProcessResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(PayOffProcessResponse);
                    String PayOffProcessRs = String.valueOf( obj2.get("PayOffProcessRs"));
//                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(PayOffProcessRs);
//                    String PayOffProcess_CustomData = String.valueOf(((JSONObject) obj3.get("PayOffProcessResponse")).get("PayOffProcess_CustomData"));
//                    JSONObject obj4 = NewGenUtil.getParsedJsonObj(PayOffProcess_CustomData);
//                    
                    
                    LogMessages.statusLog.info("PayOffProcessRs: " + PayOffProcessRs);

                    res.put("Result", PayOffProcessRs);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LoanDemandSatisfactionRes: " + e);
        }
        LogMessages.statusLog.info("Response LoanDemandSatisfactionRes: " + response);
        return response;
    }
   
}
