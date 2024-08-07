/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.common;

import com.newgen.iforms.custom.IFormReference;
import java.util.List;
import java.util.ListIterator;
import org.json.simple.JSONObject;

/**
 *
 * @author User
 */
public class BranchDetails {

    public String getBranchDetails(String roDomainName, IFormReference iFormObj) {
        String sql = "select Branch_Name, Branch_Code, RO_Name, RO_Domain,RO_Email,BM_Name,BM_Domain,BM_Email,CH_Name,CH_Domain,CH_Email,PM_Name,PM_Domain,PM_Email,CBO_Name,CBO_Domain,CBO_Email,DCEO_Name,DCEO_Domain,DCEO_Email, CEO_Name,CEO_Domain,CEO_Email,CC_Domain,CC_Email,Board_Domain,Board_Email,District,Province,CAC_Committee_Domain,CAC_Committee_Email,Head_Legal_Domain,Head_Legal_Email,Chief_Credit_Control_Domain,Chief_Credit_Control_Email from LOS_Branchuserlogin WITH(NOLOCK) WHERE RO_Domain = '" + roDomainName + "';";
        LogMessages.statusLog.info("Select query to check data::" + sql);
        try{
        List resultList = iFormObj.getDataFromDB(sql);
        LogMessages.statusLog.info("resultList:" + resultList);
        JSONObject obj = new JSONObject();
        if (resultList.isEmpty()) {
            return "0";
        } else {
            ListIterator QueryListItr = resultList.listIterator();
            LogMessages.statusLog.info("inside Listdatas::" + QueryListItr);

            while (QueryListItr.hasNext()) {
                List rowData = (List) QueryListItr.next();
                String[] itemsArray = new String[rowData.size()];
                itemsArray = (String[]) rowData.toArray(itemsArray);

                obj.put("Branch_Name", itemsArray[0]);
                obj.put("Branch_Code", itemsArray[1]);
                obj.put("RO_Name", itemsArray[2]);
                obj.put("RO_Domain", itemsArray[3]);
                obj.put("RO_Email", itemsArray[4]);
                obj.put("BM_Name", itemsArray[5]);
                obj.put("BM_Domain", itemsArray[6]);
                obj.put("BM_Email", itemsArray[7]);
                obj.put("CH_Name", itemsArray[8]);
                obj.put("CH_Domain", itemsArray[9]);
                obj.put("CH_Email", itemsArray[10]);
                obj.put("PM_Name", itemsArray[11]);
                obj.put("PM_Domain", itemsArray[12]);
                obj.put("PM_Email", itemsArray[13]);
                obj.put("CBO_Name", itemsArray[14]);
                obj.put("CBO_Domain", itemsArray[15]);
                obj.put("CBO_Email", itemsArray[16]);
                obj.put("DCEO_Name", itemsArray[17]);
                obj.put("DCEO_Domain", itemsArray[18]);
                obj.put("DCEO_Email", itemsArray[19]);
                obj.put("CEO_Name", itemsArray[20]);
                obj.put("CEO_Domain", itemsArray[21]);
                obj.put("CEO_Email", itemsArray[22]);
                obj.put("CC_Domain", itemsArray[23]);
                obj.put("CC_Email", itemsArray[24]);
                obj.put("Board_Domain", itemsArray[25]);
                obj.put("Board_Email", itemsArray[26]);
                obj.put("Branch_District", itemsArray[27]);
                obj.put("Branch_Province", itemsArray[28]);
				obj.put("CAC_Committee_Domain", itemsArray[29]);
                obj.put("CAC_Committee_Email", itemsArray[30]);
                obj.put("Head_Legal_Domain", itemsArray[31]);
		obj.put("Head_Legal_Domain_Email", itemsArray[32]);
		obj.put("Chief_Credit_Control_Domain", itemsArray[33]);
		obj.put("Chief_Credit_Control_Email", itemsArray[34]);

                LogMessages.statusLog.info("JSONObject At the end::" + obj);
            }
            return obj.toString();
        }
        }catch(Exception e){
            LogMessages.statusLog.info("Issue at branchdetails:" + e);
            return "0";
        }

    }
}
