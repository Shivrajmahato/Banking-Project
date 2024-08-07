package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtilConstants.*;

/**
 * @author bibek.shah
 */
public class NonLAALoanDisbursement {
    public String nonLoanDisbursementReq(String[] param) {
        String request = "";
        try {
            request = "{\"XferTrnAddRequest\":{\"XferTrnAddRq\":{\"XferTrnHdr\":{\"TrnType\":\"" + param[1] + "\",\"TrnSubType\":\"" + param[2] + "\"},\"XferTrnDetail\":{\"PartTrnRec\":[{\"AcctId\":{\"AcctId\":\"" + param[3] + "\"},\"CreditDebitFlg\":\"" + param[4] + "\",\"TrnAmt\":{\"amountValue\":\"" + param[5] + "\",\"currencyCode\":\"" + param[6] + "\"},\"TrnParticulars\":\"" + param[7] + "\",\"ValueDt\":\"" + param[8] + "\"},{\"AcctId\":{\"AcctId\":\"" + param[9] + "\"},\"CreditDebitFlg\":\"" + param[10] + "\",\"TrnAmt\":{\"amountValue\":\"" + param[11] + "\",\"currencyCode\":\"" + param[12] + "\"},\"TrnParticulars\":\"" + param[13] + "\",\"ValueDt\":\"" + param[14] + "\"}]}}}}";
            LogMessages.statusLog.info("Request nonLoanDisbursementReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception nonLoanDisbursementReq:  " + e);
        }
        return request;
    }

    public JSONArray nonLoanDisbursementRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String loanDisbursementResponse = String.valueOf(((JSONObject) obj.get("Data")).get("XferTrnAddResponse"));
                    LogMessages.statusLog.info("first response NONLAALoanDisbursement : " + loanDisbursementResponse);
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(loanDisbursementResponse);
                    String loanDisbursementOutputStruct = String.valueOf(((JSONObject) obj2.get("XferTrnAddRs")).get("TrnIdentifier"));
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(loanDisbursementOutputStruct);
                    String trnDt = String.valueOf(obj3.get("TrnDt"));
                    String trnId = String.valueOf(obj3.get("TrnId"));
                    LogMessages.statusLog.info("Exception LAALoanDisbursement: " + trnDt);
                    LogMessages.statusLog.info("Exception LAALoanDisbursement: " + trnId);
                    res.put("TrnDt", trnDt);
                    res.put("TrnId", trnId);
                    res.put(STATUS, SUCCESS);
                } else {
                    NewGenUtil.getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception nonLoanDisbursementRes: " + e);
        }
        return response;
    }

}
