/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.iforms.util;

import java.io.File;
import java.util.HashMap;

public class UtilConstants {
	public static String activityName = "";
	public static String columnNames = "";
	public static String fieldNames = "";
	public static String pid = "";
	public static String tableName = "";

	public static String getActivityName() {
		return activityName;
	}

	public static void setActivityName(String activityName) {
		UtilConstants.activityName = activityName;
	}

	public static final int XML_LOG = 1;
	public static final int SUCCESS_LOG = 2;
	public static final int ERROR_LOG = 3;
	// Used as return values for various calls
	public static final boolean SUCCESS = true;
	public static final boolean FAILURE = false;
	// Used as return values for various calls
	public static final String SUCCESS_STR = "SUCCESS";
	public static final String FAILURE_STR = "FAILURE";
	// The filename for the log types
	public static final String XML_LOG_NAME = "Xml.log";
	public static final String SUCCESS_LOG_NAME = "Success.log";
	public static final String ERROR_LOG_NAME = "Error.log";
	// To check if log file generation is required
	public static String LOG_FILE_REQUIRED = "Y";
	public static final String LOG_FILE_NOT_REQUIRED = "N";
	public static final String FILE_SAPARATOR = File.separator;
	public static String Initiator = "Bucket";
	public static String collection_Approver = "Legal_Approver";
	public static String ControlID = "";
	public static boolean flag = false;

	public static String action_Code = "";
	public static String fromDate = "";
	public static String toDate = "";
	public static String loanAccountNo = "";
	public static String collection_Escalation = "Escalation";
	public static String collection_Sarfaesi = "Sarfaesi_Legal_Queue";
	public static String Collection_Legal_Maintenance = "Legal_Maintenance";
	public static HashMap<String, String> colfield = new HashMap<String, String>();
	public static String cifNumber = "";
	public static String collateralID = "";
	public static String notePreparation = "NotePreparation";
	public static String dRT_Convener_Branch_Manager = "DRT Convener Branch Manager";
	public static String dRT_Branch_SNP = "DRT Branch SNP";
	public static String dRT_Branch_CAC_of_BM = "DRT Branch CAC of BM";
	public static String dRT_Branch_ConveytoAdvocate = "DRT Branch ConveytoAdvocate";
	public static String arc = "ARC";
	public static String assignmentAtZO = "AssignmentAtZO";
	public static String branchHead = "BranchHead";
	public static String DRT = "DRT";
	public static String DRT_Branch_ConveytoAdvocate = "DRT Branch ConveytoAdvocate ";
	public static String AssignmentAtZO = "AssignmentAtZO";
	public static String NotePreparationZO = "NotePreparationZO";
	public static String ZOCommitteeConvener = "ZOCommitteeConvener";
	public static String ZonalManager = "ZonalManager";
	public static String ZOSantion_Rejection_Letter = "ZOSantion_Rejection_Letter";
	public static String ZOSantionRjectionVetting = "ZOSantionRjectionVetting";
	public static String ZORecoveryOfficer = "ZORecoveryOfficer";
	public static String AssignmentAtHO = "AssignmentAtHO";
	public static String NotePreparationHO = "NotePreparationHO";
	public static String HOVetting = "HOVetting";
	public static String HODZM = "HODZM";
	public static String GM_Approval = "GM Approval ";
	public static String HOCommitteeConvener = "HOCommitteeConvener";
	public static String CAC_III = "CAC III";
	public static String CAC_II = "CAC II";
	public static String HOSanction_RejectionLetter = "BraHOSanction_RejectionLetternchHead";
	public static String HOSantionRjectionVetting = "HOSantionRjectionVetting";
	public static String HORecoveryOfficer = "HORecoveryOfficer";
	public static Boolean OTS_checker= activityName.matches("'"+UtilConstants.getCollection_Approver()+"|"+UtilConstants.getCollection_Sarfaesi()+"|"+UtilConstants.getInitiator()+"|"+UtilConstants.getCollection_Escalation()+"|"+UtilConstants.getCollection_Legal_Maintenance()+"'");

	 

    public static Boolean getOTS_checker() {
        return OTS_checker;
    }

 

    public static void setOTS_checker(Boolean oTS_checker) {
        OTS_checker = oTS_checker;
    }

	public static String getNotePreparation() {
		return notePreparation;
	}

