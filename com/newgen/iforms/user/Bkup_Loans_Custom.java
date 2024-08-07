/*     */ package com.newgen.iforms.user;
/*     */ 
/*     */ import com.newgen.common.LogMessages;
/*     */ import com.newgen.common.Logging_LOS;
/*     */ import com.newgen.iforms.EControl;
/*     */ import com.newgen.iforms.FormDef;
/*     */ import com.newgen.iforms.custom.IFormCustomHooks;
/*     */ import com.newgen.iforms.custom.IFormReference;
/*     */ import com.newgen.iforms.custom.IFormServerEventHandler;
/*     */ import com.newgen.mvcbeans.model.WorkdeskModel;
/*     */ import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.json.simple.JSONArray;
/*     */ import org.json.simple.JSONObject;
/*     */ 
/*     */ public class Bkup_Loans_Custom extends IFormCustomHooks
/*     */   implements IFormServerEventHandler
/*     */ {
/*  35 */   Logging_LOS logObj = new Logging_LOS();
/*  36 */   WDGeneralData wdgeneralObj = null;
/*  37 */   String processInstanceId = null;
/*  38 */   String activityName = null;
/*  39 */   String UserName = null;
/*     */ 
/*     */   public void beforeFormLoad(FormDef arg0, IFormReference arg1) {
/*  43 */     this.wdgeneralObj = arg1.getObjGeneralData();
/*  44 */     this.processInstanceId = this.wdgeneralObj.getM_strProcessInstanceId();
/*  45 */     this.activityName = this.wdgeneralObj.getM_strActivityName();
/*  46 */     this.UserName = this.wdgeneralObj.getM_strUserName();
/*     */   }
/*     */ 
/*     */   public String executeCustomService(FormDef arg0, IFormReference arg1, String arg2, String arg3, String arg4) {
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */   public JSONArray executeEvent(FormDef arg0, IFormReference arg1, String arg2, String arg3) {
/*  54 */     return null;
/*     */   }
/*     */ 
/*     */   public String executeServerEvent(IFormReference iFormObj, String eventType, String controlName, String stringData) {
/*  59 */     LogMessages.statusLog.info("Inside executeServerEvent");
/*  60 */     LogMessages.statusLog.info("qwerty");
/*     */     try {
/*  62 */       LogMessages.statusLog.info("controlName is: " + controlName);
/*  63 */       LogMessages.statusLog.info("eventType is: " + eventType);
/*  64 */       LogMessages.statusLog.info("stringData is: " + stringData);
/*  65 */       LogMessages.statusLog.info("IFormReference is: " + iFormObj);
/*     */ 
/*  67 */       if (!controlName.equalsIgnoreCase("click"))
/*     */       {
/* 276 */         if ((controlName.equalsIgnoreCase("submit")) && 
/* 277 */           (eventType.equalsIgnoreCase("DocMandatoryCheck")))
/*     */           try {
/* 279 */             LogMessages.statusLog.info("inside DocMandatoryCheck of submit");
/* 280 */             String retXml = iFormObj.getValue("TXT_DocAttach").toString();
/* 281 */             LogMessages.statusLog.info("TXT_FileAttributes" + iFormObj.getValue("TXT_DocAttach"));
/*     */ 
/* 283 */             ArrayList mandatoryDocList = new ArrayList();
/* 284 */             if (this.activityName.equalsIgnoreCase("RO"))
/*     */             {
/* 286 */               mandatoryDocList.add("Company Documents");
/* 287 */               mandatoryDocList.add("Collateral Documents");
/* 288 */               mandatoryDocList.add("Sales Template");
/* 289 */               mandatoryDocList.add("CIC Report");
/* 290 */               mandatoryDocList.add("Citizenship Certificates");
/*     */ 
/* 294 */               LogMessages.statusLog.info("RO mandate doc list--" + mandatoryDocList);
/* 295 */             } else if (this.activityName.equalsIgnoreCase("UnderWriter")) {
/* 296 */               mandatoryDocList.add("CFR");
/*     */             }
/* 298 */             LogMessages.statusLog.info("Final Mandate doc list--" + mandatoryDocList);
/*     */ 
/* 300 */             WFXmlResponse wfxmlresponse = new WFXmlResponse(retXml);
/* 301 */             WFXmlList wfxmllist = wfxmlresponse.createList("Documents", "Document");
/* 302 */             wfxmllist.reInitialize(true);
/* 303 */             LogMessages.statusLog.info("wfxmllist---->" + wfxmllist);
/* 304 */             ArrayList xmlDocList = new ArrayList();
/* 305 */             for (; wfxmllist.hasMoreElements(true); wfxmllist.skip(true))
/*     */             {
/* 309 */               xmlDocList.add(wfxmllist.getVal("DocumentName"));
/* 310 */               LogMessages.statusLog.info("xmlDocList---->" + xmlDocList);
/*     */             }
/*     */ 
/* 316 */             boolean contains = true;
/* 317 */             int l2 = mandatoryDocList.size();
/* 318 */             int currIndex = -1;
/* 319 */             for (int j = 0; j < l2; j++) {
/* 320 */               String e2 = (String)mandatoryDocList.get(j);
/* 321 */               int i1 = xmlDocList.indexOf(e2);
/* 322 */               LogMessages.statusLog.info("e2 mandatoryDocList element--" + e2);
/* 323 */               LogMessages.statusLog.info("i1: Index of e2 in xmlDocList--" + i1);
/* 324 */               if (i1 == -1) {
/* 325 */                 contains = false;
/* 326 */                 LogMessages.statusLog.info("xmlDocList does not contain e2 element: " + e2);
/* 327 */                 return "Please attached the mandatory document: " + e2;
/*     */               }
/* 329 */               if (i1 > currIndex) {
/* 330 */                 currIndex = i1;
/*     */               }
/*     */             }
/* 333 */             LogMessages.statusLog.info("xmlDocList contains all elements of mandatoryDocList--" + contains);
/*     */           } catch (Exception e) {
/* 335 */             LogMessages.statusLog.info("Exception inside submit--" + e);
/*     */           }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 340 */       LogMessages.statusLog.info("Exception--" + e);
/*     */     }
/* 342 */     return "";
/*     */   }
/*     */ 
/*     */   public String getCustomFilterXML(FormDef arg0, IFormReference arg1, String arg2) {
/* 346 */     return null;
/*     */   }
/*     */ 
/*     */   public String setMaskedValue(String arg0, String arg1) {
/* 350 */     return arg1;
/*     */   }
/*     */ 
/*     */   public JSONArray validateSubmittedForm(FormDef arg0, IFormReference arg1, String arg2) {
/* 354 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean checkDedupeStatus(IFormReference iFormObj, String applicantName, String citizen, String mobile, String stringData, String district, String father, String mother) {
/*     */     try {
/* 359 */       String status = "";
/*     */ 
/* 361 */       LogMessages.statusLog.info(" inside checkDedupeStatus");
/* 362 */       String DedupeQuery = "select BorrowersName from EXT_SME WITH(NOLOCK) where ( BorrowersName= '" + applicantName + "' AND CitizenshipNoKyc= '" + citizen + "' AND MobilePhone= '" + mobile + "' AND PlaceofIssuance= '" + district + "' AND FathersName='" + father + "' AND MothersName= '" + mother + "') AND (flag2 != '" + stringData + "' OR flag2 is null)";
/*     */ 
/* 366 */       LogMessages.statusLog.info("DedupeQuery is: " + DedupeQuery);
/*     */ 
/* 368 */       List DedupeQueryList = iFormObj.getDataFromDB(DedupeQuery);
/* 369 */       int listSize = DedupeQueryList.size();
/* 370 */       LogMessages.statusLog.info("List size :: " + listSize);
/* 371 */       LogMessages.statusLog.info("***********************************************");
/* 372 */       if (listSize > 0) {
/* 373 */         status = "true";
/*     */       }
/* 375 */       LogMessages.statusLog.info("status :" + status);
/* 376 */       if (status.equalsIgnoreCase("true")) {
/* 377 */         return true;
/*     */       }
/*     */ 
/* 380 */       return false;
/*     */     } catch (Exception e) {
/* 382 */       LogMessages.statusLog.info("errorLog occured");
/* 383 */       e.printStackTrace();
/*     */     }
/* 385 */     return false;
/*     */   }
/*     */ 
/*     */   private String getClientName(IFormReference iFormObj, String stringData) {
/* 389 */     String qry = "select ClientNamePD from Ext_Sme WITH(NOLOCK) where flag2 = '" + stringData + "'";
/*     */ 
/* 391 */     LogMessages.statusLog.info("ClientName query is: " + qry);
/* 392 */     List list_name = iFormObj.getDataFromDB(qry);
/*     */ 
/* 394 */     LogMessages.statusLog.info("" + list_name.size());
/*     */ 
/* 396 */     LogMessages.statusLog.info(list_name.get(0).toString());
/* 397 */     if (list_name.size() == 0) {
/* 398 */       return "";
/*     */     }
/* 400 */     return list_name.get(0).toString();
/*     */   }
/*     */ 
/*     */   public boolean picklistPreHook(String controlId, IFormReference iformObj) {
/* 404 */     LogMessages.statusLog.info("inside picklistPreHook, controlId--" + controlId);
/* 406 */     if (controlId.equalsIgnoreCase("table2_UsersOfGroups")) {
/* 407 */       String query = ""; String user = "";
/* 408 */       JSONArray jsonarray = iformObj.getDataFromGrid("table2");
/* 409 */       if (jsonarray.size() > 0) {
/* 410 */         for (int i = 0; i < jsonarray.size(); i++) {
/* 411 */           JSONObject obj = (JSONObject)jsonarray.get(i);
/* 412 */           user = user + ",'" + obj.get("Group Users").toString() + "'";
/*     */         }
/*     */       }
/*     */ 
/* 416 */       LogMessages.statusLog.info("user after if condition --" + user);
/* 418 */       String userCondition = "";
/* 419 */       if (!user.equalsIgnoreCase("")) {
/* 420 */         user = user.substring(1, user.length());
/* 421 */         LogMessages.statusLog.info("Final user list--" + user);
/* 423 */         userCondition = "Domanin_Name not in(" + user + ")";
/*     */       } else {
/* 425 */         userCondition = "Domanin_Name not in('')";
/*     */       }
/*     */ 
/* 429 */       query = "select distinct Domanin_Name as 'User ID',User_Name as 'UserName' from LOS_UserAuthorityGroupMapping WITH(NOLOCK) where Authroity_GroupName='" + iformObj.getValue("table2_AuthoritiesGroup") + "' and " + userCondition;
/*     */ 
/* 431 */       LogMessages.statusLog.info("**picklist PreHook query --" + query);
/* 433 */       ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
/* 434 */       ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
/* 435 */       ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
/* 436 */       return true;
/*     */     }
/* 438 */     return true;
/*     */   }
/*     */ 
/*     */   public void picklistSearchHook(String controlId, IFormReference iformObj, String searchString, int colIndex)
/*     */   {
/* 443 */     LogMessages.statusLog.info("inside picklistSearchHook, controlId--" + controlId);
/* 445 */     String query = "";
/*     */ 
/* 447 */     if (controlId.equals("table2_UsersOfGroups"))
/*     */     {
/* 450 */       query = picklistCustomQueryForSearch(controlId, iformObj);
/*     */ 
/* 460 */       if ((searchString != "") && (colIndex != -1)) {
/* 461 */         if (colIndex == 0)
/* 462 */           query = query + " and Domanin_Name like '%" + searchString + "%'";
/* 463 */         else if (colIndex == 1) {
/* 464 */           query = query + " and User_Name like '%" + searchString + "%'";
/*     */         }
/*     */       }
/* 467 */       LogMessages.statusLog.info("colIndex-->" + colIndex + " and searchString--" + searchString);
/*     */ 
/* 470 */       if ((colIndex == -1) && (searchString != "")) {
/* 471 */         query = query + " and (Domanin_Name like '%" + searchString + "%' or User_Name like '%" + searchString + "%')";
/*     */       }
/*     */     }
/* 474 */     LogMessages.statusLog.info("**picklist Search query--" + query);
/*     */ 
/* 477 */     ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
/* 478 */     ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
/* 479 */     ((EControl)iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
/*     */   }
/*     */ 
/*     */   public String picklistCustomQueryForSearch(String controlId, IFormReference iformObj) {
/* 483 */     LogMessages.statusLog.info("inside picklistCustomQueryForSearch, controlId--" + controlId);
/* 485 */     String query = ""; String user = "";
/* 486 */     JSONArray jsonarray = iformObj.getDataFromGrid("table2");
/* 487 */     if (jsonarray.size() > 0) {
/* 488 */       for (int i = 0; i < jsonarray.size(); i++) {
/* 489 */         JSONObject obj = (JSONObject)jsonarray.get(i);
/* 490 */         user = user + ",'" + obj.get("Group Users").toString() + "'";
/*     */       }
/*     */     }
/*     */ 
/* 494 */     LogMessages.statusLog.info("user after if condition --" + user);
/* 496 */     String userCondition = "";
/* 497 */     if (!user.equalsIgnoreCase("")) {
/* 498 */       user = user.substring(1, user.length());
/* 499 */       LogMessages.statusLog.info("Final user list--" + user);
/* 501 */       userCondition = "Domanin_Name not in(" + user + ")";
/*     */     } else {
/* 503 */       userCondition = "Domanin_Name not in('')";
/*     */     }
/*     */ 
/* 507 */     query = "select distinct Domanin_Name as 'User ID',User_Name as 'UserName' from LOS_UserAuthorityGroupMapping WITH(NOLOCK) where Authroity_GroupName='" + iformObj.getValue("table2_AuthoritiesGroup") + "' and " + userCondition;
/*     */ 
/* 509 */     LogMessages.statusLog.info("**picklistCustomQueryForSearch query --" + query);
/* 511 */     return query;
/*     */   }
/*     */ 
/*     */   private String getWorkitemID(IFormReference iFormObj, String stringData) {
/* 515 */     String qryWID = "Select WorkItemId from WFINSTRUMENTTABLE WITH(NOLOCK) where processInstanceId = '" + stringData + "'";
/*     */ 
/* 517 */     LogMessages.statusLog.info("DedupeQuery is: " + qryWID);
/* 518 */     List list_wid = iFormObj.getDataFromDB(qryWID);
/*     */ 
/* 520 */     LogMessages.statusLog.info("" + list_wid.size());
/*     */ 
/* 522 */     LogMessages.statusLog.info(list_wid.get(0).toString());
/* 523 */     if (list_wid.size() == 0) {
/* 524 */       return "";
/*     */     }
/* 526 */     return list_wid.get(0).toString();
/*     */   }
/*     */ 
/*     */   public String generateHTML(EControl ec)
/*     */   {
/* 531 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1)
/*     */   {
/* 536 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1, WorkdeskModel wm)
/*     */   {
/* 541 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ }