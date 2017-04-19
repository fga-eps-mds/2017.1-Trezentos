package fga.mds.gpp.trezentos.Model;


import fga.mds.gpp.trezentos.Exception.UserException;

public class Exam {


    private String nameExam;
    private String userClassName;
    private String classOwnerEmail;




    public Exam(){


    }

    public Exam(String nameExam, String userClassName, String classOwnerEmail)throws UserException {

        this.nameExam = nameExam;
        this.userClassName = userClassName;
        this.classOwnerEmail = classOwnerEmail;
    }


    public String getNameExam(){
        return nameExam;
    }

    public String getUserClassName(){
        return userClassName;
    }

    public String getClassOwnerEmail(){
        return classOwnerEmail;
    }

    @Override
    public String toString() {
        return "\n"+"Prova: "+ nameExam+ "\n";
    }


    public void setClassOwnerEmail(String classOwnerEmail)throws UserException {
        this.classOwnerEmail = classOwnerEmail;
    }

    public void setNameExam(String nameExam)throws UserException {
        this.nameExam = nameExam;
    }

    public void setUserClassName(String userClassName) throws UserException{
        this.userClassName = userClassName;
    }
}
