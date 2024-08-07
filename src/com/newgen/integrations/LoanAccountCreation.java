
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.util.DateUtil;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

import static com.newgen.iforms.util.NewGenUtil.getFailureResponse;
import static com.newgen.iforms.util.NewGenUtilConstants.*;

/**
 * @author bibek.shah
 **/
public class LoanAccountCreation {

    public String loanAccountCreationReq(String input, IFormReference iFormObj) {
        String request = "";
        String[] allData = input.split("@@");
        LogMessages.statusLog.info("All Data:  " + allData);
        String dataNum = allData[1];
        LogMessages.statusLog.info("Data Num:  " + dataNum);
        String[] reptData = allData[2].split("#");
        LogMessages.statusLog.info("Rept Data:  " + reptData);
        String[] param = allData[3].split("#");
        LogMessages.statusLog.info("Limit Suffix Params:  " + param[33]);

        String repmtrecData;
        try {
            if (dataNum.equalsIgnoreCase("EIonly")) {
                repmtrecData = "[{\"InstallmentId\":\"" + reptData[0] + "\",\"InstallStartDt\":\"" + reptData[1] + "\",\"InstallFreq\":{\"Cal\":\"" + reptData[2] + "\",\"Type\":\"" + reptData[3] + "\",\"StartDt\":\"" + reptData[4] + "\",\"WeekDay\":\"" + reptData[5] + "\",\"HolStat\":\"" + reptData[6] + "\"},\"IntFreq\":{\"Cal\":\"" + reptData[7] + "\",\"Type\":\"" + reptData[8] + "\",\"StartDt\":\"" + reptData[9] + "\",\"WeekDay\":\"" + reptData[10] + "\",\"HolStat\":\"" + reptData[11] + "\"},\"NoOfInstall\":\"" + reptData[12] + "\"}]";
                LogMessages.statusLog.info(":repmtrecData " + repmtrecData);
            } else if (dataNum.equalsIgnoreCase("NonEI")) {
                JSONArray outputArr = iFormObj.getDataFromGrid("tblCustomizedInstallmentLoanacc");
                JSONArray tmpJsonArr = new JSONArray();
                String interestStartDate = param[62];
                String interestStartDay = interestStartDate.split("/")[0];
                Date dateInterestStart = DateUtil.parseStringToDate(interestStartDay, NewGenUtilConstants.DD_MM_YYYY);
                String interestStartDateFormat = DateUtil.parseDateToString(dateInterestStart, NewGenUtilConstants.YYYY_MM_DD) + T_00_00_00;

                for (int i = 0; i < outputArr.size(); i++) {
                    JSONObject obj2 = (JSONObject) outputArr.get(i);
                    String parentFacility = String.valueOf(obj2.get("Parent Facility"));
                    LogMessages.statusLog.info("else if parent facility ::  " + parentFacility);
                    LogMessages.statusLog.info("else if param[33] ::  " + param[33]);
                    if (parentFacility.equalsIgnoreCase(param[33])) {
                        String frequencyType = String.valueOf(obj2.get("Frequency"));
                        String installmentStartDate = String.valueOf(obj2.get("Installment Start Date"));
                        String installmentStartDay = installmentStartDate.split("/")[0];
                        Date dateInstallmentStart = DateUtil.parseStringToDate(installmentStartDate, NewGenUtilConstants.DD_MM_YYYY);
                        String installmentStartDateFormat = DateUtil.parseDateToString(dateInstallmentStart, NewGenUtilConstants.YYYY_MM_DD) + T_00_00_00;
                        String amount = String.valueOf(obj2.get("Amount"));
                        String noOfFlows = String.valueOf(obj2.get("No. of Flows"));
                        String prdemNode = "{'InstallmentId':'PRDEM','InstallStartDt':'" + installmentStartDateFormat + "','InstallFreq':{'Cal':'00','Type':'" + frequencyType + "','StartDt':'" + installmentStartDay + "','WeekDay':'0','HolStat':'N'},'IntFreq':{'Cal':'00','Type':'" + frequencyType + "','StartDt':'" + interestStartDay + "','WeekDay':'0','HolStat':'N'},'NoOfInstall':'" + noOfFlows + "','FlowAmt':{'amountValue':'" + amount + "','currencyCode':'NPR'},'IntStartDt':'" + interestStartDateFormat + "'}";
                        tmpJsonArr.add(prdemNode);
                        LogMessages.statusLog.info(" Value of i :: " + i + " ParentFacility :: " + parentFacility);
                    }
                }
                String indemNode = "{'InstallmentId':'INDEM','InstallStartDt':'" + interestStartDateFormat + "','InstallFreq':{'Cal':'00','Type':'" + param[18] + "','StartDt':'" + interestStartDay + "','WeekDay':'0','HolStat':'N'},'IntFreq':{'Cal':'00','Type':'" + param[18] + "','StartDt':'" + interestStartDay + "','WeekDay':'0','HolStat':'N'},'IntStartDt':'" + interestStartDateFormat + "'}";
                tmpJsonArr.add(indemNode);
                repmtrecData = tmpJsonArr.toJSONString().replaceAll("\\[\"", "\\[").replaceAll("\"\\]", "\\]").replaceAll("\",\"", ",").replaceAll("'", "\"");
                LogMessages.statusLog.info(":else if repmtrecData" + repmtrecData);

            } else {
                repmtrecData = "[{\"InstallmentId\":\"" + reptData[0] + "\",\"InstallStartDt\":\"" + reptData[1] + "\",\"InstallFreq\":{\"Cal\":\"" + reptData[2] + "\",\"Type\":\"" + reptData[3] + "\",\"StartDt\":\"" + reptData[4] + "\",\"WeekDay\":\"" + reptData[5] + "\",\"HolStat\":\"" + reptData[6] + "\"},\"IntFreq\":{\"Cal\":\"" + reptData[7] + "\",\"Type\":\"" + reptData[8] + "\",\"StartDt\":\"" + reptData[9] + "\",\"WeekDay\":\"" + reptData[10] + "\",\"HolStat\":\"" + reptData[11] + "\"},\"NoOfInstall\":\"" + reptData[12] + "\"},{\"InstallmentId\":\"" + reptData[14] + "\",\"InstallStartDt\":\"" + reptData[15] + "\",\"InstallFreq\":{\"Cal\":\"" + reptData[16] + "\",\"Type\":\"" + reptData[17] + "\",\"StartDt\":\"" + reptData[18] + "\",\"WeekDay\":\"" + reptData[19] + "\",\"HolStat\":\"" + reptData[20] + "\"},\"IntFreq\":{\"Cal\":\"" + reptData[21] + "\",\"Type\":\"" + reptData[22] + "\",\"StartDt\":\"" + reptData[23] + "\",\"WeekDay\":\"" + reptData[24] + "\",\"HolStat\":\"" + reptData[25] + "\"},\"NoOfInstall\":\"" + reptData[26] + "\"}]";
                LogMessages.statusLog.info(":else  repmtrecData " + repmtrecData);
            }

            if (dataNum.equalsIgnoreCase("NonEI")) { // Without Holiday period
                request = "{\"LoanAcctAddRequest\":{\"LoanAcctAddRq\":{\"CustId\":{\"CustId\":\"" + param[0] + "\"},\"LoanAcctId\":{\"AcctType\":{\"SchmCode\":\"" + param[1] + "\",\"SchmType\":\"" + param[2] + "\"},\"AcctCurr\":\"" + param[3] + "\",\"BankInfo\":{\"BranchId\":\"" + param[4] + "\"}},\"LoanAcctGenInfo\":{\"GenLedgerSubHead\":{\"GenLedgerSubHeadCode\":\"" + param[5] + "\",\"CurCode\":\"" + param[6] + "\"},\"AcctStmtMode\":\"" + param[7] + "\",\"DespatchMode\":\"" + param[8] + "\"},\"LoanGenDetails\":{\"LoanPeriodDays\":\"" + param[9] + "\",\"LoanPeriodMonths\":\"" + param[10] + "\",\"OperAcctId\":{\"AcctId\":\"" + param[11] + "\",\"AcctCurr\":\"" + param[12] + "\"},\"PmtPlan\":{\"EqInstallDetails\":{\"EqInstallFlg\":\"" + param[13] + "\"},\"RepmtRec\":" + repmtrecData + "},\"LoanAmt\":{\"amountValue\":\"" + param[22] + "\",\"currencyCode\":\"" + param[23] + "\"}},\"AccountInterestTax\":{\"BasicIntrst\":{\"AcctDrPrefPcnt\":{\"value\":\"" + param[24] + "\"},\"CustDrPrefPcnt\":{\"value\":\"" + param[25] + "\"},\"IntTblCode\":\"" + param[26] + "\"}},\"LoanAcctAdd_CustomData\":{\"documentDate\":\"" + param[27] + "\",\"reviewDate\":\"" + param[28] + "\",\"sanctionLevelCode\":\"" + param[29] + "\",\"sanctionAuthCode\":\"" + param[30] + "\",\"acctLimitsEntered\":\"" + param[31] + "\",\"limitIdPrefix\":\"" + param[32] + "\",\"limitIdSuffix\":\"" + param[33] + "\",\"acctClassMISCEntered\":\"" + param[34] + "\",\"PENALRATEMTHFLG\":" + param[35] + ",\"freeCode1\":\"" + param[36] + "\",\"freeCode3\":\"" + param[37] + "\",\"freeCode6\":\"" + param[38] + "\",\"freeCode8\":\"" + param[50] + "\",\"freeCode9\":\"" + param[39] + "\",\"expiryDate\":\"" + param[40] + "\",\"drawingPowerIndicator\":\"" + param[41] + "\",\"freetext3\":\"" + param[42] + "\",\"freetext6\":\"" + param[43] + "\",\"freetext10\":\"" + param[44] + "\",\"industryType\":\"" + param[45] + "\",\"sectorCode\":\"" + param[46] + "\",\"subSectorCode\":\"" + param[47] + "\",\"deprivedSector\":\"" + param[48] + "\",\"sanctionLimit\":\"" + param[49] + "\",\"freeCode2\":\"" + param[51] + "\",\"freeCode4\":\"" + param[52] + "\",\"freeCode5\":\"" + param[53] + "\",\"freeCode7\":\"" + param[54] + "\",\"freeCode10\":\"" + param[55] + "\",\"borrowerCategory\":\"" + param[56] + "\",\"purposeOfAdvance\":\"" + param[57] + "\",\"modeOfAdvance\":\"" + param[58] + "\",\"natureOfAdvance\":\"" + param[59] + "\",\"guaranteeCoverCode\":\"" + param[60] + "\",\"creditFileNumber\":\"" + param[61] + "\"}}}}";
            } else {                                 // With Holiday period
                request = "{\"LoanAcctAddRequest\":{\"LoanAcctAddRq\":{\"CustId\":{\"CustId\":\"" + param[0] + "\"},\"LoanAcctId\":{\"AcctType\":{\"SchmCode\":\"" + param[1] + "\",\"SchmType\":\"" + param[2] + "\"},\"AcctCurr\":\"" + param[3] + "\",\"BankInfo\":{\"BranchId\":\"" + param[4] + "\"}},\"LoanAcctGenInfo\":{\"GenLedgerSubHead\":{\"GenLedgerSubHeadCode\":\"" + param[5] + "\",\"CurCode\":\"" + param[6] + "\"},\"AcctStmtMode\":\"" + param[7] + "\",\"DespatchMode\":\"" + param[8] + "\"},\"LoanGenDetails\":{\"LoanPeriodDays\":\"" + param[9] + "\",\"LoanPeriodMonths\":\"" + param[10] + "\",\"OperAcctId\":{\"AcctId\":\"" + param[11] + "\",\"AcctCurr\":\"" + param[12] + "\"},\"PmtPlan\":{\"EqInstallDetails\":{\"EqInstallFlg\":\"" + param[13] + "\"},\"HolidayPeriodDetails\":{\"HolidayPeriod\":\"" + param[14] + "\",\"AutoReschedPostHolidayPeriod\":\"" + param[15] + "\",\"IntDuringHolidayPeriod\":\"" + param[16] + "\",\"IntFreq\":{\"Cal\":\"" + param[17] + "\",\"Type\":\"" + param[18] + "\",\"StartDt\":\"" + param[19] + "\",\"WeekDay\":\"" + param[20] + "\",\"HolStat\":\"" + param[21] + "\"}},\"RepmtRec\":" + repmtrecData + "},\"LoanAmt\":{\"amountValue\":\"" + param[22] + "\",\"currencyCode\":\"" + param[23] + "\"}},\"AccountInterestTax\":{\"BasicIntrst\":{\"AcctDrPrefPcnt\":{\"value\":\"" + param[24] + "\"},\"CustDrPrefPcnt\":{\"value\":\"" + param[25] + "\"},\"IntTblCode\":\"" + param[26] + "\"}},\"LoanAcctAdd_CustomData\":{\"documentDate\":\"" + param[27] + "\",\"reviewDate\":\"" + param[28] + "\",\"sanctionLevelCode\":\"" + param[29] + "\",\"sanctionAuthCode\":\"" + param[30] + "\",\"acctLimitsEntered\":\"" + param[31] + "\",\"limitIdPrefix\":\"" + param[32] + "\",\"limitIdSuffix\":\"" + param[33] + "\",\"acctClassMISCEntered\":\"" + param[34] + "\",\"PENALRATEMTHFLG\":" + param[35] + ",\"freeCode1\":\"" + param[36] + "\",\"freeCode3\":\"" + param[37] + "\",\"freeCode6\":\"" + param[38] + "\",\"freeCode8\":\"" + param[50] + "\",\"freeCode9\":\"" + param[39] + "\",\"expiryDate\":\"" + param[40] + "\",\"drawingPowerIndicator\":\"" + param[41] + "\",\"freetext3\":\"" + param[42] + "\",\"freetext6\":\"" + param[43] + "\",\"freetext10\":\"" + param[44] + "\",\"industryType\":\"" + param[45] + "\",\"sectorCode\":\"" + param[46] + "\",\"subSectorCode\":\"" + param[47] + "\",\"deprivedSector\":\"" + param[48] + "\",\"sanctionLimit\":\"" + param[49] + "\",\"freeCode2\":\"" + param[51] + "\",\"freeCode4\":\"" + param[52] + "\",\"freeCode5\":\"" + param[53] + "\",\"freeCode7\":\"" + param[54] + "\",\"freeCode10\":\"" + param[55] + "\",\"borrowerCategory\":\"" + param[56] + "\",\"purposeOfAdvance\":\"" + param[57] + "\",\"modeOfAdvance\":\"" + param[58] + "\",\"natureOfAdvance\":\"" + param[59] + "\",\"guaranteeCoverCode\":\"" + param[60] + "\",\"creditFileNumber\":\"" + param[61] + "\"}}}}";
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanAccountCreationReq:  " + e);
        }
        LogMessages.statusLog.info("Request loanAccountCreationReq:  " + request);
        return request;
    }


    public JSONArray loanAccountCreationRes(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(NewGenUtilConstants.SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String loanAcctAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("LoanAcctAddResponse"));
                    LogMessages.statusLog.info("loanAcctAddResponse: " + loanAcctAddResponse);
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(loanAcctAddResponse);
                    String loanAcctAddRs = String.valueOf(((JSONObject) obj2.get("LoanAcctAddRs")).get(ACCT_ID));
                    LogMessages.statusLog.info("loanAcctAddRs: " + loanAcctAddRs);
                    JSONObject obj3 = NewGenUtil.getParsedJsonObj(loanAcctAddRs);
                    String acctOpenDt = String.valueOf(obj3.get(ACCT_ID));
                    LogMessages.statusLog.info("acctOpenDt: " + acctOpenDt);

                    res.put(ACCT_ID, acctOpenDt);
                    res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.SUCCESS);
                } else {
                    getFailureResponse(param[1], response, res, obj);
                }
            } else {
                res.put(NewGenUtilConstants.STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception loanAccountCreationRes: " + e);
        }
        LogMessages.statusLog.info("Response loanAccountCreationRes: " + response);
        return response;
    }
}
