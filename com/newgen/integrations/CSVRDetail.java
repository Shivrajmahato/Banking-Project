package com.newgen.integrations;

import com.newgen.common.LogMessages;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import org.json.XML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;


public class CSVRDetail {
    public static void main(String args[]) throws IOException
    {
        System.out.println(getData(getSPToken(),"36"));
    }
    
    public static String getData(String token, String item) throws MalformedURLException, IOException
    {
        String endpoint = "https://nabilbank.sharepoint.com/sites/ProcessAutomation/_api/lists/getbytitle('Customer Visit Data Collection')/items('"+item+"')?$select=AttachmentFiles,ID,Attachments,Title,"
                + "VisitedOn,Lat,Lon,Ownership_x0020_Type,Ownership_x0020_Date_x0020_of_x0,Property,Ownership_x0020_Mode_x0020_of_x0,"
                + "Shape_x0020_of_x0020_Land,Distance_x0020_from_x0020_our_x0,Motorable_x0020_Road_x0020_Acces,Collateral_x0020_is_x0020_not_x0,"
                + "Collateral_x0020_is_x0020_not_x00,Collateral_x0020_is_x0020_not_x01,Road_x0020_Width_x0020_As_x0020_,"
                + "Collateral_x0020_doesn_x2019_t_x,Any_x0020_High_x002d_tension_x00,Any_x0020_river_x002f_canal_x002,"
                + "Road_x0020_Access_x0020_from,Created,Shape_x0020_of_x0020_Land_x0020_,Required_x0020_setback_x0020_wou,Required_x0020_setback_x0020_wou0,"
                + "Required_x0020_setback_x0020_wou1"
                + "&$expand=AttachmentFiles";
        if(endpoint.contains(" "))
        {
           endpoint = endpoint.replace(" ", "%20"); 
        }       
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization","Bearer "+token);
        //conn.setRequestProperty("Content-Type","application/xml");
        conn.setRequestProperty("Accept","application/json;odata=verbose");
        conn.setRequestProperty("Content-Type","application/json;odata=verbose");
        conn.setRequestMethod("GET");

        StringBuffer response;
        
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) 
        {
            String output;
            response = new StringBuffer();
            while ((output = in.readLine()) != null) 
            {
                response.append(output);
            }
        }
        try {
            JSONParser parser = new JSONParser();
            LogMessages.statusLog.info("hello sunil");
            LogMessages.statusLog.info(response);
        //org.json.JSONObject json = XML.toJSONObject(response.toString());  
        //org.json.JSONObject entry = json.getJSONObject("entry");
        //org.json.JSONArray link = entry.getJSONArray("link");
        //org.json.JSONObject minline = link.getJSONObject(1);
        //org.json.JSONObject minline1 = minline.getJSONObject("m:inline");
        //org.json.JSONObject feed = minline1.getJSONObject("feed");
        //org.json.JSONArray innerEntry = feed.getJSONArray("entry");  
        //org.json.JSONObject firstEntry= innerEntry.getJSONObject(0);
        //org.json.JSONObject content= firstEntry.getJSONObject("content");
        //org.json.JSONObject mproperties= content.getJSONObject("m:properties");
        //String image1 = mproperties.getString("d:ServerRelativeUrl");
        //org.json.JSONObject json = XML.toJSONObject(response.toString()); 
        JSONObject obj = (JSONObject) parser.parse(response.toString());
        //String res = mproperties.getString("d:ServerRelativeUrl") + "#" + mproperties.getString("d:Title");
        //return "https://nabilbank.sharepoint.com"+image1;
        String res = String.valueOf(obj.get("d"));
        
        JSONObject obj2 = (JSONObject) parser.parse(res);
        //String attachedFiles = String.valueOf(obj2.get("AttachmentFiles"));
        String res2 = String.valueOf(((JSONObject) obj2.get("AttachmentFiles")));
        //JSONObject obj4 = (JSONObject) parser.parse((JSONObject) obj2.get("AttachmentFiles"));
        //JSONObject json = new JSONObject(obj3);   
        
        JSONObject obj3 = (JSONObject) parser.parse(res2);
        JSONArray jsonArrObj = (JSONArray) obj3.get("results");
        String SiteImage = "";
        String LocationMap = "";
        LogMessages.statusLog.info("res2_before:"+res2);
            LogMessages.statusLog.info("size of array:"+jsonArrObj.size());
            for (int i = 0; i < jsonArrObj.size(); i++) {  
              JSONObject explrObject = (JSONObject) jsonArrObj.get(i);
              //String FileName = String.valueOf(explrObject.get("FileName"));
              String RelativeUrl = String.valueOf(explrObject.get("ServerRelativeUrl"));
              if(i == 0){
                  LocationMap = "https://nabilbank.sharepoint.com" + RelativeUrl;
              }
              else{
 
                  SiteImage = SiteImage + "https://nabilbank.sharepoint.com" + RelativeUrl + "|";
              }
            }
        
        String Id = String.valueOf(obj2.get("ID"));
        String Title = String.valueOf(obj2.get("Title"));
        String VisitedOn = String.valueOf(obj2.get("VisitedOn"));
        String Lat = String.valueOf(obj2.get("Lat"));
        String Lon = String.valueOf(obj2.get("Lon"));
        String RoadAccessFrom = String.valueOf(obj2.get("Road_x0020_Access_x0020_from"));
        String OwnershipType = String.valueOf(obj2.get("Ownership_x0020_Type"));
        String PropertyType = String.valueOf(obj2.get("Property"));
        String ModeOfTransfer = String.valueOf(obj2.get("Ownership_x0020_Mode_x0020_of_x0"));
        String DateOfTransfer = String.valueOf(obj2.get("Ownership_x0020_Date_x0020_of_x0"));
        String ShapeOfLand = String.valueOf(obj2.get("Shape_x0020_of_x0020_Land"));
        String DistanceFromBranch = String.valueOf(obj2.get("Distance_x0020_from_x0020_our_x0"));
        String QualityOfRoadAccess = String.valueOf(obj2.get("Motorable_x0020_Road_x0020_Acces"));
        String WidthOfRoad = String.valueOf(obj2.get("Road_x0020_Width_x0020_As_x0020_"));
        String LandIsNotWithin60 = String.valueOf(obj2.get("Collateral_x0020_is_x0020_not_x0"));
        String LandIsNotWithin100 = String.valueOf(obj2.get("Collateral_x0020_is_x0020_not_x00"));
        String LandIsNotWithin500 = String.valueOf(obj2.get("Collateral_x0020_is_x0020_not_x01"));
        String IsHighTension = String.valueOf(obj2.get("Any_x0020_High_x002d_tension_x00"));
        String IsRiverCanal = String.valueOf(obj2.get("Any_x0020_river_x002f_canal_x002"));
        String IsPondPool = String.valueOf(obj2.get("Collateral_x0020_doesn_x2019_t_x"));
        String IsVerticallySloped = String.valueOf(obj2.get("Shape_x0020_of_x0020_Land_x0020_"));
        String AffectValueOfLand = String.valueOf(obj2.get("Required_x0020_setback_x0020_wou"));
        String AffectShapeOfLand = String.valueOf(obj2.get("Required_x0020_setback_x0020_wou0"));
        String AffectSalabilityOfLand = String.valueOf(obj2.get("Required_x0020_setback_x0020_wou1"));
        
        //LogMessages.statusLog.info("ID is:"+Id);
        //LogMessages.statusLog.info("Title:"+Title);
        //LogMessages.statusLog.info(res);
        String fullRes = Id + "#sunil#" + Title + "#sunil#" + VisitedOn + "#sunil#" + Lat + "#sunil#" + Lon + "#sunil#" + RoadAccessFrom +"#sunil#" + LocationMap + 
                "#sunil#" + SiteImage + "#sunil#" + OwnershipType+ "#sunil#" +PropertyType+ "#sunil#" +RoadAccessFrom+ "#sunil#" +DateOfTransfer+ "#sunil#" +
                ShapeOfLand+ "#sunil#" +DistanceFromBranch+ "#sunil#" +QualityOfRoadAccess+ "#sunil#" + WidthOfRoad+ "#sunil#" +LandIsNotWithin60+ "#sunil#" +
                LandIsNotWithin100+ "#sunil#" +LandIsNotWithin500+ "#sunil#" +IsHighTension+ "#sunil#" +IsRiverCanal +"#sunil#" + IsPondPool + "#sunil#" + ModeOfTransfer + 
                "#sunil#" + IsVerticallySloped + "#sunil#" +AffectValueOfLand + "#sunil#" + AffectShapeOfLand + "#sunil#" + AffectSalabilityOfLand;
        return fullRes;
        }
        catch(Exception e){
        return "Error";
        }
    }
    
    public static String getSPToken()
    {
        String endpoint = "https://accounts.accesscontrol.windows.net/bd121dc3-3aad-4525-9936-bb9a4acee0a1/tokens/OAuth/2/";
        
        String grant_type = "client_credentials";
        //String client_id = "c89b7511-2533-49fa-931e-7d1492407c1a@bd121dc3-3aad-4525-9936-bb9a4acee0a1";
        //String client_secret = "3yPbejMm6jCfRADGaC1yg6aqvG/i3JfJFKb2PPR4+OQ=";
        String client_id = "a26e550b-b7e3-4607-8565-e87923cb7ca1@bd121dc3-3aad-4525-9936-bb9a4acee0a1";
	String client_secret = "cKeRrSIQH/IAPmnQswVzp1VLbLNbGw5EDZZh5uffJP4=";
        String resource = "00000003-0000-0ff1-ce00-000000000000/nabilbank.sharepoint.com@bd121dc3-3aad-4525-9936-bb9a4acee0a1";
        URL url;
        InputStream stream;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(endpoint);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            String data = URLEncoder.encode("grant_type", "UTF-8")
                    + "=" + URLEncoder.encode(grant_type, "UTF-8");

            data += "&" + URLEncoder.encode("client_id", "UTF-8") + "="
                    + URLEncoder.encode(client_id, "UTF-8");
            
            data += "&" + URLEncoder.encode("client_secret", "UTF-8") + "="
                    + URLEncoder.encode(client_secret, "UTF-8");
            
            data += "&" + URLEncoder.encode("resource", "UTF-8") + "="
                    + URLEncoder.encode(resource, "UTF-8");

            urlConnection.connect();

            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();

            stream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), 8);
            String result = reader.readLine();
            Object obj=JSONValue.parse(result);  
            JSONObject jsonObject = (JSONObject) obj;
            String token = (String)jsonObject.get("access_token");
            return token;

        } catch (IOException e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
