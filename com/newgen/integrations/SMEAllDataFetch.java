
package com.newgen.integrations;
import com.newgen.common.LogMessages;
import java.util.List;
import java.util.ListIterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.newgen.iforms.custom.IFormReference;


/**
 *
 * @author Shivaraj Mahato
 */
public class SMEAllDataFetch {
    
     public static String customDate(String para) 
    {
        LogMessages.statusLog.info("Para Date to be changed: " + para);
        String finaldate = "";
        finaldate = para;
//        if (para.length() > 0) 
//        {
//            String day = para.split("/", -1)[0];
//            String monthStr = para.split("/", -1)[1];
//            String month;
//            switch (monthStr) 
//            {
//                case "Jan":
//                    month = "01";
//                    break;
//                case "Feb":
//                    month = "02";
//                    break;
//                case "Mar":
//                    month = "03";
//                    break;
//                case "Apr":
//                    month = "04";
//                    break;
//                case "May":
//                    month = "05";
//                    break;
//                case "Jun":
//                    month = "06";
//                    break;
//                case "Jul":
//                    month = "07";
//                    break;
//                case "Aug":
//                    month = "08";
//                    break;
//                case "Sep":
//                    month = "09";
//                    break;
//                case "Oct":
//                    month = "10";
//                    break;
//                case "Nov":
//                    month = "11";
//                    break;
//                default:
//                    month = "12";
//            }
//            String year = para.split("/", -1)[2].substring(0, 4);
//            finaldate = day.concat("/").concat(month).concat("/").concat(year);
//        } 
//        else 
//        {
//            finaldate = "";
//        }
        LogMessages.statusLog.info("Para Date after change: " + finaldate);
        return finaldate;

    }
     public static JSONArray smeAllDataFetch(IFormReference iFormObj) 
    {
        String ApplicationNo = iFormObj.getValue("Buildingdetails").toString();
        String PID = ApplicationNo;
        LogMessages.statusLog.info("Applicationno: " + ApplicationNo);
        // fetching all review renew data
        JSONArray sme_review_renew_data_array = new JSONArray();

        // fetching all SME textbox data
        try 
        {
         String textbox_query = "select CustomerName,AccountNo,Groups,GroupName,SectoralCode,CompanyRegNo,DrainageConnection,"
                 + "IssuedDistrictPostApproval,PanNoInst,PanIssuedByInst,AuthorizedBodyNamePostApproval,Location1InstReg,Location2InstReg,"
                 + "HouseNoInstReg,StreetNameInstReg,Location1Inst,Location2Inst,HouseNoInst,StreetNameInst,Location1InstFac,Location2InstFac,"
                 + "HouseNoInstFac,StreetNameInstFac,ActNamePostApproval,PassportNoKeyPerson,DrivingLicenseNoKeyPerson,"
                 + "EmployeeIdNoKeyPerson,OtherIdentificationKeyPerson,Phone1Inst,Phone2Inst,MobileNo1Inst,MobileNo2Inst,Email1Inst,"
                 + "Email2Inst,Location1IndPer,Location2IndPer,StreetNameIndPer,HouseNoIndPer,Location1IndCur,Location2IndCur,StreetNameIndCur,"
                 + "HouseNoIndCur,BluePrintObtained,FathersName,MothersName,SpouseName,FatherInLawName,GrandFathersName,CitizenshipNo,ConsideredFMV,"
                 + "PassportNo,PassportIssuedPlace,DrivingLicenseNo,EmployeeIdNo,OtherIdentification,PanNoInd,PanIssuedByInd,Phone1Ind,"
                 + "Phone2Ind,MobileNo1Ind,MobileNo2Ind,Email1Ind,Email2Ind,ConsiderPropertyMortageable,flag101,ProductCode,PurposeHouseLoan,"
                 + "ShortTermRatingFac,LongTermRatingFac,Rorc,IrdSubNumber,TotalFundedExisting,TotalFundedProposed,TotalFundedOutstanding,"
                 + "fundedloanadminfee,TotalNonFundExisting,TotalNonFundProposed,TotalNonFundOutstanding,nonfundedloanadminfee,GrandTotalExisting,"
                 + "GrandTotalProposed,TotalLimitOutstanding,totallimitloanfee,PrimaryLimit,TotalLimit,otherCicCharge,OtherSTRCharge,OtherSecurityType,"
                 + "OtherNatureOfCharge,SecurityCodeGC,TotalFundedFacGroup,TotalNonFundedFacGroup,TotalLimitFacilityGroup,"
                 + "FMVSME,DVSME,UNITLTVSME,GROUPLTVSME,RelationshipCIC,CreditFacCIC,InquiryTypeCIC,IncorporationCIC,NameCIC,GenderCIC,"
                 + "NationalityCIC,CitizenshipNoCIC,CitizenshipIssuedDistrictCIC,PassportNoCIC,PassportIssuedPlaceCIC,FatherNameCIC,MotherNameCIC,"
                 + "GrandFatherNameCIC,DocumentationOfAcc,CustomerIdCIC,CompanyNameCIC,CompanyRegNoCIC,CompanyRegOrg,PanNoCIC,PanIssuedByCIC,"
                 + "ApplicableCICCharge,CICReportType,BlacklistedCIC,WellEstdScore,IndustryBusinessScore,GrowthProspectsScore,OverallMarketScore,"
                 + "MarketShareScore,BrandStrengthScore,DistributionNetworkScore,InnoTrackRecordScore,DiversityOfProductScore,BusinessIndTotalScore,"
                 + "TotalScoreMarketPos,BusinessScoreBeforeDisc,DiscFactorBusinessScore,BusinessScoreAfterDisc,DisclouresScore,CurrentRatioScore,"
                 + "TOLNetWorthScore,LongTermDebtScore,ReturnOnSalesScore,DebtServicingRatioScore,AbilityToGenFundScore,FinancialscoreBeforeDisc,"
                 + "DiscFactorFinancialScore,TotalScoreFinanAfterDisc,AdherenceToLawScore,CICReportScore,MultipleBankingScore,ReputationScore,GoodGovernanceScore,"
                 + "PromoterCompetanceScore,MainPromoterDealerScore,SuccessionPlanScore,QualitySeniorMgmtScore,TotalScoreManagement,DocumentationScore,"
                 + "SubmissionReportStmtScore,InsuranceRenewalScore,InterestServicingScore,PrincipalRepayInstallmentScore,InterestServicingWithoutPrinScore,"
                 + "TrackRecOverdueScore,UtilizationFacilitiesScore,RegularDepositScore,NoRecordSecurityScore,NoRecordFrequentScore,"
                 + "TotalScoreAccount,QualityPrimarySecurityScore,AdditionalImmovalSecurityScore,TotalScoreSecurity,TotalScoreAll,TotalScoreID,"
                 + "Risk,LongTermRating,ShortTermRating,LongTermRatingSecondary,ShortTermRatingSecondary,DowngradeRequired,NewLongTermRating,NewShortTermRating,"
                 + "Remarks,auditComment,currentProgress,Decision,Flag1,Flag2,entryCounter,initialRowCount,NextWorkdesk,IsQuery,"
                 + "QueriedBy,QueriedTo,ApproverCounter,APPROVALDECISION,ToTriggerName,ToTriggerEmail,NextWorkStep,PostApprovalDecision,"
                 + "MaritalStatus,personFromBacCom,IndianEmbassyNumber,indianEmbassyIssuedFrom,ActName,ActYear,AuthorizedBodyName,IssuedDistrict,"
                 + "InstIndianEmbassyNum,InstIndianEmbassyIssuedFrom,PrivateCommercial7Yrs,NatureOfInterestRate,SecuritytypePstApprvl,InsuranceAmtbuildingPstApprvl,"
                 + "NameofBankreqtakeoverPstApprvl,AccountNumberPstApprvl,AccountBenefNamePstApprvl,RelationOfficerNamePstApprvl,GroupSheetCode,GroupSHeetName,Flag10 from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'";
         
          LogMessages.statusLog.info("SME ALL Data Fetch query: " + textbox_query);
            List textbox_List = iFormObj.getDataFromDB(textbox_query);
            ListIterator textbox_list_iterator = textbox_List.listIterator();
            
             while (textbox_list_iterator.hasNext()) 
            {
                List textbox_list_data = (List) textbox_list_iterator.next();
                LogMessages.statusLog.info("List MyObject of textbox: " + textbox_list_data);
                String[] itemsArray = new String[textbox_list_data.size()];
                itemsArray = (String[]) textbox_list_data.toArray(itemsArray);
                LogMessages.statusLog.info("Array of textbox: " + itemsArray);

                JSONObject textbox_data = new JSONObject();
                
                
                textbox_data.put("CustomerName", itemsArray[0]);
                textbox_data.put("AccountNo", itemsArray[1]);
                textbox_data.put("Groups", itemsArray[2]);
                textbox_data.put("GroupName", itemsArray[3]);
                textbox_data.put("SectoralCode", itemsArray[4]);
                textbox_data.put("CompanyRegNo", itemsArray[5]);
                textbox_data.put("DrainageConnection", itemsArray[6]);
                textbox_data.put("IssuedDistrictPostApproval", itemsArray[7]);
                textbox_data.put("PanNoInst", itemsArray[8]);
                textbox_data.put("PanIssuedByInst", itemsArray[9]);
                textbox_data.put("AuthorizedBodyNamePostApproval", itemsArray[10]);
                textbox_data.put("Location1InstReg", itemsArray[11]);
                textbox_data.put("Location2InstReg", itemsArray[12]);
                textbox_data.put("HouseNoInstReg", itemsArray[13]);
                textbox_data.put("StreetNameInstReg", itemsArray[14]);
                textbox_data.put("Location1Inst", itemsArray[15]);
                textbox_data.put("Location2Inst", itemsArray[16]);
                textbox_data.put("HouseNoInst", itemsArray[17]);
                textbox_data.put("StreetNameInst", itemsArray[18]);
                textbox_data.put("Location1InstFac", itemsArray[19]);
                textbox_data.put("Location2InstFac", itemsArray[20]);
                textbox_data.put("HouseNoInstFac", itemsArray[21]);
                textbox_data.put("StreetNameInstFac", itemsArray[22]);
               // textbox_data.put("OtherRegisteredWith", itemsArray[]);
                textbox_data.put("ActNamePostApproval", itemsArray[23]);
                textbox_data.put("PassportNoKeyPerson", itemsArray[24]);
                textbox_data.put("DrivingLicenseNoKeyPerson", itemsArray[25]);
                textbox_data.put("EmployeeIdNoKeyPerson", itemsArray[26]);
                textbox_data.put("OtherIdentificationKeyPerson", itemsArray[27]);
                textbox_data.put("Phone1Inst", itemsArray[28]);
                textbox_data.put("Phone2Inst", itemsArray[29]);
                textbox_data.put("MobileNo1Inst", itemsArray[30]);
                textbox_data.put("MobileNo2Inst", itemsArray[31]);
                textbox_data.put("Email1Inst", itemsArray[32]);
                textbox_data.put("Email2Inst", itemsArray[33]);
                textbox_data.put("Location1IndPer", itemsArray[34]);
                textbox_data.put("Location2IndPer", itemsArray[35]);
                textbox_data.put("StreetNameIndPer", itemsArray[36]);
                textbox_data.put("HouseNoIndPer", itemsArray[37]);
                textbox_data.put("Location1IndCur", itemsArray[38]);
                textbox_data.put("Location2IndCur", itemsArray[39]);
                textbox_data.put("StreetNameIndCur", itemsArray[40]);
                textbox_data.put("HouseNoIndCur", itemsArray[41]);
                textbox_data.put("BluePrintObtained", itemsArray[42]);
                textbox_data.put("FathersName", itemsArray[43]);
                textbox_data.put("MothersName", itemsArray[44]);
                textbox_data.put("SpouseName", itemsArray[45]);
                textbox_data.put("FatherInLawName", itemsArray[46]);
                textbox_data.put("GrandFathersName", itemsArray[47]);
                textbox_data.put("CitizenshipNo", itemsArray[48]);
                textbox_data.put("ConsideredFMV", itemsArray[49]);
                textbox_data.put("PassportNo", itemsArray[50]);
                textbox_data.put("PassportIssuedPlace", itemsArray[51]);
                textbox_data.put("DrivingLicenseNo", itemsArray[52]);
                textbox_data.put("EmployeeIdNo", itemsArray[53]);
                textbox_data.put("OtherIdentification", itemsArray[54]);
                textbox_data.put("PanNoInd", itemsArray[55]);
                textbox_data.put("PanIssuedByInd", itemsArray[56]);
                textbox_data.put("Phone1Ind", itemsArray[57]);
                textbox_data.put("Phone2Ind", itemsArray[58]);
                textbox_data.put("MobileNo1Ind", itemsArray[59]);
                textbox_data.put("MobileNo2Ind", itemsArray[60]);
                textbox_data.put("Email1Ind", itemsArray[61]);
                textbox_data.put("Email2Ind", itemsArray[62]);
                textbox_data.put("ConsiderPropertyMortageable", itemsArray[63]);
                textbox_data.put("flag101", itemsArray[64]);
                textbox_data.put("ProductCode", itemsArray[65]);
                textbox_data.put("PurposeHouseLoan", itemsArray[66]);
                textbox_data.put("ShortTermRatingFac", itemsArray[67]);
                textbox_data.put("LongTermRatingFac", itemsArray[68]);
                textbox_data.put("Rorc", itemsArray[69]);
                textbox_data.put("IrdSubNumber", itemsArray[70]);
                textbox_data.put("TotalFundedExisting", itemsArray[71]);
                textbox_data.put("TotalFundedProposed", itemsArray[72]);
                textbox_data.put("TotalFundedOutstanding", itemsArray[73]);
                textbox_data.put("fundedloanadminfee", itemsArray[74]);
                textbox_data.put("TotalNonFundExisting", itemsArray[75]);
                textbox_data.put("TotalNonFundProposed", itemsArray[76]);
                textbox_data.put("TotalNonFundOutstanding", itemsArray[77]);
                textbox_data.put("nonfundedloanadminfee", itemsArray[78]);
                textbox_data.put("GrandTotalExisting", itemsArray[79]);
                textbox_data.put("GrandTotalProposed", itemsArray[80]);
                textbox_data.put("TotalLimitOutstanding", itemsArray[81]);
                textbox_data.put("totallimitloanfee", itemsArray[82]);
                 textbox_data.put("PrimaryLimit", itemsArray[83]);
                textbox_data.put("TotalLimit", itemsArray[84]);
                textbox_data.put("otherCicCharge", itemsArray[85]);
                textbox_data.put("OtherSTRCharge", itemsArray[86]);
                textbox_data.put("OtherSecurityType", itemsArray[87]);
                textbox_data.put("OtherNatureOfCharge", itemsArray[88]);
                textbox_data.put("SecurityCodeGC", itemsArray[89]);
               // textbox_data.put("GroupSheetCode", itemsArray[91]);
               // textbox_data.put("GroupSHeetName", itemsArray[92]);
                textbox_data.put("TotalFundedFacGroup", itemsArray[90]);
                textbox_data.put("TotalNonFundedFacGroup", itemsArray[91]);
                textbox_data.put("TotalLimitFacilityGroup", itemsArray[92]);
                textbox_data.put("FMVSME", itemsArray[93]);
                textbox_data.put("DVSME", itemsArray[94]);
                textbox_data.put("UNITLTVSME", itemsArray[95]);
                textbox_data.put("GROUPLTVSME", itemsArray[96]);
                textbox_data.put("RelationshipCIC", itemsArray[97]);
                textbox_data.put("CreditFacCIC", itemsArray[98]);
                textbox_data.put("InquiryTypeCIC", itemsArray[99]);
                textbox_data.put("IncorporationCIC", itemsArray[100]);
                textbox_data.put("NameCIC", itemsArray[101]);
                textbox_data.put("GenderCIC", itemsArray[102]);
                textbox_data.put("NationalityCIC", itemsArray[103]);
                textbox_data.put("CitizenshipNoCIC", itemsArray[104]);
                textbox_data.put("CitizenshipIssuedDistrictCIC", itemsArray[105]);
                textbox_data.put("PassportNoCIC", itemsArray[106]);
                textbox_data.put("PassportIssuedPlaceCIC", itemsArray[107]);
                textbox_data.put("FatherNameCIC", itemsArray[108]);
                textbox_data.put("MotherNameCIC", itemsArray[109]);
                textbox_data.put("GrandFatherNameCIC", itemsArray[110]);
                textbox_data.put("DocumentationOfAcc", itemsArray[111]);
                textbox_data.put("CustomerIdCIC", itemsArray[112]);
                textbox_data.put("CompanyNameCIC", itemsArray[113]);
                textbox_data.put("CompanyRegNoCIC", itemsArray[114]);
                textbox_data.put("CompanyRegOrg", itemsArray[115]);
                textbox_data.put("PanNoCIC", itemsArray[116]);
                textbox_data.put("PanIssuedByCIC", itemsArray[117]);
                textbox_data.put("ApplicableCICCharge", itemsArray[118]);
                textbox_data.put("CICReportType", itemsArray[119]);
                textbox_data.put("BlacklistedCIC", itemsArray[120]);
                textbox_data.put("WellEstdScore", itemsArray[121]);
                textbox_data.put("IndustryBusinessScore", itemsArray[122]);
                textbox_data.put("GrowthProspectsScore", itemsArray[123]);
                textbox_data.put("OverallMarketScore", itemsArray[124]);
                textbox_data.put("MarketShareScore", itemsArray[125]);
                textbox_data.put("BrandStrengthScore", itemsArray[126]);
                textbox_data.put("DistributionNetworkScore", itemsArray[127]);
                textbox_data.put("InnoTrackRecordScore", itemsArray[128]);
                textbox_data.put("DiversityOfProductScore", itemsArray[129]);
                textbox_data.put("BusinessIndTotalScore", itemsArray[130]);
                textbox_data.put("TotalScoreMarketPos", itemsArray[131]);
                textbox_data.put("BusinessScoreBeforeDisc", itemsArray[132]);
                textbox_data.put("DiscFactorBusinessScore", itemsArray[133]);
                textbox_data.put("BusinessScoreAfterDisc", itemsArray[134]);
                textbox_data.put("DisclouresScore", itemsArray[135]);
                textbox_data.put("CurrentRatioScore", itemsArray[136]);
                textbox_data.put("TOLNetWorthScore", itemsArray[137]);
                textbox_data.put("LongTermDebtScore", itemsArray[138]);
                textbox_data.put("ReturnOnSalesScore", itemsArray[139]);
                textbox_data.put("DebtServicingRatioScore", itemsArray[140]);
                textbox_data.put("AbilityToGenFundScore", itemsArray[141]);
                textbox_data.put("FinancialscoreBeforeDisc", itemsArray[142]);
                textbox_data.put("DiscFactorFinancialScore", itemsArray[143]);
                textbox_data.put("TotalScoreFinanAfterDisc", itemsArray[144]);
                textbox_data.put("AdherenceToLawScore", itemsArray[145]);
                textbox_data.put("CICReportScore", itemsArray[146]);
                textbox_data.put("MultipleBankingScore", itemsArray[147]);
                textbox_data.put("ReputationScore", itemsArray[148]);
                textbox_data.put("GoodGovernanceScore", itemsArray[149]);
                textbox_data.put("PromoterCompetanceScore", itemsArray[150]);
                textbox_data.put("MainPromoterDealerScore", itemsArray[151]);
                textbox_data.put("SuccessionPlanScore", itemsArray[152]);
                textbox_data.put("QualitySeniorMgmtScore", itemsArray[153]);
                textbox_data.put("TotalScoreManagement", itemsArray[154]);
                textbox_data.put("DocumentationScore", itemsArray[155]);
                textbox_data.put("SubmissionReportStmtScore", itemsArray[156]);
                textbox_data.put("InsuranceRenewalScore", itemsArray[157]);
                textbox_data.put("InterestServicingScore", itemsArray[158]);
                textbox_data.put("PrincipalRepayInstallmentScore", itemsArray[159]);
                textbox_data.put("InterestServicingWithoutPrinScore", itemsArray[160]);
                textbox_data.put("TrackRecOverdueScore", itemsArray[161]);
                textbox_data.put("UtilizationFacilitiesScore", itemsArray[162]);
                textbox_data.put("RegularDepositScore", itemsArray[163]);
                textbox_data.put("NoRecordSecurityScore", itemsArray[164]);
                textbox_data.put("NoRecordFrequentScore", itemsArray[165]);
                 textbox_data.put("TotalScoreAccount", itemsArray[166]);
                textbox_data.put("QualityPrimarySecurityScore", itemsArray[167]);
                textbox_data.put("AdditionalImmovalSecurityScore", itemsArray[168]);
                textbox_data.put("TotalScoreSecurity", itemsArray[169]);
                textbox_data.put("TotalScoreAll", itemsArray[170]);
                textbox_data.put("TotalScoreID", itemsArray[171]);
                 textbox_data.put("Risk", itemsArray[172]);
                textbox_data.put("LongTermRating", itemsArray[173]);
                textbox_data.put("ShortTermRating", itemsArray[174]);
                textbox_data.put("LongTermRatingSecondary", itemsArray[175]);
                textbox_data.put("ShortTermRatingSecondary", itemsArray[176]);
                textbox_data.put("DowngradeRequired", itemsArray[177]);
                 textbox_data.put("NewLongTermRating", itemsArray[178]);
                textbox_data.put("NewShortTermRating", itemsArray[179]);
                textbox_data.put("Remarks", itemsArray[180]);
                textbox_data.put("auditComment", itemsArray[181]);
                textbox_data.put("currentProgress", itemsArray[182]);
               // textbox_data.put("ActivityName", itemsArray[186]);
                 textbox_data.put("Decision", itemsArray[183]);
                textbox_data.put("Flag1", itemsArray[184]);
                textbox_data.put("Flag2", itemsArray[185]);
                textbox_data.put("entryCounter", itemsArray[186]);
                 textbox_data.put("initialRowCount", itemsArray[187]);
                textbox_data.put("NextWorkdesk", itemsArray[188]);
                textbox_data.put("IsQuery", itemsArray[189]);
                textbox_data.put("QueriedBy", itemsArray[190]);
                 textbox_data.put("QueriedTo", itemsArray[191]);
                textbox_data.put("ApproverCounter", itemsArray[192]);
                textbox_data.put("APPROVALDECISION", itemsArray[193]);
                textbox_data.put("ToTriggerName", itemsArray[194]);
                 textbox_data.put("ToTriggerEmail", itemsArray[195]);
                textbox_data.put("NextWorkStep", itemsArray[196]);
                textbox_data.put("PostApprovalDecision", itemsArray[197]);
                textbox_data.put("MaritalStatus", itemsArray[198]);
                textbox_data.put("personFromBacCom", itemsArray[199]);
                textbox_data.put("IndianEmbassyNumber", itemsArray[200]);
                textbox_data.put("indianEmbassyIssuedFrom", itemsArray[201]);
                textbox_data.put("ActName", itemsArray[202]);
                textbox_data.put("ActYear", itemsArray[203]);
                textbox_data.put("AuthorizedBodyName", itemsArray[204]);
                textbox_data.put("IssuedDistrict", itemsArray[205]);
                textbox_data.put("InstIndianEmbassyNum", itemsArray[206]);
                textbox_data.put("InstIndianEmbassyIssuedFrom", itemsArray[207]);
                textbox_data.put("PrivateCommercial7Yrs", itemsArray[208]);
                textbox_data.put("NatureOfInterestRate", itemsArray[209]);
                textbox_data.put("SecuritytypePstApprvl", itemsArray[210]);
                textbox_data.put("InsuranceAmtbuildingPstApprvl", itemsArray[211]);
                textbox_data.put("NameofBankreqtakeoverPstApprvl", itemsArray[212]);
                textbox_data.put("AccountNumberPstApprvl", itemsArray[213]);
                textbox_data.put("AccountBenefNamePstApprvl", itemsArray[214]);
                textbox_data.put("RelationOfficerNamePstApprvl", itemsArray[215]);
                textbox_data.put("GroupSheetCode", itemsArray[216]);
                textbox_data.put("GroupSHeetName", itemsArray[217]);
                textbox_data.put("Flag10", itemsArray[218]);
               // textbox_data.put("BranchManagerNameRMPstAppr", itemsArray[220]);
               
                
                sme_review_renew_data_array.clear();
                sme_review_renew_data_array.add(textbox_data);
                LogMessages.statusLog.info("*** All Data Of text box fetch successfully ***");
            }
        }
               
       catch (Exception e)
	{
	  e.printStackTrace();	
	   LogMessages.statusLog.info("*** Error Occured During fetching all text box field of SME  ***");
	}
        
         //fetching all comboobx data of SME
       try 
       {
         String combobox_query = "select Entity,BusinessNature2,Relationship,SectorGC,SubsectorGC,SubSubSector,Nationality,RagCategory,IncorporationProfitInst,"
                 + "IncorporationNotProfitInst,NatureOfActivity,RegisteredWithInst,ProvinceInstReg,DistrictInstReg,MunicipalityVDCInstReg,ProvinceInst,DistrictInst,MunicipalityVDCInst,"
                 + "ProvinceInstFac,DistrictInstFac,MunicipalityVDCInstFac,ProvinceIndPer,DistrictIndPer,MunicipalityVDCIndPer,ProvinceIndCur,DistrictIndCur,"
                 + "MunicipalityVDCIndCur,Gender,CitizenshipIssuedBy,Profession,CFRExpiryDate,Flag100,SubSegment,ProductType,cicCharge,strCharge,NatureOfCharge,"
                 + "SecurityType,SubSecurityType,SecurityList,SubordinationOfDebt,toBeUpdated,MLCheckBox,LandVerticallySloped,LandWithinDasgaja,LandWithinRiverForest,"
                 + "RelationshipRiskAss,WellEstd,IndustryBusiness,GrowthProspects,OverallMarket,MarketShare,BrandStrength,DistributionInfra,DistributionNetwork,"
                 + "InnoTrackRecord,DiversityOfProduct,DiscFactorBusiness,DisclouresSME,CurrentRatio,TOLNetWorth,LongTermDebtSME,ReturnOnSales,DebtServicingRatioSME,"
                 + "AbilityToGenFund,DiscFactorFinancial,AdherenceToLaw,CICReport,MultipleBanking,Reputation,GoodGovernance,PromoterCompetance,MainPromoterDealer,"
                 + "SuccessionPlan,QualitySeniorMgmt,DocumentationAccountSME,SubmissionReportStmt,InsuranceRenewalSME,DebtServicingModel,PrincipalRepayInstallment,"
                 + "TrackRecOverdue,UtilizationFacilities,RegularDeposit,NoRecordFrequent,QualityPrimarySecurity,AdditionalImmovalSecurity,"
                 + "IsExposureClassified,RiskSecondary,ESRating,documentUpload,ProductControl1,ProductControl2,ProductControl3,ProductControl4,ProductControl5,ProductControl6,"
                 + "ProductControl7,ProductControl8,ProductControl9,ProductControl10,ProductControl11,ProductControl12,ProductControl13,ProductControl14,ProductControl15,"
                 + "ProductControl16,ProductControl17,ProductControl59,ProductControl18,ProductControl19,ProductControl20,ProductControl21,ProductControl22,ProductControl23,"
                 + "ProductControl24,ProductControl25,ProductControl26,ProductControl27,ProductControl28,ProductControl29,ProductControl30,ProductControl60,ProductControl31,"
                 + "ProductControl32,ProductControl33,ProductControl34,ProductControl35,ProductControl36,ProductControl37,ProductControl38,ProductControl39,ProductControl40,"
                 + "ProductControl41,ProductControl42,ProductControl43,ProductControl44,ProductControl45,ProductControl46,ProductControl47,ProductControl48,ProductControl49,"
                 + "ProductControl50,ProductControl51,ProductControl52,ProductControl53,ProductControl54,ProductControl55,ProductControl56,ProductControl57,ProductControl58,"
                 + "GeneralCompliance1,GeneralCompliance2,GeneralCompliance3,GeneralCompliance4,GeneralCompliance5,GeneralCompliance6,GeneralCompliance7,GeneralCompliance8,"
                 + "GeneralCompliance9,GeneralCompliance10,GeneralCompliance11,GeneralCompliance12,GeneralCompliance13,GeneralCompliance14,GeneralCompliance15,GeneralCompliance16,"
                 + "GeneralCompliance17,GeneralCompliance18,GeneralCompliance19,GeneralCompliance20,GeneralCompliance21,GeneralCompliance22,GeneralCompliance23,GeneralCompliance24,"
                 + "GeneralCompliance25,GeneralCompliance26,auditType,Reviewer3,BoundaryWall,DecisionCAS,CompanyRegOrgCIC from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'";
         
                    LogMessages.statusLog.info("Combo box data fetch query: " + combobox_query);
                    List combobox_list = iFormObj.getDataFromDB(combobox_query);
                    ListIterator combobox_list_iterator = combobox_list.listIterator();

                    while (combobox_list_iterator.hasNext()) 
                    {
                      List combobox_list_data = (List) combobox_list_iterator.next();
                      LogMessages.statusLog.info("List Object of combobox: " + combobox_list_data);
                      String[] itemsArray = new String[combobox_list_data.size()];
                      itemsArray = (String[]) combobox_list_data.toArray(itemsArray);
                      LogMessages.statusLog.info("Array of combobox: " + itemsArray);

                      JSONObject combobox_data = new JSONObject();
                      
                      combobox_data.put("Entity", itemsArray[0]);
                      combobox_data.put("BusinessNature2", itemsArray[1]);
                      combobox_data.put("Relationship", itemsArray[2]);
                      combobox_data.put("SectorGC", itemsArray[3]);
                      combobox_data.put("SubsectorGC", itemsArray[4]);
                      combobox_data.put("SubSubSector", itemsArray[5]);
                      combobox_data.put("Nationality", itemsArray[6]);
                      combobox_data.put("RagCategory", itemsArray[7]);
                      combobox_data.put("IncorporationProfitInst", itemsArray[8]);
                      combobox_data.put("IncorporationNotProfitInst", itemsArray[9]);
                      combobox_data.put("NatureOfActivity", itemsArray[10]);
                      combobox_data.put("RegisteredWithInst", itemsArray[11]);
                      combobox_data.put("ProvinceInstReg", itemsArray[12]);
                      combobox_data.put("DistrictInstReg", itemsArray[13]);
                      combobox_data.put("MunicipalityVDCInstReg", itemsArray[14]);
                      combobox_data.put("ProvinceInst", itemsArray[15]);
                      combobox_data.put("DistrictInst", itemsArray[16]);
                      combobox_data.put("MunicipalityVDCInst", itemsArray[17]);
                      combobox_data.put("ProvinceInstFac", itemsArray[18]);
                      combobox_data.put("DistrictInstFac", itemsArray[19]);
                      combobox_data.put("MunicipalityVDCInstFac", itemsArray[20]);
                      combobox_data.put("ProvinceIndPer", itemsArray[21]);
                      combobox_data.put("DistrictIndPer", itemsArray[22]);
                      combobox_data.put("MunicipalityVDCIndPer", itemsArray[23]);
                      combobox_data.put("ProvinceIndCur", itemsArray[24]);
                      combobox_data.put("DistrictIndCur", itemsArray[25]);
                      combobox_data.put("MunicipalityVDCIndCur", itemsArray[26]);
                      combobox_data.put("Gender", itemsArray[27]);
                      combobox_data.put("CitizenshipIssuedBy", itemsArray[28]);
                      combobox_data.put("Profession", itemsArray[29]);
                      combobox_data.put("CFRExpiryDate", itemsArray[30]);
                      combobox_data.put("Flag100", itemsArray[31]);
                      combobox_data.put("SubSegment", itemsArray[32]);
                      combobox_data.put("ProductType", itemsArray[33]);
                      combobox_data.put("cicCharge", itemsArray[34]);
                      combobox_data.put("strCharge", itemsArray[35]);
                      combobox_data.put("NatureOfCharge", itemsArray[36]);
                      combobox_data.put("SecurityType", itemsArray[37]);
                      combobox_data.put("SubSecurityType", itemsArray[38]);
                      combobox_data.put("SecurityList", itemsArray[39]);
                      combobox_data.put("SubordinationOfDebt", itemsArray[40]);
                      combobox_data.put("toBeUpdated", itemsArray[41]);
                      combobox_data.put("MLCheckBox", itemsArray[42]);
                      combobox_data.put("LandVerticallySloped", itemsArray[43]);
                      combobox_data.put("LandWithinDasgaja", itemsArray[44]);
                      combobox_data.put("LandWithinRiverForest", itemsArray[45]);
                      combobox_data.put("RelationshipRiskAss", itemsArray[46]);
                      combobox_data.put("WellEstd", itemsArray[47]);
                      combobox_data.put("IndustryBusiness", itemsArray[48]);
                      combobox_data.put("GrowthProspects", itemsArray[49]);
                      combobox_data.put("OverallMarket", itemsArray[50]);
                      combobox_data.put("MarketShare", itemsArray[51]);
                      combobox_data.put("BrandStrength", itemsArray[52]);
                      combobox_data.put("DistributionInfra", itemsArray[53]);
                      combobox_data.put("DistributionNetwork", itemsArray[54]);
                      combobox_data.put("InnoTrackRecord", itemsArray[55]);
                      combobox_data.put("DiversityOfProduct", itemsArray[56]);
                      combobox_data.put("DiscFactorBusiness", itemsArray[57]);
                      combobox_data.put("DisclouresSME", itemsArray[58]);
                      combobox_data.put("CurrentRatio", itemsArray[59]);
                      combobox_data.put("TOLNetWorth", itemsArray[60]);
                      combobox_data.put("LongTermDebtSME", itemsArray[61]);
                      combobox_data.put("ReturnOnSales", itemsArray[62]);
                      combobox_data.put("DebtServicingRatioSME", itemsArray[63]);
                      combobox_data.put("AbilityToGenFund", itemsArray[64]);
                      combobox_data.put("DiscFactorFinancial", itemsArray[65]);
                      combobox_data.put("AdherenceToLaw", itemsArray[66]);
                      combobox_data.put("CICReport", itemsArray[67]);
                      combobox_data.put("MultipleBanking", itemsArray[68]);
                      combobox_data.put("Reputation", itemsArray[69]);
                      combobox_data.put("GoodGovernance", itemsArray[70]);
                      combobox_data.put("PromoterCompetance", itemsArray[71]);
                      combobox_data.put("MainPromoterDealer", itemsArray[72]);
                      combobox_data.put("SuccessionPlan", itemsArray[73]);
                      combobox_data.put("QualitySeniorMgmt", itemsArray[74]);
                      combobox_data.put("DocumentationAccountSME", itemsArray[75]);
                      combobox_data.put("SubmissionReportStmt", itemsArray[76]);
                      combobox_data.put("InsuranceRenewalSME", itemsArray[77]);
                      combobox_data.put("DebtServicingModel", itemsArray[78]);
                      //combobox_data.put("InterestServicingSME", itemsArray[7]);
                      combobox_data.put("PrincipalRepayInstallment", itemsArray[79]);
                      
                      //combobox_data.put("InterestServicingWithoutPrinSME", itemsArray[80]);
                     
                      combobox_data.put("TrackRecOverdue", itemsArray[80]);
                      combobox_data.put("UtilizationFacilities", itemsArray[81]);
                      combobox_data.put("RegularDeposit", itemsArray[82]);
                      combobox_data.put("NoRecordFrequent", itemsArray[83]);
                      combobox_data.put("QualityPrimarySecurity", itemsArray[84]);
                      combobox_data.put("AdditionalImmovalSecurityScore", itemsArray[85]);
                      combobox_data.put("IsExposureClassified", itemsArray[86]);
                      combobox_data.put("RiskSecondary", itemsArray[87]);
                      combobox_data.put("ESRating", itemsArray[88]);
                      combobox_data.put("documentUpload", itemsArray[89]);
                      combobox_data.put("ProductControl1", itemsArray[90]);
                      combobox_data.put("ProductControl2", itemsArray[91]);
                      combobox_data.put("ProductControl3", itemsArray[92]);
                      combobox_data.put("ProductControl4", itemsArray[93]);
                      combobox_data.put("ProductControl5", itemsArray[94]);
                      combobox_data.put("ProductControl6", itemsArray[95]);
                      combobox_data.put("ProductControl7", itemsArray[96]);
                      combobox_data.put("ProductControl8", itemsArray[97]);
                      combobox_data.put("ProductControl9", itemsArray[98]);
                      combobox_data.put("ProductControl10", itemsArray[99]);
                      combobox_data.put("ProductControl11", itemsArray[100]);
                      combobox_data.put("ProductControl12", itemsArray[101]);
                      combobox_data.put("ProductControl13", itemsArray[102]);
                      combobox_data.put("ProductControl14", itemsArray[103]);
                      combobox_data.put("ProductControl15", itemsArray[104]);
                      combobox_data.put("ProductControl16", itemsArray[105]);
                      combobox_data.put("ProductControl17", itemsArray[106]);
                      combobox_data.put("ProductControl59", itemsArray[107]);
                      combobox_data.put("ProductControl18", itemsArray[108]);
                      combobox_data.put("ProductControl19", itemsArray[109]);
                      combobox_data.put("ProductControl20", itemsArray[110]);
                      combobox_data.put("ProductControl21", itemsArray[111]);
                      combobox_data.put("ProductControl22", itemsArray[112]);
                      combobox_data.put("ProductControl23", itemsArray[113]);
                      combobox_data.put("ProductControl24", itemsArray[114]);
                      combobox_data.put("ProductControl25", itemsArray[115]);
                      combobox_data.put("ProductControl26", itemsArray[116]);
                      combobox_data.put("ProductControl27", itemsArray[117]);
                      combobox_data.put("ProductControl28", itemsArray[118]);
                      combobox_data.put("ProductControl29", itemsArray[119]);
                      combobox_data.put("ProductControl30", itemsArray[120]);
                      combobox_data.put("ProductControl31", itemsArray[121]);
                      combobox_data.put("ProductControl32", itemsArray[122]);
                      combobox_data.put("ProductControl33", itemsArray[123]);
                      combobox_data.put("ProductControl34", itemsArray[124]);
                      combobox_data.put("ProductControl35", itemsArray[125]);
                      combobox_data.put("ProductControl36", itemsArray[126]);
                      combobox_data.put("ProductControl37", itemsArray[127]);
                      combobox_data.put("ProductControl38", itemsArray[128]);
                      combobox_data.put("ProductControl39", itemsArray[129]);
                      combobox_data.put("ProductControl40", itemsArray[130]);
                      combobox_data.put("ProductControl41", itemsArray[131]);
                      combobox_data.put("ProductControl42", itemsArray[132]);
                      combobox_data.put("ProductControl43", itemsArray[133]);
                      combobox_data.put("ProductControl44", itemsArray[134]);
                      combobox_data.put("ProductControl45", itemsArray[135]);
                      combobox_data.put("ProductControl46", itemsArray[136]);
                      combobox_data.put("ProductControl47", itemsArray[137]);
                      combobox_data.put("ProductControl48", itemsArray[138]);
                      combobox_data.put("ProductControl49", itemsArray[139]);
                      combobox_data.put("ProductControl50", itemsArray[140]);
                      combobox_data.put("ProductControl51", itemsArray[141]);
                      combobox_data.put("ProductControl52", itemsArray[142]);
                      combobox_data.put("ProductControl53", itemsArray[143]);
                      combobox_data.put("ProductControl54", itemsArray[144]);
                      combobox_data.put("ProductControl55", itemsArray[145]);
                      combobox_data.put("ProductControl56", itemsArray[146]);
                      combobox_data.put("ProductControl57", itemsArray[147]);
                      combobox_data.put("ProductControl58", itemsArray[148]);
                      combobox_data.put("GeneralCompliance1", itemsArray[149]);
                      combobox_data.put("GeneralCompliance2", itemsArray[150]);
                      combobox_data.put("GeneralCompliance3", itemsArray[151]);
                      combobox_data.put("GeneralCompliance4", itemsArray[152]);
                      combobox_data.put("GeneralCompliance5", itemsArray[153]);
                      combobox_data.put("GeneralCompliance6", itemsArray[154]);
                      combobox_data.put("GeneralCompliance7", itemsArray[155]);
                      combobox_data.put("GeneralCompliance8", itemsArray[156]);
                      combobox_data.put("GeneralCompliance9", itemsArray[157]);
                      combobox_data.put("GeneralCompliance10", itemsArray[158]);
                      combobox_data.put("GeneralCompliance11", itemsArray[159]);
                      combobox_data.put("GeneralCompliance12", itemsArray[160]);
                      combobox_data.put("GeneralCompliance13", itemsArray[161]);
                      combobox_data.put("GeneralCompliance14", itemsArray[162]);
                      combobox_data.put("GeneralCompliance15", itemsArray[163]);
                      combobox_data.put("GeneralCompliance16", itemsArray[164]);
                      combobox_data.put("GeneralCompliance17", itemsArray[165]);
                      combobox_data.put("GeneralCompliance18", itemsArray[166]);
                      combobox_data.put("GeneralCompliance19", itemsArray[167]);
                      combobox_data.put("GeneralCompliance20", itemsArray[168]);
                      combobox_data.put("GeneralCompliance21", itemsArray[169]);
                      combobox_data.put("GeneralCompliance22", itemsArray[170]);
                      combobox_data.put("GeneralCompliance23", itemsArray[171]);
                      combobox_data.put("GeneralCompliance24", itemsArray[172]);
                      combobox_data.put("GeneralCompliance25", itemsArray[173]);
                      combobox_data.put("GeneralCompliance26", itemsArray[174]);
                      combobox_data.put("auditType", itemsArray[175]);
                      combobox_data.put("Reviewer3", itemsArray[176]);
                      combobox_data.put("BoundaryWall", itemsArray[177]);
                      combobox_data.put("DecisionCAS", itemsArray[178]);
                      combobox_data.put("CompanyRegOrgCIC", itemsArray[179]);
                     // combobox_data.put("DistrictInstReg", itemsArray[178]);
                      /*combobox_data.put("CreditCards", itemsArray[180]);
                      combobox_data.put("POSQRCode", itemsArray[181]);
                      combobox_data.put("eNabil", itemsArray[182]);
                      combobox_data.put("NabilNet", itemsArray[183]);
                      combobox_data.put("CorporateFirm", itemsArray[184]);
                      combobox_data.put("SavingAccount", itemsArray[185]);
                      combobox_data.put("DematAccount", itemsArray[186]);
                      combobox_data.put("StaffPayroll", itemsArray[187]);
                      combobox_data.put("SavingAccountFamily", itemsArray[188]);*/
                     
                      LogMessages.statusLog.info("shivaraj Only one data, SMEAllDataFetch : "+itemsArray[178]);
                      LogMessages.statusLog.info("shivaraj sme_review_renew_data_array, SMEAllDataFetch : "+combobox_data);
                       sme_review_renew_data_array.add(combobox_data);
                    LogMessages.statusLog.info("*** All Data Of combo box fetch successfully ***");
                }
            } 
       catch (Exception e) 
            {
                e.printStackTrace();
                LogMessages.statusLog.info("*** Error Occured During fetching all combo box field of SME ***");
            }
       
        //fetch all date field of SME
            try 
            {
                 String date_query = " select ApplicationDate,DateAccountOpened,DateFacsFirstSanction,LastSancDate,DOBIndividual,"
                         + "IssuedDate,PassportIssuedDate,PassportExpiryDate,DateRegisteredInst,CFRExpDate,NextReviewDate,"
                         + "DateOfBirthCIC,CitizenshipIssuedDateCIC,PassportIssuedDateCIC,PassportExpiryDateCIC,CompanyRegDateCIC,"
                         + "IndianEmbIssuedDate,PANIssuedDate,InstIndianEmbassyIssuedDate,lastSanctionLetterDate,"
                         + "ExistingReviewDate,DateofApprovalPstApprvl,DateofApplicationPstApprvl,RegistrationExpiryDatePstApp from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'";
                 
                        LogMessages.statusLog.info("date data fetch query: " + date_query);
                        List date_list = iFormObj.getDataFromDB(date_query);
                        ListIterator date_list_iterator = date_list.listIterator();

                       while (date_list_iterator.hasNext()) 
                       {
                          List date_list_data = (List) date_list_iterator.next();
                          LogMessages.statusLog.info("List Object of date: " + date_list_data);
                          String[] itemsArray = new String[date_list_data.size()];
                          itemsArray = (String[]) date_list_data.toArray(itemsArray);
                          LogMessages.statusLog.info("Array of date: " + itemsArray);

                          JSONObject date_data = new JSONObject();
                          
                          date_data.put("ApplicationDate", customDate(itemsArray[0]));
                          date_data.put("DateAccountOpened", customDate(itemsArray[1]));
                          date_data.put("DateFacsFirstSanction", customDate(itemsArray[2]));
                          date_data.put("LastSancDate", customDate(itemsArray[3]));
                          date_data.put("DOBIndividual", customDate(itemsArray[4]));
                          date_data.put("IssuedDate", customDate(itemsArray[5]));
                          date_data.put("PassportIssuedDate", customDate(itemsArray[6]));
                          date_data.put("PassportExpiryDate", customDate(itemsArray[7]));
                          date_data.put("DateRegisteredInst", customDate(itemsArray[8]));
                          date_data.put("CFRExpDate", customDate(itemsArray[9]));
                          date_data.put("NextReviewDate", customDate(itemsArray[10]));
                          date_data.put("DateOfBirthCIC", customDate(itemsArray[11]));
                          date_data.put("CitizenshipIssuedDateCIC", customDate(itemsArray[12]));
                          date_data.put("PassportIssuedDateCIC", customDate(itemsArray[13]));
                          date_data.put("PassportExpiryDateCIC", customDate(itemsArray[14]));
                          date_data.put("CompanyRegDateCIC", customDate(itemsArray[15]));
                          date_data.put("IndianEmbIssuedDate", customDate(itemsArray[16]));
                          date_data.put("PANIssuedDate", customDate(itemsArray[17]));
                          date_data.put("InstIndianEmbassyIssuedDate", customDate(itemsArray[18]));
                          date_data.put("lastSanctionLetterDate", customDate(itemsArray[19]));
                          date_data.put("ExistingReviewDate", customDate(itemsArray[20]));
                          date_data.put("DateofApprovalPstApprvl", customDate(itemsArray[21]));
                          date_data.put("DateofApplicationPstApprvl", customDate(itemsArray[22]));
                          date_data.put("RegistrationExpiryDatePstApp", customDate(itemsArray[23]));
                          
                          sme_review_renew_data_array.add(date_data);
                          LogMessages.statusLog.info("*** All Data Of date fetch successfully ***");
                        }
                    } 
                    
            catch (Exception e) 
            {
                e.printStackTrace();
                LogMessages.statusLog.info("*** Error Occured During fetching all date field of SME ***");
            }
              
            //fetching all textarea data of SME
            //CS_SMELongRemarks
            try 
            {
                String textarealong_query = " select modeofdisbursement,repaymentfield,financialprojectedremarks,"
            + "financialworkingremarks,financialadditionalremarks,remarksofcompany,remarksofowner,remarksdowngrade,justificationriskass,"
            + "remarksfinancialcovenant,remarksfinannoncovenant,remarksbusinessrisk,remarksfinancialrisk,remarksmanagementrisk,remarkssecurityrisk,"
            + "remarksphysicalrisk,remarksnonphysicalrisk,remarkssecuritydocument  from CS_SMELongRemarks  with(nolock) where PID='" + PID + "'";
                
                
                            LogMessages.statusLog.info("TextAreaLong data fetch query: " + textarealong_query);
                            List textarealong_list = iFormObj.getDataFromDB(textarealong_query);
                            ListIterator textarealong_list_iterator = textarealong_list.listIterator();

                       while (textarealong_list_iterator.hasNext()) 
                       {
                           List textarealong_list_data = (List) textarealong_list_iterator.next();
                           LogMessages.statusLog.info("List Object of textarealong data: " + textarealong_list_data);
                           String[] itemsArray = new String[textarealong_list_data.size()];
                           itemsArray = (String[]) textarealong_list_data.toArray(itemsArray);
                           LogMessages.statusLog.info("Array of textarealong data: " + itemsArray);

                              JSONObject textarealong_data = new JSONObject();
                              
                               textarealong_data.put("modeofdisbursement", itemsArray[0]);
                               textarealong_data.put("repaymentfield", itemsArray[1]);
                               textarealong_data.put("financialprojectedremarks", itemsArray[2]);
                               textarealong_data.put("financialworkingremarks", itemsArray[3]);
                               textarealong_data.put("financialadditionalremarks", itemsArray[4]);
                               textarealong_data.put("remarksofcompany", itemsArray[5]);
                               textarealong_data.put("remarksofowner", itemsArray[6]);
                               textarealong_data.put("remarksdowngrade", itemsArray[7]);
                               textarealong_data.put("justificationriskass", itemsArray[8]);
                               textarealong_data.put("remarksfinancialcovenant", itemsArray[9]);
                               textarealong_data.put("remarksfinannoncovenant", itemsArray[10]);
                               textarealong_data.put("remarksbusinessrisk", itemsArray[11]);
                               textarealong_data.put("remarksfinancialrisk", itemsArray[12]);
                               textarealong_data.put("remarksmanagementrisk", itemsArray[13]);
                               textarealong_data.put("remarkssecurityrisk", itemsArray[14]);
                               textarealong_data.put("remarksphysicalrisk", itemsArray[15]);
                               textarealong_data.put("remarksnonphysicalrisk", itemsArray[16]);
                               textarealong_data.put("remarkssecuritydocument", itemsArray[17]);
                              
                               
                               
                                sme_review_renew_data_array.add(textarealong_data);
                                LogMessages.statusLog.info("*** All textarealong_data(CS_SMELongRemarks) of SME fetched successfully ***");
                        }
                    } 
                    catch (Exception e) 
                     {
                       e.printStackTrace();
                     }
            
             /*fetching all textarea data of SME
            //CS_SMEControlRemarks
            try 
            {
                String textareacontrol_query = " select proposalremarks,businessremarks,businessstartremarks,facilitiesremarks,termloanhigherremarks,overdraftproposedremarks,moratoriumremarks,interestremarks,"
            + "termloanequatedremarks,perguaranteeheldremarks,primarysecurityremarks,secondarysecurityremarks,personalguaranteeremarks,minimummarginremarks,fairmarketvalueremarks,termloandscrremarks,"
            + "salesrevenueremarks,annualsales25,totalcapitalremarks,currentratioremarks,grossprofitremarks,netprofitremarks,accountreceivablesremarks,stocksconsideredremarks,"
            + "minloanremarks,prepaymentfeeremarks,commitmentfeeremarks,allexternalremarks,minmarginheldremarks,inspectionremarks,oherloanremarks,blacklistedremarks  from CS_SMEControlRemarks  with(nolock) where PID='" + PID + "'";
                
                            LogMessages.statusLog.info("TextAreaControl data fetch query: " + textareacontrol_query);
                            List textareacontrol_list = iFormObj.getDataFromDB(textareacontrol_query);
                            ListIterator textareacontrol_list_iterator = textareacontrol_list.listIterator();

                       while (textareacontrol_list_iterator.hasNext()) 
                       {
                           List textareacontrol_list_data = (List) textareacontrol_list_iterator.next();
                           LogMessages.statusLog.info("List Object of textareacontrol data: " + textareacontrol_list_data);
                           String[] itemsArray = new String[textareacontrol_list_data.size()];
                           itemsArray = (String[]) textareacontrol_list_data.toArray(itemsArray);
                           LogMessages.statusLog.info("Array of textareacontrol data: " + itemsArray);

                              JSONObject textareacontrol_data = new JSONObject();
                              
                               textareacontrol_data.put("proposalremarks", itemsArray[0]);
                               textareacontrol_data.put("businessremarks", itemsArray[1]);
                               textareacontrol_data.put("businessstartremarks", itemsArray[2]);
                               textareacontrol_data.put("facilitiesremarks", itemsArray[3]);
                               textareacontrol_data.put("termloanhigherremarks", itemsArray[4]);
                               textareacontrol_data.put("overdraftproposedremarks", itemsArray[5]);
                               textareacontrol_data.put("moratoriumremarks", itemsArray[6]);
                               textareacontrol_data.put("interestremarks", itemsArray[7]);
                               textareacontrol_data.put("termloanequatedremarks", itemsArray[8]);
                               textareacontrol_data.put("perguaranteeheldremarks", itemsArray[9]);
                               textareacontrol_data.put("primarysecurityremarks", itemsArray[10]);
                               textareacontrol_data.put("secondarysecurityremarks", itemsArray[11]);
                               textareacontrol_data.put("personalguaranteeremarks", itemsArray[12]);
                               textareacontrol_data.put("minimummarginremarks", itemsArray[13]);
                               textareacontrol_data.put("fairmarketvalueremarks", itemsArray[14]);
                               textareacontrol_data.put("termloandscrremarks", itemsArray[15]);
                               textareacontrol_data.put("salesrevenueremarks", itemsArray[16]);
                               textareacontrol_data.put("annualsales25", itemsArray[17]);
                               textareacontrol_data.put("totalcapitalremarks", itemsArray[18]);
                               textareacontrol_data.put("currentratioremarks", itemsArray[19]);
                               textareacontrol_data.put("grossprofitremarks", itemsArray[20]);
                               textareacontrol_data.put("netprofitremarks", itemsArray[21]);
                               textareacontrol_data.put("accountreceivablesremarks", itemsArray[22]);
                               textareacontrol_data.put("stocksconsideredremarks", itemsArray[23]);
                               textareacontrol_data.put("minloanremarks", itemsArray[24]);
                               textareacontrol_data.put("prepaymentfeeremarks", itemsArray[25]);
                               textareacontrol_data.put("commitmentfeeremarks", itemsArray[26]);
                               textareacontrol_data.put("allexternalremarks", itemsArray[27]);
                               textareacontrol_data.put("minmarginheldremarks", itemsArray[28]);
                               textareacontrol_data.put("inspectionremarks", itemsArray[29]);
                               textareacontrol_data.put("oherloanremarks", itemsArray[30]);
                               textareacontrol_data.put("blacklistedremarks", itemsArray[31]);
                              
                              
                                 sme_review_renew_data_array.add(textareacontrol_data);
                                LogMessages.statusLog.info("*** All textareacontrol_data(CS_SMEControlRemarks) of SME fetched successfully ***");
                        }
                    } 
                    catch (Exception e) 
                     {
                       e.printStackTrace();
                     }
                               
       //fetching all textarea data of SME     
       //CS_SMEControlRemarksSec 
       
        try 
            {
                String textareacontrolrem_query = " select ControlRemarks31,ControlRemarks32,ControlRemarks33,ControlRemarks34,ControlRemarks35,"
                        + "ControlRemarks36,ControlRemarks37,ControlRemarks38,ControlRemarks39,ControlRemarks40,ControlRemarks41,ControlRemarks42,"
                        + "ControlRemarks43,ControlRemarks44,ControlRemarks45,ControlRemarks46,ControlRemarks47,ControlRemarks48,ControlRemarks49,"
                        + "ControlRemarks50,ControlRemarks51,ControlRemarks52,ControlRemarks53,ControlRemarks54,ControlRemarks55,ControlRemarks56,"
                        + "ControlRemarks57,ControlRemarks58 from CS_SMEControlRemarksSec  with(nolock) where PID='" + PID + "'";
                
                            LogMessages.statusLog.info("TextAreaControlrem data fetch query: " + textareacontrolrem_query);
                            List textareacontrolrem_list = iFormObj.getDataFromDB(textareacontrolrem_query);
                            ListIterator textareacontrolrem_list_iterator = textareacontrolrem_list.listIterator();

                       while (textareacontrolrem_list_iterator.hasNext()) 
                       {
                           List textareacontrolrem_list_data = (List) textareacontrolrem_list_iterator.next();
                           LogMessages.statusLog.info("List Object of textareacontrolrem data: " + textareacontrolrem_list_data);
                           String[] itemsArray = new String[textareacontrolrem_list_data.size()];
                           itemsArray = (String[]) textareacontrolrem_list_data.toArray(itemsArray);
                           LogMessages.statusLog.info("Array of textareacontrolrem data: " + itemsArray);

                              JSONObject textareacontrolrem_data = new JSONObject();
                              
                               textareacontrolrem_data.put("ControlRemarks31", itemsArray[0]);
                               textareacontrolrem_data.put("ControlRemarks32", itemsArray[1]);
                               textareacontrolrem_data.put("ControlRemarks33", itemsArray[2]);
                               textareacontrolrem_data.put("ControlRemarks34", itemsArray[3]);
                               textareacontrolrem_data.put("ControlRemarks35", itemsArray[4]);
                               textareacontrolrem_data.put("ControlRemarks36", itemsArray[5]);
                               textareacontrolrem_data.put("ControlRemarks37", itemsArray[6]);
                               textareacontrolrem_data.put("ControlRemarks38", itemsArray[7]);
                               textareacontrolrem_data.put("ControlRemarks39", itemsArray[8]);
                               textareacontrolrem_data.put("ControlRemarks40", itemsArray[9]);
                               textareacontrolrem_data.put("ControlRemarks41", itemsArray[10]);
                               textareacontrolrem_data.put("ControlRemarks42", itemsArray[11]);
                               textareacontrolrem_data.put("ControlRemarks43", itemsArray[12]);
                               textareacontrolrem_data.put("ControlRemarks44", itemsArray[13]);
                               textareacontrolrem_data.put("ControlRemarks45", itemsArray[14]);
                               textareacontrolrem_data.put("ControlRemarks46", itemsArray[15]);
                               textareacontrolrem_data.put("ControlRemarks47", itemsArray[16]);
                               textareacontrolrem_data.put("ControlRemarks48", itemsArray[17]);
                               textareacontrolrem_data.put("ControlRemarks49", itemsArray[18]);
                               textareacontrolrem_data.put("ControlRemarks50", itemsArray[19]);
                               textareacontrolrem_data.put("ControlRemarks51", itemsArray[20]);
                               textareacontrolrem_data.put("ControlRemarks52", itemsArray[21]);
                               textareacontrolrem_data.put("ControlRemarks53", itemsArray[22]);
                               textareacontrolrem_data.put("ControlRemarks54", itemsArray[23]);
                               textareacontrolrem_data.put("ControlRemarks55", itemsArray[24]);
                               textareacontrolrem_data.put("ControlRemarks56", itemsArray[25]);
                               textareacontrolrem_data.put("ControlRemarks57", itemsArray[26]);
                               textareacontrolrem_data.put("ControlRemarks58", itemsArray[27]);
                               
                                 sme_review_renew_data_array.add(textareacontrolrem_data);
                                LogMessages.statusLog.info("*** All textareacontrolrem_data(CS_SMEControlRemarksSec) of SME fetched successfully ***");
                        }
                    } 
                    catch (Exception e) 
                     {
                       e.printStackTrace();
                     }
             //textarea data                  
            //CS_SMEGeneralRemarks
            
            try 
            {
                String textareageneralrem_query = " select generalremarks1,generalremarks2,generalremarks3,generalremarks4,generalremarks5,generalremarks6,"
                        + "generalremarks7,generalremarks8,generalremarks9,generalremarks10,generalremarks11,generalremarks12,generalremarks13,generalremarks14,generalremarks15,"
                        + "generalremarks16,generalremarks17,generalremarks18,generalremarks19,generalremarks20,generalremarks21,generalremarks22,generalremarks23,"
                        + "generalremarks24,generalremarks25,generalremarks26 from CS_SMEGeneralRemarks  with(nolock) where PID='" + PID + "'";
                
                            LogMessages.statusLog.info("TextAreageneralrem data fetch query: " + textareageneralrem_query);
                            List textareageneralrem_list = iFormObj.getDataFromDB(textareageneralrem_query);
                            ListIterator textareageneralrem_list_iterator = textareageneralrem_list.listIterator();

                       while (textareageneralrem_list_iterator.hasNext()) 
                       {
                           List textareageneralrem_list_data = (List) textareageneralrem_list_iterator.next();
                           LogMessages.statusLog.info("List Object of textareageneralrem data: " + textareageneralrem_list_data);
                           String[] itemsArray = new String[textareageneralrem_list_data.size()];
                           itemsArray = (String[]) textareageneralrem_list_data.toArray(itemsArray);
                           LogMessages.statusLog.info("Array of textareageneralrem data: " + itemsArray);

                              JSONObject textareageneralrem_data = new JSONObject();
                              
                               textareageneralrem_data.put("generalremarks1", itemsArray[0]);
                               textareageneralrem_data.put("generalremarks2", itemsArray[1]);
                               textareageneralrem_data.put("generalremarks3", itemsArray[2]);
                               textareageneralrem_data.put("generalremarks4", itemsArray[3]);
                               textareageneralrem_data.put("generalremarks5", itemsArray[4]);
                               textareageneralrem_data.put("generalremarks6", itemsArray[5]);
                               textareageneralrem_data.put("generalremarks7", itemsArray[6]);
                               textareageneralrem_data.put("generalremarks8", itemsArray[7]);
                               textareageneralrem_data.put("generalremarks9", itemsArray[8]);
                               textareageneralrem_data.put("generalremarks10", itemsArray[9]);
                               textareageneralrem_data.put("generalremarks11", itemsArray[10]);
                               textareageneralrem_data.put("generalremarks12", itemsArray[11]);
                               textareageneralrem_data.put("generalremarks13", itemsArray[12]);
                               textareageneralrem_data.put("generalremarks14", itemsArray[13]);
                               textareageneralrem_data.put("generalremarks15", itemsArray[14]);
                               textareageneralrem_data.put("generalremarks16", itemsArray[15]);
                               textareageneralrem_data.put("generalremarks17", itemsArray[16]);
                               textareageneralrem_data.put("generalremarks18", itemsArray[17]);
                               textareageneralrem_data.put("generalremarks19", itemsArray[18]);
                               textareageneralrem_data.put("generalremarks20", itemsArray[19]);
                               textareageneralrem_data.put("generalremarks21", itemsArray[20]);
                               textareageneralrem_data.put("generalremarks22", itemsArray[21]);
                               textareageneralrem_data.put("generalremarks23", itemsArray[22]);
                               textareageneralrem_data.put("generalremarks24", itemsArray[23]);
                               textareageneralrem_data.put("generalremarks25", itemsArray[24]);
                               textareageneralrem_data.put("generalremarks26", itemsArray[25]);
                               
                                 sme_review_renew_data_array.add(textareageneralrem_data);
                                LogMessages.statusLog.info("*** All textareageneralrem_data(CS_SMEGeneralRemarks) of SME fetched successfully ***");
                        }
                    } 
                    catch (Exception e) 
                     {
                       e.printStackTrace();
                     }
             //textarea data                  
            //CS_CrossSellRemarks
            
            try 
            {
                String textareacrosssellrem_query = " select creditcard,posremarks,enabilremarks,nabilnetremarks,corporateremarks,savingremarks,dematremarks,staffremarks,savingfamilyremarks from CS_CrossSellRemarks with(nolock) where PID='" + PID + "'";
                
                 LogMessages.statusLog.info("TextAreacrosssellrem data fetch query: " + textareacrosssellrem_query);
                            List textareacrosssellrem_list = iFormObj.getDataFromDB(textareacrosssellrem_query);
                            ListIterator textareacrosssellrem_list_iterator = textareacrosssellrem_list.listIterator();

                       while (textareacrosssellrem_list_iterator.hasNext()) 
                       {
                           List textareacrosssellrem_list_data = (List) textareacrosssellrem_list_iterator.next();
                           LogMessages.statusLog.info("List Object of textareacrosssellrem data: " + textareacrosssellrem_list_data);
                           String[] itemsArray = new String[textareacrosssellrem_list_data.size()];
                           itemsArray = (String[]) textareacrosssellrem_list_data.toArray(itemsArray);
                           LogMessages.statusLog.info("Array of textareacrosssellrem data: " + itemsArray);

                              JSONObject textareacrosssellrem_data = new JSONObject();
                              
                               textareacrosssellrem_data.put("creditcard", itemsArray[0]);
                               textareacrosssellrem_data.put("posremarks", itemsArray[1]);
                               textareacrosssellrem_data.put("enabilremarks", itemsArray[2]);
                               textareacrosssellrem_data.put("nabilnetremarks", itemsArray[3]);
                               textareacrosssellrem_data.put("corporateremarks", itemsArray[4]);
                               textareacrosssellrem_data.put("savingremarks", itemsArray[5]);
                               textareacrosssellrem_data.put("dematremarks", itemsArray[6]);
                               textareacrosssellrem_data.put("staffremarks", itemsArray[7]);
                               textareacrosssellrem_data.put("savingfamilyremarks", itemsArray[8]);
                               
                                sme_review_renew_data_array.add(textareacrosssellrem_data);
                                LogMessages.statusLog.info("*** All textareacrosssellrem_data(CS_CrossSellRemarks) of SME fetched successfully ***");
                        }
                    } 
                    catch (Exception e) 
                     {
                       e.printStackTrace();
                     }*/
            
             //fetching all textarea data of SME
            try 
            {
                String textarea_query = " select BusinessNatureRemarks2,OtherChargersWavers,SecurityVariationRemarks,"
                       + "CAScommentsCAS,ROCommentsCAS,CommentCLAD from TBL_EXT_LOS with(nolock)where ApplicationNo='" + ApplicationNo + "'"; 
                        
                LogMessages.statusLog.info("TextArea data fetch query: " + textarea_query);
                List textarea_list = iFormObj.getDataFromDB(textarea_query);
                ListIterator textarea_list_iterator = textarea_list.listIterator();

                while (textarea_list_iterator.hasNext()) {
                    List textarea_list_data = (List) textarea_list_iterator.next();
                    LogMessages.statusLog.info("List Object of textarea data: " + textarea_list_data);
                    String[] itemsArray = new String[textarea_list_data.size()];
                    itemsArray = (String[]) textarea_list_data.toArray(itemsArray);
                    LogMessages.statusLog.info("Array of textarea data: " + itemsArray);

                    JSONObject textarea_data = new JSONObject();
                    
                    textarea_data.put("BusinessNatureRemarks2", itemsArray[0]);
                    textarea_data.put("OtherChargersWavers", itemsArray[1]);
                    textarea_data.put("SecurityVariationRemarks", itemsArray[2]);
                    textarea_data.put("CAScommentsCAS", itemsArray[3]);
                    textarea_data.put("ROCommentsCAS", itemsArray[4]);
                    textarea_data.put("CommentCLAD", itemsArray[5]);
                    
                    sme_review_renew_data_array.add(textarea_data);
                    LogMessages.statusLog.info("*** All textarea_data of SME fetched successfully ***");
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
           //fetching all checkbox data of SME
            try 
            {
                String sme_checkbox_query = "select SameAsPermAddress,Flag3,FactorySameAsReg,RealEstate,Automobile,Inventories,"
                        + "AccountReceivables,MovableAssets,FixedDeposit,BankDeposit,GovSecurities,CorpBonds,ListedSecurities,ValuableItems,"
                        + "OtherSecurity,limittreecheckbox,disbursement from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'";
                
                     LogMessages.statusLog.info("SME checkbox data fetch query: " + sme_checkbox_query);
                     List sme_checkbox__list = iFormObj.getDataFromDB(sme_checkbox_query);
                     ListIterator sme_checkbox_list_iterator = sme_checkbox__list.listIterator();

                 while (sme_checkbox_list_iterator.hasNext()) 
                 {
                    List sme_checkbox_list_data = (List) sme_checkbox_list_iterator.next();
                    LogMessages.statusLog.info("List Object of SME checkbox data: " + sme_checkbox_list_data);
                    String[] itemsArray = new String[sme_checkbox_list_data.size()];
                    itemsArray = (String[]) sme_checkbox_list_data.toArray(itemsArray);
                    LogMessages.statusLog.info("Array of SME checkbox  data: " + itemsArray);

                    JSONObject sme_checkbox_data = new JSONObject();
                    
                    sme_checkbox_data.put("SameAsPermAddress", itemsArray[0]);
                    sme_checkbox_data.put("Flag3", itemsArray[1]);
                    sme_checkbox_data.put("FactorySameAsReg", itemsArray[2]);
                    sme_checkbox_data.put("RealEstate", itemsArray[3]);
                    sme_checkbox_data.put("Automobile", itemsArray[4]);
                    sme_checkbox_data.put("Inventories", itemsArray[5]);
                    sme_checkbox_data.put("AccountReceivables", itemsArray[6]);
                    sme_checkbox_data.put("MovableAssets", itemsArray[7]);
                    sme_checkbox_data.put("FixedDeposit", itemsArray[8]);
                    sme_checkbox_data.put("BankDeposit", itemsArray[9]);
                    sme_checkbox_data.put("GovSecurities", itemsArray[10]);
                    sme_checkbox_data.put("CorpBonds", itemsArray[11]);
                    sme_checkbox_data.put("ListedSecurities", itemsArray[12]);
                    sme_checkbox_data.put("ValuableItems", itemsArray[13]);
                    sme_checkbox_data.put("OtherSecurity", itemsArray[14]);
                    sme_checkbox_data.put("limittreecheckbox", itemsArray[15]);
                    sme_checkbox_data.put("disbursement", itemsArray[16]);
                    
                    sme_review_renew_data_array.add(sme_checkbox_data);
                    LogMessages.statusLog.info("*** All Data Of SME checkbox fetch successfully ***");
                }
            } 
            catch (Exception e) 
            {
              e.printStackTrace();
                 
            }
            
             //fetching all listbox data of SME
            try 
            {
                String sme_listbox_query = "select Purposefacility  from CS_OtherVariables  with(nolock) where  PID='" + PID + "'";
                
                 LogMessages.statusLog.info("SME listbox data fetch query: " + sme_listbox_query);
                     List sme_listbox__list = iFormObj.getDataFromDB(sme_listbox_query);
                     ListIterator sme_listbox_list_iterator = sme_listbox__list.listIterator();

                 while (sme_listbox_list_iterator.hasNext()) 
                 {
                    List sme_listbox_list_data = (List) sme_listbox_list_iterator.next();
                    LogMessages.statusLog.info("List Object of SME listbox data: " + sme_listbox_list_data);
                    String[] itemsArray = new String[sme_listbox_list_data.size()];
                    itemsArray = (String[]) sme_listbox_list_data.toArray(itemsArray);
                    LogMessages.statusLog.info("Array of SME listbox  data: " + itemsArray);

                    JSONObject sme_listbox_data = new JSONObject();
                    
                    sme_listbox_data.put("Purposefacility", itemsArray[0]);
                    
                     sme_review_renew_data_array.add(sme_listbox_data);
                    LogMessages.statusLog.info("*** All Data Of SME listbox fetch successfully ***");
                }
            } 
            catch (Exception e) 
            {
              e.printStackTrace();
                 
            }
            //fetching limitmaintenance combobox of KYC
             try 
            {
                String sme_limitmaintenance_query = "select prioritysector,industrysector from TBL_LimitMaintenance  with(nolock) where  PID='" + PID + "'";
                
                 LogMessages.statusLog.info("SME limitmaintenance data fetch query: " + sme_limitmaintenance_query);
                     List sme_limitmaintenance__list = iFormObj.getDataFromDB(sme_limitmaintenance_query);
                     ListIterator sme_limitmaintenance_list_iterator = sme_limitmaintenance__list.listIterator();

                 while (sme_limitmaintenance_list_iterator.hasNext()) 
                 {
                    List sme_limitmaintenance_list_data = (List) sme_limitmaintenance_list_iterator.next();
                    LogMessages.statusLog.info("List Object of SME limitmaintenance data: " + sme_limitmaintenance_list_data);
                    String[] itemsArray = new String[sme_limitmaintenance_list_data.size()];
                    itemsArray = (String[]) sme_limitmaintenance_list_data.toArray(itemsArray);
                    LogMessages.statusLog.info("Array of SME limitmaintenance  data: " + itemsArray);

                    JSONObject sme_limitmaintenance_data = new JSONObject();
                    
                    sme_limitmaintenance_data.put("prioritysector", itemsArray[0]);
                    sme_limitmaintenance_data.put("industrysector", itemsArray[1]);
                    
                     sme_review_renew_data_array.add(sme_limitmaintenance_data);
                    LogMessages.statusLog.info("*** All Data Of SME limitmaintenance fetch successfully ***");
                }
            } 
            catch (Exception e) 
            {
              e.printStackTrace();
                 
            }
            
            // grid for KYC (TAB) */
            //for CS_ShareholdingDetailsRet
           JSONArray jsonArray_ShareholdingDetailsRet = new JSONArray();
            try 
            {
                int count = 0;
                
                String query = "select Name,Holding,FathersName,GrandFathersName,SpouseName,CitizenshipNo,"
                        + "CitizenshipIssuedDate,CitizenshipIssuedDistrict,PassportNo,DOB,Gender,"
                        + "IsGuarantor,CIC_required,Amount,Networth from CS_ShareholdingDetailsRet with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    LogMessages.statusLog.info("rowData: " + rowData);
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    LogMessages.statusLog.info("itemsArray: " + itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Name", itemsArray[0]);
                    obj.put("Holding (%)", itemsArray[1]);
                    obj.put("Father Name", itemsArray[2]);
                    obj.put("Grand Father Name", itemsArray[3]);
                    obj.put("Spouse Name", itemsArray[4]);
                    obj.put("Citizenship No.", itemsArray[5]);
                    obj.put("Citizenship Issued Date", itemsArray[6]);
                    obj.put("Citizenship Issued District",itemsArray[7]);
                    obj.put("Passport Number",itemsArray[8]);
                    obj.put("Date of Birth",itemsArray[9]);
                    obj.put("Gender",itemsArray[10]);
                    obj.put("Is Guarantor",itemsArray[11]);
                    obj.put("Is CIC Required?",itemsArray[12]);
                    obj.put("Amount", itemsArray[13]);
                    obj.put("Networth of the Guarantor", itemsArray[14]);
                    
                    
                    
                    LogMessages.statusLog.info("*** CS_ShareholdingDetailsRet position zero :: ***" +itemsArray[0] );
                    jsonArray_ShareholdingDetailsRet.clear();
                    jsonArray_ShareholdingDetailsRet.add(obj);
                    iFormObj.addDataToGrid("tblShareholdingSME", jsonArray_ShareholdingDetailsRet);
                    LogMessages.statusLog.info("*** Current Count:: ***" + count);
                    count++;
                }
                
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_ShareholdingDetailsRet", e);
            }
            
            //for CS_KeyPersonsDetailsSME
            JSONArray jsonArray_KeyPersonsDetailsSME = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Name,CitizenshipNo,IssuedDateBS,IssuedDate,IssuedBy,"
                        + "PassportNoKeyPerson,DrivingLicenseNoKeyPerson,EmployeeIdNoKeyPerson,"
                        + "OtherIdentificationKeyPerson,StatusForProfitInst,IsGuarantor from CS_KeyPersonsDetailsSME with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    LogMessages.statusLog.info("rowData: " + rowData);
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    LogMessages.statusLog.info("itemsArray: " + itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Name", itemsArray[0]);
                    obj.put("Citizenship No.", itemsArray[1]);
                    obj.put("Issued Date BS", itemsArray[2]);
                    obj.put("Issued Date", itemsArray[3]);
                    obj.put("Issued By (District)", itemsArray[4]);
                    obj.put("Passport No.", itemsArray[5]);
                    obj.put("Driving License No.", itemsArray[6]);
                    obj.put("Employee Identity No.", itemsArray[7]);
                    obj.put("Other", itemsArray[8]);
                    obj.put("Status", itemsArray[9]);
                    obj.put("Is Guarantor", itemsArray[10]);
                    //obj.put("Marital Status", itemsArray[11]);
                    //obj.put("Relationship Medium", itemsArray[12]);
                    //obj.put("Issued Date", itemsArray[13]);
                    //obj.put("Other", itemsArray[7]);
                    
                    //obj.put("IssuedDate", itemsArray[8]);
                    //obj.put("Status", itemsArray[9]);
                    //obj.put("Is Guarantor", itemsArray[10]);
                    
                    LogMessages.statusLog.info("*** CS_KeyPersonsDetailsSME position zero :: ***" +itemsArray[0] );
                    jsonArray_KeyPersonsDetailsSME.clear();
                    jsonArray_KeyPersonsDetailsSME.add(obj);
                    iFormObj.addDataToGrid("tblKeyPersonSME", jsonArray_KeyPersonsDetailsSME);
                    LogMessages.statusLog.info("*** Current Count:: ***" + count);
                    count++;
                }
                
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_KeyPersonsDetailsSME", e);
            }
            
            //for CS_KeyPersonCitizenDetailsRet
            JSONArray jsonArray_KeyPersonCitizenDetailsRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Name,CitizenshipNo,IssuedBy,IssuedDate from CS_KeyPersonCitizenDetailsRet with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    LogMessages.statusLog.info("rowData: " + rowData);
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    LogMessages.statusLog.info("itemsArray: " + itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Name", itemsArray[0]);
                    obj.put("Citizenship Number", itemsArray[1]);
                    obj.put("Issued By (District)", itemsArray[2]);
                    obj.put("Issued Date", itemsArray[3]);
                    
                    LogMessages.statusLog.info("*** CS_KeyPersonCitizenDetailsRet position zero :: ***" +itemsArray[0] );
                    jsonArray_KeyPersonCitizenDetailsRet.clear();
                    jsonArray_KeyPersonCitizenDetailsRet.add(obj);
                    iFormObj.addDataToGrid("tblCitizenshipDetailsProfitInst", jsonArray_KeyPersonCitizenDetailsRet);
                    LogMessages.statusLog.info("*** Current Count:: ***" + count);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
                
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_KeyPersonCitizenDetailsRet", e);
            }
            
            //facilities TAB
            // for CS_FacilitiesSME
            JSONArray jsonArray_FacilitiesSME = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select IDFacility,TypeOfFacility,Exposure,CreditFacility,RefDesc,PurposeforCreditFac,OtherCreditFacility,"
                        + "OtherPurpose,Class,RiskWeightage,STRCharge,NatureOfFacility,FacilityForInnerLimit,RelatedFacID,Limit,Outstanding,"
                        + "OverduePrincipal,OverdueInterest,IncreaseRequired,ProposedLimit,FinalDateOfRepayment,TenureFirstDrawdown,"
                        + "TenureOfEachLoan,TenureExpiryDate,NewLimitAfterIncrease,BaseRate,Premium,InterestRate,CommitmentFee,"
                        + "LoanAdminFee,PrepaymentFee,LCCommission,GuaranteeCommission,AcceptanceFee,BillsPurchase,ProposedMargin,"
                        + "Commission,TypeOfInstallment,Frequency,FirstInstallmentDue,AddInstallment,Amount,Amount1,GracePeriod,"
                        + "Mode,TotalEstdCost,BorrowingPower,NetAmountBorrowed,DetaiilitemImported,SourceRepayment,PaymentMethod,"
                        + "Usanceperiod,OtherUsancePeriod,DeferredPaymentMonths,CashMargin,PurposeTransactionDet,OtherOneoffPurpose,"
                        + "MaxTenureGurantee,JustificationFacilities,OtherChargersWavers,ExistingOrNewFac,rate,"
                        + "AdditionalMargin,DPMargin from CS_FacilitiesSME with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    LogMessages.statusLog.info("rowData: " + rowData);
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    LogMessages.statusLog.info("itemsArray: " + itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Facility ID", itemsArray[0]);
                    //obj.put("Relationship", itemsArray[1]);
                    obj.put("Type of Facility", itemsArray[1]);
                    obj.put("Exposure", itemsArray[2]);
                    obj.put("Credit Facility", itemsArray[3]);
                    obj.put("REF_DESC", itemsArray[4]);
                    obj.put("Purpose(For Selected Credit Facility)", itemsArray[5]);
                    obj.put("Other Credit Facility", itemsArray[6]);
                    obj.put("Other Purpose", itemsArray[7]);
                    obj.put("Class", itemsArray[8]);
                    obj.put("Risk Weightage", itemsArray[9]);
                    obj.put("Risk Classification", itemsArray[10]);
                    obj.put("Nature of Facility", itemsArray[11]);
                    obj.put("Identification of Primary Facility for Inner Limit", itemsArray[12]);
                    obj.put("Related Facility ID", itemsArray[13]);
                    obj.put("Limit", itemsArray[14]);
                    obj.put("Outstanding", itemsArray[15]);
                    obj.put("Overdue Principal (if any)", itemsArray[16]);
                    obj.put("Overdue Interest (if any)", itemsArray[17]);
                    obj.put("Change in Limit required?", itemsArray[18]);
                    obj.put("Proposed Limit", itemsArray[19]);
                    obj.put("Tenure (Final Date of Repayment or expiry)", itemsArray[20]);
                    obj.put("Tenure (Months from First Drawdown)", itemsArray[21]);
                    obj.put("Tenure of each loan (days)", itemsArray[22]);
                    obj.put("Tenure (Expiry Date)", itemsArray[23]);
                    obj.put("New Proposed Limit (After Increase)", itemsArray[24]);
                    obj.put("Base Rate", itemsArray[25]);
                    obj.put("Premium", itemsArray[26]);
                    obj.put("Interest Rate (Base Rate +/-) ", itemsArray[27]);
                    obj.put("Commitment Fee", itemsArray[28]);
                    obj.put("Loan Administration Fee (%)", itemsArray[29]);
                    obj.put("Prepayment Fee", itemsArray[30]);
                    obj.put("LC Commission", itemsArray[31]);
                    obj.put("Gurantee Commission", itemsArray[32]);
                    obj.put("Acceptance Fee/Commission", itemsArray[33]);
                    obj.put("Bills Purchase/ Discount Commission for Interest", itemsArray[34]);
                    obj.put("Proposed Margin", itemsArray[35]);
                    obj.put("Commission", itemsArray[36]);
                    obj.put("Type of Installment", itemsArray[37]);
                    obj.put("Frequency", itemsArray[38]);
                    obj.put("First Installment Due (Days from First Drawdown)", itemsArray[39]);
                    obj.put("Add Installment (Days from Previous Installment)", itemsArray[40]);
                    obj.put("Amount", itemsArray[41]);
                    obj.put("Amount1", itemsArray[42]);
                    obj.put("Grace Period (Moratorium) - Days", itemsArray[43]);
                    obj.put("Mode", itemsArray[44]);
                    obj.put("Total estimated cost of the project or asset/s", itemsArray[45]);
                    obj.put("Borrowing Power", itemsArray[46]);
                    obj.put("Net Amount that can be borrowed", itemsArray[47]);
                    obj.put("Detail of item being imported", itemsArray[48]);
                    obj.put("Source of repayment", itemsArray[49]);
                    obj.put("Payment Method", itemsArray[50]);
                    obj.put("Usance Period", itemsArray[51]);
                    obj.put("Other Usance Period", itemsArray[52]);
                    obj.put("Deferred Payment (Months)", itemsArray[53]);
                    obj.put("Cash Margin", itemsArray[54]);
                    obj.put("Purpose(one-off basis)", itemsArray[55]);
                    obj.put("Other Purpose(one-off)", itemsArray[56]);
                    obj.put("Maximum Tenure of Guarantee (Days)", itemsArray[57]);
                    obj.put("Justification Of Limit", itemsArray[58]);
                    obj.put("REF_DESC_copy", itemsArray[59]);
                    obj.put("Relationship", itemsArray[60]);
                    obj.put("Rate", itemsArray[61]);
                    obj.put("AdditionalMargin", itemsArray[62]);
                    obj.put("DP Margin", itemsArray[63]);
                    //obj.put("Interest Rate (Base Rate +/-) ", itemsArray[63]);
                    //obj.put("Rate", itemsArray[62]);
                    /*obj.put("Other Purpose(one-off)", itemsArray[61]);
                    obj.put("Justification Of Limit", itemsArray[62]);*/
                    
                    LogMessages.statusLog.info("*** CS_FacilitiesSME position zero :: ***" +itemsArray[0] );
                    jsonArray_FacilitiesSME.clear();
                    jsonArray_FacilitiesSME.add(obj);
                    iFormObj.addDataToGrid("tblFacilitiesSME", jsonArray_FacilitiesSME);
                    LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_FacilitiesSME", e);

            }
            // facilities Tab
            //for CS_CustomizedInstallmentRet
            
            JSONArray jsonArray_CustomizedInstallmentRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Installment,InstallmentDueDays,Amount from CS_CustomizedInstallmentRet with(nolock) where  PID='" + PID + "'";
                
                   LogMessages.statusLog.info("Query: " + query);
                   List QueryList = iFormObj.getDataFromDB(query);
                   LogMessages.statusLog.info("QueryList: " + QueryList);
                
                   ListIterator QueryListItr = QueryList.listIterator();
                 while (QueryListItr.hasNext()) 
                 {
                    List rowData = (List) QueryListItr.next();
                    LogMessages.statusLog.info("rowData: " + rowData);
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    LogMessages.statusLog.info("itemsArray: " + itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Installment", itemsArray[0]);
                    obj.put("Installment Due (Days from Drawdown)", itemsArray[1]);
                    obj.put("Amount", itemsArray[2]);
                    
                    LogMessages.statusLog.info("*** CS_CustomizedInstallmentRet position zero :: ***" +itemsArray[0] );
                    jsonArray_CustomizedInstallmentRet.clear();
                    jsonArray_CustomizedInstallmentRet.add(obj);
                    iFormObj.addDataToGrid("tblCustomizedInstallmentSME", jsonArray_CustomizedInstallmentRet);
                    LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_CustomizedInstallmentRet", e);

            }
            //Security Details & Documents
            //for CS_RealEstatePropertyRet
            
             JSONArray jsonArray_RealEstatePropertyRet = new JSONArray();
            
            try 
            {
                int count = 0;
               String query = "select bsvrid,NatureOfCharge,OtherNatureOfCharge,PlotNo11,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,PropertyType,CollateralShared,Facilities,UtilizedFMV,UtilizedDV,Remarks,Owner,NewOwner,"
+ "RelationToBorrower,Area,NoOfPlots,PlotNo,PlotArea1,AreaPlot,PlotNo2,PlotArea2,AreaPlot2,PlotNo3,PlotArea3,AreaPlot3,PlotNo4,PlotArea4,AreaPlot4,PlotNo5,PlotArea5,AreaPlot5,PlotNo6,PlotArea6,AreaPlot6,PlotNo7,"
+ "PlotArea7,AreaPlot7,PlotNo8,PlotArea8,AreaPlot8,PlotNo9,PlotArea9,AreaPlot9,PlotNo10,PlotArea10,AreaPlot10,TotalAreaPlot,Ropani,Aana,Paisa,RAPD,Bigha,Kattha,Dhur,BKDH,AreaLand,District,Municipality,LocationLOC,"
+ "OwnershipType,StatusMortgageCharge,LatestDateExecution,mortgageamount,ApartmentName,AreaBuildingApartment,Storey,PlotNumber,StatusBankMortgageCharge,DateOfExecution,BasisOfValuation,ValuedBy,PlotNo12,DateOfValuation,"
+ "estimatedfmv,estimateddv,PurchasePrice,InsuranceRequired,house_checkbox,fire_checkbox,buglary_checkbox,terrorism_checkbox,earthquake_checkbox,vehicle_checkbox,thirdparty_checkbox,comprehensive_checkbox,"
+ "accidental_checkbox,marine_checkbox,flood_checkbox,landslide_checkbox,withwaiver_checkbox,AmountofInsurance,Insurancecoveragestartdate,InsuranceExpirydate,DetailofWaivers,DateOfInspection,NameInspectingOfficial,"
+ "DesignationInspectingOfficial,DateOfTransfer,ModeOfTransfer,OtherModeOfTransfer,LandCategory,OtherLandCategory,QualityAccessRoad,OtherRoadAccess,WidthRoad,Roadaccess,ShapeOfLand,OtherShapeOfLand,DistanceFromBranch,"
+ "BuildingCategory,OtherBuildingCategory,ConstructionQuality,OtherConstructionQuality,DateNirmanIjjajat,DateNirmanSampanna,Lalpurja,ValuationReport,FourBoundary,TraceMap,OwnershipTransferDoc,LandRevenueReceipt,RegMortgageDeed,"
+ "InternalMortgageDeed,RokkaMalpot,RokkaGuthi,NirmanIjjajat,NirmanSampanna,CcitizenshipFirmCertificate,GharNaksa,InsurancePolicy,LandVerticallySloped,LandWithinRiverForest,LandWithinConservationArea,LandWithinDasgaja,LandHasPondPool,"
+ "LandShapeConsistent,LandRowSetback,RiverCanalSetback,HighTensionSetback,OtherSetbacks,SetbacksAffectsLandShape,RemAreaSetbackAdjustment,SetbacksAffectsLandValue,SetbacksAffectsLandSalability,ValReportDeductions,NoStoreyConstructionApproval,"
+ "BoundaryWall,WaterSupply,ElectricityConnection,DrainageConnection,BluePrintObtained,PropertyConsistentWithBluePrint,EnquiredWithFamily,ReviewedValuationReport,ConsideredFMV,ConsiderPropertyMortageable,PlotNo14,PlotNo15,PlotNo16,SiteImages,Latitude,"
+ "CSVRRemarks,GoogleMap,PlotNo13,InsuranceRequired,InsuranceExpirydate,Longitude from CS_RealEstatePropertyRet with(nolock) where PID='" + PID + "'";
                
                        LogMessages.statusLog.info("Query: " + query);
                        List QueryList = iFormObj.getDataFromDB(query);
                        LogMessages.statusLog.info("QueryList: " + QueryList);
                
                        ListIterator QueryListItr = QueryList.listIterator();
                        while (QueryListItr.hasNext()) 
                        {
                           List rowData = (List) QueryListItr.next();
                           LogMessages.statusLog.info("rowData: " + rowData);
                           String[] itemsArray = new String[rowData.size()];
                           itemsArray = (String[]) rowData.toArray(itemsArray);
                           LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           JSONObject obj = new JSONObject();

                           obj.put("CSVR ID", itemsArray[0]);
                           obj.put("Nature of Charge", itemsArray[1]);
                           obj.put("Other Nature of Charge", itemsArray[2]);
                           obj.put("Collateral Type", itemsArray[3]);
                           obj.put("Security Type", itemsArray[4]);
                           obj.put("Sub Security Type", itemsArray[5]);
                           obj.put("Description", itemsArray[6]);
                           obj.put("Security Code", itemsArray[7]);
                           obj.put("Property Type", itemsArray[8]);
                           obj.put("Is the Collateral Shared?", itemsArray[9]);
                           obj.put("Facilities", itemsArray[10]);
                           obj.put("Utilized Fair Market Value(FMV)", itemsArray[11]);
                           obj.put("Utilized Distress Value(DV)", itemsArray[12]);
                           obj.put("Remarks", itemsArray[13]);
                           obj.put("Current Owner", itemsArray[14]);
                           obj.put("New Owner", itemsArray[15]);
                           obj.put("Relation to Borrower", itemsArray[16]);
                           obj.put("Area (Land)", itemsArray[17]);
                           obj.put("Number of Plots", itemsArray[18]);
                           obj.put("Plot Number (Land)", itemsArray[19]);
                           obj.put("Area Plot 1", itemsArray[20]);
                           obj.put("Area of Plot", itemsArray[21]);
                           obj.put("Plot Number (Land) 2", itemsArray[22]);
                           obj.put("Area Plot 2", itemsArray[23]);
                           obj.put("Area of Plot 2", itemsArray[24]);
                           obj.put("Plot Number (Land) 3", itemsArray[25]);
                           obj.put("Area Plot 3", itemsArray[26]);
                           obj.put("Area of Plot 3", itemsArray[27]);
                           obj.put("Plot Number (Land) 4", itemsArray[28]);
                           obj.put("Area Plot 4", itemsArray[29]);
                           obj.put("Area of Plot 4", itemsArray[30]);
                           obj.put("Plot Number (Land) 5", itemsArray[31]);
                           obj.put("Area Plot 5", itemsArray[32]);
                           obj.put("Area of Plot 5", itemsArray[33]);
                           obj.put("Plot Number (Land) 6", itemsArray[34]);
                           obj.put("Area Plot 6", itemsArray[35]);
                           obj.put("Area of Plot 6", itemsArray[36]);
                           obj.put("Plot Number (Land) 7", itemsArray[37]);
                           obj.put("Area Plot 7", itemsArray[38]);
                           obj.put("Area of Plot 7", itemsArray[39]);
                           obj.put("Plot Number (Land) 8", itemsArray[40]);
                           obj.put("Area Plot 8", itemsArray[41]);
                           obj.put("Area of Plot 8", itemsArray[42]);
                           obj.put("Plot Number (Land) 9", itemsArray[43]);
                           obj.put("Area Plot 9", itemsArray[44]);
                           obj.put("Area of Plot 9", itemsArray[45]);
                           obj.put("Plot Number (Land) 10", itemsArray[46]);
                           obj.put("Area Plot 10", itemsArray[47]);
                           obj.put("Area of Plot 10", itemsArray[48]);
                           obj.put("Total Area of Plot", itemsArray[49]);
                           obj.put("Ropani", itemsArray[50]);
                           obj.put("Aana", itemsArray[51]);
                           obj.put("Paisa", itemsArray[52]);
                           obj.put("Total R-A-P-D", itemsArray[53]);
                           obj.put("Bigha", itemsArray[54]);
                           obj.put("Kattha", itemsArray[55]);
                           obj.put("Dhur", itemsArray[56]);
                           obj.put("Total B-K-D-H", itemsArray[57]);
                           obj.put("Total Area of Land in Square Meters", itemsArray[58]);
                           obj.put("District", itemsArray[59]);
                           obj.put("Municipality", itemsArray[60]);
                           obj.put("Location as per LOC", itemsArray[61]);
                           obj.put("Ownership Type", itemsArray[62]);
                           obj.put("Status of Bank's Mortgage Charge (Registered or Unregistered)", itemsArray[63]);
                           obj.put("Latest Date of Execution/Registration of Charge", itemsArray[64]);
                           obj.put("Mortgage Amount", itemsArray[65]);
                           obj.put("Apartment Name", itemsArray[66]);
                           obj.put("Area of Building, Apartment (Existing and/or Proposed)", itemsArray[67]);
                           obj.put("Storey (Existing and/or Proposed)", itemsArray[68]);
                           obj.put("Plot Number (Constructed Area)", itemsArray[69]);
                           obj.put("Status of Bank's Mortgage Charge", itemsArray[70]);
                           obj.put("Latest Date of Execution / Registration of Charge", itemsArray[71]);
                           obj.put("Basis of Valuation", itemsArray[72]);
                           obj.put("Valued By", itemsArray[73]);
                           obj.put("RemarksValuation", itemsArray[74]);
                           obj.put("Date of Valuation", itemsArray[75]);
                           obj.put("Fair Market Value", itemsArray[76]);
                           obj.put("Distress Value", itemsArray[77]);
                           obj.put("Purchase Price", itemsArray[78]);
                           obj.put("Insurance Required", itemsArray[79]);
                           obj.put("House", itemsArray[80]);
                           obj.put("Fire", itemsArray[81]);
                           obj.put("Buglary", itemsArray[82]);
                           obj.put("Terrorism", itemsArray[83]);
                           obj.put("Earthquake", itemsArray[84]);
                           obj.put("Vehicle", itemsArray[85]);
                           obj.put("Third Party", itemsArray[86]);
                           obj.put("Comprehensive", itemsArray[87]);
                           obj.put("Accidental", itemsArray[88]);
                           obj.put("Marine", itemsArray[89]);
                           obj.put("Flood", itemsArray[90]);
                           obj.put("Land Slide", itemsArray[91]);
                           obj.put("With Waivers", itemsArray[92]);
                           obj.put("Amount of Insurance", itemsArray[93]);
                           obj.put("Insurance coverage start date", itemsArray[94]);
                           obj.put("Insurance Expiry date", itemsArray[95]);
                           obj.put("Detail of Waivers", itemsArray[96]);
                           obj.put("Date of Inspection/Site Visit", itemsArray[97]);
                           obj.put("Name of the Inspecting Official/s", itemsArray[98]);
                           obj.put("Designation of the Inspecting Official/s", itemsArray[99]);
                           obj.put("Date of Transfer of Title (Land)", itemsArray[100]);
                           obj.put("Mode of Transfer of Title (Land)", itemsArray[101]);
                           obj.put("Mode of Transfer of Title (Other)", itemsArray[102]);
                           obj.put("Land Category (Utility)", itemsArray[103]);
                           obj.put("Land Category (Other)", itemsArray[104]);
                           obj.put("Quality of Access Road", itemsArray[105]);
                           obj.put("Quality of Access Road (Other)", itemsArray[106]);
                           obj.put("Width of the Road", itemsArray[107]);
                           obj.put("Road Access", itemsArray[108]);
                           obj.put("Shape of the Land", itemsArray[109]);
                           obj.put("Shape of Land (Other)", itemsArray[110]);
                           obj.put("Distance of the Location from the Branch in km", itemsArray[111]);
                           obj.put("Building Category (Utility)", itemsArray[112]);
                           obj.put("Building Category (Other)", itemsArray[113]);
                           obj.put("Construction Quality", itemsArray[114]);
                           obj.put("Construction Quality (Other)", itemsArray[115]);
                           obj.put("Date of permission for construction certificate / Nirman Ijjajat", itemsArray[116]);
                           obj.put("Date of building completion certificate / Nirman Sampanna", itemsArray[117]);
                           obj.put("Land Ownership Certificate / Lalpurja", itemsArray[118]);
                           obj.put("Valuation Report", itemsArray[119]);
                           obj.put("Four Boundary / Char Killa", itemsArray[120]);
                           obj.put("Trace Map / Blue Print", itemsArray[121]);
                           obj.put("Ownership Transfer Document (Rajinama, Bakas Patra, etc.)", itemsArray[122]);
                           obj.put("Land Revenue Report (Tiro)/Property Tax Paid Receipt", itemsArray[123]);
                           obj.put("Registered Mortgage Deed", itemsArray[124]);
                           obj.put("Internal Mortgage Deed (For Unregistered or Additional Charge)", itemsArray[125]);
                           obj.put("Rokka Letter from the Concerned Malpot Office", itemsArray[126]);
                           obj.put("Rokka Letter from the Concerned Guthi", itemsArray[127]);
                           obj.put("Permission for Construction Certificate / Nirman Ijjajat", itemsArray[128]);
                           obj.put("Building Completion Certificate / Nirman Sampanna", itemsArray[129]);
                           obj.put("Citizenship Certificate/Firm Registration Certificate of Owner", itemsArray[130]);
                           obj.put("Architect's design document of the building / Ghar Naksa", itemsArray[131]);
                           obj.put("Insurance policy with premium paid receipt", itemsArray[132]);
                           obj.put("1. The Land is not vertically sloped", itemsArray[133]);
                           obj.put("2. The land is not within 60 meters’ distance from RIVER/FOREST/P", itemsArray[134]);
                           obj.put("3. The land is not within 100 meters’ distance from CONSERVATION ", itemsArray[135]);
                           obj.put("4. The land is not within 500 meters’ distance from NO MAN’S LAND", itemsArray[136]);
                           obj.put("5. The land doesn’t have POND/POOL within valuation considered ar", itemsArray[137]);
                           obj.put("6. The location and shape of the land appear consistent with Topo", itemsArray[138]);
                           obj.put("7. The land does not have potential ROW setback", itemsArray[139]);
                           obj.put("8. There aren't any river/canal setback", itemsArray[140]);
                           obj.put("9. There aren't any high-tension wire setback", itemsArray[141]);
                           obj.put("10. There aren't any other setbacks", itemsArray[142]);
                           obj.put("11. The setbacks would not affect the shape of the land", itemsArray[143]);
                           obj.put("12. The remaining area of the land after the setback adjustments ", itemsArray[144]);
                           obj.put("13. The setbacks would not affect value of the land", itemsArray[145]);
                           obj.put("14. The setbacks would not affect salability of the land", itemsArray[146]);
                           obj.put("15. The valuation report has made appropriate deductions on accou", itemsArray[147]);
                           obj.put("16. No of storey matches with the construction approval", itemsArray[148]);
                           obj.put("17. Is there a boundary wall in the property?", itemsArray[149]);
                           obj.put("18. Is there water supply connection in the property?", itemsArray[150]);
                           obj.put("19. Is there electricity connection in the property?", itemsArray[151]);
                           obj.put("20. Is there a drainage connection in the property?", itemsArray[152]);
                           obj.put("21. Blue Print and Trace Map has been obtained by the Bank staff ", itemsArray[153]);
                           obj.put("22. To the best of my/our knowledge, the shape, area and size of ", itemsArray[154]);
                           obj.put("23. I/we have enquired with family member of collateral owner as ", itemsArray[155]);
                           obj.put("24. I/we have reviewed the valuation report which, I /we am/are s", itemsArray[156]);
                           obj.put("25. I/we consider the Fair Market Value of Preliminary/ Full Valu", itemsArray[157]);
                           obj.put("26. I/we consider the property is acceptable for mortgage to Bank", itemsArray[158]);
                           obj.put("Revised CSVR is uploaded", itemsArray[159]);
                           obj.put("Full format CSVR is uploaded", itemsArray[160]);
                           obj.put("Remarksforquestionno27", itemsArray[161]);
                           obj.put("SiteImage", itemsArray[162]);
                           obj.put("Latitude", itemsArray[163]);
                           obj.put("CSVR Remarks", itemsArray[164]);
                           obj.put("Google Map", itemsArray[165]);
                           obj.put("I certify that the above mentioned details of real estate propert", itemsArray[166]);
                           obj.put("Insurance Required ", itemsArray[167]);
                           obj.put("Insurance Expiry date ", itemsArray[168]);
                           obj.put("Longitude", itemsArray[169]);

                    LogMessages.statusLog.info("*** CS_RealEstatePropertyRet position zero :: ***" +itemsArray[0] );
                          
                           
                          
                           
                           LogMessages.statusLog.info("*** CS_RealEstatePropertyRet position zero :: ***" +itemsArray[0] );
                           jsonArray_RealEstatePropertyRet.clear();
                           jsonArray_RealEstatePropertyRet.add(obj);
                           iFormObj.addDataToGrid("tblRealEstateSecuritySME", jsonArray_RealEstatePropertyRet);
                           LogMessages.statusLog.info("*** Data added to  the grid ***");
                           count++;
                        }
                    } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_RealEstatePropertyRet", e);

                    }
            


              //security Details & Documents
              //for CS_PlotDetails
              
              JSONArray jsonArray_PlotDetails = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select NoofPlots,RoadAccess,QualityAccessRoad,WidthRoad,Frontage,RemarksPlot  from CS_PlotDetails with(nolock) where  PID='" + PID + "'";
                
                        LogMessages.statusLog.info("Query: " + query);
                        List QueryList = iFormObj.getDataFromDB(query);
                        LogMessages.statusLog.info("QueryList: " + QueryList);
                
                        ListIterator QueryListItr = QueryList.listIterator();
                        while (QueryListItr.hasNext()) 
                        {
                           List rowData = (List) QueryListItr.next();
                           LogMessages.statusLog.info("rowData: " + rowData);
                           String[] itemsArray = new String[rowData.size()];
                           itemsArray = (String[]) rowData.toArray(itemsArray);
                           LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           JSONObject obj = new JSONObject();

                           obj.put("No of Plots", itemsArray[0]);
                           obj.put("Road Access", itemsArray[1]);
                           obj.put("Quality of Access road", itemsArray[2]);
                           obj.put("Width of Road", itemsArray[3]);
                           obj.put("Frontage", itemsArray[4]);
                           obj.put("Remarks", itemsArray[5]);
                           
                           LogMessages.statusLog.info("*** CS_PlotDetails position zero :: ***" +itemsArray[0] );
                           jsonArray_PlotDetails.clear();
                           jsonArray_PlotDetails.add(obj);
                           iFormObj.addDataToGrid("tblPlotDetails", jsonArray_PlotDetails);
                           LogMessages.statusLog.info("*** Data added to  the grid ***");
                           count++;
                        }
                    } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_PlotDetails", e);

                    }
            //for security Details & Documents
            //for CS_AutomobileRet
            
