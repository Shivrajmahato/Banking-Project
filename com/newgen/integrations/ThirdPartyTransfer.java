package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author bibek.shah
 */
public class ThirdPartyTransfer {
    public String thirdpartytransferReq(String input, IFormReference iFormObj) {
		String request = "";                
                String[] param = input.split("#");
                String repmtrecData="";
		try {
                    LogMessages.statusLog.info("Inside create Request of thirdpartytransferReq."+param[0]);
                    request = "{\"XferTrnAddRequest\":{\"XferTrnAddRq\":{\"XferTrnHdr\":{\"TrnType\":\""+ param[1] +"\",\"TrnSubType\":\""+ param[2] +"\"},\"XferTrnDetail\":{\"PartTrnRec\":[{\"AcctId\":{\"AcctId\":\""+ param[3] +"\"},\"CreditDebitFlg\":\""+ param[4] +"\",\"TrnAmt\":{\"amountValue\":\""+ param[5] +"\",\"currencyCode\":\""+ param[6] +"\"},\"TrnParticulars\":\""+ param[7] +"\",\"ValueDt\":\""+ param[8] +"\"},{\"AcctId\":{\"AcctId\":\""+ param[9] +"\"},\"CreditDebitFlg\":\""+ param[10] +"\",\"TrnAmt\":{\"amountValue\":\""+ param[11] +"\",\"currencyCode\":\""+ param[12] +"\"},\"TrnParticulars\":\""+ param[13] +"\",\"ValueDt\":\""+ param[14] +"\"}]}}}}";
                    LogMessages.statusLog.info("Request thirdpartytransferReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception thirdpartytransferReq:  " + e);
		}
		return request;
	}

	public JSONArray thirdpartytransferRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("thirdpartytransferRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
                                   
				String LoanAcctAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("XferTrnAddResponse"));
                               // LogMessages.statusLog.info("Success XferTrnAddResponse: " + LoanAcctAddResponse);
				JSONObject obj2 = (JSONObject) parser.parse(LoanAcctAddResponse);
                                String foracid = String.valueOf(((JSONObject) obj2.get("XferTrnAddRs")).get("TrnIdentifier"));
                                JSONObject obj3 = (JSONObject) parser.parse(foracid);
                                String trnID = String.valueOf(obj3.get("TrnId"));
                                
                         
                               
                                LogMessages.statusLog.info("TRN ID : " + trnID);
				
				res.put("AcctId", trnID);
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
				LogMessages.statusLog.info("Exception Catch thirdpartytransferRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else thirdpartytransferRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception Else thirdpartytransferRes: " + e);
			}

		}
		return response;
	}

}
