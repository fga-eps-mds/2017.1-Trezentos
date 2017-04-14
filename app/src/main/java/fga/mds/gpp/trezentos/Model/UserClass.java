package fga.mds.gpp.trezentos.Model;


import java.io.Serializable;

import fga.mds.gpp.trezentos.Exception.UserException;

public class UserClass implements Serializable {

    private String className;
    private String institution;
    private float cutOff;
    private int idUserAccount;
    private String password;
    private float addition;
    private int sizeGroups;

    public UserClass(){


    }

    public UserClass(String className, String institution, float cutOff, String password,
                     Integer idUserAccount, float addition, int sizeGroups ) throws UserException{

        setClassName(className);
        setInstitution(institution);
        setCutOff(cutOff);
        setPassword(password);
        setIdUserAccount(idUserAccount);
        setAddition(addition);
        setSizeGroups(sizeGroups);

    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) throws UserException {
        int MIN_CLASSNAME_LENGTH = 3;
        int MAX_CLASSNAME_LENGTH = 20;

        if(className != null && !className.isEmpty()){

            if(className.length() < MIN_CLASSNAME_LENGTH || className.length() > MAX_CLASSNAME_LENGTH){

                throw new UserException("O nome da sala deve ter de 3 a 20 caracteres.");
            }else{

                this.className = className;
            }
        }else {

            throw new UserException("Preencha todos os campos!");
        }
    }

    public int getSizeGroups() {
        return sizeGroups;
    }

    public void setSizeGroups(int sizeGroups) throws UserException {
        if(sizeGroups > 0){

            this.sizeGroups = sizeGroups;

        }else{

            throw new UserException("O tamanho do grupo nao pode ser zero.");
        }

    }


    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Integer getIdUserAccount() {
        return idUserAccount;
    }

    public void setIdUserAccount(Integer idUserAccount) { this.idUserAccount = idUserAccount;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws UserException {

        int MIN_PASSWORD_LENGTH = 6;
        int MAX_PASSWORD_LENGTH = 16;

        if(password != null && !password.isEmpty()){

            if(password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH){

                throw new UserException("A senha deve ter de 6 a 16 caracteres.");

            }else{

                this.password = password;
            }
        }else {

            throw new UserException("Preencha todos os campos!");
        }

    }

    public float getAddition() {
        return addition;
    }

    public void setAddition(float addition) throws UserException {

        if(addition != 0){

            this.addition = addition;

        }else{

            throw new UserException("O acrescimo nao pode ser zero.");
        }
    }

    public float getCutOff() {
        return cutOff;
    }

    public void setCutOff(Float cutOff) throws UserException {

        if(cutOff != 0){

            this.cutOff = cutOff;

        }else{

            throw new UserException("A nota de corte nao pode ser zero.");
        }

    }


}