             JSONArray jsonArray_AutomobileRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select allotmentletter,basisofvaluation,brand,chassisno,copyofbluebook,dateofvaluation,engineno,estimateddv,"
                        + "estimatedfmv,hirepurchaseagreement,insurancepolicy,latestdateexecution,purchaseprice,quotationinvoice,registrationno,"
                        + "statushypothecationcharge,thirdpartyownershiptransfer,valuedby,vatbill,yearofproduction,modelname,natureofcharge,"
                        + "securitytype,subsecuritytype,securitydescription,othernatureofcharge,insurancerequired,house_checkbox,"
                        + "fire_checkbox,buglary_checkbox,terrorism_checkbox,earthquake_checkbox,vehicle_checkbox,thirdparty_checkbox,"
                        + "comprehensive_checkbox,accidental_checkbox,marine_checkbox,flood_checkbox,landslide_checkbox,amountofinsurance,"
                        + "insurancecoveragestartdate,insuranceexpirydate,detailofwaivers,withwaiver_checkbox,collateraltype,SecurityCode  from CS_AutomobileRet with(nolock) where  PID='" + PID + "'";
                
                        LogMessages.statusLog.info("Query: " + query);
                        List QueryList = iFormObj.getDataFromDB(query);
                        LogMessages.statusLog.info("QueryList: " + QueryList);
                
