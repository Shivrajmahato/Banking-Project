package com.newgen.integrations;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;

/**
*
* @author bibek.shah
*
**/

public class TranslateEnglish {

	public String translateEnglishReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			//LogMessages.statusLog.info("Inside create Request of balanceEnquiryReq."+param[0]);
			String type = "ENG2NEP";
			request = "{\"dateType\":\""+type+"\",\"year\":\""+param[2]+"\",\"month\":\""+param[3]+"\",\"day\":\""+param[4]+"\"}";
			LogMessages.statusLog.info("Request translateEnglishReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception translateEnglishReq:  " + e);
		}
		return request;
	}
        
        public String translateNepaliReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			//LogMessages.statusLog.info("Inside create Request of balanceEnquiryReq."+param[0]);
			String type = "NEP2ENG";
			request = "{\"dateType\":\""+type+"\",\"year\":\""+param[2]+"\",\"month\":\""+param[3]+"\",\"day\":\""+param[4]+"\"}";
			LogMessages.statusLog.info("Request translateNepaliReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception translateNepaliReq:  " + e);
		}
		return request;
	}

	public String translateEnglishRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("translateEnglishRes BODY:" + param[1]);
		String response = "";

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				
				if (code.equalsIgnoreCase("0")) {
					String result = String.valueOf(obj.get("Data"));
					JSONObject fobj = (JSONObject) parser.parse(result);
					String Year = String.valueOf(fobj.get("Year"));
					String Month = String.valueOf(fobj.get("Month"));
					String Day = String.valueOf(fobj.get("Day"));
					String finalres = Day+"/"+Month+"/"+Year;
					response = finalres;
				} else {
					String message = String.valueOf(obj.get("Message"));
					response = message;
				}
				LogMessages.statusLog.info("translateEnglishRes Success");
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception translateEnglishRes:  " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				//res.put("status",error);
				//res.put("message",message);
				LogMessages.statusLog.info("else translateEnglishRes");
				//response.add(res);
				response = error;
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception translateEnglishRes:  " + e);
			}

		}
		return response;
	}

}