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
 * @author bibek.shah
 */
public class LimitMaintenance {

    public String retLimitMaintenanceReq(String[] param) {
        String request = "";
        try {
            request = "{\"addLimitNodeRequest\":{\"AddLimitNodeInputVO\":{\"baseUserMaintLiab\":{\"amountValue\":\"" + param[1] + "\",\"currencyCode\":\"" + param[2] + "\"},\"crncy\":\"" + param[3] + "\",\"custId\":{\"cifId\":\"" + param[4] + "\"},\"sanctAuthCode\":\"" + param[5] + "\",\"sanctLevelCode\":\"" + param[6] + "\",\"singleTranFlg\":\"" + param[24] + "\",\"statusCode\":\"" + param[7] + "\",\"limitSanctRef\":\"" + param[8] + "\",\"drwngPowerInd\":\"" + param[9] + "\",\"parentLimitPrefix\":\"" + param[10] + "\",\"parentLimitSuffix\":\"" + param[11] + "\",\"limitDesc\":\"" + param[12] + "\",\"limitReviewDate\":\"" + param[13] + "\",\"limitExpiryDate\":\"" + param[14] + "\",\"limitPrefix\":\"" + param[15] + "\",\"limitSanctDate\":\"" + param[16] + "\",\"limitSuffix\":\"" + param[17] + "\",\"limitType\":\"" + param[18] + "\",\"freeText\":\"" + param[19] + "\",\"sanctLimit\":{\"amountValue\":\"" + param[20] + "\",\"currencyCode\":\"" + param[21] + "\"}},\"addLimitNode_CustomData\":{\"LIMITMASTERCODE\":\"" + param[22] + "\",\"globalLimitFlg\":\"" + param[23] + "\"}}}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception retLimitMaintenanceReq: " + e);
        }
        LogMessages.statusLog.info("Request retLimitMaintenanceReq: " + request);
        return request;
    }

    public JSONArray retLimitMaintenanceRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String addLimitNodeResponse = String.valueOf(((JSONObject) obj.get("Data")).get("addLimitNodeResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(addLimitNodeResponse);
                    //need to ask
                    String retCustDtls = String.valueOf(((JSONObject) obj2.get("AddLimitNodeOutputVO")).get("limitPrefix"));
                    LogMessages.statusLog.info("Exception retLimitMaintenanceRes: " + retCustDtls);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception retLimitMaintenanceRes: " + e);
        }
        LogMessages.statusLog.info("Response retLimitMaintenanceRes: " + response);
        return response;
    }

}
