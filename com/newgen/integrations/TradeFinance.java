/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.APIConsumption;
import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author NITRO 5
 */
public class TradeFinance {
    
    	public String retTradeFinanceReq(String input, IFormReference iFormObj) {
                
		String request = "",request2="",request3;
		String[] param = input.split("#");
		try {
			LogMessages.statusLog.info("Inside create Request of TradeFinance." + param[0]);
                    String AddConfirmation = null;
                    String AdditionalCondition1 = null;
                    String AdditionalCondition2 = null;
                    String AdditionalCondition3 = null;
                    String AdditionalCondition4 = null;
                    String Addr1a = null;
                    String Addr1b = null;
                    String Addr1c = null;
                    String Addr1d = null;
                    String Addr1e = null;
                    String Addr1f = null;
                    String Addr2a = null;
                    String Addr2b = null;
                    String Addr2c = null;
                    String Addr2d = null;
                    String dcSolId = "01";
                    String Addr2e = null;
                    String Addr2f = null;
                    String Addr3a = null;
                    String Addr3b = null;
                    String Addr3c = null;
                    String Addr3d = null;
                    String Addr3e = null;
                    String Addr3f = null;
                    String AdvBankCode= null;
                    String AdvBranchCode= null;
                    String AdviseBICCode = null;
                    String AgentCode = null;
                    String AutoReinstateFlg= null;
                    String AutoScheduleApplicableFlg = null;
                    String AvailBankCode = null;
                    String AvailBranchCode = null;
                    String AvailableBICCode = null;
                    String AvblByGenCode = null;
                    String BankAddrTypeInd = null;
                    String BankAddrTypeInd2 = null;
                    String CityRefCode1 = null;
                    String CityRefCode2 = null;
                    String CityRefCode3 = null;
                    String CityRefCode4 = null;
                    String CityRefCode5 = null;
                    String CityRefCode6= null;
                    String CollAutoReinsChrgs= null;
                    String CommCodeRefCode = null;
                    String CountryRefCode1 = null;
                    String CountryRefCode2 = null;
                    String CountryRefCode3 = null;
                    String CountryRefCode4 = null;
                    String CountryRefCode5 = null;
                    String CountryRefCode6 = null;
                    String CountryRefCode7 = null;
                    String DcAcctId = null;
                    String DcApplicableRule = null;
                    String DcApplicableSubRule = null;
                    String DcApplicantRefNum = null;
                    String DcAvailAmtCrncy = null;
                    String DcBackToBackRefNum = null;
                    String DcConfPcnt = null;
                    String DcConfReqdFlg = null;
                    String DcConfReqdBy = null;
                    String DcCrncyCode = "NPR";
                    String DcCurrentAmtCrncy = null;
                    String DcExpiryDt = null;
                    String DcIntDet = null;
                    String DcIssueDate = null;
                    String DcMaxCrAmt = null;
                    String DcNegotPeriod = null;
                    String DcOpenValueCrncy = "NPR";
                    String DcOtherBankRefNum = null;
                    String DcPartyCodeCifId = "233277793";
                    String DcPlaceOfExpiry = null;
                    String DcPordNum = null;
                    String DcPordtotalAmendNum = null;
                    String DcPreShipmentMarginPcnt = null;
                    String DcTenor = null;
                    String DcUsancePeriod = null;
                    String DespatchDestin = null;
                    String DespatchOrigin = null;
                    String DocRequired1 = null;
                    String DocRequired2 = null;
                    String DocRequired3 = null;
                    String DocRequired4 = null;
                    String DraweeBICCode = null;
                    String DraweeBankCode = null;
                    String DraweeBranchCode = null;
                    String DueDateInd = null;
                    String FwdContractNum = null;
                    String FwdContractSolId = null;
                    String GoodsAndServiesDesc1 = null;
                    String GoodsAndServiesDesc2 = null;
                    String GoodsAndServiesDesc3 = null;
                    String GoodsAndServiesDesc4 = null;
                    String HouseAirBillFlg = null;
                    String IssueBICCode = null;
                    String IssueBankCode = null;
                    String IssueBankRefNum = null;
                    String IssueBranchCode = null;
                    String LastShipDt = null;
                    String LicenceCodeRefCode = null;
                    String LicenceDesc = null;
                    String LimitPrefix = "233277793";
                    String LimitSuffix = "DCRE";
                    String MarginType = "C";
                    String MaxReinstmt = null;
                    String Name1 = null;
                    String Name2= null;
                    String Name3= null;
                    String Name4 = null;
                    String Name5 = null;
                    String Name6 = null;
                    String OpenEndedDc = null;
                    String OperativeAcctID = "02301017503597";
                    String OriginofGoodsRefCode = null;
                    String OriginofGoodsRefDesc = null;
                    String PartShipFlg = null;
                    String PaySisIdRefCode= null;
                    String PeriodOfPresentation1 = null;
                    String PeriodOfPresentation2 = null;
                    String PeriodOfPresentation3 = null;
                    String PeriodOfPresentation4 = null;
                    String PinCode1 = null;
                    String PinCode3 = null;
                    String PinCode4 = null;
                    String PinCode5 = null;
                    String PinCode6 = null;
                    String PortDestin = null;
                    String PortOrigin = null;
                    String PreAdviseFlg = null;
                    String PreAdviseRefNum = null;
                    String ReinstmtDay = null;
                    String ReinstmtParticulars = null;
                    String ReinstmtType = null;
                    String SerialNum = null;
                    String SerialNum1 = null;
                    String SerialNum2 = null;
                    String SerialNum3 = null;
                    String ShipModeRefCode = null;
                    String ShipTermRefCode = null;
                    String ShipTermRefDesc = null;
                    String ShipmentPeriod1 = null;
                    String ShipmentPeriod2 = null;
                    String ShipmentPeriod3 = null;
                    String ShipmentPeriod4= null;
                    String StateRefCode1 = null;
                    String StateRefCode2= null;
                    String StateRefCode3 = null;
                    String StateRefCode4 = null;
                    String StateRefCode5 = null;
                    String StateRefCode6 = null;
                    String TenorDtls1 = null;
                    String TenorDtls2 = null;
                    String TenorDtls3= null;
                    String TenorDtls4 = null;
                    String TextType1 = null;
                    String TextType2 = null;
                    String TextType3 = null;
                    String TextType4 = null;
                    String TransShipFlg= null;
                    String amountValue1 = "2983062.50";
                    String amountValue10 = null;
                    String amountValue2 = null;
                    String amountValue3 = null;
                    String amountValue4 = null;
                    String amountValue5 = null;
                    String amountValue6 = null;
                    String amountValue7 = null;
                    String amountValue8 = null;
                    String amountValue9 = null;
                    String currencyCode1 = "NPR";
                    String currencyCode10 = null;
                    String currencyCode2 = null;
                    String currencyCode3 = null;
                    String currencyCode4 = null;
                    String currencyCode5 = null;
                    String currencyCode6 = null;
                    String currencyCode7 = null;
                    String currencyCode8 = null;
                    String currencyCode9 = null;
                    String dcRegType = "IMSF";
                    String value1 = "5";
                    String value2 = "5";
                    String value3 = null;
			request = "{\"IssueOutwardDcDtlAddRequest\":{\"IssueOutwardDcDtlAddRq\":{\"DcOutCreditDtl\":{\"DcSolId\":\"" + dcSolId + "\",\"DcRegType\":\"" + dcRegType + "\",\"DcPartyCodeCifId\":\"" + DcPartyCodeCifId + "\",\"DcCrncyCode\":\"" + DcCrncyCode + "\"},\"LimitId\":{\"LimitPrefix\":\"" + LimitPrefix + "\",\"LimitSuffix\":\"" + LimitSuffix + "\"},\"MarginRec\":{\"MarginType\":\"" + MarginType + "\",\"OperativeAcctID\":\"" + OperativeAcctID + "\"},\"DcOutGenDetail\":{\"DcAmtDtl\":{\"DcOpenValueCrncy\":\"" + DcOpenValueCrncy + "\",\"DcOpenValue\":{\"amountValue\":\"" + amountValue1 + "\",\"currencyCode\":\"" + currencyCode1 + "\"},\"DcCurrentAmtCrncy\":\"" + DcCurrentAmtCrncy + "\",\"DcCurrentAmt\":{\"amountValue\":\"" + amountValue2 + "\",\"currencyCode\":\"" + currencyCode2 + "\"},\"DcAvailAmtCrncy\":\"" + DcAvailAmtCrncy + "\",\"DcAvailAmt\":{\"amountValue\":\"" + amountValue3 + "\",\"currencyCode\":\"" + currencyCode3 + "\"},\"DcIntDet\"" + DcIntDet + "\",\"DcMaxCrAmt\"" + DcMaxCrAmt + "\",\"DcTolerancePcnt\":{\"value\":\"" + value1 + "\"},\"DcNegtvTolerancePcnt\":{\"value\":\"" + value2 +"\"},\"DcIssueDate\":\"" + DcIssueDate +"\",\"DcAdvanceAmt\":{\"amountValue\":\"" + amountValue4 +"\",\"currencyCode\":\"" + currencyCode4 +"\"},\"DcIntPcnt\":{\"value\":\"" + value3 +"\"},\"DcAddlnIntAmt\":{\"amountValue\":\"" + amountValue5 +"\",\"currencyCode\":\"" + currencyCode5 +"\"}},\"FwdContractNum\":\"" + FwdContractNum +"\",\"DcPreShipmentMarginPcnt\":\"" + DcPreShipmentMarginPcnt +"\",\"DcPreShipMaxAdviseAmt\":{\"amountValue\":\"" + amountValue6 +"\",\"currencyCode\":\"" + currencyCode6 +"\"},\"DcApplicantRefNum\":\"" + DcApplicantRefNum +"\",\"DcPordDtl\":{\"DcPordNum\":\"" + DcPordNum +"\",\"DcPordtotalAmendNum\":\"" + DcPordtotalAmendNum +"\"},\"DcPreAdviseDtl\":{\"PreAdviseRefNum\":\"" + PreAdviseRefNum +"\",\"PreAdviseFlg\":\"" + PreAdviseFlg +"\"},\"FwdContractSolId\":\"" + FwdContractSolId +"\",\"DcAcctId\":\"" + DcAcctId +"\",\"DcOtherBankRefNum\":\"" + DcOtherBankRefNum +"\",\"DcApplParty\":{\"ApplicantPartyNameAddr\":{\"Name\":\"" + Name1 +"\",\"Addr1\":\"" + Addr1a +"\",\"Addr2\":\"" + Addr2a +"\",\"Addr3\":\"" + Addr3a +"\",\"CityRefCode\":\"" + CityRefCode1 +"\",\"StateRefCode\":\"" + StateRefCode1 +"\",\"CountryRefCode\":\"" + CountryRefCode1 +"\",\"PinCode\":\"" + PinCode1 +"\"}},\"PaySisIdRefCode\":\"" + PaySisIdRefCode +"\"},\"DcPartyDtl\":{\"BeneficiaryParty\":{\"BeneficiaryPartyNameAddr\":{\"Name\":\"" + Name2 +"\",\"Addr1\":\"" + Addr1b +"\",\",\"Addr2\":\"" + Addr2b +"\",\"Addr3\":\"" + Addr3b +"\",\"CityRefCode\":\"" + CityRefCode2 +"\",\"StateRefCode\":\"" + StateRefCode2 +"\",\"CountryRefCode\":\"" + CountryRefCode2 +"\",\"PinCode\":\"" + CountryRefCode3 +"\"}},\"IssueBank\":{\"IssueBankNameAddr\":{\"Name\":\"" + Name3 +"\",\"Addr1\":\"" + Addr1c +"\",\"Addr2\":\"" + Addr2c +"\",\"Addr3\":\"" + Addr3c +"\",\"CityRefCode\":\"" + CityRefCode3 +"\",\"StateRefCode\":\"" + StateRefCode3 +"\",\"CountryRefCode\":\"" + CountryRefCode4 +"\",\"PinCode\":\"" + PinCode3 +"\"}},\"IssueBankRefNum\":\"" + IssueBankRefNum +"\",\"DraweeBank\":{\"BankAddrTypeInd\":\"" + BankAddrTypeInd +"\",\"DraweeBankNameAddr\":{\"Name\":\"" + Name4 +"\",\"Addr1\":\"" + Addr1d +"\",\"Addr2\":\"" + Addr2d +"\",\"Addr3\":\"" + Addr3d +"\",\"CityRefCode\":\"" + CityRefCode4 +"\",\"StateRefCode\":\"" + StateRefCode4 +"\",\"CountryRefCode\":\"" + CountryRefCode5 +"\",\"PinCode\":\"" + PinCode4 +"\"},\"DraweeBICCode\":\"" + DraweeBICCode +"\"},\"AdvisingBank\":{\"BankAddrTypeInd\":\"" + BankAddrTypeInd +"\",\"AdvisingBankNameAddr\":{\"Name\":\"" + Name5 +"\",\"Addr1\":\"" + Addr1e +"\",\"Addr2\":\"" + Addr2e +"\",\"Addr3\":\"" + Addr3e +"\",\"CityRefCode\":\"" + CityRefCode5 +"\",\"StateRefCode\":\"" + StateRefCode5 +"\",\"CountryRefCode\":\"" + CountryRefCode6 +"\",\"PinCode\":\"" + PinCode5 +"\"},\"AdviseBICCode\":\"" + AdviseBICCode +"\"},\"AvailableBank\":{\"BankAddrTypeInd\":\"" + BankAddrTypeInd2 +"\",\"AvailableBankNameAddr\":{\"Name\":\"" + Name6 +"\",\"Addr1\":\"" + Addr1f +"\",\"Addr2\":\"" + Addr2f +"\",\"Addr3\":\"" + Addr3f +"\",\"CityRefCode\":\"" + CityRefCode6 +"\",\"StateRefCode\":\"" + StateRefCode6 +"\",\"CountryRefCode\":\"" + CountryRefCode7 +"\",\"PinCode\":\"" + PinCode6 +"\"},\"AvailableBICCode\":\"" + AvailableBICCode +"\"},\"AvblByGenCode\":\"" + AvblByGenCode +"\"},\"DcAddlnDtl\":{\"DcShipmentDtl\":{\"ShipModeRefCode\":\"" + ShipModeRefCode +"\",\"ShipTermRefCode\":\"" + ShipTermRefCode +"\",\"ShipTermRefDesc\":\"" + ShipTermRefDesc +"\",\"DespatchOrigin\":\"" + DespatchOrigin +"\",\"PortOrigin\":\"" + PortOrigin +"\",\"PortDestin\":\"" + PortDestin +"\",\"DespatchDestin\":\"" + DespatchDestin +"\",\"HouseAirBillFlg\":\"" + HouseAirBillFlg +"\",\"LastShipDt\":\"" + LastShipDt +"\",\"PartShipFlg\":\"" + PartShipFlg +"\",\"TransShipFlg\":\"" + TransShipFlg +"\",\"DcNegotPeriod\":\"" + DcNegotPeriod +"\",\"AgentCode\":\"" + AgentCode +"\"},\"DcCommodityDtl\":{\"OriginofGoodsRefCode\":\"" + OriginofGoodsRefCode +"\",\"CommCodeRefCode\":\"" + CommCodeRefCode +"\",\"OriginofGoodsRefDesc\":\"" + OriginofGoodsRefDesc +"\",\"LicenceCodeRefCode\":\"" + LicenceCodeRefCode +"\",\"LicenceDesc\":\"" + LicenceDesc +"\"},\"DcConfReqdFlg\":\"" + DcConfReqdFlg +"\",\"DcConfReqdBy\":\"" + DcConfReqdBy +"\",\"AddConfirmation\":\"" + AddConfirmation +"\",\"DcConfAmt\":{\"amountValue\":\"" + amountValue7 +"\",\"currencyCode\":\"" + currencyCode7 +"\"},\"DcConfPcnt\":\"" + DcConfPcnt +"\",\"TotalDcConfAmt\":{\"amountValue\":\"" + amountValue8 +"\",\"currencyCode\":\"" + currencyCode8 +"\"},\"DcRevolvingDtl\":{\"ReinstmtType\":\"" + ReinstmtType +"\",\"AutoReinstateFlg\":\"" + AutoReinstateFlg +"\",\"CollAutoReinsChrgs\":\"" + CollAutoReinsChrgs +"\",\"MaxReinstmt\":\"" + MaxReinstmt +"\",\"ReinstByAmt\":{\"amountValue\":\"" + amountValue9 +"\",\"currencyCode\":\"" + currencyCode9 +"\"},\"ReinstmtParticulars\":\"" + ReinstmtParticulars +"\",\"ReinstmtAmt\":{\"amountValue\":\"" + amountValue10 +"\",\"currencyCode\":\"" + currencyCode10 +"\"},\"ReinstmtDay\":\"" + ReinstmtDay +"\"},\"DcBackToBackRefNum\":\"" + DcBackToBackRefNum +"\",\"DcApplicableRule\":\"" + DcApplicableRule +"\",\"DcApplicableSubRule\":\"" + DcApplicableSubRule +"\",\"DcTenorDtl\":{\"DcTenor\":\"" + DcTenor +"\",\"DcUsancePeriod\":\"" + DcUsancePeriod +"\",\"DueDateInd\":\"" + DueDateInd +"\"},\"DcExpiryDt\":\"" + DcExpiryDt +"\",\"DcPlaceOfExpiry\":\"" + DcPlaceOfExpiry +"\",\"OpenEndedDc\":\"" + OpenEndedDc +"\"},\"AutoScheduleApplicableFlg\":\"" + AutoScheduleApplicableFlg +"\",\"TextRec\":[{\"GoodsAndServiesDesc\":\"" + GoodsAndServiesDesc1 +"\",\"ShipmentPeriod\":\"" + ShipmentPeriod1 +"\",\"DocRequired\":\"" + DocRequired1 +"\",\"AdditionalCondition\":\"" + AdditionalCondition1 +"\",\"TenorDtls\":\"" + TenorDtls1 +"\",\"PeriodOfPresentation\":\"" + PeriodOfPresentation1 +"\",\"TextType\":\"" + TextType1 +"\",\"SerialNum\":\"" + SerialNum1 +"\"},{\"GoodsAndServiesDesc\":\"" + GoodsAndServiesDesc2 +"\",\"ShipmentPeriod\":\"" + ShipmentPeriod2 +"\",\"DocRequired\":\"" + DocRequired2 +"\",\"AdditionalCondition\":\"" + AdditionalCondition2 +"\",\"TenorDtls\":\"" + TenorDtls2 +"\",\"PeriodOfPresentation\":\"" + PeriodOfPresentation2 +"\",\"TextType\":\"" + TextType2 +"\",\"SerialNum\":\"" + SerialNum2 +"\"},{\"GoodsAndServiesDesc\":\"" + GoodsAndServiesDesc3 +"\",\"ShipmentPeriod\":\"" + ShipmentPeriod3 +"\",\"DocRequired\":\"" + DocRequired3 +"\",\"AdditionalCondition\":\"" + AdditionalCondition3 +"\",\"TenorDtls\":\"" + TenorDtls3 +"\",\"PeriodOfPresentation\":\"" + PeriodOfPresentation3 +"\",\"TextType\":\"" + TextType3 +"\",\"SerialNum\":\"" + SerialNum +"\"},{\"GoodsAndServiesDesc\":\"" + GoodsAndServiesDesc4 +"\",\"ShipmentPeriod\":\"" + ShipmentPeriod4 +"\",\"DocRequired\":\"" + DocRequired4 +"\",\"AdditionalCondition\":\"" + AdditionalCondition4 +"\",\"TenorDtls\":\"" + TenorDtls4 +"\",\"PeriodOfPresentation\":\"" + PeriodOfPresentation4 +"\",\"TextType\":\"" + TextType4 +"\",\"SerialNum\":\"" + SerialNum3 +"\"}],\"IssueBankCode\":\"" + IssueBankCode +"\",\"IssueBranchCode\":\"" + IssueBranchCode +"\",\"DraweeBankCode\":\"" + DraweeBankCode +"\",\"DraweeBranchCode\":\"" + DraweeBranchCode +"\",\"AdvBankCode\":\"" + AdvBankCode +"\",\"AdvBranchCode\":\"" + AdvBranchCode +"\",\"AvailBankCode\":\"" + AvailBankCode +"\",\"AvailBranchCode\":\"" + AvailBranchCode +"\",\"IssueBICCode\":\"" + IssueBICCode +"\"}}}";
                        //request = "{\"IssueOutwardDcDtlAddRequest\":{\"IssueOutwardDcDtlAddRq\":{\"DcOutCreditDtl\":{\"DcSolId\":\"" + dcSolId + "\",\"DcRegType\":\"" + dcRegType + "\",\"DcPartyCodeCifId\":\"" + DcPartyCodeCifId + "\",\"DcCrncyCode\":\"" + DcCrncyCode + "\"},\"LimitId\":{\"LimitPrefix\":\"" + LimitPrefix + "\",\"LimitSuffix\":\"" + LimitSuffix + "\"},\"MarginRec\":{\"MarginType\":\"" + MarginType + "\",\"OperativeAcctID\":\"" + OperativeAcctID + "\"},\"DcOutGenDetail\":{\"DcAmtDtl\":{\"DcOpenValueCrncy\":\"" + DcOpenValueCrncy + "\",\"DcOpenValue\":{\"amountValue\":\"" + amountValue1 + "\",\"currencyCode\":\"" + currencyCode1 + "\"},\"DcCurrentAmtCrncy\":\"" + DcCurrentAmtCrncy + "\",\"DcCurrentAmt\":{\"amountValue\":\"" + amountValue2 + "\",\"currencyCode\":\"" + currencyCode2 + "\"},\"DcAvailAmtCrncy\":\"" + DcAvailAmtCrncy + "\",\"DcAvailAmt\":{\"amountValue\":\"" + amountValue3 + "\",\"currencyCode\":\"" + currencyCode3 + "\"},\"DcIntDet\"" + DcIntDet + "\",\"DcMaxCrAmt\"" + DcMaxCrAmt + "\",\"DcTolerancePcnt\":{\"value\":\"" + value1 + "\"},\"DcNegtvTolerancePcnt\":{\"value\":\"" + value2 +"\"},\"DcIssueDate\":\"" + DcIssueDate +"\",\"DcAdvanceAmt\":{\"amountValue\":\"" + amountValue4 +"\",\"currencyCode\":\"" + currencyCode4 +"\"},\"DcIntPcnt\":{\"value\":\"" + value3 +"\"},\"DcAddlnIntAmt\":{\"amountValue\":\"" + amountValue5 +"\",\"currencyCode\":\"" + currencyCode5 +"\"}},\"FwdContractNum\":\"" + FwdContractNum +"\",\"DcPreShipmentMarginPcnt\":\"" + DcPreShipmentMarginPcnt +"\",\"DcPreShipMaxAdviseAmt\":{\"amountValue\":\"" + amountValue6 +"\",\"currencyCode\":\"" + currencyCode6 +"\"},\"DcApplicantRefNum\":\"" + DcApplicantRefNum +"\",\"DcPordDtl\":{\"DcPordNum\":\"" + DcPordNum +"\",\"DcPordtotalAmendNum\":\"" + DcPordtotalAmendNum +"\"},\"DcPreAdviseDtl\":{\"PreAdviseRefNum\":\"" + PreAdviseRefNum +"\",\"PreAdviseFlg\":\"" + PreAdviseFlg +"\"},\"FwdContractSolId\":\"" + FwdContractSolId +"\",\"DcAcctId\":\"" + DcAcctId +"\",\"DcOtherBankRefNum\":\"" + DcOtherBankRefNum +"\",\"DcApplParty\":{\"ApplicantPartyNameAddr\":{\"Name\":\"" + Name1 +"\",\"Addr1\":\"" + Addr1a +"\",\"Addr2\":\"" + Addr2a +"\",\"Addr3\":\"" + Addr3a +"\",\"CityRefCode\":\"" + CityRefCode1 +"\",\"StateRefCode\":\"" + StateRefCode1 +"\",\"CountryRefCode\":\"" + CountryRefCode1 +"\",\"PinCode\":\"" + PinCode1 +"\"}},\"PaySisIdRefCode\":\"" + PaySisIdRefCode +"\"},\"DcPartyDtl\":{\"BeneficiaryParty\":{\"BeneficiaryPartyNameAddr\":{\"Name\":\"" + Name2 +"\",\"Addr1\":\"" + Addr1b +"\",\",\"Addr2\":\"" + Addr2b +"\",\"Addr3\":\"" + Addr3b +"\",\"CityRefCode\":\"" + CityRefCode2 +"\",\"StateRefCode\":\"" + StateRefCode2 +"\",\"CountryRefCode\":\"" + CountryRefCode2 +"\",\"PinCode\":\"" + CountryRefCode3 +"\"}},\"IssueBank\":{\"IssueBankNameAddr\":{\"Name\":\"" + Name3 +"\",\"Addr1\":\"" + Addr1c +"\",\"Addr2\":\"" + Addr2c +"\",\"Addr3\":\"" + Addr3c +"\",\"CityRefCode\":\"" + CityRefCode3 +"\",\"StateRefCode\":\"" + StateRefCode3 +"\",\"CountryRefCode\":\"" + CountryRefCode4 +"\",\"PinCode\":\"" + PinCode3 +"\"}},\"IssueBankRefNum\":\"" + IssueBankRefNum +"\",\"DraweeBank\":{\"BankAddrTypeInd\":\"" + BankAddrTypeInd +"\",\"DraweeBankNameAddr\":{\"Name\":\"" + Name4 +"\",\"Addr1\":\"" + Addr1d +"\",\"Addr2\":\"" + Addr2d +"\",\"Addr3\":\"" + Addr3d +"\",\"CityRefCode\":\"" + CityRefCode4 +"\",\"StateRefCode\":\"" + StateRefCode4 +"\",\"CountryRefCode\":\"" + CountryRefCode5 +"\",\"PinCode\":\"" + PinCode4 +"\"},\"DraweeBICCode\":\"" + DraweeBICCode +"\"},\"AdvisingBank\":{\"BankAddrTypeInd\":\"" + BankAddrTypeInd +"\",\"AdvisingBankNameAddr\":{\"Name\":\"" + Name5 +"\",\"Addr1\":\"" + Addr1e +"\",\"Addr2\":\"" + Addr2e +"\",\"Addr3\":\"" + Addr3e +"\",\"CityRefCode\":\"" + CityRefCode5 +"\",\"StateRefCode\":\"" + StateRefCode5 +"\",\"CountryRefCode\":\"" + CountryRefCode6 +"\",\"PinCode\":\"" + PinCode5 +"\"},\"AdviseBICCode\":\"" + AdviseBICCode +"\"},\"AvailableBank\":{\"BankAddrTypeInd\":\"" + BankAddrTypeInd2 +"\",\"AvailableBankNameAddr\":{\"Name\":\"" + Name6 +"\",\"Addr1\":\"" + Addr1f +"\",\"Addr2\":\"" + Addr2f +"\",\"Addr3\":\"" + Addr3f +"\",\"CityRefCode\":\"" + CityRefCode6 +"\",\"StateRefCode\":\"" + StateRefCode6 +"\",\"CountryRefCode\":\"" + CountryRefCode7 +"\",\"PinCode\":\"" + PinCode6 +"\"},\"AvailableBICCode\":\"" + AvailableBICCode +"\"},\"AvblByGenCode\":\"" + AvblByGenCode +"\"},\"DcAddlnDtl\":{\"DcShipmentDtl\":{\"ShipModeRefCode\":\"" + ShipModeRefCode +"\",\"ShipTermRefCode\":\"" + ShipTermRefCode +"\",\"ShipTermRefDesc\":\"" + ShipTermRefDesc +"\",\"DespatchOrigin\":\"" + DespatchOrigin +"\",\"PortOrigin\":\"" + PortOrigin +"\",\"PortDestin\":\"" + PortDestin +"\",\"DespatchDestin\":\"" + DespatchDestin +"\",\"HouseAirBillFlg\":\"" + HouseAirBillFlg +"\",\"LastShipDt\":\"" + LastShipDt +"\",\"PartShipFlg\":\"" + PartShipFlg +"\",\"TransShipFlg\":\"" + TransShipFlg +"\",\"DcNegotPeriod\":\"" + DcNegotPeriod +"\",\"AgentCode\":\"" + AgentCode +"\"},\"DcCommodityDtl\":{\"OriginofGoodsRefCode\":\"" + OriginofGoodsRefCode +"\",\"CommCodeRefCode\":\"" + CommCodeRefCode +"\",\"OriginofGoodsRefDesc\":\"" + OriginofGoodsRefDesc +"\",\"LicenceCodeRefCode\":\"" + LicenceCodeRefCode +"\",\"LicenceDesc\":\"" + LicenceDesc +"\"},\"DcConfReqdFlg\":\"" + DcConfReqdFlg +"\",\"DcConfReqdBy\":\"" + DcConfReqdBy +"\",\"AddConfirmation\":\"" + AddConfirmation +"\",\"DcConfAmt\":{\"amountValue\":\"" + amountValue7 +"\",\"currencyCode\":\"" + currencyCode7 +"\"},\"DcConfPcnt\":\"" + DcConfPcnt +"\",\"TotalDcConfAmt\":{\"amountValue\":\"" + amountValue8 +"\",\"currencyCode\":\"" + currencyCode8 +"\"},\"DcRevolvingDtl\":{\"ReinstmtType\":\"" + ReinstmtType +"\",\"AutoReinstateFlg\":\"" + AutoReinstateFlg +"\",\"CollAutoReinsChrgs\":\"" + CollAutoReinsChrgs +"\",\"MaxReinstmt\":\"" + MaxReinstmt +"\",\"ReinstByAmt\":{\"amountValue\":\"" + amountValue9 +"\",\"currencyCode\":\"" + currencyCode9 +"\"},\"ReinstmtParticulars\":\"" + ReinstmtParticulars +"\",\"ReinstmtAmt\":{\"amountValue\":\"" + amountValue10 +"\",\"currencyCode\":\"" + currencyCode10 +"\"},\"ReinstmtDay\":\"" + ReinstmtDay +"\"},\"DcBackToBackRefNum\":\"" + DcBackToBackRefNum +"\",\"DcApplicableRule\":\"" + DcApplicableRule +"\",\"DcApplicableSubRule\":\"" + DcApplicableSubRule +"\",\"DcTenorDtl\":{\"DcTenor\":\"" + DcTenor +"\",\"DcUsancePeriod\":\"" + DcUsancePeriod +"\",\"DueDateInd\":\"" + DueDateInd +"\"},\"DcExpiryDt\":\"" + DcExpiryDt +"\",\"DcPlaceOfExpiry\":\"" + DcPlaceOfExpiry +"\",\"OpenEndedDc\":\"" + OpenEndedDc +"\"},\"AutoScheduleApplicableFlg\":\"" + AutoScheduleApplicableFlg +"\",\"TextRec\":[{\"GoodsAndServiesDesc\":\"" + GoodsAndServiesDesc1 +"\",\"ShipmentPeriod\":\"" + ShipmentPeriod1 +"\",\"DocRequired\":\"" + DocRequired1 +"\",\"AdditionalCondition\":\"" + AdditionalCondition1 +"\",\"TenorDtls\":\"" + TenorDtls1 +"\",\"PeriodOfPresentation\":\"" + PeriodOfPresentation1 +"\",\"TextType\":\"" + TextType1 +"\",\"SerialNum\":\"" + SerialNum1 +"\"},{\"GoodsAndServiesDesc\":\"" + GoodsAndServiesDesc2 +"\",\"ShipmentPeriod\":\"" + ShipmentPeriod2 +"\",\"DocRequired\":\"" + DocRequired2 +"\",\"AdditionalCondition\":\"" + AdditionalCondition2 +"\",\"TenorDtls\":\"" + TenorDtls2 +"\",\"PeriodOfPresentation\":\"" + PeriodOfPresentation2 +"\",\"TextType\":\"" + TextType2 +"\",\"SerialNum\":\"" + SerialNum2 +"\"},{\"GoodsAndServiesDesc\":\"" + GoodsAndServiesDesc3 +"\",\"ShipmentPeriod\":\"" + ShipmentPeriod3 +"\",\"DocRequired\":\"" + DocRequired3 +"\",\"AdditionalCondition\":\"" + AdditionalCondition3 +"\",\"TenorDtls\":\"" + TenorDtls3 +"\",\"PeriodOfPresentation\":\"" + PeriodOfPresentation3 +"\",\"TextType\":\"" + TextType3 +"\",\"SerialNum\":\"" + SerialNum +"\"},{\"GoodsAndServiesDesc\":\"" + GoodsAndServiesDesc4 +"\",\"ShipmentPeriod\":\"" + ShipmentPeriod4 +"\",\"DocRequired\":\"" + DocRequired4 +"\",\"AdditionalCondition\":\"" + AdditionalCondition4 +"\",\"TenorDtls\":\"" + TenorDtls4 +"\",\"PeriodOfPresentation\":\"" + PeriodOfPresentation4 +"\",\"TextType\":\"" + TextType4 +"\",\"SerialNum\":\"" + SerialNum3 +"\"}],\"IssueBankCode\":\"" + IssueBankCode +"\",\"IssueBranchCode\":\"" + IssueBranchCode +"\",\"DraweeBankCode\":\"" + DraweeBankCode +"\",\"DraweeBranchCode\":\"" + DraweeBranchCode +"\",\"AdvBankCode\":\"" + AdvBankCode +"\",\"AdvBranchCode\":\"" + AdvBranchCode +"\",\"AvailBankCode\":\"" + AvailBankCode +"\",\"AvailBranchCode\":\"" + AvailBranchCode +"\",\"IssueBICCode\":\"" + IssueBICCode +"\"}}}";
                        LogMessages.statusLog.info("############TradeFinance REQUEST::::" + request);
                        
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception TradeFinanceReq: " + e);
		}
		return request2;
	}

	public JSONArray retTradeFinanceRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("retTradeFinanceRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
				String addLimitNodeResponse = String.valueOf(((JSONObject) obj.get("Data")).get("addLimitNodeResponse"));
				JSONObject obj2 = (JSONObject) parser.parse(addLimitNodeResponse);
				String retCustDtls = String.valueOf(((JSONObject) obj2.get("AddLimitNodeOutputVO")).get("limitPrefix"));
                                LogMessages.statusLog.info("Exception retTradeFinanceRes: " + retCustDtls);
                                
				res.put("status", "SUCCESS");
				response.add(res);
				}else {
					res.put("status", "FAILURE");
					String message = String.valueOf(obj.get("Message"));
					res.put("message", message);
					response.add(res);
				}
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception retTradeFinanceRes: " + e);
			}

		} else {
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(param[1]);
				String error = String.valueOf(obj);
				//String message = String.valueOf(obj.get("message"));
				res.put("status", error);
				//res.put("message",message);
				LogMessages.statusLog.info("else retTradeFinanceRes");
				response.add(res);
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception retTradeFinanceRes: " + e);
			}

		}
		return response;
	}

}
