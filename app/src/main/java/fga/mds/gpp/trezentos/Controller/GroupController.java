package fga.mds.gpp.trezentos.Controller;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fga.mds.gpp.trezentos.Controller.Util.SortStudentsUtil;
import fga.mds.gpp.trezentos.DAO.GetFirstGrades;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.Groups;
import fga.mds.gpp.trezentos.Model.UserClass;

public class GroupController {
    private Integer groupSize = 0;
    private Integer totalStudents = 0;
    private Exam exam = new Exam();
    private UserClass userClass = new UserClass();
    private static HashMap<String, Double> firstGrades = new HashMap<>();
    private static HashMap<String, Integer> groups = new HashMap<>();

    public GroupController () {

    }

    private static HashMap<String, Double> convertToHashMapDouble(String firstGrades) {

        HashMap<String, Double> myHashMap = new HashMap<>();

        try {

            JSONArray jArray = new JSONArray(firstGrades);
            JSONObject jObject = null;
            String keyString=null;

            for (int i = 0; i < jArray.length(); i++) {
                jObject = jArray.getJSONObject(i);
                keyString = (String)jObject.names().get(0);
                myHashMap.put(keyString, jObject.getDouble(keyString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myHashMap;
    }

    private static HashMap<String, Integer> convertToHashMapInt(String firstGrades) {

        HashMap<String, Integer> myHashMap = new HashMap<>();

        if (firstGrades == null) return null;

        try {

            JSONArray jArray = new JSONArray(firstGrades);
            JSONObject jObject = null;
            String keyString=null;

            for (int i = 0; i < jArray.length(); i++) {
                jObject = jArray.getJSONObject(i);
                keyString = (String)jObject.names().get(0);
                myHashMap.put(keyString, jObject.getInt(keyString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myHashMap;
    }

    public HashMap<String, Double> getFirstGrades (String name, String userClassName, String classOwnerEmail) {
        GetFirstGrades getFirstGrades = new GetFirstGrades(name, userClassName, classOwnerEmail);

        String serverResponse = getFirstGrades.get();
        firstGrades = convertToHashMapDouble(serverResponse);

        return firstGrades;
    }

}
