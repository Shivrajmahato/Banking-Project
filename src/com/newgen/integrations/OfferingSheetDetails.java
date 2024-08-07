/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.util.CommonMethods;
import com.newgen.iforms.util.NewGenUtil;
import org.apache.http.util.TextUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.newgen.iforms.util.NewGenUtil.getFailureResponse;
import static com.newgen.iforms.util.NewGenUtilConstants.*;

/**
 * @author bibek.shah
 **/
public class OfferingSheetDetails {

    public static final String LIMIT_DETAILS_LSTS = "limitDetailsLsts";
    public static final String UNIT_PAST_DUES_LSTS = "unitPastDuesLsts";
    public static final String FUNDED_LSTS = "FundedLsts";
    public static final String NON_FUNDED_LSTS = "nonFundedLsts";
    public static final String TOTAL_EXPOSURE_LSTS = "totalExposureLsts";
    public static final String GROUP_PAST_DUES_LSTS = "groupPastDuesLsts";
    public static final String GROUP_UNITS_LSTS = "groupUnitsLsts";
    public static final String CUSTOMER_ID = "Customer ID";
    public static final String CUST_ID = "CustId";
    public static final String ADDITIONAL_REQUEST = "AdditionalRequest";

    public String offeringSheetRequest(String[] param, IFormReference iFormObj) {
        String request = "";
        try {
            JSONObject finalRequest = new JSONObject();
            JSONArray output = iFormObj.getDataFromGrid("tblLimitTree");
            LogMessages.statusLog.info("############DATA FROM GRID ::::" + output.toString());
            StringBuilder additionalRequestChild = new StringBuilder();
            for (int i = 0; i < output.size(); i++) {
                JSONObject arrayJsonObject = (JSONObject) output.get(i);
                String additionalRequestData = String.valueOf(arrayJsonObject.get(ADDITIONAL_REQUEST));
                LogMessages.statusLog.info("############additionalRequestData ::::" + additionalRequestData);
                if (!TextUtils.isEmpty(additionalRequestData)) {
                    String limitType = String.valueOf(arrayJsonObject.get("LimitSuffix"));
                    additionalRequestChild.append(limitType).append(":").append(additionalRequestData).append(",");
                }
            }
            additionalRequestChild.deleteCharAt(additionalRequestChild.length() - 1);
            finalRequest.put(CUST_ID, param[1]);
            finalRequest.put("SolId", "ALL");
            finalRequest.put("UserId", "KISHORG");
            finalRequest.put(ADDITIONAL_REQUEST, additionalRequestChild.toString());
            request = finalRequest.toJSONString();
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception OfferingSheet Request: " + e);
        }
        LogMessages.statusLog.info("############OfferingSheet REQUEST::::" + request);
        return request;
    }

