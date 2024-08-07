/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Prajit
 */
public class LimitNodeModify {

    public String format(String input) {
        if (input.isEmpty() || input.equals("")) {
            return ""; // or any other default value
        } else {
            String[] parts = input.split("T");
            String dateonly = parts[0];
            String[] splitdt = dateonly.split("-");

            String day = splitdt[2];
            String month = splitdt[1];
            String year = splitdt[0];
            //String date = year + "-" + month + "-" + day + "T00:00:00.001";
            String date = day + "-" + month + "-" + year;

            return date;
        }
    }

    public String LimitNodeModifyReq(String input, IFormReference iFormObj) {
        //

        String request = "";

        String[] param = input.split("#");
        String Fid = param[2];
        LogMessages.statusLog.info("Inside Modify LimitMaintainenceReq: " + Fid);

        //request = "";
        try {
            // String limitExpiryDate = "2099-05-31T00:00:00.001"; //
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
            String statuscode = param[15];
            String limitDesc = param[16];
            String parentLimitPrefix = param[17];
            String parentLimitSuffix = param[18];
            String singleTranFlg = param[19];

            //request = "{\"modifyLimitNodeRequest\":{\"ModifyLimitNodeInputVO\":{\"limitExpiryDate\":\"" + limitExpiryDate +"\",\"limitPrefix\":\"" + limitPrefix + "\",\"limitSuffix\":\"" + limitSuffix  + "\",\"sanctLimit\":{\"amountValue\":\"" + amountValue + "\",\"currencyCode\":\"" + currencyCode + "\"},\"sanctAuthCode\":\"" + sanctAuthCode + "\",\"sanctLevelCode\":\"" + sanctLevelCode + "\",\"limitReviewDate\":\"" + limitReviewDate + "\",\"baseUserMaintLiab\":{\"amountValue\":\"" + baseUserAmountValue + "\",\"currencyCode\":\"" + baseUserCurrencyCode + "\"},\"limitSanctRef\":\"" + limitSanctRef + "\",\"limitSanctDate\":\"" + limitSanctDate + "\",\"freeText\":\"" + freeText + "\"}}}";
            request = "{\"modifyLimitNodeRequest\":{\"ModifyLimitNodeInputVO\":{\"limitExpiryDate\":\"" + limitExpiryDate + "\",\"parentLimitPrefix\":\"" + parentLimitPrefix + "\",\"parentLimitSuffix\":\"" + parentLimitSuffix + "\",\"limitPrefix\":\"" + limitPrefix + "\",\"limitSuffix\":\"" + limitSuffix + "\",\"statusCode\":\"" + statuscode + "\",\"sanctLimit\":{\"amountValue\":\"" + amountValue + "\",\"currencyCode\":\"" + currencyCode + "\"},\"sanctAuthCode\":\"" + sanctAuthCode + "\",\"singleTranFlg\":\"" + singleTranFlg + "\",\"sanctLevelCode\":\"" + sanctLevelCode + "\",\"limitReviewDate\":\"" + limitReviewDate + "\",\"baseUserMaintLiab\":{\"amountValue\":\"" + baseUserAmountValue + "\",\"currencyCode\":\"" + baseUserCurrencyCode + "\"},\"limitSanctRef\":\"" + limitSanctRef + "\",\"limitSanctDate\":\"" + limitSanctDate + "\",\"freeText\":\"" + freeText + "\",\"limitDesc\":\"" + limitDesc + "\"},\"modifyLimitNode_CustomData\":{\"limitExpiryExtendedUpto\":\"" + limitExpiryExtendedUpto + "\"}}}";

            LogMessages.statusLog.info("Modify LimitMaintainence Request ::: " + request);
            //  return request;

        } catch (Exception e) {
            LogMessages.statusLog.info("Exception of Modify LimitMaintainence Request ::: " + e);
        }
        return request;
    }

    public JSONArray LimitNodeModifyResponse(String input) {
        String[] param = input.split("##");
        LogMessages.statusLog.info("Modify  LimitNodeModifyResponse BODY:" + param[1]);
        JSONArray response = new JSONArray();
        JSONObject jsonres = new JSONObject();

        if (param[0].equalsIgnoreCase("SUCCESS")) {
            JSONParser parser = new JSONParser();
            JSONObject obj;

            try {

                obj = (JSONObject) parser.parse(param[1]);
                String code = String.valueOf(obj.get("Code"));
                if (code.equalsIgnoreCase("0")) {
                    jsonres.put("status", "SUCCESS");
                    response.add(jsonres);
                } else {
                    String status = String.valueOf(obj.get("Message"));
                    jsonres.put("status", "FAILURE");
                    jsonres.put("Message", status);
                    response.add(jsonres);
                    response.add(param[1]);
                }
            } catch (Exception e) {

            }

        } else {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[1]);
                String error = String.valueOf(obj);

                jsonres.put("status", error);
                LogMessages.statusLog.info("Indise of Modify LimitMaintainenceRes");
                response.add(jsonres);
            } catch (Exception e) {
            }
        }
        return response;

    }
}
