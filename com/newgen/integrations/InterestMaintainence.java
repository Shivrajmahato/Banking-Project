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
public class InterestMaintainence {
    public String interestMaintainenceReq(String input, IFormReference iFormObj) {
		String request = "";                
                String[] param = input.split("#");
                String repmtrecData="";
		try {
                    LogMessages.statusLog.info("Inside create Request of interestMaintainenceReq."+param[0]);
                    request = "{\"IntPrefModRequest\":{\"IntPrefModRq\":{\"EntityType\":\""+ param[1] +"\",\"EntityDispId\":\""+ param[2] +"\",\"IntTblDetailRec\":{\"IntTblCode\":\""+ param[3] +"\",\"startDate\":\""+ param[4] + "\",\"endDate\":\""+ "2099-12-31T00:00:00.001" + "\",\"acctPrefIntDr\":{\"value\":\""+ param[5] +"\"}}}}}";
                    LogMessages.statusLog.info("Request interestMaintainenceReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception interestMaintainenceReq:  " + e);
		}
		return request;
	}

	public JSONArray interestMaintainenceRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("interestMaintainenceRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
                                    LogMessages.statusLog.info("Exception interestMaintainenceRes: " + code);
				String IntPrefModResponse = String.valueOf(((JSONObject) obj.get("Data")).get("IntPrefModResponse"));
                                LogMessages.statusLog.info("Exception interestMaintainenceRes: " + IntPrefModResponse);
				JSONObject obj2 = (JSONObject) parser.parse(IntPrefModResponse);
                                String IntPrefModRs = String.valueOf((obj2.get("IntPrefModRs")));
                                String IntPrefMod_CustomData = String.valueOf((obj2.get("IntPrefMod_CustomData")));
                                LogMessages.statusLog.info("Exception interestMaintainenceRes: " + IntPrefModRs);
				res.put("IntPrefMod_CustomData", IntPrefMod_CustomData);
				res.put("IntPrefModRs", IntPrefModRs);
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
				LogMessages.statusLog.info("Exception interestMaintainenceRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else interestMaintainenceRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception interestMaintainenceRes: " + e);
			}

		}
		return response;
	}

    
}
