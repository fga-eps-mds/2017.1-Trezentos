package fga.mds.gpp.trezentos.Model;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Util.PasswordUtil;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserAccount{

    private String id;
    private String email;
    private String fisrtName;
    private String lastName;

    private String password;

    private Boolean isFromFacebook;

    private String telephoneDDI;
    private String telephoneDDD;
    private String telephoneNumber;



    private Context context;

    public UserAccount(){
        //An empty constructor is needed to create a new instance of object,
        //in addition is create constructors with arguments.
    }



    public UserAccount(String firstName,
                       String lastName,
                       String email,
                       String telephoneDDI,
                       String telephoneDDD,
                       String telephoneNumber,
                       String password,
                       String passwordConfirmation) throws UserException{

        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);

        setTelephoneDDI(telephoneDDI);
        setTelephoneDDD(telephoneDDD);
        setTelephoneNumber(telephoneNumber);

        setPassword(password, passwordConfirmation);

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String fisrtName) throws UserException{
        final Integer MAX_NAME_LENGTH = 50;
        final Integer MIN_NAME_LENGTH = 3;

        if (fisrtName != null && !fisrtName.isEmpty()){
            if (fisrtName.length() < MIN_NAME_LENGTH
                    || fisrtName.length() > MAX_NAME_LENGTH){
                throw new UserException("O nome deve ter de 3 a 50 caracteres");
            } else {
                this.fisrtName = fisrtName;
            }
        } else {
            throw new UserException("O nome não pode estar vazio");
        }
    }

    public void setLastName(String lastName) throws UserException{
        final Integer MAX_NAME_LENGTH = 50;
        final Integer MIN_NAME_LENGTH = 3;

        if (lastName != null && !lastName.isEmpty()){
            if (lastName.length() < MIN_NAME_LENGTH
                    || lastName.length() > MAX_NAME_LENGTH){
                throw new UserException("O nome deve ter de 3 a 50 caracteres");
            } else {
                this.lastName = lastName;
            }
        } else {
            throw new UserException("O nome não pode estar vazio");
        }
    }


    public void setEmail(String email) throws UserException{
        if (email != null && !email.isEmpty()){
            final Integer MAX_EMAIL_LENGTH = 50;
            final Integer MIN_EMAIL_LENGTH = 5;

            if(email.length() < MIN_EMAIL_LENGTH
                    || email.length() > MAX_EMAIL_LENGTH){

            }else{
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);

                if(matcher.matches()){
                    email.toLowerCase();
                    this.email = email;
                } else
                    throw new UserException("Email com caracteres inválidos. Tente novamente");
            }
        }else{
            throw new UserException("O email não pode estar vazio");
        }
    }

    public void setTelephoneDDI(String telephoneDDI) throws UserException{
        if(telephoneDDI.length() < 3 || telephoneDDI.length() > 3 ){
            throw new UserException("DDI inválido");
        } else {
            this.telephoneDDI = telephoneDDI;
        }
    }

    public void setTelephoneDDD(String telephoneDDD) throws UserException{
        if(telephoneDDD.length() < 3 || telephoneDDD.length() > 3 ) {
            throw new UserException("DDD inválido");
        }else{
            this.telephoneDDD = telephoneDDD;
        }
    }

    public void setTelephoneNumber(String telephoneNumber) throws UserException{
        if(telephoneNumber.length() < 9 || telephoneNumber.length() > 9 ) {
            throw new UserException("Número deve ter 9 dígitos");
        }else{
            this.telephoneNumber = telephoneNumber;
        }
    }

    public void setIsFromFacebook(boolean isFromFacebook) throws UserException{
        this.isFromFacebook = isFromFacebook;
    }

    private void validatePassword(String password, String passwordConfirmation) throws UserException{
        final Integer MIN_PASSWORD_LENGTH = 6;
        final Integer MAX_PASSWORD_LENGTH = 16;

        if(password != null && !password.isEmpty()){

            if(password.length() < MIN_PASSWORD_LENGTH
                    || password.length() > MAX_PASSWORD_LENGTH){
                throw new UserException("A senha deve ter entre 6 e 16 caracteres");
            }else{
                if(!password.equals(passwordConfirmation)){
                    throw new UserException("Senhas não coincidem, tente novamente");
                }else{
                    this.password = password;
                }
            }
        }else{
            throw new UserException("A senha não pode estar vazia");
        }
    }

    public void authenticatePassword(String password) throws UserException{
        if(password != null && !password.isEmpty()){
            this.password = password;
        }else{
            throw new UserException("A senha não pode estar vazia");
        }
    }

    private void setPassword(String password, String passwordConfirmation) throws UserException {
        validatePassword(password, passwordConfirmation);
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return fisrtName + " " + lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){ return password; }

    public String getTelephoneDDI() { return telephoneDDI; }

    public String getTelephoneDDD() { return telephoneDDD; }

    public String getTelephoneNumber() { return telephoneNumber; }

    public String getFisrtName() {
        return fisrtName;
    }

    public String getLastName() {
        return lastName;
    }
}