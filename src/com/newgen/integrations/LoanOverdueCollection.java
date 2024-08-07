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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Administrator
 */
public class LoanOverdueCollection {

    public String LoanOverdueCollectionReq(String[] param) {
                String request = "";
            //String initSolId = iformObj.getValue("table104_Solid").toString();
            //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject executeFinacleScriptRequest = new JSONObject();
        JSONObject executeFinacleScriptInputVO = new JSONObject();
        JSONObject executeFinacleScript_CustomData = new JSONObject();

        executeFinacleScriptInputVO.put("requestId", "ovdcoll.scr");

        executeFinacleScript_CustomData.put("sanLoanSchm", param[1]);
        executeFinacleScript_CustomData.put("sanPenInt", param[2]);
        executeFinacleScript_CustomData.put("funcCode", param[3]);
        executeFinacleScript_CustomData.put("sanARP", param[4]);
        executeFinacleScript_CustomData.put("sanIntNonLAA",param[5] );
        executeFinacleScript_CustomData.put("sanLoanAcc", param[6]);
        executeFinacleScript_CustomData.put("sanIntLAA", param[7]);

        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                executeFinacleScriptRequest.put("ExecuteFinacleScriptInputVO", executeFinacleScriptInputVO);
                executeFinacleScriptRequest.put("executeFinacleScript_CustomData", executeFinacleScript_CustomData);
                finalJson.put("executeFinacleScriptRequest",executeFinacleScriptRequest);
 LogMessages.statusLog.info("Request LoanOverdueCollectionReq:  " + finalJson);
 request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception LoanOverdueCollectionReq:  " + e);
        }
        LogMessages.statusLog.info("Request LoanOverdueCollectionReq:  " + request);
        return request;
    }
   
    public JSONArray LoanOverdueCollectionRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String executeFinacleScriptResponse = String.valueOf(((JSONObject) obj.get("Data")).get("executeFinacleScriptResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(executeFinacleScriptResponse);
                    String Result = String.valueOf(((JSONObject) obj2.get("executeFinacleScript_CustomData")).get("Result"));
                    
                    LogMessages.statusLog.info("Result: " + Result);

                    res.put("Result", Result);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LoanOverdueCollectionRes: " + e);
        }
        LogMessages.statusLog.info("Response LoanOverdueCollectionRes: " + response);
        return response;
    }
   
   
}

    

