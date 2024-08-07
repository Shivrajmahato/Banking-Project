package com.newgen.integrations;

/**
*
* @author bibek.shah
*
**/


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;

public class AccountEnquiry {

	public String accountEnquiryReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
                LogMessages.statusLog.info("AccountEnquiry BODY::  " + input);
		try {
			//LogMessages.statusLog.info("Inside create Request of balanceEnquiryReq."+param[0]);
			request = "{\"TransactionId\":\"" + param[0] + "\",\"AccountNumber\":\"" + param[1] + "\"}";

			LogMessages.statusLog.info("Request accountEnquiryReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception accountEnquiryReq:  " + e);
		}
		return request;
	}

	public String accountEnquiryRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("accountEnquiryRes BODY:" + param[1]);
		String response = "";

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if (code.equalsIgnoreCase("0")) {
					String AccountName = String.valueOf(obj.get("AccountName"));
					response = AccountName;
				} else {
					response = "-1";
				}
				LogMessages.statusLog.info("accountEnquiryRes Success");
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception balanceEnquiryRes:  " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				//res.put("status",error);
				//res.put("message",message);
				LogMessages.statusLog.info("else balanceEnquiryRes");
				//response.add(res);
				response = error;
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception balanceEnquiryRes:  " + e);
			}

		}
		return response;
	}

}