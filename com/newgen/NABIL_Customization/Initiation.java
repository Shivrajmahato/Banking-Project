package com.newgen.NABIL_Customization;
/*
import com.newgen.common.AckMail;
import com.newgen.common.General;
import com.newgen.common.Users;
import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormCustomHooks;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.mvcbeans.model.WorkdeskModel;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

public class Initiation extends IFormCustomHooks implements IFormServerEventHandler {

    WDGeneralData wdgeneralObj = null;
    General objGeneral = null;
    String UserName, Query;
    List<List<String>> result;
    private int batch;

    //FormReference formObject = null;
    //FormConfig formConfig = null;
    String activityName = null;
    String engineName = null;
    String sessionId = null;
    String folderId = null;
    String serverUrl = null;
    String processInstanceId = null;
    String workItemId = null;
    String userName = null;
    String processDefId = null;
    //String Query = null;
    String department = "";
    String newDept = "";
    Users user = null;
    String supervisor = "";
    String selectedattorney = "";
    ArrayList supervisors = new ArrayList();
    ArrayList attorney = new ArrayList();
    ArrayList secretary = new ArrayList();
    ArrayList departmentList = new ArrayList();
    ArrayList deptHeadList = new ArrayList();
    //PickList objPicklist;
    String docketID = "";
//  General objGeneral = null;
    AckMail ack = null;

    @Override
    public void beforeFormLoad(FormDef fd, IFormReference ifr) {

        wdgeneralObj = ifr.getObjGeneralData();
        processInstanceId = wdgeneralObj.getM_strProcessInstanceId();
        activityName = wdgeneralObj.getM_strActivityName();
        UserName = wdgeneralObj.getM_strUserName();
        objGeneral = new General();
        userName = UserName;
    }

    @Override
    public String setMaskedValue(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray executeEvent(FormDef fd, IFormReference ifr, String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String executeServerEvent(IFormReference ifr, String string, String string1, String string2) {
        objGeneral = new General();
        String immediateSupervisor = "";
        String userDepartment = "";
        String userRole = "";
        //System.out.println("*************************Inside executeServerEvent()*****************");
        switch (string1) {
            case "submitFormCompletedUpdate":
                submitFormCompleted(ifr);
                break;

            case "REMARKS_HISTORY":
                        try {
                        String docketpid = ifr.getValue("DOCKETPID").toString();
                        if (docketpid == null || docketpid.equalsIgnoreCase("")) {
                        } else {
                            String docketID = "" + ifr.getValue("DOCKETPID");
                            String pID = wdgeneralObj.getM_strProcessInstanceId();//"" + formObject.getValue("PID");
                            String Query = "select addedby, addedon, comments from  MASTER_COMMENTSHISTORY  where pid='" + pID + "' order by sno desc";
                            String docketQuery = "select addedby, addedon, comments from  MASTER_COMMENTSHISTORY  where docketpid='" + docketID + "'";
                            user.ShowComments(Query, "DocketingRemarks", docketQuery, ifr);
                            //System.out.println("After user.ShowComments()*********--");
                            Query = "";
                        }
                    } catch (Exception e) {
                        //System.out.println("Exception in showComments" + e.getMessage());
                    }
                break;        
           
            case "FormLoad":
                try {
                String tempDept = "";
                objGeneral = new General();
                //System.out.println("----------------------Department Head Workstep beforeFormLoad() populated.---------------------------");

                ifr.setValue("DOCK_DEPARTMENT", "Design");
                //ifr.setDisabledGray(true);                 
                userName = wdgeneralObj.getM_strUserName();
                UserName = wdgeneralObj.getM_strUserName();
                String dptHead = "" + ifr.getValue("DEPT_HEAD");
                String sup = "" + ifr.getValue("SUPERVISOR");
                String atr = "" + ifr.getValue("ATTORNEY");
                String sec = "" + ifr.getValue("SECRETARY");

                ack = new AckMail();
                Query = "select ackmailsent from ext_design_detail where pid='" + processInstanceId + "'";
                Boolean status = ack.getAckMailStatus(Query, ifr);
                if (status) {
                    //System.out.println("Make it red");
                    ifr.setStyle("ackAction", "backcolor", "red");
                } else {
                    //System.out.println("Make it green");
                    ifr.setStyle("ackAction", "backcolor", "green");
                }

                if ((UserName.equalsIgnoreCase(dptHead)) | (UserName.equalsIgnoreCase(sup)) || (UserName.equalsIgnoreCase(atr)) || (UserName.equalsIgnoreCase(sec))) {
                    //System.out.println("@@@@@@@ if");
                    //ifr.setEnabled("Frame1", true);
                } else {
                    //System.out.println("@@@@@@@@  else");
                    ifr.setStyle("ackAction", "disable", "true");
                    ifr.setStyle("frame1", "disable", "true");
                    ifr.setStyle("frame3", "disable", "true");			// ifr.setStyle("Frame1", "disable", "true");
                    ifr.setStyle("frame4", "disable", "true");
                    ifr.setStyle("frame5", "disable", "true");
                }
                //System.out.println("Manual Initiation1");
                tempDept = "";
                user = new Users();

                if (activityName.equalsIgnoreCase("Initiation")) {
                    ifr.setStyle("Label9", "visible", "true");
                    ifr.setStyle("Label23", "visible", "false");
                    ifr.setValue("DOCK_DEPARTMENT", "Design");
                    ifr.setValue("MANUAL_INITITATION", "Y");
                    ifr.setStyle("MAILFROM", "disable", "false");
                    ifr.setStyle("MAILTO", "disable", "false");
                    ifr.setStyle("MAILCC", "disable", "false");
                    ifr.setStyle("MAILSUBJECT", "disable", "false");
                    ifr.clearCombo("REASSIGN_DEPT_HEAD");

                    try {
                        String reassignDeptCombo = ifr.getValue("REASSIGN_DEPT_HEAD").toString();
                        String userDept = ifr.getValue("REASSIGN_DEPT").toString();
                        userRole = "Department Head";

                        deptHeadList = user.getDepartmentHeadList(userRole, userDept, ifr);
                        Iterator deptHeadItr = deptHeadList.iterator();
                        ifr.clearCombo("REASSIGN_DEPT_HEAD");
                        ifr.addItemInCombo("REASSIGN_DEPT_HEAD", "Select", "");
                        while (deptHeadItr.hasNext()) {
                            ifr.addItemInCombo("REASSIGN_DEPT_HEAD", deptHeadItr.next().toString());
                        }
                        ifr.setValue("REASSIGN_DEPT_HEAD", reassignDeptCombo);
                    } catch (Exception e) {
                        //////System.out.println("Exception in setting REASSIGN_DEPT_HEAD combo****" + e);
                    }
                }
                if (activityName.equalsIgnoreCase("Department Head") || activityName.equalsIgnoreCase("Supervisor")) {
                    if (activityName.equalsIgnoreCase("Supervisor")) {
                        ifr.setStyle("frame5", "disable", "true");	//	"Frame4" disacard section
                    }
                    if (activityName.equalsIgnoreCase("Department Head")) {
                        //ifr.setStyle("Label9", "visible", "true");
			//		ifr.setStyle("Label23", "visible", "false");
                        ifr.setStyle("SUPERVISOR", "mandatory", "true");
                        ifr.setStyle("ATTORNEY", "mandatory", "false");
                    }

                    if (activityName.equalsIgnoreCase("Supervisor")) {
                        //ifr.setStyle("Label9", "visible", "false");		// supervisor mandatory
			//		ifr.setStyle("Label23", "visible", "true");		// attorney mandatory
                         
                        ifr.setStyle("SUPERVISOR", "mandatory", "false");
                        ifr.setStyle("ATTORNEY", "mandatory", "true");
                    }
                    ifr.setStyle("MAILFROM", "disable", "true");
                    ifr.setStyle("MAILTO", "disable", "true");
                    ifr.setStyle("MAILCC", "disable", "true");
                    ifr.setStyle("MAILSUBJECT", "disable", "true");

                    // System.out.println("----------------------activity1234 :----------------" + activityName);
                    if (activityName.equalsIgnoreCase("Supervisor")) {
                        // System.out.println("----------------------Enable disable----------------");
                        ifr.setStyle("DEPT_HEAD", "disable", "true");
                        ifr.setStyle("SUPERVISOR", "disable", "true");
                    }

                    docketID = ifr.getValue("DOCKETPID").toString();
                    if (docketID != null) {
                        // System.out.println("----------------------docketID1234 :----------------" + docketID.toString());

                        Query = "select addedby, addedon, comments from master_commentshistory where docketpid='" + docketID.toString() + "'";
                        //user.ShowComments(Query, "DOCKETING_REMARKS");
                        Query = "";

                    }
                    try {
                        String reassignDeptCombo = ifr.getValue("REASSIGN_DEPT_HEAD").toString();
                        String userDept = ifr.getValue("REASSIGN_DEPT").toString();
                        userRole = "Department Head";

                        deptHeadList = user.getDepartmentHeadList(userRole, userDept, ifr);
                        Iterator deptHeadItr = deptHeadList.iterator();
                        ifr.clearCombo("REASSIGN_DEPT_HEAD");
                        ifr.addItemInCombo("REASSIGN_DEPT_HEAD", "Select", "");
                        while (deptHeadItr.hasNext()) {
                            ifr.addItemInCombo("REASSIGN_DEPT_HEAD", deptHeadItr.next().toString());
                        }
                        ifr.setValue("REASSIGN_DEPT_HEAD", reassignDeptCombo);
                    } catch (Exception e) {
                        //////System.out.println("Exception in setting REASSIGN_DEPT_HEAD combo****" + e);
                    }
                }
                ///////a@$

                
                try {
                    departmentList = user.getDepartmentList("Design", ifr);
                    Iterator deptItr = departmentList.iterator();
                    while (deptItr.hasNext()) {
                        tempDept = deptItr.next().toString();
                        //ifr.addItem("Remfry_Litigation_DOCK_DEPARTMENT",tempDept );
                        ifr.addItemInCombo("REASSIGN_DEPT", tempDept);
                    }

                    String DeptCombo = ifr.getValue("DEPT_HEAD").toString();
                    ifr.clearCombo("DEPT_HEAD");
                    ifr.addItemInCombo("DEPT_HEAD", "Select", "");
                    deptHeadList = user.getDepartmentHeadList("Department Head", "Design", ifr);
                    Iterator deptHeadItr = deptHeadList.iterator();
                    while (deptHeadItr.hasNext()) {
                        String deptDetail = deptHeadItr.next().toString();
                        ifr.addItemInCombo("DEPT_HEAD", deptDetail);
                    }
                    ifr.setValue("DEPT_HEAD", DeptCombo);
                } catch (Exception e) {
                    //////System.out.println("Exception in setting DEPT_HEAD combo****" + e);
                }

                try {
                    String supCombo = ifr.getValue("SUPERVISOR").toString();
                    supervisors = user.getSupervisor("Design", "Supervisor", ifr);
                    Iterator supervisorItr = supervisors.iterator();

                    ifr.clearCombo("SUPERVISOR");
                    ifr.addItemInCombo("SUPERVISOR", "Select", "");
                    while (supervisorItr.hasNext()) {
                        ifr.addItemInCombo("SUPERVISOR", supervisorItr.next().toString());
                    }
                    ifr.setValue("SUPERVISOR", supCombo);
                } catch (Exception e) {
                    //////System.out.println("Exception in setting ATTORNEY combo****" + e);
                }

                String attCombo = ifr.getValue("ATTORNEY").toString();
                ifr.clearCombo("ATTORNEY");
                ifr.addItemInCombo("ATTORNEY", "Select", "");

                immediateSupervisor = ifr.getValue("SUPERVISOR").toString();
                userDepartment = "Design";
                userRole = "Attorney";
                ////System.out.println("immediateSupervisor :" + immediateSupervisor);
                attorney = user.getAttorney(userDepartment, userRole, immediateSupervisor, ifr);
                Iterator attorneyItr = attorney.iterator();
                while (attorneyItr.hasNext()) {
                    ifr.addItemInCombo("ATTORNEY", attorneyItr.next().toString());
                }
                ifr.setValue("ATTORNEY", attCombo);

                try {
                    String secCombo = ifr.getValue("SECRETARY").toString();
                    immediateSupervisor = ifr.getValue("ATTORNEY").toString();
                    userDepartment = "Design";
                    userRole = "Secretary";
                    secretary = user.getAttorney(userDepartment, userRole, immediateSupervisor, ifr);
                    Iterator secretaryItr = secretary.iterator();
                    ifr.clearCombo("SECRETARY");
                    ifr.addItemInCombo("SECRETARY", "Select", "");
                    //////System.out.println("Before Adding values in ATTORNEY");
                    while (secretaryItr.hasNext()) {
                        //    //////System.out.println(" Sec_combo value :" + secretaryItr.next().toString());
                        ifr.addItemInCombo("SECRETARY", secretaryItr.next().toString());
                    }
                    ifr.setValue("SECRETARY", secCombo);
                } catch (Exception e) {
                    //////System.out.println("Exception in setting SECRETARY combo****" + e);
                }

                String clientCode = "" + ifr.getValue("CLIENT_CODE");
                if (!(clientCode.equalsIgnoreCase("null"))) {
                    try {
                        String stat = objGeneral.getPendingDebitsInitiation(clientCode, "DebitNoteLISTView", ifr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (activityName.equalsIgnoreCase("Supervisor")) {
                    //System.out.println("----------------------Enable disable of Supervisor ws----------------");
                    if (userName.equalsIgnoreCase(dptHead)) {
                        ifr.setStyle("SUPERVISOR", "disable", "false");

                    } else {
                        ifr.setStyle("SUPERVISOR", "disable", "true");
                    }
                    ifr.setStyle("ATTORNEY", "disable", "false");
                    ifr.setStyle("SECRETARY", "disable", "false");
                    ifr.setStyle("DEPT_HEAD", "disable", "true");

                }
                Query = "";
                //System.out.print("-------------------exit of beforeFormLoad---------");
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;

            case "VALUE_CHANGED":
                switch (string) {
                    case "SUPERVISOR":
                        String attCombo = ifr.getValue("ATTORNEY").toString();
                        ifr.clearCombo("ATTORNEY");
                        ifr.addItemInCombo("ATTORNEY", "Select", "");

                        immediateSupervisor = ifr.getValue("SUPERVISOR").toString();
                        userDepartment = "Design";
                        userRole = "Attorney";
                        //System.out.println("immediateSupervisor :" + immediateSupervisor);
                        attorney = user.getAttorney(userDepartment, userRole, immediateSupervisor, ifr);
                        Iterator attorneyItr = attorney.iterator();
                        while (attorneyItr.hasNext()) {
                            ifr.addItemInCombo("ATTORNEY", attorneyItr.next().toString());
                        }
                        ifr.setValue("ATTORNEY", attCombo);
                        break;

                    try {
                        String secCombo = ifr.getValue("SECRETARY").toString();
                        immediateSupervisor = ifr.getValue("ATTORNEY").toString();
                        userDepartment = "Design";
                        userRole = "Secretary";
                        secretary = user.getAttorney(userDepartment, userRole, immediateSupervisor, ifr);
                        Iterator secretaryItr = secretary.iterator();
                        ifr.clearCombo("SECRETARY");
                        ifr.addItemInCombo("SECRETARY", "Select", "");
                        while (secretaryItr.hasNext()) {
                            ifr.addItemInCombo("SECRETARY", secretaryItr.next().toString());
                        }
                        ifr.setValue("SECRETARY", secCombo);
                    } catch (Exception e) {
                        ////System.out.println("Exception in setting SECRETARY combo****" + e);
                    }
                    break;

                    case "REASSIGN_DEPT":
                        try {
                        String reassignDeptCombo = ifr.getValue("REASSIGN_DEPT_HEAD").toString();
                        String userDept = ifr.getValue("REASSIGN_DEPT").toString();
                        userRole = "Department Head";

                        deptHeadList = user.getDepartmentHeadList(userRole, userDept, ifr);
                        Iterator deptHeadItr = deptHeadList.iterator();
                        ifr.clearCombo("REASSIGN_DEPT_HEAD");
                        ifr.addItemInCombo("REASSIGN_DEPT_HEAD", "Select", "");
                        while (deptHeadItr.hasNext()) {
                            ifr.addItemInCombo("REASSIGN_DEPT_HEAD", deptHeadItr.next().toString());
                        }
                        ifr.setValue("REASSIGN_DEPT_HEAD", reassignDeptCombo);
                    } catch (Exception e) {
                        //////System.out.println("Exception in setting REASSIGN_DEPT_HEAD combo****" + e);
                    }
                    break;
                }
                break;

            case "MOUSE_CLICKED":
                switch (string) {
                    case "ackAction":
                        ack = new AckMail();
                        String mailTO = "" + ifr.getValue("MAILTO");
                        String mailCC = "" + ifr.getValue("MAILCC");
                        String mailSubject = "" + ifr.getValue("MAILSUBJECT");
                        boolean ackStatus = ack.ackMail(mailTO, mailCC, mailSubject, ifr);
                        if (ackStatus) {
                            Query = "update ext_design_detail set ackmailsent='Y' where pid='" + processInstanceId + "'";
                            ack.updateAckMailStatus(Query, ifr);
                            Query = "select ackmailsent from ext_design_detail where pid='" + processInstanceId + "'";
                            if (ack.getAckMailStatus(Query, ifr)) {
                                ifr.setStyle("ackAction", "backcolor", "red");
                            } else {
                                ifr.setStyle("ackAction", "backcolor", "green");
                            }
                        }
                        break;
                }
                break;

            case "FormValidation":
                switch (string2) {
                    case "S":
                        break;
                    case "I":
                    case "D":
                        //System.out.println("Inside D");                       
                        break;
                }
                break;
        }
        return null;
    }

    public void submitFormCompleted(IFormReference formObject) {
        System.out.print("----Inside submitform completed validation---------");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //("dd-MMM-yy");
        Date date = new Date();
        processInstanceId = wdgeneralObj.getM_strProcessInstanceId();
        userName = wdgeneralObj.getM_strUserName();

        String reassignment = "" + formObject.getValue("REASSIGNMENT");
        if (!(reassignment.equalsIgnoreCase("Yes"))) {
            user.insertComments("insert into MASTER_COMMENTSHISTORY(PID,COMMENTS,ADDEDBY,ADDEDON,SNO) Values('" + processInstanceId + "','" + formObject.getValue("INITIATOR_REMARKS").toString() + "','" + userName + "','" + dateFormat.format(date) + "',CUSTOM_MASTER_COMMENTS_HISTORY.NEXTVAL)", formObject);
            formObject.setValue("REASSIGN_REMARKS", "");
        } else {
            String comments = "" + formObject.getValue("REASSIGN_REMARKS");
            String addedBy = "" + formObject.getUserName();
            Date curDate = new Date();
            DateFormat date_Format = new SimpleDateFormat("dd/MM/yyyy"); //("dd-MMM-yy");

            String dockPid = "" + formObject.getValue("DOCKETPID");

            String insertQuery = "insert into master_commentshistory(COMMENTS,ADDEDBY,ADDEDON,DOCKETPID,SNO) values('" + comments + "','" + addedBy + "','" + date_Format.format(curDate) + "','" + dockPid + "',CUSTOM_MASTER_COMMENTS_HISTORY.NEXTVAL)";
            //System.out.println("insertQuery :"+insertQuery);
            user.insertComments(insertQuery, formObject);
        }
        formObject.setValue("INITIATOR_REMARKS", "");

        String discardStatus = "" + formObject.getValue("DISCARD_STATUS");
        if (discardStatus.equalsIgnoreCase("Yes")) {
            String addedBy = "" + formObject.getUserName();
            formObject.setValue("DISCARDBY", addedBy);
        }
    }

    @Override
    public JSONArray validateSubmittedForm(FormDef fd, IFormReference ifr, String string
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String executeCustomService(FormDef fd, IFormReference ifr, String string, String string1, String string2
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCustomFilterXML(FormDef fd, IFormReference ifr, String string
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean picklistPreHook(String controlId, IFormReference ifr) {
        
    }

//    
//     *
//     * @param controlId
//     * @param objReference
//     * @param searchString
//     * @param colIndex
//     
    @Override
    public void picklistSearchHook(String controlId, IFormReference ifr, String searchString, int colIndex) {
        //System.out.println("inside search picklistSearchHook ");
        //System.out.println("controlId :" + controlId);
        //System.out.println("searchString :" + searchString); //xyz
        //System.out.println("colIndex :" + colIndex); //-1
        if (controlId.equalsIgnoreCase("account")) {
            String accounttype = (String) ifr.getValue("accounttype");
            //System.out.println("accounttype :" + accounttype);
            if (accounttype.equalsIgnoreCase("Vendor")) {
                //System.out.println("inside vendor");
                if (colIndex == -1) {
                    //System.out.println("inside -1");
                    Query = "select VendorCode,VendorName from VendorMaster where  VendorCode like '%" + searchString + "%' OR VendorName  like '%" + searchString + "%' order by VendorCode asc";
                } else if (colIndex == 0) {
                    //System.out.println("inside 0");
                    Query = "select VendorCode,VendorName from VendorMaster where  VendorCode like '%" + searchString + "%' order by VendorCode asc";
                } else if (colIndex == 1) {
                    Query = "select VendorCode,VendorName from VendorMaster where  VendorName like '%" + searchString + "%' order by VendorCode asc";
                }
            } else if (accounttype.equalsIgnoreCase("Customer")) {
                //System.out.println("inside Customer");
                if (colIndex == -1) {
                    //System.out.println("inside -1");
                    Query = "select Code,Description from CustomerMaster where Code like '%" + searchString + "%' OR Description  like '%" + searchString + "%' order by Code asc";
                } else if (colIndex == 0) {
                    //System.out.println("inside 0");
                    Query = "select Code,Description from CustomerMaster where Code like '%" + searchString + "%' order by Code asc";
                } else if (colIndex == 1) {
                    Query = "select Code,Description from CustomerMaster where Description like '%" + searchString + "%' order by Code asc";
                }

            } else {
                //System.out.println("Kindly select the account type value");
            }
            //System.out.println("Query " + Query);
            try {
                ((EControl) ifr.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(Query);
                //System.out.println("After setting query");
            } catch (Exception e) {
                //System.out.println("Exception: " + e);
            }
        }
    }

    @Override
    public String generateHTML(EControl ec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1, WorkdeskModel wm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
*/