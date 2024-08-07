package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

import static com.newgen.iforms.util.NewGenUtilConstants.*;

/**
 * @author Shivaraj Mahato
 */
public class RetailAllDataFetch {

    private RetailAllDataFetch() {

    }

    public static String customDate(String para) {
        return para;
    }

    public static JSONArray retailAllDataFetch(IFormReference iFormObj) {
        String applicationNo = iFormObj.getValue("BuildingDetails").toString();
        String pid = applicationNo;
        LogMessages.statusLog.info("Applicationno: " + applicationNo);
        // fetching all review renew data
        JSONArray retailReviewRenewDataArray = new JSONArray();

        // fetching all retail textbox data
        fetchRetailTextBoxData(iFormObj, applicationNo, retailReviewRenewDataArray);

        //fetching all comboobx data of retail
        fetchRetailComboBoxData(iFormObj, applicationNo, retailReviewRenewDataArray);

        //fetch all date field of retail
        fetchRetailDateData(iFormObj, applicationNo, retailReviewRenewDataArray);

        //fetching all textarea data of retail
        fetchRetailTextareaData(iFormObj, applicationNo, retailReviewRenewDataArray);

        //fetching all checkbox data of Retail
        fetchRetailCheckboxData(iFormObj, applicationNo, retailReviewRenewDataArray);
        // grid for KYC (TAB) */
        //for CS_ShareholdingDetailsRet
        fetchRetailShareholdingDetailsData(iFormObj, pid);
        //for CS_KeyPersonsDetailsRet
        fetchRetailKeyPersonsDetailsData(iFormObj, pid);
        //for CS_KeyPersonCitizenDetailsRet
        fetchRetailKeyPersonCitizenDetailsData(iFormObj, pid);

        /*for Loan Facilities (TAB) */
        // for CS_FacilitiesRetail

        fetchRetailFacilitiesData(iFormObj, pid);

        fetchRetailRealEstatePropertyData(iFormObj, pid);
        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_AutomobileRet

        fetchRetailAutomobileData(iFormObj, pid);
        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_InventoriesAccReceivableRet

        fetchRetailInventoriesAccReceivableData(iFormObj, pid);

        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_FixedOrBankDeposirRet
        fetchRetailFixedOrBankDepositData(iFormObj, pid);

        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_GovermentSecuritiesRet
        fetchRetailGovernmentSecuritiesData(iFormObj, pid);

        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_CorporateBondRet
        fetchRetailCorporateBondData(iFormObj, pid);

        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_ListedSecuritiesRet

        fetchRetailListedSecuritiesData(iFormObj, pid);

        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_SharesRet
        fetchRetailSharesData(iFormObj, pid);
        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_GoldValuableSecuritiesRet

        fetchRetailGoldValuableSecuritiesData(iFormObj, pid);
        /* for Grid in  Security Details & Documents (TAB) */
        //for CS_GuaranteeRet

        fetchRetailGuaranteeData(iFormObj, pid);
        /* for Grid in  Group Sheet (TAB) */
        //for CS_ConnectionOwnership

        fetchRetailConnectionOwnershipData(iFormObj, pid);
        /*for Group Sheet(TAB)*/
        //for CS_Exposure

        fetchRetailExposureData(iFormObj, pid);

        /* for Group Sheet(TAB) */
        //for CS_GroupFacilitiesSME
        fetchRetailGroupFacilitiesSMEData(iFormObj, pid);

        return retailReviewRenewDataArray;
    }

