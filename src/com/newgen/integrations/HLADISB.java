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
 * @author Shivaraj Mahato
 */
public class HLADISB {

    public String HLADISBReq(String[] param) {
        String request = "";
        //String initSolId = iformObj.getValue("table104_Solid").toString();
        //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject loanDisbursementRequest = new JSONObject();
            JSONObject LoanDisbursementStruct = new JSONObject();
            JSONObject acctDisburseTranLA = new JSONObject();
            JSONObject amtAlreadyDisbursed = new JSONObject();
            JSONObject amtAvailForDisbursement = new JSONObject();
            JSONObject disburseAmt = new JSONObject();
            JSONObject laAcct = new JSONObject();
            JSONObject oPartTranLL = new JSONObject();
            JSONObject laAmtCrncy = new JSONObject();
            JSONObject ochargesLL = new JSONObject();
            JSONObject chargeAcctId = new JSONObject();
            JSONObject key = new JSONObject();
            JSONObject systemChargeAmt = new JSONObject();
            JSONObject userChargeAmt = new JSONObject();
            //amtAlreadyDisbursed.put("amountValue", "0");
//        amtAlreadyDisbursed.put("currencyCode", "NPR");
//        amtAvailForDisbursement.put("amountValue","2000000");
//        amtAvailForDisbursement.put("currencyCode","NPR");
//        disburseAmt.put("amountValue","2000000");
//        disburseAmt.put("currencyCode","NPR");
//         laAcct.put("foracid","09368017500007");
//         oPartTranLL.put("crAcctForAcid","02100172179401");
//         laAmtCrncy
            userChargeAmt.put("amountValue5", param[1]);
            userChargeAmt.put("currencyCode5", param[2]);
            systemChargeAmt.put("amountValue4", param[3]);
            systemChargeAmt.put("currencyCode4", param[4]);
            key.put("serial_num", param[5]);
            chargeAcctId.put("foracid1", param[6]);
            //ochargesLL.put("chargeAcctId", chargeAcctId);
            ochargesLL.put("chargeCrncy", param[7]);
            ochargesLL.put("chargeType", param[8]);
            ochargesLL.put("eventId", param[9]);
            ochargesLL.put("eventSrlNum", param[10]);
            //ochargesLL.put("key", key);
            ochargesLL.put("modifyAllwd", param[11]);
            ochargesLL.put("newRecordFlg", param[12]);
            ochargesLL.put("selectFlg", param[13]);
//            ochargesLL.put("systemChargeAmt", systemChargeAmt);
//            ochargesLL.put("userChargeAmt", userChargeAmt);
            laAmtCrncy.put("amountValue3", param[14]);
            laAmtCrncy.put("currencyCode3", param[15]);
            oPartTranLL.put("crAcctForAcid", param[16]);
            oPartTranLL.put("crValueDate", param[17]);//systemDate
            //oPartTranLL.put("laAmtCrncy", laAmtCrncy);
            oPartTranLL.put("modeOfDisb", param[18]);
            laAcct.put("foracid", param[19]);
            disburseAmt.put("amountValue2", param[20]);
            disburseAmt.put("currencyCode2", param[21]);
            amtAvailForDisbursement.put("amountValue1", param[22]);
            amtAvailForDisbursement.put("currencyCode1", param[23]);
            amtAlreadyDisbursed.put("amountValue", param[24]);
            amtAlreadyDisbursed.put("currencyCode", param[25]);
//            acctDisburseTranLA.put("amtAlreadyDisbursed", amtAlreadyDisbursed);
//            acctDisburseTranLA.put("amtAvailForDisbursement", amtAvailForDisbursement);
            acctDisburseTranLA.put("deductOvduDmds", param[26]);
            //acctDisburseTranLA.put("disburseAmt", disburseAmt);
            acctDisburseTranLA.put("finalDisbFlg", param[27]);
            acctDisburseTranLA.put("firstDisbFlg", param[28]);
            acctDisburseTranLA.put("glDate1", param[29]);//systemDate
            acctDisburseTranLA.put("grossNetDisbt", param[30]);
            acctDisburseTranLA.put("isDetailsEntered", param[31]);
//            acctDisburseTranLA.put("laAcct", laAcct);
//            acctDisburseTranLA.put("oPartTranLL", oPartTranLL);
//            acctDisburseTranLA.put("ochargesLL", ochargesLL);
            acctDisburseTranLA.put("solId", param[32]);
            acctDisburseTranLA.put("tranDate", param[33]);//SystemDate
            acctDisburseTranLA.put("tranMode", param[34]);
            acctDisburseTranLA.put("tranType", param[35]);
            acctDisburseTranLA.put("valueDate", param[36]);
            
            ochargesLL.put("chargeAcctId", chargeAcctId);
            ochargesLL.put("key", key);
            ochargesLL.put("systemChargeAmt", systemChargeAmt);
            ochargesLL.put("userChargeAmt", userChargeAmt);
            oPartTranLL.put("laAmtCrncy", laAmtCrncy);
            acctDisburseTranLA.put("amtAlreadyDisbursed", amtAlreadyDisbursed);
            acctDisburseTranLA.put("amtAvailForDisbursement", amtAvailForDisbursement);
            acctDisburseTranLA.put("disburseAmt", disburseAmt);
            acctDisburseTranLA.put("laAcct", laAcct);
            acctDisburseTranLA.put("oPartTranLL", oPartTranLL);
            acctDisburseTranLA.put("ochargesLL", ochargesLL);
            
            LoanDisbursementStruct.put("acctDisburseTranLA", acctDisburseTranLA);
            loanDisbursementRequest.put("LoanDisbursementStruct", LoanDisbursementStruct);

            finalJson.put("loanDisbursementRequest", loanDisbursementRequest);
            //LogMessages.statusLog.info("Request HLADISBReq:  " + finalJson);
            request = finalJson.toString();
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception HLADISBReq:  " + e);
        }
        LogMessages.statusLog.info("Request HLADISBReq:  " + request);
        return request;
    }

    public JSONArray HLADISBRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String loanDisbursementResponse = String.valueOf(((JSONObject) obj.get("Data")).get("loanDisbursementResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(loanDisbursementResponse);
                    String tranMesgOutput = String.valueOf(((JSONObject) obj2.get("LoanDisbursementOutputStruct")).get("tranMesgOutput"));
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(tranMesgOutput);
                    String TrnDt = String.valueOf(((JSONObject) obj3.get("tranIdentifier")).get("TrnDt"));
                    String TrnId = String.valueOf(((JSONObject) obj3.get("tranIdentifier")).get("TrnId"));

                    String loanDisbursement_CustomData = String.valueOf(((JSONObject) obj2.get("loanDisbursementResponse")).get("loanDisbursement_CustomData"));

                    LogMessages.statusLog.info("tranIdentifier: " + TrnDt);

                    res.put("tranIdentifier", TrnDt);
                    res.put("tranIdentifier", TrnId);
                    res.put("loanDisbursement_CustomData", loanDisbursement_CustomData);

                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception HLADISBRes: " + e);
        }
        LogMessages.statusLog.info("Response HLADISBRes: " + response);
        return response;
    }
}
