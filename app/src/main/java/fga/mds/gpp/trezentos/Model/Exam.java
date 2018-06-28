package fga.mds.gpp.trezentos.Model;


import java.io.Serializable;

import fga.mds.gpp.trezentos.Exception.UserException;

public class Exam implements Serializable {
    private String id;
    private String nameExam;
    private String idClassCreator;
    private String idPerson;
    private String idClass;
    private String firstGrades;
    private String secondGrades;

    public Exam(){
        //An empty constructor is needed to create a new instance of object,
        //in addition is create constructors with arguments.
    }

    public Exam(String nameExam, String idPerson, String idClassCreator, String idClass)
            throws UserException {
        setNameExam(nameExam);
        setIdPerson(idPerson);
        setIdClassCreator(idClassCreator);
        setIdClass(idClass);

    }

    public void setNameExam(String nameExam)throws UserException{
        final Integer MAX_NAME_EXAM_LENGTH = 15;
        final Integer MIN_NAME_EXAM_LENGTH = 2;

        if(nameExam != null && !nameExam.isEmpty()){
            if(nameExam.length() < MIN_NAME_EXAM_LENGTH
                    || nameExam.length() > MAX_NAME_EXAM_LENGTH){
                throw new UserException("O nome da prova deve ter entre 2 e 15 caracteres.");
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

    public String getIdClassCreator() {
        return idClassCreator;
    }

    public void setIdClassCreator(String idClassCreator) {
        this.idClassCreator = idClassCreator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
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
