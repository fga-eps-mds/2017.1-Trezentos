package fga.mds.gpp.trezentos.Model;


import java.io.Serializable;
import fga.mds.gpp.trezentos.Exception.UserException;

public class UserClass implements Serializable {
    private String className;
    private String institution;
    private float cutOff;
    private String password;
    private float addition;
    private int sizeGroups;

    public UserClass(){

    }

    public UserClass(String className, String institution, float cutOff, String password,
                     float addition, Integer sizeGroups) throws UserException{
        setClassName(className);
        setInstitution(institution);
        setCutOff(cutOff);
        setPassword(password);
        setAddition(addition);
        setSizeGroups(sizeGroups);
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className) throws UserException{
        int MIN_CLASSNAME_LENGTH = 3;
        int MAX_CLASSNAME_LENGTH = 20;

        if((className != null && !className.isEmpty())
                &&(className.length() < MIN_CLASSNAME_LENGTH
                || className.length() > MAX_CLASSNAME_LENGTH)){

            throw new UserException("@string/msg_class_name_case_error_message");
        }else if(className == null || className.isEmpty()){
            throw new UserException("@string/msg_empty_space_error_message");
        }else{
            this.className = className;
        }
    }

    public int getSizeGroups(){
        return sizeGroups;
    }

    public void setSizeGroups(int sizeGroups) throws UserException{
        if(sizeGroups > 0){
            this.sizeGroups = sizeGroups;
        }else{
            throw new UserException("@string/msg_len_size_group_error_message");
        }
    }

    public String getInstitution(){
        return institution;
    }

    public void setInstitution(String institution)throws UserException{
        int MIN_INSTITUTION_LENGTH = 3;
        int MAX_INSTITUTION_LENGTH = 20;

        if((institution != null && !institution.isEmpty()) &&
                (institution.length() < MIN_INSTITUTION_LENGTH
                        || institution.length() > MAX_INSTITUTION_LENGTH)){

            throw new UserException("@string/msg_institution_case_error_message");
        }else{
            this.institution = institution;
        }
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) throws UserException{
        int MIN_PASSWORD_LENGTH = 6;
        int MAX_PASSWORD_LENGTH = 16;

        if((password != null && !password.isEmpty()) &&
                (password.length() < MIN_PASSWORD_LENGTH ||
                        password.length() > MAX_PASSWORD_LENGTH)){

            throw new UserException("@string/msg_len_password_error_message");

        }
        else if(password == null || password.isEmpty()){
            throw new UserException("@string/msg_empty_space_error_message");

        }else{
            this.password = password;
        }
    }

    public float getAddition(){
        return addition;
    }

    public void setAddition(float addition) throws UserException{
        if(addition != 0){
            this.addition = addition;
        }else{
            throw new UserException("@string/msg_len_addition_error_message");
        }
    }

    public float getCutOff() {
        return cutOff;
    }

    public void setCutOff(float cutOff) throws UserException {
        if(cutOff != 0){
            this.cutOff = cutOff;
        }else{
            throw new UserException("@string/msg_len_cut_off_error_message");
        }
    }
}