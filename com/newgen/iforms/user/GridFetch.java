package com.newgen.iforms.user;

import com.newgen.common.Logging_LOS;
import com.newgen.iforms.custom.IFormReference;
import java.util.List;
import java.util.ListIterator;
import com.newgen.iforms.FormDef;											   
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GridFetch
{
  Logging_LOS logObj = new Logging_LOS();

  public String gridtogridfetch(String stringData, IFormReference iFormObj) { 
	JSONArray jsonArray_eba = new JSONArray();
  
    this.logObj.servicelog("inside Gridfetch");
    try
    {
      int count = 0;
      String pID = (String)iFormObj.getValue("PID");
      String query = "Select BDKeycontactperson, BDContactNo from CS_BorrowersDetails where PID ='" + pID + "'";

      this.logObj.servicelog("inside Gridfetch query::::" + query);

      List QueryList = iFormObj.getDataFromDB(query);
      ListIterator QueryListItr = QueryList.listIterator();
      while (QueryListItr.hasNext())
      {
        List rowData = (List)QueryListItr.next();
        String[] itemsArray = new String[rowData.size()];
        itemsArray = (String[])rowData.toArray(itemsArray);

        JSONObject obj = new JSONObject();
        obj.put("Key Person", itemsArray[0]);
        obj.put("Contact No", itemsArray[1]);

        jsonArray_eba.add(obj);

        iFormObj.addDataToGrid("table12", jsonArray_eba);

        count++;
      }
      return "SUCCESS";
    }
    catch (Exception e)
    {
      this.logObj.servicelog("Exception Gridfetch");
      return "ERROR";
    }
  }
}