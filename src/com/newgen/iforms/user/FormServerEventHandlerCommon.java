package com.newgen.iforms.user;

import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormCustomHooks;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.mvcbeans.model.WorkdeskModel;
import org.json.simple.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.newgen.iforms.util.NewGenUtilConstants.NOT_SUPPORTED_YET;

public abstract class FormServerEventHandlerCommon extends IFormCustomHooks implements IFormServerEventHandler {

    public void beforeFormLoad(FormDef arg0, IFormReference arg1) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public String executeCustomService(FormDef arg0, IFormReference arg1, String arg2, String arg3, String arg4) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public JSONArray executeEvent(FormDef arg0, IFormReference arg1, String arg2, String arg3) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public String getCustomFilterXML(FormDef arg0, IFormReference arg1, String arg2) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public String setMaskedValue(String arg0, String arg1) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public JSONArray validateSubmittedForm(FormDef arg0, IFormReference arg1, String arg2) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public String generateHTML(EControl ec) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1, WorkdeskModel wm) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
