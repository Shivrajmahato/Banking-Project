
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import java.text.SimpleDateFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
*
* @author bibek.shah
*
**/
public class LoanAccountCreation {
    public String loanAccountCreationReq(String input, IFormReference iFormObj) {
		String request = "";
                String[] alldata = input.split("@@");
                LogMessages.statusLog.info("All Data:  " + alldata);
                String datanum =alldata[1];
                LogMessages.statusLog.info("Data Num:  " + datanum);
                String[] reptdata = alldata[2].split("#");
                LogMessages.statusLog.info("Rept Data:  " + reptdata);
                String[] param = alldata[3].split("#");
                LogMessages.statusLog.info("Limit Suffix Params:  " + param[33]);
      
                String repmtrecData="";
		try {
			LogMessages.statusLog.info("Inside create Request of loanAccountCreationReq....#..."+alldata[0]);
			//request = "{\"LoanAcctAddRequest\":{\"LoanAcctAddRq\":{\"CustId\":{\"CustId\":\"" + param[1] + "\"},\"LoanAcctId\":{\"AcctType\":{\"SchmCode\":\"" + param[2] + "\",\"SchmType\":\"" + param[3] + "\"},\"AcctCurr\":\"" + param[4] + "\",\"BankInfo\":{\"BranchId\":\"" + param[5] + "\"}},\"LoanAcctGenInfo\":{\"GenLedgerSubHead\":{\"GenLedgerSubHeadCode\":\"" + param[6] + "\",\"CurCode\":\"" + param[7] + "\"},\"AcctStmtMode\":\"" + param[8] + "\",\"DespatchMode\":\"" + param[9] + "\"},\"LoanGenDetails\":{\"LoanPeriodDays\":\"" + param[10] + "\",\"LoanPeriodMonths\":\"" + param[11] + "\",\"OperAcctId\":{\"AcctId\":\"" + param[12] + "\",\"AcctCurr\":\"" + param[13] + "\"},\"PmtPlan\":{\"EqInstallDetails\":{\"EqInstallFlg\":\"" + param[14] + "\"},\"RepmtRec\":[{\"InstallmentId\":\"" + param[15] + "\",\"InstallStartDt\":\"" + param[16] + "\",\"InstallFreq\":{\"Cal\":\"" + param[17] + "\",\"Type\":\"" + param[18] + "\",\"StartDt\":\"" + param[19] + "\",\"WeekDay\":\"" + param[20] + "\",\"HolStat\":\"" + param[21] + "\"},\"IntFreq\":{\"Cal\":\"" + param[22] + "\",\"Type\":\"" + param[23] + "\",\"StartDt\":\"" + param[24] + "\",\"WeekDay\":\"" + param[25] + "\",\"HolStat\":\"" + param[26] + "\"},\"NoOfInstall\":\"" + param[27] + "\",\"FlowAmt\":{\"amountValue\":\"" + param[28] + "\",\"currencyCode\":\"" + param[29] + "\"},\"IntStartDt\":\"" + param[30] + "\"},{\"InstallmentId\":\"" + param[31] + "\",\"InstallStartDt\":\"" + param[32] + "\",\"InstallFreq\":{\"Cal\":\"" + param[33] + "\",\"Type\":\"" + param[34] + "\",\"StartDt\":\"" + param[35] + "\",\"WeekDay\":\"" + param[36] + "\",\"HolStat\":\"" + param[37] + "\"},\"IntFreq\":{\"Cal\":\"" + param[38] + "\",\"Type\":\"" + param[39] + "\",\"StartDt\":\"" + param[40] + "\",\"WeekDay\":\"" + param[41] + "\",\"HolStat\":\"" + param[42] + "\"},\"IntStartDt\":\"" + param[43] + "\"}]},\"LoanAmt\":{\"amountValue\":\"" + param[44] + "\",\"currencyCode\":\"" + param[45] + "\"}},\"AccountInterestTax\":{\"BasicIntrst\":{\"AcctDrPrefPcnt\":{\"value\":\"" + param[46] + "\"},\"CustDrPrefPcnt\":{\"value\":\"" + param[47] + "\"}}},\"LoanAcctAdd_CustomData\":{\"PENALRATEMTHFLG\":\"" + param[48] + "\"}}}}";
                        if(datanum.equalsIgnoreCase("EIonly")){
                            //repmtrecData = "[{\"InstallmentId\":\"" + reptdata[0] + "\",\"InstallStartDt\":\"" + reptdata[1] + "\",\"InstallFreq\":{\"Cal\":\"" + reptdata[2] + "\",\"Type\":\"" + reptdata[3] + "\",\"StartDt\":\"" + reptdata[4] + "\",\"WeekDay\":\"" + reptdata[5] + "\",\"HolStat\":\"" + reptdata[6] + "\"},\"IntFreq\":{\"Cal\":\"" + reptdata[7] + "\",\"Type\":\"" + reptdata[8] + "\",\"StartDt\":\"" + reptdata[9] + "\",\"WeekDay\":\"" + reptdata[10] + "\",\"HolStat\":\"" + reptdata[11] + "\"},\"NoOfInstall\":\"" + reptdata[12] + "\",\"IntStartDt\":\"" + reptdata[13] + "\"}]";
                            repmtrecData = "[{\"InstallmentId\":\"" + reptdata[0] + "\",\"InstallStartDt\":\"" + reptdata[1] + "\",\"InstallFreq\":{\"Cal\":\"" + reptdata[2] + "\",\"Type\":\"" + reptdata[3] + "\",\"StartDt\":\"" + reptdata[4] + "\",\"WeekDay\":\"" + reptdata[5] + "\",\"HolStat\":\"" + reptdata[6] + "\"},\"IntFreq\":{\"Cal\":\"" + reptdata[7] + "\",\"Type\":\"" + reptdata[8] + "\",\"StartDt\":\"" + reptdata[9] + "\",\"WeekDay\":\"" + reptdata[10] + "\",\"HolStat\":\"" + reptdata[11] + "\"},\"NoOfInstall\":\"" + reptdata[12] + "\"}]";
                            
                            LogMessages.statusLog.info(":repmtrecData " + repmtrecData);
                        }
                        else if(datanum.equalsIgnoreCase("NonEI")){
                            JSONParser parser = new JSONParser();
                            JSONArray outputArr= (JSONArray)iFormObj.getDataFromGrid ("tblCustomizedInstallmentLoanacc");
                            int outputArrLen = outputArr.size();
                            JSONArray tmpJsonArr = new JSONArray();
                            
                            try{
                               
                                    String InterestStartDate = param[62];
                                    //LogMessages.statusLog.info("else if Interest Date ::  " + InterestStartDate);
                                    String InterestStartDay = InterestStartDate.split("/")[0];
                                    //LogMessages.statusLog.info("else if InstallmentStartDay ::  " + InterestStartDay);
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    Date DateInterestStart = sdf.parse(InterestStartDate);
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    String InterestStartDateFormat = formatter.format(DateInterestStart) + "T00:00:00";
                                    
                                for(int i=0; i<outputArr.size(); i++){

                                    JSONObject obj2 = (JSONObject) outputArr.get(i);
                                    String ParentFacility = String.valueOf(obj2.get("Parent Facility"));
                                    LogMessages.statusLog.info("else if parent facility ::  " + ParentFacility);
                                    LogMessages.statusLog.info("else if param[33] ::  " + param[33]);
                                    if(ParentFacility.equalsIgnoreCase(param[33]))
                                    {
                                        String FrequencyType = String.valueOf(obj2.get("Frequency"));
                                        String InstallmentStartDate = String.valueOf(obj2.get("Installment Start Date"));
                                       
                                        String InstallmentStartDay = InstallmentStartDate.split("/")[0];
                                        
                                        Date DateInstallmentStart = sdf.parse(InstallmentStartDate);
                                        String InstallmentStartDateFormat = formatter.format(DateInstallmentStart) + "T00:00:00";

                                        String Amount = String.valueOf(obj2.get("Amount"));

                                        String NoOfFlows = String.valueOf(obj2.get("No. of Flows"));

                                        String prdemNode = "{'InstallmentId':'PRDEM','InstallStartDt':'" + InstallmentStartDateFormat + "','InstallFreq':{'Cal':'00','Type':'" + FrequencyType + "','StartDt':'" + InstallmentStartDay + "','WeekDay':'0','HolStat':'N'},'IntFreq':{'Cal':'00','Type':'" + FrequencyType + "','StartDt':'" + InterestStartDay + "','WeekDay':'0','HolStat':'N'},'NoOfInstall':'" + NoOfFlows +  "','FlowAmt':{'amountValue':'" + Amount +  "','currencyCode':'NPR'},'IntStartDt':'"+ InterestStartDateFormat +"'}";
                                        tmpJsonArr.add(prdemNode);
                                        //LogMessages.statusLog.info(":else if  Loop " + tmpJsonArr.toJSONString().replaceAll("\\[\"", "\\[").replaceAll("\"\\]", "\\]").replaceAll("\",\"", ","));
                                        LogMessages.statusLog.info(" Value of i :: " + i + " ParentFacility :: " + ParentFacility);
                                    }
                                }
                                String IndemNode = "{'InstallmentId':'INDEM','InstallStartDt':'" + InterestStartDateFormat + "','InstallFreq':{'Cal':'00','Type':'" + param[18] + "','StartDt':'" + InterestStartDay + "','WeekDay':'0','HolStat':'N'},'IntFreq':{'Cal':'00','Type':'" + param[18] + "','StartDt':'" + InterestStartDay + "','WeekDay':'0','HolStat':'N'},'IntStartDt':'"+ InterestStartDateFormat +"'}";
                                tmpJsonArr.add(IndemNode);
                            }catch(Exception ex){
                                LogMessages.statusLog.info("else if Exception loanAccountCreationReq:  " + ex);
                                return "Error in Non EI data input";
                            }
                            
                                    //replace [" with [
                                    //replace "] with ]
                                    //replace "," with ,
                            //repmtrecData = "[{\"InstallmentId\":\"PRDEM\",\"InstallStartDt\":\"" + stringArray[0] + "\",\"InstallFreq\":{\"Cal\":\"00\",\"Type\":\"" + stringArray[3] + "\",\"StartDt\":\"" + stringArray[1] + "\",\"WeekDay\":\"0\",\"HolStat\":\"N\"},\"IntFreq\":{\"Cal\":\"00\",\"Type\":\"" + stringArray[3] + "\",\"StartDt\":\"" + stringArray[1] + "\",\"WeekDay\":\"0\",\"HolStat\":\"N\"},\"NoOfInstall\":\"" + stringArray[2] +  "\",\"FlowAmt\":{\"amountValue\": \"" + stringArray[4] +  "\",\"currencyCode\": \"NPR\"},\"IntStartDt\":\"2099-12-31T00:00:00.000\"}]";
                            repmtrecData = tmpJsonArr.toJSONString().replaceAll("\\[\"", "\\[").replaceAll("\"\\]", "\\]").replaceAll("\",\"", ",").replaceAll("'","\"");
                            LogMessages.statusLog.info(":else if repmtrecData" + repmtrecData);
                        
                        }
                        else{
                           
                            //repmtrecData = "[{\"InstallmentId\":\"" + reptdata[0] + "\",\"InstallStartDt\":\"" + reptdata[1] + "\",\"InstallFreq\":{\"Cal\":\"" + reptdata[2] + "\",\"Type\":\"" + reptdata[3] + "\",\"StartDt\":\"" + reptdata[4] + "\",\"WeekDay\":\"" + reptdata[5] + "\",\"HolStat\":\"" + reptdata[6] + "\"},\"IntFreq\":{\"Cal\":\"" + reptdata[7] + "\",\"Type\":\"" + reptdata[8] + "\",\"StartDt\":\"" + reptdata[9] + "\",\"WeekDay\":\"" + reptdata[10] + "\",\"HolStat\":\"" + reptdata[11] + "\"},\"NoOfInstall\":\"" + reptdata[12] + "\",\"IntStartDt\":\"" + reptdata[13] + "\"},{\"InstallmentId\":\"" + reptdata[14] + "\",\"InstallStartDt\":\"" + reptdata[15] + "\",\"InstallFreq\":{\"Cal\":\"" + reptdata[16] + "\",\"Type\":\"" + reptdata[17] + "\",\"StartDt\":\"" + reptdata[18] + "\",\"WeekDay\":\"" + reptdata[19] + "\",\"HolStat\":\"" + reptdata[20] + "\"},\"IntFreq\":{\"Cal\":\"" + reptdata[21] + "\",\"Type\":\"" + reptdata[22] + "\",\"StartDt\":\"" + reptdata[23] + "\",\"WeekDay\":\"" + reptdata[24] + "\",\"HolStat\":\"" + reptdata[25] + "\"},\"NoOfInstall\":\"" + reptdata[26] + "\"}]"; 
                            repmtrecData = "[{\"InstallmentId\":\"" + reptdata[0] + "\",\"InstallStartDt\":\"" + reptdata[1] + "\",\"InstallFreq\":{\"Cal\":\"" + reptdata[2] + "\",\"Type\":\"" + reptdata[3] + "\",\"StartDt\":\"" + reptdata[4] + "\",\"WeekDay\":\"" + reptdata[5] + "\",\"HolStat\":\"" + reptdata[6] + "\"},\"IntFreq\":{\"Cal\":\"" + reptdata[7] + "\",\"Type\":\"" + reptdata[8] + "\",\"StartDt\":\"" + reptdata[9] + "\",\"WeekDay\":\"" + reptdata[10] + "\",\"HolStat\":\"" + reptdata[11] + "\"},\"NoOfInstall\":\"" + reptdata[12] +  "\"},{\"InstallmentId\":\"" + reptdata[14] + "\",\"InstallStartDt\":\"" + reptdata[15] + "\",\"InstallFreq\":{\"Cal\":\"" + reptdata[16] + "\",\"Type\":\"" + reptdata[17] + "\",\"StartDt\":\"" + reptdata[18] + "\",\"WeekDay\":\"" + reptdata[19] + "\",\"HolStat\":\"" + reptdata[20] + "\"},\"IntFreq\":{\"Cal\":\"" + reptdata[21] + "\",\"Type\":\"" + reptdata[22] + "\",\"StartDt\":\"" + reptdata[23] + "\",\"WeekDay\":\"" + reptdata[24] + "\",\"HolStat\":\"" + reptdata[25] + "\"},\"NoOfInstall\":\"" + reptdata[26] + "\"}]"; 

                            LogMessages.statusLog.info(":else  repmtrecData " + repmtrecData);
                        }
                
                if(datanum.equalsIgnoreCase("NonEI")){ // Without Holiday period
                    request = "{\"LoanAcctAddRequest\":{\"LoanAcctAddRq\":{\"CustId\":{\"CustId\":\"" + param[0] + "\"},\"LoanAcctId\":{\"AcctType\":{\"SchmCode\":\"" + param[1] + "\",\"SchmType\":\"" + param[2] + "\"},\"AcctCurr\":\"" + param[3] + "\",\"BankInfo\":{\"BranchId\":\"" + param[4] + "\"}},\"LoanAcctGenInfo\":{\"GenLedgerSubHead\":{\"GenLedgerSubHeadCode\":\"" + param[5] + "\",\"CurCode\":\"" + param[6] + "\"},\"AcctStmtMode\":\"" + param[7] + "\",\"DespatchMode\":\"" + param[8] + "\"},\"LoanGenDetails\":{\"LoanPeriodDays\":\"" + param[9] + "\",\"LoanPeriodMonths\":\"" + param[10] + "\",\"OperAcctId\":{\"AcctId\":\"" + param[11] + "\",\"AcctCurr\":\"" + param[12] + "\"},\"PmtPlan\":{\"EqInstallDetails\":{\"EqInstallFlg\":\"" + param[13] + "\"},\"RepmtRec\":"+ repmtrecData +"},\"LoanAmt\":{\"amountValue\":\"" + param[22] + "\",\"currencyCode\":\"" + param[23] + "\"}},\"AccountInterestTax\":{\"BasicIntrst\":{\"AcctDrPrefPcnt\":{\"value\":\"" + param[24] + "\"},\"CustDrPrefPcnt\":{\"value\":\"" + param[25] + "\"},\"IntTblCode\":\"" + param[26] + "\"}},\"LoanAcctAdd_CustomData\":{\"documentDate\":\"" + param[27] + "\",\"reviewDate\":\"" + param[28] + "\",\"sanctionLevelCode\":\"" + param[29] + "\",\"sanctionAuthCode\":\"" + param[30] + "\",\"acctLimitsEntered\":\"" + param[31] + "\",\"limitIdPrefix\":\"" + param[32] + "\",\"limitIdSuffix\":\"" + param[33] + "\",\"acctClassMISCEntered\":\"" + param[34] + "\",\"PENALRATEMTHFLG\":" + param[35] + ",\"freeCode1\":\"" + param[36] + "\",\"freeCode3\":\"" + param[37] + "\",\"freeCode6\":\"" + param[38]+ "\",\"freeCode8\":\"" + param[50]  + "\",\"freeCode9\":\"" + param[39] + "\",\"expiryDate\":\"" + param[40] + "\",\"drawingPowerIndicator\":\"" + param[41] + "\",\"freetext3\":\"" + param[42] + "\",\"freetext6\":\"" + param[43] + "\",\"freetext10\":\"" + param[44] + "\",\"industryType\":\"" + param[45] + "\",\"sectorCode\":\"" + param[46] + "\",\"subSectorCode\":\"" + param[47] + "\",\"deprivedSector\":\"" + param[48] + "\",\"sanctionLimit\":\"" + param[49]+ "\",\"freeCode2\":\"" + param[51]+ "\",\"freeCode4\":\"" + param[52]+ "\",\"freeCode5\":\"" + param[53]+ "\",\"freeCode7\":\"" + param[54]+ "\",\"freeCode10\":\"" + param[55] + "\",\"borrowerCategory\":\"" + param[56]+ "\",\"purposeOfAdvance\":\"" + param[57]+ "\",\"modeOfAdvance\":\"" + param[58]+ "\",\"natureOfAdvance\":\"" + param[59]+ "\",\"guaranteeCoverCode\":\"" + param[60] + "\",\"creditFileNumber\":\"" + param[61] + "\"}}}}";
                }else{                                 // With Holiday period
                    request = "{\"LoanAcctAddRequest\":{\"LoanAcctAddRq\":{\"CustId\":{\"CustId\":\"" + param[0] + "\"},\"LoanAcctId\":{\"AcctType\":{\"SchmCode\":\"" + param[1] + "\",\"SchmType\":\"" + param[2] + "\"},\"AcctCurr\":\"" + param[3] + "\",\"BankInfo\":{\"BranchId\":\"" + param[4] + "\"}},\"LoanAcctGenInfo\":{\"GenLedgerSubHead\":{\"GenLedgerSubHeadCode\":\"" + param[5] + "\",\"CurCode\":\"" + param[6] + "\"},\"AcctStmtMode\":\"" + param[7] + "\",\"DespatchMode\":\"" + param[8] + "\"},\"LoanGenDetails\":{\"LoanPeriodDays\":\"" + param[9] + "\",\"LoanPeriodMonths\":\"" + param[10] + "\",\"OperAcctId\":{\"AcctId\":\"" + param[11] + "\",\"AcctCurr\":\"" + param[12] + "\"},\"PmtPlan\":{\"EqInstallDetails\":{\"EqInstallFlg\":\"" + param[13] + "\"},\"HolidayPeriodDetails\":{\"HolidayPeriod\":\"" + param[14] + "\",\"AutoReschedPostHolidayPeriod\":\"" + param[15] + "\",\"IntDuringHolidayPeriod\":\"" + param[16] + "\",\"IntFreq\":{\"Cal\":\"" + param[17] + "\",\"Type\":\"" + param[18] + "\",\"StartDt\":\"" + param[19] + "\",\"WeekDay\":\"" + param[20] + "\",\"HolStat\":\"" + param[21] + "\"}},\"RepmtRec\":"+ repmtrecData +"},\"LoanAmt\":{\"amountValue\":\"" + param[22] + "\",\"currencyCode\":\"" + param[23] + "\"}},\"AccountInterestTax\":{\"BasicIntrst\":{\"AcctDrPrefPcnt\":{\"value\":\"" + param[24] + "\"},\"CustDrPrefPcnt\":{\"value\":\"" + param[25] + "\"},\"IntTblCode\":\"" + param[26] + "\"}},\"LoanAcctAdd_CustomData\":{\"documentDate\":\"" + param[27] + "\",\"reviewDate\":\"" + param[28] + "\",\"sanctionLevelCode\":\"" + param[29] + "\",\"sanctionAuthCode\":\"" + param[30] + "\",\"acctLimitsEntered\":\"" + param[31] + "\",\"limitIdPrefix\":\"" + param[32] + "\",\"limitIdSuffix\":\"" + param[33] + "\",\"acctClassMISCEntered\":\"" + param[34] + "\",\"PENALRATEMTHFLG\":" + param[35] + ",\"freeCode1\":\"" + param[36] + "\",\"freeCode3\":\"" + param[37] + "\",\"freeCode6\":\"" + param[38]+ "\",\"freeCode8\":\"" + param[50]  + "\",\"freeCode9\":\"" + param[39] + "\",\"expiryDate\":\"" + param[40] + "\",\"drawingPowerIndicator\":\"" + param[41] + "\",\"freetext3\":\"" + param[42] + "\",\"freetext6\":\"" + param[43] + "\",\"freetext10\":\"" + param[44] + "\",\"industryType\":\"" + param[45] + "\",\"sectorCode\":\"" + param[46] + "\",\"subSectorCode\":\"" + param[47] + "\",\"deprivedSector\":\"" + param[48] + "\",\"sanctionLimit\":\"" + param[49]+ "\",\"freeCode2\":\"" + param[51]+ "\",\"freeCode4\":\"" + param[52]+ "\",\"freeCode5\":\"" + param[53]+ "\",\"freeCode7\":\"" + param[54]+ "\",\"freeCode10\":\"" + param[55] + "\",\"borrowerCategory\":\"" + param[56]+ "\",\"purposeOfAdvance\":\"" + param[57]+ "\",\"modeOfAdvance\":\"" + param[58]+ "\",\"natureOfAdvance\":\"" + param[59]+ "\",\"guaranteeCoverCode\":\"" + param[60] + "\",\"creditFileNumber\":\"" + param[61] + "\"}}}}";
                }
                LogMessages.statusLog.info("Request loanAccountCreationReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception loanAccountCreationReq:  " + e);
		}
		return request;
	}
    
        
        
