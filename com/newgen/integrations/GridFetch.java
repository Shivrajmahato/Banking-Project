/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.integrations;

import com.newgen.common.Logging_LOS;
import com.newgen.iforms.custom.IFormReference;
import java.util.List;
import java.util.ListIterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
*
* @author bibek.shah
*
**/
public class GridFetch
{
  Logging_LOS logObj = new Logging_LOS();
  String retVal = "";

  public String gridfetchValuation(String stringData, IFormReference iFormObj) { this.logObj.servicelog("inside gridfetchValuation");

    String query = "select  a.*, d.Name, d.Address, d.ContactPerson, d.ContactNumber,d.email from (select c.NameofOwner, c.Property, c.WardNo, c.VDCMunicipality, c.District, c.ropani, c.Area,  v.* from CS_Infocollateral as c left join ValuerAssign as v on c.insertionOrderId = v.ValuationId WITH(NOLOCK) where c.PID = '" + 
      stringData + "'" + 
      ") as a left join ValuatorDet as d on d.itemindex = a.ValuerId";
    List data = iFormObj.getDataFromDB(query);
    this.logObj.servicelog("inside gridfetchValuation query::" + query);
    ListIterator QueryListItr = data.listIterator();
    this.logObj.servicelog("inside gridfetchValuation Listdatas::" + QueryListItr);
    JSONArray jsonArray = new JSONArray();
    try {
      while (QueryListItr.hasNext()) {
        List rowData = (List)QueryListItr.next();
        String[] itemsArray = new String[rowData.size()];
        itemsArray = (String[])rowData.toArray(itemsArray);
        JSONObject obj = new JSONObject();
        obj.put("Name of Owner", itemsArray[0]);
        obj.put("Property", itemsArray[1]);
        obj.put("Ward No.", itemsArray[2]);
        obj.put("Municipality VDC", itemsArray[3]);
        obj.put("District", itemsArray[4]);
        obj.put("Plot No.", itemsArray[5]);
        obj.put("Area", itemsArray[6]);
        obj.put("Name", itemsArray[14]);
        obj.put("Address", itemsArray[15]);
        obj.put("Contact Person", itemsArray[16]);
        obj.put("Contact Number", itemsArray[17]);
        obj.put("Valuation Id", itemsArray[7]);
        obj.put("Valuer Id", itemsArray[8]);
        obj.put("Initial Report Request Date", itemsArray[9]);
        obj.put("Final Report Request Date", itemsArray[10]);
        obj.put("Final Report Submission Date", itemsArray[11]);
        obj.put("Report Received Date", itemsArray[12]);
        obj.put("Valutation Type", itemsArray[13]);
        obj.put("Valuator Email", itemsArray[18]);

        JSONArray jsonArray_valuator = new JSONArray();
        jsonArray_valuator.add(obj);
        this.logObj.servicelog("gridfetchValuation jsonarray::::" + jsonArray_valuator);
        iFormObj.addDataToGrid("table159", jsonArray_valuator);
        this.logObj.servicelog("gridfetchValuation after adddatatogrid");
      }
      this.retVal = "SUCCESS";
      return this.retVal;
    }
    catch (Exception e) {
      this.logObj.servicelog("Exception at gridfetchValuation::" + e);
      this.retVal = "FAIL";
    }return this.retVal; }

  public String gridtogridfetch(String stringData, IFormReference iFormObj)
  {
    JSONArray jsonArray_eba = new JSONArray();
    JSONArray jsonArray_sd = new JSONArray();
    this.logObj.servicelog("GridFetch");
    try {
      int count = 0;
      String pID = (String)iFormObj.getValue("PID");
      String query = "Select BDKeycontactperson, BDContactNo from CS_BorrowersDetails WITH(NOLOCK) where PID ='" + 
        pID + "'";
      String query1 = "select NameofOwner, PlaceStreetName +','+ WardNo +','+  VDCMunicipality +','+  District +', Province '+  Province as Location,PlotNo,Typesofmeasurement, Area, TotalFMV, DistressedValue from CS_Infocollateral WITH(NOLOCK) where PID ='" + 
        pID + "'";
      this.logObj.servicelog("GridFetch query1:::" + query);
      this.logObj.servicelog("GridFetch query1:::" + query1);
      List QueryList = iFormObj.getDataFromDB(query);
      ListIterator QueryListItr = QueryList.listIterator();
      List QueryList1 = iFormObj.getDataFromDB(query1);
      ListIterator QueryListItr1 = QueryList1.listIterator();
      this.logObj.servicelog("GridFetch QueryListItr1:::" + QueryListItr1);
      while (QueryListItr.hasNext())
      {
        List rowData = (List)QueryListItr.next();
        String[] itemsArray = new String[rowData.size()];
        itemsArray = (String[])rowData.toArray(itemsArray);
        JSONObject obj = new JSONObject();
        obj.put("Key Person", itemsArray[0]);
        obj.put("Contact No", itemsArray[1]);
        jsonArray_eba.add(obj);
        this.logObj.servicelog("GridFetch after adddatatogrid");
      }
      while (QueryListItr1.hasNext())
      {
        this.logObj.servicelog("Inside QueryListItr1");
        List rowData1 = (List)QueryListItr1.next();
        String[] itemsArray1 = new String[rowData1.size()];
        itemsArray1 = (String[])rowData1.toArray(itemsArray1);
        this.logObj.servicelog("#####Inside QueryListItr1");
        JSONObject obj1 = new JSONObject();
        obj1.put("Name of Qwner", itemsArray1[0]);
        this.logObj.servicelog("1");
        obj1.put("Location", itemsArray1[1]);
        this.logObj.servicelog("2");
        obj1.put("PlotNo", itemsArray1[2]);
        this.logObj.servicelog("3");
        obj1.put("Types of measurement", itemsArray1[3]);
        this.logObj.servicelog("4");
        obj1.put("Area", itemsArray1[4]);
        this.logObj.servicelog("5");
        obj1.put("Total FMV", itemsArray1[5]);
        this.logObj.servicelog("6");
        obj1.put("Distressed Value", itemsArray1[6]);
        this.logObj.servicelog("7");
        jsonArray_sd.add(obj1);
        this.logObj.servicelog("GridFetch array::" + jsonArray_sd);
        this.logObj.servicelog("GridFetch after adddatatogrid");
      }
      iFormObj.addDataToGrid("table12", jsonArray_eba);
      this.logObj.servicelog("GridFetch array::" + jsonArray_eba);
      iFormObj.addDataToGrid("table7", jsonArray_sd);
      this.logObj.servicelog("GridFetch array::" + jsonArray_sd);
      String res = "SUCCESS";
      this.logObj.servicelog("GridFetch Success");
      return res;
    }
    catch (Exception e)
    {
      this.logObj.servicelog("Excepation at GridFetch.." + e);
    }return "ERROR";
  }

