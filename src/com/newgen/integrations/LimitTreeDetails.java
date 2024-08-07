/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtil.getFailureResponse;
import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;

/**
 * @author bibek.shah
 **/
public class LimitTreeDetails {

    public String limitTreeRequest(String[] param) {
        String request = "";
        try {
            JSONObject finalRequest = new JSONObject();
            finalRequest.put("CustId", param[1]);
            request = finalRequest.toJSONString();
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitTreeRequest: " + e);
        }
        LogMessages.statusLog.info("Request limitTreeRequest: " + request);
        return request;
    }

    public JSONArray limitTreeResponse(String[] param, IFormReference iFormObj) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    JSONArray jsonArray = (JSONArray) obj.get("Data");
                    if (jsonArray.size() > 0) {
                        JSONArray tableArray = new JSONArray();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject arrayJsonObject = (JSONObject) jsonArray.get(i);
                            JSONObject tableJsonObject = new JSONObject();
                            tableJsonObject.put("LimitSuffix", String.valueOf(arrayJsonObject.get("LimitSuffix")));
                            tableJsonObject.put("LimitB2kId", String.valueOf(arrayJsonObject.get("LimitB2kId")));
                            tableJsonObject.put("ParentLimitB2kId", String.valueOf(arrayJsonObject.get("ParentLimitB2kId")));
                            tableJsonObject.put("LimitDescription", String.valueOf(arrayJsonObject.get("LimitDescription")));
                            tableJsonObject.put("SanctionLimit", String.valueOf(arrayJsonObject.get("SanctionLimit")));
                            tableJsonObject.put("DrawingPower", String.valueOf(arrayJsonObject.get("DrawingPower")));
                            tableJsonObject.put("Liability", String.valueOf(arrayJsonObject.get("Liability")));
                            tableJsonObject.put("ContingentLiability", String.valueOf(arrayJsonObject.get("ContingentLiability")));
                            tableJsonObject.put("NumberOfAccounts", String.valueOf(arrayJsonObject.get("NumberOfAccounts")));
                            tableJsonObject.put("CurrencyCode", String.valueOf(arrayJsonObject.get("CurrencyCode")));
                            tableJsonObject.put("LimitSanctionedDate", String.valueOf(arrayJsonObject.get("LimitSanctionedDate")));
                            tableJsonObject.put("LimitExpiryDate", String.valueOf(arrayJsonObject.get("LimitExpiryDate")));
                            tableJsonObject.put("Remarks", String.valueOf(arrayJsonObject.get("Remarks")));
                            tableArray.add(tableJsonObject);
                            iFormObj.addDataToGrid("tblLimitTree", tableArray);
                            tableArray.clear();
                        }
                    }
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitTreeResponse: " + e);
        }
        LogMessages.statusLog.info("Response limitTreeResponse: " + response);
        return response;
    }

}
