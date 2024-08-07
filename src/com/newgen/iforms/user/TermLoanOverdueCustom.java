/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.user;

import com.newgen.common.APIConsumption;
import com.newgen.common.BranchDetails;
import com.newgen.common.LogMessages;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.integrations.LoanOverdue;
import com.newgen.integrations.SchedulePayment;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
import org.json.simple.JSONArray;

import static com.newgen.iforms.util.NewGenUtil.*;
import static com.newgen.iforms.util.NewGenUtilConstants.ERROR;

/**
 * @author 97798
 */
public class TermLoanOverdueCustom extends FormServerEventHandlerCommon {

    WDGeneralData wdgeneralObj = null;
    String processInstanceId = null;
    String activityName = null;
    String userName = null;

    APIConsumption api = new APIConsumption();

    public void beforeFormLoad(FormDef arg0, IFormReference arg1) {
        this.wdgeneralObj = arg1.getObjGeneralData();
        this.processInstanceId = this.wdgeneralObj.getM_strProcessInstanceId();
        this.activityName = this.wdgeneralObj.getM_strActivityName();
        this.userName = this.wdgeneralObj.getM_strUserName();
    }

    public String executeServerEvent(IFormReference iFormObj, String eventType, String controlName, String stringData) {
        LogMessages.statusLog.info("Inside executeServerEvent");
        try {
            LogMessages.statusLog.info("controlName is: " + controlName + " eventType is: " + eventType + " IFormReference is: " + iFormObj);
            switch (controlName) {
                case "click":
                    if (eventType.equalsIgnoreCase("getBranchDetails")) {
                        LogMessages.statusLog.info("getBranchDetails");
                        BranchDetails bd = new BranchDetails();
                        String res = bd.getBranchDetails(stringData, iFormObj);
                        LogMessages.statusLog.info("res::" + res);
                        return res;
                    } else if (eventType.equalsIgnoreCase("schedulePayment")) {
                        return schedulePayment(stringData);
                    } else if (eventType.equalsIgnoreCase("loanOverdueAccountInquiry")) {
                        return getLoanOverdueAccountInquiry(stringData);
                    }
                    break;
                case "FormLoad":
                case "MOUSE_CLICKED":
                case "VALUE_CHANGED":
                case "submit":
                default:
                    break;
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception--" + e);
        }
        return "";
    }

    private String getLoanOverdueAccountInquiry(String stringData) {
        try {
            LogMessages.statusLog.info("inside getLoanOverdueAccountInquiry");
            LoanOverdue lo = new LoanOverdue();
            String reqData = lo.loanOverdueAccountInquiryReq(stringData);
            String response = api.consumeAPI(reqData, "PrincipalInterestDemandDetails");
            return lo.loanOverdueAccountInquiryRes(getSplitRespData(response));
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception getLoanOverdueAccountInquiry:" + e);
            return ERROR;
        }
    }

    private String schedulePayment(String stringData) {
        try {
            LogMessages.statusLog.info("inside schedulePayment");
            SchedulePayment schPay = new SchedulePayment();
            String reqData = schPay.schedulePaymentReq(stringData);
            String response = api.consumeAPI(reqData, "HLASPAY");
            JSONArray res = schPay.schedulePaymentRes(getSplitRespData(response));
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception schedulePayment:" + e);
            return ERROR;
        }
    }

    @Override
    public boolean picklistPreHook(String controlId, IFormReference iFormObj) {
        return pickListPreHookExtracted(controlId, iFormObj);
    }

    @Override
    public void picklistSearchHook(String controlId, IFormReference iFormObj, String searchString, int colIndex) {
        pickListSearchHookExtracted(controlId, iFormObj, searchString, colIndex);
    }

}
