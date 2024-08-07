package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author bibek.shah
 */
public class CCALoanAccountCreation {
    public String loanAccountCreationReq(String input, IFormReference iFormObj) {
		String request = "";                
                String[] param = input.split("#");
                String repmtrecData="";
		try {
                    LogMessages.statusLog.info("Inside create Request of CCALoanAccountCreation."+param[0]);
                    //request = "{\"doCCAccountOpeningRequest\":{\"CCAcctDetailsStructOpen\":{\"acctCommonInfo\":{\"acctGeneralInfo\":{\"acctBasic\":{\"acctCrncyCode\":\""+ param[1] +"\",\"glSubHeadCode\":{\"glSubHeadCode\":\""+ param[2] +"\"},\"schmCode\":{\"schmCode\":\""+ param[3] +"\",\"schmType\":\""+ param[4] +"\"},\"solId\":\""+ param[5] +"\"},\"acctCustInfo\":{\"custId\":{\"cifId\":\""+ param[6] +"\"}}},\"acctInterestTax\":{\"basicInterest\":{\"acctDrPrefPcnt\":{\"value\":\""+ param[7] +"\"},\"intTblCode\":{\"tableCode\":\""+ param[8] +"\"}},\"intDrAcctFlg\":\""+ param[9] +"\",\"intDrAcctId\":{\"foracid\":\""+ param[10] +"\"}},\"acctLimits\":{\"documentDate\":\""+ param[11] +"\",\"drawingPowerInd\":\""+ param[12] +"\",\"expiryDate\":\""+ param[13] +"\",\"limitIdPrefix\":\""+ param[14] +"\",\"limitIdSuffix\":\""+ param[15] +"\",\"sanctDate\":\""+ param[16] +"\",\"sanctionAuthCode\":\""+ param[17] +"\",\"sanctionLevelCode\":\""+ param[18] +"\",\"sanctionLimit\":{\"amountValue\":\""+ param[19] +"\",\"currencyCode\":\""+ param[20] +"\"},\"sanctionRefNum\":\""+ param[21] +"\"}},\"acctFFDParameters\":{\"repaymentAcid\":{\"crncyCode\":\""+ param[22] +"\",\"foracid\":\""+ param[23] +"\"}},\"CCODAcctSchmDtls\":{\"operAcctSchmDtls\":{\"drBalLim\":{\"amountValue\":\""+ param[24] +"\",\"currencyCode\":\""+ param[25] +"\"},\"healthCode\":\""+ param[26] +"\"},\"sancLimit\":{\"amountValue\":\""+ param[27] +"\",\"currencyCode\":\""+ param[28] +"\"}}}}}";
                    //request = "{\"doCCAccountOpeningRequest\":{\"CCAcctDetailsStructOpen\":{\"acctCommonInfo\":{\"acctGeneralInfo\":{\"acctBasic\":{\"acctCrncyCode\":\""+ param[1] +"\",\"glSubHeadCode\":{\"glSubHeadCode\":\""+ param[2] +"\"},\"schmCode\":{\"schmCode\":\""+ param[3] +"\",\"schmType\":\""+ param[4] +"\"},\"solId\":\""+ param[5] +"\"},\"acctCustInfo\":{\"custId\":{\"cifId\":\""+ param[6] +"\"}}},\"acctInterestTax\":{\"basicInterest\":{\"acctDrPrefPcnt\":{\"value\":\""+ param[7] +"\"},\"intTblCode\":{\"tableCode\":\""+ param[8] +"\"}},\"intDrAcctFlg\":\""+ param[9] +"\",\"intDrAcctId\":{\"foracid\":\""+ param[10] +"\"}},\"acctLimits\":{\"drawingPower\":{\"amountValue\":\""+ param[19] +"\",\"currencyCode\":\"NPR\"},\"documentDate\":\""+ param[11] +"\",\"drawingPowerInd\":\""+ param[12] +"\",\"expiryDate\":\""+ param[13] +"\",\"limitIdPrefix\":\""+ param[14] +"\",\"limitIdSuffix\":\""+ param[15] +"\",\"sanctDate\":\""+ param[16] +"\",\"sanctionAuthCode\":\""+ param[17] +"\",\"sanctionLevelCode\":\""+ param[18] +"\",\"sanctionLimit\":{\"amountValue\":\""+ param[19] +"\",\"currencyCode\":\""+ param[20] +"\"},\"sanctionRefNum\":\""+ param[21] +"\"}},\"acctFFDParameters\":{\"repaymentAcid\":{\"crncyCode\":\""+ param[22] +"\",\"foracid\":\""+ param[23] +"\"}},\"CCODAcctSchmDtls\":{\"operAcctSchmDtls\":{\"drBalLim\":{\"amountValue\":\""+ param[24] +"\",\"currencyCode\":\""+ param[25] +"\"},\"healthCode\":\""+ param[26] +"\",\"maxAlwdAdvnLim\":{\"amountValue\":\"600000.00\",\"currencyCode\":\"NPR\"}},\"sancLimit\":{\"amountValue\":\""+ param[27] +"\",\"currencyCode\":\""+ param[28] +"\"}}}}}";
                    request = "{\"doCCAccountOpeningRequest\":{\"CCAcctDetailsStructOpen\":{\"acctCommonInfo\":{\"acctGeneralInfo\":{\"acctBasic\":{\"acctCrncyCode\":\""+ param[1] +"\",\"glSubHeadCode\":{\"glSubHeadCode\":\""+ param[2] +"\"},\"schmCode\":{\"schmCode\":\""+ param[3] +"\",\"schmType\":\""+ param[4] +"\"},\"solId\":\""+ param[5] +"\"},\"acctCustInfo\":{\"custId\":{\"cifId\":\""+ param[6] +"\"}}},\"acctInterestTax\":{\"basicInterest\":{\"acctDrPrefPcnt\":{\"value\":\""+ param[7] +"\"},\"intTblCode\":{\"tableCode\":\""+ param[8] +"\"}},\"intDrAcctFlg\":\""+ param[9] +"\",\"intDrAcctId\":{\"foracid\":\""+ param[10] +"\"}},\"acctLimits\":{\"drawingPower\":{\"amountValue\":\""+ param[19] +"\",\"currencyCode\":\"NPR\"},\"documentDate\":\""+ param[11] +"\",\"drawingPowerInd\":\""+ param[12] +"\",\"expiryDate\":\""+ param[13] +"\",\"limitIdPrefix\":\""+ param[14] +"\",\"limitIdSuffix\":\""+ param[15] +"\",\"sanctDate\":\""+ param[16] +"\",\"sanctionAuthCode\":\""+ param[17] +"\",\"sanctionLevelCode\":\""+ param[18] +"\",\"sanctionLimit\":{\"amountValue\":\""+ param[19] +"\",\"currencyCode\":\""+ param[20] +"\"},\"sanctionRefNum\":\""+ param[21] +"\"}},\"acctFFDParameters\":{\"repaymentAcid\":{\"crncyCode\":\""+ param[22] +"\",\"foracid\":\""+ param[23] +"\"}},\"CCODAcctSchmDtls\":{\"operAcctSchmDtls\":{\"drBalLim\":{\"amountValue\":\""+ param[24] +"\",\"currencyCode\":\""+ param[25] +"\"},\"healthCode\":\""+ param[26] +"\",\"maxAlwdAdvnLim\":{\"amountValue\":\""+ param[19] +"\",\"currencyCode\":\"NPR\"}},\"sancLimit\":{\"amountValue\":\""+ param[27] +"\",\"currencyCode\":\""+ param[28] +"\"}}},\"doCCAccountOpening_CustomData\":{\"freeCode1\":\"" + param[29] + "\",\"freeCode3\":\"" + param[30] + "\",\"freeCode6\":\"" + param[31]+ "\",\"freeCode8\":\"" + param[32]  + "\",\"freeCode9\":\"" + param[33] + "\",\"freetext3\":\"" + param[34] + "\",\"freetext6\":\"" + param[35] + "\",\"freetext10\":\"" + param[36] + "\",\"industryType\":\"" + param[37] + "\",\"sectorCode\":\"" + param[38] + "\",\"subSectorCode\":\"" + param[39] + "\",\"deprivedSector\":\"" + param[40] +"\",\"freeCode2\":\"" + param[41]+ "\",\"freeCode4\":\"" + param[42]+ "\",\"freeCode5\":\"" + param[43]+ "\",\"freeCode7\":\"" + param[44]+ "\",\"freeCode10\":\"" + param[45] + "\",\"borrowerCategory\":\"" + param[46]+ "\",\"purposeOfAdvance\":\"" + param[47]+ "\",\"modeOfAdvance\":\"" + param[48]+ "\",\"natureOfAdvance\":\"" + param[49]+ "\",\"guaranteeCoverCode\":\"" + param[50] + "\",\"creditFileNumber\":\"" + param[51] + "\",\"limitIdPrefix\":\"" + param[52] + "\",\"limitIdSuffix\":\"" + param[53]+ "\"}}}";
                    LogMessages.statusLog.info("Request:::" + request);
                } catch(Exception e) {
			LogMessages.statusLog.info("Exception CCALoanAccountCreation:  " + e);
		}
		return request;
	}

	public JSONArray loanAccountCreationRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("CCALoanAccountCreationRes BODY:::" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
                                    LogMessages.statusLog.info("Exception CCALoanAccountCreationRes: " + code);
				String LoanAcctAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("doCCAccountOpeningResponse"));
                                LogMessages.statusLog.info("Exception CCALoanAccountCreationRes: " + LoanAcctAddResponse);
				JSONObject obj2 = (JSONObject) parser.parse(LoanAcctAddResponse);
                                String foracid = String.valueOf(((JSONObject) obj2.get("CCOutputStructure")).get("foracid"));
                                LogMessages.statusLog.info("Exception CCALoanAccountCreationRes: " + foracid);
				
				res.put("AcctId", foracid);
                                res.put("status", "SUCCESS");
				response.add(res);
				}else {
					res.put("status", "FAILURE");
					String message = String.valueOf(obj.get("Message"));
					res.put("message", message);
					response.add(res);
                                        response.add(param[1]);

				}
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception CCALoanAccountCreationRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				
				response.add(res);
                                LogMessages.statusLog.info("else CCALoanAccountCreationRes");
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception CCALoanAccountCreationRes: " + e);
			}

		}
		return response;
	}

}
