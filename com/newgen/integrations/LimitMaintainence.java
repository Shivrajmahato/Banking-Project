/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.APIConsumption;
import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author bibek.shah
 */
public class LimitMaintainence {
    	public String retLimitMaintainenceReq(String input, IFormReference iFormObj) {
		String request = "",request2="",request3;
		String[] param = input.split("#");
		try {
			LogMessages.statusLog.info("Inside create Request of LimitMaintainence." + param[0]);
			//request = "{\"addLimitNodeRequest\":{\"AddLimitNodeInputVO\":{\"baseUserMaintLiab\":{\"amountValue\":\"" + param[1] + "\",\"currencyCode\":\"" + param[2] + "\"},\"crncy\":\"" + param[3] + "\",\"custId\":{\"cifId\":\"" + param[4] + "\"},\"sanctAuthCode\":\"" + param[5] + "\",\"sanctLevelCode\":\"" + param[6] + "\",\"statusCode\":\"" + param[7] + "\",\"limitSanctRef\":\"" + param[8] + "\",\"drwngPowerInd\":\"" + param[9] + "\",\"parentLimitPrefix\":\"" + param[10] + "\",\"parentLimitSuffix\":\"" + param[11] + "\",\"limitDesc\":\"" + param[12] + "\",\"limitReviewDate\":\"" + param[13] + "\",\"limitExpiryDate\":\"" + param[14] + "\",\"limitPrefix\":\"" + param[15] + "\",\"limitSanctDate\":\"" + param[16] + "\",\"limitSuffix\":\"" + param[17] + "\",\"limitType\":\"" + param[18] + "\",\"freeText\":\"" + param[19] + "\",\"sanctLimit\":{\"amountValue\":\"" + param[20] + "\",\"currencyCode\":\"" + param[21] + "\"}},\"addLimitNode_CustomData\":{\"LIMITMASTERCODE\":\"" + param[22] + "\",\"globalLimitFlg\":\"" + param[23] + "\"}}}";
                        request = "{\"addLimitNodeRequest\":{\"AddLimitNodeInputVO\":{\"baseUserMaintLiab\":{\"amountValue\":\"" + param[1] + "\",\"currencyCode\":\"" + param[2] + "\"},\"crncy\":\"" + param[3] + "\",\"custId\":{\"cifId\":\"" + param[4] + "\"},\"sanctAuthCode\":\"" + param[5] + "\",\"sanctLevelCode\":\"" + param[6] + "\",\"singleTranFlg\":\"" + param[24] + "\",\"statusCode\":\"" + param[7] + "\",\"limitSanctRef\":\"" + param[8] + "\",\"drwngPowerInd\":\"" + param[9] + "\",\"parentLimitPrefix\":\"" + param[10] + "\",\"parentLimitSuffix\":\"" + param[11] + "\",\"limitDesc\":\"" + param[12] + "\",\"limitReviewDate\":\"" + param[13] + "\",\"limitExpiryDate\":\"" + param[14] + "\",\"limitPrefix\":\"" + param[15] + "\",\"limitSanctDate\":\"" + param[16] + "\",\"limitSuffix\":\"" + param[17] + "\",\"limitType\":\"" + param[18] + "\",\"freeText\":\"" + param[19] + "\",\"sanctLimit\":{\"amountValue\":\"" + param[20] + "\",\"currencyCode\":\"" + param[21] + "\"}},\"addLimitNode_CustomData\":{\"LIMITMASTERCODE\":\"" + param[22] + "\",\"globalLimitFlg\":\"" + param[23] + "\"}}}";                 
                        LogMessages.statusLog.info("############LimitMaintainence REQUEST::::" + request);
                        
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception LimitMaintainenceReq: " + e);
		}
		return request;
	}

	public JSONArray retLimitMaintainenceRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("retLimitMaintainenceRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
				String addLimitNodeResponse = String.valueOf(((JSONObject) obj.get("Data")).get("addLimitNodeResponse"));
				JSONObject obj2 = (JSONObject) parser.parse(addLimitNodeResponse);
				String retCustDtls = String.valueOf(((JSONObject) obj2.get("AddLimitNodeOutputVO")).get("limitPrefix"));
                                LogMessages.statusLog.info("Exception retLimitMaintainenceRes: " + retCustDtls);
                                
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
				LogMessages.statusLog.info("Exception retLimitMaintainenceRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else retLimitMaintainenceRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception retLimitMaintainenceRes: " + e);
			}

		}
		return response;
	}

}
