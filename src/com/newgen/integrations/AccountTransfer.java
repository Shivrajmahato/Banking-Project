package com.newgen.integrations;

/**
 * @author bibek.shah
 **/

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;

public class AccountTransfer {

    public String accountTransferReq(String[] param) {
        String request = "";
        try {
            request = "{\"TransactionId\":\"" + param[1] + "\",\"Amount\":\"" + param[2] + "\",\"DrAccount\":\"" + param[3] + "\",\"CrAccount\":\"" + param[4] + "\",\"Description1\":\"" + param[5] + "\"}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception accountTransferReq:  " + e);
        }
        LogMessages.statusLog.info("Request accountTransferReq:  " + request);
        return request;
    }

    public JSONArray accountTransferRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                String code = String.valueOf(obj.get(CODE));
                if (ZERO.equalsIgnoreCase(code)) {
                    res.put("transactionId", String.valueOf(obj.get("TransactionId")));
                    res.put("merchantTransactionId", String.valueOf(obj.get("MerchantTransactionId")));
                    res.put("availableBalance", String.valueOf(obj.get("AvailableBalance")));
                    res.put("code", code);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.FAILURE);
                }
                res.put(NewGenUtilConstants.MESSAGE, String.valueOf(obj.get("Message")));
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception accountTransferRes:  " + e);
        }
        LogMessages.statusLog.info("Response accountTransferRes:  " + response);
        return response;
    }

}