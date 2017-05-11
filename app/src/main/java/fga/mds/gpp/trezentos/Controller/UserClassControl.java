package fga.mds.gpp.trezentos.Controller;


import android.content.Context;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.getAllClassRequest;
import fga.mds.gpp.trezentos.DAO.getClassRequest;
import fga.mds.gpp.trezentos.DAO.CreateClassPost;
import fga.mds.gpp.trezentos.DAO.SignUpRequest;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;

public class UserClassControl {

    private static UserClassControl instance;
    private final Context context;

    public UserClassControl(final Context context){

        this.context = context;

    }


    public static UserClassControl getInstance(final Context context){

        if(instance == null){

            instance = new UserClassControl(context);

        }

        return instance;
    }

    public void validateCreateClass(String className, String institution,
                                    Float cutOff, String password, Float addition,
                                    Integer sizeGroups, String email) throws UserException {

        try {
            UserClass userClass = new UserClass(className, institution, cutOff, password, addition, sizeGroups);

            CreateClassPost createClassPost = new CreateClassPost(userClass, email);

            createClassPost.execute();

        }catch (UserException userException){
            userException.printStackTrace();
        }


    }


    public String validateInformation(String className, String institution, String cutOff, String password,
                                         String addition, String sizeGroups) throws UserException{

            String erro;
             try{
                 UserClass userClass = new UserClass(className, institution, Float.parseFloat(cutOff),
                  password, Float.parseFloat(addition), Integer.parseInt(sizeGroups));

                 erro = "Sucesso";
                 return erro;
             }catch (UserException userException){

                 erro = userException.getMessage();
                 return erro;
             }
    }


    public ArrayList<UserClass> getClasses() {

        getAllClassRequest classRequest = new getAllClassRequest();

        String serverResponse = "404";

        try {
            serverResponse = classRequest.execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<UserClass> userClasses = new ArrayList<UserClass>();

        try {
            userClasses = getArrayList(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userClasses;

    }

    public ArrayList<UserClass> getClassesFromUser(String email) {

        getClassRequest classRequest = new getClassRequest(email);

        String serverResponse = "404";

        try {
            serverResponse = classRequest.execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<UserClass> userClasses = new ArrayList<UserClass>();

        try {
            userClasses = getArrayList(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userClasses;

    }

    private ArrayList<UserClass> getArrayList(String serverResponse) throws JSONException {

        JSONArray array = null;

        try {
            array = new JSONArray(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<UserClass> userClasses = new ArrayList<>();

        for(int i = 0; i < array.length(); i++){

            UserClass userClass = getUserClassFromJson(array.getJSONObject(i));

            userClasses.add(userClass);

        }

        return userClasses;
    }

    private UserClass getUserClassFromJson(JSONObject jsonObject) {

        UserClass userClass = new UserClass();


        try {
            userClass.setClassName(jsonObject.getString("name"));
            userClass.setInstitution(jsonObject.getString("institution"));
            userClass.setCutOff(Float.parseFloat(jsonObject.getString("passingScore")));
            userClass.setAddition(Float.parseFloat(jsonObject.getString("additionScore")));
            userClass.setPassword(jsonObject.getString("password"));
            userClass.setSizeGroups(Integer.parseInt(jsonObject.getString("numberOfStudentsPerGroup")));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UserException e) {
            e.printStackTrace();
        }


        return userClass;
    }


}
