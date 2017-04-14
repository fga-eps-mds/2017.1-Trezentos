package fga.mds.gpp.trezentos.Controller;


import android.util.StringBuilderPrinter;
import android.widget.EditText;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserClass;

public class UserClassControl {

    UserClass userClass;


    public UserClassControl(){

    }

    public void validateInformation(String className, String institution, String cutOff, String password,
                                    Integer idUserAccount, String addition, String sizeGroups) throws UserException {
        if(addition == null || cutOff == null || sizeGroups == null){
            throw new UserException("Preencha todos os campos!");
        }else {

            userClass = new UserClass(className, institution, Float.parseFloat(cutOff), password,
                    idUserAccount, Float.parseFloat(addition), Integer.parseInt(sizeGroups));

        }

    }



}
