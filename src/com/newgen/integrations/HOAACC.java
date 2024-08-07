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
public class HOAACC {
public String HOAACCReq(String[] param) {
        String request = "";
            //String initSolId = iformObj.getValue("table104_Solid").toString();
            //String loanAccountNumber = iformObj.getValue("table104_LoanAccountNumber").toString();
        try {

            //request = "{\"executeFinacleScriptRequest\":{\"ExecuteFinacleScriptInputVO\":{\"requestId\":\"hladsp.scr\"},\"executeFinacleScript_CustomData\":{\"initSolId\":\"" + param[1] + "\",\"loanAccountNumber\":\"" + param[2] + "\"}}}";
            JSONObject finalJson = new JSONObject();
            JSONObject doCCAccountOpeningRequest = new JSONObject();
        JSONObject CCAcctDetailsStructOpen = new JSONObject();
        JSONObject acctCommonInfo = new JSONObject();
        JSONObject acctGeneralInfo = new JSONObject();
        JSONObject acctClassMISC = new JSONObject();
        JSONObject acctBasic = new JSONObject();
        JSONObject glSubHeadCode = new JSONObject();
        JSONObject schmCode = new JSONObject();
        JSONObject acctCustInfo = new JSONObject();
        JSONObject custId = new JSONObject();
        JSONObject acctInterestTax = new JSONObject();
        JSONObject basicInterest = new JSONObject();
        JSONObject acctDrPrefPcnt = new JSONObject();
        JSONObject intTblCode = new JSONObject();
        JSONObject intDrAcctId = new JSONObject();
        JSONObject acctLimits = new JSONObject();
        JSONObject drawingPower = new JSONObject();
        JSONObject sanctionLimit = new JSONObject();
        JSONObject acctFFDParameters = new JSONObject();
        JSONObject repaymentAcid = new JSONObject();
        JSONObject CCODAcctSchmDtls = new JSONObject();
        JSONObject operAcctSchmDtls = new JSONObject();
        JSONObject drBalLim = new JSONObject();
        JSONObject maxAlwdAdvnLim = new JSONObject();
        JSONObject sancLimit = new JSONObject();
        JSONObject doCCAccountOpening_CustomData = new JSONObject();
        

        acctClassMISC.put("freeCode3", "A0018");
        acctClassMISC.put("typeOfAdvn", "9999");
        acctClassMISC.put("freeCode6", "DRM");
        acctClassMISC.put("sectorCode", "T");
        acctClassMISC.put("subSectorCode", "T0102");
        
        glSubHeadCode.put("glSubHeadCode", "6408");
        schmCode.put("schmCode", "CCLFD");
        schmCode.put("schmType", "CCA");
        
        acctBasic.put("acctCrncyCode", "NPR");
        acctBasic.put("glSubHeadCode", glSubHeadCode);
        acctBasic.put("schmCode", schmCode);
        acctBasic.put("solId", "84");
        custId.put("cifId", "842858905");
        acctCustInfo.put("custId", custId);

        acctGeneralInfo.put("acctClassMISC", acctClassMISC);
        acctGeneralInfo.put("acctBasic", acctBasic);
        acctGeneralInfo.put("acctCustInfo", acctCustInfo);
        
        
        
        intDrAcctId.put("foracid", "08410017501555");
        intTblCode.put("tableCode", "00.00");
        acctDrPrefPcnt.put("value", "14.63");
        
        basicInterest.put("acctDrPrefPcnt", acctDrPrefPcnt);
        basicInterest.put("intTblCode", intTblCode);
        acctInterestTax.put("basicInterest", basicInterest);
        acctInterestTax.put("intDrAcctFlg", "P");
        acctInterestTax.put("intDrAcctId", intDrAcctId);
        
        drawingPower.put("amountValue", "600000.00");
        drawingPower.put("currencyCode", "NPR");
        sanctionLimit.put("amountValue", "600000.00");
        sanctionLimit.put("currencyCode", "NPR");
        
        acctLimits.put("documentDate", "2024-02-28T00:00:00.001");
        acctLimits.put("drawingPower", drawingPower);
        acctLimits.put("drawingPowerInd", "E");
        acctLimits.put("expiryDate", "2024-07-16T00:00:00.001");
        acctLimits.put("limitIdPrefix", "842858905");
        acctLimits.put("limitIdSuffix", "INST1");
        acctLimits.put("sanctDate", "2024-02-28T00:00:00.001");
        acctLimits.put("sanctionAuthCode", "CEO");
        acctLimits.put("sanctionLevelCode", "HO");
        acctLimits.put("sanctionLimit", sanctionLimit);
        acctLimits.put("sanctionRefNum", "001");
        
        acctCommonInfo.put("acctGeneralInfo", acctGeneralInfo);
        acctCommonInfo.put("acctInterestTax", acctInterestTax);
        acctCommonInfo.put("acctLimits", acctLimits);
        
        repaymentAcid.put("crncyCode", "NPR");
        repaymentAcid.put("foracid", "08410017501555");
        drBalLim.put("amountValue", "600000.00");
        drBalLim.put("currencyCode", "NPR");
        maxAlwdAdvnLim.put("amountValue", "600000.00");
        maxAlwdAdvnLim.put("currencyCode", "NPR");
        sancLimit.put("amountValue", "600000.00");
        sancLimit.put("currencyCode", "NPR");
        
        operAcctSchmDtls.put("drBalLim", drBalLim);
        operAcctSchmDtls.put("healthCode", "01");
        operAcctSchmDtls.put("maxAlwdAdvnLim", maxAlwdAdvnLim);
       
        
        acctFFDParameters.put("repaymentAcid", repaymentAcid);
        CCODAcctSchmDtls.put("operAcctSchmDtls", operAcctSchmDtls);
        CCODAcctSchmDtls.put("sancLimit", sancLimit);
        
        CCAcctDetailsStructOpen.put("acctCommonInfo", acctCommonInfo);
        CCAcctDetailsStructOpen.put("acctFFDParameters", acctFFDParameters);
        CCAcctDetailsStructOpen.put("CCODAcctSchmDtls", CCODAcctSchmDtls);
        
        doCCAccountOpening_CustomData.put("freeCode2", "J999");
        doCCAccountOpening_CustomData.put("freeCode3", "A0018");
        doCCAccountOpening_CustomData.put("freeCode5", "9999");
        doCCAccountOpening_CustomData.put("freeCode6", "DRM");
        doCCAccountOpening_CustomData.put("freeCode7", "17.1");
        doCCAccountOpening_CustomData.put("freeCode9", "FNO");
        
        
        
        
        
        
       
        

        //xecuteFinacleScriptRequest.put("executeFinacleScriptRequest", new JSONObject()
                doCCAccountOpeningRequest.put("CCAcctDetailsStructOpen", CCAcctDetailsStructOpen);
                doCCAccountOpeningRequest.put("doCCAccountOpening_CustomData", doCCAccountOpening_CustomData);
                
                finalJson.put("doCCAccountOpeningRequest",doCCAccountOpeningRequest);
 LogMessages.statusLog.info("Request HOAACCReq:  " + finalJson);
 request = finalJson.toString();
            } catch (Exception e) {
            LogMessages.statusLog.info("Exception HOAACCReq:  " + e);
        }
        LogMessages.statusLog.info("Request HOAACCReq:  " + request);
        return request;
    } 
public JSONArray HOAACCRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String doCCAccountOpeningResponse = String.valueOf(((JSONObject) obj.get("Data")).get("doCCAccountOpeningResponse"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(doCCAccountOpeningResponse);
                    String CCOutputStructure = String.valueOf((JSONObject) obj2.get("CCOutputStructure"));
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(CCOutputStructure);
                    String foracid = String.valueOf((JSONObject) obj3.get("foracid"));
                   // JSONObject obj4 = NewGenUtil.getParsedJsonObj(foracid);
                    String doCCAccountOpening_CustomData = String.valueOf((JSONObject) obj2.get("doCCAccountOpening_CustomData"));
                    
                     LogMessages.statusLog.info("doCCAccountOpeningResponse: " + doCCAccountOpeningResponse);

                    res.put("CCOutputStructure", CCOutputStructure);
                    res.put("doCCAccountOpening_CustomData", doCCAccountOpening_CustomData);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception HOAACCRes: " + e);
        }
        LogMessages.statusLog.info("Response HOAACCRes: " + response);
        return response;
    }
}
