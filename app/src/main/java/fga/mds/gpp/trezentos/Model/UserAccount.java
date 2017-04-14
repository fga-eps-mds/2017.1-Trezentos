package fga.mds.gpp.trezentos.Model;

import fga.mds.gpp.trezentos.Exception.UserException;

public class UserAccount {


    private Integer idUserAccount;
    private String email;
    private String name;
    private String password;

    public UserAccount(){
        
    }

    public UserAccount(Integer idUserAccount, String email, String password) throws UserException {
        this.idUserAccount = idUserAccount;
        this.email = email;
        this.password = password;

    }

    public Integer getIdUserAccount(){
        return idUserAccount;
    }

    public void setIdUserAccount(Integer idUserAccount){
        this.idUserAccount = idUserAccount;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;

    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) throws UserException {

        this.email = email;

    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;

    }

}