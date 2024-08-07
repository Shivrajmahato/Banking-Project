/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.newgen.iforms.user;

import com.newgen.common.APIConsumption;
import com.newgen.common.BranchDetails;
import com.newgen.common.LogMessages;
import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormCustomHooks;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.iforms.util.NewGenUtilConstants;
import com.newgen.integrations.LimitExtension;
import com.newgen.mvcbeans.model.WorkdeskModel;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
import org.json.simple.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.newgen.iforms.util.NewGenUtil.*;
import static com.newgen.iforms.util.NewGenUtilConstants.ERROR;

/**
 * @author shivaraj
 */
public class LimitExtensionCustom extends IFormCustomHooks implements IFormServerEventHandler {

    WDGeneralData wdGeneralData = null;
    String processInstanceId = null;
    String activityName = null;
    String userName = null;
    BranchDetails bd = new BranchDetails();
    APIConsumption apic = new APIConsumption();
    LimitExtension limExt = new LimitExtension();

    public void beforeFormLoad(FormDef arg0, IFormReference arg1) {
        LogMessages.statusLog.info("Inside beforeFormLoad()*****");
        this.wdGeneralData = arg1.getObjGeneralData();
        this.processInstanceId = this.wdGeneralData.getM_strProcessInstanceId();
        this.activityName = this.wdGeneralData.getM_strActivityName();
        this.userName = this.wdGeneralData.getM_strUserName();
    }

    public String executeServerEvent(IFormReference iFormObj, String eventType, String controlName, String stringData) {
        LogMessages.statusLog.info("Inside executeServerEvent");
        try {
            LogMessages.statusLog.info("controlName is: " + controlName + " eventType is: " + eventType + " IFormReference is: " + iFormObj);
            switch (controlName) {
                case "click":
                    if (eventType.equalsIgnoreCase("getBranchDetails")) {
                        LogMessages.statusLog.info("getBranchDetails");
                        String res = bd.getBranchDetails(stringData, iFormObj);
                        LogMessages.statusLog.info("res::" + res);
                        return res;
                    } else if (eventType.equalsIgnoreCase("LimitSancFetchCifDetails")) {
                        return getLimitSancFetchCifDetails(stringData);
                    } else if (eventType.equalsIgnoreCase("limitSanctionLimitExtend")) {
                        return getLimitSanctionLimitExtend(stringData);
                    }
                    break;
                case "FormLoad":
                    break;
                case "MOUSE_CLICKED":
                    break;
                case "VALUE_CHANGED":
                    break;
                case "submit":
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception--" + e);
        }
        return "";
    }

    private String getLimitSancFetchCifDetails(String stringData) {
        try {
            LogMessages.statusLog.info("inside LimitSancFetchCifDetails");
            String reqData = limExt.fetchCifDetailsReq(stringData);
            String response = apic.consumeAPI(reqData, "SanctionLimitDetailsFromCustId");
            return limExt.fetchCifDetailsRes(getSplitRespData(response));
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LimitSancFetchCifDetails:" + e);
            return ERROR;
        }
    }

    private String getLimitSanctionLimitExtend(String stringData) {
        try {
            LogMessages.statusLog.info("inside limitSanctionLimitExtend");
            String reqData = limExt.limitExtensionReq(stringData);
            String response = apic.consumeAPI(reqData, "SanctionLimitExtend");
            return limExt.limitExtensionRes(getSplitRespData(response));
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitSanctionLimitExtend:" + e);
            return ERROR;
        }
    }

    @Override
    public boolean picklistPreHook(String controlId, IFormReference iformObj) {
        return pickListPreHookExtracted(controlId, iformObj);
    }

    @Override
    public void picklistSearchHook(String controlId, IFormReference iFormObj, String searchString, int colIndex) {
        pickListSearchHookExtracted(controlId, iFormObj, searchString, colIndex);
    }

    public String generateHTML(EControl ec) {
        throw new UnsupportedOperationException(NewGenUtilConstants.NOT_SUPPORTED_YET);
    }

    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1) {
        throw new UnsupportedOperationException(NewGenUtilConstants.NOT_SUPPORTED_YET);
    }

    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1, WorkdeskModel wm) {
        throw new UnsupportedOperationException(NewGenUtilConstants.NOT_SUPPORTED_YET);
    }

    public String getCustomFilterXML(FormDef arg0, IFormReference arg1, String arg2) {
        throw new UnsupportedOperationException(NewGenUtilConstants.NOT_SUPPORTED_YET);
    }

    public String setMaskedValue(String arg0, String arg1) {
        throw new UnsupportedOperationException(NewGenUtilConstants.NOT_SUPPORTED_YET);
    }

    public JSONArray validateSubmittedForm(FormDef arg0, IFormReference arg1, String arg2) {
        throw new UnsupportedOperationException(NewGenUtilConstants.NOT_SUPPORTED_YET);
    }

    public String executeCustomService(FormDef arg0, IFormReference arg1, String arg2, String arg3, String arg4) {
        throw new UnsupportedOperationException(NewGenUtilConstants.NOT_SUPPORTED_YET);
    }

    public JSONArray executeEvent(FormDef arg0, IFormReference arg1, String arg2, String arg3) {
        throw new UnsupportedOperationException(NewGenUtilConstants.NOT_SUPPORTED_YET);
    }
}
