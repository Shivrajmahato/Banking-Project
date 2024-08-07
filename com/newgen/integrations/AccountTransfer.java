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

public class AccountTransfer {

	public String accountTransferReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			LogMessages.statusLog.info("Inside create Request of accountTransferReq." + param[0]);
			//request ="{\"RetCustInqRequest\":{\"RetCustInqRq\":{\"CustId\":\""+param[1]+"\"}}}";
			request = "{\"TransactionId\":\"" + param[1] + "\",\"Amount\":\"" + param[2] + "\",\"DrAccount\":\"" + param[3] + "\",\"CrAccount\":\"" + param[4] + "\",\"Description1\":\"" + param[5] + "\"}";

			LogMessages.statusLog.info("Request accountTransferReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception accountTransferReq:  " + e);
		}
		return request;
	}

	public JSONArray accountTransferRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("accountTransferRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String transactionId = String.valueOf(obj.get("TransactionId"));
				String merchantTransactionId = String.valueOf(obj.get("MerchantTransactionId"));
				String availableBalance = String.valueOf(obj.get("AvailableBalance"));
				String code = String.valueOf(obj.get("Code"));
				String message = String.valueOf(obj.get("Message"));
				if (code.equalsIgnoreCase("0")) {
					res.put("transactionId", transactionId);
					res.put("merchantTransactionId", merchantTransactionId);
					res.put("availableBalance", availableBalance);
					res.put("code", code);
					res.put("message", message);
					res.put("status", "SUCCESS");
				} else {
					res.put("status", "FAILURE");
					res.put("message", message);
				}
				LogMessages.statusLog.info("accountTransferRes Success" + message);
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception accountTransferRes:  " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else accountTransferRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception accountTransferRes:  " + e);
			}

		}
		return response;
	}

}