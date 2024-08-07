package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;

/**
 * @author KRISHNA NAND
 **/

public class SanctionLimitDetails {

    public static final String MESSAGE = "Message";

    public String sanctionLimitDetailsReq(String[] param) {
        String request = "";
        try {
            request = "{\"CustId\":\"" + param[1] + "\"}";
            LogMessages.statusLog.info("Request sanctionLimitDetailsReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception sanctionLimitDetailsReq:  " + e);
        }
        return request;
    }

    public JSONArray sanctionLimitDetailsRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String message = String.valueOf(obj.get(MESSAGE));
                    if (message.equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                        String accountList = String.valueOf(((JSONObject) obj.get("Data")).get("AccountList"));
                        JSONArray arr = NewGenUtil.getParsedJsonArray(accountList);
                        if (!arr.isEmpty()) {
                            res.put("tblFacilitiesRetail", accountList);
                            res.put(MESSAGE, obj.get(MESSAGE));

                        } else {
                            res.put(MESSAGE, NewGenUtilConstants.FAILURE);
                            res.put(MESSAGE, String.valueOf(obj.get(MESSAGE)));
                        }
                    } else {
                        res.put(MESSAGE, NewGenUtilConstants.FAILURE);
                        res.put(MESSAGE, String.valueOf(obj.get(MESSAGE)));
                    }
                } else {
                    res.put(MESSAGE, NewGenUtilConstants.FAILURE);
                    res.put(MESSAGE, String.valueOf(obj.get(MESSAGE)));
                }
            } else {
                res.put(MESSAGE, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception occure during Conversion Response:  " + e);
        }
        return response;
    }


}
