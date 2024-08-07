package com.newgen.integrations;

/**
 * @author bibek.shah
 **/

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtil.getFailureResponse;
import static com.newgen.iforms.util.NewGenUtilConstants.*;
import org.json.simple.parser.JSONParser;

public class RetKYCDetails {

    public String retKYCDetailsReq( String[] param) {
        String request = "";
        try {
            request = "{\"CustId\":\"" + param[1] + "\"}";
            LogMessages.statusLog.info("Request retKYCDetailsReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception retKYCDetailsReq:  " + e);
        }
        return request;
    }
    
    public String retKYCDetailsReqCorp( String[] param) {
        String request = "";
        try {
            request = "{\"CustId\":\"" + param[1] + "\",\"AccountNumber\":\"\",\"CustomerType\":\"Corporate\"}";
            LogMessages.statusLog.info("Request retKYCDetailsReq:  " + request);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception retKYCDetailsReq:  " + e);
        }
        return request;
    }

    public JSONArray retKYCDetailsRes2(String[] param) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    String retCustInqResponse = String.valueOf(((JSONObject) obj.get("Data")).get("Result"));
                    JSONObject obj2 = NewGenUtil.getParsedJsonObj(retCustInqResponse);
                    res.put("CustomerNumber", String.valueOf(obj2.get("CustomerNumber")));
                    res.put("IsVerified", String.valueOf(obj2.get("IsVerified")));
                    res.put("FirstName", String.valueOf(obj2.get("FirstName")));
                    res.put("MiddleName", String.valueOf(obj2.get("MiddleName")));
                    res.put("LastName", String.valueOf(obj2.get("LastName")));
                    res.put("Gender", String.valueOf(obj2.get("Gender")));
                    res.put("DateOfBirth", String.valueOf(obj2.get("DateOfBirth")));
                    res.put("CountryOfBirth", String.valueOf(obj2.get("CountryOfBirth")));
                    res.put("Nationality", String.valueOf(obj2.get("Nationality")));
                    res.put("MothersMaidenName", String.valueOf(obj2.get("MothersMaidenName")));
                    res.put("MaritalStatus", String.valueOf(obj2.get("MaritalStatus")));
                    res.put("Education", String.valueOf(obj2.get("Education")));
                    res.put("Religion", String.valueOf(obj2.get("Religion")));
                    res.put("BloodGroup", String.valueOf(obj2.get("BloodGroup")));
                    res.put("FamilySize", String.valueOf(obj2.get("FamilySize")));
                    res.put("MobileNumber", String.valueOf(obj2.get("MobileNumber")));
                    res.put("PhoneNumber", String.valueOf(obj2.get("PhoneNumber")));
                    res.put("Email", String.valueOf(obj2.get("Email")));
                    res.put("EmploymentType", String.valueOf(obj2.get("EmploymentType")));
                    res.put("Occupation", String.valueOf(obj2.get("Occupation")));
                    res.put("CompanyName", String.valueOf(obj2.get("CompanyName")));
                    res.put("CitizenshipNumber", String.valueOf(obj2.get("CitizenshipNumber")));
                    res.put("CitizenshipIssueAuthority", String.valueOf(obj2.get("CitizenshipIssueAuthority")));
                    res.put("CitizenshipIssuePlace", String.valueOf(obj2.get("CitizenshipIssuePlace")));
                    res.put("CitizenshipIssueDate", String.valueOf(obj2.get("CitizenshipIssueDate")));
                    res.put("PassportNumber", String.valueOf(obj2.get("PassportNumber")));
                    res.put("PassportIssueAuthority", String.valueOf(obj2.get("PassportIssueAuthority")));
                    res.put("PassportIssuePlace", String.valueOf(obj2.get("PassportIssuePlace")));
                    res.put("PassportIssueDate", String.valueOf(obj2.get("PassportIssueDate")));
                    res.put("PassportValidTill", String.valueOf(obj2.get("PassportValidTill")));
                    res.put("DriverLicenseNumber", String.valueOf(obj2.get("DriverLicenseNumber")));
                    res.put("DriverLicenseAuthority", String.valueOf(obj2.get("DriverLicenseAuthority")));
                    res.put("DriverLicenseIssuePlace", String.valueOf(obj2.get("DriverLicenseIssuePlace")));
                    res.put("DriverLicenseIssueDate", String.valueOf(obj2.get("DriverLicenseIssueDate")));
                    res.put("DriverLicenseValidTill", String.valueOf(obj2.get("DriverLicenseValidTill")));
                    res.put("PANCardNumber", String.valueOf(obj2.get("PANCardNumber")));
                    res.put("PANCardIssueAuthority", String.valueOf(obj2.get("PANCardIssueAuthority")));
                    res.put("PANCardIssuePlace", String.valueOf(obj2.get("PANCardIssuePlace")));
                    res.put("PANCardIssueDate", String.valueOf(obj2.get("PANCardIssueDate")));
                    res.put("TaxRegistrationNumber", String.valueOf(obj2.get("TaxRegistrationNumber")));
                    res.put("TaxExpiry", String.valueOf(obj2.get("TaxExpiry")));
                    res.put("PermanentState", String.valueOf(obj2.get("PermanentState")));
                    res.put("PermanentDistrict", String.valueOf(obj2.get("PermanentDistrict")));
                    res.put("PermanentTown", String.valueOf(obj2.get("PermanentTown")));
                    res.put("PermanentWardNumber", String.valueOf(obj2.get("PermanentWardNumber")));
                    res.put("TemporaryState", String.valueOf(obj2.get("TemporaryState")));
                    res.put("TemporaryDistrict", String.valueOf(obj2.get("TemporaryDistrict")));
                    res.put("TemporaryTown", String.valueOf(obj2.get("TemporaryTown")));
                    res.put("TemporaryWardNumber", String.valueOf(obj2.get("TemporaryWardNumber")));
                    res.put("FatherName", String.valueOf(obj2.get("FatherName")));
                    res.put("MotherName", String.valueOf(obj2.get("MotherName")));
                    res.put("GrandFatherName", String.valueOf(obj2.get("GrandFatherName")));
                    res.put("GrandMotherName", String.valueOf(obj2.get("GrandMotherName")));
                    res.put("SpouseName", String.valueOf(obj2.get("SpouseName")));
                    res.put(STATUS, SUCCESS);

                } else {
                    getFailureResponse("", response, res, obj);
                }
            } else {
                res.put(STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception retKYCDetailsRes:  " + e);
        }
        LogMessages.statusLog.info("Response retKYCDetailsRes" + response);
        return response;
    }

    public JSONArray retKYCDetailsResCorp(String[] param) {
        LogMessages.statusLog.info("retKYCDetailsRes BODY:" + param[1]);
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();

        if (param[0].equalsIgnoreCase("SUCCESS")) {
            JSONParser parser = new JSONParser();

            JSONObject obj;
            try {
                obj = (JSONObject) parser.parse(param[1]);
                String code = String.valueOf(obj.get("Code"));
                if (code.equalsIgnoreCase("0")) {
                    String retCustInqResponse = String.valueOf(((JSONObject) obj.get("Data")).get("Result"));
                    JSONObject obj2 = (JSONObject) parser.parse(retCustInqResponse);

                    String CustomerId = String.valueOf(obj2.get("CustomerId"));
                    String CustomerType = String.valueOf(obj2.get("CustomerType"));
                    String CustomerName = String.valueOf(obj2.get("CustomerName"));
                    String BranchId = String.valueOf(obj2.get("BranchId"));
                    String RegisteredName = String.valueOf(obj2.get("RegisteredName"));
                    String CommericalName = String.valueOf(obj2.get("CommericalName"));
                    String Constitution = String.valueOf(obj2.get("Constitution"));
                    String CustomerCategory = String.valueOf(obj2.get("CustomerCategory"));
                    String BusinessSector = String.valueOf(obj2.get("BusinessSector"));
                    String BusinessSubsector = String.valueOf(obj2.get("BusinessSubsector"));
                    String CountryofRegistration = String.valueOf(obj2.get("CountryofRegistration"));
                    String Natureofbusiness = String.valueOf(obj2.get("Natureofbusiness"));
                    String PermanentState = String.valueOf(obj2.get("PermanentState"));
                    String PermanentDistrict = String.valueOf(obj2.get("PermanentDistrict"));
                    String PermanentTown = String.valueOf(obj2.get("PermanentTown"));
                    String PermanentWardNumber = String.valueOf(obj2.get("PermanentWardNumber"));
                    String CorrespondenceState = String.valueOf(obj2.get("CorrespondenceState"));
                    String CorrespondenceDistrict = String.valueOf(obj2.get("CorrespondenceDistrict"));
                    String CorrespondenceTown = String.valueOf(obj2.get("CorrespondenceTown"));
                    String CorrespondenceWardNumber = String.valueOf(obj2.get("CorrespondenceWardNumber"));
                    String PhoneNumber = String.valueOf(obj2.get("PhoneNumber"));
                    String MobileNumber = String.valueOf(obj2.get("MobileNumber"));
                    String Email = String.valueOf(obj2.get("Email"));
                    String PANCardNumber = String.valueOf(obj2.get("PANCardNumber"));
                    String PANCardIssueAuthority = String.valueOf(obj2.get("PANCardIssueAuthority"));
                    String PANCardIssuePlace = String.valueOf(obj2.get("PANCardIssuePlace"));
                    String PANCardIssueDate = String.valueOf(obj2.get("PANCardIssueDate"));
                    String TaxRegistrationNumber = String.valueOf(obj2.get("TaxRegistrationNumber"));
                    String TaxExpiry = String.valueOf(obj2.get("TaxExpiry"));
                    String TaxIssueAuthority = String.valueOf(obj2.get("TaxIssueAuthority"));
                    String TaxIssuePlace = String.valueOf(obj2.get("TaxIssuePlace"));
                    String CustomerTax = String.valueOf(obj2.get("CustomerTax"));
                    String CompanyRegistrationNumber = String.valueOf(obj2.get("CompanyRegistrationNumber"));
                    String CompanyRegistrationIssueAuthority = String.valueOf(obj2.get("CompanyRegistrationIssueAuthority"));
                    String CompanyRegistrationIssueCountry = String.valueOf(obj2.get("CompanyRegistrationIssueCountry"));
                    String CompanyRegistrationIssueState = String.valueOf(obj2.get("CompanyRegistrationIssueState"));
                    String CompanyRegistrationIssueDate = String.valueOf(obj2.get("CompanyRegistrationIssueDate"));
                    String CompanyRegistrationValidTill = String.valueOf(obj2.get("CompanyRegistrationValidTill"));
                    String OperatingLicenceNumber = String.valueOf(obj2.get("OperatingLicenceNumber"));
                    String OperatingLicenceIssueAuthority = String.valueOf(obj2.get("OperatingLicenceIssueAuthority"));
                    String OperatingLicenceIssueCountry = String.valueOf(obj2.get("OperatingLicenceIssueCountry"));
                    String OperatingLicenceIssueState = String.valueOf(obj2.get("OperatingLicenceIssueState"));
                    String OperatingLicenceIssueDate = String.valueOf(obj2.get("OperatingLicenceIssueDate"));
                    String OperatingLicenceValidTill = String.valueOf(obj2.get("OperatingLicenceValidTill"));
                    res.put("CustomerId", CustomerId);
                    res.put("CustomerType", CustomerType);
                    res.put("CustomerName", CustomerName);
                    res.put("BranchId", BranchId);
                    res.put("RegisteredName", RegisteredName);
                    res.put("CommericalName", CommericalName);
                    res.put("Constitution", Constitution);
                    res.put("CustomerCategory", CustomerCategory);
                    res.put("BusinessSector", BusinessSector);
                    res.put("BusinessSubsector", BusinessSubsector);
                    res.put("CountryofRegistration", CountryofRegistration);
                    res.put("Natureofbusiness", Natureofbusiness);
                    res.put("PermanentState", PermanentState);
                    res.put("PermanentDistrict", PermanentDistrict);
                    res.put("PermanentTown", PermanentTown);
                    res.put("PermanentWardNumber", PermanentWardNumber);
                    res.put("CorrespondenceState", CorrespondenceState);
                    res.put("CorrespondenceDistrict", CorrespondenceDistrict);
                    res.put("CorrespondenceTown", CorrespondenceTown);
                    res.put("CorrespondenceWardNumber", CorrespondenceWardNumber);
                    res.put("PhoneNumber", PhoneNumber);
                    res.put("MobileNumber", MobileNumber);
                    res.put("Email", Email);
                    res.put("PANCardNumber", PANCardNumber);
                    res.put("PANCardIssueAuthority", PANCardIssueAuthority);
                    res.put("PANCardIssuePlace", PANCardIssuePlace);
                    res.put("PANCardIssueDate", PANCardIssueDate);
                    res.put("TaxRegistrationNumber", TaxRegistrationNumber);
                    res.put("TaxExpiry", TaxExpiry);
                    res.put("TaxIssueAuthority", TaxIssueAuthority);
                    res.put("TaxIssuePlace", TaxIssuePlace);
                    res.put("CustomerTax", CustomerTax);
                    res.put("CompanyRegistrationNumber", CompanyRegistrationNumber);
                    res.put("CompanyRegistrationIssueAuthority", CompanyRegistrationIssueAuthority);
                    res.put("CompanyRegistrationIssueCountry", CompanyRegistrationIssueCountry);
                    res.put("CompanyRegistrationIssueState", CompanyRegistrationIssueState);
                    res.put("CompanyRegistrationIssueDate", CompanyRegistrationIssueDate);
                    res.put("CompanyRegistrationValidTill", CompanyRegistrationValidTill);
                    res.put("OperatingLicenceNumber", OperatingLicenceNumber);
                    res.put("OperatingLicenceIssueAuthority", OperatingLicenceIssueAuthority);
                    res.put("OperatingLicenceIssueCountry", OperatingLicenceIssueCountry);
                    res.put("OperatingLicenceIssueState", OperatingLicenceIssueState);
                    res.put("OperatingLicenceIssueDate", OperatingLicenceIssueDate);
                    res.put("OperatingLicenceValidTill", OperatingLicenceValidTill);
                    res.put("status", "SUCCESS");

                    LogMessages.statusLog.info("retKYCDetailsRes Success" + res);
                    response.add(res);
                } else {
                    res.put("status", "FAILURE");
                    String message = String.valueOf(obj.get("Message"));
                    res.put("message", message);
                    response.add(res);
                }
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception retKYCDetailsRes:  " + e);
            }

        } else {
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(param[1]);
                String error = String.valueOf(obj);
                //String message = String.valueOf(obj.get("message"));
                res.put("status", error);
                //res.put("message",message);
                LogMessages.statusLog.info("else retKYCDetailsRes");
                response.add(res);
            } catch (Exception e) {
                LogMessages.statusLog.info("Exception retKYCDetailsRes:  " + e);
            }

        }
        return response;
    }

}