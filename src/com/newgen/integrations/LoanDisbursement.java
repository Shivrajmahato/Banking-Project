package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtil.getFailureResponse;
import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;

/**
 * @author bibek.shah
 */
public class LoanDisbursement {

    public String loanDisbursementReq(String[] param) {
        String request = "";
        try {
            if (param[25].equalsIgnoreCase("NO")) {
                request = "{\"loanDisbursementRequest\":{\"LoanDisbursementStruct\":{\"acctDisburseTranLA\":{\"amtAlreadyDisbursed\":{\"amountValue\":\"" + param[1] + "\",\"currencyCode\":\"" + param[2] + "\"},\"amtAvailForDisbursement\":{\"amountValue\":\"" + param[3] + "\",\"currencyCode\":\"" + param[4] + "\"},\"deductOvduDmds\":\"" + param[5] + "\",\"disburseAmt\":{\"amountValue\":\"" + param[6] + "\",\"currencyCode\":\"" + param[7] + "\"},\"finalDisbFlg\":\"" + param[8] + "\",\"firstDisbFlg\":\"" + param[9] + "\",\"glDate\":\"" + param[10] + "\",\"grossNetDisbt\":\"" + param[11] + "\",\"isDetailsEntered\":\"" + param[12] + "\",\"laAcct\":{\"foracid\":\"" + param[13] + "\"},\"oPartTranLL\":{\"crAcctForAcid\":\"" + param[14] + "\",\"crValueDate\":\"" + param[15] + "\",\"laAmtCrncy\":{\"amountValue\":\"" + param[16] + "\",\"currencyCode\":\"" + param[17] + "\"},\"modeOfDisb\":\"" + param[18] + "\"},\"solId\":\"" + param[19] + "\",\"tranDate\":\"" + param[20] + "\",\"tranMode\":\"" + param[21] + "\",\"tranType\":\"" + param[22] + "\",\"valueDate\":\"" + param[23] + "\"}}}}";
            } else {
                request = "{\"loanDisbursementRequest\":{\"LoanDisbursementStruct\":{\"acctDisburseTranLA\":{\"amtAlreadyDisbursed\":{\"amountValue\":\"" + param[1] + "\",\"currencyCode\":\"" + param[2] + "\"},\"amtAvailForDisbursement\":{\"amountValue\":\"" + param[3] + "\",\"currencyCode\":\"" + param[4] + "\"},\"deductOvduDmds\":\"" + param[5] + "\",\"disburseAmt\":{\"amountValue\":\"" + param[6] + "\",\"currencyCode\":\"" + param[7] + "\"},\"finalDisbFlg\":\"" + param[8] + "\",\"firstDisbFlg\":\"" + param[9] + "\",\"glDate\":\"" + param[10] + "\",\"grossNetDisbt\":\"" + param[11] + "\",\"isDetailsEntered\":\"" + param[12] + "\",\"laAcct\":{\"foracid\":\"" + param[13] + "\"},\"oPartTranLL\":{\"crAcctForAcid\":\"" + param[14] + "\",\"crValueDate\":\"" + param[15] + "\",\"laAmtCrncy\":{\"amountValue\":\"" + param[16] + "\",\"currencyCode\":\"" + param[17] + "\"},\"modeOfDisb\":\"" + param[18] + "\"},\"ochargesLL\":{\"chargeAcctId\":{\"foracid\":\"" + param[14] + "\"},\"chargeCrncy\":\"NPR\",\"chargeType\":\"PROSF\",\"eventId\":\"LOAN_PROC_FEE\",\"eventSrlNum\":\"1\",\"key\":{\"serial_num\":\"1\"},\"modifyAllwd\":\"Y\",\"newRecordFlg\":\"N\",\"selectFlg\":\"Y\",\"systemChargeAmt\":{\"amountValue\":\"" + param[24] + "\",\"currencyCode\":\"NPR\"},\"userChargeAmt\":{\"amountValue\":\"" + param[24] + "\",\"currencyCode\":\"NPR\"}},\"solId\":\"" + param[19] + "\",\"tranDate\":\"" + param[20] + "\",\"tranMode\":\"" + param[21] + "\",\"tranType\":\"" + param[22] + "\",\"valueDate\":\"" + param[23] + "\"}}}}";
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanDisbursementReq:  " + e);
        }
        LogMessages.statusLog.info("Request loanDisbursementReq:  " + request);
        return request;
    }

    public JSONArray loanDisbursementRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String loanDisbursementResponse = String.valueOf(((JSONObject) obj.get("Data")).get("loanDisbursementResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(loanDisbursementResponse);
                    String loanDisbursementOutputStruct = String.valueOf(((JSONObject) obj2.get("LoanDisbursementOutputStruct")).get("tranMesgOutput"));
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(loanDisbursementOutputStruct);
                    String trnId = String.valueOf(((JSONObject) obj3.get("tranIdentifier")).get("TrnId"));
                    String trnDt = String.valueOf(((JSONObject) obj3.get("tranIdentifier")).get("TrnDt"));
                    LogMessages.statusLog.info("trnDt: " + trnDt);
                    LogMessages.statusLog.info("trnId: " + trnId);

                    res.put("TrnDt", trnDt);
                    res.put("TrnId", trnId);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanDisbursementRes: " + e);
        }
        LogMessages.statusLog.info("Response loanDisbursementRes: " + response);
        return response;
    }

}
