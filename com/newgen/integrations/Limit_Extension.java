/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.newgen.integrations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
/**
*
* @author bibek.shah
*
**/
public class Limit_Extension {
public String fetchCifDetailsReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			LogMessages.statusLog.info("Inside create Request of fetchCifDetailsReq." + param[0]);
			//request ="{\"RetCustInqRequest\":{\"RetCustInqRq\":{\"CustId\":\""+param[1]+"\"}}}";
			request = "{\"CustId\":\""+param[1]+"\"}";

			LogMessages.statusLog.info("Request fetchCifDetailsReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception fetchCifDetailsReq:  " + e);
		}
		return request;
	}

	public String fetchCifDetailsRes(String input) {
		String[] param = input.split("##",-1);
		LogMessages.statusLog.info("fetchCifDetailsRes BODY:" + param[1]);
                String res="";
		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
                                String message = String.valueOf(obj.get("Message"));
				if (code.equalsIgnoreCase("0")) {
					res = "SUCCESS"+"#"+param[1];
				} else {
					res = "FAILURE"+"#"+message;
				}
				LogMessages.statusLog.info("fetchCifDetailsRes Success" + message);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception fetchCifDetailsRes:  " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);;
				LogMessages.statusLog.info("else fetchCifDetailsRes");
				res = "FAILURE"+"#"+error;
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception fetchCifDetailsRes:  " + e);
			}

		}
		return res;
	}


        public String limitExtensionReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#",-1);
		try {
			LogMessages.statusLog.info("Inside create Request of limitExtensionReq." + param[0]);
			//request ="{\"RetCustInqRequest\":{\"RetCustInqRq\":{\"CustId\":\""+param[1]+"\"}}}";
			request = "{\"SanctionLimitModRequest\":{\"SanctionLimitModRq\":{\"AcctId\":\""+param[1]+"\",\"SanctionLimitRec\":{\"IsDetailsEntered\":\""+param[2]+"\",\"LimitLevelIntFlg\":\""+param[3]+"\",\"RecordFlg\":\""+param[5]+"\",\"SerialNum\":\""+param[6]+"\",\"ApplicableDt\":\""+param[7]+"\",\"SanctionLimit\":{\"amountValue\":\""+param[8]+"\",\"currencyCode\":\""+param[9]+"\"},\"SupersedeFlg\":\""+param[10]+"\",\"SanctionDt\":\""+param[11]+"\",\"ExpiryDt\":\""+param[12]+"\",\"DocumentDt\":\""+param[13]+"\",\"SanctionLevelCode\":\""+param[14]+"\",\"SanctionAuthCode\":\""+param[15]+"\",\"MarginIntRate\":{\"value\":\""+param[16]+"\"},\"PenalIntRate\":{\"value\":\""+param[17]+"\"},\"NormalIntRate\":{\"value\":\""+param[18]+"\"},\"remarks\":\""+param[4]+"\",\"SanctionRefNum\":\""+param[19]+"\"}}}}";

			LogMessages.statusLog.info("Request limitExtensionReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception limitExtensionReq:  " + e);
		}
		return request;
	}

	public String limitExtensionRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("limitExtensionRes BODY:" + param[1]);
                String res="";
		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
                                String message = String.valueOf(obj.get("Message"));
				if (code.equalsIgnoreCase("0")) {
					res = "SUCCESS"+"#"+param[1];
				} else {
					res = "FAILURE"+"#"+message;
				}
				LogMessages.statusLog.info("limitExtensionRes Success" + message);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception limitExtensionRes:  " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);;
				LogMessages.statusLog.info("else limitExtensionRes");
				res = "FAILURE"+"#"+error;
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception limitExtensionRes:  " + e);
			}

		}
		return res;
	}
}
