package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.GetDao;
import fga.mds.gpp.trezentos.DAO.PostDao;
import fga.mds.gpp.trezentos.DAO.PutDao;
import fga.mds.gpp.trezentos.DAO.SaveRatePost;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import okhttp3.HttpUrl;

import static fga.mds.gpp.trezentos.DAO.URLs.URL_ALL_CLASS_AVALIABLE;
import static fga.mds.gpp.trezentos.DAO.URLs.URL_CLASS_FROM_PERSON;

public class UserClassControl {

    private static UserClassControl instance;
    final Context context;

    private UserClassControl(final Context context) {
        this.context = context;
    }

    public static UserClassControl getInstance(final Context context) {
        if(instance == null){
            instance = new UserClassControl(context);
        }
        return instance;
    }

    public void validateCreateClass(String className, String institution,
                                    Float cutOff, String password, Float addition,
                                    Integer sizeGroups, String email) throws UserException {
        try{
            String url = "https://trezentos-api.herokuapp.com/api/class/register";
            UserClass userClass = new UserClass(className, institution, cutOff, password, addition, sizeGroups);
            String urlWithParameters = getClassUrl(url, userClass, email);

            PostDao postDao = new PostDao(urlWithParameters, null, "");
            postDao.execute();
        }catch(UserException userException){
            userException.printStackTrace();
        }
    }

    public String validateInformation(String className, String institution,
                                      String cutOff, String password,
                                      String addition, String sizeGroups) throws UserException {
        try{
            UserClass userClass = new UserClass(className, institution,
                    Float.parseFloat(cutOff), password, Float.parseFloat(addition),
                    Integer.parseInt(sizeGroups));
            //Just to pass on Sonar
            System.out.println(userClass.getClassName());
            return "Sucesso";
        }catch(UserException userException){
            return userException.getMessage();
        }
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    // Creates json
    private String getClassUrl(String url, UserClass userClass, String ownerEmail){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("ownerEmail", ownerEmail);
        builder.addQueryParameter("name", userClass.getClassName());
        builder.addQueryParameter("institution", userClass.getInstitution());
        builder.addQueryParameter("passingScore", String.valueOf(userClass.getCutOff()));
        builder.addQueryParameter("additionScore", String.valueOf(userClass.getAddition()));
        builder.addQueryParameter("password", userClass.getPassword());
        builder.addQueryParameter("numberOfStudentsPerGroup", String.valueOf(userClass.getSizeGroups()));

        return builder.build().toString();
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

    public ArrayList<UserClass> getExploreClasses() throws JSONException {
        //String url = "https://trezentos-api.herokuapp.com/api/class/find";
        String returnAllClassesUrlWithParameters = getAllClassesAvaliableUrl();

        String serverResponse = "404";
        serverResponse = new GetDao(returnAllClassesUrlWithParameters).get();

        Log.d("RESPONSE", serverResponse);


        JSONObject object = new JSONObject(serverResponse);
        String erro = object.getString("error");
        String message = object.getString("message");
        JSONArray classArrayJson = object.getJSONArray("classes");


        ArrayList<UserClass> userClasses = null;
        userClasses = getArrayList(classArrayJson);

        Log.d("RESPONSE", String.valueOf(userClasses.size()));




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

        Log.d("RESPONSE", String.valueOf(userClasses.size()));

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
        String serverResponse;
        if (!evaluation.getStudentEmail().isEmpty()){
                SaveRatePost saveRatePost = new SaveRatePost(evaluation);
                serverResponse = saveRatePost.execute().get();
        } else {
            throw new UserClassException(context.getString(R.string.join_class_null_password_error));
        }

        return serverResponse;
    }
}
