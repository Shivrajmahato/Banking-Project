package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BSVRDetail {
  private BSVRDetail() {

  }
  public static String getData(String token, String item) throws MalformedURLException, IOException {
           LogMessages.statusLog.info("Inside BSVR"); 
    String endpoint = NewGenUtilConstants.BASE_URL + "/sites/Automation/_api/lists/getbytitle('BSVR%20System')/items('" + item + "')?$select=AttachmentFiles,ID,Attachments,Title," +
      "VisitedOn,Lat,Lon,Address_x0020_of_x0020_Business_,Business_x0020_was_x0020_in_x002,Overall_x0020_operation_x0020_of,Basic_x0020_requisites_x0020_for,The_x0020_amount_x0020_of_x0020_," +
      "The_x0020_quality_x0020_of_x0020,The_x0020_key_x0020_person_x0020" +
      "&$expand=AttachmentFiles";
    
    if (endpoint.contains(" ")) {
      endpoint = endpoint.replace(" ", "%20");
    }
    LogMessages.statusLog.info("Inside BSVR URL:: "+endpoint); 
    HttpURLConnection conn = NewGenUtil.getHttpURLConnection(endpoint);
    conn.setRequestProperty("Authorization", "Bearer " + token);
    conn.setRequestProperty("Accept", "application/json;odata=verbose");
    conn.setRequestProperty("Content-Type", "application/json;odata=verbose");
    conn.setRequestMethod("GET");
    LogMessages.statusLog.info("Inside BSVR#"); 

    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      String output;
      StringBuilder response = new StringBuilder();
      while ((output = in.readLine()) != null) {
        response.append(output);
      }
      LogMessages.statusLog.info(response);
      JSONObject obj = NewGenUtil.getParsedJsonObj(response.toString());
      String res1 = String.valueOf(obj.get("d"));

      JSONObject obj2 = NewGenUtil.getParsedJsonObj(res1);
      String res2 = String.valueOf(obj2.get("AttachmentFiles"));
      JSONObject obj3 = NewGenUtil.getParsedJsonObj(res2);
      JSONArray jsonArrObj = (JSONArray) obj3.get("results");
      JSONArray siteImagesArray = new JSONArray();
      StringBuilder siteImage = new StringBuilder();
      LogMessages.statusLog.info("res2_before:" + res2);
      LogMessages.statusLog.info("size of array:" + jsonArrObj.size());
      for (int i = 0; i < jsonArrObj.size(); i++) {
        JSONObject explrObject = (JSONObject) jsonArrObj.get(i);
        siteImage.append(String.valueOf(explrObject.get("FileName"))).append("|");
        siteImagesArray.add(String.valueOf(explrObject.get("FileName")));
      }

      String Id = String.valueOf(obj2.get("ID"));
      String Title = String.valueOf(obj2.get("Title"));
      String VisitedOn = String.valueOf(obj2.get("VisitedOn"));
      String Lat = String.valueOf(obj2.get("Lat"));
      String Lon = String.valueOf(obj2.get("Lon"));
      String BusinessAddress = String.valueOf(obj2.get("Address_x0020_of_x0020_Business_"));
      String BusinessOperation = String.valueOf(obj2.get("Business_x0020_was_x0020_in_x002"));
      String OveralloperationOf = String.valueOf(obj2.get("Overall_x0020_operation_x0020_of"));
      String BasicRequisitesFor = String.valueOf(obj2.get("Basic_x0020_requisites_x0020_for"));
      String AmountOfInventory = String.valueOf(obj2.get("The_x0020_amount_x0020_of_x0020_"));
      String QualityOfInventory = String.valueOf(obj2.get("The_x0020_quality_x0020_of_x0020"));
      String KeyPerson = String.valueOf(obj2.get("The_x0020_key_x0020_person_x0020"));

      JSONObject res = new JSONObject();
      res.put("Id", Id);
      res.put("ClientName", Title);
      res.put("VisitedOn", VisitedOn);
      res.put("Lat", Lat);
      res.put("Lon", Lon);
      res.put("BusinessAddress", BusinessAddress);
      res.put("BusinessOperation", BusinessOperation);
      res.put("OveralloperationOf", OveralloperationOf);
      res.put("BasicRequisitesFor", BasicRequisitesFor);
      res.put("AmountOfInventory", AmountOfInventory);
      res.put("QualityOfInventory", QualityOfInventory);
      res.put("KeyPerson", KeyPerson);
//      res.put("SiteImage", siteImage);
      res.put("SiteImage",siteImagesArray);

      return res.toString();
    } catch (Exception e) {
      LogMessages.statusLog.info("Inside BSVR exception"+e);   
      return "Error";
    }
  }

  public static String getSPToken() {
    try {
      LogMessages.statusLog.info("Inside BSVR getSPToken");   
      HttpURLConnection urlConnection = NewGenUtil.getHttpURLConnection(NewGenUtilConstants.ENDPOINT);
      urlConnection.setRequestMethod("POST");
      urlConnection.setDoOutput(true);
      LogMessages.statusLog.info("Inside BSVR getSPToken after URL conn:");   

      String data = URLEncoder.encode("grant_type", NewGenUtilConstants.UTF_8) +
        "=" + URLEncoder.encode(NewGenUtilConstants.GRANT_TYPE, NewGenUtilConstants.UTF_8);

      data += "&" + URLEncoder.encode("client_id", NewGenUtilConstants.UTF_8) + "=" +
        URLEncoder.encode(NewGenUtilConstants.CLIENT_ID2, NewGenUtilConstants.UTF_8);

      data += "&" + URLEncoder.encode("client_secret", NewGenUtilConstants.UTF_8) + "=" +
        URLEncoder.encode(NewGenUtilConstants.CLIENT_SECCODE2, NewGenUtilConstants.UTF_8);

      data += "&" + URLEncoder.encode("resource", NewGenUtilConstants.UTF_8) + "=" +
        URLEncoder.encode(NewGenUtilConstants.RESOURCE, NewGenUtilConstants.UTF_8);

      urlConnection.connect();
      LogMessages.statusLog.info("Inside BSVR getSPToken");   
      OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
      wr.write(data);
      wr.flush();

      InputStream stream = urlConnection.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8), 8);

      String result = reader.readLine();
      JSONObject jsonObject = NewGenUtil.getParsedJsonObj(result);
      LogMessages.statusLog.info("Inside BSVR getSPToken token:"+(String) jsonObject.get("access_token"));   
      return (String) jsonObject.get("access_token");

    } catch (IOException e) {
      LogMessages.statusLog.info("Inside BSVR getSPToken"+e);   
      return null;
    }
  }
}