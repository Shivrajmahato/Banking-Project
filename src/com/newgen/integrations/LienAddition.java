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
public class LienAddition {
public String LienAdditionReq(String[] param) {
        String request = "";
            //String initSolId = iformObj.getValue("table104_Solid").toString();
            //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject LienDt = new JSONObject();
        JSONObject NewLienAmt = new JSONObject();
        JSONObject LienDtls = new JSONObject();
        JSONObject BankInfo = new JSONObject();
        JSONObject AcctId = new JSONObject();
        JSONObject AcctLienAddRq = new JSONObject();
        JSONObject AcctLienAddRequest = new JSONObject();
        

        LienDt.put("StartDt", "2024-02-28T00:00:00.001");
        LienDt.put("EndDt", "2024-10-27T00:00:00.001");
        
        NewLienAmt.put("amountValue", "1000");
        NewLienAmt.put("currencyCode", "NPR");

        BankInfo.put("BankId", "003");
        
        AcctId.put("AcctId", "02100172179401");
        AcctId.put("AcctCurr", "NPR");
        AcctId.put("BankInfo", BankInfo);
        
        LienDtls.put("NewLienAmt", NewLienAmt);
        LienDtls.put("LienDt", LienDt);
        LienDtls.put("ReasonCode", "RTCD");
        LienDtls.put("Rmks", "test remarks");
        
        
        AcctLienAddRq.put("AcctId", AcctId);
        AcctLienAddRq.put("ModuleType", "ULIEN");
        AcctLienAddRq.put("LienDtls", LienDtls);
        
       
        

        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                AcctLienAddRequest.put("AcctLienAddRq", AcctLienAddRq);
                finalJson.put("AcctLienAddRequest",AcctLienAddRequest);
 LogMessages.statusLog.info("Request LienAdditionReq:  " + finalJson);
 request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception LienAdditionReq:  " + e);
        }
        LogMessages.statusLog.info("Request LienAdditionReq:  " + request);
        return request;
    }
public JSONArray LienAdditionRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String AcctLienAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("AcctLienAddResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(AcctLienAddResponse);
                    String AcctLienAddRs = String.valueOf((JSONObject) obj2.get("AcctLienAddRs"));
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(AcctLienAddRs);
                    
                    String AcctId = String.valueOf(((JSONObject) obj2.get("AcctLienAddRs")).get("AcctId"));
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
                    String AcctLienAdd_CustomData = String.valueOf((JSONObject) obj2.get("AcctLienAdd_CustomData"));
                    
                  LogMessages.statusLog.info("AcctLienAddResponse: " + AcctLienAddResponse);

                    res.put("AcctLienAddRs", AcctLienAddRs);
                    res.put("AcctLienAdd_CustomData", AcctLienAdd_CustomData);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LienAdditionRes: " + e);
        }
        LogMessages.statusLog.info("Response LienAdditionRes: " + response);
        return response;
    }
}
