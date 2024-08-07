/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtilConstants.*;

/**
 * @author bibek.shah
 **/
public class LimitExtension {

    public String fetchCifDetailsReq(String input) {
        String request = "";
        String[] param = input.split("#", -1);
        try {
            request = "{\"CustId\":\"" + param[1] + "\"}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception fetchCifDetailsReq:  " + e);
        }
        LogMessages.statusLog.info("Request fetchCifDetailsReq:  " + request);
        return request;
    }

    public String limitExtensionReq(String input) {
        String request = "";
        String[] param = input.split("#", -1);
        try {
            LogMessages.statusLog.info("Inside create Request of limitExtensionReq." + param[0]);
            request = "{\"SanctionLimitModRequest\":{\"SanctionLimitModRq\":{\"AcctId\":\"" + param[1] + "\",\"SanctionLimitRec\":{\"IsDetailsEntered\":\"" + param[2] + "\",\"LimitLevelIntFlg\":\"" + param[3] + "\",\"RecordFlg\":\"" + param[5] + "\",\"SerialNum\":\"" + param[6] + "\",\"ApplicableDt\":\"" + param[7] + "\",\"SanctionLimit\":{\"amountValue\":\"" + param[8] + "\",\"currencyCode\":\"" + param[9] + "\"},\"SupersedeFlg\":\"" + param[10] + "\",\"SanctionDt\":\"" + param[11] + "\",\"ExpiryDt\":\"" + param[12] + "\",\"DocumentDt\":\"" + param[13] + "\",\"SanctionLevelCode\":\"" + param[14] + "\",\"SanctionAuthCode\":\"" + param[15] + "\",\"MarginIntRate\":{\"value\":\"" + param[16] + "\"},\"PenalIntRate\":{\"value\":\"" + param[17] + "\"},\"NormalIntRate\":{\"value\":\"" + param[18] + "\"},\"remarks\":\"" + param[4] + "\",\"SanctionRefNum\":\"" + param[19] + "\"}}}}";
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitExtensionReq:  " + e);
        }
        LogMessages.statusLog.info("Request limitExtensionReq:  " + request);
        return request;
    }

    public String fetchCifDetailsRes(String[] param) {
        String res = "";
        try {
            res = getLimitExtensionResp(param);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception fetchCifDetailsRes:  " + e);
        }
        LogMessages.statusLog.info("Response fetchCifDetailsRes: " + res);
        return res;
    }

    public String limitExtensionRes(String[] param) {
        String res = "";
        try {
            res = getLimitExtensionResp(param);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitExtensionRes:  " + e);
        }
        LogMessages.statusLog.info("Response limitExtensionRes: " + res);
        return res;
    }

    private String getLimitExtensionResp(String[] param) {
        JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
        String res;
        if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
            if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                res = NewGenUtilConstants.SUCCESS + "#" + param[1];
            } else {
                res = NewGenUtilConstants.FAILURE + "#" + obj.get(MESSAGE);
            }
        } else {
            res = NewGenUtilConstants.FAILURE + "#" + obj;
        }
        return res;
    }
}
