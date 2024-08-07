package com.newgen.iforms.user;

/**
 *

 *
 *
 */
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

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ListIterator;


import com.newgen.common.APIConsumption;
import com.newgen.common.BranchDetails;
import com.newgen.common.ConfProperty;
import com.newgen.common.LogMessages;
import com.newgen.iforms.EControl;
import com.newgen.integrations.CCAAccountModify;
import com.newgen.integrations.CCALoanAccountCreation;
import com.newgen.integrations.CSVRDetail;
import com.newgen.integrations.InterestMaintainence;
import com.newgen.integrations.LimitMaintainence;
import com.newgen.integrations.LimitNodeModify;
import com.newgen.integrations.LimitTreeDetails;
import com.newgen.integrations.LoanAccountCreation;
import com.newgen.integrations.LoanDisbursement;
import com.newgen.integrations.LoanServicingDetails;
import com.newgen.integrations.NonLAALoanDisbursement;
import com.newgen.integrations.OfferingSheetDetails;
import com.newgen.integrations.TranslateEnglish;
import com.newgen.integrations.RetailAllDataFetch;
import com.newgen.integrations.SMEAllDataFetch;
import com.newgen.integrations.SanctionLimitDetails;
import com.newgen.integrations.SanctionLimitMod;
import com.newgen.integrations.ThirdPartyTransfer;
import com.newgen.integrations.AccountEnquiry;
import com.newgen.mvcbeans.model.WorkdeskModel;
import com.newgen.mvcbeans.model.wfobjects.WDGeneralData;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Loans_Custom extends IFormCustomHooks implements IFormServerEventHandler {

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
        System.out.println("Inside executeServerEvent");
        try {
            LogMessages.statusLog.info("controlName is: " + controlName);
            LogMessages.statusLog.info("eventType is: " + eventType);
           // LogMessages.statusLog.info("stringData is: " + stringData);
            LogMessages.statusLog.info("IFormReference is: " + iFormObj);

            switch (controlName) {
                case "click":
                    if (eventType.equalsIgnoreCase("getBranchDetails")) {
                        LogMessages.statusLog.info("getBranchDetails");
                        String res = bd.getBranchDetails(stringData, iFormObj);
                        LogMessages.statusLog.info("res::" + res);
                        return res;
                    } else if (eventType.equalsIgnoreCase("intTableMaintain")) {
                        try {
                            LogMessages.statusLog.info("inside intTableMaintain");
                            InterestMaintainence retkycdet = new InterestMaintainence();
                            String reqdata = retkycdet.interestMaintainenceReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "HINTTM");
                            JSONArray res = retkycdet.interestMaintainenceRes(response);
                            LogMessages.statusLog.info("After intTableMaintain" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception intTableMaintain:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("sanctionLimitMod")) {
                        try {
                            LogMessages.statusLog.info("inside sanctionLimitMod");
                            SanctionLimitMod retkycdet = new SanctionLimitMod();
                            String reqdata = retkycdet.sanctionLimitModReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "SanctionLimitExtend");
                            JSONArray res = retkycdet.sanctionLimitModRes(response);
                            LogMessages.statusLog.info("After sanctionLimitMod" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception sanctionLimitMod:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("cCALoanAccountModify")) {
                        try {
                            LogMessages.statusLog.info("inside cCALoanAccountModify");
                            CCAAccountModify retkycdet = new CCAAccountModify();
                            String reqdata = retkycdet.cCAAccountModifyReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "CAAAccountModify");
                            JSONArray res = retkycdet.cCAAccountModifyRes(response);
                            LogMessages.statusLog.info("After cCALoanAccountModify" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception cCALoanAccountModify:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("Fetch_All_Retail_Review_Renew_Data")) {
                        LogMessages.statusLog.info("Fetch_All_Retail_Review_Renew_Data");
                        JSONArray data = RetailAllDataFetch.retailAllDataFetch(iFormObj);
                        return String.valueOf(data);
                    } else if (eventType.equalsIgnoreCase("Fetch_All_SME_Review_Renew_Data")) {
                        LogMessages.statusLog.info("Fetch_All_SME_Review_Renew_Data");
                        JSONArray data = SMEAllDataFetch.smeAllDataFetch(iFormObj);
                        LogMessages.statusLog.info("Krishna, Loans_Custom: "+data);
                        return String.valueOf(data);
                    } else if (eventType.equalsIgnoreCase("getCSVRDetail")) {
                        LogMessages.statusLog.info("inside getCSRV Detail");
                        CSVRDetail csvrd = new CSVRDetail();
                        String res = csvrd.getData(csvrd.getSPToken(), stringData);
                        LogMessages.statusLog.info("inside gridfetch1");
                        return res;

                    } else if (eventType.equalsIgnoreCase("LimitTreeDetails")) {
                        try {
                            LogMessages.statusLog.info("inside LimitTreeDetails");
                            LimitTreeDetails lmtdet = new LimitTreeDetails();
                            String reqdata = lmtdet.limitTreeRequest(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "LimitTreeDetails");
                            JSONArray res = lmtdet.limitTreeResponse(response, iFormObj);
                            LogMessages.statusLog.info("After LimitTreeDetails" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception LimitTreeDetails:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("OfferingSheetGeneration")) {
                        try {
                            LogMessages.statusLog.info("inside OfferingSheetGeneration");
                            OfferingSheetDetails lmtdet = new OfferingSheetDetails();
                            String reqdata = lmtdet.offeringSheetRequest(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "OfferingSheetGeneration");
                            JSONArray res = lmtdet.offeringSheetResponse(response, iFormObj);
                            LogMessages.statusLog.info("After OfferingSheetGeneration" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception OfferingSheetGeneration:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("gridfetch")) {
                        LogMessages.statusLog.info("inside gridfetch");
                        GridFetch grid = new GridFetch();
                        String res = grid.gridtogridfetch(stringData, iFormObj);
                        LogMessages.statusLog.info("inside gridfetch1");
                        return res;
                    }else if (eventType.equalsIgnoreCase("GetAccountDetailsInLoanAcctCreation")) {
                        try {
                            LogMessages.statusLog.info("inside AccountInquiry");
                            //String[] data = stringData.split("#@#");
                            AccountEnquiry accenq = new AccountEnquiry();
                            String reqdataaenq = accenq.accountEnquiryReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String responseaenq = apic.consumeAPI(reqdataaenq, "AccountValidation");
                            String resaenq = accenq.accountEnquiryRes(responseaenq);
                            LogMessages.statusLog.info("After AccountInquiry" + resaenq);
                            return resaenq;
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception inside AccountInquiry:" + e);
                            return "ERROR";
                        }

                    }
                     else if (eventType.equalsIgnoreCase("retKYCDetails")) {
                        try {
                            LogMessages.statusLog.info("inside retKYCDetails");
                            RetKYCDetails retkycdet = new RetKYCDetails();
                            String reqdata = retkycdet.retKYCDetailsReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "CustKycInfo");
                            JSONArray res = retkycdet.retKYCDetailsRes2(response);
                            LogMessages.statusLog.info("After retKYCDetails" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception retKYCDetails:" + e);
                            return "ERROR";
                        }
                    } //KRISHNA API
                    else if (eventType.equalsIgnoreCase("LoanServicingDetails")) {
                        try {
                            LogMessages.statusLog.info("inside LoanServicingDetails");
                            LoanServicingDetails loanServicing = new LoanServicingDetails();
                            String reqdata = loanServicing.loanServicingReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "LoanServicingDetails");
                            JSONArray res = loanServicing.loanDetails(response);
                            LogMessages.statusLog.info("After LoanServicingDetails" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception LoanServicingDetails:" + e);
                            return "ERROR";
                        }

                    } //KRISHNA API
                    else if (eventType.equalsIgnoreCase("SanctionLimitDetails")) {
                        try {
                            LogMessages.statusLog.info("inside Sanction Limit");
                            SanctionLimitDetails sanctionLimit = new SanctionLimitDetails();
                            String reqdata = sanctionLimit.sanctionLimitDetailsReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "SanctionLimitDetailsFromCustId");
                            JSONArray res = sanctionLimit.sanctionLimitDetailsRes(response);
                            LogMessages.statusLog.info("After Sanction Limit: " + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception Sanction Limit: " + e);
                            return "ERROR";
                        }

                    } else if (eventType.equalsIgnoreCase("nonLAAloanDisbursement")) {
                        try {
                            LogMessages.statusLog.info("inside nonLAAloanDisbursement");
                            NonLAALoanDisbursement nonlndisbrs = new NonLAALoanDisbursement();
                            String reqdata = nonlndisbrs.nonloanDisbursementReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "BalanceTransfer");
                            JSONArray res = nonlndisbrs.nonloanDisbursementRes(response);
                            LogMessages.statusLog.info("nonLAAloanDisbursement" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception nonLAAloanDisbursement:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("ThirdPartyTransfer")) {
                        try {
                            LogMessages.statusLog.info("inside ThirdPartyTransfer");
                            ThirdPartyTransfer tpt = new ThirdPartyTransfer();
                            String reqdata = tpt.thirdpartytransferReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "BalanceTransfer");
                            JSONArray res = tpt.thirdpartytransferRes(response);
                            LogMessages.statusLog.info("ThirdPartyTransfer" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception ThirdPartyTransfer:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("cCALoanAccountOpening")) {
                        try {
                            LogMessages.statusLog.info("inside cCALoanAccountOpening");
                            CCALoanAccountCreation cca = new CCALoanAccountCreation();
                            String reqdata = cca.loanAccountCreationReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "CCAAccountOpen");
                            JSONArray res = cca.loanAccountCreationRes(response);
                            LogMessages.statusLog.info("cCALoanAccountOpening" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception cCALoanAccountOpening :" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("LoanDisbursement")) {
                        try {
                            LogMessages.statusLog.info("inside LoanDisbursement");
                            LoanDisbursement lndisbrs = new LoanDisbursement();
                            String reqdata = lndisbrs.loanDisbursementReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "LoanDisbursement");
                            JSONArray res = lndisbrs.loanDisbursementRes(response);
                            LogMessages.statusLog.info("Loan disbursement" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception LoanDisbursement:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("LoanAccountOpen")) {
                        try {
                            LogMessages.statusLog.info("inside LoanAccountOpen");
                            LoanAccountCreation lnacccr = new LoanAccountCreation();
                            String reqdata = lnacccr.loanAccountCreationReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "LoanAccountOpen");
                            JSONArray res = lnacccr.loanAccountCreationRes(response);
                            LogMessages.statusLog.info("Loan Account Creation" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception LoanAccountOpen :" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("LimitNodeCreation")) {
                        try {
                            LogMessages.statusLog.info("inside limitMaintainence");
                            LimitMaintainence retkycdet = new LimitMaintainence();
                            String reqdata = retkycdet.retLimitMaintainenceReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "LimitNodeCreation");
                            JSONArray res = retkycdet.retLimitMaintainenceRes(response);
                            LogMessages.statusLog.info("After limitMaintainence" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception limitMaintainence:" + e);
                            return "ERROR";
                        }
                    } 
                    //Limit Node Modify Added by Prajit.
                    else if (eventType.equalsIgnoreCase("LimitNodeModify")) {

                        try {
                            LogMessages.statusLog.info("inside Loan Custom LimitNodeModify");
                            LimitNodeModify lnmod = new LimitNodeModify();
                            String reqdata = lnmod.LimitNodeModifyReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "LimitNodeModify");

                            JSONArray res = lnmod.LimitNodeModifyResponse(response);
                            LogMessages.statusLog.info("After Modofy limitNode == " + res);
                            return String.valueOf(res);

                        } catch (Exception e) {
                             LogMessages.statusLog.info("Exception limitMaintainence : s" + e);
                            return "ERROR";
                        }

                    }
                    else if (eventType.equalsIgnoreCase("translateEnglish")) {
                        try {
                            LogMessages.statusLog.info("inside translateEnglish");
                            TranslateEnglish trnseng = new TranslateEnglish();
                            String reqdata = trnseng.translateEnglishReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "DateConverter");
                            String res = trnseng.translateEnglishRes(response);
                            LogMessages.statusLog.info("After translateEnglish" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception TranslateEnglish:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("translateNepali")) {
                        try {
                            LogMessages.statusLog.info("inside translateEnglish");
                            TranslateEnglish trnseng = new TranslateEnglish();
                            String reqdata = trnseng.translateNepaliReq(stringData, iFormObj);
                            APIConsumption apic = new APIConsumption();
                            String response = apic.consumeAPI(reqdata, "DateConverter");
                            String res = trnseng.translateEnglishRes(response);
                            LogMessages.statusLog.info("After translateNepali" + res);
                            return String.valueOf(res);
                        } catch (Exception e) {
                            LogMessages.statusLog.info("Exception TranslateNepali:" + e);
                            return "ERROR";
                        }
                    } else if (eventType.equalsIgnoreCase("accountTransfer")) {
          try {
            LogMessages.statusLog.info("inside accountTransfer");
            String[] data = stringData.split("#@#");
            BalanceEnquiry blncenq = new BalanceEnquiry();
            String reqdatabenq = blncenq.balanceEnquiryReq(data[1], iFormObj);
            APIConsumption apic = new APIConsumption();
            String responsebenq = apic.consumeAPI(reqdatabenq, "AccountInfo");
            String resbenq = blncenq.balanceEnquiryRes(responsebenq);
            String reqamount[] = data[2].split("#");
                    LogMessages.statusLog.info("balanceEnquiryRes BODY: SUCCESS"+ Float.parseFloat(resbenq) +"###"+reqamount[2]);
            if (Double.parseDouble(reqamount[2]) < Double.parseDouble(resbenq)) {
              AccountTransfer acctrf = new AccountTransfer();
              String reqdata = acctrf.accountTransferReq(data[2], iFormObj);
                LogMessages.statusLog.info("accttrans BODY: SUCCESS ##"+ reqdata);
              String response = apic.consumeAPI(reqdata, "AccountTransfer");
                              LogMessages.statusLog.info("accttrans BODY: SUCCESS ##"+ response);

              JSONArray res = acctrf.accountTransferRes(response);
                              LogMessages.statusLog.info("accttrans BODY: SUCCESS ##"+ res);

              LogMessages.statusLog.info("After accountTransfer" + res);
              return String.valueOf(res);
            } else {
              return "FAILURE";
            }
          } catch (Exception e) {
            LogMessages.statusLog.info("Exception retKYCDetails:" + e);
            return "ERROR";
          }
        }
        else if (eventType.equalsIgnoreCase("accountTransfer")) {
                                try {
                                  LogMessages.statusLog.info("inside accountTransfer");
                                  String[] data = stringData.split("#@#");
                                  BalanceEnquiry blncenq = new BalanceEnquiry();
                                  String reqdatabenq = blncenq.balanceEnquiryReq(data[1], iFormObj);
                                  APIConsumption apic = new APIConsumption();
                                  String responsebenq = apic.consumeAPI(reqdatabenq, "AccountInfo");
                                  String resbenq = blncenq.balanceEnquiryRes(responsebenq);
                                  String reqamount[] = data[2].split("#");
                                          LogMessages.statusLog.info("balanceEnquiryRes BODY: SUCCESS"+ Float.parseFloat(resbenq) +"###"+reqamount[2]);
                                  if (Double.parseDouble(reqamount[2]) < Double.parseDouble(resbenq)) {
                                    AccountTransfer acctrf = new AccountTransfer();
                                    String reqdata = acctrf.accountTransferReq(data[2], iFormObj);
                                      LogMessages.statusLog.info("accttrans BODY: SUCCESS ##"+ reqdata);
                                    String response = apic.consumeAPI(reqdata, "AccountTransfer");
                                                    LogMessages.statusLog.info("accttrans BODY: SUCCESS ##"+ response);

                                    JSONArray res = acctrf.accountTransferRes(response);
                                                    LogMessages.statusLog.info("accttrans BODY: SUCCESS ##"+ res);

                                    LogMessages.statusLog.info("After accountTransfer" + res);
                                    return String.valueOf(res);
                                  } else {
                                    return "FAILURE";
                                  }
                                } catch (Exception e) {
                                  LogMessages.statusLog.info("Exception retKYCDetails:" + e);
                                  return "ERROR";
                                }
                    }else if (eventType.equalsIgnoreCase("saveRetailDataIntoCustomTable")) {
                        String result = "";
                        String WINumber = stringData.split("#", -1)[0];
                        LogMessages.statusLogLoans.info("**** saveRetailDataIntoCustomTable Starts for Workitem:: " + WINumber + " ****");
                        //Ext_Retail
                        String selectQuery = "SELECT * from Ext_Retail WITH(NOLOCK) where caseID='" + WINumber + "';";
                        LogMessages.statusLogLoans.info("Select query to check data in Ext_Retail table::" + selectQuery);
                        List resultList = iFormObj.getDataFromDB(selectQuery);
                        LogMessages.statusLogLoans.info("resultList:" + resultList);

                        if (resultList.isEmpty()) {
                            String insertQuery = "INSERT INTO Ext_Retail (caseID, FinancialsSubmittedRemarks, InfoFinancialRemarks, \n" + "ViabilityBusinessRemarks, IndustryStableRemarks, NabilCardHolderRemarks, IncomeNetworthRemarks, \n" + "GuarantorRepaymentRemarks, ApplicantBgRemarks, NonCoreIncomeRemarks, CautionListRemarks, \n" + "BankStmtAnalysedRemarks, SecurityRemarks, FacOtherBanksRemarks, FacNabilClassifyRemarks, \n" + "NRBChecklistRemarks, CICDescription, GeneralRemarks, SecurityVariationsRemarks, ConComVariationsRemarks) \n" + "VALUES ('" + stringData.split("#", -1)[0] + "', '" + stringData.split("#", -1)[1] + "', \n" + "'" + stringData.split("#", -1)[2] + "', '" + stringData.split("#", -1)[3] + "', '" + stringData.split("#", -1)[4] + "', \n" + "'" + stringData.split("#", -1)[5] + "', '" + stringData.split("#", -1)[6] + "', '" + stringData.split("#", -1)[7] + "', \n" + "'" + stringData.split("#", -1)[8] + "', '" + stringData.split("#", -1)[9] + "', '" + stringData.split("#", -1)[10] + "', \n" + "'" + stringData.split("#", -1)[11] + "', '" + stringData.split("#", -1)[12] + "', '" + stringData.split("#", -1)[13] + "', \n" + "'" + stringData.split("#", -1)[14] + "', '" + stringData.split("#", -1)[15] + "', '" + stringData.split("#", -1)[16] + "', '" + stringData.split("#", -1)[17] + "', '" + stringData.split("#", -1)[18] + "', '" + stringData.split("#", -1)[19] + "');";

                            LogMessages.statusLogLoans.info("insert query in Ext_Retail table::" + insertQuery);
                            int res = iFormObj.saveDataInDB(insertQuery);
                            LogMessages.statusLogLoans.info("Savedataresponse:::" + res);
                            result = String.valueOf(res);
                        } else {
                            String updateQuery = "UPDATE Ext_Retail SET FinancialsSubmittedRemarks='" + stringData.split("#", -1)[1] + "', \n" + "InfoFinancialRemarks='" + stringData.split("#", -1)[2] + "', ViabilityBusinessRemarks='" + stringData.split("#", -1)[3] + "', \n" + "IndustryStableRemarks='" + stringData.split("#", -1)[4] + "', NabilCardHolderRemarks='" + stringData.split("#", -1)[5] + "',\n" + "IncomeNetworthRemarks='" + stringData.split("#", -1)[6] + "', GuarantorRepaymentRemarks='" + stringData.split("#", -1)[7] + "',\n" + "ApplicantBgRemarks='" + stringData.split("#", -1)[8] + "', NonCoreIncomeRemarks='" + stringData.split("#", -1)[9] + "',\n" + "CautionListRemarks='" + stringData.split("#", -1)[10] + "', BankStmtAnalysedRemarks='" + stringData.split("#", -1)[11] + "',\n" + "SecurityRemarks='" + stringData.split("#", -1)[12] + "', FacOtherBanksRemarks='" + stringData.split("#", -1)[13] + "',\n" + "FacNabilClassifyRemarks='" + stringData.split("#", -1)[14] + "', NRBChecklistRemarks='" + stringData.split("#", -1)[15] + "', CICDescription='" + stringData.split("#", -1)[16] + "', GeneralRemarks='" + stringData.split("#", -1)[17] + "', SecurityVariationsRemarks='" + stringData.split("#", -1)[18] + "', ConComVariationsRemarks='" + stringData.split("#", -1)[19] + "'\n" + "WHERE caseID='" + WINumber + "';";

                            LogMessages.statusLogLoans.info("update query in Ext_Retail table::" + updateQuery);
                            int res = iFormObj.saveDataInDB(updateQuery);
                            LogMessages.statusLogLoans.info("Savedataresponse:::" + res);
                            result = String.valueOf(res);
                        }
                        return result;
                    }else if (eventType.equalsIgnoreCase("getRetailDataFromCustomTable")) {
                        String WINumber = stringData.split("#", -1)[0];
                        String result = "";
                        LogMessages.statusLogLoans.info("**** RetailDataFromCustomTable  Starts for Workitem::" + WINumber + " ****");
                        String selectQuery = "SELECT * from Ext_Retail WITH(NOLOCK) where caseID='" + WINumber + "';";
                        LogMessages.statusLogLoans.info("Select query for check in remarks in Ext_Retail table::" + selectQuery);
                        List resultList = iFormObj.getDataFromDB(selectQuery);
                        LogMessages.statusLogLoans.info("resultList:" + resultList);
                        LogMessages.statusLogLoans.info("resultList size:" + resultList.size());
                        if (resultList.isEmpty()) {
                            result = "";
                        } else {
                            result = (String) ((List) resultList.get(0)).get(1) + "#" + (String) ((List) resultList.get(0)).get(2) + "#" + (String) ((List) resultList.get(0)).get(3) + "#" + (String) ((List) resultList.get(0)).get(4) + "#" + (String) ((List) resultList.get(0)).get(5) + "#" + (String) ((List) resultList.get(0)).get(6) + "#" + (String) ((List) resultList.get(0)).get(7) + "#" + (String) ((List) resultList.get(0)).get(8) + "#" + (String) ((List) resultList.get(0)).get(9) + "#" + (String) ((List) resultList.get(0)).get(10) + "#" + (String) ((List) resultList.get(0)).get(11) + "#" + (String) ((List) resultList.get(0)).get(12) + "#" + (String) ((List) resultList.get(0)).get(13) + "#" + (String) ((List) resultList.get(0)).get(14) + "#" + (String) ((List) resultList.get(0)).get(15) + "#" + (String) ((List) resultList.get(0)).get(16) + "#" + (String) ((List) resultList.get(0)).get(17) + "#" + (String) ((List) resultList.get(0)).get(18) + "#" + (String) ((List) resultList.get(0)).get(19);
                        }
                        return result;
                    } else if (eventType.equalsIgnoreCase("genSMEApprovalSummaryTempIndividual")) {
                        LogMessages.statusLogLoans.info("Inside genSMEApprovalSummaryTempIndividual");
                        String cabinetName = iFormObj.getCabinetName();
                        LogMessages.statusLogLoans.info("cabinetName =" + cabinetName);
                        String serverIP = iFormObj.getServerIp();
                        LogMessages.statusLogLoans.info("serverIP =" + serverIP);
                        String serverPort = iFormObj.getServerPort();
                        LogMessages.statusLogLoans.info("serverPort =" + serverPort);

                        List< String> paramlist = new ArrayList< String>();
                        paramlist.add("Text:" + stringData);
                        LogMessages.statusLogLoans.info("---" + paramlist.get(0) + "---- storedprocedure");

                        iFormObj.getDataFromStoredProcedure("LOS_SP_SME_INDIVIDUAL_TEMPLATE_FETCH_DATA", paramlist);
                        // Template Generation
                        String addDocOutXML = "";
                        CommonMethods cm = null;
                        try {
                            LogMessages.statusLogLoans.info("Try-catch genSMEApprovalSummaryTempIndividual");
                            cm = new CommonMethods();
                            LogMessages.statusLogLoans.info("Try-catch genSMEApprovalSummaryTempIndividual--1");
                            addDocOutXML = cm.generateDocument(iFormObj, "SMEApprovalSummary_Individual_template", stringData, serverIP, serverPort, cabinetName);
                            LogMessages.statusLogLoans.info("Try-catch genSMEApprovalSummaryTempIndividual---2");
                            retVal = addDocOutXML;
                        } catch (NGIMException e) {
                            // TODO Auto-generated catch block
                            // e.printStackTrace();
                            LogMessages.errorLogLoans.info("Exception found of type NGIM in genSMEApprovalSummaryTempIndividual : ", e);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            LogMessages.errorLogLoans.info("Exception found in genSMEApprovalSummaryTempIndividual : ", e);
                            // e.printStackTrace();
                        }
                        LogMessages.statusLogLoans.info("After generateDocument call....");
                        return retVal;
                    } else if (eventType.equalsIgnoreCase("genSMEApprovalSummaryTempInstitution")) {
                        LogMessages.statusLogLoans.info("Inside genSMEApprovalSummaryTempInstitution");
                        String cabinetName = iFormObj.getCabinetName();
                        LogMessages.statusLogLoans.info("cabinetName =" + cabinetName);
                        String serverIP = iFormObj.getServerIp();
                        LogMessages.statusLogLoans.info("serverIP =" + serverIP);
                        String serverPort = iFormObj.getServerPort();
                        LogMessages.statusLogLoans.info("serverPort =" + serverPort);

                        List< String> paramlist = new ArrayList< String>();
                        paramlist.add("Text:" + stringData);
                        LogMessages.statusLogLoans.info("---" + paramlist.get(0) + "---- storedprocedure");

                        iFormObj.getDataFromStoredProcedure("LOS_SP_SME_INSTITUTION_TEMPLATE_FETCH_DATA", paramlist);
                        // Template Generation
                        String addDocOutXML = "";
                        CommonMethods cm = null;
                        try {
                            LogMessages.statusLogLoans.info("Try-catch genSMEApprovalSummaryTempInstitution");
                            cm = new CommonMethods();
                            LogMessages.statusLogLoans.info("Try-catch genSMEApprovalSummaryTempInstitution--1");
                            addDocOutXML = cm.generateDocument(iFormObj, "SMEApprovalSummary_Institution_template", stringData, serverIP, serverPort, cabinetName);
                            LogMessages.statusLogLoans.info("Try-catch genSMEApprovalSummaryTempInstitution---2");
                            retVal = addDocOutXML;
                        } catch (NGIMException e) {
                            // TODO Auto-generated catch block
                            // e.printStackTrace();
                            LogMessages.errorLogLoans.info("Exception found of type NGIM in genSMEApprovalSummaryTempInstitution : ", e);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            LogMessages.errorLogLoans.info("Exception found in genSMEApprovalSummaryTempInstitution : ", e);
                            // e.printStackTrace();
                        }
                        LogMessages.statusLogLoans.info("After generateDocument call....");
                        return retVal;
                    } else if (eventType.equalsIgnoreCase("genRetailApprovalSummaryTempIndividual")) {
                        LogMessages.statusLogLoans.info("Inside genRetailApprovalSummaryTempIndividual");
                        String cabinetName = iFormObj.getCabinetName();
                        LogMessages.statusLogLoans.info("cabinetName =" + cabinetName);
                        String serverIP = iFormObj.getServerIp();
                        LogMessages.statusLogLoans.info("serverIP =" + serverIP);
                        String serverPort = iFormObj.getServerPort();
                        LogMessages.statusLogLoans.info("serverPort =" + serverPort);

                        List< String> paramlist = new ArrayList< String>();
                        paramlist.add("Text:" + stringData);
                        LogMessages.statusLogLoans.info("---" + paramlist.get(0) + "---- storedprocedure");

                        iFormObj.getDataFromStoredProcedure("LOS_SP_RETAILAS_INDIVIDUAL_TEMPLATE_FETCH_DATA", paramlist);
                        // Template Generation
                        String addDocOutXML = "";
                        CommonMethods cm = null;
                        try {
                            LogMessages.statusLogLoans.info("Try-catch genRetailApprovalSummaryTempIndividual");
                            cm = new CommonMethods();
                            LogMessages.statusLogLoans.info("Try-catch genRetailApprovalSummaryTempIndividual--1");
                            addDocOutXML = cm.generateDocument(iFormObj, "RetailApprovalSummary_Individual_template", stringData, serverIP, serverPort, cabinetName);
                            LogMessages.statusLogLoans.info("Try-catch genRetailApprovalSummaryTempIndividual---2");
                            retVal = addDocOutXML;
                        } catch (NGIMException e) {
                            // TODO Auto-generated catch block
                            // e.printStackTrace();
                            LogMessages.errorLogLoans.info("Exception found of type NGIM in genRetailApprovalSummaryTempIndividual : ", e);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            LogMessages.errorLogLoans.info("Exception found in genRetailApprovalSummaryTempIndividual : ", e);
                            // e.printStackTrace();
                        }
                        LogMessages.statusLogLoans.info("After generateDocument call....");
                        return retVal;
                    } else if (eventType.equalsIgnoreCase("genRetailApprovalSummaryTempInstitution")) {
                        LogMessages.statusLogLoans.info("Inside genRetailApprovalSummaryTempInstitution");
                        String cabinetName = iFormObj.getCabinetName();
                        LogMessages.statusLogLoans.info("cabinetName =" + cabinetName);
                        String serverIP = iFormObj.getServerIp();
                        LogMessages.statusLogLoans.info("serverIP =" + serverIP);
                        String serverPort = iFormObj.getServerPort();
                        LogMessages.statusLogLoans.info("serverPort =" + serverPort);

                        List< String> paramlist = new ArrayList< String>();
                        paramlist.add("Text:" + stringData);
                        LogMessages.statusLogLoans.info("---" + paramlist.get(0) + "---- storedprocedure");

                        iFormObj.getDataFromStoredProcedure("LOS_SP_RETAILAS_INSTITUTION_TEMPLATE_FETCH_DATA", paramlist);
                        // Template Generation
                        String addDocOutXML = "";
                        CommonMethods cm = null;
                        try {
                            LogMessages.statusLogLoans.info("Try-catch genRetailApprovalSummaryTempInstitution");
                            cm = new CommonMethods();
                            LogMessages.statusLogLoans.info("Try-catch genRetailApprovalSummaryTempInstitution--1");
                            addDocOutXML = cm.generateDocument(iFormObj, "RetailApprovalSummary_Institution_template", stringData, serverIP, serverPort, cabinetName);
                            LogMessages.statusLogLoans.info("Try-catch genRetailApprovalSummaryTempInstitution---2");
                            retVal = addDocOutXML;
                        } catch (NGIMException e) {
                            // TODO Auto-generated catch block
                            // e.printStackTrace();
                            LogMessages.errorLogLoans.info("Exception found of type NGIM in genRetailApprovalSummaryTempInstitution : ", e);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            LogMessages.errorLogLoans.info("Exception found in genRetailApprovalSummaryTempInstitution : ", e);
                            // e.printStackTrace();
                        }
                        LogMessages.statusLogLoans.info("After generateDocument call....");
                        return retVal;
                    }
                    break;
                case "FormLoad":
                    break;
                case "MOUSE_CLICKED":
                    switch (eventType) {
                        case "onclickbtn_BAMdownload":
                            this.wdgeneralObj = iFormObj.getObjGeneralData();
                            String loggedinUserIndex = this.wdgeneralObj.getM_strUserIndex();
                            LogMessages.statusLog.info("loggedinUserIndex: " + loggedinUserIndex);
                            return loggedinUserIndex;
                    }

                    break;
                case "VALUE_CHANGED":
                    //String tmp384_383 = eventType; tmp384_383.getClass(); tmp384_383;

                    break;
                case "submit":
                    switch (eventType) {
                        case "DocMandatoryCheck":
                            try {
                                LogMessages.statusLog.info("inside DocMandatoryCheck of submit");
                                String retXml = iFormObj.getValue("TXT_DocAttach").toString();
                                LogMessages.statusLog.info("TXT_FileAttributes" + iFormObj.getValue("TXT_DocAttach"));

                                ArrayList mandatoryDocList = new ArrayList();
                                if (this.activityName.equalsIgnoreCase("RO")) {
                                    mandatoryDocList.add("Company Documents");
                                    mandatoryDocList.add("Collateral Documents");
                                    //mandatoryDocList.add("Sales Template");
                                    mandatoryDocList.add("CIC Report");
                                    mandatoryDocList.add("Citizenship Certificates");

                                    LogMessages.statusLog.info("RO mandate doc list--" + mandatoryDocList);
                                } else if (this.activityName.equalsIgnoreCase("AuthorityGroup")) {
                                    String q_authorityGrp = iFormObj.getValue("q_TargetAuthorityGroup").toString();
                                    LogMessages.statusLog.info("q_TargetAuthorityGroup ---" + q_authorityGrp);

                                    if (q_authorityGrp.equalsIgnoreCase("UnderWriter")) {
                                        mandatoryDocList.add("CFR");
                                    }
                                } else if (this.activityName.equalsIgnoreCase("CAS")) {
                                    mandatoryDocList.add("Security Documents");
                                    LogMessages.statusLog.info("Inside CAS, Security Document added in Mandatorylist--");
                                }
                                LogMessages.statusLog.info("Final Mandate doc list--" + mandatoryDocList);

                                WFXmlResponse wfxmlresponse = new WFXmlResponse(retXml);
                                WFXmlList wfxmllist = wfxmlresponse.createList("Documents", "Document");
                                wfxmllist.reInitialize(true);
                                LogMessages.statusLog.info("wfxmllist---->" + wfxmllist);
                                ArrayList xmlDocList = new ArrayList();
                                for (; wfxmllist.hasMoreElements(true); wfxmllist.skip(true)) {
                                    xmlDocList.add(wfxmllist.getVal("DocumentName"));
                                    LogMessages.statusLog.info("xmlDocList---->" + xmlDocList);
                                }

                                boolean contains = true;
                                int l2 = mandatoryDocList.size();
                                int currIndex = -1;
                                for (int j = 0; j < l2; j++) {
                                    String e2 = (String) mandatoryDocList.get(j);
                                    int i1 = xmlDocList.indexOf(e2);
                                    LogMessages.statusLog.info("e2 mandatoryDocList element--" + e2);
                                    LogMessages.statusLog.info("i1: Index of e2 in xmlDocList--" + i1);
                                    if (i1 == -1) {
                                        contains = false;
                                        LogMessages.statusLog.info("xmlDocList does not contain e2 element: " + e2);
                                        return "Please attached the mandatory document: " + e2;
                                    }
                                    if (i1 > currIndex) {
                                        currIndex = i1;
                                    }
                                }
                                LogMessages.statusLog.info("xmlDocList contains all elements of mandatoryDocList--" + contains);
                            } catch (Exception e) {
                                LogMessages.statusLog.info("Exception inside submit--" + e);
                            }
                            break;
                        case "DocMandatoryCheckSME":
                            try {

                                LogMessages.statusLog.info("SME inside DocMandatoryCheck of SME of submit");
                                String retXml = iFormObj.getValue("TXT_DocAttach").toString();
                                LogMessages.statusLog.info("TXT_FileAttributes" + iFormObj.getValue("TXT_DocAttach"));
                                ArrayList mandatoryDocList = new ArrayList();

                                if (this.activityName.equalsIgnoreCase("RO")) {
                                    mandatoryDocList.add("Scorecard");
                                    LogMessages.statusLog.info("RO mandate doc list--" + mandatoryDocList);

                                } else if (this.activityName.equalsIgnoreCase("UnderWriter")) {
                                    mandatoryDocList.add("Scorecard");
                                } else if (this.activityName.equalsIgnoreCase("Data Entry")) {
                                    mandatoryDocList.add("Scorecard");
                                    LogMessages.statusLog.info("Data Entry, CM Scorecard doc added in Mandatorylist--");
                                }

                                LogMessages.statusLog.info("Final Mandate doc list--" + mandatoryDocList);

                                WFXmlResponse wfxmlresponse = new WFXmlResponse(retXml);
                                WFXmlList wfxmllist = wfxmlresponse.createList("Documents", "Document");
                                wfxmllist.reInitialize(true);
                                LogMessages.statusLog.info("wfxmllist---->" + wfxmllist);
                                ArrayList xmlDocList = new ArrayList();
                                for (; wfxmllist.hasMoreElements(true); wfxmllist.skip(true)) {
                                    xmlDocList.add(wfxmllist.getVal("DocumentName"));
                                    LogMessages.statusLog.info("xmlDocList---->" + xmlDocList);
                                }

                                boolean contains = true;
                                int l2 = mandatoryDocList.size();
                                int currIndex = -1;
                                for (int j = 0; j < l2; j++) {
                                    String e2 = (String) mandatoryDocList.get(j);
                                    int i1 = xmlDocList.indexOf(e2);
                                    LogMessages.statusLog.info("e2 mandatoryDocList element--" + e2);
                                    LogMessages.statusLog.info("i1: Index of e2 in xmlDocList--" + i1);
                                    if (i1 == -1) {
                                        contains = false;
                                        LogMessages.statusLog.info("xmlDocList does not contain e2 element: " + e2);
                                        return "Please attached the mandatory document: " + e2;
                                    }
                                    if (i1 > currIndex) {
                                        currIndex = i1;
                                    }
                                }
                                LogMessages.statusLog.info("xmlDocList contains all elements of mandatoryDocList--" + contains);
                            } catch (Exception e) {
                                LogMessages.statusLog.info("Exception inside submit--" + e);
                            }
                            break;
                        case "setEmailIdsofUsers":
                            try {
                                LogMessages.statusLog.info("inside setEmailIdsofUsers of submit");
                                String userDecision = iFormObj.getValue("UserDecision").toString();
                                LogMessages.statusLog.info("userDecision--" + userDecision);
                                String userDomainID = iFormObj.getValue("TargetAssignedUser").toString();
                                LogMessages.statusLog.info("userDomainID--" + userDomainID);
                                String userAuthorityGrp = iFormObj.getValue("TargetAuthorityGroup").toString();
                                LogMessages.statusLog.info("userAuthorityGrp--" + userAuthorityGrp);
                                String mailIdFetchquery = "select top 1 Email from LOS_UserAuthorityGroupMapping WITH(NOLOCK) where Domanin_Name='" + userDomainID + "' and Authroity_GroupName='" + userAuthorityGrp + "'";
                                LogMessages.statusLog.info("mailIdFetchquery--" + mailIdFetchquery);

                                List mailIdFetchList = iFormObj.getDataFromDB(mailIdFetchquery);

                                LogMessages.statusLog.info("mailIdFetchList--" + mailIdFetchList);
                                if (!mailIdFetchList.isEmpty()) {
                                    if (this.activityName.equalsIgnoreCase("AuthorityGroup")) {
                                        LogMessages.statusLog.info("----AuthorityGroup-------");
                                        LogMessages.statusLog.info("userDecision--" + userDecision);
                                        if (userDecision.equalsIgnoreCase("Query")) {
                                            iFormObj.setValue("Reviewer2", (String) ((List) mailIdFetchList.get(0)).get(0));
                                            LogMessages.statusLog.info("****Query user mail id set : Reviewer2--*" + (String) ((List) mailIdFetchList.get(0)).get(0));
                                        } else if ((!userDecision.equalsIgnoreCase("Return")) && (!userDecision.equalsIgnoreCase("Reject"))) {
                                            iFormObj.setValue("Reviewer3", (String) ((List) mailIdFetchList.get(0)).get(0));
                                            LogMessages.statusLog.info("****AuthorityGrp mail id set : Reviewer3--*" + (String) ((List) mailIdFetchList.get(0)).get(0));
                                        }
                                    } else if (this.activityName.equalsIgnoreCase("RO")) {
                                        LogMessages.statusLog.info("----RO-------");
                                        LogMessages.statusLog.info("userDecision--" + userDecision);
                                        iFormObj.setValue("Reviewer3", (String) ((List) mailIdFetchList.get(0)).get(0));
                                        LogMessages.statusLog.info("****AuthorityGrp mail id set : Reviewer3--*" + (String) ((List) mailIdFetchList.get(0)).get(0));
                                    }
                                } else {
                                    LogMessages.statusLog.info("mailIdFetchList is empty--" + mailIdFetchList);
                                }
                                LogMessages.statusLog.info("Now setEmailIdsofCAS and CLAD groups**** of submit()");

                                String lastAuthorityGroup = iFormObj.getValue("LastAuthorityGroup").toString();
                                LogMessages.statusLog.info("----activityName--" + this.activityName + " and lastAuthorityGroup--" + lastAuthorityGroup);

                                LogMessages.statusLog.info("userDecision--" + userDecision);

                                if ((this.activityName.equalsIgnoreCase("AuthorityGroup")) && (lastAuthorityGroup.equalsIgnoreCase("Yes"))
                                        && (!userDecision.equalsIgnoreCase("Return")) && (!userDecision.equalsIgnoreCase("Reject")) && (!userDecision.equalsIgnoreCase("Query"))) {
                                    LogMessages.statusLog.info("Setting EmailIdsofCAS and CLAD groups**** starts@!");
                                    String casGroupsMailIdquery = "select UserIndex,UserName,MailId from PDBUser WITH(NOLOCK) where UserIndex in (select UserIndex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='CAS Group'))";

                                    LogMessages.statusLog.info("query of casGroupsMailIdquery--" + casGroupsMailIdquery);
                                    String cladGroupsMailIdquery = "select UserIndex,UserName,MailId from PDBUser WITH(NOLOCK) where UserIndex in (select UserIndex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='CLAD Group'))";
                                    LogMessages.statusLog.info("query of cladGroupsMailIdquery--" + cladGroupsMailIdquery);

                                    List casGroupsMailIdList = iFormObj.getDataFromDB(casGroupsMailIdquery);

                                    LogMessages.statusLog.info("casGroupsMailIdList--" + casGroupsMailIdList);
                                    if (!casGroupsMailIdList.isEmpty()) {

                                        LogMessages.statusLog.info("casGroupsMailIdList size()--" + casGroupsMailIdList.size());

                                        String groupIDsofCAS = "";

                                        for (int i = 0; i < casGroupsMailIdList.size(); i++) {
                                            LogMessages.statusLog.info("**CAS " + i + "th row mail id--" + (String) ((List) casGroupsMailIdList.get(i)).get(2));
                                            groupIDsofCAS = groupIDsofCAS + (String) ((List) casGroupsMailIdList.get(i)).get(2) + ";";

                                        }
                                        LogMessages.statusLog.info("Final groupIDsofCAS**-" + groupIDsofCAS);
                                        iFormObj.setValue("groupMailIDsofCAS", groupIDsofCAS);
                                        LogMessages.statusLog.info("Form value of groupMailIDsofCAS set---**-" + iFormObj.getValue("groupMailIDsofCAS"));
                                    }

                                    List cladGroupsMailIdList = iFormObj.getDataFromDB(cladGroupsMailIdquery);

                                    LogMessages.statusLog.info("cladGroupsMailIdList--" + cladGroupsMailIdList);
                                    if (!cladGroupsMailIdList.isEmpty()) {
                                        LogMessages.statusLog.info("cladGroupsMailIdList size()--" + cladGroupsMailIdList.size());

                                        String groupIDsofCLAD = "";

                                        for (int i = 0; i < cladGroupsMailIdList.size(); i++) {
                                            LogMessages.statusLog.info("**CLAD " + i + "th row mail id--" + (String) ((List) cladGroupsMailIdList.get(i)).get(2));
                                            groupIDsofCLAD = groupIDsofCLAD + (String) ((List) cladGroupsMailIdList.get(i)).get(2) + ";";

                                        }

                                        LogMessages.statusLog.info("Final groupIDsofCLAD**-" + groupIDsofCLAD);
                                        iFormObj.setValue("groupMailIDsofCLAD", groupIDsofCLAD);
                                        LogMessages.statusLog.info("Form value of groupMailIDsofCLAD set---**-" + iFormObj.getValue("groupMailIDsofCLAD"));
                                    }

                                    LogMessages.statusLog.info("Setting EmailIdsofCAS and CLAD groups**** ends@!");

                                }

                                LogMessages.statusLog.info("End of setEmailIdsofUsers of submit()");
                            } catch (Exception e) {
                                LogMessages.statusLog.info("Exception inside setEmailIdsofUsers of submit--" + e);
                            }
                            break;
                    }
                    break;
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception inside setEmailIdsofUsers of submit--" + e);
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
        String qry = "select ClientNamePD from Ext_Sme(nolock) where flag2 = '" + stringData + "'";

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
                    JSONObject obj = (JSONObject) jsonarray.get(i);
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

            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
            return true;
        }
        if (controlId.equals("table1_AssignedQueryUser")) {
            query = "select username  as 'UserId',concat(PersonalName,' ',FamilyName) as 'UserName' from pdbuser(nolock) where userindex in (select userindex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='LOS Query Group'))";

            LogMessages.statusLog.info("**picklist PreHook query --" + query);
            System.out.println("**picklist PreHook query--" + query);

            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
            return true;
        }
        ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
        ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
        return true;
    }

    public void picklistSearchHook(String controlId, IFormReference iformObj, String searchString, int colIndex) {
        LogMessages.statusLog.info("inside picklistSearchHook, controlId--" + controlId);
        System.out.println("inside picklistSearchHook, controlId--" + controlId);
        String query = "";

        if (controlId.equals("table2_UsersOfGroups")) {
            query = picklistCustomQueryForSearch(controlId, iformObj);

            if ((searchString != "") && (colIndex != -1)) {
                if (colIndex == 0) {
                    query = query + " and Domanin_Name like '%" + searchString + "%'";
                } else if (colIndex == 1) {
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

            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
        } else if (controlId.equals("table1_AssignedQueryUser")) {
            query = "select username  as 'UserId',concat(PersonalName,' ',FamilyName) as 'UserName' from pdbuser(nolock) where userindex in (select userindex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='LOS Query Group'))";

            if ((searchString != "") && (colIndex != -1)) {
                if (colIndex == 0) {
                    query = query + " and username like '%" + searchString + "%'";
                } else if (colIndex == 1) {
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

            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
        } else {
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
            ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
        }
    }

    public String picklistCustomQueryForSearch(String controlId, IFormReference iformObj) {
        LogMessages.statusLog.info("inside picklistCustomQueryForSearch, controlId--" + controlId);
        System.out.println("inside picklistCustomQueryForSearch, controlId--" + controlId);
        String query = "";
        String user = "";
        JSONArray jsonarray = iformObj.getDataFromGrid("table2");
        if (jsonarray.size() > 0) {
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
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
        String qryWID = "Select WorkItemId from WFINSTRUMENTTABLE WITH(NOLOCK) where processInstanceId = '" + stringData + "'";

        LogMessages.statusLog.info("DedupeQuery is: " + qryWID);
        List list_wid = iFormObj.getDataFromDB(qryWID);

        LogMessages.statusLog.info("" + list_wid.size());

        LogMessages.statusLog.info(list_wid.get(0).toString());
        if (list_wid.size() == 0) {
            return "";
        }
        return list_wid.get(0).toString();
    }

    public String generateHTML(EControl ec) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1, WorkdeskModel wm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
