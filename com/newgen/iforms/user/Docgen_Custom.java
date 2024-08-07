package com.newgen.iforms.user;

import com.newgen.common.LogMessages;
import com.newgen.common.Logging_LOS;
import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.integrations.GridFetch;
import com.newgen.mvcbeans.model.WorkdeskModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

public class Docgen_Custom
  implements IFormServerEventHandler
{
  Logging_LOS logObj = new Logging_LOS();

  public void beforeFormLoad(FormDef arg0, IFormReference arg1)
  {
  }

  public String executeCustomService(FormDef arg0, IFormReference arg1, String arg2, String arg3, String arg4)
  {
    return null;
  }

  public JSONArray executeEvent(FormDef arg0, IFormReference arg1, String arg2, String arg3)
  {
    return null;
  }

  public String executeServerEvent(IFormReference iFormObj, String eventType, String controlName, String stringData)
  {
    String retVal = "";
    LogMessages.statusLog.info("Inside executeServerEvent");
    LogMessages.statusLog.info("qwerty");
    try
    {
      LogMessages.statusLog.info("controlName is: " + controlName);
      LogMessages.statusLog.info("eventType is: " + eventType);
      LogMessages.statusLog.info("stringData is: " + stringData);
      LogMessages.statusLog.info("IFormReference is: " + iFormObj);

      if (controlName.equalsIgnoreCase("click"))
      {
        if (eventType.equalsIgnoreCase("gridfetch"))
        {
          LogMessages.statusLog.info("inside gridfetch");
          GridFetch grid = new GridFetch();
          String res = grid.gridtogridfetch(stringData, iFormObj);
          LogMessages.statusLog.info("inside gridfetch1");
          return res;
        }
      }
    }
    catch (Exception localException)
    {
      LogMessages.statusLog.info("Null returns");
    }return null;
  }

  public String getCustomFilterXML(FormDef arg0, IFormReference arg1, String arg2)
  {
    return null;
  }

  public String setMaskedValue(String arg0, String arg1)
  {
    return arg1;
  }

  public JSONArray validateSubmittedForm(FormDef arg0, IFormReference arg1, String arg2)
  {
    return null;
  }

  public String generateHTML(EControl ec)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1, WorkdeskModel wm)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}