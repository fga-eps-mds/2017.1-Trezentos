package fga.mds.gpp.trezentos.Controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.Controller.Util.SortStudentsUtil;
import fga.mds.gpp.trezentos.DAO.GetDao;
import fga.mds.gpp.trezentos.DAO.PutDao;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.Model.UserClass;
import okhttp3.HttpUrl;
import okhttp3.MediaType;

public class GroupController {
    private Integer groupSize = 0;
    private Integer totalStudents = 0;
    private Exam exam = new Exam();
    private UserClass userClass = new UserClass();
    private static HashMap<String, Double> firstGrades = new HashMap<>();
    private static HashMap<String, Integer> groups = new HashMap<>();

    public GroupController () {

    }

    private static HashMap<String, Double> convertToHashMapDouble(String value) {
        if (value.length() < 2) return null;
        String[] keyValuePairs = value.substring(1, value.length()-1).split(",");              //split the string to creat key-value pairs
        HashMap<String, Double> map = new HashMap<>();

        for(String pair : keyValuePairs) {                      //iterate over the pairs
            String[] entry = pair.split("=");                   //split the pairs to get key and value
            map.put(entry[0].trim(), Double.valueOf(entry[1].trim()));
        }

        return map;
    }

    private static HashMap<String, Integer> convertToHashMapInt(String value) {
        if (value.length() < 2) return null;
        String[] keyValuePairs = value.substring(1, value.length()-1).split(",");              //split the string to creat key-value pairs
        HashMap<String,Integer> map = new HashMap<>();

        for(String pair : keyValuePairs) {                      //iterate over the pairs
            String[] entry = pair.split("=");                   //split the pairs to get key and value
            map.put(entry[0].trim(), Integer.valueOf(entry[1].trim()));
        }

        return map;
    }

//    public static HashMap<String, Double> getFirstGrades (String name, String userClassName,
//                                                          String classOwnerEmail) {
//        GetFirstGrades getFirstGrades = new GetFirstGrades(name, userClassName, classOwnerEmail);
//
//        String serverResponse = getFirstGrades.get();
//        firstGrades = convertToHashMapDouble(serverResponse);
//
//        return firstGrades;
//    }

    public static HashMap<String, Double> getFirstGrades(String name, String userClassName, String classOwnerEmail) {
        String url = "https://trezentos-api.herokuapp.com/api/exam/first_grades";
        String urlWithParameters = getUrlWithParameters(url, name, userClassName, classOwnerEmail);
        String serverResponse = new GetDao(urlWithParameters).get();
        firstGrades = convertToHashMapDouble(serverResponse);

        return firstGrades;
    }

    public static HashMap<String, Integer> getGroups (String name, String userClassName,
                                                      String classOwnerEmail) {
        String url = "https://trezentos-api.herokuapp.com/api/exam/groups";
        String urlWithParameters = getUrlWithParameters(url, name, userClassName, classOwnerEmail);
        String serverResponse = new GetDao(urlWithParameters).get();
        groups = convertToHashMapInt(serverResponse);

        return groups;
    }

    private static String getUrlWithParameters(String url, String name, String userClassName,
                                               String classOwnerEmail){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter("classOwnerEmail", classOwnerEmail);
        builder.addQueryParameter("userClassName", userClassName);
        builder.addQueryParameter("name", name);

        return builder.build().toString();
    }


//    public static HashMap<String, Integer> getGroups (String name, String userClassName,
//                                                      String classOwnerEmail) {
//
//        String serverResponse = new RetrieveGroups(name, userClassName, classOwnerEmail).get();
//        groups = convertToHashMapInt(serverResponse);
//
//        return groups;
//    }

    public boolean sortAndSaveGroups(HashMap<String, Double> grades, UserClass userClass, String email, Exam exam) {
        Map<String, Integer> sortedGroups = SortStudentsUtil.sortGroups(grades,
                userClass.getSizeGroups(), userClass.getStudents().size());

        return saveGroups(sortedGroups.toString(), userClass, email, exam);
    }

//    private boolean saveGroups(String groups, UserClass userClass, String email, Exam exam) {
//
//        String response = "false";
//
//        try {
//            response = new SaveGroupsRequest(email, userClass.getClassName(), exam.getNameExam(),
//                    groups).execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        return response.equals("true");
//    }


    private boolean saveGroups(String groups, UserClass userClass, String email, Exam exam) {
        String response = "false";
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        String body = getJsonBody(email, userClass.getClassName(), exam.getNameExam(), groups);
        PutDao putDao = new PutDao("https://trezentos-api.herokuapp.com/api/exam/groups",
                                        json, body);

        try {
            response = putDao.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return response.equals("true");
    }

    private String getJsonBody(String email, String className, String examName, String groupsClass) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email);
            jsonObject.put("userClassName", className);
            jsonObject.put("name", examName);
            jsonObject.put("groups", groupsClass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public static ArrayList<Student> setSpecificGroupAndGrades (String userEmail,
                                                                HashMap<String, Double> firstGrades,
                                                /* HashMap<String, Double> secondGrades,*/
                                                 HashMap<String, Integer> groups){
        ArrayList<Student> groupAndGrades = new ArrayList<>();
        int userNumberGroup = groups.get(userEmail), it = 0;
        for (Map.Entry<String, Integer> entry : groups.entrySet()){
            if (entry.getValue().equals(userNumberGroup)){
                Student student = new Student();
                student.setStudentEmail(entry.getKey());
                groupAndGrades.add(it, student);
                it++;
            }
        }

        int groupsAndGradesSize = it; it = 0;
        for (int i = 0; i < groupsAndGradesSize; i++){
            Student studentGroup = groupAndGrades.get(i);

            for (Map.Entry<String, Double> entry : firstGrades.entrySet()){
                if (studentGroup.getStudentEmail().equals(entry.getKey())){
                    studentGroup.setFirstGrade(entry.getValue());
                    groupAndGrades.set(it, studentGroup);
                    it++;
                }
            }
        }

        return groupAndGrades;
    }
}