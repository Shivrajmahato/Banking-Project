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
 * @author Prajit
 */
public class LimitNodeModify {

    public String format(String input) {
        if (input.isEmpty()) {
            return ""; // or any other default value
        } else {
            String[] parts = input.split("T");
            String[] splitDt = parts[0].split("-");
            String day = splitDt[2];
            String month = splitDt[1];
            String year = splitDt[0];
            return day + "-" + month + "-" + year;
        }
    }

    public String limitNodeModifyReq(String[] param) {
        String request = "";
        try {
            String limitExpiryDate = param[2];
            String limitPrefix = param[3];
            String limitSuffix = param[4];
            String amountValue = param[5];
            String currencyCode = param[6];
            String sanctAuthCode = param[7];
            String sanctLevelCode = param[8];
            String limitReviewDate = param[9];
            String baseUserAmountValue = param[10];
            String baseUserCurrencyCode = param[11];
            String limitSanctRef = param[12];
            String limitSanctDate = param[13];
            String freeText = param[14];
            String limitExpiryExtendedUpto = format(limitExpiryDate);
            String statusCode = param[15];
            String limitDesc = param[16];
            String parentLimitPrefix = param[17];
            String parentLimitSuffix = param[18];
            String singleTranFlg = param[19];

            request = "{\"modifyLimitNodeRequest\":{\"ModifyLimitNodeInputVO\":{\"limitExpiryDate\":\"" + limitExpiryDate + "\",\"parentLimitPrefix\":\"" + parentLimitPrefix + "\",\"parentLimitSuffix\":\"" + parentLimitSuffix + "\",\"limitPrefix\":\"" + limitPrefix + "\",\"limitSuffix\":\"" + limitSuffix + "\",\"statusCode\":\"" + statusCode + "\",\"sanctLimit\":{\"amountValue\":\"" + amountValue + "\",\"currencyCode\":\"" + currencyCode + "\"},\"sanctAuthCode\":\"" + sanctAuthCode + "\",\"singleTranFlg\":\"" + singleTranFlg + "\",\"sanctLevelCode\":\"" + sanctLevelCode + "\",\"limitReviewDate\":\"" + limitReviewDate + "\",\"baseUserMaintLiab\":{\"amountValue\":\"" + baseUserAmountValue + "\",\"currencyCode\":\"" + baseUserCurrencyCode + "\"},\"limitSanctRef\":\"" + limitSanctRef + "\",\"limitSanctDate\":\"" + limitSanctDate + "\",\"freeText\":\"" + freeText + "\",\"limitDesc\":\"" + limitDesc + "\"},\"modifyLimitNode_CustomData\":{\"limitExpiryExtendedUpto\":\"" + limitExpiryExtendedUpto + "\"}}}";

        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitNodeModifyReq: " + e);
        }
        LogMessages.statusLog.info("Request limitNodeModifyReq: " + request);
        return request;
    }

    public JSONArray limitNodeModifyResponse(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject jsonRes = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    jsonRes.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, jsonRes, obj);
                }
            } else {
                jsonRes.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(jsonRes);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitNodeModifyResponse:" + e);
        }
        LogMessages.statusLog.info("Response limitNodeModifyResponse:" + response);
        return response;
    }
}
