package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.GetDao;
import fga.mds.gpp.trezentos.DAO.PutDao;
import fga.mds.gpp.trezentos.DAO.RequestHandler;
import fga.mds.gpp.trezentos.DAO.URLs;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.StudentExamActivity;
import okhttp3.HttpUrl;

import static fga.mds.gpp.trezentos.DAO.URLs.URL_ALL_CLASS_AVALIABLE;
import static fga.mds.gpp.trezentos.DAO.URLs.URL_CLASS_FROM_PERSON;
import static fga.mds.gpp.trezentos.DAO.URLs.URL_CLASS_WITHOUT_PERSON;

public class UserClassControl {

    private static UserClassControl instance;
    final Context context;
    private UserClass userClass;

    private UserClassControl(final Context context) {
        this.context = context;
    }

    public static UserClassControl getInstance(final Context context) {
        if(instance == null){
            instance = new UserClassControl(context);
        }
        return instance;
    }

    //Create Class
    private HashMap<String, String> getCreateClassParams() {

        HashMap<String, String> params = new HashMap<>();
        params.put("className", userClass.getClassName());
        params.put("classPassword", userClass.getPassword());
        params.put("classInstitution", userClass.getInstitution());
        params.put("classCutOff", String.valueOf(userClass.getCutOff()));
        params.put("classDescription", userClass.getDescription());
        params.put("classCreationDate", userClass.getCreationDate());
        params.put("idClassCreator", userClass.getIdClassCreator());
        params.put("classCreatorName", userClass.getCreatorName());

        return params;

    }

    public String validateCreateClass(String className, String classInstitution, Float classCutOff,
                                    String classPassword,String classPasswordConfirm, Float classAddition, Integer classSizeGroups,
                                    String classDescription, String classCreationDate, String idClassCreator,
                                    String classCreatorName) throws UserException {
        try {

            userClass = new UserClass(className, classInstitution, classCutOff, classPassword, classPasswordConfirm,
                    classAddition, classSizeGroups, classDescription,classCreationDate, idClassCreator, classCreatorName);
        }catch (UserException e){
            return e.getMessage();
        }

        return "";
    }

    public String createClass() throws UserException {

        RequestHandler requestHandler = new RequestHandler(URLs.URL_CREATE_CLASS, getCreateClassParams());

        String serverResponse = "404";

        try{
            serverResponse = requestHandler.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }
        Log.d("RESPONSE", ""+serverResponse);
        //dialog.dismiss();
        return serverResponse;

    }
    //End Create Class

    public String deleteClass(String classId, String userId) throws UserException {
        HashMap<String, String> params = new HashMap<>();
        Log.d("DELETE", classId);
        Log.d("DELETE", userId);


        params.put("classID", classId);
        params.put("userID", userId);

        RequestHandler requestHandler = new RequestHandler(URLs.URL_DELETE_CLASS, params);

        String serverResponse = "404";

        try{
            serverResponse = requestHandler.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }
        Log.d("RESPONSE", ""+serverResponse);
        //dialog.dismiss();
        return serverResponse;

    }




