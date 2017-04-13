package fga.mds.gpp.trezentos.Model;

import android.content.Context;
import android.content.res.Resources;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fga.mds.gpp.trezentos.DAO.UserDao;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;

public class UserAccount {

    private static UserDao userDao = new UserDao();
    private String email;
    private String name;

    private String password;
    private String passwordConfirmation;

    public Context context;

    /* metodos */

    public UserAccount() {

    }

    public UserAccount(String name, String email, String password, String passwordConfirmation) throws UserException {
            setName(name);
            setEmail(email);
            setPasswordConfirmation(passwordConfirmation);
            setPassword(password);
    }

    public void setName(String name) throws UserException {
        Integer MAX_NAME_LENGTH = 50;
        Integer MIN_NAME_LENGTH = 3;

        if (name != null && !name.isEmpty()) {
            if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
                throw new UserException("O nome deve ter de 3 a 50 caracteres");
            }else{
                this.name = name;
            }
        } else {
            throw new UserException("O nome não pode estar vazio");
        }
    }

    public void setEmail(String email) throws UserException {
        if (email != null && !email.isEmpty()) {
            Integer MAX_EMAIL_LENGTH = 50;
            Integer MIN_EMAIL_LENGTH = 5;

            if (email.length() < MIN_EMAIL_LENGTH || email.length() > MAX_EMAIL_LENGTH) {
                throw new UserException("O email deve ter entre 5 e 50 caracteres válidos");
            }
            /*validaçao caracteres*/
            else {
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);

                    if (matcher.matches()){
                        email.toLowerCase();
                        this.email = email;
                    }
                    else
                        throw new UserException("Email com caracteres inválidos. Tente novamente");
            }
        }else {
            throw new UserException("O email não pode estar vazio");
        }
    }

    public void setPassword(String password) throws UserException {
            ValidatePassword(password);
    }

    public void ValidatePassword (String password) throws UserException{
        if (password != null && !password.isEmpty()) {
            Integer MIN_PASSWORD_LENGTH = 6;
            Integer MAX_PASSWORD_LENGTH = 16;

            if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
                throw new UserException("A senha deve ter entre 6 e 16 caracteres");
            }else {
                if (!password.equals(passwordConfirmation)) {
                    throw new UserException("Senhas não coincidem, tente novamente");
                } else {
                    this.password = password;
                }
            }
        } else {
            throw new UserException("A senha não pode estar vazia");
        }

    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

}




