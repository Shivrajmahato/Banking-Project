/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtil.getFailureResponse;
import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Shivaraj Mahato
 */
public class HACLHM {
public String HACLHMReq(String[] param) {
        String request = "";
            //String initSolId = iformObj.getValue("table104_Solid").toString();
            //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject SanctionLimitModRequest = new JSONObject();
        JSONObject SanctionLimitModRq = new JSONObject();
        JSONObject SanctionLimitRec = new JSONObject();
        JSONObject SanctionLimit = new JSONObject();
        JSONObject MarginIntRate = new JSONObject();
        JSONObject PenalIntRate = new JSONObject();
        JSONObject NormalIntRate = new JSONObject();
        

        MarginIntRate.put("value", "0");
        PenalIntRate.put("value", "0");
        NormalIntRate.put("value", "0");

        SanctionLimit.put("amountValue", "2500000");
        SanctionLimit.put("currencyCode", "NPR");
        SanctionLimitRec.put("IsDetailsEntered", "1");
        SanctionLimitRec.put("LimitLevelIntFlg", "N");
        SanctionLimitRec.put("RecordFlg", "Y");
        SanctionLimitRec.put("SerialNum", "0001");
        SanctionLimitRec.put("ApplicableDt", "2024-02-28T00:00:01.001");
        SanctionLimitRec.put("SanctionLimit", SanctionLimit);
        SanctionLimitRec.put("SupersedeFlg", "N");
        SanctionLimitRec.put("SanctionDt", "2024-02-28T00:00:01.001");
        SanctionLimitRec.put("ExpiryDt", "2024-10-19T00:00:01.001");
        SanctionLimitRec.put("DocumentDt", "2024-02-28T00:00:01.001");
        SanctionLimitRec.put("SanctionLevelCode", "CH");
        SanctionLimitRec.put("SanctionAuthCode", "AMSME");
        SanctionLimitRec.put("MarginIntRate", MarginIntRate);
        SanctionLimitRec.put("PenalIntRate", PenalIntRate);
        SanctionLimitRec.put("NormalIntRate", NormalIntRate);
        SanctionLimitRec.put("remarks", "30 days extension");
        SanctionLimitRec.put("SanctionRefNum", "LimitExtension-0000016302");
        SanctionLimitModRq.put("AcctId", "19201017500080");
        SanctionLimitModRq.put("SanctionLimitRec", SanctionLimitRec);
        

        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                SanctionLimitModRequest.put("SanctionLimitModRq", SanctionLimitModRq);
                finalJson.put("SanctionLimitModRequest",SanctionLimitModRequest);
 LogMessages.statusLog.info("Request HACLHMReq:  " + finalJson);
 request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception HACLHMReq:  " + e);
        }
        LogMessages.statusLog.info("Request HACLHMReq:  " + request);
        return request;
    }
public JSONArray HACLHMRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String SanctionLimitModResponse = String.valueOf(((JSONObject) obj.get("Data")).get("SanctionLimitModResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(SanctionLimitModResponse);
                    String AcctId = String.valueOf(((JSONObject) obj2.get("SanctionLimitModRs")).get("AcctId"));
                    //JSONObject obj3 = NewGenUtil.getParsedJsonObj(AcctId);
                    String DayLightLimitAmt = String.valueOf(((JSONObject) obj2.get("SanctionLimitModRs")).get("DayLightLimitAmt"));
                    JSONObject obj4 = NewGenUtil.getParsedJsonObj(DayLightLimitAmt);
                    //JSONObject obj3 = NewGenUtil.getParsedJsonObj(tranMesgOutput);
                    String amountValue = String.valueOf((JSONObject) obj4.get("amountValue"));
                    String currencyCode = String.valueOf((JSONObject) obj4.get("currencyCode"));
                    
                    String SanctionLimitMod_CustomData = String.valueOf((JSONObject) obj2.get("SanctionLimitMod_CustomData"));
                    
                    
                    LogMessages.statusLog.info("SanctionLimitModResponse: " + SanctionLimitModResponse);

                    res.put("amountValue", amountValue);
                    res.put("currencyCode", currencyCode);
                    res.put("SanctionLimitMod_CustomData", SanctionLimitMod_CustomData);
                    
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception HACLHMRes: " + e);
        }
        LogMessages.statusLog.info("Response HACLHMRes: " + response);
        return response;
    }
}
