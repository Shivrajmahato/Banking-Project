/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author User
 */
public class SanctionLimitMod {
    public String sanctionLimitModReq(String[] param) {
        String request = "";
        try {
            request = "{\"SanctionLimitModRequest\":{\"SanctionLimitModRq\":{\"AcctId\":\"" + param[1] + "\",\"SanctionLimitRec\":{\"IsDetailsEntered\":\"" + param[2] + "\",\"LimitLevelIntFlg\":[\"" + param[3] + "\"],\"RecordFlg\":\"" + param[4] + "\",\"SerialNum\":\"" + param[5] + "\",\"ApplicableDt\":\"" + param[6] + "\",\"SanctionLimit\":{\"amountValue\":\"" + param[7] + "\",\"currencyCode\":\"" + param[8] + "\"},\"SupersedeFlg\":\"" + param[9] + "\",\"SanctionDt\":\"" + param[10] + "\",\"ExpiryDt\":\"" + param[11] + "\",\"DocumentDt\":\"" + param[12] + "\",\"SanctionLevelCode\":\"" + param[13] + "\",\"SanctionAuthCode\":\"" + param[14] + "\",\"MarginIntRate\":{\"value\":\"" + param[15] + "\"},\"PenalIntRate\":{\"value\":\"" + param[16] + "\"},\"NormalIntRate\":{\"value\":\"" + param[17] + "\"},\"SanctionRefNum\":\"" + param[18] + "\"}}}}";
            LogMessages.statusLog.info("Request sanctionLimitModReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception sanctionLimitModReq:  " + e);
        }
        return request;
    }

    public JSONArray sanctionLimitModRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String sanctionLimitModResponse = String.valueOf(((JSONObject) obj.get("Data")).get("SanctionLimitModResponse"));
                    LogMessages.statusLog.info("sanctionLimitModResponse: " + sanctionLimitModResponse);
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(sanctionLimitModResponse);
                    String forAcid = String.valueOf(((JSONObject) obj2.get("SanctionLimitModRs")).get("AcctId"));

                    res.put("AcctId", forAcid);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception sanctionLimitModRes: " + e);
        }
        return response;
    }


}