	public static void setNotePreparation(String notePreparation) {
		UtilConstants.notePreparation = notePreparation;
	}

	public static String getBranchHead() {
		return branchHead;
	}

	public static void setBranchHead(String branchHead) {
		UtilConstants.branchHead = branchHead;
	}

	public static String getdRT_Convener_Branch_Manager() {
		return dRT_Convener_Branch_Manager;
	}

	public static void setdRT_Convener_Branch_Manager(String dRT_Convener_Branch_Manager) {
		UtilConstants.dRT_Convener_Branch_Manager = dRT_Convener_Branch_Manager;
	}

	public static String getdRT_Branch_SNP() {
		return dRT_Branch_SNP;
	}

	public static void setdRT_Branch_SNP(String dRT_Branch_SNP) {
		UtilConstants.dRT_Branch_SNP = dRT_Branch_SNP;
	}

	public static String getCollection_Legal_Maintenance() {
		return Collection_Legal_Maintenance;
	}

	public static String getAction_Code() {
		return action_Code;
	}

	public static String getCollection_Approver() {
		return collection_Approver;
	}

	public static String getCollection_Escalation() {
		return collection_Escalation;
	}

	public static String getCollection_Sarfaesi() {
		return collection_Sarfaesi;
	}

	public static void setAction_Code(String action_Code) {
		UtilConstants.action_Code = action_Code;
	}

	public static String getFromDate() {
		return fromDate;
	}

	public static void setFromDate(String fromDate) {
		UtilConstants.fromDate = fromDate;
	}

	public static String getToDate() {
		return toDate;
	}

	public static void setToDate(String toDate) {
		UtilConstants.toDate = toDate;
	}

	public static String getPid() {
		return pid;
	}

	public static void setPid(String pid) {
		UtilConstants.pid = pid;
	}

	public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean flag) {
		UtilConstants.flag = flag;
	}

	public static String getControlID() {
		return ControlID;
	}

	public static void setControlID(String controlID) {
		ControlID = controlID;
	}

	public static String getCollectionApprover() {
		return collection_Approver;
	}


	public static void setInitiator(String initiator) {
		Initiator = initiator;
	}

	public static void setCollectionApprover(String collectionApprover) {
		collection_Approver = collectionApprover;
	}

	public static String getLoanaccountno() {
		return loanAccountNo;
	}

	public static void setLoanaccountno(String loanaccountno) {
		loanAccountNo = loanaccountno;
	}

	public static String getColumnNames() {
		return columnNames;
	}

	public static void setColumnNames(String columnNames) {
		UtilConstants.columnNames = columnNames;
	}

	public static String getFieldNames() {
		return fieldNames;
	}

	public static void setFieldNames(String fieldNames) {
		UtilConstants.fieldNames = fieldNames;
	}

	public static String getTableName() {
		return tableName;
	}

	public static void setTableName(String tableName) {
		UtilConstants.tableName = tableName;
	}

	public static String getLOG_FILE_REQUIRED() {
		return LOG_FILE_REQUIRED;
	}

	public static void setLOG_FILE_REQUIRED(String lOG_FILE_REQUIRED) {
		LOG_FILE_REQUIRED = lOG_FILE_REQUIRED;
	}

	public static int getErrorLog() {
		return ERROR_LOG;
	}

	public static String getInitiator() {
		return Initiator;
	}

	public static int getXmlLog() {
		return XML_LOG;
	}

	public static int getSuccessLog() {
		return SUCCESS_LOG;
	}

	public static boolean isSuccess() {
		return SUCCESS;
	}

	public static boolean isFailure() {
		return FAILURE;
	}

	public static String getSuccessStr() {
		return SUCCESS_STR;
	}

	public static String getFailureStr() {
		return FAILURE_STR;
	}

	public static String getXmlLogName() {
		return XML_LOG_NAME;
	}

	public static String getSuccessLogName() {
		return SUCCESS_LOG_NAME;
	}

	public static String getErrorLogName() {
		return ERROR_LOG_NAME;
	}

	public static String getLogFileRequired() {
		return LOG_FILE_REQUIRED;
	}

	public static String getLogFileNotRequired() {
		return LOG_FILE_NOT_REQUIRED;
	}

	public static String getFileSaparator() {
		return FILE_SAPARATOR;
	}

}

