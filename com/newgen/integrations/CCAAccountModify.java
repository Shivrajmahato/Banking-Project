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
 * @author User
 */
public class CCAAccountModify {
     public String cCAAccountModifyReq(String input, IFormReference iFormObj) {
		String request = "";                
                String[] param = input.split("#");
                String repmtrecData="";
		try {
                    LogMessages.statusLog.info("Inside create Request of cCAAccountModifyReq."+param[0]);                   
                    //request = "{\"CAAcctModRequest\":{\"CAAcctModRq\":{\"CAAcctId\":{\"AcctId\":\""+ param[1] +"\"},\"CAAcctMod_CustomData\":{\"acctClassMISCEntered\":\""+ param[2] +"\",\"freeCode2\":\""+ param[3] +"\",\"freeCode3\":\""+ param[4] +"\",\"freeCode5\":\""+ param[5] +"\",\"freeCode6\":\""+ param[6] +"\",\"freeCode7\":\""+ param[7] +"\",\"freeCode9\":\""+ param[8] +"\",\"acctSchemeEntered\":\""+ param[9] +"\",\"maxAlwdAdvnLim\":\""+ param[10] +"\",\"acctInterestTaxEntered\":\""+ param[11] +"\",\"intCrAcctFlg\":\""+ param[12] +"\",\"intCrAcctId\":\""+ param[13] +"\",\"intDrAcctFlg\":\""+ param[14] +"\",\"intDrAcctId\":\""+ param[15] +"\"}}}}";
                    //request = "{\"CAAcctModRequest\":{\"CAAcctModRq\":{\"CAAcctId\":{\"AcctId\":\""+ param[1] +"\"},\"CAAcctMod_CustomData\":{\"acctClassMISCEntered\":\""+ param[2] +"\",\"freeCode2\":\""+ param[3] +"\",\"freeCode3\":\""+ param[4] +"\",\"freeCode5\":\""+ param[5] +"\",\"freeCode6\":\""+ param[6] +"\",\"freeCode7\":\""+ param[7] +"\",\"freeCode9\":\""+ param[8] +"\",\"acctSchemeEntered\":\""+ param[9] +"\",\"maxAlwdAdvnLim\":\""+ param[10] +"\",\"acctInterestTaxEntered\":\""+ param[11] +"\",\"intCrAcctFlg\":\""+ param[12] +"\",\"intDrAcctFlg\":\""+ param[13]+"\",\"intDrAcctId\":\""+ param[14] +"\"}}}}";
                    request = "{\"CAAcctModRequest\":{\"CAAcctModRq\":{\"CAAcctId\":{\"AcctId\":\""+ param[1] +"\"},\"CAAcctMod_CustomData\":{\"acctClassMISCEntered\":\""+ param[2]  +"\",\"acctSchemeEntered\":\""+ param[3] +"\",\"maxAlwdAdvnLim\":\""+ param[4] +"\",\"acctInterestTaxEntered\":\""+ param[5] +"\",\"intCrAcctFlg\":\""+ param[6] +"\",\"intDrAcctFlg\":\""+ param[7]+"\",\"intDrAcctId\":\""+ param[8] +"\",\"freeCode1\":\""+ param[9] +"\",\"freeCode2\":\""+ param[10] +"\",\"freeCode3\":\""+ param[11] +"\",\"freeCode4\":\""+ param[12] +"\",\"freeCode5\":\""+ param[13] +"\",\"freeCode6\":\""+ param[14]+"\",\"freeCode7\":\""+ param[15] +"\",\"freeCode8\":\""+ param[16]+"\",\"freeCode9\":\""+ param[17] +"\",\"freeCode10\":\""+ param[18]+"\",\"freetext3\":\""+ param[19]+"\",\"freetext6\":\""+ param[20] +"\",\"freetext10\":\""+ param[21]+"\",\"IndustryType\":\""+ param[22] +"\",\"sectorCode\":\""+ param[23] +"\",\"subSectorCode\":\""+ param[24] +"\",\"deprivedSectorType\":\""+ param[25] +"\",\"borrowerCategory\":\""+ param[26] +"\",\"purposeOfAdvance\":\""+ param[27]+"\",\"modeOfAdvance\":\""+ param[28] +"\",\"natureOfAdvance\":\""+ param[29]+"\",\"guaranteeCoverCode\":\""+ param[30] +"\",\"creditFileNumber\":\""+ param[31]+"\",\"drawingPowerIndicator\":\""+ param[32] +"\"}}}}";
                    LogMessages.statusLog.info("Request cCAAccountModifyReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception cCAAccountModifyReq:  " + e);
		}
		return request;
	}

	public JSONArray cCAAccountModifyRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("cCAAccountModifyRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
                                    LogMessages.statusLog.info("Exception cCAAccountModifyRes: " + code);
				String LoanAcctAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("CAAcctModResponse"));
                                LogMessages.statusLog.info("Exception cCAAccountModifyRes: " + LoanAcctAddResponse);
				JSONObject obj2 = (JSONObject) parser.parse(LoanAcctAddResponse);
                                String foracid = String.valueOf(((JSONObject) obj2.get("CAAcctModRs")).get("CAAcctId"));
                                JSONObject obj3 = (JSONObject) parser.parse(foracid);
                                String acctid = String.valueOf((obj3.get("AcctId")));
                                LogMessages.statusLog.info("Exception cCAAccountModifyRes: " + acctid);
				
				res.put("AcctId", acctid);
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
				LogMessages.statusLog.info("Exception cCAAccountModifyRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else cCAAccountModifyRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception cCAAccountModifyRes: " + e);
			}

		}
		return response;
	}

   
}
