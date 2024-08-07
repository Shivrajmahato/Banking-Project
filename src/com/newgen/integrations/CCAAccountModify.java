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
public class CCAAccountModify {
    public String cCAAccountModifyReq( String[] param) {
        String request = "";
        try {
            request = "{\"CAAcctModRequest\":{\"CAAcctModRq\":{\"CAAcctId\":{\"AcctId\":\"" + param[1] + "\"},\"CAAcctMod_CustomData\":{\"acctClassMISCEntered\":\"" + param[2] + "\",\"acctSchemeEntered\":\"" + param[3] + "\",\"maxAlwdAdvnLim\":\"" + param[4] + "\",\"acctInterestTaxEntered\":\"" + param[5] + "\",\"intCrAcctFlg\":\"" + param[6] + "\",\"intDrAcctFlg\":\"" + param[7] + "\",\"intDrAcctId\":\"" + param[8] + "\",\"freeCode1\":\"" + param[9] + "\",\"freeCode2\":\"" + param[10] + "\",\"freeCode3\":\"" + param[11] + "\",\"freeCode4\":\"" + param[12] + "\",\"freeCode5\":\"" + param[13] + "\",\"freeCode6\":\"" + param[14] + "\",\"freeCode7\":\"" + param[15] + "\",\"freeCode8\":\"" + param[16] + "\",\"freeCode9\":\"" + param[17] + "\",\"freeCode10\":\"" + param[18] + "\",\"freetext3\":\"" + param[19] + "\",\"freetext6\":\"" + param[20] + "\",\"freetext10\":\"" + param[21] + "\",\"IndustryType\":\"" + param[22] + "\",\"sectorCode\":\"" + param[23] + "\",\"subSectorCode\":\"" + param[24] + "\",\"deprivedSectorType\":\"" + param[25] + "\",\"borrowerCategory\":\"" + param[26] + "\",\"purposeOfAdvance\":\"" + param[27] + "\",\"modeOfAdvance\":\"" + param[28] + "\",\"natureOfAdvance\":\"" + param[29] + "\",\"guaranteeCoverCode\":\"" + param[30] + "\",\"creditFileNumber\":\"" + param[31] + "\",\"drawingPowerIndicator\":\"" + param[32] + "\"}}}}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception cCAAccountModifyReq:  " + e);
        }
        LogMessages.statusLog.info("Request cCAAccountModifyReq:  " + request);
        return request;
    }

    public JSONArray cCAAccountModifyRes(String [] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String loanAcctAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("CAAcctModResponse"));
                    LogMessages.statusLog.info("loanAcctAddResponse: " + loanAcctAddResponse);
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(loanAcctAddResponse);
                    String forAcid = String.valueOf(((JSONObject) obj2.get("CAAcctModRs")).get("CAAcctId"));
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(forAcid);
                    String acctId = String.valueOf((obj3.get("AcctId")));
                    LogMessages.statusLog.info("acctId: " + acctId);

                    res.put("AcctId", acctId);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception cCAAccountModifyRes: " + e);
        }
        LogMessages.statusLog.info("Response cCAAccountModifyRes: " + response);
        return response;
    }


}
