package fga.mds.gpp.trezentos.Model;


import java.io.Serializable;

import fga.mds.gpp.trezentos.Exception.UserException;

public class Exam implements Serializable {
    private String nameExam;
    private String userClassName;
    private String classOwnerEmail;
    private String firstGrades;
    private String secondGrades;

    public Exam(){
        //An empty constructor is needed to create a new instance of object,
        //in addition is create constructors with arguments.
    }

    public Exam(String nameExam, String userClassName, String classOwnerEmail)
            throws UserException {
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

    public String getNameExam() {
        return nameExam;
    }

    public void setUserClassName(String userClassName) throws UserException{

        if(userClassName != null && !userClassName.isEmpty()){
            this.userClassName = userClassName;

        }else{
            throw new UserException("O nome não pode ser vazio");
        }

    }

    public String getUserClassName() {
        return userClassName;
    }

    public void setClassOwnerEmail(String classOwnerEmail)throws UserException {
        if (classOwnerEmail != null && !classOwnerEmail.isEmpty()){
            this.classOwnerEmail = classOwnerEmail;
        }else{
            throw new UserException("O email não pode estar vazio");
        }
    }

    public String getClassOwnerEmail() {
        return classOwnerEmail;
    }

    public void setFirstGrades(String firstGrades) {
        this.firstGrades = firstGrades;
    }

    public String getFirstGrades() {
        return firstGrades;
    }

    public void setSecondGrades(String secondGrades) {
        this.secondGrades = secondGrades;
    }

    public String getSecondGrades() {
        return secondGrades;
    }
}
