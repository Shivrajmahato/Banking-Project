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
public class NonLAALoanDisbursement {
    public String nonloanDisbursementReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			LogMessages.statusLog.info("Inside create Request of nonloanDisbursementReq." + param[0]);
			request = "{\"XferTrnAddRequest\":{\"XferTrnAddRq\":{\"XferTrnHdr\":{\"TrnType\":\"" + param[1] + "\",\"TrnSubType\":\"" + param[2] + "\"},\"XferTrnDetail\":{\"PartTrnRec\":[{\"AcctId\":{\"AcctId\":\"" + param[3] + "\"},\"CreditDebitFlg\":\"" + param[4] + "\",\"TrnAmt\":{\"amountValue\":\"" + param[5] + "\",\"currencyCode\":\"" + param[6] + "\"},\"TrnParticulars\":\"" + param[7] + "\",\"ValueDt\":\"" + param[8] + "\"},{\"AcctId\":{\"AcctId\":\"" + param[9] + "\"},\"CreditDebitFlg\":\"" + param[10] + "\",\"TrnAmt\":{\"amountValue\":\"" + param[11] + "\",\"currencyCode\":\"" + param[12] + "\"},\"TrnParticulars\":\"" + param[13] + "\",\"ValueDt\":\"" + param[14] + "\"}]}}}}";
			LogMessages.statusLog.info("Request nonloanDisbursementReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception nonloanDisbursementReq:  " + e);
		}
		return request;
	}

	public JSONArray nonloanDisbursementRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("nonloanDisbursementRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
				String loanDisbursementResponse = String.valueOf(((JSONObject) obj.get("Data")).get("XferTrnAddResponse"));
				 LogMessages.statusLog.info("first response NONLAALoanDisbursement : " + loanDisbursementResponse);
                                JSONObject obj2 = (JSONObject) parser.parse(loanDisbursementResponse);
                                String LoanDisbursementOutputStruct = String.valueOf(((JSONObject) obj2.get("XferTrnAddRs")).get("TrnIdentifier"));
				JSONObject obj3 = (JSONObject) parser.parse(LoanDisbursementOutputStruct);
                                
				
				String TrnDt = String.valueOf( obj3.get("TrnDt"));
                                String TrnId = String.valueOf( obj3.get("TrnId"));
                                LogMessages.statusLog.info("Exception LAALoanDisbursement: " + TrnDt);
                                LogMessages.statusLog.info("Exception LAALoanDisbursement: " + TrnId);
				res.put("TrnDt", TrnDt);
                                res.put("TrnId", TrnId);
                                res.put("status", "SUCCESS");
                                
                                //{"XferTrnAddResponse":{"XferTrnAddRs":{"TrnIdentifier":{"TrnDt":"2023-08-18T00:00:00","TrnId":"
                                
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
				LogMessages.statusLog.info("Exception nonloanDisbursementRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else nonloanDisbursementRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception nonloanDisbursementRes: " + e);
			}

		}
		return response;
	}

}
