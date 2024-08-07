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
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Shivaraj Mahato
 */
public class LienModification {
public String LienModificationReq(String[] param) {
        String request = "";
            //String initSolId = iformObj.getValue("table104_Solid").toString();
            //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject AcctLienModRequest = new JSONObject();
        JSONObject AcctLienModRq = new JSONObject();
        JSONObject AcctId = new JSONObject();
        JSONObject BankInfo = new JSONObject();
        JSONObject LienDtls = new JSONObject();
        JSONObject LienDt = new JSONObject();
        JSONObject NewLienAmt = new JSONObject();
        

        LienDt.put("StartDt", "2024-02-28T00:00:00.001");
        LienDt.put("EndDt", "2024-10-27T00:00:00.001");
        
        NewLienAmt.put("amountValue", "0");
        NewLienAmt.put("currencyCode", "NPR");

        BankInfo.put("BankId", "003");
        BankInfo.put("BranchId", "17");
        
        
        AcctId.put("AcctId", "02100172179401");
        AcctId.put("BankInfo", BankInfo);
        
        LienDtls.put("LienId", "DC553861");
        LienDtls.put("LienDt", LienDt);
        LienDtls.put("NewLienAmt", NewLienAmt);
        
        
        
        AcctLienModRq.put("AcctId", AcctId);
        AcctLienModRq.put("ModuleType", "ULIEN");
        AcctLienModRq.put("LienDtls", LienDtls);
        
       
        

        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                AcctLienModRequest.put("AcctLienModRq", AcctLienModRq);
                finalJson.put("AcctLienModRequest",AcctLienModRequest);
 LogMessages.statusLog.info("Request LienModificationReq:  " + finalJson);
 request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception LienModificationReq:  " + e);
        }
        LogMessages.statusLog.info("Request LienModificationReq:  " + request);
        return request;
    }
public JSONArray LienModificationRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String AcctLienModResponse = String.valueOf(((JSONObject) obj.get("Data")).get("AcctLienModResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(AcctLienModResponse);
                    String AcctLienModRs = String.valueOf((JSONObject) obj2.get("AcctLienModRs"));
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(AcctLienModRs);
                    
                    String AcctId = String.valueOf(((JSONObject) obj2.get("AcctLienModRs")).get("AcctId"));
                    JSONObject obj4 = NewGenUtil.getParsedJsonObj(AcctId);
                    String AcctId1 = String.valueOf((JSONObject) obj4.get("AcctId"));
                    String SchmCode = String.valueOf(((JSONObject) obj4.get("AcctType")).get("SchmCode"));
                    String SchmType = String.valueOf(((JSONObject) obj4.get("AcctType")).get("SchmType"));
                    String AcctCurr = String.valueOf((JSONObject) obj4.get("AcctCurr"));
                    String BankId = String.valueOf(((JSONObject) obj4.get("BankInfo")).get("BankId"));
                    String Name = String.valueOf(((JSONObject) obj4.get("BankInfo")).get("Name"));
                    String BranchId = String.valueOf(((JSONObject) obj4.get("BankInfo")).get("BranchId"));
                    String BranchName = String.valueOf(((JSONObject) obj4.get("BankInfo")).get("BranchName"));
                    String PostAddr = String.valueOf(((JSONObject) obj4.get("BankInfo")).get("PostAddr"));
                    JSONObject obj5 = NewGenUtil.getParsedJsonObj(PostAddr);
                    String Addr1 = String.valueOf((JSONObject) obj5.get("Addr1"));
                    String Addr2 = String.valueOf((JSONObject) obj5.get("Addr2"));
                    String Addr3 = String.valueOf((JSONObject) obj5.get("Addr3"));
                    String City = String.valueOf((JSONObject) obj5.get("City"));
                    String StateProv = String.valueOf((JSONObject) obj5.get("StateProv"));
                    String PostalCode = String.valueOf((JSONObject) obj5.get("PostalCode"));
                    String Country = String.valueOf((JSONObject) obj5.get("Country"));
                    String AddrType = String.valueOf((JSONObject) obj5.get("AddrType"));
                    String LienIdRec = String.valueOf((JSONObject) obj3.get("LienIdRec"));
                    JSONObject obj6 = NewGenUtil.getParsedJsonObj(LienIdRec);
                    String LienId = String.valueOf((JSONObject) obj6.get("LienId"));
                    String AcctLienMod_CustomData = String.valueOf((JSONObject) obj2.get("AcctLienMod_CustomData"));
                    
                  LogMessages.statusLog.info("AcctLienModResponse: " + AcctLienModResponse);

                    res.put("AcctLienModRs", AcctLienModRs);
                    res.put("AcctLienMod_CustomData", AcctLienMod_CustomData);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LienModificationRes: " + e);
        }
        LogMessages.statusLog.info("Response LienModificationRes: " + response);
        return response;
    }
}
