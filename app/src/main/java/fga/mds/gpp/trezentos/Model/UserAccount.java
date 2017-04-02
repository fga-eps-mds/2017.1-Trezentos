package fga.mds.gpp.trezentos.Model;

import fga.mds.gpp.trezentos.DAO.UserDao;

public class UserAccount {


    private static UserDao userDao = new UserDao();
    private Integer idUserAccount;
    private String name;
    private String user;
    private String password;


    public UserAccount(){
        
    }

    public UserAccount(Integer idUserAccount, String user, String password){
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

    public String getName() { return name;}

    public void setName(String name) {
        this.name = name;

    }

    public static void authenticateLogin(UserAccount userAccount){

        userDao.authenticate(userAccount);

    }

    public static void insertData(UserAccount userAccount){

        userDao.insert(userAccount);

    }

}