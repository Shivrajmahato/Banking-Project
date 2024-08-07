/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.newgen.iforms.user;

import com.newgen.common.LogMessages;
import com.newgen.common.Logging_LOS;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.controls.Picklist;
import com.newgen.iforms.custom.IFormCustomHooks;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.iforms.util.CommonMethods;
import com.newgen.iforms.util.XMLGeneration;
import com.newgen.iforms.util.XMLParser;
import com.newgen.integrations.GridFetch;
import com.newgen.integrations.RetKYCDetails;
import com.newgen.niplj.generic.NGIMException;
import com.newgen.integrations.AccountTransfer;
import com.newgen.integrations.BalanceEnquiry;
import com.newgen.integrations.EmailValuator;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;			  
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ListIterator;


import com.newgen.common.APIConsumption;
import com.newgen.common.BranchDetails;
import com.newgen.common.LogMessages;
import com.newgen.iforms.EControl;
import com.newgen.integrations.Limit_Extension;
import com.newgen.integrations.TranslateEnglish;
import com.newgen.mvcbeans.model.WorkdeskModel;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
/**
 *
 * @author Administrator
 */
public class Limit_Extension_Custom extends IFormCustomHooks
  implements IFormServerEventHandler
{
  Logging_LOS logObj = new Logging_LOS();
  WDGeneralData wdgeneralObj = null;
  String processInstanceId = null;
  String activityName = null;
  String UserName = null;
  BranchDetails bd = new BranchDetails();							   

  public void beforeFormLoad(FormDef arg0, IFormReference arg1) {
    System.out.println("Inside beforeFormLoad()*****");
    this.wdgeneralObj = arg1.getObjGeneralData();
    this.processInstanceId = this.wdgeneralObj.getM_strProcessInstanceId();
    this.activityName = this.wdgeneralObj.getM_strActivityName();
    this.UserName = this.wdgeneralObj.getM_strUserName();
  }

  public String executeCustomService(FormDef arg0, IFormReference arg1, String arg2, String arg3, String arg4) {
    return null;
  }

  public JSONArray executeEvent(FormDef arg0, IFormReference arg1, String arg2, String arg3) {
    return null;
  }

  public String executeServerEvent(IFormReference iFormObj, String eventType, String controlName, String stringData) {
																			  
																		
    String retVal = "";
    LogMessages.statusLog.info("Inside executeServerEvent");
    LogMessages.statusLog.info("qwerty");
												  
    try {
		LogMessages.statusLog.info("eventType is: " + eventType);
		LogMessages.statusLog.info("stringData is: " + stringData);
		LogMessages.statusLog.info("IFormReference is: " + iFormObj);
                switch (controlName) {
                    case "click":
                            if (eventType.equalsIgnoreCase("getBranchDetails")) {
					LogMessages.statusLog.info("getBranchDetails");
                                        String res = bd.getBranchDetails(stringData, iFormObj);	 
					LogMessages.statusLog.info("res::"+res);
					return res;
				}else if(eventType.equalsIgnoreCase("LimitSancFetchCifDetails")){
                                     try {
						LogMessages.statusLog.info("inside LimitSancFetchCifDetails");
						Limit_Extension limExt = new Limit_Extension();
						String reqdata = limExt.fetchCifDetailsReq(stringData, iFormObj);
						APIConsumption apic = new APIConsumption();
						String response = apic.consumeAPI(reqdata, "SanctionLimitDetailsFromCustId");
						String res = limExt.fetchCifDetailsRes(response);
						LogMessages.statusLog.info("After LimitSancFetchCifDetails"+res);
						return res;
					} catch(Exception e) {
						LogMessages.statusLog.info("Exception LimitSancFetchCifDetails:" + e);
						return "ERROR";
					}

                                    }else if(eventType.equalsIgnoreCase("limitSanctionLimitExtend")){
                                     try {
						LogMessages.statusLog.info("inside limitSanctionLimitExtend");
						Limit_Extension limExt = new Limit_Extension();
						String reqdata = limExt.limitExtensionReq(stringData, iFormObj);
						APIConsumption apic = new APIConsumption();
						String response = apic.consumeAPI(reqdata, "SanctionLimitExtend");
						String res = limExt.limitExtensionRes(response);
						LogMessages.statusLog.info("After limitSanctionLimitExtend"+res);
						return res;
					} catch(Exception e) {
						LogMessages.statusLog.info("Exception limitSanctionLimitExtend:" + e);
						return "ERROR";
					}

                                    }
                               
                                break;
      case "FormLoad":
        break;
      case "MOUSE_CLICKED":
        break;
      case "VALUE_CHANGED":
        //String tmp384_383 = eventType; tmp384_383.getClass(); tmp384_383;

        break;
      case "submit":								  
        break;
      }
    }
    catch (Exception e) {
      LogMessages.statusLog.info("Exception--" + e);
    }
    return "";
  }

  public String getCustomFilterXML(FormDef arg0, IFormReference arg1, String arg2) {
    return null;
  }

  public String setMaskedValue(String arg0, String arg1) {
    return arg1;
  }

  public JSONArray validateSubmittedForm(FormDef arg0, IFormReference arg1, String arg2) {
    return null;
  }

  private String getClientName(IFormReference iFormObj, String stringData) {
    String qry = "select ClientNamePD from Ext_Sme where flag2 = '" + stringData + "'";

    LogMessages.statusLog.info("ClientName query is: " + qry);
    List list_name = iFormObj.getDataFromDB(qry);

    LogMessages.statusLog.info("" + list_name.size());

    LogMessages.statusLog.info(list_name.get(0).toString());
    if (list_name.size() == 0) {
      return "";
    }
    return list_name.get(0).toString();
  }

  public boolean picklistPreHook(String controlId, IFormReference iformObj) {
				 
    LogMessages.statusLog.info("inside picklistPreHook, controlId--" + controlId);
    System.out.println("inside picklistPreHook, controlId--" + controlId);
    String query = "";
    if (controlId.equalsIgnoreCase("table2_UsersOfGroups")) {
      String user = "";
      JSONArray jsonarray = iformObj.getDataFromGrid("table2");
      if (jsonarray.size() > 0) {
        for (int i = 0; i < jsonarray.size(); i++) {
          JSONObject obj = (JSONObject)jsonarray.get(i);
          user = user + ",'" + obj.get("Group Users").toString() + "'";
		 
        }
      }

															
      LogMessages.statusLog.info("user after if condition --" + user);
      System.out.println("user after if condition --" + user);
      String userCondition = "";
      if (!user.equalsIgnoreCase("")) {
        user = user.substring(1, user.length());
																	  
        LogMessages.statusLog.info("Final user list--" + user);
        System.out.println("Final user list--" + user);
        userCondition = "Domanin_Name not in(" + user + ")";
      } else {
        userCondition = "Domanin_Name not in('')";
      }

      query = "select distinct Domanin_Name as 'User ID',User_Name as 'UserName' from LOS_UserAuthorityGroupMapping(nolock) where Authroity_GroupName='" + iformObj.getValue("table2_AuthoritiesGroup") + "' and " + userCondition;

      LogMessages.statusLog.info("**picklist PreHook query --" + query);
      System.out.println("**picklist PreHook query--" + query);
															   
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
      return true;
    }if (controlId.equals("table1_AssignedQueryUser")) {
      query = "select username  as 'UserId',concat(PersonalName,' ',FamilyName) as 'UserName' from pdbuser(nolock) where userindex in (select userindex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='LOS Query Group'))";
																																			  
					 
      LogMessages.statusLog.info("**picklist PreHook query --" + query);
      System.out.println("**picklist PreHook query--" + query);
																									
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
      return true;
    }
    ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
    ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
    return true;
  }

  public void picklistSearchHook(String controlId, IFormReference iformObj, String searchString, int colIndex)
  {
    LogMessages.statusLog.info("inside picklistSearchHook, controlId--" + controlId);
    System.out.println("inside picklistSearchHook, controlId--" + controlId);
    String query = "";

    if (controlId.equals("table2_UsersOfGroups"))
    {
      query = picklistCustomQueryForSearch(controlId, iformObj);

      if ((searchString != "") && (colIndex != -1)) {
        if (colIndex == 0)
          query = query + " and Domanin_Name like '%" + searchString + "%'";
        else if (colIndex == 1) {
          query = query + " and User_Name like '%" + searchString + "%'";
        }
							
      }

      LogMessages.statusLog.info("colIndex-->" + colIndex + " and searchString--" + searchString);
      System.out.println("colIndex-->" + colIndex + " and searchString--" + searchString);

      if ((colIndex == -1) && (searchString != "")) {
        query = query + " and (Domanin_Name like '%" + searchString + "%' or User_Name like '%" + searchString + "%')";
																							   
      }

													
      LogMessages.statusLog.info("**picklist Search query--" + query);
      System.out.println("**picklist Search query -" + query);

      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
    }
    else if (controlId.equals("table1_AssignedQueryUser")) {
      query = "select username  as 'UserId',concat(PersonalName,' ',FamilyName) as 'UserName' from pdbuser(nolock) where userindex in (select userindex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='LOS Query Group'))";

      if ((searchString != "") && (colIndex != -1)) {
        if (colIndex == 0)
          query = query + " and username like '%" + searchString + "%'";
        else if (colIndex == 1) {
          query = query + " and concat(PersonalName,' ',FamilyName) like '%" + searchString + "%'";
																		
						
        }
      }
      LogMessages.statusLog.info("colIndex-->" + colIndex + " and searchString--" + searchString);
      System.out.println("colIndex-->" + colIndex + " and searchString--" + searchString);

      if ((colIndex == -1) && (searchString != "")) {
        query = query + " and (username like '%" + searchString + "%' or concat(PersonalName,' ',FamilyName) like '%" + searchString + "%')";
      }

      LogMessages.statusLog.info("**picklist Search query--" + query);
      System.out.println("**picklist Search query -" + query);

      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
    } else {
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
      ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
    }
  }

			 
  public String picklistCustomQueryForSearch(String controlId, IFormReference iformObj) {
    LogMessages.statusLog.info("inside picklistCustomQueryForSearch, controlId--" + controlId);
    System.out.println("inside picklistCustomQueryForSearch, controlId--" + controlId);
    String query = ""; String user = "";
    JSONArray jsonarray = iformObj.getDataFromGrid("table2");
    if (jsonarray.size() > 0) {
      for (int i = 0; i < jsonarray.size(); i++) {
        JSONObject obj = (JSONObject)jsonarray.get(i);
        user = user + ",'" + obj.get("Group Users").toString() + "'";
      }
    }

    LogMessages.statusLog.info("user after if condition --" + user);
    System.out.println("user after if condition --" + user);
    String userCondition = "";
    if (!user.equalsIgnoreCase("")) {
      user = user.substring(1, user.length());
      LogMessages.statusLog.info("Final user list--" + user);
      System.out.println("Final user list--" + user);
      userCondition = "Domanin_Name not in(" + user + ")";
    } else {
      userCondition = "Domanin_Name not in('')";
    }

    query = "select distinct Domanin_Name as 'User ID',User_Name as 'UserName' from LOS_UserAuthorityGroupMapping(nolock) where Authroity_GroupName='" + iformObj.getValue("table2_AuthoritiesGroup") + "' and " + userCondition;

    LogMessages.statusLog.info("**picklistCustomQueryForSearch query --" + query);
    System.out.println("**picklistCustomQueryForSearch query--" + query);
    return query;
  }

  private String getWorkitemID(IFormReference iFormObj, String stringData) {
    String qryWID = "Select WorkItemId from WFINSTRUMENTTABLE where processInstanceId = '" + stringData + "'";

    LogMessages.statusLog.info("DedupeQuery is: " + qryWID);
    List list_wid = iFormObj.getDataFromDB(qryWID);

    LogMessages.statusLog.info("" + list_wid.size());

    LogMessages.statusLog.info(list_wid.get(0).toString());
    if (list_wid.size() == 0) {
      return "";
    }
    return list_wid.get(0).toString();
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
