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

import fga.mds.gpp.trezentos.DAO.getClassRequest;
import fga.mds.gpp.trezentos.DAO.CreateClassPost;
import fga.mds.gpp.trezentos.DAO.SignUpRequest;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;

public class UserClassControl {

    private static UserClassControl instance;
    private final Context context;

    private UserClassControl(final Context context){

        this.context = context;

    }

    public static UserClassControl getInstance(final Context context){

        if(instance == null){

            instance = new UserClassControl(context);

        }

        return instance;
    }

    public void validateCreateClass(String className, String institution, float cutOff, String password,
                                     float addition, Integer sizeGroups) throws UserException {

        UserClass userClass = new UserClass(className, institution, cutOff, password, addition, sizeGroups);

        CreateClassPost createClassPost = new CreateClassPost(userClass);
        createClassPost.execute();
    }


    public void validateInformation(UserClass user) throws UserException{

        int MIN_CLASSNAME_LENGHT = 3;
        int MAX_CLASSNAME_LENGHT = 20;
        int MIN_PASSWORD_LENGHT = 6;
        int MAX_PASSWORD_LENGHT = 16;
        int MIN_INSTITUITION_LENGHT = 2;
        int MAX_INSTITUITION_LENGHT = 30;

        if(user.getClassName() == null || user.getPassword() == null ||
                user.getCutOff() <= 0 || user.getSizeGroups() <= 0 ||
                user.getAddition() <= 0 || user.getInstitution() == null){

            throw new UserException("Preencha todos os campos.");

        }
        else{

            if(user.getInstitution().length() < MIN_INSTITUITION_LENGHT ||
                    user.getInstitution().length() > MAX_INSTITUITION_LENGHT){

                throw new UserException("A instituição deve ter de 2 a 30 caracteres.");

            }

            if(user.getClassName().length() < MIN_CLASSNAME_LENGHT ||
                    user.getClassName().length() > MAX_CLASSNAME_LENGHT){

                throw new UserException("O nome da sala deve ter de 3 a 20 caracteres.");

            }

                if(user.getPassword().length() < MIN_PASSWORD_LENGHT ||
                    user.getPassword().length() > MAX_PASSWORD_LENGHT){

                throw new UserException("A senha deve ter de 6 a 16 caracteres.");
            }

        }

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
        }


        return userClass;
    }


}
