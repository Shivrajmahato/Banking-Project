/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.common;

import com.newgen.iforms.custom.IFormReference;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.json.simple.JSONObject;

/**
 * @author shivaraj
 */
public class BranchDetails {

    public String getBranchDetails(String roDomainName, IFormReference iFormObj) {
        String sql = "select Branch_Name, Branch_Code, RO_Name, RO_Domain,RO_Email,BM_Name,BM_Domain,BM_Email,CH_Name,CH_Domain,CH_Email,PM_Name,PM_Domain,PM_Email,CBO_Name,CBO_Domain,CBO_Email,DCEO_Name,DCEO_Domain,DCEO_Email, CEO_Name,CEO_Domain,CEO_Email,CC_Domain,CC_Email,Board_Domain,Board_Email,District,Province,CAC_Committee_Domain,CAC_Committee_Email,Head_Legal_Domain,Head_Legal_Email,Chief_Credit_Control_Domain,Chief_Credit_Control_Email from LOS_Branchuserlogin WITH(NOLOCK) WHERE RO_Domain = '" + roDomainName + "';";
        LogMessages.statusLog.info("Select query to check data::" + sql);
        try {
            List<List<String>> resultList = iFormObj.getDataFromDB(sql);
            LogMessages.statusLog.info("resultList:" + resultList);

            if (resultList.isEmpty()) {
                return "0";
            } else {
                Map<String, String> obj = new HashMap<>();
                obj.put("Branch_Name", resultList.get(0).get(0));
                obj.put("Branch_Code", resultList.get(1).get(1));
                obj.put("RO_Name", resultList.get(2).get(2));
                obj.put("RO_Domain", resultList.get(3).get(3));
                obj.put("RO_Email", resultList.get(4).get(4));
                obj.put("BM_Name", resultList.get(5).get(5));
                obj.put("BM_Domain", resultList.get(6).get(6));
                obj.put("BM_Email", resultList.get(7).get(7));
                obj.put("CH_Name", resultList.get(8).get(8));
                obj.put("CH_Domain", resultList.get(9).get(9));
                obj.put("CH_Email", resultList.get(10).get(10));
                obj.put("PM_Name", resultList.get(11).get(11));
                obj.put("PM_Domain", resultList.get(12).get(12));
                obj.put("PM_Email", resultList.get(13).get(13));
                obj.put("CBO_Name", resultList.get(14).get(14));
                obj.put("CBO_Domain", resultList.get(15).get(15));
                obj.put("CBO_Email", resultList.get(16).get(16));
                obj.put("DCEO_Name", resultList.get(17).get(17));
                obj.put("DCEO_Domain", resultList.get(18).get(18));
                obj.put("DCEO_Email", resultList.get(19).get(19));
                obj.put("CEO_Name", resultList.get(20).get(20));
                obj.put("CEO_Domain", resultList.get(21).get(21));
                obj.put("CEO_Email", resultList.get(22).get(22));
                obj.put("CC_Domain", resultList.get(23).get(23));
                obj.put("CC_Email", resultList.get(24).get(24));
                obj.put("Board_Domain", resultList.get(25).get(25));
                obj.put("Board_Email", resultList.get(26).get(26));
                obj.put("Branch_District", resultList.get(27).get(27));
                obj.put("Branch_Province", resultList.get(28).get(28));
                obj.put("CAC_Committee_Domain", resultList.get(29).get(29));
                obj.put("CAC_Committee_Email", resultList.get(30).get(30));
                obj.put("Head_Legal_Domain", resultList.get(31).get(31));
                obj.put("Head_Legal_Domain_Email", resultList.get(32).get(32));
                obj.put("Chief_Credit_Control_Domain", resultList.get(33).get(33));
                obj.put("Chief_Credit_Control_Email", resultList.get(34).get(34));

                LogMessages.statusLog.info("JSONObject At the end::" + obj);
                return obj.toString();
            }
        } catch (Exception e) {
            LogMessages.statusLog.info("Issue at branchdetails:" + e);
            return "0";
        }

    }
}
