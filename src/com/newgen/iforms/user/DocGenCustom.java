package com.newgen.iforms.user;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.integrations.GridFetch;

public class DocGenCustom extends FormServerEventHandlerCommon {

    public String executeServerEvent(IFormReference iFormObj, String eventType, String controlName, String stringData) {
        LogMessages.statusLog.info("Inside executeServerEvent");
        try {
            LogMessages.statusLog.info("controlName is: " + controlName + " eventType is: " + eventType + " IFormReference is: " + iFormObj + "stringData is: " + stringData);
            if (controlName.equalsIgnoreCase("click") && eventType.equalsIgnoreCase("gridfetch")) {
                GridFetch grid = new GridFetch();
                return grid.gridToGridFetch(iFormObj);
            }
        } catch (Exception localException) {
            LogMessages.statusLog.info("Null returns");
        }
        return null;
    }

}