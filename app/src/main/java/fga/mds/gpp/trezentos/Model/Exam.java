package fga.mds.gpp.trezentos.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        final Integer MAX_NAME_EXAM_LENGTH = 3;
        final Integer MIN_NAME_EXAM_LENGTH = 20;

        if(userClassName != null && !userClassName.isEmpty()){
            if(userClassName.length() < MIN_NAME_EXAM_LENGTH
                    || userClassName.length() > MAX_NAME_EXAM_LENGTH){
                throw new UserException("O nome da sala deve ter de 3 a 20 caracteres.");
            }else{
                this.userClassName = userClassName;
            }
        }else{
            throw new UserException("O nome não pode ser vazio");
        }

    }

    public String getUserClassName(){
        return userClassName;
    }

    public void setClassOwnerEmail(String classOwnerEmail)throws UserException {
        if (classOwnerEmail != null && !classOwnerEmail.isEmpty()){
            final Integer MAX_EMAIL_LENGTH = 50;
            final Integer MIN_EMAIL_LENGTH = 5;

            if(classOwnerEmail.length() < MIN_EMAIL_LENGTH
                    || classOwnerEmail.length() > MAX_EMAIL_LENGTH){

            }else{
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(classOwnerEmail);

                if(matcher.matches()){
                    classOwnerEmail.toLowerCase();
                    this.classOwnerEmail = classOwnerEmail;
                } else
                    throw new UserException("Email com caracteres inválidos. Tente novamente");
            }
        }else{
            throw new UserException("O email não pode estar vazio");
        }
    }

    public String getClassOwnerEmail() {
        return classOwnerEmail;
    }
}