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
public class HLARA {
public String HLARAReq(String[] param) {
        String request = "";
            //String initSolId = iformObj.getValue("table104_Solid").toString();
            //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject loanRescheduleRequest = new JSONObject();
        JSONObject LoanRescheduleInputVO = new JSONObject();
        JSONObject basicRshdlDtl = new JSONObject();
        JSONObject acctRepaymentShdlLA = new JSONObject();
        JSONObject laEiParams = new JSONObject();
        JSONObject hldyFrequency = new JSONObject();
        JSONObject stepPcnt = new JSONObject();
        JSONObject orepaymentLL = new JSONObject();
        JSONObject eiFrequency = new JSONObject();
        JSONObject flowAmt = new JSONObject();
        JSONObject interestFrequency = new JSONObject();
        JSONObject laAcct = new JSONObject();
        JSONObject loanShdlCommonParams = new JSONObject();
        JSONObject instlmntFreq = new JSONObject();
        JSONObject intFreq = new JSONObject();
        
        
        
        stepPcnt.put("value", "0");
        laAcct.put("foracid", "00668017500606");
        
        laEiParams.put("gradPmtAlwd", "N");
        laEiParams.put("hldyFrequency", hldyFrequency);
        laEiParams.put("intRestFreq", "D");
        laEiParams.put("noOfSteps", "0");
        laEiParams.put("stepPcnt", stepPcnt);
        
        
        hldyFrequency.put("hldyStat", "N");
        hldyFrequency.put("startDd", "20");
        hldyFrequency.put("type", "M");
        hldyFrequency.put("weekDay", "0");
        
        orepaymentLL.put("delFlg", "N");
        orepaymentLL.put("eiFrequency", eiFrequency);
        orepaymentLL.put("flowAmt", flowAmt);
        orepaymentLL.put("flowId", "EIDEM");
        orepaymentLL.put("flowStartDate", "2024-02-28T00:00:00.001");
        orepaymentLL.put("interestFrequency", interestFrequency);
        orepaymentLL.put("noOfDemands", "180");
        
        eiFrequency.put("hldyStat", "N");
        eiFrequency.put("startDd", "20");
        eiFrequency.put("type", "M");
        eiFrequency.put("weekDay", "0");
        
        flowAmt.put("amountValue", "9097.54");
        flowAmt.put("currencyCode", "NPR");
        
        interestFrequency.put("hldyStat", "N");
        interestFrequency.put("startDd", "20");
        interestFrequency.put("type", "M");
        interestFrequency.put("weekDay", "0");
        
        intFreq.put("hldyStat", "N");
        intFreq.put("startDd", "20");
        intFreq.put("type", "M");
        intFreq.put("weekDay", "0");
        
        instlmntFreq.put("hldyStat", "N");
        instlmntFreq.put("startDd", "20");
        instlmntFreq.put("type", "M");
        instlmntFreq.put("weekDay", "0");

        loanShdlCommonParams.put("instlmntFreq", instlmntFreq);
        loanShdlCommonParams.put("instlmntStartDate", "2024-02-28T00:00:00.001");
        loanShdlCommonParams.put("intFreq", intFreq);
        loanShdlCommonParams.put("intStartDate", "2024-02-28T00:00:00.001");
        
        
        acctRepaymentShdlLA.put("laEiParams", laEiParams);
        acctRepaymentShdlLA.put("orepaymentLL", orepaymentLL);
        basicRshdlDtl.put("acctRepaymentShdlLA", acctRepaymentShdlLA);
        basicRshdlDtl.put("laAcct", laAcct);
        
        LoanRescheduleInputVO.put("applyIntUptoDate", "Y");
        LoanRescheduleInputVO.put("basicRshdlDtl", basicRshdlDtl);
        LoanRescheduleInputVO.put("capitalizeIntFlag", "N");
        LoanRescheduleInputVO.put("carryOverDueDmds", null);
        LoanRescheduleInputVO.put("loanShdlCommonParams", loanShdlCommonParams);
        LoanRescheduleInputVO.put("rescheduleMethod", "I");
        LoanRescheduleInputVO.put("rshdlAmtFlg", "O");
        LoanRescheduleInputVO.put("rshdlNotes", "test");
        
        
        

        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                loanRescheduleRequest.put("LoanRescheduleInputVO", LoanRescheduleInputVO);
                finalJson.put("loanRescheduleRequest",loanRescheduleRequest);
 LogMessages.statusLog.info("Request HLARAReq:  " + finalJson);
 request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception HLARAReq:  " + e);
        }
        LogMessages.statusLog.info("Request HLARAReq:  " + request);
        return request;
    }  
public JSONArray HLARARes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String loanRescheduleResponse = String.valueOf(((JSONObject) obj.get("Data")).get("loanRescheduleResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(loanRescheduleResponse);
                    String LoanRescheduleOutputVO = String.valueOf(((JSONObject) obj2.get("LoanRescheduleOutputVO")));
                    String loanReschedule_CustomData = String.valueOf(((JSONObject) obj2.get("loanReschedule_CustomData")));
                    
                    LogMessages.statusLog.info("loanRescheduleResponse: " + loanRescheduleResponse);

                    res.put("LoanRescheduleOutputVO", LoanRescheduleOutputVO);
                    res.put("loanReschedule_CustomData", loanReschedule_CustomData);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception HLARARes: " + e);
        }
        LogMessages.statusLog.info("Response HLARARes: " + response);
        return response;
    }
}
