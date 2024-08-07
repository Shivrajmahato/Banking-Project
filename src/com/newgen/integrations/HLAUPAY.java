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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author Shivaraj mahato
 */
public class HLAUPAY {
public String HLAUPAYReq(String[] param) {
        String request = "";
            //String initSolId = iformObj.getValue("table104_Solid").toString();
            //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
            Date date = new Date();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject UnscheduleLaPmtRequest = new JSONObject();
        JSONObject UnscheduleLaPmtRq = new JSONObject();
        JSONObject laPayDtlLLRecFi = new JSONObject();
        JSONObject laPayMsg_laPayDtlLL_enteredCrAmt = new JSONObject();
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T':mm:ss.mmm");

        laPayMsg_laPayDtlLL_enteredCrAmt.put("amountValue", "10000");//need to put
        laPayMsg_laPayDtlLL_enteredCrAmt.put("currencyCode", "NPR");

        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_laAcct_foracid", "0168017500440");//need to put
        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_laAcct_crncyCode", "NPR");
        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_laAcct_solId", "16");//need to put
        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_laPayValueDate", f.format(date));
        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_enteredCrAmt",laPayMsg_laPayDtlLL_enteredCrAmt );//need to put
        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_tranRmks", "");
        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_srcAcct_foracid", "0101017501186");//need to put
        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_srcAcct_crncyCode", "NPR");
        laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_srcAcct_solId", "16");//need to put
        //laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_laAcct_foracid", "0168017500440");
                
        //executeFinacleScript_CustomData.put("loanAccountNumber", "00867017501100");
        //executeFinacleScript_CustomData.put("intrestTillDate", 03-02-2024);//need to put
        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                laPayDtlLLRecFi.put("laPayMsg_laPayDtlLL_enteredCrAmt", laPayMsg_laPayDtlLL_enteredCrAmt);
                UnscheduleLaPmtRq.put("laPayMsg_laPayCrit_tranType", "T");
                UnscheduleLaPmtRq.put("laPayMsg_laPayCrit_drFromOperAcctFlg", "Y");
                UnscheduleLaPmtRq.put("laPayDtlLLRecFi", laPayDtlLLRecFi);
                UnscheduleLaPmtRequest.put("UnscheduleLaPmtRq", UnscheduleLaPmtRq);
                finalJson.put("UnscheduleLaPmtRequest",UnscheduleLaPmtRequest);
 LogMessages.statusLog.info("Request HLAUPAYReq:  " + finalJson);
 request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception HLAUPAYReq:  " + e);
        }
        LogMessages.statusLog.info("Request HLAUPAYReq:  " + request);
        return request;
    }
public JSONArray HLAUPAYRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String UnscheduleLaPmtResponse = String.valueOf(((JSONObject) obj.get("Data")).get("UnscheduleLaPmtResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(UnscheduleLaPmtResponse);
                    String Result = String.valueOf(((JSONObject) obj2.get("UnscheduleLaPmtRs")).get("laPayMsg_laPayCrit_tranId"));
                    //String Result1 = String.valueOf(((JSONObject) obj2.get("UnscheduleLaPmt_CustomData")));
                    
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
            LogMessages.statusLog.info("Exception HLAUPAYRes: " + e);
        }
        LogMessages.statusLog.info("Response HLAUPAYRes: " + response);
        return response;
    }
}
