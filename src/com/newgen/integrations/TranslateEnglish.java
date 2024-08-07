package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.newgen.iforms.util.NewGenUtilConstants.CODE;
import static com.newgen.iforms.util.NewGenUtilConstants.ZERO;

/**
 * @author bibek.shah
 **/

public class TranslateEnglish {

    public String translateEnglishReq(String[] param) {
        String request = "";
        try {
            String type = "ENG2NEP";
            request = "{\"dateType\":\"" + type + "\",\"year\":\"" + param[2] + "\",\"month\":\"" + param[3] + "\",\"day\":\"" + param[4] + "\"}";
            LogMessages.statusLog.info("Request translateEnglishReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception translateEnglishReq:  " + e);
        }
        return request;
    }

    public String translateNepaliReq(String[] param) {
        String request = "";
        try {
            String type = "NEP2ENG";
            request = "{\"dateType\":\"" + type + "\",\"year\":\"" + param[2] + "\",\"month\":\"" + param[3] + "\",\"day\":\"" + param[4] + "\"}";
            LogMessages.statusLog.info("Request translateNepaliReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception translateNepaliReq:  " + e);
        }
        return request;
    }

    public String translateEnglishRes(String[] param) {
        String response = "";
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String result = String.valueOf(obj.get("Data"));
                    JSONObject fobj = (JSONObject) parser.parse(result);
                    String year = String.valueOf(fobj.get("Year"));
                    String month = String.valueOf(fobj.get("Month"));
                    String day = String.valueOf(fobj.get("Day"));
                    response = day + "/" + month + "/" + year;
                } else {
                    response = String.valueOf(obj.get("Message"));
                }

            } else {
                response = String.valueOf(obj);
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception translateEnglishRes:  " + e);
        }
        LogMessages.statusLog.info("translateEnglishRes response");
        return response;
    }

}