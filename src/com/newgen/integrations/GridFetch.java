/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.LoggingLOS;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

import static com.newgen.iforms.util.NewGenUtilConstants.ERROR;
import static com.newgen.iforms.util.NewGenUtilConstants.SUCCESS;


/**
 * @author bibek.shah
 **/
public class GridFetch {
    LoggingLOS logObj = new LoggingLOS();

    public String gridToGridFetch(IFormReference iFormObj) {
        this.logObj.serviceLog("GridFetch");
        try {
            String pID = (String) iFormObj.getValue("PID");
            String query1 = "Select BDKeycontactperson, BDContactNo from CS_BorrowersDetails WITH(NOLOCK) where PID ='" +
                    pID + "'";
            String query2 = "select NameofOwner, PlaceStreetName +','+ WardNo +','+  VDCMunicipality +','+  District +', Province '+  Province as Location,PlotNo,Typesofmeasurement, Area, TotalFMV, DistressedValue from CS_Infocollateral WITH(NOLOCK) where PID ='" +
                    pID + "'";
            this.logObj.serviceLog("GridFetch query1:::" + query1);
            this.logObj.serviceLog("GridFetch query1:::" + query2);

            List<String> rowData1 = iFormObj.getDataFromDB(query1);
            this.logObj.serviceLog("GridFetch rowData1:::" + query1);
            JSONObject obj = new JSONObject();
            obj.put("Key Person", rowData1.get(0));
            obj.put("Contact No", rowData1.get(1));

            JSONArray jsonArrayEba = new JSONArray();
            jsonArrayEba.add(obj);
            this.logObj.serviceLog("GridFetch array::" + jsonArrayEba);

            List<String> rowData2 = iFormObj.getDataFromDB(query2);
            this.logObj.serviceLog("GridFetch rowData2:::" + query2);
            JSONObject obj1 = new JSONObject();
            obj1.put("Name of Qwner", rowData2.get(0));
            obj1.put("Location", rowData2.get(1));
            obj1.put("PlotNo", rowData2.get(2));
            obj1.put("Types of measurement", rowData2.get(3));
            obj1.put("Area", rowData2.get(4));
            obj1.put("Total FMV", rowData2.get(5));
            obj1.put("Distressed Value", rowData2.get(6));

            JSONArray jsonArraySd = new JSONArray();
            jsonArraySd.add(obj1);
            this.logObj.serviceLog("GridFetch array::" + jsonArraySd);


            iFormObj.addDataToGrid("table12", jsonArrayEba);
            iFormObj.addDataToGrid("table7", jsonArraySd);

            this.logObj.serviceLog("GridFetch Success");
            return SUCCESS;
        } catch (Exception e) {
            this.logObj.serviceLog("Exception at GridFetch.." + e);
        }
        return ERROR;
    }

}