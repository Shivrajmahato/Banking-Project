package com.newgen.iforms.user;

/**
 * @author shivaraj
 * 
 */

import com.newgen.common.APIConsumption;
import com.newgen.common.BranchDetails;
import com.newgen.common.LogMessages;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.util.CommonMethods;
import com.newgen.integrations.*;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.newgen.iforms.util.NewGenUtil.*;
import static com.newgen.iforms.util.NewGenUtilConstants.*;

public class LoansCustom extends FormServerEventHandlerCommon {

    public static final String USER_DECISION = "userDecision--";
    public static final String AUTHORITY_GROUP = "AuthorityGroup";
    public static final String SPECIAL_STRING = "', \n";
    public static final String ACCTTRANS_BODY_SUCCESS = "accttrans BODY: SUCCESS ##";


    WDGeneralData wdGeneralData = null;
    String processInstanceId = null;
    String activityName = null;
    String userName = null;
    BranchDetails bd = new BranchDetails();
    APIConsumption apiConsumption = new APIConsumption();

    public void beforeFormLoad(FormDef arg0, IFormReference arg1) {
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
                    return handleClickEvent(iFormObj, eventType, stringData);
                case "FormLoad":
                    break;
                case "MOUSE_CLICKED":
                    if (handleMouseClickEvent(iFormObj, eventType)) return this.wdGeneralData.getM_strUserIndex();
                    break;
                case "VALUE_CHANGED":
                    break;
                case "submit":
                    String e21 = handleSubmitEvent(iFormObj, eventType);
                    if (e21 != null) return e21;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception inside setEmailIdsofUsers of submit--" + e);
        }
        return "";
    }

    private String handleClickEvent(IFormReference iFormObj, String eventType, String stringData) throws IOException {
        switch (eventType) {
            case "getBranchDetails":
                return bd.getBranchDetails(stringData, iFormObj);
            case "intTableMaintain":
                return intTableMaintain(stringData);
            case "sanctionLimitMod":
                return getSanctionLimitMod(stringData);
            case "cCALoanAccountModify":
                return getCCALoanAccountModify(stringData);
            case "Fetch_All_Retail_Review_Renew_Data":
                return String.valueOf(RetailAllDataFetch.retailAllDataFetch(iFormObj));
            case "Fetch_All_SME_Review_Renew_Data":
                return String.valueOf(SMEAllDataFetch.smeAllDataFetch(iFormObj));
            case "getCSVRDetail":
                return CSVRDetail.getData(CSVRDetail.getSPToken(), stringData);
            case "getBSVRDetail":
                LogMessages.statusLog.info("Inside getBSVRDetail"); 
                return BSVRDetail.getData(BSVRDetail.getSPToken(), stringData);
            case "LimitTreeDetails":
                return getLimitTreeDetails(iFormObj, stringData);
            case "OfferingSheetGeneration":
                return getOfferingSheetGeneration(iFormObj, stringData);
            case "gridfetch":
                GridFetch grid = new GridFetch();
                return grid.gridToGridFetch(iFormObj);
            case "GetAccountDetailsInLoanAcctCreation":
                return getAccountDetailsInLoanAcctCreation(stringData);
            case "retKYCDetails":
                return getRetKYCDetails(stringData);
            case "retKYCDetailsCorp":
                return getRetKYCDetailsCorp(stringData);
            case "LoanServicingDetails":
                return getLoanServicingDetails(stringData);
            case "SanctionLimitDetails":
                return getSanctionLimitDetails(stringData);
            case "nonLAAloanDisbursement":
                return getNonLAAloanDisbursement(stringData);
            case "ThirdPartyTransfer":
                return getThirdPartyTransfer(stringData);
            case "cCALoanAccountOpening":
                return getCCALoanAccountOpening(stringData);
            case "LoanDisbursement":
                return getLoanDisbursement(stringData);
            case "LoanAccountOpen":
                return getLoanAccountOpen(iFormObj, stringData);
            case "LimitNodeCreation":
                return getLimitNodeCreation(stringData);
            case "LimitNodeModify":
                return getLimitNodeModify(stringData);
            case "translateEnglish":
                return translateEnglish(stringData);
            case "translateNepali":
                return translateNepali(stringData);
            case "accountTransfer":
                return performAccountTransfer(stringData);
            case "saveRetailDataIntoCustomTable":
                return saveRetailDataIntoCustomTable(iFormObj, stringData);
            case "getRetailDataFromCustomTable":
                return getRetailDataFromCustomTable(iFormObj, stringData);
            case "genSMEApprovalSummaryTempIndividual":
            case "genSMEApprovalSummaryTempInstitution":
            case "genRetailApprovalSummaryTempIndividual":
            case "genRetailApprovalSummaryTempInstitution":
                return generateTemplateDoc(iFormObj, stringData, eventType);
            case "loanDemandSatisfaction":
                return loanDemandSatisfaction(stringData);
            case "loanOverdueCollection":
                return loanOverdueCollection(stringData);
                
            default:
                return "";
        }
    }

    private boolean handleMouseClickEvent(IFormReference iFormObj, String eventType) {
        if (eventType.equalsIgnoreCase("onclickbtn_BAMdownload")) {
            this.wdGeneralData = iFormObj.getObjGeneralData();
            return true;
        }
        return false;
    }

    private String handleSubmitEvent(IFormReference iFormObj, String eventType) {
        switch (eventType) {
            case "DocMandatoryCheck":
                String e21 = getDocMandatoryCheck(iFormObj);
                if (e21 != null) return e21;
                break;
            case "DocMandatoryCheckSME":
                String e2 = getDocMandatoryCheckSME(iFormObj);
                if (e2 != null) return e2;
                break;
            case "setEmailIdsofUsers":
                setEmailIdsOfUsers(iFormObj);
                break;
            default:
                break;
        }
        return null;
    }

    private String getDocMandatoryCheckSME(IFormReference iFormObj) {
        try {
            LogMessages.statusLog.info("SME inside DocMandatoryCheck of SME of submit");
            String retXml = iFormObj.getValue("TXT_DocAttach").toString();
            LogMessages.statusLog.info("TXT_FileAttributes" + retXml);

            ArrayList<String> mandatoryDocList = new ArrayList<>();
            if (this.activityName.equalsIgnoreCase("RO") || this.activityName.equalsIgnoreCase("UnderWriter") || this.activityName.equalsIgnoreCase("Data Entry")) {
                mandatoryDocList.add("Scorecard");
            }
            String e2 = getWFXmlResponse(retXml, mandatoryDocList);
            if (e2 != null) return e2;
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception inside submit--" + e);
        }
        return null;
    }

    private String getDocMandatoryCheck(IFormReference iFormObj) {
        try {
            LogMessages.statusLog.info("inside DocMandatoryCheck of submit");
            String retXml = iFormObj.getValue("TXT_DocAttach").toString();
            LogMessages.statusLog.info("TXT_FileAttributes" + retXml);

            ArrayList<String> mandatoryDocList = new ArrayList<>();
            if (this.activityName.equalsIgnoreCase("RO")) {
                mandatoryDocList.add("Company Documents");
                mandatoryDocList.add("Collateral Documents");
                mandatoryDocList.add("CIC Report");
                mandatoryDocList.add("Citizenship Certificates");

                LogMessages.statusLog.info("RO mandate doc list--" + mandatoryDocList);
            } else if (this.activityName.equalsIgnoreCase(AUTHORITY_GROUP)) {
                String qAuthorityGrp = iFormObj.getValue("q_TargetAuthorityGroup").toString();
                if (qAuthorityGrp.equalsIgnoreCase("UnderWriter")) {
                    mandatoryDocList.add("CFR");
                }
            } else if (this.activityName.equalsIgnoreCase("CAS")) {
                mandatoryDocList.add("Security Documents");
            }

            String e2 = getWFXmlResponse(retXml, mandatoryDocList);
            if (e2 != null) return e2;
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception inside submit--" + e);
        }
        return null;
    }

    private String getWFXmlResponse(String retXml, ArrayList<String> mandatoryDocList) {
        LogMessages.statusLog.info("Final Mandate doc list--" + mandatoryDocList);
        WFXmlResponse wfxmlresponse = new WFXmlResponse(retXml);
        WFXmlList wfxmllist = wfxmlresponse.createList("Documents", "Document");
        wfxmllist.reInitialize(true);
        LogMessages.statusLog.info("wfxmllist---->" + wfxmllist);
        ArrayList<String> xmlDocList = new ArrayList<>();
        for (; wfxmllist.hasMoreElements(true); wfxmllist.skip(true)) {
            xmlDocList.add(wfxmllist.getVal("DocumentName"));
            LogMessages.statusLog.info("xmlDocList---->" + xmlDocList);
        }

        int currIndex = -1;
        for (int j = 0; j < mandatoryDocList.size(); j++) {
            String e2 = mandatoryDocList.get(j);
            int i1 = xmlDocList.indexOf(e2);
            LogMessages.statusLog.info("e2 mandatoryDocList element--" + e2);
            LogMessages.statusLog.info("i1: Index of e2 in xmlDocList--" + i1);
            if (i1 == -1) {
                LogMessages.statusLog.info("xmlDocList does not contain e2 element: " + e2);
                return "Please attached the mandatory document: " + e2;
            }
            if (i1 > currIndex) {
                currIndex = i1;
            }
        }
        LogMessages.statusLog.info("xmlDocList contains all elements of mandatoryDocList--");
        return null;
    }

    private String getStoredProcedureName(String eventType) {
        if (eventType.equalsIgnoreCase("genSMEApprovalSummaryTempIndividual")) {
            return LOS_SP_SME_INDIVIDUAL_TEMPLATE_FETCH_DATA;
        } else if (eventType.equalsIgnoreCase("genSMEApprovalSummaryTempInstitution")) {
            return LOS_SP_SME_INSTITUTION_TEMPLATE_FETCH_DATA;
        } else if (eventType.equalsIgnoreCase("genRetailApprovalSummaryTempIndividual")) {
            return LOS_SP_RETAILAS_INDIVIDUAL_TEMPLATE_FETCH_DATA;
        } else if (eventType.equalsIgnoreCase("genRetailApprovalSummaryTempInstitution")) {
            return LOS_SP_RETAILAS_INSTITUTION_TEMPLATE_FETCH_DATA;
        }
        return "";
    }

    private String generateTemplateDoc(IFormReference iFormObj, String stringData, String eventType) {
        String retVal = "";
        LogMessages.statusLogLoans.info("Inside generateTemplateDoc");
        List<String> paramList = new ArrayList<>();
        paramList.add("Text:" + stringData);
        LogMessages.statusLogLoans.info("---" + paramList.get(0) + "---- storedprocedure");
        iFormObj.getDataFromStoredProcedure(getStoredProcedureName(eventType), paramList);
        // Template Generation
        try {
            LogMessages.statusLogLoans.info("Try-catch " + eventType + " start");
            String cabinetName = iFormObj.getCabinetName();
            String serverIP = iFormObj.getServerIp();
            String serverPort = iFormObj.getServerPort();
            LogMessages.statusLogLoans.info("cabinetName =" + cabinetName + " serverIP =" + serverIP + " serverPort =" + serverPort);
            CommonMethods cm = new CommonMethods();
            retVal = cm.generateDocument(iFormObj, "SMEApprovalSummary_Institution_template", stringData, serverIP, serverPort, cabinetName);
            LogMessages.statusLogLoans.info("Try-catch " + eventType + " end");
        } catch (Exception e) {
            LogMessages.errorLogLoans.info("Exception found in " + eventType + " : ", e);
        }
        LogMessages.statusLogLoans.info("After generateDocument call....");
        return retVal;
    }

    private String getRetailDataFromCustomTable(IFormReference iFormObj, String stringData) {
//        String wiNumber = stringData.split("#", -1)[0];
//        String result = "";
//        LogMessages.statusLogLoans.info("**** RetailDataFromCustomTable  Starts for Workitem::" + wiNumber + " ****");
//        String selectQuery = "SELECT * from Ext_Retail WITH(NOLOCK) where caseID='" + wiNumber + "';";
//        LogMessages.statusLogLoans.info("Select query for check in remarks in Ext_Retail table::" + selectQuery);
//        List<List<String>> resultList = iFormObj.getDataFromDB(selectQuery);
//        LogMessages.statusLogLoans.info("resultList:" + resultList);
//        LogMessages.statusLogLoans.info("resultList size:" + resultList.size());
//        if (resultList.isEmpty()) {
//            result = "";
//        } else {
//            result = resultList.get(0).get(1) + "#" + resultList.get(0).get(2) + "#" + resultList.get(0).get(3) + "#" + resultList.get(0).get(4) + "#" + resultList.get(0).get(5) + "#" + resultList.get(0).get(6) + "#" + ((List) resultList.get(0)).get(7) + "#" + ((List) resultList.get(0)).get(8) + "#" + ((List) resultList.get(0)).get(9) + "#" + ((List) resultList.get(0)).get(10) + "#" + ((List) resultList.get(0)).get(11) + "#" + ((List) resultList.get(0)).get(12) + "#" + (String) ((List) resultList.get(0)).get(13) + "#" + ((List) resultList.get(0)).get(14) + "#" + ((List) resultList.get(0)).get(15) + "#" + ((List) resultList.get(0)).get(16) + "#" + ((List) resultList.get(0)).get(17) + "#" + resultList.get(0).get(18) + "#" + resultList.get(0).get(19);
//        }
        return "";
    }

    private String saveRetailDataIntoCustomTable(IFormReference iFormObj, String stringData) {
        String result;
//        String wiNumber = stringData.split("#", -1)[0];
//        LogMessages.statusLogLoans.info("**** saveRetailDataIntoCustomTable Starts for Workitem:: " + wiNumber + " ****");
//        //Ext_Retail
//        String selectQuery = "SELECT * from Ext_Retail WITH(NOLOCK) where caseID='" + wiNumber + "';";
//        LogMessages.statusLogLoans.info("Select query to check data in Ext_Retail table::" + selectQuery);
//        List<List<String>> resultList = iFormObj.getDataFromDB(selectQuery);
//        LogMessages.statusLogLoans.info("resultList:" + resultList);
//
//        if (resultList.isEmpty()) {
//            String insertQuery = "INSERT INTO Ext_Retail (caseID, FinancialsSubmittedRemarks, InfoFinancialRemarks, \n" + "ViabilityBusinessRemarks, IndustryStableRemarks, NabilCardHolderRemarks, IncomeNetworthRemarks, \n" + "GuarantorRepaymentRemarks, ApplicantBgRemarks, NonCoreIncomeRemarks, CautionListRemarks, \n" + "BankStmtAnalysedRemarks, SecurityRemarks, FacOtherBanksRemarks, FacNabilClassifyRemarks, \n" + "NRBChecklistRemarks, CICDescription, GeneralRemarks, SecurityVariationsRemarks, ConComVariationsRemarks) \n" + "VALUES ('" + stringData.split("#", -1)[0] + "', '" + stringData.split("#", -1)[1] + SPECIAL_STRING + "'" + stringData.split("#", -1)[2] + "', '" + stringData.split("#", -1)[3] + "', '" + stringData.split("#", -1)[4] + SPECIAL_STRING + "'" + stringData.split("#", -1)[5] + "', '" + stringData.split("#", -1)[6] + "', '" + stringData.split("#", -1)[7] + SPECIAL_STRING + "'" + stringData.split("#", -1)[8] + "', '" + stringData.split("#", -1)[9] + "', '" + stringData.split("#", -1)[10] + SPECIAL_STRING + "'" + stringData.split("#", -1)[11] + "', '" + stringData.split("#", -1)[12] + "', '" + stringData.split("#", -1)[13] + SPECIAL_STRING + "'" + stringData.split("#", -1)[14] + "', '" + stringData.split("#", -1)[15] + "', '" + stringData.split("#", -1)[16] + "', '" + stringData.split("#", -1)[17] + "', '" + stringData.split("#", -1)[18] + "', '" + stringData.split("#", -1)[19] + "');";
//
//            LogMessages.statusLogLoans.info("insert query in Ext_Retail table::" + insertQuery);
//            int res = iFormObj.saveDataInDB(insertQuery);
//            LogMessages.statusLogLoans.info("Savedataresponse:::" + res);
//            result = String.valueOf(res);
//        } else {
//            String updateQuery = "UPDATE Ext_Retail SET FinancialsSubmittedRemarks='" + stringData.split("#", -1)[1] + SPECIAL_STRING + "InfoFinancialRemarks='" + stringData.split("#", -1)[2] + "', ViabilityBusinessRemarks='" + stringData.split("#", -1)[3] + SPECIAL_STRING + "IndustryStableRemarks='" + stringData.split("#", -1)[4] + "', NabilCardHolderRemarks='" + stringData.split("#", -1)[5] + "',\n" + "IncomeNetworthRemarks='" + stringData.split("#", -1)[6] + "', GuarantorRepaymentRemarks='" + stringData.split("#", -1)[7] + "',\n" + "ApplicantBgRemarks='" + stringData.split("#", -1)[8] + "', NonCoreIncomeRemarks='" + stringData.split("#", -1)[9] + "',\n" + "CautionListRemarks='" + stringData.split("#", -1)[10] + "', BankStmtAnalysedRemarks='" + stringData.split("#", -1)[11] + "',\n" + "SecurityRemarks='" + stringData.split("#", -1)[12] + "', FacOtherBanksRemarks='" + stringData.split("#", -1)[13] + "',\n" + "FacNabilClassifyRemarks='" + stringData.split("#", -1)[14] + "', NRBChecklistRemarks='" + stringData.split("#", -1)[15] + "', CICDescription='" + stringData.split("#", -1)[16] + "', GeneralRemarks='" + stringData.split("#", -1)[17] + "', SecurityVariationsRemarks='" + stringData.split("#", -1)[18] + "', ConComVariationsRemarks='" + stringData.split("#", -1)[19] + "'\n" + "WHERE caseID='" + wiNumber + "';";
//
//            LogMessages.statusLogLoans.info("update query in Ext_Retail table::" + updateQuery);
//            int res = iFormObj.saveDataInDB(updateQuery);
//            LogMessages.statusLogLoans.info("Savedataresponse:::" + res);
//            result = String.valueOf(res);
//        }
        return "";
    }

    private String translateNepali(String stringData) {
        try {
            LogMessages.statusLog.info("inside translateEnglish");
            TranslateEnglish trnseng = new TranslateEnglish();
            String reqData = trnseng.translateNepaliReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "DateConverter");
            String res = trnseng.translateEnglishRes(getSplitRespData(response));
            LogMessages.statusLog.info("After translateNepali" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception TranslateNepali:" + e);
            return ERROR;
        }
    }

    private String translateEnglish(String stringData) {
        try {
            LogMessages.statusLog.info("inside translateEnglish");
            TranslateEnglish trnseng = new TranslateEnglish();
            String reqData = trnseng.translateEnglishReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "DateConverter");
            return trnseng.translateEnglishRes(getSplitRespData(response));
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception TranslateEnglish:" + e);
            return ERROR;
        }
    }

    private String getLimitNodeModify(String stringData) {
        try {
            LogMessages.statusLog.info("inside Loan Custom LimitNodeModify");
            LimitNodeModify lnmod = new LimitNodeModify();
            String reqData = lnmod.limitNodeModifyReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "LimitNodeModify");
            JSONArray res = lnmod.limitNodeModifyResponse(getSplitRespData(response));
            return String.valueOf(res);

        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitMaintainence : s" + e);
            return ERROR;
        }
    }

    private String getLimitNodeCreation(String stringData) {
        try {
            LogMessages.statusLog.info("inside limitMaintainence");
            LimitMaintenance retkycdet = new LimitMaintenance();
            String reqData = retkycdet.retLimitMaintenanceReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "LimitNodeCreation");
            JSONArray res = retkycdet.retLimitMaintenanceRes(getSplitRespData(response));
            LogMessages.statusLog.info("After limitMaintainence" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception limitMaintainence:" + e);
            return ERROR;
        }
    }

    private String getLoanAccountOpen(IFormReference iFormObj, String stringData) {
        try {
            LogMessages.statusLog.info("inside LoanAccountOpen");
            LoanAccountCreation lnacccr = new LoanAccountCreation();
            String reqData = lnacccr.loanAccountCreationReq(stringData, iFormObj);
            String response = apiConsumption.consumeAPI(reqData, "LoanAccountOpen");
            JSONArray res = lnacccr.loanAccountCreationRes(getSplitRespData(response));
            LogMessages.statusLog.info("Loan Account Creation" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LoanAccountOpen :" + e);
            return ERROR;
        }
    }

    private String getLoanDisbursement(String stringData) {
        try {
            LogMessages.statusLog.info("inside LoanDisbursement");
            LoanDisbursement lndisbrs = new LoanDisbursement();
            String reqData = lndisbrs.loanDisbursementReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "LoanDisbursement");
            JSONArray res = lndisbrs.loanDisbursementRes(getSplitRespData(response));
            LogMessages.statusLog.info("Loan disbursement" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LoanDisbursement:" + e);
            return ERROR;
        }
    }

    private String getCCALoanAccountOpening(String stringData) {
        try {
            LogMessages.statusLog.info("inside cCALoanAccountOpening");
            CCALoanAccountCreation cca = new CCALoanAccountCreation();
            String reqData = cca.loanAccountCreationReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "CCAAccountOpen");
            JSONArray res = cca.loanAccountCreationRes(getSplitRespData(response));
            LogMessages.statusLog.info("cCALoanAccountOpening" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception cCALoanAccountOpening :" + e);
            return ERROR;
        }
    }

    private String getThirdPartyTransfer(String stringData) {
        try {
            LogMessages.statusLog.info("inside ThirdPartyTransfer");
            ThirdPartyTransfer tpt = new ThirdPartyTransfer();
            String reqData = tpt.thirdPartyTransferReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "BalanceTransfer");
            JSONArray res = tpt.thirdPartyTransferRes(getSplitRespData(response));
            LogMessages.statusLog.info("ThirdPartyTransfer" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception ThirdPartyTransfer:" + e);
            return ERROR;
        }
    }

    private String getNonLAAloanDisbursement(String stringData) {
        try {
            LogMessages.statusLog.info("inside nonLAAloanDisbursement");
            NonLAALoanDisbursement nonlndisbrs = new NonLAALoanDisbursement();
            String reqData = nonlndisbrs.nonLoanDisbursementReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "BalanceTransfer");
            JSONArray res = nonlndisbrs.nonLoanDisbursementRes(getSplitRespData(response));
            LogMessages.statusLog.info("nonLAAloanDisbursement" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception nonLAAloanDisbursement:" + e);
            return ERROR;
        }
    }

    private String getSanctionLimitDetails(String stringData) {
        try {
            LogMessages.statusLog.info("inside Sanction Limit");
            SanctionLimitDetails sanctionLimit = new SanctionLimitDetails();
            String reqData = sanctionLimit.sanctionLimitDetailsReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "SanctionLimitDetailsFromCustId");
            JSONArray res = sanctionLimit.sanctionLimitDetailsRes(getSplitRespData(response));
            LogMessages.statusLog.info("After Sanction Limit: " + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception Sanction Limit: " + e);
            return ERROR;
        }
    }

    private String getLoanServicingDetails(String stringData) {
        try {
            LogMessages.statusLog.info("inside LoanServicingDetails");
            LoanServicingDetails loanServicing = new LoanServicingDetails();
            String reqData = loanServicing.loanServicingReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "LoanServicingDetails");
            JSONArray res = loanServicing.loanDetails(getSplitRespData(response));
            LogMessages.statusLog.info("After LoanServicingDetails" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LoanServicingDetails:" + e);
            return ERROR;
        }
    }

    private String getRetKYCDetails(String stringData) {
        try {
            LogMessages.statusLog.info("inside retKYCDetails");
            RetKYCDetails retkycdet = new RetKYCDetails();
            String reqData = retkycdet.retKYCDetailsReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "CustKycInfo");
            JSONArray res = retkycdet.retKYCDetailsRes2(getSplitRespData(response));
            LogMessages.statusLog.info("After retKYCDetails" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception retKYCDetails:" + e);
            return ERROR;
        }
    }
    
    
    private String getRetKYCDetailsCorp(String stringData) {
        try {
            LogMessages.statusLog.info("inside retKYCDetails");
            RetKYCDetails retkycdet = new RetKYCDetails();
            String reqData = retkycdet.retKYCDetailsReqCorp(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "CustKycInfo");
            JSONArray res = retkycdet.retKYCDetailsResCorp(getSplitRespData(response));
            LogMessages.statusLog.info("After retKYCDetails" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception retKYCDetails:" + e);
            return ERROR;
        }
    }
    private String getAccountDetailsInLoanAcctCreation(String stringData) {
        try {
            LogMessages.statusLog.info("inside AccountInquiry");
            AccountEnquiry accountEnquiry = new AccountEnquiry();
            String reqDataEnq = accountEnquiry.accountEnquiryReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqDataEnq, "AccountValidation");
            return accountEnquiry.accountEnquiryRes(getSplitRespData(response));
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception inside AccountInquiry:" + e);
            return ERROR;
        }
    }

    private String getOfferingSheetGeneration(IFormReference iFormObj, String stringData) {
        try {
            LogMessages.statusLog.info("inside OfferingSheetGeneration");
            OfferingSheetDetails lmtdet = new OfferingSheetDetails();
            String reqData = lmtdet.offeringSheetRequest(getSplitReqData(stringData), iFormObj);
            String response = apiConsumption.consumeAPI(reqData, "OfferingSheetGeneration");
            JSONArray res = lmtdet.offeringSheetResponse(getSplitRespData(response), iFormObj);
            LogMessages.statusLog.info("After OfferingSheetGeneration" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception OfferingSheetGeneration:" + e);
            return ERROR;
        }
    }

    private String getLimitTreeDetails(IFormReference iFormObj, String stringData) {
        try {
            LogMessages.statusLog.info("inside LimitTreeDetails");
            LimitTreeDetails lmtdet = new LimitTreeDetails();
            String reqData = lmtdet.limitTreeRequest(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "LimitTreeDetails");
            JSONArray res = lmtdet.limitTreeResponse(getSplitRespData(response), iFormObj);
            LogMessages.statusLog.info("After LimitTreeDetails" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception LimitTreeDetails:" + e);
            return ERROR;
        }
    }

    private String getCCALoanAccountModify(String stringData) {
        try {
            LogMessages.statusLog.info("inside cCALoanAccountModify");
            CCAAccountModify retkycdet = new CCAAccountModify();
            String reqData = retkycdet.cCAAccountModifyReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "CAAAccountModify");
            JSONArray res = retkycdet.cCAAccountModifyRes(getSplitRespData(response));
            LogMessages.statusLog.info("After cCALoanAccountModify" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception cCALoanAccountModify:" + e);
            return ERROR;
        }
    }

    private String getSanctionLimitMod(String stringData) {
        try {
            LogMessages.statusLog.info("inside sanctionLimitMod");
            SanctionLimitMod retkycdet = new SanctionLimitMod();
            String reqData = retkycdet.sanctionLimitModReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "SanctionLimitExtend");
            JSONArray res = retkycdet.sanctionLimitModRes(getSplitRespData(response));
            LogMessages.statusLog.info("After sanctionLimitMod" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception sanctionLimitMod:" + e);
            return ERROR;
        }
    }

    private String intTableMaintain(String stringData) {
        try {
            LogMessages.statusLog.info("inside intTableMaintain");
            InterestMaintenance retKycDet = new InterestMaintenance();
            String reqData = retKycDet.interestMaintenanceReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "HINTTM");
            JSONArray res = retKycDet.interestMaintenanceRes(getSplitRespData(response));
            LogMessages.statusLog.info("After intTableMaintain" + res);
            return String.valueOf(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception intTableMaintain:" + e);
            return ERROR;
        }
    }

    private String performAccountTransfer(String stringData) {
        try {
            LogMessages.statusLog.info("inside accountTransfer");
            String[] data = stringData.split("#@#");
            BalanceEnquiry blncenq = new BalanceEnquiry();
            String reqDatabenq = blncenq.balanceEnquiryReq(getSplitReqData(data[1]));
            String responseEnq = apiConsumption.consumeAPI(reqDatabenq, "AccountInfo");
            String resbenq = blncenq.balanceEnquiryRes(getSplitRespData(responseEnq));
            String[] reqAmount = getSplitReqData(data[2]);
            LogMessages.statusLog.info("balanceEnquiryRes BODY: SUCCESS" + Float.parseFloat(resbenq) + "###" + reqAmount[2]);
            if (Double.parseDouble(reqAmount[2]) <= Double.parseDouble(resbenq)) {
                AccountTransfer acctrf = new AccountTransfer();
                String reqData = acctrf.accountTransferReq(getSplitReqData(data[2]));
                LogMessages.statusLog.info(ACCTTRANS_BODY_SUCCESS + reqData);
                String response = apiConsumption.consumeAPI(reqData, "AccountTransfer");
                LogMessages.statusLog.info(ACCTTRANS_BODY_SUCCESS + response);

                JSONArray res = acctrf.accountTransferRes(getSplitRespData(response));
                LogMessages.statusLog.info(ACCTTRANS_BODY_SUCCESS + res);

                LogMessages.statusLog.info("After accountTransfer" + res);
                return String.valueOf(res);
            } else {
                return FAILURE;
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception retKYCDetails:" + e);
            return ERROR;
        }
    }
    private String loanDemandSatisfaction(String stringData) {
        try{
            LoanDemandSatisfaction lds = new LoanDemandSatisfaction();
            String reqData = lds.LoanDemandSatisfactionReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "LoanDemandSatisfactionProcess");
            LogMessages.statusLog.info("Exception response:" + response);
            JSONArray res = lds.LoanDemandSatisfactionRes(getSplitRespData(response));
            LogMessages.statusLog.info("JSON loanDemandSatisfaction:" + res);
            return res.toString();
        }catch (Exception e) {
          return ERROR;  
        }
    }
     private String loanOverdueCollection(String stringData) {
        try{
            LoanOverdueCollection loc = new LoanOverdueCollection();
            String reqData = loc.LoanOverdueCollectionReq(getSplitReqData(stringData));
            String response = apiConsumption.consumeAPI(reqData, "LoanOverdueCollection");
            LogMessages.statusLog.info("Exception response:" + response);
            JSONArray res = loc.LoanOverdueCollectionRes(getSplitRespData(response));
            LogMessages.statusLog.info("JSON LoanOverdueCollection:" + res);
            return res.toString();
        }catch (Exception e) {
          return ERROR;  
        }
    }

    private void setEmailIdsOfUsers(IFormReference iFormObj) {
        try {
            LogMessages.statusLog.info("inside setEmailIdsOfUsers of submit");
            String userDecision = getUserDecision(iFormObj);
            LogMessages.statusLog.info("Now setEmailIdsofCAS and CLAD groups**** of submit()");

            String lastAuthorityGroup = iFormObj.getValue("LastAuthorityGroup").toString();
            LogMessages.statusLog.info("----activityName--" + this.activityName + " and lastAuthorityGroup--" + lastAuthorityGroup);

            LogMessages.statusLog.info(USER_DECISION + userDecision);
            if (this.activityName.equalsIgnoreCase(AUTHORITY_GROUP) && lastAuthorityGroup.equalsIgnoreCase("Yes")
                    && (!userDecision.equalsIgnoreCase("Return")) && (!userDecision.equalsIgnoreCase("Reject")) && (!userDecision.equalsIgnoreCase("Query"))) {
                LogMessages.statusLog.info("Setting EmailIdsofCAS and CLAD groups**** starts@!");
                String casGroupsMailIdquery = "select UserIndex,UserName,MailId from PDBUser WITH(NOLOCK) where UserIndex in (select UserIndex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='CAS Group'))";

                LogMessages.statusLog.info("query of casGroupsMailIdquery--" + casGroupsMailIdquery);
                String cladGroupsMailIdquery = "select UserIndex,UserName,MailId from PDBUser WITH(NOLOCK) where UserIndex in (select UserIndex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='CLAD Group'))";
                LogMessages.statusLog.info("query of cladGroupsMailIdquery--" + cladGroupsMailIdquery);

                List<List<String>> casGroupsMailIdList = iFormObj.getDataFromDB(casGroupsMailIdquery);

                LogMessages.statusLog.info("casGroupsMailIdList--" + casGroupsMailIdList);
                if (!casGroupsMailIdList.isEmpty()) {

                    LogMessages.statusLog.info("casGroupsMailIdList size()--" + casGroupsMailIdList.size());

                    StringBuilder groupIDsofCAS = new StringBuilder();

                    for (int i = 0; i < casGroupsMailIdList.size(); i++) {
                        LogMessages.statusLog.info("**CAS " + i + "th row mail id--" + casGroupsMailIdList.get(i));
                        groupIDsofCAS.append(casGroupsMailIdList.get(i)).append(";");

                    }
                    LogMessages.statusLog.info("Final groupIDsofCAS**-" + groupIDsofCAS);
                    iFormObj.setValue("groupMailIDsofCAS", groupIDsofCAS.toString());
                    LogMessages.statusLog.info("Form value of groupMailIDsofCAS set---**-" + iFormObj.getValue("groupMailIDsofCAS"));
                }

                List<List<String>> cladGroupsMailIdList = iFormObj.getDataFromDB(cladGroupsMailIdquery);

                LogMessages.statusLog.info("cladGroupsMailIdList--" + cladGroupsMailIdList);
                if (!cladGroupsMailIdList.isEmpty()) {
                    LogMessages.statusLog.info("cladGroupsMailIdList size()--" + cladGroupsMailIdList.size());

                    StringBuilder groupIDsofCLAD = new StringBuilder("");

                    for (int i = 0; i < cladGroupsMailIdList.size(); i++) {
                        LogMessages.statusLog.info("**CLAD " + i + "th row mail id--" + cladGroupsMailIdList.get(i));
                        groupIDsofCLAD.append(cladGroupsMailIdList.get(i)).append(";");
                    }

                    LogMessages.statusLog.info("Final groupIDsofCLAD**-" + groupIDsofCLAD.toString());
                    iFormObj.setValue("groupMailIDsofCLAD", groupIDsofCLAD.toString());
                    LogMessages.statusLog.info("Form value of groupMailIDsofCLAD set---**-" + iFormObj.getValue("groupMailIDsofCLAD"));
                }

                LogMessages.statusLog.info("Setting EmailIdsofCAS and CLAD groups**** ends@!");

            }

            LogMessages.statusLog.info("End of setEmailIdsofUsers of submit()");
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception inside setEmailIdsofUsers of submit--" + e);
        }
    }

    private String getUserDecision(IFormReference iFormObj) {
        String userDecision = iFormObj.getValue("UserDecision").toString();
        LogMessages.statusLog.info(USER_DECISION + userDecision);
        String userDomainID = iFormObj.getValue("TargetAssignedUser").toString();
        LogMessages.statusLog.info("userDomainID--" + userDomainID);
        String userAuthorityGrp = iFormObj.getValue("TargetAuthorityGroup").toString();
        LogMessages.statusLog.info("userAuthorityGrp--" + userAuthorityGrp);
        String mailIdFetchQuery = "select top 1 Email from LOS_UserAuthorityGroupMapping WITH(NOLOCK) where Domanin_Name='" + userDomainID + "' and Authroity_GroupName='" + userAuthorityGrp + "'";
        LogMessages.statusLog.info("mailIdFetchQuery--" + mailIdFetchQuery);

        List<List<String>> mailIdFetchList = iFormObj.getDataFromDB(mailIdFetchQuery);

        LogMessages.statusLog.info("mailIdFetchList--" + mailIdFetchList);
        if (!mailIdFetchList.isEmpty()) {
            if (this.activityName.equalsIgnoreCase(AUTHORITY_GROUP)) {
                LogMessages.statusLog.info("----AuthorityGroup-------");
                if (userDecision.equalsIgnoreCase("Query")) {
                    iFormObj.setValue("Reviewer2", mailIdFetchList.get(0).get(0));
                    LogMessages.statusLog.info("****Query user mail id set : Reviewer2--*" + mailIdFetchList.get(0));
                } else if ((!userDecision.equalsIgnoreCase("Return")) && (!userDecision.equalsIgnoreCase("Reject"))) {
                    iFormObj.setValue("Reviewer3", mailIdFetchList.get(0).get(0));
                    LogMessages.statusLog.info("****AuthorityGrp mail id set : Reviewer3--*" + mailIdFetchList.get(0));
                }
            } else if (this.activityName.equalsIgnoreCase("RO")) {
                LogMessages.statusLog.info("----RO-------");
                iFormObj.setValue("Reviewer3", mailIdFetchList.get(0).get(0));
                LogMessages.statusLog.info("****AuthorityGrp mail id set : Reviewer3--*" + mailIdFetchList.get(0));
            }
        } else {
            LogMessages.statusLog.info("mailIdFetchList is empty--" + mailIdFetchList);
        }
        return userDecision;
    }

    @Override
    public boolean picklistPreHook(String controlId, IFormReference iformObj) {
        return pickListPreHookExtracted(controlId, iformObj);
    }

    @Override
    public void picklistSearchHook(String controlId, IFormReference iFormObj, String searchString, int colIndex) {
        pickListSearchHookExtracted(controlId, iFormObj, searchString, colIndex);
    }

    

}