        public JSONArray loanAccountCreationRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("loanAccountCreationRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
                                    LogMessages.statusLog.info("Exception loanAccountCreationRes: " + code);
				String LoanAcctAddResponse = String.valueOf(((JSONObject) obj.get("Data")).get("LoanAcctAddResponse"));
                                LogMessages.statusLog.info("Exception loanAccountCreationRes: " + LoanAcctAddResponse);
				JSONObject obj2 = (JSONObject) parser.parse(LoanAcctAddResponse);
                                String LoanAcctAddRs = String.valueOf(((JSONObject) obj2.get("LoanAcctAddRs")).get("AcctId"));
                                LogMessages.statusLog.info("Exception loanAccountCreationRes: " + LoanAcctAddRs);
				JSONObject obj3 = (JSONObject) parser.parse(LoanAcctAddRs);
                                String AcctOpenDt = String.valueOf( obj3.get("AcctId"));
                                LogMessages.statusLog.info("Exception loanAccountCreationRes: " + AcctOpenDt);
				
				res.put("AcctId", AcctOpenDt);
                                res.put("status", "SUCCESS");
                                
				response.add(res);
                                
                                LogMessages.statusLog.info(" loanAccountCreationRes with param 1: " + response);
				
				}else {
					res.put("status", "FAILURE");
					String message = String.valueOf(obj.get("Message"));
					res.put("message", message);
					response.add(res);
                                        response.add(param[1]);
				}
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception loanAccountCreationRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else loanAccountCreationRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception loanAccountCreationRes: " + e);
			}

		}
		return response;
	}
}
