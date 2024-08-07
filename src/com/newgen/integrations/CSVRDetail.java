package com.newgen.integrations;

import com.newgen.common.LogMessages;
import com.newgen.iforms.util.NewGenUtil;
import com.newgen.iforms.util.NewGenUtilConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class CSVRDetail {

    public static final String SUNIL = "#sunil#";

    private CSVRDetail() {

    }

    public static String getData(String token, String item) throws IOException {
        String endpoint = NewGenUtilConstants.BASE_URL + "/sites/ProcessAutomation/_api/lists/getbytitle('Customer Visit Data Collection')/items('" + item + "')?$select=AttachmentFiles,ID,Attachments,Title,"
                + "VisitedOn,Lat,Lon,Ownership_x0020_Type,Ownership_x0020_Date_x0020_of_x0,Property,Ownership_x0020_Mode_x0020_of_x0,"
                + "Shape_x0020_of_x0020_Land,Distance_x0020_from_x0020_our_x0,Motorable_x0020_Road_x0020_Acces,Collateral_x0020_is_x0020_not_x0,"
                + "Collateral_x0020_is_x0020_not_x00,Collateral_x0020_is_x0020_not_x01,Road_x0020_Width_x0020_As_x0020_,"
                + "Collateral_x0020_doesn_x2019_t_x,Any_x0020_High_x002d_tension_x00,Any_x0020_river_x002f_canal_x002,"
                + "Road_x0020_Access_x0020_from,Created,Shape_x0020_of_x0020_Land_x0020_,Required_x0020_setback_x0020_wou,Required_x0020_setback_x0020_wou0,"
                + "Required_x0020_setback_x0020_wou1"
                + "&$expand=AttachmentFiles";
        if (endpoint.contains(" ")) {
            endpoint = endpoint.replace(" ", "%20");
        }
        HttpURLConnection conn = NewGenUtil.getHttpURLConnection(endpoint);
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setRequestProperty("Accept", "application/json;odata=verbose");
        conn.setRequestProperty("Content-Type", "application/json;odata=verbose");
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String output;
            StringBuilder response = new StringBuilder();
            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            LogMessages.statusLog.info(response);
            JSONObject obj = NewGenUtil.getParsedJsonObj(response.toString());
            String res = String.valueOf(obj.get("d"));

            JSONObject obj2 = NewGenUtil.getParsedJsonObj(res);
            String res2 = String.valueOf(obj2.get("AttachmentFiles"));

            JSONObject obj3 = NewGenUtil.getParsedJsonObj(res2);
            JSONArray jsonArrObj = (JSONArray) obj3.get("results");
            StringBuilder siteImage = new StringBuilder();
            StringBuilder locationMap = new StringBuilder();
            LogMessages.statusLog.info("res2_before:" + res2);
            LogMessages.statusLog.info("size of array:" + jsonArrObj.size());
            for (int i = 0; i < jsonArrObj.size(); i++) {
                JSONObject explrObject = (JSONObject) jsonArrObj.get(i);
                String relativeUrl = String.valueOf(explrObject.get("ServerRelativeUrl"));
                if (i == 0) {
                    locationMap.append(NewGenUtilConstants.BASE_URL).append(relativeUrl);
                } else {
                     siteImage.append(NewGenUtilConstants.BASE_URL).append(relativeUrl).append("|");
                }
            }
            return getFullResponseData(obj2, siteImage.toString(), locationMap.toString());
        } catch (Exception e) {
            return "Error";
        }
    }

    public static String getSPToken() {
        try {
            HttpURLConnection urlConnection = NewGenUtil.getHttpURLConnection(NewGenUtilConstants.ENDPOINT);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            String data = URLEncoder.encode("grant_type", NewGenUtilConstants.UTF_8)
                    + "=" + URLEncoder.encode(NewGenUtilConstants.GRANT_TYPE, NewGenUtilConstants.UTF_8);

            data += "&" + URLEncoder.encode("client_id", NewGenUtilConstants.UTF_8) + "="
                    + URLEncoder.encode(NewGenUtilConstants.CLIENT_ID, NewGenUtilConstants.UTF_8);

            data += "&" + URLEncoder.encode("client_secret", NewGenUtilConstants.UTF_8) + "="
                    + URLEncoder.encode(NewGenUtilConstants.CLIENT_SECCODE, NewGenUtilConstants.UTF_8);

            data += "&" + URLEncoder.encode("resource", NewGenUtilConstants.UTF_8) + "="
                    + URLEncoder.encode(NewGenUtilConstants.RESOURCE, NewGenUtilConstants.UTF_8);

            urlConnection.connect();

            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();

            InputStream stream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8), 8);

            String result = reader.readLine();
            JSONObject jsonObject = NewGenUtil.getParsedJsonObj(result);
            return (String) jsonObject.get("access_token");

        } catch (IOException e) {
            return null;
        }
    }

    private static String getFullResponseData(JSONObject obj2, String siteImage, String locationMap) {
        String id = String.valueOf(obj2.get("ID"));
        String title = String.valueOf(obj2.get("Title"));
        String visitedOn = String.valueOf(obj2.get("VisitedOn"));
        String lat = String.valueOf(obj2.get("Lat"));
        String lon = String.valueOf(obj2.get("Lon"));
        String roadAccessFrom = String.valueOf(obj2.get("Road_x0020_Access_x0020_from"));
        String ownershipType = String.valueOf(obj2.get("Ownership_x0020_Type"));
        String propertyType = String.valueOf(obj2.get("Property"));
        String modeOfTransfer = String.valueOf(obj2.get("Ownership_x0020_Mode_x0020_of_x0"));
        String dateOfTransfer = String.valueOf(obj2.get("Ownership_x0020_Date_x0020_of_x0"));
        String shapeOfLand = String.valueOf(obj2.get("Shape_x0020_of_x0020_Land"));
        String distanceFromBranch = String.valueOf(obj2.get("Distance_x0020_from_x0020_our_x0"));
        String qualityOfRoadAccess = String.valueOf(obj2.get("Motorable_x0020_Road_x0020_Acces"));
        String widthOfRoad = String.valueOf(obj2.get("Road_x0020_Width_x0020_As_x0020_"));
        String landIsNotWithin60 = String.valueOf(obj2.get("Collateral_x0020_is_x0020_not_x0"));
        String landIsNotWithin100 = String.valueOf(obj2.get("Collateral_x0020_is_x0020_not_x00"));
        String landIsNotWithin500 = String.valueOf(obj2.get("Collateral_x0020_is_x0020_not_x01"));
        String isHighTension = String.valueOf(obj2.get("Any_x0020_High_x002d_tension_x00"));
        String isRiverCanal = String.valueOf(obj2.get("Any_x0020_river_x002f_canal_x002"));
        String isPondPool = String.valueOf(obj2.get("Collateral_x0020_doesn_x2019_t_x"));
        String isVerticallySloped = String.valueOf(obj2.get("Shape_x0020_of_x0020_Land_x0020_"));
        String affectValueOfLand = String.valueOf(obj2.get("Required_x0020_setback_x0020_wou"));
        String affectShapeOfLand = String.valueOf(obj2.get("Required_x0020_setback_x0020_wou0"));
        String affectSalabilityOfLand = String.valueOf(obj2.get("Required_x0020_setback_x0020_wou1"));

        return id + SUNIL + title + SUNIL + visitedOn + SUNIL + lat + SUNIL + lon + SUNIL + roadAccessFrom + SUNIL + locationMap +
                SUNIL + siteImage + SUNIL + ownershipType + SUNIL + propertyType + SUNIL + roadAccessFrom + SUNIL + dateOfTransfer + SUNIL +
                shapeOfLand + SUNIL + distanceFromBranch + SUNIL + qualityOfRoadAccess + SUNIL + widthOfRoad + SUNIL + landIsNotWithin60 + SUNIL +
                landIsNotWithin100 + SUNIL + landIsNotWithin500 + SUNIL + isHighTension + SUNIL + isRiverCanal + SUNIL + isPondPool + SUNIL + modeOfTransfer +
                SUNIL + isVerticallySloped + SUNIL + affectValueOfLand + SUNIL + affectShapeOfLand + SUNIL + affectSalabilityOfLand;
    }
}
