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
public class SanctionLimitMod {
     public String sanctionLimitModReq(String input, IFormReference iFormObj) {
		String request = "";                
                String[] param = input.split("#");
                String repmtrecData="";
		try {
                    LogMessages.statusLog.info("Inside create Request of sanctionLimitModReq."+param[0]);                   
                    request = "{\"SanctionLimitModRequest\":{\"SanctionLimitModRq\":{\"AcctId\":\""+param[1]+"\",\"SanctionLimitRec\":{\"IsDetailsEntered\":\""+param[2]+"\",\"LimitLevelIntFlg\":[\""+param[3]+"\"],\"RecordFlg\":\""+param[4]+"\",\"SerialNum\":\""+param[5]+"\",\"ApplicableDt\":\""+param[6]+"\",\"SanctionLimit\":{\"amountValue\":\""+param[7]+"\",\"currencyCode\":\""+param[8]+"\"},\"SupersedeFlg\":\""+param[9]+"\",\"SanctionDt\":\""+param[10]+"\",\"ExpiryDt\":\""+param[11]+"\",\"DocumentDt\":\""+param[12]+"\",\"SanctionLevelCode\":\""+param[13]+"\",\"SanctionAuthCode\":\""+param[14]+"\",\"MarginIntRate\":{\"value\":\""+param[15]+"\"},\"PenalIntRate\":{\"value\":\""+param[16]+"\"},\"NormalIntRate\":{\"value\":\""+param[17]+"\"},\"SanctionRefNum\":\""+param[18]+"\"}}}}";
                    LogMessages.statusLog.info("Request sanctionLimitModReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception sanctionLimitModReq:  " + e);
		}
		return request;
	}

	public JSONArray sanctionLimitModRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("sanctionLimitModRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
                                    LogMessages.statusLog.info("Exception sanctionLimitModRes: " + code);
				String SanctionLimitModResponse = String.valueOf(((JSONObject) obj.get("Data")).get("SanctionLimitModResponse"));
                                LogMessages.statusLog.info("Exception sanctionLimitModRes: " + SanctionLimitModResponse);
				JSONObject obj2 = (JSONObject) parser.parse(SanctionLimitModResponse);
                                String foracid = String.valueOf(((JSONObject) obj2.get("SanctionLimitModRs")).get("AcctId"));
                                
				
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
				LogMessages.statusLog.info("Exception sanctionLimitModRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else sanctionLimitModRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception sanctionLimitModRes: " + e);
			}

		}
		return response;
	}

       
}
