package fga.mds.gpp.trezentos.Model;

public class UserAccount {

    private Integer idUserAccount;
    private String user;
    private String password;

    public void UserAccount(Integer idUserAccount, String user, String password){
        this.idUserAccount = idUserAccount;
        this.user = user;
        this.password = password;

    }

    public Integer getIdUserAccount(){
        return idUserAccount;
    }

    public void setIdUserAccount(Integer idUserAccount){
        this.idUserAccount = idUserAccount;

    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;

    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;

    }
}