    public JSONArray offeringSheetResponse(String[] param, IFormReference iFormObj) {
        JSONArray response = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            JSONObject obj = NewGenUtil.getParsedJsonObj(param[1]);
            if (param[0].equalsIgnoreCase(SUCCESS)) {
                if (ZERO.equalsIgnoreCase(String.valueOf(obj.get(CODE)))) {
                    iFormObj.setValue("OS_customerName", String.valueOf(((JSONObject) obj.get("Data")).get("CustomerName")));
                    iFormObj.setValue("OS_groupName", String.valueOf(((JSONObject) obj.get("Data")).get("GroupName")));
                    addTable1ArrDataToGrid(iFormObj, obj);

                    JSONObject obj2 = addTable2ArrDataToGrid(iFormObj, obj);

                    String fundedLsts = addTable3ArrDataToGrid(iFormObj, obj);

                    JSONObject obj4 = addTable4ArrDataToGrid(iFormObj, obj);

                    JSONObject obj5 = addTable5ArrDataToGrid(iFormObj, obj);

                    JSONObject obj6 = addTable6ArrDataToGrid(iFormObj, obj);

                    JSONObject obj7 = addTable7ArrDataToGrid(iFormObj, obj);

                    String unitPastDuesLsts = obj2.get(UNIT_PAST_DUES_LSTS).toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes UNIT PAST DUES: " + unitPastDuesLsts);
                    String nonFundedLsts = obj4.get(NON_FUNDED_LSTS).toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes: NON FUNDED LIST " + nonFundedLsts);
                    String totalExposureLsts = obj5.get(TOTAL_EXPOSURE_LSTS).toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes: TOTAL EXPOSURE " + totalExposureLsts);
                    String groupPastDuesLsts = obj6.get(GROUP_PAST_DUES_LSTS).toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes: GROUP PAST DUES " + groupPastDuesLsts);
                    String groupUnitsLsts = obj7.get(GROUP_UNITS_LSTS).toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes: GROUP UNIT LIST " + groupUnitsLsts);
                    String limitDetailsLsts = obj.get(LIMIT_DETAILS_LSTS).toString();
                    LogMessages.statusLog.info("Exception OfferingSheetRes LIMITDETAILSLIST: " + limitDetailsLsts);
                    res.put(LIMIT_DETAILS_LSTS, limitDetailsLsts);
                    res.put(UNIT_PAST_DUES_LSTS, unitPastDuesLsts);
                    res.put(FUNDED_LSTS, fundedLsts);
                    res.put(NON_FUNDED_LSTS, nonFundedLsts);
                    res.put(TOTAL_EXPOSURE_LSTS, totalExposureLsts);
                    res.put(GROUP_PAST_DUES_LSTS, groupPastDuesLsts);
                    res.put(GROUP_UNITS_LSTS, groupUnitsLsts);
                    res.put(STATUS, SUCCESS);

                } else {
                    getFailureResponse("", response, res, obj);
                }
            } else {
                res.put(STATUS, String.valueOf(obj));
            }
            response.add(res);
        } catch (Exception e) {
            LogMessages.statusLog.info("Exception TRY CATCH retLimitTreeDetailsRes: " + e);
        }
        return response;
    }

    private JSONObject addTable7ArrDataToGrid(IFormReference iFormObj, JSONObject obj) {
        String groupUnits = String.valueOf(((JSONObject) obj.get("Data")).get("groupUnits"));
        JSONObject obj8 = NewGenUtil.getParsedJsonObj(groupUnits);
        JSONArray jsonArrayGroupUnitList = (JSONArray) obj8.get(GROUP_UNITS_LSTS);
        if (jsonArrayGroupUnitList.size() > 0) {
            JSONArray table7Arr = new JSONArray();
            for (int i = 0; i < jsonArrayGroupUnitList.size(); i++) {
                JSONObject arrayJsonObject = (JSONObject) jsonArrayGroupUnitList.get(i);
                JSONObject table7 = new JSONObject();
                table7.put("Cust ID", String.valueOf(arrayJsonObject.get(CUST_ID)));
                table7.put("Unit Name", String.valueOf(arrayJsonObject.get("UnitName")));
                table7.put("CustSbu", String.valueOf(arrayJsonObject.get("CustSbu")));
                table7.put("CustRo", String.valueOf(arrayJsonObject.get("CustRo")));
                table7Arr.add(table7);
                iFormObj.addDataToGrid("tblGroupUnits", table7Arr);
                table7Arr.clear();
            }
        }
        return obj8;
    }

    private JSONObject addTable6ArrDataToGrid(IFormReference iFormObj, JSONObject obj) {
        String groupPastDues = String.valueOf(((JSONObject) obj.get("Data")).get("groupPastDues"));
        JSONObject obj7 = NewGenUtil.getParsedJsonObj(groupPastDues);
        JSONArray jsonArrayGroupPastDues = (JSONArray) obj7.get(GROUP_PAST_DUES_LSTS);
        if (jsonArrayGroupPastDues.size() > 0) {
            JSONArray table6Arr = new JSONArray();
            for (int i = 0; i < jsonArrayGroupPastDues.size(); i++) {
                JSONObject arrayJsonObject = (JSONObject) jsonArrayGroupPastDues.get(i);
                JSONObject table6 = new JSONObject();
                table6.put(CUSTOMER_ID, String.valueOf(arrayJsonObject.get(CUST_ID)));
                table6.put("A/c No", String.valueOf(arrayJsonObject.get("AccountNumber")));
                table6.put("Disb Id/Bill No", String.valueOf(arrayJsonObject.get("DisbIdBillNo")));
                table6.put("O/S LCY", String.valueOf(arrayJsonObject.get("OSLcy")));
                table6.put("Overdue Since", CommonMethods.dateConverter(String.valueOf(arrayJsonObject.get("OverdueSince"))));
                table6.put("AccountSbu", String.valueOf(arrayJsonObject.get("AccountSbu")));
                table6.put("Reason for Overdue", String.valueOf(arrayJsonObject.get("ReasonForOverdue")));
                table6Arr.add(table6);
                iFormObj.addDataToGrid("tblGroupPastDues", table6Arr);
                table6Arr.clear();
            }
        }
        return obj7;
    }

    private JSONObject addTable5ArrDataToGrid(IFormReference iFormObj, JSONObject obj) {
        String totalExposure = String.valueOf(((JSONObject) obj.get("Data")).get("totalExposure"));
        JSONObject obj6 = NewGenUtil.getParsedJsonObj(totalExposure);
        JSONArray jsonArrayTotalExposure = (JSONArray) obj6.get(TOTAL_EXPOSURE_LSTS);
        if (jsonArrayTotalExposure.size() > 0) {
            JSONArray table5Arr = new JSONArray();
            for (int i = 0; i < jsonArrayTotalExposure.size(); i++) {
                JSONObject arrayJsonObject = (JSONObject) jsonArrayTotalExposure.get(i);
                JSONObject table5 = new JSONObject();
                table5.put("Total Group Limit", String.valueOf(arrayJsonObject.get("Limit")));
                table5.put("Funded", String.valueOf(arrayJsonObject.get("Funded")));
                table5.put("Non Funded", String.valueOf(arrayJsonObject.get("NonFunded")));
                table5.put("Total", String.valueOf(arrayJsonObject.get("Total")));
                table5.put("Excess", String.valueOf(arrayJsonObject.get("Excess")));
                table5.put("Actual OD", String.valueOf(arrayJsonObject.get("ActualOd")));
                table5.put("Lien Amount", String.valueOf(arrayJsonObject.get("LienAmount")));
                table5.put("Actual Limit", String.valueOf(arrayJsonObject.get("ActualLimit")));
                table5Arr.add(table5);
                iFormObj.addDataToGrid("tblTotalExposure", table5Arr);
                table5Arr.clear();
            }
        }
        return obj6;
    }

    private JSONObject addTable4ArrDataToGrid(IFormReference iFormObj, JSONObject obj) {
        String nonFunded = String.valueOf(((JSONObject) obj.get("Data")).get("nonFunded"));
        JSONObject obj5 = NewGenUtil.getParsedJsonObj(nonFunded);
        JSONArray jsonArrayNonFunded = (JSONArray) obj5.get(NON_FUNDED_LSTS);
        if (jsonArrayNonFunded.size() > 0) {
            JSONArray table4Arr = new JSONArray();
            for (int i = 0; i < jsonArrayNonFunded.size(); i++) {
                JSONObject arrayJsonObject = (JSONObject) jsonArrayNonFunded.get(i);
                JSONObject table4 = new JSONObject();
                table4.put(CUSTOMER_ID, String.valueOf(arrayJsonObject.get(CUST_ID)));
                table4.put("ID Type", String.valueOf(arrayJsonObject.get("IdType")));
                table4.put("Non Funding Outstanding", String.valueOf(arrayJsonObject.get("NonFundedOutstanding")));
                table4Arr.add(table4);
                iFormObj.addDataToGrid("tblNonFunded", table4Arr);
                table4Arr.clear();
            }
        }
        return obj5;
    }

    private String addTable3ArrDataToGrid(IFormReference iFormObj, JSONObject obj) {
        String funded = String.valueOf(((JSONObject) obj.get("Data")).get("funded"));
        LogMessages.statusLog.info("Exception funded: " + funded);
        String fundedLsts = "";
        if (!funded.equalsIgnoreCase("null") || funded != null) {
            JSONObject obj4 = NewGenUtil.getParsedJsonObj(funded);
            fundedLsts = obj4.get("fundedLsts").toString();
            JSONArray jsonArrayNonFunded = (JSONArray) obj4.get("fundedLsts");
            if (jsonArrayNonFunded.size() > 0) {
                JSONArray table3Arr = new JSONArray();
                for (int i = 0; i < jsonArrayNonFunded.size(); i++) {
                    JSONObject arrayJsonObject = (JSONObject) jsonArrayNonFunded.get(i);
                    JSONObject table3 = new JSONObject();
                    table3.put(CUSTOMER_ID, String.valueOf(arrayJsonObject.get(CUST_ID)));
                    table3.put("ID Type", String.valueOf(arrayJsonObject.get("IdType")));
                    table3.put("Funding Outstanding", String.valueOf(arrayJsonObject.get("FundedOutstanding")));
                    table3Arr.add(table3);
                    iFormObj.addDataToGrid("tblFunded", table3Arr);
                    table3Arr.clear();
                }
            }
        }
        LogMessages.statusLog.info("Exception OfferingSheetRes FUNDED LIST: " + fundedLsts);
        return fundedLsts;
    }

    private JSONObject addTable2ArrDataToGrid(IFormReference iFormObj, JSONObject obj) {
        String unitPastDues = String.valueOf(((JSONObject) obj.get("Data")).get("unitPastDues"));
        JSONObject obj3 = NewGenUtil.getParsedJsonObj(unitPastDues);
        JSONArray jsonArrayUnitPastDues = (JSONArray) obj3.get(UNIT_PAST_DUES_LSTS);
        if (jsonArrayUnitPastDues.size() > 0) {
            JSONArray table2Arr = new JSONArray();
            for (int i = 0; i < jsonArrayUnitPastDues.size(); i++) {
                JSONObject arrayJsonObject = (JSONObject) jsonArrayUnitPastDues.get(i);
                JSONObject table2 = new JSONObject();
                table2.put("A/c No", String.valueOf(arrayJsonObject.get("AccountNumber")));
                table2.put("Disb Id/Bill No", String.valueOf(arrayJsonObject.get("DisbIdBillNo")));
                table2.put("O/S LCY", String.valueOf(arrayJsonObject.get("OSLcy")));
                table2.put("Overdue Since", CommonMethods.dateConverter(String.valueOf(arrayJsonObject.get("OverdueSince"))));
                table2.put("Reason For Overdue", String.valueOf(arrayJsonObject.get("ReasonForOverdue")));
                table2Arr.add(table2);
                iFormObj.addDataToGrid("tblUnitPastDues", table2Arr);
                table2Arr.clear();
            }
        }
        return obj3;
    }

    private void addTable1ArrDataToGrid(IFormReference iFormObj, JSONObject obj) {
        String limitDetails = String.valueOf(((JSONObject) obj.get("Data")).get("limitDetails"));
        JSONArray jsonArrayLimit = (JSONArray) NewGenUtil.getParsedJsonObj(limitDetails).get(LIMIT_DETAILS_LSTS);
        if (jsonArrayLimit.size() > 0) {
            JSONArray table1Arr = new JSONArray();
            for (int i = 0; i < jsonArrayLimit.size(); i++) {
                JSONObject arrayJsonObject = (JSONObject) jsonArrayLimit.get(i);
                JSONObject table1 = new JSONObject();
                table1.put("Limit Node", String.valueOf(arrayJsonObject.get("LimitSuffix")));
                table1.put("Description", String.valueOf(arrayJsonObject.get("LimitDescription")));
                table1.put("Approved Limits", String.valueOf(arrayJsonObject.get("ApprovedLines")));
                table1.put("Present Outstanding", String.valueOf(arrayJsonObject.get("PresentOutstanding")));
                table1.put("Additional Request", String.valueOf(arrayJsonObject.get(ADDITIONAL_REQUEST)));
                table1.put("Total Incl Additonal request", String.valueOf(arrayJsonObject.get("TotalAdditionalRequest")));
                table1.put("Excess/Actual OS", String.valueOf(arrayJsonObject.get("ExcessOver")));
                table1Arr.add(table1);
                iFormObj.addDataToGrid("tblLimitsDetails", table1Arr);
                table1Arr.clear();
            }
        }
    }

}
