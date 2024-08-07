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
public class CCALoanAccountCreation {

    public String loanAccountCreationReq(String[] param) {
        String request = "";
        try {
            request = "{\"doCCAccountOpeningRequest\":{\"CCAcctDetailsStructOpen\":{\"acctCommonInfo\":{\"acctGeneralInfo\":{\"acctBasic\":{\"acctCrncyCode\":\"" + param[1] + "\",\"glSubHeadCode\":{\"glSubHeadCode\":\"" + param[2] + "\"},\"schmCode\":{\"schmCode\":\"" + param[3] + "\",\"schmType\":\"" + param[4] + "\"},\"solId\":\"" + param[5] + "\"},\"acctCustInfo\":{\"custId\":{\"cifId\":\"" + param[6] + "\"}}},\"acctInterestTax\":{\"basicInterest\":{\"acctDrPrefPcnt\":{\"value\":\"" + param[7] + "\"},\"intTblCode\":{\"tableCode\":\"" + param[8] + "\"}},\"intDrAcctFlg\":\"" + param[9] + "\",\"intDrAcctId\":{\"foracid\":\"" + param[10] + "\"}},\"acctLimits\":{\"drawingPower\":{\"amountValue\":\"" + param[19] + "\",\"currencyCode\":\"NPR\"},\"documentDate\":\"" + param[11] + "\",\"drawingPowerInd\":\"" + param[12] + "\",\"expiryDate\":\"" + param[13] + "\",\"limitIdPrefix\":\"" + param[14] + "\",\"limitIdSuffix\":\"" + param[15] + "\",\"sanctDate\":\"" + param[16] + "\",\"sanctionAuthCode\":\"" + param[17] + "\",\"sanctionLevelCode\":\"" + param[18] + "\",\"sanctionLimit\":{\"amountValue\":\"" + param[19] + "\",\"currencyCode\":\"" + param[20] + "\"},\"sanctionRefNum\":\"" + param[21] + "\"}},\"acctFFDParameters\":{\"repaymentAcid\":{\"crncyCode\":\"" + param[22] + "\",\"foracid\":\"" + param[23] + "\"}},\"CCODAcctSchmDtls\":{\"operAcctSchmDtls\":{\"drBalLim\":{\"amountValue\":\"" + param[24] + "\",\"currencyCode\":\"" + param[25] + "\"},\"healthCode\":\"" + param[26] + "\",\"maxAlwdAdvnLim\":{\"amountValue\":\"" + param[19] + "\",\"currencyCode\":\"NPR\"}},\"sancLimit\":{\"amountValue\":\"" + param[27] + "\",\"currencyCode\":\"" + param[28] + "\"}}},\"doCCAccountOpening_CustomData\":{\"freeCode1\":\"" + param[29] + "\",\"freeCode3\":\"" + param[30] + "\",\"freeCode6\":\"" + param[31] + "\",\"freeCode8\":\"" + param[32] + "\",\"freeCode9\":\"" + param[33] + "\",\"freetext3\":\"" + param[34] + "\",\"freetext6\":\"" + param[35] + "\",\"freetext10\":\"" + param[36] + "\",\"industryType\":\"" + param[37] + "\",\"sectorCode\":\"" + param[38] + "\",\"subSectorCode\":\"" + param[39] + "\",\"deprivedSector\":\"" + param[40] + "\",\"freeCode2\":\"" + param[41] + "\",\"freeCode4\":\"" + param[42] + "\",\"freeCode5\":\"" + param[43] + "\",\"freeCode7\":\"" + param[44] + "\",\"freeCode10\":\"" + param[45] + "\",\"borrowerCategory\":\"" + param[46] + "\",\"purposeOfAdvance\":\"" + param[47] + "\",\"modeOfAdvance\":\"" + param[48] + "\",\"natureOfAdvance\":\"" + param[49] + "\",\"guaranteeCoverCode\":\"" + param[50] + "\",\"creditFileNumber\":\"" + param[51] + "\",\"limitIdPrefix\":\"" + param[52] + "\",\"limitIdSuffix\":\"" + param[53] + "\"}}}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanAccountCreationReq: " + e);
        }
        LogMessages.statusLog.info("Request loanAccountCreationReq: " + request);
        return request;
    }

    public JSONArray loanAccountCreationRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String loanAcctAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("doCCAccountOpeningResponse"));
                    LogMessages.statusLog.info("loanAcctAddResponse: " + loanAcctAddResponse);
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(loanAcctAddResponse);
                    String forAcId = String.valueOf(((JSONObject) obj2.get("CCOutputStructure")).get("foracid"));
                    LogMessages.statusLog.info("forAcId: " + forAcId);

                    res.put("AcctId", forAcId);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanAccountCreationRes: " + e);
        }
        LogMessages.statusLog.info("Response loanAccountCreationRes: " + response);
        return response;
    }

}
