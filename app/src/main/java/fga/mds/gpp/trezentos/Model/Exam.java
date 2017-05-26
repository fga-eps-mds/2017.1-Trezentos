package fga.mds.gpp.trezentos.Model;

import java.io.Serializable;

import fga.mds.gpp.trezentos.Exception.UserException;

public class Exam implements Serializable {
    private String nameExam;
    private String userClassName;
    private String classOwnerEmail;

    public Exam(){
        //An empty constructor is needed to create a new instance of object,
        //in addition is create constructors with arguments.
    }

    public Exam(String nameExam, String userClassName, String classOwnerEmail)
            throws UserException{
        setNameExam(nameExam);
        setUserClassName(userClassName);
        setClassOwnerEmail(classOwnerEmail);
    }

    public void setNameExam(String nameExam)throws UserException{
        final Integer MAX_NAME_EXAM_LENGTH = 15;
        final Integer MIN_NAME_EXAM_LENGTH = 2;

        if(nameExam != null && !nameExam.isEmpty()){
            if(nameExam.length() < MIN_NAME_EXAM_LENGTH
                    || nameExam.length() > MAX_NAME_EXAM_LENGTH){
                throw new UserException("O nome da prova " +
                        "deve ter entre 2 e 15 caracteres.");
            }else{
                this.nameExam = nameExam;
            }
        }else{
            throw new UserException("O nome não pode ser vazio");
        }
    }

    public String getNameExam(){
        return nameExam;
    }

    public void setUserClassName(String userClassName) throws UserException{
        this.userClassName = userClassName;
    }

    public String getUserClassName(){
        return userClassName;
    }

    public void setClassOwnerEmail(String classOwnerEmail)throws UserException {
        this.classOwnerEmail = classOwnerEmail;
    }

    public String getClassOwnerEmail() {
        return classOwnerEmail;
    }
}
