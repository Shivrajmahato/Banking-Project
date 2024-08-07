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

public class BalanceEnquiry {

	public String balanceEnquiryReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			//LogMessages.statusLog.info("Inside create Request of balanceEnquiryReq."+param[0]);
			//request = "{\"TransactionId\":\"" + param[0] + "\",\"AccountNumber\":\"" + param[1] + "\"}";
			request = "{\"AcctInqRequest\":{\"AcctInqRq\":{\"AcctId\":{\"AcctId\":\""+param[1]+"\"}}}}";

			LogMessages.statusLog.info("Request balanceEnquiryReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception balanceEnquiryReq:  " + e);
		}
		return request;
	}

	public String balanceEnquiryRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("balanceEnquiryRes BODY:" + param[1]);
		String response = "";

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();
                        		LogMessages.statusLog.info("balanceEnquiryRes BODY: SUCCESS");
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
                               	LogMessages.statusLog.info("balanceEnquiryRes BODY: SUCCESS"+ code);
				if (code.equalsIgnoreCase("0")) {                                                                                            		LogMessages.statusLog.info("balanceEnquiryRes BODY: SUCCESS ##"+ code);
					String AcctInqResponse = String.valueOf(((JSONObject) obj.get("Data")).get("AcctInqResponse"));
				JSONObject obj2 = (JSONObject) parser.parse(AcctInqResponse);
                                String AcctBal = String.valueOf(((JSONObject) obj2.get("AcctInqRs")).get("AcctBal"));
				JSONArray arr = (JSONArray) parser.parse(AcctBal);
                                String BalType="",avlAmt="";
				for (int i = 0; i < arr.size(); i++) {
                                                                   	LogMessages.statusLog.info("balanceEnquiryRes BODY: SUCCESS"+ i);
					String s = String.valueOf(arr.get(i));
					JSONObject obj1 = (JSONObject) parser.parse(s);
                                        BalType = String.valueOf(obj1.get("BalType"));
                                        if(BalType.equalsIgnoreCase("EFFAVL")){
                                                                           	LogMessages.statusLog.info("balanceEnquiryRes BODY: SUCCESS"+ String.valueOf(((JSONObject) obj1.get("BalAmt")).get("amountValue")));
                                            avlAmt = String.valueOf(((JSONObject) obj1.get("BalAmt")).get("amountValue"));     
                                            response = avlAmt;
                                        }
                                        LogMessages.statusLog.info("balanceEnquiryRes BODY: response"+ response);
                                }
				} else {
					response = "0";
				}
				LogMessages.statusLog.info("balanceEnquiryRes Success");
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception balanceEnquiryRes:  " + e);
			}
                        return response;
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
                        return response;
		}
	}

}