package fga.mds.gpp.trezentos.Controller;


import android.widget.EditText;

import fga.mds.gpp.trezentos.DAO.CreateClassPost;
import fga.mds.gpp.trezentos.DAO.SignUpRequest;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;

public class UserClassControl {

    UserClass userClass;

    public UserClassControl(){

    }

    public void validateCreateClass(String className, String institution, float cutOff, String password,
                                    Integer idUserAccount, float addition, Integer sizeGroups) throws UserException {

        userClass = new UserClass(className, institution, cutOff, password, idUserAccount, addition, sizeGroups);



        CreateClassPost createClassPost = new CreateClassPost(userClass);
        createClassPost.execute();
    }


    public void validateInformation(UserClass user) throws UserException{

        int MIN_CLASSNAME_LENGHT = 3;
        int MAX_CLASSNAME_LENGHT = 20;
        int MIN_PASSWORD_LENGHT = 6;
        int MAX_PASSWORD_LENGHT = 16;


        if(user.getClassName() == null || user.getPassword() == null ||
                user.getCutOff() <= 0 || user.getSizeGroups() <= 0 || user.getAddition() <= 0){

            throw new UserException("Preencha todos os campos.");

        }
        else{
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

}
