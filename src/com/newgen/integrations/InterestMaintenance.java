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
public class InterestMaintenance {

    public String interestMaintenanceReq(String[] param) {
        String request = "";
        try {
            request = "{\"IntPrefModRequest\":{\"IntPrefModRq\":{\"EntityType\":\"" + param[1] + "\",\"EntityDispId\":\"" + param[2] + "\",\"IntTblDetailRec\":{\"IntTblCode\":\"" + param[3] + "\",\"startDate\":\"" + param[4] + "\",\"endDate\":\"" + "2099-12-31T00:00:00.001" + "\",\"acctPrefIntDr\":{\"value\":\"" + param[5] + "\"}}}}}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception interestMaintenanceReq:  " + e);
        }
        LogMessages.statusLog.info("Request interestMaintenanceReq:  " + request);
        return request;
    }

    public JSONArray interestMaintenanceRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String intPrefModResponse = String.valueOf(((JSONObject) obj.get("Data")).get("IntPrefModResponse"));
                    LogMessages.statusLog.info("intPrefModResponse: " + intPrefModResponse);
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(intPrefModResponse);
                    String intPrefModRs = String.valueOf((obj2.get("IntPrefModRs")));
                    LogMessages.statusLog.info("intPrefModRs: " + intPrefModRs);
                    String intPrefModCustomData = String.valueOf((obj2.get("IntPrefMod_CustomData")));
                    res.put("IntPrefMod_CustomData", intPrefModCustomData);
                    res.put("IntPrefModRs", intPrefModRs);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception interestMaintenanceRes: " + e);
        }
        LogMessages.statusLog.info("Response interestMaintenanceRes: " + response);
        return response;
    }

}