    public String validateJoinClass(UserClass userClass, String password, String studentEmail) throws UserClassException, ExecutionException, InterruptedException {
        String serverResponse;

        if(!password.isEmpty()){
            if(password.equals(userClass.getPassword())){
                String urlWithParameters = getStudentUrl(userClass, studentEmail);
                serverResponse = new PutDao(urlWithParameters, null, "")
                        .execute().get();
            }else{
                throw new UserClassException(context.getString(R.string.join_class_wrong_password_error));
            }
        }else{
            throw new UserClassException(context.getString(R.string.join_class_null_password_error));
        }
        return serverResponse;
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    private String getStudentUrl(UserClass userClass, String studentEmail){
        String url = "https://trezentos-api.herokuapp.com/api/class/user/student";
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        //builder.addQueryParameter("email", userClass.getOwnerEmail());
        builder.addQueryParameter("name", userClass.getClassName());
        builder.addQueryParameter("student", studentEmail);

        return builder.build().toString();
    }

    public ArrayList<UserClass> getExploreClasses(String userId) throws JSONException {
        String returnAllClassesUrlWithParameters = getClassesWithoutUserUrl(userId);

        String serverResponse = "404";
        serverResponse = new GetDao(returnAllClassesUrlWithParameters).get();

        Log.d("RESPONSE EXPLORE", serverResponse);

        JSONObject object = new JSONObject(serverResponse);
        String erro = object.getString("error");
        String message = object.getString("message");
        JSONArray classArrayJson = object.getJSONArray("classes");


        ArrayList<UserClass> userClasses = null;
        userClasses = getArrayList(classArrayJson);

        return userClasses;
    }

    public ArrayList<UserClass> getClasses(String userId) throws JSONException {
        String returnAllClassesUrlWithParameters = getClassesFromUserUrl(userId);

        String serverResponse = "404";
        serverResponse = new GetDao(returnAllClassesUrlWithParameters).get();

        Log.d("RESPONSE", serverResponse);


        JSONObject object = new JSONObject(serverResponse);
        String erro = object.getString("error");
        String message = object.getString("message");
        JSONArray classArrayJson = object.getJSONArray("classes");


        ArrayList<UserClass> userClasses = null;
        userClasses = getArrayList(classArrayJson);


        return userClasses;
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    private String getAllClassesAvaliableUrl() {
        HttpUrl.Builder builder = HttpUrl.parse(URL_ALL_CLASS_AVALIABLE).newBuilder();
        return builder.build().toString();
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    private String getClassesFromUserUrl(String userId) {
        HttpUrl.Builder builder = HttpUrl.parse(URL_CLASS_FROM_PERSON).newBuilder();
        builder.addQueryParameter("idPerson", userId);

        return builder.build().toString();
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    private String getClassesWithoutUserUrl(String userId) {
        HttpUrl.Builder builder = HttpUrl.parse(URL_CLASS_WITHOUT_PERSON).newBuilder();
        builder.addQueryParameter("idPerson", userId);

        return builder.build().toString();
    }

    private ArrayList<UserClass> getArrayList(JSONArray array) throws JSONException{


        ArrayList<UserClass> userClasses = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            UserClass userClass = getUserClassFromJson(array.getJSONObject(i));
            userClasses.add(userClass);
        }

        return userClasses;
    }

    private UserClass getUserClassFromJson(JSONObject jsonObject) {
        UserClass userClass = new UserClass();

        try{
            userClass.setIdClass(jsonObject.getString("idClass"));
            userClass.setIdClassCreator(jsonObject.getString("idClassCreator"));
            userClass.setClassName(jsonObject.getString("className"));
            userClass.setInstitution(jsonObject.getString("classInstitution"));
            userClass.setDescription(jsonObject.getString("classDescription"));
            userClass.setCreatorName(jsonObject.getString("classCreatorName"));
            userClass.setCreationDate(jsonObject.getString("classCreationDate"));
        }catch(JSONException | UserException e){
            e.printStackTrace();
        }

        return userClass;
    }

    private ArrayList<String> getStudentsFromJson(JSONArray jsonArray) {
        ArrayList<String> students = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            try{
                students.add(jsonArray.getString(i));
            }catch(JSONException e){
                e.printStackTrace();
            }
        }

        return students;
    }

    public String validateRate(Evaluation evaluation) throws UserClassException, ExecutionException, InterruptedException {
        String serverResponse = "";
        if (!evaluation.getStudentEmail().isEmpty()){
                //SaveRatePost saveRatePost = new SaveRatePost(evaluation);
                //serverResponse = saveRatePost.execute().get();
        } else {
            throw new UserClassException(context.getString(R.string.join_class_null_password_error));
        }

        return serverResponse;
    }

    public ArrayList<Student> getUsersFromClass(String idClass, String idExam) throws JSONException {
        HashMap<String, String> params = new HashMap<>();
        params.put("idClass", idClass);
        params.put("idExam", idExam);

        RequestHandler requestHandler = new RequestHandler(URLs.URL_STUDENTS_FROM_CLASS, params);

        String serverResponse = "404";

        try{
            serverResponse = requestHandler.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }

        JSONObject object = new JSONObject(serverResponse);
        String erro = object.getString("error");
        String message = object.getString("message");
        JSONArray classArrayJson = object.getJSONArray("users");

        ArrayList<Student> userClass = null;
        userClass = getUserArrayList(classArrayJson, idExam);

        return userClass;
    }

    public ArrayList<Student> getUserArrayList(JSONArray array, String idExam) throws JSONException{


        ArrayList<Student> users = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            Student userClass = getUsersFromJson(array.getJSONObject(i), idExam);
            users.add(userClass);
        }

        return users;
    }

    private Student getUsersFromJson(JSONObject jsonObject, String idExam) throws JSONException {
        Student user = new Student();
        JSONArray gradesArrayJson = jsonObject.getJSONArray("grades");

        try{
            user.setId(jsonObject.getString("idPerson"));
            user.setFirstName(jsonObject.getString("personFirstName"));
            user.setLastName(jsonObject.getString("personLastName"));
            user.setEmail(jsonObject.getString("personEmail"));
            for(int i = 0; i < gradesArrayJson.length(); i++) {
                if(gradesArrayJson.getJSONObject(i).getString("gradeType").equals("1")) {
                    user.setFirstGrade(Double.parseDouble(gradesArrayJson.getJSONObject(i).getString("grade")));
                } else {
                    user.setSecondGrade(Double.parseDouble(gradesArrayJson.getJSONObject(i).getString("grade")));
                }
            }


        }catch(JSONException | UserException e){
            e.printStackTrace();
        }

        return user;
    }
}