                        ListIterator QueryListItr = QueryList.listIterator();
                        while (QueryListItr.hasNext()) 
                        {
                           List rowData = (List) QueryListItr.next();
                           LogMessages.statusLog.info("rowData: " + rowData);
                           String[] itemsArray = new String[rowData.size()];
                           itemsArray = (String[]) rowData.toArray(itemsArray);
                           LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                           JSONObject obj = new JSONObject();

                           obj.put("Allotment Letter", itemsArray[0]);
                           obj.put("Basis of Valuation", itemsArray[1]);
                           obj.put("Brand", itemsArray[2]);
                           obj.put("Chasis No.", itemsArray[3]);
                           obj.put("Copy of Blue Book noting the Bank's Interest", itemsArray[4]);
                           obj.put("Date of Valuation", itemsArray[5]);
                           obj.put("Engine No.", itemsArray[6]);
                           obj.put("Estimated Distress Value", itemsArray[7]);
                           obj.put("Estimated Fair Market Value", itemsArray[8]);
                           obj.put("Hire purchase agreement", itemsArray[9]);
                           obj.put("Insurance policy with premium paid receipt", itemsArray[10]);
                           obj.put("Latest Date of Execution/Registration of Charge", itemsArray[11]);
                           obj.put("Purchase Price", itemsArray[12]);
                           obj.put("Quotation/Proforma Invoice", itemsArray[13]);
                           obj.put("Registration Number", itemsArray[14]);
                           obj.put("Status of Bank's Hypothecation Charge", itemsArray[15]);
                           obj.put("Third-party ownership transfer", itemsArray[16]);
                           obj.put("Valued By", itemsArray[17]);
                           obj.put("VAT Bill / Tax Invoice", itemsArray[18]);
                           obj.put("Year of Production", itemsArray[19]);
                           obj.put("Model", itemsArray[20]);
                           obj.put("Nature of Charge", itemsArray[21]);
                           obj.put("Security Type", itemsArray[22]);
                           obj.put("Sub Security Type", itemsArray[23]);
                           obj.put("Description", itemsArray[24]);
                           obj.put("Other Nature of Charge", itemsArray[25]);
                           obj.put("Insurance Required ", itemsArray[26]);
                           obj.put("House", itemsArray[27]);
                           obj.put("Fire", itemsArray[28]);
                           obj.put("Buglary", itemsArray[29]);
                           obj.put("Terrorism", itemsArray[30]);
                           obj.put("Earthquake", itemsArray[31]);
                           obj.put("Vehicle", itemsArray[32]);
                           obj.put("Third Party", itemsArray[33]);
                           obj.put("Comprehensive", itemsArray[34]);
                           obj.put("Accidental", itemsArray[35]);
                           obj.put("Marine", itemsArray[36]);
                           obj.put("Flood", itemsArray[37]);
                           obj.put("Landslide", itemsArray[38]);
                           obj.put("Amount of Insurance", itemsArray[39]);
                           obj.put("Insurance coverage start date", itemsArray[40]);
                           obj.put("Insurance Expiry date ", itemsArray[41]);
                           obj.put("Detail of Waivers", itemsArray[42]);
                           obj.put("With Waivers", itemsArray[43]);
                           obj.put("Collateral Type", itemsArray[44]);
                           obj.put("Security Code", itemsArray[45]);
                           
                           
                           LogMessages.statusLog.info("*** CS_AutomobileRet position zero :: ***" +itemsArray[0] );
                           jsonArray_AutomobileRet.clear();
                           jsonArray_AutomobileRet.add(obj);
                           iFormObj.addDataToGrid("tblAutomobileSecuritySME", jsonArray_AutomobileRet);
                           LogMessages.statusLog.info("*** Data added to  the grid ***");
                           count++;
                        }
                    } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_AutomobileRet", e);

                    }
            //Security Details & Documents
            // for CS_InventoriesAccReceivableRet
            
            JSONArray jsonArray_InventoriesAccReceivableRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select OtherNatureOfCharge,SecurityCode,EstimatedFMV,EstimatedDV,AmountofInsurance,DetailofWaivers,"
                        + "NameInspectingOfficial,DesignationInspectingOfficial,TotalInventory,TotalReceivable,OtherCurrentAsset,"
                        + "TotalPayables,NetTradingAssets,NatureOfCharge,SecurityType,SubSecurityType,SecurityDescription,TypeOfAssets,"
                        + "StatusHypothecationCharge,BasisOfValuation,InsuranceRequired,BusinessInOperation,BusinessOperationSatisfactory,"
                        + "RegDocPanRenewed,LeaseAgreement,RequisitesForSmoothOperation,AmountInventoryAdequate,QualityInventorySatisfactory,"
                        + "FirefightingMeasures,SecurityArrangements,NoLabourIssues,KeyPersonContacted,NoNegativeNews,BorrowingPoweradhered,"
                        + "InsurancePolicy,AssignmentOfBills,PowerOfAttorney,GeneralLetterHypo,SupplementaryAgreement,PledgeAgreement,"
                        + "RegOfCharge,LatestDateExecution,DateLastStmt,Insurancecoveragestartdate,InsuranceExpirydate,House_Checkbox,Fire_Checkbox,"
                        + "Buglary_Checkbox,Terrorism_Checkbox,Earthquake_Checkbox,Vehicle_Checkbox,Thirdparty_Checkbox,Comprehensive_Checkbox,Accidental_Checkbox,Marine_Checkbox,Flood_Checkbox,Landslide_Checkbox,Withwaiver_Checkbox,collateralType from CS_InventoriesAccReceivableRet with(nolock) where  PID='" + PID + "'";
                
                        LogMessages.statusLog.info("Query: " + query);
                        List QueryList = iFormObj.getDataFromDB(query);
                        LogMessages.statusLog.info("QueryList: " + QueryList);
                
                        ListIterator QueryListItr = QueryList.listIterator();
                        while (QueryListItr.hasNext()) 
                        {
                           List rowData = (List) QueryListItr.next();
                           LogMessages.statusLog.info("rowData: " + rowData);
                           String[] itemsArray = new String[rowData.size()];
                           itemsArray = (String[]) rowData.toArray(itemsArray);
                           LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                           JSONObject obj = new JSONObject();

                           obj.put("Other Nature of Charge", itemsArray[0]);
                           obj.put("Security Code", itemsArray[1]);
                           obj.put("Estimated Fair Market Value", itemsArray[2]);
                           obj.put("Estimated Distress Value", itemsArray[3]);
                           obj.put("Amount of Insurance", itemsArray[4]);
                           obj.put("Detail of Waivers", itemsArray[5]);
                           obj.put("Name of the Inspecting Official/s", itemsArray[6]);
                           obj.put("Designation of the Inspecting Official/s", itemsArray[7]);
                           obj.put("Total Inventory", itemsArray[8]);
                           obj.put("Total Receivable within Approved Tenure", itemsArray[9]);
                           obj.put("Other Current Asset, if allowed for Drawing Power Assessment", itemsArray[10]);
                           obj.put("Total Payables/Creditors", itemsArray[11]);
                           obj.put("Net Trading Assets", itemsArray[12]);
                           obj.put("Nature Of Charge", itemsArray[13]);
                           obj.put("Security Type", itemsArray[14]);
                           obj.put("Sub Security Type", itemsArray[15]);
                           obj.put("Description", itemsArray[16]);
                           obj.put("Type of Assets", itemsArray[17]);
                           obj.put("Status of Bank's Hypothecation Charge", itemsArray[18]);
                           obj.put("Basis of Valuation", itemsArray[19]);
                           obj.put("Insurance Required ", itemsArray[20]);
                           obj.put("1. Business was in operation at the time of inspection", itemsArray[21]);
                           obj.put("2. Overall operation of the business was found to be satisfactory", itemsArray[22]);
                           obj.put("3. Registration document & PAN were renewed & displayed properly", itemsArray[23]);
                           obj.put("4. In case of rented premises, the lease agreement was reviewed", itemsArray[24]);
                           obj.put("5. Basic requisites for smooth operation of business/factory foun", itemsArray[25]);
                           obj.put("6. The amount of inventory & receivables was found to be adequate", itemsArray[26]);
                           obj.put("7. The quality of inventory & receivables was found to be satisfa", itemsArray[27]);
                           obj.put("8. Firefighting measures were in place", itemsArray[28]);
                           obj.put("9. Security arrangements were in place", itemsArray[29]);
                           obj.put("10. There were no labour issues apparent", itemsArray[30]);
                           obj.put("11. The key person of the borrower could be contacted", itemsArray[31]);
                           obj.put("12. There were no negative news regarding Borrower/Promoter/Direc", itemsArray[32]);
                           obj.put("13. Borrowing Power adhered to during entire period since last ap", itemsArray[33]);
                           obj.put("Insurance policy with premium paid receipt", itemsArray[34]);
                           obj.put("Assignment of bills and account receivables", itemsArray[35]);
                           obj.put("Power of attorney", itemsArray[36]);
                           obj.put("General letter of hypothecation", itemsArray[37]);
                           obj.put("Supplementary agreement", itemsArray[38]);
                           obj.put("Pledge agreement", itemsArray[39]);
                           obj.put("Registration of charge under secured transaction registry", itemsArray[40]);
                           obj.put("Latest Date of Execution/Registration of Charge (STR)", itemsArray[41]);
                           obj.put("Date of Last Statement of Net Trading Assets", itemsArray[42]);
                           obj.put("Insurance coverage start date", itemsArray[43]);
                           obj.put("Insurance Expiry date ", itemsArray[44]);
                           obj.put("House", itemsArray[45]);
                           obj.put("Fire", itemsArray[46]);
                           obj.put("Buglary", itemsArray[47]);
                           obj.put("Terrorism", itemsArray[48]);
                           obj.put("Earthquake", itemsArray[49]);
                           obj.put("Vehicle", itemsArray[50]);
                           obj.put("Third Party", itemsArray[51]);
                           obj.put("Comprehensive", itemsArray[52]);
                           obj.put("Accidental", itemsArray[53]);
                           obj.put("marine", itemsArray[54]);
                           obj.put("Flood", itemsArray[55]);
                           obj.put("Landslide", itemsArray[56]);
                           obj.put("With Waivers", itemsArray[57]);
                           obj.put("Collateral Type", itemsArray[58]);
                           
                     LogMessages.statusLog.info("*** CS_InventoriesAccReceivableRet position zero :: ***" +itemsArray[0] );
                     jsonArray_InventoriesAccReceivableRet.clear();
                     jsonArray_InventoriesAccReceivableRet.add(obj);
                     iFormObj.addDataToGrid("tblInventoryARMovableSecuritySME", jsonArray_InventoriesAccReceivableRet);
                     LogMessages.statusLog.info("*** Data added to  the grid ***");
                     count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_InventoriesAccReceivableRet", e);

                    }
            
            // Security Details & Documents
            // for CS_BorrowingpowerAssessment
            
             JSONArray jsonArray_BorrowingpowerAssessment = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Totalinventory,Totalreceivablewithinapprovedtenure,Othercurrentassets,Totalpayablescreditors,"
                        + "NetTradingAssets,Margin,BorrowingPower,Outstanding,SurplusDeficit,Dated from CS_BorrowingpowerAssessment with(nolock) where  PID='" + PID + "'";
                
                        LogMessages.statusLog.info("Query: " + query);
                        List QueryList = iFormObj.getDataFromDB(query);
                        LogMessages.statusLog.info("QueryList: " + QueryList);
                
                        ListIterator QueryListItr = QueryList.listIterator();
                        while (QueryListItr.hasNext()) 
                        {
                           List rowData = (List) QueryListItr.next();
                           LogMessages.statusLog.info("rowData: " + rowData);
                           String[] itemsArray = new String[rowData.size()];
                           itemsArray = (String[]) rowData.toArray(itemsArray);
                           LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                           JSONObject obj = new JSONObject();

                           obj.put("Total inventory", itemsArray[0]);
                           obj.put("Total receivable within approved tenure", itemsArray[1]);
                           obj.put("Other current asset, if allowed for drawing power assessment", itemsArray[2]);
                           obj.put("Total payables/creditors", itemsArray[3]);
                           obj.put("Net Trading Assets", itemsArray[4]);
                           obj.put("Margin", itemsArray[5]);
                           obj.put("Borrowing Power", itemsArray[6]);
                           obj.put("Outstanding", itemsArray[7]);
                           obj.put("Surplus/(Deficit)", itemsArray[8]);
                           obj.put("Date", itemsArray[9]);
                           
                     LogMessages.statusLog.info("*** CS_BorrowingpowerAssessment position zero :: ***" +itemsArray[0] );
                     jsonArray_BorrowingpowerAssessment.clear();
                     jsonArray_BorrowingpowerAssessment.add(obj);
                     iFormObj.addDataToGrid("table108 ( Borrowing power assessment during the review period )", jsonArray_BorrowingpowerAssessment);
                     LogMessages.statusLog.info("*** Data added to  the grid ***");
                     count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_BorrowingpowerAssessment", e);

                    }
           
                // SEcurity Details & Documents
                //for CS_FixedOrBankDeposirRet
                
                 JSONArray jsonArray_FixedOrBankDeposirRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select OtherNatureOfCharge,SecurityCode,AccountNo,ValueOfFixedDepositCharge,NatureOfCharge,"
                        + "SecurityType,SubSecurityType,SecurityDescription,TypeOfDeposit,Currency,StatusBankLienCharge,"
                        + "FixedDepositReceipt,LienChargeOverDeposit,MaturityDate,LatestDateExecution,collateraltype from CS_FixedOrBankDeposirRet with(nolock) where  PID='" + PID + "'";
                
                 LogMessages.statusLog.info("Query: " + query);
                 List QueryList = iFormObj.getDataFromDB(query);
                 LogMessages.statusLog.info("QueryList: " + QueryList);
                
                 ListIterator QueryListItr = QueryList.listIterator();
                 while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("Other Nature of Charge", itemsArray[0]);
                   obj.put("Security Code", itemsArray[1]);
                   obj.put("Fixed Deposit or Bank Deposit Account Number", itemsArray[2]);
                   obj.put("Value of the Fixed Deposit/Charge over Deposit", itemsArray[3]);
                   obj.put("Nature Of Charge", itemsArray[4]);
                   obj.put("Security Type", itemsArray[5]);
                   obj.put("Sub Security Type", itemsArray[6]);
                   obj.put("Description", itemsArray[7]);
                   obj.put("Type of Deposit", itemsArray[8]);
                   obj.put("Currency", itemsArray[9]);
                   obj.put("Status of Bank's Lien Charge", itemsArray[10]);
                   obj.put("Fixed Deposit Receipt", itemsArray[11]);
                   obj.put("Lien Charge over the Deposit", itemsArray[12]);
                   obj.put("Maturity Date", itemsArray[13]);
                   obj.put("Latest Date of Execution", itemsArray[14]);
                   obj.put("Collateral Type", itemsArray[15]);
                   //obj.put("now", itemsArray[16]);
                   
                   LogMessages.statusLog.info("*** CS_FixedOrBankDeposirRet position zero :: ***" +itemsArray[0] );
                   jsonArray_FixedOrBankDeposirRet.clear();
                   jsonArray_FixedOrBankDeposirRet.add(obj);
                   iFormObj.addDataToGrid("tblFixedDepositSecuritySME", jsonArray_FixedOrBankDeposirRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_FixedOrBankDeposirRet", e);

                    }
            
               //Security Details & Documents
               //for CS_GovermentSecuritiesRet
               
                JSONArray jsonArray_GovermentSecuritiesRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select OtherNatureOfCharge,SecurityCode,InstrumentNo,ValueGovSecurities,NatureOfCharge,SecurityType,"
                        + "SubSecurityType,SecurityDescription,Instrument,TypeOfSecurity,StatusBankLienCharge,OriginalInstrument,"
                        + "LetterOfPledge,ConfirmationFromNRB,MaturityDate,LatestDateExecution,CollateralType from CS_GovermentSecuritiesRet with(nolock) where  PID='" + PID + "'";
                
                 LogMessages.statusLog.info("Query: " + query);
                 List QueryList = iFormObj.getDataFromDB(query);
                 LogMessages.statusLog.info("QueryList: " + QueryList);
                
                 ListIterator QueryListItr = QueryList.listIterator();
                 while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("Other Nature of Charge", itemsArray[0]);
                   obj.put("Security Code", itemsArray[1]);
                   obj.put("Instrument Number", itemsArray[2]);
                   obj.put("Value of the Government Securities", itemsArray[3]);
                   obj.put("Nature Of Charge", itemsArray[4]);
                   obj.put("Security Type", itemsArray[5]);
                   obj.put("Sub Security Type", itemsArray[6]);
                   obj.put("Description", itemsArray[7]);
                   obj.put("Instrument", itemsArray[8]);
                   obj.put("Type of Security", itemsArray[9]);
                   obj.put("Status of Bank's Lien or Pledge Charge", itemsArray[10]);
                   obj.put("Original Instrument with Blank Endorsement", itemsArray[11]);
                   obj.put("Letter of Pledge / Lien Charge over the Security", itemsArray[12]);
                   obj.put("Confirmation from NRB with regard to the Bank's Charge", itemsArray[13]);
                   obj.put("Maturity Date", itemsArray[14]);
                   obj.put("Latest Date of Execution", itemsArray[15]);
                   obj.put("Collateral Type", itemsArray[16]);
                   
                   LogMessages.statusLog.info("*** CS_GovermentSecuritiesRet position zero :: ***" +itemsArray[0] );
                   jsonArray_GovermentSecuritiesRet.clear();
                   jsonArray_GovermentSecuritiesRet.add(obj);
                   iFormObj.addDataToGrid("tblGovSecuritySME", jsonArray_GovermentSecuritiesRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_GovermentSecuritiesRet", e);

                    }
            //Security Details & Documents
            //for CS_CorporateBondRet
            
            JSONArray jsonArray_CorporateBondRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select OtherNatureOfCharge,SecurityCode,InstrumentNo,ValueCorpBond,NatureOfCharge,"
                        + "SecurityType,SubSecurityType,SecurityDescription,IssuingCompanyName,StatusBankLienCharge,"
                        + "OriginalInstrument,ConfirmationFromIssuingCompany,MaturityDate,LatestDateExecution from CS_CorporateBondRet with(nolock) where  PID='" + PID + "'";
                
                 LogMessages.statusLog.info("Query: " + query);
                 List QueryList = iFormObj.getDataFromDB(query);
                 LogMessages.statusLog.info("QueryList: " + QueryList);
                
                 ListIterator QueryListItr = QueryList.listIterator();
                 while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("Other Nature of Charge", itemsArray[0]);
                   obj.put("Security Code", itemsArray[1]);
                   obj.put("Instrument Number", itemsArray[2]);
                   obj.put("Value of the Corporate Bond", itemsArray[3]);
                   obj.put("Nature Of Charge", itemsArray[4]);
                   obj.put("Security Type", itemsArray[5]);
                   obj.put("Sub SecurityType", itemsArray[6]);
                   obj.put("Description", itemsArray[7]);
                   obj.put("Name of the Issuing Company", itemsArray[8]);
                   obj.put("Status of Bank's Lien or Pledge Charge", itemsArray[9]);
                   obj.put("Original Instrument with Bank Endorsement", itemsArray[10]);
                   obj.put("Confirmation from issuing Company/CDS with regard to Bank's Charg", itemsArray[11]);
                   obj.put("MaturityDate", itemsArray[12]);
                   obj.put("LatestDateExecution", itemsArray[13]);
                   
                   LogMessages.statusLog.info("*** CS_CorporateBondRet position zero :: ***" +itemsArray[0] );
                   jsonArray_CorporateBondRet.clear();
                   jsonArray_CorporateBondRet.add(obj);
                   iFormObj.addDataToGrid("tblCorpBondsSecuritySME", jsonArray_CorporateBondRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_CorporateBondRet", e);

                    }
            //Security Details & Documents
            //for CS_ListedSecuritiesRet
            
           JSONArray jsonArray_ListedSecuritiesRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select OtherNatureOfCharge,SecurityCode,IssuingCompanyName,InstrumentNo,NatureOfCharge,SecurityType,"
                        + "SubSecurityType,SecurityDescription,StatusBankLienCharge,DmatAccountStmt,Manjurinama,Annexure,PledgeConfirmation,"
                        + "DebitInstructionSlip,LatestDateExecution,CollateralType from CS_ListedSecuritiesRet with(nolock) where  PID='" + PID + "'";
                
                
                LogMessages.statusLog.info("Query: " + query);
                 List QueryList = iFormObj.getDataFromDB(query);
                 LogMessages.statusLog.info("QueryList: " + QueryList);
                
                 ListIterator QueryListItr = QueryList.listIterator();
                 while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("Other Nature of Charge", itemsArray[0]);
                   obj.put("Security Code", itemsArray[1]);
                   obj.put("Name of Issuing Company", itemsArray[2]);
                   obj.put("Instrument Number", itemsArray[3]);
                   obj.put("Nature Of Charge", itemsArray[4]);
                   obj.put("Security Type", itemsArray[5]);
                   obj.put("Sub Security Type", itemsArray[6]);
                   obj.put("Description", itemsArray[7]);
                   obj.put("Status of Bank's Lien or Pledge Charge", itemsArray[8]);
                   obj.put("D-Mat Account Statement", itemsArray[9]);
                   obj.put("Manjurinama", itemsArray[10]);
                   obj.put("Annexure 18 and 19", itemsArray[11]);
                   obj.put("Pledge Confirmation", itemsArray[12]);
                   obj.put("Debit Instruction Slip", itemsArray[13]);
                   obj.put("Latest Date of Execution", itemsArray[14]);
                   obj.put("Collateral Type", itemsArray[15]);
                   
                   LogMessages.statusLog.info("*** CS_ListedSecuritiesRet position zero :: ***" +itemsArray[0] );
                   jsonArray_ListedSecuritiesRet.clear();
                   jsonArray_ListedSecuritiesRet.add(obj);
                   iFormObj.addDataToGrid("tblListedSecuritySME", jsonArray_ListedSecuritiesRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_ListedSecuritiesRet", e);

                    }
            
            //Security Details & Documents
            //for CS_GoldValuableSecuritiesRet
            
            JSONArray jsonArray_GoldValuableSecuritiesRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select OtherNatureOfCharge,SecurityCode,OtherType,Weight,NatureOfCharge,SecurityType,"
                        + "SubSecurityType,SecurityDescription,Type,StatusOfBankLienCharge,PledgeAgreement,LatestDateexecution,CollateralType from CS_GoldValuableSecuritiesRet with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                 ListIterator QueryListItr = QueryList.listIterator();
                 while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("Other Nature of Charge", itemsArray[0]);
                   obj.put("Security Code", itemsArray[1]);
                   obj.put("Other Type", itemsArray[2]);
                   obj.put("Weight", itemsArray[3]);
                   obj.put("Nature Of Charge", itemsArray[4]);
                   obj.put("Security Type", itemsArray[5]);
                   obj.put("Sub Security Type", itemsArray[6]);
                   obj.put("Description", itemsArray[7]);
                   obj.put("Type", itemsArray[8]);
                   obj.put("Status of Bank's Lien or Pledge Charge", itemsArray[9]);
                   obj.put("Pledge Agreement", itemsArray[10]);
                   obj.put("Latest Date of Execution", itemsArray[11]);
                   obj.put("Collateral Type", itemsArray[12]);
                   
                   LogMessages.statusLog.info("*** CS_GoldValuableSecuritiesRet position zero :: ***" +itemsArray[0] );
                   jsonArray_GoldValuableSecuritiesRet.clear();
                   jsonArray_GoldValuableSecuritiesRet.add(obj);
                   iFormObj.addDataToGrid("tblValuablesSecuritySME", jsonArray_GoldValuableSecuritiesRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_GoldValuableSecuritiesRet", e);

                    }
            //Security Details & Documents
            // for CS_GuaranteeRet
            
           JSONArray jsonArray_GuaranteeRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select otherGuarantorName,Age,Amount,Networth,CitizenshipNo,CitizenshipIssuedDistrict,PassportNo,"
                        + "PassportIssuedPlace,FathersName,MothersName,GrandFathersName,SpouseName,Required,Type,IsGuarantor,NameOfGuarantor,"
                        + "GuaranteeStatus,DOB,DateOfExecution,Expiry,CitizenshipIssuedDate,PassportIssuedDate,PassportExpiryDate from CS_GuaranteeRet with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                 ListIterator QueryListItr = QueryList.listIterator();
                 while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("Guarantor's Name", itemsArray[0]);
                   obj.put("Age", itemsArray[1]);
                   obj.put("Amount", itemsArray[2]);
                   obj.put("Networth of the Guarantor", itemsArray[3]);
                   obj.put("Citizenship Number", itemsArray[4]);
                   obj.put("Citizenship Issued District", itemsArray[5]);
                   obj.put("Passport Number", itemsArray[6]);
                   obj.put("Passport Issued Place", itemsArray[7]);
                   obj.put("Father's Name", itemsArray[8]);
                   obj.put("Mother's Name", itemsArray[9]);
                   obj.put("Grand Father's Name", itemsArray[10]);
                   obj.put("Spouse Name", itemsArray[11]);
                   obj.put("Required", itemsArray[12]);
                   obj.put("Type", itemsArray[13]);
                   obj.put("Is Guarantor a Shareholder Partner?", itemsArray[14]);
                   obj.put("Name of the Guarantor", itemsArray[15]);
                   obj.put("Status", itemsArray[16]);
                   obj.put("Date of Birth", itemsArray[17]);
                   obj.put("Date of Execution", itemsArray[18]);
                   obj.put("Expiry", itemsArray[19]);
                   obj.put("Citizenship Issued Date", itemsArray[20]);
                   obj.put("Passport Issued Date", itemsArray[21]);
                   obj.put("Passport Expiry Date", itemsArray[22]);
                   
                   LogMessages.statusLog.info("*** CS_GuaranteeRet position zero :: ***" +itemsArray[0] );
                   jsonArray_GuaranteeRet.clear();
                   jsonArray_GuaranteeRet.add(obj);
                   iFormObj.addDataToGrid("tblGuaranteeSME", jsonArray_GuaranteeRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_GuaranteeRet", e);

                    }
            //Security Details & Documents
            //for CS_ConnectionOwnership
            
            JSONArray jsonArray_ConnectionOwnership = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select unitName,ownership,ShareholderName1,HoldingPercentage1,ShareholderName2,HoldingPercentage2,"
                        + "ShareholderName3,HoldingPercentage3,HoldingPercentage4,ShareholderName4,ShareholderName5,HoldingPercentage5,"
                        + "ShareholderName6,HoldingPercentage6,ShareholderName7,HoldingPercentage7,ShareholderName8,HoldingPercentage8,"
                        + "ShareholderName9,HoldingPercentage9,ShareholderName10,HoldingPercentage10,HoldingPercentage11,ShareholderName11,"
                        + "ShareholderName12,HoldingPercentage12,ShareholderName13,HoldingPercentage13,ShareholderName14,HoldingPercentage14,"
                        + "ShareholderName15,HoldingPercentage15,ShareholderName16,HoldingPercentage16,ShareholderName17,HoldingPercentage17,"
                        + "ShareholderName18,HoldingPercentage18,ShareholderName19,HoldingPercentage19,ShareholderName20,HoldingPercentage20,"
                        + "Remarks,Incorporation,ShareholdersNum  from CS_ConnectionOwnership with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("Individual/Unit Name", itemsArray[0]);
                   obj.put("Owership", itemsArray[1]);
                   obj.put("First Shareholder Name", itemsArray[2]);
                   obj.put("First Shareholding percentage", itemsArray[3]);
                   obj.put("Second Shareholder Name", itemsArray[4]);
                   obj.put("Second Shareholding percentage", itemsArray[5]);
                   obj.put("Third Shareholder Name", itemsArray[6]);
                   obj.put("Third Shareholding percentage", itemsArray[7]);
                   obj.put("Forth Shareholding percentage", itemsArray[8]);
                   obj.put("Fourth Shareholder Name", itemsArray[9]);
                   obj.put("Fifth Shareholder Name", itemsArray[10]);
                   obj.put("Fifth Shareholding percentage", itemsArray[11]);
                   obj.put("Sixth Shareholder Name", itemsArray[12]);
                   obj.put("sixth Shareholding percentage", itemsArray[13]);
                   obj.put("Seventh Shareholder Name", itemsArray[14]);
                   obj.put("Seventh Shareholding percentage", itemsArray[15]);
                   obj.put("Eighth Shareholder Name", itemsArray[16]);
                   obj.put("Eighth Shareholding percentage", itemsArray[17]);
                   obj.put("Ninth Shareholder Name", itemsArray[18]);
                   obj.put("Ninth Shareholding percentage", itemsArray[19]);
                   obj.put("Tenth Shareholder Name", itemsArray[20]);
                   obj.put("Tenth Sharholding percentage", itemsArray[21]);
                   obj.put("Eleventh Shareholding percentage", itemsArray[22]);
                   obj.put("Eleventh Shareholder Name", itemsArray[23]);
                   obj.put("Twelfth Shareholder Name", itemsArray[24]);
                   obj.put("twelfth Shareholding percentage", itemsArray[25]);
                   obj.put("Thirteen Shareholder Name", itemsArray[26]);
                   obj.put("Thirteen Shareholding percentage", itemsArray[27]);
                   obj.put("Fourteen Shareholder Name", itemsArray[28]);
                   obj.put("Fourteen Shareholding percentage", itemsArray[29]);
                   obj.put("Fifteenth Shareholder Name", itemsArray[30]);
                   obj.put("Fifteenth Shareholding percentage", itemsArray[31]);
                   obj.put("Sixteenth Shareholder Name", itemsArray[32]);
                   obj.put("Sixteenth Shareholding percentage", itemsArray[33]);
                   obj.put("Seventeen Shareholder Name", itemsArray[34]);
                   obj.put("Seventeenth Shareholding percentage", itemsArray[35]);
                   obj.put("Eighteenth Shareholder Name", itemsArray[36]);
                   obj.put("Eighteenth Shareholding percentage", itemsArray[37]);
                   obj.put("Nineeten Shareholder Name", itemsArray[38]);
                   obj.put("Ninenth Shareholding percentage", itemsArray[39]);
                   obj.put("Twenth Shareholder Name", itemsArray[40]);
                   obj.put("Twenth Shareholding percentage", itemsArray[41]);
                   obj.put("Remarks", itemsArray[42]);
                   obj.put("Incorporation", itemsArray[43]);
                   obj.put("Number of Shareholders", itemsArray[44]);
                   
                   LogMessages.statusLog.info("*** CS_ConnectionOwnership position zero :: ***" +itemsArray[0] );
                   jsonArray_ConnectionOwnership.clear();
                   jsonArray_ConnectionOwnership.add(obj);
                   iFormObj.addDataToGrid("tblConnectionOwnershipSME", jsonArray_ConnectionOwnership);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_ConnectionOwnership", e);

                    }
            
            //Groupsheet
            //for CS_Exposure
            
             JSONArray jsonArray_Exposure = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select A,B,C,D,E,F,G,H,I,Total,unitName from CS_Exposure with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("A", itemsArray[0]); 
                   obj.put("B", itemsArray[1]);
                   obj.put("C", itemsArray[2]);
                   obj.put("D", itemsArray[3]);
                   obj.put("E", itemsArray[4]);
                   obj.put("F", itemsArray[5]);
                   obj.put("G", itemsArray[6]);
                   obj.put("H", itemsArray[7]);
                   obj.put("I", itemsArray[8]);
                   obj.put("Total", itemsArray[9]);
                   obj.put("Unit Name ", itemsArray[10]);
                   
                   LogMessages.statusLog.info("*** CS_Exposure position zero :: ***" +itemsArray[0] );
                   jsonArray_Exposure.clear();
                   jsonArray_Exposure.add(obj);
                   iFormObj.addDataToGrid("tblExposure", jsonArray_Exposure);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_Exposure", e);

                    }
            
            //Groupsheet
            //for CS_GroupFacilitiesSME
            
            JSONArray jsonArray_GroupFacilitiesSME = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select  OtherCreditFacility,limitShortTermLoan,outstandingShortTermLoan,pricingShortTermLoan,Remarks,"
                        + "unitNameFac,shortTermLoan  from CS_GroupFacilitiesSME with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();

                   obj.put("Other Credit Facility", itemsArray[0]);
                   obj.put("Short Term Loan limit", itemsArray[1]);
                   obj.put("Outstanding1", itemsArray[2]);
                   obj.put("Pricing1", itemsArray[3]);
                   obj.put("Remarks", itemsArray[4]);
                   obj.put("Unit Name", itemsArray[5]);
                   obj.put("Facility", itemsArray[6]);
                   
                   LogMessages.statusLog.info("*** CS_GroupFacilitiesSME position zero :: ***" +itemsArray[0] );
                   jsonArray_GroupFacilitiesSME.clear();
                   jsonArray_GroupFacilitiesSME.add(obj);
                   iFormObj.addDataToGrid("tblGroupFacilitiesSME", jsonArray_GroupFacilitiesSME);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_GroupFacilitiesSME", e);

                    }
                   
            /*CIC Information
            //for CS_GuarantorDetailsCICRet
            
             JSONArray jsonArray_GuarantorDetailsCICRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Name,CitizenshipNo,CitizenshipIssuedDistrict,PassportNo,PassportIssuedPlace,FathersName,MothersName,"
                        + "GrandFathersName,SpouseName,DOB,CitizenshipIssuedDate,PassportIssuedDate,PassportExpiryDate from CS_GuarantorDetailsCICRet with(nolock) where  PID='" + PID + "'";
                
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Name of Guarantor", itemsArray[0]);
                   obj.put("Citizenship Number", itemsArray[1]);
                   obj.put("Citizenship Issued District ", itemsArray[2]);
                   obj.put("Passport Number", itemsArray[3]);
                   obj.put("Passport Issued Place", itemsArray[4]);
                   obj.put("Father's Name", itemsArray[5]);
                   obj.put("Mother's Name", itemsArray[6]);
                   obj.put("Grand Father's Name", itemsArray[7]);
                   obj.put("Spouse Name", itemsArray[8]);
                   obj.put("Date of Birth", itemsArray[9]);
                   obj.put("Citizenship Issued Date", itemsArray[10]);
                   obj.put("Passport Issued Date", itemsArray[11]);
                   obj.put("Passport Expiry Date", itemsArray[12]);
                   
                    LogMessages.statusLog.info("*** CS_GuarantorDetailsCICRet position zero :: ***" +itemsArray[0] );
                   jsonArray_GuarantorDetailsCICRet.clear();
                   jsonArray_GuarantorDetailsCICRet.add(obj);
                   iFormObj.addDataToGrid("tblGuarantorDetailsCICSME", jsonArray_GuarantorDetailsCICRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_GuarantorDetailsCICRet", e);

                    }
            
            //CIC Information
            //for CS_CICInfoRet
            
             JSONArray jsonArray_CICInfoRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Name,CIRNum,CICCharge,ApplicableCharge,BankName,Facility,Limit,OutstandingAmt,OverdueAmt,CICReportType,"
                        + "CustomerBlacklisted,LoanFromOtherBanks,CICReportGenDate from CS_CICInfoRet with(nolock) where  PID='" + PID + "'";
            
            
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Name", itemsArray[0]);
                   obj.put("CIR Number", itemsArray[1]);
                   obj.put("CIC Charge", itemsArray[2]);
                   obj.put("Applicable CIC Charge", itemsArray[3]);
                   obj.put("Bank Name", itemsArray[4]);
                   obj.put("Facility", itemsArray[5]);
                   obj.put("Limit", itemsArray[6]);
                   obj.put("Outstanding Amount", itemsArray[7]);
                   obj.put("Overdue Amount (If overdue)", itemsArray[8]);
                   obj.put("CIC Report Type", itemsArray[9]);
                   obj.put("The customer is not blacklisted", itemsArray[10]);
                   obj.put("Customer is availing loan from other banks?", itemsArray[11]);
                   obj.put("CIC Report Generated Date", itemsArray[12]);
                   
                   LogMessages.statusLog.info("*** CS_CICInfoRet position zero :: ***" +itemsArray[0] );
                   jsonArray_CICInfoRet.clear();
                   jsonArray_CICInfoRet.add(obj);
                   iFormObj.addDataToGrid("tblCICInformationSME", jsonArray_CICInfoRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_CICInfoRet", e);

                    }
            //Variations
            //for CS_SecurityVariationsRet
            
             JSONArray jsonArray_SecurityVariationsRet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select TypeOfSecurity,Particulars,VariationsObserved from CS_SecurityVariationsRet with(nolock) where  PID='" + PID + "'";
                
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Type of Security", itemsArray[0]);
                   obj.put("Particulars", itemsArray[1]);
                   obj.put("Variations Observed", itemsArray[2]);
                   
                   LogMessages.statusLog.info("*** CS_SecurityVariationsRet position zero :: ***" +itemsArray[0] );
                   jsonArray_SecurityVariationsRet.clear();
                   jsonArray_SecurityVariationsRet.add(obj);
                   iFormObj.addDataToGrid("tblSecurityDetailsVariations", jsonArray_SecurityVariationsRet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_SecurityVariationsRet", e);

                    }
            //Variations
            //for CS_ControlComplianceVariations
            
             JSONArray jsonArray_ControlComplianceVariations = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Particulars,VariationsObserved from CS_ControlComplianceVariations with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Particulars", itemsArray[0]);
                   obj.put("Variations Observed", itemsArray[1]);
                   
                   LogMessages.statusLog.info("*** CS_ControlComplianceVariations position zero :: ***" +itemsArray[0] );
                   jsonArray_ControlComplianceVariations.clear();
                   jsonArray_ControlComplianceVariations.add(obj);
                   iFormObj.addDataToGrid("tblControlCompliancesVariations", jsonArray_ControlComplianceVariations);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_ControlComplianceVariations", e);

                    }
            //Approval Log
            //for CS_Comment
            
             JSONArray jsonArray_Comment = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select FromWorkstep,LoggedInUser,CurrentUser,Decision,QueriedTo,SkipNextAuthority,C_Date,"
                        + "Remarks from CS_Comment with(nolock) where  PID='" + PID + "'";
                
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("From Workstep", itemsArray[0]);
                   obj.put("Logged In User", itemsArray[1]);
                   obj.put("Current User", itemsArray[2]);
                   obj.put("Decision", itemsArray[3]);
                    obj.put("QueriedTo", itemsArray[4]);
                   obj.put("Skip Next Authority", itemsArray[5]);
                   obj.put("Date and Time", itemsArray[6]);
                   obj.put("Comment", itemsArray[7]);
                   
                   LogMessages.statusLog.info("*** CS_Comment position zero :: ***" +itemsArray[0] );
                   jsonArray_Comment.clear();
                   jsonArray_Comment.add(obj);
                   iFormObj.addDataToGrid("tblApprovalLogRetail", jsonArray_Comment);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_Comment", e);

                    }
            
                //Post Approval Log
                //for CS_PostApproval
                
                 JSONArray jsonArray_PostApproval = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select FromWorkStep,LoggedInUser,CurrentUser,Decision,NextWorkStep,C_Date,Remarks from CS_PostApproval with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("From Workstep", itemsArray[0]);
                   obj.put("Logged In User", itemsArray[1]);
                   obj.put("Current User", itemsArray[2]);
                   obj.put("Decision", itemsArray[3]);
                   obj.put("Next Work Desk", itemsArray[4]);
                   obj.put("Date and Time", itemsArray[5]);
                   obj.put("Comment", itemsArray[6]);
                   
                   LogMessages.statusLog.info("*** CS_PostApproval position zero :: ***" +itemsArray[0] );
                   jsonArray_PostApproval.clear();
                   jsonArray_PostApproval.add(obj);
                   iFormObj.addDataToGrid("tblPostApprovalLogSME", jsonArray_PostApproval);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_PostApproval", e);

                    }
            //Post Approval Summary
            // for CS_FacilitiesPostApproval
            
            JSONArray jsonArray_FacilitiesPostApproval = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select facilityType,class,loanAmount,NatureOfFacility,identificationOfLimit,loanAdminfee,"
                        + "CommitmentFee,detailsOfFac,drawingPower,CommisionNonFunded,BaseRate,Premium,TotalInterestRate,"
                        + "ModeOfdisbursement,MoratoriumPeriod,ModeOfRepayment,Tenure,ExposureLNM,TenureExpiryLNM,RefDescLNM,"
                        + "RefDescCopyLNM,typeofFacilityLNM,FrequencyLAO from CS_FacilitiesPostApproval with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Facility Type", itemsArray[0]);
                   obj.put("Class", itemsArray[1]);
                   obj.put("Loan Amount", itemsArray[2]);
                   obj.put("Nature Of Facility", itemsArray[3]);
                   obj.put("Identification Of Inner Limit", itemsArray[4]);
                   obj.put("Loan Administration Fee", itemsArray[5]);
                   obj.put("Commitment Fee", itemsArray[6]);
                   obj.put("Details of facility including details of complementary limit", itemsArray[7]);
                   obj.put("Drawing Power/Margin", itemsArray[8]);
                   obj.put("Commission incase of Non Funded Facility", itemsArray[9]);
                   obj.put("Base rate", itemsArray[10]);
                   obj.put("Premium", itemsArray[11]);
                   obj.put("Total Interest Rate", itemsArray[12]);
                   obj.put("Mode of disbursement", itemsArray[13]);
                   obj.put("Moratorium period (In case of term loan)", itemsArray[14]);
                   obj.put("Mode of repayment (incase of term loan)", itemsArray[15]);
                   obj.put("Tenure ( Incase of Term Loan)", itemsArray[16]);
                   obj.put("Exposure LNM", itemsArray[17]);
                   obj.put("Tenure (Expiry Date) LNM", itemsArray[18]);
                   obj.put("REF_DESC LNM", itemsArray[19]);
                   obj.put("REF_DESC_copy LNM", itemsArray[20]);
                   obj.put("Type of Facility LNM", itemsArray[21]);
                   obj.put("Frequency LAO", itemsArray[22]);
                   
                   LogMessages.statusLog.info("*** CS_FacilitiesPostApproval position zero :: ***" +itemsArray[0] );
                   jsonArray_FacilitiesPostApproval.clear();
                   jsonArray_FacilitiesPostApproval.add(obj);
                   iFormObj.addDataToGrid("tblFacilitiesSMEPostApproval", jsonArray_FacilitiesPostApproval);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_FacilitiesPostApproval", e);

                    }
            //Offering Sheet
            //for CS_Offerinsheet
            
             JSONArray jsonArray_Offerinsheet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select LimitNode,Description,ApprovedLimits,PresentOutstanding,AdditionalRequest,TotalIncAdditionalRequest,"
                        + "ExcessAcutalOS from CS_Offeringsheet with(nolock) where  PID='" + PID + "'";
                
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Limit Node", itemsArray[0]);
                   obj.put("Description", itemsArray[1]);
                   obj.put("Approved  Limits", itemsArray[2]);
                   obj.put("Present Outstanding", itemsArray[3]);
                   obj.put("Additional Request", itemsArray[4]);
                   obj.put("Total Incl  Additonal request", itemsArray[5]);
                   obj.put("Excess/Actual OS", itemsArray[6]);
                   
                   LogMessages.statusLog.info("*** CS_Offerinsheet position zero :: ***" +itemsArray[0] );
                   jsonArray_Offerinsheet.clear();
                   jsonArray_Offerinsheet.add(obj);
                   iFormObj.addDataToGrid("table137", jsonArray_Offerinsheet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_Offerinsheet", e);

                    }
            //Offering Sheet
            //CS_unitPastDues
            
             JSONArray jsonArray_unitPastDues = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select ACNo,DisbIdBillNo,OSLCY,OverdueSince,OverdueAmount from CS_unitPassDues with(nolock) where  PID='" + PID + "'";
                   
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("A/c No", itemsArray[0]);
                   obj.put("Disb Id/Bill No", itemsArray[1]);
                   obj.put("O/S LCY", itemsArray[2]);
                   obj.put("Overdue Since", itemsArray[3]);
                   obj.put("Overdue amount", itemsArray[4]);
                   
                   LogMessages.statusLog.info("*** CS_unitPastDues position zero :: ***" +itemsArray[0] );
                   jsonArray_unitPastDues.clear();
                   jsonArray_unitPastDues.add(obj);
                   iFormObj.addDataToGrid("table138", jsonArray_unitPastDues);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_unitPastDues", e);

                    }
            //Offering Sheet
            //CS_fundedOfferingSheet
            
             JSONArray jsonArray_fundedOfferingSheet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select LimitNode,GroupCIFID from CS_fundedOfferingSheet with(nolock) where  PID='" + PID + "'";
            
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Limit Node", itemsArray[0]);
                   obj.put("GroupCIFID", itemsArray[1]);
                   
                   LogMessages.statusLog.info("*** CS_fundedOfferingSheet position zero :: ***" +itemsArray[0] );
                   jsonArray_fundedOfferingSheet.clear();
                   jsonArray_fundedOfferingSheet.add(obj);
                   iFormObj.addDataToGrid("table139", jsonArray_fundedOfferingSheet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_fundedOfferingSheet", e);

                    }
            //Offering Sheet
            //CS_nonFundedOfferingSheet
            
             JSONArray jsonArray_nonFundedOfferingSheet = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select LimitNode,GroupCIFID from CS_nonFundedOfferingSheet with(nolock) where  PID='" + PID + "'";
                   
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Limt Node", itemsArray[0]);
                   obj.put("Group related CIF ID", itemsArray[1]);
                   
                   LogMessages.statusLog.info("*** CS_nonFundedOfferingSheet position zero :: ***" +itemsArray[0] );
                   jsonArray_nonFundedOfferingSheet.clear();
                   jsonArray_nonFundedOfferingSheet.add(obj);
                   iFormObj.addDataToGrid("table140", jsonArray_nonFundedOfferingSheet);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_nonFundedOfferingSheet", e);

                    }
            //Offering Sheet
            //for CS_TotalExposure
            
             JSONArray jsonArray_TotalExposure = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select TotalGroupLiimit,Funded,NonFunded,Total,Excess,ActualOD,LienAmount,"
                        + "ActualLimit from CS_TotalExposure with(nolock) where  PID='" + PID + "'";
                   
                   
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Total Grop Liimit", itemsArray[0]);
                   obj.put("Funded", itemsArray[1]);
                   obj.put("Non Funded", itemsArray[2]);
                   obj.put("Total", itemsArray[3]);
                   obj.put("Excess", itemsArray[4]);
                   obj.put("Actual OD", itemsArray[5]);
                   obj.put("Lien Amount", itemsArray[6]);
                   obj.put("Actual Limit", itemsArray[7]);
                   
                   LogMessages.statusLog.info("*** CS_TotalExposure position zero :: ***" +itemsArray[0] );
                   jsonArray_TotalExposure.clear();
                   jsonArray_TotalExposure.add(obj);
                   iFormObj.addDataToGrid("table141", jsonArray_TotalExposure);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_TotalExposure", e);

                    }
            //Offering Sheet
            //for CS_GropPastDues
            
             JSONArray jsonArray_GropPastDues = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select ACNo,DisbIdBillNo,OverdueSince,OverdueAmount from CS_GroupPastDues with(nolock) where  PID='" + PID + "'";
            
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("A/c No", itemsArray[0]);
                   obj.put("Disb Id/Bill No", itemsArray[1]);
                   obj.put("Overdue Since", itemsArray[2]);
                   obj.put("Overdue amount", itemsArray[3]);
                   
                   LogMessages.statusLog.info("*** CS_GropPastDues position zero :: ***" +itemsArray[0] );
                   jsonArray_GropPastDues.clear();
                   jsonArray_GropPastDues.add(obj);
                   iFormObj.addDataToGrid("table142", jsonArray_GropPastDues);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_GropPastDues", e);

                    }
            //Loan Information Table
            //for CS_LoanInfoTablepost
            
            JSONArray jsonArray_LoanInfoTablepost = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Approved,ApprovedLimit,Remarks from CS_loanInfoTablepost with(nolock) where  PID='" + PID + "'";
                   
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   
                   obj.put("Approved", itemsArray[0]); 
                   obj.put("Approved Limit", itemsArray[1]); 
                   obj.put("Remarks", itemsArray[2]); 
                   
                   LogMessages.statusLog.info("*** CS_LoanInfoTablepost position zero :: ***" +itemsArray[0] );
                   jsonArray_LoanInfoTablepost.clear();
                   jsonArray_LoanInfoTablepost.add(obj);
                   iFormObj.addDataToGrid("tblLoanInfoSME", jsonArray_LoanInfoTablepost);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_LoanInfoTablepost", e);

                    }
            //Multiple Banking Paripasu
            //CS_Paripassu
            
             JSONArray jsonArray_Paripassu = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select NameofBanks,WorkingcapitalLimit,TotalLimitn,"
                        + "ParipassuDate from CS_Paripassu with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Name of Bank/s", itemsArray[0]);
                   obj.put("Working capital Limit", itemsArray[1]);
                   obj.put("Total Limit", itemsArray[2]);
                   obj.put("Paripassu Date", itemsArray[3]);
                   
                   LogMessages.statusLog.info("*** CS_Paripassu position zero :: ***" +itemsArray[0] );
                   jsonArray_Paripassu.clear();
                   jsonArray_Paripassu.add(obj);
                   iFormObj.addDataToGrid("tblParispasu", jsonArray_Paripassu);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_Paripassu", e);

                    }
            //Multiple Banking Paripasu
            //CS_ConsortiumNabilLead
            
             JSONArray jsonArray_ConsortiumNabilLead = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select ConsortiumAggrementDate from CS_ConsortiumNabilLead with(nolock) where  PID='" + PID + "'";
            
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Consortium Aggrement Date", itemsArray[0]);
                   
                   LogMessages.statusLog.info("*** CS_ConsortiumNabilLead position zero :: ***" +itemsArray[0] );
                   jsonArray_ConsortiumNabilLead.clear();
                   jsonArray_ConsortiumNabilLead.add(obj);
                   iFormObj.addDataToGrid("tblConsortiumNabilLead", jsonArray_ConsortiumNabilLead);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_ConsortiumNabilLead", e);

                    }
            //Multiple Banking Paripasu
            //for CS_ConsortiumNabilCoLead
            
            JSONArray jsonArray_ConsortiumNabilCoLead = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select CoLeadBankName,MemberNameBank,WorkingcapitalLimit,TotalLimit,ConsortiumAggrementDate from CS_ConsortiumNabilCoLead with(nolock) where  PID='" + PID + "'";
                
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Co-Lead Bank Name", itemsArray[0]);
                   obj.put("Member Name of Bank/s", itemsArray[1]);
                   obj.put("Working capital Limit", itemsArray[2]);
                   obj.put("Total Limit", itemsArray[3]);
                   obj.put("Consortium Aggrement Date", itemsArray[4]);
                   
                   LogMessages.statusLog.info("*** CS_ConsortiumNabilCoLead position zero :: ***" +itemsArray[0] );
                   jsonArray_ConsortiumNabilCoLead.clear();
                   jsonArray_ConsortiumNabilCoLead.add(obj);
                   iFormObj.addDataToGrid("tblConsortiumNabilCoLead", jsonArray_ConsortiumNabilCoLead);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_ConsortiumNabilCoLead", e);

                    }
            //Multiple Banking Paripasu
            //for CS_ConsortiumNabilMember
            
             JSONArray jsonArray_ConsortiumNabilMember = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select ConsortiumAggrementDate,LeadBankName,CoLeadBankName,MemberNameBank,"
                        + "WorkingcapitalLimit,TotalLimit from CS_ConsortiumNabilMember with(nolock) where  PID='" + PID + "'";
                  
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Consortium Aggrement Date", itemsArray[0]);
                   obj.put("Lead Bank Name", itemsArray[1]);
                   obj.put("Co-Lead Bank Name", itemsArray[2]);
                   obj.put("Member Name of Bank/s", itemsArray[3]);
                   obj.put("Working capital Limit", itemsArray[4]);
                   obj.put("Total Limit", itemsArray[5]);
                   
                    LogMessages.statusLog.info("*** CS_ConsortiumNabilMember position zero :: ***" +itemsArray[0] );
                   jsonArray_ConsortiumNabilMember.clear();
                   jsonArray_ConsortiumNabilMember.add(obj);
                   iFormObj.addDataToGrid("tblConsortiumNabilMember", jsonArray_ConsortiumNabilMember);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_ConsortiumNabilMember", e);

                    }
            //Multiple Banking Paripasu
            //for CS_Noobjectionletter
            
            JSONArray jsonArray_Noobjectionletter = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select NoObjectionletterdate,NameofBanks,WorkingcapitalLimit,TotalLimit from CS_NoObjectionletter with(nolock) where  PID='" + PID + "'";
            
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("No objection letter date", itemsArray[0]);
                   obj.put("Name of Bank/s", itemsArray[1]);
                   obj.put("Working capital Limit", itemsArray[2]);
                   obj.put("Total Limit", itemsArray[3]);
                   
                   LogMessages.statusLog.info("*** CS_Noobjectionletter position zero :: ***" +itemsArray[0] );
                   jsonArray_Noobjectionletter.clear();
                   jsonArray_Noobjectionletter.add(obj);
                   iFormObj.addDataToGrid("tblNoObjectionLetter", jsonArray_Noobjectionletter);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_Noobjectionletter", e);

                    }
            //Limit Maintenance
            //for CS_LimitMaintanancePostAppro
            
            JSONArray jsonArray_LimitMaintanancePostAppro = new JSONArray();
            
            try 
            {
                int count = 0;
                String query = "select Suffix,CCYLM,CommittedLine,DrawingPowerIndicator,GlobalLimit,CollateralValueEroded,LoanType,"
                        + "MiniCollateralValueBase,ApprovalAuthority,ApprovalLevel,LimitStatus,LimitActivationCondition,LimitApprovedDate,"
                        + "LimitExpiryDate,ContractSignDate,LimitEffectiveDate,LimitReviewDate,AvailibilityEndDate,Prefix,Discription,"
                        + "LimitType,LimitTypeID,ParentLimitPrefix,ParentLimitSuffix,ApprovedLimit,BankSetID,DrawingPowerPcnt,DrawingPowerMarginRetained,"
                        + "LimitExpiryExtendUpto,NoOfExtension,BaseUserMaintanLiability,EffectiveUserMaintLiability,MinimumCollateral,"
                        + "InterestTableCode,LimitApprovalReference,PatternofFunding,BusinessUnitLimit,Notes,ReasonCode,MasterLimitNode,"
                        + "DebitACFees,ProductCode,Frequency,PremiumRate from CS_LimitMaintanancePostAppro with(nolock) where  PID='" + PID + "'";
            
                   
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                LogMessages.statusLog.info("QueryList: " + QueryList);
                
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                  {
                   List rowData = (List) QueryListItr.next();
                   LogMessages.statusLog.info("rowData: " + rowData);
                   String[] itemsArray = new String[rowData.size()];
                   itemsArray = (String[]) rowData.toArray(itemsArray);
                   LogMessages.statusLog.info("itemsArray: " + itemsArray);
                           
                   JSONObject obj = new JSONObject();
                   
                   obj.put("Suffix", itemsArray[0]);
                   obj.put("CCY", itemsArray[1]);
                   obj.put("Committed Line", itemsArray[2]);
                   obj.put("Drawing Power Indicator", itemsArray[3]);
                   obj.put("Global Limit", itemsArray[4]);
                   obj.put("Collateral Value Eroded", itemsArray[5]);
                   obj.put("Loan Type", itemsArray[6]);
                   obj.put("Minimum Collateral Value Base", itemsArray[7]);
                   obj.put("Approval Authority", itemsArray[8]);
                   obj.put("Approval Level", itemsArray[9]);
                   obj.put("Limit Status", itemsArray[10]);
                   obj.put("Limit activation condition precedent met", itemsArray[11]);
                   obj.put("Limit Approved Date", itemsArray[12]);
                   obj.put("Limit Expiry Date", itemsArray[13]);
                   obj.put("Contract Sign Date", itemsArray[14]);
                   obj.put("Limit Effective Date", itemsArray[15]);
                   obj.put("Limit Review Date", itemsArray[16]);
                   obj.put("Availibility End Date", itemsArray[17]);
                   obj.put("Prefix", itemsArray[18]);
                   obj.put("Description", itemsArray[19]);
                   obj.put("Limit Type", itemsArray[20]);
                   obj.put("Limit Type ID", itemsArray[21]);
                   obj.put("Parent Limit-Prefix", itemsArray[22]);
                   obj.put("Parent Limit-Suffux", itemsArray[23]);
                   obj.put("Approved Limit", itemsArray[24]);
                   obj.put("Bank Set ID", itemsArray[25]);
                   obj.put("Drawing Power Pcnt.", itemsArray[26]);
                   obj.put("Drawing Power / Margin Retained", itemsArray[27]);
                   obj.put("Limit Expiry Extend Upto", itemsArray[28]);
                   obj.put("No.of Extension", itemsArray[29]);
                   obj.put("Base User Maintanance Liability", itemsArray[30]);
                   obj.put("Effective User Maintanance Liability", itemsArray[31]);
                   obj.put("Minimum Collateral %", itemsArray[32]);
                   obj.put("Interest Table Code", itemsArray[33]);
                   obj.put("Limit Approval Reference", itemsArray[34]);
                   obj.put("Pattern of Funding", itemsArray[35]);
                   obj.put("Business Unit of Limit", itemsArray[36]);
                   obj.put("noNotes", itemsArray[37]);
                   obj.put("Reason Code", itemsArray[38]);
                   obj.put("Master Limit Node", itemsArray[39]);
                   obj.put("Debit a/c for Fees", itemsArray[40]);
                   obj.put("Product Code", itemsArray[41]);
                   obj.put("Frequency LAO", itemsArray[42]);
                   obj.put("Premium Rate LAO", itemsArray[43]);
                   
                   LogMessages.statusLog.info("*** CS_LimitMaintanancePostAppro position zero :: ***" +itemsArray[0] );
                   jsonArray_LimitMaintanancePostAppro.clear();
                   jsonArray_LimitMaintanancePostAppro.add(obj);
                   iFormObj.addDataToGrid("tblLimitMaintainance", jsonArray_LimitMaintanancePostAppro);
                   LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                    }
                } 
                   catch (Exception e) 
                    {
                      LogMessages.errorLog.info("Exception found CS_LimitMaintanancePostAppro", e);

                    }*/
         return sme_review_renew_data_array;
                   
    }               
                   
}           
                
            
            
                  
            
            
                   
               
               
                   
                   
                
                
                
                
                
                           
                
            
            
                           
                           
                           
                           
                
                
                
            
            
            
                           
                           
                           
            
            
            
                    
                    
            
            
                
                
                    
                    
                               
                       
                       
                
                
                 
                 




































