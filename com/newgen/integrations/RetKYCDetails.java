package com.newgen.integrations;

/**
*
* @author bibek.shah
*
**/

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;

public class RetKYCDetails {

	public String retKYCDetailsReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			LogMessages.statusLog.info("Inside create Request of retKYCDetailsReq." + param[0]);
			//request = "{\"RetCustInqRequest\":{\"RetCustInqRq\":{\"CustId\":\"" + param[1] + "\"}}}";
			request = "{\"CustId\":\""+param[1]+"\"}";
			LogMessages.statusLog.info("Request retKYCDetailsReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception retKYCDetailsReq:  " + e);
		}
		return request;
	}
        
        public String retKYCDetailsCorpReq(String input, IFormReference iFormObj) {
		String request = "";
		String[] param = input.split("#");
		try {
			LogMessages.statusLog.info("Inside create Request of retKYCDetailsReq." + param[0]);
			//request = "{\"RetCustInqRequest\":{\"RetCustInqRq\":{\"CustId\":\"" + param[1] + "\"}}}";
			request = "{\"CustId\":\""+param[1]+"\",\"CustomerType\": \""+param[2]+"\"}";
			LogMessages.statusLog.info("Request retKYCDetailsReq:  " + request);
		} catch(Exception e) {
			LogMessages.statusLog.info("Exception retKYCDetailsReq:  " + e);
		}
		return request;
	}

	public JSONArray retKYCDetailsRes(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("retKYCDetailsRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
				String retCustInqResponse = String.valueOf(((JSONObject) obj.get("Data")).get("RetCustInqResponse"));
				JSONObject obj2 = (JSONObject) parser.parse(retCustInqResponse);
				String retCustDtls = String.valueOf(((JSONObject) obj2.get("RetCustInqRs")).get("RetCustDtls"));
				JSONObject retCustDtlsobj = (JSONObject) parser.parse(retCustDtls);
				String FatherOrHusbandName = String.valueOf(retCustDtlsobj.get("FatherOrHusbandName"));
				String Gender = String.valueOf(retCustDtlsobj.get("Gender"));
				String Name = String.valueOf(retCustDtlsobj.get("Name"));
				String RelationshipOpeningDt = String.valueOf(retCustDtlsobj.get("RelationshipOpeningDt"));
				String BirthDt = String.valueOf(retCustDtlsobj.get("BirthDt"));
				String PhoneEmailInfo = String.valueOf(retCustDtlsobj.get("PhoneEmailInfo"));
				JSONArray arr = (JSONArray) parser.parse(PhoneEmailInfo);
				String PhoneOrEmail = "";
				String PhoneNum1 = "",CellPhoneNum="",PhoneNum2="";
				String EmailInfo = "",HomeEmailInfo="";
				for (int i = 0; i < arr.size(); i++) {
					String s = String.valueOf(arr.get(i));
					JSONObject obj1 = (JSONObject) parser.parse(s);
					PhoneOrEmail = String.valueOf(obj1.get("PhoneOrEmail"));
					String PhoneEmailType = String.valueOf(obj1.get("PhoneEmailType"));
					if (PhoneOrEmail.equalsIgnoreCase("PHONE")) {
						
						if(PhoneEmailType.equalsIgnoreCase("COMMPH2")) {
							PhoneNum1 = String.valueOf(obj1.get("PhoneNum"));
						} else if(PhoneEmailType.equalsIgnoreCase("CELLPH")) {
							PhoneNum2 = String.valueOf(obj1.get("PhoneNum"));
						} else {
							CellPhoneNum = String.valueOf(obj1.get("PhoneNum"));
						}
					} else if (PhoneOrEmail.equalsIgnoreCase("EMAIL")) {
						
						if(PhoneEmailType.equalsIgnoreCase("COMMEML")) {
							EmailInfo = String.valueOf(obj1.get("EmailInfo"));
						} else {
							HomeEmailInfo = String.valueOf(obj1.get("EmailInfo"));
						}
					}
				}
				String retCustAddrInfo = String.valueOf(retCustDtlsobj.get("RetCustAddrInfo"));
				JSONArray arr1 = (JSONArray) parser.parse(retCustAddrInfo);
				String AddrLine1 = "";
				String AddrLine2 = "";
				String HouseNum = "";
				String StreetName = "";
				String State = "";
				String FatherName ="";
				String GFatherName ="";
				String MotherName ="",Citizenship="",Driving="",Passport="",Employee="";
				
				for (int i = 0; i < 2; i++) {
					String s = String.valueOf(arr1.get(i));
					JSONObject obj1 = (JSONObject) parser.parse(s);
					AddrLine1 += String.valueOf(obj1.get("AddrLine1")) + " #";
					AddrLine2 += String.valueOf(obj1.get("AddrLine1")) + " #";
					HouseNum += String.valueOf(obj1.get("HouseNum")) + " #";
					StreetName += String.valueOf(obj1.get("StreetName")) + " #";
					State += String.valueOf(obj1.get("State")) + " #";
				}

				String demographicDtls = String.valueOf(((JSONObject) obj2.get("RetCustInqRs")).get("DemographicDtls"));
				JSONObject demographicDtlsobj = (JSONObject) parser.parse(demographicDtls);
				String Nationality = String.valueOf(demographicDtlsobj.get("Nationality"));
				String EntityDocDtls = String.valueOf(((JSONObject) obj2.get("RetCustInqRs")).get("EntityDocDtls"));
				JSONArray entityrelation = (JSONArray) parser.parse(EntityDocDtls);
				if(entityrelation != null) {
					for (int i = 0; i < entityrelation.size(); i++) {
						String s = String.valueOf(entityrelation.get(i));
						JSONObject obj1 = (JSONObject) parser.parse(s);
						String Doccode = String.valueOf(obj1.get("DocCode"));
						if(Doccode.equalsIgnoreCase("R001")) {
							Citizenship = String.valueOf(obj1.get("ContactName"));
						}
						if(Doccode.equalsIgnoreCase("R002")) {
							Driving = String.valueOf(obj1.get("ContactName"));
						}
						if(Doccode.equalsIgnoreCase("R003")) {
							Passport = String.valueOf(obj1.get("ContactName"));
						}
						if(Doccode.equalsIgnoreCase("R018")) {
							Employee = String.valueOf(obj1.get("ContactName"));
						}
					}
				}
				String psychographicDtls = String.valueOf(((JSONObject) obj2.get("RetCustInqRs")).get("PsychographicDtls"));

				String relBankDtls = String.valueOf(((JSONObject) obj2.get("RetCustInqRs")).get("RelBankDtls"));
				String tradeFinDtls = String.valueOf(((JSONObject) obj2.get("RetCustInqRs")).get("TradeFinDtls"));
				String retailBaselDtls = String.valueOf(((JSONObject) obj2.get("RetCustInqRs")).get("RetailBaselDtls"));
				String relationshipData = String.valueOf(((JSONObject) obj2.get("RetCustInqRs")).get("relationshipData"));
				JSONArray arrrelation = (JSONArray) parser.parse(relationshipData);
				if(arrrelation != null) {
					for (int i = 0; i < arrrelation.size(); i++) {
						String s = String.valueOf(arrrelation.get(i));
						JSONObject obj1 = (JSONObject) parser.parse(s);
						String RelationShip = String.valueOf(obj1.get("RelationShip"));
						if(RelationShip.equalsIgnoreCase("001")) {
							FatherName = String.valueOf(obj1.get("ContactName"));
						}
						if(RelationShip.equalsIgnoreCase("009")) {
							GFatherName = String.valueOf(obj1.get("ContactName"));
						}
						if(RelationShip.equalsIgnoreCase("002")) {
							MotherName = String.valueOf(obj1.get("ContactName"));
						}
					}
				}	
				
				
				//			System.out.println("RetCustDtls:::: "+FatherOrHusbandName+"##"+Gender+"##"+Name+"##"+RelationshipOpeningDt);
				//			System.out.println("PHONEMAIL::"+PhoneNum+"##"+EmailInfo);
				//			System.out.println("retCustAddrInfo:::: "+AddrLine1+"##"+AddrLine2+"##"+HouseNum+"##"+StreetName);
				//			System.out.println("demographicDtls:::: "+Nationality);
				res.put("FatherName", FatherName);
				res.put("GFatherName", GFatherName);
				res.put("MotherName", MotherName);
				res.put("BirthDt", BirthDt);
				res.put("status", "SUCCESS");
				res.put("FatherOrHusbandName", FatherOrHusbandName);
				res.put("Gender", Gender);
				res.put("Name", Name);
				res.put("RelationshipOpeningDt", RelationshipOpeningDt);
				
				res.put("PhoneNum1", PhoneNum1);
				res.put("PhoneNum2", PhoneNum2);
				res.put("CellPhoneNum", CellPhoneNum);
				res.put("EmailInfo", EmailInfo);
				res.put("HomeEmailInfo", HomeEmailInfo);
				
				res.put("EmailInfo", EmailInfo);
				String[] AddrLine = AddrLine1.split("#");
				res.put("AddrLineper", AddrLine[0]);
				res.put("AddrLinecur", AddrLine[1]);
				String[] AddrLinel2 = AddrLine2.split("#");
				res.put("Location2per", AddrLinel2[0]);
				res.put("Location2cur", AddrLinel2[1]);
				String[] HouseNum1 = HouseNum.split("#");
				String[] StreetName1 = StreetName.split("#");
				res.put("HouseNum1", HouseNum1[0]);
				res.put("StreetName1", StreetName1[0]);
				res.put("HouseNum2", HouseNum1[1]);
				res.put("StreetName2", StreetName1[1]);
				String[] State1 = State.split("#");
				res.put("State1", State1[0]);
				res.put("State2", State1[1]);
				res.put("Nationality", Nationality);
				res.put("Citizenship", Citizenship);
				res.put("Driving", Driving);
				res.put("Passport", Passport);
				res.put("Employee", Employee);
                                res.put("status", "SUCCESS");
                                
				LogMessages.statusLog.info("retKYCDetailsRes Success" + FatherOrHusbandName + "##" + Gender + "##" + Name + "##" + RelationshipOpeningDt +PhoneNum1+CellPhoneNum+PhoneNum2+"##" + EmailInfo + AddrLine1 + "##" + AddrLine2 + "##" + HouseNum + "##" + StreetName + Nationality);
				response.add(res);
				}else {
					res.put("status", "FAILURE");
					String message = String.valueOf(obj.get("Message"));
					res.put("message", message);
					response.add(res);
				}
			}
			catch(Exception e) {
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
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception retKYCDetailsRes:  " + e);
			}

		}
		return response;
	}

	public JSONArray retKYCDetailsRes2(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("retKYCDetailsRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
				String retCustInqResponse = String.valueOf(((JSONObject) obj.get("Data")).get("Result"));
				JSONObject obj2 = (JSONObject) parser.parse(retCustInqResponse);
				
				String CustomerNumber = String.valueOf(obj2.get("CustomerNumber"));
				String IsVerified = String.valueOf(obj2.get("IsVerified"));
				String FirstName = String.valueOf(obj2.get("FirstName"));
				String MiddleName = String.valueOf(obj2.get("MiddleName"));
				String LastName = String.valueOf(obj2.get("LastName"));
				String Gender = String.valueOf(obj2.get("Gender"));
				String DateOfBirth = String.valueOf(obj2.get("DateOfBirth"));
				String CountryOfBirth = String.valueOf(obj2.get("CountryOfBirth"));
				String Nationality = String.valueOf(obj2.get("Nationality"));
				String MothersMaidenName = String.valueOf(obj2.get("MothersMaidenName"));
				String MaritalStatus = String.valueOf(obj2.get("MaritalStatus"));
				String Education = String.valueOf(obj2.get("Education"));
				String Religion = String.valueOf(obj2.get("Religion"));
				String BloodGroup = String.valueOf(obj2.get("BloodGroup"));
				String FamilySize = String.valueOf(obj2.get("FamilySize"));
				String MobileNumber = String.valueOf(obj2.get("MobileNumber"));
				String PhoneNumber = String.valueOf(obj2.get("PhoneNumber"));
				String Email = String.valueOf(obj2.get("Email"));
				String EmploymentType = String.valueOf(obj2.get("EmploymentType"));
				String Occupation = String.valueOf(obj2.get("Occupation"));
				String CompanyName = String.valueOf(obj2.get("CompanyName"));
				String CitizenshipNumber = String.valueOf(obj2.get("CitizenshipNumber"));
				String CitizenshipIssueAuthority = String.valueOf(obj2.get("CitizenshipIssueAuthority"));
				String CitizenshipIssuePlace = String.valueOf(obj2.get("CitizenshipIssuePlace"));
				String CitizenshipIssueDate = String.valueOf(obj2.get("CitizenshipIssueDate"));
				String PassportNumber = String.valueOf(obj2.get("PassportNumber"));
				String PassportIssueAuthority = String.valueOf(obj2.get("PassportIssueAuthority"));
				String PassportIssuePlace = String.valueOf(obj2.get("PassportIssuePlace"));
				String PassportIssueDate = String.valueOf(obj2.get("PassportIssueDate"));
				String PassportValidTill = String.valueOf(obj2.get("PassportValidTill"));
				String DriverLicenseNumber = String.valueOf(obj2.get("DriverLicenseNumber"));
				String DriverLicenseAuthority = String.valueOf(obj2.get("DriverLicenseAuthority"));
				String DriverLicenseIssuePlace = String.valueOf(obj2.get("DriverLicenseIssuePlace"));
				String DriverLicenseIssueDate = String.valueOf(obj2.get("DriverLicenseIssueDate"));
				String DriverLicenseValidTill = String.valueOf(obj2.get("DriverLicenseValidTill"));
				String PANCardNumber = String.valueOf(obj2.get("PANCardNumber"));
				String PANCardIssueAuthority = String.valueOf(obj2.get("PANCardIssueAuthority"));
				String PANCardIssuePlace = String.valueOf(obj2.get("PANCardIssuePlace"));
				String PANCardIssueDate = String.valueOf(obj2.get("PANCardIssueDate"));
				String TaxRegistrationNumber = String.valueOf(obj2.get("TaxRegistrationNumber"));
				String TaxExpiry = String.valueOf(obj2.get("TaxExpiry"));
				String PermanentState = String.valueOf(obj2.get("PermanentState"));
				String PermanentDistrict = String.valueOf(obj2.get("PermanentDistrict"));
				String PermanentTown = String.valueOf(obj2.get("PermanentTown"));
				String PermanentWardNumber = String.valueOf(obj2.get("PermanentWardNumber"));
				String TemporaryState = String.valueOf(obj2.get("TemporaryState"));
				String TemporaryDistrict = String.valueOf(obj2.get("TemporaryDistrict"));
				String TemporaryTown = String.valueOf(obj2.get("TemporaryTown"));
				String TemporaryWardNumber = String.valueOf(obj2.get("TemporaryWardNumber"));
				String FatherName = String.valueOf(obj2.get("FatherName"));
				String MotherName = String.valueOf(obj2.get("MotherName"));
				String GrandFatherName = String.valueOf(obj2.get("GrandFatherName"));
				String GrandMotherName = String.valueOf(obj2.get("GrandMotherName"));
				String SpouseName = String.valueOf(obj2.get("SpouseName"));
				res.put("CustomerNumber", CustomerNumber);
				res.put("IsVerified", IsVerified);
				res.put("FirstName", FirstName);
				res.put("MiddleName", MiddleName);
				res.put("LastName", LastName);
				res.put("Gender", Gender);
				res.put("DateOfBirth", DateOfBirth);
				res.put("CountryOfBirth", CountryOfBirth);
				res.put("Nationality", Nationality);
				res.put("MothersMaidenName", MothersMaidenName);
				res.put("MaritalStatus", MaritalStatus);
				res.put("Education", Education);
				res.put("Religion", Religion);
				res.put("BloodGroup", BloodGroup);
				res.put("FamilySize", FamilySize);
				res.put("MobileNumber", MobileNumber);
				res.put("PhoneNumber", PhoneNumber);
				res.put("Email", Email);
				res.put("EmploymentType", EmploymentType);
				res.put("Occupation", Occupation);
				res.put("CompanyName", CompanyName);
				res.put("CitizenshipNumber", CitizenshipNumber);
				res.put("CitizenshipIssueAuthority", CitizenshipIssueAuthority);
				res.put("CitizenshipIssuePlace", CitizenshipIssuePlace);
				res.put("CitizenshipIssueDate", CitizenshipIssueDate);
				res.put("PassportNumber", PassportNumber);
				res.put("PassportIssueAuthority", PassportIssueAuthority);
				res.put("PassportIssuePlace", PassportIssuePlace);
				res.put("PassportIssueDate", PassportIssueDate);
				res.put("PassportValidTill", PassportValidTill);
				res.put("DriverLicenseNumber", DriverLicenseNumber);
				res.put("DriverLicenseAuthority", DriverLicenseAuthority);
				res.put("DriverLicenseIssuePlace", DriverLicenseIssuePlace);
				res.put("DriverLicenseIssueDate", DriverLicenseIssueDate);
				res.put("DriverLicenseValidTill", DriverLicenseValidTill);
				res.put("PANCardNumber", PANCardNumber);
				res.put("PANCardIssueAuthority", PANCardIssueAuthority);
				res.put("PANCardIssuePlace", PANCardIssuePlace);
				res.put("PANCardIssueDate", PANCardIssueDate);
				res.put("TaxRegistrationNumber", TaxRegistrationNumber);
				res.put("TaxExpiry", TaxExpiry);
				res.put("PermanentState", PermanentState);
				res.put("PermanentDistrict", PermanentDistrict);
				res.put("PermanentTown", PermanentTown);
				res.put("PermanentWardNumber", PermanentWardNumber);
				res.put("TemporaryState", TemporaryState);
				res.put("TemporaryDistrict", TemporaryDistrict);
				res.put("TemporaryTown", TemporaryTown);
				res.put("TemporaryWardNumber", TemporaryWardNumber);
				res.put("FatherName", FatherName);
				res.put("MotherName", MotherName);
				res.put("GrandFatherName", GrandFatherName);
				res.put("GrandMotherName", GrandMotherName);
				res.put("SpouseName", SpouseName);
				res.put("status", "SUCCESS");

				LogMessages.statusLog.info("retKYCDetailsRes Success" + res);
				response.add(res);
				}else {
					res.put("status", "FAILURE");
					String message = String.valueOf(obj.get("Message"));
					res.put("message", message);
					response.add(res);
				}
			}
			catch(Exception e) {
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
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception retKYCDetailsRes:  " + e);
			}

		}
		return response;
	}
        
        public JSONArray retKYCDetailsResCorp(String input) {
		String[] param = input.split("##");
		LogMessages.statusLog.info("retKYCDetailsRes BODY:" + param[1]);
		JSONArray response = new JSONArray();
		JSONObject res = new JSONObject();

		if (param[0].equalsIgnoreCase("SUCCESS")) {
			JSONParser parser = new JSONParser();

			JSONObject obj;
			try {
				obj = (JSONObject) parser.parse(param[1]);
				String code = String.valueOf(obj.get("Code"));
				if(code.equalsIgnoreCase("0")) {
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
				}else {
					res.put("status", "FAILURE");
					String message = String.valueOf(obj.get("Message"));
					res.put("message", message);
					response.add(res);
				}
			}
			catch(Exception e) {
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
			}
			catch(Exception e) {
				LogMessages.statusLog.info("Exception retKYCDetailsRes:  " + e);
			}

		}
		return response;
	}

}