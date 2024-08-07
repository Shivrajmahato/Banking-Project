package com.newgen.iforms.util;

import com.newgen.common.LogMessages;
import com.newgen.iforms.EControl;
import com.newgen.iforms.custom.IFormReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewGenUtil {

    public static final String TABLE_2_USERS_OF_GROUPS = "table2_UsersOfGroups";
    public static final String TABLE_1_ASSIGNED_QUERY_USER = "table1_AssignedQueryUser";

    private NewGenUtil() {

    }

    public static HttpURLConnection getHttpURLConnection(String endpoint) {
        try {
            URL url = new URL(endpoint);
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            return null;
        }
    }

    public static JSONObject getParsedJsonObj(String data) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public static JSONArray getParsedJsonArray(String data) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONArray) parser.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String[] getSplitRespData(String input) {
        String[] param = input.split("##");
        LogMessages.statusLog.info("response after split is:" + param);
        return param;
    }

    public static String[] getSplitReqData(String input) {
        String[] param = input.split("#");
        LogMessages.statusLog.info("request after split is:" + param);
        return param;
    }

    public static void getFailureResponse(String e, JSONArray response, JSONObject res, JSONObject obj) {
        res.put(NewGenUtilConstants.STATUS, NewGenUtilConstants.FAILURE);
        res.put(NewGenUtilConstants.MESSAGE, String.valueOf(obj.get("Message")));
        if (!e.isEmpty()) {
            response.add(e);
        }
    }

    public static void setEControlWithQuery(String controlId, IFormReference iformObj, String query) {
        ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_strQuery(query);
        ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
        ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
    }

    public static void setEControlWithoutQuery(String controlId, IFormReference iformObj) {
        ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnableNext(true);
        ((EControl) iformObj.getIFormControl(controlId)).getM_objPicklist().setM_bEnablePrevious(true);
    }


    public static void pickListSearchHookExtracted(String controlId, IFormReference iFormObj, String searchString, int colIndex) {
        LogMessages.statusLog.info("inside picklistSearchHook, controlId--" + controlId);
        if (controlId.equals(TABLE_2_USERS_OF_GROUPS)) {
            getTable2UsersOfGroupsQuery(controlId, iFormObj, searchString, colIndex);
        } else if (controlId.equals(TABLE_1_ASSIGNED_QUERY_USER)) {
            getTable1AssignedUserQuery(controlId, iFormObj, searchString, colIndex);
        } else {
            setEControlWithoutQuery(controlId, iFormObj);
        }
    }

    public static boolean pickListPreHookExtracted(String controlId, IFormReference iFormObj) {
        LogMessages.statusLog.info("inside picklistPreHook, controlId--" + controlId);
        if (controlId.equalsIgnoreCase(TABLE_2_USERS_OF_GROUPS)) {
            getTable2UsersOfGroupsQuery(controlId, iFormObj, "", 0);
            return true;
        }
        if (controlId.equals(TABLE_1_ASSIGNED_QUERY_USER)) {
            getTable1AssignedUserQuery(controlId, iFormObj, "", 0);
            return true;
        }
        setEControlWithoutQuery(controlId, iFormObj);
        return false;
    }

    private static void getTable1AssignedUserQuery(String controlId, IFormReference iformObj, String searchString, int colIndex) {
        String query = "select username  as 'UserId',concat(PersonalName,' ',FamilyName) as 'UserName' from pdbuser(nolock) where userindex in (select userindex from PDBGroupMember(nolock) where GroupIndex IN (select GroupIndex from PDBGroup(nolock) where GroupName='LOS Query Group'))";
        getQuerySearchString(controlId, iformObj, searchString, colIndex, query);
    }

    private static void getTable2UsersOfGroupsQuery(String controlId, IFormReference iformObj, String searchString, int colIndex) {
        String query = picklistCustomQueryForSearch(iformObj);
        getQuerySearchString(controlId, iformObj, searchString, colIndex, query);
    }

    private static String picklistCustomQueryForSearch(IFormReference iFormObj) {
        String query;
        StringBuilder user = new StringBuilder();
        JSONArray jsonarray = iFormObj.getDataFromGrid("table2");
        if (!jsonarray.isEmpty()) {
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                user.append(",'").append(obj.get("Group Users")).append("'");
            }
        }

        LogMessages.statusLog.info("user after if condition --" + user);
        String userCondition;
        if (!user.toString().equals("")) {
            user.substring(1, user.length());

            LogMessages.statusLog.info("Final user list--" + user);
            userCondition = "Domanin_Name not in(" + user + ")";
        } else {
            userCondition = "Domanin_Name not in('')";
        }

        query = "select distinct Domanin_Name as 'User ID',User_Name as 'UserName' from LOS_UserAuthorityGroupMapping(nolock) where Authroity_GroupName='" + iFormObj.getValue("table2_AuthoritiesGroup") + "' and " + userCondition;

        LogMessages.statusLog.info("query --" + query);
        return query;
    }

    private static void getQuerySearchString(String controlId, IFormReference iformObj, String searchString, int colIndex, String query) {
        if (!"".equals(searchString) && colIndex != -1) {
            if (colIndex == 0) {
                query = query + " and username like '%" + searchString + "%'";
            } else if (colIndex == 1) {
                query = query + " and concat(PersonalName,' ',FamilyName) like '%" + searchString + "%'";
            }
        }
        LogMessages.statusLog.info("colIndex-->" + colIndex + " and searchString--" + searchString);

        if (colIndex == -1 && !"".equals(searchString)) {
            query = query + " and (username like '%" + searchString + "%' or concat(PersonalName,' ',FamilyName) like '%" + searchString + "%')";
        }

        LogMessages.statusLog.info("**picklist Search query--" + query);
        setEControlWithQuery(controlId, iformObj, query);
    }

}
