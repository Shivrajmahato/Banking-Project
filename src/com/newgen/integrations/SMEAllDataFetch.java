
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
public class SMEAllDataFetch {


    private SMEAllDataFetch() {

    }

    public static String customDate(String para) {
        return para;
    }

    public static JSONArray smeAllDataFetch(IFormReference iFormObj) {
        String applicationNo = iFormObj.getValue("Buildingdetails").toString();
        String pid = applicationNo;
        LogMessages.statusLog.info("Applicationno: " + applicationNo);
        // fetching all review renew data
        JSONArray smeReviewRenewDataArray = new JSONArray();

        // fetching all SME textbox data
        fetchSmeReviewRenewData(iFormObj, applicationNo, smeReviewRenewDataArray);

        //fetching all comboobx data of SME
        fetchSmeComboboxData(iFormObj, applicationNo, smeReviewRenewDataArray);

        //fetch all date field of SME
        fetchSmeDateData(iFormObj, applicationNo, smeReviewRenewDataArray);

        //fetching all textarea data of SME
        //CS_SMELongRemarks
        fetchSmeTextareaLongData(iFormObj, pid, smeReviewRenewDataArray);

        //fetching all textarea data of SME
        fetchSmeTextAreaData(iFormObj, applicationNo, smeReviewRenewDataArray);

        //fetching all checkbox data of SME
        fetchSmeCheckboxData(iFormObj, applicationNo, smeReviewRenewDataArray);

        //fetching all listbox data of SME
        fetchSmeListBoxData(iFormObj, pid, smeReviewRenewDataArray);

        //fetching limitmaintenance combobox of KYC
        fetchSmeLimitMaintenanceData(iFormObj, pid, smeReviewRenewDataArray);

        // grid for KYC (TAB) */
        //for CS_ShareholdingDetailsRet
        fetchSmeShareholdingDetailsData(iFormObj, pid);

        //for CS_KeyPersonsDetailsSME
        fetchSmeKeyPersonsDetailsData(iFormObj, pid);

        //for CS_KeyPersonCitizenDetailsRet
        fetchSmeKeyPersonCitizenDetailsData(iFormObj, pid);

        //facilities TAB
        // for CS_FacilitiesSME
        fetchSmeFacilitiesData(iFormObj, pid);

        // facilities Tab
        //for CS_CustomizedInstallmentRet
        fetchSmeCustomizedInstallmentData(iFormObj, pid);

        //Security Details & Documents
        //for CS_RealEstatePropertyRet
        fetchSmeRealEstatePropertyData(iFormObj, pid);

        //security Details & Documents
        //for CS_PlotDetails
        fetchSmePlotDetailsData(iFormObj, pid);

        //for security Details & Documents
        //for CS_AutomobileRet
        fetchSmeAutomobileData(iFormObj, pid);

        //Security Details & Documents
        // for CS_InventoriesAccReceivableRet
        fetchSmeInventoriesAccReceivableData(iFormObj, pid);

        // Security Details & Documents
        // for CS_BorrowingpowerAssessment
        fetchSmeBorrowingPowerAssessmentData(iFormObj, pid);

        // SEcurity Details & Documents
        //for CS_FixedOrBankDeposirRet
        fetchSmeFixedOrBankDepositData(iFormObj, pid);

        //Security Details & Documents
        //for CS_GovermentSecuritiesRet
        fetchSmeGovernmentSecuritiesData(iFormObj, pid);

        //Security Details & Documents
        //for CS_CorporateBondRet
        fetchSmeCorporateBondData(iFormObj, pid);

        //Security Details & Documents
        //for CS_ListedSecuritiesRet
        fetchSmeListedSecuritiesData(iFormObj, pid);

        //Security Details & Documents
        //for CS_GoldValuableSecuritiesRet
        fetchSmeGoldValuableSecuritiesData(iFormObj, pid);

        //Security Details & Documents
        // for CS_GuaranteeRet
        fetchSmeGuaranteeData(iFormObj, pid);

        //Security Details & Documents
        //for CS_ConnectionOwnership
        fetchSmeConnectionOwnershipData(iFormObj, pid);

        //Groupsheet
        //for CS_Exposure
        fetchSmeExposureData(iFormObj, pid);

        //Groupsheet
        //for CS_GroupFacilitiesSME
        fetchSmeGroupFacilitiesData(iFormObj, pid);
        return smeReviewRenewDataArray;
    }

    private static void fetchSmeGroupFacilitiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayGroupFacilitiesSME = new JSONArray();
        try {
            String query = "select  OtherCreditFacility,limitShortTermLoan,outstandingShortTermLoan,pricingShortTermLoan,Remarks,"
                    + "unitNameFac,shortTermLoan  from CS_GroupFacilitiesSME with(nolock) where  PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + query);
            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);
            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);
                JSONObject obj = new JSONObject();
                obj.put("Other Credit Facility", rowData.get(0));
                obj.put("Short Term Loan limit", rowData.get(1));
                obj.put("Outstanding1", rowData.get(2));
                obj.put("Pricing1", rowData.get(3));
                obj.put(REMARKS, rowData.get(4));
                obj.put("Unit Name", rowData.get(5));
                obj.put("Facility", rowData.get(6));

                jsonArrayGroupFacilitiesSME.clear();
                jsonArrayGroupFacilitiesSME.add(obj);
                iFormObj.addDataToGrid("tblGroupFacilitiesSME", jsonArrayGroupFacilitiesSME);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_GroupFacilitiesSME", e);
        }
    }

    private static void fetchSmeExposureData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayExposure = new JSONArray();
        try {
            String query = "select A,B,C,D,E,F,G,H,I,Total,unitName from CS_Exposure with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);
                JSONObject obj = new JSONObject();

                obj.put("A", rowData.get(0));
                obj.put("B", rowData.get(1));
                obj.put("C", rowData.get(2));
                obj.put("D", rowData.get(3));
                obj.put("E", rowData.get(4));
                obj.put("F", rowData.get(5));
                obj.put("G", rowData.get(6));
                obj.put("H", rowData.get(7));
                obj.put("I", rowData.get(8));
                obj.put("Total", rowData.get(9));
                obj.put("Unit Name ", rowData.get(10));

                jsonArrayExposure.clear();
                jsonArrayExposure.add(obj);
                iFormObj.addDataToGrid("tblExposure", jsonArrayExposure);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_Exposure", e);
        }
    }

    private static void fetchSmeConnectionOwnershipData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayConnectionOwnership = new JSONArray();
        try {
            String query = "select unitName,ownership,ShareholderName1,HoldingPercentage1,ShareholderName2,HoldingPercentage2,"
                    + "ShareholderName3,HoldingPercentage3,HoldingPercentage4,ShareholderName4,ShareholderName5,HoldingPercentage5,"
                    + "ShareholderName6,HoldingPercentage6,ShareholderName7,HoldingPercentage7,ShareholderName8,HoldingPercentage8,"
                    + "ShareholderName9,HoldingPercentage9,ShareholderName10,HoldingPercentage10,HoldingPercentage11,ShareholderName11,"
                    + "ShareholderName12,HoldingPercentage12,ShareholderName13,HoldingPercentage13,ShareholderName14,HoldingPercentage14,"
                    + "ShareholderName15,HoldingPercentage15,ShareholderName16,HoldingPercentage16,ShareholderName17,HoldingPercentage17,"
                    + "ShareholderName18,HoldingPercentage18,ShareholderName19,HoldingPercentage19,ShareholderName20,HoldingPercentage20,"
                    + "Remarks,Incorporation,ShareholdersNum  from CS_ConnectionOwnership with(nolock) where  PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + query);
            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Individual/Unit Name", rowData.get(0));
                obj.put("Owership", rowData.get(1));
                obj.put("First Shareholder Name", rowData.get(2));
                obj.put("First Shareholding percentage", rowData.get(3));
                obj.put("Second Shareholder Name", rowData.get(4));
                obj.put("Second Shareholding percentage", rowData.get(5));
                obj.put("Third Shareholder Name", rowData.get(6));
                obj.put("Third Shareholding percentage", rowData.get(7));
                obj.put("Forth Shareholding percentage", rowData.get(8));
                obj.put("Fourth Shareholder Name", rowData.get(9));
                obj.put("Fifth Shareholder Name", rowData.get(10));
                obj.put("Fifth Shareholding percentage", rowData.get(11));
                obj.put("Sixth Shareholder Name", rowData.get(12));
                obj.put("sixth Shareholding percentage", rowData.get(13));
                obj.put("Seventh Shareholder Name", rowData.get(14));
                obj.put("Seventh Shareholding percentage", rowData.get(15));
                obj.put("Eighth Shareholder Name", rowData.get(16));
                obj.put("Eighth Shareholding percentage", rowData.get(17));
                obj.put("Ninth Shareholder Name", rowData.get(18));
                obj.put("Ninth Shareholding percentage", rowData.get(19));
                obj.put("Tenth Shareholder Name", rowData.get(20));
                obj.put("Tenth Sharholding percentage", rowData.get(21));
                obj.put("Eleventh Shareholding percentage", rowData.get(22));
                obj.put("Eleventh Shareholder Name", rowData.get(23));
                obj.put("Twelfth Shareholder Name", rowData.get(24));
                obj.put("twelfth Shareholding percentage", rowData.get(25));
                obj.put("Thirteen Shareholder Name", rowData.get(26));
                obj.put("Thirteen Shareholding percentage", rowData.get(27));
                obj.put("Fourteen Shareholder Name", rowData.get(28));
                obj.put("Fourteen Shareholding percentage", rowData.get(29));
                obj.put("Fifteenth Shareholder Name", rowData.get(30));
                obj.put("Fifteenth Shareholding percentage", rowData.get(31));
                obj.put("Sixteenth Shareholder Name", rowData.get(32));
                obj.put("Sixteenth Shareholding percentage", rowData.get(33));
                obj.put("Seventeen Shareholder Name", rowData.get(34));
                obj.put("Seventeenth Shareholding percentage", rowData.get(35));
                obj.put("Eighteenth Shareholder Name", rowData.get(36));
                obj.put("Eighteenth Shareholding percentage", rowData.get(37));
                obj.put("Nineeten Shareholder Name", rowData.get(38));
                obj.put("Ninenth Shareholding percentage", rowData.get(39));
                obj.put("Twenth Shareholder Name", rowData.get(40));
                obj.put("Twenth Shareholding percentage", rowData.get(41));
                obj.put(REMARKS, rowData.get(42));
                obj.put("Incorporation", rowData.get(43));
                obj.put("Number of Shareholders", rowData.get(44));

                jsonArrayConnectionOwnership.clear();
                jsonArrayConnectionOwnership.add(obj);
                iFormObj.addDataToGrid("tblConnectionOwnershipSME", jsonArrayConnectionOwnership);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_ConnectionOwnership", e);
        }
    }

    private static void fetchSmeGuaranteeData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayGuaranteeRet = new JSONArray();
        try {
            String query = "select otherGuarantorName,Age,Amount,Networth,CitizenshipNo,CitizenshipIssuedDistrict,PassportNo,"
                    + "PassportIssuedPlace,FathersName,MothersName,GrandFathersName,SpouseName,Required,Type,IsGuarantor,NameOfGuarantor,"
                    + "GuaranteeStatus,DOB,DateOfExecution,Expiry,CitizenshipIssuedDate,PassportIssuedDate,PassportExpiryDate from CS_GuaranteeRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Guarantor's Name", rowData.get(0));
                obj.put("Age", rowData.get(1));
                obj.put(AMOUNT, rowData.get(2));
                obj.put("Networth of the Guarantor", rowData.get(3));
                obj.put("Citizenship Number", rowData.get(4));
                obj.put("Citizenship Issued District", rowData.get(5));
                obj.put("Passport Number", rowData.get(6));
                obj.put("Passport Issued Place", rowData.get(7));
                obj.put("Father's Name", rowData.get(8));
                obj.put("Mother's Name", rowData.get(9));
                obj.put("Grand Father's Name", rowData.get(10));
                obj.put("Spouse Name", rowData.get(11));
                obj.put("Required", rowData.get(12));
                obj.put("Type", rowData.get(13));
                obj.put("Is Guarantor a Shareholder Partner?", rowData.get(14));
                obj.put("Name of the Guarantor", rowData.get(15));
                obj.put("Status", rowData.get(16));
                obj.put("Date of Birth", rowData.get(17));
                obj.put("Date of Execution", rowData.get(18));
                obj.put("Expiry", rowData.get(19));
                obj.put("Citizenship Issued Date", rowData.get(20));
                obj.put("Passport Issued Date", rowData.get(21));
                obj.put("Passport Expiry Date", rowData.get(22));

                jsonArrayGuaranteeRet.clear();
                jsonArrayGuaranteeRet.add(obj);
                iFormObj.addDataToGrid("tblGuaranteeSME", jsonArrayGuaranteeRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_GuaranteeRet", e);
        }
    }

    private static void fetchSmeGoldValuableSecuritiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayGoldValuableSecuritiesRet = new JSONArray();
        try {
            String query = "select OtherNatureOfCharge,SecurityCode,OtherType,Weight,NatureOfCharge,SecurityType,"
                    + "SubSecurityType,SecurityDescription,Type,StatusOfBankLienCharge,PledgeAgreement,LatestDateexecution,CollateralType from CS_GoldValuableSecuritiesRet with(nolock) where  PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + query);
            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(0));
                obj.put(SECURITY_CODE, rowData.get(1));
                obj.put("Other Type", rowData.get(2));
                obj.put("Weight", rowData.get(3));
                obj.put(NATURE_OF_CHARGE, rowData.get(4));
                obj.put(SECURITY_TYPE, rowData.get(5));
                obj.put(SUB_SECURITY_TYPE, rowData.get(6));
                obj.put(DESCRIPTION, rowData.get(7));
                obj.put("Type", rowData.get(8));
                obj.put(STATUS_OF_BANK_S_LIEN_OR_PLEDGE_CHARGE, rowData.get(9));
                obj.put("Pledge Agreement", rowData.get(10));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(11));
                obj.put(COLLATERAL_TYPE, rowData.get(12));

                jsonArrayGoldValuableSecuritiesRet.clear();
                jsonArrayGoldValuableSecuritiesRet.add(obj);
                iFormObj.addDataToGrid("tblValuablesSecuritySME", jsonArrayGoldValuableSecuritiesRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_GoldValuableSecuritiesRet", e);
        }
    }

    private static void fetchSmeListedSecuritiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayListedSecuritiesRet = new JSONArray();
        try {
            String query = "select OtherNatureOfCharge,SecurityCode,IssuingCompanyName,InstrumentNo,NatureOfCharge,SecurityType,"
                    + "SubSecurityType,SecurityDescription,StatusBankLienCharge,DmatAccountStmt,Manjurinama,Annexure,PledgeConfirmation,"
                    + "DebitInstructionSlip,LatestDateExecution,CollateralType from CS_ListedSecuritiesRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(0));
                obj.put(SECURITY_CODE, rowData.get(1));
                obj.put("Name of Issuing Company", rowData.get(2));
                obj.put(INSTRUMENT_NUMBER, rowData.get(3));
                obj.put(NATURE_OF_CHARGE, rowData.get(4));
                obj.put(SECURITY_TYPE, rowData.get(5));
                obj.put(SUB_SECURITY_TYPE, rowData.get(6));
                obj.put(DESCRIPTION, rowData.get(7));
                obj.put(STATUS_OF_BANK_S_LIEN_OR_PLEDGE_CHARGE, rowData.get(8));
                obj.put("D-Mat Account Statement", rowData.get(9));
                obj.put("Manjurinama", rowData.get(10));
                obj.put("Annexure 18 and 19", rowData.get(11));
                obj.put("Pledge Confirmation", rowData.get(12));
                obj.put("Debit Instruction Slip", rowData.get(13));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(14));
                obj.put(COLLATERAL_TYPE, rowData.get(15));

                jsonArrayListedSecuritiesRet.clear();
                jsonArrayListedSecuritiesRet.add(obj);
                iFormObj.addDataToGrid("tblListedSecuritySME", jsonArrayListedSecuritiesRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_ListedSecuritiesRet", e);
        }
    }

    private static void fetchSmeCorporateBondData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayCorporateBondRet = new JSONArray();
        try {
            String query = "select OtherNatureOfCharge,SecurityCode,InstrumentNo,ValueCorpBond,NatureOfCharge,"
                    + "SecurityType,SubSecurityType,SecurityDescription,IssuingCompanyName,StatusBankLienCharge,"
                    + "OriginalInstrument,ConfirmationFromIssuingCompany,MaturityDate,LatestDateExecution from CS_CorporateBondRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(0));
                obj.put(SECURITY_CODE, rowData.get(1));
                obj.put(INSTRUMENT_NUMBER, rowData.get(2));
                obj.put("Value of the Corporate Bond", rowData.get(3));
                obj.put(NATURE_OF_CHARGE, rowData.get(4));
                obj.put(SECURITY_TYPE, rowData.get(5));
                obj.put("Sub SecurityType", rowData.get(6));
                obj.put(DESCRIPTION, rowData.get(7));
                obj.put("Name of the Issuing Company", rowData.get(8));
                obj.put(STATUS_OF_BANK_S_LIEN_OR_PLEDGE_CHARGE, rowData.get(9));
                obj.put("Original Instrument with Bank Endorsement", rowData.get(10));
                obj.put("Confirmation from issuing Company/CDS with regard to Bank's Charg", rowData.get(11));
                obj.put("MaturityDate", rowData.get(12));
                obj.put("LatestDateExecution", rowData.get(13));

                jsonArrayCorporateBondRet.clear();
                jsonArrayCorporateBondRet.add(obj);
                iFormObj.addDataToGrid("tblCorpBondsSecuritySME", jsonArrayCorporateBondRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_CorporateBondRet", e);
        }
    }

    private static void fetchSmeGovernmentSecuritiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayGovernmentSecuritiesRet = new JSONArray();
        try {
            String query = "select OtherNatureOfCharge,SecurityCode,InstrumentNo,ValueGovSecurities,NatureOfCharge,SecurityType,"
                    + "SubSecurityType,SecurityDescription,Instrument,TypeOfSecurity,StatusBankLienCharge,OriginalInstrument,"
                    + "LetterOfPledge,ConfirmationFromNRB,MaturityDate,LatestDateExecution,CollateralType from CS_GovermentSecuritiesRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(0));
                obj.put(SECURITY_CODE, rowData.get(1));
                obj.put(INSTRUMENT_NUMBER, rowData.get(2));
                obj.put("Value of the Government Securities", rowData.get(3));
                obj.put(NATURE_OF_CHARGE, rowData.get(4));
                obj.put(SECURITY_TYPE, rowData.get(5));
                obj.put(SUB_SECURITY_TYPE, rowData.get(6));
                obj.put(DESCRIPTION, rowData.get(7));
                obj.put("Instrument", rowData.get(8));
                obj.put("Type of Security", rowData.get(9));
                obj.put(STATUS_OF_BANK_S_LIEN_OR_PLEDGE_CHARGE, rowData.get(10));
                obj.put("Original Instrument with Blank Endorsement", rowData.get(11));
                obj.put("Letter of Pledge / Lien Charge over the Security", rowData.get(12));
                obj.put("Confirmation from NRB with regard to the Bank's Charge", rowData.get(13));
                obj.put("Maturity Date", rowData.get(14));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(15));
                obj.put(COLLATERAL_TYPE, rowData.get(16));

                jsonArrayGovernmentSecuritiesRet.clear();
                jsonArrayGovernmentSecuritiesRet.add(obj);
                iFormObj.addDataToGrid("tblGovSecuritySME", jsonArrayGovernmentSecuritiesRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_GovermentSecuritiesRet", e);
        }
    }

    private static void fetchSmeFixedOrBankDepositData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayFixedOrBankDepositRet = new JSONArray();
        try {
            String query = "select OtherNatureOfCharge,SecurityCode,AccountNo,ValueOfFixedDepositCharge,NatureOfCharge,"
                    + "SecurityType,SubSecurityType,SecurityDescription,TypeOfDeposit,Currency,StatusBankLienCharge,"
                    + "FixedDepositReceipt,LienChargeOverDeposit,MaturityDate,LatestDateExecution,collateraltype from CS_FixedOrBankDeposirRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(0));
                obj.put(SECURITY_CODE, rowData.get(1));
                obj.put("Fixed Deposit or Bank Deposit Account Number", rowData.get(2));
                obj.put("Value of the Fixed Deposit/Charge over Deposit", rowData.get(3));
                obj.put(NATURE_OF_CHARGE, rowData.get(4));
                obj.put(SECURITY_TYPE, rowData.get(5));
                obj.put(SUB_SECURITY_TYPE, rowData.get(6));
                obj.put(DESCRIPTION, rowData.get(7));
                obj.put("Type of Deposit", rowData.get(8));
                obj.put("Currency", rowData.get(9));
                obj.put("Status of Bank's Lien Charge", rowData.get(10));
                obj.put("Fixed Deposit Receipt", rowData.get(11));
                obj.put("Lien Charge over the Deposit", rowData.get(12));
                obj.put("Maturity Date", rowData.get(13));
                obj.put(LATEST_DATE_OF_EXECUTION, rowData.get(14));
                obj.put(COLLATERAL_TYPE, rowData.get(15));

                jsonArrayFixedOrBankDepositRet.clear();
                jsonArrayFixedOrBankDepositRet.add(obj);
                iFormObj.addDataToGrid("tblFixedDepositSecuritySME", jsonArrayFixedOrBankDepositRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_FixedOrBankDeposirRet", e);
        }
    }

    private static void fetchSmeBorrowingPowerAssessmentData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayBorrowingpowerAssessment = new JSONArray();
        try {
            String query = "select Totalinventory,Totalreceivablewithinapprovedtenure,Othercurrentassets,Totalpayablescreditors,"
                    + "NetTradingAssets,Margin,BorrowingPower,Outstanding,SurplusDeficit,Dated from CS_BorrowingpowerAssessment with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Total inventory", rowData.get(0));
                obj.put("Total receivable within approved tenure", rowData.get(1));
                obj.put("Other current asset, if allowed for drawing power assessment", rowData.get(2));
                obj.put("Total payables/creditors", rowData.get(3));
                obj.put("Net Trading Assets", rowData.get(4));
                obj.put("Margin", rowData.get(5));
                obj.put("Borrowing Power", rowData.get(6));
                obj.put("Outstanding", rowData.get(7));
                obj.put("Surplus/(Deficit)", rowData.get(8));
                obj.put("Date", rowData.get(9));

                jsonArrayBorrowingpowerAssessment.clear();
                jsonArrayBorrowingpowerAssessment.add(obj);
                iFormObj.addDataToGrid("table108 ( Borrowing power assessment during the review period )", jsonArrayBorrowingpowerAssessment);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_BorrowingpowerAssessment", e);
        }
    }

    private static void fetchSmeInventoriesAccReceivableData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayInventoriesAccReceivableRet = new JSONArray();

        try {
            String query = "select OtherNatureOfCharge,SecurityCode,EstimatedFMV,EstimatedDV,AmountofInsurance,DetailofWaivers,"
                    + "NameInspectingOfficial,DesignationInspectingOfficial,TotalInventory,TotalReceivable,OtherCurrentAsset,"
                    + "TotalPayables,NetTradingAssets,NatureOfCharge,SecurityType,SubSecurityType,SecurityDescription,TypeOfAssets,"
                    + "StatusHypothecationCharge,BasisOfValuation,InsuranceRequired,BusinessInOperation,BusinessOperationSatisfactory,"
                    + "RegDocPanRenewed,LeaseAgreement,RequisitesForSmoothOperation,AmountInventoryAdequate,QualityInventorySatisfactory,"
                    + "FirefightingMeasures,SecurityArrangements,NoLabourIssues,KeyPersonContacted,NoNegativeNews,BorrowingPoweradhered,"
                    + "InsurancePolicy,AssignmentOfBills,PowerOfAttorney,GeneralLetterHypo,SupplementaryAgreement,PledgeAgreement,"
                    + "RegOfCharge,LatestDateExecution,DateLastStmt,Insurancecoveragestartdate,InsuranceExpirydate,House_Checkbox,Fire_Checkbox,"
                    + "Buglary_Checkbox,Terrorism_Checkbox,Earthquake_Checkbox,Vehicle_Checkbox,Thirdparty_Checkbox,Comprehensive_Checkbox,Accidental_Checkbox,Marine_Checkbox,Flood_Checkbox,Landslide_Checkbox,Withwaiver_Checkbox,collateralType from CS_InventoriesAccReceivableRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(0));
                obj.put(SECURITY_CODE, rowData.get(1));
                obj.put("Estimated Fair Market Value", rowData.get(2));
                obj.put("Estimated Distress Value", rowData.get(3));
                obj.put(AMOUNT_OF_INSURANCE, rowData.get(4));
                obj.put(DETAIL_OF_WAIVERS, rowData.get(5));
                obj.put("Name of the Inspecting Official/s", rowData.get(6));
                obj.put("Designation of the Inspecting Official/s", rowData.get(7));
                obj.put("Total Inventory", rowData.get(8));
                obj.put("Total Receivable within Approved Tenure", rowData.get(9));
                obj.put("Other Current Asset, if allowed for Drawing Power Assessment", rowData.get(10));
                obj.put("Total Payables/Creditors", rowData.get(11));
                obj.put("Net Trading Assets", rowData.get(12));
                obj.put(NATURE_OF_CHARGE, rowData.get(13));
                obj.put(SECURITY_TYPE, rowData.get(14));
                obj.put(SUB_SECURITY_TYPE, rowData.get(15));
                obj.put(DESCRIPTION, rowData.get(16));
                obj.put("Type of Assets", rowData.get(17));
                obj.put("Status of Bank's Hypothecation Charge", rowData.get(18));
                obj.put(BASIS_OF_VALUATION, rowData.get(19));
                obj.put(INSURANCE_REQUIRED, rowData.get(20));
                obj.put("1. Business was in operation at the time of inspection", rowData.get(21));
                obj.put("2. Overall operation of the business was found to be satisfactory", rowData.get(22));
                obj.put("3. Registration document & PAN were renewed & displayed properly", rowData.get(23));
                obj.put("4. In case of rented premises, the lease agreement was reviewed", rowData.get(24));
                obj.put("5. Basic requisites for smooth operation of business/factory foun", rowData.get(25));
                obj.put("6. The amount of inventory & receivables was found to be adequate", rowData.get(26));
                obj.put("7. The quality of inventory & receivables was found to be satisfa", rowData.get(27));
                obj.put("8. Firefighting measures were in place", rowData.get(28));
                obj.put("9. Security arrangements were in place", rowData.get(29));
                obj.put("10. There were no labour issues apparent", rowData.get(30));
                obj.put("11. The key person of the borrower could be contacted", rowData.get(31));
                obj.put("12. There were no negative news regarding Borrower/Promoter/Direc", rowData.get(32));
                obj.put("13. Borrowing Power adhered to during entire period since last ap", rowData.get(33));
                obj.put(INSURANCE_POLICY_WITH_PREMIUM_PAID_RECEIPT, rowData.get(34));
                obj.put("Assignment of bills and account receivables", rowData.get(35));
                obj.put("Power of attorney", rowData.get(36));
                obj.put("General letter of hypothecation", rowData.get(37));
                obj.put("Supplementary agreement", rowData.get(38));
                obj.put("Pledge agreement", rowData.get(39));
                obj.put("Registration of charge under secured transaction registry", rowData.get(40));
                obj.put("Latest Date of Execution/Registration of Charge (STR)", rowData.get(41));
                obj.put("Date of Last Statement of Net Trading Assets", rowData.get(42));
                obj.put(INSURANCE_COVERAGE_START_DATE, rowData.get(43));
                obj.put(INSURANCE_EXPIRY_DATE, rowData.get(44));
                obj.put(HOUSE, rowData.get(45));
                obj.put(FIRE, rowData.get(46));
                obj.put(BUGLARY, rowData.get(47));
                obj.put(TERRORISM, rowData.get(48));
                obj.put(EARTHQUAKE, rowData.get(49));
                obj.put(VEHICLE, rowData.get(50));
                obj.put(THIRD_PARTY, rowData.get(51));
                obj.put(COMPREHENSIVE, rowData.get(52));
                obj.put(ACCIDENTAL, rowData.get(53));
                obj.put("marine", rowData.get(54));
                obj.put(FLOOD, rowData.get(55));
                obj.put("Landslide", rowData.get(56));
                obj.put(WITH_WAIVERS, rowData.get(57));
                obj.put(COLLATERAL_TYPE, rowData.get(58));

                jsonArrayInventoriesAccReceivableRet.clear();
                jsonArrayInventoriesAccReceivableRet.add(obj);
                iFormObj.addDataToGrid("tblInventoryARMovableSecuritySME", jsonArrayInventoriesAccReceivableRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_InventoriesAccReceivableRet", e);

        }
    }

    private static void fetchSmeAutomobileData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayAutomobileRet = new JSONArray();
        try {
            String query = "select allotmentletter,basisofvaluation,brand,chassisno,copyofbluebook,dateofvaluation,engineno,estimateddv,"
                    + "estimatedfmv,hirepurchaseagreement,insurancepolicy,latestdateexecution,purchaseprice,quotationinvoice,registrationno,"
                    + "statushypothecationcharge,thirdpartyownershiptransfer,valuedby,vatbill,yearofproduction,modelname,natureofcharge,"
                    + "securitytype,subsecuritytype,securitydescription,othernatureofcharge,insurancerequired,house_checkbox,"
                    + "fire_checkbox,buglary_checkbox,terrorism_checkbox,earthquake_checkbox,vehicle_checkbox,thirdparty_checkbox,"
                    + "comprehensive_checkbox,accidental_checkbox,marine_checkbox,flood_checkbox,landslide_checkbox,amountofinsurance,"
                    + "insurancecoveragestartdate,insuranceexpirydate,detailofwaivers,withwaiver_checkbox,collateraltype,SecurityCode  from CS_AutomobileRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Allotment Letter", rowData.get(0));
                obj.put(BASIS_OF_VALUATION, rowData.get(1));
                obj.put("Brand", rowData.get(2));
                obj.put("Chasis No.", rowData.get(3));
                obj.put("Copy of Blue Book noting the Bank's Interest", rowData.get(4));
                obj.put("Date of Valuation", rowData.get(5));
                obj.put("Engine No.", rowData.get(6));
                obj.put("Estimated Distress Value", rowData.get(7));
                obj.put("Estimated Fair Market Value", rowData.get(8));
                obj.put("Hire purchase agreement", rowData.get(9));
                obj.put(INSURANCE_POLICY_WITH_PREMIUM_PAID_RECEIPT, rowData.get(10));
                obj.put("Latest Date of Execution/Registration of Charge", rowData.get(11));
                obj.put("Purchase Price", rowData.get(12));
                obj.put("Quotation/Proforma Invoice", rowData.get(13));
                obj.put("Registration Number", rowData.get(14));
                obj.put("Status of Bank's Hypothecation Charge", rowData.get(15));
                obj.put("Third-party ownership transfer", rowData.get(16));
                obj.put("Valued By", rowData.get(17));
                obj.put("VAT Bill / Tax Invoice", rowData.get(18));
                obj.put("Year of Production", rowData.get(19));
                obj.put("Model", rowData.get(20));
                obj.put("Nature of Charge", rowData.get(21));
                obj.put(SECURITY_TYPE, rowData.get(22));
                obj.put(SUB_SECURITY_TYPE, rowData.get(23));
                obj.put(DESCRIPTION, rowData.get(24));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(25));
                obj.put(INSURANCE_REQUIRED, rowData.get(26));
                obj.put(HOUSE, rowData.get(27));
                obj.put(FIRE, rowData.get(28));
                obj.put(BUGLARY, rowData.get(29));
                obj.put(TERRORISM, rowData.get(30));
                obj.put(EARTHQUAKE, rowData.get(31));
                obj.put(VEHICLE, rowData.get(32));
                obj.put(THIRD_PARTY, rowData.get(33));
                obj.put(COMPREHENSIVE, rowData.get(34));
                obj.put(ACCIDENTAL, rowData.get(35));
                obj.put("Marine", rowData.get(36));
                obj.put(FLOOD, rowData.get(37));
                obj.put("Landslide", rowData.get(38));
                obj.put(AMOUNT_OF_INSURANCE, rowData.get(39));
                obj.put(INSURANCE_COVERAGE_START_DATE, rowData.get(40));
                obj.put(INSURANCE_EXPIRY_DATE, rowData.get(41));
                obj.put(DETAIL_OF_WAIVERS, rowData.get(42));
                obj.put(WITH_WAIVERS, rowData.get(43));
                obj.put(COLLATERAL_TYPE, rowData.get(44));
                obj.put(SECURITY_CODE, rowData.get(45));

                jsonArrayAutomobileRet.clear();
                jsonArrayAutomobileRet.add(obj);
                iFormObj.addDataToGrid("tblAutomobileSecuritySME", jsonArrayAutomobileRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_AutomobileRet", e);
        }
    }

    private static void fetchSmePlotDetailsData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayPlotDetails = new JSONArray();
        try {
            String query = "select NoofPlots,RoadAccess,QualityAccessRoad,WidthRoad,Frontage,RemarksPlot  from CS_PlotDetails with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("No of Plots", rowData.get(0));
                obj.put("Road Access", rowData.get(1));
                obj.put("Quality of Access road", rowData.get(2));
                obj.put("Width of Road", rowData.get(3));
                obj.put("Frontage", rowData.get(4));
                obj.put(REMARKS, rowData.get(5));

                jsonArrayPlotDetails.clear();
                jsonArrayPlotDetails.add(obj);
                iFormObj.addDataToGrid("tblPlotDetails", jsonArrayPlotDetails);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_PlotDetails", e);
        }
    }

    private static void fetchSmeRealEstatePropertyData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayRealEstatePropertyRet = new JSONArray();
        try {
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
                    + "CSVRRemarks,GoogleMap,PlotNo13,InsuranceRequired,InsuranceExpirydate,Longitude from CS_RealEstatePropertyRet with(nolock) where PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);
            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("CSVR ID", rowData.get(0));
                obj.put("Nature of Charge", rowData.get(1));
                obj.put(OTHER_NATURE_OF_CHARGE, rowData.get(2));
                obj.put(COLLATERAL_TYPE, rowData.get(3));
                obj.put(SECURITY_TYPE, rowData.get(4));
                obj.put(SUB_SECURITY_TYPE, rowData.get(5));
                obj.put(DESCRIPTION, rowData.get(6));
                obj.put(SECURITY_CODE, rowData.get(7));
                obj.put("Property Type", rowData.get(8));
                obj.put("Is the Collateral Shared?", rowData.get(9));
                obj.put("Facilities", rowData.get(10));
                obj.put("Utilized Fair Market Value(FMV)", rowData.get(11));
                obj.put("Utilized Distress Value(DV)", rowData.get(12));
                obj.put(REMARKS, rowData.get(13));
                obj.put("Current Owner", rowData.get(14));
                obj.put("New Owner", rowData.get(15));
                obj.put("Relation to Borrower", rowData.get(16));
                obj.put("Area (Land)", rowData.get(17));
                obj.put("Number of Plots", rowData.get(18));
                obj.put("Plot Number (Land)", rowData.get(19));
                obj.put("Area Plot 1", rowData.get(20));
                obj.put("Area of Plot", rowData.get(21));
                obj.put("Plot Number (Land) 2", rowData.get(22));
                obj.put("Area Plot 2", rowData.get(23));
                obj.put("Area of Plot 2", rowData.get(24));
                obj.put("Plot Number (Land) 3", rowData.get(25));
                obj.put("Area Plot 3", rowData.get(26));
                obj.put("Area of Plot 3", rowData.get(27));
                obj.put("Plot Number (Land) 4", rowData.get(28));
                obj.put("Area Plot 4", rowData.get(29));
                obj.put("Area of Plot 4", rowData.get(30));
                obj.put("Plot Number (Land) 5", rowData.get(31));
                obj.put("Area Plot 5", rowData.get(32));
                obj.put("Area of Plot 5", rowData.get(33));
                obj.put("Plot Number (Land) 6", rowData.get(34));
                obj.put("Area Plot 6", rowData.get(35));
                obj.put("Area of Plot 6", rowData.get(36));
                obj.put("Plot Number (Land) 7", rowData.get(37));
                obj.put("Area Plot 7", rowData.get(38));
                obj.put("Area of Plot 7", rowData.get(39));
                obj.put("Plot Number (Land) 8", rowData.get(40));
                obj.put("Area Plot 8", rowData.get(41));
                obj.put("Area of Plot 8", rowData.get(42));
                obj.put("Plot Number (Land) 9", rowData.get(43));
                obj.put("Area Plot 9", rowData.get(44));
                obj.put("Area of Plot 9", rowData.get(45));
                obj.put("Plot Number (Land) 10", rowData.get(46));
                obj.put("Area Plot 10", rowData.get(47));
                obj.put("Area of Plot 10", rowData.get(48));
                obj.put("Total Area of Plot", rowData.get(49));
                obj.put("Ropani", rowData.get(50));
                obj.put("Aana", rowData.get(51));
                obj.put("Paisa", rowData.get(52));
                obj.put("Total R-A-P-D", rowData.get(53));
                obj.put("Bigha", rowData.get(54));
                obj.put("Kattha", rowData.get(55));
                obj.put("Dhur", rowData.get(56));
                obj.put("Total B-K-D-H", rowData.get(57));
                obj.put("Total Area of Land in Square Meters", rowData.get(58));
                obj.put("District", rowData.get(59));
                obj.put("Municipality", rowData.get(60));
                obj.put("Location as per LOC", rowData.get(61));
                obj.put("Ownership Type", rowData.get(62));
                obj.put("Status of Bank's Mortgage Charge (Registered or Unregistered)", rowData.get(63));
                obj.put("Latest Date of Execution/Registration of Charge", rowData.get(64));
                obj.put("Mortgage Amount", rowData.get(65));
                obj.put("Apartment Name", rowData.get(66));
                obj.put("Area of Building, Apartment (Existing and/or Proposed)", rowData.get(67));
                obj.put("Storey (Existing and/or Proposed)", rowData.get(68));
                obj.put("Plot Number (Constructed Area)", rowData.get(69));
                obj.put("Status of Bank's Mortgage Charge", rowData.get(70));
                obj.put("Latest Date of Execution / Registration of Charge", rowData.get(71));
                obj.put(BASIS_OF_VALUATION, rowData.get(72));
                obj.put("Valued By", rowData.get(73));
                obj.put("RemarksValuation", rowData.get(74));
                obj.put("Date of Valuation", rowData.get(75));
                obj.put("Fair Market Value", rowData.get(76));
                obj.put("Distress Value", rowData.get(77));
                obj.put("Purchase Price", rowData.get(78));
                obj.put(INSURANCE_REQUIRED, rowData.get(79));
                obj.put(HOUSE, rowData.get(80));
                obj.put(FIRE, rowData.get(81));
                obj.put(BUGLARY, rowData.get(82));
                obj.put(TERRORISM, rowData.get(83));
                obj.put(EARTHQUAKE, rowData.get(84));
                obj.put(VEHICLE, rowData.get(85));
                obj.put(THIRD_PARTY, rowData.get(86));
                obj.put(COMPREHENSIVE, rowData.get(87));
                obj.put(ACCIDENTAL, rowData.get(88));
                obj.put("Marine", rowData.get(89));
                obj.put(FLOOD, rowData.get(90));
                obj.put("Land Slide", rowData.get(91));
                obj.put(WITH_WAIVERS, rowData.get(92));
                obj.put(AMOUNT_OF_INSURANCE, rowData.get(93));
                obj.put(INSURANCE_COVERAGE_START_DATE, rowData.get(94));
                obj.put(INSURANCE_EXPIRY_DATE, rowData.get(95));
                obj.put(DETAIL_OF_WAIVERS, rowData.get(96));
                obj.put("Date of Inspection/Site Visit", rowData.get(97));
                obj.put("Name of the Inspecting Official/s", rowData.get(98));
                obj.put("Designation of the Inspecting Official/s", rowData.get(99));
                obj.put("Date of Transfer of Title (Land)", rowData.get(100));
                obj.put("Mode of Transfer of Title (Land)", rowData.get(101));
                obj.put("Mode of Transfer of Title (Other)", rowData.get(102));
                obj.put("Land Category (Utility)", rowData.get(103));
                obj.put("Land Category (Other)", rowData.get(104));
                obj.put("Quality of Access Road", rowData.get(105));
                obj.put("Quality of Access Road (Other)", rowData.get(106));
                obj.put("Width of the Road", rowData.get(107));
                obj.put("Road Access", rowData.get(108));
                obj.put("Shape of the Land", rowData.get(109));
                obj.put("Shape of Land (Other)", rowData.get(110));
                obj.put("Distance of the Location from the Branch in km", rowData.get(111));
                obj.put("Building Category (Utility)", rowData.get(112));
                obj.put("Building Category (Other)", rowData.get(113));
                obj.put("Construction Quality", rowData.get(114));
                obj.put("Construction Quality (Other)", rowData.get(115));
                obj.put("Date of permission for construction certificate / Nirman Ijjajat", rowData.get(116));
                obj.put("Date of building completion certificate / Nirman Sampanna", rowData.get(117));
                obj.put("Land Ownership Certificate / Lalpurja", rowData.get(118));
                obj.put("Valuation Report", rowData.get(119));
                obj.put("Four Boundary / Char Killa", rowData.get(120));
                obj.put("Trace Map / Blue Print", rowData.get(121));
                obj.put("Ownership Transfer Document (Rajinama, Bakas Patra, etc.)", rowData.get(122));
                obj.put("Land Revenue Report (Tiro)/Property Tax Paid Receipt", rowData.get(123));
                obj.put("Registered Mortgage Deed", rowData.get(124));
                obj.put("Internal Mortgage Deed (For Unregistered or Additional Charge)", rowData.get(125));
                obj.put("Rokka Letter from the Concerned Malpot Office", rowData.get(126));
                obj.put("Rokka Letter from the Concerned Guthi", rowData.get(127));
                obj.put("Permission for Construction Certificate / Nirman Ijjajat", rowData.get(128));
                obj.put("Building Completion Certificate / Nirman Sampanna", rowData.get(129));
                obj.put("Citizenship Certificate/Firm Registration Certificate of Owner", rowData.get(130));
                obj.put("Architect's design document of the building / Ghar Naksa", rowData.get(131));
                obj.put(INSURANCE_POLICY_WITH_PREMIUM_PAID_RECEIPT, rowData.get(132));
                obj.put("1. The Land is not vertically sloped", rowData.get(133));
                obj.put("2. The land is not within 60 meters’ distance from RIVER/FOREST/P", rowData.get(134));
                obj.put("3. The land is not within 100 meters’ distance from CONSERVATION ", rowData.get(135));
                obj.put("4. The land is not within 500 meters’ distance from NO MAN’S LAND", rowData.get(136));
                obj.put("5. The land doesn’t have POND/POOL within valuation considered ar", rowData.get(137));
                obj.put("6. The location and shape of the land appear consistent with Topo", rowData.get(138));
                obj.put("7. The land does not have potential ROW setback", rowData.get(139));
                obj.put("8. There aren't any river/canal setback", rowData.get(140));
                obj.put("9. There aren't any high-tension wire setback", rowData.get(141));
                obj.put("10. There aren't any other setbacks", rowData.get(142));
                obj.put("11. The setbacks would not affect the shape of the land", rowData.get(143));
                obj.put("12. The remaining area of the land after the setback adjustments ", rowData.get(144));
                obj.put("13. The setbacks would not affect value of the land", rowData.get(145));
                obj.put("14. The setbacks would not affect salability of the land", rowData.get(146));
                obj.put("15. The valuation report has made appropriate deductions on accou", rowData.get(147));
                obj.put("16. No of storey matches with the construction approval", rowData.get(148));
                obj.put("17. Is there a boundary wall in the property?", rowData.get(149));
                obj.put("18. Is there water supply connection in the property?", rowData.get(150));
                obj.put("19. Is there electricity connection in the property?", rowData.get(151));
                obj.put("20. Is there a drainage connection in the property?", rowData.get(152));
                obj.put("21. Blue Print and Trace Map has been obtained by the Bank staff ", rowData.get(153));
                obj.put("22. To the best of my/our knowledge, the shape, area and size of ", rowData.get(154));
                obj.put("23. I/we have enquired with family member of collateral owner as ", rowData.get(155));
                obj.put("24. I/we have reviewed the valuation report which, I /we am/are s", rowData.get(156));
                obj.put("25. I/we consider the Fair Market Value of Preliminary/ Full Valu", rowData.get(157));
                obj.put("26. I/we consider the property is acceptable for mortgage to Bank", rowData.get(158));
                obj.put("Revised CSVR is uploaded", rowData.get(159));
                obj.put("Full format CSVR is uploaded", rowData.get(160));
                obj.put("Remarksforquestionno27", rowData.get(161));
                obj.put("SiteImage", rowData.get(162));
                obj.put("Latitude", rowData.get(163));
                obj.put("CSVR Remarks", rowData.get(164));
                obj.put("Google Map", rowData.get(165));
                obj.put("I certify that the above mentioned details of real estate propert", rowData.get(166));
                obj.put(INSURANCE_REQUIRED, rowData.get(167));
                obj.put(INSURANCE_EXPIRY_DATE, rowData.get(168));
                obj.put("Longitude", rowData.get(169));


                jsonArrayRealEstatePropertyRet.clear();
                jsonArrayRealEstatePropertyRet.add(obj);
                iFormObj.addDataToGrid("tblRealEstateSecuritySME", jsonArrayRealEstatePropertyRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_RealEstatePropertyRet", e);

        }
    }

    private static void fetchSmeCustomizedInstallmentData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayCustomizedInstallmentRet = new JSONArray();

        try {
            String query = "select Installment,InstallmentDueDays,Amount from CS_CustomizedInstallmentRet with(nolock) where  PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);
            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Installment", rowData.get(0));
                obj.put("Installment Due (Days from Drawdown)", rowData.get(1));
                obj.put(AMOUNT, rowData.get(2));

                jsonArrayCustomizedInstallmentRet.clear();
                jsonArrayCustomizedInstallmentRet.add(obj);
                iFormObj.addDataToGrid("tblCustomizedInstallmentSME", jsonArrayCustomizedInstallmentRet);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_CustomizedInstallmentRet", e);
        }
    }

    private static void fetchSmeFacilitiesData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayFacilitiesSME = new JSONArray();

        try {
            String query = "select IDFacility,TypeOfFacility,Exposure,CreditFacility,RefDesc,PurposeforCreditFac,OtherCreditFacility,"
                    + "OtherPurpose,Class,RiskWeightage,STRCharge,NatureOfFacility,FacilityForInnerLimit,RelatedFacID,Limit,Outstanding,"
                    + "OverduePrincipal,OverdueInterest,IncreaseRequired,ProposedLimit,FinalDateOfRepayment,TenureFirstDrawdown,"
                    + "TenureOfEachLoan,TenureExpiryDate,NewLimitAfterIncrease,BaseRate,Premium,InterestRate,CommitmentFee,"
                    + "LoanAdminFee,PrepaymentFee,LCCommission,GuaranteeCommission,AcceptanceFee,BillsPurchase,ProposedMargin,"
                    + "Commission,TypeOfInstallment,Frequency,FirstInstallmentDue,AddInstallment,Amount,Amount1,GracePeriod,"
                    + "Mode,TotalEstdCost,BorrowingPower,NetAmountBorrowed,DetaiilitemImported,SourceRepayment,PaymentMethod,"
                    + "Usanceperiod,OtherUsancePeriod,DeferredPaymentMonths,CashMargin,PurposeTransactionDet,OtherOneoffPurpose,"
                    + "MaxTenureGurantee,JustificationFacilities,OtherChargersWavers,ExistingOrNewFac,rate,"
                    + "AdditionalMargin,DPMargin from CS_FacilitiesSME with(nolock) where  PID='" + pid + "'";

            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);
            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Facility ID", rowData.get(0));
                obj.put("Type of Facility", rowData.get(1));
                obj.put("Exposure", rowData.get(2));
                obj.put("Credit Facility", rowData.get(3));
                obj.put("REF_DESC", rowData.get(4));
                obj.put("Purpose(For Selected Credit Facility)", rowData.get(5));
                obj.put("Other Credit Facility", rowData.get(6));
                obj.put("Other Purpose", rowData.get(7));
                obj.put("Class", rowData.get(8));
                obj.put("Risk Weightage", rowData.get(9));
                obj.put("Risk Classification", rowData.get(10));
                obj.put("Nature of Facility", rowData.get(11));
                obj.put("Identification of Primary Facility for Inner Limit", rowData.get(12));
                obj.put("Related Facility ID", rowData.get(13));
                obj.put("Limit", rowData.get(14));
                obj.put("Outstanding", rowData.get(15));
                obj.put("Overdue Principal (if any)", rowData.get(16));
                obj.put("Overdue Interest (if any)", rowData.get(17));
                obj.put("Change in Limit required?", rowData.get(18));
                obj.put("Proposed Limit", rowData.get(19));
                obj.put("Tenure (Final Date of Repayment or expiry)", rowData.get(20));
                obj.put("Tenure (Months from First Drawdown)", rowData.get(21));
                obj.put("Tenure of each loan (days)", rowData.get(22));
                obj.put("Tenure (Expiry Date)", rowData.get(23));
                obj.put("New Proposed Limit (After Increase)", rowData.get(24));
                obj.put("Base Rate", rowData.get(25));
                obj.put("Premium", rowData.get(26));
                obj.put("Interest Rate (Base Rate +/-) ", rowData.get(27));
                obj.put("Commitment Fee", rowData.get(28));
                obj.put("Loan Administration Fee (%)", rowData.get(29));
                obj.put("Prepayment Fee", rowData.get(30));
                obj.put("LC Commission", rowData.get(31));
                obj.put("Gurantee Commission", rowData.get(32));
                obj.put("Acceptance Fee/Commission", rowData.get(33));
                obj.put("Bills Purchase/ Discount Commission for Interest", rowData.get(34));
                obj.put("Proposed Margin", rowData.get(35));
                obj.put("Commission", rowData.get(36));
                obj.put("Type of Installment", rowData.get(37));
                obj.put("Frequency", rowData.get(38));
                obj.put("First Installment Due (Days from First Drawdown)", rowData.get(39));
                obj.put("Add Installment (Days from Previous Installment)", rowData.get(40));
                obj.put(AMOUNT, rowData.get(41));
                obj.put("Amount1", rowData.get(42));
                obj.put("Grace Period (Moratorium) - Days", rowData.get(43));
                obj.put("Mode", rowData.get(44));
                obj.put("Total estimated cost of the project or asset/s", rowData.get(45));
                obj.put("Borrowing Power", rowData.get(46));
                obj.put("Net Amount that can be borrowed", rowData.get(47));
                obj.put("Detail of item being imported", rowData.get(48));
                obj.put("Source of repayment", rowData.get(49));
                obj.put("Payment Method", rowData.get(50));
                obj.put("Usance Period", rowData.get(51));
                obj.put("Other Usance Period", rowData.get(52));
                obj.put("Deferred Payment (Months)", rowData.get(53));
                obj.put("Cash Margin", rowData.get(54));
                obj.put("Purpose(one-off basis)", rowData.get(55));
                obj.put("Other Purpose(one-off)", rowData.get(56));
                obj.put("Maximum Tenure of Guarantee (Days)", rowData.get(57));
                obj.put("Justification Of Limit", rowData.get(58));
                obj.put("REF_DESC_copy", rowData.get(59));
                obj.put("Relationship", rowData.get(60));
                obj.put("Rate", rowData.get(61));
                obj.put("AdditionalMargin", rowData.get(62));
                obj.put("DP Margin", rowData.get(63));

                jsonArrayFacilitiesSME.clear();
                jsonArrayFacilitiesSME.add(obj);
                iFormObj.addDataToGrid("tblFacilitiesSME", jsonArrayFacilitiesSME);
                LogMessages.statusLog.info(DATA_ADDED_TO_THE_GRID);
            }
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_FacilitiesSME", e);

        }
    }

    private static void fetchSmeKeyPersonCitizenDetailsData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayKeyPersonCitizenDetailsRet = new JSONArray();

        try {
            String query = "select Name,CitizenshipNo,IssuedBy,IssuedDate from CS_KeyPersonCitizenDetailsRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + query);

            List<List<String>> queryList = iFormObj.getDataFromDB(query);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Name", rowData.get(0));
                obj.put("Citizenship Number", rowData.get(1));
                obj.put("Issued By (District)", rowData.get(2));
                obj.put("Issued Date", rowData.get(3));

                jsonArrayKeyPersonCitizenDetailsRet.clear();
                jsonArrayKeyPersonCitizenDetailsRet.add(obj);
                iFormObj.addDataToGrid("tblCitizenshipDetailsProfitInst", jsonArrayKeyPersonCitizenDetailsRet);
                LogMessages.statusLog.info("*** Data added to the grid ***");
            }

        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_KeyPersonCitizenDetailsRet", e);
        }
    }

    private static void fetchSmeKeyPersonsDetailsData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayKeyPersonsDetailsSME = new JSONArray();

        try {
            String keyPersonsDetailsQuery = "select Name,CitizenshipNo,IssuedDateBS,IssuedDate,IssuedBy,"
                    + "PassportNoKeyPerson,DrivingLicenseNoKeyPerson,EmployeeIdNoKeyPerson,"
                    + "OtherIdentificationKeyPerson,StatusForProfitInst,IsGuarantor from CS_KeyPersonsDetailsSME with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + keyPersonsDetailsQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(keyPersonsDetailsQuery);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Name", rowData.get(0));
                obj.put("Citizenship No.", rowData.get(1));
                obj.put("Issued Date BS", rowData.get(2));
                obj.put("Issued Date", rowData.get(3));
                obj.put("Issued By (District)", rowData.get(4));
                obj.put("Passport No.", rowData.get(5));
                obj.put("Driving License No.", rowData.get(6));
                obj.put("Employee Identity No.", rowData.get(7));
                obj.put("Other", rowData.get(8));
                obj.put("Status", rowData.get(9));
                obj.put("Is Guarantor", rowData.get(10));

                jsonArrayKeyPersonsDetailsSME.clear();
                jsonArrayKeyPersonsDetailsSME.add(obj);
                iFormObj.addDataToGrid("tblKeyPersonSME", jsonArrayKeyPersonsDetailsSME);
            }

        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_KeyPersonsDetailsSME", e);
        }
    }

    private static void fetchSmeShareholdingDetailsData(IFormReference iFormObj, String pid) {
        JSONArray jsonArrayShareholdingDetailsRet = new JSONArray();
        try {
            String shareholdingDetailsQuery = "select Name,Holding,FathersName,GrandFathersName,SpouseName,CitizenshipNo,"
                    + "CitizenshipIssuedDate,CitizenshipIssuedDistrict,PassportNo,DOB,Gender,"
                    + "IsGuarantor,CIC_required,Amount,Networth from CS_ShareholdingDetailsRet with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info(QUERY + shareholdingDetailsQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(shareholdingDetailsQuery);
            LogMessages.statusLog.info(QUERY_LIST + queryList);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info(ROW_DATA + rowData);

                JSONObject obj = new JSONObject();
                obj.put("Name", rowData.get(0));
                obj.put("Holding (%)", rowData.get(1));
                obj.put("Father Name", rowData.get(2));
                obj.put("Grand Father Name", rowData.get(3));
                obj.put("Spouse Name", rowData.get(4));
                obj.put("Citizenship No.", rowData.get(5));
                obj.put("Citizenship Issued Date", rowData.get(6));
                obj.put("Citizenship Issued District", rowData.get(7));
                obj.put("Passport Number", rowData.get(8));
                obj.put("Date of Birth", rowData.get(9));
                obj.put("Gender", rowData.get(10));
                obj.put("Is Guarantor", rowData.get(11));
                obj.put("Is CIC Required?", rowData.get(12));
                obj.put(AMOUNT, rowData.get(13));
                obj.put("Networth of the Guarantor", rowData.get(14));


                jsonArrayShareholdingDetailsRet.clear();
                jsonArrayShareholdingDetailsRet.add(obj);
                iFormObj.addDataToGrid("tblShareholdingSME", jsonArrayShareholdingDetailsRet);
            }

        } catch (Exception e) {
            LogMessages.errorLog.info("Exception found CS_ShareholdingDetailsRet", e);
        }
    }

    private static void fetchSmeLimitMaintenanceData(IFormReference iFormObj, String pid, JSONArray smeReviewRenewDataArray) {
        try {
            String smeLimitMaintenanceQuery = "select prioritysector,industrysector from TBL_LimitMaintenance  with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info("SME limitmaintenance data fetch query: " + smeLimitMaintenanceQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(smeLimitMaintenanceQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of SME limitmaintenance data: " + rowData);

                JSONObject smeLimitMaintenanceData = new JSONObject();
                smeLimitMaintenanceData.put("prioritysector", rowData.get(0));
                smeLimitMaintenanceData.put("industrysector", rowData.get(1));

                smeReviewRenewDataArray.add(smeLimitMaintenanceData);
                LogMessages.statusLog.info("*** All Data Of SME limitmaintenance fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all limitmaintenance of SME ***");

        }
    }

    private static void fetchSmeListBoxData(IFormReference iFormObj, String pid, JSONArray smeReviewRenewDataArray) {
        try {
            String smeListBoxQuery = "select Purposefacility  from CS_OtherVariables  with(nolock) where  PID='" + pid + "'";
            LogMessages.statusLog.info("SME listbox data fetch query: " + smeListBoxQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(smeListBoxQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of SME listbox data: " + rowData);

                JSONObject smeListBoxData = new JSONObject();
                smeListBoxData.put("Purposefacility", rowData.get(0));

                smeReviewRenewDataArray.add(smeListBoxData);
                LogMessages.statusLog.info("*** All Data Of SME listbox fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all listbox of SME ***");
        }
    }

    private static void fetchSmeCheckboxData(IFormReference iFormObj, String applicationNo, JSONArray smeReviewRenewDataArray) {
        try {
            String smeCheckboxQuery = "select SameAsPermAddress,Flag3,FactorySameAsReg,RealEstate,Automobile,Inventories,"
                    + "AccountReceivables,MovableAssets,FixedDeposit,BankDeposit,GovSecurities,CorpBonds,ListedSecurities,ValuableItems,"
                    + "OtherSecurity,limittreecheckbox,disbursement from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";
            LogMessages.statusLog.info("SME checkbox data fetch query: " + smeCheckboxQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(smeCheckboxQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of SME checkbox data: " + rowData);

                JSONObject smeCheckboxData = new JSONObject();
                smeCheckboxData.put("SameAsPermAddress", rowData.get(0));
                smeCheckboxData.put("Flag3", rowData.get(1));
                smeCheckboxData.put("FactorySameAsReg", rowData.get(2));
                smeCheckboxData.put("RealEstate", rowData.get(3));
                smeCheckboxData.put("Automobile", rowData.get(4));
                smeCheckboxData.put("Inventories", rowData.get(5));
                smeCheckboxData.put("AccountReceivables", rowData.get(6));
                smeCheckboxData.put("MovableAssets", rowData.get(7));
                smeCheckboxData.put("FixedDeposit", rowData.get(8));
                smeCheckboxData.put("BankDeposit", rowData.get(9));
                smeCheckboxData.put("GovSecurities", rowData.get(10));
                smeCheckboxData.put("CorpBonds", rowData.get(11));
                smeCheckboxData.put("ListedSecurities", rowData.get(12));
                smeCheckboxData.put("ValuableItems", rowData.get(13));
                smeCheckboxData.put("OtherSecurity", rowData.get(14));
                smeCheckboxData.put("limittreecheckbox", rowData.get(15));
                smeCheckboxData.put("disbursement", rowData.get(16));

                smeReviewRenewDataArray.add(smeCheckboxData);
                LogMessages.statusLog.info("*** All Data Of SME checkbox fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all checkbox of SME ***");

        }
    }

    private static void fetchSmeTextAreaData(IFormReference iFormObj, String applicationNo, JSONArray smeReviewRenewDataArray) {
        try {
            String textAreaQuery = " select BusinessNatureRemarks2,OtherChargersWavers,SecurityVariationRemarks,"
                    + "CAScommentsCAS,ROCommentsCAS,CommentCLAD from TBL_EXT_LOS with(nolock)where ApplicationNo='" + applicationNo + "'";
            LogMessages.statusLog.info("TextArea data fetch query: " + textAreaQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(textAreaQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of textarea data: " + rowData);

                JSONObject textAreaData = new JSONObject();
                textAreaData.put("BusinessNatureRemarks2", rowData.get(0));
                textAreaData.put("OtherChargersWavers", rowData.get(1));
                textAreaData.put("SecurityVariationRemarks", rowData.get(2));
                textAreaData.put("CAScommentsCAS", rowData.get(3));
                textAreaData.put("ROCommentsCAS", rowData.get(4));
                textAreaData.put("CommentCLAD", rowData.get(5));

                smeReviewRenewDataArray.add(textAreaData);
                LogMessages.statusLog.info("*** All textarea_data of SME fetched successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all textarea_data of SME ***");
        }
    }

    private static void fetchSmeTextareaLongData(IFormReference iFormObj, String pid, JSONArray smeReviewRenewDataArray) {
        try {
            String textareaLongQuery = " select modeofdisbursement,repaymentfield,financialprojectedremarks,"
                    + "financialworkingremarks,financialadditionalremarks,remarksofcompany,remarksofowner,remarksdowngrade,justificationriskass,"
                    + "remarksfinancialcovenant,remarksfinannoncovenant,remarksbusinessrisk,remarksfinancialrisk,remarksmanagementrisk,remarkssecurityrisk,"
                    + "remarksphysicalrisk,remarksnonphysicalrisk,remarkssecuritydocument  from CS_SMELongRemarks  with(nolock) where PID='" + pid + "'";
            LogMessages.statusLog.info("TextAreaLong data fetch query: " + textareaLongQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(textareaLongQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of textarealong data: " + rowData);

                JSONObject textarealongData = new JSONObject();
                textarealongData.put("modeofdisbursement", rowData.get(0));
                textarealongData.put("repaymentfield", rowData.get(1));
                textarealongData.put("financialprojectedremarks", rowData.get(2));
                textarealongData.put("financialworkingremarks", rowData.get(3));
                textarealongData.put("financialadditionalremarks", rowData.get(4));
                textarealongData.put("remarksofcompany", rowData.get(5));
                textarealongData.put("remarksofowner", rowData.get(6));
                textarealongData.put("remarksdowngrade", rowData.get(7));
                textarealongData.put("justificationriskass", rowData.get(8));
                textarealongData.put("remarksfinancialcovenant", rowData.get(9));
                textarealongData.put("remarksfinannoncovenant", rowData.get(10));
                textarealongData.put("remarksbusinessrisk", rowData.get(11));
                textarealongData.put("remarksfinancialrisk", rowData.get(12));
                textarealongData.put("remarksmanagementrisk", rowData.get(13));
                textarealongData.put("remarkssecurityrisk", rowData.get(14));
                textarealongData.put("remarksphysicalrisk", rowData.get(15));
                textarealongData.put("remarksnonphysicalrisk", rowData.get(16));
                textarealongData.put("remarkssecuritydocument", rowData.get(17));


                smeReviewRenewDataArray.add(textarealongData);
                LogMessages.statusLog.info("*** All textarealong_data(CS_SMELongRemarks) of SME fetched successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all textarealong_data of SME ***");
        }
    }

    private static void fetchSmeDateData(IFormReference iFormObj, String applicationNo, JSONArray smeReviewRenewDataArray) {
        try {
            String dateQuery = " select ApplicationDate,DateAccountOpened,DateFacsFirstSanction,LastSancDate,DOBIndividual,"
                    + "IssuedDate,PassportIssuedDate,PassportExpiryDate,DateRegisteredInst,CFRExpDate,NextReviewDate,"
                    + "DateOfBirthCIC,CitizenshipIssuedDateCIC,PassportIssuedDateCIC,PassportExpiryDateCIC,CompanyRegDateCIC,"
                    + "IndianEmbIssuedDate,PANIssuedDate,InstIndianEmbassyIssuedDate,lastSanctionLetterDate,"
                    + "ExistingReviewDate,DateofApprovalPstApprvl,DateofApplicationPstApprvl,RegistrationExpiryDatePstApp from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";
            LogMessages.statusLog.info("date data fetch query: " + dateQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(dateQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of date: " + rowData);

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
                dateData.put("CFRExpDate", customDate(rowData.get(9)));
                dateData.put("NextReviewDate", customDate(rowData.get(10)));
                dateData.put("DateOfBirthCIC", customDate(rowData.get(11)));
                dateData.put("CitizenshipIssuedDateCIC", customDate(rowData.get(12)));
                dateData.put("PassportIssuedDateCIC", customDate(rowData.get(13)));
                dateData.put("PassportExpiryDateCIC", customDate(rowData.get(14)));
                dateData.put("CompanyRegDateCIC", customDate(rowData.get(15)));
                dateData.put("IndianEmbIssuedDate", customDate(rowData.get(16)));
                dateData.put("PANIssuedDate", customDate(rowData.get(17)));
                dateData.put("InstIndianEmbassyIssuedDate", customDate(rowData.get(18)));
                dateData.put("lastSanctionLetterDate", customDate(rowData.get(19)));
                dateData.put("ExistingReviewDate", customDate(rowData.get(20)));
                dateData.put("DateofApprovalPstApprvl", customDate(rowData.get(21)));
                dateData.put("DateofApplicationPstApprvl", customDate(rowData.get(22)));
                dateData.put("RegistrationExpiryDatePstApp", customDate(rowData.get(23)));

                smeReviewRenewDataArray.add(dateData);
                LogMessages.statusLog.info("*** All Data Of date fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all date field of SME ***");
        }
    }

    private static void fetchSmeComboboxData(IFormReference iFormObj, String applicationNo, JSONArray smeReviewRenewDataArray) {
        try {
            String comboboxQuery = "select Entity,BusinessNature2,Relationship,SectorGC,SubsectorGC,SubSubSector,Nationality,RagCategory,IncorporationProfitInst,"
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
                    + "GeneralCompliance25,GeneralCompliance26,auditType,Reviewer3,BoundaryWall,DecisionCAS,CompanyRegOrgCIC from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";
            LogMessages.statusLog.info("Combo box data fetch query: " + comboboxQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(comboboxQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List Object of combobox: " + rowData);

                JSONObject comboboxData = new JSONObject();
                comboboxData.put("Entity", rowData.get(0));
                comboboxData.put("BusinessNature2", rowData.get(1));
                comboboxData.put("Relationship", rowData.get(2));
                comboboxData.put("SectorGC", rowData.get(3));
                comboboxData.put("SubsectorGC", rowData.get(4));
                comboboxData.put("SubSubSector", rowData.get(5));
                comboboxData.put("Nationality", rowData.get(6));
                comboboxData.put("RagCategory", rowData.get(7));
                comboboxData.put("IncorporationProfitInst", rowData.get(8));
                comboboxData.put("IncorporationNotProfitInst", rowData.get(9));
                comboboxData.put("NatureOfActivity", rowData.get(10));
                comboboxData.put("RegisteredWithInst", rowData.get(11));
                comboboxData.put("ProvinceInstReg", rowData.get(12));
                comboboxData.put("DistrictInstReg", rowData.get(13));
                comboboxData.put("MunicipalityVDCInstReg", rowData.get(14));
                comboboxData.put("ProvinceInst", rowData.get(15));
                comboboxData.put("DistrictInst", rowData.get(16));
                comboboxData.put("MunicipalityVDCInst", rowData.get(17));
                comboboxData.put("ProvinceInstFac", rowData.get(18));
                comboboxData.put("DistrictInstFac", rowData.get(19));
                comboboxData.put("MunicipalityVDCInstFac", rowData.get(20));
                comboboxData.put("ProvinceIndPer", rowData.get(21));
                comboboxData.put("DistrictIndPer", rowData.get(22));
                comboboxData.put("MunicipalityVDCIndPer", rowData.get(23));
                comboboxData.put("ProvinceIndCur", rowData.get(24));
                comboboxData.put("DistrictIndCur", rowData.get(25));
                comboboxData.put("MunicipalityVDCIndCur", rowData.get(26));
                comboboxData.put("Gender", rowData.get(27));
                comboboxData.put("CitizenshipIssuedBy", rowData.get(28));
                comboboxData.put("Profession", rowData.get(29));
                comboboxData.put("CFRExpiryDate", rowData.get(30));
                comboboxData.put("Flag100", rowData.get(31));
                comboboxData.put("SubSegment", rowData.get(32));
                comboboxData.put("ProductType", rowData.get(33));
                comboboxData.put("cicCharge", rowData.get(34));
                comboboxData.put("strCharge", rowData.get(35));
                comboboxData.put("NatureOfCharge", rowData.get(36));
                comboboxData.put("SecurityType", rowData.get(37));
                comboboxData.put("SubSecurityType", rowData.get(38));
                comboboxData.put("SecurityList", rowData.get(39));
                comboboxData.put("SubordinationOfDebt", rowData.get(40));
                comboboxData.put("toBeUpdated", rowData.get(41));
                comboboxData.put("MLCheckBox", rowData.get(42));
                comboboxData.put("LandVerticallySloped", rowData.get(43));
                comboboxData.put("LandWithinDasgaja", rowData.get(44));
                comboboxData.put("LandWithinRiverForest", rowData.get(45));
                comboboxData.put("RelationshipRiskAss", rowData.get(46));
                comboboxData.put("WellEstd", rowData.get(47));
                comboboxData.put("IndustryBusiness", rowData.get(48));
                comboboxData.put("GrowthProspects", rowData.get(49));
                comboboxData.put("OverallMarket", rowData.get(50));
                comboboxData.put("MarketShare", rowData.get(51));
                comboboxData.put("BrandStrength", rowData.get(52));
                comboboxData.put("DistributionInfra", rowData.get(53));
                comboboxData.put("DistributionNetwork", rowData.get(54));
                comboboxData.put("InnoTrackRecord", rowData.get(55));
                comboboxData.put("DiversityOfProduct", rowData.get(56));
                comboboxData.put("DiscFactorBusiness", rowData.get(57));
                comboboxData.put("DisclouresSME", rowData.get(58));
                comboboxData.put("CurrentRatio", rowData.get(59));
                comboboxData.put("TOLNetWorth", rowData.get(60));
                comboboxData.put("LongTermDebtSME", rowData.get(61));
                comboboxData.put("ReturnOnSales", rowData.get(62));
                comboboxData.put("DebtServicingRatioSME", rowData.get(63));
                comboboxData.put("AbilityToGenFund", rowData.get(64));
                comboboxData.put("DiscFactorFinancial", rowData.get(65));
                comboboxData.put("AdherenceToLaw", rowData.get(66));
                comboboxData.put("CICReport", rowData.get(67));
                comboboxData.put("MultipleBanking", rowData.get(68));
                comboboxData.put("Reputation", rowData.get(69));
                comboboxData.put("GoodGovernance", rowData.get(70));
                comboboxData.put("PromoterCompetance", rowData.get(71));
                comboboxData.put("MainPromoterDealer", rowData.get(72));
                comboboxData.put("SuccessionPlan", rowData.get(73));
                comboboxData.put("QualitySeniorMgmt", rowData.get(74));
                comboboxData.put("DocumentationAccountSME", rowData.get(75));
                comboboxData.put("SubmissionReportStmt", rowData.get(76));
                comboboxData.put("InsuranceRenewalSME", rowData.get(77));
                comboboxData.put("DebtServicingModel", rowData.get(78));
                comboboxData.put("PrincipalRepayInstallment", rowData.get(79));
                comboboxData.put("TrackRecOverdue", rowData.get(80));
                comboboxData.put("UtilizationFacilities", rowData.get(81));
                comboboxData.put("RegularDeposit", rowData.get(82));
                comboboxData.put("NoRecordFrequent", rowData.get(83));
                comboboxData.put("QualityPrimarySecurity", rowData.get(84));
                comboboxData.put("AdditionalImmovalSecurityScore", rowData.get(85));
                comboboxData.put("IsExposureClassified", rowData.get(86));
                comboboxData.put("RiskSecondary", rowData.get(87));
                comboboxData.put("ESRating", rowData.get(88));
                comboboxData.put("documentUpload", rowData.get(89));
                comboboxData.put("ProductControl1", rowData.get(90));
                comboboxData.put("ProductControl2", rowData.get(91));
                comboboxData.put("ProductControl3", rowData.get(92));
                comboboxData.put("ProductControl4", rowData.get(93));
                comboboxData.put("ProductControl5", rowData.get(94));
                comboboxData.put("ProductControl6", rowData.get(95));
                comboboxData.put("ProductControl7", rowData.get(96));
                comboboxData.put("ProductControl8", rowData.get(97));
                comboboxData.put("ProductControl9", rowData.get(98));
                comboboxData.put("ProductControl10", rowData.get(99));
                comboboxData.put("ProductControl11", rowData.get(100));
                comboboxData.put("ProductControl12", rowData.get(101));
                comboboxData.put("ProductControl13", rowData.get(102));
                comboboxData.put("ProductControl14", rowData.get(103));
                comboboxData.put("ProductControl15", rowData.get(104));
                comboboxData.put("ProductControl16", rowData.get(105));
                comboboxData.put("ProductControl17", rowData.get(106));
                comboboxData.put("ProductControl59", rowData.get(107));
                comboboxData.put("ProductControl18", rowData.get(108));
                comboboxData.put("ProductControl19", rowData.get(109));
                comboboxData.put("ProductControl20", rowData.get(110));
                comboboxData.put("ProductControl21", rowData.get(111));
                comboboxData.put("ProductControl22", rowData.get(112));
                comboboxData.put("ProductControl23", rowData.get(113));
                comboboxData.put("ProductControl24", rowData.get(114));
                comboboxData.put("ProductControl25", rowData.get(115));
                comboboxData.put("ProductControl26", rowData.get(116));
                comboboxData.put("ProductControl27", rowData.get(117));
                comboboxData.put("ProductControl28", rowData.get(118));
                comboboxData.put("ProductControl29", rowData.get(119));
                comboboxData.put("ProductControl30", rowData.get(120));
                comboboxData.put("ProductControl31", rowData.get(121));
                comboboxData.put("ProductControl32", rowData.get(122));
                comboboxData.put("ProductControl33", rowData.get(123));
                comboboxData.put("ProductControl34", rowData.get(124));
                comboboxData.put("ProductControl35", rowData.get(125));
                comboboxData.put("ProductControl36", rowData.get(126));
                comboboxData.put("ProductControl37", rowData.get(127));
                comboboxData.put("ProductControl38", rowData.get(128));
                comboboxData.put("ProductControl39", rowData.get(129));
                comboboxData.put("ProductControl40", rowData.get(130));
                comboboxData.put("ProductControl41", rowData.get(131));
                comboboxData.put("ProductControl42", rowData.get(132));
                comboboxData.put("ProductControl43", rowData.get(133));
                comboboxData.put("ProductControl44", rowData.get(134));
                comboboxData.put("ProductControl45", rowData.get(135));
                comboboxData.put("ProductControl46", rowData.get(136));
                comboboxData.put("ProductControl47", rowData.get(137));
                comboboxData.put("ProductControl48", rowData.get(138));
                comboboxData.put("ProductControl49", rowData.get(139));
                comboboxData.put("ProductControl50", rowData.get(140));
                comboboxData.put("ProductControl51", rowData.get(141));
                comboboxData.put("ProductControl52", rowData.get(142));
                comboboxData.put("ProductControl53", rowData.get(143));
                comboboxData.put("ProductControl54", rowData.get(144));
                comboboxData.put("ProductControl55", rowData.get(145));
                comboboxData.put("ProductControl56", rowData.get(146));
                comboboxData.put("ProductControl57", rowData.get(147));
                comboboxData.put("ProductControl58", rowData.get(148));
                comboboxData.put("GeneralCompliance1", rowData.get(149));
                comboboxData.put("GeneralCompliance2", rowData.get(150));
                comboboxData.put("GeneralCompliance3", rowData.get(151));
                comboboxData.put("GeneralCompliance4", rowData.get(152));
                comboboxData.put("GeneralCompliance5", rowData.get(153));
                comboboxData.put("GeneralCompliance6", rowData.get(154));
                comboboxData.put("GeneralCompliance7", rowData.get(155));
                comboboxData.put("GeneralCompliance8", rowData.get(156));
                comboboxData.put("GeneralCompliance9", rowData.get(157));
                comboboxData.put("GeneralCompliance10", rowData.get(158));
                comboboxData.put("GeneralCompliance11", rowData.get(159));
                comboboxData.put("GeneralCompliance12", rowData.get(160));
                comboboxData.put("GeneralCompliance13", rowData.get(161));
                comboboxData.put("GeneralCompliance14", rowData.get(162));
                comboboxData.put("GeneralCompliance15", rowData.get(163));
                comboboxData.put("GeneralCompliance16", rowData.get(164));
                comboboxData.put("GeneralCompliance17", rowData.get(165));
                comboboxData.put("GeneralCompliance18", rowData.get(166));
                comboboxData.put("GeneralCompliance19", rowData.get(167));
                comboboxData.put("GeneralCompliance20", rowData.get(168));
                comboboxData.put("GeneralCompliance21", rowData.get(169));
                comboboxData.put("GeneralCompliance22", rowData.get(170));
                comboboxData.put("GeneralCompliance23", rowData.get(171));
                comboboxData.put("GeneralCompliance24", rowData.get(172));
                comboboxData.put("GeneralCompliance25", rowData.get(173));
                comboboxData.put("GeneralCompliance26", rowData.get(174));
                comboboxData.put("auditType", rowData.get(175));
                comboboxData.put("Reviewer3", rowData.get(176));
                comboboxData.put("BoundaryWall", rowData.get(177));
                comboboxData.put("DecisionCAS", rowData.get(178));
                comboboxData.put("CompanyRegOrgCIC", rowData.get(179));

                LogMessages.statusLog.info("shivaraj sme_review_renew_data_array, SMEAllDataFetch : " + comboboxData);
                smeReviewRenewDataArray.add(comboboxData);
                LogMessages.statusLog.info("*** All Data Of combo box fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all combo box field of SME ***");
        }
    }

    private static void fetchSmeReviewRenewData(IFormReference iFormObj, String applicationNo, JSONArray smeReviewRenewDataArray) {
        try {
            String textBoxQuery = "select CustomerName,AccountNo,Groups,GroupName,SectoralCode,CompanyRegNo,DrainageConnection,"
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
                    + "NameofBankreqtakeoverPstApprvl,AccountNumberPstApprvl,AccountBenefNamePstApprvl,RelationOfficerNamePstApprvl,GroupSheetCode,GroupSHeetName,Flag10 from TBL_EXT_LOS  with(nolock)where ApplicationNo='" + applicationNo + "'";
            LogMessages.statusLog.info("SME ALL Data Fetch query: " + textBoxQuery);

            List<List<String>> queryList = iFormObj.getDataFromDB(textBoxQuery);

            for (List<String> rowData : queryList) {
                LogMessages.statusLog.info("List MyObject of textbox: " + rowData);


                JSONObject textBoxData = new JSONObject();
                textBoxData.put("CustomerName", rowData.get(0));
                textBoxData.put("AccountNo", rowData.get(1));
                textBoxData.put("Groups", rowData.get(2));
                textBoxData.put("GroupName", rowData.get(3));
                textBoxData.put("SectoralCode", rowData.get(4));
                textBoxData.put("CompanyRegNo", rowData.get(5));
                textBoxData.put("DrainageConnection", rowData.get(6));
                textBoxData.put("IssuedDistrictPostApproval", rowData.get(7));
                textBoxData.put("PanNoInst", rowData.get(8));
                textBoxData.put("PanIssuedByInst", rowData.get(9));
                textBoxData.put("AuthorizedBodyNamePostApproval", rowData.get(10));
                textBoxData.put("Location1InstReg", rowData.get(11));
                textBoxData.put("Location2InstReg", rowData.get(12));
                textBoxData.put("HouseNoInstReg", rowData.get(13));
                textBoxData.put("StreetNameInstReg", rowData.get(14));
                textBoxData.put("Location1Inst", rowData.get(15));
                textBoxData.put("Location2Inst", rowData.get(16));
                textBoxData.put("HouseNoInst", rowData.get(17));
                textBoxData.put("StreetNameInst", rowData.get(18));
                textBoxData.put("Location1InstFac", rowData.get(19));
                textBoxData.put("Location2InstFac", rowData.get(20));
                textBoxData.put("HouseNoInstFac", rowData.get(21));
                textBoxData.put("StreetNameInstFac", rowData.get(22));
                textBoxData.put("ActNamePostApproval", rowData.get(23));
                textBoxData.put("PassportNoKeyPerson", rowData.get(24));
                textBoxData.put("DrivingLicenseNoKeyPerson", rowData.get(25));
                textBoxData.put("EmployeeIdNoKeyPerson", rowData.get(26));
                textBoxData.put("OtherIdentificationKeyPerson", rowData.get(27));
                textBoxData.put("Phone1Inst", rowData.get(28));
                textBoxData.put("Phone2Inst", rowData.get(29));
                textBoxData.put("MobileNo1Inst", rowData.get(30));
                textBoxData.put("MobileNo2Inst", rowData.get(31));
                textBoxData.put("Email1Inst", rowData.get(32));
                textBoxData.put("Email2Inst", rowData.get(33));
                textBoxData.put("Location1IndPer", rowData.get(34));
                textBoxData.put("Location2IndPer", rowData.get(35));
                textBoxData.put("StreetNameIndPer", rowData.get(36));
                textBoxData.put("HouseNoIndPer", rowData.get(37));
                textBoxData.put("Location1IndCur", rowData.get(38));
                textBoxData.put("Location2IndCur", rowData.get(39));
                textBoxData.put("StreetNameIndCur", rowData.get(40));
                textBoxData.put("HouseNoIndCur", rowData.get(41));
                textBoxData.put("BluePrintObtained", rowData.get(42));
                textBoxData.put("FathersName", rowData.get(43));
                textBoxData.put("MothersName", rowData.get(44));
                textBoxData.put("SpouseName", rowData.get(45));
                textBoxData.put("FatherInLawName", rowData.get(46));
                textBoxData.put("GrandFathersName", rowData.get(47));
                textBoxData.put("CitizenshipNo", rowData.get(48));
                textBoxData.put("ConsideredFMV", rowData.get(49));
                textBoxData.put("PassportNo", rowData.get(50));
                textBoxData.put("PassportIssuedPlace", rowData.get(51));
                textBoxData.put("DrivingLicenseNo", rowData.get(52));
                textBoxData.put("EmployeeIdNo", rowData.get(53));
                textBoxData.put("OtherIdentification", rowData.get(54));
                textBoxData.put("PanNoInd", rowData.get(55));
                textBoxData.put("PanIssuedByInd", rowData.get(56));
                textBoxData.put("Phone1Ind", rowData.get(57));
                textBoxData.put("Phone2Ind", rowData.get(58));
                textBoxData.put("MobileNo1Ind", rowData.get(59));
                textBoxData.put("MobileNo2Ind", rowData.get(60));
                textBoxData.put("Email1Ind", rowData.get(61));
                textBoxData.put("Email2Ind", rowData.get(62));
                textBoxData.put("ConsiderPropertyMortageable", rowData.get(63));
                textBoxData.put("flag101", rowData.get(64));
                textBoxData.put("ProductCode", rowData.get(65));
                textBoxData.put("PurposeHouseLoan", rowData.get(66));
                textBoxData.put("ShortTermRatingFac", rowData.get(67));
                textBoxData.put("LongTermRatingFac", rowData.get(68));
                textBoxData.put("Rorc", rowData.get(69));
                textBoxData.put("IrdSubNumber", rowData.get(70));
                textBoxData.put("TotalFundedExisting", rowData.get(71));
                textBoxData.put("TotalFundedProposed", rowData.get(72));
                textBoxData.put("TotalFundedOutstanding", rowData.get(73));
                textBoxData.put("fundedloanadminfee", rowData.get(74));
                textBoxData.put("TotalNonFundExisting", rowData.get(75));
                textBoxData.put("TotalNonFundProposed", rowData.get(76));
                textBoxData.put("TotalNonFundOutstanding", rowData.get(77));
                textBoxData.put("nonfundedloanadminfee", rowData.get(78));
                textBoxData.put("GrandTotalExisting", rowData.get(79));
                textBoxData.put("GrandTotalProposed", rowData.get(80));
                textBoxData.put("TotalLimitOutstanding", rowData.get(81));
                textBoxData.put("totallimitloanfee", rowData.get(82));
                textBoxData.put("PrimaryLimit", rowData.get(83));
                textBoxData.put("TotalLimit", rowData.get(84));
                textBoxData.put("otherCicCharge", rowData.get(85));
                textBoxData.put("OtherSTRCharge", rowData.get(86));
                textBoxData.put("OtherSecurityType", rowData.get(87));
                textBoxData.put("OtherNatureOfCharge", rowData.get(88));
                textBoxData.put("SecurityCodeGC", rowData.get(89));
                textBoxData.put("TotalFundedFacGroup", rowData.get(90));
                textBoxData.put("TotalNonFundedFacGroup", rowData.get(91));
                textBoxData.put("TotalLimitFacilityGroup", rowData.get(92));
                textBoxData.put("FMVSME", rowData.get(93));
                textBoxData.put("DVSME", rowData.get(94));
                textBoxData.put("UNITLTVSME", rowData.get(95));
                textBoxData.put("GROUPLTVSME", rowData.get(96));
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
                textBoxData.put("DocumentationOfAcc", rowData.get(111));
                textBoxData.put("CustomerIdCIC", rowData.get(112));
                textBoxData.put("CompanyNameCIC", rowData.get(113));
                textBoxData.put("CompanyRegNoCIC", rowData.get(114));
                textBoxData.put("CompanyRegOrg", rowData.get(115));
                textBoxData.put("PanNoCIC", rowData.get(116));
                textBoxData.put("PanIssuedByCIC", rowData.get(117));
                textBoxData.put("ApplicableCICCharge", rowData.get(118));
                textBoxData.put("CICReportType", rowData.get(119));
                textBoxData.put("BlacklistedCIC", rowData.get(120));
                textBoxData.put("WellEstdScore", rowData.get(121));
                textBoxData.put("IndustryBusinessScore", rowData.get(122));
                textBoxData.put("GrowthProspectsScore", rowData.get(123));
                textBoxData.put("OverallMarketScore", rowData.get(124));
                textBoxData.put("MarketShareScore", rowData.get(125));
                textBoxData.put("BrandStrengthScore", rowData.get(126));
                textBoxData.put("DistributionNetworkScore", rowData.get(127));
                textBoxData.put("InnoTrackRecordScore", rowData.get(128));
                textBoxData.put("DiversityOfProductScore", rowData.get(129));
                textBoxData.put("BusinessIndTotalScore", rowData.get(130));
                textBoxData.put("TotalScoreMarketPos", rowData.get(131));
                textBoxData.put("BusinessScoreBeforeDisc", rowData.get(132));
                textBoxData.put("DiscFactorBusinessScore", rowData.get(133));
                textBoxData.put("BusinessScoreAfterDisc", rowData.get(134));
                textBoxData.put("DisclouresScore", rowData.get(135));
                textBoxData.put("CurrentRatioScore", rowData.get(136));
                textBoxData.put("TOLNetWorthScore", rowData.get(137));
                textBoxData.put("LongTermDebtScore", rowData.get(138));
                textBoxData.put("ReturnOnSalesScore", rowData.get(139));
                textBoxData.put("DebtServicingRatioScore", rowData.get(140));
                textBoxData.put("AbilityToGenFundScore", rowData.get(141));
                textBoxData.put("FinancialscoreBeforeDisc", rowData.get(142));
                textBoxData.put("DiscFactorFinancialScore", rowData.get(143));
                textBoxData.put("TotalScoreFinanAfterDisc", rowData.get(144));
                textBoxData.put("AdherenceToLawScore", rowData.get(145));
                textBoxData.put("CICReportScore", rowData.get(146));
                textBoxData.put("MultipleBankingScore", rowData.get(147));
                textBoxData.put("ReputationScore", rowData.get(148));
                textBoxData.put("GoodGovernanceScore", rowData.get(149));
                textBoxData.put("PromoterCompetanceScore", rowData.get(150));
                textBoxData.put("MainPromoterDealerScore", rowData.get(151));
                textBoxData.put("SuccessionPlanScore", rowData.get(152));
                textBoxData.put("QualitySeniorMgmtScore", rowData.get(153));
                textBoxData.put("TotalScoreManagement", rowData.get(154));
                textBoxData.put("DocumentationScore", rowData.get(155));
                textBoxData.put("SubmissionReportStmtScore", rowData.get(156));
                textBoxData.put("InsuranceRenewalScore", rowData.get(157));
                textBoxData.put("InterestServicingScore", rowData.get(158));
                textBoxData.put("PrincipalRepayInstallmentScore", rowData.get(159));
                textBoxData.put("InterestServicingWithoutPrinScore", rowData.get(160));
                textBoxData.put("TrackRecOverdueScore", rowData.get(161));
                textBoxData.put("UtilizationFacilitiesScore", rowData.get(162));
                textBoxData.put("RegularDepositScore", rowData.get(163));
                textBoxData.put("NoRecordSecurityScore", rowData.get(164));
                textBoxData.put("NoRecordFrequentScore", rowData.get(165));
                textBoxData.put("TotalScoreAccount", rowData.get(166));
                textBoxData.put("QualityPrimarySecurityScore", rowData.get(167));
                textBoxData.put("AdditionalImmovalSecurityScore", rowData.get(168));
                textBoxData.put("TotalScoreSecurity", rowData.get(169));
                textBoxData.put("TotalScoreAll", rowData.get(170));
                textBoxData.put("TotalScoreID", rowData.get(171));
                textBoxData.put("Risk", rowData.get(172));
                textBoxData.put("LongTermRating", rowData.get(173));
                textBoxData.put("ShortTermRating", rowData.get(174));
                textBoxData.put("LongTermRatingSecondary", rowData.get(175));
                textBoxData.put("ShortTermRatingSecondary", rowData.get(176));
                textBoxData.put("DowngradeRequired", rowData.get(177));
                textBoxData.put("NewLongTermRating", rowData.get(178));
                textBoxData.put("NewShortTermRating", rowData.get(179));
                textBoxData.put(REMARKS, rowData.get(180));
                textBoxData.put("auditComment", rowData.get(181));
                textBoxData.put("currentProgress", rowData.get(182));
                textBoxData.put("Decision", rowData.get(183));
                textBoxData.put("Flag1", rowData.get(184));
                textBoxData.put("Flag2", rowData.get(185));
                textBoxData.put("entryCounter", rowData.get(186));
                textBoxData.put("initialRowCount", rowData.get(187));
                textBoxData.put("NextWorkdesk", rowData.get(188));
                textBoxData.put("IsQuery", rowData.get(189));
                textBoxData.put("QueriedBy", rowData.get(190));
                textBoxData.put("QueriedTo", rowData.get(191));
                textBoxData.put("ApproverCounter", rowData.get(192));
                textBoxData.put("APPROVALDECISION", rowData.get(193));
                textBoxData.put("ToTriggerName", rowData.get(194));
                textBoxData.put("ToTriggerEmail", rowData.get(195));
                textBoxData.put("NextWorkStep", rowData.get(196));
                textBoxData.put("PostApprovalDecision", rowData.get(197));
                textBoxData.put("MaritalStatus", rowData.get(198));
                textBoxData.put("personFromBacCom", rowData.get(199));
                textBoxData.put("IndianEmbassyNumber", rowData.get(200));
                textBoxData.put("indianEmbassyIssuedFrom", rowData.get(201));
                textBoxData.put("ActName", rowData.get(202));
                textBoxData.put("ActYear", rowData.get(203));
                textBoxData.put("AuthorizedBodyName", rowData.get(204));
                textBoxData.put("IssuedDistrict", rowData.get(205));
                textBoxData.put("InstIndianEmbassyNum", rowData.get(206));
                textBoxData.put("InstIndianEmbassyIssuedFrom", rowData.get(207));
                textBoxData.put("PrivateCommercial7Yrs", rowData.get(208));
                textBoxData.put("NatureOfInterestRate", rowData.get(209));
                textBoxData.put("SecuritytypePstApprvl", rowData.get(210));
                textBoxData.put("InsuranceAmtbuildingPstApprvl", rowData.get(211));
                textBoxData.put("NameofBankreqtakeoverPstApprvl", rowData.get(212));
                textBoxData.put("AccountNumberPstApprvl", rowData.get(213));
                textBoxData.put("AccountBenefNamePstApprvl", rowData.get(214));
                textBoxData.put("RelationOfficerNamePstApprvl", rowData.get(215));
                textBoxData.put("GroupSheetCode", rowData.get(216));
                textBoxData.put("GroupSHeetName", rowData.get(217));
                textBoxData.put("Flag10", rowData.get(218));

                smeReviewRenewDataArray.clear();
                smeReviewRenewDataArray.add(textBoxData);
                LogMessages.statusLog.info("*** All Data Of text box fetch successfully ***");
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("*** Error Occured During fetching all text box field of SME  ***");
        }
    }

}           
                
            
            
                  
            
            
                   
               
               
                   
                   
                
                
                
                
                
                           
                
            
            
                           
                           
                           
                           
                
                
                
            
            
            
                           
                           
                           
            
            
            
                    
                    
            
            
                
                
                    
                    
                               
                       
                       
                
                
                 
                 




