    private static void fetchRetailGroupFacilitiesSMEData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayGroupFacilitiesSME = new JSONArray();
        try {
            String facilitiesSMEQuery = "Select unitNameFac,shortTermLoan,limitShortTermLoan,outstandingShortTermLoan,pricingShortTermLoan,Remarks,OthercreditFacility  from CS_GroupFacilitiesSME with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + facilitiesSMEQuery);
            List<List<String>> queryList = iFormObj.getDataFromDB(facilitiesSMEQuery);

            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put("Unit Name", rowData.get(0));
                obj.put("Facility", rowData.get(1));
                obj.put("Short Term Loan limit", rowData.get(2));
                obj.put("Outstanding1", rowData.get(3));
                obj.put("Pricing1", rowData.get(4));
                obj.put(REMARKS, rowData.get(5));
                obj.put("Other Credit Facility", rowData.get(6));


                jsonArrayGroupFacilitiesSME.clear();
                jsonArrayGroupFacilitiesSME.add(obj);
                iFormObj.addDataToGrid("tblGroupFacilitiesRetail", jsonArrayGroupFacilitiesSME);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_GroupFacilitiesSME", e);
        }
    }

    private static void fetchRetailExposureData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayExposure = new JSONArray();

        try {
            String retailExposureQuery = "Select unitName,A,B,C,D,E,F,G,H,I,Total from CS_Exposure with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + retailExposureQuery);
            List<List<String>> queryList = iFormObj.getDataFromDB(retailExposureQuery);

            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put("Unit Name ", rowData.get(0));
                obj.put("A", rowData.get(1));
                obj.put("B", rowData.get(2));
                obj.put("C", rowData.get(3));
                obj.put("D", rowData.get(4));
                obj.put("E", rowData.get(5));
                obj.put("F", rowData.get(6));
                obj.put("G", rowData.get(7));
                obj.put("H", rowData.get(8));
                obj.put("I", rowData.get(9));
                obj.put("Total", rowData.get(10));


                jsonArrayExposure.clear();
                jsonArrayExposure.add(obj);
                iFormObj.addDataToGrid("tblExposureRetail", jsonArrayExposure);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_Exposure", e);
        }
    }

    private static void fetchRetailConnectionOwnershipData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayConnectionOwnership = new JSONArray();

        try {
            String connectionOwnershipQuery = "Select unitName,Incorporation,Remarks,ShareholdersNum,ShareholderName1,ShareholderName2,ShareholderName3,ShareholderName4,"
                    + "ShareholderName5,ShareholderName6,ShareholderName7,ShareholderName8,ShareholderName9,ShareholderName10,ShareholderName11,ShareholderName12,"
                    + "ShareholderName13,ShareholderName14,ShareholderName15,ShareholderName16,ShareholderName17,ShareholderName18,ShareholderName19,"
                    + "ShareholderName20,HoldingPercentage1,HoldingPercentage2,HoldingPercentage3,HoldingPercentage4,HoldingPercentage5,HoldingPercentage6,"
                    + "HoldingPercentage7,HoldingPercentage8,HoldingPercentage9,HoldingPercentage10,HoldingPercentage11,HoldingPercentage12,HoldingPercentage13,"
                    + "HoldingPercentage14,HoldingPercentage15,HoldingPercentage16,HoldingPercentage17,HoldingPercentage18,HoldingPercentage19,HoldingPercentage20,ownership  from CS_ConnectionOwnership with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + connectionOwnershipQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(connectionOwnershipQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put("Individual/Unit Name", rowData.get(0));
                obj.put("Incorporation", rowData.get(1));
                obj.put(REMARKS, rowData.get(2));
                obj.put("Number of Shareholders", rowData.get(3));
                obj.put("First Shareholder Name", rowData.get(4));
                obj.put("Second Shareholder Name", rowData.get(5));
                obj.put("Third Shareholder Name", rowData.get(6));
                obj.put("Fourth Shareholder Name", rowData.get(7));
                obj.put("Fifth Shareholder Name", rowData.get(8));
                obj.put("Sixth Shareholder Name", rowData.get(9));
                obj.put("Seventh Shareholder Name", rowData.get(10));
                obj.put("Eighth Shareholder Name", rowData.get(11));
                obj.put("Ninth Shareholder Name", rowData.get(12));
                obj.put("Tenth Shareholder Name", rowData.get(13));
                obj.put("Eleventh Shareholder Name", rowData.get(14));
                obj.put("Twelfth Shareholder Name", rowData.get(15));
                obj.put("Thirteen Shareholder Name", rowData.get(16));
                obj.put("Fourteen Shareholder Name", rowData.get(17));
                obj.put("Fifteenth Shareholder Name", rowData.get(18));
                obj.put("Sixteenth Shareholder Name", rowData.get(19));
                obj.put("Seventeen Shareholder Name", rowData.get(20));
                obj.put("Eighteenth Shareholder Name", rowData.get(21));
                obj.put("Nineeten Shareholder Name", rowData.get(22));
                obj.put("Twenth Shareholder Name", rowData.get(23));
                obj.put("First Shareholding percentage", rowData.get(24));
                obj.put("Second Shareholding percentage", rowData.get(25));
                obj.put("Third Shareholding percentage", rowData.get(26));
                obj.put("Forth Shareholding percentage", rowData.get(27));
                obj.put("Fifth Shareholding percentage", rowData.get(28));
                obj.put("sixth Shareholding percentage", rowData.get(29));
                obj.put("Seventh Shareholding percentage", rowData.get(30));
                obj.put("Eighth Shareholding percentage", rowData.get(31));
                obj.put("Ninth Shareholding percentage", rowData.get(32));
                obj.put("Tenth Sharholding percentage", rowData.get(33));
                obj.put("Eleventh Shareholding percentage", rowData.get(34));
                obj.put("twelfth Shareholding percentage", rowData.get(35));
                obj.put("Thirteen Shareholding percentage", rowData.get(36));
                obj.put("Fourteen Shareholding percentage", rowData.get(37));
                obj.put("Fifteenth Shareholding percentage", rowData.get(38));
                obj.put("Sixteenth Shareholding percentage", rowData.get(39));
                obj.put("Seventeenth Shareholding percentage", rowData.get(40));
                obj.put("Eighteenth Shareholding percentage", rowData.get(41));
                obj.put("Ninenth Shareholding percentage", rowData.get(42));
                obj.put("Twenth Shareholding percentage", rowData.get(43));
                obj.put("Owership", rowData.get(44));


                jsonArrayConnectionOwnership.clear();
                jsonArrayConnectionOwnership.add(obj);
                iFormObj.addDataToGrid("tblConnectionOwnershipRetail", jsonArrayConnectionOwnership);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_ConnectionOwnership", e);
        }
    }

    private static void fetchRetailGuaranteeData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayGuaranteeRet = new JSONArray();
        try {
            String guaranteeQuery = "Select Required,Type,Relation,OtherRelation,NameOfGuarantor,Gender,DOB,DOBBS,Age,GuaranteeStatus,DateOfExecution,Amount,Expiry,"
                    + "Networth,CitizenshipNo,CitizenshipIssuedDate,CitizenshipIssuedDateBS,CitizenshipIssuedDistrict,PassportNo,PassportIssuedDate,PassportExpiryDate,PassportIssuedPlace,"
                    + "FathersName,MothersName,GrandFathersName,SpouseName  from CS_GuaranteeRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + guaranteeQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(guaranteeQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put("Required", rowData.get(0));
                obj.put("Type", rowData.get(1));
                obj.put("Relation", rowData.get(2));
                obj.put("Specify Other Relation", rowData.get(3));
                obj.put("Name of the Guarantor", rowData.get(4));
                obj.put("Gender", rowData.get(5));
                obj.put("Date of Birth", rowData.get(6));
                obj.put("Date of Birth (BS)", rowData.get(7));
                obj.put("Age", rowData.get(8));
                obj.put("Status", rowData.get(9));
                obj.put("Date of Execution", rowData.get(10));
                obj.put("Amount", rowData.get(11));
                obj.put("Expiry", rowData.get(12));
                obj.put("Networth of the Guarantor", rowData.get(13));
                obj.put("Citizenship Number", rowData.get(14));
                obj.put("Citizenship Issued Date", rowData.get(15));
                obj.put("Citizenship Issued Date (BS)", rowData.get(16));
                obj.put("Citizenship Issued District", rowData.get(17));
                obj.put("Passport Number", rowData.get(18));
                obj.put("Passport Issued Date", rowData.get(19));
                obj.put("Passport Expiry Date", rowData.get(20));
                obj.put("Passport Issued Place", rowData.get(21));
                obj.put("Father's Name", rowData.get(22));
                obj.put("Mother's Name", rowData.get(23));
                obj.put("Grand Father's Name", rowData.get(24));
                obj.put("Spouse Name", rowData.get(25));


                jsonArrayGuaranteeRet.clear();
                jsonArrayGuaranteeRet.add(obj);
                iFormObj.addDataToGrid("tblGuaranteeRetail", jsonArrayGuaranteeRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_GuaranteeRet", e);
        }
    }

    private static void fetchRetailGoldValuableSecuritiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayGoldValuableSecuritiesRet = new JSONArray();
        try {
            String goldValuableSecuritiesQuery = "Select NatureOfCharge,Type,OtherType,Weight,StatusOfBankLienCharge,LatestDateexecution,PledgeAgreement,SecurityType,"
                    + "SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_GoldValuableSecuritiesRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + goldValuableSecuritiesQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(goldValuableSecuritiesQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put(NATURE_OF_CHARGE, rowData.get(0));
                obj.put("Type", rowData.get(1));
                obj.put("Other Type", rowData.get(2));
                obj.put("Weight", rowData.get(3));
                obj.put(STATUS_OF_BANK_S_LIEN_OR_PLEDGE_CHARGE, rowData.get(4));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(5));
                obj.put("Pledge Agreement", rowData.get(6));
                obj.put(SECURITY_TYPE, rowData.get(7));
                obj.put(SUB_TYPE_SECURITY, rowData.get(8));
                obj.put(DESCRIPTION, rowData.get(9));
                obj.put(SECURITY_CODE, rowData.get(10));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(11));

                jsonArrayGoldValuableSecuritiesRet.clear();
                jsonArrayGoldValuableSecuritiesRet.add(obj);
                iFormObj.addDataToGrid("tblValuablesSecurity", jsonArrayGoldValuableSecuritiesRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }

        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_GoldValuableSecuritiesRet", e);
        }
    }

    private static void fetchRetailSharesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArraySharesRet = new JSONArray();
        try {
            String sharesQuery = "Select TypeOfShare,ShareIssuedBy,LatestMarketPrice,AvgPrice,TotalValue,Margin,BorrowingPower  from CS_SharesRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + sharesQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(sharesQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put("Type of Share", rowData.get(0));
                obj.put("Share issued by", rowData.get(1));
                obj.put("Latest Market Price", rowData.get(2));
                obj.put("Average Price (Last 180 days)", rowData.get(3));
                obj.put("Total Value (Considered for Borrowing Power)", rowData.get(4));
                obj.put("Margin", rowData.get(5));
                obj.put("Borrowing Power (Net of Margin)", rowData.get(6));

                jsonArraySharesRet.clear();
                jsonArraySharesRet.add(obj);
                iFormObj.addDataToGrid("tblSharesRetail", jsonArraySharesRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_SharesRet", e);
        }
    }

    private static void fetchRetailListedSecuritiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayListedSecuritiesRet = new JSONArray();

        try {
            String query = "Select NatureOfCharge,IssuingCompanyName,InstrumentNo,StatusBankLienCharge,LatestDateExecution,Manjurinama,"
                    + "Annexure,PledgeConfirmation,DebitInstructionSlip,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_ListedSecuritiesRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + query);
            List<List<String>> queryList = iFormObj.getDataFromDB(query);

            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put(NATURE_OF_CHARGE, rowData.get(0));
                obj.put("Name of Issuing Company", rowData.get(1));
                obj.put(INSTRUMENT_NUMBER, rowData.get(2));
                obj.put(STATUS_OF_BANK_S_LIEN_OR_PLEDGE_CHARGE, rowData.get(3));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(4));
                obj.put("Manjurinama", rowData.get(5));
                obj.put("Annexure 18 and 19", rowData.get(6));
                obj.put("Pledge Confirmation", rowData.get(7));
                obj.put("Debit Instruction Slip", rowData.get(8));
                obj.put(SECURITY_TYPE, rowData.get(9));
                obj.put(SUB_TYPE_SECURITY, rowData.get(10));
                obj.put(DESCRIPTION, rowData.get(11));
                obj.put(SECURITY_CODE, rowData.get(12));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(13));


                jsonArrayListedSecuritiesRet.clear();
                jsonArrayListedSecuritiesRet.add(obj);
                iFormObj.addDataToGrid("tblListedSecurity", jsonArrayListedSecuritiesRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_ListedSecuritiesRet", e);
        }
    }

    private static void fetchRetailCorporateBondData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayCorporateBondRet = new JSONArray();

        try {
            String corporateBondQuery = "Select NatureOfCharge,IssuingCompanyName,InstrumentNo,MaturityDate,StatusBankLienCharge,LatestDateExecution,ValueCorpBond,"
                    + "OriginalInstrument,LetterOfPledge,ConfirmationFromIssuingCompany,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_CorporateBondRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + corporateBondQuery);
            List<List<String>> queryList = iFormObj.getDataFromDB(corporateBondQuery);

            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put(NATURE_OF_CHARGE, rowData.get(0));
                obj.put("Name of the Issuing Company", rowData.get(1));
                obj.put(INSTRUMENT_NUMBER, rowData.get(2));
                obj.put(MATURITY_DATE, rowData.get(3));
                obj.put(STATUS_OF_BANK_S_LIEN_OR_PLEDGE_CHARGE, rowData.get(4));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(5));
                obj.put("Value of the Corporate Bond", rowData.get(6));
                obj.put("Original Instrument with Bank Endorsement", rowData.get(7));
                obj.put("Letter of Pledge / Lien Charge over the Security", rowData.get(8));
                obj.put("Confirmation from issuing Company/CDS with regard to Bank's Charg", rowData.get(9));
                obj.put(SECURITY_TYPE, rowData.get(10));
                obj.put(SUB_TYPE_SECURITY, rowData.get(11));
                obj.put(DESCRIPTION, rowData.get(12));
                obj.put(SECURITY_CODE, rowData.get(13));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(14));

                jsonArrayCorporateBondRet.clear();
                jsonArrayCorporateBondRet.add(obj);
                iFormObj.addDataToGrid("tblCorpBondsSecurity", jsonArrayCorporateBondRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_CorporateBondRet", e);
        }
    }

    private static void fetchRetailGovernmentSecuritiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayGovernmentSecuritiesRet = new JSONArray();

        try {
            String governmentSecuritiesQuery = "Select NatureOfCharge,Instrument,TypeOfSecurity,InstrumentNo,MaturityDate,StatusBankLienCharge,LatestDateExecution,"
                    + "ValueGovSecurities,OriginalInstrument,LetterOfPledge,ConfirmationFromNRB,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_GovermentSecuritiesRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + governmentSecuritiesQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(governmentSecuritiesQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put(NATURE_OF_CHARGE, rowData.get(0));
                obj.put("Instrument", rowData.get(1));
                obj.put("Type of Security", rowData.get(2));
                obj.put(INSTRUMENT_NUMBER, rowData.get(3));
                obj.put(MATURITY_DATE, rowData.get(4));
                obj.put(STATUS_OF_BANK_S_LIEN_OR_PLEDGE_CHARGE, rowData.get(5));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(6));
                obj.put("Value of the Government Securities", rowData.get(7));
                obj.put("Original Instrument with Blank Endorsement", rowData.get(8));
                obj.put("Letter of Pledge / Lien Charge over the Security", rowData.get(9));
                obj.put("Confirmation from NRB with regard to the Bank's Charge", rowData.get(10));
                obj.put(SECURITY_TYPE, rowData.get(11));
                obj.put(SUB_TYPE_SECURITY, rowData.get(12));
                obj.put(DESCRIPTION, rowData.get(13));
                obj.put(SECURITY_CODE, rowData.get(14));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(15));

                jsonArrayGovernmentSecuritiesRet.clear();
                jsonArrayGovernmentSecuritiesRet.add(obj);
                iFormObj.addDataToGrid("tblGovSecurity", jsonArrayGovernmentSecuritiesRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_GovermentSecuritiesRet", e);
        }
    }

    private static void fetchRetailFixedOrBankDepositData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayFixedOrBankDepositRet = new JSONArray();

        try {
            String fixedOrBankDepositQuery = "Select NatureOfCharge,TypeOfDeposit,Currency,AccountNo,MaturityDate,StatusBankLienCharge,LatestDateExecution,ValueOfFixedDepositCharge,"
                    + "FixedDepositReceipt,LienChargeOverDeposit,SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge  from CS_FixedOrBankDeposirRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + fixedOrBankDepositQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(fixedOrBankDepositQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put(NATURE_OF_CHARGE, rowData.get(0));
                obj.put("Type of Deposit", rowData.get(1));
                obj.put("Currency", rowData.get(2));
                obj.put("Fixed Deposit or Bank Deposit Account Number", rowData.get(3));
                obj.put(MATURITY_DATE, rowData.get(4));
                obj.put("Status of Bank's Lien Charge", rowData.get(5));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(6));
                obj.put("Value of the Fixed Deposit/Charge over Deposit", rowData.get(7));
                obj.put("Fixed Deposit Receipt", rowData.get(8));
                obj.put("Lien Charge over the Deposit", rowData.get(9));
                obj.put(SECURITY_TYPE, rowData.get(10));
                obj.put(SUB_TYPE_SECURITY, rowData.get(11));
                obj.put(DESCRIPTION, rowData.get(12));
                obj.put(SECURITY_CODE, rowData.get(13));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(14));

                jsonArrayFixedOrBankDepositRet.clear();
                jsonArrayFixedOrBankDepositRet.add(obj);
                iFormObj.addDataToGrid("tblFixedDepositSecurity", jsonArrayFixedOrBankDepositRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_FixedOrBankDeposirRet", e);
        }
    }

    private static void fetchRetailInventoriesAccReceivableData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayInventoriesAccReceivableRet = new JSONArray();

        try {
            String inventoriesAccReceivableQuery = "Select NatureOfCharge,TypeOfAssets,StatusHypothecationCharge,LatestDateExecution,BasisOfValuation,DateLastStmt,EstimatedFMV,"
                    + "EstimatedDV,NameInspectingOfficial,DesignationInspectingOfficial,TotalInventory,TotalReceivable,OtherCurrentAsset,TotalPayables,"
                    + "NetTradingAssets,BusinessInOperation,BusinessOperationSatisfactory,RegDocPanRenewed,LeaseAgreement,RequisitesForSmoothOperation,"
                    + "AmountInventoryAdequate,QualityInventorySatisfactory,FirefightingMeasures,SecurityArrangements,NoLabourIssues,KeyPersonContacted,"
                    + "NoNegativeNews,InsurancePolicy,AssignmentOfBills,PowerOfAttorney,GeneralLetterHypo,SupplementaryAgreement,PledgeAgreement,RegOfCharge,"
                    + "SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge  from CS_InventoriesAccReceivableRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + inventoriesAccReceivableQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(inventoriesAccReceivableQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put(NATURE_OF_CHARGE, rowData.get(0));
                obj.put("Type of Assets", rowData.get(1));
                obj.put("Status of Bank's Hypothecation Charge", rowData.get(2));
                obj.put("Latest Date of Execution/Registration of Charge (STR)", rowData.get(3));
                obj.put(BASIS_OF_VALUATION, rowData.get(4));
                obj.put("Date of Last Statement of Net Trading Assets", rowData.get(5));
                obj.put("Estimated Fair Market Value(Including completed portion of build)", rowData.get(6));
                obj.put("Estimated Distress Value(Including completed portion of building)", rowData.get(7));
                obj.put("Name of the Inspecting Official/s", rowData.get(8));
                obj.put("Designation of the Inspecting Official/s", rowData.get(9));
                obj.put("Total Inventory", rowData.get(10));
                obj.put("Total Receivable within Approved Tenure", rowData.get(11));
                obj.put("Other Current Asset, if allowed for Drawing Power Assessment", rowData.get(12));
                obj.put("Total Payables/Creditors", rowData.get(13));
                obj.put("Net Trading Assets", rowData.get(14));
                obj.put("1. Business was in operation at the time of inspection", rowData.get(15));
                obj.put("2. Overall operation of the business was found to be satisfactory", rowData.get(16));
                obj.put("3. Registration document & PAN were renewed & displayed properly", rowData.get(17));
                obj.put("4. In case of rented premises, the lease agreement was reviewed", rowData.get(18));
                obj.put("5. Basic requisites for smooth operation of business/factory foun", rowData.get(19));
                obj.put("6. The amount of inventory & receivables was found to be adequate", rowData.get(20));
                obj.put("7. The quality of inventory & receivables was found to be satisfa", rowData.get(21));
                obj.put("8. Firefighting measures were in place", rowData.get(22));
                obj.put("9. Security arrangements were in place", rowData.get(23));
                obj.put("10. There were no labour issues apparent", rowData.get(24));
                obj.put("11. The key person of the borrower could be contacted", rowData.get(25));
                obj.put("12. There were no negative news regarding Borrower/Promoter/Direc", rowData.get(26));
                obj.put(INSURANCE_POLICY_WITH_PREMIUM_PAID_RECEIPT, rowData.get(27));
                obj.put("Assignment of bills and account receivables", rowData.get(28));
                obj.put("Power of attorney", rowData.get(29));
                obj.put("General letter of hypothecation", rowData.get(30));
                obj.put("Supplementary agreement", rowData.get(31));
                obj.put("Pledge agreement", rowData.get(32));
                obj.put("Registration of charge under secured transaction registry", rowData.get(33));
                obj.put(SECURITY_TYPE, rowData.get(34));
                obj.put(SUB_TYPE_SECURITY, rowData.get(35));
                obj.put(DESCRIPTION, rowData.get(36));
                obj.put(SECURITY_CODE, rowData.get(37));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(38));


                jsonArrayInventoriesAccReceivableRet.clear();
                jsonArrayInventoriesAccReceivableRet.add(obj);
                iFormObj.addDataToGrid("tblInventoryARMovableSecurity", jsonArrayInventoriesAccReceivableRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }

        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_InventoriesAccReceivableRet", e);
        }
    }

    private static void fetchRetailAutomobileData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayAutomobileRet = new JSONArray();

        try {
            String automobileQuery = "Select NatureOfCharge,VehicleType,Brand,ModelName,YearOfProduction,EngineNo,ChassisNo,RegistrationNo,StatusHypothecationCharge,LatestDateExecution,"
                    + "BasisOfValuation,ValuedBy,DateOfValuation,EstimatedFMV,EstimatedDV,PurchasePrice,QuotationInvoice,AllotmentLetter,"
                    + "VatBill,CopyOfBlueBook,InsurancePolicy,HirepurchaseAgreement,ThirdPartyOwnershipTransfer,SecurityType,SubSecurityType,"
                    + "SecurityDescription,SecurityCode,OtherNatureOfCharge from CS_AutomobileRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + automobileQuery);
            List<List<String>> queryList = iFormObj.getDataFromDB(automobileQuery);

            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put(NATURE_OF_CHARGE, rowData.get(0));
                obj.put("Vehicle Type", rowData.get(1));
                obj.put("Brand", rowData.get(2));
                obj.put("Model", rowData.get(3));
                obj.put("Year of Production", rowData.get(4));
                obj.put("Engine No.", rowData.get(5));
                obj.put("Chasis No.", rowData.get(6));
                obj.put("Registration Number", rowData.get(7));
                obj.put("Status of Bank's Hypothecation Charge", rowData.get(8));
                obj.put("Latest Date of Execution/Registration of Charge", rowData.get(9));
                obj.put(BASIS_OF_VALUATION, rowData.get(10));
                obj.put("Valued By", rowData.get(11));
                obj.put("Date of Valuation", rowData.get(12));
                obj.put("Estimated Fair Market Value", rowData.get(13));
                obj.put("Estimated Distress Value", rowData.get(14));
                obj.put(PURCHASE_PRICE, rowData.get(15));
                obj.put("Quotation/Proforma Invoice", rowData.get(16));
                obj.put("Allotment Letter", rowData.get(17));
                obj.put("VAT Bill / Tax Invoice", rowData.get(18));
                obj.put("Copy of Blue Book noting the Bank's Interest", rowData.get(19));
                obj.put(INSURANCE_POLICY_WITH_PREMIUM_PAID_RECEIPT, rowData.get(20));
                obj.put("Hire purchase agreement", rowData.get(21));
                obj.put("Third-party ownership transfer", rowData.get(22));
                obj.put(SECURITY_TYPE, rowData.get(23));
                obj.put(SUB_TYPE_SECURITY, rowData.get(24));
                obj.put(DESCRIPTION, rowData.get(25));
                obj.put(SECURITY_CODE, rowData.get(26));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(27));


                jsonArrayAutomobileRet.clear();
                jsonArrayAutomobileRet.add(obj);
                iFormObj.addDataToGrid("tblAutomobileSecurity", jsonArrayAutomobileRet);
                LogMessages.statusLog.info("*** Data added to the grid ......................***");
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_AutomobileRet", e);
        }
    }

    private static void fetchRetailRealEstatePropertyData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayRealEstatePropertyRet = new JSONArray();

        try {
            String realEstatePropertyQuery = "select NatureOfCharge,PropertyType,Owner,NewOwner,RelationToBorrower,PlotNo,Area,Municipality,District,"
                    + "OwnershipType,StatusMortgageCharge,LatestDateExecution,ApartmentName,AreaBuildingApartment,Storey,PlotNumber,StatusBankMortgageCharge,"
                    + "DateOfExecution,BasisOfValuation,ValuedBy,DateOfValuation,EstimatedFMV,EstimatedDV,PurchasePrice,DateOfInspection,NameInspectingOfficial,"
                    + "DesignationInspectingOfficial,DateOfTransfer,ModeOfTransfer,LandCategory,QualityAccessRoad,WidthRoad,RoadAccess,ShapeOfLand,DistanceFromBranch,"
                    + "BuildingCategory,ConstructionQuality,DateNirmanIjjajat,DateNirmanSampanna,Lalpurja,ValuationReport,FourBoundary,TraceMap,OwnershipTransferDoc,"
                    + "LandRevenueReceipt,RegMortgageDeed,InternalMortgageDeed,RokkaMalpot,RokkaGuthi,NirmanIjjajat,NirmanSampanna,CcitizenshipFirmCertificate,GharNaksa,"
                    + "InsurancePolicy,NoOfPlots,PlotNo2,PlotNo3,PlotNo4,PlotNo5,PlotNo6,PlotNo7,PlotNo8,PlotNo9,PlotNo10,Ropani,Aana,Paisa,Bigha,Kattha,Dhur,AreaLand,AreaPlot,"
                    + "AreaPlot2,AreaPlot3,AreaPlot4,AreaPlot5,AreaPlot6,AreaPlot7,AreaPlot8,AreaPlot9,AreaPlot10,TotalAreaPlot,LocationLOC,OtherBuildingCategory,OtherConstructionQuality,"
                    + "OtherRoadAccess,OtherLandCategory,OtherShapeOfLand,OtherModeOfTransfer,LandVerticallySloped,LandWithinRiverForest,LandWithinConservationArea,"
                    + "LandWithinDasgaja,LandHasPondPool,LandShapeConsistent,LandRowSetback,RiverCanalSetback,HighTensionSetback,OtherSetbacks,SetbacksAffectsLandShape,"
                    + "RemAreaSetbackAdjustment,SetbacksAffectsLandValue,SetbacksaffectsLandSalability,ValReportDeductions,NoStoreyConstructionApproval,BoundaryWall,"
                    + "WaterSupply,ElectricityConnection,DrainageConnection,BluePrintObtained,PropertyConsistentWithBluePrint,EnquiredWithFamily,ReviewedValuationReport,"
                    + "ConsideredFMV,ConsiderPropertyMortageable,BKDH,RAPD,PlotArea1,PlotArea2,PlotArea3,PlotArea4,PlotArea5,PlotArea6,PlotArea7,PlotArea8,PlotArea9,PlotArea10,"
                    + "SecurityType,SubSecurityType,SecurityDescription,SecurityCode,OtherNatureOfCharge,CollateralShared,Facilities,UtilizedFMV,UtilizedDV,Remarks,noofCollateral,"
                    + "Facilities2,UtilizedFMV2,UtilizedDV2,Remarks2,Facilities3,Facilities4,Facilities5,UtilizedFMV3,UtilizedFMV4,UtilizedFMV5,UtilizedDV3,UtilizedDV4,UtilizedDV5,"
                    + "Remarks3,Remarks4,Remarks5,BSVRID,SiteImages,Latitude,Longitude,CSVRRemarks,GoogleMap from CS_RealEstatePropertyRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + realEstatePropertyQuery);
            List<List<String>> queryList = iFormObj.getDataFromDB(realEstatePropertyQuery);

            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put(NATURE_OF_CHARGE, rowData.get(0));
                obj.put("Property Type", rowData.get(1));
                obj.put("Current Owner", rowData.get(2));
                obj.put("New Owner", rowData.get(3));
                obj.put("Relation to Borrower", rowData.get(4));
                obj.put("Plot Number (Land)", rowData.get(5));
                obj.put("Area (Land)", rowData.get(6));
                obj.put("Municipality", rowData.get(7));
                obj.put("District", rowData.get(8));
                obj.put("Ownership Type", rowData.get(9));
                obj.put("Status of Bank's Mortgage Charge (Registered or Unregistered)", rowData.get(10));
                obj.put("Latest Date of Execution/Registration of Charge", rowData.get(11));
                obj.put("Apartment Name", rowData.get(12));
                obj.put("Area of Building, Apartment (Existing and/or Proposed)", rowData.get(13));
                obj.put("Storey (Existing and/or Proposed)", rowData.get(14));
                obj.put("Plot Number (Constructed Area)", rowData.get(15));
                obj.put("Status of Bank's Mortgage Charge", rowData.get(16));
                obj.put("Latest Date of Execution / Registration of Charge", rowData.get(17));
                obj.put(BASIS_OF_VALUATION, rowData.get(18));
                obj.put("Valued By", rowData.get(19));
                obj.put("Date of Valuation", rowData.get(20));
                obj.put("Estimated Fair Market Value", rowData.get(21));
                obj.put("Estimated Distress Value", rowData.get(22));
                obj.put(PURCHASE_PRICE, rowData.get(23));
                obj.put("Date of Inspection/Site Visit", rowData.get(24));
                obj.put("Name of the Inspecting Official/s", rowData.get(25));
                obj.put("Designation of the Inspecting Official/s", rowData.get(26));
                obj.put("Date of Transfer of Title (Land)", rowData.get(27));
                obj.put("Mode of Transfer of Title (Land)", rowData.get(28));
                obj.put("Land Category (Utility)", rowData.get(29));
                obj.put("Quality of Access Road", rowData.get(30));
                obj.put("Width of the Road", rowData.get(31));
                obj.put("Road Access", rowData.get(32));
                obj.put("Shape of the Land", rowData.get(33));
                obj.put("Distance of the Location from the Branch in km", rowData.get(34));
                obj.put("Building Category (Utility)", rowData.get(35));
                obj.put("Construction Quality", rowData.get(36));
                obj.put("Date of permission for construction certificate / Nirman Ijjajat", rowData.get(37));
                obj.put("Date of building completion certificate / Nirman Sampanna", rowData.get(38));
                obj.put("Land Ownership Certificate / Lalpurja", rowData.get(39));
                obj.put("Valuation Report", rowData.get(40));
                obj.put("Four Boundary / Char Killa", rowData.get(41));
                obj.put("Trace Map / Blue Print", rowData.get(42));
                obj.put("Ownership Transfer Document (Rajinama, Bakas Patra, etc.)", rowData.get(43));
                obj.put("Land Revenue Report (Tiro)/Property Tax Paid Receipt", rowData.get(44));
                obj.put("Registered Mortgage Deed", rowData.get(45));
                obj.put("Internal Mortgage Deed (For Unregistered or Additional Charge)", rowData.get(46));
                obj.put("Rokka Letter from the Concerned Malpot Office", rowData.get(47));
                obj.put("Rokka Letter from the Concerned Guthi", rowData.get(48));
                obj.put("Permission for Construction Certificate / Nirman Ijjajat", rowData.get(49));
                obj.put("Building Completion Certificate / Nirman Sampanna", rowData.get(50));
                obj.put("Citizenship Certificate/Firm Registration Certificate of Owner", rowData.get(51));
                obj.put("Architect's design document of the building / Ghar Naksa", rowData.get(52));
                obj.put(INSURANCE_POLICY_WITH_PREMIUM_PAID_RECEIPT, rowData.get(53));
                obj.put("Number of Plots", rowData.get(54));
                obj.put("Plot Number (Land) 2", rowData.get(55));
                obj.put("Plot Number (Land) 3", rowData.get(56));
                obj.put("Plot Number (Land) 4", rowData.get(57));
                obj.put("Plot Number (Land) 5", rowData.get(58));
                obj.put("Plot Number (Land) 6", rowData.get(59));
                obj.put("Plot Number (Land) 7", rowData.get(60));
                obj.put("Plot Number (Land) 8", rowData.get(61));
                obj.put("Plot Number (Land) 9", rowData.get(62));
                obj.put("Plot Number (Land) 10", rowData.get(63));
                obj.put("Ropani", rowData.get(64));
                obj.put("Aana", rowData.get(65));
                obj.put("Paisa", rowData.get(66));
                obj.put("Bigha", rowData.get(67));
                obj.put("Kattha", rowData.get(68));
                obj.put("Dhur", rowData.get(69));
                obj.put("Total Area of Land in Square Meters", rowData.get(70));
                obj.put("Area of Plot", rowData.get(71));
                obj.put("Area of Plot 2", rowData.get(72));
                obj.put("Area of Plot 3", rowData.get(73));
                obj.put("Area of Plot 4", rowData.get(74));
                obj.put("Area of Plot 5", rowData.get(75));
                obj.put("Area of Plot 6", rowData.get(76));
                obj.put("Area of Plot 7", rowData.get(77));
                obj.put("Area of Plot 8", rowData.get(78));
                obj.put("Area of Plot 9", rowData.get(79));
                obj.put("Area of Plot 10", rowData.get(80));
                obj.put("Total Area of Plot", rowData.get(81));
                obj.put("Location as per LOC", rowData.get(82));
                obj.put("Building Category (Other)", rowData.get(83));
                obj.put("Construction Quality (Other)", rowData.get(84));
                obj.put("Quality of Access Road (Other)", rowData.get(85));
                obj.put("Land Category (Other)", rowData.get(86));
                obj.put("Shape of Land (Other)", rowData.get(87));
                obj.put("Mode of Transfer of Title (Other)", rowData.get(88));
                obj.put("1. The Land is not vertically sloped", rowData.get(89));
                obj.put("2. The land is not within 60 metersâ€™ distance from RIVER/FOREST/P", rowData.get(90));
                obj.put("3. The land is not within 100 metersâ€™ distance from CONSERVATION ", rowData.get(91));
                obj.put("4. The land is not within 500 metersâ€™ distance from NO MANâ€™S LAND", rowData.get(92));
                obj.put("5. The land doesnâ€™t have POND/POOL within valuation considered ar", rowData.get(93));
                obj.put("6. The location and shape of the land appear consistent with Topo", rowData.get(94));
                obj.put("7. The land does not have potential ROW setback", rowData.get(95));
                obj.put("8. There aren't any river/canal setback", rowData.get(96));
                obj.put("9. There aren't any high-tension wire setback", rowData.get(97));
                obj.put("10. There aren't any other setbacks", rowData.get(98));
                obj.put("11. The setbacks would not affect the shape of the land", rowData.get(99));
                obj.put("12. The remaining area of the land after the setback adjustments ", rowData.get(100));
                obj.put("13. The setbacks would not affect value of the land", rowData.get(101));
                obj.put("14. The setbacks would not affect salability of the land", rowData.get(102));
                obj.put("15. The valuation report has made appropriate deductions on accou", rowData.get(103));
                obj.put("16. No of storey matches with the construction approval", rowData.get(104));
                obj.put("17. Is there a boundary wall in the property?", rowData.get(105));
                obj.put("18. Is there water supply connection in the property?", rowData.get(106));
                obj.put("19. Is there electricity connection in the property?", rowData.get(107));
                obj.put("20. Is there a drainage connection in the property?", rowData.get(108));
                obj.put("21. Blue Print and Trace Map has been obtained by the Bank staff ", rowData.get(109));
                obj.put("22. To the best of my/our knowledge, the shape, area and size of ", rowData.get(110));
                obj.put("23. I/we have enquired with family member of collateral owner as ", rowData.get(111));
                obj.put("24. I/we have reviewed the valuation report which, I /we am/are s", rowData.get(112));
                obj.put("25. I/we consider the Fair Market Value of Preliminary/ Full Valu", rowData.get(113));
                obj.put("26. I/we consider the property is acceptable for mortgage to Bank", rowData.get(114));
                obj.put("Total B-K-D-H", rowData.get(115));
                obj.put("Total R-A-P-D", rowData.get(116));
                obj.put("Area Plot 1", rowData.get(117));
                obj.put("Area Plot 2", rowData.get(118));
                obj.put("Area Plot 3", rowData.get(119));
                obj.put("Area Plot 4", rowData.get(120));
                obj.put("Area Plot 5", rowData.get(121));
                obj.put("Area Plot 6", rowData.get(122));
                obj.put("Area Plot 7", rowData.get(123));
                obj.put("Area Plot 8", rowData.get(124));
                obj.put("Area Plot 9", rowData.get(125));
                obj.put("Area Plot 10", rowData.get(126));
                obj.put(SECURITY_TYPE, rowData.get(127));
                obj.put(SUB_TYPE_SECURITY, rowData.get(128));
                obj.put(DESCRIPTION, rowData.get(129));
                obj.put(SECURITY_CODE, rowData.get(130));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(131));
                obj.put("Is the Collateral Shared ?", rowData.get(132));
                obj.put("Facilities", rowData.get(133));
                obj.put("Utilized Fair Market Value(FMV)", rowData.get(134));
                obj.put("Utilized Distress Value(DV)", rowData.get(135));
                obj.put(REMARKS, rowData.get(136));
                obj.put("Number of Collateral Shared", rowData.get(137));
                obj.put("2. Facilities", rowData.get(138));
                obj.put("2. Utilized Fair Market Value(FMV)", rowData.get(139));
                obj.put("2. Utilized Distress Value(DV)", rowData.get(140));
                obj.put("2. Remarks", rowData.get(141));
                obj.put("3. Facilities", rowData.get(142));
                obj.put("4. Facilities", rowData.get(143));
                obj.put("5. Facilities", rowData.get(144));
                obj.put("3. Utilized Fair Market Value(FMV)", rowData.get(145));
                obj.put("4. Utilized Fair Market Value(FMV)", rowData.get(146));
                obj.put("5. Utilized Fair Market Value(FMV)", rowData.get(147));
                obj.put("3. Utilized Distress Value(DV)", rowData.get(148));
                obj.put("4. Utilized Distress Value(DV)", rowData.get(149));
                obj.put("5. Utilized Distress Value(DV)", rowData.get(150));
                obj.put("3. Remarks", rowData.get(151));
                obj.put("4. Remarks", rowData.get(152));
                obj.put("5. Remarks", rowData.get(153));
                obj.put("CSVR ID", rowData.get(154));
                obj.put("SiteImage", rowData.get(155));
                obj.put("Latitude", rowData.get(156));
                obj.put("Longitude", rowData.get(157));
                obj.put("CSVR Remarks", rowData.get(158));
                obj.put("Google Map", rowData.get(159));
                LogMessages.statusLog.info("*** CS_RealEstatePropertyRet position zero :: ***" + rowData.get(0));

                jsonArrayRealEstatePropertyRet.clear();
                jsonArrayRealEstatePropertyRet.add(obj);
                iFormObj.addDataToGrid("tblRealEstateSecurity", jsonArrayRealEstatePropertyRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_RealEstatePropertyRet", e);
        }
    }

    private static void fetchRetailFacilitiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayFacilitiesRetail = new JSONArray();

        try {
            String facilitiesQuery = "select ExistingOrNewFac,Exposure,TypeOfFacility,CreditFacility,Class,RiskRating,RORC,"
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
                    + "PremiumEnhancement,InterestRateEnhancement,REF_DESC,REF_DESC_copy from CS_FacilitiesRetail with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + facilitiesQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(facilitiesQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put("Facilities", rowData.get(0));
                obj.put("Exposure", rowData.get(1));
                obj.put("Type of Facility", rowData.get(2));
                obj.put("Credit Facility", rowData.get(3));
                obj.put("Class", rowData.get(4));
                obj.put("Short-Term Risk Rating", rowData.get(5));
                obj.put("RORC (Net)", rowData.get(6));
                obj.put("Limit", rowData.get(7));
                obj.put("Outstanding", rowData.get(8));
                obj.put("Overdue Principal (if any)", rowData.get(9));
                obj.put("Overdue Interest (if any)", rowData.get(10));
                obj.put("Repayment Installment Amount", rowData.get(11));
                obj.put("Tenure (Final Date of Repayment)", rowData.get(12));
                obj.put("Interest Rate (Base Rate +/-) (Percentage)", rowData.get(13));
                obj.put("Loan Administration Fee Existing (Percentage)", rowData.get(14));
                obj.put("Loan Administration Fee Enhancement (Percentage)", rowData.get(15));
                obj.put("Prepayment Fee (Percentage)", rowData.get(16));
                obj.put("Purpose", rowData.get(17));
                obj.put("Brand", rowData.get(18));
                obj.put("Model", rowData.get(19));
                obj.put("Year of Production", rowData.get(20));
                obj.put(PURCHASE_PRICE, rowData.get(21));
                obj.put("Vendor/Dealer Name", rowData.get(22));
                obj.put("Interest Rate (Base Rate +/-) (New Facility) (Percentage)", rowData.get(23));
                obj.put("Interest Amount", rowData.get(24));
                obj.put("Loan Administration Fee (New Facility) (Percentage)", rowData.get(25));
                obj.put("Prepayment Fee (New Facility)", rowData.get(26));
                obj.put("Location of Property", rowData.get(27));
                obj.put("Mode", rowData.get(28));
                obj.put("Fair Market Value", rowData.get(29));
                obj.put("Distress Value", rowData.get(30));
                obj.put("Name of the Student", rowData.get(31));
                obj.put("College Name", rowData.get(32));
                obj.put("Country", rowData.get(33));
                obj.put("Course Name", rowData.get(34));
                obj.put("Relationship with the Obligor", rowData.get(35));
                obj.put("Location of Property being Mortgaged", rowData.get(36));
                obj.put("Professional Valuation - Fair Market Value", rowData.get(37));
                obj.put("Professional Valuation - Distress Value", rowData.get(38));
                obj.put("Proposed Limit", rowData.get(39));
                obj.put("Tenure (Months from First Drawdown)", rowData.get(40));
                obj.put("Type of Installment", rowData.get(41));
                obj.put("Frequency", rowData.get(42));
                obj.put("First Installment Due (Days from First Drawdown)", rowData.get(43));
                obj.put("Amount", rowData.get(44));
                obj.put("Grace Period (Moratorium) - Days", rowData.get(45));
                obj.put("Type of Deposit", rowData.get(46));
                obj.put("Currency", rowData.get(47));
                obj.put("Total Amount", rowData.get(48));
                obj.put("Margin", rowData.get(49));
                obj.put("Amount (Net Of Margin)", rowData.get(50));
                obj.put("Instrument", rowData.get(51));
                obj.put("Type of Security", rowData.get(52));
                obj.put("Borrowing Power (Net of Margin)", rowData.get(53));
                obj.put("Type of Share", rowData.get(54));
                obj.put("Share issued by", rowData.get(55));
                obj.put("Number of Share", rowData.get(56));
                obj.put("Latest Market Price", rowData.get(57));
                obj.put("Average Price (Last 180 days)", rowData.get(58));
                obj.put("Total Value Considered for Borrowing Power", rowData.get(59));
                obj.put("Tenure (Expiry Date)", rowData.get(60));
                obj.put("Expiry", rowData.get(61));
                obj.put("Commitment Fee", rowData.get(62));
                obj.put("Increase Required?", rowData.get(63));
                obj.put("New Proposed Limit (After Increase)", rowData.get(64));
                obj.put("Base Rate (New Facility)", rowData.get(65));
                obj.put("Premium (New Facility)", rowData.get(66));
                obj.put("Tranche", rowData.get(67));
                obj.put("Drawdown Based On", rowData.get(68));
                obj.put("Percentage", rowData.get(69));
                obj.put(REMARKS, rowData.get(70));
                obj.put("BOID", rowData.get(71));
                obj.put("Base Rate", rowData.get(72));
                obj.put("Premium", rowData.get(73));
                obj.put("Long-Term Risk Rating", rowData.get(74));
                obj.put("Base Rate (Enhancement)", rowData.get(75));
                obj.put("Premium (Enhancement)", rowData.get(76));
                obj.put("Interest Rate (Base Rate +/-) (Enhancement) (Percentage)", rowData.get(77));
                obj.put("REF_DESC", rowData.get(78));
                obj.put("REF_DESC_copy", rowData.get(79));

                LogMessages.statusLog.info("*** CS_FacilitiesRetail position zero :: ***" + rowData.get(0));
                LogMessages.statusLog.info("*** CS_FacilitiesRetail position one :: ***" + rowData.get(1));

                jsonArrayFacilitiesRetail.clear();
                jsonArrayFacilitiesRetail.add(obj);
                iFormObj.addDataToGrid("tblFacilitiesRetail", jsonArrayFacilitiesRetail);
                LogMessages.statusLog.info("*** Data added to  the grid ***");
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_LoanAccOpeningPostApproval", e);

        }
    }

    private static void fetchRetailKeyPersonCitizenDetailsData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayKeyPersonCitizenDetailsRet = new JSONArray();
        try {
            String keyPersonCitizenDetailsQuery = "Select Name,CitizenshipNo,IssuedBy,IssuedDate,IssuedDateBS from CS_KeyPersonCitizenDetailsRet with(nolock) where PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + keyPersonCitizenDetailsQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(keyPersonCitizenDetailsQuery);
            for (List<String> rowData : queryList) {

                JSONObject obj = new JSONObject();
                obj.put("Name", rowData.get(0));
                obj.put("Citizenship Number", rowData.get(1));
                obj.put("Issued By (District)", rowData.get(2));
                obj.put("Issued Date", rowData.get(3));
                obj.put("Issued Date (BS)", rowData.get(4));

                jsonArrayKeyPersonCitizenDetailsRet.clear();
                jsonArrayKeyPersonCitizenDetailsRet.add(obj);
                iFormObj.addDataToGrid("tblCitizenshipDetailsProfitInst", jsonArrayKeyPersonCitizenDetailsRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_KeyPersonCitizenDetailsRet", e);
        }
    }

    private static void fetchRetailKeyPersonsDetailsData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayKeyPersonsDetailsRet = new JSONArray();
        try {
            String keyPersonsDetailsQuery = "select Name,StatusForProfitInst from CS_KeyPersonsDetailsRet with(nolock) where PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + keyPersonsDetailsQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(keyPersonsDetailsQuery);
            for (List<String> rowData : queryList) {
                JSONObject obj = new JSONObject();
                obj.put("Name", rowData.get(0));
                obj.put("Status", rowData.get(1));

                jsonArrayKeyPersonsDetailsRet.clear();
                jsonArrayKeyPersonsDetailsRet.add(obj);
                iFormObj.addDataToGrid("tblProprietorPartnerDirectorInfoRetail", jsonArrayKeyPersonsDetailsRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_KeyPersonCitizenDetailsRet", e);
        }
    }

    private static void fetchRetailShareholdingDetailsData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayShareholdingDetailsRet = new JSONArray();
        try {
            String retailShareholdingDetailsQuery = "select Name,Holding from CS_ShareholdingDetailsRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + retailShareholdingDetailsQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(retailShareholdingDetailsQuery);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Name", rowData.get(0));
                obj.put("Holding (%)", rowData.get(1));
                LogMessages.statusLog.info("*** CS_ShareholdingDetailsRet position zero :: ***" + rowData.get(0));
                LogMessages.statusLog.info("*** CS_ShareholdingDetailsRet position one :: ***" + rowData.get(1));

                jsonArrayShareholdingDetailsRet.clear();
                jsonArrayShareholdingDetailsRet.add(obj);
                iFormObj.addDataToGrid("tblShareholdingRetail", jsonArrayShareholdingDetailsRet);
            }

        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_ShareholdingDetailsRet", e);
        }
    }

    private static void fetchRetailCheckboxData(IFormReference iFormObj, String applicationNo, JSONArray retailReviewRenewDataArray) {
        try {
            String retailCheckboxQuery = "select SameAsPermAddress,Flag3,RealEstate,Automobile,Inventories,AccountReceivables,MovableAssets,FixedDeposit,BankDeposit,GovSecurities,CorpBonds,"
                    + "ListedSecurities,ValuableItems,OtherSecurity,AutoLoanCheckBox,HLCheckBox,MLCheckBox,EduLoanCheckBox,PLCCheckBox,PODCheckBox,ODLNCheckBox,ODGovCheckBox,ODLSCheckBox,OtherFacCheckBox,"
                    + "limittreecheckbox,disbursement from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";

            LogMessages.statusLog.info("Retail checkbox data fetch query: " + retailCheckboxQuery);
            List<List<String>> queryList = iFormObj.getDataFromDB(retailCheckboxQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of Retail checkbox data: " + rowData);

                JSONObject retailCheckboxData = new JSONObject();
                retailCheckboxData.put("SameAsPermAddress", rowData.get(0));
                retailCheckboxData.put("Flag3", rowData.get(1));
                retailCheckboxData.put("RealEstate", rowData.get(2));
                retailCheckboxData.put("Automobile", rowData.get(3));
                retailCheckboxData.put("Inventories", rowData.get(4));
                retailCheckboxData.put("AccountReceivables", rowData.get(5));
                retailCheckboxData.put("MovableAssets", rowData.get(6));
                retailCheckboxData.put("FixedDeposit", rowData.get(7));
                retailCheckboxData.put("BankDeposit", rowData.get(8));
                retailCheckboxData.put("GovSecurities", rowData.get(9));
                retailCheckboxData.put("CorpBonds", rowData.get(10));
                retailCheckboxData.put("ListedSecurities", rowData.get(11));
                retailCheckboxData.put("ValuableItems", rowData.get(12));
                retailCheckboxData.put("OtherSecurity", rowData.get(13));
                retailCheckboxData.put("AutoLoanCheckBox", rowData.get(14));
                retailCheckboxData.put("HLCheckBox", rowData.get(15));
                retailCheckboxData.put("MLCheckBox", rowData.get(16));
                retailCheckboxData.put("EduLoanCheckBox", rowData.get(17));
                retailCheckboxData.put("PLCCheckBox", rowData.get(18));
                retailCheckboxData.put("PODCheckBox", rowData.get(19));
                retailCheckboxData.put("ODLNCheckBox", rowData.get(20));
                retailCheckboxData.put("ODGovCheckBox", rowData.get(21));
                retailCheckboxData.put("ODLSCheckBox", rowData.get(22));
                retailCheckboxData.put("OtherFacCheckBox", rowData.get(23));
                retailCheckboxData.put("limittreecheckbox", rowData.get(24));
                retailCheckboxData.put("disbursement", rowData.get(25));

                retailReviewRenewDataArray.add(retailCheckboxData);
                LogMessages.statusLog.info("*** All Data Of Retail checkbox fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all checkbox of retail ***");

        }
    }

    private static void fetchRetailTextareaData(IFormReference iFormObj, String applicationNo, JSONArray retailReviewRenewDataArray) {
        try {
            String textareaQuery = " select Remarks1,Remarks2,Remarks3,Remarks4,Remarks5,RemarksIncome,CAScommentsCAS,ROCommentsCAS,CommentCLAD from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";

            LogMessages.statusLog.info("TextArea data fetch query: " + textareaQuery);
            List<List<String>> queryList = iFormObj.getDataFromDB(textareaQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of textarea data: " + rowData);

                JSONObject textareaData = new JSONObject();
                textareaData.put("Remarks1", rowData.get(0));
                textareaData.put("Remarks2", rowData.get(1));
                textareaData.put("Remarks3", rowData.get(2));
                textareaData.put("Remarks4", rowData.get(3));
                textareaData.put("Remarks5", rowData.get(4));
                textareaData.put("RemarksIncome", rowData.get(5));
                textareaData.put("CAScommentsCAS", rowData.get(6));
                textareaData.put("ROCommentsCAS", rowData.get(7));
                textareaData.put("CommentCLAD", rowData.get(8));

                retailReviewRenewDataArray.add(textareaData);
                LogMessages.statusLog.info("*** All textarea_data of Retail fetched successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all textarea_data of retail ***");
        }
    }

    private static void fetchRetailDateData(IFormReference iFormObj, String applicationNo, JSONArray retailReviewRenewDataArray) {
        try {
            String dateQuery = " select ApplicationDate,DateAccountOpened,DateFacsFirstSanction,LastSancDate,DOBIndividual,IssuedDate,PassportIssuedDate,PassportExpiryDate,DateRegisteredInst,"
                    + "CICReportDate,DateOfBirthCIC,CitizenshipIssuedDateCIC,PassportIssuedDateCIC,PassportExpiryDateCIC,DateLatestFinancialStmt,DobGuarantor,"
                    + "RegistrationExpiryDatePstApp,DateOfApprovalPstApprvl,DateOfApplicationPstApprvl from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";
            LogMessages.statusLog.info("date data fetch query: " + dateQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(dateQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of date: " + queryList);

                JSONObject dateData = new JSONObject();
                dateData.put("ApplicationDate", customDate(rowData.get(0)));
                dateData.put("DateAccountOpened", customDate(rowData.get(1)));
                dateData.put("DateFacsFirstSanction", customDate(rowData.get(2)));
                dateData.put("LastSancDate", customDate(rowData.get(3)));
                dateData.put("DOBIndividual", customDate(rowData.get(4)));
                dateData.put("IssuedDate", customDate(rowData.get(5)));
                dateData.put("PassportIssuedDate", customDate(rowData.get(6)));
                dateData.put("PassportExpiryDate", customDate(rowData.get(7)));
                dateData.put("DateRegisteredInst", customDate(rowData.get(8)));
                dateData.put("CICReportDate", customDate(rowData.get(9)));
                dateData.put("DateOfBirthCIC", customDate(rowData.get(10)));
                dateData.put("CitizenshipIssuedDateCIC", customDate(rowData.get(11)));
                dateData.put("PassportIssuedDateCIC", customDate(rowData.get(12)));
                dateData.put("PassportExpiryDateCIC", customDate(rowData.get(13)));
                dateData.put("DateLatestFinancialStmt", customDate(rowData.get(14)));
                dateData.put("DobGuarantor", customDate(rowData.get(15)));
                dateData.put("RegistrationExpiryDatePstApp", customDate(rowData.get(16)));
                dateData.put("DateOfApprovalPstApprvl", customDate(rowData.get(17)));
                dateData.put("DateOfApplicationPstApprvl", customDate(rowData.get(18)));


                retailReviewRenewDataArray.add(dateData);
                LogMessages.statusLog.info("*** All Data Of date fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all date field of retail ***");
        }
    }

    private static void fetchRetailComboBoxData(IFormReference iFormObj, String applicationNo, JSONArray retailReviewRenewDataArray) {
        try {
            String comboboxQuery = "select Entity,Nationality,Relationship,ProvinceIndPer,DistrictIndPer,MunicipalityVDCIndPer,ProvinceIndCur,"
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
                    + "NRBChecklistGC,Reviewer3,BoundaryWall,DecisionCAS from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";
            LogMessages.statusLog.info("Combo box data fetch query: " + comboboxQuery);
            List<List<String>> queryList = iFormObj.getDataFromDB(comboboxQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of combobox: " + rowData);

                JSONObject comboboxData = new JSONObject();
                comboboxData.put("Entity", rowData.get(0));
                comboboxData.put("Nationality", rowData.get(1));
                comboboxData.put("Relationship", rowData.get(2));
                comboboxData.put("ProvinceIndPer", rowData.get(3));
                comboboxData.put("DistrictIndPer", rowData.get(4));
                comboboxData.put("MunicipalityVDCIndPer", rowData.get(5));
                comboboxData.put("ProvinceIndCur", rowData.get(6));
                comboboxData.put("DistrictIndCur", rowData.get(7));
                comboboxData.put("MunicipalityVDCIndCur", rowData.get(8));
                comboboxData.put("Gender", rowData.get(9));
                comboboxData.put("CitizenshipIssuedBy", rowData.get(10));
                comboboxData.put("Profession", rowData.get(11));
                comboboxData.put("IncorporationProfitInst", rowData.get(12));
                comboboxData.put("IncorporationNotProfitInst", rowData.get(13));
                comboboxData.put("NatureOfBusiness", rowData.get(14));
                comboboxData.put("NatureOfActivity", rowData.get(15));
                comboboxData.put("ProvinceInstReg", rowData.get(16));
                comboboxData.put("DistrictInstReg", rowData.get(17));
                comboboxData.put("MunicipalityVDCInstReg", rowData.get(18));
                comboboxData.put("ProvinceInst", rowData.get(19));
                comboboxData.put("DistrictInst", rowData.get(20));
                comboboxData.put("MunicipalityVDCInst", rowData.get(21));
                comboboxData.put("IsCustomerBlacklisted", rowData.get(22));
                comboboxData.put("LoanFromOtherBanks", rowData.get(23));
                comboboxData.put("SectorGC", rowData.get(24));
                comboboxData.put("SubsectorGC", rowData.get(25));
                comboboxData.put("SubSubSector", rowData.get(26));
                comboboxData.put("Flag100", rowData.get(27));
                comboboxData.put("NatureOfCharge", rowData.get(28));
                comboboxData.put("SecurityType", rowData.get(29));
                comboboxData.put("SubSecurityType", rowData.get(30));
                comboboxData.put("SecurityList", rowData.get(31));
                comboboxData.put("IsCollateralShared", rowData.get(32));
                comboboxData.put("NofCollateralShared", rowData.get(33));
                comboboxData.put("Facilities1", rowData.get(34));
                comboboxData.put("Facilities2", rowData.get(35));
                comboboxData.put("Facilities3", rowData.get(36));
                comboboxData.put("Facilities4", rowData.get(37));
                comboboxData.put("Facilities5", rowData.get(38));
                comboboxData.put("SubordinationOfDebt", rowData.get(39));
                comboboxData.put("toBeUpdated", rowData.get(40));
                comboboxData.put("GeneralCompliance1", rowData.get(41));
                comboboxData.put("LandVerticallySloped", rowData.get(42));
                comboboxData.put("LandWithinDasgaja", rowData.get(43));
                comboboxData.put("LandWithinRiverForest", rowData.get(44));
                comboboxData.put("IncomeConsideredOf", rowData.get(45));
                comboboxData.put("ProfessionIncome", rowData.get(46));
                comboboxData.put("Audited", rowData.get(47));
                comboboxData.put("WaterSupply", rowData.get(48));
                comboboxData.put("CreditFacilityCC", rowData.get(49));
                comboboxData.put("PurposeAutoLoan", rowData.get(50));
                comboboxData.put("PurposeHouseLoan", rowData.get(51));
                comboboxData.put("PurposeMortgageLoan", rowData.get(52));
                comboboxData.put("PurposeEducationLoan", rowData.get(53));
                comboboxData.put("PurposePL", rowData.get(54));
                comboboxData.put("TypeOfShareCandC", rowData.get(55));
                comboboxData.put("PurposeShare", rowData.get(56));
                comboboxData.put("CountryOfEducation", rowData.get(57));
                comboboxData.put("AutomobileType", rowData.get(58));
                comboboxData.put("AutoRegistrationType", rowData.get(59));
                comboboxData.put("TypePOD", rowData.get(60));
                comboboxData.put("TypeODLN", rowData.get(61));
                comboboxData.put("CurrencyODLN", rowData.get(62));
                comboboxData.put("InstrumentODLN", rowData.get(63));
                comboboxData.put("ShareFacilityType", rowData.get(64));
                comboboxData.put("DepositTypeODLN", rowData.get(65));
                comboboxData.put("SecurityTypeODLN", rowData.get(66));
                comboboxData.put("LandfreeHoldHL", rowData.get(67));
                comboboxData.put("TOLNetWorth", rowData.get(68));
                comboboxData.put("IsExposureClassified", rowData.get(69));
                comboboxData.put("HouseNotExceedingHL", rowData.get(70));
                comboboxData.put("LocationofPropertyML", rowData.get(71));
                comboboxData.put("PropertyEL", rowData.get(72));
                comboboxData.put("ExclusionOfRentalIncomeHL", rowData.get(73));
                comboboxData.put("DocumentaryEvidenceML", rowData.get(74));
                comboboxData.put("ConfirmationOfNoHL", rowData.get(75));
                comboboxData.put("Drawdown", rowData.get(76));
                comboboxData.put("DrawdownML", rowData.get(77));
                comboboxData.put("DrawdownEL", rowData.get(78));
                comboboxData.put("OwnsPromoterShare", rowData.get(79));
                comboboxData.put("PledgeLessThan", rowData.get(80));
                comboboxData.put("NoObjectionLetter", rowData.get(81));
                comboboxData.put("VehicleAge5Years", rowData.get(82));
                comboboxData.put("VehicleAge7Years", rowData.get(83));
                comboboxData.put("PrivateCommercial7Yrs", rowData.get(84));
                comboboxData.put("InterestPaidMonthly", rowData.get(85));
                comboboxData.put("InterestPaidQuarterly", rowData.get(86));
                comboboxData.put("RepaymentDueDate", rowData.get(87));
                comboboxData.put("InterestDuringMoratium", rowData.get(88));
                comboboxData.put("RepaymentStandingOrder", rowData.get(89));
                comboboxData.put("ComprehensiveInsuranceCoverage", rowData.get(90));
                comboboxData.put("BuildingBeingFinancedHL", rowData.get(91));
                comboboxData.put("EarlyPaymentFee", rowData.get(92));
                comboboxData.put("NoOfLoanFamily", rowData.get(93));
                comboboxData.put("additionalSecurity", rowData.get(94));
                comboboxData.put("InterestToCouponRate", rowData.get(95));
                comboboxData.put("ElectricityConnection", rowData.get(96));
                comboboxData.put("EnquiredWithFamily", rowData.get(97));
                comboboxData.put("EmploymentTermGC", rowData.get(98));
                comboboxData.put("OtherSubIncomeGC", rowData.get(99));
                comboboxData.put("FinancialsSubmittedGC", rowData.get(100));
                comboboxData.put("InfoFinancialGC", rowData.get(101));
                comboboxData.put("ViabilityBusinessGC", rowData.get(102));
                comboboxData.put("IndustryStableGC", rowData.get(103));
                comboboxData.put("NabilCardHolderGC", rowData.get(104));
                comboboxData.put("IncomeNetworthGC", rowData.get(105));
                comboboxData.put("GuarantorRepaymentGC", rowData.get(106));
                comboboxData.put("ApplicantBgGC", rowData.get(107));
                comboboxData.put("NonCoreIncomeGC", rowData.get(108));
                comboboxData.put("CICReportGC", rowData.get(109));
                comboboxData.put("CautionListGC", rowData.get(110));
                comboboxData.put("BankStmtAnalysedGC", rowData.get(111));
                comboboxData.put("SecurityGC", rowData.get(112));
                comboboxData.put("FacOtherBanksGC", rowData.get(113));
                comboboxData.put("FacNabilClassifyGC", rowData.get(114));
                comboboxData.put("NRBChecklistGC", rowData.get(115));
                comboboxData.put("Reviewer3", rowData.get(116));
                comboboxData.put("BoundaryWall", rowData.get(117));
                comboboxData.put("DecisionCAS", rowData.get(118));

                retailReviewRenewDataArray.add(comboboxData);
                LogMessages.statusLog.info("*** All Data Of combo box fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all combo box field of retail ***");
        }
    }

    private static void fetchRetailTextBoxData(IFormReference iFormObj, String applicationNo, JSONArray retailReviewRenewDataArray) {
        try {
            String textBoxQuery = "select ConsiderPropertyMortageable,CustomerID,Groups,CustomerName,AccountNo,Location1IndPer,"
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
                    + "MalpotOfficeNamePstApprvl,OS_customerName,OS_groupName from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";
            LogMessages.statusLog.info("Retail ALL Data Fetch query: " + textBoxQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(textBoxQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List MyObject of textbox: " + rowData);

                JSONObject textBoxData = new JSONObject();
                textBoxData.put("ConsiderPropertyMortageable", rowData.get(0));
                textBoxData.put("CustomerID", rowData.get(1));
                textBoxData.put("Groups", rowData.get(2));
                textBoxData.put("CustomerName", rowData.get(3));
                textBoxData.put("AccountNo", rowData.get(4));
                textBoxData.put("Location1IndPer", rowData.get(5));
                textBoxData.put("StreetNameIndPer", rowData.get(6));
                textBoxData.put("HouseNoIndPer", rowData.get(7));
                textBoxData.put("Location2IndPer", rowData.get(8));
                textBoxData.put("Location1IndCur", rowData.get(9));
                textBoxData.put("StreetNameIndCur", rowData.get(10));
                textBoxData.put("HouseNoIndCur", rowData.get(11));
                textBoxData.put("Location2IndCur", rowData.get(12));
                textBoxData.put("BluePrintObtained", rowData.get(13));
                textBoxData.put("MaritalStatus", rowData.get(14));
                textBoxData.put("FathersName", rowData.get(15));
                textBoxData.put("MothersName", rowData.get(16));
                textBoxData.put("SpouseName", rowData.get(17));
                textBoxData.put("FatherInLawName", rowData.get(18));
                textBoxData.put("GrandFathersName", rowData.get(19));
                textBoxData.put("CitizenshipNo", rowData.get(20));
                textBoxData.put("ConsideredFMV", rowData.get(21));
                textBoxData.put("PassportNo", rowData.get(22));
                textBoxData.put("PassportIssuedPlace", rowData.get(23));
                textBoxData.put("DrivingLicenseNo", rowData.get(24));
                textBoxData.put("EmployeeIdNo", rowData.get(25));
                textBoxData.put("OtherIdentification", rowData.get(26));
                textBoxData.put("IndianEmbassyNumber", rowData.get(27));
                textBoxData.put("indianEmbassyIssuedFrom", rowData.get(28));
                textBoxData.put("PanNoInd", rowData.get(29));
                textBoxData.put("PanIssuedByInd", rowData.get(30));
                textBoxData.put("Phone1Ind", rowData.get(31));
                textBoxData.put("Phone2Ind", rowData.get(32));
                textBoxData.put("MobileNo1Ind", rowData.get(33));
                textBoxData.put("MobileNo2Ind", rowData.get(34));
                textBoxData.put("Email1Ind", rowData.get(35));
                textBoxData.put("Email2Ind", rowData.get(36));
                textBoxData.put("Location1InstReg", rowData.get(37));
                textBoxData.put("StreetNameInstReg", rowData.get(38));
                textBoxData.put("HouseNoInstReg", rowData.get(39));
                textBoxData.put("Location2InstReg", rowData.get(40));
                textBoxData.put("Location1Inst", rowData.get(41));
                textBoxData.put("StreetNameInst", rowData.get(42));
                textBoxData.put("HouseNoInst", rowData.get(43));
                textBoxData.put("Location2Inst", rowData.get(44));
                textBoxData.put("ActName", rowData.get(45));
                textBoxData.put("ActYear", rowData.get(46));
                textBoxData.put("AuthorizedBodyName", rowData.get(47));
                textBoxData.put("RegisteredWithInst", rowData.get(48));
                textBoxData.put("CompanyRegNo", rowData.get(49));
                textBoxData.put("DrainageConnection", rowData.get(50));
                textBoxData.put("PassportNoKeyPerson", rowData.get(51));
                textBoxData.put("DrivingLicenseNoKeyPerson", rowData.get(52));
                textBoxData.put("EmployeeIdNoKeyPerson", rowData.get(53));
                textBoxData.put("OtherIdentificationKeyPerson", rowData.get(54));
                textBoxData.put("PanNoInst", rowData.get(55));
                textBoxData.put("PanIssuedByInst", rowData.get(56));
                textBoxData.put("Phone1Inst", rowData.get(57));
                textBoxData.put("Phone2Inst", rowData.get(58));
                textBoxData.put("MobileNo1Inst", rowData.get(59));
                textBoxData.put("MobileNo2Inst", rowData.get(60));
                textBoxData.put("Email1Inst", rowData.get(61));
                textBoxData.put("Email2Inst", rowData.get(62));
                textBoxData.put("SectoralCode", rowData.get(63));
                textBoxData.put("flag101", rowData.get(64));
                textBoxData.put("TotalFundedExisting", rowData.get(65));
                textBoxData.put("EstimatedDV", rowData.get(66));
                textBoxData.put("EstimatedFMV", rowData.get(67));
                textBoxData.put("TotalNonFundExisting", rowData.get(68));
                textBoxData.put("TotalNonFundProposed", rowData.get(69));
                textBoxData.put("TotalNonFundOutstanding", rowData.get(70));
                textBoxData.put("GrandTotalExisting", rowData.get(71));
                textBoxData.put("GrandTotalProposed", rowData.get(72));
                textBoxData.put("TotalLimitOutstanding", rowData.get(73));
                textBoxData.put("CreditFacility", rowData.get(74));
                textBoxData.put("TotalLimit", rowData.get(75));
                textBoxData.put("OtherSecurityType", rowData.get(76));
                textBoxData.put("OtherNatureOfCharge", rowData.get(77));
                textBoxData.put("SecurityCodeGC", rowData.get(78));
                textBoxData.put("TotalEstFMV", rowData.get(79));
                textBoxData.put("TotalEstDV", rowData.get(80));
                textBoxData.put("UtilizedFMV1", rowData.get(81));
                textBoxData.put("UtilizedDV1", rowData.get(82));
                textBoxData.put("UtilizedFMV2", rowData.get(83));
                textBoxData.put("UtilizedDV2", rowData.get(84));
                textBoxData.put("UtilizedFMV3", rowData.get(85));
                textBoxData.put("UtilizedDV3", rowData.get(86));
                textBoxData.put("UtilizedFMV4", rowData.get(87));
                textBoxData.put("UtilizedDV4", rowData.get(88));
                textBoxData.put("UtilizedFMV5", rowData.get(89));
                textBoxData.put("UtilizedDV5", rowData.get(90));
                textBoxData.put("SecurityDocumentRemarks", rowData.get(91));
                textBoxData.put("TotalFundedFacGroup", rowData.get(92));
                textBoxData.put("TotalNonFundedFacGroup", rowData.get(93));
                textBoxData.put("TotalLimitFacilityGroup", rowData.get(94));
                textBoxData.put("TotalFundedProposed", rowData.get(95));
                textBoxData.put("TotalFundedOutstanding", rowData.get(96));
                textBoxData.put("RelationshipCIC", rowData.get(97));
                textBoxData.put("CreditFacCIC", rowData.get(98));
                textBoxData.put("InquiryTypeCIC", rowData.get(99));
                textBoxData.put("IncorporationCIC", rowData.get(100));
                textBoxData.put("NameCIC", rowData.get(101));
                textBoxData.put("GenderCIC", rowData.get(102));
                textBoxData.put("NationalityCIC", rowData.get(103));
                textBoxData.put("CitizenshipNoCIC", rowData.get(104));
                textBoxData.put("CitizenshipIssuedDistrictCIC", rowData.get(105));
                textBoxData.put("PassportNoCIC", rowData.get(106));
                textBoxData.put("PassportIssuedPlaceCIC", rowData.get(107));
                textBoxData.put("FatherNameCIC", rowData.get(108));
                textBoxData.put("MotherNameCIC", rowData.get(109));
                textBoxData.put("GrandFatherNameCIC", rowData.get(110));
                textBoxData.put("SpouseNameCIC", rowData.get(111));
                textBoxData.put("DocumentationOfAcc", rowData.get(112));
                textBoxData.put("CustomerIdCIC", rowData.get(113));
                textBoxData.put("CompanyNameCIC", rowData.get(114));
                textBoxData.put("CompanyRegNoCIC", rowData.get(115));
                textBoxData.put("CompanyRegOrg", rowData.get(116));
                textBoxData.put("PanNoCIC", rowData.get(117));
                textBoxData.put("PanIssuedByCIC", rowData.get(118));
                textBoxData.put("ApplicableCICCharge", rowData.get(119));
                textBoxData.put("CICReportType", rowData.get(120));
                textBoxData.put("BlacklistedCIC", rowData.get(121));
                textBoxData.put("CICBorrowedAcquired", rowData.get(122));
                textBoxData.put("DiscFactorBusiness", rowData.get(123));
                textBoxData.put("IncomeOf", rowData.get(124));
                textBoxData.put("SalaryIncome", rowData.get(125));
                textBoxData.put("ProfessionalIncome", rowData.get(126));
                textBoxData.put("BusinessIncome", rowData.get(127));
                textBoxData.put("RentIncome", rowData.get(128));
                textBoxData.put("InvestmentIncome", rowData.get(129));
                textBoxData.put("OtherIncome", rowData.get(130));
                textBoxData.put("TotalIncome", rowData.get(131));
                textBoxData.put("LivingExpenses", rowData.get(132));
                textBoxData.put("InterestExpenses", rowData.get(133));
                textBoxData.put("BusinessExpenses", rowData.get(134));
                textBoxData.put("OtherExpenses", rowData.get(135));
                textBoxData.put("TotalExpenses", rowData.get(136));
                textBoxData.put("UYI", rowData.get(137));
                textBoxData.put("YearlyRepaymentUYIPercentage", rowData.get(138));
                textBoxData.put("YearlyMaximumInterestUYI", rowData.get(139));
                textBoxData.put("NetSalesRevenue", rowData.get(140));
                textBoxData.put("NetIncome", rowData.get(141));
                textBoxData.put("TotalReceipts", rowData.get(142));
                textBoxData.put("TotalPayments", rowData.get(143));
                textBoxData.put("NetIncomePlusDepreciation", rowData.get(144));
                textBoxData.put("NetSurplusDepreciation", rowData.get(145));
                textBoxData.put("DSCR", rowData.get(146));
                textBoxData.put("OtherCreditFacCC", rowData.get(147));
                textBoxData.put("LegitimateUse", rowData.get(148));
                textBoxData.put("OtherPurposeAuto", rowData.get(149));
                textBoxData.put("MainPromoterDealer", rowData.get(150));
                textBoxData.put("PurposePodOdln", rowData.get(151));
                textBoxData.put("ShareIssuedByCandC", rowData.get(152));
                textBoxData.put("SegmentControlCompliance", rowData.get(153));
                textBoxData.put("DiversityOfProduct", rowData.get(154));
                textBoxData.put("InterestServicing", rowData.get(155));
                textBoxData.put("SegmentFacilities", rowData.get(156));
                textBoxData.put("AgeInYMD", rowData.get(157));
                textBoxData.put("MinAgeBorrower", rowData.get(158));
                textBoxData.put("SBU", rowData.get(159));
                textBoxData.put("MaxAgeBorrower", rowData.get(160));
                textBoxData.put("RiskDept1", rowData.get(161));
                textBoxData.put("MinAgeBusiness", rowData.get(162));
                textBoxData.put("RiskDept2", rowData.get(163));
                textBoxData.put("MaxAgeGuarantor", rowData.get(164));
                textBoxData.put("RiskDept3", rowData.get(165));
                textBoxData.put("NetIncomeBasis", rowData.get(166));
                textBoxData.put("NewPrevWS", rowData.get(167));
                textBoxData.put("GrossIncomeBasis", rowData.get(168));
                textBoxData.put("LastAuthorityGroup", rowData.get(169));
                textBoxData.put("DSCRControlCompliance", rowData.get(170));
                textBoxData.put("lastChainGroup", rowData.get(171));
                textBoxData.put("GroupName", rowData.get(172));
                textBoxData.put("MinLoanLimit", rowData.get(173));
                textBoxData.put("MaxLoanLimit", rowData.get(174));
                textBoxData.put("Facility", rowData.get(175));
                textBoxData.put("FlagA", rowData.get(176));
                textBoxData.put("CAS_decisionCombo", rowData.get(177));
                textBoxData.put("LTVRatio", rowData.get(178));
                textBoxData.put("LTVRatio1", rowData.get(179));
                textBoxData.put("BorrowerName", rowData.get(180));
                textBoxData.put("ApplicationType", rowData.get(181));
                textBoxData.put("PurchasePriceHL", rowData.get(182));
                textBoxData.put("ProductName", rowData.get(183));
                textBoxData.put("MinTenure", rowData.get(184));
                textBoxData.put("MaxTenure", rowData.get(185));
                textBoxData.put("RelationshipRiskAss", rowData.get(186));
                textBoxData.put("PurposeRiskAssessment", rowData.get(187));
                textBoxData.put("SubSegment", rowData.get(188));
                textBoxData.put("TargetAssignedUser", rowData.get(189));
                textBoxData.put("CustomizedMaxTenure", rowData.get(190));
                textBoxData.put("TargetAuthorityGroup", rowData.get(191));
                textBoxData.put("TenureNotExceedHL", rowData.get(192));
                textBoxData.put("CICReport", rowData.get(193));
                textBoxData.put("TenureAndReview", rowData.get(194));
                textBoxData.put("Approver1", rowData.get(195));
                textBoxData.put("CurrentRatio", rowData.get(196));
                textBoxData.put("InstallmentType", rowData.get(197));
                textBoxData.put("BrandStrength", rowData.get(198));
                textBoxData.put("Frequency", rowData.get(199));
                textBoxData.put("DistributionInfra", rowData.get(200));
                textBoxData.put("MoratoriumPeriodHL", rowData.get(201));
                textBoxData.put("GrowthProspects", rowData.get(202));
                textBoxData.put("IndustryBusiness", rowData.get(203));
                textBoxData.put("MarketShare", rowData.get(204));
                textBoxData.put("OverallMarket", rowData.get(205));
                textBoxData.put("UserDecision", rowData.get(206));
                textBoxData.put("InterestRateCandC", rowData.get(207));
                textBoxData.put("WellEstd", rowData.get(208));
                textBoxData.put("LoanAdminFeeCandC", rowData.get(209));
                textBoxData.put("Reviewer1", rowData.get(210));
                textBoxData.put("EarlyPaymentFeeTextDisplay", rowData.get(211));
                textBoxData.put("CommitmentFee", rowData.get(212));
                textBoxData.put("PanAndBankStmt", rowData.get(213));
                textBoxData.put("Reviewer2", rowData.get(214));
                textBoxData.put("AbilityToGenFund", rowData.get(215));
                textBoxData.put("AdditionalImmovalSecurity", rowData.get(216));
                textBoxData.put("DebtServicingModel", rowData.get(217));
                textBoxData.put("BranchCodeGC", rowData.get(218));
                textBoxData.put("SBUCodeGC", rowData.get(219));
                textBoxData.put("Decision", rowData.get(220));
                textBoxData.put("Flag1", rowData.get(221));
                textBoxData.put("Flag2", rowData.get(222));
                textBoxData.put("entryCounter", rowData.get(223));
                textBoxData.put("initialRowCount", rowData.get(224));
                textBoxData.put("NextWorkdesk", rowData.get(225));
                textBoxData.put("IsQuery", rowData.get(226));
                textBoxData.put("QueriedBy", rowData.get(227));
                textBoxData.put("QueriedTo", rowData.get(228));
                textBoxData.put("ApproverCounter", rowData.get(229));
                textBoxData.put("APPROVALDECISION", rowData.get(230));
                textBoxData.put("ToTriggerName", rowData.get(231));
                textBoxData.put("ToTriggerEmail", rowData.get(232));
                textBoxData.put("NextWorkStep", rowData.get(233));
                textBoxData.put("PostApprovalDecision", rowData.get(234));
                textBoxData.put("SuccessionPlan", rowData.get(235));
                textBoxData.put("MaritalStatusPostApproval", rowData.get(236));
                textBoxData.put("ActNamePostApproval", rowData.get(237));
                textBoxData.put("ActYearPostApproval", rowData.get(238));
                textBoxData.put("AuthorizedBodyNamePostApproval", rowData.get(239));
                textBoxData.put("IssuedDistrictPostApproval", rowData.get(240));
                textBoxData.put("SecuritytypePstApprvl", rowData.get(241));
                textBoxData.put("InsuranceAmtbuildingPstApprvl", rowData.get(242));
                textBoxData.put("NameofBankreqtakeoverPstApprvl", rowData.get(243));
                textBoxData.put("AccountNumberPstApprvl", rowData.get(244));
                textBoxData.put("AccountBenefNamePstApprvl", rowData.get(245));
                textBoxData.put("RelationOfficerNamePstApprvl", rowData.get(246));
                textBoxData.put("BranchManagerNameRMPstApprvl", rowData.get(247));
                textBoxData.put("ProvinancePstApprvl", rowData.get(248));
                textBoxData.put("MalpotOfficeNamePstApprvl", rowData.get(249));
                textBoxData.put("OS_customerName", rowData.get(250));
                textBoxData.put("OS_groupName", rowData.get(251));

                retailReviewRenewDataArray.clear();
                retailReviewRenewDataArray.add(textBoxData);
                LogMessages.statusLog.info("*** All Data Of text box fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all text box field of retail  ***");
        }
    }
}

