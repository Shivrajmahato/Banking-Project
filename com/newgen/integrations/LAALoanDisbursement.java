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
public class LAALoanDisbursement {
    public String loanDisbursementReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			LogMessages.statusLog.info("Inside create Request of LAALoanDisbursement." + param[0]);
			request = "{\"loanDisbursementRequest\":{\"LoanDisbursementStruct\":{\"acctDisburseTranLA\":{\"amtAlreadyDisbursed\":{\"amountValue\":\"" + param[1] + "\",\"currencyCode\":\"" + param[2] + "\"},\"amtAvailForDisbursement\":{\"amountValue\":\"" + param[3] + "\",\"currencyCode\":\"" + param[4] + "\"},\"deductOvduDmds\":\"" + param[5] + "\",\"disburseAmt\":{\"amountValue\":\"" + param[6] + "\",\"currencyCode\":\"" + param[7] + "\"},\"finalDisbFlg\":\"" + param[8] + "\",\"firstDisbFlg\":\"" + param[9] + "\",\"glDate\":\"" + param[10] + "\",\"grossNetDisbt\":\"" + param[11] + "\",\"isDetailsEntered\":\"" + param[12] + "\",\"laAcct\":{\"foracid\":\"" + param[13] + "\"},\"oPartTranLL\":{\"crAcctForAcid\":\"" + param[14] + "\",\"crValueDate\":\"" + param[15] + "\",\"laAmtCrncy\":{\"amountValue\":\"" + param[16] + "\",\"currencyCode\":\"" + param[17] + "\"},\"modeOfDisb\":\"" + param[18] + "\"},\"solId\":\"" + param[19] + "\",\"tranDate\":\"" + param[20] + "\",\"tranMode\":\"" + param[21] + "\",\"tranType\":\"" + param[22] + "\",\"valueDate\":\"" + param[23] + "\"}}}}";
                        APIConsumption apic = new APIConsumption();
			LogMessages.statusLog.info("Request LAALoanDisbursement:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception LAALoanDisbursement:  " + e);
		}
		return request;
	}

	public JSONArray loanDisbursementRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("LAALoanDisbursement BODY:::" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
				String loanDisbursementResponse = String.valueOf(((JSONObject) obj.get("Data")).get("loanDisbursementResponse"));
				JSONObject obj2 = (JSONObject) parser.parse(loanDisbursementResponse);
                                String LoanDisbursementOutputStruct = String.valueOf(((JSONObject) obj2.get("loanDisbursementResponse")).get("LoanDisbursementOutputStruct"));
				JSONObject obj3 = (JSONObject) parser.parse(LoanDisbursementOutputStruct);
                                String tranMesgOutput = String.valueOf(((JSONObject) obj3.get("LoanDisbursementOutputStruct")).get("tranMesgOutput"));
				JSONObject obj4 = (JSONObject) parser.parse(tranMesgOutput);
				String TrnDt = String.valueOf(((JSONObject) obj4.get("tranMesgOutput")).get("TrnDt"));
                                String TrnId = String.valueOf(((JSONObject) obj4.get("tranMesgOutput")).get("TrnId"));
                                LogMessages.statusLog.info("Exception LAALoanDisbursement: " + TrnDt);
                                LogMessages.statusLog.info("Exception LAALoanDisbursement: " + TrnId);
				res.put("TrnDt", TrnDt);
                                res.put("TrnId", TrnId);
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
				LogMessages.statusLog.info("Exception LAALoanDisbursement: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else LAALoanDisbursement");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception LAALoanDisbursement: " + e);
			}

		}
		return response;
	}

}
