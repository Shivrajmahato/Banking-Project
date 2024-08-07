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
public class HLINTTM {

    public String HLINTTMReq(String[] param) {
        String request = "";
        //String initSolId = iformObj.getValue("table104_Solid").toString();
        //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject LoanIntTblModRequest = new JSONObject();
            JSONObject LoanIntTblModRq = new JSONObject();
            JSONObject LaAcct = new JSONObject();
            JSONObject LoanPnTblDtlRec = new JSONObject();

            LaAcct.put("AcctId", "00368017500647");
            LoanPnTblDtlRec.put("StartDate", "2024-02-28T00:00:00.001"); //EffectiveDate
            LoanPnTblDtlRec.put("PenalIntTblCode", "LBASE");
            LoanPnTblDtlRec.put("PenalPrfPcnt", "3.5");
//xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
            LoanIntTblModRq.put("LaAcct", LaAcct);
            LoanIntTblModRq.put("LoanPnTblDtlRec", LoanPnTblDtlRec);
            LoanIntTblModRequest.put("LoanIntTblModRq", LoanIntTblModRq);
            finalJson.put("LoanIntTblModRequest", LoanIntTblModRequest);
            LogMessages.statusLog.info("Request HLINTTMReq:  " + finalJson);
            request = finalJson.toString();
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception HLINTTMReq:  " + e);
        }
        LogMessages.statusLog.info("Request HLINTTMReq:  " + request);
        
        return request;
    }

    public JSONArray HLINTTMRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String LoanIntTblModResponse = String.valueOf(((JSONObject) obj.get("Data")).get("LoanIntTblModResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(LoanIntTblModResponse);
                    String LoanIntTblModRs = String.valueOf(((JSONObject) obj2.get("LoanIntTblModRs")));
                    String LoanIntTblMod_CustomData = String.valueOf(((JSONObject) obj2.get("LoanIntTblMod_CustomData")));

                    LogMessages.statusLog.info("LoanIntTblModRs: " + LoanIntTblModRs);

                    res.put("LoanIntTblModRs", LoanIntTblModRs);
                    res.put("LoanIntTblMod_CustomData", LoanIntTblMod_CustomData);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception HLINTTMRes: " + e);
        }
        LogMessages.statusLog.info("Response HLINTTMRes: " + response);
        return response;
    }
}
