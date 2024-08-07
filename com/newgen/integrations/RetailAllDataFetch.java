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
public class RetailAllDataFetch 
{

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

    public static JSONArray retailAllDataFetch(IFormReference iFormObj) 
    {
        String ApplicationNo = iFormObj.getValue("BuildingDetails").toString();
        String PID = ApplicationNo;
        LogMessages.statusLog.info("Applicationno: " + ApplicationNo);
        // fetching all review renew data
        JSONArray retail_review_renew_data_array = new JSONArray();

        // fetching all retail textbox data
        try 
        {
         String textbox_query = "select ConsiderPropertyMortageable,CustomerID,Groups,CustomerName,AccountNo,Location1IndPer,"
                 + "StreetNameIndPer,HouseNoIndPer,Location2IndPer,Location1IndCur,StreetNameIndCur,HouseNoIndCur,Location2IndCur,"
                 + "BluePrintObtained,MaritalStatus,FathersName,MothersName,SpouseName,FatherInLawName,GrandFathersName,CitizenshipNo,"
                 + "ConsideredFMV,PassportNo,PassportIssuedPlace,DrivingLicenseNo,EmployeeIdNo,OtherIdentification,IndianEmbassyNumber,"
                 + "indianEmbassyIssuedFrom,PanNoInd,PanIssuedByInd,Phone1Ind,Phone2Ind,MobileNo1Ind,MobileNo2Ind,Email1Ind,Email2Ind,"
                 + "Location1InstReg,StreetNameInstReg,HouseNoInstReg,Location2InstReg,Location1Inst,StreetNameInst,HouseNoInst,Location2Inst,"
                 + "ActName,ActYear,AuthorizedBodyName,RegisteredWithInst,CompanyRegNo,DrainageConnection,PassportNoKeyPerson,DrivingLicenseNoKeyPerson,"
                 + "EmployeeIdNoKeyPerson,OtherIdentificationKeyPerson,PanNoInst,PanIssuedByInst,Phone1Inst,Phone2Inst,MobileNo1Inst,MobileNo2Inst,"
                 + "Email1Inst,Email2Inst,SectoralCode,flag101,TotalFundedExisting,EstimatedDV,EstimatedFMV,TotalNonFundExisting,TotalNonFundProposed,"
                 + "TotalNonFundOutstanding,GrandTotalExisting,GrandTotalProposed,TotalLimitOutstanding,CreditFacility,TotalLimit,OtherSecurityType,"
                 + "OtherNatureOfCharge,SecurityCodeGC,TotalEstFMV,TotalEstDV,UtilizedFMV1,UtilizedDV1,UtilizedFMV2,UtilizedDV2,UtilizedFMV3,UtilizedDV3,"
                 + "UtilizedFMV4,UtilizedDV4,UtilizedFMV5,UtilizedDV5,SecurityDocumentRemarks,TotalFundedFacGroup,TotalNonFundedFacGroup,TotalLimitFacilityGroup,"
                 + "TotalFundedProposed,TotalFundedOutstanding,RelationshipCIC,CreditFacCIC,InquiryTypeCIC,IncorporationCIC,NameCIC,GenderCIC,NationalityCIC,"
                 + "CitizenshipNoCIC,CitizenshipIssuedDistrictCIC,PassportNoCIC,PassportIssuedPlaceCIC,FatherNameCIC,MotherNameCIC,GrandFatherNameCIC,SpouseNameCIC,"
                 + "DocumentationOfAcc,CustomerIdCIC,CompanyNameCIC,CompanyRegNoCIC,CompanyRegOrg,PanNoCIC,PanIssuedByCIC,ApplicableCICCharge,CICReportType,"
                 + "BlacklistedCIC,CICBorrowedAcquired,DiscFactorBusiness,IncomeOf,SalaryIncome,ProfessionalIncome,BusinessIncome,RentIncome,InvestmentIncome,"
                 + "OtherIncome,TotalIncome,LivingExpenses,InterestExpenses,BusinessExpenses,OtherExpenses,TotalExpenses,UYI,YearlyRepaymentUYIPercentage,"
                 + "YearlyMaximumInterestUYI,NetSalesRevenue,NetIncome,TotalReceipts,TotalPayments,NetIncomePlusDepreciation,NetSurplusDepreciation,DSCR,OtherCreditFacCC,"
                 + "LegitimateUse,OtherPurposeAuto,MainPromoterDealer,PurposePodOdln,ShareIssuedByCandC,SegmentControlCompliance,DiversityOfProduct,"
                 + "InterestServicing,SegmentFacilities,AgeInYMD,MinAgeBorrower,SBU,MaxAgeBorrower,RiskDept1,MinAgeBusiness,RiskDept2,MaxAgeGuarantor,"
                 + "RiskDept3,NetIncomeBasis,NewPrevWS,GrossIncomeBasis,LastAuthorityGroup,DSCRControlCompliance,lastChainGroup,GroupName,MinLoanLimit,"
                 + "MaxLoanLimit,Facility,FlagA,CAS_decisionCombo,LTVRatio,LTVRatio1,BorrowerName,ApplicationType,PurchasePriceHL,ProductName,MinTenure,"
                 + "MaxTenure,RelationshipRiskAss,PurposeRiskAssessment,SubSegment,TargetAssignedUser,CustomizedMaxTenure,TargetAuthorityGroup,TenureNotExceedHL,"
                 + "CICReport,TenureAndReview,Approver1,CurrentRatio,InstallmentType,BrandStrength,Frequency,DistributionInfra,MoratoriumPeriodHL,GrowthProspects,"
                 + "IndustryBusiness,MarketShare,OverallMarket,UserDecision,InterestRateCandC,WellEstd,LoanAdminFeeCandC,Reviewer1,EarlyPaymentFeeTextDisplay,CommitmentFee,"
                 + "PanAndBankStmt,Reviewer2,AbilityToGenFund,AdditionalImmovalSecurity,DebtServicingModel,BranchCodeGC,SBUCodeGC,Decision,Flag1,Flag2,entryCounter,"
                 + "initialRowCount,NextWorkdesk,IsQuery,QueriedBy,QueriedTo,ApproverCounter,APPROVALDECISION,ToTriggerName,ToTriggerEmail,NextWorkStep,PostApprovalDecision,"
                 + "SuccessionPlan,MaritalStatusPostApproval,ActNamePostApproval,ActYearPostApproval,AuthorizedBodyNamePostApproval,IssuedDistrictPostApproval,"
                 + "SecuritytypePstApprvl,InsuranceAmtbuildingPstApprvl,NameofBankreqtakeoverPstApprvl,AccountNumberPstApprvl,AccountBenefNamePstApprvl,RelationOfficerNamePstApprvl,BranchManagerNameRMPstApprvl,ProvinancePstApprvl,"
                 + "MalpotOfficeNamePstApprvl,OS_customerName,OS_groupName from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'";
            LogMessages.statusLog.info("Retail ALL Data Fetch query: " + textbox_query);
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
                
                
                textbox_data.put("ConsiderPropertyMortageable", itemsArray[0]);
                textbox_data.put("CustomerID", itemsArray[1]);
                textbox_data.put("Groups", itemsArray[2]);
                textbox_data.put("CustomerName", itemsArray[3]);
                textbox_data.put("AccountNo", itemsArray[4]);
                textbox_data.put("Location1IndPer", itemsArray[5]);
                textbox_data.put("StreetNameIndPer", itemsArray[6]);
                textbox_data.put("HouseNoIndPer", itemsArray[7]);
                textbox_data.put("Location2IndPer", itemsArray[8]);
                textbox_data.put("Location1IndCur", itemsArray[9]);
                textbox_data.put("StreetNameIndCur", itemsArray[10]);
                textbox_data.put("HouseNoIndCur", itemsArray[11]);
                textbox_data.put("Location2IndCur", itemsArray[12]);
                textbox_data.put("BluePrintObtained", itemsArray[13]);
                textbox_data.put("MaritalStatus", itemsArray[14]);
                textbox_data.put("FathersName", itemsArray[15]);
                textbox_data.put("MothersName", itemsArray[16]);
                textbox_data.put("SpouseName", itemsArray[17]);
                textbox_data.put("FatherInLawName", itemsArray[18]);
                
                textbox_data.put("GrandFathersName", itemsArray[19]);
                textbox_data.put("CitizenshipNo", itemsArray[20]);
                textbox_data.put("ConsideredFMV", itemsArray[21]);
                textbox_data.put("PassportNo", itemsArray[22]);
                textbox_data.put("PassportIssuedPlace", itemsArray[23]);
                
                
                textbox_data.put("DrivingLicenseNo", itemsArray[24]);
                textbox_data.put("EmployeeIdNo", itemsArray[25]);
                textbox_data.put("OtherIdentification", itemsArray[26]);
                textbox_data.put("IndianEmbassyNumber", itemsArray[27]);
                textbox_data.put("indianEmbassyIssuedFrom", itemsArray[28]);
                textbox_data.put("PanNoInd", itemsArray[29]);
                textbox_data.put("PanIssuedByInd", itemsArray[30]);
                textbox_data.put("Phone1Ind", itemsArray[31]);
                textbox_data.put("Phone2Ind", itemsArray[32]);
                textbox_data.put("MobileNo1Ind", itemsArray[33]);
                textbox_data.put("MobileNo2Ind", itemsArray[34]);
                textbox_data.put("Email1Ind", itemsArray[35]);
                textbox_data.put("Email2Ind", itemsArray[36]);
                textbox_data.put("Location1InstReg", itemsArray[37]);
                textbox_data.put("StreetNameInstReg", itemsArray[38]);
                textbox_data.put("HouseNoInstReg", itemsArray[39]);
                textbox_data.put("Location2InstReg", itemsArray[40]);
                
                
                textbox_data.put("Location1Inst", itemsArray[41]);
                textbox_data.put("StreetNameInst", itemsArray[42]);
                textbox_data.put("HouseNoInst", itemsArray[43]);
                
                
                textbox_data.put("Location2Inst", itemsArray[44]);
                textbox_data.put("ActName", itemsArray[45]);
                textbox_data.put("ActYear", itemsArray[46]);
                textbox_data.put("AuthorizedBodyName", itemsArray[47]);
                textbox_data.put("RegisteredWithInst", itemsArray[48]);
                
                
                textbox_data.put("CompanyRegNo", itemsArray[49]);
                textbox_data.put("DrainageConnection", itemsArray[50]);
                textbox_data.put("PassportNoKeyPerson", itemsArray[51]);
                textbox_data.put("DrivingLicenseNoKeyPerson", itemsArray[52]);
                textbox_data.put("EmployeeIdNoKeyPerson", itemsArray[53]);
                textbox_data.put("OtherIdentificationKeyPerson", itemsArray[54]);
                textbox_data.put("PanNoInst", itemsArray[55]);
                textbox_data.put("PanIssuedByInst", itemsArray[56]);
                textbox_data.put("Phone1Inst", itemsArray[57]);
                textbox_data.put("Phone2Inst", itemsArray[58]);
                textbox_data.put("MobileNo1Inst", itemsArray[59]);
                textbox_data.put("MobileNo2Inst", itemsArray[60]);
                textbox_data.put("Email1Inst", itemsArray[61]);
                textbox_data.put("Email2Inst", itemsArray[62]);
                textbox_data.put("SectoralCode", itemsArray[63]);
                textbox_data.put("flag101", itemsArray[64]);
                textbox_data.put("TotalFundedExisting", itemsArray[65]);
                textbox_data.put("EstimatedDV", itemsArray[66]);
                textbox_data.put("EstimatedFMV", itemsArray[67]);
                textbox_data.put("TotalNonFundExisting", itemsArray[68]);
                textbox_data.put("TotalNonFundProposed", itemsArray[69]);
                textbox_data.put("TotalNonFundOutstanding", itemsArray[70]);
                
                
                textbox_data.put("GrandTotalExisting", itemsArray[71]);
                textbox_data.put("GrandTotalProposed", itemsArray[72]);
                textbox_data.put("TotalLimitOutstanding", itemsArray[73]);
                textbox_data.put("CreditFacility", itemsArray[74]);
                textbox_data.put("TotalLimit", itemsArray[75]);
                textbox_data.put("OtherSecurityType", itemsArray[76]);
                
                textbox_data.put("OtherNatureOfCharge", itemsArray[77]);
                textbox_data.put("SecurityCodeGC", itemsArray[78]);
                textbox_data.put("TotalEstFMV", itemsArray[79]);
                textbox_data.put("TotalEstDV", itemsArray[80]);
                textbox_data.put("UtilizedFMV1", itemsArray[81]);
                textbox_data.put("UtilizedDV1", itemsArray[82]);
                textbox_data.put("UtilizedFMV2", itemsArray[83]);
                textbox_data.put("UtilizedDV2", itemsArray[84]);
                textbox_data.put("UtilizedFMV3", itemsArray[85]);
                textbox_data.put("UtilizedDV3", itemsArray[86]);
                textbox_data.put("UtilizedFMV4", itemsArray[87]);
                textbox_data.put("UtilizedDV4", itemsArray[88]);
                textbox_data.put("UtilizedFMV5", itemsArray[89]);
                textbox_data.put("UtilizedDV5", itemsArray[90]);
                textbox_data.put("SecurityDocumentRemarks", itemsArray[91]);
                textbox_data.put("TotalFundedFacGroup", itemsArray[92]);
                textbox_data.put("TotalNonFundedFacGroup", itemsArray[93]);
                textbox_data.put("TotalLimitFacilityGroup", itemsArray[94]);
                textbox_data.put("TotalFundedProposed", itemsArray[95]);
                textbox_data.put("TotalFundedOutstanding", itemsArray[96]);
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
                textbox_data.put("SpouseNameCIC", itemsArray[111]);
                textbox_data.put("DocumentationOfAcc", itemsArray[112]);
                textbox_data.put("CustomerIdCIC", itemsArray[113]);
                textbox_data.put("CompanyNameCIC", itemsArray[114]);
                textbox_data.put("CompanyRegNoCIC", itemsArray[115]);
                textbox_data.put("CompanyRegOrg", itemsArray[116]);
                textbox_data.put("PanNoCIC", itemsArray[117]);
                textbox_data.put("PanIssuedByCIC", itemsArray[118]);
                textbox_data.put("ApplicableCICCharge", itemsArray[119]);
                textbox_data.put("CICReportType", itemsArray[120]);
                textbox_data.put("BlacklistedCIC", itemsArray[121]);
                textbox_data.put("CICBorrowedAcquired", itemsArray[122]);
                textbox_data.put("DiscFactorBusiness", itemsArray[123]);
                textbox_data.put("IncomeOf", itemsArray[124]);
                textbox_data.put("SalaryIncome", itemsArray[125]);
                textbox_data.put("ProfessionalIncome", itemsArray[126]);
                
                textbox_data.put("BusinessIncome", itemsArray[127]);
                textbox_data.put("RentIncome", itemsArray[128]);
                textbox_data.put("InvestmentIncome", itemsArray[129]);
                textbox_data.put("OtherIncome", itemsArray[130]);
                textbox_data.put("TotalIncome", itemsArray[131]);
                textbox_data.put("LivingExpenses", itemsArray[132]);
                textbox_data.put("InterestExpenses", itemsArray[133]);
                textbox_data.put("BusinessExpenses", itemsArray[134]);
                textbox_data.put("OtherExpenses", itemsArray[135]);
                textbox_data.put("TotalExpenses", itemsArray[136]);
                textbox_data.put("UYI", itemsArray[137]);
                textbox_data.put("YearlyRepaymentUYIPercentage", itemsArray[138]);
                textbox_data.put("YearlyMaximumInterestUYI", itemsArray[139]);
                
                
                
                textbox_data.put("NetSalesRevenue", itemsArray[140]);
                textbox_data.put("NetIncome", itemsArray[141]);
                textbox_data.put("TotalReceipts", itemsArray[142]);
                textbox_data.put("TotalPayments", itemsArray[143]);
                
                
                textbox_data.put("NetIncomePlusDepreciation", itemsArray[144]);
                textbox_data.put("NetSurplusDepreciation", itemsArray[145]);
                textbox_data.put("DSCR", itemsArray[146]);
                textbox_data.put("OtherCreditFacCC", itemsArray[147]);
                
                
                textbox_data.put("LegitimateUse", itemsArray[148]);
                textbox_data.put("OtherPurposeAuto", itemsArray[149]);
                textbox_data.put("MainPromoterDealer", itemsArray[150]);
                textbox_data.put("PurposePodOdln", itemsArray[151]);
                textbox_data.put("ShareIssuedByCandC", itemsArray[152]);
                textbox_data.put("SegmentControlCompliance", itemsArray[153]);
                textbox_data.put("DiversityOfProduct", itemsArray[154]);
                textbox_data.put("InterestServicing", itemsArray[155]);
                textbox_data.put("SegmentFacilities", itemsArray[156]);
                textbox_data.put("AgeInYMD", itemsArray[157]);
                textbox_data.put("MinAgeBorrower", itemsArray[158]);
                textbox_data.put("SBU", itemsArray[159]);
                textbox_data.put("MaxAgeBorrower", itemsArray[160]);
                textbox_data.put("RiskDept1", itemsArray[161]);
                textbox_data.put("MinAgeBusiness", itemsArray[162]);
                textbox_data.put("RiskDept2", itemsArray[163]);
                textbox_data.put("MaxAgeGuarantor", itemsArray[164]);
                textbox_data.put("RiskDept3", itemsArray[165]);
                textbox_data.put("NetIncomeBasis", itemsArray[166]);
                textbox_data.put("NewPrevWS", itemsArray[167]);
                textbox_data.put("GrossIncomeBasis", itemsArray[168]);
                textbox_data.put("LastAuthorityGroup", itemsArray[169]);
                textbox_data.put("DSCRControlCompliance", itemsArray[170]);
                textbox_data.put("lastChainGroup", itemsArray[171]);
                textbox_data.put("GroupName", itemsArray[172]);
                textbox_data.put("MinLoanLimit", itemsArray[173]);
                textbox_data.put("MaxLoanLimit", itemsArray[174]);
                textbox_data.put("Facility", itemsArray[175]);
                textbox_data.put("FlagA", itemsArray[176]);
                textbox_data.put("CAS_decisionCombo", itemsArray[177]);
                textbox_data.put("LTVRatio", itemsArray[178]);
                textbox_data.put("LTVRatio1", itemsArray[179]);
                textbox_data.put("BorrowerName", itemsArray[180]);
                textbox_data.put("ApplicationType", itemsArray[181]);
                textbox_data.put("PurchasePriceHL", itemsArray[182]);
		textbox_data.put("ProductName",itemsArray[183]);
                textbox_data.put("MinTenure",itemsArray[184]);
                
                
                textbox_data.put("MaxTenure", itemsArray[185]);
                textbox_data.put("RelationshipRiskAss", itemsArray[186]);
                textbox_data.put("PurposeRiskAssessment", itemsArray[187]);
                textbox_data.put("SubSegment", itemsArray[188]);
                textbox_data.put("TargetAssignedUser", itemsArray[189]);
                textbox_data.put("CustomizedMaxTenure", itemsArray[190]);
                textbox_data.put("TargetAuthorityGroup", itemsArray[191]);
                textbox_data.put("TenureNotExceedHL", itemsArray[192]);
                textbox_data.put("CICReport", itemsArray[193]);
                
                
                textbox_data.put("TenureAndReview", itemsArray[194]);
                textbox_data.put("Approver1", itemsArray[195]);
                textbox_data.put("CurrentRatio", itemsArray[196]);
                textbox_data.put("InstallmentType", itemsArray[197]);
                textbox_data.put("BrandStrength", itemsArray[198]);
                textbox_data.put("Frequency", itemsArray[199]);
                textbox_data.put("DistributionInfra", itemsArray[200]);
                textbox_data.put("MoratoriumPeriodHL", itemsArray[201]);
                textbox_data.put("GrowthProspects", itemsArray[202]);
                textbox_data.put("IndustryBusiness", itemsArray[203]);
                textbox_data.put("MarketShare", itemsArray[204]);
                textbox_data.put("OverallMarket", itemsArray[205]);
                textbox_data.put("UserDecision", itemsArray[206]);
                textbox_data.put("InterestRateCandC", itemsArray[207]);
                textbox_data.put("WellEstd", itemsArray[208]);
                textbox_data.put("LoanAdminFeeCandC", itemsArray[209]);
                textbox_data.put("Reviewer1", itemsArray[210]);
                textbox_data.put("EarlyPaymentFeeTextDisplay", itemsArray[211]);
                textbox_data.put("CommitmentFee", itemsArray[212]);
                textbox_data.put("PanAndBankStmt", itemsArray[213]);
                textbox_data.put("Reviewer2", itemsArray[214]);
                textbox_data.put("AbilityToGenFund", itemsArray[215]);
                textbox_data.put("AdditionalImmovalSecurity", itemsArray[216]);
                textbox_data.put("DebtServicingModel", itemsArray[217]);
                
                
                
                textbox_data.put("BranchCodeGC", itemsArray[218]);
                textbox_data.put("SBUCodeGC", itemsArray[219]);
                
                
                textbox_data.put("Decision", itemsArray[220]);
                textbox_data.put("Flag1", itemsArray[221]);
                textbox_data.put("Flag2", itemsArray[222]);
                
                textbox_data.put("entryCounter", itemsArray[223]);
                textbox_data.put("initialRowCount", itemsArray[224]);
                textbox_data.put("NextWorkdesk", itemsArray[225]);
                textbox_data.put("IsQuery", itemsArray[226]);
                textbox_data.put("QueriedBy", itemsArray[227]);
                textbox_data.put("QueriedTo", itemsArray[228]);
                textbox_data.put("ApproverCounter", itemsArray[229]);
                textbox_data.put("APPROVALDECISION", itemsArray[230]);
                textbox_data.put("ToTriggerName", itemsArray[231]);
                textbox_data.put("ToTriggerEmail", itemsArray[232]);
                textbox_data.put("NextWorkStep", itemsArray[233]);
                textbox_data.put("PostApprovalDecision", itemsArray[234]);
                textbox_data.put("SuccessionPlan", itemsArray[235]);
                textbox_data.put("MaritalStatusPostApproval", itemsArray[236]);
                textbox_data.put("ActNamePostApproval", itemsArray[237]);
                textbox_data.put("ActYearPostApproval", itemsArray[238]);
                textbox_data.put("AuthorizedBodyNamePostApproval", itemsArray[239]);
                textbox_data.put("IssuedDistrictPostApproval", itemsArray[240]);
                
                
                textbox_data.put("SecuritytypePstApprvl", itemsArray[241]);
                textbox_data.put("InsuranceAmtbuildingPstApprvl", itemsArray[242]);
                textbox_data.put("NameofBankreqtakeoverPstApprvl", itemsArray[243]);
                textbox_data.put("AccountNumberPstApprvl", itemsArray[244]);
                textbox_data.put("AccountBenefNamePstApprvl", itemsArray[245]);
                textbox_data.put("RelationOfficerNamePstApprvl", itemsArray[246]);
                textbox_data.put("BranchManagerNameRMPstApprvl", itemsArray[247]);
                textbox_data.put("ProvinancePstApprvl", itemsArray[248]);
                textbox_data.put("MalpotOfficeNamePstApprvl", itemsArray[249]);
                textbox_data.put("OS_customerName", itemsArray[250]);
                textbox_data.put("OS_groupName", itemsArray[251]);
                //textbox_data.put("Flag10", itemsArray[252]);
                
               
                
                
                
                
                
               
               

                retail_review_renew_data_array.clear();
                retail_review_renew_data_array.add(textbox_data);
                LogMessages.statusLog.info("*** All Data Of text box fetch successfully ***");
            }
        }
               