  public String gridtogridfetchCSVR(String stringData, IFormReference iFormObj)
  {
    
    this.logObj.servicelog("GridFetchCSVR");
    JSONArray jsonArray_csvr = new JSONArray();
    try {
      String pID = stringData;
      String query = "select Property,NameofOwner,Daam2,Province,District,VDCMunicipality,Ropani,Paisa1,Builtuparea,Noofstorey,Yearconst,Roadwidth,"
      		+ "Naksapassobtained,AreaNetofalldeduction,Ihaveconfirmedpossession,TotalFMVland,TotalFMVofBuilding,TotalFMV from CS_Infocollateral WITH(NOLOCK) where PID ='" + 
        pID + "'";
      this.logObj.servicelog("GridFetchCSVR query1:::" + query);
      List QueryList = iFormObj.getDataFromDB(query);
      ListIterator QueryListItr = QueryList.listIterator();
      this.logObj.servicelog("GridFetchCSVR QueryListItr:::" + QueryListItr);
      while (QueryListItr.hasNext())
      {
        List rowData = (List)QueryListItr.next();
        String[] itemsArray = new String[rowData.size()];
        itemsArray = (String[])rowData.toArray(itemsArray);
        JSONObject obj = new JSONObject();
        obj.put("Security Type", itemsArray[0]);
        //obj.put("Already Mortgage/ New", itemsArray[1]);
        obj.put("Current Owner", itemsArray[1]);
        //obj.put("Proposed Owner", itemsArray[3]);
        obj.put("Relationship", itemsArray[2]);
        obj.put("Province", itemsArray[3]);
        obj.put("District", itemsArray[4]);
        obj.put("Municipality/VDC", itemsArray[5]);
        //obj.put("City", itemsArray[0]);
        //obj.put("Street", itemsArray[1]);
        obj.put("Plot no", itemsArray[6]);
        //obj.put("Area as per LORC", itemsArray[1]);
        obj.put("Area as per actual measurement", itemsArray[7]);
        //obj.put("Area considered for valuation", itemsArray[1]);
        obj.put("Total built up area", itemsArray[8]);
        obj.put("No. of storey", itemsArray[9]);
        //obj.put("Naksa Pass Date", itemsArray[0]);
        //obj.put("Date of BCC", itemsArray[1]);
        obj.put("Age of building", itemsArray[10]);
        //obj.put("Valuators ID", itemsArray[1]);
        //obj.put("Valuator's Name", itemsArray[0]);
        //obj.put("Date of Valuation", itemsArray[1]);
        //obj.put("Extension ratio of valuation", itemsArray[0]);
        obj.put("Road Width", itemsArray[11]);
        //obj.put("Road Direction", itemsArray[0]);
        obj.put("Required ROW Deducted?", itemsArray[12]);
        //obj.put("Frontage/Width", itemsArray[]);
        obj.put("Whether cooling of period elapsed", itemsArray[13]);
        //obj.put("Mode of transfer", itemsArray[]);
        //obj.put("Transfer Date", itemsArray[]);
        //obj.put("Registration Amount", itemsArray[]);
        obj.put("Whether Falls under watchlist", itemsArray[14]);
        obj.put("FMV of land", itemsArray[15]);
        obj.put("FMV of Building", itemsArray[16]);
        obj.put("Total FMV (Land & Building)", itemsArray[17]);
        //obj.put("Internal cross collateralized between group units", itemsArray[]);
        
        jsonArray_csvr.clear();
        jsonArray_csvr.add(obj);
        this.logObj.servicelog("GridFetchCSVR array::" + jsonArray_csvr);
        iFormObj.addDataToGrid("Real_Estate_Collateral_Table", jsonArray_csvr);
        this.logObj.servicelog("GridFetchCSVR after adddatatogrid");
      }
      
      
      String res = "SUCCESS";
      this.logObj.servicelog("GridFetchCSVR Success");
      return res;
    }
    catch (Exception e)
    {
      this.logObj.servicelog("Excepation at GridFetchCSVR.." + e);
    return "ERROR";
    }
  }

}