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
public class HINTTM {
public String HINTTMReq(String[] param) {
        String request = "";
            //String initSolId = iformObj.getValue("table104_Solid").toString();
            //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject IntPrefModRequest = new JSONObject();
        JSONObject IntPrefModRq = new JSONObject();
        JSONObject IntTblDetailRec = new JSONObject();
        JSONObject acctPrefIntDr = new JSONObject();

        acctPrefIntDr.put("value", "4");

        IntTblDetailRec.put("IntTblCode", "BASE");
        IntTblDetailRec.put("startDate", "2024-02-28T00:00:00.001");
        IntTblDetailRec.put("acctPrefIntDr", acctPrefIntDr);
        IntPrefModRq.put("EntityType", "ACCNT");
        IntPrefModRq.put("EntityDispId", "0201017502202");
        IntPrefModRq.put("IntTblDetailRec", IntTblDetailRec);

        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                IntPrefModRequest.put("IntPrefModRq", IntPrefModRq);
                finalJson.put("IntPrefModRequest",IntPrefModRequest);
 LogMessages.statusLog.info("Request HINTTMReq:  " + finalJson);
 request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception HINTTMReq:  " + e);
        }
        LogMessages.statusLog.info("Request HINTTMReq:  " + request);
        return request;
    } 
 public JSONArray HINTTMRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String IntPrefModResponse = String.valueOf(((JSONObject) obj.get("Data")).get("IntPrefModResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(IntPrefModResponse);
                    String IntPrefModRs = String.valueOf(((JSONObject) obj2.get("IntPrefModRs")));
                    String IntPrefMod_CustomData = String.valueOf(((JSONObject) obj2.get("IntPrefMod_CustomData")));
                    
                    LogMessages.statusLog.info("IntPrefModRs: " + IntPrefModRs);

                    res.put("IntPrefModRs", IntPrefModRs);
                    res.put("IntPrefMod_CustomData", IntPrefMod_CustomData);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception HINTTMRes: " + e);
        }
        LogMessages.statusLog.info("Response HINTTMRes: " + response);
        return response;
    }
}