       catch (Exception e)
	{
	  e.printStackTrace();	
	   LogMessages.statusLog.info("*** Error Occured During fetching all text box field of retail  ***");
	}
                
               
                
               
                
                
                
               
       //fetching all comboobx data of retail
       try 
       {
         String combobox_query = "select Entity,Nationality,Relationship,ProvinceIndPer,DistrictIndPer,MunicipalityVDCIndPer,ProvinceIndCur,"
                 + "DistrictIndCur,MunicipalityVDCIndCur,Gender,CitizenshipIssuedBy,Profession,IncorporationProfitInst,IncorporationNotProfitInst,"
                 + "NatureOfBusiness,NatureOfActivity,ProvinceInstReg,DistrictInstReg,MunicipalityVDCInstReg,ProvinceInst,DistrictInst,MunicipalityVDCInst,"
                 + "IsCustomerBlacklisted,LoanFromOtherBanks,SectorGC,SubsectorGC,SubSubSector,Flag100,NatureOfCharge,SecurityType,SubSecurityType,"
                 + "SecurityList,IsCollateralShared,NofCollateralShared,Facilities1,Facilities2,Facilities3,Facilities4,Facilities5,SubordinationOfDebt,"
                 + "toBeUpdated,GeneralCompliance1,LandVerticallySloped,LandWithinDasgaja,LandWithinRiverForest,IncomeConsideredOf,ProfessionIncome,"
                 + "Audited,WaterSupply,CreditFacilityCC,PurposeAutoLoan,PurposeHouseLoan,PurposeMortgageLoan,PurposeEducationLoan,PurposePL,TypeOfShareCandC,"
                 + "PurposeShare,CountryofEducation,AutomobileType,AutoRegistrationType,TypePOD,TypeODLN,CurrencyODLN,InstrumentODLN,ShareFacilityType,"
                 + "DepositTypeODLN,SecurityTypeODLN,LandfreeHoldHL,TOLNetWorth,IsExposureClassified,HouseNotExceedingHL,LocationofPropertyML,PropertyEL,"
                 + "ExclusionOfRentalIncomeHL,DocumentaryEvidenceML,ConfirmationOfNoHL,Drawdown,DrawdownML,DrawdownEL,OwnsPromoterShare,PledgeLessThan,"
                 + "NoObjectionLetter,VehicleAge5Years,VehicleAge7Years,PrivateCommercial7Yrs,InterestPaidMonthly,InterestPaidQuarterly,RepaymentDueDate,"
                 + "InterestDuringMoratium,RepaymentStandingOrder,ComprehensiveInsuranceCoverage,BuildingBeingFinancedHL,EarlyPaymentFee,NoOfLoanFamily,"
                 + "additionalSecurity,InterestToCouponRate,ElectricityConnection,EnquiredWithFamily,EmploymentTermGC,OtherSubIncomeGC,"
                 + "FinancialsSubmittedGC,InfoFinancialGC,ViabilityBusinessGC,IndustryStableGC,NabilCardHolderGC,IncomeNetworthGC,GuarantorRepaymentGC,"
                 + "ApplicantBgGC,NonCoreIncomeGC,CICReportGC,CautionListGC,BankStmtAnalysedGC,SecurityGC,FacOtherBanksGC,FacNabilClassifyGC,"
                 + "NRBChecklistGC,Reviewer3,BoundaryWall,DecisionCAS from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'";
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
            combobox_data.put("Nationality", itemsArray[1]);
            combobox_data.put("Relationship", itemsArray[2]);
                    combobox_data.put("ProvinceIndPer", itemsArray[3]);
                    combobox_data.put("DistrictIndPer", itemsArray[4]);
                    combobox_data.put("MunicipalityVDCIndPer", itemsArray[5]);
                    combobox_data.put("ProvinceIndCur", itemsArray[6]);
                    combobox_data.put("DistrictIndCur", itemsArray[7]);
                    combobox_data.put("MunicipalityVDCIndCur", itemsArray[8]);
                    combobox_data.put("Gender", itemsArray[9]);
                    combobox_data.put("CitizenshipIssuedBy", itemsArray[10]);
                    combobox_data.put("Profession", itemsArray[11]);
                    combobox_data.put("IncorporationProfitInst", itemsArray[12]);
                    combobox_data.put("IncorporationNotProfitInst", itemsArray[13]);
                    combobox_data.put("NatureOfBusiness", itemsArray[14]);
                    combobox_data.put("NatureOfActivity", itemsArray[15]);
                    combobox_data.put("ProvinceInstReg", itemsArray[16]);
                    combobox_data.put("DistrictInstReg", itemsArray[17]);
                    combobox_data.put("MunicipalityVDCInstReg", itemsArray[18]);
                    combobox_data.put("ProvinceInst", itemsArray[19]);
                    combobox_data.put("DistrictInst", itemsArray[20]);
                    combobox_data.put("MunicipalityVDCInst", itemsArray[21]);
                    combobox_data.put("IsCustomerBlacklisted", itemsArray[22]);
                    combobox_data.put("LoanFromOtherBanks", itemsArray[23]);
                    combobox_data.put("SectorGC", itemsArray[24]);
                    combobox_data.put("SubsectorGC", itemsArray[25]);
                    combobox_data.put("SubSubSector", itemsArray[26]);
                    combobox_data.put("Flag100", itemsArray[27]);
                    combobox_data.put("NatureOfCharge", itemsArray[28]);
                    combobox_data.put("SecurityType", itemsArray[29]);
                    combobox_data.put("SubSecurityType", itemsArray[30]);
                    combobox_data.put("SecurityList", itemsArray[31]);
                    combobox_data.put("IsCollateralShared", itemsArray[32]);
                    combobox_data.put("NofCollateralShared", itemsArray[33]);
                    combobox_data.put("Facilities1", itemsArray[34]);
                    combobox_data.put("Facilities2", itemsArray[35]);
                    combobox_data.put("Facilities3", itemsArray[36]);
                    combobox_data.put("Facilities4", itemsArray[37]);
                    combobox_data.put("Facilities5", itemsArray[38]);
                    combobox_data.put("SubordinationOfDebt", itemsArray[39]);
                    combobox_data.put("toBeUpdated", itemsArray[40]);
                    combobox_data.put("GeneralCompliance1", itemsArray[41]);
                    combobox_data.put("LandVerticallySloped", itemsArray[42]);
                    combobox_data.put("LandWithinDasgaja", itemsArray[43]);
                    combobox_data.put("LandWithinRiverForest", itemsArray[44]);
                    
                    combobox_data.put("IncomeConsideredOf", itemsArray[45]);
                    combobox_data.put("ProfessionIncome", itemsArray[46]);
                    combobox_data.put("Audited", itemsArray[47]);
                    combobox_data.put("WaterSupply", itemsArray[48]);
                    combobox_data.put("CreditFacilityCC", itemsArray[49]);
                    combobox_data.put("PurposeAutoLoan", itemsArray[50]);
                    combobox_data.put("PurposeHouseLoan", itemsArray[51]);
                    combobox_data.put("PurposeMortgageLoan", itemsArray[52]);
                    combobox_data.put("PurposeEducationLoan", itemsArray[53]);
                    combobox_data.put("PurposePL", itemsArray[54]);
                    combobox_data.put("TypeOfShareCandC", itemsArray[55]);
                    combobox_data.put("PurposeShare", itemsArray[56]);
                    combobox_data.put("CountryOfEducation", itemsArray[57]);
                    combobox_data.put("AutomobileType", itemsArray[58]);
                    combobox_data.put("AutoRegistrationType", itemsArray[59]);
                    combobox_data.put("TypePOD", itemsArray[60]);
                    combobox_data.put("TypeODLN", itemsArray[61]);
                    combobox_data.put("CurrencyODLN", itemsArray[62]);
                    combobox_data.put("InstrumentODLN", itemsArray[63]);
                    combobox_data.put("ShareFacilityType", itemsArray[64]);
                    combobox_data.put("DepositTypeODLN", itemsArray[65]);
                    
                    combobox_data.put("SecurityTypeODLN", itemsArray[66]);
                    combobox_data.put("LandfreeHoldHL", itemsArray[67]);
                    combobox_data.put("TOLNetWorth", itemsArray[68]);
                    combobox_data.put("IsExposureClassified", itemsArray[69]);
                    combobox_data.put("HouseNotExceedingHL", itemsArray[70]);
                    combobox_data.put("LocationofPropertyML", itemsArray[71]);
                    combobox_data.put("PropertyEL", itemsArray[72]);
                    combobox_data.put("ExclusionOfRentalIncomeHL", itemsArray[73]);
                    combobox_data.put("DocumentaryEvidenceML", itemsArray[74]);
                    combobox_data.put("ConfirmationOfNoHL", itemsArray[75]);
                    combobox_data.put("Drawdown", itemsArray[76]);
                    combobox_data.put("DrawdownML", itemsArray[77]);
                    combobox_data.put("DrawdownEL", itemsArray[78]);
                    combobox_data.put("OwnsPromoterShare", itemsArray[79]);
                    combobox_data.put("PledgeLessThan", itemsArray[80]);
                    combobox_data.put("NoObjectionLetter", itemsArray[81]);
                    combobox_data.put("VehicleAge5Years", itemsArray[82]);
                    combobox_data.put("VehicleAge7Years", itemsArray[83]);
                    combobox_data.put("PrivateCommercial7Yrs", itemsArray[84]);
                    combobox_data.put("InterestPaidMonthly", itemsArray[85]);
                    combobox_data.put("InterestPaidQuarterly", itemsArray[86]);
                    combobox_data.put("RepaymentDueDate", itemsArray[87]);
                    combobox_data.put("InterestDuringMoratium", itemsArray[88]);
                    combobox_data.put("RepaymentStandingOrder", itemsArray[89]);
                    combobox_data.put("ComprehensiveInsuranceCoverage", itemsArray[90]);
                    combobox_data.put("BuildingBeingFinancedHL", itemsArray[91]);
                    combobox_data.put("EarlyPaymentFee", itemsArray[92]);
                    combobox_data.put("NoOfLoanFamily", itemsArray[93]);
                    combobox_data.put("additionalSecurity", itemsArray[94]);
                    combobox_data.put("InterestToCouponRate", itemsArray[95]);
                    combobox_data.put("ElectricityConnection", itemsArray[96]);
                    combobox_data.put("EnquiredWithFamily", itemsArray[97]);
                    combobox_data.put("EmploymentTermGC", itemsArray[98]);
                    combobox_data.put("OtherSubIncomeGC", itemsArray[99]);
                    combobox_data.put("FinancialsSubmittedGC", itemsArray[100]);
                    combobox_data.put("InfoFinancialGC", itemsArray[101]);
                    combobox_data.put("ViabilityBusinessGC", itemsArray[102]);
                    combobox_data.put("IndustryStableGC", itemsArray[103]);
                    combobox_data.put("NabilCardHolderGC", itemsArray[104]);
                    combobox_data.put("IncomeNetworthGC", itemsArray[105]);
                    combobox_data.put("GuarantorRepaymentGC", itemsArray[106]);
                    combobox_data.put("ApplicantBgGC", itemsArray[107]);
                    combobox_data.put("NonCoreIncomeGC", itemsArray[108]);
                    combobox_data.put("CICReportGC", itemsArray[109]);
                    combobox_data.put("CautionListGC", itemsArray[110]);
                    combobox_data.put("BankStmtAnalysedGC", itemsArray[111]);
                    combobox_data.put("SecurityGC", itemsArray[112]);
                    combobox_data.put("FacOtherBanksGC", itemsArray[113]);
                    combobox_data.put("FacNabilClassifyGC", itemsArray[114]);
                    combobox_data.put("NRBChecklistGC", itemsArray[115]);
                    combobox_data.put("Reviewer3", itemsArray[116]);
                    combobox_data.put("BoundaryWall", itemsArray[117]);
                    combobox_data.put("DecisionCAS", itemsArray[118]);
                    //combobox_data.put("CompanyRegOrgCIC", itemsArray[119]);
                    
                    

                    retail_review_renew_data_array.add(combobox_data);
                    LogMessages.statusLog.info("*** All Data Of combo box fetch successfully ***");
                }
            } 
       catch (Exception e) 
            {
                e.printStackTrace();
                LogMessages.statusLog.info("*** Error Occured During fetching all combo box field of retail ***");
            }

            //fetch all date field of retail
            try 
            {
                 String date_query = " select ApplicationDate,DateAccountOpened,DateFacsFirstSanction,LastSancDate,DOBIndividual,IssuedDate,PassportIssuedDate,PassportExpiryDate,DateRegisteredInst,"
                        + "CICReportDate,DateOfBirthCIC,CitizenshipIssuedDateCIC,PassportIssuedDateCIC,PassportExpiryDateCIC,DateLatestFinancialStmt,DobGuarantor,"
                        + "RegistrationExpiryDatePstApp,DateOfApprovalPstApprvl,DateOfApplicationPstApprvl from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'";
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
                    date_data.put("CICReportDate", customDate(itemsArray[9]));
                    date_data.put("DateOfBirthCIC", customDate(itemsArray[10]));
                    date_data.put("CitizenshipIssuedDateCIC", customDate(itemsArray[11]));
                    date_data.put("PassportIssuedDateCIC", customDate(itemsArray[12]));
                    date_data.put("PassportExpiryDateCIC", customDate(itemsArray[13]));
                    date_data.put("DateLatestFinancialStmt", customDate(itemsArray[14]));
                    date_data.put("DobGuarantor", customDate(itemsArray[15]));
                    date_data.put("RegistrationExpiryDatePstApp", customDate(itemsArray[16]));
                    date_data.put("DateOfApprovalPstApprvl", customDate(itemsArray[17]));
                    date_data.put("DateOfApplicationPstApprvl", customDate(itemsArray[18]));
                    
                    
                    retail_review_renew_data_array.add(date_data);
                    LogMessages.statusLog.info("*** All Data Of date fetch successfully ***");
                }
            } 
                    
            catch (Exception e) 
            {
                e.printStackTrace();
                LogMessages.statusLog.info("*** Error Occured During fetching all date field of retail ***");
            }
                   

            //fetching all textarea data of retail
            try 
            {
                String textarea_query = " select Remarks1,Remarks2,Remarks3,Remarks4,Remarks5,RemarksIncome,CAScommentsCAS,ROCommentsCAS,CommentCLAD from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'"; 
                        
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
                    textarea_data.put("Remarks1", itemsArray[0]);
                    textarea_data.put("Remarks2", itemsArray[1]);
                    textarea_data.put("Remarks3", itemsArray[2]);
                    textarea_data.put("Remarks4", itemsArray[3]);
                    textarea_data.put("Remarks5", itemsArray[4]);
                    textarea_data.put("RemarksIncome", itemsArray[5]);
                    textarea_data.put("CAScommentsCAS", itemsArray[6]);
                    textarea_data.put("ROCommentsCAS", itemsArray[7]);
                    textarea_data.put("CommentCLAD", itemsArray[8]);
                    
                    retail_review_renew_data_array.add(textarea_data);
                    LogMessages.statusLog.info("*** All textarea_data of Retail fetched successfully ***");
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }

            //fetching all checkbox data of Retail
            try 
            {
                String retail_checkbox_query = "select SameAsPermAddress,Flag3,RealEstate,Automobile,Inventories,AccountReceivables,MovableAssets,FixedDeposit,BankDeposit,GovSecurities,CorpBonds,"
                        + "ListedSecurities,ValuableItems,OtherSecurity,AutoLoanCheckBox,HLCheckBox,MLCheckBox,EduLoanCheckBox,PLCCheckBox,PODCheckBox,ODLNCheckBox,ODGovCheckBox,ODLSCheckBox,OtherFacCheckBox,"
                        + "limittreecheckbox,disbursement from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + ApplicationNo + "'";

                LogMessages.statusLog.info("Retail checkbox data fetch query: " + retail_checkbox_query);
                List retail_checkbox__list = iFormObj.getDataFromDB(retail_checkbox_query);
                ListIterator retail_checkbox_list_iterator = retail_checkbox__list.listIterator();

                while (retail_checkbox_list_iterator.hasNext()) {
                    List Retail_checkbox_list_data = (List) retail_checkbox_list_iterator.next();
                    LogMessages.statusLog.info("List Object of Retail checkbox data: " + Retail_checkbox_list_data);
                    String[] itemsArray = new String[Retail_checkbox_list_data.size()];
                    itemsArray = (String[]) Retail_checkbox_list_data.toArray(itemsArray);
                    LogMessages.statusLog.info("Array of Retail checkbox  data: " + itemsArray);

                    JSONObject retail_checkbox_data = new JSONObject();
                    retail_checkbox_data.put("SameAsPermAddress", itemsArray[0]);
                    retail_checkbox_data.put("Flag3", itemsArray[1]);
                    retail_checkbox_data.put("RealEstate", itemsArray[2]);
                    retail_checkbox_data.put("Automobile", itemsArray[3]);
                    retail_checkbox_data.put("Inventories", itemsArray[4]);
                    retail_checkbox_data.put("AccountReceivables", itemsArray[5]);
                    retail_checkbox_data.put("MovableAssets", itemsArray[6]);
                    retail_checkbox_data.put("FixedDeposit", itemsArray[7]);
                    retail_checkbox_data.put("BankDeposit", itemsArray[8]);
                    retail_checkbox_data.put("GovSecurities", itemsArray[9]);
                    retail_checkbox_data.put("CorpBonds", itemsArray[10]);
                    retail_checkbox_data.put("ListedSecurities", itemsArray[11]);
                    retail_checkbox_data.put("ValuableItems", itemsArray[12]);
                    retail_checkbox_data.put("OtherSecurity", itemsArray[13]);
                    retail_checkbox_data.put("AutoLoanCheckBox", itemsArray[14]);
                    retail_checkbox_data.put("HLCheckBox", itemsArray[15]);
                    retail_checkbox_data.put("MLCheckBox", itemsArray[16]);
                    retail_checkbox_data.put("EduLoanCheckBox", itemsArray[17]);
                    retail_checkbox_data.put("PLCCheckBox", itemsArray[18]);
                    retail_checkbox_data.put("PODCheckBox", itemsArray[19]);
                    retail_checkbox_data.put("ODLNCheckBox", itemsArray[20]);
                    retail_checkbox_data.put("ODGovCheckBox", itemsArray[21]);
                    retail_checkbox_data.put("ODLSCheckBox", itemsArray[22]);
                    retail_checkbox_data.put("OtherFacCheckBox", itemsArray[23]);
                    retail_checkbox_data.put("limittreecheckbox", itemsArray[24]);
                    retail_checkbox_data.put("disbursement", itemsArray[25]);

                    retail_review_renew_data_array.add(retail_checkbox_data);
                    LogMessages.statusLog.info("*** All Data Of Retail checkbox fetch successfully ***");
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
                 //String Entity = iFormObj.getValue("Entity").toString();
                
                 //LogMessages.statusLog.info("Entity:: " +Entity );
                 
                 //if (!"Individual".equalsIgnoreCase(Entity)){
                String query = "select Name,Holding from CS_ShareholdingDetailsRet with(nolock) where  PID='" + PID + "'";
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
                    LogMessages.statusLog.info("*** CS_ShareholdingDetailsRet position zero :: ***" +itemsArray[0] );
                    LogMessages.statusLog.info("*** CS_ShareholdingDetailsRet position one :: ***" +itemsArray[1] );
                   
                    
                    jsonArray_ShareholdingDetailsRet.clear();
                    jsonArray_ShareholdingDetailsRet.add(obj);
                    iFormObj.addDataToGrid("tblShareholdingRetail", jsonArray_ShareholdingDetailsRet);
                    LogMessages.statusLog.info("*** Current Count:: ***" + count);
                    count++;
                }
                
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_ShareholdingDetailsRet", e);
            }
            //for CS_KeyPersonsDetailsRet
            JSONArray jsonArray_KeyPersonsDetailsRet = new JSONArray();
            try 
            {
                int count = 0;
                String query = "select Name,StatusForProfitInst from CS_KeyPersonsDetailsRet with(nolock) where PID='" + PID + "'";
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {

                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Name", itemsArray[0]);
                    obj.put("Status", itemsArray[1]);
                    
                    
                    LogMessages.statusLog.info("*** CS_KeyPersonsDetailsRet position zero :: ***" +itemsArray[0] );
                    LogMessages.statusLog.info("*** CS_KeyPersonsDetailsRet position one :: ***" +itemsArray[1] );
                    
                   
                   

                    jsonArray_KeyPersonsDetailsRet.clear();
                    jsonArray_KeyPersonsDetailsRet.add(obj);
                    iFormObj.addDataToGrid("tblProprietorPartnerDirectorInfoRetail", jsonArray_KeyPersonsDetailsRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            }
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_KeyPersonCitizenDetailsRet", e);
            }
            //for CS_KeyPersonCitizenDetailsRet
            JSONArray jsonArray_KeyPersonCitizenDetailsRet = new JSONArray();
            try 
            {
                int count = 0;
                String query = "Select Name,CitizenshipNo,IssuedBy,IssuedDate,IssuedDateBS from CS_KeyPersonCitizenDetailsRet with(nolock) where PID='" + PID + "'";
                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {

                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Name", itemsArray[0]);
                    obj.put("Citizenship Number", itemsArray[1]);
                    obj.put("Issued By (District)", itemsArray[2]);
                    obj.put("Issued Date", itemsArray[3]);
                    obj.put("Issued Date (BS)", itemsArray[4]);
                    LogMessages.statusLog.info("*** CS_KeyPersonCitizenDetailsRet position zero :: ***" +itemsArray[0] );
                    jsonArray_KeyPersonCitizenDetailsRet.clear();
                    jsonArray_KeyPersonCitizenDetailsRet.add(obj);
                    iFormObj.addDataToGrid("tblCitizenshipDetailsProfitInst", jsonArray_KeyPersonCitizenDetailsRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            }
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_KeyPersonCitizenDetailsRet", e);
            }
            
              /*for Loan Facilities (TAB) */
            // for CS_FacilitiesRetail

            JSONArray jsonArray_FacilitiesRetail = new JSONArray();

            try 
            {
                 int count = 0;
                 String query = "select ExistingOrNewFac,Exposure,TypeOfFacility,CreditFacility,Class,RiskRating,RORC,"
                         + "LimitExisting,OutstandingExisting,OverduePrincipal,OverdueInterest,RepaymentInstallmentAmt,"
                         + "FinalDateOfRepayment,InterestRateExisting,LoanAdminFeeExisting,LoanAdminFeeEnhance,"
                         + "PrepaymentFeeExisting,Purpose,Brand,Model,YearOfProduction,PurchasePrice,VendorDealerName,"
                         + "InterestRateNew,InterestAmount,LoanAdminFeeNew,PrepaymentFeeNew,LocationOfProperty,Mode,"
                         + "FMVHousingLoan,DVHousingLoan,StudentName,CollegeName,Country,CourseName,RelationWithObligor,"
                         + "LocationOfMortgagedProperty,FMVMortgageLoan,DVMortgageLoan,LimitNew,Tenure,TypeOfInstallment,"
                         + "Frequency,FirstInstallmentDue1,Amount,GracePeriod,TypeOfDeposit,Currency,TotalAmount,Margin,"
                         + "AmountNetOfMargin,Instrument,TypeOfSecurity,BorrowingPower,TypeOfShare,ShareIssuedBy,NoOfShare,"
                         + "LatestMarketPrice,AvgPrice180Days,TotalValue,TenureExpiryDate,Expiry,CommitmentFee,"
                         + "IncreaseRequired,NewLimitAfterIncrease,BaseRate,Premium,Tranche,DrawdownBasedOn,Percentage,"
                         + "Remarks,BOID,BaseRateExisting,PremiumExisting,LongTermRiskRating,BaseRateEnhancement,"
                         + "PremiumEnhancement,InterestRateEnhancement,REF_DESC,REF_DESC_copy from CS_FacilitiesRetail with(nolock) where PID='" + PID + "'";

                 LogMessages.statusLog.info("Query: " + query);
                 List QueryList = iFormObj.getDataFromDB(query);
                 ListIterator QueryListItr = QueryList.listIterator();
                   while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Facilities", itemsArray[0]);
                    obj.put("Exposure", itemsArray[1]);
                    obj.put("Type of Facility", itemsArray[2]);
                    obj.put("Credit Facility", itemsArray[3]);
                    obj.put("Class", itemsArray[4]);
                    obj.put("Short-Term Risk Rating", itemsArray[5]);
                    obj.put("RORC (Net)", itemsArray[6]);
                    obj.put("Limit", itemsArray[7]);
                    obj.put("Outstanding", itemsArray[8]);
                    obj.put("Overdue Principal (if any)", itemsArray[9]);
                    obj.put("Overdue Interest (if any)", itemsArray[10]);
                    obj.put("Repayment Installment Amount", itemsArray[11]);
                    obj.put("Tenure (Final Date of Repayment)", itemsArray[12]);
                    obj.put("Interest Rate (Base Rate +/-) (Percentage)", itemsArray[13]);
                    obj.put("Loan Administration Fee Existing (Percentage)", itemsArray[14]);
                    obj.put("Loan Administration Fee Enhancement (Percentage)", itemsArray[15]);
                    obj.put("Prepayment Fee (Percentage)", itemsArray[16]);
                    obj.put("Purpose", itemsArray[17]);
                    obj.put("Brand", itemsArray[18]);
                    obj.put("Model", itemsArray[19]);
                    obj.put("Year of Production", itemsArray[20]);
                    obj.put("Purchase Price", itemsArray[21]);
                    obj.put("Vendor/Dealer Name", itemsArray[22]);
                    obj.put("Interest Rate (Base Rate +/-) (New Facility) (Percentage)", itemsArray[23]);
                    //obj.put("Recommended Limit", itemsArray[24]);
                    obj.put("Interest Amount", itemsArray[24]);
                    obj.put("Loan Administration Fee (New Facility) (Percentage)", itemsArray[25]);
                    obj.put("Prepayment Fee (New Facility)", itemsArray[26]);
                    obj.put("Location of Property", itemsArray[27]);
                    obj.put("Mode", itemsArray[28]);
                    obj.put("Fair Market Value", itemsArray[29]);
                    obj.put("Distress Value", itemsArray[30]);
                    obj.put("Name of the Student", itemsArray[31]);
                    obj.put("College Name", itemsArray[32]);
                    obj.put("Country", itemsArray[33]);
                    obj.put("Course Name", itemsArray[34]);
                    obj.put("Relationship with the Obligor", itemsArray[35]);
                    obj.put("Location of Property being Mortgaged", itemsArray[36]);
                    obj.put("Professional Valuation - Fair Market Value", itemsArray[37]);
                    obj.put("Professional Valuation - Distress Value", itemsArray[38]);
                    obj.put("Proposed Limit", itemsArray[39]);
                    obj.put("Tenure (Months from First Drawdown)", itemsArray[40]);
                    obj.put("Type of Installment", itemsArray[41]);
                    obj.put("Frequency", itemsArray[42]);
                    obj.put("First Installment Due (Days from First Drawdown)", itemsArray[43]);
                    obj.put("Amount", itemsArray[44]);
                    obj.put("Grace Period (Moratorium) - Days", itemsArray[45]);
                    obj.put("Type of Deposit", itemsArray[46]);
                    obj.put("Currency", itemsArray[47]);
                    obj.put("Total Amount", itemsArray[48]);
                    obj.put("Margin", itemsArray[49]);
                    obj.put("Amount (Net Of Margin)", itemsArray[50]);
                    obj.put("Instrument", itemsArray[51]);
                    obj.put("Type of Security", itemsArray[52]);
                    obj.put("Borrowing Power (Net of Margin)", itemsArray[53]);
                    obj.put("Type of Share", itemsArray[54]);
                    obj.put("Share issued by", itemsArray[55]);
                    obj.put("Number of Share", itemsArray[56]);
                    obj.put("Latest Market Price", itemsArray[57]);
                    obj.put("Average Price (Last 180 days)", itemsArray[58]);
                    obj.put("Total Value Considered for Borrowing Power", itemsArray[59]);
                    obj.put("Tenure (Expiry Date)", itemsArray[60]);
                    obj.put("Expiry", itemsArray[61]);
                    obj.put("Commitment Fee", itemsArray[62]);
                    obj.put("Increase Required?", itemsArray[63]);
                    obj.put("New Proposed Limit (After Increase)", itemsArray[64]);
                    obj.put("Base Rate (New Facility)", itemsArray[65]);
                    obj.put("Premium (New Facility)", itemsArray[66]);
                    obj.put("Tranche", itemsArray[67]);
                    obj.put("Drawdown Based On", itemsArray[68]);
                    obj.put("Percentage", itemsArray[69]);
                    obj.put("Remarks", itemsArray[70]);
                    obj.put("BOID", itemsArray[71]);
                    obj.put("Base Rate", itemsArray[72]);
                    obj.put("Premium", itemsArray[73]);
                    obj.put("Long-Term Risk Rating", itemsArray[74]);
                    obj.put("Base Rate (Enhancement)", itemsArray[75]);
                    obj.put("Premium (Enhancement)", itemsArray[76]);
                    obj.put("Interest Rate (Base Rate +/-) (Enhancement) (Percentage)", itemsArray[77]);
                    obj.put("REF_DESC", itemsArray[78]);
                    obj.put("REF_DESC_copy", itemsArray[79]);
                    //obj.put("Rate", itemsArray[80]);
                     
                    LogMessages.statusLog.info("*** CS_FacilitiesRetail position zero :: ***" +itemsArray[0] );
                    LogMessages.statusLog.info("*** CS_FacilitiesRetail position one :: ***" +itemsArray[1] );
                    
                    jsonArray_FacilitiesRetail.clear();
                    jsonArray_FacilitiesRetail.add(obj);
                    iFormObj.addDataToGrid("tblFacilitiesRetail", jsonArray_FacilitiesRetail);
                    LogMessages.statusLog.info("*** Data added to  the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_LoanAccOpeningPostApproval", e);

            }
            /* for facilities (TAB) 
            // for CS_CustomizedInstallmentRet

            JSONArray jsonArray_CustomizedInstallmentRet = new JSONArray();

            try 
            {
                int count = 0;
                String query = "Select Amount,Installment,InstallmentDueDays from CS_CustomizedInstallmentRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Amount", itemsArray[0]);
                    obj.put("Installment", itemsArray[1]);
                    obj.put("InstallmentDueDays", itemsArray[2]);
                    LogMessages.statusLog.info("*** CS_CustomizedInstallmentRet position zero :: ***" +itemsArray[0] );
                    jsonArray_CustomizedInstallmentRet.clear();
                    jsonArray_CustomizedInstallmentRet.add(obj);
                    iFormObj.addDataToGrid("table19", jsonArray_CustomizedInstallmentRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_CustomizedInstallmentRet", e);
            }*/

             //for Grid in  Security Details & Documents (TAB) 
            //for CS_RealEstatePropertyRet
            JSONArray jsonArray_RealEstatePropertyRet = new JSONArray();

            try 
            {
                int count = 0;
                String query = "select NatureOfCharge,PropertyType,Owner,NewOwner,RelationToBorrower,PlotNo,Area,Municipality,District,"
                            + "OwnershipType,StatusMortgageCharge,LatestDateExecution,ApartmentName,AreaBuildingApartment,Storey,PlotNumber,StatusBankMortgageCharge,"
                            +  "DateOfExecution,BasisOfValuation,ValuedBy,DateOfValuation,EstimatedFMV,EstimatedDV,PurchasePrice,DateOfInspection,NameInspectingOfficial,"
                            +    "DesignationInspectingOfficial,DateOfTransfer,ModeOfTransfer,LandCategory,QualityAccessRoad,WidthRoad,RoadAccess,ShapeOfLand,DistanceFromBranch,"
                            +    "BuildingCategory,ConstructionQuality,DateNirmanIjjajat,DateNirmanSampanna,Lalpurja,ValuationReport,FourBoundary,TraceMap,OwnershipTransferDoc,"
                            +    "LandRevenueReceipt,RegMortgageDeed,InternalMortgageDeed,RokkaMalpot,RokkaGuthi,NirmanIjjajat,NirmanSampanna,CcitizenshipFirmCertificate,GharNaksa,"
                            +    "InsurancePolicy,NoOfPlots,PlotNo2,PlotNo3,PlotNo4,PlotNo5,PlotNo6,PlotNo7,PlotNo8,PlotNo9,PlotNo10,Ropani,Aana,Paisa,Bigha,Kattha,Dhur,AreaLand,AreaPlot,"
                            +    "AreaPlot2,AreaPlot3,AreaPlot4,AreaPlot5,AreaPlot6,AreaPlot7,AreaPlot8,AreaPlot9,AreaPlot10,TotalAreaPlot,LocationLOC,OtherBuildingCategory,OtherConstructionQuality,"
                            +    "OtherRoadAccess,OtherLandCategory,OtherShapeOfLand,OtherModeOfTransfer,LandVerticallySloped,LandWithinRiverForest,LandWithinConservationArea,"
                            +    "LandWithinDasgaja,LandHasPondPool,LandShapeConsistent,LandRowSetback,RiverCanalSetback,HighTensionSetback,OtherSetbacks,SetbacksAffectsLandShape,"
                            +    "RemAreaSetbackAdjustment,SetbacksAffectsLandValue,SetbacksaffectsLandSalability,ValReportDeductions,NoStoreyConstructionApproval,BoundaryWall,"
                            +    "WaterSupply,ElectricityConnection,DrainageConnection,BluePrintObtained,PropertyConsistentWithBluePrint,EnquiredWithFamily,ReviewedValuationReport,"
                            +    "ConsideredFMV,ConsiderPropertyMortageable,BKDH,RAPD,PlotArea1,PlotArea2,PlotArea3,PlotArea4,PlotArea5,PlotArea6,PlotArea7,PlotArea8,PlotArea9,PlotArea10,"
                            +    "SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge,CollateralShared,Facilities,UtilizedFMV,UtilizedDV,Remarks,noofCollateral,"
                            +    "Facilities2,UtilizedFMV2,UtilizedDV2,Remarks2,Facilities3,Facilities4,Facilities5,UtilizedFMV3,UtilizedFMV4,UtilizedFMV5,UtilizedDV3,UtilizedDV4,UtilizedDV5,"
                            +    "Remarks3,Remarks4,Remarks5,BSVRID,SiteImages,Latitude,Longitude,CSVRRemarks,GoogleMap from CS_RealEstatePropertyRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();

                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Nature of Charge", itemsArray[0]);
                    
                    obj.put("Property Type", itemsArray[1]);
                    obj.put("Current Owner", itemsArray[2]);
                    obj.put("New Owner", itemsArray[3]);
                    obj.put("Relation to Borrower", itemsArray[4]);
                    obj.put("Plot Number (Land)", itemsArray[5]);
                    obj.put("Area (Land)", itemsArray[6]);
                    obj.put("Municipality", itemsArray[7]);
                    obj.put("District", itemsArray[8]);
                    obj.put("Ownership Type", itemsArray[9]);
                    obj.put("Status of Bank's Mortgage Charge (Registered or Unregistered)", itemsArray[10]);
                    obj.put("Latest Date of Execution/Registration of Charge", itemsArray[11]);
                    obj.put("Apartment Name", itemsArray[12]);
                    obj.put("Area of Building, Apartment (Existing and/or Proposed)", itemsArray[13]);
                    obj.put("Storey (Existing and/or Proposed)", itemsArray[14]);
                    obj.put("Plot Number (Constructed Area)", itemsArray[15]);
                    obj.put("Status of Bank's Mortgage Charge", itemsArray[16]);
                    obj.put("Latest Date of Execution / Registration of Charge", itemsArray[17]);
                    obj.put("Basis of Valuation", itemsArray[18]);
                    obj.put("Valued By", itemsArray[19]);
                    obj.put("Date of Valuation", itemsArray[20]);
                    obj.put("Estimated Fair Market Value", itemsArray[21]);
                    obj.put("Estimated Distress Value", itemsArray[22]);
                    obj.put("Purchase Price", itemsArray[23]);
                    obj.put("Date of Inspection/Site Visit", itemsArray[24]);
                    obj.put("Name of the Inspecting Official/s", itemsArray[25]);
                    obj.put("Designation of the Inspecting Official/s", itemsArray[26]);
                    obj.put("Date of Transfer of Title (Land)", itemsArray[27]);
                    obj.put("Mode of Transfer of Title (Land)", itemsArray[28]);
                    obj.put("Land Category (Utility)", itemsArray[29]);
                    obj.put("Quality of Access Road", itemsArray[30]);
                    obj.put("Width of the Road", itemsArray[31]);
                    obj.put("Road Access", itemsArray[32]);
                    obj.put("Shape of the Land", itemsArray[33]);
                    obj.put("Distance of the Location from the Branch in km", itemsArray[34]);
                    obj.put("Building Category (Utility)", itemsArray[35]);
                    obj.put("Construction Quality", itemsArray[36]);
                    obj.put("Date of permission for construction certificate / Nirman Ijjajat", itemsArray[37]);
                    obj.put("Date of building completion certificate / Nirman Sampanna", itemsArray[38]);
                    obj.put("Land Ownership Certificate / Lalpurja", itemsArray[39]);
                    obj.put("Valuation Report", itemsArray[40]);
                    obj.put("Four Boundary / Char Killa", itemsArray[41]);
                    obj.put("Trace Map / Blue Print", itemsArray[42]);
                    obj.put("Ownership Transfer Document (Rajinama, Bakas Patra, etc.)", itemsArray[43]);
                    obj.put("Land Revenue Report (Tiro)/Property Tax Paid Receipt", itemsArray[44]);
                    obj.put("Registered Mortgage Deed", itemsArray[45]);
                    obj.put("Internal Mortgage Deed (For Unregistered or Additional Charge)", itemsArray[46]);
                    obj.put("Rokka Letter from the Concerned Malpot Office", itemsArray[47]);
                    obj.put("Rokka Letter from the Concerned Guthi", itemsArray[48]);
                    obj.put("Permission for Construction Certificate / Nirman Ijjajat", itemsArray[49]);
                    obj.put("Building Completion Certificate / Nirman Sampanna", itemsArray[50]);
                    obj.put("Citizenship Certificate/Firm Registration Certificate of Owner", itemsArray[51]);
                    obj.put("Architect's design document of the building / Ghar Naksa", itemsArray[52]);
                    obj.put("Insurance policy with premium paid receipt", itemsArray[53]);
                    obj.put("Number of Plots", itemsArray[54]);
                    obj.put("Plot Number (Land) 2", itemsArray[55]);
                    obj.put("Plot Number (Land) 3", itemsArray[56]);
                    obj.put("Plot Number (Land) 4", itemsArray[57]);
                    obj.put("Plot Number (Land) 5", itemsArray[58]);
                    obj.put("Plot Number (Land) 6", itemsArray[59]);
                    obj.put("Plot Number (Land) 7", itemsArray[60]);
                    obj.put("Plot Number (Land) 8", itemsArray[61]);
                    obj.put("Plot Number (Land) 9", itemsArray[62]);
                    obj.put("Plot Number (Land) 10", itemsArray[63]);
                    obj.put("Ropani", itemsArray[64]);
                    obj.put("Aana", itemsArray[65]);
                    obj.put("Paisa", itemsArray[66]);
                    obj.put("Bigha", itemsArray[67]);
                    obj.put("Kattha", itemsArray[68]);
                    obj.put("Dhur", itemsArray[69]);
                    obj.put("Total Area of Land in Square Meters", itemsArray[70]);
                    obj.put("Area of Plot", itemsArray[71]);
                    obj.put("Area of Plot 2", itemsArray[72]);
                    obj.put("Area of Plot 3", itemsArray[73]);
                    obj.put("Area of Plot 4", itemsArray[74]);
                    obj.put("Area of Plot 5", itemsArray[75]);
                    obj.put("Area of Plot 6", itemsArray[76]);
                    obj.put("Area of Plot 7", itemsArray[77]);
                    obj.put("Area of Plot 8", itemsArray[78]);
                    obj.put("Area of Plot 9", itemsArray[79]);
                    obj.put("Area of Plot 10", itemsArray[80]);
                    obj.put("Total Area of Plot", itemsArray[81]);
                    obj.put("Location as per LOC", itemsArray[82]);
                    obj.put("Building Category (Other)", itemsArray[83]);
                    obj.put("Construction Quality (Other)", itemsArray[84]);
                    obj.put("Quality of Access Road (Other)", itemsArray[85]);
                    obj.put("Land Category (Other)", itemsArray[86]);
                    obj.put("Shape of Land (Other)", itemsArray[87]);
                    obj.put("Mode of Transfer of Title (Other)", itemsArray[88]);
                    obj.put("1. The Land is not vertically sloped", itemsArray[89]);
                    obj.put("2. The land is not within 60 metersâ€™ distance from RIVER/FOREST/P", itemsArray[90]);
                    obj.put("3. The land is not within 100 metersâ€™ distance from CONSERVATION ", itemsArray[91]);
                    obj.put("4. The land is not within 500 metersâ€™ distance from NO MANâ€™S LAND", itemsArray[92]);
                    obj.put("5. The land doesnâ€™t have POND/POOL within valuation considered ar", itemsArray[93]);
                    obj.put("6. The location and shape of the land appear consistent with Topo", itemsArray[94]);
                    obj.put("7. The land does not have potential ROW setback", itemsArray[95]);
                    obj.put("8. There aren't any river/canal setback", itemsArray[96]);
                    obj.put("9. There aren't any high-tension wire setback", itemsArray[97]);
                    obj.put("10. There aren't any other setbacks", itemsArray[98]);
                    obj.put("11. The setbacks would not affect the shape of the land", itemsArray[99]);
                    obj.put("12. The remaining area of the land after the setback adjustments ", itemsArray[100]);
                    obj.put("13. The setbacks would not affect value of the land", itemsArray[101]);
                    obj.put("14. The setbacks would not affect salability of the land", itemsArray[102]);
                    obj.put("15. The valuation report has made appropriate deductions on accou", itemsArray[103]);
                    obj.put("16. No of storey matches with the construction approval", itemsArray[104]);
                    obj.put("17. Is there a boundary wall in the property?", itemsArray[105]);
                    obj.put("18. Is there water supply connection in the property?", itemsArray[106]);
                    obj.put("19. Is there electricity connection in the property?", itemsArray[107]);
                    obj.put("20. Is there a drainage connection in the property?", itemsArray[108]);
                    obj.put("21. Blue Print and Trace Map has been obtained by the Bank staff ", itemsArray[109]);
                    obj.put("22. To the best of my/our knowledge, the shape, area and size of ", itemsArray[110]);
                    obj.put("23. I/we have enquired with family member of collateral owner as ", itemsArray[111]);
                    obj.put("24. I/we have reviewed the valuation report which, I /we am/are s", itemsArray[112]);
                    obj.put("25. I/we consider the Fair Market Value of Preliminary/ Full Valu", itemsArray[113]);
                    obj.put("26. I/we consider the property is acceptable for mortgage to Bank", itemsArray[114]);
                    obj.put("Total B-K-D-H", itemsArray[115]);
                    obj.put("Total R-A-P-D", itemsArray[116]);
                    obj.put("Area Plot 1", itemsArray[117]);
                    obj.put("Area Plot 2", itemsArray[118]);
                    obj.put("Area Plot 3", itemsArray[119]);
                    obj.put("Area Plot 4", itemsArray[120]);
                    obj.put("Area Plot 5", itemsArray[121]);
                    obj.put("Area Plot 6", itemsArray[122]);
                    obj.put("Area Plot 7", itemsArray[123]);
                    obj.put("Area Plot 8", itemsArray[124]);
                    obj.put("Area Plot 9", itemsArray[125]);
                    obj.put("Area Plot 10", itemsArray[126]);
                    obj.put("Security Type", itemsArray[127]);
                    obj.put("Sub Type Security", itemsArray[128]);
                    obj.put("Description", itemsArray[129]);
                    obj.put("Security Code", itemsArray[130]);
                    obj.put("Other Nature of Charge", itemsArray[131]);
                    obj.put("Is the Collateral Shared ?", itemsArray[132]);
                    obj.put("Facilities", itemsArray[133]);
                    obj.put("Utilized Fair Market Value(FMV)", itemsArray[134]);
                    obj.put("Utilized Distress Value(DV)", itemsArray[135]);
                    obj.put("Remarks", itemsArray[136]);
                    obj.put("Number of Collateral Shared", itemsArray[137]);
                    obj.put("2. Facilities", itemsArray[138]);
                    obj.put("2. Utilized Fair Market Value(FMV)", itemsArray[139]);
                    obj.put("2. Utilized Distress Value(DV)", itemsArray[140]);
                    obj.put("2. Remarks", itemsArray[141]);
                    obj.put("3. Facilities", itemsArray[142]);
                    obj.put("4. Facilities", itemsArray[143]);
                    obj.put("5. Facilities", itemsArray[144]);
                    obj.put("3. Utilized Fair Market Value(FMV)", itemsArray[145]);
                    obj.put("4. Utilized Fair Market Value(FMV)", itemsArray[146]);
                    obj.put("5. Utilized Fair Market Value(FMV)", itemsArray[147]);
                    obj.put("3. Utilized Distress Value(DV)", itemsArray[148]);
                    obj.put("4. Utilized Distress Value(DV)", itemsArray[149]);
                    obj.put("5. Utilized Distress Value(DV)", itemsArray[150]);
                    obj.put("3. Remarks", itemsArray[151]);
                    obj.put("4. Remarks", itemsArray[152]);
                    obj.put("5. Remarks", itemsArray[153]);
                    obj.put("CSVR ID", itemsArray[154]);
                    obj.put("SiteImage", itemsArray[155]);
                    obj.put("Latitude", itemsArray[156]);
                    obj.put("Longitude", itemsArray[157]);
                    obj.put("CSVR Remarks", itemsArray[158]);
                    obj.put("Google Map", itemsArray[159]);
                    LogMessages.statusLog.info("*** CS_RealEstatePropertyRet position zero :: ***" +itemsArray[0] );

                    jsonArray_RealEstatePropertyRet.clear();
                    jsonArray_RealEstatePropertyRet.add(obj);
                    iFormObj.addDataToGrid("tblRealEstateSecurity", jsonArray_RealEstatePropertyRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_RealEstatePropertyRet", e);
            }
            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_AutomobileRet

            JSONArray jsonArray_AutomobileRet = new JSONArray();

            try 
            {
                int count = 0;
                String query = "Select NatureOfCharge,VehicleType,Brand,ModelName,YearOfProduction,EngineNo,ChassisNo,RegistrationNo,StatusHypothecationCharge,LatestDateExecution,"
                        + "BasisOfValuation,ValuedBy,DateOfValuation,EstimatedFMV,EstimatedDV,PurchasePrice,QuotationInvoice,AllotmentLetter,"
                        + "VatBill,CopyOfBlueBook,InsurancePolicy,HirepurchaseAgreement,ThirdPartyOwnershipTransfer,SecurityType,SubSecurityType,"
                        + "SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_AutomobileRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();

                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Nature of Charge", itemsArray[0]);
                    obj.put("Vehicle Type", itemsArray[1]);
                    obj.put("Brand", itemsArray[2]);
                    obj.put("Model", itemsArray[3]);
                    obj.put("Year of Production", itemsArray[4]);
                    obj.put("Engine No.", itemsArray[5]);
                    obj.put("Chasis No.", itemsArray[6]);
                    obj.put("Registration Number", itemsArray[7]);
                    obj.put("Status of Bank's Hypothecation Charge", itemsArray[8]);
                    obj.put("Latest Date of Execution/Registration of Charge", itemsArray[9]);
                    obj.put("Basis of Valuation", itemsArray[10]);
                    obj.put("Valued By", itemsArray[11]);
                    obj.put("Date of Valuation", itemsArray[12]);
                    obj.put("Estimated Fair Market Value", itemsArray[13]);
                    obj.put("Estimated Distress Value", itemsArray[14]);
                    obj.put("Purchase Price", itemsArray[15]);
                    obj.put("Quotation/Proforma Invoice", itemsArray[16]);
                    obj.put("Allotment Letter", itemsArray[17]);
                    obj.put("VAT Bill / Tax Invoice", itemsArray[18]);
                    obj.put("Copy of Blue Book noting the Bank's Interest", itemsArray[19]);
                    obj.put("Insurance policy with premium paid receipt", itemsArray[20]);
                    obj.put("Hire purchase agreement", itemsArray[21]);
                    obj.put("Third-party ownership transfer", itemsArray[22]);
                    obj.put("Security Type", itemsArray[23]);
                    obj.put("Sub Type Security", itemsArray[24]);
                    obj.put("Description", itemsArray[25]);
                    obj.put("Security Code", itemsArray[26]);
                    obj.put("Other Nature of Charge", itemsArray[27]);
                   

                    jsonArray_AutomobileRet.clear();
                    jsonArray_AutomobileRet.add(obj);
                    iFormObj.addDataToGrid("tblAutomobileSecurity", jsonArray_AutomobileRet);
                    LogMessages.statusLog.info("*** Data added to the grid ......................***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_AutomobileRet", e);
            }
            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_InventoriesAccReceivableRet

            JSONArray jsonArray_InventoriesAccReceivableRet = new JSONArray();

            try 
            {
                int count = 0;
                String query = "Select NatureOfCharge,TypeOfAssets,StatusHypothecationCharge,LatestDateExecution,BasisOfValuation,DateLastStmt,EstimatedFMV,"
                        + "EstimatedDV,NameInspectingOfficial,DesignationInspectingOfficial,TotalInventory,TotalReceivable,OtherCurrentAsset,TotalPayables,"
                        + "NetTradingAssets,BusinessInOperation,BusinessOperationSatisfactory,RegDocPanRenewed,LeaseAgreement,RequisitesForSmoothOperation,"
                        + "AmountInventoryAdequate,QualityInventorySatisfactory,FirefightingMeasures,SecurityArrangements,NoLabourIssues,KeyPersonContacted,"
                        + "NoNegativeNews,InsurancePolicy,AssignmentOfBills,PowerOfAttorney,GeneralLetterHypo,SupplementaryAgreement,PledgeAgreement,RegOfCharge,"
                        + "SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge  from CS_InventoriesAccReceivableRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();

                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Nature of Charge", itemsArray[0]);
                    obj.put("Type of Assets", itemsArray[1]);
                    obj.put("Status of Bank's Hypothecation Charge", itemsArray[2]);
                    obj.put("Latest Date of Execution/Registration of Charge (STR)", itemsArray[3]);
                    obj.put("Basis of Valuation", itemsArray[4]);
                    obj.put("Date of Last Statement of Net Trading Assets", itemsArray[5]);
                    obj.put("Estimated Fair Market Value(Including completed portion of build)", itemsArray[6]);
                    obj.put("Estimated Distress Value(Including completed portion of building)", itemsArray[7]);
                    obj.put("Name of the Inspecting Official/s", itemsArray[8]);
                    obj.put("Designation of the Inspecting Official/s", itemsArray[9]);
                    obj.put("Total Inventory", itemsArray[10]);
                    obj.put("Total Receivable within Approved Tenure", itemsArray[11]);
                    obj.put("Other Current Asset, if allowed for Drawing Power Assessment", itemsArray[12]);
                    obj.put("Total Payables/Creditors", itemsArray[13]);
                    obj.put("Net Trading Assets", itemsArray[14]);
                    obj.put("1. Business was in operation at the time of inspection", itemsArray[15]);
                    obj.put("2. Overall operation of the business was found to be satisfactory", itemsArray[16]);
                    obj.put("3. Registration document & PAN were renewed & displayed properly", itemsArray[17]);
                    obj.put("4. In case of rented premises, the lease agreement was reviewed", itemsArray[18]);
                    obj.put("5. Basic requisites for smooth operation of business/factory foun", itemsArray[19]);
                    obj.put("6. The amount of inventory & receivables was found to be adequate", itemsArray[20]);
                    obj.put("7. The quality of inventory & receivables was found to be satisfa", itemsArray[21]);
                    obj.put("8. Firefighting measures were in place", itemsArray[22]);
                    obj.put("9. Security arrangements were in place", itemsArray[23]);
                    obj.put("10. There were no labour issues apparent", itemsArray[24]);
                    obj.put("11. The key person of the borrower could be contacted", itemsArray[25]);
                    obj.put("12. There were no negative news regarding Borrower/Promoter/Direc", itemsArray[26]);
                    obj.put("Insurance policy with premium paid receipt", itemsArray[27]);
                    obj.put("Assignment of bills and account receivables", itemsArray[28]);
                    obj.put("Power of attorney", itemsArray[29]);
                    obj.put("General letter of hypothecation", itemsArray[30]);
                    obj.put("Supplementary agreement", itemsArray[31]);
                    obj.put("Pledge agreement", itemsArray[32]);
                    obj.put("Registration of charge under secured transaction registry", itemsArray[33]);
                    obj.put("Security Type", itemsArray[34]);
                    obj.put("Sub Type Security", itemsArray[35]);
                    obj.put("Description", itemsArray[36]);
                    obj.put("Security Code", itemsArray[37]);
                    obj.put("Other Nature of Charge", itemsArray[38]);
                   

                    jsonArray_InventoriesAccReceivableRet.clear();
                    jsonArray_InventoriesAccReceivableRet.add(obj);
                    iFormObj.addDataToGrid("tblInventoryARMovableSecurity", jsonArray_InventoriesAccReceivableRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }

            } catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_InventoriesAccReceivableRet", e);
            }

            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_FixedOrBankDeposirRet
            JSONArray jsonArray_FixedOrBankDeposirRet = new JSONArray();

            try 
            {
                int count = 0;
                String query = "Select NatureOfCharge,TypeOfDeposit,Currency,AccountNo,MaturityDate,StatusBankLienCharge,LatestDateExecution,ValueOfFixedDepositCharge,"
                        + "FixedDepositReceipt,LienChargeOverDeposit,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge  from CS_FixedOrBankDeposirRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Nature of Charge", itemsArray[0]);
                    obj.put("Type of Deposit", itemsArray[1]);
                    obj.put("Currency", itemsArray[2]);
                    obj.put("Fixed Deposit or Bank Deposit Account Number", itemsArray[3]);
                    obj.put("Maturity Date", itemsArray[4]);
                    obj.put("Status of Bank's Lien Charge", itemsArray[5]);
                    obj.put("Latest Date of Execution", itemsArray[6]);
                    obj.put("Value of the Fixed Deposit/Charge over Deposit", itemsArray[7]);
                    obj.put("Fixed Deposit Receipt", itemsArray[8]);
                    obj.put("Lien Charge over the Deposit", itemsArray[9]);
                    obj.put("Security Type", itemsArray[10]);
                    obj.put("Sub Type Security", itemsArray[11]);
                    obj.put("Description", itemsArray[12]);
                    obj.put("Security Code", itemsArray[13]);
                    obj.put("Other Nature of Charge", itemsArray[14]);

                    jsonArray_FixedOrBankDeposirRet.clear();
                    jsonArray_FixedOrBankDeposirRet.add(obj);
                    iFormObj.addDataToGrid("tblFixedDepositSecurity", jsonArray_FixedOrBankDeposirRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_FixedOrBankDeposirRet", e);
            }

            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_GovermentSecuritiesRet
            JSONArray jsonArray_GovermentSecuritiesRet = new JSONArray();

            try 
            {
                int count = 0;
                String query = "Select NatureOfCharge,Instrument,TypeOfSecurity,InstrumentNo,MaturityDate,StatusBankLienCharge,LatestDateExecution,"
                        + "ValueGovSecurities,OriginalInstrument,LetterOfPledge,ConfirmationFromNRB,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_GovermentSecuritiesRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Nature of Charge", itemsArray[0]);
                    obj.put("Instrument", itemsArray[1]);
                    obj.put("Type of Security", itemsArray[2]);
                    obj.put("Instrument Number", itemsArray[3]);
                    obj.put("Maturity Date", itemsArray[4]);
                    obj.put("Status of Bank's Lien or Pledge Charge", itemsArray[5]);
                    obj.put("Latest Date of Execution", itemsArray[6]);
                    obj.put("Value of the Government Securities", itemsArray[7]);
                    obj.put("Original Instrument with Blank Endorsement", itemsArray[8]);
                    obj.put("Letter of Pledge / Lien Charge over the Security", itemsArray[9]);
                    obj.put("Confirmation from NRB with regard to the Bank's Charge", itemsArray[10]);
                    obj.put("Security Type", itemsArray[11]);
                    obj.put("Sub Type Security", itemsArray[12]);
                    obj.put("Description", itemsArray[13]);
                    obj.put("Security Code", itemsArray[14]);
                    obj.put("Other Nature of Charge", itemsArray[15]);

                    jsonArray_GovermentSecuritiesRet.clear();
                    jsonArray_GovermentSecuritiesRet.add(obj);
                    iFormObj.addDataToGrid("tblGovSecurity", jsonArray_GovermentSecuritiesRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_GovermentSecuritiesRet", e);
            }

            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_CorporateBondRet
            JSONArray jsonArray_CorporateBondRet = new JSONArray();

            try 
            {
                int count = 0;
                String query = "Select NatureOfCharge,IssuingCompanyName,InstrumentNo,MaturityDate,StatusBankLienCharge,LatestDateExecution,ValueCorpBond,"
                        + "OriginalInstrument,LetterOfPledge,ConfirmationFromIssuingCompany,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_CorporateBondRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Nature of Charge", itemsArray[0]);
                    obj.put("Name of the Issuing Company", itemsArray[1]);
                    obj.put("Instrument Number", itemsArray[2]);
                    obj.put("Maturity Date", itemsArray[3]);
                    obj.put("Status of Bank's Lien or Pledge Charge", itemsArray[4]);
                    obj.put("Latest Date of Execution", itemsArray[5]);
                    obj.put("Value of the Corporate Bond", itemsArray[6]);
                    obj.put("Original Instrument with Bank Endorsement", itemsArray[7]);
                    obj.put("Letter of Pledge / Lien Charge over the Security", itemsArray[8]);
                    obj.put("Confirmation from issuing Company/CDS with regard to Bank's Charg", itemsArray[9]);
                    obj.put("Security Type", itemsArray[10]);
                    obj.put("Sub Type Security", itemsArray[11]);
                    obj.put("Description", itemsArray[12]);
                    obj.put("Security Code", itemsArray[13]);
                    obj.put("Other Nature of Charge", itemsArray[14]);

                    jsonArray_CorporateBondRet.clear();
                    jsonArray_CorporateBondRet.add(obj);
                    iFormObj.addDataToGrid("tblCorpBondsSecurity", jsonArray_CorporateBondRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_CorporateBondRet", e);
            }

            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_ListedSecuritiesRet
            
            JSONArray jsonArray_ListedSecuritiesRet = new JSONArray();

            try 
            {
                int count = 0;
                String query = "Select NatureOfCharge,IssuingCompanyName,InstrumentNo,StatusBankLienCharge,LatestDateExecution,Manjurinama,"
                        + "Annexure,PledgeConfirmation,DebitInstructionSlip,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_ListedSecuritiesRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Nature of Charge", itemsArray[0]);
                    obj.put("Name of Issuing Company", itemsArray[1]);
                    obj.put("Instrument Number", itemsArray[2]);
                    obj.put("Status of Bank's Lien or Pledge Charge", itemsArray[3]);
                    obj.put("Latest Date of Execution", itemsArray[4]);
                    obj.put("Manjurinama", itemsArray[5]);
                    obj.put("Annexure 18 and 19", itemsArray[6]);
                    obj.put("Pledge Confirmation", itemsArray[7]);
                    obj.put("Debit Instruction Slip", itemsArray[8]);
                    obj.put("Security Type", itemsArray[9]);
                    obj.put("Sub Type Security", itemsArray[10]);
                    obj.put("Description", itemsArray[11]);
                    obj.put("Security Code", itemsArray[12]);
                    obj.put("Other Nature of Charge", itemsArray[13]);
                    

                    jsonArray_ListedSecuritiesRet.clear();
                    jsonArray_ListedSecuritiesRet.add(obj);
                    iFormObj.addDataToGrid("tblListedSecurity", jsonArray_ListedSecuritiesRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_ListedSecuritiesRet", e);
            }

            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_SharesRet
            JSONArray jsonArray_SharesRet = new JSONArray();
            try 
            {
                int count = 0;
                String query = "Select TypeOfShare,ShareIssuedBy,LatestMarketPrice,AvgPrice,TotalValue,Margin,BorrowingPower  from CS_SharesRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) 
                {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Type of Share", itemsArray[0]);
                    obj.put("Share issued by", itemsArray[1]);
                    obj.put("Latest Market Price", itemsArray[2]);
                    obj.put("Average Price (Last 180 days)", itemsArray[3]);
                    obj.put("Total Value (Considered for Borrowing Power)", itemsArray[4]);
                    obj.put("Margin", itemsArray[5]);
                    obj.put("Borrowing Power (Net of Margin)", itemsArray[6]);

                    jsonArray_SharesRet.clear();
                    jsonArray_SharesRet.add(obj);
                    iFormObj.addDataToGrid("tblSharesRetail", jsonArray_SharesRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_SharesRet", e);
            }
            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_GoldValuableSecuritiesRet

            JSONArray jsonArray_GoldValuableSecuritiesRet = new JSONArray();
            try {
                int count = 0;
                String query = "Select NatureOfCharge,Type,OtherType,Weight,StatusOfBankLienCharge,LatestDateexecution,PledgeAgreement,SecurityType,"
                        + "SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_GoldValuableSecuritiesRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();

                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Nature of Charge", itemsArray[0]);
                    obj.put("Type", itemsArray[1]);
                    obj.put("Other Type", itemsArray[2]);
                    obj.put("Weight", itemsArray[3]);
                    obj.put("Status of Bank's Lien or Pledge Charge", itemsArray[4]);
                    obj.put("Latest Date of Execution", itemsArray[5]);
                    obj.put("Pledge Agreement", itemsArray[6]);
                    obj.put("Security Type", itemsArray[7]);
                    obj.put("Sub Type Security", itemsArray[8]);
                    obj.put("Description", itemsArray[9]);
                    obj.put("Security Code", itemsArray[10]);
                    obj.put("Other Nature of Charge", itemsArray[11]);

                    jsonArray_GoldValuableSecuritiesRet.clear();
                    jsonArray_GoldValuableSecuritiesRet.add(obj);
                    iFormObj.addDataToGrid("tblValuablesSecurity", jsonArray_GoldValuableSecuritiesRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }

            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_GoldValuableSecuritiesRet", e);
            }
            /* for Grid in  Security Details & Documents (TAB) */
            //for CS_GuaranteeRet

            JSONArray jsonArray_GuaranteeRet = new JSONArray();
            try {
                int count = 0;
                String query = "Select Required,Type,Relation,OtherRelation,NameOfGuarantor,Gender,DOB,DOBBS,Age,GuaranteeStatus,DateOfExecution,Amount,Expiry,"
                        + "Networth,CitizenshipNo,CitizenshipIssuedDate,CitizenshipIssuedDateBS,CitizenshipIssuedDistrict,PassportNo,PassportIssuedDate,PassportExpiryDate,PassportIssuedPlace,"
                        + "FathersName,MothersName,GrandFathersName,SpouseName  from CS_GuaranteeRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Required", itemsArray[0]);
                    obj.put("Type", itemsArray[1]);
                    obj.put("Relation", itemsArray[2]);
                    obj.put("Specify Other Relation", itemsArray[3]);
                    obj.put("Name of the Guarantor", itemsArray[4]);
                    obj.put("Gender", itemsArray[5]);
                    obj.put("Date of Birth", itemsArray[6]);
                    obj.put("Date of Birth (BS)", itemsArray[7]);
                    obj.put("Age", itemsArray[8]);
                    obj.put("Status", itemsArray[9]);
                    obj.put("Date of Execution", itemsArray[10]);
                    obj.put("Amount", itemsArray[11]);
                    obj.put("Expiry", itemsArray[12]);
                    obj.put("Networth of the Guarantor", itemsArray[13]);
                    obj.put("Citizenship Number", itemsArray[14]);
                    obj.put("Citizenship Issued Date", itemsArray[15]);
                    obj.put("Citizenship Issued Date (BS)", itemsArray[16]);
                    obj.put("Citizenship Issued District", itemsArray[17]);
                    obj.put("Passport Number", itemsArray[18]);
                    obj.put("Passport Issued Date", itemsArray[19]);
                    obj.put("Passport Expiry Date", itemsArray[20]);
                    obj.put("Passport Issued Place", itemsArray[21]);
                    obj.put("Father's Name", itemsArray[22]);
                    obj.put("Mother's Name", itemsArray[23]);
                    obj.put("Grand Father's Name", itemsArray[24]);
                    obj.put("Spouse Name", itemsArray[25]);
                    

                    jsonArray_GuaranteeRet.clear();
                    jsonArray_GuaranteeRet.add(obj);
                    iFormObj.addDataToGrid("tblGuaranteeRetail", jsonArray_GuaranteeRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_GuaranteeRet", e);
            }
            /* for Grid in  Group Sheet (TAB) */
            //for CS_ConnectionOwnership

            JSONArray jsonArray_ConnectionOwnership = new JSONArray();

            try {
                int count = 0;
                String query = "Select unitName,Incorporation,Remarks,ShareholdersNum,ShareholderName1,ShareholderName2,ShareholderName3,ShareholderName4,"
                        + "ShareholderName5,ShareholderName6,ShareholderName7,ShareholderName8,ShareholderName9,ShareholderName10,ShareholderName11,ShareholderName12,"
                        + "ShareholderName13,ShareholderName14,ShareholderName15,ShareholderName16,ShareholderName17,ShareholderName18,ShareholderName19,"
                        + "ShareholderName20,HoldingPercentage1,HoldingPercentage2,HoldingPercentage3,HoldingPercentage4,HoldingPercentage5,HoldingPercentage6,"
                        + "HoldingPercentage7,HoldingPercentage8,HoldingPercentage9,HoldingPercentage10,HoldingPercentage11,HoldingPercentage12,HoldingPercentage13,"
                        + "HoldingPercentage14,HoldingPercentage15,HoldingPercentage16,HoldingPercentage17,HoldingPercentage18,HoldingPercentage19,HoldingPercentage20,ownership  from CS_ConnectionOwnership with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();

                    obj.put("Individual/Unit Name", itemsArray[0]);
                    obj.put("Incorporation", itemsArray[1]);
                    obj.put("Remarks", itemsArray[2]);
                    obj.put("Number of Shareholders", itemsArray[3]);
                    obj.put("First Shareholder Name", itemsArray[4]);
                    obj.put("Second Shareholder Name", itemsArray[5]);
                    obj.put("Third Shareholder Name", itemsArray[6]);
                    obj.put("Fourth Shareholder Name", itemsArray[7]);
                    obj.put("Fifth Shareholder Name", itemsArray[8]);
                    obj.put("Sixth Shareholder Name", itemsArray[9]);
                    obj.put("Seventh Shareholder Name", itemsArray[10]);
                    obj.put("Eighth Shareholder Name", itemsArray[11]);
                    obj.put("Ninth Shareholder Name", itemsArray[12]);
                    obj.put("Tenth Shareholder Name", itemsArray[13]);
                    obj.put("Eleventh Shareholder Name", itemsArray[14]);
                    obj.put("Twelfth Shareholder Name", itemsArray[15]);
                    obj.put("Thirteen Shareholder Name", itemsArray[16]);
                    obj.put("Fourteen Shareholder Name", itemsArray[17]);
                    obj.put("Fifteenth Shareholder Name", itemsArray[18]);
                    obj.put("Sixteenth Shareholder Name", itemsArray[19]);
                    obj.put("Seventeen Shareholder Name", itemsArray[20]);
                    obj.put("Eighteenth Shareholder Name", itemsArray[21]);
                    obj.put("Nineeten Shareholder Name", itemsArray[22]);
                    obj.put("Twenth Shareholder Name", itemsArray[23]);
                    obj.put("First Shareholding percentage", itemsArray[24]);
                    obj.put("Second Shareholding percentage", itemsArray[25]);
                    obj.put("Third Shareholding percentage", itemsArray[26]);
                    obj.put("Forth Shareholding percentage", itemsArray[27]);
                    obj.put("Fifth Shareholding percentage", itemsArray[28]);
                    obj.put("sixth Shareholding percentage", itemsArray[29]);
                    obj.put("Seventh Shareholding percentage", itemsArray[30]);
                    obj.put("Eighth Shareholding percentage", itemsArray[31]);
                    obj.put("Ninth Shareholding percentage", itemsArray[32]);
                    obj.put("Tenth Sharholding percentage", itemsArray[33]);
                    obj.put("Eleventh Shareholding percentage", itemsArray[34]);
                    obj.put("twelfth Shareholding percentage", itemsArray[35]);
                    obj.put("Thirteen Shareholding percentage", itemsArray[36]);
                    obj.put("Fourteen Shareholding percentage", itemsArray[37]);
                    obj.put("Fifteenth Shareholding percentage", itemsArray[38]);
                    obj.put("Sixteenth Shareholding percentage", itemsArray[39]);
                    obj.put("Seventeenth Shareholding percentage", itemsArray[40]);
                    obj.put("Eighteenth Shareholding percentage", itemsArray[41]);
                    obj.put("Ninenth Shareholding percentage", itemsArray[42]);
                    obj.put("Twenth Shareholding percentage", itemsArray[43]);
                    obj.put("Owership", itemsArray[44]);
                    

                    jsonArray_ConnectionOwnership.clear();
                    jsonArray_ConnectionOwnership.add(obj);
                    iFormObj.addDataToGrid("tblConnectionOwnershipRetail", jsonArray_ConnectionOwnership);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_ConnectionOwnership", e);
            }
            /*for Group Sheet(TAB)*/
            //for CS_Exposure

            JSONArray jsonArray_Exposure = new JSONArray();

            try {
                int count = 0;
                String query = "Select unitName,A,B,C,D,E,F,G,H,I,Total from CS_Exposure with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Unit Name ", itemsArray[0]);
                    obj.put("A", itemsArray[1]);
                    obj.put("B", itemsArray[2]);
                    obj.put("C", itemsArray[3]);
                    obj.put("D", itemsArray[4]);
                    obj.put("E", itemsArray[5]);
                    obj.put("F", itemsArray[6]);
                    obj.put("G", itemsArray[7]);
                    obj.put("H", itemsArray[8]);
                    obj.put("I", itemsArray[9]);
                    obj.put("Total", itemsArray[10]);
                    

                    jsonArray_Exposure.clear();
                    jsonArray_Exposure.add(obj);
                    iFormObj.addDataToGrid("tblExposureRetail", jsonArray_Exposure);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_Exposure", e);
            }

            /* for Group Sheet(TAB) */
            //for CS_GroupFacilitiesSME
            JSONArray jsonArray_GroupFacilitiesSME = new JSONArray();
            try {
                int count = 0;
                String query = "Select unitNameFac,shortTermLoan,limitShortTermLoan,outstandingShortTermLoan,pricingShortTermLoan,Remarks,OthercreditFacility  from CS_GroupFacilitiesSME with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Unit Name", itemsArray[0]);
                    obj.put("Facility", itemsArray[1]);
                    obj.put("Short Term Loan limit", itemsArray[2]);
                    obj.put("Outstanding1", itemsArray[3]);
                    obj.put("Pricing1", itemsArray[4]);
                    obj.put("Remarks", itemsArray[5]);
                    obj.put("Other Credit Facility", itemsArray[6]);
                   

                    jsonArray_GroupFacilitiesSME.clear();
                    jsonArray_GroupFacilitiesSME.add(obj);
                    iFormObj.addDataToGrid("tblGroupFacilitiesRetail", jsonArray_GroupFacilitiesSME);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_GroupFacilitiesSME", e);
            }

            /* for CIC Information(TAB) 
            //for CS_GuarantorDetailsCICRet
            JSONArray jsonArray_GuarantorDetailsCICRet = new JSONArray();

            try {
                int count = 0;
                String query = "Select Name,Gender,DOB,CitizenshipNo,CitizenshipIssuedDate,CitizenshipIssuedDistrict,PassportNo,PassportIssuedDate,"
                        + "PassportExpiryDate,PassportIssuedPlace,FathersName,MothersName,GrandFathersName,SpouseName  from CS_GuarantorDetailsCICRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Name of Guarantor", itemsArray[0]);
                    obj.put("Gender", itemsArray[1]);
                    obj.put("Date of Birth", itemsArray[2]);
                    obj.put("Citizenship Number", itemsArray[3]);
                    obj.put("Citizenship Issued Date", itemsArray[4]);
                    obj.put("Citizenship Issued District", itemsArray[5]);
                    obj.put("Passport Number", itemsArray[6]);
                    obj.put("Passport Issued Date", itemsArray[7]);
                    obj.put("Passport Expiry Date", itemsArray[8]);
                    obj.put("Passport Issued Place", itemsArray[9]);
                    obj.put("Father's Name", itemsArray[10]);
                    obj.put("Mother's Name", itemsArray[11]);
                    obj.put("Grand Father's Name", itemsArray[12]);
                    obj.put("Spouse Name", itemsArray[13]);

                    jsonArray_GuarantorDetailsCICRet.clear();
                    jsonArray_GuarantorDetailsCICRet.add(obj);
                    iFormObj.addDataToGrid("tblGuarantorDetailsCIC", jsonArray_GuarantorDetailsCICRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_GuarantorDetailsCICRet", e);
            }

            // for CIC Information(TAB) 
            //for CS_CICInfoRet
            JSONArray jsonArray_CICInfoRet = new JSONArray();
            try {
                int count = 0;
                String query = "Select Name,CICReportGenDate,CIRNum,CICReportType,CICCharge,ApplicableCharge,CustomerBlacklisted,LoanFromOtherBanks,BankName,"
                        + "Facility,Limit,OutstandingAmt,OverdueAmt from CS_CICInfoRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Name", itemsArray[0]);
                    obj.put("CIC Report Generated Date", itemsArray[1]);
                    obj.put("CIR Number", itemsArray[2]);
                    obj.put("CIC Report Type", itemsArray[3]);
                    obj.put("CIC Charge", itemsArray[4]);
                    obj.put("Applicable CIC Charge", itemsArray[5]);
                    obj.put("The customer is not blacklisted", itemsArray[6]);
                    obj.put("Customer is availing loan from other banks?", itemsArray[7]);
                    obj.put("Bank Name", itemsArray[8]);
                    obj.put("Facility", itemsArray[9]);
                    obj.put("Limit", itemsArray[10]);
                    obj.put("Outstanding Amount", itemsArray[11]);
                    obj.put("Overdue Amount (If overdue)", itemsArray[12]);
                    

                    jsonArray_CICInfoRet.clear();
                    jsonArray_CICInfoRet.add(obj);
                    iFormObj.addDataToGrid("tblCICInformation", jsonArray_CICInfoRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }

            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_CICInfoRet", e);
            }
            // for Repayment Capacity(TAB) 
            //for CS_IncomeExpensesRet

            JSONArray jsonArray_IncomeExpensesRet = new JSONArray();
            try {
                int count = 0;
                String query = "Select IncomeConsideredOf,IncomeOf,Profession,Remarks,SalaryIncome,BusinessIncome,ProfessionalIncome,"
                        + "RentalIncome,InvestmentIncome,OtherIncome,TotalIncome,LivingExpenses,InterestExpenses,BusinessExpenses,OtherExpenses,TotalExpenses,UYI from CS_IncomeExpensesRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Income considered of:", itemsArray[0]);
                    obj.put("Income of", itemsArray[1]);
                    obj.put("Profession", itemsArray[2]);
                    obj.put("Remarks", itemsArray[3]);
                    obj.put("Salary Income", itemsArray[4]);
                    obj.put("Business Income", itemsArray[5]);
                    obj.put("Professional Income", itemsArray[6]);
                    obj.put("Rental Income", itemsArray[7]);
                    obj.put("Investment Income", itemsArray[8]);
                    obj.put("Other Income", itemsArray[9]);
                    obj.put("Total Income", itemsArray[10]);
                    obj.put("Living Expenses", itemsArray[11]);
                    obj.put("Interest Expenses", itemsArray[12]);
                    obj.put("Business Expenses", itemsArray[13]);
                    obj.put("Other Expenses", itemsArray[14]);
                    obj.put("Total Expenses", itemsArray[15]);
                    obj.put("Uncommitted Yearly Income", itemsArray[16]);
                    

                    jsonArray_IncomeExpensesRet.clear();
                    jsonArray_IncomeExpensesRet.add(obj);
                    iFormObj.addDataToGrid("tblIncomeExpensesRetail", jsonArray_IncomeExpensesRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_IncomeExpensesRet", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_AutoLoanControlCompliance

            JSONArray jsonArray_AutoLoanControlCompliance = new JSONArray();
            try {
                int count = 0;
                String query = "Select Purpose,OtherPurpose,TypeOfAutomobile,RegistrationType,AgeOfBorrowerText,MinimumAgeBorrower,"
                        + "MaximumAgeBorrower,AgeOfBusiness,MinimumAgeBusiness,NetIncomeBasis,GrossIncomeBasis,DSCR,LoanLimit,MinimumLoanLimit,"
                        + "MaximumLoanLimit,LTVRatio,Tenure,MinimumTenure,MaximumTenure,AgeVehicle5Years,AgeVehicle7Years,CustomizedMaxTenure,"
                        + "Vehicle5Private7Commercial,TypeOfInstallment,Frequency,RepaymentDueDate,InterestDuringMoratorium,RepaymentStandingOrder,"
                        + "InsuranceCoverage,InterestRate,LoanAdminFee,EarlyPaymentFee,NoOfLoanFamily,AdditionalSecurity,AgeBorrowerTenureEnd,"
                        + "NetIncomeBasisCompliance,GrossIncomeBasisCompliance,DSCRCompliance,LTVRatioCompliance,AgeVehicle5YrsCompliance,"
                        + "AgeVehicle7YrsCompliance,Private5Comm7Compliance,InstallmentTypeCompliance,FrequencyCompliance,InterestDuringMoratoriumCompliance,"
                        + "RepaymentStandingOrderCompliance,InsuranceCovCompliance,InterestRateCompliance,LoanAdminFeeCompliance,EarlyPaymentFeeCompliance,NoOfLoanCompliance,"
                        + "AdditionalSecurityCompliance,AlFacility  from CS_AutoLoanControlCompliance with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Purpose", itemsArray[0]);
                    obj.put("Other Purpose", itemsArray[1]);
                    obj.put("Type of Automobile", itemsArray[2]);
                    obj.put("Registration Type", itemsArray[3]);
                    obj.put("Age of Borrower", itemsArray[4]);
                    obj.put("Minimum Age of Borrower", itemsArray[5]);
                    obj.put("Maximum Age of Borrower", itemsArray[6]);
                    obj.put("Age of Business", itemsArray[7]);
                    obj.put("Minimum Age of Business", itemsArray[8]);
                    obj.put("Net Income Basis", itemsArray[9]);
                    obj.put("Gross Income Basis", itemsArray[10]);
                    obj.put("Debt Service Coverage Ratio (DSCR)", itemsArray[11]);
                    obj.put("Loan Limit", itemsArray[12]);
                    obj.put("Minimum Loan Limit", itemsArray[13]);
                    obj.put("Maximum Loan Limit", itemsArray[14]);
                    obj.put("LTV Ratio", itemsArray[15]);
                    obj.put("Tenure in Months", itemsArray[16]);
                    obj.put("Minimum Tenure", itemsArray[17]);
                    obj.put("Maximum Tenure", itemsArray[18]);
                    obj.put("Age of Vehicle within 5 years", itemsArray[19]);
                    obj.put("Age of Vehicle within 7 years", itemsArray[20]);
                    obj.put("Customized Maximum Tenure (Months)", itemsArray[21]);
                    obj.put("Age of Vehicle within 5 years for 'Private' & 7 years 'Commercial", itemsArray[22]);
                    obj.put("Type of Installment", itemsArray[23]);
                    obj.put("Frequency", itemsArray[24]);
                    obj.put("Repayment Due Date", itemsArray[25]);
                    obj.put("Interest During Moratorium", itemsArray[26]);
                    obj.put("Repayment by way of standing order for which authority held", itemsArray[27]);
                    obj.put("Comprehensive Insurance coverage including RSD for total market v", itemsArray[28]);
                    obj.put("Interest Rate (Base Rate +/-)", itemsArray[29]);
                    obj.put("Loan Administration Fee", itemsArray[30]);
                    obj.put("Early Payment Fee", itemsArray[31]);
                    obj.put("Number of Loan within family members not exceeding 2 Nos.", itemsArray[32]);
                    obj.put("Additional Security to be held", itemsArray[33]);
                    obj.put("Age of Borrower at the end of the Loan Tenure", itemsArray[34]);
                    obj.put("Net Income Basis Compliance", itemsArray[35]);
                    obj.put("Gross Income Basis Compliance", itemsArray[36]);
                    obj.put("DSCR Compliance", itemsArray[37]);
                    obj.put("LTV Ratio Compliance", itemsArray[38]);
                    obj.put("Age of Vehicle within 5 years Compliance", itemsArray[39]);
                    obj.put("Age of Vehicle within 7 years Compliance", itemsArray[40]);
                    obj.put("Age Vehicle within 5 yrs Private & 7 yrs Commercial Compliance", itemsArray[41]);
                    obj.put("Type of Installment Compliance", itemsArray[42]);
                    obj.put("Frequency Compliance", itemsArray[43]);
                    obj.put("Interest During Moratorium Compliance", itemsArray[44]);
                    obj.put("Repayment by way of standing order Compliance", itemsArray[45]);
                    obj.put("Comprehensive Insurance Coverage Compliance", itemsArray[46]);
                    obj.put("Interest Rate Compliance", itemsArray[47]);
                    obj.put("Loan Administration Fee Compliance", itemsArray[48]);
                    obj.put("Early Payment Fee Compliance", itemsArray[49]);
                    obj.put("No. of Loan within family members not exceeding 2 Nos Compliance", itemsArray[50]);
                    obj.put("Additional Security to be held Compliance", itemsArray[51]);
                    obj.put("Credit Facility", itemsArray[52]);
                    

                    jsonArray_AutoLoanControlCompliance.clear();
                    jsonArray_AutoLoanControlCompliance.add(obj);
                    iFormObj.addDataToGrid("tblAutoLoanControlCompliances", jsonArray_AutoLoanControlCompliance);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }

            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_IncomeExpensesRet", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_HousingLoanControlComp

            JSONArray jsonArray_HousingLoanControlComp = new JSONArray();
            try {
                int count = 0;
                String query = "Select Purpose,LandfreeHoldHL,HouseNotExceedingHL,AgeOfBorrowerText,MinimumAgeBorrower,MaximumAgeBorrower,"
                        + "NetIncomeBasis,GrossIncomeBasis,RentalIncomeExclusion,LoanLimit,MinimumLoanLimit,MaximumLoanLimit,ConfirmNoHomeLoan,"
                        + "PurchasePrice,LTVRatio,Tenure,MinimumTenure,MaximumTenure,TenureExceed70,InstallmentType,Frequency,"
                        + "RepaymentDueDate,MoratoriumDays,InterestDuringMoratorium,RepaymentStandingOrder,BuildingInsured,InterestRate,"
                        + "LoanAdminFee,EarlyPaymentFee,OtherPurpose,AgeOfBorrower,AgeBorrowerTenureEnd,LTVRatio1,LandFreeHoldCompliance,"
                        + "HouseNotExceedingHLCompliance,NetIncomeBasisCompliance,GrossIncomeBasisCompliance,RentalIncomeExclusionCompliance,"
                        + "ConfirmNoHomeLoanCompliance,PurchasePriceCompliance,LTVRatioCompliance,TenureNotExceeding70YearsAgeCompliance,"
                        + "InstallmentTypeCompliance,FrequencyCompliance,MoratoriumPeriodCompliance,InterestDuringMoratoriumCompliance,RepaymentStandingOrderCompliance,"
                        + "PropertyInsuredCompliance,InterestRateCompliance,LoanAdminFeeCompliance,EarlyPaymentFeeCompliance,ReadyBuiltHouse,Constructed,"
                        + "ReadyBuiltHouseCompliance,ConstructedCompliance,LTVFMV,HIFacility from CS_HousingLoanControlComp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Purpose", itemsArray[0]);
                    obj.put("Land free hold by borrower or registered in Guthi sansthan and or", itemsArray[1]);
                    obj.put("House or Apartment not exceeding 3,000 sq feet", itemsArray[2]);
                    obj.put("Age of Borrower", itemsArray[3]);
                    obj.put("Minimum Age of the Borrower Compliance", itemsArray[4]);
                    obj.put("Maximum Age of the Borrower Compliance", itemsArray[5]);
                    obj.put("Net Income Basis", itemsArray[6]);
                    obj.put("Gross Income Basis", itemsArray[7]);
                    obj.put("Exclusion of rental income as a source of repayment", itemsArray[8]);
                    obj.put("Loan Limit", itemsArray[9]);
                    obj.put("Minimum Loan Limit Compliance", itemsArray[10]);
                    obj.put("Maximum Loan Limit Compliance", itemsArray[11]);
                    obj.put("Confirmation of no home loan from Nabil or any other Bank", itemsArray[12]);
                    obj.put("Purchase Price \"Thali\" and Limit", itemsArray[13]);
                    obj.put("LTV Ratio", itemsArray[14]);
                    obj.put("Tenure of Loan", itemsArray[15]);
                    obj.put("Minimum Tenure Compliance", itemsArray[16]);
                    obj.put("Maximum Tenure Compliance", itemsArray[17]);
                    obj.put("The tenure does not exceed 70 years of age of the borrower", itemsArray[18]);
                    obj.put("Type of Installment", itemsArray[19]);
                    obj.put("Frequency", itemsArray[20]);
                    obj.put("Repayment Due Date", itemsArray[21]);
                    obj.put("Moratorium Period (Days)", itemsArray[22]);
                    obj.put("Interest During Moratorium", itemsArray[23]);
                    obj.put("Repayment by way of standing order for which authority held", itemsArray[24]);
                    obj.put("The building being financed and mortgaged is insured against fire", itemsArray[25]);
                    obj.put("Interest Rate (Base Rate +/-)", itemsArray[26]);
                    obj.put("Loan Administration Fee", itemsArray[27]);
                    obj.put("Early Payment Fee complies with NRB Guideline", itemsArray[28]);
                    obj.put("Other Purpose", itemsArray[29]);
                    obj.put("Borrower's Age", itemsArray[30]);
                    obj.put("Age at the end of the Loan Tenure", itemsArray[31]);
                    obj.put("LTV Ratio wrt DV", itemsArray[32]);
                    obj.put("Land free hold Compliance", itemsArray[33]);
                    obj.put("House or Apartment not exceeding 3,000 sq feet Compliance", itemsArray[34]);
                    obj.put("Net Income Basis Compliance", itemsArray[35]);
                    obj.put("Gross Income Basis Compliance", itemsArray[36]);
                    obj.put("Exclusion of rental income as a source of repayment Compliance", itemsArray[37]);
                    obj.put("Confirmation of no home loan from Nabil or other Bank Compliance", itemsArray[38]);
                    obj.put("Purchase Price \"Thali\" and Limit Compliance", itemsArray[39]);
                    obj.put("LTV Ratio Compliance", itemsArray[40]);
                    obj.put("The tenure does not exceed 70 years of age of borrower Compliance", itemsArray[41]);
                    obj.put("Type of Installment Compliance", itemsArray[42]);
                    obj.put("Frequency Compliance", itemsArray[43]);
                    obj.put("Moratorium Period Compliance", itemsArray[44]);
                    obj.put("Interest During Moratorium Compliance", itemsArray[45]);
                    obj.put("Repayment by way of standing order Compliance", itemsArray[46]);
                    obj.put("The building being financed and mortgaged insurance Compliance", itemsArray[47]);
                    obj.put("Interest Rate Compliance", itemsArray[48]);
                    obj.put("Loan Administration Fee Compliance", itemsArray[49]);
                    obj.put("Early Payment Fee Compliance", itemsArray[50]);
                    obj.put("Ready build house/flat/Apartment not more than 35 years old.", itemsArray[51]);
                    obj.put("Constructed or to be constructed with due approval from authorize", itemsArray[52]);
                    obj.put("Ready build house/flat/Apartment not more than 35 years old Compl", itemsArray[53]);
                    obj.put("Constructed or to be constructed with due approval Compliance", itemsArray[54]);
                    obj.put("LTV Ratio wrt FMV", itemsArray[55]);
                    obj.put("Credit Facility", itemsArray[56]);
                    

                    jsonArray_HousingLoanControlComp.clear();
                    jsonArray_HousingLoanControlComp.add(obj);
                    iFormObj.addDataToGrid("tblHomeLoanControlCompliances", jsonArray_HousingLoanControlComp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_HousingLoanControlComp", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_MortgageLoanControlComp

            JSONArray jsonArray_MortgageLoanControlComp = new JSONArray();
            try {
                int count = 0;
                String query = "Select LegitimateUse,Purpose,LandFreeHoldML,PropertyLocation,AgeOfBorrowerText,AgeOfBorrower,MinAgeBorrowerCompliance,MaxAgeBorrowerCompliance,NetIncomeBasis,"
                        + "NetIncomeBasisCompliance,GrossIncomeBasis,GrossIncomeBasisCompliance,LoanLimit,MinLoanLimitCompliance,MaxLoanLimitCompliance,"
                        + "DocumentaryEvidence,LTVRatio,LTVRatio1,LTVRatioCompliance,Tenure,MinTenureCompliance,MinTenureCompliance,TenureExceed70,TenureExceed70Compliance,"
                        + "InstallmentType,InstallmentTypeCompliance,Frequency,FrequencyCompliance,RepaymentDueDate,InterestDuringMoratorium,"
                        + "InterestDurinMoratoriumCompliance,RepaymentStandingOrder,RepaymentStandingOrderCompliance,BuildingInsured,BuildingInsuredCompliance,"
                        + "InterestRate,InterestRateCompliance,LoanAdminFee,LoanAdminFeeCompliance,EarlyPaymentFee,EarlyPaymentFeeCompliance,"
                        + "AgeAtTheEndOfTenure,LandFreeHoldCompliance,OtherPurpose,DocumentaryEvidenceCompliance,ReadyBuiltHouse,ReadyBuiltHouseCompliance,"
                        + "Constructed,ConstructedCompliance,LTVFMV,MIFacility  from CS_MortgageLoanControlComp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Legitimate Use", itemsArray[0]);
                    obj.put("Purpose", itemsArray[1]);
                    obj.put("Land free hold by borrower or registered in Guthi sansthan and or", itemsArray[2]);
                    obj.put("Location of Property", itemsArray[3]);
                    obj.put("Age of Borrower", itemsArray[4]);
                    obj.put("Borrower's Age", itemsArray[5]);
                    obj.put("Minimum Age of the Borrower Compliance", itemsArray[6]);
                    obj.put("Maximum Age of the Borrower Compliance", itemsArray[7]);
                    obj.put("Net Income Basis", itemsArray[8]);
                    obj.put("Net Income Basis Compliance", itemsArray[9]);
                    obj.put("Gross Income Basis", itemsArray[10]);
                    obj.put("Gross Income Basis Compliance", itemsArray[11]);
                    obj.put("Loan Limit", itemsArray[12]);
                    obj.put("Minimum Loan Limit Compliance", itemsArray[13]);
                    obj.put("Maximum Loan Limit Compliance", itemsArray[14]);
                    obj.put("Documentary evidence of end use of fund held in case of loan abov", itemsArray[15]);
                    obj.put("LTV Ratio", itemsArray[16]);
                    obj.put("LTV Ratio wrt DV", itemsArray[17]);
                    obj.put("LTV Ratio Compliance", itemsArray[18]);
                    obj.put("Tenure of Loan", itemsArray[19]);
                    obj.put("Minimum Tenure Compliance", itemsArray[20]);
                    obj.put("Maximum Tenure Compliance", itemsArray[21]);
                    obj.put("The tenure does not exceed 70 years of age of the borrower", itemsArray[22]);
                    obj.put("The tenure does not exceed 70 years of age of borrower Compliance", itemsArray[23]);
                    obj.put("Type of Installment", itemsArray[24]);
                    obj.put("Type of Installment Compliance", itemsArray[25]);
                    obj.put("Frequency", itemsArray[26]);
                    obj.put("Frequency Compliance", itemsArray[27]);
                    obj.put("Repayment Due Date", itemsArray[28]);
                    obj.put("Interest During Moratorium", itemsArray[29]);
                    obj.put("Interest During Moratorium Compliance", itemsArray[30]);
                    obj.put("Repayment by way of standing order for which authority held", itemsArray[31]);
                    obj.put("Repayment by way of standing order Compliance", itemsArray[32]);
                    obj.put("The building being mortgaged is insured against fire, earthquake,", itemsArray[33]);
                    obj.put("The building being mortgaged insured Compliance", itemsArray[34]);
                    obj.put("Interest Rate Base Rate +/-", itemsArray[35]);
                    obj.put("Interest Rate Compliance", itemsArray[36]);
                    obj.put("Loan Administration Fee", itemsArray[37]);
                    obj.put("Loan Administration Fee Compliance", itemsArray[38]);
                    obj.put("Early Payment Fee complies with NRB Guideline", itemsArray[39]);
                    obj.put("Early Payment Fee Compliance", itemsArray[40]);
                    obj.put("Age of Borrower at the end of the Loan Tenure", itemsArray[41]);
                    obj.put("Land Free Hold Compliance", itemsArray[42]);
                    obj.put("Other Purpose", itemsArray[43]);
                    obj.put("Documentary evidence Compliance", itemsArray[44]);
                    obj.put("Ready build house/flat/Apartment not more than 35 years old.", itemsArray[45]);
                    obj.put("Ready build house/flat/Apartment not more than 35 years old Compl", itemsArray[46]);
                    obj.put("Constructed or to be constructed with due approval from authorize", itemsArray[47]);
                    obj.put("Constructed or to be constructed Compliance", itemsArray[48]);
                    obj.put("LTV Ratio wrt FMV", itemsArray[49]);
                    obj.put("Credit Facility", itemsArray[50]);
                    

                    jsonArray_MortgageLoanControlComp.clear();
                    jsonArray_MortgageLoanControlComp.add(obj);
                    iFormObj.addDataToGrid("tblMortgageLoanControlCompliances", jsonArray_MortgageLoanControlComp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_MortgageLoanControlComp", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_EducationLoanCtrlComp

            JSONArray jsonArray_EducationLoanCtrlComp = new JSONArray();
            try {
                int count = 0;
                String query = "Select Purpose,OtherPurpose,CountryOfEducation,CountryName,TypeOfProperty,AgeOfBorrowerText,AgeOfBorrower,MinAgeBorrowerCompliance,"
                        + "MaxAgeBorrowerCompliance,NetIncomeBasis,NetIncomeBasisCompliance,GrossIncomeBasis,GrossIncomeBasisCompliance,DSCR,DSCRCompliance,"
                        + "LoanLimit,MinLoanLimitCompliance,MaxLoanLimitCompliance,LTVRatio,LTVRatio1,LTVRatioCompliance,Drawdown,Tenure,MinTenureCompliance,"
                        + "MaxTenureCompliance,TenureExceed70Years,TenureExceed70Compliance,TypeOfInstallment,InstallmentTypeCompliance,Frequency,"
                        + "FrequencyCompliance,RepaymentDueDate,InterestDuringMoratorium,InterestDuringMoratoriumCompliance,RepaymentStandingOrder,RepaymentStandingOrderCompliance,"
                        + "BuildingInsured,BuildingInsuredCompliance,InterestRate,InterestRateCompliance,LoanAdminFee,LoanAdminFeeCompliance,EarlyPaymentFee,"
                        + "AgeBorrowerTenureEnd,CommitmentFee,AgeGuarantorTenureEnd,MaxAgeGuarantorCompliance,AgeOfBusiness,MinAgeBusinessCompliance,"
                        + "MoratoriumDays,MoratoriumPeriodCompliance,LTVFMV,EIFacility  from CS_EducationLoanCtrlComp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Purpose", itemsArray[0]);
                    obj.put("Other Purpose", itemsArray[1]);
                    obj.put("Country of Education", itemsArray[2]);
                    obj.put("Country Name", itemsArray[3]);
                    obj.put("Type of Property For Mortgage", itemsArray[4]);
                    obj.put("Property", itemsArray[5]);
                    obj.put("Age of Borrower or Business", itemsArray[6]);
                    obj.put("Age of Borrower", itemsArray[7]);
                    obj.put("Borrower's Age", itemsArray[8]);
                    obj.put("Minimum Age of the Borrower Compliance", itemsArray[9]);
                    obj.put("Maximum Age of the Borrower Compliance", itemsArray[10]);
                    obj.put("Net Income Basis", itemsArray[11]);
                    obj.put("Net Income Basis Compliance", itemsArray[12]);
                    obj.put("Gross Income Basis", itemsArray[13]);
                    obj.put("Gross Income Basis Compliance", itemsArray[14]);
                    obj.put("Debt Service Coverage Ratio DSCR", itemsArray[15]);
                    obj.put("DSCR Compliance", itemsArray[16]);
                    obj.put("Loan Limit", itemsArray[17]);
                    obj.put("Minimum Loan Limit Compliance", itemsArray[18]);
                    obj.put("Maximum Loan Limit Compliance", itemsArray[19]);
                    obj.put("LTV Ratio", itemsArray[20]);
                    obj.put("LTV Ratio wrt DV", itemsArray[21]);
                    obj.put("LTV Ratio Compliance", itemsArray[22]);
                    obj.put("Drawdown", itemsArray[23]);
                    obj.put("Tenure of Loan", itemsArray[24]);
                    obj.put("Minimum Tenure Compliance", itemsArray[25]);
                    obj.put("Maximum Tenure of Loan", itemsArray[26]);
                    obj.put("The tenure does not exceed 70 years of age of the borrower", itemsArray[27]);
                    obj.put("The tenure does not exceed 70 years of age of borrower Compliance", itemsArray[28]);
                    obj.put("Type of Installment", itemsArray[29]);
                    obj.put("Type of Installment Compliance", itemsArray[30]);
                    obj.put("Frequency", itemsArray[31]);
                    obj.put("Frequency Compliance", itemsArray[32]);
                    obj.put("Repayment Due Date", itemsArray[33]);
                    obj.put("Interest During Moratorium", itemsArray[34]);
                    obj.put("Interest During Moratorium Compliance", itemsArray[35]);
                    obj.put("Repayment by way of standing order for which authority held", itemsArray[36]);
                    obj.put("Repayment by way of standing order Compliance", itemsArray[37]);
                    obj.put("The building being mortgaged is insured against fire, earthquake,", itemsArray[38]);
                    obj.put("The building being mortgaged insured Compliance", itemsArray[39]);
                    obj.put("Interest Rate Base Rate +/-", itemsArray[40]);
                    obj.put("Interest Rate Compliance", itemsArray[41]);
                    obj.put("Loan Administration Fee", itemsArray[42]);
                    obj.put("Loan Administration Fee Compliance", itemsArray[43]);
                    obj.put("Early Payment Fee", itemsArray[44]);
                    obj.put("Age of Borrower at the end of the Loan Tenure", itemsArray[45]);
                    obj.put("Commitment Fee", itemsArray[46]);
                    obj.put("Age of Guarantor at Tenure End", itemsArray[47]);
                    obj.put("Maximum Age of Guarantor Compliance", itemsArray[48]);
                    obj.put("Age of Business", itemsArray[49]);
                    obj.put("Minimum Age of Business Compliance", itemsArray[50]);
                    obj.put("Moratorium Period Days", itemsArray[51]);
                    obj.put("Moratorium Period Compliance", itemsArray[52]);
                    obj.put("LTV Ratio wrt FMV", itemsArray[53]);
                    obj.put("Credit Facility", itemsArray[54]);

                    jsonArray_EducationLoanCtrlComp.clear();
                    jsonArray_EducationLoanCtrlComp.add(obj);
                    iFormObj.addDataToGrid("tblEducationLoanControlCompliances", jsonArray_EducationLoanCtrlComp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } 
            catch (Exception e) 
            {
                LogMessages.errorLog.info("Exception found CS_EducationLoanCtrlComp", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_PLCleanCtrlComp

            JSONArray jsonArray_PLCleanCtrlComp = new JSONArray();
            try {
                int count = 0;
                String query = "Select LegitimateUse,Purpose,OtherPurpose,AgeOfBorrowerText,AgeOfBorrower,MinAgeBorrowerCompliance,"
                        + "AgeBorrowerTenureEnd,MaxAgeBorrowerCompliance,NetIncomeBasis,NetIncomeBasisCompliance,GrossIncomeBasis,"
                        + "GrossIncomeBasisCompliance,LoanLimit,MinLoanLimitCompliance,MaxLoanLimitCompliance,Tenure,MaxTenureCompliance,"
                        + "InterestDuringMoratorium,InterestDuringMoratoriumCompliance,InterestRate,InterestRateCompliance,LoanAdminFee,"
                        + "LoanAdminFeeCompliance,EarlyPaymentFee,CommitmentFee,PANBankStmtAnalysed,EarlyPaymentFeeNRB,EarlyPaymentFeeCompliance  from CS_PLCleanCtrlComp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Legitimate Use", itemsArray[0]);
                    obj.put("Purpose", itemsArray[1]);
                    obj.put("Other Purpose", itemsArray[2]);
                    obj.put("Age of Borrower or Business", itemsArray[3]);
                    obj.put("Age of Borrower", itemsArray[4]);
                    obj.put("Borrower's Age", itemsArray[5]);
                    obj.put("Minimum Age of the Borrower Compliance", itemsArray[6]);
                    obj.put("Age of Borrower at the end of the Loan Tenure", itemsArray[7]);
                    obj.put("Maximum Age of the Borrower Compliance", itemsArray[8]);
                    obj.put("Net Income Basis", itemsArray[9]);
                    obj.put("Net Income Basis Compliance", itemsArray[10]);
                    obj.put("Gross Income Basis", itemsArray[11]);
                    obj.put("Gross Income Basis Compliance", itemsArray[12]);
                    obj.put("Loan Limit", itemsArray[13]);
                    obj.put("Minimum Loan Limit Compliance", itemsArray[14]);
                    obj.put("Maximum Loan Limit", itemsArray[15]);
                    obj.put("Tenure and Review", itemsArray[16]);
                    obj.put("Maximum Tenure Compliance", itemsArray[17]);
                    obj.put("Interest During Moratorium", itemsArray[18]);
                    obj.put("Interest During Moratorium Compliance", itemsArray[19]);
                    obj.put("Interest Rate (Base Rate +/-)", itemsArray[20]);
                    obj.put("Interest Rate (Base Rate +/-)", itemsArray[21]);
                    obj.put("Loan Administration Fee", itemsArray[22]);
                    obj.put("Loan Administration Fee Compliance", itemsArray[23]);
                    obj.put("Early Payment Fee", itemsArray[24]);
                    obj.put("Commitment Fee", itemsArray[25]);
                    obj.put("PAN and Bank Statement held and analysed", itemsArray[26]);
                    obj.put("Early Payment Fee complies with NRB Guideline", itemsArray[27]);
                    obj.put("Early Payment Fee Compliance", itemsArray[28]);

                    jsonArray_PLCleanCtrlComp.clear();
                    jsonArray_PLCleanCtrlComp.add(obj);
                    iFormObj.addDataToGrid("tblPLCleanControlCompliances", jsonArray_PLCleanCtrlComp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_PLCleanCtrlComp", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_PODCtrlComp

            JSONArray jsonArray_PODCtrlComp = new JSONArray();

            try {
                int count = 0;
                String query = "Select Type,Purpose,Property,AgeOfBorrowerText,AgeOfBorrower,MinAgeBorrowerCompliance,AgeBorrowerTenureEnd,"
                        + "MaxAgeBorrowerCompliance,NetIncomeBasis,NetIncomeBasisCompliance,GrossIncomeBasis,GrossIncomeBasisCompliance,"
                        + "LoanLimit,MinLoanLimitCompliance,MaxLoanLimitCompliance,LTVRatio,LTVRatio1,LTVRatioCompliance,TenureDate,"
                        + "MaxTenureCompliance,InterestPaidMonthly,InterestPaidMonthlyCompliance,BuildingInsured,BuildingInsuredCompliance,"
                        + "InterestRate,InterestRateCompliance,LoanAdminFee,LoanAdminFeeCompliance,CommitmentFee,LTVFMV,PODFacility  from CS_PODCtrlComp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Type", itemsArray[0]);
                    obj.put("Purpose", itemsArray[1]);
                    obj.put("Property", itemsArray[2]);
                    obj.put("Age of Borrower", itemsArray[3]);
                    obj.put("Borrower's Age", itemsArray[4]);
                    obj.put("Minimum Age of the Borrower", itemsArray[5]);
                    obj.put("Age of Borrower at the end of the Loan Tenure", itemsArray[6]);
                    obj.put("Maximum Age of the Borrower", itemsArray[7]);
                    obj.put("Net Income Basis", itemsArray[8]);
                    obj.put("Net Income Basis Compliance", itemsArray[9]);
                    obj.put("Gross Income Basis", itemsArray[10]);
                    obj.put("Gross Income Basis Compliance", itemsArray[11]);
                    obj.put("Loan Limit", itemsArray[12]);
                    obj.put("Minimum Loan Limit Compliance", itemsArray[13]);
                    obj.put("Maximum Loan Limit Compliance", itemsArray[14]);
                    obj.put("LTV Ratio", itemsArray[15]);
                    obj.put("LTV Ratio with DV", itemsArray[16]);
                    obj.put("LTV Ratio Compliance", itemsArray[17]);
                    obj.put("Tenure and Review", itemsArray[18]);
                    obj.put("Maximum Tenure Compliance", itemsArray[19]);
                    obj.put("Interest to be paid monthly", itemsArray[20]);
                    obj.put("Interest to be paid monthly Compliance", itemsArray[21]);
                    obj.put("The building being mortgaged is insured against fire, earthquake,", itemsArray[22]);
                    obj.put("The building being mortgaged is insured Compliance", itemsArray[23]);
                    obj.put("Interest Rate (Base Rate +/-)", itemsArray[24]);
                    obj.put("Interest Rate Compliance", itemsArray[25]);
                    obj.put("Loan Administration Fee", itemsArray[26]);
                    obj.put("Loan Administration Fee Compliance", itemsArray[27]);
                    obj.put("Commitment Fee", itemsArray[28]);
                    obj.put("LTV Ratio with FMV", itemsArray[29]);
                    obj.put("Credit Facility", itemsArray[30]);
                    

                    jsonArray_PODCtrlComp.clear();
                    jsonArray_PODCtrlComp.add(obj);
                    iFormObj.addDataToGrid("tblPODLoanControlCompliances", jsonArray_PODCtrlComp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_PODCtrlComp", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_ShareLoanCtrlComp

            JSONArray jsonArray_ShareLoanCtrlComp = new JSONArray();

            try {
                int count = 0;
                String query = "Select TypeOfShare,Purpose,OtherPurpose,TypeOfFacility,ShareIssuedBy,AgeOfBorrowerText,AgeOfBorrower,"
                        + "MinAgeBorrowerCompliance,AgeBorrowerTenureEnd,MaxAgeBorrowerCompliance,LoanLimit,MinimumLoanLimitCompliance,"
                        + "MaxLoanLimitCompliance,LTVRatio,LTVRatioCompliance,OwnsMoreThan1per,PledgeLessThan50per,NoObjectionLetter,"
                        + "TenureDate,MaxTenureCompliance,InterestPaidQuarterly,InterestPaidQuarterlyCompliance,InterestRate,InterestRateCompliance,"
                        + "LoanAdminFee,LoanAdminFeeCompliance,EarlyPaymentFee,CommitmentFee from CS_ShareLoanCtrlComp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Type of Share", itemsArray[0]);
                    obj.put("Purpose", itemsArray[1]);
                    obj.put("Other Purpose", itemsArray[2]);
                    obj.put("Type of Facility", itemsArray[3]);
                    obj.put("Share issued by", itemsArray[4]);
                    obj.put("Age of Borrower", itemsArray[5]);
                    obj.put("Borrower's Age", itemsArray[6]);
                    obj.put("Minimum Age of the Borrower Compliance", itemsArray[7]);
                    obj.put("Age of Borrower at the end of the Loan Tenure", itemsArray[8]);
                    obj.put("Maximum Age of the Borrower Compliance", itemsArray[9]);
                    obj.put("Loan Limit", itemsArray[10]);
                    obj.put("Minimum Loan Limit Compliance", itemsArray[11]);
                    obj.put("Maximum Loan Limit Compliance", itemsArray[12]);
                    obj.put("LTV Ratio", itemsArray[13]);
                    obj.put("LTV Ratio Compliance", itemsArray[14]);
                    obj.put("Owns more than 1% of the total promoter share", itemsArray[15]);
                    obj.put("Pledge of less than 50% of the share held", itemsArray[16]);
                    obj.put("No Objection Letter is obtained from Issuing Bank or its Register", itemsArray[17]);
                    obj.put("Tenure and Review", itemsArray[18]);
                    obj.put("Maximum Tenure Compliance", itemsArray[19]);
                    obj.put("Interest  to be paid quarterly", itemsArray[20]);
                    obj.put("Interest to be paid Quarterly Compliance", itemsArray[21]);
                    obj.put("Interest Rate (Base Rate +/-)", itemsArray[22]);
                    obj.put("Interest Rate Compliance", itemsArray[23]);
                    obj.put("Loan Administration Fee", itemsArray[24]);
                    obj.put("Loan Administration Fee Compliance", itemsArray[25]);
                    obj.put("Early Payment Fee", itemsArray[26]);
                    obj.put("Commitment Fee", itemsArray[27]);

                    jsonArray_ShareLoanCtrlComp.clear();
                    jsonArray_ShareLoanCtrlComp.add(obj);
                    iFormObj.addDataToGrid("tblShareLoanControlCompliances", jsonArray_ShareLoanCtrlComp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_ShareLoanCtrlComp", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_ODLNClassACtrlComp

            JSONArray jsonArray_ODLNClassACtrlComp = new JSONArray();

            try {
                int count = 0;
                String query = "Select Type,Currency,Purpose,TypeOfDeposit,AgeOfBorrowerText,AgeOfBorrower,MinAgeBorrowerCompliance,"
                        + "LTVRatio,LTVRatioCompliance,TenureDate,InterestPaidQuarterly,BuildingInsured,InterestRateCouponRate,"
                        + "InterestRateCouponRateCompliance,BuildingInsuredCompliance,InterestPaidQuarterlyCompliance,MaxTenureCompliance from CS_ODLNClassACtrlComp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Type", itemsArray[0]);
                    obj.put("Currency", itemsArray[1]);
                    obj.put("Purpose", itemsArray[2]);
                    obj.put("Type of Deposit", itemsArray[3]);
                    obj.put("Age of Borrower", itemsArray[4]);
                    obj.put("Borrower's Age", itemsArray[5]);
                    obj.put("Minimum Age of the Borrower Compliance", itemsArray[6]);
                    obj.put("LTV Ratio", itemsArray[7]);
                    obj.put("LTV Ratio Compliance", itemsArray[8]);
                    obj.put("Tenure and Review", itemsArray[9]);
                    obj.put("Interest  to be paid quarterly", itemsArray[10]);
                    obj.put("The building being mortgaged is insured against fire, earthquake,", itemsArray[11]);
                    obj.put("Interest Rate is > the coupon rate of the Deposit/Fixed Deposit", itemsArray[12]);
                    obj.put("Interest Rate is > the coupon rate Compliance", itemsArray[13]);
                    obj.put("The building being mortgaged is insured Compliance", itemsArray[14]);
                    obj.put("Interest to be paid quarterly Compliance", itemsArray[15]);
                    obj.put("Maximum Tenure Compliance", itemsArray[16]);

                    jsonArray_ODLNClassACtrlComp.clear();
                    jsonArray_ODLNClassACtrlComp.add(obj);
                    iFormObj.addDataToGrid("tblClassAControlCompliances", jsonArray_ODLNClassACtrlComp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_ODLNClassACtrlComp", e);
            }
            // for Control & Compliance(TAB) 
            //for CS_ODLNClassBCtrlComp

            JSONArray jsonArray_ODLNClassBCtrlComp = new JSONArray();

            try {
                int count = 0;
                String query = "Select Type,Instrument,Purpose,TypeOfSecurity,AgeOfBorrowerText,AgeOfBorrower,MinAgeOfBorrowerCompliance,"
                        + "LTVRatio,LTVRatioCompliance,TenureDate,InterestPaidMonthly,BuildingInsured,InterestRateCouponRate,OtherInstrument,"
                        + "InterestRateCouponRateCompliance,MaxTenureCompliance,InterestPaidMonthlyCompliance,BuildingInsuredCompliance from CS_ODLNClassBCtrlComp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Type", itemsArray[0]);
                    obj.put("Instrument", itemsArray[1]);
                    obj.put("Purpose", itemsArray[2]);
                    obj.put("Type of Security", itemsArray[3]);
                    obj.put("Age of Borrower", itemsArray[4]);
                    obj.put("Borrower's Age", itemsArray[5]);
                    obj.put("Minimum Age of the Borrower Compliance", itemsArray[6]);
                    obj.put("LTV Ratio", itemsArray[7]);
                    obj.put("LTV Ratio Compliance", itemsArray[8]);
                    obj.put("Tenure and Review", itemsArray[9]);
                    obj.put("Interest  to be paid monthly", itemsArray[10]);
                    obj.put("The building being mortgaged is insured against fire, earthquake,", itemsArray[11]);
                    obj.put("Interest Rate is > the coupon rate of the Deposit/Fixed Deposit", itemsArray[12]);
                    obj.put("Other Instrument", itemsArray[13]);
                    obj.put("Interest Rate is > the coupon rate Compliance", itemsArray[14]);
                    obj.put("Maximum Tenure Compliance", itemsArray[15]);
                    obj.put("Interest to be paid monthly Compliance", itemsArray[16]);
                    obj.put("The building being mortgaged is insured Compliance", itemsArray[17]);

                    jsonArray_ODLNClassBCtrlComp.clear();
                    jsonArray_ODLNClassBCtrlComp.add(obj);
                    iFormObj.addDataToGrid("tblClassBControlCompliances", jsonArray_ODLNClassBCtrlComp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_ODLNClassBCtrlComp", e);
            }
            // for Variations(TAB) 
            //for CS_SecurityVariationsRet

            JSONArray jsonArray_SecurityVariationsRet = new JSONArray();

            try {
                int count = 0;
                String query = "Select Particulars,TypeOfSecurity,VariationsObserved from CS_SecurityVariationsRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Particulars", itemsArray[0]);
                    obj.put("Type of Security", itemsArray[1]);
                    obj.put("Variations Observed", itemsArray[2]);

                    jsonArray_SecurityVariationsRet.clear();
                    jsonArray_SecurityVariationsRet.add(obj);
                    iFormObj.addDataToGrid("tblSecurityDetailsVariations", jsonArray_SecurityVariationsRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_SecurityVariationsRet", e);
            }
            // for Variations(TAB) 
            //for CS_VariationsRet

            JSONArray jsonArray_VariationsRet = new JSONArray();

            try {
                int count = 0;
                String query = "Select Facility,Particulars,Remarks,VariationsObserved from CS_VariationsRet with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Facility", itemsArray[0]);
                    obj.put("Particulars", itemsArray[1]);
                    obj.put("Remarks", itemsArray[2]);
                    obj.put("Variations Observed", itemsArray[3]);

                    jsonArray_VariationsRet.clear();
                    jsonArray_VariationsRet.add(obj);
                    iFormObj.addDataToGrid("tblVariationsControlRetail", jsonArray_VariationsRet);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_VariationsRet", e);
            }
            // for Approval Log (TAB) 
            // for CS_Comment

            JSONArray jsonArray_Comment = new JSONArray();

            try {
                int count = 0;
                String query = "Select Decision,C_Date,FromWorkstep,LoggedInUser,CurrentUser,Remarks,QueriedTo,SkipNextAuthority  from CS_Comment with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Decision", itemsArray[0]);
                    obj.put("Date and Time", itemsArray[1]);
                    obj.put("From Workstep", itemsArray[2]);
                    obj.put("Logged In User", itemsArray[3]);
                    obj.put("Current User", itemsArray[4]);
                    obj.put("Comment", itemsArray[5]);
                    obj.put("QueriedTo", itemsArray[6]);
                    obj.put("Skip Next Authority", itemsArray[7]);
                    

                    jsonArray_Comment.clear();
                    jsonArray_Comment.add(obj);
                    iFormObj.addDataToGrid("tblApprovalLogRetail", jsonArray_Comment);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_Comment", e);
            }
            //for Post Approval Log (TAB) 
            //for CS_PostApproval

           JSONArray jsonArray_PostApproval = new JSONArray();

            try {
                int count = 0;
                String query = "Select Decision,C_Date,FromWorkstep,NextWorkStep,LoggedInUser,CurrentUser,Remarks  from CS_PostApproval with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Decision", itemsArray[0]);
                    obj.put("Date and Time", itemsArray[1]);
                    obj.put("From Workstep", itemsArray[2]);
                    obj.put("Next Work Desk", itemsArray[3]);
                    obj.put("Logged In User", itemsArray[4]);
                    obj.put("Current User", itemsArray[5]);
                    obj.put("Comment", itemsArray[6]);

                    jsonArray_PostApproval.clear();
                    jsonArray_PostApproval.add(obj);
                    iFormObj.addDataToGrid("tblPostApprovalLogRetail", jsonArray_PostApproval);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_PostApproval", e);
            }
            
            // for Post Approval Summary (TAB) 
            // for CS_GuarantorDetailPostApproval

            JSONArray jsonArray_GuarantorDetailPostApproval = new JSONArray();

            try {
                int count = 0;
                String query = "Select ProvincePermtAdd,DistrictPermtAdd,MunicipalityVDCPermtAdd,WardNoPermtAdd,ProvinceTempAdd,"
                        + "DistrictTempAdd,MunicipalityVDCTempAdd,WardNoTempAdd,RelationMedium from CS_GuarantorDetailPostApproval with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Province", itemsArray[0]);
                    obj.put("District", itemsArray[1]);
                    obj.put("Municipality or VDC", itemsArray[2]);
                    obj.put("Ward No", itemsArray[3]);
                    obj.put("Temporary Address", itemsArray[4]);
                    obj.put("Province1", itemsArray[5]);
                    obj.put("District1", itemsArray[6]);
                    obj.put("Municipality or VDC 1", itemsArray[7]);
                    obj.put("Ward No1", itemsArray[8]);
                    obj.put("Relation Medium", itemsArray[9]);
                    

                    jsonArray_GuarantorDetailPostApproval.clear();
                    jsonArray_GuarantorDetailPostApproval.add(obj);
                    iFormObj.addDataToGrid("table86", jsonArray_GuarantorDetailPostApproval);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_GuarantorDetailPostApproval", e);
            }
            // for Post Approval Summary (TAB) 
            // for CS_ShareholdersDetail

            JSONArray jsonArray_ShareholdersDetail = new JSONArray();

            try {
                int count = 0;
                String query = "Select Name,DateOfBirth,Gender,MaritalStatus,FatherName,MotherName,GrandFatherName,"
                        + "SpouseName,FatherInLawName,Nationality,PersonFromBackwardCommunity from CS_ShareholdersDetail with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Name", itemsArray[0]);
                    obj.put("Date of Birth", itemsArray[1]);
                    obj.put("Gender", itemsArray[2]);
                    obj.put("Marital Status", itemsArray[3]);
                    obj.put("Father Name", itemsArray[4]);
                    obj.put("Mother Name", itemsArray[5]);
                    obj.put("Grand Father Name", itemsArray[6]);
                    obj.put("Spouse Name", itemsArray[7]);
                    obj.put("Father in Law Name", itemsArray[8]);
                    obj.put("Nationality", itemsArray[9]);
                    obj.put("Person From Backward Community", itemsArray[10]);

                    jsonArray_ShareholdersDetail.clear();
                    jsonArray_ShareholdersDetail.add(obj);
                    iFormObj.addDataToGrid("table79", jsonArray_ShareholdersDetail);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_ShareholdersDetail", e);
            }
            // for Post Approval Summary(TAB) 
            // for CS_CntrlCompPostAppSummary

            JSONArray jsonArray_CntrlCompPostAppSummary = new JSONArray();

            try {
                int count = 0;
                String query = "Select LoanPurpose,DrawingPower,Costconstructionfirstphase,FirstInstallmentAmount,CostconstructionSecondPhase,SecondInstallmentAmount,"
                        + "CostfinalProjectCompletion,ThirdInstallmentAmount from CS_CntrlCompPostAppSummary with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Loan Purpose", itemsArray[0]);
                    obj.put("Drawing Power (% of Distress Value)", itemsArray[1]);
                    obj.put("Cost of construction in first phase", itemsArray[2]);
                    obj.put("First installment amount", itemsArray[3]);
                    obj.put("Cost of Construction in second phase", itemsArray[4]);
                    obj.put("Second Installment Amount", itemsArray[5]);
                    obj.put("Cost of final project completion", itemsArray[6]);
                    obj.put("Third installment amount", itemsArray[7]);

                    jsonArray_CntrlCompPostAppSummary.clear();
                    jsonArray_CntrlCompPostAppSummary.add(obj);
                    iFormObj.addDataToGrid("table90", jsonArray_CntrlCompPostAppSummary);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_CntrlCompPostAppSummary", e);
            }
            // for Post Approval Summary (TAB)
            // for CS_RetlCrdtFacilityPostApp
            JSONArray jsonArray_RetlCrdtFacilityPostApp = new JSONArray();

            try {
                int count = 0;
                String query = "Select FacilityType,TypesHousingloan,LoanAmount,InterestRateType,BaseRate,PremiumRate,"
                        + "InterestRate,LoanAdminFeeFigure,EMIAmountFigure,LoanPeriodsMonths,MoratoriumPeriod,Loanexpdate,LoanCommitmentFee,"
                        + "VendorName,VehicleName,StudentName,CountryName,NameOfEmbassy,CourseName,CollegeName,Exposure,TenureExpiryDatestr,"
                        + "REF_DESC,REF_DESC_copy,TypeofFacility,Frequency,Amount from CS_RetlCrdtFacilityPostApp with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Facility Type", itemsArray[0]);
                    obj.put("Types of Housing loan", itemsArray[1]);
                    obj.put("Loan amount", itemsArray[2]);
                    obj.put("Interest Rate Type", itemsArray[3]);
                    obj.put("Base Rate", itemsArray[4]);
                    obj.put("Premium Rate", itemsArray[5]);
                    obj.put("Interest Rate", itemsArray[6]);
                    obj.put("Loan Admin Fee in Figure", itemsArray[7]);
                    obj.put("EMI Amount in Figure", itemsArray[8]);
                    obj.put("Loan Periods in Months", itemsArray[9]);
                    obj.put("Moratorium Period", itemsArray[10]);
                    obj.put("Loan expiry date", itemsArray[11]);
                    obj.put("Loan Commitment Fee", itemsArray[12]);
                    obj.put("Vendor Name", itemsArray[13]);
                    obj.put("Vehicle Name", itemsArray[14]);
                    obj.put("Student Name", itemsArray[15]);
                    obj.put("Country Name", itemsArray[16]);
                    obj.put("Name Of Embassy", itemsArray[17]);
                    obj.put("Course Name", itemsArray[18]);
                    obj.put("College Name", itemsArray[19]);
                    obj.put("Exposure LNM", itemsArray[20]);
                    obj.put("Tenure (Expiry Date) LNM", itemsArray[21]);
                    obj.put("REF_DESC LNM", itemsArray[22]);
                    obj.put("REF_DESC_copy LNM", itemsArray[23]);
                    obj.put("Type of Facility LNM", itemsArray[24]);
                    obj.put("Frequency LAO", itemsArray[25]);
                    obj.put("EMI LAO", itemsArray[26]);
                    

                    jsonArray_RetlCrdtFacilityPostApp.clear();
                    jsonArray_RetlCrdtFacilityPostApp.add(obj);
                    iFormObj.addDataToGrid("table87", jsonArray_RetlCrdtFacilityPostApp);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_RetlCrdtFacilityPostApp", e);
            }

            // for Loan Information Table (TAB) 
            // for CS_loanInfoTablepost
            JSONArray jsonArray_loanInfoTablepost = new JSONArray();

            try {
                int count = 0;
                String query = "Select Approved,ApprovedLimit,Remarks from CS_loanInfoTablepost with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Approved", itemsArray[0]);
                    obj.put("Approved Limit", itemsArray[1]);
                    obj.put("Remarks", itemsArray[2]);

                    jsonArray_loanInfoTablepost.clear();
                    jsonArray_loanInfoTablepost.add(obj);
                    iFormObj.addDataToGrid("table80", jsonArray_loanInfoTablepost);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_loanInfoTablepost", e);
            }
            // for Offering Sheet (TAB) 
            // for CS_OS_Limittree

            JSONArray jsonArray_OS_Limittree = new JSONArray();

            try {
                int count = 0;
                String query = "Select limit_suffix,limit_b2kid,parent_limitb2kid,limit_description,sanction_limit,drawing_power,liability,contingent_liability,"
                        + "number_of_accounts,currency_code,limit_sanction_date,limit_expiry_date,remarks,additional_request  from CS_OS_Limittree with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("LimitSuffix", itemsArray[0]);
                    obj.put("LimitB2kId", itemsArray[1]);
                    obj.put("ParentLimitB2kId", itemsArray[2]);
                    obj.put("LimitDescription", itemsArray[3]);
                    obj.put("SanctionLimit", itemsArray[4]);
                    obj.put("DrawingPower", itemsArray[5]);
                    obj.put("Liability", itemsArray[6]);
                    obj.put("Contingent Liability", itemsArray[7]);
                    obj.put("NumberOfAccounts", itemsArray[8]);
                    obj.put("CurrencyCode", itemsArray[9]);
                    obj.put("LimitSanctionedDate", itemsArray[10]);
                    obj.put("LimitExpiryDate", itemsArray[11]);
                    obj.put("Remarks", itemsArray[12]);
                    obj.put("AdditionalRequest", itemsArray[13]);

                    jsonArray_OS_Limittree.clear();
                    jsonArray_OS_Limittree.add(obj);
                    iFormObj.addDataToGrid("tblLimitTree", jsonArray_OS_Limittree);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_OS_Limittree", e);

            }
            //for Offering Sheet (TAB) 
            // for CS_OS_ListDetails

            JSONArray jsonArray_OS_ListDetails = new JSONArray();

            try {
                int count = 0;
                String query = "Select Limit_node,description,approved_limits,present_outstand,additional_req,total_add_req,excess_OS from CS_OS_ListDetails with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Limit Node", itemsArray[0]);
                    obj.put("Description", itemsArray[1]);
                    obj.put("Approved Limits", itemsArray[2]);
                    obj.put("Present Outstanding", itemsArray[3]);
                    obj.put("Additional Request", itemsArray[4]);
                    obj.put("Total Incl Additonal request", itemsArray[5]);
                    obj.put("Excess/Actual OS", itemsArray[6]);

                    jsonArray_OS_ListDetails.clear();
                    jsonArray_OS_ListDetails.add(obj);
                    iFormObj.addDataToGrid("tblLimitsDetails", jsonArray_OS_ListDetails);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_OS_ListDetails", e);
            }
            // for Offering Sheet (TAB) 
            // for CS_OS_UnitPassDues

            JSONArray jsonArray_OS_UnitPassDues = new JSONArray();

            try {
                int count = 0;
                String query = "Select unit_ac_no,unit_disb_no,unit_os_lcy,unit_overdue_since,unit_reason_for_overdue  from CS_OS_UnitPassDues with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("A/c No", itemsArray[0]);
                    obj.put("Disb Id/Bill No", itemsArray[1]);
                    obj.put("O/S LCY", itemsArray[2]);
                    obj.put("Overdue Since", itemsArray[3]);
                    obj.put("Reason For Overdue", itemsArray[4]);
                    

                    jsonArray_OS_UnitPassDues.clear();
                    jsonArray_OS_UnitPassDues.add(obj);
                    iFormObj.addDataToGrid("tblUnitPastDues", jsonArray_OS_UnitPassDues);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_OS_UnitPassDues", e);

            }
            // for Offering Sheet (TAB) 
            //for CS_OS_Funded

            JSONArray jsonArray_OS_Funded = new JSONArray();

            try {
                int count = 0;
                String query = "Select funded_customer_id,funded_id_type,funded_outstanding from CS_OS_Funded with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Customer ID", itemsArray[0]);
                    obj.put("ID Type", itemsArray[1]);
                    obj.put("Funding Outstanding", itemsArray[2]);

                    jsonArray_OS_Funded.clear();
                    jsonArray_OS_Funded.add(obj);
                    iFormObj.addDataToGrid("tblFunded", jsonArray_OS_Funded);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_OS_Funded", e);
            }
            // for Offering Sheet (TAB) 
            // for CS_OS_NONFunded

            JSONArray jsonArray_OS_NONFunded = new JSONArray();

            try {
                int count = 0;
                String query = "Select nonfunded_customer_id,nonfunded_idtype,nonfunded_outstanding from CS_OS_NONFunded with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Customer ID", itemsArray[0]);
                    obj.put("ID Type", itemsArray[1]);
                    obj.put("Non Funding Outstanding", itemsArray[2]);

                    jsonArray_OS_NONFunded.clear();
                    jsonArray_OS_NONFunded.add(obj);
                    iFormObj.addDataToGrid("tblNonFunded", jsonArray_OS_NONFunded);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_OS_NONFunded", e);

            }
            // for Offering Sheet (TAB) 
            // for CS_OS_TotalExpouse

            JSONArray jsonArray_OS_TotalExpouse = new JSONArray();

            try {
                int count = 0;
                String query = "Select exposure_group_limit,exposure_funded,exposure_nonfunded,exposure_total,exposure_excess,"
                        + "exposure_actutal_od,exposure_lien_amount,exposure_actutal_amount from CS_OS_TotalExpouse with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Total Group Limit", itemsArray[0]);
                    obj.put("Funded", itemsArray[1]);
                    obj.put("Non Fundedm", itemsArray[2]);
                    obj.put("Total", itemsArray[3]);
                    obj.put("Excess", itemsArray[4]);
                    obj.put("Actual OD", itemsArray[5]);
                    obj.put("Lien Amount", itemsArray[6]);
                    obj.put("Actual Limit", itemsArray[7]);
                    

                    jsonArray_OS_TotalExpouse.clear();
                    jsonArray_OS_TotalExpouse.add(obj);
                    iFormObj.addDataToGrid("tblTotalExposure", jsonArray_OS_TotalExpouse);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_OS_TotalExpouse", e);
            }
            // for Offering Sheet (TAB) 
            // for CS_OS_GroupPassDue

            JSONArray jsonArray_OS_GroupPassDue = new JSONArray();

            try {
                int count = 0;
                String query = "Select group_cust_id,group_acno,group_disb_id,group_os,group_overdue,group_accountsbu,group_reason from CS_OS_GroupPassDue with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Customer ID", itemsArray[0]);
                    obj.put("A/c No", itemsArray[1]);
                    obj.put("Disb Id/Bill No", itemsArray[2]);
                    obj.put("O/S LCY", itemsArray[3]);
                    obj.put("Overdue Since", itemsArray[4]);
                    obj.put("AccountSbu", itemsArray[5]);
                    obj.put("Reason for Overdue", itemsArray[6]);

                    jsonArray_OS_GroupPassDue.clear();
                    jsonArray_OS_GroupPassDue.add(obj);
                    iFormObj.addDataToGrid("tblGroupPastDues", jsonArray_OS_GroupPassDue);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_OS_GroupPassDue", e);
            }
            // for Offering Sheet (TAB) 
            // for CS_OS_GroupUnits

            JSONArray jsonArray_OS_GroupUnits = new JSONArray();

            try {
                int count = 0;
                String query = "Select group_unit_custid,group_unit_name,group_unit_custsbu,group_unit_custro from CS_OS_GroupUnits with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Cust ID", itemsArray[0]);
                    obj.put("Unit Name", itemsArray[1]);
                    obj.put("CustSbu", itemsArray[2]);
                    obj.put("CustRo", itemsArray[3]);

                    jsonArray_OS_GroupUnits.clear();
                    jsonArray_OS_GroupUnits.add(obj);
                    iFormObj.addDataToGrid("tblGroupUnits", jsonArray_OS_GroupUnits);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_OS_GroupUnits", e);
            }
            // for Limit Maintenance (TAB) 
            // for CS_LimitMaintanancePostAppro

            JSONArray jsonArray_LimitMaintanancePostAppro = new JSONArray();

            try {
                int count = 0;
                String query = "Select Prefix,Suffix,Discription,CCYLM,LimitType,LimitTypeID,CommittedLine,ParentLimitPrefix,ParentLimitSuffix,"
                        + "ApprovedLimit,DrawingPowerIndicator,GlobalLimit,BankSetID,CollateralValueEroded,DrawingPowerPcnt,DrawingPowerMarginRetained,"
                        + "LimitApprovedDate,LimitExpiryDate,ContractSignDate,LimitEffectiveDate,LimitExpiryExtendUpto,NoOfExtension,LimitReviewDate,"
                        + "LoanType,BaseUserMaintanLiability,EffectiveUserMaintLiability,MinimumCollateral,MiniCollateralValueBase,ApprovalAuthority,"
                        + "ApprovalLevel,LimitStatus,InterestTableCode,LimitApprovalReference,PatternofFunding,BusinessUnitLimit,Notes,ReasonCode,"
                        + "MasterLimitNode,DebitACFees,LimitActivationCondition,ProductCode,AvailibilityEndDate,Frequency,PremiumRate,EMI  from CS_LimitMaintanancePostAppro with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Prefix", itemsArray[0]);
                    obj.put("Suffix", itemsArray[1]);
                    obj.put("Description", itemsArray[2]);
                    obj.put("CCY", itemsArray[3]);
                    obj.put("Limit Type", itemsArray[4]);
                    obj.put("Limit Type ID", itemsArray[5]);
                    obj.put("Committed Line", itemsArray[6]);
                    obj.put("Parent Limit-Prefix", itemsArray[7]);
                    obj.put("Parent Limit-Suffux", itemsArray[8]);
                    obj.put("Approved Limit", itemsArray[9]);
                    obj.put("Drawing Power Indicator", itemsArray[10]);
                    obj.put("Global Limit", itemsArray[11]);
                    obj.put("Bank Set ID", itemsArray[12]);
                    obj.put("Collateral Value Eroded", itemsArray[13]);
                    obj.put("Drawing Power Pcnt.", itemsArray[14]);
                    obj.put("Drawing Power / Margin Retained", itemsArray[15]);
                    obj.put("Limit Approved Date", itemsArray[16]);
                    obj.put("Limit Expiry Date", itemsArray[17]);
                    obj.put("Contract Sign Date", itemsArray[18]);
                    obj.put("Limit Effective Date", itemsArray[19]);
                    obj.put("Limit Expiry Extend Upto", itemsArray[20]);
                    obj.put("No.of Extension", itemsArray[21]);
                    obj.put("Limit Review Date", itemsArray[22]);
                    obj.put("Loan Type", itemsArray[23]);
                    obj.put("Base User Maintanance Liability", itemsArray[24]);
                    obj.put("Effective User Maintanance Liability", itemsArray[25]);
                    obj.put("Minimum Collateral %", itemsArray[26]);
                    obj.put("Minimum Collateral Value Base", itemsArray[27]);
                    obj.put("Approval Authority", itemsArray[28]);
                    obj.put("Approval Level", itemsArray[29]);
                    obj.put("Limit Status", itemsArray[30]);
                    obj.put("Interest Table Code", itemsArray[31]);
                    obj.put("Limit Approval Reference", itemsArray[32]);
                    obj.put("Pattern of Funding", itemsArray[33]);
                    obj.put("Business Unit of Limit", itemsArray[34]);
                    obj.put("noNotes", itemsArray[35]);
                    obj.put("Reason Code", itemsArray[36]);
                    obj.put("Master Limit Node", itemsArray[37]);
                    obj.put("Debit a/c for Fees", itemsArray[38]);
                    obj.put("Limit activation condition precedent met", itemsArray[39]);
                    obj.put("Product Code", itemsArray[40]);
                    obj.put("Availibility End Date", itemsArray[41]);
                    obj.put("Frequency LAO", itemsArray[42]);
                    obj.put("Premium Rate LAO", itemsArray[43]);
                    obj.put("EMI LAO", itemsArray[44]);
                    

                    jsonArray_LimitMaintanancePostAppro.clear();
                    jsonArray_LimitMaintanancePostAppro.add(obj);
                    iFormObj.addDataToGrid("table93", jsonArray_LimitMaintanancePostAppro);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_LimitMaintanancePostAppro", e);

            }
            //for Loan Account Opening (TAB) 
            // for CS_LoanAccOpeningPostApproval

            JSONArray jsonArray_LoanAccOpeningPostApproval = new JSONArray();

            try {
                int count = 0;
                String query = "Select Loantype,functionOpen,CIFID,SOLID,CCY,Schemecode,GeneralLedger,AcPreferentialInterest,Baserate,LoanAmt,"
                        + "LoanPeriodMonths,RepayMethod,CreditFileNo,OperativeAcID,InstallmentStartDate,InterestStartDate,InstallmentFrequency,"
                        + "AccountLimits,SanctionDate,ExpiryDate,DocumentDate,ReviewDate,SanctionAuthority,SanctionLevel,DrawingPower,LimitID,"
                        + "MaxAllowedLimit,AcHealthCode,NextInterestCalculationDate,ServiceChargeAmount,ThirdpartyaccNumber,ThirdpartytransferAmt,"
                        + "Sectorcode,SubsectorCode,OccupationCode,borrowercategory,purposeadvance,modeadvance,advancetype,natureadvance,GuranteeCoverCode,"
                        + "FreeCode1,FreeCode2,FreeCode3,FreeCode4,FreeCode5,FreeCode6,FreeCode7,FreeCode8,FreeCode9,PendingDocument,"
                        + "FollowUpdate,Pendingdocexcuationdate,pendingappauth,InterestRateModule,SubsidiesProduct,LimitIDSuffix,Type,EMI,CurrentDate  from CS_LoanAccOpeningPostApproval with(nolock) where PID='" + PID + "'";

                LogMessages.statusLog.info("Query: " + query);
                List QueryList = iFormObj.getDataFromDB(query);
                ListIterator QueryListItr = QueryList.listIterator();
                while (QueryListItr.hasNext()) {
                    List rowData = (List) QueryListItr.next();
                    String[] itemsArray = new String[rowData.size()];
                    itemsArray = (String[]) rowData.toArray(itemsArray);
                    JSONObject obj = new JSONObject();
                    obj.put("Loan type", itemsArray[0]);
                    obj.put("Function", itemsArray[1]);
                    obj.put("CIF ID", itemsArray[2]);
                    obj.put("SOL ID", itemsArray[3]);
                    obj.put("CCY", itemsArray[4]);
                    obj.put("Scheme Code", itemsArray[5]);
                    obj.put("General Ledger Subhead Code", itemsArray[6]);
                    obj.put("A/c. Preferential Interest", itemsArray[7]);
                    obj.put("Base rate", itemsArray[8]);
                    obj.put("Loan Amount", itemsArray[9]);
                    obj.put("Loan Period (Months/Days)", itemsArray[10]);
                    obj.put("Repay Method", itemsArray[11]);
                    obj.put("Credit File No", itemsArray[12]);
                    obj.put("Operative A/c. ID", itemsArray[13]);
                    obj.put("Installment Start Date", itemsArray[14]);
                    obj.put("Interest Start Date", itemsArray[15]);
                    obj.put("Installment Frequency", itemsArray[16]);
                    obj.put("Account Limits", itemsArray[17]);
                    obj.put("Sanction date", itemsArray[18]);
                    obj.put("Expiry date", itemsArray[19]);
                    obj.put("Document date", itemsArray[20]);
                    obj.put("Review date", itemsArray[21]);
                    obj.put("Sanction Authority", itemsArray[22]);
                    obj.put("Sanction Level", itemsArray[23]);
                    obj.put("Drawing Power", itemsArray[24]);
                    obj.put("Limit ID Prefix", itemsArray[25]);
                    obj.put("Max. Allowed Limit", itemsArray[26]);
                    obj.put("A/c. Health Code", itemsArray[27]);
                    obj.put("Next Interest Calculation Date (Dr.)", itemsArray[28]);
                    obj.put("Service charge amount", itemsArray[29]);
                    obj.put("Third party account Number", itemsArray[30]);
                    obj.put("Third party transfer amount", itemsArray[31]);
                    obj.put("Sector code", itemsArray[32]);
                    obj.put("subsector code", itemsArray[33]);
                    obj.put("occupation code", itemsArray[34]);
                    obj.put("borrower category", itemsArray[35]);
                    obj.put("purpose of advance", itemsArray[36]);
                    obj.put("mode of advance", itemsArray[37]);
                    obj.put("advance type", itemsArray[38]);
                    obj.put("nature of advance", itemsArray[39]);
                    obj.put("Gurantee cover code", itemsArray[40]);
                    obj.put("Free code 1", itemsArray[41]);
                    obj.put("Free code 2", itemsArray[42]);
                    obj.put("Free code 3", itemsArray[43]);
                    obj.put("Free code 4", itemsArray[44]);
                    obj.put("Free code 5", itemsArray[45]);
                    obj.put("Free code 6", itemsArray[46]);
                    obj.put("Free code 7", itemsArray[47]);
                    obj.put("Free code 8", itemsArray[48]);
                    obj.put("Free code 9", itemsArray[49]);
                    obj.put("Pending document", itemsArray[50]);
                    obj.put("Follow up date", itemsArray[51]);
                    obj.put("Pending document exceuation date", itemsArray[52]);
                    obj.put("pending approval authority", itemsArray[53]);
                    obj.put("Interest rate module", itemsArray[54]);
                    obj.put("Subsidies product", itemsArray[55]);
                    obj.put("Limit ID Suffix", itemsArray[56]);
                    obj.put("Type", itemsArray[57]);
                    obj.put("EMI", itemsArray[58]);
                    obj.put("Current Date", itemsArray[59]);
                    

                    jsonArray_LoanAccOpeningPostApproval.clear();
                    jsonArray_LoanAccOpeningPostApproval.add(obj);
                    iFormObj.addDataToGrid("table91", jsonArray_LoanAccOpeningPostApproval);
                    LogMessages.statusLog.info("*** Data added to the grid ***");
                    count++;
                }
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception found CS_LoanAccOpeningPostApproval", e);

            }*/
              
        
        return retail_review_renew_data_array;
    }    }

