package fga.mds.gpp.trezentos.Model;

import fga.mds.gpp.trezentos.Exception.UserException;

public class Exam{
    private String nameExam;
    private String userClassName;
    private String classOwnerEmail;

    public Exam(){
        //An empty constructor is needed to create a new instance of object,
        //in addition is create constructors with arguments.
    }

    public Exam(String nameExam, String userClassName, String classOwnerEmail)
            throws UserException{
        this.nameExam = nameExam;
        this.userClassName = userClassName;
        this.classOwnerEmail = classOwnerEmail;
    }

    public void setNameExam(String nameExam)throws UserException{
        final Integer MAX_NAME_EXAM_LENGTH = 15;
        final Integer MIN_NAME_EXAM_LENGTH = 2;

        if(nameExam != null && !nameExam.isEmpty()){
            if(nameExam.length() < MIN_NAME_EXAM_LENGTH
                    || nameExam.length() > MAX_NAME_EXAM_LENGTH){
                throw new UserException("@string/msg_len_name_exam_error");
            }else{
                this.nameExam = nameExam;
            }
        }else{
            throw new UserException("@string/msg_null_name_exam_error");
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

    public void setClassOwnerEmail(String classOwnerEmail)throws UserException{
        this.classOwnerEmail = classOwnerEmail;
    }

    public String getClassOwnerEmail(){
        return classOwnerEmail;
    }

    @Override
    public String toString(){
        return "\n"+"@string/exam_to_string"+ nameExam+ "\n";
    }
}