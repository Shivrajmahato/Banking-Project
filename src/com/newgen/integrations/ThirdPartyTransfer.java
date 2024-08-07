package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtil.getFailureResponse;
import static com.newgen.iforms.util.NewGenUtilConstants.*;

/**
 * @author bibek.shah
 */
public class ThirdPartyTransfer {

    public String thirdPartyTransferReq(String[] param) {
        String request = "";
        try {
            request = "{\"XferTrnAddRequest\":{\"XferTrnAddRq\":{\"XferTrnHdr\":{\"TrnType\":\"" + param[1] + "\",\"TrnSubType\":\"" + param[2] + "\"},\"XferTrnDetail\":{\"PartTrnRec\":[{\"AcctId\":{\"AcctId\":\"" + param[3] + "\"},\"CreditDebitFlg\":\"" + param[4] + "\",\"TrnAmt\":{\"amountValue\":\"" + param[5] + "\",\"currencyCode\":\"" + param[6] + "\"},\"TrnParticulars\":\"" + param[7] + "\",\"ValueDt\":\"" + param[8] + "\"},{\"AcctId\":{\"AcctId\":\"" + param[9] + "\"},\"CreditDebitFlg\":\"" + param[10] + "\",\"TrnAmt\":{\"amountValue\":\"" + param[11] + "\",\"currencyCode\":\"" + param[12] + "\"},\"TrnParticulars\":\"" + param[13] + "\",\"ValueDt\":\"" + param[14] + "\"}]}}}}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception thirdPartyTransferReq:  " + e);
        }
        LogMessages.statusLog.info("Request thirdPartyTransferReq:  " + request);
        return request;
    }

    public JSONArray thirdPartyTransferRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String loanAcctAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("XferTrnAddResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(loanAcctAddResponse);
                    String forAcid = String.valueOf(((JSONObject) obj2.get("XferTrnAddRs")).get("TrnIdentifier"));
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(forAcid);
                    String trnID = String.valueOf(obj3.get("TrnId"));
                    LogMessages.statusLog.info("TRN ID : " + trnID);

                    res.put("AcctId", trnID);
                    res.put(STATUS, SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception thirdPartyTransferRes: " + e);
        }
        LogMessages.statusLog.info("Response thirdPartyTransferRes: " + response);
        return response;
    }

}
