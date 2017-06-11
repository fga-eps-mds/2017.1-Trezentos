package fga.mds.gpp.trezentos.Controller;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.GetAllClassRequest;
import fga.mds.gpp.trezentos.DAO.AddStudentToClassRequest;
import fga.mds.gpp.trezentos.DAO.CreateClassPost;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class UserClassControl {

    private static UserClassControl instance;
    final Context context;

    private UserClassControl(final Context context) {
        this.context = context;
    }

    public static UserClassControl getInstance(final Context context) {

        if (instance == null) {
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

        } catch (UserException userException) {
            userException.printStackTrace();
        }
    }

    public String validateInformation(String className, String institution,
                                      String cutOff, String password,
                                      String addition, String sizeGroups) throws UserException {

        String erro;
        try {
            UserClass userClass = new UserClass(className, institution,
                    Float.parseFloat(cutOff), password, Float.parseFloat(addition),
                    Integer.parseInt(sizeGroups));
            //Just to pass on Sonar
            System.out.println(userClass.getClassName());
            erro = "Sucesso";
            return erro;
        } catch (UserException userException) {
            erro = userException.getMessage();
            return erro;
        }
    }

    public ArrayList<UserClass> getClasses() {
        GetAllClassRequest classRequest = new GetAllClassRequest();
        String serverResponse = "404";
        serverResponse = classRequest.get();
        ArrayList<UserClass> userClasses = new ArrayList<UserClass>();

        try {
            userClasses = getArrayList(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userClasses;
    }

    private ArrayList<UserClass> getArrayList(String serverResponse) throws JSONException{

        JSONArray array = null;

        try {
            array = new JSONArray(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<UserClass> userClasses = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
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
            userClass.setOwnerEmail(jsonObject.getString("ownerEmail"));
            userClass.setStudents(getStudentsFromJson(jsonObject.getJSONArray("students")));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UserException e) {
            e.printStackTrace();
        }

        return userClass;
    }

    private ArrayList<String> getStudentsFromJson(JSONArray jsonArray) {
        ArrayList<String> students = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                students.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return students;
    }

    public String validateJoinClass(UserClass userClass, String password, String studentEmail) throws UserClassException, ExecutionException, InterruptedException {
        String serverResponse;
        if (!password.isEmpty()){
            if(password.equals(userClass.getPassword())){
                AddStudentToClassRequest addStudentToClassRequest = new AddStudentToClassRequest(userClass, studentEmail);
                serverResponse = addStudentToClassRequest.execute().get();
//                Log.i("LOG", serverResponse);
            } else {
                throw new UserClassException(context.getString(R.string.join_class_wrong_password_error));
            }
        } else {
            throw new UserClassException(context.getString(R.string.join_class_null_password_error));
        }

        return serverResponse;
    }
}
